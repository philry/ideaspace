package com.itany.nmms.exception;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/11/2 10:04
 * Description:
 * version:1.0
 */
public class FileUploadException extends Exception {
    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
