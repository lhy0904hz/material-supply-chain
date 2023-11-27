package com.material.chain.logistics.service.impl;

import com.material.chain.base.utils.AppContextUtil;
import com.material.chain.common.enums.StatusEnum;
import com.material.chain.logistics.domain.dto.LogisticsOrderAddressDTO;
import com.material.chain.logistics.domain.dto.LogisticsOrderDTO;
import com.material.chain.logistics.domain.dto.LogisticsOrderItemDTO;
import com.material.chain.logistics.domain.po.*;
import com.material.chain.logistics.domain.vo.LogisticsProviderVo;
import com.material.chain.logistics.domain.vo.LogisticsTrajectoryVo;
import com.material.chain.logistics.enums.LogisticsStatusEnum;
import com.material.chain.logistics.mapper.*;
import com.material.chain.logistics.service.LogisticsService;
import com.material.chain.logistics.utils.GenerateNoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

        Long currentUserId = AppContextUtil.getCurrentUserId();
        Long timeMillis = System.currentTimeMillis();

        //新增订单信息
        String orderNo = GenerateNoUtil.generatePurchaseNo();
        po.setBusinessNo(orderNo);
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
}
