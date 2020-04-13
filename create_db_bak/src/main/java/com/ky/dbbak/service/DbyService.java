package com.ky.dbbak.service;

import com.alibaba.fastjson.JSON;
import com.ky.dbbak.entity.OrgEntity;
import com.ky.dbbak.entity.ZtcsEntity;
import com.ky.dbbak.mapper.OrgMapper;
import com.ky.dbbak.mybatis.PagerResult;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.*;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName TargetService
 * @Description: TODO
 * @Author czw
 * @Date 2020/3/24
 **/
@Service
public class DbyService {

    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;
    @Autowired
    DzzbxxMapper dzzbxxMapper;
    @Autowired
    PubkjqjMapper pubkjqjMapper;
    @Autowired
    YebMapper yebMapper;
    @Autowired
    ZtcsMapper ztcsMapper;
    @Autowired
    PznrMapper pznrMapper;
    @Autowired
    GlFzxzlMapper glFzxzlMapper;
    @Autowired
    KmxxMapper kmxxMapper;
    @Autowired
    PzmlMapper pzmlMapper;
    @Autowired
    PzlxMapper pzlxMapper;
    @Autowired
    OrgMapper orgMapper;
    @Autowired
    KjqjdyMapper kjqjdyMapper;
    @Autowired
    KMNCSMapper kmncsMapper;
    @Autowired
    KMYEMapper kmyeMapper;
    @Autowired
    JZPZMapper jzpzMapper;

    public List<Map<String, Object>> kjkmResult(List<Map<String, Object>> resultList, Map<String, Object> pageDataGL_Ztcs) {
        List<Map<String, Object>> resultListNew = new ArrayList<Map<String, Object>>();
        if (resultList != null && resultList.size() > 0) {
            for (Map<String, Object> map : resultList
            ) {
                resultListNew.add(map);
                Integer legth = map.get("KJKMBM").toString().length();
                String kmbmfa = pageDataGL_Ztcs.get("kmbmfa").toString();
                String[] lbfjStr = kmbmfa.split("-");
                int num = 0;//8  4 2 2 2 2
                List<String> kmqc = new ArrayList<String>();
                Map<String, Object> quM = new HashMap<String, Object>();
                for (int w = 0; w < lbfjStr.length; w++) {
                    Map<String, Object> dataPullBase = new HashMap<String, Object>(map);
                    num = num + Integer.valueOf(lbfjStr[w]);
                    if (num < legth) {
                        quM.put("kmdm", map.get("KJKMBM").toString().substring(0, num));
                        List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(quM);
                        dataPullBase.put("KJKMBM", map.get("KJKMBM").toString().substring(0, num));
                        dataPullBase.put("KJKMJC", w + 1);
                        dataPullBase.put("KJKMJB", w + 1);
                        dataPullBase.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                        kmqc.add(pageDataGL_KMXX.get(0).get("kmmc").toString());
                        //kmqc += pageDataGL_KMXX.get(0).get("kmmc").toString().trim() + "/";
//                        Map<String, Object> queryPd = new HashMap<String, Object>();
//                        queryPd.put("kmdms", kmdms);
//                        List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                        String kmqcStr = String.join("/", kmqc);
                        dataPullBase.put("KMQC", kmqcStr.trim());
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
                    //kmqc = kmqc.substring(kmqc.lastIndexOf(kmqc),kmqc.length()-1);
                    //dataPullBase.put("KMQC", kmqc.trim());
                }

            }

        }
        return resultListNew;
    }

    @Transactional("targetTransactionManager")
    public Boolean kjqjdyGService(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<OrgEntity> orgEntities = orgMapper._querySuo(KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        pageData.put("kjnd", dzzbxxList.get(0).get("KJND"));
        pageData.put("gsdm", dzzbxxList.get(0).get("DWDM"));
        pageData.put("ZTH", orgEntities.get(0).getZt());
        List<Map<String, Object>> pubKjqjList = pubkjqjMapper._queryPubKjqj(pageData);
        for (Map<String, Object> pd : pubKjqjList) {
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
            //会计月份
            dataPull.put("KJYF", pd.get("kjqjxh"));
            dataPull.put("KSRQ", pd.get("qsrq"));
            dataPull.put("JZRQ", pd.get("jsrq"));
            resultList.add(dataPull);
        }
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                kjqjdyMapper._add(map1);
            }
            return true;
        }
        return true;
    }

    @Transactional("targetTransactionManager")
    public Boolean kmncsGService(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<OrgEntity> orgEntities = orgMapper._querySuo(KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        pageData.put("kjnd", dzzbxxList.get(0).get("KJND"));
        pageData.put("hsdwdm", dzzbxxList.get(0).get("DWDM"));
        pageData.put("gsdm", dzzbxxList.get(0).get("DWDM"));
        pageData.put("ztbh", orgEntities.get(0).getZt());
        pageData.put("ZTH", orgEntities.get(0).getZt());
        List<Map<String, Object>> glYebList = yebMapper._queryGL_Yeb(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryDwZtcs(pageData);
        for (Map<String, Object> pd : glYebList) {
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
            dataPull.put("BZMC", datadzzbxx.get("BWB"));
            dataPull.put("KJYF", 0);
            dataPull.put("KJKMBM", " ");
            dataPull.put("KJTX", " ");
            dataPull.put("KJKMMC", " ");
            dataPull.put("KMQC", " ");
            dataPull.put("KJKMJC", 0);
            dataPull.put("SFZDJKM", 0);
            dataPull.put("SJKMBM", " ");
            dataPull.put("SFXJHXJDJW", 0);
            dataPull.put("YEFX", 0);
            dataPull.put("BBQCYE", BigDecimal.ZERO);
            dataPull.put("QCSL", BigDecimal.ZERO);
            //dataPull.put("WBQCYE", BigDecimal.ZERO);

            //8.会计月份
            if (new BigDecimal(pd.get("ncd").toString()).compareTo(new BigDecimal("0")) == 0 && new BigDecimal(pd.get("ncj").toString()).compareTo(new BigDecimal("0")) == 0) {
                continue;
            }
            dataPull.put("KJYF", 1);
            dataPull.put("KJKMBM", pd.get("kmdm"));
            List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._queryKmxxmx(pd);
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace("　", ""));
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("D")) {
                    dataPull.put("YEFX", -1);
                    dataPull.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pd.get("ncd").toString()) - Double.valueOf(pd.get("ncj").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("J")) {
                    dataPull.put("YEFX", 1);
                    dataPull.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pd.get("ncj").toString()) - Double.valueOf(pd.get("ncd").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                }

                dataPull.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));

