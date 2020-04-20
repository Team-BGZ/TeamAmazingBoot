package com.example.demo.service.impl;

import com.example.demo.dao.OrganizationMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.Organization;
import com.example.demo.pojo.User;
import com.example.demo.service.OrganizationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2020/4/9.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;


    @Override
    public List<Organization> selectOrganization(Organization Organization) {
        return organizationMapper.selectOrganization(Organization);
    }
}
