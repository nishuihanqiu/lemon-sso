package com.lls.lemon.core.exception;

/************************************
 * LemonArgumentException
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class LemonArgumentException extends LemonException {

    public LemonArgumentException(String s) {
        super(s);
    }

    public LemonArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public LemonArgumentException(Throwable cause) {
        super(cause);
    }

}
