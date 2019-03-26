package com.lls.lemon.core.exception;

/************************************
 * LemonSerializerException
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class LemonSerializerException extends LemonException {

    public LemonSerializerException(String message) {
        super(message);
    }

    public LemonSerializerException(String message, Throwable cause) {
        super(message, cause);
    }

    public LemonSerializerException(Throwable cause) {
        super(cause);
    }

}
