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
    PzmlMapper pzmlMapper;
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
        orgData.put("gsdm", Org.get(0).getGsdm());
        orgData.put("ZTH", Org.get(0).getZtbh());
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
        for (Map<String, Object> pd : bypznrList
        ) {
            Map<String, Object> dataPullBase = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            Map pageData11 = new HashMap();
            pageData11.put("IDPZH", pd.get("IDPZH"));
            List<Map<String, Object>> pageDataPzmlList = pzmlMapper._queryPzmlG(pageData11);
            if (pageDataPzmlList == null || pageDataPzmlList.size() < 1) {
                continue;
            }
            this.pzfzmxBase(dataPullBase, pd, dzzbxxList);
            Map<String, Object> pageData1 = new HashMap<String, Object>();
            pageData1.put("kmdm", pd.get("kmdm"));
            pageData1.put("gsdm", Org.get(0).getGsdm());
            pageData1.put("ZTH", Org.get(0).getZtbh());
            pageData1.put("kjnd", Org.get(0).getKjnd());
            List<Map<String, Object>> dataKmxx = kmxxMapper._queryKmxxmx(pageData1);
            if (dataKmxx != null && dataKmxx.size() > 0) {
                dataPullBase.put("KJKMMC", dataKmxx.get(0).get("kmmc"));
            }

            if (pd.get("bmdm") != null && !StringUtils.isEmpty(pd.get("bmdm").toString())) {
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "部门");
                Map<String, Object> qrbmdm = new HashMap<>();
                qrbmdm.put("bmdm", pd.get("bmdm"));
                qrbmdm.put("gsdm", Org.get(0).getGsdm());
                qrbmdm.put("kjnd", Org.get(0).getKjnd());
                List<Map<String, Object>> pageDataPUBBMXX = pubbmxxMapper._queryyePubbmxx(qrbmdm);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "0");
                Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("0");
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");
                this.pzfzmxpageDataGL_Fzxlb(dataPull, pd, Org, pageDataPUBBMXX, pageDataGL_Fzxlb, 2);
                resultList.add(dataPull);
            }
            if (pd.get("wldm") != null && !StringUtils.isEmpty(pd.get("wldm").toString())) {
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "单位往来");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("dwdm", pd.get("wldm"));
                queryPd.put("kjnd", Org.get(0).getKjnd());
                queryPd.put("gsdm", Org.get(0).getGsdm());
                List<Map<String, Object>> pageDataPUBKSZL = pubkszlMapper._queryYePubkszl(queryPd);
                queryPd.put("lbdm", "3");
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");
                Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("3");
                this.pzfzmxpageDataPUBKSZL(dataPull, pd, Org, pageDataPUBKSZL, pageDataGL_Fzxlb, 2);
                resultList.add(dataPull);
            }
            if (pd.get("xmdm") != null && !StringUtils.isEmpty(pd.get("xmdm").toString())) {
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                dataPull.put("FZLX", "项目");
                Map<String, Object> queryPd1 = new HashMap<String, Object>();
                queryPd1.put("XMDM", pd.get("xmdm"));
                queryPd1.put("KJND", Org.get(0).getKjnd());
                queryPd1.put("GSDM", Org.get(0).getGsdm());
                List<Map<String, Object>> pageDataGL_Xmzl = xmzlMapper._queryYeXmzl(queryPd1);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", "1");
                Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("1");
                dataPull.put("FZBM", "");
                dataPull.put("FZMC", "");
                dataPull.put("FZQC", "");
                this.pzfzmxpageDataGL_Xmzl(dataPull, pd, Org, pageDataGL_Xmzl, pageDataGL_Fzxlb, 2);
                resultList.add(dataPull);
            }
            for (int q = 4; q < 31; q++) {
                if (pd.get("fzdm" + q).toString().trim() != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString().trim())) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("fzdm", pd.get("fzdm" + q));
                    queryPd.put("lbdm", String.valueOf(q));
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get(String.valueOf(q).trim());
                    queryPd.put("fzdm", pd.get("fzdm" + q));
                    queryPd.put("kjnd", Org.get(0).getKjnd());
                    queryPd.put("gsdm", Org.get(0).getGsdm());
                    List<Map<String, Object>> pageDataGL_Fzxzl = fzxzlMapper._queryYeFzxzl(queryPd);
                    dataPull.put("FZBM", "");
                    dataPull.put("FZMC", "");
                    dataPull.put("FZQC", "");
                    this.pzfzmxpageDataGL_Fzxzl(dataPull, pd, Org, pageDataGL_Fzxzl, pageDataGL_Fzxlb, 2, q);
                    resultList.add(dataPull);
                }
            }
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
                num = num + Integer.valueOf(lbfjStr[w].trim());
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

    public static Map<String, Object> pzfzmxBase(Map<String, Object> dataPullBase, Map<String, Object> pd, List<Map<String, Object>> dzzbxxList) {
        dataPullBase.put("XZQHDM", dzzbxxList.get(0).get("XZQHDM"));
        dataPullBase.put("XZQHMC", dzzbxxList.get(0).get("XZQHMC"));
        dataPullBase.put("KJND", dzzbxxList.get(0).get("KJND"));
        dataPullBase.put("DWMC", dzzbxxList.get(0).get("DWMC"));
        dataPullBase.put("DWDM", dzzbxxList.get(0).get("DWDM"));
        dataPullBase.put("KJDZZBBH", dzzbxxList.get(0).get("KJDZZBBH"));
        dataPullBase.put("KJDZZBMC", dzzbxxList.get(0).get("KJDZZBMC"));
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
        return dataPullBase;
    }

    public Map<String, Object> pzfzmxpageDataGL_Fzxlb(Map<String, Object> dataPull, Map<String, Object> pd, List<OrgEntity> Org, List<Map<String, Object>> pageDataPUBBMXX, Map<String, Object> pageDataGL_Fzxlb, int flagVersion) {
        if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
            dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
            dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));

            if (pageDataGL_Fzxlb != null) {

                String fzlxjg = pageDataGL_Fzxlb.get("lbfj").toString();
                String fzdm = pageDataPUBBMXX.get(0).get("bmdm").toString().trim();
                List<String> qc = new ArrayList<String>();
                if (!StringUtils.isEmpty(fzlxjg)) {
                    String[] fzlxjgStr = fzlxjg.split("-");
                    int num = 0;//3  3  2  2  111 111 11
                    if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                        for (int w = 0; w < fzlxjgStr.length; w++) {
                            num = num + Integer.valueOf(fzlxjgStr[w].trim());
                            if (num < fzdm.length()) {
                                if (flagVersion == 1) {
                                    List<Map<String, Object>> maps = kmxzlxMapper._queryPUBBMXXq(fzdm.substring(0, num));
                                    if (maps != null && maps.size() > 0 && maps.get(0).get("bmmc") != null) {
                                        qc.add(maps.get(0).get("bmmc").toString());
                                    }
                                } else if (flagVersion == 2) {
                                    Map<String, Object> queryPd = new HashMap<String, Object>();
                                    queryPd.put("bmdm", pd.get("fzdm0"));
                                    queryPd.put("gsdm", Org.get(0).getGsdm());
                                    queryPd.put("kjnd", Org.get(0).getKjnd());
                                    List<Map<String, Object>> maps = pubbmxxMapper._queryyePubbmxx(queryPd);
                                    if (maps != null && maps.size() > 0 && maps.get(0).get("bmmc") != null) {
                                        qc.add(maps.get(0).get("bmmc").toString());
                                    }
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
        return dataPull;
    }

    public Map<String, Object> pzfzmxpageDataPUBKSZL(Map<String, Object> dataPull, Map<String, Object> pd, List<OrgEntity> Org, List<Map<String, Object>> pageDataPUBKSZL, Map<String, Object> pageDataGL_Fzxlb, int flagVersion) {
        if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
            dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
            dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));

            if (pageDataGL_Fzxlb != null) {

                String fzlxjg = pageDataGL_Fzxlb.get("lbfj").toString();
                String fzdm = pageDataPUBKSZL.get(0).get("dwdm").toString().trim();
                List<String> qc = new ArrayList<String>();
                if (!StringUtils.isEmpty(fzlxjg)) {
                    String[] fzlxjgStr = fzlxjg.split("-");
                    int num = 0;//3  3  2  2  111 111 11
                    if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                        for (int w = 0; w < fzlxjgStr.length; w++) {
                            num = num + Integer.valueOf(fzlxjgStr[w].trim());
                            if (num < fzdm.length()) {
                                if (flagVersion == 1) {
                                    List<Map<String, Object>> maps = kmxzlxMapper._queryPUBKSZLq(fzdm.substring(0, num));
                                    if (maps != null && maps.size() > 0 && maps.get(0).get("dwmc") != null) {
                                        qc.add(maps.get(0).get("dwmc").toString());
                                    }
                                } else if (flagVersion == 2) {
                                    Map<String, Object> queryPd = new HashMap<String, Object>();
                                    queryPd.put("dwdm", pd.get("fzdm3"));
                                    queryPd.put("kjnd", Org.get(0).getKjnd());
                                    queryPd.put("gsdm", Org.get(0).getGsdm());
                                    List<Map<String, Object>> maps = pubkszlMapper._queryYePubkszl(queryPd);
                                    if (maps != null && maps.size() > 0 && maps.get(0).get("dwmc") != null) {
                                        qc.add(maps.get(0).get("dwmc").toString());
                                    }
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
        return dataPull;
    }

    public Map<String, Object> pzfzmxpageDataGL_Xmzl(Map<String, Object> dataPull, Map<String, Object> pd, List<OrgEntity> Org, List<Map<String, Object>> pageDataGL_Xmzl, Map<String, Object> pageDataGL_Fzxlb, int flagVersion) {
        if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
            if (flagVersion == 1) {
                dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
            } else if (flagVersion == 2) {
                dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("xmdm"));
                dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("xmmc"));
            }

            if (pageDataGL_Fzxlb != null) {
                String fzlxjg = pageDataGL_Fzxlb.get("lbfj").toString();
                String fzdm = "";
                if (flagVersion == 1) {
                    fzdm = pageDataGL_Xmzl.get(0).get("XMDM").toString().trim();
                } else if (flagVersion == 2) {
                    fzdm = pageDataGL_Xmzl.get(0).get("xmdm").toString().trim();
                }

                List<String> qc = new ArrayList<String>();
                if (!StringUtils.isEmpty(fzlxjg)) {
                    String[] fzlxjgStr = fzlxjg.split("-");
                    int num = 0;//3  3  2  2  111 111 11
                    if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                        for (int w = 0; w < fzlxjgStr.length; w++) {
                            num = num + Integer.valueOf(fzlxjgStr[w].trim());
                            if (num < fzdm.length()) {
                                if (flagVersion == 1) {
                                    List<Map<String, Object>> maps = kmxzlxMapper._queryXMZLq(fzdm.substring(0, num));
                                    if (maps != null && maps.size() > 0) {
                                        if (maps.get(0).get("XMMC") != null) {
                                            qc.add(maps.get(0).get("XMMC").toString());
                                        }
                                    }
                                } else if (flagVersion == 2) {
                                    Map<String, Object> queryPd = new HashMap<String, Object>();
                                    queryPd.put("XMDM", pd.get("fzdm1"));
                                    queryPd.put("KJND", Org.get(0).getKjnd());
                                    queryPd.put("GSDM", Org.get(0).getGsdm());
                                    List<Map<String, Object>> maps = xmzlMapper._queryYeXmzl(queryPd);
                                    if (maps != null && maps.size() > 0) {
                                        if (maps.get(0).get("XMMC") != null) {
                                            qc.add(maps.get(0).get("XMMC").toString());
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
                if (pageDataGL_Xmzl.get(0).get("XMMC") != null) {
                    qc.add(pageDataGL_Xmzl.get(0).get("XMMC").toString());
                } else if (pageDataGL_Xmzl.get(0).get("xmmc") != null) {
                    qc.add(pageDataGL_Xmzl.get(0).get("xmmc").toString());
                }

                dataPull.put("FZQC", String.join("/", qc));
            } else {
                dataPull.put("FZQC", pageDataGL_Xmzl.get(0).get("XMMC"));
            }
            if (pageDataGL_Xmzl.get(0).get("XMDM") != null) {
                dataPull = wuji(pageDataGL_Fzxlb, pageDataGL_Xmzl.get(0).get("XMDM").toString(), dataPull);
            } else if (pageDataGL_Xmzl.get(0).get("xmdm") != null) {
                dataPull = wuji(pageDataGL_Fzxlb, pageDataGL_Xmzl.get(0).get("xmdm").toString(), dataPull);
            }

        }
        return dataPull;
    }

    public Map<String, Object> pzfzmxpageDataGL_Fzxzl(Map<String, Object> dataPull, Map<String, Object> pd, List<OrgEntity> Org, List<Map<String, Object>> pageDataGL_Fzxzl, Map<String, Object> pageDataGL_Fzxlb, int flagVersion, int q) {
        if (pageDataGL_Fzxlb != null && pageDataGL_Fzxlb.size() > 0) {
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
                    num = num + Integer.valueOf(lbfjStr[w].trim());
                    if (num <= result.length()) {
                        Map<String, Object> queryPd = new HashMap<String, Object>();
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
        return dataPull;
    }

    @Transactional("targetTransactionManager")
    public boolean pzfzmxb(List<Map<String, Object>> bypznrList, List<Map<String, Object>> dzzbxxList, Map<String, Object> stringObjectMap) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> pd : bypznrList
        ) {
            Map<String, Object> dataPullBase = new HashMap<String, Object>();
            Map pageData1 = new HashMap();
            pageData1.put("IDPZH", pd.get("IDPZH"));
            List<Map<String, Object>> pageDataPzmlList = pzmlMapper._queryPzmlG(pageData1);
            if (pageDataPzmlList == null || pageDataPzmlList.size() < 1) {
                continue;
            }
            this.pzfzmxBase(dataPullBase, pd, dzzbxxList);
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
                Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("0");
                this.pzfzmxpageDataGL_Fzxlb(dataPull, pd, null, pageDataPUBBMXX, pageDataGL_Fzxlb, 1);
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
                Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("3");
                pzfzmxpageDataPUBKSZL(dataPull, pd, null, pageDataPUBKSZL, pageDataGL_Fzxlb, 1);
                resultList.add(dataPull);
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
                Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("1");
                pzfzmxpageDataGL_Xmzl(dataPull, pd, null, pageDataGL_Xmzl, pageDataGL_Fzxlb, 1);

                resultList.add(dataPull);
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

                    this.pzfzmxpageDataGL_Fzxzl(dataPull, pd, null, pageDataGL_Fzxzl, pageDataGL_Fzxlb, 1, q);
                    resultList.add(dataPull);
                }
            }
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
                num = num + Integer.valueOf(lbfjStr[w].trim());
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
