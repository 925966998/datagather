package com.ky.dbbak.controller;

import com.ky.dbbak.entity.FZLXEntity;
import com.ky.dbbak.service.FzxlbService;
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
            String lxdm1= kj.get("kmxz").toString();
            List<Map<String, Object>> _queryKMXZLX= kmxzlxMapper._queryGL_KMXZLX(lxdm1);
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
            if (a>1){
                dataPull.put("FZHSBZ", 1);
            }else {
                dataPull.put("FZHSBZ", 0);
            }
            String[] fzhsbz= kj.get("fzhs").toString().split(",");
            System.out.println(fzhsbz.length);
            if (fzhsbz.length>1){
                String fzhsx = "";
                for (int x = 1; x < fzhsbz.length; x++) {
                    if (!StringUtils.isEmpty(fzhsbz[x])){
                        String lbdm=fzhsbz[x];
                        System.out.println(lbdm);
                        if (stringObjectMap.get(lbdm) != null){
                            Map<String,Object> o = (Map<String, Object>) stringObjectMap.get(lbdm);
                            fzhsx += o.get("lbmc").toString().trim() + ",";
                        }
                    }
                }
                fzhsx = fzhsx.substring(fzhsx.lastIndexOf(fzhsx),fzhsx.length()-1);
                fzhsx = fzhsx.replace("　", "");
                dataPull.put("FZHSX", fzhsx.trim());
            }else {
                dataPull.put("FZHSX", " ");
            }
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
                    kmqc = kmqc.substring(kmqc.lastIndexOf(kmqc),kmqc.length()-1);
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
        dataPullBase.put("FZLX", " ");
        dataPullBase.put("FZBM", " ");
        dataPullBase.put("FZMC", " ");
        dataPullBase.put("FZQC", " ");
        dataPullBase.put("FZJC", 0);
        dataPullBase.put("SJFZBM", " ");
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
                if(pageDataFzxzl.size()>0 && pageDataFzxzl!=null){
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
//            fzxxMapper._addFzxx(map);
//        }
//        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
//        fzxxMapper._addFzxx(map);
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                fzxxMapper._add(map1);
            }
        }
        return "success";
    }
}
