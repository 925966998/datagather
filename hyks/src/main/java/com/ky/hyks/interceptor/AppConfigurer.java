package com.ky.hyks.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

/**
 * @Classname:com.ky.azyx.interceptor
 * @Auther: ywj
 * @Date: 2020/8/31 09:24
 * @Description:
 */
@Configuration
public class AppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public HandlerInterceptor getMyInterceptor() {
        return new Interceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePaths = {"/ky-suppladmin.pngier/reset", "/ky-supplier/loginOut", "/web/*", "/js/pdfJs/web/viewer.html", "upload/*"};
        registry.addInterceptor(getMyInterceptor()).excludePathPatterns(Arrays.asList(excludePaths)).addPathPatterns("/ky-supplier/**");
        super.addInterceptors(registry);
    }
}
