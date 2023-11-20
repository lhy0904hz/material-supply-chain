package com.material.chain.user.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ERROR("99999", "系统错误");

    private String code;

    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
