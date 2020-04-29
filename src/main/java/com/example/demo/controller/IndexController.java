package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.Organization;
import com.example.demo.pojo.User;
import com.example.demo.service.OrganizationService;
import com.example.demo.service.UserService;
import com.example.demo.util.ListToTreeUtil;
import com.example.demo.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/4/9.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ListToTreeUtil listToTreeUtil;



    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }


    @GetMapping(value = "/doAjax")
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


    @GetMapping(value = "/doListToTree")
    @ResponseBody
    public JSONObject doListToTree(){
        JSONObject res= new JSONObject();
        List<Organization> utilList=organizationService.selectOrganization(null);
        List returnList=listToTreeUtil.listToTree(utilList);
        res.put("message","success");
        res.put("list",returnList);
        return res;
    }


    @PostMapping(value = "/doAgainSubmit")
    @ResponseBody
    public JSONObject doAgainSubmit(String testParm){
        JSONObject res= new JSONObject();
        res.put("message",testParm);
        return res;
    }


    @GetMapping(value = "/doSetLock")
    @ResponseBody
    public JSONObject doSetLock(String testParm){
        JSONObject res= new JSONObject();
        res.put("message",redisUtil.lock(testParm));
        return res;
    }

    @GetMapping(value = "/doDelLock")
    @ResponseBody
    public JSONObject doDelLock(String testParm){
        JSONObject res= new JSONObject();
        res.put("message",redisUtil.del(testParm));
        return res;
    }


}

