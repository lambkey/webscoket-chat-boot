package com.lamb.websocket.interceptor;

import com.lamb.websocket.common.exception.NotLoginException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-04
 * @Description: 登录拦截器
 * @Version: 1.0
 */

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 忽略预检查请求
        if (HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }

        if (request.getSession().getAttribute("user") != null){
            return true;
        }else {
            throw new NotLoginException("用户未登录");
        }
    }
}
