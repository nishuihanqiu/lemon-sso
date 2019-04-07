package com.lls.lemon.core.session;

import com.lls.lemon.core.consts.LemonConsts;
import com.lls.lemon.core.exception.LemonArgumentException;
import com.lls.lemon.core.model.LemonAuth;
import com.lls.lemon.core.store.Store;
import com.lls.lemon.core.store.StoreManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/************************************
 * CookieSession
 * @author liliangshan
 * @date 2019-03-27
 ************************************/
public class CookieSession extends LemonSession {

    private static final Logger logger = LoggerFactory.getLogger(CookieSession.class);
    private static final int DEFAULT_COOKIE_EXPIRED_TIME_SECONDS = 30 * 60;
    // 默认缓存时间,单位/秒, 2H
    private static final int COOKIE_MAX_AGE = Integer.MAX_VALUE;
    // 保存路径,根路径
    private static final String COOKIE_PATH = "/";

    private HttpServletResponse response;
    private static Store store = StoreManager.getInstance().getStore();

    public CookieSession() {
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public String login(LemonAuth auth, boolean isRemember, int expiredSeconds) {
        logger.info("cookie auth:{}, isRemember:{}", auth, isRemember);
        if (expiredSeconds <= DEFAULT_COOKIE_EXPIRED_TIME_SECONDS) {
            expiredSeconds = DEFAULT_COOKIE_EXPIRED_TIME_SECONDS;
        }
        logger.info("cookie expired time:{} seconds.", expiredSeconds);
        String storeKey = this.getStoreKey(auth);
        logger.info("cookie store key:{}", storeKey);
        store.set(storeKey, auth, expiredSeconds * 1000);
        String sessionId = this.createSessionId(auth);
        this.buildCookie(sessionId, isRemember);
        return sessionId;
    }

    private void buildCookie(String sessionId, boolean isRemember) {
        int age = isRemember ? COOKIE_MAX_AGE : -1;
        Cookie cookie = new Cookie(LemonConsts.LEMON_SESSION_ID, sessionId);
        cookie.setDomain(LemonConsts.LEMON_COOKIE_DOMAIN);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(age);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Override
    protected void logout(String sessionId) {
        if (sessionId == null || sessionId.trim().isEmpty()) {
            throw new LemonArgumentException("Cookie sessionId must not be null.");
        }
        this.remove(sessionId);
    }

    @Override
    public void logout(HttpServletRequest request) {
        String sessionId = this.getSessionId(request);
        if (sessionId == null) {
            return;
        }
        this.logout(sessionId);
    }

    @Override
    public LemonAuth getAuth(String sessionId) {
        return getStoreAuth(sessionId, store);
    }

    @Override
    public void remove(String sessionId) {
        logger.info("remove cookie sessionId:{}", sessionId);
        Cookie cookie = new Cookie(LemonConsts.LEMON_SESSION_ID, null);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        String storeKey = getStoreKey(sessionId);
        if (storeKey == null) {
            return;
        }
        store.delete(storeKey);
    }

    private Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }

        Cookie cookie = null;
        for (Cookie item : cookies) {
            if (LemonConsts.LEMON_SESSION_ID.equals(item.getName())) {
                cookie = item;
            }
        }
        return cookie;
    }

    @Override
    public String getSessionId(HttpServletRequest request) {
        Cookie cookie = this.getCookie(request);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }
}
