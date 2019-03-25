package com.lls.lemon.core.model;

import com.lls.lemon.core.enums.LemonStateEnum;

import java.io.Serializable;

/************************************
 * Result
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
public class Result<T> implements Serializable {

  public static final long serialVersionUID = -7345992330241L;

  private String code;
  private String message;
  private T data;

  public Result(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public Result(T data) {
    this.code = LemonStateEnum.SUCCESS.getCode();
    this.message = LemonStateEnum.SUCCESS.getMessage();
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
}
