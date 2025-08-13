package com.lamb.websocket.service;

import com.lamb.websocket.common.rep.R;
import com.lamb.websocket.pojo.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lamb.websocket.vo.MessageRespVO;
import com.lamb.websocket.vo.page.MessageQuery;
import com.lamb.websocket.vo.page.PageVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

/**
* @author yzy52
* @description 针对表【t_message】的数据库操作Service
* @createDate 2025-08-11 15:50:00
*/
public interface MessageService extends IService<Message> {

    R<Map<String, Object>> page(MessageQuery query);

    R<String> send(MessageRespVO messageRespVO, HttpServletRequest request);
}
