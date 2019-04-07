package com.lls.lemon.core.session;

import com.lls.lemon.core.model.LemonAuth;

import javax.servlet.http.HttpServletRequest;

/************************************
 * Session
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
interface Session {

    /**
     * login
     *
     * @param auth           user auth
     * @param isRemember     <p>Default is {@code true}. Switch this to {@code false} don't remember it.
     * @param expiredSeconds expire time interval (seconds)
     * @return sessionId
     * @see #createSessionId(LemonAuth)
     */
    String login(LemonAuth auth, boolean isRemember, int expiredSeconds);

    /**
     * logout
     */
    void logout(HttpServletRequest request);

    /**
     * get user auth
     *
     * @param sessionId cookie value or token
     * @return user auth
     * @see #createSessionId(LemonAuth)
     */
    LemonAuth getAuth(String sessionId);

    /**
     * remove exists auth session token or cookie
     *
     * @param sessionId cookie value or token
     * @see #createSessionId(LemonAuth)
     */
    void remove(String sessionId);

    /**
     * create sessionId by auth
     *
     * @param auth user auth object
     * @return sessionId
     * @see #createSessionId(LemonAuth)
     */
    String createSessionId(LemonAuth auth);

    /**
     * get store key by sessionId
     *
     * @param sessionId cookie value or token
     * @return key for store {@link com.lls.lemon.core.store.Store }
     */
    String getStoreKey(String sessionId);

    /**
     * get session version by sessionId
     *
     * @param sessionId cookie value or token
     * @return version for session
     */
    String getVersion(String sessionId);

    /**
     * get sessionId by HttpServletRequest
     *
     * @param request HttpServletRequest
     * @return sessionId for session
     */
    String getSessionId(HttpServletRequest request);


}
