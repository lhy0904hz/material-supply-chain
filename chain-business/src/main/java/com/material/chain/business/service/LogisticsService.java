package com.material.chain.business.service;

import com.material.chain.business.domain.function.LogisticsFunction;
import com.material.chain.logistics.domain.dto.LogisticsOrderDTO;

public interface LogisticsService<K, T, R> {

    LogisticsOrderDTO getLogisticsOrderParam(LogisticsFunction<K, T, R> function);
}
