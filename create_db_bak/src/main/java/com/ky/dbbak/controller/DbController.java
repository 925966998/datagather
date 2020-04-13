package com.ky.dbbak.controller;


import com.ky.dbbak.service.*;
import com.ky.dbbak.sourcemapper.KmxzlxMapper;
import com.ky.dbbak.sourcemapper.SourceMapper;
import com.ky.dbbak.sourcemapper.ZtcsMapper;
import com.ky.dbbak.targetmapper.FzyeMapper;
import com.ky.dbbak.targetmapper.TragetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ky-datagather/db/")
public class DbController {


    @Autowired
    TargetService targetService;

    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;
    @Autowired
    KmxzlxMapper kmxzlxMapper;
    @Autowired
    FzxlbService fzxlbService;
    @Autowired
    ZtcsMapper ztcsMapper;

    @Autowired
    FzyeMapper fzyeMapper;

    @Autowired
    KjkmService kjkmService;

    @Autowired
    FzncsService fzncsService;

    @Autowired
    PzfzmxService pzfzmxService;
//    @RequestMapping(value = "ysdw")
//    @ResponseBody
//    public String insert(String XZQHDM) throws Exception {
//        Map<String, Object> pageData = new HashMap<String, Object>();
//        pageData.put("XZQHDM", XZQHDM);
//        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
//        tragetMapper._add(dzzbxxList.get(0));
//        return "success";
//    }

    @RequestMapping(value = "fzncs")
    @ResponseBody
    public String fzncs(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> GL_YebList = sourceMapper._queryGL_Yeb(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> stringObjectMap = fzxlbService._queryGL_Fzxlb1(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
        List<Map<String, Object>> resultList = fzncsService.fzncsb(GL_YebList, dzzbxxList, stringObjectMap, pageDataGL_Ztcs);
//        List<Map<String, Object>> resultListNew = targetService.kjkmResult(resultList, pageDataGL_Ztcs.get(0));
        boolean falg = fzncsService.fzncB(resultList);
        if (falg == true) {
            return "success";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "pzfzmx")
    @ResponseBody
    public String index(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> bypznrList = sourceMapper._queryPznr(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> stringObjectMap = fzxlbService._queryGL_Fzxlb1(pageData);
        boolean falg = pzfzmxService.pzfzmxb(bypznrList, dzzbxxList, stringObjectMap);
        if (falg == true) {
            return "success";
        } else {
            return "false";
        }
    }


    @RequestMapping(value = "fzncsGB")
    @ResponseBody
    public String fzncsGB(String KJDZZBBH) throws Exception {
        List<Map<String, Object>> GL_YebList = fzncsService.fzncs(KJDZZBBH);
        Map<String, Object> stringObjectMap = kjkmService._queryGL_Fzxlb1(KJDZZBBH);
        List<Map<String, Object>> pageDataGL_Ztcs = fzncsService.ZtcsStr(KJDZZBBH);
        List<Map<String, Object>> resultList = fzncsService.kmncsxx(KJDZZBBH, GL_YebList, stringObjectMap, pageDataGL_Ztcs);
        List<Map<String, Object>> resultListNew = fzncsService.FzncsResult(resultList, pageDataGL_Ztcs, KJDZZBBH);
        boolean falg = fzncsService.fzncB(resultListNew);
        if (falg == true) {
            return "success";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "pzfzmxGB")
    @ResponseBody
    public String pzfzmx(String KJDZZBBH) throws Exception {
        List<Map<String, Object>> bypznrList = pzfzmxService.pzfzmx(KJDZZBBH);
        Map<String, Object> stringObjectMap = kjkmService._queryGL_Fzxlb1(KJDZZBBH);
        List<Map<String, Object>> resultList = pzfzmxService.pzfzmxStr(KJDZZBBH, stringObjectMap, bypznrList);
        boolean falg = pzfzmxService.pzfzmxG(resultList);
        if (falg == true) {
            return "success";
        } else {
            return "false";
        }
    }
}