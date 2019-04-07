package com.lls.lemon.core.filter;

import com.lls.lemon.core.consts.LemonConsts;
import com.lls.lemon.core.enums.LemonState;
import com.lls.lemon.core.model.LemonAuth;
import com.lls.lemon.core.path.AntPathMatcher;
import com.lls.lemon.core.session.TokenSession;
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
    private TokenSession session;


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
        session = new TokenSession();
        logger.info("token filter init completed.");
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
        String sessionId = session.getSessionId(request);
        LemonAuth auth = session.getAuth(sessionId);
        if (auth != null) {
            request.setAttribute(LemonConsts.LEMON_X_AUTH, auth);
            filterChain.doFilter(request, response);
            return;
        }

        // response
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(LemonConsts.LEMON_FAILED_RESULT.toJson());

    }

    @Override
    void doLogoutApiFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String servletPath = request.getServletPath();
        if (logoutPath != null && logoutPath.trim().length() > 0 && logoutPath.equals(servletPath)) {
            session.logout(request);
            // response
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(LemonConsts.LEMON_SUCCESS_RESULT.toJson());
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    void doApiFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }

}
