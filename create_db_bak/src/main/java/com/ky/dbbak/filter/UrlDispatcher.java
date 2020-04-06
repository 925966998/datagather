package com.ky.dbbak.filter;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @ClassName UrlDispatcher
 * @Description: TODO
 * @Author czw
 * @Date 2020/4/6
 **/
public class UrlDispatcher implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
        String path = httpRequest.getRequestURI();
        Object versionFlag = httpRequest.getSession().getAttribute("versionFlag");
        if (versionFlag != null) {
            if (versionFlag.toString().equals("G版") && path.contains("ky-datagather")) {
                httpRequest.getRequestDispatcher(path + "GB").forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
        return;
    }
}
