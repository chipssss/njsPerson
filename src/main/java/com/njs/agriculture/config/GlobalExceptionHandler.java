package com.njs.agriculture.config;

import com.njs.agriculture.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/27
 * @Description:
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ServerResponse handler(Exception e) {
        e.printStackTrace();
        return ServerResponse.createByError("未知错误",e.getMessage());
    }
}
