package com.material.chain.base.handler;

import com.material.chain.base.exception.ApiException;
import com.material.chain.common.utils.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
   public ApiResult<String> buildShipException(ApiException exception) {
       exception.printStackTrace();
       ApiResult<String> result = new ApiResult<>();
       result.setData(null);
       result.setCode(exception.getCode());
       result.setMessage(exception.getMessage());
       return result;
   }
}
