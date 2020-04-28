package com.example.demo.util.aspect;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.IPUtil;
import com.example.demo.util.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/4/13.
 */


@Aspect
@Component
public class DoAgainSubmitAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Around(value ="execution( * com.example.demo.controller.IndexController.doAgainSubmit(..))" )
    public JSONObject before(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>进入DoAgainSubmitAspectBefore");
        JSONObject res= new JSONObject();
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String simpleName = signature.getDeclaringType().getSimpleName();

        HttpServletRequest request  = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();//获取requ
        String ip = IPUtil.getIpAddress(request);
        String ipKey = String.format("%s#%s",simpleName,methodName);
        int hashCode = Math.abs(ipKey.hashCode());
        String key = String.format("%s_%d",ip,hashCode);

        String value = (String) redisUtil.get(key);
        if (StringUtils.isNotBlank(value)){
            res.put("message","请勿重复提交");
            return res;
        }
        redisUtil.set(key, UUID.randomUUID().toString().replace("-", ""),5);

        res= (JSONObject)joinPoint.proceed();
        return res;
    }

}
