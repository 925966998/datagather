package com.ky.dbbak.service;

import com.alibaba.fastjson.JSONObject;
import com.ky.dbbak.entity.SysUserEntity;
import com.ky.dbbak.mapper.SysUserMapper;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.utils.DoConnection;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class LoginService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Value("${dbip}")
    private String dbip;
    @Value("${dbport}")
    private String dbport;
    @Value("${dbname}")
    private String dbname;
    @Value("${name}")
    private String name;
    @Value("${dbpass}")
    private String dbpass;

    public Object login(JSONObject parseObject) {
        Connection connection = DoConnection.connection("sqlserver", "单节点", null, dbip, dbport, dbname, name,
                dbpass);
        if (connection == null) {
            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数据源配置信息有误");
        }

        if (parseObject.containsKey("userName") && parseObject.containsKey("password")) {
            SysUserEntity sysUserEntity = sysUserMapper.queryByUserName(parseObject.getString("userName"));
            if (sysUserEntity != null) {
                if (sysUserEntity.getPassword().equals(parseObject.getString("password"))) {
                    return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, sysUserEntity);
                }
            } else {
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "用户名密码错误");
            }
        } else {
            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "用户名密码错误");
        }
        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "用户名密码错误");
    }
}
