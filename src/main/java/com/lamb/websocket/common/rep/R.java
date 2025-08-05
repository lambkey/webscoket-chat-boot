package com.lamb.websocket.common.rep;

import lombok.Data;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-04
 * @Description: 返回结果封装
 * @Version: 1.0
 */
@Data
public class R<T> {

    private Integer code;
    private String message;
    private T data;

    public R(){

    }

    public static <T> R<T> success(T data){
        R<T> r = new R<>();
        r.setCode(200);
        r.setMessage("成功");
        r.setData(data);
        return r;
    }

    public static <T> R<T> success(String message, T data){
        R<T> r = new R<>();
        r.setCode(200);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    public static <T> R<T> error(Integer code, String message){
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static <T> R<T> error(String message){
        R<T> r = new R<>();
        r.setCode(500);
        r.setMessage(message);
        return r;
    }




}
