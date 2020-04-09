package com.ky.dbbak.controller;

import com.ky.dbbak.service.FzxlbService;
import com.ky.dbbak.service.FzyeService;
import com.ky.dbbak.service.KjkmService;
import com.ky.dbbak.service.KmyeService;
import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.DzzbxxMapper;
import com.ky.dbbak.targetmapper.FzyeMapper;
import com.ky.dbbak.targetmapper.TragetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ky-datagather/Fzye/")
public class FzyeController {

    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;

    @Autowired
    YebMapper yebMapper;

    @Autowired
    FzyeMapper fzyeMapper;

    @Autowired
    ZtcsMapper ztcsMapper;

    @Autowired
    PznrMapper pznrMapper;

    @Autowired
    KmxxMapper kmxxMapper;

    @Autowired
    DzzbxxMapper dzzbxxMapper;

    @Autowired
    KmxzlxMapper kmxzlxMapper;

    @Autowired
    FzxlbService fzxlbService;

    @Autowired
    KmyeService kmyeService;

    @Autowired
    FzyeService fzyeService;

    @Autowired
    KjkmService kjkmService;

    //KMYE   科目余额表
    @RequestMapping(value = "fzye")
    @ResponseBody
    public String fzye(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> GL_YebList = kmxzlxMapper._queryGL_Yeb();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        Map<String, Object> stringObjectMap = fzxlbService._queryGL_Fzxlb1(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
        List<Map<String, Object>> resultList = fzyeService.fzyeB(GL_YebList,dzzbxxList,stringObjectMap,pageDataGL_Ztcs);
        List<Map<String, Object>> resultListNew = kmyeService.kjkmResult(resultList, pageDataGL_Ztcs.get(0));
        boolean falg = fzyeService.fzyeBb(resultListNew);
        if (falg == true){
            return "success";
        }else {
            return "false";
        }
    }





    @RequestMapping(value = "fzyeGB")
    @ResponseBody
    public String fzye_G(String KJDZZBBH) {
        List<Map<String, Object>> GL_YebList = fzyeService.fzye(KJDZZBBH);
        Map<String, Object> stringObjectMap = kjkmService._queryGL_Fzxlb1(KJDZZBBH);
        List<Map<String, Object>> pageDataGL_Ztcs = fzyeService.ZtcsStr(KJDZZBBH);
        List<Map<String, Object>> resultList = fzyeService.fhyexx(KJDZZBBH, GL_YebList, stringObjectMap, pageDataGL_Ztcs);
        List<Map<String, Object>> resultListNew = fzyeService.kjkmResult(resultList, pageDataGL_Ztcs, KJDZZBBH);
        boolean falg = fzyeService.fzyeBb(resultListNew);
        if (falg == true) {
            return "success";
        } else {
            return "false";
        }
    }
}
