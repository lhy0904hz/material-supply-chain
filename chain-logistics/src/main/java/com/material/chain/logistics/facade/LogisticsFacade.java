package com.material.chain.logistics.facade;

import com.material.chain.common.utils.ApiResult;
import com.material.chain.logistics.client.LogisticsClient;
import com.material.chain.logistics.domain.dto.LogisticsOrderDTO;
import com.material.chain.logistics.domain.vo.LogisticsProviderVo;
import com.material.chain.logistics.domain.vo.LogisticsTrajectoryVo;
import com.material.chain.logistics.service.LogisticsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "物流")
@RestController
@RequestMapping("/logistics")
public class LogisticsFacade implements LogisticsClient {

    @Autowired
    private LogisticsService logisticsService;

    @Override
    public ApiResult<List<LogisticsProviderVo>> getProviderList() {
        return ApiResult.success(logisticsService.getProviderList());
    }

    @Override
    public ApiResult<String> createOrder(LogisticsOrderDTO dto) {
        return ApiResult.success(logisticsService.createOrder(dto));
    }

    @Override
    public ApiResult<List<LogisticsTrajectoryVo>> getLogisticsTrajectoryList(Long orderId) {
        return ApiResult.success(logisticsService.getLogisticsTrajectoryList(orderId));
    }
}
