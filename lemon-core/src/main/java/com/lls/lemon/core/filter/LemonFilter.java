package com.lls.lemon.core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/************************************
 * LemonFilter
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public abstract class LemonFilter implements Filter {

    abstract void doOpenApiFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException;

    abstract void doLoginApiFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException;

    abstract void doLogoutApiFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException;

    abstract void doApiFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException;

    @Override
    final public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        this.doOpenApiFilter(httpServletRequest, httpServletResponse, filterChain);
        this.doLoginApiFilter(httpServletRequest, httpServletResponse, filterChain);
        this.doLogoutApiFilter(httpServletRequest, httpServletResponse, filterChain);
        this.doApiFilter(servletRequest, servletResponse, filterChain);

    }

}
