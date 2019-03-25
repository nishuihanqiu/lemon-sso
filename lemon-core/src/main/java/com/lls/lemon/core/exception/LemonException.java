package com.lls.lemon.core.exception;

/************************************
 * LemonException
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
public class LemonException extends RuntimeException {

  public LemonException(String message) {
    super(message);
  }

  public LemonException(String message, Throwable cause) {
    super(message, cause);
  }

  public LemonException(Throwable cause) {
    super(cause);
  }

}
