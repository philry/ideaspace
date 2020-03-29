package com.itany.netClass.exception;

public class PointNotException extends Exception {
    public PointNotException() {
    }

    public PointNotException(String message) {
        super(message);
    }

    public PointNotException(String message, Throwable cause) {
        super(message, cause);
    }

    public PointNotException(Throwable cause) {
        super(cause);
    }

    public PointNotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
