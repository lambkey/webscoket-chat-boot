package com.lamb.websocket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lamb.websocket.common.rep.R;
import com.lamb.websocket.pojo.User;
import com.lamb.websocket.service.UserService;
import com.lamb.websocket.mapper.UserMapper;
import com.lamb.websocket.vo.LoginReqFormVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
* @author yzy52
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2025-08-01 17:21:37
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    /**
     * @Description: 登录校验
     * @Param: [com.lamb.websocket.vo.LoginReqFormVO]
     * @return: com.lamb.websocket.vo.LoginRespVO
     * @Author: 杨卓颖
     * @Date: 2025/8/4
    */
    @Override
    public R<String> login(LoginReqFormVO loginReqFormVO, HttpServletRequest  request) {

        User user = this.getOne(new QueryWrapper<User>().eq("username", loginReqFormVO.getUsername()));

        System.out.println(request.getSession().getAttribute("user"));

        // 登录成功设置Session登录信息
        if (user != null && user.getPassword().equals(loginReqFormVO.getPassword())) {
            request.getSession().setAttribute("user", user);
            return R.success(user.getUsername());
        }

        return R.error(401,"用户名或密码错误");
    }
}




