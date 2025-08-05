package com.lamb.websocket.aspect;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yangzhuoying
 * @CreateTime: 2025-08-01
 * @Description: 请求日志切面打印
 * @Version: 1.0
 */
@Aspect
@Component
public class LogAspect {

    public static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.lamb.websocket.controller.*.*(..))")
    public void controllerAspectPointCut() {
    }

    @Before("controllerAspectPointCut()")
    public void doBefore(JoinPoint joinPoint){

        MDC.put("LOG_ID", IdUtil.getSnowflake().nextIdStr());

        // 开始打印日志
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        LOG.info("--------------------LogAspect 开始---------------------");
        LOG.info("请求地址:{}{}",request.getRequestURL().toString(),request.getMethod());
        LOG.info("类名方法:{}.{}",signature.getDeclaringTypeName(),name);
        LOG.info("远程地址:{}",getRemoteIp(request));

        // 打印请求参数
        Object[] args = joinPoint.getArgs();

        List<Object> collect = Arrays.stream(args)
                .filter(x -> !(x instanceof ServletRequest || x instanceof ServletResponse || x instanceof MultipartFile[]))
                .collect(Collectors.toList());


        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();

        // 添加过滤字段
//        filter.getExcludes().add("pkCorp");

        SerializeFilter[] filters = {filter};

        LOG.info("请求参数:{}", JSON.toJSONString(collect,filters, SerializerFeature.WriteNullListAsEmpty));

    }

    @Around("controllerAspectPointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        // 过滤文件返回结果
        if (ObjectUtil.isNotEmpty(result)){
            String resultString = JSON.toJSONString(result);
            JSONObject jsonObject = JSON.parseObject(resultString);
            LOG.info("返回结果:{}", jsonObject);
        }

        LOG.info("----------------- LogAspect 结束 耗时:{} ms -----------------",System.currentTimeMillis() - startTime);

        return result;
    }


    /**
     * 使用nginx做反向代理,需要用该方法才能去到正式的远程IP
     * @param request
     * @return
     */
    public String getRemoteIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }

        return ip;
    }


}
