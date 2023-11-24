package com.material.chain.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderEnum {

    ORDER_CREATE(3001, "已创建"),

    PAID_RECEIVED(3002, "已付款"),

    BE_SHIPPED(3003, "待发货"),

    SHIPPED(3004, "已发货"),

    IN_TRANSIT(3005, "运输中"),

    FINISH(3006, "运输完成"),

        ;

    private Integer code;

    private String value;
}
