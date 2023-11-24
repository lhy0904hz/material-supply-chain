package com.material.chain.business.service;

import com.material.chain.base.holder.SpringContextHolder;
import com.material.chain.business.domain.dto.PurchaseOrderDTO;
import com.material.chain.business.enums.PurchasePlatformEnum;

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


}
