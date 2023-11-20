package com.material.chain.user.handler;

import com.material.chain.common.utils.ApiResult;
import com.material.chain.user.Exception.GlobalException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
   public ApiResult<String> buildShipException(GlobalException exception) {
       exception.printStackTrace();
       ApiResult<String> result = new ApiResult<>();
       result.setData(null);
       result.setCode(exception.getCode());
       result.setMessage(exception.getMessage());
       return result;
   }
}
