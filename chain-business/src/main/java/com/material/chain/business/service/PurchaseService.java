package com.material.chain.business.service;

import com.material.chain.base.holder.SpringContextHolder;
import com.material.chain.business.domain.dto.PurchaseLogisticsDTO;
import com.material.chain.business.domain.dto.PurchaseOrderDTO;
import com.material.chain.business.domain.dto.PurchasePageDTO;
import com.material.chain.business.domain.vo.PurchaseOrderVo;
import com.material.chain.business.enums.PurchasePlatformEnum;
import com.material.chain.common.doamin.vo.PageVo;
import com.material.chain.logistics.domain.vo.LogisticsProviderVo;

import java.util.List;

public interface PurchaseService {

    /**
     * 创建采购单
     */
    Boolean createPurchaseOrder(PurchaseOrderDTO dto);



    /**
     * 根据采购单类型返回Bean
     *
     */
    static PurchaseService getBean(String type) {
        return SpringContextHolder.getBean(PurchasePlatformEnum.getBeanNameByType(type));
    }

    /**
     * 修改采购单状态
     * @param id 主键id
     * @param orderStatus 采购单状态
     */
    void updatePurchaseOrderStatus(Long id, Integer orderStatus);


    /**
     * 分页列表
     */
    PageVo<PurchaseOrderVo> pageList(PurchasePageDTO dto);

    /**
     * 采购单详情
     */
    PurchaseOrderVo detail(Long purchaseOrderId);

    /**
     * 选择物流发货
     */
    Boolean chooseLogistics(PurchaseLogisticsDTO dto);

    /**
     * 获取物流商列表
     */
    List<LogisticsProviderVo> getLogisticsProviderList();
}
