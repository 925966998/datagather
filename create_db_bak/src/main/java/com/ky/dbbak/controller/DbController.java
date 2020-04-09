package com.ky.dbbak.controller;


import com.ky.dbbak.entity.FZNCSEntity;
import com.ky.dbbak.service.*;
import com.ky.dbbak.sourcemapper.KmxzlxMapper;
import com.ky.dbbak.sourcemapper.SourceMapper;
import com.ky.dbbak.sourcemapper.ZtcsMapper;
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
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = sourceMapper._queryGL_Yeb(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> stringObjectMap = fzxlbService._queryGL_Fzxlb1(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
        for (Map<String, Object> pd : GL_YebList
        ) {
            Map<String, Object> dataPullBase = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPullBase.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPullBase.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPullBase.put("KJND", datadzzbxx.get("KJND"));
            dataPullBase.put("DWMC", datadzzbxx.get("DWMC"));
            dataPullBase.put("DWDM", datadzzbxx.get("DWDM"));
            dataPullBase.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPullBase.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            dataPullBase.put("BZMC", dzzbxxList.get(0).get("BWB"));
            dataPullBase.put("KJYF", 1);
            dataPullBase.put("KJKMBM", " ");
            dataPullBase.put("KJKMMC", " ");
            dataPullBase.put("KJKMJC", 1);
            dataPullBase.put("KJTX", " ");
            dataPullBase.put("SFZDJKM", 1);
            dataPullBase.put("SJKMBM", " ");
            dataPullBase.put("FZLX", " ");
            dataPullBase.put("FZBM", " ");
            dataPullBase.put("FZMC", " ");
            dataPullBase.put("YEFX", 1);
            dataPullBase.put("BBQCYE", BigDecimal.ZERO);
            dataPullBase.put("QCSL", BigDecimal.ZERO);
            dataPullBase.put("WBQCYE", BigDecimal.ZERO);

            if (BigDecimal.valueOf(Double.valueOf(pd.get("ncd").toString())).compareTo(BigDecimal.ZERO) == 0 && BigDecimal.valueOf(Double.valueOf(pd.get("ncj").toString())).compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            if (pd.get("ncj") != null && !StringUtils.isEmpty(pd.get("ncj").toString().trim())) {
                dataPullBase.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pd.get("ncj").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
            } else {
                dataPullBase.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pd.get("ncd").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            dataPullBase.put("QCSL", BigDecimal.ZERO);
            dataPullBase.put("WBQCYE", BigDecimal.ZERO);
            dataPullBase.put("KJKMBM", pd.get("kmdm"));
            List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPullBase.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("D")) {
                    dataPullBase.put("YEFX", -1);
                    dataPullBase.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pd.get("ncd").toString()) - Double.valueOf(pd.get("ncj").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("J")) {
                    dataPullBase.put("YEFX", 1);
                    dataPullBase.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pd.get("ncj").toString()) - Double.valueOf(pd.get("ncd").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                }

                Integer legth = pageDataGL_KMXX.get(0).get("kmdm").toString().length();
                if (legth > 4) {

                    String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
                    String[] lbfjStr = kmbmfa.split("-");
                    int num = 0;
                    for (int w = 0; w < lbfjStr.length; w++) {
                        num = num + Integer.valueOf(lbfjStr[w]);
                        if (legth == num) {
                            dataPullBase.put("KJKMJC", w + 1);
                            dataPullBase.put("SJKMBM", pd.get("kmdm").toString().substring(0, num - Integer.valueOf(lbfjStr[w])));
                        }
                    }
                } else {
                    dataPullBase.put("KJKMJC", 1);
                }
//                switch (legth) {
//                    case 4:
//                        dataPullBase.put("KJKMJC", 1);
//                        break;
//                    case 6:
//                        dataPullBase.put("KJKMJC", 2);
//                        dataPullBase.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 4));
//                        break;
//                    case 8:
//                        dataPullBase.put("KJKMJC", 3);
//                        dataPullBase.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 6));
//                        break;
//                    case 10:
//                        dataPullBase.put("KJKMJC", 4);
//                        dataPullBase.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 8));
//                        break;
//                    case 12:
//                        dataPullBase.put("KJKMJC", 5);
//                        dataPullBase.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 10));
//                        break;
//                }
                dataPullBase.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                dataPullBase.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));

            }
            if (pd.get("fzdm0") != null && !StringUtils.isEmpty(pd.get("fzdm0").toString().trim())) {
                Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "部门");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("bmdm", pd.get("fzdm0"));
                List<Map<String, Object>> pageDataPUBBMXX = sourceMapper._queryPubbmxx(queryPd);
                if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                    dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                }
                resultList.add(dataPull);
            }
            if (pd.get("fzdm1") != null && !StringUtils.isEmpty(pd.get("fzdm1").toString().trim())) {
                Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "项目");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("xmdm", pd.get("fzdm1"));
                List<Map<String, Object>> pageDataGL_Xmzl = sourceMapper._queryGL_Xmzl(queryPd);
                if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                    dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                }
                resultList.add(dataPull);
            }
            if (pd.get("fzdm2") != null && !StringUtils.isEmpty(pd.get("fzdm2").toString().trim())) {

            }
            if (pd.get("fzdm3") != null && !StringUtils.isEmpty(pd.get("fzdm3").toString().trim())) {
                Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "单位往来");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("wldm", pd.get("fzdm3"));
                List<Map<String, Object>> pageDataPUBKSZL = sourceMapper._queryPUBKSZL(queryPd);
                if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                    dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                }
                resultList.add(dataPull);
            }
            for (int q = 4; q < 31; q++) {
                if (pd.get("fzdm" + q) != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString().trim())) {
                    Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("fzdm", pd.get("fzdm" + q));
                    queryPd.put("lbdm", String.valueOf(q));
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get(String.valueOf(q));
//                    List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                    List<Map<String, Object>> pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
//                    if (pageDataFzxlb != null && pageDataFzxlb.size() > 0) {
                    dataPull.put("FZLX", pageDataGL_Fzxlb.get("lbmc"));
//                    }
                    if (pageDataGL_Fzxzl != null && pageDataGL_Fzxzl.size() > 0) {
                        dataPull.put("FZBM", pageDataGL_Fzxzl.get(0).get("fzdm"));
                        dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                    }
                    resultList.add(dataPull);
                }
            }
        }


        //        for (Map<String, Object> map : resultList
