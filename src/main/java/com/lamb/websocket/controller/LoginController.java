package com.lamb.websocket.controller;

import com.lamb.websocket.common.exception.NotLoginException;
import com.lamb.websocket.common.rep.R;
import com.lamb.websocket.pojo.User;
import com.lamb.websocket.service.UserService;
import com.lamb.websocket.vo.LoginReqFormVO;
import com.lamb.websocket.vo.LoginRespVO;
import com.lamb.websocket.vo.UserRespVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-01
 * @Description: 登录验证控制器
 * @Version: 1.0
 */
@RequestMapping("/auth")
@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R<String> login(@RequestBody @Validated LoginReqFormVO loginReqFormVO, HttpServletRequest  request) {
        return userService.login(loginReqFormVO,request);
    }

    @GetMapping("/checkLoginStatus")
    public R<String> checkLoginStatus(HttpServletRequest request)  {
        UserRespVO user = (UserRespVO) request.getSession().getAttribute("user");
        if (user == null) {
            return R.error(401, "用户未登录");
        }
        return R.success("已登录");
    }


}
