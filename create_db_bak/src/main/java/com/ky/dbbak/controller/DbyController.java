package com.ky.dbbak.controller;

import com.ky.dbbak.mapper.DbMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @class: create_db_bak
 * @classDesc: 功能描述（）
 * @author: yaoWieJie
 * @createTime: 2020-03-22 18:29
 * @version: v1.0
 */
@Controller
@RequestMapping(value = "/dby/")
public class DbyController {

    private static final Logger logger = LoggerFactory.getLogger(DbyController.class);

    @Autowired
    DbMapper dbMapper;

    //KJQJDY   会计期间定义表
    @RequestMapping("/kjqjdy")
    @ResponseBody
    public String kjqjdy(){
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        return  "kjqjdy-会计期间定义表生成完成";
    }
    //KMNCS   科目年初数表
    @RequestMapping("/kmncs")
    @ResponseBody
    public String kmncs(){
        return "kmncs-科目年初数表生成完成";
    }
    //KMYE   科目余额表
    @RequestMapping("/kmye")
    @ResponseBody
    public String kmye(){
        return "kmye-科目余额表生成完成";
    }
}