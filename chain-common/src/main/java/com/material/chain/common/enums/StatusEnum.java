package com.material.chain.common.enums;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * 流程节点状态枚举
 */
public enum StatusEnum {

    NORMAL(0, "正常"),
    DELETE(1, "删除"),
    ;


    private Integer code;

    private String value;

    StatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
