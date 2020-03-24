package com.ky.dbbak.controller;

import com.ky.dbbak.mapper.FzlxMapper;
import com.ky.dbbak.mapper.FzxxMapper;
import com.ky.dbbak.mapper.FzyeMapper;
import com.ky.dbbak.mapper.KjkmMapper;
import com.ky.dbbak.sourcemapper.SourceMapper;
import com.ky.dbbak.sourcemapper.YebMapper;
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
@RequestMapping(value = "/fzlx/")
public class FzlxController {

    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;

    @Autowired
    YebMapper yebMapper;

    @Autowired
    FzyeMapper fzyeMapper;

    @RequestMapping(value = "fzlx")
    @ResponseBody
    public String fzlx(String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = sourceMapper._queryGL_Yeb(pageData);
        pageData.put("XZQHDM",XZQHDM);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
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
            for (int i = 0; i < 31; i++){
                dataPull.put("FZLXBM",i);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", i);
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
            tragetMapper._addFzlx(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        tragetMapper._addFzlx(map);
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
            dataPull.put("SFWYSFZ", 0);
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
                            }
                            dataPull.put("SJFZBM",result.substring(0, num-(Integer.valueOf(lbfjStr[lbfjStr.length]))));
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
            tragetMapper._addFzxx(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        tragetMapper._addFzxx(map);
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




    /*第五张——辅助余额表*/
    @RequestMapping(value = "Fzye")
    @ResponseBody
    public String Fzye(String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        //行政区域代码
        pageData.put("XZQHDM", XZQHDM);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> GL_YebList = sourceMapper._queryGL_Yeb(pageData);
        for (Map<String, Object> pd : GL_YebList
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
            //8、会计月份



            //9、会计体系
            dataPull.put("KJTX", pd.get("KJTXDM"));
            //10、会计科目编码
            dataPull.put("KJKMBM", pd.get("kmdm"));
            //11、会计科目名称
            List<Map<String, Object>> dataKmxx = sourceMapper._queryGL_KMXX(pd);
            dataPull.put("KJKMMC", dataKmxx.get(0).get("kmmc"));
            //12、会计科目全称



            //13、年初借方余额
            dataPull.put("NCJFYE",GL_YebList.get(0).get("ncj"));
            //14、年初贷方余额
            dataPull.put("NCJFYE",GL_YebList.get(0).get("ncd"));
            //15、年初余额方向
            int a = Integer.valueOf(GL_YebList.get(0).get("ncj").toString());
            int b = Integer.valueOf(GL_YebList.get(0).get("ncd").toString());
            if ( a > b){
                dataPull.put("NCYEFX",1);
            }else if (a==b){
                dataPull.put("NCYEFX",0);
            }else {
                dataPull.put("NCYEFX",-1);
            }
            int qcjfye= (int)GL_YebList.get(0).get("ncj") ;
            int qcdfye= (int)GL_YebList.get(0).get("ncd") ;
            for (int i=1 ; i <13;i++){
                //16、期初借方余额
                dataPull.put("QCJFYE",qcjfye);
                qcjfye+=(int)pd.get("yj"+i);
                //17、期初贷方余额
                dataPull.put("QCDFYE",qcdfye);
                qcdfye+=(int)pd.get("yd"+i);
                if (qcjfye>qcjfye){
                    //18、期初余额方向
                    dataPull.put("QCYEFX",1);
                }else if (qcjfye==qcjfye){
                    //18、期初余额方向
                    dataPull.put("QCYEFX",0);
                }else {
                    //18、期初余额方向
                    dataPull.put("QCYEFX",-1);
                }
            }


            //26、QCWBJFYE期初外币借方余额
            dataPull.put("QCWBJFYE",0);
            //27期初外币贷方余额
            dataPull.put("QCWBDFYE",0);
            //28.借方外币发生额
            dataPull.put("JFWBFSE",0);
            //29.贷方外币发生额
            dataPull.put("DFWBFSE",0);
            //30.期末外币借方余额
            dataPull.put("QMWBJFYE",0);
            //31.期末外币贷方余额
            dataPull.put("QMWBDFYE",0);
            //32.会计科目级别
            List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                Integer legth = pageDataGL_KMXX.get(0).get("kmdm").toString().length();
                switch (legth) {
                    case 4:
                        dataPull.put("KJKMJB", 1);
                        break;
                    case 6:
                        dataPull.put("KJKMJB", 2);
                        //35.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 4));
                        break;
                    case 8:
                        dataPull.put("KJKMJB", 3);;
                        //35.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 6));
                        break;
                    case 10:
                        dataPull.put("KJKMJB", 4);
                        //35.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 8));
                        break;
                    case 12:
                        dataPull.put("KJKMJB", 5);
                        //35.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 10));
                        break;
                }
                //33.是否最底级科目
                dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
            }
            //34.币种名称
            dataPull.put("BZMC", dzzbxxList.get(0).get("BWB"));

            //36.分录数
            //37.辅助类型
            //38.辅助编码
            //39.辅助名称
            //40.辅助级别
            //41.上级辅助编码


            //42.币种代码
            dataPull.put("BZDM", null);

        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            fzyeMapper._addFzye(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        fzyeMapper._addFzye(map);
        return "FZYE-辅助余额表生成完成";
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
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            dataPull.put("FZHSBZ",  null);
            dataPull.put("FZHSX",  null);
            dataPull.put("JLDWDM", null);
            dataPull.put("SFXJHXJDJW", 0);

            List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
            List<Map<String, Object>> pageDataGL_Yeb = yebMapper._queryYebKjnd(pd);
            //12.科目全称
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
                dataPull.put("KMQC", null);
            }
            List<Map<String, Object>>  dataKmxx1 = sourceMapper._queryKmxx();
            for (int j = 0; j<9;j++){
                dataPull.put("KMLBBH", dataKmxx1.get(j).get("lxdm"));
                dataPull.put("KMLBMC", dataKmxx1.get(j).get("lxmc"));
            }
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("D")) {
                    dataPull.put("YEFX", -1);
                }
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("J")) {
                    dataPull.put("YEFX", 1);
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
            dataPull.put("KJTX", pd.get("KJTXDM"));
            dataPull.put("KJKMBM", pd.get("kmdm"));
            List<Map<String, Object>> dataKmxx = sourceMapper._queryGL_KMXX(pd);
            dataPull.put("KJKMMC", dataKmxx.get(0).get("kmmc"));

        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            tragetMapper._addKjkm(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        tragetMapper._addKjkm(map);
        return "KJKM-会计科目表生成完成";
    }


}
