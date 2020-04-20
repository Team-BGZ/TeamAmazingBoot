package com.example.demo.pojo;

import java.util.List;

/**
 * Created by Administrator on 2020/4/20.
 */
public class Organization {


    private Integer id;
    private String organizationName;
    private Integer organizationUpId;
    private String organizationAdress;

    private List<Organization> organizationSon;

    public List<Organization> getOrganizationSon() {
        return organizationSon;
    }

    public void setOrganizationSon(List<Organization> organizationSon) {
        this.organizationSon = organizationSon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getOrganizationUpId() {
        return organizationUpId;
    }

    public void setOrganizationUpId(Integer organizationUpId) {
        this.organizationUpId = organizationUpId;
    }

    public String getOrganizationAdress() {
        return organizationAdress;
    }

    public void setOrganizationAdress(String organizationAdress) {
        this.organizationAdress = organizationAdress;
    }
}

