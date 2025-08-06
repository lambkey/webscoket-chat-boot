package com.lamb.websocket.config;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-06
 * @Description: 通过该配置监听类，能解决websocket为null问题
 * @Version: 1.0
 */
@Component
public class WebsocketListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre)  {
        HttpSession session = ((HttpServletRequest) sre.getServletRequest()).getSession();
    }

    public WebsocketListener(){}

    @Override
    public void requestDestroyed(ServletRequestEvent arg0)  {}
}