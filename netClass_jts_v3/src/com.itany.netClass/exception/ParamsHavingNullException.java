package com.itany.netClass.exception;

public class ParamsHavingNullException extends Exception{
    public ParamsHavingNullException() {
        super();
    }

    public ParamsHavingNullException(String message) {
        super(message);
    }

    public ParamsHavingNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsHavingNullException(Throwable cause) {
        super(cause);
    }

    protected ParamsHavingNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
