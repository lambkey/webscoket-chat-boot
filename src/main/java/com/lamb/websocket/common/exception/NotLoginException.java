package com.lamb.websocket.common.exception;

import lombok.Data;
import lombok.Getter;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-04
 * @Description: 未登录异常
 * @Version: 1.0
 */
@Getter
public class NotLoginException extends RuntimeException{

    private final Integer code;
    private final String message;


    public NotLoginException(String message) {
        super(message);
        this.code = 401;
        this.message = message;
    }


    // 不在控制台打印异常
//    @Override
//    public synchronized Throwable fillInStackTrace() {
//        return this;
//    }


}
