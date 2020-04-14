package com.example.demo.util.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2020/4/13.
 */


@Aspect
@Component
public class LogAspect {


    @Around(value = "execution(* com.example.demo.controller.IndexController.doAjax(..))")
    //这个Around只执行一次 妈的不知道为什么
    public JSONObject around(ProceedingJoinPoint joinPoint) throws Throwable {  //ProceedingJoinPoint只能在around里面用
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>进入Around");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getHeader("token"));

        System.out.println("Around");
        JSONObject result = (JSONObject) joinPoint.proceed(); //around改变了原方法的返回值，你不写这个就gg
        return result;
    }


    @Before(value ="execution( * com.example.demo.controller.IndexController.*(..))" )
    public void before(JoinPoint joinPoint){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>进入Before");
        String operationType = "";
        String operateExplain = "";
        //获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
        Signature signature = joinPoint.getSignature();
        //目标方法名
        String methodName = signature.getName();
        //目标方法所属类的简单类名
        String simpleName = signature.getDeclaringType().getSimpleName();
        //获取传入目标方法的参数对象
        Object target = joinPoint.getTarget();
        //获取当前代理类的全限定名
        String targetName = joinPoint.getTarget().getClass().getName();

        //获取传入目标方法的参数对象
        Object[] args = joinPoint.getArgs();
        Class<?> aClass = null;

        try {
            aClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("署名信息的对象:"+signature);
        System.out.println("目标方法所属类的简单类名:"+simpleName);
        System.out.println("获取传入目标方法的参数对象:"+target);

        System.out.println("代理类："+targetName);
        System.out.println("方法名:"+methodName);
        System.out.println("操作类型:"+operationType);
        System.out.println("操作解释:"+operateExplain);

        System.out.println("---------------------------开始保存日志----------------------------------");
    }


}
