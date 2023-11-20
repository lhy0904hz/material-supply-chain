package com.material.chain.user.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    NORMAL(1, "正常"),
    DELETE(2, "删除");


    private Integer code;

    private String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
