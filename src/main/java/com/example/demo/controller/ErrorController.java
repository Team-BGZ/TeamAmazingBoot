package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2020/4/9.
 */
@Controller
public class ErrorController {



    @RequestMapping(value = "/error201")
    public String error(){
        return "error";
    }


}
