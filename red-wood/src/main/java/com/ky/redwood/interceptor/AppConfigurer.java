package com.ky.redwood.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
public class AppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public HandlerInterceptor getMyInterceptor() {
        return new Interceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePaths = {"/ky-redwood/login", "/ky-redwood/reset", "/ky-redwood/loginOut", "/web/*"};
        registry.addInterceptor(getMyInterceptor()).excludePathPatterns(Arrays.asList(excludePaths)).addPathPatterns("/ky-redwood/**");
        super.addInterceptors(registry);
    }
}
