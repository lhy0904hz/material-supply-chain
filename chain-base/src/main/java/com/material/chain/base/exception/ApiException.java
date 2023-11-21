package com.material.chain.base.exception;


import com.material.chain.common.enums.GlobalStatusEnum;
import lombok.Data;

@Data
public class ApiException extends RuntimeException{

    private Integer code;

    private String message;

    public ApiException(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public ApiException(String message) {
        this(GlobalStatusEnum.ERROR_STATUS.getCode(), message);
    }

}
