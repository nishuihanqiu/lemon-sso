package com.lls.lemon.core.model;

import com.lls.lemon.core.enums.LemonState;

import java.io.Serializable;

/************************************
 * Result
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
public class Result<T> implements Serializable {

  private static final long serialVersionUID = -7345992330241L;

  private String code;
  private String message;
  private T data;

  public Result(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public Result(T data) {
    this.code = LemonState.SUCCESS.getCode();
    this.message = LemonState.SUCCESS.getMessage();
    this.data = data;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String toJson() {
    return "{\"code\":" + this.getCode() + ", \"message\":\"" + this.getMessage()
            + ", \"data\":\"" + this.getData() +"\"}";
  }
}
