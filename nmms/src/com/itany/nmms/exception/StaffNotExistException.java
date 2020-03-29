package com.itany.nmms.exception;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 16:04
 * Description:
 * version:1.0
 */
public class StaffNotExistException extends Exception {
    public StaffNotExistException() {
    }

    public StaffNotExistException(String message) {
        super(message);
    }

    public StaffNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public StaffNotExistException(Throwable cause) {
        super(cause);
    }

    public StaffNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
