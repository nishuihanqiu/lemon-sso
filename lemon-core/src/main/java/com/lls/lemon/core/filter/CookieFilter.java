package com.lls.lemon.core.filter;

import com.lls.lemon.core.consts.LemonConsts;
import com.lls.lemon.core.model.LemonAuth;
import com.lls.lemon.core.session.CookieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    private String logoutPath;
    private CookieSession session;
    private String lemonURL;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String port = filterConfig.getInitParameter(LemonConsts.LEMON_PORT);
        String baseUrl = filterConfig.getInitParameter(LemonConsts.LEMON_HOST);
        if (port != null) {
            baseUrl = baseUrl + port;
        }
        String basePathPrefix = filterConfig.getInitParameter(LemonConsts.LEMON_URL_BASE_PATH_PREFIX);
        this.lemonURL = baseUrl + basePathPrefix;
        this.logoutPath = filterConfig.getInitParameter(LemonConsts.LEMON_URL_LOGOUT_PATH);
        this.openApiUrls = filterConfig.getInitParameter(LemonConsts.LEMON_URL_OPEN_API_PATH);
        String separator = filterConfig.getInitParameter(LemonConsts.LEMON_URL_SEPARATOR);
        pathMatcher.setPathSeparator(separator);

        logger.info("cookie filter init base url:{},base path prefix:{}, logout:{}, open api urls:{}",
                baseUrl, basePathPrefix, logoutPath, this.openApiUrls);
        session = new CookieSession();
        logger.info("cookie filter init completed.");
    }

    @Override
    protected boolean checkIsLoginApiFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LemonAuth auth = this.getLemonAuth(request, session);
        if (auth != null) {
            request.setAttribute(LemonConsts.LEMON_X_AUTH, auth);
            return true;
        }

        String header = request.getHeader("content-type");
        boolean isJson = header != null && header.contains("json");
        if (isJson) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(LemonConsts.LEMON_FAILED_RESULT.toJson());
            return false;
        }

        // total link
        String link = request.getRequestURL().toString();
        // redirect logout
        String redirectLoginURL = this.lemonURL.concat(LemonConsts.LEMON_URL_LOGIN_PATH) +
                "?" + LemonConsts.LEMON_URL_REDIRECT_PATH + "=" + link;
        response.sendRedirect(redirectLoginURL);
        return false;
    }

    @Override
    protected boolean checkLogoutApiFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String servletPath = request.getServletPath();
        if (logoutPath != null && logoutPath.trim().length() > 0 && logoutPath.equals(servletPath)) {
            session.logout(request);
            String redirectLogoutURL = this.lemonURL.concat(LemonConsts.LEMON_URL_LOGOUT_PATH);
            response.sendRedirect(redirectLogoutURL);
            return true;
        }
        return false;
    }

    @Override
    protected void doApiFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("cookie filter destroy completed.");
    }

}
