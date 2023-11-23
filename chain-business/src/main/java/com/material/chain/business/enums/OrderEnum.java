package com.material.chain.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderEnum {

    ORDER_CREATE(3001, "已创建");

    private Integer code;

    private String value;
}
