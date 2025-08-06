package com.lamb.websocket.websocket;

import com.lamb.websocket.config.WebSocketGetHttpSessionConfiguration;
import com.lamb.websocket.pojo.User;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-06
 * @Description: 在线聊天websocket类
 * @Version: 1.0
 */
@ServerEndpoint(value = "/chat", configurator = WebSocketGetHttpSessionConfiguration.class)
@Component
public class ChatEndpoint {

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        System.out.println("websocket和客户端连接成功");
       // 1. 用户连接上线，要找到当前登录的用户信息
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        User user = (User) httpSession.getAttribute("user");
        System.out.println("当前登录的用户："+user);

    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("接收到客户端的消息："+message);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("websocket和客户端连接关闭");
    }


}
