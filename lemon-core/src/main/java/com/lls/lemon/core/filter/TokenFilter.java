package com.lls.lemon.core.filter;

import com.lls.lemon.core.consts.LemonConsts;
import com.lls.lemon.core.model.LemonAuth;
import com.lls.lemon.core.session.TokenSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/************************************
 * TokenFilter
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class TokenFilter extends LemonFilter {

    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    private String logoutPath;
    private TokenSession session;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.openApiUrls = filterConfig.getInitParameter(LemonConsts.LEMON_URL_OPEN_API_PATH);
        this.logoutPath = filterConfig.getInitParameter(LemonConsts.LEMON_URL_LOGOUT_PATH);
        String separator = filterConfig.getInitParameter(LemonConsts.LEMON_URL_SEPARATOR);
        pathMatcher.setPathSeparator(separator);

        logger.info("token filter init logout:{}, open api urls:{}", logoutPath, this.openApiUrls);
        session = new TokenSession();
        logger.info("token filter init completed.");
    }

    @Override
    protected boolean checkIsLoginApiFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LemonAuth auth = this.getLemonAuth(request, session);
        if (auth != null) {
            request.setAttribute(LemonConsts.LEMON_X_AUTH, auth);
            return true;
        }

        // response
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(LemonConsts.LEMON_FAILED_RESULT.toJson());
        return false;
    }

    @Override
    protected boolean checkLogoutApiFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String servletPath = request.getServletPath();
        if (logoutPath != null && logoutPath.trim().length() > 0 && logoutPath.equals(servletPath)) {
            session.logout(request);
            // response
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(LemonConsts.LEMON_SUCCESS_RESULT.toJson());
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
        logger.info("token filter destroy completed.");
    }

}
