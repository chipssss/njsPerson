package com.njs.agriculture.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApkInterceptorConfig implements WebMvcConfigurer {
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
            registry.addResourceHandler("/apk/**")
                    .addResourceLocations("file:D:\\static\\apk\\");
            //linux 和mac
        } else {
            registry.addResourceHandler("/apk/**")
                    .addResourceLocations("file:\\var\\static\\apk\\");
        }
    }

}
