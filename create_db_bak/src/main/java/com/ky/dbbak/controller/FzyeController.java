package com.ky.dbbak.controller;

import com.ky.dbbak.service.FzxlbService;
import com.ky.dbbak.service.kmyeService;
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
    kmyeService kmyeService;

    //KMYE   科目余额表
    @RequestMapping(value = "fzye")
    @ResponseBody
    public String fzye(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = kmxzlxMapper._queryGL_Yeb();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        Map<String, Object> stringObjectMap = fzxlbService._queryGL_Fzxlb1(pageData);
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
            dataPullBase.put("KJYF","  ");
            dataPullBase.put("KJTX","  ");
            dataPullBase.put("KJKMBM", " ");
            dataPullBase.put("KJKMMC", " ");
            dataPullBase.put("KJKMQC", " ");
            dataPullBase.put("KJKMJC", 1);
            dataPullBase.put("SFZDJKM", 1);
            dataPullBase.put("SJKMBM", " ");
            dataPullBase.put("NCJFYE", BigDecimal.ZERO);
            dataPullBase.put("NCDFYE", BigDecimal.ZERO);
            dataPullBase.put("NCYEFX", 0);
            dataPullBase.put("QCJFYE", BigDecimal.ZERO);
            dataPullBase.put("QCDFYE", BigDecimal.ZERO);
            dataPullBase.put("QCYEFX", 0);
            dataPullBase.put("JFFSE", BigDecimal.ZERO);
            dataPullBase.put("JFLJFSE", BigDecimal.ZERO);
            dataPullBase.put("DFFSE", BigDecimal.ZERO);
            dataPullBase.put("DFLJFSE", BigDecimal.ZERO);
            dataPullBase.put("QMJFYE", BigDecimal.ZERO);
            dataPullBase.put("QMDFYE", BigDecimal.ZERO);
            dataPullBase.put("QMYEFX", 0);
            dataPullBase.put("FLS", 0);
            dataPullBase.put("BZMC", datadzzbxx.get("BWB"));
            //26.期初外币借方余额
            dataPullBase.put("QCWBJFYE", BigDecimal.ZERO);
            //27.期初外币贷方余额
            dataPullBase.put("QCWBDFYE", BigDecimal.ZERO);
            //28.借方外币发生额
            dataPullBase.put("JFWBFSE", BigDecimal.ZERO);
            //29.DFWBFSE
            dataPullBase.put("DFWBFSE", BigDecimal.ZERO);
            //30.QMWBJFYE
            dataPullBase.put("QMWBJFYE", BigDecimal.ZERO);
            //31.期末外币贷方余额
            dataPullBase.put("QMWBDFYE",BigDecimal.ZERO);
            dataPullBase.put("FZLX", " ");
            dataPullBase.put("FZBM", " ");
            dataPullBase.put("FZMC", " ");
            dataPullBase.put("SJFZBM", " ");
            dataPullBase.put("FZJB", " ");
            dataPullBase.put("KJKMBM", pd.get("kmdm"));
            BigDecimal jfljfse = BigDecimal.ZERO;
            BigDecimal dfljfse = BigDecimal.ZERO;
            BigDecimal qmjfye = BigDecimal.ZERO;
            BigDecimal qmdfye = BigDecimal.ZERO;

            List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
            dataPullBase.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
            dataPullBase.put("KJKMBM", pd.get("kmdm"));
            dataPullBase.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
            dataPullBase.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
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
                            dataPullBase.put("KJKMJB", w + 1);
                            dataPullBase.put("SJKMBM", pd.get("kmdm").toString().substring(0, num - Integer.valueOf(lbfjStr[w])));
                        }
                    }
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("kmdms", kmdms);
                    List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                    String kjkmqc = String.join("/", pageDataGL_KMXX1);
                    kjkmqc = kjkmqc.trim();
                    kjkmqc = kjkmqc.replace("　", "");
                    dataPullBase.put("KJKMQC", kjkmqc);
                } else {
                    dataPullBase.put("KJKMJB", 1);
                    dataPullBase.put("SJKMBM", "");
                    dataPullBase.put("KJKMQC", pageDataGL_KMXX.get(0).get("kmmc"));
                }
            }
            for (int i = 1; i < 13; i++) {
                if (BigDecimal.valueOf(Double.valueOf(pd.get("ncd").toString())).compareTo(BigDecimal.ZERO) == 0 && BigDecimal.valueOf(Double.valueOf(pd.get("ncj").toString())).compareTo(BigDecimal.ZERO) == 0) {
                    if (BigDecimal.valueOf(Double.valueOf(pd.get("yd" + i).toString())).compareTo(BigDecimal.ZERO) == 0 && BigDecimal.valueOf(Double.valueOf(pd.get("yj" + i).toString())).compareTo(BigDecimal.ZERO) == 0) {
                        continue;
                    }
                }
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                //8.会计月份
                dataPull.put("KJYF", i);
                //13.年初借方余额
                BigDecimal ncj = new BigDecimal(pd.get("ncj").toString());
                if (ncj.compareTo(new BigDecimal("0")) == 0) {
                    dataPull.put("NCJFYE", new BigDecimal("0"));
                } else {
                    dataPull.put("NCJFYE", ncj.setScale(4, BigDecimal.ROUND_HALF_UP));
                }
                //14.年初贷方余额
                BigDecimal ncd = new BigDecimal(pd.get("ncd").toString());
                if (ncd.compareTo(new BigDecimal("0")) == 0) {
                    dataPull.put("NCDFYE", new BigDecimal("0"));
                } else {
                    dataPull.put("NCDFYE", ncd.setScale(4, BigDecimal.ROUND_HALF_UP));
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
                    qcjfye = ncj;
                    qcdfye = ncd;
                    dataPull.put("QCJFYE", ncj.setScale(4, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QCDFYE", ncd.setScale(4, BigDecimal.ROUND_HALF_UP));
                } else {
                    qcjfye=qmjfye;
                    qcdfye=qmdfye;
                    dataPull.put("QCJFYE", qmjfye.setScale(2,BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QCDFYE", qmdfye.setScale(2,BigDecimal.ROUND_HALF_UP));
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
                BigDecimal jj = qcjfye.add(jffse);
                BigDecimal dd = qcdfye.add(dffse);
                if (jj.compareTo(dd) == 1) {
                    qmjfye = jj.subtract(dd);
                    qmdfye = new BigDecimal("0");
                    dataPull.put("QMJFYE", qmjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QMDFYE", new BigDecimal("0"));
                    dataPull.put("QMYEFX", 1);
                } else if (jj.compareTo(dd) == -1) {
                    qmdfye = dd.subtract(jj);
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
                long num = kmxzlxMapper._queryPznrCount(qCountPd);
                dataPull.put("FLS", num);
                if (pd.get("fzdm0") != null && !StringUtils.isEmpty(pd.get("fzdm0").toString().trim())) {
                    dataPull=new HashMap<String,Object>(dataPull);
                    dataPull.put("FZLX", "部门");
                    Map<String, Object> queryPd1 = new HashMap<String, Object>();
                    Map<String, Object> queryPd2 = new HashMap<String, Object>();
                    queryPd1.put("bmdm", pd.get("fzdm0"));
                    List<Map<String, Object>> pageDataPUBBMXX = sourceMapper._queryPubbmxx(queryPd1);
                    queryPd2.put("lbdm", "0");
                    dataPull.put("FZBM", "");
                    dataPull.put("FZMC", "");
                    dataPull.put("FZQC", "");
                    if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                        dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                        dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                        dataPull.put("FZJB", 1);
                        dataPull.put("SJFZBM", " ");
                    }
                    resultList.add(dataPull);
                }
                if (pd.get("fzdm1") != null && !StringUtils.isEmpty(pd.get("fzdm1").toString().trim())) {
                    dataPull=new HashMap<String,Object>(dataPull);
                    dataPull.put("FZLX", "项目");
                    Map<String, Object> queryPd1 = new HashMap<String, Object>();
                    Map<String, Object> queryPd2 = new HashMap<String, Object>();
                    queryPd1.put("xmdm", pd.get("fzdm1"));
                    List<Map<String, Object>> pageDataGL_Xmzl = sourceMapper._queryGL_Xmzl(queryPd1);
                    queryPd2.put("lbdm", "1");
                    dataPull.put("FZBM", "");
                    dataPull.put("FZMC", "");
                    dataPull.put("FZQC", "");
                    if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                        dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                        dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                        dataPull.put("FZJB", 1);
                        dataPull.put("SJFZBM", " ");
                    }
                    resultList.add(dataPull);
                }
                if (pd.get("fzdm3") != null && !StringUtils.isEmpty(pd.get("fzdm2").toString().trim())) {

                }
                if (pd.get("fzdm2") != null && !StringUtils.isEmpty(pd.get("fzdm3").toString().trim())) {
                    dataPull=new HashMap<String,Object>(dataPull);
                    dataPull.put("FZLX", "往来单位");
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("wldm", pd.get("fzdm3"));
                    List<Map<String, Object>> pageDataPUBKSZL = sourceMapper._queryPUBKSZL(queryPd);
                    queryPd.put("lbdm", "2");
                    dataPull.put("FZBM", "");
                    dataPull.put("FZMC", "");
                    dataPull.put("FZQC", "");
                    if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                        dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                        dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                        dataPull.put("FZJB", 1);
                        dataPull.put("SJFZBM", " ");
                    }
                    resultList.add(dataPull);
                }
                for (int q = 4; q < 31; q++) {
                    dataPull = new HashMap<String, Object>(dataPull);
                    if (pd.get("fzdm" + q) != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString().trim())) {
                        Map<String, Object> queryPd1 = new HashMap<String, Object>();
                        Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get(String.valueOf(q));
                        queryPd1.put("fzdm", pd.get("fzdm" + q));
                        Map<String, Object> queryPd2 = new HashMap<String, Object>();
                        queryPd2.put("lbdm", String.valueOf(q));
                        dataPull.put("FZLX", pageDataGL_Fzxlb.get("lbmc"));
                        dataPull.put("FZBM", pd.get("fzdm" + q));
                        List<Map<String, Object>> pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd1);
                        dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                        String lbfj = pageDataGL_Fzxlb.get("lbfj").toString();
                        String[] lbfjStr = lbfj.split("-");
                        String result = pd.get("fzdm" + q).toString();
                        int numx = 0;
                        for (int w = 0; w < lbfjStr.length; w++) {
                            numx = numx + Integer.valueOf(lbfjStr[w]);
                            if (numx <= result.length()) {
                                dataPull.put("SJFZBM", result.substring(0, numx - Integer.valueOf(lbfjStr[w])));
                            }
                            if (numx == result.length()) {
                                dataPull.put("FZJB", (w + 1));
                            }
                        }
                        resultList.add(dataPull);
                    }
                }
            }
        }
//        Integer listNum = resultList.size();
//        Integer listnum2 = listNum % 50;
//        Integer listnum3 = listNum / 50;
//        Map map = new HashMap();
//        for (int p = 0; p < listnum3; p++) {
//            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
//            fzyeMapper._addFzye(map);
//        }
//        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
//        fzyeMapper._addFzye(map);
//        for (Map map1:resultList
//        ) {
//            fzyeMapper._add(map1);
//        }
        List<Map<String, Object>> resultListNew = kmyeService.kjkmResult(resultList, pageDataGL_Ztcs.get(0));
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map map1 : resultListNew
            ) {
                fzyeMapper._add(map1);
            }
        }
        return "success";
    }


}
