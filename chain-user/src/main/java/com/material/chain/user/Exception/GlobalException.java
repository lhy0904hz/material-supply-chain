package com.material.chain.user.Exception;


import com.material.chain.common.enums.GlobalStatusEnum;
import lombok.Data;

@Data
public class GlobalException extends RuntimeException{

    private Integer code;

    private String message;

    public GlobalException(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public GlobalException(String message) {
        this(GlobalStatusEnum.ERROR_STATUS.getCode(), message);
    }

}
