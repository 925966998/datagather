package com.ky.dbbak.controller;


import com.ky.dbbak.mapper.DbMapper;
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
@RequestMapping(value = "/db/")
public class DbController {

    @Autowired
    DbMapper dbMapper;

    @RequestMapping(value = "ysdw")
    @ResponseBody
    public String insert() throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> dzzbxxList = dbMapper._queryDzzbxx(pageData);
        dbMapper._add(dzzbxxList.get(0));
        return "YSDW-预算单位表生成完成";
    }

    @RequestMapping(value = "fzncs")
    @ResponseBody
    public String fzncs(Integer bid) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = dbMapper._queryGL_Yeb(pageData);
        List<Map<String, Object>> dzzbxxList = dbMapper._queryDzzbxx(pageData);
        for (Map<String, Object> pd : GL_YebList
        ) {
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            dataPull.put("BZMC", dzzbxxList.get(0).get("BWB"));
            dataPull.put("KJYF", 0);
            dataPull.put("BBQCYE", BigDecimal.ZERO);
            dataPull.put("QCSL", BigDecimal.ZERO);
            dataPull.put("WBQCYE", BigDecimal.ZERO);
            dataPull.put("KJKMBM", pd.get("kmdm"));
            List<Map<String, Object>> pageDataGL_KMXX = dbMapper._queryGL_KMXX(pd);
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

                Integer legth = pageDataGL_KMXX.get(0).get("kmdm").toString().length();
                switch (legth) {
                    case 4:
                        dataPull.put("KJKMJC", 1);
                        break;
                    case 6:
                        dataPull.put("KJKMJC", 2);
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 4));
                        break;
                    case 8:
                        dataPull.put("KJKMJC", 3);
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 6));
                        break;
                    case 10:
                        dataPull.put("KJKMJC", 4);
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 8));
                        break;
                    case 12:
                        dataPull.put("KJKMJC", 5);
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 10));
                        break;
                }
                dataPull.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));

            }
            if (pd.get("fzdm0") != null && !StringUtils.isEmpty(pd.get("fzdm0").toString().trim())) {
                dataPull.put("FZLX", "部门");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("bmdm", pd.get("fzdm0"));
                List<Map<String, Object>> pageDataPUBBMXX = dbMapper._queryPubbmxx(queryPd);
                if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                    dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                }
                resultList.add(dataPull);
            }
            if (pd.get("fzdm1") != null && !StringUtils.isEmpty(pd.get("fzdm1").toString().trim())) {
                dataPull.put("FZLX", "项目");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("xmdm", pd.get("fzdm1"));
                List<Map<String, Object>> pageDataGL_Xmzl = dbMapper._queryGL_Xmzl(queryPd);
                if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                    dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                }

                resultList.add(dataPull);
            }
            if (pd.get("fzdm2") != null && !StringUtils.isEmpty(pd.get("fzdm2").toString().trim())) {

            }
            if (pd.get("fzdm3") != null && !StringUtils.isEmpty(pd.get("fzdm3").toString().trim())) {
                dataPull.put("FZLX", "往来单位");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("wldm", pd.get("fzdm3"));
                List<Map<String, Object>> pageDataPUBKSZL = dbMapper._queryPUBKSZL(queryPd);
                if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                    dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                }
                resultList.add(dataPull);
            }
            for (int q = 4; q < 31; q++) {
                if (pd.get("fzdm" + q) != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString().trim())) {
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("fzdm", pd.get("fzdm" + q));
                    queryPd.put("lbdm", String.valueOf(q));
                    List<Map<String, Object>> pageDataFzxlb = dbMapper._queryGL_Fzxlb(queryPd);
                    List<Map<String, Object>> pageDataGL_Fzxzl = dbMapper._queryGL_Fzxzl(queryPd);
                    if (pageDataFzxlb != null && pageDataFzxlb.size() > 0) {
                        dataPull.put("FZLX", pageDataFzxlb.get(0).get("lbmc"));
                    }
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
//            dbMapper._addFzncs(map);
//        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            dbMapper._addFzncs(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        dbMapper._addFzncs(map);
//        Integer listNum = resultList.size();
//        Integer listnum2 = listNum % 50;
//        Integer listnum3 = listNum / 50;
//        for (int p = 0; p < listnum3; p++) {
//            twoDbService.insertFZNCSBatch(resultList.subList(p * 50, (p * 50 + 50)));
//        }
//        twoDbService.insertFZNCSBatch(resultList.subList(resultList.size() - listnum2, resultList.size()));
        return "FZNCS-辅助年初数表生成完成";
    }

    @RequestMapping(value = "pzfzmx")
    @ResponseBody
    public String index(Integer bid) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        pageData.put("id", bid);
        List<Map<String, Object>> bypznrList = dbMapper._queryPznr(pageData);
        List<Map<String, Object>> dzzbxxList = dbMapper._queryDzzbxx(pageData);
        int i = 1;
        int flag = 1;
        for (Map<String, Object> pd : bypznrList
        ) {
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            dataPull.put("BZ", "人民币");
            dataPull.put("WBJFFSE", BigDecimal.ZERO);
            dataPull.put("WBDFFSE", BigDecimal.ZERO);
            dataPull.put("HL", BigDecimal.ZERO);
            dataPull.put("SL", BigDecimal.ZERO);
            dataPull.put("DJ", BigDecimal.ZERO);
            if (pd.get("jdbz") != null && pd.get("jdbz").toString().equals("借")) {
                dataPull.put("JFFSE", BigDecimal.valueOf(Double.valueOf(pd.get("je").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
            } else if (pd.get("jdbz") != null && pd.get("jdbz").toString().equals("贷")) {
                dataPull.put("DFFSE", BigDecimal.valueOf(Double.valueOf(pd.get("je").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
            }

            String kjqj = pd.get("kjqj").toString();
            if (!StringUtils.isEmpty(kjqj)) {
                Integer mouth = Integer.valueOf(kjqj.substring(kjqj.length() - 2, kjqj.length()));
                dataPull.put("KJYF", mouth);
            }
            dataPull.put("PZLXBH", pd.get("PZLXDM"));
            dataPull.put("JZPZZL", pd.get("PZLXDM") + "凭证");
            dataPull.put("JZPZBH", pd.get("pzh"));
            dataPull.put("JZPZHH", pd.get("flh"));
            dataPull.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
                    + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
                    + "-" + pd.get("PZLXDM") + "-" + pd.get("pzh") + "-" + pd.get("flh") + "-" + pd.get("KJTXDM"));

            dataPull.put("JZPZZY", pd.get("zy"));
            dataPull.put("KJTX", pd.get("KJTXDM"));
            dataPull.put("KJKMBM", pd.get("kmdm"));
            List<Map<String, Object>> dataKmxx = dbMapper._queryGL_KMXX(pd);
            dataPull.put("KJKMMC", dataKmxx.get(0).get("kmmc"));
            //bmdm ---PUBBMXX
            //xmdm---GL_Xmzl
            //wldw---PUBKSZL
            if (pd.get("bmdm") != null && !StringUtils.isEmpty(pd.get("bmdm").toString())) {
                dataPull.put("FZLX", "部门");
                List<Map<String, Object>> pageDataPUBBMXX = dbMapper._queryPubbmxx(pd);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "0");
                List<Map<String, Object>> pageDataFzxlb = dbMapper._queryGL_Fzxlb(queryPd);
                if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                    dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                    dataPull.put("FZQC", pageDataPUBBMXX.get(0).get("bmmc"));
                    dataPull = wuji(pageDataFzxlb, pageDataPUBBMXX.get(0).get("bmdm").toString(), dataPull);
                }
                resultList.add(dataPull);
                flag = 2;
            }
            if (pd.get("wldm") != null && !StringUtils.isEmpty(pd.get("wldm").toString())) {
                dataPull.put("FZLX", "往来单位");
                List<Map<String, Object>> pageDataPUBKSZL = dbMapper._queryPUBKSZL(pd);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "3");
                List<Map<String, Object>> pageDataFzxlb = dbMapper._queryGL_Fzxlb(queryPd);
                if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                    dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                    dataPull.put("FZQC", pageDataPUBKSZL.get(0).get("dwqc"));
                    dataPull = wuji(pageDataFzxlb, pageDataPUBKSZL.get(0).get("dwdm").toString(), dataPull);
                }
                resultList.add(dataPull);
                flag = 2;
            }
            if (pd.get("xmdm") != null && !StringUtils.isEmpty(pd.get("xmdm").toString())) {
                dataPull.put("FZLX", "项目");
                List<Map<String, Object>> pageDataGL_Xmzl = dbMapper._queryGL_Xmzl(pd);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "1");
                List<Map<String, Object>> pageDataFzxlb = dbMapper._queryGL_Fzxlb(queryPd);
                if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                    dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                    dataPull.put("FZQC", pageDataGL_Xmzl.get(0).get("XMQC"));
                    dataPull = wuji(pageDataFzxlb, pageDataGL_Xmzl.get(0).get("XMDM").toString(), dataPull);
                }
                resultList.add(dataPull);
                flag = 2;
            }
            for (int q = 4; q < 31; q++) {
                if (pd.get("fzdm" + q) != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString())) {
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("fzdm", pd.get("fzdm" + q));
                    queryPd.put("lbdm", String.valueOf(q));
                    List<Map<String, Object>> pageDataFzxlb = dbMapper._queryGL_Fzxlb(queryPd);
                    List<Map<String, Object>> pageDataGL_Fzxzl = dbMapper._queryGL_Fzxzl(queryPd);
                    if (pageDataFzxlb != null && pageDataFzxlb.size() > 0) {
                        dataPull.put("FZLX", pageDataFzxlb.get(0).get("lbmc"));
                        dataPull.put("FZBM", pageDataGL_Fzxzl.get(0).get("fzdm"));
                        dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                        String fzqc = "";
                        if (pageDataGL_Fzxzl.get(0).get("fzdm") != null) {
                            String lbfj = pageDataFzxlb.get(0).get("lbfj").toString();
                            String[] lbfjStr = lbfj.split("-");
                            String result = pageDataGL_Fzxzl.get(0).get("fzdm").toString();
                            int num = 0;
                            for (int w = 0; w < lbfjStr.length; w++) {
                                num = num + Integer.valueOf(lbfjStr[w]);
                                if (num <= result.length()) {
                                    queryPd.put("fzdm", result.substring(0, num));
                                    queryPd.put("lbdm", String.valueOf(q));
                                    List<Map<String, Object>> pageDataGL_FzxzlQc = dbMapper._queryGL_Fzxzl(queryPd);
                                    if (pageDataGL_FzxzlQc != null && pageDataGL_FzxzlQc.size() > 0) {
                                        fzqc += pageDataGL_FzxzlQc.get(0).get("fzmc") + "/";
                                    }
                                }
                            }
                        }
                        dataPull.put("FZQC", fzqc);
                        dataPull = wuji(pageDataFzxlb, pageDataGL_Fzxzl.get(0).get("fzdm").toString(), dataPull);
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

        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            dbMapper._addPzfzmx(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        dbMapper._addPzfzmx(map);
        return "PZFZMX-凭证辅助明细表生成完成";
    }

    public Map<String, Object> wuji(List<Map<String, Object>> pageDataFzxlb, String result, Map<String, Object> dataPull) {
        String lbfj = pageDataFzxlb.get(0).get("lbfj").toString();
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
        return dataPull;
    }

//
//    String lbfj = pageDataFzxlb.get(0).get("lbfj").toString();
//    String[] lbfjStr = lbfj.split("-");
//    Integer num = 0;
//                for(
//    int w = 0;
//    w<lbfjStr.length;w++)
//
//    {
//        num = num + Integer.valueOf(lbfjStr[w]);
//        if (num <= pageDataPUBBMXX.get(0).get("bmdm").toString().length()) {
//            switch (w) {
//                case 0:
//                    dataPull.put("YJFZBM", pageDataPUBBMXX.get(0).get("bmdm").toString().substring(0, num));
//                case 1:
//                    dataPull.put("EJFZBM", pageDataPUBBMXX.get(0).get("bmdm").toString().substring(0, num));
//                case 2:
//                    dataPull.put("SJFZBM", pageDataPUBBMXX.get(0).get("bmdm").toString().substring(0, num));
//                case 3:
//                    dataPull.put("SIJFZBM", pageDataPUBBMXX.get(0).get("bmdm").toString().substring(0, num));
//                case 4:
//                    dataPull.put("WJFZBM", pageDataPUBBMXX.get(0).get("bmdm").toString().substring(0, num));
//            }
//        }
//    }
}