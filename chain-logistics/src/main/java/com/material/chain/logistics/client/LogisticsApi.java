package com.material.chain.logistics.client;


import com.material.chain.common.utils.ApiResult;
import com.material.chain.logistics.domain.dto.LogisticsOrderDTO;
import com.material.chain.logistics.domain.vo.LogisticsProviderVo;
import com.material.chain.logistics.domain.vo.LogisticsTrajectoryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "chain-logistics", path = "/chainLogistics")
public interface LogisticsApi {

    /**
     * 获取物流商集合
     */
    @GetMapping("/getProviderList")
    ApiResult<List<LogisticsProviderVo>> getProviderList();

    /**
     * 创建订单
     */
    @PostMapping("/createOrder")
    ApiResult<String> createOrder(LogisticsOrderDTO dto);

    /**
     * 获取物流轨迹
     */
    @GetMapping("/getLogisticsTrajectoryList")
    ApiResult<List<LogisticsTrajectoryVo>> getLogisticsTrajectoryList(Long orderId);
}
