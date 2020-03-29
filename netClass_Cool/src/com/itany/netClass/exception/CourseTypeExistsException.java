package com.itany.netClass.exception;

public class CourseTypeExistsException extends Exception {
    public CourseTypeExistsException() {
    }

    public CourseTypeExistsException(String message) {
        super(message);
    }

    public CourseTypeExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseTypeExistsException(Throwable cause) {
        super(cause);
    }

    public CourseTypeExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
