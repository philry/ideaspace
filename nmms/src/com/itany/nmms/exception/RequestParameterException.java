package com.itany.nmms.exception;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/11/1 15:57
 * Description:
 * version:1.0
 */
public class RequestParameterException extends Exception {
    public RequestParameterException() {
    }

    public RequestParameterException(String message) {
        super(message);
    }

    public RequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestParameterException(Throwable cause) {
        super(cause);
    }

    public RequestParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