//        ) {
//            sourceMapper._addFzncs(map);
//        }

        List<Map<String, Object>> resultListNew = targetService.kjkmResult(resultList, pageDataGL_Ztcs.get(0));


        List<String> resultMapListStr = new ArrayList<String>();
        List<String> resultMapHaveListStr = new ArrayList<String>();
        List<Map<String, Object>> resultListNew2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultListNew2Have = new ArrayList<Map<String, Object>>();
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map<String, Object> map : resultListNew
            ) {
                if (!resultMapListStr.contains(map.get("KJDZZBBH") + "-" + map.get("FZLX") + "-" + map.get("KJKMBM") + "-" + map.get("FZBM"))) {
                    resultMapListStr.add(map.get("KJDZZBBH") + "-" + map.get("FZLX") + "-" + map.get("KJKMBM") + "-" + map.get("FZBM"));
                    resultListNew2.add(map);
                } else {
                    resultMapHaveListStr.add(map.get("KJDZZBBH") + "-" + map.get("FZLX") + "-" + map.get("KJKMBM") + "-" + map.get("FZBM"));
                    resultListNew2Have.add(map);
                }

            }
        }

        for (Map map3 : resultListNew2
        ) {
            for (Map map4 : resultListNew2Have
            ) {
                if ((map3.get("KJDZZBBH") + "-" + map3.get("FZLX") + "-" + map3.get("KJKMBM") + "-" + map3.get("FZBM")).equals(map4.get("KJDZZBBH") + "-" + map4.get("FZLX") + "-" + map4.get("KJKMBM") + "-" + map4.get("FZBM"))) {
                    map3.put("BBQCYE", new BigDecimal(map3.get("BBQCYE").toString()).add(new BigDecimal(map4.get("BBQCYE").toString())));
                    map3.put("QCSL", new BigDecimal(map3.get("QCSL").toString()).add(new BigDecimal(map4.get("QCSL").toString())));
                    map3.put("WBQCYE", new BigDecimal(map3.get("WBQCYE").toString()).add(new BigDecimal(map4.get("WBQCYE").toString())));
                }
            }
        }
