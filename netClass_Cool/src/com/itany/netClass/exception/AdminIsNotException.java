package com.itany.netClass.exception;

public class AdminIsNotException extends Exception {
    public AdminIsNotException() {
    }

    public AdminIsNotException(String message) {
        super(message);
    }

    public AdminIsNotException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminIsNotException(Throwable cause) {
        super(cause);
    }

    public AdminIsNotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
