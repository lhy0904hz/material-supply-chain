package com.material.chain.logistics.service;

import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.logistics.domain.dto.LogisticsOrderDTO;
import com.material.chain.logistics.domain.dto.LogisticsOrderPageDTO;
import com.material.chain.logistics.domain.vo.LogisticsOrderVo;
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

    /**
     * 分页列表
     */
    PageVo<LogisticsOrderVo> pageList(LogisticsOrderPageDTO dto);

    /**
     * 根据id修改状态
     */
    void updateStatusByIds(Integer status, List<Long> ids);

    /**
     * 添加物流轨迹记录
     */
    void addLogisticsLog(List<Long> ids, String desc);

    /**
     * 根据业务单号查询物流状态
     */
    Integer getOrderStatusByBusinessNo(String businessNo);
}
