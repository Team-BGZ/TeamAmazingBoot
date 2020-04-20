package com.example.demo.dao;

import com.example.demo.pojo.Organization;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2020/4/9.
 */
@Mapper
public interface OrganizationMapper {
    List<Organization> selectOrganization(Organization organization);
}
