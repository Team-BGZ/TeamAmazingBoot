package com.example.demo.util.interceptor;

import com.example.demo.service.UserService;
import com.example.demo.util.IPUtil;
import com.example.demo.util.redis.RedisUtil;
import com.example.demo.util.token.RedisTokenHelp;
import com.example.demo.util.token.TokenHelper;
import com.example.demo.util.token.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2020/4/9.
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenHelper redisTokenHelp;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        System.out.println("----------------------------------你被拦截了----------------------------------");
        String token = httpServletRequest.getHeader("token");
        System.out.println("token是："+token);

        TokenModel model = redisTokenHelp.get(token);
        if(redisTokenHelp.check(model)) {
            String ifSubit= httpServletRequest.getHeader("ifSubit");
            if(!StringUtils.isEmpty(ifSubit)){

                HttpServletRequest request  = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();//获取requ

                String simpleName=((HandlerMethod) o).getBean().getClass().getName();
                String methodName=((HandlerMethod) o).getMethod().getName();

                String ip = IPUtil.getIpAddress(request);
                String ipKey = String.format("%s#%s",simpleName,methodName);
                int hashCode = Math.abs(ipKey.hashCode());
                String key = String.format("%s_%d",ip,hashCode);
                String value = (String) redisUtil.get(key);

                if (!StringUtils.isEmpty(value)){
                    httpServletResponse.getWriter().write("重复提交getout");
                    return false;
                }
                redisUtil.set(key, UUID.randomUUID().toString().replace("-", ""),5);
            }
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
