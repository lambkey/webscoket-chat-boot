package com.lamb.websocket.vo.page;

import lombok.Data;

@Data
public class PageQuery {

    //当前页码
    private Long currentPage;

    //页码大小
    private Long pageSize;

}