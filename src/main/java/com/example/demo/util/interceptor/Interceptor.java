package com.example.demo.util.interceptor;

import com.example.demo.service.UserService;
import com.example.demo.util.token.RedisTokenHelp;
import com.example.demo.util.token.TokenHelper;
import com.example.demo.util.token.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2020/4/9.
 */
@Component
public class Interceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenHelper redisTokenHelp;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        System.out.println("----------------------------------你被拦截了----------------------------------");
        String token = httpServletRequest.getHeader("token");
        System.out.println("token是："+token);

        TokenModel model = redisTokenHelp.get(token);
        if(redisTokenHelp.check(model)) {
            return true;
        }else{
            redirect(httpServletRequest,httpServletResponse);
            return false;
        }
    }

    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setHeader("REDIRECT", "REDIRECT");
            response.setHeader("CONTEXTPATH","error201");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.sendRedirect("error201");
        }
    }

}
