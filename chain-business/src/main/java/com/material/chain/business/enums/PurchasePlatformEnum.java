package com.material.chain.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PurchasePlatformEnum {

    INTERNAL("internal", "国内采购单", ""),
    GLOBAL("global", "国际采购单", "");

    private String type;

    private String value;

    private String beanName;

    /**
     * 根据类型拿到bean
     */
    public static String getBeanNameByType(String type) {
        return Arrays.stream(values())
                .filter(e -> e.getType().equals(type))
                .findFirst()
                .map(PurchasePlatformEnum::getBeanName)
                .orElse(INTERNAL.beanName);
    }

}
