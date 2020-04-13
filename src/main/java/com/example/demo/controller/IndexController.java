package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2020/4/9.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/doAjax")
    @ResponseBody
    public JSONObject doAjax(){
        JSONObject res= new JSONObject();
        if(redisUtil.get("list")==null){
            List<User> list=userService.selectUser(null);
            redisUtil.set("list",list);
            res.put("message","failfailfailfailfailfailfail");
        }else{
            res.put("message","redis使用了哈哈哈哈哈哈哈哈哈哈耶");
            res.put("list",redisUtil.get("list"));
        }
        return res;
    }


}

