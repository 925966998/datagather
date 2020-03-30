package com.ky.dbbak.controller;

import com.ky.dbbak.entity.FZLXEntity;
import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.krb5.internal.PAEncTSEnc;

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

    @Autowired
    KmxxMapper kmxxMapper;

    @Autowired
    KmxzlxMapper kmxzlxMapper;

    @Autowired
    ZtcsMapper ztcsMapper;

    @RequestMapping(value = "fzlx")
    @ResponseBody
    public String fzlx(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        //List<Map<String, Object>> bypznrList = sourceMapper._queryPznr(pageData);
        List<Map<String, Object>> bypznrList = sourceMapper._queryGL_Yeb(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        List<String> lbdmList = new ArrayList<String>();
        for (Map<String, Object> pd : bypznrList) {
            for (int i = 0; i < 31; i++) {

                if (pd.get(("fzdm" + String.valueOf(i))) != null && !StringUtils.isEmpty(pd.get(("fzdm" + String.valueOf(i))).toString().trim())) {
                    if (!lbdmList.contains((String.valueOf(i)))) {
                        lbdmList.add((String.valueOf(i)));
                    }
                }
            }
            /*
            if (pd.get("bmdm") != null && !StringUtils.isEmpty(pd.get("bmdm").toString().trim())) {
                if (!lbdmList.contains((String.valueOf(0)))) {
                    lbdmList.add((String.valueOf(0)));
                }
            }
            if (pd.get("wldm") != null && !StringUtils.isEmpty(pd.get("wldm").toString().trim())) {
                if (!lbdmList.contains((String.valueOf(3)))) {
                    lbdmList.add((String.valueOf(3)));
                }
            }
            if (pd.get("xmdm") != null && !StringUtils.isEmpty(pd.get("xmdm").toString().trim())) {
                if (!lbdmList.contains((String.valueOf(1)))) {
                    lbdmList.add((String.valueOf(1)));
                }
            }
            */
        }
        Map<String, Object> dataPull = new HashMap<String, Object>();
        Map<String, Object> dataPullBase = new HashMap<String, Object>();
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
        dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
        dataPull.put("KJND", datadzzbxx.get("KJND"));
        dataPull.put("DWMC", datadzzbxx.get("DWMC"));
        dataPull.put("DWDM", datadzzbxx.get("DWDM"));
        dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
        dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
        for (String str : lbdmList
        ) {
            dataPull = new HashMap<>(dataPull);
            Map<String, Object> queryPd = new HashMap<String, Object>();
            queryPd.put("lbdm", str);
            List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
            if (pageDataFzxlb != null && pageDataFzxlb.size() > 0) {
                dataPull.put("FZLXMC", pageDataFzxlb.get(0).get("lbmc"));
                dataPull.put("FZLXJG", pageDataFzxlb.get(0).get("lbfj"));
                dataPull.put("FZLXBM", pageDataFzxlb.get(0).get("lbdm"));
            }
            resultList.add(dataPull);
        }
//        Integer listNum = resultList.size();
//        Integer listnum2 = listNum % 50;
//        Integer listnum3 = listNum / 50;
//        Map map = new HashMap();
//        for (int p = 0; p < listnum3; p++) {
//            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
//            fzlxMapper._addFzlx(map);
//        }
//        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
//        fzlxMapper._addFzlx(map);
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                fzlxMapper._addFzlx(map1);
            }
        }
        return "FZLX-辅助类型表生成完成";
    }


    /* 辅助信息表*/
    @RequestMapping(value = "fzxx")
    @ResponseBody
    public String Fzxx(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> bypznrList = sourceMapper._queryPznr(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        List<String> lbdmList = new ArrayList<String>();
        Map<String, Object> dataPullBase = new HashMap<String, Object>();
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        dataPullBase.put("XZQHDM", datadzzbxx.get("XZQHDM"));
        dataPullBase.put("XZQHMC", datadzzbxx.get("XZQHMC"));
        dataPullBase.put("KJND", datadzzbxx.get("KJND"));
        dataPullBase.put("DWMC", datadzzbxx.get("DWMC"));
        dataPullBase.put("DWDM", datadzzbxx.get("DWDM"));
        dataPullBase.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
        dataPullBase.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
        dataPullBase.put("FZSM", " ");
        dataPullBase.put("SFWYSFZ", BigDecimal.ZERO);
        for (Map<String, Object> pd : bypznrList) {
            for (int i = 4; i < 31; i++) {
                if (pd.get(("fzdm" + String.valueOf(i))) != null && !StringUtils.isEmpty(pd.get(("fzdm" + String.valueOf(i))).toString().trim())) {
                        Map<String, Object> dataPull = new HashMap<String, Object>();
                        dataPull = new HashMap<String, Object>(dataPullBase);
                        Map<String, Object> queryPd = new HashMap<String, Object>();
                        queryPd.put("lbdm", String.valueOf(i));
                        List<Map<String, Object>> pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
                        dataPull.put("FZLX", pageDataFzxlb.get(0).get("lbmc"));
                        dataPull.put("FZBM", pd.get("fzdm" + String.valueOf(i)));
                        List<Map<String, Object>> pageDatapubfz = kmxzlxMapper._queryFzxzlq(pd.get("fzdm" + String.valueOf(i)).toString());
                        dataPull.put("FZMC", pageDatapubfz.get(0).get("fzmc"));

                        String lbfj = pageDataFzxlb.get(0).get("lbfj").toString();
                        String[] lbfjStr = lbfj.split("-");
                        String result = pd.get(("fzdm" + String.valueOf(i))).toString();
                        int num = 0;
                        String fzqc = "";
                        for (int w = 0; w < lbfjStr.length; w++) {
                            num = num + Integer.valueOf(lbfjStr[w]);
                            if (num <= result.length()) {
                                if (num == result.length()) {
                                    dataPull.put("SJFZBM", result.substring(0, (num - Integer.valueOf(lbfjStr[w]))));
                                    dataPull.put("FZJC", (w + 1));
                                }
                                queryPd.put("fzdm", result.substring(0, num));
                                List<Map<String, Object>> pageDataGL_FzxzlQc = sourceMapper._queryGL_Fzxzl(queryPd);
                                if (pageDataGL_FzxzlQc != null && pageDataGL_FzxzlQc.size() > 0) {
                                    fzqc += pageDataGL_FzxzlQc.get(0).get("fzmc") + "/";
                                }
                            }
                        }
                        if (!StringUtils.isEmpty(fzqc)) {
                            fzqc = fzqc.substring(0, fzqc.length() - 1);
                        }
                        dataPull.put("FZQC", fzqc);

                        resultList.add(dataPull);
                }
            }
            if (pd.get("bmdm") != null && !StringUtils.isEmpty(pd.get("bmdm").toString().trim())) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    dataPull.put("FZLX", "部门");
                    dataPull.put("FZBM", pd.get("bmdm"));
                    List<Map<String, Object>> pageDatapubXX = kmxzlxMapper._queryPUBBMXXq(pd.get("bmdm").toString());
                    dataPull.put("FZMC", pageDatapubXX.get(0).get("bmmc"));
                    dataPull.put("FZQC", pageDatapubXX.get(0).get("bmmc"));
                    dataPull.put("FZJC", 1);
                    dataPull.put("SJFZBM", " ");
                    resultList.add(dataPull);
            }
            if (pd.get("wldm") != null && !StringUtils.isEmpty(pd.get("wldm").toString().trim())) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    dataPull.put("FZLX", "往来单位");
                    dataPull.put("FZBM", pd.get("wldm"));
                    List<Map<String, Object>> pageDatawlXX = kmxzlxMapper._queryPUBKSZLq(pd.get("wldm").toString());
                    dataPull.put("FZMC", pageDatawlXX.get(0).get("bmmc"));
                    dataPull.put("FZQC", pageDatawlXX.get(0).get("bmmc"));
                    dataPull.put("FZJC", 1);
                    dataPull.put("SJFZBM", "");
                    resultList.add(dataPull);
            }
            if (pd.get("xmdm") != null && !StringUtils.isEmpty(pd.get("xmdm").toString().trim())) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    dataPull.put("FZLX", "项目");
                    dataPull.put("FZBM", pd.get("xmdm"));
                    List<Map<String, Object>> pageDataXMZL = kmxzlxMapper._queryXMZLq(pd.get("xmdm").toString());
                    dataPull.put("FZMC", pageDataXMZL.get(0).get("XMMC"));
                    dataPull.put("FZQC", pageDataXMZL.get(0).get("XMMC"));
                    dataPull.put("FZJC", 1);
                    dataPull.put("SJFZBM", " ");
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
        fzxxMapper._addFzxx(map);
        return "success";

    }


    /*会计科目表 */
    @RequestMapping(value = "kjkm")
    @ResponseBody
    public String kjkm(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> kjkmList = kmxzlxMapper._queryKjkmxx();
        for (Map<String, Object> kj : kjkmList
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
            //8.会计体系
            dataPull.put("KJTX", kj.get("KJTXDM"));
            //9.会计科目编码
            dataPull.put("KJKMBM", kj.get("kmdm"));
            List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(kj);
            dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
            //13.辅助核算标志
            dataPull.put("FZHSBZ", " ");
            //14.辅助核算项
            dataPull.put("FZHSX", " ");
            Integer kjkmjb = Integer.valueOf(((kj.get("kmdm").toString().length() - 4) / 2) + 1);
            dataPull.put("KJKMJC", kjkmjb);
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                //18.余额方向
                String yefx = pageDataGL_KMXX.get(0).get("yefx").toString();
                if(!yefx.equals("")  || !StringUtils.isEmpty(yefx)){
                    switch (yefx) {
                        case "J":
                            dataPull.put("YEFX", 1);
                            break;
                        case "D":
                            dataPull.put("YEFX", -1);
                            break;
                        default:
                            dataPull.put("YEFX", 0);
                            break;
                    }
                }
                //12.科目全称
                String kmdm = kj.get("kmdm").toString();
                String kjkmqc = "";
                if (!StringUtils.isEmpty(kmdm) && kmdm != null) {
                    if (kmdm.length() == 4) {
                        dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc"));
                        //15.上级科目编码
                        dataPull.put("SJKMBM", "");
                    } else {
                        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
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
                        //15.上级科目编码
                        String kmdm3 = kmdm.substring(0, kmdm.length() - 2);
                        dataPull.put("SJKMBM", kmdm3);
                    }
                }
            }
            List<Map<String, Object>> dataKmxx1 = sourceMapper._queryKmxx();
            //15.科目类别编号
            //16.科目类别名称
            dataPull.put("KMLBBH", kj.get("kmxz"));
            String lxdm1= kj.get("kmxz").toString();
            List<Map<String, Object>> _queryKMXZLX= kmxzlxMapper._queryGL_KMXZLX(lxdm1);
            dataPull.put("KMLBMC", _queryKMXZLX.get(0).get("lxmc"));
            //17.计量单位代码
            dataPull.put("JLDWDM", " ");
            //21.是否现金或现金等价物
            dataPull.put("SFXJHXJDJW", 0);
            resultList.add(dataPull);
        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            kjkmMapper._addKjkm(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        kjkmMapper._addKjkm(map);
        return "success";
    }

    @RequestMapping(value = "fzxxtwo")
    @ResponseBody
    public String Fzxxtwo(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        //List<Map<String, Object>> bypznrList = sourceMapper._queryPznr(pageData);
        List<FZLXEntity> fzlxEntityList = fzlxMapper._queryAll(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        List<String> lbdmList = new ArrayList<String>();
        Map<String, Object> dataPullBase = new HashMap<String, Object>();
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        dataPullBase.put("XZQHDM", datadzzbxx.get("XZQHDM"));
        dataPullBase.put("XZQHMC", datadzzbxx.get("XZQHMC"));
        dataPullBase.put("KJND", datadzzbxx.get("KJND"));
        dataPullBase.put("DWMC", datadzzbxx.get("DWMC"));
        dataPullBase.put("DWDM", datadzzbxx.get("DWDM"));
        dataPullBase.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
        dataPullBase.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
        dataPullBase.put("FZSM", " ");
        dataPullBase.put("SFWYSFZ", BigDecimal.ZERO);
        for (FZLXEntity fzlx : fzlxEntityList) {
            if (fzlx.getFZLXBM().equals("0")){
                List<Map<String, Object>> pageDatapubbmXX = kmxzlxMapper._queryPUBBMXX();
                for (Map<String, Object>  pubbmxx: pageDatapubbmXX) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    dataPull.put("FZLX", fzlx.getFZLXMC());
                    dataPull.put("FZBM", pubbmxx.get("bmdm"));
                    dataPull.put("FZMC", pubbmxx.get("bmmc"));
                    dataPull.put("FZQC", pubbmxx.get("bmmc"));
                    dataPull.put("FZJC", 1);
                    dataPull.put("SJFZBM", " ");
                    resultList.add(dataPull);
                }
            }else if (fzlx.getFZLXBM().equals("1")){
                List<Map<String, Object>> pageDataxmzl = kmxzlxMapper._queryGL_XMZL();
                for (Map<String, Object>  xmzl: pageDataxmzl) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    dataPull.put("FZLX", fzlx.getFZLXMC());
                    dataPull.put("FZBM", xmzl.get("XMDM"));
                    dataPull.put("FZMC", xmzl.get("XMMC"));
                    dataPull.put("FZQC", xmzl.get("XMMC"));
                    dataPull.put("FZJC", 1);
                    dataPull.put("SJFZBM", " ");
                    resultList.add(dataPull);
                }
            }else if (fzlx.getFZLXBM().equals("3")){
                List<Map<String, Object>> pageDataPubkszl = kmxzlxMapper._queryPUBKSZL();
                for (Map<String, Object>  pubkszl: pageDataPubkszl) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    dataPull.put("FZLX", fzlx.getFZLXMC());
                    dataPull.put("FZBM", pubkszl.get("dwdm"));
                    dataPull.put("FZMC", pubkszl.get("dwmc"));
                    dataPull.put("FZQC", pubkszl.get("dwmc"));
                    dataPull.put("FZJC", 1);
                    dataPull.put("SJFZBM", " ");
                    resultList.add(dataPull);
                }
            }else{
                Map<String, Object> dataFzxlb = new HashMap<String, Object>();
                dataFzxlb.put("lbdm",fzlx.getFZLXBM());
                List<Map<String, Object>> pageDataFzxzl = kmxzlxMapper._queryGL_Fzxzl(dataFzxlb);
                for (Map<String, Object>  fzxzl: pageDataFzxzl) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    dataPull.put("FZLX", fzlx.getFZLXMC());
                    dataPull.put("FZBM", fzxzl.get("fzdm"));
                    dataPull.put("FZMC", fzxzl.get("fzmc"));
                    //辅助全称
                    String fzdm = fzxzl.get("fzdm").toString();
                    String fzqc = "";
                    if (!StringUtils.isEmpty(fzdm) && fzdm != null) {
                        if (fzdm.length() <= 4) {
                            dataPull.put("FZQC", fzxzl.get("fzmc"));
                            dataPull.put("FZJC",1);
                            dataPull.put("SJFZBM"," ");
                            resultList.add(dataPull);
                        } else {
                            String fzlxjg = fzlx.getFZLXJG();
                            String[] lbfjStr = fzlxjg.split("-");
                            //String result = pd.get("kmdm").toString();
                            int num = 0;
                            List fzdms = new ArrayList();
                            for (int w = 0; w < lbfjStr.length; w++) {
                                num = num + Integer.valueOf(lbfjStr[w]);
                                if (num <= fzdm.length()) {
                                    fzdms.add(fzdm.substring(0, num));
                                }
                            }
                            Map<String, Object> queryPd = new HashMap<String, Object>();
                            queryPd.put("fzdms", fzdms);
                            List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_FZXX1(queryPd);
                            fzqc = String.join("/", pageDataGL_KMXX1);
                            dataPull.put("FZQC", fzqc);
                            //辅助级次
                            dataPull.put("FZJC", fzdms.size());
                            //上级辅助编码
                            dataPull.put("SJFZBM", fzdm.substring(0,fzdm.length()-2));
                            resultList.add(dataPull);
                        }
                    } else {
                        dataPull.put("KMQC", " ");
                    }
                    //resultList.add(dataPull);
                }
            }
            //resultList.add(dataPull);
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
        return "success";
    }
}