//
        if (resultListNew2 != null && resultListNew2.size() > 0) {
            for (Map map1 : resultListNew2
            ) {
                tragetMapper._addFzncs(map1);
            }
        }


//        if (resultListNew != null && resultListNew.size() > 0) {
//            for (Map map1 : resultListNew
//            ) {
//                Map newMap = new HashMap();
//                newMap.put("KJDZZBBH",map1.get("KJDZZBBH"));
//                newMap.put("KJKMBM",map1.get("KJKMBM"));
//                newMap.put("FZLX",map1.get("FZLX"));
//                newMap.put("FZBM",map1.get("FZBM"));
//                List<FZNCSEntity>  FzncsEntities = fzyeMapper.queryFzncs(newMap);
//                if(FzncsEntities == null || FzncsEntities.size() == 0){
//                    tragetMapper._addFzncs(map1);
//                }else {
//                    BigDecimal sumBBQCYE = new BigDecimal(map1.get("BBQCYE").toString()).add(new BigDecimal(FzncsEntities.get(0).getBBQCYE()));
//                    BigDecimal sumQCSL = new BigDecimal(map1.get("QCSL").toString()).add(new BigDecimal(FzncsEntities.get(0).getQCSL()));
//                    BigDecimal sumWBQCYE = new BigDecimal(map1.get("WBQCYE").toString()).add(new BigDecimal(FzncsEntities.get(0).getWBQCYE()));
//                    map1.put("NCJFYE",sumBBQCYE);
//                    map1.put("NCDFYE",sumQCSL);
//                    map1.put("QCJFYE",sumWBQCYE);
//                    fzyeMapper._updateFzncs(map1);
//                }
//
//            }
//        }
//        Integer listNum = resultListNew.size();
//        Integer listnum2 = listNum % 50;
//        Integer listnum3 = listNum / 50;
//        Map map = new HashMap();
//        for (int p = 0; p < listnum3; p++) {
//            map.put("list", resultListNew.subList(p * 50, (p * 50 + 50)));
//            tragetMapper._addFzncs(map);
//        }
//        map.put("list", resultListNew.subList(resultListNew.size() - listnum2, resultListNew.size()));
//        tragetMapper._addFzncs(map);
//        Integer listNum = resultList.size();
//        Integer listnum2 = listNum % 50;
//        Integer listnum3 = listNum / 50;
//        for (int p = 0; p < listnum3; p++) {
//            twoDbService.insertFZNCSBatch(resultList.subList(p * 50, (p * 50 + 50)));
//        }
//        twoDbService.insertFZNCSBatch(resultList.subList(resultList.size() - listnum2, resultList.size()));
        return "success";
    }

    @RequestMapping(value = "pzfzmx")
    @ResponseBody
    public String index(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> bypznrList = sourceMapper._queryPznr(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        int i = 1;
        int flag = 1;
        Map<String, Object> stringObjectMap = fzxlbService._queryGL_Fzxlb1(pageData);
        for (Map<String, Object> pd : bypznrList
        ) {
            Map<String, Object> dataPullBase = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPullBase.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPullBase.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPullBase.put("KJND", datadzzbxx.get("KJND"));
            dataPullBase.put("DWMC", datadzzbxx.get("DWMC"));
            dataPullBase.put("DWDM", datadzzbxx.get("DWDM"));
            dataPullBase.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPullBase.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            dataPullBase.put("BZ", dzzbxxList.get(0).get("BWB"));
            dataPullBase.put("PZLXBH", " ");
            dataPullBase.put("JZPZZL", " ");
            dataPullBase.put("JZPZBH", " ");
            dataPullBase.put("JZPZHH", " ");
            dataPullBase.put("FLXH", " ");
            dataPullBase.put("JZPZZY", " ");
            dataPullBase.put("KJTX", " ");
            dataPullBase.put("KJKMBM", " ");
            dataPullBase.put("KJKMMC", " ");
            dataPullBase.put("FZLX", " ");
            dataPullBase.put("FZBM", " ");
            dataPullBase.put("FZMC", " ");
            dataPullBase.put("FZQC", " ");
            dataPullBase.put("YJFZBM", " ");
            dataPullBase.put("EJFZBM", " ");
            dataPullBase.put("SJFZBM", " ");
            dataPullBase.put("SIJFZBM", " ");
            dataPullBase.put("WJFZBM", " ");
            dataPullBase.put("JFFSE", BigDecimal.ZERO);
            dataPullBase.put("DFFSE", BigDecimal.ZERO);
            dataPullBase.put("WBJFFSE", BigDecimal.ZERO);
            dataPullBase.put("WBDFFSE", BigDecimal.ZERO);
            dataPullBase.put("HL", BigDecimal.ZERO);
            dataPullBase.put("SL", BigDecimal.ZERO);
            dataPullBase.put("DJ", BigDecimal.ZERO);
            if (pd.get("jdbz") != null && pd.get("jdbz").toString().trim().equals("借")) {
                dataPullBase.put("JFFSE", BigDecimal.valueOf(Double.valueOf(pd.get("je").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                dataPullBase.put("DFFSE", BigDecimal.ZERO);
            } else if (pd.get("jdbz") != null && pd.get("jdbz").toString().trim().equals("贷")) {
                dataPullBase.put("DFFSE", BigDecimal.valueOf(Double.valueOf(pd.get("je").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                dataPullBase.put("JFFSE", BigDecimal.ZERO);
            }
            String kjqj = pd.get("kjqj").toString();
            if (!StringUtils.isEmpty(kjqj)) {
                Integer mouth = Integer.valueOf(kjqj.substring(kjqj.length() - 2, kjqj.length()));
                dataPullBase.put("KJYF", mouth);
            }

            String pzh = "";
            String flh = "";
            if (pd.get("pzh").toString().length() < 2) {
                pzh = "00" + pd.get("pzh").toString();
            } else {
                pzh = pd.get("pzh").toString();
            }
            if (pd.get("flh").toString().length() > 1) {
                flh = "00" + pd.get("flh").toString();
            } else {
                flh = pd.get("flh").toString();
            }
            String pzhpj = dataPullBase.get("KJYF").toString() + pzh + flh;
            dataPullBase.put("JZPZBH", pd.get("pzh"));
            dataPullBase.put("JZPZHH", (pzhpj));

            dataPullBase.put("JZPZZY", pd.get("zy"));
            dataPullBase.put("KJTX", pd.get("KJTXDM"));
            if (pd.get("KJTXDM") != null && pd.get("KJTXDM").toString().equals("01")) {
                dataPullBase.put("JZPZZL", "财记");
                dataPullBase.put("PZLXBH", "财记");
                dataPullBase.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
                        + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
                        + "-" + "财记" + "-" + pd.get("pzh") + "-" + pzhpj + "-" + pd.get("KJTXDM"));


            } else if (pd.get("KJTXDM") != null && pd.get("KJTXDM").toString().equals("02")) {
                dataPullBase.put("JZPZZL", "预记");
                dataPullBase.put("PZLXBH", "预记");
                dataPullBase.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
                        + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
                        + "-" + "预记" + "-" + pd.get("pzh") + "-" + pzhpj + "-" + pd.get("KJTXDM"));

            }

            dataPullBase.put("KJKMBM", pd.get("kmdm"));
            List<Map<String, Object>> dataKmxx = sourceMapper._queryGL_KMXX(pd);
            if(dataKmxx!=null&&dataKmxx.size()>0){
                dataPullBase.put("KJKMMC", dataKmxx.get(0).get("kmmc").toString().trim().replace("　", ""));
            }
            //bmdm ---PUBBMXX
            //xmdm---GL_Xmzl
            //wldw---PUBKSZL
            if (pd.get("bmdm") != null && !StringUtils.isEmpty(pd.get("bmdm").toString())) {
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "部门");
                List<Map<String, Object>> pageDataPUBBMXX = sourceMapper._queryPubbmxx(pd);
//                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");

                if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                    dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("0");
                    String fzlxjg = pageDataGL_Fzxlb.get("lbfj").toString();
                    String fzdm = pageDataPUBBMXX.get(0).get("bmdm").toString();
                    List<String> qc = new ArrayList<String>();
                    if (!StringUtils.isEmpty(fzlxjg)) {
                        String[] fzlxjgStr = fzlxjg.split("-");
                        int num = 0;//3  3  2  2  111 111 11
                        if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                            for (int w = 0; w < fzlxjgStr.length; w++) {
                                num = num + Integer.valueOf(fzlxjgStr[w]);
                                if (num < fzdm.length()) {
                                    List<Map<String, Object>> maps = kmxzlxMapper._queryPUBBMXXq(fzdm.substring(0, num));
                                    qc.add(maps.get(0).get("bmmc").toString());
                                }
                            }
                        }
                    }
                    qc.add(pageDataPUBBMXX.get(0).get("bmmc").toString());
                    dataPull.put("FZQC", String.join("/", qc));
                    dataPull = wuji(pageDataGL_Fzxlb, pageDataPUBBMXX.get(0).get("bmdm").toString(), dataPull);
                }
                resultList.add(dataPull);
                flag = 2;
            }
            if (pd.get("wldm") != null && !StringUtils.isEmpty(pd.get("wldm").toString())) {
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "单位往来");
                List<Map<String, Object>> pageDataPUBKSZL = sourceMapper._queryPUBKSZL(pd);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "3");
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");
//                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                    dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("3");
                    String fzlxjg = pageDataGL_Fzxlb.get("lbfj").toString();
                    String fzdm = pageDataPUBKSZL.get(0).get("dwdm").toString();
                    List<String> qc = new ArrayList<String>();
                    if (!StringUtils.isEmpty(fzlxjg)) {
                        String[] fzlxjgStr = fzlxjg.split("-");
                        int num = 0;//3  3  2  2  111 111 11
                        if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                            for (int w = 0; w < fzlxjgStr.length; w++) {
                                num = num + Integer.valueOf(fzlxjgStr[w]);
                                if (num < fzdm.length()) {
                                    List<Map<String, Object>> maps = kmxzlxMapper._queryPUBBMXXq(fzdm.substring(0, num));
                                    qc.add(maps.get(0).get("dwmc").toString());
                                }
                            }
                        }
                    }
                    qc.add(pageDataPUBKSZL.get(0).get("dwmc").toString());
                    dataPull.put("FZQC", String.join("/", qc));
                    dataPull = wuji(pageDataGL_Fzxlb, pageDataPUBKSZL.get(0).get("dwdm").toString(), dataPull);
                }
                resultList.add(dataPull);
                flag = 2;
            }
            if (pd.get("xmdm") != null && !StringUtils.isEmpty(pd.get("xmdm").toString())) {
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "项目");
                List<Map<String, Object>> pageDataGL_Xmzl = sourceMapper._queryGL_Xmzl(pd);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "1");
