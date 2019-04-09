package com.lls.lemon.sso.core;

import java.io.Serializable;

/************************************
 * Result
 * @author liliangshan
 * @date 2019-04-09
 ************************************/
public class Result<T> implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 400;
    public static final int NET_WORK_ERROR_code = 500;
    public static final Result<String> SUCCESS = new Result<String>(null);
    public static final Result<String> FAILED = new Result<String>(FAIL_CODE, null);

    private int code;
    private String message;
    private T data;

    public Result(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Result(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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