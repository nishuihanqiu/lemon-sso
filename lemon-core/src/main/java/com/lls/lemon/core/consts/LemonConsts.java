package com.lls.lemon.core.consts;

import com.lls.lemon.core.enums.LemonState;
import com.lls.lemon.core.model.Result;

/************************************
 * LemonConsts
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
public class LemonConsts {

    /**
     * SESSION_ID
     */
    public static final String LEMON_SESSION_ID = "LEMON_SESSION_ID";

    /**
     * COOKIE DOMAIN
     */

    public static final String LEMON_COOKIE_DOMAIN = "LEMON_COOKIE_DOMAIN";

    /**
     * AUTH
     */
    public static final String LEMON_X_AUTH = "LEMON_X_AUTH";

    /**
     * HOST
     */
    public static final String LEMON_HOST = "lemon_host";

    /**
     * PORT
     */
    public static final String LEMON_PORT = "lemon_port";

    /**
     * URL SEPARATOR
     */
    public static final String LEMON_URL_SEPARATOR = "lemon_url_separator";

    /**
     * BASE PATH
     */
    public static final String LEMON_URL_BASE_PATH_PREFIX = "lemon_url_base_path_prefix";

    /**
     * LOGIN URL
     */
    public static final String LEMON_URL_LOGIN_PATH = "lemon_url_login_path";

    /**
     * LOGOUT URL
     */
    public static final String LEMON_URL_LOGOUT_PATH = "lemon_url_logout_path";

    /**
     * REDIRECT_URL
     */
    public static final String LEMON_URL_REDIRECT_PATH = "lemon_url_redirect_path";

    /**
     * OPEN API URLs
     */
    public static final String LEMON_URL_OPEN_API_PATH = "lemon_url_open_api_path";

    /**
     * DEFAULT EXPIRED TIME (MILLS)
     */
    public static final String LEMON_DEFAULT_EXPIRED_TIME_MILLS = "lemon_expired_time_mills";

    /**
     * SUCCESS RESULT
     */
    public static final Result<String> LEMON_SUCCESS_RESULT = new Result<>(LemonState.SUCCESS.getCode(),
            "success result");

    /**
     * FAILED RESULT
     */
    public static final Result<String> LEMON_FAILED_RESULT = new Result<>(LemonState.FAILED.getCode(),
            LemonState.FAILED.getMessage());

    /**
     * NETWORK ERROR
     */
    public static final Result<String> LEMON_NETWORK_ERROR = new Result<>(LemonState.NET_ERROR.getCode(),
            LemonState.NET_ERROR.getMessage());


}
