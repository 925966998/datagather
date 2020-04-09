package com.ky.redwood.service;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.SysUserEntity;
import com.ky.redwood.mapper.SysUserMapper;
import com.ky.redwood.mybatis.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    SysUserMapper sysUserMapper;

    public Object login(JSONObject parseObject) {

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
