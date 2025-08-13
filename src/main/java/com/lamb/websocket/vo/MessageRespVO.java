package com.lamb.websocket.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-11
 * @Description: 消息记录返回类
 * @Version: 1.0
 */
@Data
public class MessageRespVO {

    /**
     *
     */
    private Integer id;

    /**
     * 发送者id
     */
    private Integer fromId;

    /**
     * 接收者id
     */
    private Integer toId;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    // 当前登录的用户
    private Integer currentId;

}