                String kmdm = pd.get("kmdm").toString();
                if (kmdm.length() >= 4) {
                    String substring = kmdm.substring(0, 4);
                    //16.是否现金或现金等价物  赋值0
                    if (substring.equals("1001") || substring.equals("1002") || substring.equals("1011") || substring.equals("1021")) {
                        dataPull.put("SFXJHXJDJW", 1);
                    } else {
                        dataPull.put("SFXJHXJDJW", 0);
                    }
                }
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
                            queryPd.put("gsdm", pd.get("gsdm").toString());
                            queryPd.put("ZTH", pd.get("ZTH").toString());
                            queryPd.put("kjnd", pd.get("kjnd").toString());
                            List<Map<String, Object>> pageDataGL_KMXXQc = kmxxMapper._queryKmxxList(queryPd);
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
                    dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace("　", ""));
                }

            }
            //20.期初数量  赋值0
            dataPull.put("QCSL", new BigDecimal("0"));
            //21.外币期初余额  赋值0
            dataPull.put("WBQCYE", new BigDecimal("0"));
            resultList.add(dataPull);
        }
        List<Map<String, Object>> resultListNew = kjkmResult(resultList, pageDataGL_Ztcs.get(0));

        List<String> resultMapListStr = new ArrayList<String>();
        List<String> resultMapHaveListStr = new ArrayList<String>();
        List<Map<String, Object>> resultListNew2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultListNew2Have = new ArrayList<Map<String, Object>>();
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map<String, Object> map : resultListNew
            ) {
                if (!resultMapListStr.contains(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"))) {
                    resultMapListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"));
                    resultListNew2.add(map);
                } else {
                    resultMapHaveListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"));
                    resultListNew2Have.add(map);
                }

            }
        }
        for (Map map3 : resultListNew2
        ) {
            for (Map map4 : resultListNew2Have
            ) {
                if ((map3.get("KJDZZBBH") + "-" + map3.get("KJYF") + "-" + map3.get("KJKMBM")).equals(map4.get("KJDZZBBH") + "-" + map4.get("KJYF") + "-" + map4.get("KJKMBM"))) {
                    map3.put("BBQCYE", new BigDecimal(map3.get("BBQCYE").toString()).add(new BigDecimal(map4.get("BBQCYE").toString())));
                }
            }
        }
        if (resultListNew2 != null && resultListNew2.size() > 0) {
            for (Map map1 : resultListNew2
            ) {
                kmncsMapper._addKmncs(map1);
            }
            return true;
        }
        return true;
    }

    @Transactional("targetTransactionManager")
    public Boolean kmyeGService(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        List<OrgEntity> orgEntities = orgMapper._querySuo(KJDZZBBH);
        pageData.put("gsdm", dzzbxxList.get(0).get("DWDM"));
        pageData.put("kjnd", dzzbxxList.get(0).get("KJND"));
        pageData.put("ZTH", orgEntities.get(0).getZt());
        pageData.put("hsdwdm", dzzbxxList.get(0).get("DWDM"));
        pageData.put("ztbh", orgEntities.get(0).getZt());
        List<Map<String, Object>> GL_YebList = yebMapper._queryGL_Yeb(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryDwZtcs(pageData);
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
            dataPullBase.put("KJYF", 0);
            dataPullBase.put("KJTX", " ");
            dataPullBase.put("KJKMBM", " ");
            dataPullBase.put("KJKMMC", " ");
            dataPullBase.put("KMQC", " ");
            dataPullBase.put("NCJFYE", BigDecimal.ZERO);
            dataPullBase.put("NCDFYE", BigDecimal.ZERO);
            dataPullBase.put("NCYEFX", 0);
            dataPullBase.put("QCJFYE", BigDecimal.ZERO);
            dataPullBase.put("QCDFYE", BigDecimal.ZERO);
            dataPullBase.put("QCYEFX", 0);
            dataPullBase.put("QMYEFX", 0);
            dataPullBase.put("FLS", 0);
            dataPullBase.put("KJKMJB", 0);
            dataPullBase.put("SFZDJKM", 0);
            dataPullBase.put("SFXJHXJDJW", 0);
            dataPullBase.put("SJKMBM", " ");
            dataPullBase.put("BZMC", " ");
            dataPullBase.put("BZDM", " ");
            dataPullBase.put("JFFSE", BigDecimal.ZERO);
            dataPullBase.put("JFLJFSE", BigDecimal.ZERO);
            dataPullBase.put("DFFSE", BigDecimal.ZERO);
            dataPullBase.put("DFLJFSE", BigDecimal.ZERO);
            dataPullBase.put("QMJFYE", BigDecimal.ZERO);
            dataPullBase.put("QMDFYE", BigDecimal.ZERO);

            BigDecimal jfljfse = new BigDecimal("0");
            BigDecimal dfljfse = new BigDecimal("0");
            BigDecimal qmjfye = new BigDecimal("0");
            BigDecimal qmdfye = new BigDecimal("0");

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
                //11.会计科目名称
                List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._queryKmxxmx(pageData);
                if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                    dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace("　", ""));
                    dataPull.put("KJKMBM", pd.get("kmdm"));
                    dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
                    dataPull.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                    String kmdm = pageDataGL_KMXX.get(0).get("kmdm").toString();
                    Integer legth = pageDataGL_KMXX.get(0).get("kmdm").toString().length();
                    if (legth > 4) {

                        String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
                        String[] lbfjStr = kmbmfa.split("-");
                        int num = 0;
                        List<String> kmdms = new ArrayList<String>();
                        for (int w = 0; w < lbfjStr.length; w++) {
                            num = num + Integer.valueOf(lbfjStr[w]);
                            if (num <= kmdm.length()) {
                                kmdms.add(kmdm.substring(0, num));
                            }
                            if (legth == num) {
                                dataPull.put("KJKMJB", w + 1);
                                dataPull.put("SJKMBM", pd.get("kmdm").toString().substring(0, num - Integer.valueOf(lbfjStr[w])));
                            }
                        }
                        Map<String, Object> queryPd = new HashMap<String, Object>();
                        queryPd.put("kmdms", kmdms);
                        List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                        String kjkmqc = String.join("/", pageDataGL_KMXX1);
                        kjkmqc = kjkmqc.trim();
                        kjkmqc = kjkmqc.replace("　", "");
                        dataPull.put("KMQC", kjkmqc);
                    } else {
                        dataPull.put("KJKMJB", 1);
                        dataPull.put("SJKMBM", " ");
                        dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace("　", ""));
                    }
                }


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

                //19.外币年初借方余额//赋值0
                dataPull.put("WBNCJFYE", new BigDecimal("0"));
                //20.外币年初贷方余额//赋值0
                dataPull.put("WBNCDFYE", new BigDecimal("0"));
                //21.外币期初借方余额//赋值0
                dataPull.put("WBQCJFYE", new BigDecimal("0"));
                //22.外币期初贷方余额//赋值0
                dataPull.put("WBQCDFYE", new BigDecimal("0"));
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
                qCountPd.put("kmdm", pd.get("kmdm"));
                if (i > 9) {
                    qCountPd.put("kjqj", datadzzbxx.get("KJND").toString() + i);
                } else {
                    qCountPd.put("kjqj", datadzzbxx.get("KJND") + "0" + i);
                }
                qCountPd.put("gsdm", pd.get("gsdm").toString());
                qCountPd.put("ZTH", pd.get("ZTH").toString());
                long num = pznrMapper._queryByGPznrCount(qCountPd);
                dataPull.put("FLS", num);
                //40.是否现金或现金等价物  //赋值0
                String kmdm = pd.get("kmdm").toString();
                if (kmdm.length() >= 4) {
                    String substring = kmdm.substring(0, 4);
                    //16.是否现金或现金等价物  赋值0
                    if (substring.equals("1001") || substring.equals("1002") || substring.equals("1011") || substring.equals("1021")) {
                        dataPull.put("SFXJHXJDJW", 1);
                    } else {
                        dataPull.put("SFXJHXJDJW", 0);
                    }

                }
                //41.币种名称 // 人民币
                dataPull.put("BZMC", datadzzbxx.get("BWB"));
                //42.币种代码//为空
                dataPull.put("BZDM", " ");
                resultList.add(dataPull);
            }
        }

        List<Map<String, Object>> resultListNew = kjkmResult(resultList, pageDataGL_Ztcs.get(0));

        List<String> resultMapListStr = new ArrayList<String>();
        List<String> resultMapHaveListStr = new ArrayList<String>();
        List<Map<String, Object>> resultListNew2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultListNew2Have = new ArrayList<Map<String, Object>>();
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map<String, Object> map : resultListNew
            ) {
                if (!resultMapListStr.contains(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"))) {
                    resultMapListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"));
                    resultListNew2.add(map);
                } else {
                    resultMapHaveListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"));
                    resultListNew2Have.add(map);
                }

            }
        }
        for (Map map3 : resultListNew2
        ) {
            for (Map map4 : resultListNew2Have
            ) {
                if ((map3.get("KJDZZBBH") + "-" + map3.get("KJYF") + "-" + map3.get("KJKMBM")).equals(map4.get("KJDZZBBH") + "-" + map4.get("KJYF") + "-" + map4.get("KJKMBM"))) {
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
                kmyeMapper._add(map1);
            }
            return true;
        }
        return true;
    }

    private Map<String, List<Map<String, Object>>> jzpznr(List<Map<String, Object>> bypznrList) {
        List<String> noList = new ArrayList<String>();
        for (Map<String, Object> map : bypznrList
        ) {
            if (!noList.contains(map.get("jdbz").toString() + "-" + map.get("IDPZH").toString() + "-" + map.get("KJTXDM").toString())) {
                noList.add(map.get("jdbz").toString() + "-" + map.get("IDPZH").toString() + "-" + map.get("KJTXDM").toString());
            }
        }
        Map<String, List<Map<String, Object>>> resultMap = new HashMap<String, List<Map<String, Object>>>();
        for (String str : noList
        ) {
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : bypznrList
            ) {
                if ((map.get("jdbz").toString() + "-" + map.get("IDPZH").toString() + "-" + map.get("KJTXDM").toString()).equals(str)) {
                    mapList.add(map);
                }
            }
            resultMap.put(str, mapList);
        }
        return resultMap;
    }

    @Transactional("targetTransactionManager")
    public int jzpzGService(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        List<OrgEntity> orgEntities = orgMapper._querySuo(KJDZZBBH);
        pageData.put("gsdm", dzzbxxList.get(0).get("DWDM"));
        pageData.put("kjnd", dzzbxxList.get(0).get("KJND"));
        pageData.put("ZTH", orgEntities.get(0).getZt());
        pageData.put("hsdwdm", dzzbxxList.get(0).get("DWDM"));
        pageData.put("ztbh", orgEntities.get(0).getZt());
        List<Map<String, Object>> bypznrList = pznrMapper._queryPznr_G(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryDwZtcs(pageData);
        Map<String, List<Map<String, Object>>> jzpznrList = jzpznr(bypznrList);
        System.out.println(JSON.toJSONString(jzpznrList));
        for (Map<String, Object> pd : bypznrList) {
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            dataPull.put("KJYF", 0);
            dataPull.put("JZPZRQ", " ");
            dataPull.put("JZLXBH", " ");
            dataPull.put("JZLXMC", " ");
            dataPull.put("JZPZZL", " ");
            dataPull.put("JZPZBH", " ");
            dataPull.put("JZPZHH", " ");
            dataPull.put("FLXH", " ");
            dataPull.put("JZPZZY", " ");
            dataPull.put("KJTX", " ");
            dataPull.put("KJKMBM", " ");
            dataPull.put("KJKMMC", " ");
            dataPull.put("KMQC", " ");
            dataPull.put("DFKMBM", " ");
            dataPull.put("DFKMMC", " ");
            dataPull.put("BZ", " ");
            dataPull.put("JSFS", " ");
            dataPull.put("ZDRY", " ");
            dataPull.put("FHRY", " ");
            dataPull.put("JZRY", " ");
            dataPull.put("CNRY", " ");
            dataPull.put("CWZG", " ");
            dataPull.put("YPZH", " ");
            dataPull.put("JZBZ", " ");
            dataPull.put("ZFBZ", " ");
            dataPull.put("SFJZ", " ");
            dataPull.put("SFWYSZ", " ");
            dataPull.put("ZFDJBH", " ");
            dataPull.put("GNKMDM", " ");
            dataPull.put("GNKMMC", " ");
            dataPull.put("JJKMDM", " ");
            dataPull.put("JJKMMC", " ");
            dataPull.put("FJS", 0);
            dataPull.put("JFFSE", BigDecimal.ZERO);
            dataPull.put("DFFSE", BigDecimal.ZERO);
            dataPull.put("HL", BigDecimal.ZERO);
            dataPull.put("SL", BigDecimal.ZERO);
            dataPull.put("DJ", BigDecimal.ZERO);
            //8.会计月份，
            String kjqj = pd.get("kjqj").toString();
            if (!StringUtils.isEmpty(kjqj)) {
                Integer mouth = Integer.valueOf(kjqj.substring(kjqj.length() - 2, kjqj.length()));
                dataPull.put("KJYF", mouth);
                //9.记账凭证日期
                Map pageData1 = new HashMap();
                pageData1.put("IDPZH", pd.get("IDPZH"));
                List<Map<String, Object>> pageDataPzmlList = pzmlMapper._queryPzmlG(pageData1);
                if (pageDataPzmlList != null && pageDataPzmlList.size() > 0) {
                    dataPull.put("JZPZRQ", pageDataPzmlList.get(0).get("pzrq"));
                    //32.附件数
                    dataPull.put("FJS", Integer.parseInt(pageDataPzmlList.get(0).get("fjzs").toString()));
                    //33.制单人员
                    dataPull.put("ZDRY", pageDataPzmlList.get(0).get("sr"));
                    //34.复核人员
                    dataPull.put("FHRY", pageDataPzmlList.get(0).get("sh"));
                    //35.记账人员
                    dataPull.put("JZRY", pageDataPzmlList.get(0).get("jzr"));
                    //36.出纳人员
                    dataPull.put("CNRY", pageDataPzmlList.get(0).get("CN"));
                    //37.财务主管
                    dataPull.put("CWZG", pageDataPzmlList.get(0).get("kjzg"));
                    //38.源凭证号
                    String pzly = pageDataPzmlList.get(0).get("pzly").toString();
                    if (pzly.equals("") || StringUtils.isEmpty(pzly)) {
                        dataPull.put("YPZH", "");
                        //41.是否结转
                        dataPull.put("SFJZ", "0");
                    } else {
                        dataPull.put("YPZH", pzly);
                        //41.是否结转
                        dataPull.put("SFJZ", "1");
                    }
                    //39.记账标志 0=作废；1=未审核；2=已审核；3=已记帐
                    String zt = pageDataPzmlList.get(0).get("zt").toString();
                    if (zt != null && !zt.equals("")) {
                        switch (zt) {
                            case "1":
                                dataPull.put("JZBZ", "0");
                                //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                                dataPull.put("ZFBZ", "0");
                                break;
                            case "2":
                                dataPull.put("JZBZ", "0");
                                //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                                dataPull.put("ZFBZ", "0");
                                break;
                            case "3":
                                dataPull.put("JZBZ", "1");
                                //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                                dataPull.put("ZFBZ", "0");
                                break;
                            default:
                                dataPull.put("JZBZ", "0");
                                //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                                dataPull.put("ZFBZ", "1");
                                break;
                        }
                    }
//                } else {
//                    dataPull.put("JZPZRQ", " ");
//                    //32.附件数
//                    dataPull.put("FJS", " ");
//                    //33.制单人员
//                    dataPull.put("ZDRY", " ");
//                    //34.复核人员
//                    dataPull.put("FHRY", " ");
//                    //35.记账人员
//                    dataPull.put("JZRY", " ");
//                    //36.出纳人员
//                    dataPull.put("CNRY", " ");
//                    //37.财务主管
//                    dataPull.put("CWZG", " ");
//                    //38.源凭证号
//
//                    dataPull.put("YPZH", " ");
//                    //41.是否结转
//                    dataPull.put("SFJZ", " ");
//                    //39.记账标志 0=作废；1=未审核；2=已审核；3=已记帐
//                    dataPull.put("JZBZ", " ");
//                    //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
//                    dataPull.put("ZFBZ", " ");
                    return 2;
                }

                //10.记账类型编号
//                dataPull.put("JZLXBH", pd.get("PZLXDM").toString());
                //11.记账类型名称
                pageData.put("pzlxdm", pd.get("PZLXDM").toString());
                List<Map<String, Object>> pageDatePzlxList = pzlxMapper._queryPzlxG(pageData);
                String pzlxmc = "";
                if (pageDatePzlxList != null && pageDatePzlxList.size() > 0) {
                    pzlxmc = pageDatePzlxList.get(0).get("pzlxmc").toString();
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
                String pzhpj = dataPull.get("KJYF").toString() + pzh + flh;
                //12.记账凭证种类
                if (pd.get("KJTXDM") != null && pd.get("KJTXDM").toString().equals("01")) {
                    dataPull.put("JZPZZL", "财记");
                    dataPull.put("JZLXMC", "财记");
                    dataPull.put("JZLXBH", "财记");
                    dataPull.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
                            + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
                            + "-" + "财记" + "-" + pd.get("pzh") + "-" + pzhpj + "-" + pd.get("KJTXDM"));

                } else if (pd.get("KJTXDM") != null && pd.get("KJTXDM").toString().equals("02")) {
                    dataPull.put("JZPZZL", "预记");
                    dataPull.put("JZLXMC", "预记");
                    dataPull.put("JZLXBH", "预记");
                    dataPull.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
                            + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
                            + "-" + "预记" + "-" + pd.get("pzh") + "-" + pzhpj + "-" + pd.get("KJTXDM"));

                }

//                dataPull.put("JZPZZL", pzlxmc);
                //11.记账类型名称
                dataPull.put("JZPZBH", pzlxmc);
                //13.记账凭证编号
                dataPull.put("JZPZBH", pd.get("pzh"));
                //14.记账凭证行号
//                dataPull.put("JZPZHH", pd.get("flh"));
                dataPull.put("JZPZHH", (pzhpj));

                //15.分录序号
//                dataPull.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
//                        + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
//                        + "-" + pd.get("PZLXDM") + "-" + pd.get("pzh") + "-" + pd.get("flh") + "-" + pd.get("KJTXDM"));
                //16.记账凭证摘要
                dataPull.put("JZPZZY", pd.get("zy"));
                //17.会计体系01会计，02预算
                dataPull.put("KJTX", pd.get("KJTXDM"));
                //18.会计科目编码
                dataPull.put("KJKMBM", pd.get("kmdm"));
                //19.会计科目名称
//                List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._querykmxx(pd);
//                String kmmc = pageDataGL_KMXX.get(0).get("kmmc").toString();
                //20.科目全称   货币资金/自有资金
                String kmdm = pd.get("kmdm").toString();
                String kjkmqc = "";
                if (!StringUtils.isEmpty(kmdm) && kmdm != null) {
                    if (kmdm.length() == 4) {
                        dataPull.put("KJKMMC", pd.get("kmmc").toString().trim().replace("　", ""));
                        dataPull.put("KMQC", pd.get("kmmc").toString().trim().replace("　", ""));
                    } else {
                        dataPull.put("KJKMMC", pd.get("kmmc").toString().trim().replace("　", ""));
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
                        queryPd.put("gsdm", pd.get("gsdm").toString());
                        queryPd.put("ZTH", pd.get("ZTH").toString());
                        List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                        kjkmqc = String.join("/", pageDataGL_KMXX1);
                        dataPull.put("KMQC", kjkmqc);
                    }
                } else {
                    dataPull.put("KMQC", "");
                }
                if (pd.get("jdbz").equals("借")) {
                    dataPull.put("JFFSE", new BigDecimal(pd.get("je").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("DFFSE", new BigDecimal("0"));
                    List<Map<String, Object>> pznrDDList = new ArrayList<Map<String, Object>>();
                    List<Map<String, Object>> pznrJJList = new ArrayList<Map<String, Object>>();
                    Map<String, List<Map<String, Object>>> jzpznrListMap = new HashMap<>(jzpznrList);
                    pznrDDList = jzpznrListMap.get("贷" + "-" + pd.get("IDPZH").toString() + "-" + pd.get("KJTXDM").toString());
                    if (pznrDDList != null && pznrDDList.size() > 0) {
                        pznrDDList.remove(pd);
                    }
                    pznrJJList = jzpznrListMap.get("借" + "-" + pd.get("IDPZH").toString() + "-" + pd.get("KJTXDM").toString());
                    if (pznrJJList != null && pznrJJList.size() > 0) {

                        if (pznrJJList != null && pznrJJList.size() > 0) {

                            for (Map<String, Object> map : pznrJJList
                            ) {
                                Double je = Double.valueOf(map.get("je").toString());
                                if (je < 0) {
                                    if (pznrDDList != null && pznrDDList.size() > 0) {
                                        pznrDDList = new ArrayList<>(pznrDDList);
                                        pznrDDList.add(map);
                                    } else {
                                        pznrDDList = new ArrayList<>();
                                        pznrDDList.add(map);
                                    }

                                }
                            }
                        }

                    }
                    List<String> kmdms = new ArrayList<String>();
                    List<String> kmdmmcs = new ArrayList<String>();
                    if (pznrDDList != null && pznrDDList.size() > 0) {

                        for (Map<String, Object> map : pznrDDList
                        ) {
                            kmdms.add(map.get("kmdm").toString());
                            kmdmmcs.add(map.get("kmmc").toString().trim().replace("　", ""));
                        }
                    }
                    if (kmdms != null && kmdms.size() > 0) {
                        if (kmdms != null && kmdms.size() > 0) {

                            HashSet set = new HashSet(kmdms);
                            kmdms.clear();
                            kmdms.addAll(set);
                            set.clear();
                            HashSet set1 = new HashSet(kmdmmcs);
                            kmdmmcs.clear();
                            kmdmmcs.addAll(set1);
                            set1.clear();
                            if (kmdmmcs != null && kmdmmcs.size() > 0) {
                                dataPull.put("DFKMMC", String.join("/", kmdmmcs));
                            } else {
                                dataPull.put("DFKMMC", " ");
                            }
                            if (kmdms != null && kmdms.size() > 0) {
                                dataPull.put("DFKMBM", String.join("/", kmdms));
                            } else {
                                dataPull.put("DFKMBM", " ");
                            }
                        }
                    }
                } else {
                    dataPull.put("JFFSE", new BigDecimal("0"));
                    dataPull.put("DFFSE", new BigDecimal(pd.get("je").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    Map<String, List<Map<String, Object>>> jzpznrListMap = new HashMap<>(jzpznrList);
                    List<Map<String, Object>> pznrDDList = new ArrayList<Map<String, Object>>();
                    List<Map<String, Object>> pznrJJList = new ArrayList<Map<String, Object>>();
                    pznrDDList = jzpznrListMap.get("借" + "-" + pd.get("IDPZH").toString() + "-" + pd.get("KJTXDM").toString());
                    pznrJJList = jzpznrListMap.get("贷" + "-" + pd.get("IDPZH").toString() + "-" + pd.get("KJTXDM").toString());
                    if (pznrDDList != null && pznrDDList.size() > 0) {
                        pznrDDList.remove(pd);
                    }
                    if (pznrJJList != null && pznrJJList.size() > 0) {
                        if (pznrJJList != null && pznrJJList.size() > 0) {

                            for (Map<String, Object> map : pznrJJList
                            ) {
                                Double je = Double.valueOf(map.get("je").toString());
                                if (je < 0) {
                                    if (pznrDDList != null && pznrDDList.size() > 0) {
                                        pznrDDList = new ArrayList<>(pznrDDList);
                                        pznrDDList.add(map);
                                    } else {
                                        pznrDDList = new ArrayList<>();
                                        pznrDDList.add(map);
                                    }
                                }
                            }
                        }
                    }
                    List<String> kmdms = new ArrayList<String>();
                    List<String> kmdmmcs = new ArrayList<String>();
                    if (pznrDDList != null && pznrDDList.size() > 0) {

                        for (Map<String, Object> map : pznrDDList
                        ) {
                            kmdms.add(map.get("kmdm").toString());
                            kmdmmcs.add(map.get("kmmc").toString().trim().replace("　", ""));
                        }
                    }
                    if (kmdms != null && kmdms.size() > 0) {
                        if (kmdms != null && kmdms.size() > 0) {

                            HashSet set = new HashSet(kmdms);
                            kmdms.clear();
                            kmdms.addAll(set);
                            set.clear();
                            HashSet set1 = new HashSet(kmdmmcs);
                            kmdmmcs.clear();
                            kmdmmcs.addAll(set1);
                            set1.clear();
                            if (kmdmmcs != null && kmdmmcs.size() > 0) {
                                dataPull.put("DFKMMC", String.join("/", kmdmmcs));
                            } else {
                                dataPull.put("DFKMMC", " ");
                            }
                            if (kmdms != null && kmdms.size() > 0) {
                                dataPull.put("DFKMBM", String.join("/", kmdms));
                            } else {
                                dataPull.put("DFKMBM", " ");
                            }
                        }
                    }

                }
                //25.币种   人民币
                dataPull.put("BZ", "人民币");
                //26借方外币发生额   //为0
                dataPull.put("JFWBFSE", new BigDecimal("0"));
                //27.贷方外币发生额   //为0
                dataPull.put("DFWBFSE", new BigDecimal("0"));
                //28.汇率   //为空
                dataPull.put("HL", new BigDecimal("0"));
                //29.数量   //为0
                dataPull.put("SL", new BigDecimal("0"));
                //30.单价   //为空
                dataPull.put("DJ", new BigDecimal("0"));
                //31.结算方式   //为空
                dataPull.put("JSFS", "");
                //42.是否为预算账
                if (pd.get("KJTXDM").toString().equals("01")) {
                    dataPull.put("SFWYSZ", "0");
                } else {
                    dataPull.put("SFWYSZ", "1");
                }
                //43.支付单据编号   为空
                dataPull.put("ZFDJBH", "");
                //44.功能科目代码
                String fzdm4 = pd.get("fzdm4").toString();
                Map<Object, Object> dataFzxlbMap = new HashMap<>();
                if (!StringUtils.isEmpty(fzdm4)) {
                    dataPull.put("GNKMDM", fzdm4);
                    //45.功能科目名称
                    dataFzxlbMap.put("fzdm", fzdm4);
                    dataFzxlbMap.put("gsdm", pd.get("gsdm").toString());
                    List<Map<String, Object>> fzxzlList = glFzxzlMapper._queryFzdm(dataFzxlbMap);
                    if (fzxzlList.size() > 0 && fzxzlList != null) {
                        dataPull.put("GNKMMC", fzxzlList.get(0).get("fzmc"));
                    } else {
                        dataPull.put("GNKMDM", " ");
                        dataPull.put("GNKMMC", " ");
                    }
                } else {
                    dataPull.put("GNKMDM", " ");
                    dataPull.put("GNKMMC", " ");
                }
                //46.经济科目代码
                String fzdm5 = pd.get("fzdm5").toString();
                Map<Object, Object> dataFzxlbMap5 = new HashMap<>();
                if (!fzdm4.equals("") && !StringUtils.isEmpty(fzdm5)) {
                    dataPull.put("JJKMDM", fzdm5);
                    //45.功能科目名称
                    dataFzxlbMap5.put("fzdm", fzdm5);
                    dataFzxlbMap5.put("gsdm", pd.get("gsdm").toString());
                    //47.经济科目名称
                    List<Map<String, Object>> FzxzlList = glFzxzlMapper._queryGL_Fzxzl(dataFzxlbMap);
                    if (FzxzlList.size() > 0 && FzxzlList != null) {
                        dataPull.put("JJKMMC", FzxzlList.get(0).get("fzmc"));
                    } else {
                        dataPull.put("JJKMDM", " ");
                        dataPull.put("JJKMMC", " ");
                    }
                } else {
                    dataPull.put("JJKMDM", " ");
                    dataPull.put("JJKMMC", " ");
                }
                //48.资金性质代码   //为空
                dataPull.put("ZJXZDM", " ");
                //49.资金性质名称   //为空
                dataPull.put("ZJXZMC", " ");
                //50.指标来源代码   //为空
                dataPull.put("ZBLYDM", " ");
                //51.指标来源名称   //为空
                dataPull.put("ZBLYMC", " ");
                //52.支出类型代码   //为空
                dataPull.put("ZCLXDM", " ");
                //53.支出类型名称   //为空
                dataPull.put("ZCLXMC", " ");
                //54.预算管理类型代码   //为空
                dataPull.put("YSGLLXDM", " ");
                //55.预算管理类型名称   //为空
                dataPull.put("YSGLLXMC", " ");
                //56.支付方式代码   //为空
                dataPull.put("ZFFSDM", " ");
                //57.支付方式名称   //为空
                dataPull.put("ZFFSMC", " ");
                //58.预算项目代码   //为空
                dataPull.put("YSXMDM", " ");
                //59.预算项目名称   //为空
                dataPull.put("YSXMMC", " ");
                //60.项目分类代码   //为空
                dataPull.put("XMFLDM", " ");
                //61.项目分类名称   //为空
                dataPull.put("XMFLMC", " ");
                //62.指标文号名称   //为空
                dataPull.put("ZBWHMC", " ");
                //63.结算方式代码   //为空
                dataPull.put("JSFSDM", " ");
                //64.结算方式名称   //为空
                dataPull.put("JSFSMC", " ");
                resultList.add(dataPull);
            }
        }
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                jzpzMapper._addJzpz(map1);
            }
            return 1;
        }
        return 0;
    }

    public List<ZtcsEntity> queryByZTH(String hsdwdm) {
        return ztcsMapper.queryByZTH(hsdwdm);
    }

    @Transactional("targetTransactionManager")
    public Boolean kjqjdyBService(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        pageData.put("kjnd", dzzbxxList.get(0).get("KJND"));
        List<Map<String, Object>> pubKjqjList = pubkjqjMapper._queryPubKjqj(pageData);
        for (Map<String, Object> pd : pubKjqjList) {
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
            //会计月份
            dataPull.put("KJYF", pd.get("kjqjxh"));
            dataPull.put("KSRQ", pd.get("qsrq"));
            dataPull.put("JZRQ", pd.get("jsrq"));
            //kjqjdyMapper._addKjqjdy(dataPull);
            resultList.add(dataPull);
        }
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                kjqjdyMapper._add(map1);
            }
            return true;
        }
        return true;
    }

    @Transactional("targetTransactionManager")
    public Boolean kmncsBService(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> glYebList = yebMapper._queryGL_Yeb(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
        for (Map<String, Object> pd : glYebList) {
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
            dataPull.put("BZMC", datadzzbxx.get("BWB"));
            dataPull.put("KJYF", 0);
            dataPull.put("KJKMBM", " ");
            dataPull.put("KJTX", " ");
            dataPull.put("KJKMMC", " ");
            dataPull.put("KMQC", " ");
            dataPull.put("KJKMJC", 0);
            dataPull.put("SFZDJKM", 0);
            dataPull.put("SJKMBM", " ");
            dataPull.put("SFXJHXJDJW", 0);
            dataPull.put("YEFX", 0);
            dataPull.put("BBQCYE", BigDecimal.ZERO);
            dataPull.put("QCSL", BigDecimal.ZERO);
            //dataPull.put("WBQCYE", BigDecimal.ZERO);

            //8.会计月份
            if (new BigDecimal(pd.get("ncd").toString()).compareTo(new BigDecimal("0")) == 0 && new BigDecimal(pd.get("ncj").toString()).compareTo(new BigDecimal("0")) == 0) {
                continue;
            }
            dataPull.put("KJYF", 1);
            dataPull.put("KJKMBM", pd.get("kmdm"));
            List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace("　", ""));
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("D")) {
                    dataPull.put("YEFX", -1);
                    dataPull.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pd.get("ncd").toString()) - Double.valueOf(pd.get("ncj").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("J")) {
                    dataPull.put("YEFX", 1);
                    dataPull.put("BBQCYE", BigDecimal.valueOf(Double.valueOf(pd.get("ncj").toString()) - Double.valueOf(pd.get("ncd").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
                }

                dataPull.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));

                String kmdm = pd.get("kmdm").toString();
                if (kmdm.length() >= 4) {
                    String substring = kmdm.substring(0, 4);
                    //16.是否现金或现金等价物  赋值0
                    if (substring.equals("1001") || substring.equals("1002") || substring.equals("1011") || substring.equals("1021")) {
                        dataPull.put("SFXJHXJDJW", 1);
                    } else {
                        dataPull.put("SFXJHXJDJW", 0);
                    }

                }
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
                    dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace("　", ""));
                }

            }
            //20.期初数量  赋值0
            dataPull.put("QCSL", new BigDecimal("0"));
            //21.外币期初余额  赋值0
            dataPull.put("WBQCYE", new BigDecimal("0"));
            resultList.add(dataPull);
        }
        List<Map<String, Object>> resultListNew = kjkmResult(resultList, pageDataGL_Ztcs.get(0));

        List<String> resultMapListStr = new ArrayList<String>();
        List<String> resultMapHaveListStr = new ArrayList<String>();
        List<Map<String, Object>> resultListNew2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultListNew2Have = new ArrayList<Map<String, Object>>();
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map<String, Object> map : resultListNew
            ) {
                if (!resultMapListStr.contains(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"))) {
                    resultMapListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"));
                    resultListNew2.add(map);
                } else {
                    resultMapHaveListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"));
                    resultListNew2Have.add(map);
                }

            }
        }
        for (Map map3 : resultListNew2
        ) {
            for (Map map4 : resultListNew2Have
            ) {
                if ((map3.get("KJDZZBBH") + "-" + map3.get("KJYF") + "-" + map3.get("KJKMBM")).equals(map4.get("KJDZZBBH") + "-" + map4.get("KJYF") + "-" + map4.get("KJKMBM"))) {
                    map3.put("BBQCYE", new BigDecimal(map3.get("BBQCYE").toString()).add(new BigDecimal(map4.get("BBQCYE").toString())));
                }
            }
        }
        if (resultListNew2 != null && resultListNew2.size() > 0) {
            for (Map map1 : resultListNew2
            ) {
                kmncsMapper._addKmncs(map1);
            }
            return true;
        }
        return true;
    }

    @Transactional("targetTransactionManager")
    public Boolean kmyeBService(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = yebMapper._queryAllYeb(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
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
            dataPullBase.put("KJYF", 0);
            dataPullBase.put("KJTX", " ");
            dataPullBase.put("KJKMBM", " ");
            dataPullBase.put("KJKMMC", " ");
            dataPullBase.put("KMQC", " ");
            dataPullBase.put("NCJFYE", BigDecimal.ZERO);
            dataPullBase.put("NCDFYE", BigDecimal.ZERO);
            dataPullBase.put("NCYEFX", 0);
            dataPullBase.put("QCJFYE", BigDecimal.ZERO);
            dataPullBase.put("QCDFYE", BigDecimal.ZERO);
            dataPullBase.put("QCYEFX", 0);
            dataPullBase.put("QMYEFX", 0);
            dataPullBase.put("FLS", 0);
            dataPullBase.put("KJKMJB", 0);
            dataPullBase.put("SFZDJKM", 0);
            dataPullBase.put("SFXJHXJDJW", 0);
            dataPullBase.put("SJKMBM", " ");
            dataPullBase.put("BZMC", " ");
            dataPullBase.put("BZDM", " ");
            dataPullBase.put("JFFSE", BigDecimal.ZERO);
            dataPullBase.put("JFLJFSE", BigDecimal.ZERO);
            dataPullBase.put("DFFSE", BigDecimal.ZERO);
            dataPullBase.put("DFLJFSE", BigDecimal.ZERO);
            dataPullBase.put("QMJFYE", BigDecimal.ZERO);
            dataPullBase.put("QMDFYE", BigDecimal.ZERO);

            BigDecimal jfljfse = new BigDecimal("0");
            BigDecimal dfljfse = new BigDecimal("0");
            BigDecimal qmjfye = new BigDecimal("0");
            BigDecimal qmdfye = new BigDecimal("0");

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
                //11.会计科目名称
                List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
                if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                    dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace("　", ""));
                    dataPull.put("KJKMBM", pd.get("kmdm"));
                    dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
                    dataPull.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                    String kmdm = pageDataGL_KMXX.get(0).get("kmdm").toString();
                    Integer legth = pageDataGL_KMXX.get(0).get("kmdm").toString().length();
                    if (legth > 4) {

                        String kmbmfa = pageDataGL_Ztcs.get(0).get("kmbmfa").toString();
                        String[] lbfjStr = kmbmfa.split("-");
                        int num = 0;
                        List<String> kmdms = new ArrayList<String>();
                        for (int w = 0; w < lbfjStr.length; w++) {
                            num = num + Integer.valueOf(lbfjStr[w]);
                            if (num <= kmdm.length()) {
                                kmdms.add(kmdm.substring(0, num));
                            }
                            if (legth == num) {
                                dataPull.put("KJKMJB", w + 1);
                                dataPull.put("SJKMBM", pd.get("kmdm").toString().substring(0, num - Integer.valueOf(lbfjStr[w])));
                            }
                        }
                        Map<String, Object> queryPd = new HashMap<String, Object>();
                        queryPd.put("kmdms", kmdms);
                        List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                        String kjkmqc = String.join("/", pageDataGL_KMXX1);
                        kjkmqc = kjkmqc.trim();
                        kjkmqc = kjkmqc.replace("　", "");
                        dataPull.put("KMQC", kjkmqc);
                    } else {
                        dataPull.put("KJKMJB", 1);
                        dataPull.put("SJKMBM", " ");
                        dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace("　", ""));
                    }
                }


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

                //19.外币年初借方余额//赋值0
                dataPull.put("WBNCJFYE", new BigDecimal("0"));
                //20.外币年初贷方余额//赋值0
                dataPull.put("WBNCDFYE", new BigDecimal("0"));
                //21.外币期初借方余额//赋值0
                dataPull.put("WBQCJFYE", new BigDecimal("0"));
                //22.外币期初贷方余额//赋值0
                dataPull.put("WBQCDFYE", new BigDecimal("0"));
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
                qCountPd.put("kmdm", pd.get("kmdm"));
                if (i > 9) {
                    qCountPd.put("kjqj", datadzzbxx.get("KJND").toString() + i);
                } else {
                    qCountPd.put("kjqj", datadzzbxx.get("KJND") + "0" + i);
                }

                long num = pznrMapper._queryByPznrCount(qCountPd);
                dataPull.put("FLS", num);
                //40.是否现金或现金等价物  //赋值0
                String kmdm = pd.get("kmdm").toString();
                if (kmdm.length() >= 4) {
                    String substring = kmdm.substring(0, 4);
                    //16.是否现金或现金等价物  赋值0
                    if (substring.equals("1001") || substring.equals("1002") || substring.equals("1011") || substring.equals("1021")) {
                        dataPull.put("SFXJHXJDJW", 1);
                    } else {
                        dataPull.put("SFXJHXJDJW", 0);
                    }

                }
                //41.币种名称 // 人民币
                dataPull.put("BZMC", datadzzbxx.get("BWB"));
                //42.币种代码//为空
                dataPull.put("BZDM", " ");
                resultList.add(dataPull);
            }
        }

        List<Map<String, Object>> resultListNew = kjkmResult(resultList, pageDataGL_Ztcs.get(0));

        List<String> resultMapListStr = new ArrayList<String>();
        List<String> resultMapHaveListStr = new ArrayList<String>();
        List<Map<String, Object>> resultListNew2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultListNew2Have = new ArrayList<Map<String, Object>>();
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map<String, Object> map : resultListNew
            ) {
                if (!resultMapListStr.contains(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"))) {
                    resultMapListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"));
                    resultListNew2.add(map);
                } else {
                    resultMapHaveListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM"));
                    resultListNew2Have.add(map);
                }

            }
        }

        for (Map map3 : resultListNew2
        ) {
            for (Map map4 : resultListNew2Have
            ) {
                if ((map3.get("KJDZZBBH") + "-" + map3.get("KJYF") + "-" + map3.get("KJKMBM")).equals(map4.get("KJDZZBBH") + "-" + map4.get("KJYF") + "-" + map4.get("KJKMBM"))) {
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
                kmyeMapper._add(map1);
            }
            return true;
        }
        return true;
    }

    @Transactional("targetTransactionManager")
    public int jzpzBService(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> resultAllList = new ArrayList<>();
        List<Map<String, Object>> bypznrList = pznrMapper._queryAll(pageData);
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcs();
        Map<String, List<Map<String, Object>>> jzpznrList = this.jzpznr(bypznrList);
        for (Map<String, Object> pd : bypznrList) {
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            dataPull.put("KJYF", 0);
            dataPull.put("JZPZRQ", " ");
            dataPull.put("JZLXBH", " ");
            dataPull.put("JZLXMC", " ");
            dataPull.put("JZPZZL", " ");
            dataPull.put("JZPZBH", " ");
            dataPull.put("JZPZHH", " ");
            dataPull.put("FLXH", " ");
            dataPull.put("JZPZZY", " ");
            dataPull.put("KJTX", " ");
            dataPull.put("KJKMBM", " ");
            dataPull.put("KJKMMC", " ");
            dataPull.put("KMQC", " ");
            dataPull.put("DFKMBM", " ");
            dataPull.put("DFKMMC", " ");
            dataPull.put("BZ", " ");
            dataPull.put("JSFS", " ");
            dataPull.put("ZDRY", " ");
            dataPull.put("FHRY", " ");
            dataPull.put("JZRY", " ");
            dataPull.put("CNRY", " ");
            dataPull.put("CWZG", " ");
            dataPull.put("YPZH", " ");
            dataPull.put("JZBZ", " ");
            dataPull.put("ZFBZ", " ");
            dataPull.put("SFJZ", " ");
            dataPull.put("SFWYSZ", " ");
            dataPull.put("ZFDJBH", " ");
            dataPull.put("GNKMDM", " ");
            dataPull.put("GNKMMC", " ");
            dataPull.put("JJKMDM", " ");
            dataPull.put("JJKMMC", " ");
            dataPull.put("FJS", 0);
            dataPull.put("JFFSE", BigDecimal.ZERO);
            dataPull.put("DFFSE", BigDecimal.ZERO);
            dataPull.put("HL", BigDecimal.ZERO);
            dataPull.put("SL", BigDecimal.ZERO);
            dataPull.put("DJ", BigDecimal.ZERO);
            //8.会计月份，
            String kjqj = pd.get("kjqj").toString();
            if (!StringUtils.isEmpty(kjqj)) {
                Integer mouth = Integer.valueOf(kjqj.substring(kjqj.length() - 2, kjqj.length()));
                dataPull.put("KJYF", mouth);
                //9.记账凭证日期
                Map pageData1 = new HashMap();
                pageData1.put("IDPZH", pd.get("IDPZH"));
                List<Map<String, Object>> pageDataPzmlList = pzmlMapper._queryPzmlG(pageData1);
                if (pageDataPzmlList != null && pageDataPzmlList.size() > 0) {
                    dataPull.put("JZPZRQ", pageDataPzmlList.get(0).get("pzrq"));
                    //32.附件数
                    dataPull.put("FJS", Integer.parseInt(pageDataPzmlList.get(0).get("fjzs").toString()));
                    //33.制单人员
                    dataPull.put("ZDRY", pageDataPzmlList.get(0).get("sr"));
                    //34.复核人员
                    dataPull.put("FHRY", pageDataPzmlList.get(0).get("sh"));
                    //35.记账人员
                    dataPull.put("JZRY", pageDataPzmlList.get(0).get("jzr"));
                    //36.出纳人员
                    dataPull.put("CNRY", pageDataPzmlList.get(0).get("CN"));
                    //37.财务主管
                    dataPull.put("CWZG", pageDataPzmlList.get(0).get("kjzg"));
                    //38.源凭证号
                    String pzly = pageDataPzmlList.get(0).get("pzly").toString();
                    if (pzly.equals("") || StringUtils.isEmpty(pzly)) {
                        dataPull.put("YPZH", "");
                        //41.是否结转
                        dataPull.put("SFJZ", "0");
                    } else {
                        dataPull.put("YPZH", pzly);
                        //41.是否结转
                        dataPull.put("SFJZ", "1");
                    }
                    //39.记账标志 0=作废；1=未审核；2=已审核；3=已记帐
                    String zt = pageDataPzmlList.get(0).get("zt").toString();
                    if (zt != null && !zt.equals("")) {
                        switch (zt) {
                            case "1":
                                dataPull.put("JZBZ", "0");
                                //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                                dataPull.put("ZFBZ", "0");
                                break;
                            case "2":
                                dataPull.put("JZBZ", "0");
                                //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                                dataPull.put("ZFBZ", "0");
                                break;
                            case "3":
                                dataPull.put("JZBZ", "1");
                                //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                                dataPull.put("ZFBZ", "0");
                                break;
                            default:
                                dataPull.put("JZBZ", "0");
                                //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                                dataPull.put("ZFBZ", "1");
                                break;
                        }
                    }
                } else {
//                    dataPull.put("JZPZRQ", " ");
//                    //32.附件数
//                    dataPull.put("FJS", " ");
//                    //33.制单人员
//                    dataPull.put("ZDRY", " ");
//                    //34.复核人员
//                    dataPull.put("FHRY", " ");
//                    //35.记账人员
//                    dataPull.put("JZRY", " ");
//                    //36.出纳人员
//                    dataPull.put("CNRY", " ");
//                    //37.财务主管
//                    dataPull.put("CWZG", " ");
//                    //38.源凭证号
//
//                    dataPull.put("YPZH", " ");
//                    //41.是否结转
//                    dataPull.put("SFJZ", " ");
//                    //39.记账标志 0=作废；1=未审核；2=已审核；3=已记帐
//                    dataPull.put("JZBZ", " ");
//                    //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
//                    dataPull.put("ZFBZ", " ");
                    return 2;
                }

                //10.记账类型编号
//                dataPull.put("JZLXBH", pd.get("PZLXDM").toString());
                //11.记账类型名称
                List<Map<String, Object>> pageDatePzlxList = pzlxMapper._queryPzlx(pd);
                String pzlxmc = "";
                if (pageDatePzlxList != null && pageDatePzlxList.size() > 0) {
                    pzlxmc = pageDatePzlxList.get(0).get("pzlxmc").toString();
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
                String pzhpj = dataPull.get("KJYF").toString() + pzh + flh;
                //12.记账凭证种类
                if (pd.get("KJTXDM") != null && pd.get("KJTXDM").toString().equals("01")) {
                    dataPull.put("JZPZZL", "财记");
                    dataPull.put("JZLXMC", "财记");
                    dataPull.put("JZLXBH", "财记");
                    dataPull.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
                            + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
                            + "-" + "财记" + "-" + pd.get("pzh") + "-" + pzhpj + "-" + pd.get("KJTXDM"));

                } else if (pd.get("KJTXDM") != null && pd.get("KJTXDM").toString().equals("02")) {
                    dataPull.put("JZPZZL", "预记");
                    dataPull.put("JZLXMC", "预记");
                    dataPull.put("JZLXBH", "预记");
                    dataPull.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
                            + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
                            + "-" + "预记" + "-" + pd.get("pzh") + "-" + pzhpj + "-" + pd.get("KJTXDM"));

                }

//                dataPull.put("JZPZZL", pzlxmc);
                //11.记账类型名称
                dataPull.put("JZPZBH", pzlxmc);
                //13.记账凭证编号
                dataPull.put("JZPZBH", pd.get("pzh"));
                //14.记账凭证行号
//                dataPull.put("JZPZHH", pd.get("flh"));
                dataPull.put("JZPZHH", (pzhpj));

                //15.分录序号
//                dataPull.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
//                        + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
//                        + "-" + pd.get("PZLXDM") + "-" + pd.get("pzh") + "-" + pd.get("flh") + "-" + pd.get("KJTXDM"));
                //16.记账凭证摘要
                dataPull.put("JZPZZY", pd.get("zy"));
                //17.会计体系01会计，02预算
                dataPull.put("KJTX", pd.get("KJTXDM"));
                //18.会计科目编码
                dataPull.put("KJKMBM", pd.get("kmdm"));
                //19.会计科目名称
//                List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._querykmxx(pd);
//                String kmmc = pageDataGL_KMXX.get(0).get("kmmc").toString();
                //20.科目全称   货币资金/自有资金
                String kmdm = pd.get("kmdm").toString();
                String kjkmqc = "";
                if (!StringUtils.isEmpty(kmdm) && kmdm != null) {
                    if (kmdm.length() == 4) {
                        dataPull.put("KJKMMC", pd.get("kmmc").toString().trim().replace("　", ""));
                        dataPull.put("KMQC", pd.get("kmmc").toString().trim().replace("　", ""));
                    } else {
                        dataPull.put("KJKMMC", pd.get("kmmc").toString().trim().replace("　", ""));
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
                    }
                } else {
                    dataPull.put("KMQC", "");
                }
                //21.借方发生额yj1,yj2,yj3
                //List<Map<String, Object>> pageDataYebList = yebMapper._queryGL_Yeb(pd);
                //dataPull.put("JFFSE", new BigDecimal(pageDataYebList.get(0).get("yj" + mouth).toString()));
                //22.贷方发生额 yd1,yd2
                //dataPull.put("DFFSE", new BigDecimal(pageDataYebList.get(0).get("yd" + mouth).toString()));
                //23.对方科目编码

//                String dfkmmc = "";
//                String dfkmbm = "";
//                if (pd.get("jdbz").equals("借")) {
//                    dataPull.put("JFFSE", new BigDecimal(pd.get("je").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
//                    dataPull.put("DFFSE", new BigDecimal("0"));
//                    Map<Object, Object> dmap = new HashMap<>();
//                    dmap.put("IDPZH", pd.get("IDPZH"));
//                    dmap.put("jdbz", "贷");
//                    dmap.put("KJTXDM", pd.get("KJTXDM"));
//                    List<Map<String, Object>> pznrList = pznrMapper._queryByPznr(dmap);
//                    if (pznrList.size() > 0 && pznrList != null) {
//                        //循环list,拼接名字,编码
//                        //pageData.put("DFKMBM", pznrList.get(0).get("kmdm"));
//                        dataPull = getDfkmbmAndDfkmmc(pznrList, dfkmbm, dfkmmc, dataPull);
//                    } else {
//                        List<Map<String, Object>> pznrSmallJeList = pznrMapper._querySmallJe(dmap);
//                        dataPull = getDfkmbmAndDfkmmc(pznrSmallJeList, dfkmbm, dfkmbm, dataPull);
//                    }
//                } else {
//                    dataPull.put("JFFSE", new BigDecimal("0"));
//                    dataPull.put("DFFSE", new BigDecimal(pd.get("je").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
//                    Map<Object, Object> dmap = new HashMap<>();
//                    dmap.put("IDPZH", pd.get("IDPZH"));
//                    dmap.put("jdbz", "借");
//                    dmap.put("KJTXDM", pd.get("KJTXDM"));
//                    List<Map<String, Object>> pznrList = pznrMapper._queryByPznr(dmap);
//                    if (pznrList.size() > 0 && pznrList != null) {
//                        dataPull = getDfkmbmAndDfkmmc(pznrList, dfkmbm, dfkmbm, dataPull);
//                    } else {
//                        List<Map<String, Object>> pznrSmallJeList = pznrMapper._querySmallJe(dmap);
//                        dataPull = getDfkmbmAndDfkmmc(pznrSmallJeList, dfkmbm, dfkmbm, dataPull);
//                    }
//                }

                if (pd.get("jdbz").equals("借")) {
                    dataPull.put("JFFSE", new BigDecimal(pd.get("je").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    dataPull.put("DFFSE", new BigDecimal("0"));
                    List<Map<String, Object>> pznrDDList = new ArrayList<Map<String, Object>>();
                    List<Map<String, Object>> pznrJJList = new ArrayList<Map<String, Object>>();
                    Map<String, List<Map<String, Object>>> jzpznrListMap = new HashMap<>(jzpznrList);
                    pznrDDList = jzpznrListMap.get("贷" + "-" + pd.get("IDPZH").toString() + "-" + pd.get("KJTXDM").toString());
                    if (pznrDDList != null && pznrDDList.size() > 0) {
                        pznrDDList.remove(pd);
                    }
                    pznrJJList = jzpznrListMap.get("借" + "-" + pd.get("IDPZH").toString() + "-" + pd.get("KJTXDM").toString());
                    if (pznrJJList != null && pznrJJList.size() > 0) {

                        if (pznrJJList != null && pznrJJList.size() > 0) {

                            for (Map<String, Object> map : pznrJJList
                            ) {
                                Double je = Double.valueOf(map.get("je").toString());
                                if (je < 0) {
                                    if (pznrDDList != null && pznrDDList.size() > 0) {
                                        pznrDDList = new ArrayList<>(pznrDDList);
                                        pznrDDList.add(map);
                                    } else {
                                        pznrDDList = new ArrayList<>();
                                        pznrDDList.add(map);
                                    }

                                }
                            }
                        }

                    }
                    List<String> kmdms = new ArrayList<String>();
                    List<String> kmdmmcs = new ArrayList<String>();
                    if (pznrDDList != null && pznrDDList.size() > 0) {

                        for (Map<String, Object> map : pznrDDList
                        ) {
                            kmdms.add(map.get("kmdm").toString());
                            kmdmmcs.add(map.get("kmmc").toString().trim().replace("　", ""));
                        }
                    }
                    if (kmdms != null && kmdms.size() > 0) {
                        if (kmdms != null && kmdms.size() > 0) {

                            HashSet set = new HashSet(kmdms);
                            kmdms.clear();
                            kmdms.addAll(set);
                            set.clear();
                            HashSet set1 = new HashSet(kmdmmcs);
                            kmdmmcs.clear();
                            kmdmmcs.addAll(set1);
                            set1.clear();
                            if (kmdmmcs != null && kmdmmcs.size() > 0) {
                                dataPull.put("DFKMMC", String.join("/", kmdmmcs));
                            } else {
                                dataPull.put("DFKMMC", " ");
                            }
                            if (kmdms != null && kmdms.size() > 0) {
                                dataPull.put("DFKMBM", String.join("/", kmdms));
                            } else {
                                dataPull.put("DFKMBM", " ");
                            }
                        }
                    }
                } else {
                    dataPull.put("JFFSE", new BigDecimal("0"));
                    dataPull.put("DFFSE", new BigDecimal(pd.get("je").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    Map<String, List<Map<String, Object>>> jzpznrListMap = new HashMap<>(jzpznrList);
                    List<Map<String, Object>> pznrDDList = new ArrayList<Map<String, Object>>();
                    List<Map<String, Object>> pznrJJList = new ArrayList<Map<String, Object>>();
                    pznrDDList = jzpznrListMap.get("借" + "-" + pd.get("IDPZH").toString() + "-" + pd.get("KJTXDM").toString());
                    pznrJJList = jzpznrListMap.get("贷" + "-" + pd.get("IDPZH").toString() + "-" + pd.get("KJTXDM").toString());
                    if (pznrDDList != null && pznrDDList.size() > 0) {
                        pznrDDList.remove(pd);
                    }
                    if (pznrJJList != null && pznrJJList.size() > 0) {
                        if (pznrJJList != null && pznrJJList.size() > 0) {

                            for (Map<String, Object> map : pznrJJList
                            ) {
                                Double je = Double.valueOf(map.get("je").toString());
                                if (je < 0) {
                                    if (pznrDDList != null && pznrDDList.size() > 0) {
                                        pznrDDList = new ArrayList<>(pznrDDList);
                                        pznrDDList.add(map);
                                    } else {
                                        pznrDDList = new ArrayList<>();
                                        pznrDDList.add(map);
                                    }
                                }
                            }
                        }
                    }
                    List<String> kmdms = new ArrayList<String>();
                    List<String> kmdmmcs = new ArrayList<String>();
                    if (pznrDDList != null && pznrDDList.size() > 0) {

                        for (Map<String, Object> map : pznrDDList
                        ) {
                            kmdms.add(map.get("kmdm").toString());
                            kmdmmcs.add(map.get("kmmc").toString().trim().replace("　", ""));
                        }
                    }
                    if (kmdms != null && kmdms.size() > 0) {
                        if (kmdms != null && kmdms.size() > 0) {

                            HashSet set = new HashSet(kmdms);
                            kmdms.clear();
                            kmdms.addAll(set);
                            set.clear();
                            HashSet set1 = new HashSet(kmdmmcs);
                            kmdmmcs.clear();
                            kmdmmcs.addAll(set1);
                            set1.clear();
                            if (kmdmmcs != null && kmdmmcs.size() > 0) {
                                dataPull.put("DFKMMC", String.join("/", kmdmmcs));
                            } else {
                                dataPull.put("DFKMMC", " ");
                            }
                            if (kmdms != null && kmdms.size() > 0) {
                                dataPull.put("DFKMBM", String.join("/", kmdms));
                            } else {
                                dataPull.put("DFKMBM", " ");
                            }
                        }
                    }

                }
                //25.币种   人民币
                dataPull.put("BZ", "人民币");
                //26借方外币发生额   //为0
                dataPull.put("JFWBFSE", new BigDecimal("0"));
                //27.贷方外币发生额   //为0
                dataPull.put("DFWBFSE", new BigDecimal("0"));
                //28.汇率   //为空
                dataPull.put("HL", new BigDecimal("0"));
                //29.数量   //为0
                dataPull.put("SL", new BigDecimal("0"));
                //30.单价   //为空
                dataPull.put("DJ", new BigDecimal("0"));
                //31.结算方式   //为空
                dataPull.put("JSFS", "");


                //42.是否为预算账
                if (pd.get("KJTXDM").toString().equals("01")) {
                    dataPull.put("SFWYSZ", "0");
                } else {
                    dataPull.put("SFWYSZ", "1");
                }
                //43.支付单据编号   为空
                dataPull.put("ZFDJBH", "");
                //44.功能科目代码
                String fzdm4 = pd.get("fzdm4").toString();
                Map<Object, Object> dataFzxlbMap = new HashMap<>();
                if (!StringUtils.isEmpty(fzdm4)) {
                    dataPull.put("GNKMDM", fzdm4);
                    //45.功能科目名称
                    dataFzxlbMap.put("fzdm", fzdm4);
                    List<Map<String, Object>> fzxzlList = glFzxzlMapper._queryFzdm(dataFzxlbMap);
                    if (fzxzlList.size() > 0 && fzxzlList != null) {
                        dataPull.put("GNKMMC", fzxzlList.get(0).get("fzmc"));
                    } else {
                        dataPull.put("GNKMDM", " ");
                        dataPull.put("GNKMMC", " ");
                    }
                } else {
                    dataPull.put("GNKMDM", " ");
                    dataPull.put("GNKMMC", " ");
                }
                //46.经济科目代码
                String fzdm5 = pd.get("fzdm5").toString();
                Map<Object, Object> dataFzxlbMap5 = new HashMap<>();
                if (!fzdm4.equals("") && !StringUtils.isEmpty(fzdm5)) {
                    dataPull.put("JJKMDM", fzdm5);
                    //45.功能科目名称
                    dataFzxlbMap5.put("fzdm", fzdm5);
                    //47.经济科目名称
                    List<Map<String, Object>> FzxzlList = glFzxzlMapper._queryGL_Fzxzl(dataFzxlbMap);
                    if (FzxzlList.size() > 0 && FzxzlList != null) {
                        dataPull.put("JJKMMC", FzxzlList.get(0).get("fzmc"));
                    } else {
                        dataPull.put("JJKMDM", " ");
                        dataPull.put("JJKMMC", " ");
                    }
                } else {
                    dataPull.put("JJKMDM", " ");
                    dataPull.put("JJKMMC", " ");
                }
                //48.资金性质代码   //为空
                dataPull.put("ZJXZDM", " ");
                //49.资金性质名称   //为空
                dataPull.put("ZJXZMC", " ");
                //50.指标来源代码   //为空
                dataPull.put("ZBLYDM", " ");
                //51.指标来源名称   //为空
                dataPull.put("ZBLYMC", " ");
                //52.支出类型代码   //为空
                dataPull.put("ZCLXDM", " ");
                //53.支出类型名称   //为空
                dataPull.put("ZCLXMC", " ");
                //54.预算管理类型代码   //为空
                dataPull.put("YSGLLXDM", " ");
                //55.预算管理类型名称   //为空
                dataPull.put("YSGLLXMC", " ");
                //56.支付方式代码   //为空
                dataPull.put("ZFFSDM", " ");
                //57.支付方式名称   //为空
                dataPull.put("ZFFSMC", " ");
                //58.预算项目代码   //为空
                dataPull.put("YSXMDM", " ");
                //59.预算项目名称   //为空
                dataPull.put("YSXMMC", " ");
                //60.项目分类代码   //为空
                dataPull.put("XMFLDM", " ");
                //61.项目分类名称   //为空
                dataPull.put("XMFLMC", " ");
                //62.指标文号名称   //为空
                dataPull.put("ZBWHMC", " ");
                //63.结算方式代码   //为空
                dataPull.put("JSFSDM", " ");
                //64.结算方式名称   //为空
                dataPull.put("JSFSMC", " ");
                resultList.add(dataPull);
            }
        }
        /*
        //resultList进行循环
        //List<Map<String, Object>> resultList = new ArrayList<>();
        //List<Map<String, Object>> resultAllList = new ArrayList<>();
        for (Map<String, Object> rlist : resultList) {
            //10020101
            //判断长度为4，如果为4，则是它本身
            if(rlist.get("kmdm").toString().length()==4){
                resultAllList.add(rlist);
            }else{
                //不为4，则按照kmdm的级次进行查询
                //查询级次
                int jc = rlist.get("kmdm").toString().length();
                int ie = ((jc - 4) / 2) + 1;
                for (int i = 0; i < ie; i++) {
                    //寻找对应长度的kmdm信息
                }
            }
        }
        */

        //List<Map<String, Object>> resultListNew = dbyService.kjkmResult(resultList, pageDataGL_Ztcs.get(0));

        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                jzpzMapper._addJzpz(map1);
            }
            return 1;
        }
        return 0;
    }
}
