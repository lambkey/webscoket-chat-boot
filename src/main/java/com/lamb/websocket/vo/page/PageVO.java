package com.lamb.websocket.vo.page;

import lombok.Data;

import java.util.List;

@Data
public class PageVO<T>{

    //总条数
    private Long total;

    //总页数
    private Long pages;

    //总记录
    private List<T> records;

    public PageVO(Long total,Long pages, List<T> records){
        this.total = total;
        this.pages = pages;
        this.records = records;
    }

}