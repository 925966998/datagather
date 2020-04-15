package com.ky.dbbak.service;

import com.ky.dbbak.entity.OrgEntity;
import com.ky.dbbak.mapper.OrgMapper;
import com.ky.dbbak.mybatis.PagerResult;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.DzzbxxMapper;
import com.ky.dbbak.targetmapper.FzyeMapper;
import com.ky.dbbak.targetmapper.TragetMapper;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TargetService
 * @Description: TODO
 * @Author czw
 * @Date 2020/3/24
 **/
@Service
public class FzyeService {
    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;
    @Autowired
    KmxzlxMapper kmxzlxMapper;
    @Autowired
    KmxxMapper kmxxMapper;

    @Autowired
    OrgMapper orgMapper;

    @Autowired
    ZtcsMapper ztcsMapper;

    @Autowired
    DzzbxxMapper dzzbxxMapper;

    @Autowired
    PubbmxxMapper pubbmxxMapper;

    @Autowired
    XmzlMapper xmzlMapper;

    @Autowired
    PubkszlMapper pubkszlMapper;

    @Autowired
    FzxzlMapper fzxzlMapper;

    @Autowired
    FzyeMapper fzyeMapper;

    @Autowired
    FzxlbMapper fzxlbMapper;
    @Autowired
    FzxlbService fzxlbService;

        public Map<String, Object> _queryGL_Fzxlb1(String KJDZZBBH) {
            Map<String, Object> map = new HashMap<String, Object>();
            List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
            Map<String, Object> orgData = new HashMap<String, Object>();
            orgData.put("kjnd",Org.get(0).getKjnd());
            orgData.put("gsdm",Org.get(0).getGsdm());
            List<Map<String, Object>> maps = fzxlbMapper._querykmFzxlb(orgData);
            if (maps != null && maps.size() > 0) {
                for (Map<String, Object> resultMap : maps
                ) {
                    map.put(resultMap.get("lbdm").toString().trim(), resultMap);
                }
            }
            return map;
        }


