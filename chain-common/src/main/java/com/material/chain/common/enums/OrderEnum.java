package com.material.chain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderEnum {

    INIT(0, "初始化"),

    ORDER_CREATE(3001, "已创建"),

    BE_SHIPPED(3002, "待发货"),

    SHIPPED(3003, "已发货"),

    IN_TRANSIT(3004, "运输中"),

    TO_BE_SIGNED(3005, "待签收"),

    FINISH(3006, "已完成"),

    GENERATE_TASK(3007, "已生成任务")

        ;

    private Integer code;

    private String value;
}
