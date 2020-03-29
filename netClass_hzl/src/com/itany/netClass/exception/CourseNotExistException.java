package com.itany.netClass.exception;

public class CourseNotExistException extends Exception {
    public CourseNotExistException() {
    }

    public CourseNotExistException(String message) {
        super(message);
    }

    public CourseNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseNotExistException(Throwable cause) {
        super(cause);
    }

    public CourseNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
