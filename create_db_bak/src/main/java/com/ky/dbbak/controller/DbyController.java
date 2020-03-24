package com.ky.dbbak.controller;

import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.*;
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
@RequestMapping(value = "/ky-datagather/dby/")
public class DbyController {

    private static final Logger logger = LoggerFactory.getLogger(DbyController.class);

    @Autowired
    DzzbxxMapper dzzbxxMapper;
    @Autowired
    PubkjqjMapper pubkjqjMapper;
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
    KMYEMapper kmyeMapper;
    @Autowired
    PzmlMapper pzmlMapper;
    @Autowired
    PzlxMapper pzlxMapper;
    @Autowired
    KjqjdyMapper kjqjdyMapper;
    @Autowired
    KMNCSMapper kmncsMapper;
    @Autowired
    JZPZMapper jzpzMapper;

    //KJQJDY   会计期间定义表
    @RequestMapping(value = "kjqjdy")
    @ResponseBody
    public String kjqjdy(String XZQHDM) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("XZQHDM", XZQHDM);
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
            //kjqjdyMapper._addKjqjdy(dataPull);
            resultList.add(dataPull);
        }
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
        return "kjqjdy-会计期间定义表生成完成";
    }

    //KMNCS   科目年初数表
    @RequestMapping(value = "kmncs")
    @ResponseBody
    public String kmncs(String XZQHDM) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("XZQHDM", XZQHDM);
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
                    default:
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
                    String kmdm3 = kmdm.substring(0, kmdm.length() - 2);
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
            resultList.add(dataPull);
        }
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
        return "kmncs-科目年初数表生成完成";
    }

    //KMYE   科目余额表
    @RequestMapping(value = "kmye")
    @ResponseBody
    public String kmye(String XZQHDM) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = yebMapper._queryGL_Yeb(pageData);
        pageData.put("XZQHDM", XZQHDM);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        for (Map<String, Object> pd : GL_YebList) {
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            Double qcjfye = (Double) pd.get("ncj");
            //BigDecimal qcjfye = new BigDecimal((Double) pd.get("ncj"));
            Double qcdfye = (Double) pd.get("ncd");
            //BigDecimal qcdfye = new BigDecimal((Double) pd.get("ncd"));
            Double jfljfse = 0.00;
            //BigDecimal jfljfse = new BigDecimal();
            Double dfljfse = 0.00;
           //BigDecimal dfljfse = new BigDecimal();
            for (int i = 1; i < 13; i++) {
                if (!pd.get("yj" + i).toString().equals("0") && !StringUtils.isEmpty(pd.get("yj" + i).toString().trim()) &&
                        !pd.get("yd" + i).toString().equals("0") && !StringUtils.isEmpty(pd.get("yd" + i).toString().trim())
                ) {
                    //8.会计月份
                    dataPull.put("KJYF", i);
                    //9.会计体系  01会计，02预算
                    List<Map<String, Object>> pageDataPznrList = pznrMapper._queryPznr(pd);
                    if(pageDataPznrList.size()>0){
                        dataPull.put("KJTX", pageDataPznrList.get(0).get("KJTXDM"));
                    }
                    //10.会计科目编码
                    dataPull.put("KJKMBM", pd.get("kmdm"));
                    //11.会计科目名称
                    List<Map<String, Object>> pageDataKmxxList = kmxxMapper._queryGL_KMXX(pd);
                    dataPull.put("KJKMMC", pageDataKmxxList.get(0).get("kmmc"));
                    //12.科目全称
                    String kmdm = pd.get("kmdm").toString();
                    if (!StringUtils.isEmpty(kmdm)) {
                        if (kmdm.length() == 4) {
                            dataPull.put("KMQC", pageDataKmxxList.get(0).get("kmmc"));
                            //38.是否最低级科目
                            dataPull.put("SFZDJKM", 0);
                            //39.上级科目编码
                            dataPull.put("SJKMBM", "" );
                        } else {
                            //StringBuilder builderKmqc = new StringBuilder();
                            //String kmdm2 = kmdm.substring(0, 4);
                            String kmqc = "";
                            //builderKmqc.append(pageDataKmxxList.get(0).get(kmdm));
                            while (kmdm.length() > 4) {
                                kmqc+="/" + pageDataKmxxList.get(0).get(kmdm);
                                kmdm = kmdm.substring(0,kmdm.length()-2);
                            }
                            dataPull.put("KMQC", kmqc);
                            dataPull.put("SFZDJKM", 1);
                            //39.上级科目编码
                            String kmdm3 = kmdm.substring(0, kmdm.length() - 2);
                            dataPull.put("SJKMBM", kmdm3);
                        }
                        //37.会计科目级别
                        Integer kjkmjb = Integer.valueOf(((kmdm.length() - 4) / 2) + 1);
                        dataPull.put("KJKMJB", kjkmjb);
                    } else {
                        dataPull.put("KMQC", null);
                    }
                    //13.年初借方余额
                    Double ncj = (Double) pd.get("ncj");
                    //BigDecimal ncj = new BigDecimal((Double) pd.get("ncj"));
                    Double ncd = (Double) pd.get("ncd");
                    //BigDecimal ncd = new BigDecimal((Double) pd.get("ncd"));
                    dataPull.put("NCJFYE", new BigDecimal(ncj));
                    //14.年初贷方余额
                    dataPull.put("NCJFYE", new BigDecimal(ncd));
                    //15.年初余额方向  ncj-ncd  -1：贷，0：平，1：借。
                    if (ncj>ncd) {
                        dataPull.put("NCYEFX", 1);
                    } else if (ncj<ncd) {
                        dataPull.put("NCYEFX", -1);
                    } else {
                        dataPull.put("NCYEFX", 0);
                    }
                    //16.期初借方余额//GL_yeb表1月为ncj  2月为yj1以此类推
                    dataPull.put("QCJFYE", new BigDecimal(qcjfye));
                    //qcjfye.add((BigDecimal) pd.get("yj" + i));
                    qcjfye+=(Double) pd.get("yj"+i);
                    //17.期初贷方余额
                    dataPull.put("QCDFYE", new BigDecimal(qcdfye));
                    qcdfye += (Double) pd.get("yd" + i);
                    //qcdfye.add((BigDecimal) pd.get("yd" + i));
                    //18.期初余额方向  ncj-ncd  -1：贷，0：平，1：借。
                    if (qcjfye>qcdfye) {
                        dataPull.put("QCYEFX", 1);
                    } else if (qcjfye<qcdfye) {
                        dataPull.put("QCYEFX", -1);
                    } else {
                        dataPull.put("QCYEFX", 0);
                    }
                    //19.外币年初借方余额//赋值0
                    dataPull.put("WBNCJFYE", BigDecimal.ZERO);
                    //20.外币年初贷方余额//赋值0
                    dataPull.put("WBNCDFYE", BigDecimal.ZERO);
                    //21.外币期初借方余额//赋值0
                    dataPull.put("WBQCJFYE", BigDecimal.ZERO);
                    //22.外币期初贷方余额//赋值0
                    dataPull.put("WBQCDFYE", BigDecimal.ZERO);
                    //23.借方发生额
                    //int jffse = (int) pd.get("yj" + i);
                    Double jffse = (Double) pd.get("yj"+i);
                    dataPull.put("JFFSE", new BigDecimal(jffse));
                    //24.借方累计发生额
                    jfljfse += jffse;
                    dataPull.put("JFLJFSE", new BigDecimal(jfljfse));
                    //25.外币借方发生额//赋值0
                    dataPull.put("WBJFFSE", BigDecimal.ZERO);
                    //26.外币借方累计发生额//赋值0
                    dataPull.put("WBJFLJFSE", BigDecimal.ZERO);
                    //27.贷方发生额
                    Double dffse = (Double) pd.get("yd" + i);
                    dataPull.put("DFFSE", new BigDecimal(dffse));
                    //28.贷方累计发生额
                    dfljfse += dffse;
                    dataPull.put("DFLJFSE", new BigDecimal(dfljfse));
                    //29.外币贷方发生额//赋值0
                    dataPull.put("WBDFFSE", BigDecimal.ZERO);
                    //30.外币贷方累计发生额//赋值0
                    dataPull.put("WBDFLJFSE", BigDecimal.ZERO);
                    //31.期末借方余额
                    //32.期末贷方余额
                    //33.期末余额方向   -1：贷，0：平，1：借。
                    if (jfljfse > dfljfse) {
                        dataPull.put("QMJFYE", new BigDecimal(jfljfse).subtract(new BigDecimal(dfljfse)));
                        dataPull.put("QMDFYE", BigDecimal.ZERO);
                        dataPull.put("QMYEFX", 1);
                    } else if (jfljfse < dfljfse) {
                        dataPull.put("QMJFYE", BigDecimal.ZERO);
                        dataPull.put("QMDFYE", new BigDecimal(jfljfse).subtract(new BigDecimal(dfljfse)));
                        dataPull.put("QMYEFX", -1);
                    } else {
                        dataPull.put("QMJFYE", BigDecimal.ZERO);
                        dataPull.put("QMDFYE", BigDecimal.ZERO);
                        dataPull.put("QMYEFX", 0);
                    }
                    //34.外币期末借方余额//赋值0
                    dataPull.put("WBQMJFYE", BigDecimal.ZERO);
                    //35.外币期末贷方余额//赋值0
                    dataPull.put("WBQMDFYE", BigDecimal.ZERO);
                    //36.分录数,查找月份，科目代码和辅助明晰一样的有几条
                    int fls = 0;
                    for (int j = 1; j < 31; j++) {
                        if (pd.get("fzdm" + j) != null && !StringUtils.isEmpty(pd.get("fzdm" + j).toString().trim())) {
                            fls += 1;
                        }
                    }
                    dataPull.put("FLS", fls);
                    //40.是否现金或现金等价物  //赋值0
                    dataPull.put("SFXJHXJDJW", 0);
                    //41.币种名称 // 人民币
                    dataPull.put("BZMC", "人民币");
                    //42.币种代码//为空
                    dataPull.put("BZDM", "");
                    //kmyeMapper._add(dataPull);
                    resultList.add(dataPull);
                }
            }
        }

        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            kmyeMapper._add(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        kmyeMapper._add(map);
        return "kmye-科目余额表生成完成";
    }

    //JZPZ   记账凭证
    @RequestMapping(value = "jzpz")
    @ResponseBody
    public String insert(String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> bypznrList = pznrMapper._queryPznr(pageData);
        pageData.put("XZQHDM", XZQHDM);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        for (Map<String, Object> pd : bypznrList) {
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            //8.会计月份，
            String kjqj = pd.get("kjqj").toString();
            if (!StringUtils.isEmpty(kjqj)) {
                Integer mouth = Integer.valueOf(kjqj.substring(kjqj.length() - 2, kjqj.length()));
                dataPull.put("KJYF", mouth);

                //9.记账凭证日期
                List<Map<String, Object>> pageDataPzmlList = pzmlMapper._queryPzml(pd);
                dataPull.put("JZPZRQ", pageDataPzmlList.get(0).get("srrq"));
                //10.记账类型编号
                dataPull.put("JZLXBH", pd.get("pzlxdm"));
                //11.记账类型名称
                List<Map<String, Object>> pageDatePzlxList = pzlxMapper._queryPzlx(pd);
                String pzlxmc = (String) pageDatePzlxList.get(0).get("pzlxmc");
                dataPull.put("JZLXMC", pzlxmc);
                //12.记账凭证种类
                dataPull.put("JZPZZL", pzlxmc);
                //11.记账类型名称
                dataPull.put("JZPZBH", pzlxmc);
                //13.记账凭证编号
                dataPull.put("JZPZBH", pd.get("pzh"));
                //14.记账凭证行号
                dataPull.put("JZPZBH", pd.get("flh"));
                //15.分录序号
                dataPull.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
                        + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
                        + "-" + pd.get("PZLXDM") + "-" + pd.get("pzh") + "-" + pd.get("flh") + "-" + pd.get("KJTXDM"));
                //16.记账凭证摘要
                dataPull.put("JZPZZY", pd.get("zy"));
                //17.会计体系01会计，02预算
                dataPull.put("KJTX", pd.get("KJTXDM"));
                //18.会计科目编码
                dataPull.put("KJKMBM", pd.get("kmdm"));
                //19.会计科目名称
                List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._queryGL_KMXX(pd);
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                //20.科目全称   货币资金/自有资金
                String kmdm = pd.get("kmdm").toString();
                if (!StringUtils.isEmpty(kmdm)) {
                    if (kmdm.length() == 4) {
                        dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc"));
                        //14.是否最低级科目
                        //dataPull.put("SFZDJKM", 0);
                        //15.上级科目编码
                        //dataPull.put("SJKMBM", null);
                    } else {
                        StringBuilder builderKmqc = new StringBuilder();
                        Integer kmdm2 = Integer.valueOf(kmdm.substring(0, 4));
                        builderKmqc.append(pageDataGL_KMXX.get(0).get(kmdm2));
                        while (kmdm2 > 0) {
                            builderKmqc.append("/" + pageDataGL_KMXX.get(0).get(kmdm2));
                            kmdm2 = kmdm2 - 2;
                        }
                        dataPull.put("KMQC", builderKmqc);
                        //dataPull.put("SFZDJKM", 1);
                        //15.上级科目编码
                        //Integer kmdm3 = Integer.valueOf(kmdm.substring(0, kmdm.length() - 2));
                        //dataPull.put("SJKMBM", kmdm3);
                    }
                    //13.会计科目级次-4/2
                    //Integer kjkmjb = Integer.valueOf(((kmdm.length() - 4) / 2) + 1);
                    //dataPull.put("KJKMJC", kjkmjb);
                } else {
                    dataPull.put("KMQC", null);
                }
                //21.借方发生额yj1,yj2,yj3
                List<Map<String, Object>> pageDataYebList = yebMapper._queryGL_Yeb(pd);
                dataPull.put("JFFSE", pageDataYebList.get(0).get("yj" + mouth));
                //22.贷方发生额 yd1,yd2
                dataPull.put("JFFSE", pageDataYebList.get(0).get("yd" + mouth));
                //23.对方科目编码
                if (pd.get("jdbz").equals("借")) {
                    Map<Object, Object> dmap = new HashMap<>();
                    dmap.put("IDPZH", pd.get("IDPZH"));
                    dmap.put("jdbz", "贷");
                    List<Map<String, Object>> pznrList = pznrMapper._queryPznr(dmap);
                    for (Map<String, Object> pz : pznrList) {
                        pageData.put("DFKMBM", pz.get("kmdm"));
                        //24.对方科目名称
                        List<Map<String, Object>> kmxxList = kmxxMapper._queryGL_KMXX(pz);
                        pageData.put("DFKMMC", kmxxList.get(0).get("kmmc"));
                    }
                } else {
                    Map<Object, Object> dmap = new HashMap<>();
                    dmap.put("IDPZH", pd.get("IDPZH"));
                    dmap.put("jdbz", "借");
                    List<Map<String, Object>> pznrList = pznrMapper._queryPznr(dmap);
                    for (Map<String, Object> pz : pznrList) {
                        pageData.put("DFKMBM", pz.get("kmdm"));
                        //24.对方科目名称
                        List<Map<String, Object>> kmxxList = kmxxMapper._queryGL_KMXX(pz);
                        pageData.put("DFKMMC", kmxxList.get(0).get("kmmc"));
                    }

                    //25.币种   人民币
                    dataPull.put("BZ", "人民币");
                    //26借方外币发生额   //为0
                    dataPull.put("JFWBFSE", BigDecimal.ZERO);
                    //27.贷方外币发生额   //为0
                    dataPull.put("DFWBFSE", BigDecimal.ZERO);
                    //28.汇率   //为空
                    dataPull.put("HL", BigDecimal.ZERO);
                    //29.数量   //为0
                    dataPull.put("SL", BigDecimal.ZERO);
                    //30.单价   //为空
                    dataPull.put("DJ", BigDecimal.ZERO);
                    //31.结算方式   //为空
                    dataPull.put("JSFS", null);
                    //32.附件数
                    dataPull.put("FJS", pageDataPzmlList.get(0).get("fjzs"));
                    //33.制单人员
                    dataPull.put("ZDRY", pageDataPzmlList.get(0).get("sr"));
                    //34.复核人员
                    dataPull.put("FHRY", pageDataPzmlList.get(0).get("sh"));
                    //35.记账人员
                    dataPull.put("JZRY", pageDataPzmlList.get(0).get("jzr"));
                    //36.出纳人员
                    dataPull.put("CNRY", pageDataPzmlList.get(0).get("CN"));
                    //37.财务主管
                    dataPull.put("CWZG", pageDataPzmlList.get(0).get("kjzg"));
                    //38.源凭证号
                    if (pageDataPzmlList.get(0).get("pzly").toString().equals("") || StringUtils.isEmpty(pageDataPzmlList.get(0).get("pzly").toString().trim())) {
                        dataPull.put("YPZH", null);
                        //42.是否为预算账
                        dataPull.put("SFWYSZ", null);
                    } else {
                        dataPull.put("YPZH", pageDataPzmlList.get(0).get("pzly"));
                        //42.是否为预算账
                        dataPull.put("SFWYSZ", "1");
                    }
                    //39.记账标志 0=作废；1=未审核；2=已审核；3=已记帐
                    String zt = pageDataPzmlList.get(0).get("zt").toString();
                    switch (zt) {
                        case "1":
                            dataPull.put("JZBZ", null);
                            //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                            dataPull.put("ZFBZ", null);
                            //41.是否结转
                            dataPull.put("SFJZ", "1");
                            break;
                        case "2":
                            dataPull.put("JZBZ", null);
                            //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                            dataPull.put("ZFBZ", null);
                            //41.是否结转
                            dataPull.put("SFJZ", null);
                            break;
                        case "3":
                            dataPull.put("JZBZ", null);
                            //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                            dataPull.put("ZFBZ", null);
                            //41.是否结转
                            dataPull.put("SFJZ", "1");
                            break;
                        default:
                            dataPull.put("JZBZ", null);
                            //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                            dataPull.put("ZFBZ", "1");
                            //41.是否结转
                            dataPull.put("SFJZ", null);
                            break;
                    }
                    //43.支付单据编号   为空
                    dataPull.put("ZFDJBH", null);
                    //44.功能科目代码
                    String fzdm4 = pageDataYebList.get(0).get("fzdm4").toString();
                    Map<Object, Object> dataFzxlbMap = new HashMap<>();
                    if (!fzdm4.equals("") && !StringUtils.isEmpty(fzdm4)) {
                        dataPull.put("GNKMDM", fzdm4);
                        //45.功能科目名称
                        dataFzxlbMap.put("lbdm", fzdm4);
                        dataPull.put("GNKMMC", glFzxlbMapper._queryGL_Fzxlb(dataFzxlbMap));
                    } else {
                        dataPull.put("GNKMDM", null);
                        dataPull.put("GNKMMC", null);
                    }
                    //46.经济科目代码
                    String fzdm5 = pageDataYebList.get(0).get("fzdm5").toString();
                    Map<Object, Object> dataFzxlbMap5 = new HashMap<>();
                    if (!fzdm4.equals("") && !StringUtils.isEmpty(fzdm5)) {
                        dataPull.put("JJKMDM", fzdm5);
                        //45.功能科目名称
                        dataFzxlbMap5.put("lbdm", fzdm5);
                        //47.经济科目名称
                        dataPull.put("JJKMMC", glFzxlbMapper._queryGL_Fzxlb(dataFzxlbMap5));
                    } else {
                        dataPull.put("JJKMDM", null);
                        dataPull.put("JJKMMC", null);
                    }
                    //48.资金性质代码   //为空
                    dataPull.put("ZJXZDM", null);
                    //49.资金性质名称   //为空
                    dataPull.put("ZJXZMC", null);
                    //50.指标来源代码   //为空
                    dataPull.put("ZBLYDM", null);
                    //51.指标来源名称   //为空
                    dataPull.put("ZBLYMC", null);
                    //52.支出类型代码   //为空
                    dataPull.put("ZCLXDM", null);
                    //53.支出类型名称   //为空
                    dataPull.put("ZCLXMC", null);
                    //54.预算管理类型代码   //为空
                    dataPull.put("YSGLLXDM", null);
                    //55.预算管理类型名称   //为空
                    dataPull.put("YSGLLXMC", null);
                    //56.支付方式代码   //为空
                    dataPull.put("ZFFSDM", null);
                    //57.支付方式名称   //为空
                    dataPull.put("ZFFSMC", null);
                    //58.预算项目代码   //为空
                    dataPull.put("YSXMDM", null);
                    //59.预算项目名称   //为空
                    dataPull.put("YSXMMC", null);
                    //60.项目分类代码   //为空
                    dataPull.put("XMFLDM", null);
                    //61.项目分类名称   //为空
                    dataPull.put("XMFLMC", null);
                    //62.指标文号名称   //为空
                    dataPull.put("ZBWHMC", null);
                    //63.结算方式代码   //为空
                    dataPull.put("JSFSDM", null);
                    //64.结算方式名称   //为空
                    dataPull.put("JSFSMC", null);
                    resultList.add(dataPull);
                }
            }
        }

        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            jzpzMapper._add(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        jzpzMapper._add(map);
        return "jzpz-记账凭证表生成完成";
    }
}