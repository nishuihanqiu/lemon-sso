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

    public TokenSession() {
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
    protected void logout(String sessionId) {
        if (sessionId == null || sessionId.trim().isEmpty()) {
            throw new LemonArgumentException("Token sessionId must not be null.");
        }
        this.remove(sessionId);
    }

    @Override
    public void logout(HttpServletRequest request) {
        String headerSessionId = this.getSessionId(request);
        if (headerSessionId == null) {
            return;
        }
        this.logout(headerSessionId);
    }

    @Override
    public LemonAuth getAuth(String sessionId) {
        return getStoreAuth(sessionId, store);
    }

    @Override
    public void remove(String sessionId) {
        String storeKey = this.getStoreKey(sessionId);
        if (storeKey == null) {
            return;
        }
        store.delete(storeKey);
    }


}
