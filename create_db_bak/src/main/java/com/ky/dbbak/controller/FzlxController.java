package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSON;
import com.ky.dbbak.entity.FZLXEntity;
import com.ky.dbbak.service.*;
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
    @Autowired
    FzxxService fzxxService;

    @RequestMapping(value = "fzlx")
    @ResponseBody
    public String fzlx(String KJDZZBBH) throws Exception {
        boolean falg = fzlxService.FzlxB(KJDZZBBH);
        if (falg==true){
            return "success";
        }else {
            return "false";
        }
    }

    @RequestMapping(value = "fzlxGB")
    @ResponseBody
    public String fzlx_G(String KJDZZBBH) throws Exception {
        boolean falg = fzlxService.Fzlx( KJDZZBBH);
        if (falg == true){
            return "success";
        }else {
            return "false";
        }
    }


    @RequestMapping(value = "fzxxtwo")
    @ResponseBody
    public String Fzxxtwo(String KJDZZBBH) throws Exception {
        boolean falg = fzxxService.fzxxB(KJDZZBBH);
        if (falg == true){
            return "success";
        }else {
            return "false";
        }
    }

    @RequestMapping(value = "fzxxtwoGB")
    @ResponseBody
    public String Fzxxtwo_G(String KJDZZBBH) throws Exception {
        boolean falg = fzxxService.fzxxG(KJDZZBBH);
        if (falg == true){
            return "success";
        }else {
            return "false";
        }
    }




    /*会计科目表 */
    @RequestMapping(value = "kjkm")
    @ResponseBody
    public String kjkm(String KJDZZBBH) throws Exception {
        boolean falg = kjkmService.kjkmB(KJDZZBBH);
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
        boolean falg = kjkmService.kjkmG(KJDZZBBH);
        if (falg == true){
            return "success";
        }else {
            return "false";
        }
    }



}
