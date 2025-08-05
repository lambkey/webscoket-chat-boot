package com.lamb.websocket.service;

import com.lamb.websocket.common.rep.R;
import com.lamb.websocket.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lamb.websocket.vo.LoginReqFormVO;
import com.lamb.websocket.vo.LoginRespVO;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author yzy52
* @description 针对表【t_user】的数据库操作Service
* @createDate 2025-08-01 17:21:37
*/
public interface UserService extends IService<User> {

    R<String> login(LoginReqFormVO loginReqFormVO, HttpServletRequest  request);

}
