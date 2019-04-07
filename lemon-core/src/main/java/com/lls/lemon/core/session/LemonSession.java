package com.lls.lemon.core.session;

import com.lls.lemon.core.consts.LemonConsts;
import com.lls.lemon.core.exception.LemonArgumentException;
import com.lls.lemon.core.model.LemonAuth;
import com.lls.lemon.core.store.Store;

import javax.servlet.http.HttpServletRequest;

/************************************
 * LemonSession
 * @author liliangshan
 * @date 2019-03-27
 ************************************/
public abstract class LemonSession implements Session {

    private static final String DEFAULT_SESSION_SEPARATOR = "_";

    protected String getStoreKey(LemonAuth auth) {
        if (auth == null) {
            throw new LemonArgumentException("AUTH must not be null.");
        }

        Long authId = auth.getAuthId();
        if (authId == null) {
            throw new LemonArgumentException("AUTH ID must not be null.");
        }

        return String.valueOf(authId);
    }

    abstract protected void logout(String sessionId);

    @Override
    public String createSessionId(LemonAuth auth) {
        if (auth == null) {
            throw new LemonArgumentException("auth must not be null.");
        }
        Long authId = auth.getAuthId();
        if (authId == null) {
            throw new LemonArgumentException("auth id must not be null.");
        }
        String version = auth.getVersion();
        if (version == null) {
            throw new LemonArgumentException("auth version must not be null.");
        }
        return String.valueOf(auth.getAuthId()).concat(DEFAULT_SESSION_SEPARATOR).concat(version);
    }

    private static String[] parseTokenSessionId(String sessionId) {
        if (sessionId == null || !sessionId.contains(DEFAULT_SESSION_SEPARATOR)) {
            return null;
        }
        return sessionId.split(DEFAULT_SESSION_SEPARATOR);
    }

    private static String getValue(String sessionId, int index) {
        String[] values = parseTokenSessionId(sessionId);
        if (values == null || values.length < (index + 1)) {
            return null;
        }

        String value = values[index];
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value;
    }

    @Override
    public String getStoreKey(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        return getValue(sessionId, 0);
    }

    @Override
    public String getVersion(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        return getValue(sessionId, 1);
    }

    public static LemonAuth getStoreAuth(String sessionId, Store store) {
        String storeKey = getValue(sessionId, 0);
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
    public String getSessionId(HttpServletRequest request) {
        if (request == null) {
            throw new LemonArgumentException("http servlet request must not be null.");
        }
        return request.getHeader(LemonConsts.LEMON_SESSION_ID);
    }
}
