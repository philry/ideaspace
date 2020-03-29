package com.itany.netClass.exception;

public class CourseTypeNotExistException extends Exception {
    public CourseTypeNotExistException() {
    }

    public CourseTypeNotExistException(String message) {
        super(message);
    }

    public CourseTypeNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseTypeNotExistException(Throwable cause) {
        super(cause);
    }

    public CourseTypeNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
