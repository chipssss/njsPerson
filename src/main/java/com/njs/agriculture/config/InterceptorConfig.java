package com.njs.agriculture.config;

import com.njs.agriculture.common.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import se.jiderhamn.classloader.leak.prevention.ClassLoaderLeakPreventorListener;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/12
 * @Description:
 */
@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {


//    //防止内存泄漏，远程部署tomcat,监听器
//    @Bean
//    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
//        ServletListenerRegistrationBean slrBean = new ServletListenerRegistrationBean();
//        slrBean.setListener(new ClassLoaderLeakPreventorListener());
//        return slrBean;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //日志
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                log.info("Time - " + time.format(LocalDateTime.now()));
                log.info("URL - " + request.getRequestURL());
                log.info("HttpMethod - " + request.getMethod() + "\r\n");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        });


        //拦截登录
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                // 获取访问的url
                String url = request.getRequestURI();

                if (url.indexOf("/portal/") >= 0)
                    return true;

                if (url.indexOf("/backend/user/register") >= 0)
                    return true;

                if (url.indexOf("/backend/user/login") >= 0)
                    return true;

                //判断是否已登录
                HttpSession seesion = request.getSession();
                if (seesion.getAttribute(Const.CURRENT_ADMIN) != null)
                    return true;

                /*若不满足以上，拦截，跳转到首页*/
                //TODO 首页
                return false;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        });
    }



    public static void main(String[] args) {
        String url = "/portal/re.do";
        System.out.println(url.indexOf("/portal/"));
    }
}
