package com.itany.netClass.exception;

public class CodeNotWriteException extends Exception {
    public CodeNotWriteException() {
    }

    public CodeNotWriteException(String message) {
        super(message);
    }

    public CodeNotWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeNotWriteException(Throwable cause) {
        super(cause);
    }

    public CodeNotWriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
