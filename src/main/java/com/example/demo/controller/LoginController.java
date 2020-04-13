package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.redis.RedisUtil;
import com.example.demo.util.token.RedisTokenHelp;
import com.example.demo.util.token.TokenHelper;
import com.example.demo.util.token.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2020/4/9.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenHelper redisTokenHelp;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/go", method = {RequestMethod.GET})
    public String go(){
        return "login";
    }


    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject login(User user){
        JSONObject res= new JSONObject();
        List<User> list=userService.selectUser(user);
        if(list.size()==1){
            res.put("message","success");
            list=userService.selectUser(null); //这个只是为了测试redis放值取值用的
            TokenModel model=redisTokenHelp.create(list.get(0).getId());
            res.put("TokenModel",model);
            redisUtil.set("list",list);
        }else{
            res.put("message","fail");
        }
        return res;
    }


    @RequestMapping(value = "/loginOut", method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject loginOut(String token){
        JSONObject res= new JSONObject();
        TokenModel model = redisTokenHelp.get(token);
        redisTokenHelp.delete(model.getUserId());
        res.put("message","success");
        return res;
    }




}
