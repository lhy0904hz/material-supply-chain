package com.material.chain.user.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResult<T> implements Serializable {

    private boolean success;

    private String code;

    private String message;

    private T data;

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);
        apiResult.setCode("200");
        apiResult.setMessage("请求成功");
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> error(String code, String message) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(false);
        apiResult.setCode(code);
        apiResult.setMessage(message);
        return apiResult;
    }
}
