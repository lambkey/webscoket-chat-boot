package com.lamb.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-06
 * @Description: 负责bean注入配置文件
 * @Version: 1.0
 */
@Configuration
public class BeanInjectConfig {

    // 注入websocket相关的配置
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
