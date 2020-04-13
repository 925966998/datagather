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
public class PzfzmxService {
    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;
    @Autowired
    OrgMapper orgMapper;
    @Autowired
    PznrMapper pznrMapper;
    @Autowired
    KmxxMapper kmxxMapper;

    @Autowired
    PubbmxxMapper pubbmxxMapper;

    @Autowired
    PubkszlMapper pubkszlMapper;

    @Autowired
    XmzlMapper xmzlMapper;

    @Autowired
    FzxzlMapper fzxzlMapper;

    @Autowired
    KmxzlxMapper kmxzlxMapper;

    public List pzfzmx(String KJDZZBBH) {
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        Map<String, Object> orgData = new HashMap<String, Object>();
        orgData.put("kjnd", Org.get(0).getKjnd());
        orgData.put("gsdm", Org.get(0).getOrgCode());
        orgData.put("ZTH", Org.get(0).getZt());
        List<Map<String, Object>> bypznrList = pznrMapper._queryPznr_G(orgData);
        return bypznrList;
    }

    @Transactional("targetTransactionManager")
    public List<Map<String, Object>> pzfzmxStr(String KJDZZBBH, Map<String, Object> stringObjectMap,
                                               List<Map<String, Object>> bypznrList) {

        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        int i = 1;
        int flag = 1;
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
            dataPullBase.put("KJKMBM", pd.get("kmdm").toString().trim().replace("　", ""));
            Map<String, Object> pageData1 = new HashMap<String, Object>();
            pageData1.put("kmdm", pd.get("kmdm"));
            pageData1.put("gsdm", Org.get(0).getOrgCode());
            pageData1.put("ZTH", Org.get(0).getZt());
            pageData1.put("kjnd", Org.get(0).getKjnd());
            List<Map<String, Object>> dataKmxx = kmxxMapper._queryKmxxmx(pageData1);
            dataPullBase.put("KJKMMC", dataKmxx.get(0).get("kmmc"));
            if (pd.get("bmdm") != null && !StringUtils.isEmpty(pd.get("bmdm").toString())) {
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "部门");
                Map<String, Object> qrbmdm = new HashMap<>();
                qrbmdm.put("bmdm", pd.get("bmdm"));
                qrbmdm.put("gsdm", Org.get(0).getOrgCode());
                qrbmdm.put("kjnd", Org.get(0).getKjnd());
                List<Map<String, Object>> pageDataPUBBMXX = pubbmxxMapper._queryyePubbmxx(qrbmdm);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "0");
                Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("0");
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");
                if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                    dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                    dataPull.put("FZQC", pageDataPUBBMXX.get(0).get("bmmc"));
                    dataPull = wujiG(pageDataGL_Fzxlb, pageDataPUBBMXX.get(0).get("bmdm").toString(), dataPull);
                }
                resultList.add(dataPull);
                flag = 2;
            }
            if (pd.get("wldm") != null && !StringUtils.isEmpty(pd.get("wldm").toString())) {
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "单位往来");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("dwdm", pd.get("wldm"));
                queryPd.put("kjnd", Org.get(0).getKjnd());
                queryPd.put("gsdm", Org.get(0).getOrgCode());
                List<Map<String, Object>> pageDataPUBKSZL = pubkszlMapper._queryYePubkszl(queryPd);
                queryPd.put("lbdm", "3");
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");
                Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("3");
                if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                    dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                    dataPull.put("FZQC", pageDataPUBKSZL.get(0).get("dwqc"));
                    dataPull = wujiG(pageDataGL_Fzxlb, pageDataPUBKSZL.get(0).get("dwdm").toString(), dataPull);
                }
                resultList.add(dataPull);
                flag = 2;
            }
            if (pd.get("xmdm") != null && !StringUtils.isEmpty(pd.get("xmdm").toString())) {
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "项目");
                Map<String, Object> queryPd1 = new HashMap<String, Object>();
                queryPd1.put("XMDM", pd.get("xmdm"));
                queryPd1.put("KJND", Org.get(0).getKjnd());
                queryPd1.put("GSDM", Org.get(0).getOrgCode());
                List<Map<String, Object>> pageDataGL_Xmzl = xmzlMapper._queryYeXmzl(queryPd1);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "1");
                Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("1");
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");
                if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                    dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                    dataPull.put("FZQC", pageDataGL_Xmzl.get(0).get("XMMC"));
                    dataPull = wujiG(pageDataGL_Fzxlb, pageDataGL_Xmzl.get(0).get("XMDM").toString(), dataPull);
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
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get(String.valueOf(q));
                    queryPd.put("fzdm", pd.get("fzdm" + q));
                    queryPd.put("kjnd", datadzzbxx.get("KJND"));
                    queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                    List<Map<String, Object>> pageDataGL_Fzxzl = fzxzlMapper._queryYeFzxzl(queryPd);
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
                                    queryPd.put("kjnd", datadzzbxx.get("KJND"));
                                    queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                                    List<Map<String, Object>> pageDataGL_FzxzlQc = fzxzlMapper._queryYeFzxzl(queryPd);
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
                        dataPull = wujiG(pageDataGL_Fzxlb, pageDataGL_Fzxzl.get(0).get("fzdm").toString(), dataPull);
                    }
                    resultList.add(dataPull);
                    flag = 2;
                }
            }
            flag = 1;
            i++;
        }
        return resultList;
    }

    @Transactional("targetTransactionManager")
    public boolean pzfzmxG(List<Map<String, Object>> resultList) {
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                tragetMapper._addPzfzmx(map1);
            }
        }
        return true;
    }

    public Map<String, Object> wujiG(Map<String, Object> pageDataFzxlb, String result, Map<String, Object> dataPull) {
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

    @Transactional("targetTransactionManager")
    public boolean pzfzmxb(List<Map<String, Object>> bypznrList, List<Map<String, Object>> dzzbxxList, Map<String, Object> stringObjectMap) {
        int i = 1;
        int flag = 1;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
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
            if (dataKmxx != null && dataKmxx.size() > 0) {
                dataPullBase.put("KJKMMC", dataKmxx.get(0).get("kmmc").toString().trim().replace("　", ""));
            }
            if (pd.get("bmdm") != null && !StringUtils.isEmpty(pd.get("bmdm").toString())) {
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "部门");
                List<Map<String, Object>> pageDataPUBBMXX = sourceMapper._queryPubbmxx(pd);
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");
                if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                    dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("0");
                    if (pageDataGL_Fzxlb != null) {

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
                                        if (maps != null && maps.size() > 0 &&maps.get(0).get("bmmc") != null) {
                                            qc.add(maps.get(0).get("bmmc").toString());
                                        }
                                    }
                                }
                            }
                        }
                        qc.add(pageDataPUBBMXX.get(0).get("bmmc").toString());
                        dataPull.put("FZQC", String.join("/", qc));
                    } else {
                        dataPull.put("FZQC", pageDataPUBBMXX.get(0).get("bmmc"));
                    }
                    dataPull = wuji(pageDataGL_Fzxlb, pageDataPUBBMXX.get(0).get("bmdm").toString(), dataPull);
                }
                resultList.add(dataPull);
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
                if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                    dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("3");
                    if (pageDataGL_Fzxlb != null) {

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
                                        if (maps != null && maps.size() > 0 && maps.get(0).get("dwmc") != null){
                                            qc.add(maps.get(0).get("dwmc").toString());
                                        }
                                    }
                                }
                            }
                        }
                        qc.add(pageDataPUBKSZL.get(0).get("dwmc").toString());
                        dataPull.put("FZQC", String.join("/", qc));
                    } else {
                        dataPull.put("FZQC", pageDataPUBKSZL.get(0).get("dwmc"));
                    }
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
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");
                if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                    dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("1");
                    if (pageDataGL_Fzxlb != null) {

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
                                        if (maps != null && maps.size() > 0) {
                                            if (maps.get(0).get("XMMC") != null) {
                                                qc.add(maps.get(0).get("XMMC").toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        qc.add(pageDataGL_Xmzl.get(0).get("XMMC").toString());
                        dataPull.put("FZQC", String.join("/", qc));
                    } else {
                        dataPull.put("FZQC", pageDataGL_Xmzl.get(0).get("XMMC"));
                    }
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
            flag = 1;
            i++;
        }
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                tragetMapper._addPzfzmx(map1);
            }
            return true;
        }
        return true;
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
}
