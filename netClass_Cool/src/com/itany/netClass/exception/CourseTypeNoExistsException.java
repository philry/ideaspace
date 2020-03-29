package com.itany.netClass.exception;

public class CourseTypeNoExistsException extends Exception {
    public CourseTypeNoExistsException() {
    }

    public CourseTypeNoExistsException(String message) {
        super(message);
    }

    public CourseTypeNoExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseTypeNoExistsException(Throwable cause) {
        super(cause);
    }

    public CourseTypeNoExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
