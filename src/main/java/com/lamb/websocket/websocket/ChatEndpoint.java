package com.lamb.websocket.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamb.websocket.common.utils.MessageSendUtil;
import com.lamb.websocket.config.WebSocketGetHttpSessionConfiguration;
import com.lamb.websocket.pojo.User;
import com.lamb.websocket.vo.UserRespVO;
import com.lamb.websocket.vo.WebsocketRespVO;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-06
 * @Description: 在线聊天websocket类
 * @Version: 1.0
 */
@ServerEndpoint(value = "/chat", configurator = WebSocketGetHttpSessionConfiguration.class)
@Component
public class ChatEndpoint {

    private static ConcurrentHashMap<UserRespVO, ChatEndpoint> userSessionMap = new ConcurrentHashMap<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {

        this.session = session;

        // 1. 用户连接上线，要找到当前登录的用户信息
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        UserRespVO user = (UserRespVO) httpSession.getAttribute("user");

        // 2. 获取当前用户信息，将当前用户信息以及用户的ChatEndpoint存储到一个map中
        userSessionMap.put(user, this);

        // 3. 将这个用户登录的用户信息发送给所有在线用户>> 系统消息
        String message = MessageSendUtil.sendSystemMessage(true, null, getOnlineUsers());


        // 4. 给所有用户发送当前登录用户上线的消息,消息中排除目标用户
        broadcastMessage(message);

    }

    // 广播消息给所有用户，除了自己
    private void broadcastMessage(String message){
        Set<UserRespVO> users = getOnlineUsers();

        for (UserRespVO user : users) {
            // 发送给目标用户
            ChatEndpoint chatEndpoint = userSessionMap.get(user);
            try {
                WebsocketRespVO websocketRespVO = JSONObject.parseObject(message, WebsocketRespVO.class);

                JSONArray userRespVOS =(JSONArray)websocketRespVO.getMessage();

                // 在消息中排除目标用户
                for (int i = userRespVOS.size() - 1; i >= 0; i--) {
                    if (userRespVOS.getJSONObject(i).getInteger("id").equals(user.getId())) {
                        userRespVOS.remove(i);
                    }
                }

                chatEndpoint.session.getBasicRemote().sendText(new ObjectMapper().writeValueAsString(websocketRespVO));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 获取所有在线用户
    private Set<UserRespVO> getOnlineUsers(){
        return userSessionMap.keySet();
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("接收到客户端的消息："+message);
    }

    @OnClose
    public void onClose(Session session) {
        // 1. 获取当前断开连接的用户信息
        HttpSession httpSession = (HttpSession)session.getUserProperties().get(HttpSession.class.getName());
        UserRespVO user = (UserRespVO) httpSession.getAttribute("user");

        // 2. 删除当前用户信息
        userSessionMap.remove(user);


        // 3. 将这个用户登录的用户信息发送给所有在线用户>> 系统消息
        String message = MessageSendUtil.sendSystemMessage(true, null, getOnlineUsers());


        // 4. 给所有用户发送当前登录用户上线的消息,消息中排除目标用户
        broadcastMessage(message);
    }


}
