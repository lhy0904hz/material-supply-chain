package com.material.chain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum GlobalStatusEnum {

    SUCCESS_STATUS(0, "请求成功"),
    ERROR_STATUS(1, "系统错误"),
    TOKEN_EXPIRE(1001, "token已失效，请重新登录"),
    TOKEN_REFRESH(1002, "token刷新成功");

    private Integer code;

    private String message;


}
