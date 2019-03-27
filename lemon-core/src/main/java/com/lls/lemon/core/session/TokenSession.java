package com.lls.lemon.core.session;

import com.lls.lemon.core.consts.LemonConsts;
import com.lls.lemon.core.exception.LemonArgumentException;
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
public class TokenSession extends LemonSession {

    private static final Logger logger = LoggerFactory.getLogger(TokenSession.class);
    private static final int DEFAULT_TOKEN_EXPIRED_TIME_SECONDS = 30 * 60;

    private static Store store = StoreManager.getInstance().getStore();
    private HttpServletRequest request;

    public TokenSession(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String login(LemonAuth auth, boolean isRemember, int expiredSeconds) {
        logger.info("token auth:{}, isRemember:{}", auth, isRemember);
        String storeKey = this.getStoreKey(auth);
        if (expiredSeconds <= DEFAULT_TOKEN_EXPIRED_TIME_SECONDS) {
            expiredSeconds = DEFAULT_TOKEN_EXPIRED_TIME_SECONDS;
        }
        logger.info("token expired time:{} seconds.", expiredSeconds);
        logger.info("token store key:{}", storeKey);
        store.set(storeKey, auth, expiredSeconds * 1000);
        return this.createSessionId(auth);
    }

    @Override
    public void logout(String sessionId) {
        if (sessionId == null || sessionId.trim().isEmpty()) {
            throw new LemonArgumentException("sessionId must not be null.");
        }
        this.remove(sessionId);
    }

    @Override
    public void logout() {
        if (request == null) {
            return;
        }
        String headerSessionId = request.getHeader(LemonConsts.LEMON_SESSION_ID);
        if (headerSessionId == null) {
            return;
        }
        this.logout(headerSessionId);
    }

    @Override
    public LemonAuth getAuth(String sessionId) {
        String storeKey = this.getStoreKey(sessionId);
        if (storeKey == null) {
            return null;
        }

        LemonAuth auth = store.get(storeKey);
        if (auth == null) {
            return null;
        }

        String version = auth.getVersion();
        if (!auth.getVersion().equals(version)) {
            store.delete(storeKey);
            return null;
        }

        if ((System.currentTimeMillis() - auth.getExpiredFlushTimeMills()) > auth.getExpiredTimeMills() / 2) {
            store.set(storeKey, auth, auth.getExpiredTimeMills());
        }
        return auth;
    }

    @Override
    public void remove(String sessionId) {
        if (sessionId == null) {
            return;
        }
        String storeKey = this.getStoreKey(sessionId);
        if (storeKey == null) {
            return;
        }
        store.delete(storeKey);
    }


    @Override
    public String getStoreKey(String sessionId) {
        return this.getValue(sessionId, 0);
    }

    @Override
    public String getVersion(String sessionId) {
        return this.getValue(sessionId, 1);
    }



}
