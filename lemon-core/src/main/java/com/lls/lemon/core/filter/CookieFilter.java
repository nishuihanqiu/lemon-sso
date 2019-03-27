package com.lls.lemon.core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/************************************
 * CookieFilter
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class CookieFilter extends LemonFilter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  void doOpenApiFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

  }

  @Override
  void doLoginApiFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

  }

  @Override
  void doLogoutApiFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

  }

  @Override
  void doApiFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

  }

  @Override
  public void destroy() {

  }

}
