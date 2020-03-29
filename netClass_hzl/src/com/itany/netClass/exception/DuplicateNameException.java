package com.itany.netClass.exception;

public class DuplicateNameException extends Exception {
    public DuplicateNameException() {
    }

    public DuplicateNameException(String message) {
        super(message);
    }

    public DuplicateNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateNameException(Throwable cause) {
        super(cause);
    }

    public DuplicateNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
