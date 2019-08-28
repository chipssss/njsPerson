package com.njs.agriculture.config;

import com.njs.agriculture.common.Const;
import com.njs.agriculture.common.ResponseCode;
import com.njs.agriculture.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
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
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(LogInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(AuthorityInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public AuthorityInterceptor AuthorityInterceptor() {
        return new AuthorityInterceptor();
    }

    @Bean
    public LogInterceptor LogInterceptor() {
        return new LogInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * * 根据系统标识来判断合适的文件路径格式
         * * 下面将路径中的/img/XX.jpg映射到文件系统中的D:/static/img/XX.jpg或者Unix下的/var/static/img/XX.jpg
         * * 如果需要映射的文件夹是在项目中的，比如resource/static,
         *   可以使用.addResourceLocations("classpath:/static/")
         */

        String os = System.getProperty("os.name");
        final String windowsFlag = "win";
        // windows
        if (os.toLowerCase().startsWith(windowsFlag)){
            registry.addResourceHandler("/img/**")
                    .addResourceLocations("file:D:\\static\\img\\");
            //linux 和mac
        } else {
            registry.addResourceHandler("/img/**")
                    .addResourceLocations("file:\\var\\static\\img\\");
        }
    }

    public static void main(String[] args) {
        String url = "/portal/re.do";
        System.out.println(url.indexOf("/portal/"));
    }
}
