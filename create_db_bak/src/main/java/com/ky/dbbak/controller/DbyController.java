package com.ky.dbbak.controller;

import com.alibaba.druid.util.StringUtils;
import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.DzzbxxMapper;
import com.ky.dbbak.targetmapper.KjqjdyMapper;
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
    @Autowired
    GlFzxlbMapper glFzxlbMapper;
    @Autowired
    GlFzxzlMapper glFzxzlMapper;
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
        //8.会计月份，搜索辅助明细多少个，辅助代码多少个？
        //9.会计体系  01会计，02预算
                //10.会计科目编码
                //11.会计科目名称
                //12.科目全称
                //查询GL_ZTCS的kmbmfa，获取级别信息
                //查询长度的科目名称，在拼接
                //13.年初借方余额
                //14.年初贷方余额
                //15.年初余额方向  ncj-ncd  -1：贷，0：平，1：借。
        //16.期初借方余额//GL_yeb表1月为ncj  2月为yj1以此类推
                //17.期初贷方余额
                //18.期初余额方向  ncj-ncd  -1：贷，0：平，1：借。
        //19.外币年初借方余额//赋值0
                //20.外币年初贷方余额//赋值0
                //21.外币期初借方余额//赋值0
                //22.外币期初贷方余额//赋值0
                //23.借方发生额
                //24.借方累计发生额
                //25.外币借方发生额//赋值0
                //26.外币借方累计发生额//赋值0
                //27.贷方发生额
                //28.贷方累计发生额
                //29.外币贷方发生额//赋值0
                //30.外币贷方累计发生额//赋值0
                //31.期末借方余额
                //32.期末贷方余额
                //33.期末余额方向   -1：贷，0：平，1：借。
        //34.外币期末借方余额//赋值0
                //35.外币期末贷方余额//赋值0
                //36.分录数,查找月份，科目代码和辅助明晰一样的有几条
                //37.会计科目级别
                //38.是否最低级科目
                //39.上级科目编码
                //40.是否现金或现金等价物  //赋值0
                //41.币种名称 // 人民币
                //42.币种代码//为空

        return "kmye-科目余额表生成完成";
    }
}