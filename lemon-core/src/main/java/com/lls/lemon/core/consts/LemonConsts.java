package com.lls.lemon.core.consts;

import com.lls.lemon.core.enums.LemonStateEnum;
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
  public static final String LEMON_SESSION_ID = "lemon_session_id";

  /**
   * AUTH
   */
  public static final String LEMON_X_AUTH = "lemon_x_auth";

  /**
   * HOST
   */
  public static final String LEMON_HOST = "lemon_host";

  /**
   * PORT
   */
  public static final String LEMON_PORT = "lemon_port";

  /**
   * LOGIN URL
   */
  public static final String LEMON_LOGIN_URL = "lemon_login_url";

  /**
   * LOGOUT URL
   */
  public static final String LEMON_LOGOUT_URL = "lemon_logout_url";

  /**
   * REDIRECT_URL
   */
  public static final String LEMON_REDIREC_URL = "lemon_redirect_url";

  /**
   * SUCCESS RESULT
   */
  public static final Result<String> LEMON_SUCCESS_RESULT = new Result<>("success result");

  /**
   * FAILED RESULT
   */
  public static final Result<String> LEMON_FAILED_RESULT = new Result<>(LemonStateEnum.FAILED.getCode(),
    LemonStateEnum.FAILED.getMessage());

  /**
   * NETWORK ERROR
   */
  public static final Result<String> LEMON_NETWORK_ERROR = new Result<>(LemonStateEnum.NET_ERROR.getCode(),
    LemonStateEnum.NET_ERROR.getMessage());


}
