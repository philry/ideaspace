package com.itany.netClass.exception;

public class CourseExistsException extends Exception {
    public CourseExistsException() {
    }

    public CourseExistsException(String message) {
        super(message);
    }

    public CourseExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseExistsException(Throwable cause) {
        super(cause);
    }

    public CourseExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
