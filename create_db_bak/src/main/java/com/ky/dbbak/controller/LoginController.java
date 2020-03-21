package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.dbbak.entity.SysUserEntity;
import com.ky.dbbak.mapper.SysUserMapper;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ky-datagather")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;
    @Autowired
    SysUserMapper sysUserMapper;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        logger.info("The LoginController login method params are {},{}", userName, password);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", userName);
        jsonObject.put("password", password);
        RestResult restResult = (RestResult) loginService.login(jsonObject);
        if (restResult.getCode() == 10000) {
            request.getSession().setAttribute("user", restResult.getData());
            restResult.setData(((SysUserEntity) restResult.getData()).getId());
        }
        return restResult;
    }


    @RequestMapping(value = "/loginout", method = RequestMethod.POST)
    public Object loginout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return new RestResult();
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public RestResult reset(HttpServletRequest request) {
        String psw = "123456";
        String userName = request.getParameter("userName");
        String md5psw = DigestUtils.md5DigestAsHex(psw.getBytes());
        SysUserEntity sysUserEntity = sysUserMapper.queryByUserName(userName);
        if (sysUserEntity != null) {
            sysUserEntity.setPassword(md5psw);
            sysUserMapper._updateEntity(sysUserEntity);
            return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, "重置成功默认密码:123456,请登录后立即修改密码");
        } else {
            return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, "用户名不存在");
        }
    }
}
