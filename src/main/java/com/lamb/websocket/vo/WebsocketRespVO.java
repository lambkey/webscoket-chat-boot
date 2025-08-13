package com.lamb.websocket.vo;

import com.lamb.websocket.pojo.User;
import lombok.Data;
import lombok.Setter;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-06
 * @Description: websocket消息返回VO
 * @Version: 1.0
 */
@Data
@Setter
public class WebsocketRespVO {

    private boolean isSystem;
    private UserRespVO fromUser;
    private Object message; // 如果isSystem为true，message则为用户的set集合

}
