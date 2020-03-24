package com.ky.dbbak.controller;

import com.ky.dbbak.mapper.FzyeMapper;
import com.ky.dbbak.sourcemapper.SourceMapper;
import com.ky.dbbak.sourcemapper.YebMapper;
import com.ky.dbbak.sourcemapper.ZtcsMapper;
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
@RequestMapping(value = "/fzye/")
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
            int qcjfye= (int)GL_YebList.get(0).get("ncj") ;
            int qcdfye= (int)GL_YebList.get(0).get("ncd") ;
            int jfljfse = 0;
            int dfljfse = 0;
            for (int i = 1; i < 13; i++) {
                if (!pd.get("yj" + i).toString().equals("0") && !StringUtils.isEmpty(pd.get("yj" + i).toString().trim()) &&
                        !pd.get("yd" + i).toString().equals("0") && !StringUtils.isEmpty(pd.get("yd" + i).toString().trim())
                ) {
                    //8.会计月份
                    dataPull.put("KJYF", i);
                }
                //9、会计体系
                dataPull.put("KJTX", pd.get("KJTXDM"));
                //10、会计科目编码
                dataPull.put("KJKMBM", pd.get("kmdm"));
                //11、会计科目名称
                List<Map<String, Object>> dataKmxx = sourceMapper._queryGL_KMXX(pd);
                dataPull.put("KJKMMC", dataKmxx.get(0).get("kmmc"));
                //12、会计科目全称
                List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
                List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
                String kjkmqc = "";
                if (pd.get("kmdm") != null) {
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
                }
                dataPull.put("KJKMQC", kjkmqc);

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

                //19.借方发生额
                int jffse = (int) pd.get("yj" + i);
                dataPull.put("JFFSE",jffse);
                //20.借方累计发生额
                jfljfse+=jffse;
                dataPull.put("JFLJFSE",jfljfse);
                //21.贷方发生额
                int dffse = (int) pd.get("yd" + i);
                dataPull.put("DFFSE",dffse);
                //22.贷方累计发生额
                dfljfse+=dffse;
                dataPull.put("DFLJFSE",dfljfse);

                //23.期末借方余额
                //24.期末贷方余额
                //25.期末余额方向
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
            }

            //26、QCWBJFYE期初外币借方余额
            dataPull.put("QCWBJFYE", BigDecimal.ZERO);
            //27期初外币贷方余额
            dataPull.put("QCWBDFYE", BigDecimal.ZERO);
            //28.借方外币发生额
            dataPull.put("JFWBFSE",  BigDecimal.ZERO);
            //29.贷方外币发生额
            dataPull.put("DFWBFSE",  BigDecimal.ZERO);
            //30.期末外币借方余额
            dataPull.put("QMWBJFYE", BigDecimal.ZERO);
            //31.期末外币贷方余额
            dataPull.put("QMWBDFYE", BigDecimal.ZERO);
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
            int fls = 0;
            for (int j = 1; j < 31; j++) {
                if (pd.get("fzdm" + j) != null && !StringUtils.isEmpty(pd.get("fzdm" + j).toString().trim())) {
                    fls+=1;
                }
            }
            dataPull.put("FLS",fls);
            //37.辅助类型
            //38.辅助编码
            //39.辅助名称
            //40.辅助级别
            //41.上级辅助编码
            if (pd.get("fzdm0") != null && !StringUtils.isEmpty(pd.get("fzdm0").toString().trim())) {
                dataPull.put("FZLX", "部门");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("bmdm", pd.get("fzdm0"));
                List<Map<String, Object>> pageDataPUBBMXX = sourceMapper._queryPubbmxx(queryPd);
                queryPd.put("lbdm",0);
                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                    dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                    dataPull.put("FZJB",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    dataPull.put("SJFZBM",null);
                }
                resultList.add(dataPull);
            }
            if (pd.get("fzdm1") != null && !StringUtils.isEmpty(pd.get("fzdm1").toString().trim())) {
                dataPull.put("FZLX", "项目");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("xmdm", pd.get("fzdm1"));
                List<Map<String, Object>> pageDataGL_Xmzl = sourceMapper._queryGL_Xmzl(queryPd);
                queryPd.put("lbdm",1);
                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                    dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                    dataPull.put("FZJB",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    dataPull.put("SJFZBM",null);
                }
                resultList.add(dataPull);
            }
            if (pd.get("fzdm2") != null && !StringUtils.isEmpty(pd.get("fzdm2").toString().trim())) {

            }
            if (pd.get("fzdm3") != null && !StringUtils.isEmpty(pd.get("fzdm3").toString().trim())) {
                dataPull.put("FZLX", "往来单位");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("wldm", pd.get("fzdm3"));
                List<Map<String, Object>> pageDataPUBKSZL = sourceMapper._queryPUBKSZL(queryPd);
                queryPd.put("lbdm", "3");
                List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                    dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                    dataPull.put("FZJB",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    dataPull.put("SJFZBM",null);
                }
                resultList.add(dataPull);
            }
            for (int q = 4; q < 31; q++) {
                if (pd.get("fzdm" + q) != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString().trim())) {
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("fzdm", pd.get("fzdm" + q));
                    queryPd.put("lbdm", String.valueOf(q));
                    List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                    List<Map<String, Object>> pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
                    if (pageDataFzxlb != null && pageDataFzxlb.size() > 0) {
                        dataPull.put("FZLX", pageDataFzxlb.get(0).get("lbmc"));
                    }
                    if (pageDataGL_Fzxzl != null && pageDataGL_Fzxzl.size() > 0) {
                        dataPull.put("FZBM", pageDataGL_Fzxzl.get(0).get("fzdm"));
                        dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                        dataPull.put("FZJB",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                        String lbfj = pageDataFzxlb.get(0).get("lbfj").toString();
                        String[] lbfjStr = lbfj.split("-");
                        String result = pageDataGL_Fzxzl.get(0).get("fzdm").toString();
                        int num = 0;
                        for (int w = 0; w < lbfjStr.length; w++) {
                            num = num + Integer.valueOf(lbfjStr[w]);
                            if (num < result.length()) {
                                dataPull.put("SJFZBM",result.substring(0, num));
                            }
                        }
                    }
                    resultList.add(dataPull);
                }
            }
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


}
