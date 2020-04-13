package com.example.demo.service;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2020/4/9.
 */
public interface UserService {
    List<User> selectUser(User user);
}
