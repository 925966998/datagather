package com.ky.hyks.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname:com.ky.azyx.interceptor
 * @Auther: ywj
 * @Date: 2020/8/31 09:10
 * @Description:
 */
public class Interceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURL().toString().contains("login")){
//            LicenseVerify licenseVerify = new LicenseVerify();
//            return licenseVerify.verify();
        }else{
            Object object = request.getSession().getAttribute("user");
            if (object == null){
                //未登录，返回登录页面
                logger.error("登录失效");
                response.setStatus(401);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*
        * TODO
        *
        *
        * */
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*
        * TODO
        *
        *
        * */
    }
}
