package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSON;
import com.ky.dbbak.entity.GLPznrEntity;
import com.ky.dbbak.entity.JZPZEntity;
import com.ky.dbbak.service.DbyService;
import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @class: create_db_bak
 * @classDesc: 功能描述（）
 * @author: yaoWieJie
 * @createTime: 2020-03-22 18:29
 * @version: v1.0
 */
@Controller
@RequestMapping(value = "/ky-datagather/dby/")
public class DbyController {

    private static final Logger logger = LoggerFactory.getLogger(DbyController.class);

    @Autowired
    DbyService dbyService;

    //KJQJDY   会计期间定义表
    @RequestMapping(value = "kjqjdy")
    @ResponseBody
    public String kjqjdy(String KJDZZBBH) {
        Boolean kjqjdyBBoolean = dbyService.kjqjdyService(KJDZZBBH, 1);
        if (kjqjdyBBoolean.equals(false)) {
            return "false";
        }
        return "success";
    }

    //KMNCS   科目年初数表
    @RequestMapping(value = "kmncs")
    @ResponseBody
    public String kmncs(String KJDZZBBH) {
        Boolean kmncsBBoolean = dbyService.kmncsService(KJDZZBBH, 1);
        if (kmncsBBoolean.equals(false)) {
            return "false";
        }
        return "success";
    }

    //KMYE   科目余额表
    @RequestMapping(value = "kmye")
    @ResponseBody
    public String kmye(String KJDZZBBH) {
        Boolean kmyeBBoolean = dbyService.kmyeService(KJDZZBBH, 1);
        if (kmyeBBoolean.equals(false)) {
            return "false";
        }
        return "success";
    }

    //JZPZ   记账凭证
    @RequestMapping(value = "jzpz")
    @ResponseBody
    public String insert(String KJDZZBBH) throws Exception {
        int jzpzBBoolean = dbyService.jzpzService(KJDZZBBH,1);
        if (jzpzBBoolean == 0) {
            return "false";
        } else if (jzpzBBoolean == 2) {
            return "error";
        }
        return "success";
    }

    //KJQJDY   会计期间定义表
    @RequestMapping(value = "kjqjdyGB")
    @ResponseBody
    public String kjqjdyController(String KJDZZBBH) {
        Boolean kjqjdyGBoolean = dbyService.kjqjdyService(KJDZZBBH, 2);
        if (kjqjdyGBoolean.equals(false)) {
            return "false";
        }
        return "success";
    }

    //KMNCS   科目年初数表
    @RequestMapping(value = "kmncsGB")
    @ResponseBody
    public String kmncsController(String KJDZZBBH) {
        Boolean kmncsGBoolean = dbyService.kmncsService(KJDZZBBH, 2);
        if (kmncsGBoolean.equals(false)) {
            return "false";
        }
        return "success";
    }

    //KMYE   科目余额表
    @RequestMapping(value = "kmyeGB")
    @ResponseBody
    public String kmyeController(String KJDZZBBH) {
        Boolean kmyeGBoolean = dbyService.kmyeService(KJDZZBBH, 2);
        if (kmyeGBoolean.equals(false)) {
            return "false";
        }
        return "success";
    }

    //JZPZ   记账凭证
    @RequestMapping(value = "jzpzGB")
    @ResponseBody
    public String jzpzController(String KJDZZBBH) throws Exception {
        int jzpzGBoolean = dbyService.jzpzService(KJDZZBBH,2);
        if (jzpzGBoolean == 0) {
            return "false";
        } else if (jzpzGBoolean == 2) {
            return "error";
        }
        return "success";
    }
}