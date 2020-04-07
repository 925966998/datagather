package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSON;
import com.ky.dbbak.entity.FZLXEntity;
import com.ky.dbbak.service.FzlxService;
import com.ky.dbbak.service.FzxlbService;
import com.ky.dbbak.service.FzyeService;
import com.ky.dbbak.service.KjkmService;
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

    @Autowired
    FzxlbService fzxlbService;

    @Autowired
    FzyeService fzyeService;
    @Autowired
    FzlxService fzlxService;

    @Autowired
    KjkmService kjkmService;
    @RequestMapping(value = "fzlx")
    @ResponseBody
    public String fzlx(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
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
        }
        Map<String, Object> dataPull = new HashMap<String, Object>();
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
        dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
        dataPull.put("KJND", datadzzbxx.get("KJND"));
        dataPull.put("DWMC", datadzzbxx.get("DWMC"));
        dataPull.put("DWDM", datadzzbxx.get("DWDM"));
        dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
        dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
        dataPull.put("FZLXMC", " ");
        dataPull.put("FZLXJG", " ");
        dataPull.put("FZLXBM", " ");
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
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                fzlxMapper._addFzlx(map1);
            }
        }
        return "FZLX-辅助类型表生成完成";
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
        Map<String, Object> stringObjectMap = fzxlbService._queryGL_Fzxlb1(pageData);
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
            //15.科目类别编号
            //16.科目类别名称
            dataPull.put("KMLBBH", kj.get("kmxz"));
            String lxdm1 = kj.get("kmxz").toString();
            List<Map<String, Object>> _queryKMXZLX = kmxzlxMapper._queryKMXZLX(lxdm1);
            dataPull.put("KMLBMC", _queryKMXZLX.get(0).get("lxmc"));
            //17.计量单位代码
            dataPull.put("JLDWDM", " ");
            //21.是否现金或现金等价物
            dataPull.put("SFXJHXJDJW", 0);
            List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(kj);
            dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
            //13.辅助核算标志
            dataPull.put("FZHSBZ", " ");
            //14.辅助核算项
            dataPull.put("FZHSX", " ");
            if (kj.get("kmdm").toString().length() >= 4) {
                String substring = kj.get("kmdm").toString().substring(0, 4);
                //16.是否现金或现金等价物  赋值0
                if (substring.equals("1001") || substring.equals("1002") || substring.equals("1011") || substring.equals("1021")) {
                    dataPull.put("SFXJHXJDJW", 1);
                } else {
                    dataPull.put("SFXJHXJDJW", 0);
                }
            }
            int a = kj.get("fzhs").toString().length();
            if (a > 1) {
                dataPull.put("FZHSBZ", 1);
            } else {
                dataPull.put("FZHSBZ", 0);
            }
            String[] fzhsbz = kj.get("fzhs").toString().split(",");
            System.out.println(fzhsbz.length);
            if (fzhsbz.length > 1) {
                String fzhsx = "";
                for (int x = 1; x < fzhsbz.length; x++) {
                    if (!StringUtils.isEmpty(fzhsbz[x])) {
                        String lbdm = fzhsbz[x];
                        System.out.println(lbdm);
                        if (stringObjectMap.get(lbdm) != null) {
                            Map<String, Object> o = (Map<String, Object>) stringObjectMap.get(lbdm);
                            fzhsx += o.get("lbmc").toString().trim() + ",";
                        }
                    }
                }
                if (!StringUtils.isEmpty(fzhsx)) {
                    fzhsx = fzhsx.substring(0, fzhsx.length() - 1);
                    fzhsx = fzhsx.replace("　", "");
                    dataPull.put("FZHSX", fzhsx.trim());
                }else{
                    dataPull.put("FZHSX", " ");
                }
            } else {
                dataPull.put("FZHSX", " ");
            }
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                //18.余额方向
                String yefx = pageDataGL_KMXX.get(0).get("yefx").toString();
                if (!yefx.equals("") || !StringUtils.isEmpty(yefx)) {
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
                List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
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
            resultList.add(dataPull);
        }
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                kjkmMapper._add(map1);
            }
        }
        return "success";
    }

    @RequestMapping(value = "fzxxtwo")
    @ResponseBody
    public String Fzxxtwo(String KJDZZBBH) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<String> lbdmList = new ArrayList<String>();
        List<FZLXEntity> fzlxEntityList = fzlxMapper._queryAll(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
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
        dataPullBase.put("SFWYSFZ", BigDecimal.ONE);
        dataPullBase.put("FZLX", " ");
        dataPullBase.put("FZBM", " ");
        dataPullBase.put("FZMC", " ");
        dataPullBase.put("FZQC", " ");
        dataPullBase.put("FZJC", 0);
        dataPullBase.put("SJFZBM", " ");
        for (FZLXEntity fzlx : fzlxEntityList) {
            if (fzlx.getFZLXBM().equals("0")) {
                List<Map<String, Object>> pageDatapubbmXX = kmxzlxMapper._queryPUBBMXX();
                for (Map<String, Object> pubbmxx : pageDatapubbmXX) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    if (!lbdmList.contains(fzlx.getFZLXBM() + "-" + pubbmxx.get("bmdm"))) {
                        lbdmList.add(fzlx.getFZLXBM() + "-" + pubbmxx.get("bmdm"));
                        dataPull.put("FZLX", fzlx.getFZLXMC());
                        dataPull.put("FZBM", pubbmxx.get("bmdm"));
                        dataPull.put("FZMC", pubbmxx.get("bmmc"));
                        dataPull.put("FZQC", pubbmxx.get("bmmc"));
                        dataPull.put("FZJC", 1);
                        dataPull.put("SJFZBM", " ");
                        resultList.add(dataPull);
                    }
                }
            } else if (fzlx.getFZLXBM().equals("1")) {
                List<Map<String, Object>> pageDataxmzl = kmxzlxMapper._queryGL_XMZL();
                for (Map<String, Object> xmzl : pageDataxmzl) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    if (!lbdmList.contains(fzlx.getFZLXBM() + "-" + xmzl.get("XMDM"))) {
                        lbdmList.add(fzlx.getFZLXBM() + "-" + xmzl.get("XMDM"));
                        dataPull.put("FZLX", fzlx.getFZLXMC());
                        dataPull.put("FZBM", xmzl.get("XMDM"));
                        dataPull.put("FZMC", xmzl.get("XMMC"));
                        dataPull.put("FZQC", xmzl.get("XMMC"));
                        dataPull.put("FZJC", 1);
                        dataPull.put("SJFZBM", " ");
                        resultList.add(dataPull);
                    }
                }
            } else if (fzlx.getFZLXBM().equals("3")) {
                List<Map<String, Object>> pageDataPubkszl = kmxzlxMapper._queryPUBKSZL();
                for (Map<String, Object> pubkszl : pageDataPubkszl) {
                    if (!lbdmList.contains(fzlx.getFZLXBM() + "-" + pubkszl.get("dwdm"))) {
                        Map<String, Object> dataPull = new HashMap<String, Object>();
                        dataPull = new HashMap<String, Object>(dataPullBase);
                        lbdmList.add(fzlx.getFZLXBM() + "-" + pubkszl.get("dwdm"));
                        dataPull.put("FZLX", fzlx.getFZLXMC());
                        dataPull.put("FZBM", pubkszl.get("dwdm"));
                        dataPull.put("FZMC", pubkszl.get("dwmc"));
                        dataPull.put("FZQC", pubkszl.get("dwmc"));
                        dataPull.put("FZJC", 1);
                        dataPull.put("SJFZBM", " ");
                        resultList.add(dataPull);
                    }
                }
            } else {
                Map<String, Object> dataFzxlb = new HashMap<String, Object>();
                dataFzxlb.put("lbdm", fzlx.getFZLXBM());
                List<Map<String, Object>> pageDataFzxzl = kmxzlxMapper._queryGL_Fzxzl(dataFzxlb);
                if (pageDataFzxzl.size() > 0 && pageDataFzxzl != null) {

                    for (Map<String, Object> fzxzl : pageDataFzxzl) {
                        Map<String, Object> dataPull = new HashMap<String, Object>();
                        dataPull = new HashMap<String, Object>(dataPullBase);
                        if (!lbdmList.contains(fzlx.getFZLXBM() + "-" + fzxzl.get("fzdm"))) {
                            lbdmList.add(fzlx.getFZLXBM() + "-" + fzxzl.get("fzdm"));
                            dataPull.put("FZLX", fzlx.getFZLXMC());
                            dataPull.put("FZBM", fzxzl.get("fzdm"));
                            dataPull.put("FZMC", fzxzl.get("fzmc"));
                            String fzlxjg = fzlx.getFZLXJG();
                            String fzdm = fzxzl.get("fzdm").toString();
                            int jc = 1;
                            List<String> qcList = new ArrayList<String>();
                            String sjdm = "";
                            if (!StringUtils.isEmpty(fzlxjg)) {
                                String[] fzlxjgStr = fzlxjg.split("-");
                                int num = 0;//3  3  2  2  111 111 11
                                if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                    for (int w = 0; w < fzlxjgStr.length; w++) {
                                        num = num + Integer.valueOf(fzlxjgStr[w]);

                                        if (num < fzdm.length()) {
                                            if (!lbdmList.contains(fzlx.getFZLXBM() + "-" + fzdm.substring(0, num))) {
                                                lbdmList.add(fzlx.getFZLXBM() + "-" + fzdm.substring(0, num));
                                                Map<String, Object> dataPullCh = new HashMap<String, Object>(dataPullBase);
                                                Map<String, Object> queryPd = new HashMap<String, Object>();
                                                queryPd.put("fzdm", fzdm.substring(0, num));
                                                queryPd.put("lbdm", fzlx.getFZLXBM());
                                                List<Map<String, Object>> pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
                                                if (w == 0) {
                                                    dataPullCh.put("SJFZBM", " ");
                                                } else {
                                                    dataPullCh.put("SJFZBM", fzdm.substring(0, num - Integer.valueOf(fzlxjgStr[w])));
                                                }
                                                dataPullCh.put("FZJC", jc);
                                                dataPullCh.put("FZLX", fzlx.getFZLXMC());
                                                dataPullCh.put("FZBM", pageDataGL_Fzxzl.get(0).get("fzdm"));
                                                dataPullCh.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                                                qcList.add(pageDataGL_Fzxzl.get(0).get("fzmc").toString());
                                                dataPullCh.put("FZQC", String.join("/", qcList));
                                                resultList.add(dataPullCh);
                                            } else {
                                                Map<String, Object> queryPd = new HashMap<String, Object>();
                                                queryPd.put("fzdm", fzdm.substring(0, num));
                                                queryPd.put("lbdm", fzlx.getFZLXBM());
                                                List<Map<String, Object>> pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
                                                qcList.add(pageDataGL_Fzxzl.get(0).get("fzmc").toString());
                                            }
                                            jc++;
                                        } else if (num == fzdm.length()) {
                                            sjdm = fzdm.substring(0, num - Integer.valueOf(fzlxjgStr[w]));
                                            break;
                                        }
                                    }
                                }
                            }
                            dataPull.put("SJFZBM", sjdm);
                            dataPull.put("FZJC", jc);
                            if (qcList != null && qcList.size() > 0) {
                                dataPull.put("FZQC", String.join("/", qcList) + "/" + fzxzl.get("fzmc"));
                            } else {
                                dataPull.put("FZQC", fzxzl.get("fzmc"));
                            }
                            resultList.add(dataPull);
                        }

                    }
                }
            }
        }
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                fzxxMapper._add(map1);

            }
        }
        return "success";
    }



    @RequestMapping(value = "fzlxGB")
    @ResponseBody
    public String fzlx_G(String KJDZZBBH)throws Exception {
        List<String> lbdmList = fzlxService.Fzlx(KJDZZBBH);
        List<Map<String, Object>> resultList = fzlxService.FzlxStr(lbdmList,KJDZZBBH);
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                try {
                    fzlxMapper._addFzlx(map1);
                }catch (Exception e){
                    System.out.println(map1);
                }
            }
        }
        return "FZLX-辅助类型表生成完成";
    }


    /*会计科目表 */
    @RequestMapping(value = "kjkmGB")
    @ResponseBody
    public String kjkm_G(String KJDZZBBH) throws Exception {
        List<Map<String, Object>> kjkmList = kjkmService.Kjkm(KJDZZBBH) ;
        Map<String, Object> stringObjectMap = kjkmService._queryGL_Fzxlb1(KJDZZBBH);
        List<Map<String, Object>> resultList = kjkmService.Kjkmxx(KJDZZBBH ,kjkmList,stringObjectMap);
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                kjkmMapper._add(map1);
            }
        }
        return "success";
    }

    @RequestMapping(value = "fzxxtwoGB")
    @ResponseBody
    public String Fzxxtwo_G(String KJDZZBBH) throws Exception {
        Map<String, Object> dataPullBase = fzlxService.Fzxx(KJDZZBBH);
        List<Map<String, Object>> pageDatapubbmXX = fzlxService.pubbmxx(KJDZZBBH);
        List<Map<String, Object>> pageDataxmzl = fzlxService.Xmzl(KJDZZBBH);
        List<Map<String, Object>> pageDataPubkszl = fzlxService.Pubkszl(KJDZZBBH);
        List<Map<String, Object>> resultList = fzlxService.FzxxStr(dataPullBase,pageDatapubbmXX,pageDataxmzl,pageDataPubkszl,KJDZZBBH);
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                fzxxMapper._add(map1);
            }
        }
        return "success";
    }


}