//                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");
                if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                    dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("1");
                    String fzlxjg = pageDataGL_Fzxlb.get("lbfj").toString();
                    String fzdm = pageDataGL_Xmzl.get(0).get("XMDM").toString();
                    List<String> qc = new ArrayList<String>();
                    if (!StringUtils.isEmpty(fzlxjg)) {
                        String[] fzlxjgStr = fzlxjg.split("-");
                        int num = 0;//3  3  2  2  111 111 11
                        if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                            for (int w = 0; w < fzlxjgStr.length; w++) {
                                num = num + Integer.valueOf(fzlxjgStr[w]);
                                if (num < fzdm.length()) {
                                    List<Map<String, Object>> maps = kmxzlxMapper._queryPUBBMXXq(fzdm.substring(0, num));
                                    qc.add(maps.get(0).get("XMMC").toString());
                                }
                            }
                        }
                    }
                    qc.add(pageDataGL_Xmzl.get(0).get("XMMC").toString());
                    dataPull.put("FZQC", String.join("/", qc));
                    dataPull = wuji(pageDataGL_Fzxlb, pageDataGL_Xmzl.get(0).get("XMDM").toString(), dataPull);
                }
                resultList.add(dataPull);
                flag = 2;
            }
            for (int q = 4; q < 31; q++) {
                if (pd.get("fzdm" + q) != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString())) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("fzdm", pd.get("fzdm" + q));
                    queryPd.put("lbdm", String.valueOf(q));
