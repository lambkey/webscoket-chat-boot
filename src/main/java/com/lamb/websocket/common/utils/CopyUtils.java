package com.lamb.websocket.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author JoinYang
 * @date 2023/9/7 0:01
 * @Version 1.0
 */
public class CopyUtils {

    /**
     * 对BeanUtils进行二次封装，方便List集合的元素对象属性的Copy
     * @param sourceList
     * @param targetClass
     * @param <S>
     * @param <T>
     * @return List<targetClass.newInstance()>
     */
    public static <S, T> List<T> copyListProperties(List<S> sourceList,Class<T> targetClass){

        if (sourceList == null || sourceList.isEmpty()){
            return Collections.emptyList();
        }

        List<T> result = new ArrayList<>();

        sourceList.forEach(

                x ->{
                    T t;
                    try {
                      t =  targetClass.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException("创建targetClass对象实例异常");
                    }
                    BeanUtils.copyProperties(x,t);
                    result.add(t);
                }
        );

        return result;
    }

    /**
     * 普通对象属性复制
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, Class<T> targetClass){

        if (Objects.isNull(source)){
            return null;
        }
        T t = null;
        try {
            t = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source,t);

        return t;

    }

}
