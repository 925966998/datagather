package com.ky.dbbak.dbconfig;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Druid连接池配置类
 */
@Configuration
public class DruidConfig {

    /**
     * 配置Druid监控
     * 后台管理Servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        HashMap initParams = new HashMap();//这是配置的druid监控的登录密码
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");
        //默认就是允许所有访问
        initParams.put("allow", "");
        //黑名单的IP
        initParams.put("deny", "");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置web监控的filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        HashMap initParams = new HashMap();
        initParams.put("exclusions", "/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");//过滤掉需要监控的文件
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
