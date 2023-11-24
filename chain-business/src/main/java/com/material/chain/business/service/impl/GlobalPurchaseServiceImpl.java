package com.material.chain.business.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.material.chain.base.exception.ApiException;
import com.material.chain.base.page.PageResponse;
import com.material.chain.base.page.PageUtil;
import com.material.chain.base.redis.RedissonLockManager;
import com.material.chain.base.utils.AppContextUtil;
import com.material.chain.business.constant.TopicConstant;
import com.material.chain.business.domain.dto.PurchaseOrderAddressDTO;
import com.material.chain.business.domain.dto.PurchaseOrderDTO;
import com.material.chain.business.domain.dto.PurchaseOrderItemDTO;
import com.material.chain.business.domain.dto.PurchasePageDTO;
import com.material.chain.business.domain.po.*;
import com.material.chain.business.domain.vo.PurchaseOrderAddressVo;
import com.material.chain.business.domain.vo.PurchaseOrderItemVo;
import com.material.chain.business.domain.vo.PurchaseOrderVo;
import com.material.chain.business.enums.OrderEnum;
import com.material.chain.business.enums.PayEnum;
import com.material.chain.business.enums.PurchasePlatformEnum;
import com.material.chain.business.mapper.GlobalPurchaseAddressPoMapper;
import com.material.chain.business.mapper.GlobalPurchaseItemPoMapper;
import com.material.chain.business.mapper.GlobalPurchaseOrderPoMapper;
import com.material.chain.business.service.MaterialService;
import com.material.chain.business.service.PurchaseService;
import com.material.chain.business.utils.RandomUtil;
import com.material.chain.common.constant.RedisKey;
import com.material.chain.common.doamin.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GlobalPurchaseServiceImpl implements PurchaseService {

    @Autowired
    private GlobalPurchaseOrderPoMapper globalPurchaseOrderPoMapper;
    @Autowired
    private GlobalPurchaseItemPoMapper purchaseItemPoMapper;
    @Autowired
    private GlobalPurchaseAddressPoMapper purchaseAddressPoMapper;
    @Autowired
    private MaterialService materialService;

    @Autowired
    private RocketMQTemplate rocketmqTemplate;


    /**
     * 创建采购单
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean createPurchaseOrder(PurchaseOrderDTO dto) {
        Long currentUserId = AppContextUtil.getCurrentUserId();
        long timeMillis = System.currentTimeMillis();

        List<PurchaseOrderItemDTO> purchaseOrderItemList = dto.getPurchaseOrderItemList();
        PurchaseOrderAddressDTO address = dto.getAddress();
        if (CollectionUtils.isEmpty(purchaseOrderItemList) || Objects.isNull(address)) {
            throw new ApiException("采购单物料或收件人地址不能为空");
        }

        List<Long> materialIds = purchaseOrderItemList.stream().map(PurchaseOrderItemDTO::getMaterialId).collect(Collectors.toList());
        List<MaterialInventoryPo> inventoryList = materialService.getMaterialInventoryByIds(materialIds);
        if (CollectionUtils.isEmpty(inventoryList)) {
            throw new ApiException("该物料没有找到库存");
        }

        //预校验库存
        checkInventory(inventoryList, purchaseOrderItemList);

        //总金额
        BigDecimal priceTotal = purchaseOrderItemList.stream().map(purchase -> {
            Integer quantity = purchase.getQuantity();
            BigDecimal unitPrice = purchase.getUnitPrice();
            if (Objects.isNull(quantity) || Objects.isNull(unitPrice)) {
                return BigDecimal.ZERO;
            }
            return unitPrice.multiply(new BigDecimal(quantity));
        }).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        //总数量
        Integer quantityTotal = purchaseOrderItemList.stream().mapToInt(PurchaseOrderItemDTO::getQuantity).sum();

        //保存采购单
        GlobalPurchaseOrderPo po = savePurchaseOrder(dto, currentUserId, timeMillis, priceTotal, quantityTotal);
        //采购单id
        Long purchaseId = po.getId();
        //保存采购单物料
        savePurchaseItem(purchaseOrderItemList, po, dto.getSupplierId(), currentUserId, timeMillis);
        //保存地址
        savePurchaseAddress(address, purchaseId, currentUserId, timeMillis);

        //异步线程去扣减库存
        List<CompletableFuture<Void>> completableFutureList = new ArrayList<>();
        RedissonLockManager instance = RedissonLockManager.getInstance();
        for (PurchaseOrderItemDTO orderItemDTO : purchaseOrderItemList) {
            CompletableFuture<Void> inventoryFuture = CompletableFuture.runAsync(() -> {
                //分布式锁锁住，按物料粒度去锁，减少锁的粒度，增加性能
                String iInventoryKey = String.format(RedisKey.REDUCE_INVENTORY_KEY, orderItemDTO.getMaterialId());
                instance.getLockToVoid(iInventoryKey, 5L, 10L, () -> {
                    reduceInventory(inventoryList, orderItemDTO);
                    return null;
                });
            });
            completableFutureList.add(inventoryFuture);
        }
        //等待所有异步线程执行完
        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0])).join();

        //钩子，上面事务确保提交成功后才会执行下面方法
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                //发送MQ方法，模拟支付
                PurchaseOrderVo vo = new PurchaseOrderVo();
                vo.setPurchaseId(purchaseId);
                vo.setPurchaseType(PurchasePlatformEnum.GLOBAL.getType());
                Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(vo)).build();
                log.info("发送生产者消息开始");
                rocketmqTemplate.send(TopicConstant.PURCHASE_ORDER_TOPIC, message);
                log.info("发送生产者消息结束");
            }
        });
        return true;
    }

    /**
     * 修改采购单状态
     * @param id 主键id
     * @param orderStatus 采购单状态
     */
    @Override
    public void updatePurchaseOrderStatus(Long id, Integer orderStatus) {
        globalPurchaseOrderPoMapper.updateOrderStatusById(id, orderStatus);
    }

    /**
     * 分页列表
     */
    @Override
    public PageVo<PurchaseOrderVo> pageList(PurchasePageDTO dto) {
        LambdaQueryWrapper<GlobalPurchaseOrderPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotBlank(dto.getOrderNo()), GlobalPurchaseOrderPo::getOrederNo, dto.getOrderNo());
        PageResponse<GlobalPurchaseOrderPo> page = PageUtil.getPage(() -> globalPurchaseOrderPoMapper.selectList(wrapper));

        //Page<GlobalPurchaseOrderPo> page = globalPurchaseOrderPoMapper.selectPage(new Page<>(dto.getPageNo(), dto.getPageSize()), wrapper);
        List<GlobalPurchaseOrderPo> records = Optional.ofNullable(page).map(PageResponse::getRecords).orElse(new ArrayList<>());
        if (CollectionUtils.isEmpty(records)) {
            return new PageVo<>();
        }
        List<Long> purchaseIds = records.stream().map(GlobalPurchaseOrderPo::getId).collect(Collectors.toList());
        List<GlobalPurchaseItemPo> itemList = purchaseItemPoMapper.findAllInPurchaseIds(purchaseIds);
        List<GlobalPurchaseAddressPo> addressList = purchaseAddressPoMapper.findAllInPurchaseIds(purchaseIds);
        if (CollectionUtils.isEmpty(itemList) || CollectionUtils.isEmpty(addressList)) {
            return new PageVo<>();
        }

        PageVo<PurchaseOrderVo> pageVo = new PageVo<>();
        List<PurchaseOrderVo> purchaseOrderList = new ArrayList<>();

        for (GlobalPurchaseOrderPo po : records) {
            PurchaseOrderVo vo = new PurchaseOrderVo();
            vo.setPurchaseId(po.getId());
            vo.setOrderNo(po.getOrederNo());
            vo.setCurrency(po.getCurrency());
            vo.setCountryCode(po.getCountryCode());
            vo.setSupplierId(po.getSupplierId());
            vo.setPurchaseType(PurchasePlatformEnum.GLOBAL.getValue());

            //物料列表
            List<PurchaseOrderItemVo> itemVoList = itemList.stream()
                    .filter(item -> Objects.equals(item.getPurchaseId(), po.getId()))
                    .map(item -> {
                        PurchaseOrderItemVo itemVo = new PurchaseOrderItemVo();
                        itemVo.setId(item.getId());
                        itemVo.setPurchaseId(po.getId());
                        itemVo.setMaterialId(item.getMaterialId());
                        itemVo.setSupplierId(item.getSupplierId());
                        itemVo.setMaterialName(item.getMaterialName());
                        itemVo.setThickness(item.getThickness());
                        itemVo.setWidth(item.getWidth());
                        itemVo.setLength(item.getLength());
                        itemVo.setWeight(item.getWeight());
                        itemVo.setUnitPrice(item.getUnitPrice());
                        itemVo.setQuantity(item.getQuantity());
                        return itemVo;
                    }).collect(Collectors.toList());
            vo.setItemList(itemVoList);

            //地址信息
            GlobalPurchaseAddressPo addressPo = addressList.stream().filter(address -> Objects.equals(address.getPurchaseId(), po.getId())).findAny().orElse(null);
            if (Objects.nonNull(addressPo)) {
                PurchaseOrderAddressVo addressVo = new PurchaseOrderAddressVo();
                addressVo.setAddress(addressPo.getAddress());
                addressVo.setPurchaseId(addressPo.getPurchaseId());
                addressVo.setId(addressPo.getId());
                addressVo.setProvince(addressPo.getProvince());
                addressVo.setCity(addressPo.getCity());
                addressVo.setArea(addressPo.getArea());
                addressVo.setRecipientName(addressPo.getRecipientName());
                addressVo.setRecipientPhone(addressPo.getRecipientPhone());
                vo.setAddress(addressVo);
            }
            purchaseOrderList.add(vo);
        }

        pageVo.setPageNo(page.getPageNo().longValue());
        pageVo.setPageSize(page.getSize().longValue());
        pageVo.setRecords(purchaseOrderList);
        pageVo.setTotal(page.getTotal().longValue());

        return pageVo;
    }

    /**
     * 扣减库存
     * @param inventoryList 物料库存数据
     * @param orderItemDTO 当前需要扣减的数据
     */
    private void reduceInventory(List<MaterialInventoryPo> inventoryList, PurchaseOrderItemDTO orderItemDTO) {
        MaterialInventoryPo inventoryPo = inventoryList.stream().filter(inventory -> Objects.equals(inventory.getMaterialId(), orderItemDTO.getMaterialId())).findAny().orElse(null);
        if (Objects.isNull(inventoryPo)) {
            log.warn("没有找到该物料的库存 物料id：{}", orderItemDTO.getMaterialId());
            return;
        }
        Integer quantity = orderItemDTO.getQuantity();
        Integer inventoryNumber = inventoryPo.getInventoryNumber();
        AtomicInteger atomicInteger = new AtomicInteger(inventoryNumber);
        atomicInteger.addAndGet(-quantity);
        materialService.updateMaterialInventory(inventoryPo.getId(), atomicInteger.get());
    }

    /**
     * 保存采购单
     */
    private GlobalPurchaseOrderPo savePurchaseOrder(PurchaseOrderDTO dto, Long currentUserId, Long timeMillis, BigDecimal priceTotal, Integer quantityTotal) {
        GlobalPurchaseOrderPo po = new GlobalPurchaseOrderPo();
        po.setOrederNo(RandomUtil.generatePurchaseNo());
        po.setPriceTotal(priceTotal);
        po.setQuantityTotal(quantityTotal);
        po.setSupplierId(dto.getSupplierId());
        po.setOrderStatus(OrderEnum.ORDER_CREATE.getCode());
        po.setPayType(PayEnum.DELIVERY_BEFORE_PAYMENT.getCode());
        po.setCurrency(dto.getCurrency());
        po.setCountryCode(dto.getCountryCode());
        po.setCurrency("ID");
        po.setCountryCode("ID");
        po.setCreateId(currentUserId);
        po.setUpdateId(currentUserId);
        po.setCreateTime(timeMillis);
        po.setUpdateTime(timeMillis);
        int result = globalPurchaseOrderPoMapper.insert(po);
        if (result == 0) {
            throw new ApiException("创建采购单失败");
        }
        return po;
    }

    /**
     * 保存采购单物料
     */
    private void savePurchaseItem(List<PurchaseOrderItemDTO> purchaseOrderItemList, GlobalPurchaseOrderPo po, Long supplierId, Long currentUserId, Long timeMillis) {
        List<GlobalPurchaseItemPo> purchaseItemPoList = purchaseOrderItemList.stream().map(item -> {
            GlobalPurchaseItemPo itemPo = new GlobalPurchaseItemPo();
            itemPo.setPurchaseId(po.getId());
            itemPo.setMaterialId(item.getMaterialId());
            itemPo.setSupplierId(supplierId);
            itemPo.setMaterialName(item.getMaterialName());
            itemPo.setLength(item.getLength());
            itemPo.setWeight(item.getWeight());
            itemPo.setWidth(item.getWidth());
            itemPo.setUnitPrice(item.getUnitPrice());
            itemPo.setQuantity(item.getQuantity());
            BigDecimal priceTotal = new BigDecimal(item.getQuantity()).multiply(item.getUnitPrice());
            itemPo.setPriceTotal(priceTotal);
            itemPo.setThickness(item.getThickness());
            itemPo.setCreateId(currentUserId);
            itemPo.setUpdateId(currentUserId);
            itemPo.setCreateTime(timeMillis);
            itemPo.setUpdateTime(timeMillis);
            return itemPo;
        }).collect(Collectors.toList());
        purchaseItemPoMapper.batchInsert(purchaseItemPoList);
    }

    /**
     * 保存采购单地址
     */
    private void savePurchaseAddress(PurchaseOrderAddressDTO address, Long purchaseId, Long currentUserId, Long timeMillis) {
        GlobalPurchaseAddressPo addressPo = new GlobalPurchaseAddressPo();
        addressPo.setAddress(address.getAddress());
        addressPo.setProvince(address.getProvince());
        addressPo.setCity(address.getCity());
        addressPo.setArea(address.getArea());
        addressPo.setRecipientName(address.getRecipientName());
        addressPo.setRecipientPhone(address.getRecipientPhone());
        addressPo.setPurchaseId(purchaseId);
        addressPo.setCreateId(currentUserId);
        addressPo.setUpdateId(currentUserId);
        addressPo.setCreateTime(timeMillis);
        addressPo.setUpdateTime(timeMillis);
        purchaseAddressPoMapper.insert(addressPo);
    }

    /**
     * 预校验库存是否足够下单
     */
    private void checkInventory(List<MaterialInventoryPo> inventoryList, List<PurchaseOrderItemDTO> purchaseOrderItemList) {
        for (PurchaseOrderItemDTO itemDTO : purchaseOrderItemList) {
            long count = inventoryList.stream().filter(inventory -> inventory.getInventoryNumber() > itemDTO.getQuantity()).count();
            if (count > 0) {
                continue;
            }
            MaterialPo materialPo = materialService.getMaterialCodeById(itemDTO.getMaterialId());
            if (Objects.isNull(materialPo)) {
                throw new ApiException("数据异常");
            }
            throw new ApiException("物料编号为" + materialPo.getMaterialCode() + "的库存不足，无法下单");
        }
    }
}
