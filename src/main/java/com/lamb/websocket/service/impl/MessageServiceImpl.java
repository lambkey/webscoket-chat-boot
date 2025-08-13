package com.lamb.websocket.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lamb.websocket.common.rep.R;
import com.lamb.websocket.common.utils.CopyUtils;
import com.lamb.websocket.pojo.Message;
import com.lamb.websocket.pojo.User;
import com.lamb.websocket.service.MessageService;
import com.lamb.websocket.mapper.MessageMapper;
import com.lamb.websocket.vo.MessageRespVO;
import com.lamb.websocket.vo.UserRespVO;
import com.lamb.websocket.vo.page.MessageQuery;
import com.lamb.websocket.vo.page.PageVO;
import com.lamb.websocket.websocket.ChatEndpoint;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.management.Query;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author yzy52
* @description 针对表【t_message】的数据库操作Service实现
* @createDate 2025-08-11 15:50:00
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

    @Override
    public R<Map<String, Object>> page(MessageQuery query) {

        Long currentPage = query.getCurrentPage();
        Long pageSize = query.getPageSize();

        Page<Message> objectPage = Page.of(currentPage, pageSize);


        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("from_id", query.getFromUserId());
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        UserRespVO user = (UserRespVO)request.getSession().getAttribute("user");
        queryWrapper.eq("to_id", user.getId());
        queryWrapper.or();
        queryWrapper.eq("from_id", user.getId());
        queryWrapper.eq("to_id", query.getFromUserId());





        Page<Message> page = this.page(objectPage,queryWrapper);

        List<Message> records = page.getRecords();

        List<MessageRespVO> messageRespVOS = CopyUtils.copyListProperties(records, MessageRespVO.class);

        Map<String, Object> result = new HashMap<>();
        result.put("resultPage", new PageVO<>(page.getTotal(),page.getSize(),  messageRespVOS));
        result.put("currentUser", user.getId());
        result.put("chatWithUser", query.getFromUserId());

        return R.success(result);
    }

    @Override
    public R<String> send(MessageRespVO messageRespVO, HttpServletRequest request) {


        Message message = CopyUtils.copy(messageRespVO, Message.class);

        UserRespVO user = (UserRespVO)request.getSession().getAttribute("user");

        assert message != null;

        message.setFromId(user.getId());
        message.setCreatedTime(new Date());
        // 保存消息
        this.save( message);

        // 往前端发送消息
        ChatEndpoint.sendMessage(message);


        return R.success("发送成功");
    }
}




