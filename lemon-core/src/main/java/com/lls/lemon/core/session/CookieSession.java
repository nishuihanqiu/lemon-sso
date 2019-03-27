package com.lls.lemon.core.session;

import com.lls.lemon.core.model.LemonAuth;
import com.lls.lemon.core.store.Store;
import com.lls.lemon.core.store.StoreManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/************************************
 * CookieSession
 * @author liliangshan
 * @date 2019-03-27
 ************************************/
public class CookieSession implements Session {

    private static final Logger logger = LoggerFactory.getLogger(CookieSession.class);

    private HttpServletRequest request;
    private HttpServletResponse response;
    private static Store store = StoreManager.getInstance().getStore();

    @Override
    public String login(LemonAuth auth, boolean isRemember) {
        return null;
    }

    @Override
    public void logout(String sessionId) {

    }

    @Override
    public LemonAuth getAuth(String sessionId) {
        return null;
    }

    @Override
    public void remove(String sessionId) {

    }

    @Override
    public String createSessionId(LemonAuth auth) {
        return null;
    }

    @Override
    public String getStoreKey(String sessionId) {
        return null;
    }

    @Override
    public String getVersion(String sessionId) {
        return null;
    }
}
