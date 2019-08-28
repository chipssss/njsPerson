package com.njs.agriculture.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/8/28
 * @Description:
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("Time - " + time.format(LocalDateTime.now()));
        log.info("URL - " + request.getRequestURL());
        log.info("HttpMethod - " + request.getMethod() + "\r\n");
        return true;
    }
}
