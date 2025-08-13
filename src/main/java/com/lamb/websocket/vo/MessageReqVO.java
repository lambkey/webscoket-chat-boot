package com.lamb.websocket.vo;

import lombok.Data;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-12
 * @Description: 发送消息请求
 * @Version: 1.0
 */
@Data
public class MessageReqVO {

    private Integer toId;

    private String content;

}
