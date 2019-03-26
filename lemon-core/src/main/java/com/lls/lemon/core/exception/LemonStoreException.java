package com.lls.lemon.core.exception;

/************************************
 * LemonStoreException
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class LemonStoreException extends LemonException {

    public LemonStoreException(String message) {
        super(message);
    }

    public LemonStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public LemonStoreException(Throwable cause) {
        super(cause);
    }

}