//                    List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get(String.valueOf(q));
                    List<Map<String, Object>> pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
                    dataPull.put("FZBM", "");
                    dataPull.put("FZMC", "");
                    dataPull.put("FZQC", "");
                    if (pageDataGL_Fzxlb != null) {
                        dataPull.put("FZLX", pageDataGL_Fzxlb.get("lbmc"));
                        dataPull.put("FZBM", pageDataGL_Fzxzl.get(0).get("fzdm"));
                        dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                        String fzqc = "";
                        if (pageDataGL_Fzxzl.get(0).get("fzdm") != null) {
                            String lbfj = pageDataGL_Fzxlb.get("lbfj").toString();
                            String[] lbfjStr = lbfj.split("-");
                            String result = pageDataGL_Fzxzl.get(0).get("fzdm").toString();
                            int num = 0;
                            for (int w = 0; w < lbfjStr.length; w++) {
                                num = num + Integer.valueOf(lbfjStr[w]);
                                if (num <= result.length()) {
                                    queryPd.put("fzdm", result.substring(0, num));
                                    queryPd.put("lbdm", String.valueOf(q));
                                    List<Map<String, Object>> pageDataGL_FzxzlQc = sourceMapper._queryGL_Fzxzl(queryPd);
                                    if (pageDataGL_FzxzlQc != null && pageDataGL_FzxzlQc.size() > 0) {
                                        fzqc += pageDataGL_FzxzlQc.get(0).get("fzmc") + "/";
                                    }
                                }
                            }
                        }
                        if (!StringUtils.isEmpty(fzqc)) {
                            fzqc = fzqc.substring(0, fzqc.length() - 1);
                        }
                        dataPull.put("FZQC", fzqc);
                        dataPull = wuji(pageDataGL_Fzxlb, pageDataGL_Fzxzl.get(0).get("fzdm").toString(), dataPull);
                    }
                    resultList.add(dataPull);
                    flag = 2;
                }
            }
