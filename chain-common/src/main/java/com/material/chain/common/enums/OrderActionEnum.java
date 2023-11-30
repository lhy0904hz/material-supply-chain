package com.material.chain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单时间枚举
 */
@Getter
@AllArgsConstructor
public enum OrderActionEnum {

    /**
     * 创建订单
     */
    CREATE,

    /**
     * 付款
     */
    PAY,

    /**
     * 发货
     */
    DELIVERY,

    /**
     * 运输
     */
    TRANSPORT,

    /**
     * 完成
     */
    FINISH,
}
