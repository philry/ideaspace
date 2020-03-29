package com.itany.netClass.exception;

public class MessageIsNullException extends Exception {
    public MessageIsNullException() {
    }

    public MessageIsNullException(String message) {
        super(message);
    }

    public MessageIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageIsNullException(Throwable cause) {
        super(cause);
    }

    public MessageIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
