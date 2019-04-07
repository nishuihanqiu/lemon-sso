package com.lls.lemon.core.filter;

import com.lls.lemon.core.model.LemonAuth;
import com.lls.lemon.core.path.AntPathMatcher;
import com.lls.lemon.core.session.LemonSession;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/************************************
 * LemonFilter
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public abstract class LemonFilter implements Filter {

    protected String openApiUrls;
    protected AntPathMatcher pathMatcher = new AntPathMatcher();

    protected boolean checkOpenApiFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (openApiUrls == null || openApiUrls.trim().length() == 0) {
            return false;
        }

        String path = request.getServletPath();
        String[] apiUrls = openApiUrls.split(";");
        List<String> results = Arrays.stream(apiUrls).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return pathMatcher.match(s.trim(), path);
            }
        }).collect(Collectors.toList());

        return !results.isEmpty();
    }

    protected abstract boolean checkIsLoginApiFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    protected abstract boolean checkLogoutApiFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    protected abstract void doApiFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException;

    @Override
    final public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (this.checkOpenApiFilter(httpServletRequest, httpServletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (this.checkLogoutApiFilter(httpServletRequest, httpServletResponse)) {
            return;
        }

        if (!this.checkIsLoginApiFilter(httpServletRequest, httpServletResponse)) {
            return;
        }

        this.doApiFilter(servletRequest, servletResponse, filterChain);
    }

    protected LemonAuth getLemonAuth(HttpServletRequest request, LemonSession session) {
        String sessionId = session.getSessionId(request);
        return session.getAuth(sessionId);
    }

}
