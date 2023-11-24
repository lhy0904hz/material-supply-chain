package com.material.chain.business.service.impl;

import com.material.chain.base.exception.ApiException;
import com.material.chain.base.utils.AppContextUtil;
import com.material.chain.business.constant.TopicConstant;
import com.material.chain.business.domain.dto.PurchaseOrderAddressDTO;
import com.material.chain.business.domain.dto.PurchaseOrderDTO;
import com.material.chain.business.domain.dto.PurchaseOrderItemDTO;
import com.material.chain.business.domain.po.*;
import com.material.chain.business.enums.OrderEnum;
import com.material.chain.business.enums.PayEnum;
import com.material.chain.business.mapper.GlobalPurchaseAddressPoMapper;
import com.material.chain.business.mapper.GlobalPurchaseItemPoMapper;
import com.material.chain.business.mapper.GlobalPurchaseOrderPoMapper;
import com.material.chain.business.service.MaterialService;
import com.material.chain.business.service.PurchaseService;
import com.material.chain.business.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
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

        Long purchaseId = po.getId();

        //保存采购单物料
        savePurchaseItem(purchaseOrderItemList, po, dto.getSupplierId(), currentUserId, timeMillis);

        //保存地址
        savePurchaseAddress(address, purchaseId, currentUserId, timeMillis);

        Message<String> helloRocketmq = MessageBuilder.withPayload("hello rocketmq").build();
        log.info("发送生产者消息开始");
        rocketmqTemplate.send(TopicConstant.PURCHASE_ORDER_TOPIC, helloRocketmq);
        log.info("发送生产者消息结束");

        //扣减库存
        for (PurchaseOrderItemDTO orderItemDTO : purchaseOrderItemList) {
            MaterialInventoryPo inventoryPo = inventoryList.stream().filter(inventory -> Objects.equals(inventory.getMaterialId(), orderItemDTO.getMaterialId())).findAny().orElse(null);
            if (Objects.isNull(inventoryPo)) {
                log.warn("没有找到该物料的库存 物料id：{}", orderItemDTO.getMaterialId());
                continue;
            }
            //TODO 分布式锁扣减库存
            Integer quantity = orderItemDTO.getQuantity();

        }








        return null;
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
