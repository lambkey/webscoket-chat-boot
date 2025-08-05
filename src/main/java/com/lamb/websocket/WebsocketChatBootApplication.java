package com.lamb.websocket;

import cn.hutool.core.util.IdUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.lamb.websocket.mapper"})
@SpringBootApplication
public class WebsocketChatBootApplication {

    public static void main(String[] args) {
        MDC.put("LOG_ID", IdUtil.getSnowflake().nextIdStr());
        SpringApplication.run(WebsocketChatBootApplication.class, args);
    }

}
