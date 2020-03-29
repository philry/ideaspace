package com.itany.nmms.exception;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/31 15:12
 * Description:
 * version:1.0
 */
public class ProductTypeExistsException extends Exception {
    public ProductTypeExistsException() {
    }

    public ProductTypeExistsException(String message) {
        super(message);
    }

    public ProductTypeExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductTypeExistsException(Throwable cause) {
        super(cause);
    }

    public ProductTypeExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
