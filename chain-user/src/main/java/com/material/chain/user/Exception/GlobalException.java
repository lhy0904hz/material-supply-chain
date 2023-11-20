package com.material.chain.user.Exception;


import com.material.chain.user.common.ErrorCode;
import lombok.Data;

@Data
public class GlobalException extends RuntimeException{

    private String code;

    private String message;

    public GlobalException(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public GlobalException(String message) {
        this(ErrorCode.ERROR.getCode(), message);
    }

}
