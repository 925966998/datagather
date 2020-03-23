package com.ky.dbbak.controller;

import com.ky.dbbak.mapper.*;
import com.ky.dbbak.targetmapper.DzzbxxMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
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
    DzzbxxMapper dzzbxxMapper;
    @Autowired
    PubkjqjMapper pubkjqjMapper;
    @Autowired
    KjqjdyMapper kjqjdyMapper;
    @Autowired
    YebMapper yebMapper;
    @Autowired
    PznrMapper pznrMapper;
    @Autowired
    KmxxMapper kmxxMapper;
    @Autowired
    ZtcsMapper ztcsMapper;

    //KJQJDY   会计期间定义表
    @RequestMapping(value = "kjqjdy")
    @ResponseBody
    public String kjqjdy() {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        pageData.put("kjnd", dzzbxxList.get(0).get("KJND"));
        List<Map<String, Object>> pubKjqjList = pubkjqjMapper._queryPubKjqj(pageData);
        for (Map<String, Object> pd : pubKjqjList) {
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            //从电子信息账簿表查询信息
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            //会计月份
            dataPull.put("KJYF", pd.get("kjqjxh"));
            dataPull.put("KSRQ", pd.get("qsrq"));
            dataPull.put("JZRQ", pd.get("jsrq"));
            kjqjdyMapper._addKjqjdy(dataPull);
        }
        return "kjqjdy-会计期间定义表生成完成";
    }

    //KMNCS   科目年初数表
    @RequestMapping("/kmncs")
    @ResponseBody
    public String kmncs(Integer bid) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        pageData.put("kjnd", dzzbxxList.get(0).get("KJND"));
        List<Map<String, Object>> GL_PznrList = pznrMapper._queryPznr(pageData);

        for (Map<String, Object> pd : GL_PznrList) {
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            //从电子信息账簿表查询信息
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            //8.会计月份
            String kjqj = pd.get("kjqj").toString();
            if (!StringUtils.isEmpty(kjqj)) {
                Integer mouth = Integer.valueOf(kjqj.substring(kjqj.length() - 2, kjqj.length()));
                dataPull.put("KJYF", mouth);
            }
            //9.会计体系  01会计，02预算
            dataPull.put("KJTX", pd.get("KJTXDM"));
            //10.会计科目编码
            dataPull.put("KJKMBM", pd.get("kmdm"));
            //11.会计科目名称
            List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._queryGL_KMXX(pd);
            List<Map<String, Object>> pageDataGL_Yeb = yebMapper._queryYebKjnd(pd);
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                //18.余额方向
                String yefx = pageDataGL_KMXX.get(0).get("yefx").toString();
                switch (yefx) {
                    case "j":
                        dataPull.put("YEFX", 1);
                        //19.本币期初余额
                        dataPull.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pageDataGL_Yeb.get(0).get("ncj").toString()) - Double.valueOf(pageDataGL_Yeb.get(0).get("ncd").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                        break;
                    case "D":
                        dataPull.put("YEFX", -1);
                        //19.本币期初余额
                        dataPull.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pageDataGL_Yeb.get(0).get("ncd").toString()) - Double.valueOf(pageDataGL_Yeb.get(0).get("ncj").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                        break;
                    case "":
                        dataPull.put("YEFX", 0);
                        //19.本币期初余额
                        dataPull.put("BBQCYE", BigDecimal.ZERO);
                        break;
                }
            }
            //12.科目全称
            String kmdm = pd.get("kmdm").toString();
            if (!StringUtils.isEmpty(kmdm)) {
                if (kmdm.length() == 4) {
                    dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc"));
                    //14.是否最低级科目
                    dataPull.put("SFZDJKM", 0);
                    //15.上级科目编码
                    dataPull.put("SJKMBM", null);
                } else {
                    StringBuilder builderKmqc = new StringBuilder();
                    Integer kmdm2 = Integer.valueOf(kmdm.substring(0, 4));
                    builderKmqc.append(pageDataGL_KMXX.get(0).get(kmdm2));
                    while (kmdm2 > 0) {
                        builderKmqc.append("/" + pageDataGL_KMXX.get(0).get(kmdm2));
                        kmdm2 = kmdm2 - 2;
                    }
                    dataPull.put("KMQC", builderKmqc);
                    dataPull.put("SFZDJKM", 1);
                    //15.上级科目编码
                    Integer kmdm3 = Integer.valueOf(kmdm.substring(0, kmdm.length() - 2));
                    dataPull.put("SJKMBM", kmdm3);
                }
                //13.会计科目级次-4/2
                Integer kjkmjb = Integer.valueOf(((kmdm.length() - 4) / 2) + 1);
                dataPull.put("KJKMJC", kjkmjb);
            } else {
                dataPull.put("KMQC", null);
            }
            //16.是否现金或现金等价物  赋值0
            dataPull.put("SFXJHXJDJW", 0);
            //17.币种名称//手动输入 人民币
            dataPull.put("BZMC", "人民币");


            //20.期初数量  赋值0
            dataPull.put("QCSL", BigDecimal.ZERO);
            //21.外币期初余额  赋值0
            dataPull.put("WBQCYE", BigDecimal.ZERO);
        }
        return "kmncs-科目年初数表生成完成";
    }

    //KMYE   科目余额表
    @RequestMapping("/kmye")
    @ResponseBody
    public String kmye() {
        return "kmye-科目余额表生成完成";
    }
}