package com.material.chain.business;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum SupplierEnum {

    BRAND_SUPPLIER(1, "品牌供应商"),
    SELF_SUPPORT_SUPPLIER(2, "自营供应商");

    private Integer code;

    private String value;

    public static String getValue(Integer code) {
        return Arrays.stream(SupplierEnum.values())
                .filter(e -> e.getCode().equals(code)).map(SupplierEnum::getValue).findFirst().orElse(null);


    }
}
