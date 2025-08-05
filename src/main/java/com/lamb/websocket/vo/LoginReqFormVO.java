package com.lamb.websocket.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-04
 * @Description: 登录请求VO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class LoginReqFormVO {

    @NotNull(message="用户名不能为空")
    private String username;

    @NotNull(message="密码不能为空")
    private String password;
}
