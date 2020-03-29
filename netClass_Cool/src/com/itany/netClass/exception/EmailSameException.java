package com.itany.netClass.exception;

public class EmailSameException extends Exception {
    public EmailSameException() {
    }

    public EmailSameException(String message) {
        super(message);
    }

    public EmailSameException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSameException(Throwable cause) {
        super(cause);
    }

    public EmailSameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
