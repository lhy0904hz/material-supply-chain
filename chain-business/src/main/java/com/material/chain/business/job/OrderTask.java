package com.material.chain.business.job;

import com.material.chain.base.exception.ApiException;
import com.material.chain.base.page.PageParam;
import com.material.chain.base.page.ThreadPagingUtil;
import com.material.chain.base.utils.AppContextUtil;
import com.material.chain.business.domain.dto.PurchasePageDTO;
import com.material.chain.business.domain.vo.PurchaseOrderVo;
import com.material.chain.business.service.PurchaseService;
import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.common.enums.LogisticsStatusEnum;
import com.material.chain.common.enums.OrderEnum;
import com.material.chain.common.utils.ApiResult;
import com.material.chain.logistics.client.LogisticsClient;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class OrderTask {
    @Autowired
    private LogisticsClient logisticsClient;

    /**
     * 同步物流状态
     */
    @XxlJob(value = "syncLogisticsJob")
    public void syncLogisticsJob() {
        log.info("同步物流状态开始");
        Integer pageNo = 1;
        Integer pageSize = 10;
        while (true) {
            PageParam pageParam = new PageParam();
            pageParam.setPageNo(pageNo);
            pageParam.setPageSize(pageSize);
            ThreadPagingUtil.set(pageParam);

            PurchasePageDTO dto = new PurchasePageDTO();
            dto.setNotInStatusList(Collections.singletonList(OrderEnum.FINISH.getCode()));
            dto.setPurchaseType("global");
            PurchaseService purchaseService = PurchaseService.getBean("global");
            PageVo<PurchaseOrderVo> pageVo = purchaseService.pageList(dto);
            List<PurchaseOrderVo> purchaseOrderList = Optional.ofNullable(pageVo).map(PageVo::getRecords).orElse(new ArrayList<>());
            if (CollectionUtils.isEmpty(purchaseOrderList)) {
                break;
            }
            for (PurchaseOrderVo orderVo : purchaseOrderList) {
                try {
                    log.info("订单：{}开始同步状态", orderVo.getOrderNo());
                    ApiResult<Integer> result = logisticsClient.getOrderStatusByBusinessNo(orderVo.getOrderNo());
                    Integer logisticsStatus = Optional.ofNullable(result).map(ApiResult::getData).orElse(null);
                    if (Objects.isNull(logisticsStatus)) {
                        continue;
                    }
                    purchaseService.updateStatus(orderVo.getPurchaseId(), statusConvert(logisticsStatus), logisticsStatus);
                }catch (Exception e) {
                    log.error("同步状态任务出错", e);
                    return;
                }
            }
            pageNo++;
        }
        log.info("同步物流状态结束");
    }

    private Integer statusConvert(Integer logisticsStatus) {
        if (Objects.equals(LogisticsStatusEnum.VISIT_PICKING_UP.getCode(), logisticsStatus)) {
            return OrderEnum.BE_SHIPPED.getCode();
        }
        if (Objects.equals(LogisticsStatusEnum.OUT_BOUND.getCode(), logisticsStatus)) {
            return OrderEnum.SHIPPED.getCode();
        }
        if (Objects.equals(LogisticsStatusEnum.IN_TRANSIT.getCode(), logisticsStatus)) {
            return OrderEnum.IN_TRANSIT.getCode();
        }
        if (Objects.equals(LogisticsStatusEnum.COMPLETED.getCode(), logisticsStatus)) {
            return OrderEnum.TO_BE_SIGNED.getCode();
        }
        throw new ApiException("物流状态异常");
    }
}
