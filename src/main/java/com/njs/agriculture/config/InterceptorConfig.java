package com.njs.agriculture.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/12
 * @Description:
 */
@Configuration
@Slf4j
@JsonIgnoreProperties
public class InterceptorConfig implements WebMvcConfigurer {


    @Bean
    public HttpMessageConverters fastjsonHttpMessageConverter(){
        //定义一个转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        //在转换器中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        HttpMessageConverter<?> converter = fastConverter;

        return new HttpMessageConverters(converter);

    }




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
        if (os.toLowerCase().startsWith(windowsFlag)) {
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
