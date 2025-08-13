package com.lamb.websocket.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamb.websocket.pojo.User;
import com.lamb.websocket.vo.UserRespVO;
import com.lamb.websocket.vo.WebsocketRespVO;


/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-06
 * @Description: 发送消息工具类，区别是系统消息还是用户消息
 * @Version: 1.0
 */
public class MessageSendUtil {

    /**
     * @Description: 如果isSystem为true，message则为用户的set集合，则是系统群发消息，否则就是个人对个人发送消息
     * @Param: [boolean, com.lamb.websocket.pojo.User, java.lang.Object]
     * @return: java.lang.String
     * @Author: 杨卓颖
     * @Date: 2025/8/6
    */
    public static String sendSystemMessage(boolean isSystem, UserRespVO fromUser, Object message) {
        
        try {
            WebsocketRespVO websocketRespVO = new WebsocketRespVO();
            websocketRespVO.setSystem(isSystem);
            if (fromUser != null){
                websocketRespVO.setFromUser(fromUser);
            }
            websocketRespVO.setMessage(message);

            ObjectMapper objectMapper = new ObjectMapper();
            
            return objectMapper.writeValueAsString(websocketRespVO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        

    }
}
