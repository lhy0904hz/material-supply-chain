package com.material.chain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum LogisticsStatusEnum {

    VISIT_PICKING_UP(5001, "等待上门揽件"),

    OUT_BOUND(5002, "已出库"),

    IN_TRANSIT(5003, "运输中"),

    TO_BE_SIGNED(5004, "待签收"),

    COMPLETED(5005, "已完成"),

    ;

    private Integer code;

    private String value;
}
