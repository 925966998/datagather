package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ky.dbbak.entity.FZYEEntity;
import com.ky.dbbak.entity.KMYEEntity;
import com.ky.dbbak.entity.FZYEEntity;
import com.ky.dbbak.entity.KMNCSEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ky.dbbak.entity.FZYEEntity;
import com.ky.dbbak.entity.KMYEEntity;
import com.ky.dbbak.service.DbyService;
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
    @Autowired
    SourceMapper sourceMapper;
    @Autowired
    DbyService dbyService;

    //KJQJDY   会计期间定义表
    @RequestMapping(value = "kjqjdy")
    @ResponseBody
    public String kjqjdy(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("KJDZZBBH", KJDZZBBH);
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
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                kjqjdyMapper._add(map1);
            }
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
    //KMNCS   科目年初数表
    @RequestMapping(value = "kmncs")
    @ResponseBody
    public String kmncs(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> glYebList = yebMapper._queryGL_Yeb(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
        for (Map<String, Object> pd : glYebList) {
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
            dataPull.put("BZMC", datadzzbxx.get("BWB"));
            dataPull.put("KJYF", 0);
            dataPull.put("KJKMBM", " ");
            dataPull.put("KJTX", " ");
            dataPull.put("KJKMMC", " ");
            dataPull.put("KMQC", " ");
            dataPull.put("KJKMJC", 0);
            dataPull.put("SFZDJKM", 0);
            dataPull.put("SJKMBM", " ");
            dataPull.put("SFXJHXJDJW", 0);
            dataPull.put("YEFX", 0);
            dataPull.put("BBQCYE", BigDecimal.ZERO);
            dataPull.put("QCSL", BigDecimal.ZERO);
            //dataPull.put("WBQCYE", BigDecimal.ZERO);

            //8.会计月份
            if (new BigDecimal(pd.get("ncd").toString()).compareTo(new BigDecimal("0")) == 0 && new BigDecimal(pd.get("ncj").toString()).compareTo(new BigDecimal("0")) == 0) {
                continue;
            }
            dataPull.put("KJYF", 1);
            dataPull.put("KJKMBM", pd.get("kmdm"));
            List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("D")) {
                    dataPull.put("YEFX", -1);
                    dataPull.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pd.get("ncd").toString()) - Double.valueOf(pd.get("ncj").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("J")) {
                    dataPull.put("YEFX", 1);
                    dataPull.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pd.get("ncj").toString()) - Double.valueOf(pd.get("ncd").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                }

                dataPull.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));

                String kmdm = pd.get("kmdm").toString();
                if (kmdm.length() >= 4) {
                    String substring = kmdm.substring(0, 4);
                    //16.是否现金或现金等价物  赋值0
                    if (substring.equals("1001") || substring.equals("1002") || substring.equals("1011") || substring.equals("1021")) {
                        dataPull.put("SFXJHXJDJW", 1);
                    } else {
                        dataPull.put("SFXJHXJDJW", 0);
                    }

                }
                if (kmdm.length() > 4) {
                    String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
                    String[] lbfjStr = kmbmfa.split("-");
                    int num = 0;
                    String kmqc = "";
                    for (int w = 0; w < lbfjStr.length; w++) {
                        num = num + Integer.valueOf(lbfjStr[w]);
                        if (kmdm.length() == num) {
                            dataPull.put("KJKMJC", w + 1);
                            dataPull.put("SJKMBM", kmdm.substring(0, num - Integer.valueOf(lbfjStr[w])));
                        }
                        if (num <= kmdm.length()) {
                            Map<String, Object> queryPd = new HashMap<String, Object>();
                            queryPd.put("kmdm", kmdm.substring(0, num));
                            List<Map<String, Object>> pageDataGL_KMXXQc = sourceMapper._queryGL_KMXX(queryPd);
                            if (pageDataGL_KMXXQc != null && pageDataGL_KMXXQc.size() > 0) {
                                kmqc += pageDataGL_KMXXQc.get(0).get("kmmc").toString().trim() + "/";
                            }
                        }
                    }
                    kmqc = kmqc.substring(kmqc.lastIndexOf(kmqc), kmqc.length() - 1);
                    kmqc = kmqc.replace("　", "");
                    dataPull.put("KMQC", kmqc.trim());
                } else {
                    dataPull.put("KJKMJC", 1);
                    dataPull.put("SJKMBM", " ");
                    dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc"));
                }

            }


            //20.期初数量  赋值0
            dataPull.put("QCSL", new BigDecimal("0"));
            //21.外币期初余额  赋值0
            dataPull.put("WBQCYE", new BigDecimal("0"));
            resultList.add(dataPull);
        }

        List<Map<String, Object>> resultListNew = dbyService.kjkmResult(resultList, pageDataGL_Ztcs.get(0));

        List<String> resultMapListStr = new ArrayList<String>();
        List<String> resultMapHaveListStr = new ArrayList<String>();
        List<Map<String, Object>> resultListNew2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultListNew2Have = new ArrayList<Map<String, Object>>();
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map<String, Object> map : resultListNew
            ) {
                if (!resultMapListStr.contains(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"))) {
                    resultMapListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"));
                    resultListNew2.add(map);
                } else {
                    resultMapHaveListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"));
                    resultListNew2Have.add(map);
                }

            }
        }

        for (Map map3 : resultListNew2
        ) {
            for (Map map4 : resultListNew2Have
            ) {
                if ((map3.get("KJDZZBBH") + "-" + map3.get("KJYF") + "-" + map3.get("KJKMBM")).equals(map4.get("KJDZZBBH") + "-" + map4.get("KJYF") + "-" + map4.get("KJKMBM"))) {
                    map3.put("map3", new BigDecimal(map3.get("BBQCYE").toString()).add(new BigDecimal(map4.get("BBQCYE").toString())));
                }
            }
        }

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
//
//
//
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
        if (resultListNew2 != null && resultListNew2.size() > 0) {
            for (Map map1 : resultListNew2
            ) {
                kmncsMapper._addKmncs(map1);
            }
        }

