package com.ky.dbbak.controller;

import com.ky.dbbak.sourcemapper.SourceMapper;
import com.ky.dbbak.sourcemapper.YebMapper;
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
import java.util.Arrays;
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


    /*第五张——辅助余额表*/
    @RequestMapping(value = "fzye")
    @ResponseBody
    public String Fzye(String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        //行政区域代码
        pageData.put("XZQHDM", XZQHDM);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> GL_YebList = sourceMapper._queryGL_Yeb(pageData);
        resultList = doPackageData(dzzbxxList, GL_YebList, resultList);
        for (Map map :
                resultList) {
            fzyeMapper._add(map);
        }
        /*Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            fzyeMapper._addFzye(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        fzyeMapper._addFzye(map);*/
        return "FZYE-辅助余额表生成完成";
    }

    private List<Map<String, Object>> doPackageData(List<Map<String, Object>> dzzbxxList, List<Map<String, Object>> gl_yebList, List<Map<String, Object>> resultList) {
        for (Map<String, Object> pd : gl_yebList
        ) {
            Map<String, Object> dataPullBase = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            //从电子信息账簿表查询信息
            dataPullBase.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPullBase.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPullBase.put("KJND", datadzzbxx.get("KJND"));
            dataPullBase.put("DWMC", datadzzbxx.get("DWMC"));
            dataPullBase.put("DWDM", datadzzbxx.get("DWDM"));
            dataPullBase.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPullBase.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            dataPullBase.put("BZDM", null);
            //26、QCWBJFYE期初外币借方余额
            dataPullBase.put("QCWBJFYE", BigDecimal.ZERO);
            //27期初外币贷方余额
            dataPullBase.put("QCWBDFYE", BigDecimal.ZERO);
            //28.借方外币发生额
            dataPullBase.put("JFWBFSE", BigDecimal.ZERO);
            //29.贷方外币发生额
            dataPullBase.put("DFWBFSE", BigDecimal.ZERO);
            //30.期末外币借方余额
            dataPullBase.put("QMWBJFYE", BigDecimal.ZERO);
            //31.期末外币贷方余额
            dataPullBase.put("QMWBDFYE", BigDecimal.ZERO);
            //34.币种名称
            dataPullBase.put("BZMC", datadzzbxx.get("BWB"));
            //9、会计体系
            dataPullBase.put("KJTX", pd.get("KJTXDM"));
            //10、会计科目编码
            dataPullBase.put("KJKMBM", pd.get("kmdm"));
            //13、年初借方余额
            dataPullBase.put("NCJFYE", pd.get("ncj"));
            //14、年初贷方余额
            dataPullBase.put("NCDFYE", pd.get("ncd"));
            //36.分录数
            int fls = 0;
            for (int j = 1; j < 31; j++) {
                if (pd.get("fzdm" + j) != null && !StringUtils.isEmpty(pd.get("fzdm" + j).toString().trim())) {
                    fls += 1;
                }
            }
            dataPullBase.put("FLS", fls);
            dataPullBase = dealAmount(pd, dataPullBase);
            dataPullBase = dealKjkm(dataPullBase, pd);
            resultList = dealFz(pd, dataPullBase, resultList);
        }
        return resultList;
    }

    private Map<String, Object> dealAmount(Map<String, Object> pd, Map<String, Object> dataPullBase) {

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
            BigDecimal jfljfse = new BigDecimal("0");
            BigDecimal dfljfse = new BigDecimal("0");
            for (int j = 1; j <= i; j++) {
                jfljfse = jfljfse.add(new BigDecimal(pd.get("yj" + j).toString()));
                dfljfse = dfljfse.add(new BigDecimal(pd.get("yd" + j).toString()));
            }
            dataPullBase.put("JFLJFSE", jfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));
            //21.贷方发生额
            dataPullBase.put("DFFSE", ydi);
            dataPullBase.put("DFLJFSE", dfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));

            //23.期末借方余额
            //24.期末贷方余额
            //25.期末余额方向
            if (jfljfse.compareTo(dfljfse) == 1) {
                dataPullBase.put("QMJFYE", jfljfse.subtract(dfljfse));
                dataPullBase.put("QMDFYE", 0);
                dataPullBase.put("QMYEFX", 1);
            } else if (jfljfse.compareTo(dfljfse) == -1) {
                dataPullBase.put("QMJFYE", 0);
                dataPullBase.put("QMDFYE", dfljfse.subtract(jfljfse));
                dataPullBase.put("QMYEFX", -1);
            } else {
                dataPullBase.put("QMJFYE", 0);
                dataPullBase.put("QMDFYE", 0);
                dataPullBase.put("QMYEFX", 0);
            }
        }
        return dataPullBase;
    }

    private List<Map<String, Object>> dealFz(Map<String, Object> pd, Map<String, Object> dataPullBase, List<Map<String, Object>> resultList) {
        if (pd.get("fzdm0") != null && !StringUtils.isEmpty(pd.get("fzdm0").toString().trim())) {
            Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
            dataPull.put("FZLX", "部门");
            Map<String, Object> queryPd = new HashMap<String, Object>();
            queryPd.put("bmdm", pd.get("fzdm0"));
            List<Map<String, Object>> pageDataPUBBMXX = sourceMapper._queryPubbmxx(queryPd);
            queryPd.put("lbdm", "0");
            List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
            if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                dataPull.put("FZJB", pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                dataPull.put("SJFZBM", null);
            }
            resultList.add(dataPull);
        }
        if (pd.get("fzdm1") != null && !StringUtils.isEmpty(pd.get("fzdm1").toString().trim())) {
            Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
            dataPull.put("FZLX", "项目");
            Map<String, Object> queryPd = new HashMap<String, Object>();
            queryPd.put("xmdm", pd.get("fzdm1"));
            List<Map<String, Object>> pageDataGL_Xmzl = sourceMapper._queryGL_Xmzl(queryPd);
            queryPd.put("lbdm", "1");
            List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
            if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                dataPull.put("FZJB", pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                dataPull.put("SJFZBM", null);
            }
            resultList.add(dataPull);
        }
        if (pd.get("fzdm2") != null && !StringUtils.isEmpty(pd.get("fzdm2").toString().trim())) {

        }
        if (pd.get("fzdm3") != null && !StringUtils.isEmpty(pd.get("fzdm3").toString().trim())) {
            Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
            dataPull.put("FZLX", "往来单位");
            Map<String, Object> queryPd = new HashMap<String, Object>();
            queryPd.put("wldm", pd.get("fzdm3"));
            List<Map<String, Object>> pageDataPUBKSZL = sourceMapper._queryPUBKSZL(queryPd);
            queryPd.put("lbdm", "3");
            List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
            if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                dataPull.put("FZJB", pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                dataPull.put("SJFZBM", null);
            }
            resultList.add(dataPull);
        }
        for (int q = 4; q < 31; q++) {
            Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
            if (pd.get("fzdm" + q) != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString().trim())) {
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("fzdm", pd.get("fzdm" + q));
                queryPd.put("lbdm", String.valueOf(q));
                List<Map<String, Object>> pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                if (pageDataFzxlb != null && pageDataFzxlb.size() > 0) {
                    dataPull.put("FZLX", pageDataFzxlb.get(0).get("lbmc"));
                }
                if (pageDataGL_Fzxzl != null && pageDataGL_Fzxzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Fzxzl.get(0).get("fzdm"));
                    dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                    dataPull.put("FZJB", pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    String lbfj = pageDataFzxlb.get(0).get("lbfj").toString();
                    String[] lbfjStr = lbfj.split("-");
                    String result = pageDataGL_Fzxzl.get(0).get("fzdm").toString();
                    int num = 0;
                    for (int w = 0; w < lbfjStr.length; w++) {
                        num = num + Integer.valueOf(lbfjStr[w]);
                        if (num < result.length()) {
                            dataPull.put("SJFZBM", result.substring(0, num));
                        }
                    }
                }
                resultList.add(dataPull);
            }
        }
        return resultList;
    }

    private Map<String, Object> dealKjkm(Map<String, Object> dataPullBase, Map<String, Object> pd) {
        List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
        dataPullBase.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
        if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
            String kmdm = pageDataGL_KMXX.get(0).get("kmdm").toString();
            Integer legth = kmdm.length();
            switch (legth) {
                case 4:
                    dataPullBase.put("KJKMJB", 1);
                    break;
                case 6:
                    dataPullBase.put("KJKMJB", 2);
                    //35.上级科目编码
                    dataPullBase.put("SJKMBM", kmdm.substring(0, 4));
                    break;
                case 8:
                    dataPullBase.put("KJKMJB", 3);
                    //35.上级科目编码
                    dataPullBase.put("SJKMBM", kmdm.substring(0, 6));
                    break;
                case 10:
                    dataPullBase.put("KJKMJB", 4);
                    //35.上级科目编码
                    dataPullBase.put("SJKMBM", kmdm.substring(0, 8));
                    break;
                case 12:
                    dataPullBase.put("KJKMJB", 5);
                    //35.上级科目编码
                    dataPullBase.put("SJKMBM", kmdm.substring(0, 10));
                    break;
            }
            //33.是否最底级科目
            dataPullBase.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
        }
        String kjkmqc = "";
       /* if (pd.get("kmdm") != null) {
            //12、会计科目全称
            List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
            String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
            String[] lbfjStr = kmbmfa.split("-");
            String result = pd.get("kmdm").toString();
            int num = 0;
            for (int w = 0; w < lbfjStr.length; w++) {
                num = num + Integer.valueOf(lbfjStr[w]);
                if (num <= result.length()) {
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("kmdm", result.substring(0, num));
                    List<Map<String, Object>> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX(queryPd);
                    if (pageDataGL_KMXX1 != null && pageDataGL_KMXX1.size() > 0) {
                        kjkmqc += pageDataGL_KMXX1.get(0).get("kmmc") + "/";
                    }
                }
            }
        }*/

        if (pd.get("kmdm") != null) {
            //12、会计科目全称
            List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
            String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
            String[] lbfjStr = kmbmfa.split("-");
            String result = pd.get("kmdm").toString();
            int num = 0;
            List kmdms = new ArrayList();
            for (int w = 0; w < lbfjStr.length; w++) {
                num = num + Integer.valueOf(lbfjStr[w]);
                if (num <= result.length()) {
                    kmdms.add(result.substring(0, num));
                }
            }
            Map<String, Object> queryPd = new HashMap<String, Object>();
            queryPd.put("kmdms", kmdms);
            List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
            kjkmqc = String.join("/", pageDataGL_KMXX1);
        }
        dataPullBase.put("KJKMQC", kjkmqc);
        return dataPullBase;
    }


}
