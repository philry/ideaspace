package com.itany.netClass.exception;

public class adminNotExistException extends Exception {
    public adminNotExistException() {
    }

    public adminNotExistException(String message) {
        super(message);
    }

    public adminNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public adminNotExistException(Throwable cause) {
        super(cause);
    }

    public adminNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