        @Transactional("targetTransactionManager")
        public List FzyeBase (String KJDZZBBH , int FzyeBBH){
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> GL_YebList = new ArrayList<>();
        List<Map<String, Object>> pageDataGL_Ztcs = new ArrayList<>();
        Map<String, Object> stringObjectMap = new HashMap<>();
        if (FzyeBBH==1){
            GL_YebList= kmxzlxMapper._queryGL_Yeb();
            pageDataGL_Ztcs = ztcsMapper._queryZtcs();
            stringObjectMap = fzxlbService._queryGL_Fzxlb1(pageData);
        }else if (FzyeBBH==2){
            Map<String, Object> orgData = new HashMap<String, Object>();
            orgData.put("kjnd",Org.get(0).getKjnd());
            orgData.put("gsdm",Org.get(0).getGsdm());
            orgData.put("ZTH",Org.get(0).getZtbh());
            GL_YebList = kmxzlxMapper._queryYebList(orgData);
            Map<String, Object> ztcsStr = new HashMap<String, Object>();
            ztcsStr.put("kjnd",Org.get(0).getKjnd());
            ztcsStr.put("ztbh",Org.get(0).getZtbh());
            pageDataGL_Ztcs = ztcsMapper._queryZtcszh(ztcsStr);
            stringObjectMap = this._queryGL_Fzxlb1(KJDZZBBH);
        }
        for (Map<String, Object> pd : GL_YebList) {
            Map<String, Object> dataPullBase = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPullBase.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPullBase.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPullBase.put("KJND", datadzzbxx.get("KJND"));
            dataPullBase.put("DWMC", datadzzbxx.get("DWMC"));
            dataPullBase.put("DWDM", datadzzbxx.get("DWDM"));
            dataPullBase.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPullBase.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            dataPullBase.put("KJYF", 1);
            dataPullBase.put("KJTX", "  ");
            dataPullBase.put("KJKMBM", " ");
            dataPullBase.put("KJKMMC", " ");
            dataPullBase.put("KJKMQC", " ");
            dataPullBase.put("KJKMJC", 1);
            dataPullBase.put("SFZDJKM", 1);
            dataPullBase.put("SJKMBM", " ");
            dataPullBase.put("NCJFYE", BigDecimal.ZERO);
            dataPullBase.put("NCDFYE", BigDecimal.ZERO);
            dataPullBase.put("NCYEFX", 0);
            dataPullBase.put("QCJFYE", BigDecimal.ZERO);
            dataPullBase.put("QCDFYE", BigDecimal.ZERO);
            dataPullBase.put("QCYEFX", 0);
            dataPullBase.put("JFFSE", BigDecimal.ZERO);
            dataPullBase.put("JFLJFSE", BigDecimal.ZERO);
            dataPullBase.put("DFFSE", BigDecimal.ZERO);
            dataPullBase.put("DFLJFSE", BigDecimal.ZERO);
            dataPullBase.put("QMJFYE", BigDecimal.ZERO);
            dataPullBase.put("QMDFYE", BigDecimal.ZERO);
            dataPullBase.put("QMYEFX", 0);
            dataPullBase.put("FLS", 0);
            dataPullBase.put("BZMC", datadzzbxx.get("BWB"));
            //26.期初外币借方余额
            dataPullBase.put("QCWBJFYE", BigDecimal.ZERO);
            //27.期初外币贷方余额
            dataPullBase.put("QCWBDFYE", BigDecimal.ZERO);
            //28.借方外币发生额
            dataPullBase.put("JFWBFSE", BigDecimal.ZERO);
            //29.DFWBFSE
            dataPullBase.put("DFWBFSE", BigDecimal.ZERO);
            //30.QMWBJFYE
            dataPullBase.put("QMWBJFYE", BigDecimal.ZERO);
            //31.期末外币贷方余额
            dataPullBase.put("QMWBDFYE", BigDecimal.ZERO);
            dataPullBase.put("FZLX", " ");
            dataPullBase.put("FZBM", " ");
            dataPullBase.put("FZMC", " ");
            dataPullBase.put("SJFZBM", " ");
            dataPullBase.put("FZJB", 0);
            dataPullBase.put("BZDM", " ");
            BigDecimal jfljfse = new BigDecimal("0");
            BigDecimal dfljfse = new BigDecimal("0");
            BigDecimal qmjfye = new BigDecimal("0");
            BigDecimal qmdfye = new BigDecimal("0");
            dataPullBase.put("KJKMBM", pd.get("kmdm"));
            List<Map<String, Object>> pageDataGL_KMXX = new ArrayList<>();
            if (FzyeBBH==1){
                pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
            }else if (FzyeBBH==2){
                Map<String, Object> pageData1 = new HashMap<String, Object>();
                pageData1.put("kmdm",pd.get("kmdm"));
                pageData1.put("gsdm",Org.get(0).getGsdm());
                pageData1.put("ZTH",Org.get(0).getZtbh());
                pageData1.put("kjnd",Org.get(0).getKjnd());
                pageDataGL_KMXX = kmxxMapper._queryKmxxmx(pageData1);
            }
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPullBase.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace("　", ""));
                dataPullBase.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
                dataPullBase.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                String kmdm = pageDataGL_KMXX.get(0).get("kmdm").toString();
                Integer legth = pageDataGL_KMXX.get(0).get("kmdm").toString().length();
                if (legth > 4) {
                    String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
                    String[] lbfjStr = kmbmfa.split("-");
                    int num = 0;
                    List<String> kmdms = new ArrayList<String>();
                    for (int w = 0; w < lbfjStr.length; w++) {
                        num = num + Integer.valueOf(lbfjStr[w].trim());
                        if (num <= kmdm.length()) {
                            kmdms.add(kmdm.substring(0, num));
                        }
                        if (legth == num) {
                            dataPullBase.put("KJKMJB", w + 1);
                            dataPullBase.put("SJKMBM", pd.get("kmdm").toString().substring(0, num - Integer.valueOf(lbfjStr[w].trim())));
                        }
                    }
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    List<String> pageDataGL_KMXX1 = new ArrayList<>();
                    if (FzyeBBH ==1){
                        queryPd.put("kmdms", kmdms);
                        pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                    }else if (FzyeBBH==2){
                        queryPd.put("kmdms", kmdms);
                        queryPd.put("kjnd", Org.get(0).getKjnd());
                        queryPd.put("gsdm", Org.get(0).getGsdm());
                        queryPd.put("ZTH", Org.get(0).getZtbh());
                        pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                    }
                    String kjkmqc = String.join("/", pageDataGL_KMXX1);
                    kjkmqc = kjkmqc.trim();
                    kjkmqc = kjkmqc.replace("　", "");
                    dataPullBase.put("KJKMQC", kjkmqc);
                } else {
                    dataPullBase.put("KJKMJB", 1);
                    dataPullBase.put("SJKMBM", "");
                    dataPullBase.put("KJKMQC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace("　", ""));
                }
            }
            for (int i = 1; i < 13; i++) {
                if (new BigDecimal(pd.get("ncd").toString()).compareTo(new BigDecimal("0")) == 0 && new BigDecimal(pd.get("ncj").toString()).compareTo(new BigDecimal("0")) == 0) {
                    int flag = 1;
                    for (int j = 1; j <= i; j++) {
                        if (new BigDecimal(pd.get("yd" + j).toString()).compareTo(new BigDecimal("0")) != 0 || new BigDecimal(pd.get("yj" + j).toString()).compareTo(new BigDecimal("0")) != 0) {
                            flag = 2;
                        }
                    }
                    if (flag == 1) {
                        continue;
                    }
                }
                Map<String, Object> dataPull = new HashMap<String, Object>();
                dataPull = new HashMap<String, Object>(dataPullBase);
                //8.会计月份
                dataPull.put("KJYF", i);
                //13.年初借方余额
                //13.年初借方余额
                BigDecimal ncj = new BigDecimal(pd.get("ncj").toString());
                if (ncj.compareTo(new BigDecimal("0")) == 0) {
                    dataPull.put("NCJFYE", new BigDecimal("0"));
                } else {
                    dataPull.put("NCJFYE", ncj.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //14.年初贷方余额
                BigDecimal ncd = new BigDecimal(pd.get("ncd").toString());
                if (ncd.compareTo(new BigDecimal("0")) == 0) {
                    dataPull.put("NCDFYE", new BigDecimal("0"));
                } else {
                    dataPull.put("NCDFYE", ncd.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //15.年初余额方向  ncj-ncd  -1：贷，0：平，1：借。
                if (ncj.compareTo(ncd) == 1) {
                    dataPull.put("NCYEFX", 1);
                } else if (ncj.compareTo(ncd) == -1) {
                    dataPull.put("NCYEFX", -1);
                } else {
                    dataPull.put("NCYEFX", 0);
                }
                BigDecimal qcjfye = new BigDecimal(pd.get("ncj").toString());
                BigDecimal qcdfye = new BigDecimal(pd.get("ncd").toString());
                //17.期初借贷方余额
                if (i == 1) {
                    qcjfye = ncj.setScale(2, BigDecimal.ROUND_HALF_UP);
                    qcdfye = ncd.setScale(2, BigDecimal.ROUND_HALF_UP);
                    dataPull.put("QCJFYE", ncj.setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QCDFYE", ncd.setScale(2, BigDecimal.ROUND_HALF_UP));
                } else {
                    qcjfye = qmjfye.setScale(2, BigDecimal.ROUND_HALF_UP);
                    qcdfye = qmdfye.setScale(2, BigDecimal.ROUND_HALF_UP);
                    dataPull.put("QCJFYE", qmjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QCDFYE", qmdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //18.期初余额方向  ncj-ncd  -1：贷，0：平，1：借。
                if (i == 1) {
                    if (ncj.compareTo(ncd) == 1) {
                        dataPull.put("QCYEFX", 1);
                    } else if (ncj.compareTo(ncd) == -1) {
                        dataPull.put("QCYEFX", -1);
                    } else {
                        dataPull.put("QCYEFX", 0);
                    }
                } else {
                    if (qmjfye.compareTo(new BigDecimal("0")) == 1) {
                        if (qmdfye.compareTo(new BigDecimal("0")) == 1) {
                            dataPull.put("QCYEFX", 0);
                        } else {
                            dataPull.put("QCYEFX", -1);
                        }
                    } else {
                        dataPull.put("QCYEFX", 1);
                    }
                }

                //23.借方发生额
                BigDecimal jffse = new BigDecimal(pd.get("yj" + i).toString());
                dataPull.put("JFFSE", jffse.setScale(2, BigDecimal.ROUND_HALF_UP));
                //24.借方累计发生额
                jfljfse = jfljfse.add(jffse);
                dataPull.put("JFLJFSE", jfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));
                //25.外币借方发生额//赋值0
                dataPull.put("WBJFFSE", new BigDecimal("0"));
                //26.外币借方累计发生额//赋值0
                dataPull.put("WBJFLJFSE", new BigDecimal("0"));
                //27.贷方发生额
                BigDecimal dffse = new BigDecimal(pd.get("yd" + i).toString());
                dataPull.put("DFFSE", dffse.setScale(2, BigDecimal.ROUND_HALF_UP));
                //28.贷方累计发生额
                dfljfse = dfljfse.add(dffse);
                dataPull.put("DFLJFSE", dfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));
                //29.外币贷方发生额//赋值0
                dataPull.put("WBDFFSE", new BigDecimal("0"));
                //30.外币贷方累计发生额//赋值0
                dataPull.put("WBDFLJFSE", new BigDecimal("0"));
                //31.期末借方余额
                //32.期末贷方余额
                //33.期末余额方向   -1：贷，0：平，1：借。
                BigDecimal jj = qcjfye.setScale(2, BigDecimal.ROUND_HALF_UP).add(jffse.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal dd = qcdfye.setScale(2, BigDecimal.ROUND_HALF_UP).add(dffse.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (jj.compareTo(dd) == 1) {
                    qmjfye = jj.setScale(2, BigDecimal.ROUND_HALF_UP).subtract(dd.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    qmdfye = new BigDecimal("0");
                    dataPull.put("QMJFYE", qmjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QMDFYE", new BigDecimal("0"));
                    dataPull.put("QMYEFX", 1);
                } else if (jj.compareTo(dd) == -1) {
                    qmdfye = dd.setScale(2, BigDecimal.ROUND_HALF_UP).subtract(jj.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    qmjfye = new BigDecimal("0");
                    dataPull.put("QMJFYE", new BigDecimal("0"));
                    dataPull.put("QMDFYE", qmdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("QMYEFX", -1);
                } else {
                    qmjfye = new BigDecimal("0");
                    qmdfye = new BigDecimal("0");
                    dataPull.put("QMJFYE", new BigDecimal("0"));
                    dataPull.put("QMDFYE", new BigDecimal("0"));
                    dataPull.put("QMYEFX", 0);
                }
                //34.外币期末借方余额//赋值0
                dataPull.put("WBQMJFYE", new BigDecimal("0"));
                //35.外币期末贷方余额//赋值0
                dataPull.put("WBQMDFYE", new BigDecimal("0"));
                //36.分录数,查找月份，科目代码和辅助明晰一样的有几条
                Map<String, Object> qCountPd = new HashMap<String, Object>();
                if (FzyeBBH==1){
                    qCountPd.put("kmdm", pd.get("kmdm"));
                    if (i > 9) {
                        qCountPd.put("kjqj", datadzzbxx.get("KJND").toString() + i);
                    } else {
                        qCountPd.put("kjqj", datadzzbxx.get("KJND") + "0" + i);
                    }
                    long num = kmxzlxMapper._queryPznrCount(qCountPd);
                    dataPull.put("FLS", num);
                }else if (FzyeBBH==2){
                    qCountPd.put("kmdm", pd.get("kmdm"));
                    if (i > 9) {
                        qCountPd.put("kjqj", datadzzbxx.get("KJND").toString() + i);
                    } else {
                        qCountPd.put("kjqj", datadzzbxx.get("KJND") + "0" + i);
                    }
                    qCountPd.put("gsdm",Org.get(0).getGsdm());
                    qCountPd.put("ZTH",Org.get(0).getZtbh());
                    long num = kmxzlxMapper._queryCount(qCountPd);
                    dataPull.put("FLS", num);
                }
                if (pd.get("fzdm0") != null && !StringUtils.isEmpty(pd.get("fzdm0").toString().trim())) {
                    dataPull = new HashMap<String, Object>(dataPull);
                    dataPull.put("FZLX", "部门");
                    Map<String, Object> queryPd1 = new HashMap<String, Object>();
                    Map<String, Object> queryPd2 = new HashMap<String, Object>();
                    List<Map<String, Object>> pageDataPUBBMXX = new ArrayList<>();
                    if (FzyeBBH==1){
                        queryPd1.put("bmdm", pd.get("fzdm0"));
                        pageDataPUBBMXX = sourceMapper._queryPubbmxx(queryPd1);
                    }else if (FzyeBBH==2){
                        queryPd1.put("bmdm", pd.get("fzdm0"));
                        queryPd1.put("gsdm",Org.get(0).getGsdm());
                        queryPd1.put("kjnd",Org.get(0).getKjnd());
                        pageDataPUBBMXX = pubbmxxMapper._queryyePubbmxx(queryPd1);
                    }
                    queryPd2.put("lbdm", "0");
                    dataPull.put("FZBM", "");
                    dataPull.put("FZMC", "");
                    dataPull.put("FZQC", "");
//                    lbdm,lbmc,lbfj
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("0");
                    if (pageDataGL_Fzxlb != null) {
                        if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                            String fzlxjg = pageDataGL_Fzxlb.get("lbfj").toString();
                            String fzdm = pageDataPUBBMXX.get(0).get("bmdm").toString().trim();
                            List<String> qc = new ArrayList<String>();
                            int jc = 1;
                            if (!StringUtils.isEmpty(fzlxjg)) {
                                String[] fzlxjgStr = fzlxjg.split("-");
                                int num1 = 0;//3  3  2  2  111 111 11
                                if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                    for (int w = 0; w < fzlxjgStr.length; w++) {
                                        num1 = num1 + Integer.valueOf(fzlxjgStr[w].trim());
                                        if (num1 < fzdm.length()) {
                                            List<Map<String, Object>> maps = new ArrayList<>();
                                            if (FzyeBBH==1){
                                                maps = kmxzlxMapper._queryPUBBMXXq(fzdm.substring(0, num1));
                                            }else if (FzyeBBH==2){
                                                Map<String,Object> bmfzdm = new HashMap<>();
                                                bmfzdm.put("bmdm", fzdm.substring(0, num1));
                                                bmfzdm.put("gsdm",datadzzbxx.get("DWDM"));
                                                bmfzdm.put("kjnd",datadzzbxx.get("KJND"));
                                                maps = pubbmxxMapper._queryyePubbmxx(bmfzdm);
                                            }
                                            if(maps.get(0).get("bmmc")!=null){
                                                qc.add(maps.get(0).get("bmmc").toString());
                                            }
                                            dataPull.put("SJFZBM", maps.get(0).get("bmdm").toString());
                                            jc++;
                                        }
                                    }
                                }
                            }
                            qc.add(pageDataPUBBMXX.get(0).get("bmmc").toString());
                            dataPull.put("FZQC", String.join("/", qc));
                            dataPull.put("FZJB", jc);
                            dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                            dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                        }
                    }
                    resultList.add(dataPull);
                }
                if (pd.get("fzdm1") != null && !StringUtils.isEmpty(pd.get("fzdm1").toString().trim())) {
                    dataPull = new HashMap<String, Object>(dataPull);
                    dataPull.put("FZLX", "项目");
                    Map<String, Object> queryPd1 = new HashMap<String, Object>();
                    Map<String, Object> queryPd2 = new HashMap<String, Object>();
                    List<Map<String, Object>> pageDataGL_Xmzl = new ArrayList<>();
                    if (FzyeBBH==1){
                        queryPd1.put("xmdm", pd.get("fzdm1"));
                        pageDataGL_Xmzl = sourceMapper._queryGL_Xmzl(queryPd1);
                    }else if (FzyeBBH==2){
                        queryPd1.put("XMDM", pd.get("fzdm1"));
                        queryPd1.put("KJND", Org.get(0).getKjnd());
                        queryPd1.put("GSDM", Org.get(0).getGsdm());
                        pageDataGL_Xmzl = xmzlMapper._queryYeXmzl(queryPd1);
                    }
                    queryPd2.put("lbdm", "1");
                    dataPull.put("FZBM", "");
                    dataPull.put("FZMC", "");
                    dataPull.put("FZQC", "");
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("1");
                    if (pageDataGL_Fzxlb != null) {
                        if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                            String fzlxjg = pageDataGL_Fzxlb.get("lbfj").toString();
                            String fzdm = "";
                            if(FzyeBBH==1){
                                fzdm = pageDataGL_Xmzl.get(0).get("XMDM").toString().trim();
                            }else if(FzyeBBH==2){
                                fzdm = pageDataGL_Xmzl.get(0).get("xmdm").toString().trim();
                            }
                            List<String> qc = new ArrayList<String>();
                            int jc = 1;
                            if (!StringUtils.isEmpty(fzlxjg)) {
                                String[] fzlxjgStr = fzlxjg.split("-");
                                int num1 = 0;//3  3  2  2  111 111 11
                                if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                    for (int w = 0; w < fzlxjgStr.length; w++) {
                                        num1 = num1 + Integer.valueOf(fzlxjgStr[w].trim());
                                        if (num1 < fzdm.length()) {
                                            List<Map<String, Object>> maps = new ArrayList<>();
                                            if (FzyeBBH==1){
                                                maps = kmxzlxMapper._queryXMZLq(fzdm.substring(0, num1));
                                                if(maps.get(0).get("XMMC")!=null){
                                                    qc.add(maps.get(0).get("XMMC").toString());
                                                }
                                                dataPull.put("SJFZBM", maps.get(0).get("XMDM").toString());
                                            }else if (FzyeBBH==2){
                                                Map<String, Object> xmzldm = new HashMap<String, Object>();
                                                xmzldm.put("XMDM", fzdm.substring(0, num1));
                                                xmzldm.put("KJND", datadzzbxx.get("KJND"));
                                                xmzldm.put("GSDM", Org.get(0).getGsdm());
                                                maps = xmzlMapper._queryYeXmzl(xmzldm);
                                                if(maps.get(0).get("xmmc")!=null){
                                                    qc.add(maps.get(0).get("xmmc").toString());
                                                }
                                                dataPull.put("SJFZBM", fzdm.substring(0, num1));
                                            }

                                            jc++;
                                        }
                                    }
                                }
                            }
                            if (FzyeBBH==1){
                                qc.add(pageDataGL_Xmzl.get(0).get("XMMC").toString());
                                dataPull.put("FZQC", String.join("/", qc));
                                dataPull.put("FZJB", jc);
                                dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                                dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                            }else if (FzyeBBH==2){
                                qc.add(pageDataGL_Xmzl.get(0).get("xmmc").toString());
                                dataPull.put("FZQC", String.join("/", qc));
                                dataPull.put("FZJB", jc);
                                dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("xmdm"));
                                dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("xmmc"));
                            }

                        }
                    }
                    resultList.add(dataPull);
                }
                if (FzyeBBH==2 && pd.get("fzdm2") != null && !StringUtils.isEmpty(pd.get("fzdm2").toString().trim())) {
                    dataPull=new HashMap<String,Object>(dataPull);
                    dataPull.put("FZLX", "个人往来");
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("dwdm", pd.get("fzdm2"));
                    queryPd.put("kjnd", Org.get(0).getKjnd());
                    queryPd.put("gsdm", Org.get(0).getGsdm());
                    List<Map<String, Object>> pageDataPUBKSZL = pubkszlMapper._queryYePubkszl(queryPd);
                    queryPd.put("lbdm", "2");
                    dataPull.put("FZBM", "");
                    dataPull.put("FZMC", "");
                    dataPull.put("FZQC", "");
                    dataPull.put("SJFZBM", " ");
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("2");
                    if (pageDataGL_Fzxlb != null) {
                        if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                            String fzlxjg = pageDataGL_Fzxlb.get("lbfj").toString();
                            String fzdm = pageDataPUBKSZL.get(0).get("dwdm").toString().trim();
                            List<String> qc = new ArrayList<String>();
                            int jc = 1;
                            if (!StringUtils.isEmpty(fzlxjg)) {
                                String[] fzlxjgStr = fzlxjg.split("-");
                                int num1 = 0;//3  3  2  2  111 111 11
                                if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                    for (int w = 0; w < fzlxjgStr.length; w++) {
                                        num1 = num1 + Integer.valueOf(fzlxjgStr[w].trim());
                                        if (num1 < fzdm.length()) {
                                            Map<String, Object> pubkszldm = new HashMap<String, Object>();
                                            pubkszldm.put("dwdm", fzdm.substring(0, num1));
                                            pubkszldm.put("kjnd", datadzzbxx.get("KJND"));
                                            pubkszldm.put("gsdm", Org.get(0).getGsdm());
                                            List<Map<String, Object>> maps = pubkszlMapper._queryYePubkszl(pubkszldm);
                                            if(maps.get(0).get("dwmc")!=null){
                                                qc.add(maps.get(0).get("dwmc").toString());
                                            }
                                            dataPull.put("SJFZBM", maps.get(0).get("dwdm").toString());
                                            jc++;
                                        }
                                    }
                                }
                            }
                            qc.add(pageDataPUBKSZL.get(0).get("dwmc").toString());
                            dataPull.put("FZQC", String.join("/", qc));
                            dataPull.put("FZJB", jc);
                            dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                            dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                        }
                    }
                    resultList.add(dataPull);
                }
                if (pd.get("fzdm3") != null && !StringUtils.isEmpty(pd.get("fzdm3").toString().trim())) {
                    dataPull = new HashMap<String, Object>(dataPull);
                    dataPull.put("FZLX", "单位往来");
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    List<Map<String, Object>> pageDataPUBKSZL = new ArrayList<>();
                    if (FzyeBBH==1){
                        queryPd.put("wldm", pd.get("fzdm3"));
                        pageDataPUBKSZL = sourceMapper._queryPUBKSZL(queryPd);
                    }else if (FzyeBBH==2){
                        queryPd.put("dwdm", pd.get("fzdm3"));
                        queryPd.put("kjnd", Org.get(0).getKjnd());
                        queryPd.put("gsdm", Org.get(0).getGsdm());
                        pageDataPUBKSZL = pubkszlMapper._queryYePubkszl(queryPd);
                    }
                    queryPd.put("lbdm", "3");
                    dataPull.put("FZBM", "");
                    dataPull.put("FZMC", "");
                    dataPull.put("FZQC", "");
                    Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get("3");
                    if (pageDataGL_Fzxlb != null) {
                        if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                            String fzlxjg = pageDataGL_Fzxlb.get("lbfj").toString();
                            String fzdm = pageDataPUBKSZL.get(0).get("dwdm").toString().trim();
                            List<String> qc = new ArrayList<String>();
                            int jc = 1;
                            if (!StringUtils.isEmpty(fzlxjg)) {
                                String[] fzlxjgStr = fzlxjg.split("-");
                                int num1 = 0;//3  3  2  2  111 111 11
                                if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                    for (int w = 0; w < fzlxjgStr.length; w++) {
                                        num1 = num1 + Integer.valueOf(fzlxjgStr[w].trim());
                                        if (num1 < fzdm.length()) {
                                            List<Map<String, Object>> maps= new ArrayList<>();
                                            if (FzyeBBH==1){
                                                maps = kmxzlxMapper._queryPUBKSZLq(fzdm.substring(0, num1));
                                            }else if (FzyeBBH==2){
                                                Map<String, Object> pubkszldm = new HashMap<String, Object>();
                                                pubkszldm.put("dwdm", fzdm.substring(0, num1));
                                                pubkszldm.put("kjnd", datadzzbxx.get("KJND"));
                                                pubkszldm.put("gsdm", Org.get(0).getGsdm());
                                                maps = pubkszlMapper._queryYePubkszl(pubkszldm);
                                            }
                                            if (maps.size() > 0 && maps != null){
                                                if(maps.get(0).get("dwmc")!=null){
                                                    qc.add(maps.get(0).get("dwmc").toString());
                                                }
                                                dataPull.put("SJFZBM", maps.get(0).get("dwdm").toString());
                                            }
                                            jc++;
                                        }
                                    }
                                }
                            }
                            qc.add(pageDataPUBKSZL.get(0).get("dwmc").toString());
                            dataPull.put("FZQC", String.join("/", qc));
                            dataPull.put("FZJB", jc);
                            dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                            dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                        }
                    }

                    resultList.add(dataPull);
                }
                for (int q = 4; q < 31; q++) {
                    dataPull = new HashMap<String, Object>(dataPull);
                    if (pd.get("fzdm" + q) != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString().trim())) {
                        Map<String, Object> queryPd1 = new HashMap<String, Object>();
                        List<Map<String, Object>> pageDataGL_Fzxzl = new ArrayList<>();
                        if (FzyeBBH==1){
                            queryPd1.put("fzdm", pd.get("fzdm" + q));
                            pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd1);
                        }else if (FzyeBBH==2){
                            queryPd1.put("fzdm", pd.get("fzdm" + q));
                            queryPd1.put("kjnd", datadzzbxx.get("KJND"));
                            queryPd1.put("gsdm", Org.get(0).getGsdm());
                            pageDataGL_Fzxzl = fzxzlMapper._queryYeFzxzl(queryPd1);
                        }
                        Map<String, Object> pageDataGL_Fzxlb = (Map<String, Object>) stringObjectMap.get(String.valueOf(q).trim());
                        Map<String, Object> queryPd2 = new HashMap<String, Object>();
                        queryPd2.put("lbdm", String.valueOf(q));
                        dataPull.put("FZLX", pageDataGL_Fzxlb.get("lbmc"));
                        dataPull.put("FZBM", pd.get("fzdm" + q));
                        dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                        String lbfj = pageDataGL_Fzxlb.get("lbfj").toString();
                        String[] lbfjStr = lbfj.split("-");
                        String result = pd.get("fzdm" + q).toString();
                        int numx = 0;
                        for (int w = 0; w < lbfjStr.length; w++) {
                            numx = numx + Integer.valueOf(lbfjStr[w].trim());
                            if (numx <= result.length()) {
                                dataPull.put("SJFZBM", result.substring(0, numx - Integer.valueOf(lbfjStr[w].trim())));
                            }
                            if (numx == result.length()) {
                                dataPull.put("FZJB", (w + 1));
                            }
                        }
                        resultList.add(dataPull);
                    }
                }
            }
        }
        return resultList;
    }



    public List ZtcsStr(String KJDZZBBH){
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        Map<String, Object> ztcsStr = new HashMap<String, Object>();
        ztcsStr.put("kjnd",Org.get(0).getKjnd());
        ztcsStr.put("ztbh",Org.get(0).getZtbh());
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcszh(ztcsStr);
        return pageDataGL_Ztcs;
    }

    @Transactional("targetTransactionManager")
    public boolean fzyeBb(List<Map<String, Object>> resultListNew){
        List<String> resultMapListStr = new ArrayList<String>();
        List<String> resultMapHaveListStr = new ArrayList<String>();
        List<Map<String, Object>> resultListNew2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultListNew2Have = new ArrayList<Map<String, Object>>();
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map<String, Object> map : resultListNew
            ) {
                if (!resultMapListStr.contains(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM") + "-" + map.get("FZLX") + "-" + map.get("FZBM"))) {
                    resultMapListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM") + "-" + map.get("FZLX") + "-" + map.get("FZBM"));
                    resultListNew2.add(map);
                } else {
                    resultMapHaveListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM") + "-" + map.get("FZLX") + "-" + map.get("FZBM"));
                    resultListNew2Have.add(map);
                }
            }
        }
        for (Map map3 : resultListNew2
        ) {
            for (Map map4 : resultListNew2Have
            ) {
                if ((map3.get("KJDZZBBH") + "-" + map3.get("KJYF") + "-" + map3.get("KJKMBM") + "-" + map3.get("FZLX") + "-" + map3.get("FZBM")).equals(map4.get("KJDZZBBH") + "-" + map4.get("KJYF") + "-" + map4.get("KJKMBM") + "-" + map4.get("FZLX") + "-" + map4.get("FZBM"))) {
                    map3.put("NCJFYE", new BigDecimal(map3.get("NCJFYE").toString()).add(new BigDecimal(map4.get("NCJFYE").toString())));
                    map3.put("NCDFYE", new BigDecimal(map3.get("NCDFYE").toString()).add(new BigDecimal(map4.get("NCDFYE").toString())));
                    map3.put("QCJFYE", new BigDecimal(map3.get("QCJFYE").toString()).add(new BigDecimal(map4.get("QCJFYE").toString())));
                    map3.put("QCDFYE", new BigDecimal(map3.get("QCDFYE").toString()).add(new BigDecimal(map4.get("QCDFYE").toString())));
                    map3.put("JFFSE", new BigDecimal(map3.get("JFFSE").toString()).add(new BigDecimal(map4.get("JFFSE").toString())));
                    map3.put("JFLJFSE", new BigDecimal(map3.get("JFLJFSE").toString()).add(new BigDecimal(map4.get("JFLJFSE").toString())));
                    map3.put("DFFSE", new BigDecimal(map3.get("DFFSE").toString()).add(new BigDecimal(map4.get("DFFSE").toString())));
                    map3.put("DFLJFSE", new BigDecimal(map3.get("DFLJFSE").toString()).add(new BigDecimal(map4.get("DFLJFSE").toString())));
                    map3.put("QMJFYE", new BigDecimal(map3.get("QMJFYE").toString()).add(new BigDecimal(map4.get("QMJFYE").toString())));
                    map3.put("QMDFYE", new BigDecimal(map3.get("QMDFYE").toString()).add(new BigDecimal(map4.get("QMDFYE").toString())));
                }
            }
        }
        if (resultListNew2 != null && resultListNew2.size() > 0) {
            for (Map map1 : resultListNew2
            ) {
                fzyeMapper._add(map1);
            }
            return true;
        }
        return true;
    }

    @Transactional("targetTransactionManager")
    public List<Map<String, Object>> kjkmResult(List<Map<String, Object>> resultList, List<Map<String, Object>> pageDataGL_Ztcs,
                                                String KJDZZBBH) {
        List<Map<String, Object>> resultListNew = new ArrayList<Map<String, Object>>();
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> map : resultList
            ) {
                resultListNew.add(map);
                Integer legth = map.get("KJKMBM").toString().trim().length();
                String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
                String[] lbfjStr = kmbmfa.split("-");
                int num = 0;//8  4 2 2 2 2
                String kmqc = "";
                Map<String, Object> quM = new HashMap<String, Object>();
                List kmdms = new ArrayList();
                for (int w = 0; w < lbfjStr.length; w++) {
                    Map<String, Object> dataPullBase = new HashMap<String, Object>(map);
                    num = num + Integer.valueOf(lbfjStr[w].trim());
                    if (num < legth) {
                        quM.put("kmdm", map.get("KJKMBM").toString().substring(0, num));
                        quM.put("kjnd", Org.get(0).getKjnd());
                        quM.put("gsdm", Org.get(0).getGsdm());
                        quM.put("ZTH", Org.get(0).getZtbh());
                        List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._queryKmxxmx(quM);
                        dataPullBase.put("KJKMBM", map.get("KJKMBM").toString().substring(0, num));
                        dataPullBase.put("KJKMJB", w + 1);
                        dataPullBase.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));

                        kmdms.add(map.get("KJKMBM").toString().substring(0, num));
                        Map<String, Object> queryPd = new HashMap<String, Object>();
                        queryPd.put("kmdms", kmdms);
                        queryPd.put("kjnd", Org.get(0).getKjnd());
                        queryPd.put("gsdm", Org.get(0).getGsdm());
                        queryPd.put("ZTH", Org.get(0).getZtbh());
                        List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                        kmqc = String.join("/", pageDataGL_KMXX1);
                        dataPullBase.put("KJKMQC", kmqc.trim());
                        if (w != 0) {
                            dataPullBase.put("SJKMBM", map.get("KJKMBM").toString().substring(0, num - Integer.valueOf(lbfjStr[w].trim())));
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



}
