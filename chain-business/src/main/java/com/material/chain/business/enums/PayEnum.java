package com.material.chain.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayEnum {

    DELIVERY_BEFORE_PAYMENT(1, "先货后款");

    private Integer code;

    private String value;
}
