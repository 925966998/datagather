package com.ky.dbbak.entity;


import com.ky.dbbak.mybatis.BaseEntity;

public class SysUserEntity extends BaseEntity {

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
