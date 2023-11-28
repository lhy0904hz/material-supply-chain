package com.material.chain.logistics.job;

import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.common.enums.LogisticsStatusEnum;
import com.material.chain.logistics.domain.dto.LogisticsOrderDTO;
import com.material.chain.logistics.domain.vo.LogisticsOrderVo;
import com.material.chain.logistics.service.LogisticsService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LogisticsTask {

    @Autowired
    private LogisticsService logisticsService;

    /**
     * 模拟物流状态流转
     */

    /**
     * 等待上门揽件--》已发货
     */
    @XxlJob(value = "pickUpHandler")
    public void pickUpHandler() {
        Integer pageNo = 1;
        Integer pageSize = 10;
        while (true) {
            if (batchUpdateStatus(pageNo,
                    pageSize,
                    LogisticsStatusEnum.VISIT_PICKING_UP.getCode(),
                    LogisticsStatusEnum.OUT_BOUND.getCode())) {
                pageNo++;
            } else {
                break;
            }
        }
    }

    /**
     * 已发货--》运输中
     */
    @XxlJob(value = "outBoundHandler")
    public void outBoundHandler() {
        Integer pageNo = 1;
        Integer pageSize = 10;
        while (true) {
            if (batchUpdateStatus(pageNo,
                    pageSize,
                    LogisticsStatusEnum.OUT_BOUND.getCode(),
                    LogisticsStatusEnum.IN_TRANSIT.getCode())) {
                pageNo++;
            } else {
                break;
            }
        }
    }

    /**
     * 运输中--》待签收
     */
    @XxlJob(value = "inTransitHandler")
    public void inTransitHandler() {
        Integer pageNo = 1;
        Integer pageSize = 10;
        while (true) {
            if (batchUpdateStatus(pageNo,
                    pageSize,
                    LogisticsStatusEnum.IN_TRANSIT.getCode(),
                    LogisticsStatusEnum.TO_BE_SIGNED.getCode())) {
                pageNo++;
            } else {
                break;
            }
        }
    }

    /**
     * 批量修改状态
     */
    private boolean batchUpdateStatus(Integer pageNo, Integer pageSize, Integer startStatus, Integer endStatus) {
        LogisticsOrderDTO dto = new LogisticsOrderDTO();
        dto.setPageNo(pageNo);
        dto.setPageSize(pageSize);
        dto.setStatus(startStatus);
        PageVo<LogisticsOrderVo> pageVo = logisticsService.pageList(dto);
        List<LogisticsOrderVo> records = Optional.ofNullable(pageVo).map(PageVo::getRecords).orElse(new ArrayList<>());
        if (CollectionUtils.isEmpty(records)) {
            return false;
        }
        List<Long> ids = records.stream().map(LogisticsOrderVo::getLogisticsOrderId).collect(Collectors.toList());
        logisticsService.updateStatusByIds(endStatus, ids);
        return true;
    }
}
