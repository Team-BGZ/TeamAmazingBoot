package com.example.demo.service;

import com.example.demo.pojo.Organization;
import com.example.demo.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2020/4/9.
 */
public interface OrganizationService {
    List<Organization> selectOrganization(Organization organization);
}
