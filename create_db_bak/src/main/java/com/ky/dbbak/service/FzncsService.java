package com.ky.dbbak.service;

import com.ky.dbbak.entity.OrgEntity;
import com.ky.dbbak.mapper.OrgMapper;
import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.TragetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FzncsService {
    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;
    @Autowired
    OrgMapper orgMapper;
    @Autowired
    KmxzlxMapper kmxzlxMapper;

    @Autowired
    ZtcsMapper ztcsMapper;

    @Autowired
    KmxxMapper kmxxMapper;

    @Autowired
    PubbmxxMapper pubbmxxMapper;

    @Autowired
    XmzlMapper xmzlMapper;

    @Autowired
    PubkszlMapper pubkszlMapper;

    @Autowired
    FzxzlMapper fzxzlMapper;

    public List fzncs(String KJDZZBBH){
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        Map<String, Object> orgData = new HashMap<String, Object>();
        orgData.put("kjnd",Org.get(0).getKjnd());
        orgData.put("gsdm",Org.get(0).getOrgCode());
        orgData.put("ZTH",Org.get(0).getZt());
        List<Map<String, Object>> GL_YebList = kmxzlxMapper._queryYebList(orgData);
        return GL_YebList;
    }

    public List ZtcsStr(String KJDZZBBH){
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        Map<String, Object> ztcsStr = new HashMap<String, Object>();
        ztcsStr.put("kjnd",Org.get(0).getKjnd());
        ztcsStr.put("ztbh",Org.get(0).getZt());
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcszh(ztcsStr);
        return pageDataGL_Ztcs;
    }

    @Transactional("targetTransactionManager")
    public List<Map<String, Object>> FzncsResult(List<Map<String, Object>> resultList, List<Map<String, Object>> pageDataGL_Ztcs,
                                                String KJDZZBBH) {
        List<Map<String, Object>> resultListNew = new ArrayList<Map<String, Object>>();
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> map : resultList
            ) {
                resultListNew.add(map);
                Integer legth = map.get("KJKMBM").toString().length();
                String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
                String[] lbfjStr = kmbmfa.split("-");
                int num = 0;//8  4 2 2 2 2
                Map<String, Object> quM = new HashMap<String, Object>();
                List kmdms = new ArrayList();
                for (int w = 0; w < lbfjStr.length; w++) {
                    Map<String, Object> dataPullBase = new HashMap<String, Object>(map);
                    num = num + Integer.valueOf(lbfjStr[w]);
                    if (num < legth) {
                        quM.put("kmdm", map.get("KJKMBM").toString().substring(0, num));
                        quM.put("kjnd", Org.get(0).getKjnd());
                        quM.put("gsdm", Org.get(0).getOrgCode());
                        quM.put("ZTH", Org.get(0).getZt());
                        List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._queryKmxxmx(quM);
                        dataPullBase.put("KJKMBM", map.get("KJKMBM").toString().substring(0, num));
                        dataPullBase.put("KJKMJC", w + 1);
                        dataPullBase.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                        if (w != 0) {
                            dataPullBase.put("SJKMBM", map.get("KJKMBM").toString().substring(0, num - Integer.valueOf(lbfjStr[w])));
                        } else {
                            dataPullBase.put("SJKMBM", " ");
                        }
                        dataPullBase.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                        dataPullBase.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
                        resultListNew.add(dataPullBase);
                    } else {
                        break;
                    }
                }
            }
        }
        return resultListNew;
    }

    @Transactional("targetTransactionManager")
    public List<Map<String, Object>> kmncsxx (String KJDZZBBH, List<Map<String, Object>> GL_YebList,
                                             Map<String, Object> stringObjectMap, List<Map<String, Object>> pageDataGL_Ztcs){
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
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
            Map<String, Object> pageData1 = new HashMap<String, Object>();
            pageData1.put("kmdm",pd.get("kmdm"));
            pageData1.put("gsdm",Org.get(0).getOrgCode());
            pageData1.put("ZTH",Org.get(0).getZt());
            pageData1.put("kjnd",Org.get(0).getKjnd());
            List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._queryKmxxmx(pageData1);
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
                dataPullBase.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                dataPullBase.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
            }
            if (pd.get("fzdm0") != null && !StringUtils.isEmpty(pd.get("fzdm0").toString().trim())) {
                Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "部门");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("bmdm", pd.get("fzdm0"));
                queryPd.put("gsdm",Org.get(0).getOrgCode());
                queryPd.put("kjnd",Org.get(0).getKjnd());
                List<Map<String, Object>> pageDataPUBBMXX = pubbmxxMapper._queryyePubbmxx(queryPd);
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
                queryPd.put("XMDM", pd.get("fzdm1"));
                queryPd.put("KJND", Org.get(0).getKjnd());
                queryPd.put("GSDM", Org.get(0).getOrgCode());
                List<Map<String, Object>> pageDataGL_Xmzl = xmzlMapper._queryYeXmzl(queryPd);
                if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                    dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                }
                resultList.add(dataPull);
            }
            if (pd.get("fzdm2") != null && !StringUtils.isEmpty(pd.get("fzdm2").toString().trim())) {
                Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "个人往来");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("dwdm", pd.get("fzdm2"));
                queryPd.put("kjnd", Org.get(0).getKjnd());
                queryPd.put("gsdm", Org.get(0).getOrgCode());
                List<Map<String, Object>> pageDataPUBKSZL = pubkszlMapper._queryYePubkszl(queryPd);
                if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                    dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                }
                resultList.add(dataPull);
            }
            if (pd.get("fzdm3") != null && !StringUtils.isEmpty(pd.get("fzdm3").toString().trim())) {
                Map<String, Object> dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "单位往来");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("dwdm", pd.get("fzdm3"));
                queryPd.put("kjnd", Org.get(0).getKjnd());
                queryPd.put("gsdm", Org.get(0).getOrgCode());
                List<Map<String, Object>> pageDataPUBKSZL = pubkszlMapper._queryYePubkszl(queryPd);
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
                    queryPd.put("kjnd", datadzzbxx.get("KJND"));
                    queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                    List<Map<String, Object>> pageDataGL_Fzxzl = fzxzlMapper._queryYeFzxzl(queryPd);
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get(String.valueOf(q));
                    dataPull.put("FZLX", pageDataGL_Fzxlb.get("lbmc"));
                    if (pageDataGL_Fzxzl != null && pageDataGL_Fzxzl.size() > 0) {
                        dataPull.put("FZBM", pageDataGL_Fzxzl.get(0).get("fzdm"));
                        dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                    }
                    resultList.add(dataPull);
                }
            }
        }
        return resultList;
    }



    @Transactional("targetTransactionManager")
    public List fzncsb(List<Map<String, Object>> GL_YebList,List<Map<String, Object>> dzzbxxList,
                       Map<String, Object> stringObjectMap,List<Map<String, Object>> pageDataGL_Ztcs){
        List<Map<String, Object>> resultList = new ArrayList<>();
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
                    List<Map<String, Object>> pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
                    dataPull.put("FZLX", pageDataGL_Fzxlb.get("lbmc"));
                    if (pageDataGL_Fzxzl != null && pageDataGL_Fzxzl.size() > 0) {
                        dataPull.put("FZBM", pageDataGL_Fzxzl.get(0).get("fzdm"));
                        dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                    }
                    resultList.add(dataPull);
                }
            }
        }
        return resultList;
    }
    @Transactional("targetTransactionManager")
    public boolean fzncB(List<Map<String, Object>> resultListNew){
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
            return true;
        }
        return true;
    }
}