//            if (flag == 1) {
//                resultList.add(dataPull);
//            }
            flag = 1;
            i++;
        }
//
//        Integer listNum = resultList.size();
//        Integer listnum2 = listNum % 50;
//        Integer listnum3 = listNum / 50;
//        Map map = new HashMap();
//        for (int p = 0; p < listnum3; p++) {
//            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
//            tragetMapper._addPzfzmx(map);
//        }
//        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
//        tragetMapper._addPzfzmx(map);
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                tragetMapper._addPzfzmx(map1);
            }
        }
        return "success";
    }

    public Map<String, Object> wuji(Map<String, Object> pageDataFzxlb, String result, Map<String, Object> dataPull) {
        if (pageDataFzxlb != null) {
            String lbfj = pageDataFzxlb.get("lbfj").toString();
            String[] lbfjStr = lbfj.split("-");
            Integer num = 0;
            for (int w = 0; w < lbfjStr.length; w++) {
                num = num + Integer.valueOf(lbfjStr[w]);
                if (num <= result.length()) {
                    switch (w) {
                        case 0:
                            dataPull.put("YJFZBM", result.substring(0, num));
                            break;
                        case 1:
                            dataPull.put("EJFZBM", result.substring(0, num));
                            break;
                        case 2:
                            dataPull.put("SJFZBM", result.substring(0, num));
                            break;
                        case 3:
                            dataPull.put("SIJFZBM", result.substring(0, num));
                            break;
                        case 4:
                            dataPull.put("WJFZBM", result.substring(0, num));
                            break;
                    }
                }
            }
        }
        return dataPull;
    }


    @RequestMapping(value = "fzncsGB")
    @ResponseBody
    public String fzncsGB(String KJDZZBBH) throws Exception {
        List<Map<String, Object>> GL_YebList = fzncsService.fzncs(KJDZZBBH);
        Map<String, Object> stringObjectMap = kjkmService._queryGL_Fzxlb1(KJDZZBBH);
        List<Map<String, Object>> pageDataGL_Ztcs = fzncsService.ZtcsStr(KJDZZBBH);
        List<Map<String, Object>> resultList = fzncsService.kmncsxx(KJDZZBBH, GL_YebList, stringObjectMap, pageDataGL_Ztcs);
        List<Map<String, Object>> resultListNew = fzncsService.FzncsResult(resultList, pageDataGL_Ztcs, KJDZZBBH);
        List<String> resultMapListStr = new ArrayList<String>();
        List<String> resultMapHaveListStr = new ArrayList<String>();
        List<Map<String, Object>> resultListNew2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultListNew2Have = new ArrayList<Map<String, Object>>();
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map<String, Object> map : resultListNew
            ) {
                if (!resultMapListStr.contains(map.get("KJDZZBBH") + "-" + map.get("FZLX") + "-" + map.get("KJKMBM") + "-" + map.get("FZBM"))) {
                    resultMapListStr.add(map.get("KJDZZBBH") + "-" + map.get("FZLX") + "-" + map.get("KJKMBM") + "-" + map.get("FZBM"));
                    resultListNew2.add(map);
                } else {
                    resultMapHaveListStr.add(map.get("KJDZZBBH") + "-" + map.get("FZLX") + "-" + map.get("KJKMBM") + "-" + map.get("FZBM"));
                    resultListNew2Have.add(map);
                }
            }
        }
        for (Map map3 : resultListNew2
        ) {
            for (Map map4 : resultListNew2Have
            ) {
                if ((map3.get("KJDZZBBH") + "-" + map3.get("FZLX") + "-" + map3.get("KJKMBM") + "-" + map3.get("FZBM")).equals(map4.get("KJDZZBBH") + "-" + map4.get("FZLX") + "-" + map4.get("KJKMBM") + "-" + map4.get("FZBM"))) {
                    map3.put("BBQCYE", new BigDecimal(map3.get("BBQCYE").toString()).add(new BigDecimal(map4.get("BBQCYE").toString())));
                    map3.put("QCSL", new BigDecimal(map3.get("QCSL").toString()).add(new BigDecimal(map4.get("QCSL").toString())));
                    map3.put("WBQCYE", new BigDecimal(map3.get("WBQCYE").toString()).add(new BigDecimal(map4.get("WBQCYE").toString())));
                }
            }
        }
        if (resultListNew2 != null && resultListNew2.size() > 0) {
            for (Map map1 : resultListNew2
            ) {
                tragetMapper._addFzncs(map1);
            }
        }

        return "success";
    }

    @RequestMapping(value = "pzfzmxGB")
    @ResponseBody
    public String pzfzmx(String KJDZZBBH) throws Exception {
        List<Map<String, Object>> bypznrList = pzfzmxService.pzfzmx(KJDZZBBH);
        Map<String, Object> stringObjectMap = kjkmService._queryGL_Fzxlb1(KJDZZBBH);
        List<Map<String, Object>> resultList = pzfzmxService.pzfzmxStr(KJDZZBBH, stringObjectMap, bypznrList);
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                tragetMapper._addPzfzmx(map1);
            }
        }
        return "success";
    }
}