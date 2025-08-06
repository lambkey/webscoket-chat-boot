package com.lamb.websocket.config;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;

import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-06
 * @Description: 通过该配置类，能解决websocket拿不到用户登录的session信息的问题
 * @Version: 1.0
 */
@Configuration
public class WebSocketGetHttpSessionConfiguration extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        HttpSession httpSession = (HttpSession)request.getHttpSession();
        // 将httpSession对象存储到配置对象中
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);

        super.modifyHandshake(sec, request, response);
    }
}
