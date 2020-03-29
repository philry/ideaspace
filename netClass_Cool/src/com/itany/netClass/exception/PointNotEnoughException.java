package com.itany.netClass.exception;

public class PointNotEnoughException extends Exception {
    public PointNotEnoughException() {
    }

    public PointNotEnoughException(String message) {
        super(message);
    }

    public PointNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }

    public PointNotEnoughException(Throwable cause) {
        super(cause);
    }

    public PointNotEnoughException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