//        if (resultListNew != null && resultListNew.size() > 0) {
//            for (Map map1 : resultListNew
//            ) {
//                kmncsMapper._addKmncs(map1);
//            }
//        }
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
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = yebMapper._queryAllYeb(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
        for (Map<String, Object> pd : GL_YebList) {
            Map<String, Object> dataPullBase = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPullBase.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPullBase.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPullBase.put("KJND", datadzzbxx.get("KJND"));
            dataPullBase.put("DWMC", datadzzbxx.get("DWMC"));
            dataPullBase.put("DWDM", datadzzbxx.get("DWDM"));
            dataPullBase.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPullBase.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            dataPullBase.put("KJYF", 0);
            dataPullBase.put("KJTX", " ");
            dataPullBase.put("KJKMBM", " ");
            dataPullBase.put("KJKMMC", " ");
            dataPullBase.put("KMQC", " ");
            dataPullBase.put("NCJFYE", BigDecimal.ZERO);
            dataPullBase.put("NCDFYE", BigDecimal.ZERO);
            dataPullBase.put("NCYEFX", 0);
            dataPullBase.put("QCJFYE", BigDecimal.ZERO);
            dataPullBase.put("QCDFYE", BigDecimal.ZERO);
            dataPullBase.put("QCYEFX", 0);
            dataPullBase.put("QMYEFX", 0);
            dataPullBase.put("FLS", 0);
            dataPullBase.put("KJKMJB", 0);
            dataPullBase.put("SFZDJKM", 0);
            dataPullBase.put("SFXJHXJDJW", 0);
            dataPullBase.put("SJKMBM", " ");
            dataPullBase.put("BZMC", " ");
            dataPullBase.put("BZDM", " ");
            dataPullBase.put("JFFSE", BigDecimal.ZERO);
            dataPullBase.put("JFLJFSE", BigDecimal.ZERO);
            dataPullBase.put("DFFSE", BigDecimal.ZERO);
            dataPullBase.put("DFLJFSE", BigDecimal.ZERO);
            dataPullBase.put("QMJFYE", BigDecimal.ZERO);
            dataPullBase.put("QMDFYE", BigDecimal.ZERO);

            BigDecimal jfljfse = new BigDecimal("0");
            BigDecimal dfljfse = new BigDecimal("0");
            BigDecimal qmjfye = new BigDecimal("0");
            BigDecimal qmdfye = new BigDecimal("0");

            for (int i = 1; i < 13; i++) {
                if (new BigDecimal(pd.get("ncd").toString()).compareTo(new BigDecimal("0")) == 0 && new BigDecimal(pd.get("ncj").toString()).compareTo(new BigDecimal("0")) == 0) {
                    int flag = 1;
                    for (int j = 1; j <= i; j++) {
                        if (new BigDecimal(pd.get("yd" + j).toString()).compareTo(new BigDecimal("0")) != 0 || new BigDecimal(pd.get("yj" + j).toString()).compareTo(new BigDecimal("0")) != 0) {
                            flag = 2;
                        }
                    }
                    if (flag == 1) {
                        continue;
                    }
                }
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                //8.会计月份
                dataPull.put("KJYF", i);
                //11.会计科目名称
                List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                dataPull.put("KJKMBM", pd.get("kmdm"));
                dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
                dataPull.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                    String kmdm = pageDataGL_KMXX.get(0).get("kmdm").toString();
                    Integer legth = pageDataGL_KMXX.get(0).get("kmdm").toString().length();
                    if (legth > 4) {

                        String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
                        String[] lbfjStr = kmbmfa.split("-");
                        int num = 0;
                        List<String> kmdms = new ArrayList<String>();
                        for (int w = 0; w < lbfjStr.length; w++) {
                            num = num + Integer.valueOf(lbfjStr[w]);
                            if (num <= kmdm.length()) {
                                kmdms.add(kmdm.substring(0, num));
                            }
                            if (legth == num) {
                                dataPull.put("KJKMJB", w + 1);
                                dataPull.put("SJKMBM", pd.get("kmdm").toString().substring(0, num - Integer.valueOf(lbfjStr[w])));
                            }
                        }
                        Map<String, Object> queryPd = new HashMap<String, Object>();
                        queryPd.put("kmdms", kmdms);
                        List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                        String kjkmqc = String.join("/", pageDataGL_KMXX1);
                        kjkmqc = kjkmqc.trim();
                        kjkmqc = kjkmqc.replace("　", "");
                        dataPull.put("KMQC", kjkmqc);
                    } else {
                        dataPull.put("KJKMJB", 1);
                        dataPull.put("SJKMBM", " ");
                        dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc"));
                    }
                }


                //13.年初借方余额
                BigDecimal ncj = new BigDecimal(pd.get("ncj").toString());
                if (ncj.compareTo(new BigDecimal("0")) == 0) {
                    dataPull.put("NCJFYE", new BigDecimal("0"));
                } else {
                    dataPull.put("NCJFYE", ncj.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //14.年初贷方余额
                BigDecimal ncd = new BigDecimal(pd.get("ncd").toString());
                if (ncd.compareTo(new BigDecimal("0")) == 0) {
                    dataPull.put("NCDFYE", new BigDecimal("0"));
                } else {
                    dataPull.put("NCDFYE", ncd.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //15.年初余额方向  ncj-ncd  -1：贷，0：平，1：借。
                if (ncj.compareTo(ncd) == 1) {
                    dataPull.put("NCYEFX", 1);
                } else if (ncj.compareTo(ncd) == -1) {
                    dataPull.put("NCYEFX", -1);
                } else {
                    dataPull.put("NCYEFX", 0);
                }
                BigDecimal qcjfye = new BigDecimal(pd.get("ncj").toString());
                BigDecimal qcdfye = new BigDecimal(pd.get("ncd").toString());
                //17.期初借贷方余额
                if (i == 1) {
                    qcjfye = ncj.setScale(2, BigDecimal.ROUND_HALF_UP);
                    qcdfye = ncd.setScale(2, BigDecimal.ROUND_HALF_UP);
                    dataPull.put("QCJFYE", ncj.setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QCDFYE", ncd.setScale(2, BigDecimal.ROUND_HALF_UP));
                } else {
                    qcjfye = qmjfye.setScale(2, BigDecimal.ROUND_HALF_UP);
                    qcdfye = qmdfye.setScale(2, BigDecimal.ROUND_HALF_UP);
                    dataPull.put("QCJFYE", qmjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QCDFYE", qmdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //18.期初余额方向  ncj-ncd  -1：贷，0：平，1：借。
                if (i == 1) {
                    if (ncj.compareTo(ncd) == 1) {
                        dataPull.put("QCYEFX", 1);
                    } else if (ncj.compareTo(ncd) == -1) {
                        dataPull.put("QCYEFX", -1);
                    } else {
                        dataPull.put("QCYEFX", 0);
                    }
                } else {
                    if (qmjfye.compareTo(new BigDecimal("0")) == 1) {
                        if (qmdfye.compareTo(new BigDecimal("0")) == 1) {
                            dataPull.put("QCYEFX", 0);
                        } else {
                            dataPull.put("QCYEFX", -1);
                        }
                    } else {
                        dataPull.put("QCYEFX", 1);
                    }
                }

                //19.外币年初借方余额//赋值0
                dataPull.put("WBNCJFYE", new BigDecimal("0"));
                //20.外币年初贷方余额//赋值0
                dataPull.put("WBNCDFYE", new BigDecimal("0"));
                //21.外币期初借方余额//赋值0
                dataPull.put("WBQCJFYE", new BigDecimal("0"));
                //22.外币期初贷方余额//赋值0
                dataPull.put("WBQCDFYE", new BigDecimal("0"));
                //23.借方发生额
                BigDecimal jffse = new BigDecimal(pd.get("yj" + i).toString());
                dataPull.put("JFFSE", jffse.setScale(2, BigDecimal.ROUND_HALF_UP));
                //24.借方累计发生额
                jfljfse = jfljfse.add(jffse);
                dataPull.put("JFLJFSE", jfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));
                //25.外币借方发生额//赋值0
                dataPull.put("WBJFFSE", new BigDecimal("0"));
                //26.外币借方累计发生额//赋值0
                dataPull.put("WBJFLJFSE", new BigDecimal("0"));
                //27.贷方发生额
                BigDecimal dffse = new BigDecimal(pd.get("yd" + i).toString());
                dataPull.put("DFFSE", dffse.setScale(2, BigDecimal.ROUND_HALF_UP));
                //28.贷方累计发生额
                dfljfse = dfljfse.add(dffse);
                dataPull.put("DFLJFSE", dfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));
                //29.外币贷方发生额//赋值0
                dataPull.put("WBDFFSE", new BigDecimal("0"));
                //30.外币贷方累计发生额//赋值0
                dataPull.put("WBDFLJFSE", new BigDecimal("0"));
                //31.期末借方余额
                //32.期末贷方余额
                //33.期末余额方向   -1：贷，0：平，1：借。

                BigDecimal jj = qcjfye.setScale(2, BigDecimal.ROUND_HALF_UP).add(jffse.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal dd = qcdfye.setScale(2, BigDecimal.ROUND_HALF_UP).add(dffse.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (jj.compareTo(dd) == 1) {
                    qmjfye = jj.setScale(2, BigDecimal.ROUND_HALF_UP).subtract(dd.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    qmdfye = new BigDecimal("0");
                    dataPull.put("QMJFYE", qmjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QMDFYE", new BigDecimal("0"));
                    dataPull.put("QMYEFX", 1);
                } else if (jj.compareTo(dd) == -1) {
                    qmdfye = dd.setScale(2, BigDecimal.ROUND_HALF_UP).subtract(jj.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    qmjfye = new BigDecimal("0");
                    dataPull.put("QMJFYE", new BigDecimal("0"));
                    dataPull.put("QMDFYE", qmdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QMYEFX", -1);
                } else {
                    dataPull.put("QMJFYE", new BigDecimal("0"));
                    dataPull.put("QMDFYE", new BigDecimal("0"));
                    dataPull.put("QMYEFX", 0);
                }
                //34.外币期末借方余额//赋值0
                dataPull.put("WBQMJFYE", new BigDecimal("0"));
                //35.外币期末贷方余额//赋值0
                dataPull.put("WBQMDFYE", new BigDecimal("0"));
                //36.分录数,查找月份，科目代码和辅助明晰一样的有几条
                Map<String, Object> qCountPd = new HashMap<String, Object>();
                qCountPd.put("kmdm", pd.get("kmdm"));
                if (i > 9) {
                    qCountPd.put("kjqj", datadzzbxx.get("KJND").toString() + i);
                } else {
                    qCountPd.put("kjqj", datadzzbxx.get("KJND") + "0" + i);
                }

                long num = pznrMapper._queryByPznrCount(qCountPd);
                dataPull.put("FLS", num);
                //40.是否现金或现金等价物  //赋值0
                String kmdm = pd.get("kmdm").toString();
                if (kmdm.length() >= 4) {
                    String substring = kmdm.substring(0, 4);
                    //16.是否现金或现金等价物  赋值0
                    if (substring.equals("1001") || substring.equals("1002") || substring.equals("1011") || substring.equals("1021")) {
                        dataPull.put("SFXJHXJDJW", 1);
                    } else {
                        dataPull.put("SFXJHXJDJW", 0);
                    }

                }
                //41.币种名称 // 人民币
                dataPull.put("BZMC", datadzzbxx.get("BWB"));
                //42.币种代码//为空
                dataPull.put("BZDM", " ");
                resultList.add(dataPull);
            }
        }

        List<Map<String, Object>> resultListNew = dbyService.kjkmResult(resultList, pageDataGL_Ztcs.get(0));
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map map1 : resultListNew
            ) /*{
                Map newMap = new HashMap();
                newMap.put("KJDZZBBH",map1.get("KJDZZBBH"));
                newMap.put("KJYF",map1.get("KJYF"));
                newMap.put("KJKMBM",map1.get("KJKMBM"));
                List<KMYEEntity>  kmyeEntities = kmyeMapper.querySum(newMap);
                if(kmyeEntities == null || kmyeEntities.size() == 0){
                    kmyeMapper._add(map1);
                }else{
                    BigDecimal sumNCJFYE = new BigDecimal(map1.get("NCJFYE").toString()).add(new BigDecimal(kmyeEntities.get(0).getNCJFYE()));
                    BigDecimal sumNCDFYE = new BigDecimal(map1.get("NCDFYE").toString()).add(new BigDecimal(kmyeEntities.get(0).getNCDFYE()));
                    BigDecimal sumQCJFYE = new BigDecimal(map1.get("QCJFYE").toString()).add(new BigDecimal(kmyeEntities.get(0).getQCJFYE()));
                    BigDecimal sumQCDFYE = new BigDecimal(map1.get("QCDFYE").toString()).add(new BigDecimal(kmyeEntities.get(0).getQCDFYE()));
                    BigDecimal sumJFFSE = new BigDecimal(map1.get("JFFSE").toString()).add(new BigDecimal(kmyeEntities.get(0).getJFFSE()));
                    BigDecimal sumJFLJFSE = new BigDecimal(map1.get("JFLJFSE").toString()).add(new BigDecimal(kmyeEntities.get(0).getJFLJFSE()));
                    BigDecimal sumDFFSE = new BigDecimal(map1.get("DFFSE").toString()).add(new BigDecimal(kmyeEntities.get(0).getDFFSE()));
                    BigDecimal sumDFLJFSE = new BigDecimal(map1.get("DFLJFSE").toString()).add(new BigDecimal(kmyeEntities.get(0).getDFLJFSE()));
                    BigDecimal sumQMJFYE = new BigDecimal(map1.get("QMJFYE").toString()).add(new BigDecimal(kmyeEntities.get(0).getQMJFYE()));
                    BigDecimal sumQMDFYE = new BigDecimal(map1.get("QMDFYE").toString()).add(new BigDecimal(kmyeEntities.get(0).getQMDFYE()));
                    map1.put("NCJFYE",sumNCJFYE);
                    map1.put("NCDFYE",sumNCDFYE);
                    map1.put("QCJFYE",sumQCJFYE);
                    map1.put("QCDFYE",sumQCDFYE);
                    map1.put("JFFSE",sumJFFSE);
                    map1.put("JFLJFSE",sumJFLJFSE);
                    map1.put("DFFSE",sumDFFSE);
                    map1.put("DFLJFSE",sumDFLJFSE);
                    map1.put("QMJFYE",sumQMJFYE);
                    map1.put("QMDFYE",sumQMDFYE);
                    kmyeMapper._updateKmye(map1);
                }
            }*/ {
                JSONObject.parseObject(map1, KMYEEntity.class);

            }
        }
        /*
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 45;
        Integer listnum3 = listNum / 45;
        Map map = new HashMap();
//        for (int p = 0; p < listnum3; p++) {
//            map.put("list", resultList.subList(p * 45, (p * 45 + 45)));
//            kmyeMapper._add(map);
//        }
//        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
//        kmyeMapper._add(map);

            for (Map map1:resultList
                 ) {
                kmyeMapper._add(map1);
            }
            */
        return "success";
    }

    //JZPZ   记账凭证
    @RequestMapping(value = "jzpz")
    @ResponseBody
    public String insert(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> resultAllList = new ArrayList<>();
        List<Map<String, Object>> bypznrList = pznrMapper._queryAll(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
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
                dataPull.put("JZPZRQ", pageDataPzmlList.get(0).get("pzrq"));
                //10.记账类型编号
                dataPull.put("JZLXBH", pd.get("PZLXDM").toString());
                //11.记账类型名称
                List<Map<String, Object>> pageDatePzlxList = pzlxMapper._queryPzlx(pd);
                String pzlxmc = pageDatePzlxList.get(0).get("pzlxmc").toString();
                dataPull.put("JZLXMC", pzlxmc);
                //12.记账凭证种类
                if (pd.get("KJTXDM") != null && pd.get("KJTXDM").toString().equals("01")) {
                    dataPull.put("JZPZZL", "财记");
                } else if (pd.get("KJTXDM") != null && pd.get("KJTXDM").toString().equals("02")) {
                    dataPull.put("JZPZZL", "预记");
                }

//                dataPull.put("JZPZZL", pzlxmc);
                //11.记账类型名称
                dataPull.put("JZPZBH", pzlxmc);
                //13.记账凭证编号
                dataPull.put("JZPZBH", pd.get("pzh"));
                //14.记账凭证行号
//                dataPull.put("JZPZHH", pd.get("flh"));
                dataPull.put("JZPZHH", (dataPull.get("KJYF").toString() + pd.get("pzh").toString() + pd.get("flh").toString()));

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
                List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._querykmxx(pd);
                String kmmc = pageDataGL_KMXX.get(0).get("kmmc").toString();
                //20.科目全称   货币资金/自有资金
                String kmdm = pd.get("kmdm").toString();
                String kjkmqc = "";
                if (!StringUtils.isEmpty(kmdm) && kmdm != null) {
                    if (kmdm.length() == 4) {
                        dataPull.put("KJKMMC", kmmc);
                        dataPull.put("KMQC", kmmc);
                    } else {
                        dataPull.put("KJKMMC", kmmc);
                        String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
                        String[] lbfjStr = kmbmfa.split("-");
                        //String result = pd.get("kmdm").toString();
                        int num = 0;
                        List kmdms = new ArrayList();
                        for (int w = 0; w < lbfjStr.length; w++) {
                            num = num + Integer.valueOf(lbfjStr[w]);
                            if (num <= kmdm.length()) {
                                kmdms.add(kmdm.substring(0, num));
                            }
                        }
                        Map<String, Object> queryPd = new HashMap<String, Object>();
                        queryPd.put("kmdms", kmdms);
                        List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                        kjkmqc = String.join("/", pageDataGL_KMXX1);
                        dataPull.put("KMQC", kjkmqc);
                    }
                } else {
                    dataPull.put("KMQC", "");
                }
                //21.借方发生额yj1,yj2,yj3
                //List<Map<String, Object>> pageDataYebList = yebMapper._queryGL_Yeb(pd);
                //dataPull.put("JFFSE", new BigDecimal(pageDataYebList.get(0).get("yj" + mouth).toString()));
                //22.贷方发生额 yd1,yd2
                //dataPull.put("DFFSE", new BigDecimal(pageDataYebList.get(0).get("yd" + mouth).toString()));
                //23.对方科目编码
                String dfkmmc = "";
                String dfkmbm = "";
                if (pd.get("jdbz").equals("借")) {
                    dataPull.put("JFFSE", new BigDecimal(pd.get("je").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("DFFSE", new BigDecimal("0"));
                    Map<Object, Object> dmap = new HashMap<>();
                    dmap.put("IDPZH", pd.get("IDPZH"));
                    dmap.put("jdbz", "贷");
                    dmap.put("KJTXDM", pd.get("KJTXDM"));
                    List<Map<String, Object>> pznrList = pznrMapper._queryByPznr(dmap);
                    if (pznrList.size() > 0 && pznrList != null) {
                        //循环list,拼接名字,编码
                        //pageData.put("DFKMBM", pznrList.get(0).get("kmdm"));
                        dataPull = getDfkmbmAndDfkmmc(pznrList, dfkmbm, dfkmmc, dataPull);
                    } else {
                        List<Map<String, Object>> pznrSmallJeList = pznrMapper._querySmallJe(dmap);
                        dataPull = getDfkmbmAndDfkmmc(pznrSmallJeList, dfkmbm, dfkmbm, dataPull);
                    }
                } else {
                    dataPull.put("JFFSE", new BigDecimal("0"));
                    dataPull.put("DFFSE", new BigDecimal(pd.get("je").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    Map<Object, Object> dmap = new HashMap<>();
                    dmap.put("IDPZH", pd.get("IDPZH"));
                    dmap.put("jdbz", "借");
                    dmap.put("KJTXDM", pd.get("KJTXDM"));
                    List<Map<String, Object>> pznrList = pznrMapper._queryByPznr(dmap);
                    if (pznrList.size() > 0 && pznrList != null) {
                        dataPull = getDfkmbmAndDfkmmc(pznrList, dfkmbm, dfkmbm, dataPull);
                    } else {
                        List<Map<String, Object>> pznrSmallJeList = pznrMapper._querySmallJe(dmap);
                        dataPull = getDfkmbmAndDfkmmc(pznrSmallJeList, dfkmbm, dfkmbm, dataPull);
                    }
                }

                /*
                //23.对方科目编码
                String dfkmmc = "";
                String dfkmbm = "";
                Map<Object, Object> dmap = new HashMap<>();
                dmap.put("IDPZH", pd.get("IDPZH"));
                dmap.put("KJTXDM", pd.get("KJTXDM"));
                List<Map<String, Object>> pznrList = pznrMapper._queryByPznr(dmap);

                //重复数据集合
                List<String> list1 = new ArrayList<>();
                //去重数据集合
                List<String> list2 = new  ArrayList<>();
                //负数集合
                List<String> list3 = new  ArrayList<>();

                for (Map<String, Object>  p1: pznrList) {

                dataPull.put("DFFSE",p1.get("je").toString());
                if (p1.get("jdbz").equals("借")){
                    //获得贷的重复的kmdm
                    for (Map<String, Object>  p2: pznrList) {
                        if (p2.get("jdbz").equals("贷")){
                            list1.add(p2.get("kmdm").toString());
                        }else{
                            //为空则遍历p1中je的负值,就是借的对方编码
                            if(new BigDecimal(p2.get("je").toString()).compareTo(new BigDecimal("0"))==-1){
                                list3.add(p2.get("kmdm").toString());
                            }

                        }
                    }
                    //的判断集合
                    if(list1.size()>0 && list1 != null){
                        //对list1进行去重获得list2
                        for (String cd:list1) {
                            if (!list2.contains(cd)) {
                                list2.add(cd);
                            }
                        }
                        //遍历list2,进行对方编码赋值
                        for (String bm: list2) {
                            dfkmbm+="/"+bm;
                            //根据bm查询kmmc
                            List<Map<String, Object>> kmdm1 = kmxxMapper._queryKmdm(bm);
                            dfkmmc+="/"+kmdm1.get(0).toString().trim();
                        }
                        //截取第一个"/"
                        dataPull.put("DFKMBM",dfkmbm.substring(1));
                        dataPull.put("DFKMDM",dfkmmc.substring(1));
                    }else{
                        //对list1进行去重获得list2
                        for (String cd:list3) {
                            if (!list2.contains(cd)) {
                                list2.add(cd);
                            }
                        }
                        //遍历list2,进行对方编码赋值
                        for (String bm: list2) {
                            dfkmbm+="/"+bm;
                            //根据bm查询kmmc
                            List<Map<String, Object>> kmdm1 = kmxxMapper._queryKmdm(bm);
                            dfkmmc+="/"+kmdm1.get(0).toString().trim();
                        }
                        //截取第一个"/"
                        dataPull.put("DFKMBM",dfkmbm.substring(1));
                        dataPull.put("DFKMDM",dfkmmc.substring(1));
                    }
                }else{
                    //取贷的对方
                    //获得贷的重复的kmdm
                    for (Map<String, Object>  p2: pznrList) {
                        if (p2.get("jdbz").equals("借")){
                            list1.add(p2.get("kmdm").toString());
                        }else{
                            //为空则遍历p1中je的负值,就是借的对方编码
                            if(new BigDecimal(p2.get("je").toString()).compareTo(new BigDecimal("0"))==-1){
                                list3.add(p2.get("kmdm").toString());
                            }

                        }
                    }
                    //的判断集合
                    if(list1.size()>0 && list1 != null){
                        //对list1进行去重获得list2
                        for (String cd:list1) {
                            if (!list2.contains(cd)) {
                                list2.add(cd);
                            }
                        }
                        //遍历list2,进行对方编码赋值
                        for (String bm: list2) {
                            dfkmbm+="/"+bm;
                            //根据bm查询kmmc
                            List<Map<String, Object>> kmdm1 = kmxxMapper._queryKmdm(bm);
                            dfkmmc+="/"+kmdm1.get(0).toString().trim();
                        }
                        //截取第一个"/"
                        dataPull.put("DFKMBM",dfkmbm.substring(1));
                        dataPull.put("DFKMDM",dfkmmc.substring(1));
                    }else{
                        //对list1进行去重获得list2
                        for (String cd:list3) {
                            if (!list2.contains(cd)) {
                                list2.add(cd);
                            }
                        }
                        //遍历list2,进行对方编码赋值
                        for (String bm: list2) {
                            dfkmbm+="/"+bm;
                            //根据bm查询kmmc
                            List<Map<String, Object>> kmdm1 = kmxxMapper._queryKmdm(bm);
                            dfkmmc+="/"+kmdm1.get(0).toString().trim();
                        }
                        //截取第一个"/"
                        dataPull.put("DFKMBM",dfkmbm.substring(1));
                        dataPull.put("DFKMDM",dfkmmc.substring(1));
                    }


                }

            }
                */
                //25.币种   人民币
                dataPull.put("BZ", "人民币");
                //26借方外币发生额   //为0
                dataPull.put("JFWBFSE", new BigDecimal("0"));
                //27.贷方外币发生额   //为0
                dataPull.put("DFWBFSE", new BigDecimal("0"));
                //28.汇率   //为空
                dataPull.put("HL", new BigDecimal("0"));
                //29.数量   //为0
                dataPull.put("SL", new BigDecimal("0"));
                //30.单价   //为空
                dataPull.put("DJ", new BigDecimal("0"));
                //31.结算方式   //为空
                dataPull.put("JSFS", "");
                //32.附件数
                dataPull.put("FJS", Integer.parseInt(pageDataPzmlList.get(0).get("fjzs").toString()));
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
                String pzly = pageDataPzmlList.get(0).get("pzly").toString();
                if (pzly.equals("") || StringUtils.isEmpty(pzly)) {
                    dataPull.put("YPZH", "");
                    //41.是否结转
                    dataPull.put("SFJZ", "0");
                } else {
                    dataPull.put("YPZH", pzly);
                    //41.是否结转
                    dataPull.put("SFJZ", "1");
                }
                //39.记账标志 0=作废；1=未审核；2=已审核；3=已记帐
                String zt = pageDataPzmlList.get(0).get("zt").toString();
                if (zt != null && !zt.equals("")) {
                    switch (zt) {
                        case "1":
                            dataPull.put("JZBZ", "0");
                            //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                            dataPull.put("ZFBZ", "0");
                            break;
                        case "2":
                            dataPull.put("JZBZ", "0");
                            //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                            dataPull.put("ZFBZ", "0");
                            break;
                        case "3":
                            dataPull.put("JZBZ", "1");
                            //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                            dataPull.put("ZFBZ", "0");
                            break;
                        default:
                            dataPull.put("JZBZ", "0");
                            //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                            dataPull.put("ZFBZ", "1");
                            break;
                    }
                }

                //42.是否为预算账
                if (pd.get("KJTXDM").toString().equals("01")) {
                    dataPull.put("SFWYSZ", "0");
                } else {
                    dataPull.put("SFWYSZ", "1");
                }
                //43.支付单据编号   为空
                dataPull.put("ZFDJBH", "");
                //44.功能科目代码
                String fzdm4 = pd.get("fzdm4").toString();
                Map<Object, Object> dataFzxlbMap = new HashMap<>();
                if (!fzdm4.equals("") && !StringUtils.isEmpty(fzdm4)) {
                    dataPull.put("GNKMDM", fzdm4);
                    //45.功能科目名称
                    dataFzxlbMap.put("fzdm", fzdm4);
                    List<Map<String, Object>> fzxzlList = glFzxzlMapper._queryFzdm(dataFzxlbMap);
                    if (fzxzlList.size() > 0 && fzxzlList != null) {
                        dataPull.put("GNKMMC", fzxzlList.get(0).get("fzmc"));
                    } else {
                        dataPull.put("GNKMDM", " ");
                        dataPull.put("GNKMMC", " ");
                    }
                } else {
                    dataPull.put("GNKMDM", " ");
                    dataPull.put("GNKMMC", " ");
                }
                //46.经济科目代码
                String fzdm5 = pd.get("fzdm5").toString();
                Map<Object, Object> dataFzxlbMap5 = new HashMap<>();
                if (!fzdm4.equals("") && !StringUtils.isEmpty(fzdm5)) {
                    dataPull.put("JJKMDM", fzdm5);
                    //45.功能科目名称
                    dataFzxlbMap5.put("fzdm", fzdm5);
                    //47.经济科目名称
                    List<Map<String, Object>> FzxzlList = glFzxzlMapper._queryGL_Fzxzl(dataFzxlbMap);
                    if (FzxzlList.size() > 0 && FzxzlList != null) {
                        dataPull.put("JJKMMC", FzxzlList.get(0).get("fzmc"));
                    } else {
                        dataPull.put("JJKMDM", " ");
                        dataPull.put("JJKMMC", " ");
                    }
                } else {
                    dataPull.put("JJKMDM", " ");
                    dataPull.put("JJKMMC", " ");
                }
                //48.资金性质代码   //为空
                dataPull.put("ZJXZDM", " ");
                //49.资金性质名称   //为空
                dataPull.put("ZJXZMC", " ");
                //50.指标来源代码   //为空
                dataPull.put("ZBLYDM", " ");
                //51.指标来源名称   //为空
                dataPull.put("ZBLYMC", " ");
                //52.支出类型代码   //为空
                dataPull.put("ZCLXDM", " ");
                //53.支出类型名称   //为空
                dataPull.put("ZCLXMC", " ");
                //54.预算管理类型代码   //为空
                dataPull.put("YSGLLXDM", " ");
                //55.预算管理类型名称   //为空
                dataPull.put("YSGLLXMC", " ");
                //56.支付方式代码   //为空
                dataPull.put("ZFFSDM", " ");
                //57.支付方式名称   //为空
                dataPull.put("ZFFSMC", " ");
                //58.预算项目代码   //为空
                dataPull.put("YSXMDM", " ");
                //59.预算项目名称   //为空
                dataPull.put("YSXMMC", " ");
                //60.项目分类代码   //为空
                dataPull.put("XMFLDM", " ");
                //61.项目分类名称   //为空
                dataPull.put("XMFLMC", " ");
                //62.指标文号名称   //为空
                dataPull.put("ZBWHMC", " ");
                //63.结算方式代码   //为空
                dataPull.put("JSFSDM", " ");
                //64.结算方式名称   //为空
                dataPull.put("JSFSMC", " ");
                resultList.add(dataPull);
            }
        }
        /*
        //resultList进行循环
        //List<Map<String, Object>> resultList = new ArrayList<>();
        //List<Map<String, Object>> resultAllList = new ArrayList<>();
        for (Map<String, Object> rlist : resultList) {
            //10020101
            //判断长度为4，如果为4，则是它本身
            if(rlist.get("kmdm").toString().length()==4){
                resultAllList.add(rlist);
            }else{
                //不为4，则按照kmdm的级次进行查询
                //查询级次
                int jc = rlist.get("kmdm").toString().length();
                int ie = ((jc - 4) / 2) + 1;
                for (int i = 0; i < ie; i++) {
                    //寻找对应长度的kmdm信息
                }
            }
        }
        */

        List<Map<String, Object>> resultListNew = dbyService.kjkmResult(resultList, pageDataGL_Ztcs.get(0));
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map map1 : resultListNew
            ) {
                jzpzMapper._addJzpz(map1);
            }
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


    private Map<String, Object> dealAmount(Map<String, Object> pd, Map<String, Object> dataPullBase) {
        BigDecimal jfljfse = new BigDecimal("0");
        BigDecimal dfljfse = new BigDecimal("0");
        for (int i = 1; i < 13; i++) {
            if (!pd.get("yj" + i).toString().equals("0") && !StringUtils.isEmpty(pd.get("yj" + i).toString().trim()) &&
                    !pd.get("yd" + i).toString().equals("0") && !StringUtils.isEmpty(pd.get("yd" + i).toString().trim())
            ) {
                //8.会计月份
                dataPullBase.put("KJYF", i);
            }
            //15、年初余额方向
            BigDecimal a = new BigDecimal(pd.get("ncj").toString());
            BigDecimal b = new BigDecimal(pd.get("ncd").toString());
            if (a.compareTo(b) == 1) {
                dataPullBase.put("NCYEFX", 1);
            } else if (a.compareTo(b) == 0) {
                dataPullBase.put("NCYEFX", 0);
            } else {
                dataPullBase.put("NCYEFX", -1);
            }
            //16、期初借方余额
            BigDecimal yji = new BigDecimal(pd.get("yj" + i).toString());
            BigDecimal qcjfye = a.add(yji);
            dataPullBase.put("QCJFYE", qcjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
            //17、期初贷方余额
            BigDecimal ydi = new BigDecimal(pd.get("yd" + i).toString());
            BigDecimal qcdfye = b.add(ydi);
            dataPullBase.put("QCDFYE", qcdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
            if (qcjfye.compareTo(qcjfye) == 1) {
                //18、期初余额方向
                dataPullBase.put("QCYEFX", 1);
            } else if (qcjfye.compareTo(qcjfye) == 0) {
                //18、期初余额方向
                dataPullBase.put("QCYEFX", 0);
            } else {
                //18、期初余额方向
                dataPullBase.put("QCYEFX", -1);
            }
            //19.借方发生额
            dataPullBase.put("JFFSE", yji);
            //20.借方累计发生额
            jfljfse = jfljfse.add(yji);
            dfljfse = dfljfse.add(ydi);
            dataPullBase.put("JFLJFSE", jfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));
            //21.贷方发生额
            dataPullBase.put("DFFSE", ydi);
            dataPullBase.put("DFLJFSE", dfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));

            //23.期末借方余额
            //24.期末贷方余额
            //25.期末余额方向
            BigDecimal jj = qcjfye.add(yji);
            BigDecimal dd = qcdfye.add(ydi);
            if (jj.compareTo(dd) == 1) {
                BigDecimal qmjfye = dd.subtract(dd);
                dataPullBase.put("QMJFYE", qmjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                dataPullBase.put("QMDFYE", new BigDecimal("0"));
                dataPullBase.put("QMYEFX", 1);
            } else if (jj.compareTo(dd) == -1) {
                BigDecimal qmdfye = dd.subtract(jj);
                dataPullBase.put("QMJFYE", new BigDecimal("0"));
                dataPullBase.put("QMDFYE", qmdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                dataPullBase.put("QMYEFX", -1);
            } else {
                dataPullBase.put("QMJFYE", new BigDecimal("0"));
                dataPullBase.put("QMDFYE", new BigDecimal("0"));
                dataPullBase.put("QMYEFX", 0);
            }
        }
        return dataPullBase;
    }

    private Map<String, Object> getDfkmbmAndDfkmmc(List<Map<String, Object>> list, String dfkmbm, String dfkmmc, Map<String, Object> dataPull) {
        for (Map<String, Object> li : list) {
            //24.对方科目名称
            dfkmbm += "/" + li.get("kmdm").toString();
            List<Map<String, Object>> kmxxList = kmxxMapper._queryKmdm(li.get("kmdm").toString());
            dfkmmc += "/" + kmxxList.get(0).get("kmmc").toString();
        }
        if (dfkmmc.length() > 1) {
            dataPull.put("DFKMMC", dfkmmc.substring(1));
        } else {
            dataPull.put("DFKMMC", " ");
        }
        if (dfkmbm.length() > 1) {
            dataPull.put("DFKMBM", dfkmbm.substring(1));
        } else {
            dataPull.put("DFKMBM", " ");
        }
        return dataPull;
    }
}