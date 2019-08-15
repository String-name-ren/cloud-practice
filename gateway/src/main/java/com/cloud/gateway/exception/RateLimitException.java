package com.cloud.gateway.exception;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-08-13 09:47
 **/
public class RateLimitException extends RuntimeException {

    public RateLimitException(String message) {
        super(message);
    }

}
