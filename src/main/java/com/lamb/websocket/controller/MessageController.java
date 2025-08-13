package com.lamb.websocket.controller;

import com.lamb.websocket.common.rep.R;
import com.lamb.websocket.service.MessageService;
import com.lamb.websocket.vo.MessageRespVO;
import com.lamb.websocket.vo.page.MessageQuery;
import com.lamb.websocket.vo.page.PageVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-11
 * @Description: 根据目标用户id和当前登录的用户id查找聊天记录
 * @Version: 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @GetMapping("/list")
    public R<Map<String, Object>> list(MessageQuery messageQuery){
        return messageService.page(messageQuery);
    }

    @PostMapping("/send")
    public R<String> send(@RequestBody MessageRespVO messageRespVO, HttpServletRequest request){
        return messageService.send(messageRespVO, request);
    }

}
