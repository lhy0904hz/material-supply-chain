package com.material.chain.logistics.client;


import com.material.chain.common.utils.ApiResult;
import com.material.chain.logistics.domain.dto.LogisticsOrderDTO;
import com.material.chain.logistics.domain.vo.LogisticsProviderVo;
import com.material.chain.logistics.domain.vo.LogisticsTrajectoryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "chain-logistics", path = "/logistics")
public interface LogisticsClient {

    /**
     * 获取物流商集合
     */
    @GetMapping("/getProviderList")
    ApiResult<List<LogisticsProviderVo>> getProviderList();

    /**
     * 创建订单
     */
    @PostMapping("/createOrder")
    ApiResult<String> createOrder(@RequestBody LogisticsOrderDTO dto);

    /**
     * 获取物流轨迹
     */
    @GetMapping("/getLogisticsTrajectoryList")
    ApiResult<List<LogisticsTrajectoryVo>> getLogisticsTrajectoryList(@RequestParam(value = "orderId") Long orderId);
}
