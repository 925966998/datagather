package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSON;
import com.ky.dbbak.entity.FZLXEntity;
import com.ky.dbbak.service.FzlxService;
import com.ky.dbbak.service.FzxlbService;
import com.ky.dbbak.service.FzyeService;
import com.ky.dbbak.service.KjkmService;
import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.krb5.internal.PAEncTSEnc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ky-datagather/fzlx/")
public class FzlxController {

    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;

    @Autowired
    YebMapper yebMapper;

    @Autowired
    FzyeMapper fzyeMapper;

    @Autowired
    FzlxMapper fzlxMapper;

    @Autowired
    FzxxMapper fzxxMapper;

    @Autowired
    KjkmMapper kjkmMapper;

    @Autowired
    KmxxMapper kmxxMapper;

    @Autowired
    KmxzlxMapper kmxzlxMapper;

    @Autowired
    ZtcsMapper ztcsMapper;

    @Autowired
    FzxlbService fzxlbService;

    @Autowired
    FzyeService fzyeService;
    @Autowired
    FzlxService fzlxService;

    @Autowired
    KjkmService kjkmService;

    @RequestMapping(value = "fzlx")
    @ResponseBody
    public String fzlx(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> bypznrList = sourceMapper._queryGL_Yeb(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> dataPull = new HashMap<String, Object>();
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
        dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
        dataPull.put("KJND", datadzzbxx.get("KJND"));
        dataPull.put("DWMC", datadzzbxx.get("DWMC"));
        dataPull.put("DWDM", datadzzbxx.get("DWDM"));
        dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
        dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
        dataPull.put("FZLXMC", " ");
        dataPull.put("FZLXJG", " ");
        dataPull.put("FZLXBM", " ");
        List<String> lbdmList = fzlxService.FzlxB(bypznrList);
        boolean falg = fzlxService.FzlxStrB(lbdmList,dataPull);
        if (falg==true){
            return "success";
        }else {
            return "false";
        }
    }


    /*会计科目表 */
    @RequestMapping(value = "kjkm")
    @ResponseBody
    public String kjkm(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> kjkmList = kmxzlxMapper._queryKjkmxx();
        Map<String, Object> stringObjectMap = fzxlbService._queryGL_Fzxlb1(pageData);
        boolean falg = kjkmService.kjkmB(dzzbxxList,kjkmList,stringObjectMap);
        if (falg == true){
            return "success";
        }else {
            return "false";
        }
    }

    @RequestMapping(value = "fzxxtwo")
    @ResponseBody
    public String Fzxxtwo(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<FZLXEntity> fzlxEntityList = fzlxMapper._queryAll(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> dataPullBase = new HashMap<String, Object>();
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        dataPullBase.put("XZQHDM", datadzzbxx.get("XZQHDM"));
        dataPullBase.put("XZQHMC", datadzzbxx.get("XZQHMC"));
        dataPullBase.put("KJND", datadzzbxx.get("KJND"));
        dataPullBase.put("DWMC", datadzzbxx.get("DWMC"));
        dataPullBase.put("DWDM", datadzzbxx.get("DWDM"));
        dataPullBase.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
        dataPullBase.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
        dataPullBase.put("FZSM", " ");
        dataPullBase.put("SFWYSFZ", BigDecimal.ONE);
        dataPullBase.put("FZLX", " ");
        dataPullBase.put("FZBM", " ");
        dataPullBase.put("FZMC", " ");
        dataPullBase.put("FZQC", " ");
        dataPullBase.put("FZJC", 0);
        dataPullBase.put("SJFZBM", " ");
        boolean falg = fzlxService.fzxxB(fzlxEntityList,dataPullBase);
        if (falg == true){
            return "success";
        }else {
            return "false";
        }
    }


    @RequestMapping(value = "fzlxGB")
    @ResponseBody
    public String fzlx_G(String KJDZZBBH) throws Exception {
        List<String> lbdmList = fzlxService.Fzlx(KJDZZBBH);
        boolean falg = fzlxService.FzlxStr(lbdmList, KJDZZBBH);
        if (falg == true){
            return "success";
        }else {
            return "false";
        }
    }


    /*会计科目表 */
    @RequestMapping(value = "kjkmGB")
    @ResponseBody
    public String kjkm_G(String KJDZZBBH) throws Exception {
        List<Map<String, Object>> kjkmList = kjkmService.Kjkm(KJDZZBBH);
        Map<String, Object> stringObjectMap = kjkmService._queryGL_Fzxlb1(KJDZZBBH);
        boolean falg = kjkmService.Kjkmxx(KJDZZBBH, kjkmList, stringObjectMap);
        if (falg == true){
            return "success";
        }else {
            return "false";
        }
    }

    @RequestMapping(value = "fzxxtwoGB")
    @ResponseBody
    public String Fzxxtwo_G(String KJDZZBBH) throws Exception {
        Map<String, Object> dataPullBase = fzlxService.Fzxx(KJDZZBBH);
        List<Map<String, Object>> pageDatapubbmXX = fzlxService.pubbmxx(KJDZZBBH);
        List<Map<String, Object>> pageDataxmzl = fzlxService.Xmzl(KJDZZBBH);
        List<Map<String, Object>> pageDataPubkszl = fzlxService.Pubkszl(KJDZZBBH);
        boolean falg = fzlxService.FzxxStr(dataPullBase, pageDatapubbmXX, pageDataxmzl, pageDataPubkszl, KJDZZBBH);
        if (falg == true){
            return "success";
        }else {
            return "false";
        }
    }


}
