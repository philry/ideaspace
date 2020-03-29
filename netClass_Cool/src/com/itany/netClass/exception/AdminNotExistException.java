package com.itany.netClass.exception;

public class AdminNotExistException extends Exception {
    public AdminNotExistException() {
    }

    public AdminNotExistException(String message) {
        super(message);
    }

    public AdminNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminNotExistException(Throwable cause) {
        super(cause);
    }

    public AdminNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
