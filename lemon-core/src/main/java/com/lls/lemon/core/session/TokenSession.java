package com.lls.lemon.core.session;

import com.lls.lemon.core.model.LemonAuth;
import com.lls.lemon.core.store.Store;
import com.lls.lemon.core.store.StoreManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/************************************
 * TokenSession
 * @author liliangshan
 * @date 2019-03-27
 ************************************/
public class TokenSession implements Session {

    private static final Logger logger = LoggerFactory.getLogger(TokenSession.class);

    private static Store store = StoreManager.getInstance().getStore();
    private HttpServletRequest request;

    public TokenSession(HttpServletRequest request) {
        this.request = request;
    }

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

}
