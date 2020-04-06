package com.ky.dbbak.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FilterConfig
 * @Description: TODO
 * @Author czw
 * @Date 2020/4/6
 **/
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new UrlDispatcher());
        registration.addUrlPatterns("/*");
        registration.setName("UrlFilter");
        registration.setOrder(1);
        return registration;
    }
}
