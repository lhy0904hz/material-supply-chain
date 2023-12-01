package com.material.chain.logistics.job;

import com.material.chain.base.page.PageParam;
import com.material.chain.base.page.ThreadPagingUtil;
import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.common.enums.LogisticsStatusEnum;
import com.material.chain.logistics.domain.dto.LogisticsOrderPageDTO;
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
    @XxlJob("pickUpHandler2")
    public void pickUpHandler2() {
        Integer pageNo = 1;
        Integer pageSize = 10;
        log.info("等待上门揽件--》已发货 执行");
        while (true) {
            if (batchUpdateStatus(pageNo,
                    pageSize,
                    LogisticsStatusEnum.VISIT_PICKING_UP.getCode(),
                    LogisticsStatusEnum.OUT_BOUND.getCode(),
                    LogisticsStatusEnum.OUT_BOUND.getValue())) {
                pageNo++;
            } else {
                break;
            }
        }
        log.info("等待上门揽件--》已发货 完成");
    }

    /**
     * 已发货--》运输中
     */
    @XxlJob(value = "outBoundHandler")
    public void outBoundHandler() {
        log.info("已发货--》运输中 开始");
        Integer pageNo = 1;
        Integer pageSize = 10;
        while (true) {
            if (batchUpdateStatus(pageNo,
                    pageSize,
                    LogisticsStatusEnum.OUT_BOUND.getCode(),
                    LogisticsStatusEnum.IN_TRANSIT.getCode(),
                    LogisticsStatusEnum.IN_TRANSIT.getValue())) {
                pageNo++;
            } else {
                break;
            }
        }
        log.info("已发货--》运输中 完成");
    }

    /**
     * 运输中--》待签收
     */
    @XxlJob(value = "inTransitHandler")
    public void inTransitHandler() {
        log.info("运输中--》待签收 开始");
        Integer pageNo = 1;
        Integer pageSize = 10;
        while (true) {
            if (batchUpdateStatus(pageNo,
                    pageSize,
                    LogisticsStatusEnum.IN_TRANSIT.getCode(),
                    LogisticsStatusEnum.COMPLETED.getCode(),
                    LogisticsStatusEnum.COMPLETED.getValue())) {
                pageNo++;
            } else {
                break;
            }
        }
        log.info("运输中--》待签收 完成");
    }

    /**
     * 批量修改状态
     */
    private boolean batchUpdateStatus(Integer pageNo, Integer pageSize, Integer startStatus, Integer endStatus, String desc) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(pageNo);
        pageParam.setPageSize(pageSize);
        ThreadPagingUtil.set(pageParam);
        LogisticsOrderPageDTO dto = new LogisticsOrderPageDTO();
        dto.setStatus(startStatus);
        PageVo<LogisticsOrderVo> pageVo = logisticsService.pageList(dto);
        List<LogisticsOrderVo> records = Optional.ofNullable(pageVo).map(PageVo::getRecords).orElse(new ArrayList<>());
        if (CollectionUtils.isEmpty(records)) {
            return false;
        }
        List<Long> ids = records.stream().map(LogisticsOrderVo::getLogisticsOrderId).collect(Collectors.toList());
        logisticsService.updateStatusByIds(endStatus, ids);
        logisticsService.addLogisticsLog(ids, desc);
        return true;
    }
}
