package com.hdm.springbootdemo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @ClassName: AyUserFilter
 * @description:
 * @author: huangdaming
 * @Date: 2019-12-01 12:18
 */
@WebFilter(filterName = "ayUserFilter",urlPatterns = "/*")
public class AyUserFilter implements Filter  {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------->init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("------->doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("------->destroy");
    }
}
