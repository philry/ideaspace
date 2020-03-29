package com.itany.netClass.exception;

public class RequestParameterException extends RuntimeException{
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
