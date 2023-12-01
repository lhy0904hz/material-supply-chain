package com.material.chain.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.material.chain.base.page.PageResponse;
import com.material.chain.base.page.PageUtil;
import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.common.enums.StatusEnum;
import com.material.chain.logistics.domain.dto.LogisticsOrderAddressDTO;
import com.material.chain.logistics.domain.dto.LogisticsOrderDTO;
import com.material.chain.logistics.domain.dto.LogisticsOrderItemDTO;
import com.material.chain.logistics.domain.dto.LogisticsOrderPageDTO;
import com.material.chain.logistics.domain.po.*;
import com.material.chain.logistics.domain.vo.LogisticsOrderVo;
import com.material.chain.logistics.domain.vo.LogisticsProviderVo;
import com.material.chain.logistics.domain.vo.LogisticsTrajectoryVo;
import com.material.chain.common.enums.LogisticsStatusEnum;
import com.material.chain.logistics.mapper.*;
import com.material.chain.logistics.service.LogisticsService;
import com.material.chain.logistics.utils.GenerateNoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LogisticsServiceImpl implements LogisticsService {

    @Autowired
    private LogisticsProviderPoMapper providerPoMapper;

    @Autowired
    private LogisticsOrderPoMapper orderPoMapper;

    @Autowired
    private LogisticsOrderAddressPoMapper addressPoMapper;

    @Autowired
    private LogisticsOrderItemPoMapper itemPoMapper;

    @Autowired
    private LogisticsTrajectoryPoMapper trajectoryPoMapper;

    /**
     * 获取物流商列表
     */
    @Override
    public List<LogisticsProviderVo> getProviderList() {
        List<LogisticsProviderPo> providerList = providerPoMapper.findAllByStatus(StatusEnum.NORMAL.getCode());
        if (CollectionUtils.isEmpty(providerList)) {
            return Collections.emptyList();
        }
        return providerList.stream().map(p -> {
            LogisticsProviderVo vo = new LogisticsProviderVo();
            vo.setProviderId(p.getId());
            vo.setProviderName(p.getName());
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 创建物流订单
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createOrder(LogisticsOrderDTO dto) {
        LogisticsOrderPo po = new LogisticsOrderPo();

        Long currentUserId = dto.getUserId();
        Long timeMillis = System.currentTimeMillis();

        //新增订单信息
        String orderNo = GenerateNoUtil.generatePurchaseNo();
        po.setBusinessNo(dto.getBusinessNo());
        po.setOrderNo(orderNo);
        po.setStatus(LogisticsStatusEnum.VISIT_PICKING_UP.getCode());
        po.setBusinessNo(dto.getBusinessNo());
        po.setCreateId(currentUserId);
        po.setUpdateId(currentUserId);
        po.setCreateTime(timeMillis);
        po.setUpdateTime(timeMillis);
        orderPoMapper.insert(po);

        Long orderId = po.getId();

        //新增物流地址信息
        LogisticsOrderAddressDTO addressDTO = Optional.of(dto).map(LogisticsOrderDTO::getOrderAddress).orElse(null);
        if (Objects.isNull(addressDTO)) {
            return StringUtils.EMPTY;
        }
        LogisticsOrderAddressPo addressPo = new LogisticsOrderAddressPo();
        addressPo.setProviderId(dto.getProviderId());
        addressPo.setOrderId(orderId);
        addressPo.setRecipientProvince(addressDTO.getRecipientProvince());
        addressPo.setRecipientCity(addressDTO.getRecipientCity());
        addressPo.setRecipientArea(addressDTO.getRecipientArea());
        addressPo.setRecipientAddress(addressDTO.getRecipientAddress());
        addressPo.setRecipientName(addressDTO.getRecipientName());
        addressPo.setRecipientPhone(addressDTO.getRecipientPhone());
        addressPo.setSenderProvince(addressDTO.getSenderProvince());
        addressPo.setSenderCity(addressDTO.getSenderCity());
        addressPo.setSenderArea(addressDTO.getSenderArea());
        addressPo.setSenderAddress(addressDTO.getSenderAddress());
        addressPo.setSenderName(addressDTO.getSenderName());
        addressPo.setSenderPhone(addressDTO.getSenderPhone());
        addressPo.setCreateId(currentUserId);
        addressPo.setUpdateId(currentUserId);
        addressPo.setCreateTime(timeMillis);
        addressPo.setUpdateTime(timeMillis);
        addressPoMapper.insert(addressPo);

        //新增包裹信息
        LogisticsOrderItemDTO itemDTO = Optional.of(dto).map(LogisticsOrderDTO::getOrderItem).orElse(null);
        if (Objects.isNull(itemDTO)) {
            return StringUtils.EMPTY;
        }
        LogisticsOrderItemPo itemPo = new LogisticsOrderItemPo();
        itemPo.setOrderId(orderId);
        itemPo.setProviderId(dto.getProviderId());
        itemPo.setItemName(itemDTO.getItemName());
        itemPo.setPriceTotal(itemDTO.getPriceTotal());
        itemPo.setQuantityTotal(itemDTO.getQuantityTotal());
        itemPo.setWeightTotal(itemDTO.getWeightTotal());
        itemPo.setCreateId(currentUserId);
        itemPo.setUpdateId(currentUserId);
        itemPo.setCreateTime(timeMillis);
        itemPo.setUpdateTime(timeMillis);
        itemPoMapper.insert(itemPo);

        //新增物流轨迹
        LogisticsTrajectoryPo trajectoryPo = new LogisticsTrajectoryPo();
        trajectoryPo.setOrderId(orderId);
        trajectoryPo.setTrajectoryDesc(LogisticsStatusEnum.VISIT_PICKING_UP.getValue());
        trajectoryPo.setCreateId(currentUserId);
        trajectoryPo.setUpdateId(currentUserId);
        trajectoryPo.setCreateTime(timeMillis);
        trajectoryPo.setUpdateTime(timeMillis);
        trajectoryPoMapper.insert(trajectoryPo);

        return orderNo;
    }

    /**
     * 获取物流轨迹
     */
    @Override
    public List<LogisticsTrajectoryVo> getLogisticsTrajectoryList(Long orderId) {
        List<LogisticsTrajectoryPo> trajectoryList = trajectoryPoMapper.findAllByOrderId(orderId);
        if (CollectionUtils.isEmpty(trajectoryList)) {
            return Collections.emptyList();
        }
        return trajectoryList.stream().map(t -> {
            LogisticsTrajectoryVo vo = new LogisticsTrajectoryVo();
            vo.setDesc(t.getTrajectoryDesc());
            vo.setDateTime(t.getCreateTime());
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 分页列表
     */
    @Override
    public PageVo<LogisticsOrderVo> pageList(LogisticsOrderPageDTO dto) {
        LambdaQueryWrapper<LogisticsOrderPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotBlank(dto.getLogisticsOrderNo()), LogisticsOrderPo::getOrderNo, dto.getLogisticsOrderNo());
        wrapper.eq(StringUtils.isNotBlank(dto.getBusinessNo()), LogisticsOrderPo::getBusinessNo, dto.getBusinessNo());
        wrapper.eq(Objects.nonNull(dto.getStatus()), LogisticsOrderPo::getStatus, dto.getStatus());

        PageResponse<LogisticsOrderPo> page = PageUtil.getPage(() -> orderPoMapper.selectList(wrapper));
        List<LogisticsOrderPo> records = Optional.of(page).map(PageResponse::getRecords).orElse(new ArrayList<>());
        if (CollectionUtils.isEmpty(records)) {
            return new PageVo<>(0L, dto.getPageNo().longValue(), dto.getPageSize().longValue(), new ArrayList<>());
        }
        PageVo<LogisticsOrderVo> pageVo = new PageVo<>();
        List<LogisticsOrderVo> logisticsOrderList = new ArrayList<>();
        for (LogisticsOrderPo po : records) {
            LogisticsOrderVo vo = new LogisticsOrderVo();
            vo.setLogisticsOrderId(po.getId());
            vo.setOrderNo(po.getOrderNo());
            vo.setBusinessNo(po.getBusinessNo());
            vo.setStatus(po.getStatus());
            logisticsOrderList.add(vo);
        }
        pageVo.setPageNo(page.getPageNo().longValue());
        pageVo.setPageSize(page.getSize().longValue());
        pageVo.setRecords(logisticsOrderList);
        pageVo.setTotal(page.getTotal().longValue());
        return pageVo;
    }

    /**
     * 根据id修改状态
     */
    @Override
    public void updateStatusByIds(Integer status, List<Long> ids) {
        orderPoMapper.updateStatusByIds(status, ids);
    }

    /**
     * 添加物流轨迹记录
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addLogisticsLog(List<Long> ids, String desc) {
        long timeMillis = System.currentTimeMillis();
        List<LogisticsTrajectoryPo> list = new ArrayList<>();
        for (Long id : ids) {
            LogisticsTrajectoryPo po = new LogisticsTrajectoryPo();
            po.setOrderId(id);
            po.setTrajectoryDesc(desc);
            po.setCreateId(1L);
            po.setUpdateId(1L);
            po.setCreateTime(timeMillis);
            po.setUpdateTime(timeMillis);
            list.add(po);
        }
        trajectoryPoMapper.batchInsert(list);
    }

    /**
     * 根据业务单号查询物流状态
     */
    @Override
    public Integer getOrderStatusByBusinessNo(String businessNo) {
        LogisticsOrderPo orderPo = orderPoMapper.findByBusinessNo(businessNo);
        return Optional.ofNullable(orderPo).map(LogisticsOrderPo::getStatus).orElse(null);
    }
}
