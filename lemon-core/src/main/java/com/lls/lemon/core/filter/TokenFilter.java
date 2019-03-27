package com.lls.lemon.core.filter;

import com.lls.lemon.core.consts.LemonConsts;
import com.lls.lemon.core.path.AntPathMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/************************************
 * TokenFilter
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class TokenFilter extends LemonFilter {

    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    private String baseUrl;
    private String basePathPrefix;
    private String logoutPath;
    private String openApiUrls;
    private AntPathMatcher pathMatcher = new AntPathMatcher();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String port = filterConfig.getInitParameter(LemonConsts.LEMON_PORT);
        this.baseUrl = filterConfig.getInitParameter(LemonConsts.LEMON_HOST);
        if (port != null) {
            this.baseUrl = this.baseUrl + port;
        }
        this.basePathPrefix = filterConfig.getInitParameter(LemonConsts.LEMON_URL_BASE_PATH_PREFIX);
        this.logoutPath = filterConfig.getInitParameter(LemonConsts.LEMON_URL_LOGOUT_PATH);
        this.openApiUrls = filterConfig.getInitParameter(LemonConsts.LEMON_URL_OPEN_API_PATH);
        String separator = filterConfig.getInitParameter(LemonConsts.LEMON_URL_SEPARATOR);
        pathMatcher.setPathSeparator(separator);

        logger.info("token filter init base url:{},base path prefix:{}, logout:{}, open api urls:{}",
                baseUrl, basePathPrefix, logoutPath, this.openApiUrls);
    }

    @Override
    void doOpenApiFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        if (openApiUrls == null || openApiUrls.trim().length() == 0) {
            return;
        }

        String path = request.getServletPath();
        String[] apiUrls = openApiUrls.split(";");
        List<String> results = Arrays.stream(apiUrls).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return pathMatcher.match(s.trim(), path);
            }
        }).collect(Collectors.toList());

        if (!results.isEmpty()) {
            filterChain.doFilter(request, response);
        }

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
