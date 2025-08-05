package com.lamb.websocket.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

import java.util.Map;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-01
 * @Description: 测试
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @PostMapping
    public Map result(){
        return Map.of("result","success");
    }

}
