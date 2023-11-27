package com.material.chain.logistics.service;

import com.material.chain.logistics.domain.dto.LogisticsOrderDTO;
import com.material.chain.logistics.domain.vo.LogisticsProviderVo;
import com.material.chain.logistics.domain.vo.LogisticsTrajectoryVo;

import java.util.List;

public interface LogisticsService {

    /**
     * 获取物流商集合
     */
    List<LogisticsProviderVo> getProviderList();

    /**
     * 创建订单
     */
    String createOrder(LogisticsOrderDTO dto);

    /**
     * 获取物流轨迹
     */
    List<LogisticsTrajectoryVo> getLogisticsTrajectoryList(Long orderId);
}
