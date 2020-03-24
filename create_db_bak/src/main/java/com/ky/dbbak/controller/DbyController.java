package com.ky.dbbak.controller;

import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.DzzbxxMapper;
import com.ky.dbbak.targetmapper.KjqjdyMapper;
import com.ky.dbbak.targetmapper.KmyeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    KmyeMapper kmyeMapper;
    //KJQJDY   会计期间定义表
    @RequestMapping(value = "kjqjdy")
    @ResponseBody
    public String kjqjdy(String XZQHDM) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("XZQHDM",XZQHDM);
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
    public String kmncs(String XZQHDM) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("XZQHDM",XZQHDM);
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
    public String kmye(String XZQHDM) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("XZQHDM",XZQHDM);
        List<Map<String, Object>> GL_YebList = yebMapper._queryGL_Yeb(pageData);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        for (Map<String, Object> pd : GL_YebList){
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            int qcjfye =(int) pd.get("ncj");
            int qcdfye =(int) pd.get("ncd");
            int jfljfse = 0;
            int dfljfse = 0;
            for (int i = 1; i < 13; i++) {
                if (!pd.get("yj" + i).toString().equals("0") && !StringUtils.isEmpty(pd.get("yj" + i).toString().trim()) &&
                        !pd.get("yd" + i).toString().equals("0") && !StringUtils.isEmpty(pd.get("yd" + i).toString().trim())
                ){
                    //8.会计月份
                    dataPull.put("KJYF",i);
                    //9.会计体系  01会计，02预算
                    List<Map<String, Object>> pageDataPznrList = pznrMapper._queryPznr(pd);
                    dataPull.put("KJTX",pageDataPznrList.get(0).get("KJTXDM"));
                    //10.会计科目编码
                    dataPull.put("KJKMBM",pd.get("kmdm"));
                    //11.会计科目名称
                    List<Map<String, Object>> pageDataKmxxList = kmxxMapper._queryGL_KMXX(pd);
                    dataPull.put("KJKMMC",pageDataKmxxList.get(0).get("kmmc"));
                    //12.科目全称
                    String kmdm = pd.get("kmdm").toString();
                    if (!StringUtils.isEmpty(kmdm)) {
                        if (kmdm.length() == 4) {
                            dataPull.put("KMQC", pageDataKmxxList.get(0).get("kmmc"));
                            //38.是否最低级科目
                            dataPull.put("SFZDJKM", 0);
                            //39.上级科目编码
                            dataPull.put("SJKMBM", null);
                        } else {
                            StringBuilder builderKmqc = new StringBuilder();
                            Integer kmdm2 = Integer.valueOf(kmdm.substring(0, 4));
                            builderKmqc.append(pageDataKmxxList.get(0).get(kmdm2));
                            while (kmdm2 > 0) {
                                builderKmqc.append("/" + pageDataKmxxList.get(0).get(kmdm2));
                                kmdm2 = kmdm2 - 2;
                            }
                            dataPull.put("KMQC", builderKmqc);
                            dataPull.put("SFZDJKM", 1);
                            //39.上级科目编码
                            Integer kmdm3 = Integer.valueOf(kmdm.substring(0, kmdm.length() - 2));
                            dataPull.put("SJKMBM", kmdm3);
                        }
                        //37.会计科目级别
                        Integer kjkmjb = Integer.valueOf(((kmdm.length() - 4) / 2) + 1);
                        dataPull.put("KJKMJB", kjkmjb);
                    } else {
                        dataPull.put("KMQC", null);
                    }
                    int ncj = (int) pd.get("ncj");
                    int ncd = (int) pd.get("ncd");
                    //13.年初借方余额
                    dataPull.put("NCJFYE",pd.get("ncj"));
                    //14.年初贷方余额
                    dataPull.put("NCJFYE",pd.get("ncd"));
                    //15.年初余额方向  ncj-ncd  -1：贷，0：平，1：借。
                    if (ncj>ncd){
                        dataPull.put("NCYEFX",1);
                    }else if (ncj<ncd){
                        dataPull.put("NCYEFX",-1);
                    }else{
                        dataPull.put("NCYEFX",0);
                    }
                    //16.期初借方余额//GL_yeb表1月为ncj  2月为yj1以此类推
                        dataPull.put("QCJFYE",qcjfye);
                        qcjfye+=(int)pd.get("yj"+i);
                    //17.期初贷方余额
                    dataPull.put("QCDFYE",qcdfye);
                    qcdfye+=(int)pd.get("yd"+i);
                    //18.期初余额方向  ncj-ncd  -1：贷，0：平，1：借。
                    if (qcjfye>qcdfye){
                        dataPull.put("QCYEFX",1);
                    }else if (qcjfye<qcdfye){
                        dataPull.put("QCYEFX",-1);
                    }else{
                        dataPull.put("QCYEFX",0);
                    }
                    //19.外币年初借方余额//赋值0
                    dataPull.put("WBNCJFYE",BigDecimal.ZERO);
                    //20.外币年初贷方余额//赋值0
                    dataPull.put("WBNCDFYE",BigDecimal.ZERO);
                    //21.外币期初借方余额//赋值0
                    dataPull.put("WBQCJFYE",BigDecimal.ZERO);
                    //22.外币期初贷方余额//赋值0
                    dataPull.put("WBQCDFYE",BigDecimal.ZERO);
                    //23.借方发生额
                    int jffse = (int) pd.get("yj" + i);
                    dataPull.put("JFFSE",jffse);
                    //24.借方累计发生额
                    jfljfse+=jffse;
                    dataPull.put("JFLJFSE",jfljfse);
                    //25.外币借方发生额//赋值0
                    dataPull.put("WBJFFSE",BigDecimal.ZERO);
                    //26.外币借方累计发生额//赋值0
                    dataPull.put("WBJFLJFSE",BigDecimal.ZERO);
                    //27.贷方发生额
                    int dffse = (int) pd.get("yd" + i);
                    dataPull.put("DFFSE",dffse);
                    //28.贷方累计发生额
                    dfljfse+=dffse;
                    dataPull.put("DFLJFSE",dfljfse);
                    //29.外币贷方发生额//赋值0
                    dataPull.put("WBDFFSE",BigDecimal.ZERO);
                    //30.外币贷方累计发生额//赋值0
                    dataPull.put("WBDFLJFSE",BigDecimal.ZERO);
                    //31.期末借方余额
                    //32.期末贷方余额
                    //33.期末余额方向   -1：贷，0：平，1：借。
                    if (jfljfse>dfljfse){
                        dataPull.put("QMJFYE",(jfljfse-dfljfse));
                        dataPull.put("QMDFYE",0);
                        dataPull.put("QMYEFX",1);
                    }else if (jfljfse<dfljfse){
                        dataPull.put("QMJFYE",0);
                        dataPull.put("QMDFYE",(dfljfse-jfljfse));
                        dataPull.put("QMYEFX",-1);
                    }else{
                        dataPull.put("QMJFYE",0);
                        dataPull.put("QMDFYE",0);
                        dataPull.put("QMYEFX",0);
                    }
                    //34.外币期末借方余额//赋值0
                    dataPull.put("WBQMJFYE",BigDecimal.ZERO);
                    //35.外币期末贷方余额//赋值0
                    dataPull.put("WBQMDFYE",BigDecimal.ZERO);
                    //36.分录数,查找月份，科目代码和辅助明晰一样的有几条
                    int fls = 0;
                    for (int j = 1; j < 31; j++) {
                        if (pd.get("fzdm" + j) != null && !StringUtils.isEmpty(pd.get("fzdm" + j).toString().trim())) {
                            fls+=1;
                        }
                    }
                    dataPull.put("FLS",fls);
                    //40.是否现金或现金等价物  //赋值0
                    dataPull.put("SFXJHXJDJW",0);
                    //41.币种名称 // 人民币
                    dataPull.put("BZMC","人民币");
                    //42.币种代码//为空
                    dataPull.put("BZDM",null);
                    kmyeMapper._add(dataPull);
                }
            }
        }
        return "kmye-科目余额表生成完成";
    }

    //JZPZ   记账凭证
    @RequestMapping(value = "jzpz")
    @ResponseBody
    public String insert(String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = yebMapper._queryGL_Yeb(pageData);
        pageData.put("XZQHDM",XZQHDM);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        for (Map<String, Object> pd : GL_YebList){
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);

            //8.会计月份，双循环 搜索辅助明细多少个，辅助代码多少个？
            //9.记账凭证日期
            //10.记账类型编号
            //11.记账类型名称
            //12.记账凭证种类
            //11.记账类型名称+"凭证"
            //13.记账凭证编号
            //14.记账凭证行号
            //15.分录序号
            //按标准生成字段，数字必须为半角字符：
            //会计年度4位+月份2位+凭证类型代码+凭证号+行号+2位会计体系代码。
            //以半角的“-”做为分隔符。例：2019-08-记账-7-952-01，同表9的第15个字段，
            //相互关联
            //16.记账凭证摘要
            //17.会计体系01会计，02预算
            //18.会计科目编码

            //19.会计科目名称

            //20.科目全称   货币资金/自有资金

            //21.借方发生额yj1,yj2,yj3


            //22.贷方发生额 yd1,yd2

            //23.对方科目编码

            //24.对方科目名称

            //25.币种   人民币
            dataPull.put("BZ","人民币");
            //26借方外币发生额   //为0
            dataPull.put("JFWBFSE",BigDecimal.ZERO);
            //27.贷方外币发生额   //为0
            dataPull.put("DFWBFSE",BigDecimal.ZERO);
            //28.汇率   //为空
            dataPull.put("HL",BigDecimal.ZERO);
            //29.数量   //为0
            dataPull.put("SL",BigDecimal.ZERO);
            //30.单价   //为空
            dataPull.put("DJ",BigDecimal.ZERO);
            //31.结算方式   //为空
            dataPull.put("JSFS",null);
            //32.附件数

            //33.制单人员

            //34.复核人员

            //35.记账人员

            //36.出纳人员

            //37.财务主管

            //38.源凭证号

            //39.记账标志 0=作废；1=未审核；2=已审核；3=已记帐

            //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐

            //41.是否结转

            //42.是否为预算账
            //43.支付单据编号   为空
            //44.功能科目代码

            //45.功能科目名称

            //46.经济科目代码

            //47.经济科目名称

            //48.资金性质代码   //为空
            dataPull.put("ZJXZDM",null);
            //49.资金性质名称   //为空
            dataPull.put("ZJXZMC",null);
            //50.指标来源代码   //为空
            dataPull.put("ZBLYDM",null);
            //51.指标来源名称   //为空
            dataPull.put("ZBLYMC",null);
            //52.支出类型代码   //为空
            dataPull.put("ZCLXDM",null);
            //53.支出类型名称   //为空
            dataPull.put("ZCLXMC",null);
            //54.预算管理类型代码   //为空
            dataPull.put("YSGLLXDM",null);
            //55.预算管理类型名称   //为空
            dataPull.put("YSGLLXMC",null);
            //56.支付方式代码   //为空
            dataPull.put("ZFFSDM",null);
            //57.支付方式名称   //为空
            dataPull.put("ZFFSMC",null);
            //58.预算项目代码   //为空
            dataPull.put("YSXMDM",null);
            //59.预算项目名称   //为空
            dataPull.put("YSXMMC",null);
            //60.项目分类代码   //为空
            dataPull.put("XMFLDM",null);
            //61.项目分类名称   //为空
            dataPull.put("XMFLMC",null);
            //62.指标文号名称   //为空
            dataPull.put("ZBWHMC",null);
            //63.结算方式代码   //为空
            dataPull.put("JSFSDM",null);
            //64.结算方式名称   //为空
            dataPull.put("JSFSMC",null);
        }

        return "jzpz-记账凭证表生成完成";
    }
}