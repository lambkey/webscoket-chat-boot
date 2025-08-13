package com.lamb.websocket.handler;

import com.lamb.websocket.common.exception.NotLoginException;
import com.lamb.websocket.common.rep.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-04
 * @Description: 统一异常处理
 * @Version: 1.0
 */
@ControllerAdvice
public class ExceptionRestHandler {


    private static final Logger LOG = LoggerFactory.getLogger(ExceptionRestHandler.class);

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public R<String> handlerNotLoginException(NotLoginException e, HandlerMethod handlerMethod)
    {
        LOG.info("业务异常，错误码:{},错误信息:{}",e.getCode(),e.getMessage());
        return R.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R<String> handlerException(Exception e, HandlerMethod handlerMethod)
    {
        LOG.error("系统异常，错误信息:{}",e.getMessage());
        return R.error(500, e.getMessage());
    }

}
