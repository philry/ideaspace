package com.itany.netClass.exception;

public class StatusErrorException extends Exception {
    public StatusErrorException() {
    }

    public StatusErrorException(String message) {
        super(message);
    }

    public StatusErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusErrorException(Throwable cause) {
        super(cause);
    }

    public StatusErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
