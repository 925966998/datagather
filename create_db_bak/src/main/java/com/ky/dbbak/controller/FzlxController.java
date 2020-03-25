package com.ky.dbbak.controller;

import com.ky.dbbak.sourcemapper.SourceMapper;
import com.ky.dbbak.sourcemapper.YebMapper;
import com.ky.dbbak.targetmapper.*;
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
@RequestMapping(value = "/ky-datagather/fzlx/")
public class FzlxController {

    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;

    @Autowired
    YebMapper yebMapper;

    @Autowired
    FzyeMapper fzyeMapper;

    @Autowired
    FzlxMapper fzlxMapper;

    @Autowired
    FzxxMapper fzxxMapper;

    @Autowired
    KjkmMapper kjkmMapper;

    @RequestMapping(value = "fzlx")
    @ResponseBody
    public String fzlx(String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = sourceMapper._queryGL_Yeb(pageData);
        pageData.put("XZQHDM",XZQHDM);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        for (Map<String, Object> pd:GL_YebList) {
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            for (int i = 0; i < 31; i++){
                dataPull.put("FZLXBM",i+"");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", i+"");
                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                if (pageDataFzxlb != null && pageDataFzxlb.size() > 0) {
                    dataPull.put("FZLXMC", pageDataFzxlb.get(0).get("lbmc"));
                    dataPull.put("FZLXJG", pageDataFzxlb.get(0).get("lbfj"));
                }
                resultList.add(dataPull);
            }
        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            fzlxMapper._addFzlx(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        fzlxMapper._addFzlx(map);
        return "FZLX-辅助类型表生成完成";
    }




    /* 辅助信息表*/
    @RequestMapping(value = "fzxx")
    @ResponseBody
    public String Fzxx(String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> bypznrList = sourceMapper._queryPznr(pageData);
        pageData.put("XZQHDM", XZQHDM);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
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
            dataPull.put("FZSM", null);
            dataPull.put("SFWYSFZ", BigDecimal.ZERO);
            if (pd.get("bmdm") != null && !StringUtils.isEmpty(pd.get("bmdm").toString())) {
                dataPull.put("FZLX", "部门");
                List<Map<String, Object>> pageDataPUBBMXX = sourceMapper._queryPubbmxx(pd);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "0");
                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                    dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                    dataPull.put("FZQC", pageDataPUBBMXX.get(0).get("bmmc"));
                    dataPull = wuji(pageDataFzxlb, pageDataPUBBMXX.get(0).get("bmdm").toString(), dataPull);
                    dataPull.put("FZJC",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    dataPull.put("SJFZBM",null);
                }
                resultList.add(dataPull);
                flag = 2;
            }

            if (pd.get("xmdm") != null && !StringUtils.isEmpty(pd.get("xmdm").toString())) {
                dataPull.put("FZLX", "项目");
                List<Map<String, Object>> pageDataGL_Xmzl = sourceMapper._queryGL_Xmzl(pd);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "1");
                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                    dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                    dataPull.put("FZQC", pageDataGL_Xmzl.get(0).get("XMQC"));
                    dataPull = wuji(pageDataFzxlb, pageDataGL_Xmzl.get(0).get("XMDM").toString(), dataPull);
                    dataPull.put("FZJC",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    dataPull.put("SJFZBM",null);
                }
                resultList.add(dataPull);
                flag = 2;
            }
            if (pd.get("wldm") != null && !StringUtils.isEmpty(pd.get("wldm").toString())) {
                dataPull.put("FZLX", "往来单位");
                List<Map<String, Object>> pageDataPUBKSZL = sourceMapper._queryPUBKSZL(pd);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "3");
                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                    dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                    dataPull.put("FZQC", pageDataPUBKSZL.get(0).get("dwqc"));
                    dataPull = wuji(pageDataFzxlb, pageDataPUBKSZL.get(0).get("dwdm").toString(), dataPull);
                    dataPull.put("FZJC",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    dataPull.put("SJFZBM",null);
                }
                resultList.add(dataPull);
                flag = 2;
            }
            for (int q = 4; q < 31; q++) {
                if (pd.get("fzdm" + q) != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString())) {
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("fzdm", pd.get("fzdm" + q));
                    queryPd.put("lbdm", String.valueOf(q));
                    List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                    List<Map<String, Object>> pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
                    if (pageDataFzxlb != null && pageDataFzxlb.size() > 0) {
                        dataPull.put("FZLX", pageDataFzxlb.get(0).get("lbmc"));
                        dataPull.put("FZBM", pageDataGL_Fzxzl.get(0).get("fzdm"));
                        dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                        dataPull.put("FZJC",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
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
                                    List<Map<String, Object>> pageDataGL_FzxzlQc = sourceMapper._queryGL_Fzxzl(queryPd);
                                    if (pageDataGL_FzxzlQc != null && pageDataGL_FzxzlQc.size() > 0) {
                                        fzqc += pageDataGL_FzxzlQc.get(0).get("fzmc") + "/";
                                    }
                                }
                                if (num < result.length()) {
                                    dataPull.put("SJFZBM",result.substring(0, num));
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

        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            fzxxMapper._addFzxx(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        fzxxMapper._addFzxx(map);
        return "FZLX-辅助信息表生成完成";
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


    /*会计科目表 */
    @RequestMapping(value = "kjkm")
    @ResponseBody
    public String kjkm(String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        pageData.put("XZQHDM", XZQHDM);
        List<Map<String, Object>> bypznrList = sourceMapper._queryPznr(pageData);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        for (Map<String, Object> pd : bypznrList
        ) {
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
            //8.会计体系
            List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
            List<Map<String, Object>>  dataKmxx1 = sourceMapper._queryKmxx();
            //9.会计科目编码
            dataPull.put("KJKMBM", pd.get("kmdm"));
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                //10.会计科目名称
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("D")) {
                    //18.余额方向
                    dataPull.put("YEFX", -1);
                }
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("J")) {
                    //18.余额方向
                    dataPull.put("YEFX", 1);
                }
                Integer legth = pageDataGL_KMXX.get(0).get("kmdm").toString().length();
                switch (legth) {
                    case 4:
                        //12.会计科目级次
                        dataPull.put("KJKMJC", 1);
                        //19.上级科目编码
                        dataPull.put("SJKMBM", "");
                        break;
                    case 6:
                        //12.会计科目级次
                        dataPull.put("KJKMJC", 2);
                        //19.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 4));
                        break;
                    case 8:
                        //12.会计科目级次
                        dataPull.put("KJKMJC", 3);
                        //19.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 6));
                        break;
                    case 10:
                        //12.会计科目级次
                        dataPull.put("KJKMJC", 4);
                        //19.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 8));
                        break;
                    case 12:
                        //12.会计科目级次
                        dataPull.put("KJKMJC", 5);
                        //19.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 10));
                        break;
                }
                //8.会计体系
                dataPull.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                //20.是否最底级科目
                dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
            }
            //11.科目全称
            String kmdm = pd.get("kmdm").toString();
            if (!com.alibaba.druid.util.StringUtils.isEmpty(kmdm)) {
                if (kmdm.length() == 4) {
                    dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc"));
                } else {
                    StringBuilder builderKmqc = new StringBuilder();
                    Integer kmdm2 = Integer.valueOf(kmdm.substring(0, 4));
                    builderKmqc.append(pageDataGL_KMXX.get(0).get(kmdm2));
                    while (kmdm2 > 0) {
                        builderKmqc.append("/" + pageDataGL_KMXX.get(0).get(kmdm2));
                        kmdm2 = kmdm2 - 2;
                    }
                    dataPull.put("KMQC", builderKmqc);
                }
            } else {
                dataPull.put("KMQC", "");
            }
            //13.辅助核算标志
            dataPull.put("FZHSBZ",  "");
            //14.辅助核算项
            dataPull.put("FZHSX",  "");
            //15.科目类别编号
            //16.科目类别名称
            dataPull.put("KMLBBH", dataKmxx1.get(0).get("lxdm"));
            dataPull.put("KMLBMC", dataKmxx1.get(0).get("lxmc"));

            //17.计量单位代码
            dataPull.put("JLDWDM", "");
            //21.是否现金或现金等价物
            dataPull.put("SFXJHXJDJW", 0);
        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        kjkmMapper._addKjkm(map);
        return "KJKM-会计科目表生成完成";
    }


}
