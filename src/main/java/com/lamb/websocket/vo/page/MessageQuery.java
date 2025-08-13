package com.lamb.websocket.vo.page;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-11
 * @Description: 消息查询
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageQuery extends PageQuery{
    private String fromUserId;
}
