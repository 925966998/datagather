package com.ky.ykt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.ykt.logUtil.Log;
import com.ky.ykt.mybatis.RestResult;
import com.ky.ykt.service.PersonUploadService;
import com.ky.ykt.service.WeChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping("/ky-ykt/weChat")
public class WeChatController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    PersonUploadService personUploadService;
    @Autowired
    WeChatService weChatService;

    @Log(description = "微信公众号登录操作", module = "微信登录")
    @RequestMapping(value = "/weChatLogin", method = RequestMethod.POST)
    public Object weChatLogin(HttpServletRequest request, HttpSession session) {
        Map map = new HashMap();
        String name = request.getParameter("name");
        String idCardNo = request.getParameter("idCardNo");
        String bankCardNo = request.getParameter("bankCardNo");
        logger.info("The WeChatController weChatLogin method params are {},{}", name, idCardNo, bankCardNo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("idCardNo", idCardNo);
        jsonObject.put("bankCardNo", bankCardNo);
        RestResult restResult = (RestResult) weChatService.wechatLogin(jsonObject);
        if (restResult.getCode() == 10000) {
            session.setAttribute("person", restResult.getData());
            session.setMaxInactiveInterval(60 * 60);
        }
        return restResult;
    }

}
