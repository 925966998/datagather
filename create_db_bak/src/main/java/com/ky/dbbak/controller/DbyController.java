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
        Boolean kjqjdyBBoolean = dbyService.kjqjdyBService(KJDZZBBH);
        if (kjqjdyBBoolean.equals(false)) {
            return "false";
        }
        /*
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            kjqjdyMapper._addKjqjdy(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        kjqjdyMapper._addKjqjdy(map);
        */
        return "success";
    }

    //KMNCS   科目年初数表
    @RequestMapping(value = "kmncs")
    @ResponseBody
    public String kmncs(String KJDZZBBH) {
        Boolean kmncsBBoolean = dbyService.kmncsBService(KJDZZBBH);
        if (kmncsBBoolean.equals(false)) {
            return "false";
        }

//        if (resultListNew != null && resultListNew.size() > 0) {
//            for (Map map1 : resultListNew
//            ) {
//                kmncsMapper._addKmncs(map1);
//            }
//        }
//
//        if (resultListNew != null && resultListNew.size() > 0) {
//            for (Map map1 : resultListNew
//            ) {
//                if (resultMapHaveListStr.contains(map1.get("KJDZZBBH") + "-" + map1.get("KJYF") + "-" + map1.get("KJKMBM"))) {
//                    resultListNew2Have.add(map1);
//                } else {
//                    resultListNew2.add(map1);
//                }
//            }
//        }
//        List<Map<String, Object>> listUp = new ArrayList<>();
//        if (resultMapHaveListStr != null && resultMapHaveListStr.size() > 0 && resultListNew2 != null && resultListNew2.size() > 0) {
//            for (String str : resultMapHaveListStr) {
//                Map<String, Object> map4 = new HashMap<>();
//                BigDecimal num = BigDecimal.ZERO;
//                for (Map<String, Object> map3 : resultListNew2) {
//                    if (str.equals(map3.get("KJDZZBBH") + "-" + map3.get("KJYF") + "-" + map3.get("KJKMBM"))) {
//                        map4 = new HashMap<>(map3);
//                        num.add(new BigDecimal(map3.get("BBQCYE").toString()));
//                    }
//                }
//                map4.put("BBQCYE", num);
//                listUp.add(map4);
//            }
//        }
        /*
        if (resultListNew2 != null && resultListNew2.size() > 0) {
            for (Map map1 : resultListNew2
            ) {
                Map newMap = new HashMap();
                newMap.put("KJDZZBBH", map1.get("KJDZZBBH"));
                newMap.put("KJYF", map1.get("KJYF"));
                newMap.put("KJKMBM", map1.get("KJKMBM"));
                List<KMNCSEntity> kmyeEntities = kmncsMapper.querySum(newMap);
                if (kmyeEntities == null || kmyeEntities.size() == 0) {
                    kmncsMapper._addKmncs(map1);
                } else {
                    BigDecimal sumBBQCYE = new BigDecimal(map1.get("BBQCYE").toString()).add(new BigDecimal(kmyeEntities.get(0).getBBQCYE()));
                    map1.put("NCJFYE", sumBBQCYE);
                    kmncsMapper._updateKmncs(map1);
                }
            }
        }
        */
        /*
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            kmncsMapper._add(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        kmncsMapper._add(map);
        */
        return "success";
    }

    //KMYE   科目余额表
    @RequestMapping(value = "kmye")
    @ResponseBody
    public String kmye(String KJDZZBBH) {
        Boolean kmyeBBoolean = dbyService.kmyeBService(KJDZZBBH);
        if (kmyeBBoolean.equals(false)) {
            return "false";
        }
        return "success";
    }

    //JZPZ   记账凭证
    @RequestMapping(value = "jzpz")
    @ResponseBody
    public String insert(String KJDZZBBH) throws Exception {
        int jzpzBBoolean = dbyService.jzpzBService(KJDZZBBH);
        if (jzpzBBoolean == 0) {
            return "false";
        } else if (jzpzBBoolean == 2) {
            return "error";
        }

        /*
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 25;
        Integer listnum3 = listNum / 25;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 25, (p * 25 + 25)));
            jzpzMapper._add(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        jzpzMapper._add(map);
        */

        return "success";
    }

    //KJQJDY   会计期间定义表
    @RequestMapping(value = "kjqjdyGB")
    @ResponseBody
    public String kjqjdyController(String KJDZZBBH) {
        Boolean kjqjdyGBoolean = dbyService.kjqjdyGService(KJDZZBBH);
        if (kjqjdyGBoolean.equals(false)) {
            return "false";
        }
        return "success";
    }

    //KMNCS   科目年初数表
    @RequestMapping(value = "kmncsGB")
    @ResponseBody
    public String kmncsController(String KJDZZBBH) {
        Boolean kmncsGBoolean = dbyService.kmncsGService(KJDZZBBH);
        if (kmncsGBoolean.equals(false)) {
            return "false";
        }
        return "success";
    }

    //KMYE   科目余额表
    @RequestMapping(value = "kmyeGB")
    @ResponseBody
    public String kmyeController(String KJDZZBBH) {
        Boolean kmyeGBoolean = dbyService.kmyeGService(KJDZZBBH);
        if (kmyeGBoolean.equals(false)) {
            return "false";
        }
        return "success";
    }

    //JZPZ   记账凭证
    @RequestMapping(value = "jzpzGB")
    @ResponseBody
    public String jzpzController(String KJDZZBBH) throws Exception {
        int jzpzGBoolean = dbyService.jzpzGService(KJDZZBBH);
        if (jzpzGBoolean == 0) {
            return "false";
        } else if (jzpzGBoolean == 2) {
            return "error";
        }
        return "success";
    }
}