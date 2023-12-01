package com.material.chain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum LogisticsStatusEnum {

    VISIT_PICKING_UP(5001, "等待上门揽件"),

    OUT_BOUND(5002, "已发货"),

    IN_TRANSIT(5003, "运输中"),

    COMPLETED(5004, "运输完成"),

    ;

    private Integer code;

    private String value;
}
