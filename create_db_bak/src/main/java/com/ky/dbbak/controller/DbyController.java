package com.ky.dbbak.controller;

import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * @class: create_db_bak
 * @classDesc: 功能描述（）
 * @author: yaoWieJie
 * @createTime: 2020-03-22 18:29
 * @version: v1.0
 */
@Controller
@RequestMapping(value = "/ky-datagather/dby/")
public class DbyController {

    private static final Logger logger = LoggerFactory.getLogger(DbyController.class);

    @Autowired
    DzzbxxMapper dzzbxxMapper;
    @Autowired
    PubkjqjMapper pubkjqjMapper;
    @Autowired
    YebMapper yebMapper;
    @Autowired
    PznrMapper pznrMapper;
    @Autowired
    KmxxMapper kmxxMapper;
    @Autowired
    ZtcsMapper ztcsMapper;
    @Autowired
    GlFzxlbMapper glFzxlbMapper;
    @Autowired
    GlFzxzlMapper glFzxzlMapper;
    @Autowired
    KMYEMapper kmyeMapper;
    @Autowired
    PzmlMapper pzmlMapper;
    @Autowired
    PzlxMapper pzlxMapper;
    @Autowired
    KjqjdyMapper kjqjdyMapper;
    @Autowired
    KMNCSMapper kmncsMapper;
    @Autowired
    JZPZMapper jzpzMapper;
    @Autowired
    SourceMapper sourceMapper;

    //KJQJDY   会计期间定义表
    @RequestMapping(value = "kjqjdy")
    @ResponseBody
    public String kjqjdy(String XZQHDM) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        pageData.put("XZQHDM", XZQHDM);
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
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            kjqjdyMapper._addKjqjdy(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        kjqjdyMapper._addKjqjdy(map);
        return "kjqjdy-会计期间定义表生成完成";
    }

    //KMNCS   科目年初数表
    @RequestMapping(value = "kmncs")
    @ResponseBody
    public String kmncs(String XZQHDM) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        //List<Map<String, Object>> GL_PznrList = pznrMapper._queryAll(pageData);
        List<Map<String, Object>> glYebList = yebMapper._queryGL_Yeb(pageData);
        pageData.put("XZQHDM", XZQHDM);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
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
            //8.会计月份
            //String ncj = pd.get("ncj").toString();
            Double d1 = new Double(pd.get("ncj").toString());
            //String ncd = pd.get("ncd").toString();
            Double d2 = new Double(pd.get("ncd").toString());
            if (d1!= 0 || d2!=0) {
                dataPull.put("KJYF", 0);
                Map<String, Object> pznrMap = new HashMap<String, Object>();
                pznrMap.put("kmdm", pd.get("kmdm"));
                List<Map<String, Object>> pageDataGL_Pznr = pznrMapper._queryByPznr1(pznrMap);
                //9.会计体系  01会计，02预算
                if (pageDataGL_Pznr.size()>0 && pageDataGL_Pznr != null){
                    String kjtxdm = pageDataGL_Pznr.get(0).get("KJTXDM").toString();
                    if(kjtxdm != null && !kjtxdm.equals("")){
                        dataPull.put("KJTX", kjtxdm);
                    }
                }
                //10.会计科目编码
                dataPull.put("KJKMBM", pd.get("kmdm"));
                //11.会计科目名称
                List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._querykmxx(pznrMap);
                List<Map<String, Object>> pageDataGL_Yeb = yebMapper._queryByKmdm(pznrMap);
                if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                    String kmmc = pageDataGL_KMXX.get(0).get("kmmc").toString();
                    dataPull.put("KJKMMC", kmmc);
                    //18.余额方向
                    String yefx = pageDataGL_KMXX.get(0).get("yefx").toString();
                    if (!yefx.equals("") && !StringUtils.isEmpty(yefx)) {
                        switch (yefx) {
                            case "J":
                                dataPull.put("YEFX", 1);
                                //19.本币期初余额
                                BigDecimal yefxj = new BigDecimal(pd.get("ncj").toString());
                                dataPull.put("BBQCYE", yefxj.setScale(2, BigDecimal.ROUND_HALF_UP));
                                break;
                            case "D":
                                dataPull.put("YEFX", -1);
                                //19.本币期初余额
                                BigDecimal yefxd = new BigDecimal(pd.get("ncd").toString());
                                dataPull.put("BBQCYE", yefxd.setScale(2, BigDecimal.ROUND_HALF_UP));
                                break;
                        }
                    }
                    //是否是最末级
                    dataPull.put("SFZDJKM",pageDataGL_KMXX.get(0).get("kmmx").toString());
                    //12.科目全称
                    String kmdm = pd.get("kmdm").toString();
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
                        //13.会计科目级别
                        Integer kjkmjb = Integer.valueOf(((kmdm.length() - 4) / 2) + 1);
                        dataPull.put("KJKMJC", kjkmjb);
                    } else {
                        dataPull.put("KMQC", "");
                    }
                }
                //16.是否现金或现金等价物  赋值0
                dataPull.put("SFXJHXJDJW", 0);
                //17.币种名称//手动输入 人民币
                dataPull.put("BZMC", "人民币");
                //20.期初数量  赋值0
                dataPull.put("QCSL", BigDecimal.ZERO);
                //21.外币期初余额  赋值0
                dataPull.put("WBQCYE", BigDecimal.ZERO);
                resultList.add(dataPull);
            }
        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            kmncsMapper._add(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        kmncsMapper._add(map);
        return "kmncs-科目年初数表生成完成";
    }

    //KMYE   科目余额表
    @RequestMapping(value = "kmye")
    @ResponseBody
    public String kmye(String XZQHDM) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = yebMapper._queryAllYeb(pageData);
        pageData.put("XZQHDM", XZQHDM);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
        for (Map<String, Object> pd : GL_YebList) {
            Map<String, Object> dataPull = new HashMap<String, Object>();
            Map<String, Object> datadzzbxx = dzzbxxList.get(0);
            dataPull.put("XZQHDM", datadzzbxx.get("XZQHDM"));
            dataPull.put("XZQHMC", datadzzbxx.get("XZQHMC"));
            dataPull.put("KJND", datadzzbxx.get("KJND"));
            dataPull.put("DWMC", datadzzbxx.get("DWMC"));
            dataPull.put("DWDM", datadzzbxx.get("DWDM"));
            dataPull.put("KJDZZBBH", datadzzbxx.get("KJDZZBBH"));
            dataPull.put("KJDZZBMC", datadzzbxx.get("KJDZZBMC"));
            //Double qcjfye = (Double) pd.get("ncj");
            //BigDecimal qcjfye = new BigDecimal((Double) pd.get("ncj"));
            //Double qcdfye = (Double) pd.get("ncd");
            BigDecimal qcjfye = new BigDecimal(pd.get("ncj").toString());
            BigDecimal qcdfye = new BigDecimal(pd.get("ncd").toString());
            //BigDecimal qcdfye = new BigDecimal((Double) pd.get("ncd"));
            BigDecimal jfljfse = new BigDecimal("0");
            //BigDecimal jfljfse = new BigDecimal();
            BigDecimal dfljfse = new BigDecimal("0");
            //BigDecimal dfljfse = new BigDecimal();
            for (int i = 1; i < 13; i++) {
                String yji = pd.get("yj" + i).toString();
                String ydi = pd.get("yd" + i).toString();
                if (!yji.equals("0") && !StringUtils.isEmpty(yji) && !ydi.equals("0") && !StringUtils.isEmpty(ydi)) {
                    //8.会计月份
                    dataPull.put("KJYF", i);
                    //9.会计体系  01会计，02预算
                    List<Map<String, Object>> pageDataPznrList = pznrMapper._queryPznr(pd);
                    if (pageDataPznrList.size() > 0 && pageDataPznrList != null) {
                        String newkmdm = pd.get("kmdm").toString();
                        dataPull.put("KJTX", pageDataPznrList.get(0).get("KJTXDM").toString());
                        //10.会计科目编码
                        dataPull.put("KJKMBM", newkmdm);
                    }
                    //11.会计科目名称
                    List<Map<String, Object>> pageDataKmxxList = kmxxMapper._queryGL_KMXX(pd);
                    if (pageDataKmxxList.size() > 0 && pageDataKmxxList != null) {
                        String kmmc = pageDataKmxxList.get(0).get("kmmc").toString();
                        dataPull.put("KJKMMC", kmmc);
                        //12.科目全称
                        String kmdm = pd.get("kmdm").toString();
                        String kjkmqc = "";
                        if (!StringUtils.isEmpty(kmdm) && kmdm != null) {
                            if (kmdm.length() == 4) {
                                dataPull.put("KMQC", pageDataKmxxList.get(0).get("kmmc"));
                                //14.是否最低级科目
                                dataPull.put("SFZDJKM", 0);
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
                                dataPull.put("SFZDJKM", 1);
                                //15.上级科目编码
                                String kmdm3 = kmdm.substring(0, kmdm.length() - 2);
                                dataPull.put("SJKMBM", kmdm3);
                            }
                            //13.会计科目级别
                            Integer kjkmjb = Integer.valueOf(((kmdm.length() - 4) / 2) + 1);
                            dataPull.put("KJKMJC", kjkmjb);
                        } else {
                            dataPull.put("KMQC", "");
                            //14.是否最低级科目
                            dataPull.put("SFZDJKM", 0);
                            //15.上级科目编码
                            dataPull.put("SJKMBM", "");
                        }
                    }
                    //13.年初借方余额
                    //Double ncj = (Double) pd.get("ncj");
                    BigDecimal ncj = new BigDecimal(pd.get("ncj").toString());
                    //Double ncd = (Double) pd.get("ncd");
                    BigDecimal ncd = new BigDecimal(pd.get("ncd").toString());
                    dataPull.put("NCJFYE", ncj.setScale(2, BigDecimal.ROUND_HALF_UP));
                    //14.年初贷方余额
                    dataPull.put("NCJFYE", ncd.setScale(2, BigDecimal.ROUND_HALF_UP));
                    //15.年初余额方向  ncj-ncd  -1：贷，0：平，1：借。
                    if (ncj.compareTo(ncd) == 1) {
                        dataPull.put("NCYEFX", 1);
                    } else if (ncj.compareTo(ncd) == -1) {
                        dataPull.put("NCYEFX", -1);
                    } else {
                        dataPull.put("NCYEFX", 0);
                    }
                    //16.期初借方余额//GL_yeb表1月为ncj  2月为yj1以此类推
                     /*
                    if (jfljfse.compareTo(dfljfse)==1) {
                        BigDecimal qmjfye = jfljfse.subtract(dfljfse);
                        dataPull.put("QCJFYE", qmjfye.setScale(2,BigDecimal.ROUND_HALF_UP));
                        dataPull.put("QCJFYE", BigDecimal.ZERO);
                    } else if (jfljfse.compareTo(dfljfse)==-1) {
                        BigDecimal qmdfye= dfljfse.subtract(jfljfse);
                        dataPull.put("QCJFYE", BigDecimal.ZERO);
                        dataPull.put("QCJFYE", qmdfye.setScale(2,BigDecimal.ROUND_HALF_UP));
                    } else {
                        dataPull.put("QCJFYE", BigDecimal.ZERO);
                        dataPull.put("QCJFYE", BigDecimal.ZERO);
                    }
                    Double newqcjfye = (Double) pd.get("yj" + (i - 1));
                    if(i==1){
                        dataPull.put("QCJFYE", new BigDecimal(qcjfye).setScale(2,BigDecimal.ROUND_HALF_UP));
                    }else{
                        dataPull.put("QCJFYE", new BigDecimal(newqcjfye).setScale(2,BigDecimal.ROUND_HALF_UP));
                    }
                    */
                    //dataPull.put("QCJFYE", new BigDecimal(qcjfye));
                    //qcjfye.add((BigDecimal) pd.get("yj" + i));
                    //qcjfye+=(Double) pd.get("yj"+i);
                    //17.期初贷方余额
                    if (i == 1) {
                        dataPull.put("QCJFYE", qcjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                        dataPull.put("QCDFYE", qcdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                    } else {
                        BigDecimal newqcdfye = new BigDecimal(pd.get("yd" + (i - 1)).toString());
                        BigDecimal newqcjfye = new BigDecimal(pd.get("yj" + (i - 1)).toString());
                        dataPull.put("QCJFYE", newqcjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                        dataPull.put("QCDFYE", newqcdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    //dataPull.put("QCDFYE", new BigDecimal(qcdfye));
                    //qcdfye += (Double) pd.get("yd" + i);
                    //qcdfye.add((BigDecimal) pd.get("yd" + i));
                    //18.期初余额方向  ncj-ncd  -1：贷，0：平，1：借。
                    if (qcjfye.compareTo(qcdfye) == 1) {
                        dataPull.put("QCYEFX", 1);
                    } else if (qcjfye.compareTo(qcdfye) == -1) {
                        dataPull.put("QCYEFX", -1);
                    } else {
                        dataPull.put("QCYEFX", 0);
                    }
                    //19.外币年初借方余额//赋值0
                    dataPull.put("WBNCJFYE", BigDecimal.ZERO);
                    //20.外币年初贷方余额//赋值0
                    dataPull.put("WBNCDFYE", BigDecimal.ZERO);
                    //21.外币期初借方余额//赋值0
                    dataPull.put("WBQCJFYE", BigDecimal.ZERO);
                    //22.外币期初贷方余额//赋值0
                    dataPull.put("WBQCDFYE", BigDecimal.ZERO);
                    //23.借方发生额
                    //int jffse = (int) pd.get("yj" + i);
                    BigDecimal jffse = new BigDecimal(pd.get("yj" + i).toString());
                    dataPull.put("JFFSE", jffse.setScale(2, BigDecimal.ROUND_HALF_UP));
                    //24.借方累计发生额
                    jfljfse = jfljfse.add(jffse);
                    System.out.println("第" + i + "次" + jfljfse);
                    dataPull.put("JFLJFSE", jfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));
                    //25.外币借方发生额//赋值0
                    dataPull.put("WBJFFSE", BigDecimal.ZERO);
                    //26.外币借方累计发生额//赋值0
                    dataPull.put("WBJFLJFSE", BigDecimal.ZERO);
                    //27.贷方发生额
                    BigDecimal dffse = new BigDecimal(pd.get("yd" + i).toString());
                    dataPull.put("DFFSE", dffse.setScale(2, BigDecimal.ROUND_HALF_UP));
                    //28.贷方累计发生额
                    //dfljfse += dffse;
                    dfljfse = dfljfse.add(dffse);
                    System.out.println("第" + i + "次" + dfljfse);
                    dataPull.put("DFLJFSE", dfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));
                    //29.外币贷方发生额//赋值0
                    dataPull.put("WBDFFSE", BigDecimal.ZERO);
                    //30.外币贷方累计发生额//赋值0
                    dataPull.put("WBDFLJFSE", BigDecimal.ZERO);
                    //31.期末借方余额
                    //32.期末贷方余额
                    //33.期末余额方向   -1：贷，0：平，1：借。
                    BigDecimal jj = qcjfye.add(jffse);
                    BigDecimal dd = qcdfye.add(dffse);
                    if (jj.compareTo(dd) == 1) {
                        BigDecimal qmjfye = dd.subtract(dd);
                        dataPull.put("QMJFYE", qmjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                        dataPull.put("QMDFYE", BigDecimal.ZERO);
                        dataPull.put("QMYEFX", 1);
                    } else if (jj.compareTo(dd) == -1) {
                        BigDecimal qmdfye = dd.subtract(jj);
                        dataPull.put("QMJFYE", BigDecimal.ZERO);
                        dataPull.put("QMDFYE", qmdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                        dataPull.put("QMYEFX", -1);
                    } else {
                        dataPull.put("QMJFYE", BigDecimal.ZERO);
                        dataPull.put("QMDFYE", BigDecimal.ZERO);
                        dataPull.put("QMYEFX", 0);
                    }
                    //34.外币期末借方余额//赋值0
                    dataPull.put("WBQMJFYE", BigDecimal.ZERO);
                    //35.外币期末贷方余额//赋值0
                    dataPull.put("WBQMDFYE", BigDecimal.ZERO);
                    //36.分录数,查找月份，科目代码和辅助明晰一样的有几条
                    int fls = 0;
                    for (int j = 1; j < 31; j++) {
                        if (pd.get("fzdm" + j) != null && !StringUtils.isEmpty(pd.get("fzdm" + j).toString().trim())) {
                            fls += 1;
                        }
                    }
                    dataPull.put("FLS", fls);
                    //40.是否现金或现金等价物  //赋值0
                    dataPull.put("SFXJHXJDJW", 0);
                    //41.币种名称 // 人民币
                    dataPull.put("BZMC", "人民币");
                    //42.币种代码//为空
                    dataPull.put("BZDM", "");
                    //kmyeMapper._add(dataPull);
                    resultList.add(dataPull);
                }
            }
            // resultList.add(dataPull);
        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            kmyeMapper._add(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        kmyeMapper._add(map);
        return "kmye-科目余额表生成完成";
    }

    //JZPZ   记账凭证
    @RequestMapping(value = "jzpz")
    @ResponseBody
    public String insert(String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> bypznrList = pznrMapper._queryAll(pageData);
        pageData.put("XZQHDM", XZQHDM);
        List<Map<String, Object>> dzzbxxList = dzzbxxMapper._queryDzzbxx(pageData);
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
            //8.会计月份，
            String kjqj = pd.get("kjqj").toString();
            if (!StringUtils.isEmpty(kjqj)) {
                Integer mouth = Integer.valueOf(kjqj.substring(kjqj.length() - 2, kjqj.length()));
                dataPull.put("KJYF", mouth);

                //9.记账凭证日期
                List<Map<String, Object>> pageDataPzmlList = pzmlMapper._queryPzml(pd);
                dataPull.put("JZPZRQ", pageDataPzmlList.get(0).get("srrq"));
                //10.记账类型编号
                dataPull.put("JZLXBH", pd.get("pzlxdm"));
                //11.记账类型名称
                List<Map<String, Object>> pageDatePzlxList = pzlxMapper._queryPzlx(pd);
                String pzlxmc = (String) pageDatePzlxList.get(0).get("pzlxmc");
                dataPull.put("JZLXMC", pzlxmc);
                //12.记账凭证种类
                dataPull.put("JZPZZL", pzlxmc);
                //11.记账类型名称
                dataPull.put("JZPZBH", pzlxmc);
                //13.记账凭证编号
                dataPull.put("JZPZBH", pd.get("pzh"));
                //14.记账凭证行号
                dataPull.put("JZPZBH", pd.get("flh"));
                //15.分录序号
                dataPull.put("FLXH", pd.get("kjqj").toString().substring(0, (pd.get("kjqj").toString().length() - 2)) + "-"
                        + pd.get("kjqj").toString().substring((pd.get("kjqj").toString().length() - 2), (pd.get("kjqj").toString().length()))
                        + "-" + pd.get("PZLXDM") + "-" + pd.get("pzh") + "-" + pd.get("flh") + "-" + pd.get("KJTXDM"));
                //16.记账凭证摘要
                dataPull.put("JZPZZY", pd.get("zy"));
                //17.会计体系01会计，02预算
                dataPull.put("KJTX", pd.get("KJTXDM"));
                //18.会计科目编码
                dataPull.put("KJKMBM", pd.get("kmdm"));
                //19.会计科目名称
                List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._queryGL_KMXX(pd);
                String kmmc = pageDataGL_KMXX.get(0).get("kmmc").toString();
                dataPull.put("KJKMMC", kmmc);
                //20.科目全称   货币资金/自有资金
                String kmdm = pd.get("kmdm").toString();
                String kjkmqc = "";
                if (!StringUtils.isEmpty(kmdm) && kmdm != null) {
                    if (kmdm.length() == 4) {
                        dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc"));
                        //14.是否最低级科目
                        dataPull.put("SFZDJKM", 0);
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
                        dataPull.put("SFZDJKM", 1);
                        //15.上级科目编码
                        String kmdm3 = kmdm.substring(0, kmdm.length() - 2);
                        dataPull.put("SJKMBM", kmdm3);
                    }
                    //13.会计科目级别
                    Integer kjkmjb = Integer.valueOf(((kmdm.length() - 4) / 2) + 1);
                    dataPull.put("KJKMJC", kjkmjb);
                } else {
                    dataPull.put("KMQC", "");
                }
                //21.借方发生额yj1,yj2,yj3
                List<Map<String, Object>> pageDataYebList = yebMapper._queryGL_Yeb(pd);
                dataPull.put("JFFSE", new BigDecimal(pageDataYebList.get(0).get("yj" + mouth).toString()));
                //22.贷方发生额 yd1,yd2
                dataPull.put("JFFSE", new BigDecimal(pageDataYebList.get(0).get("yd" + mouth).toString()));
                //23.对方科目编码
                if (pd.get("jdbz").equals("借")) {
                    Map<Object, Object> dmap = new HashMap<>();
                    dmap.put("IDPZH", pd.get("IDPZH"));
                    dmap.put("jdbz", "贷");
                    List<Map<String, Object>> pznrList = pznrMapper._queryPznr(dmap);
                    for (Map<String, Object> pz : pznrList) {

                        pageData.put("DFKMBM", pz.get("kmdm"));
                        //24.对方科目名称
                        List<Map<String, Object>> kmxxList = kmxxMapper._queryGL_KMXX(pz);
                        pageData.put("DFKMMC", kmxxList.get(0).get("kmmc"));
                    }
                } else {
                    Map<Object, Object> dmap = new HashMap<>();
                    dmap.put("IDPZH", pd.get("IDPZH"));
                    dmap.put("jdbz", "借");
                    List<Map<String, Object>> pznrList = pznrMapper._queryByPznr(dmap);
                    for (Map<String, Object> pz : pznrList) {
                        pageData.put("DFKMBM", pz.get("kmdm"));
                        //24.对方科目名称
                        List<Map<String, Object>> kmxxList = kmxxMapper._queryGL_KMXX(pz);
                        pageData.put("DFKMMC", kmxxList.get(0).get("kmmc"));
                    }
                }
                //25.币种   人民币
                dataPull.put("BZ", "人民币");
                //26借方外币发生额   //为0
                dataPull.put("JFWBFSE", BigDecimal.ZERO);
                //27.贷方外币发生额   //为0
                dataPull.put("DFWBFSE", BigDecimal.ZERO);
                //28.汇率   //为空
                dataPull.put("HL", BigDecimal.ZERO);
                //29.数量   //为0
                dataPull.put("SL", BigDecimal.ZERO);
                //30.单价   //为空
                dataPull.put("DJ", BigDecimal.ZERO);
                //31.结算方式   //为空
                dataPull.put("JSFS", "");
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
                if (pageDataPzmlList.get(0).get("pzly").toString().equals("") || StringUtils.isEmpty(pageDataPzmlList.get(0).get("pzly").toString().trim())) {
                    dataPull.put("YPZH", "");
                    //42.是否为预算账
                    dataPull.put("SFWYSZ", "");
                } else {
                    dataPull.put("YPZH", pageDataPzmlList.get(0).get("pzly"));
                    //42.是否为预算账
                    dataPull.put("SFWYSZ", "1");
                }
                //39.记账标志 0=作废；1=未审核；2=已审核；3=已记帐
                String zt = pageDataPzmlList.get(0).get("zt").toString();
                switch (zt) {
                    case "1":
                        dataPull.put("JZBZ", "");
                        //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                        dataPull.put("ZFBZ", "");
                        //41.是否结转
                        dataPull.put("SFJZ", "1");
                        break;
                    case "2":
                        dataPull.put("JZBZ", "");
                        //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                        dataPull.put("ZFBZ", "");
                        //41.是否结转
                        dataPull.put("SFJZ", "");
                        break;
                    case "3":
                        dataPull.put("JZBZ", "");
                        //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                        dataPull.put("ZFBZ", "");
                        //41.是否结转
                        dataPull.put("SFJZ", "1");
                        break;
                    default:
                        dataPull.put("JZBZ", "");
                        //40.作废标志 0=作废；1=未审核；2=已审核；3=已记帐
                        dataPull.put("ZFBZ", "1");
                        //41.是否结转
                        dataPull.put("SFJZ", "");
                        break;
                }
                //43.支付单据编号   为空
                dataPull.put("ZFDJBH", "");
                //44.功能科目代码
                String fzdm4 = pageDataYebList.get(0).get("fzdm4").toString();
                Map<Object, Object> dataFzxlbMap = new HashMap<>();
                if (!fzdm4.equals("") && !StringUtils.isEmpty(fzdm4)) {
                    dataPull.put("GNKMDM", fzdm4);
                    //45.功能科目名称
                    dataFzxlbMap.put("fzdm", fzdm4);
                    List<Map<String, Object>> fzxzlList = glFzxzlMapper._queryFzdm(dataFzxlbMap);
                    if (fzxzlList.size() > 0 && fzxzlList != null) {
                        dataPull.put("GNKMMC", fzxzlList.get(0).get("fzmc"));
                    } else {
                        dataPull.put("GNKMDM", "");
                        dataPull.put("GNKMMC", "");
                    }
                }
                //46.经济科目代码
                String fzdm5 = pageDataYebList.get(0).get("fzdm5").toString();
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
                        dataPull.put("JJKMDM", "");
                        dataPull.put("JJKMMC", "");
                    }
                }
                //48.资金性质代码   //为空
                dataPull.put("ZJXZDM", "");
                //49.资金性质名称   //为空
                dataPull.put("ZJXZMC", "");
                //50.指标来源代码   //为空
                dataPull.put("ZBLYDM", "");
                //51.指标来源名称   //为空
                dataPull.put("ZBLYMC", "");
                //52.支出类型代码   //为空
                dataPull.put("ZCLXDM", "");
                //53.支出类型名称   //为空
                dataPull.put("ZCLXMC", "");
                //54.预算管理类型代码   //为空
                dataPull.put("YSGLLXDM", "");
                //55.预算管理类型名称   //为空
                dataPull.put("YSGLLXMC", "");
                //56.支付方式代码   //为空
                dataPull.put("ZFFSDM", "");
                //57.支付方式名称   //为空
                dataPull.put("ZFFSMC", "");
                //58.预算项目代码   //为空
                dataPull.put("YSXMDM", "");
                //59.预算项目名称   //为空
                dataPull.put("YSXMMC", "");
                //60.项目分类代码   //为空
                dataPull.put("XMFLDM", "");
                //61.项目分类名称   //为空
                dataPull.put("XMFLMC", "");
                //62.指标文号名称   //为空
                dataPull.put("ZBWHMC", "");
                //63.结算方式代码   //为空
                dataPull.put("JSFSDM", "");
                //64.结算方式名称   //为空
                dataPull.put("JSFSMC", "");
                resultList.add(dataPull);
            }
        }

        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 30;
        Integer listnum3 = listNum / 30;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 30, (p * 30 + 30)));
            jzpzMapper._add(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        jzpzMapper._add(map);
        return "jzpz-记账凭证表生成完成";
    }


    private Map<String, Object> dealAmount(Map<String, Object> pd, Map<String, Object> dataPullBase) {
        BigDecimal jfljfse = new BigDecimal("0");
        BigDecimal dfljfse = new BigDecimal("0");
        for (int i = 1; i < 13; i++) {
            if (!pd.get("yj" + i).toString().equals("0") && !StringUtils.isEmpty(pd.get("yj" + i).toString().trim()) &&
                    !pd.get("yd" + i).toString().equals("0") && !StringUtils.isEmpty(pd.get("yd" + i).toString().trim())
            ) {
                //8.会计月份
                dataPullBase.put("KJYF", i);
            }
            //15、年初余额方向
            BigDecimal a = new BigDecimal(pd.get("ncj").toString());
            BigDecimal b = new BigDecimal(pd.get("ncd").toString());
            if (a.compareTo(b) == 1) {
                dataPullBase.put("NCYEFX", 1);
            } else if (a.compareTo(b) == 0) {
                dataPullBase.put("NCYEFX", 0);
            } else {
                dataPullBase.put("NCYEFX", -1);
            }
            //16、期初借方余额
            BigDecimal yji = new BigDecimal(pd.get("yj" + i).toString());
            BigDecimal qcjfye = a.add(yji);
            dataPullBase.put("QCJFYE", qcjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
            //17、期初贷方余额
            BigDecimal ydi = new BigDecimal(pd.get("yd" + i).toString());
            BigDecimal qcdfye = b.add(ydi);
            dataPullBase.put("QCDFYE", qcdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
            if (qcjfye.compareTo(qcjfye) == 1) {
                //18、期初余额方向
                dataPullBase.put("QCYEFX", 1);
            } else if (qcjfye.compareTo(qcjfye) == 0) {
                //18、期初余额方向
                dataPullBase.put("QCYEFX", 0);
            } else {
                //18、期初余额方向
                dataPullBase.put("QCYEFX", -1);
            }
            //19.借方发生额
            dataPullBase.put("JFFSE", yji);
            //20.借方累计发生额
            jfljfse = jfljfse.add(yji);
            dfljfse = dfljfse.add(ydi);
            dataPullBase.put("JFLJFSE", jfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));
            //21.贷方发生额
            dataPullBase.put("DFFSE", ydi);
            dataPullBase.put("DFLJFSE", dfljfse.setScale(2, BigDecimal.ROUND_HALF_UP));

            //23.期末借方余额
            //24.期末贷方余额
            //25.期末余额方向
            BigDecimal jj = qcjfye.add(yji);
            BigDecimal dd = qcdfye.add(ydi);
            if (jj.compareTo(dd) == 1) {
                BigDecimal qmjfye = dd.subtract(dd);
                dataPullBase.put("QMJFYE", qmjfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                dataPullBase.put("QMDFYE", BigDecimal.ZERO);
                dataPullBase.put("QMYEFX", 1);
            } else if (jj.compareTo(dd) == -1) {
                BigDecimal qmdfye = dd.subtract(jj);
                dataPullBase.put("QMJFYE", BigDecimal.ZERO);
                dataPullBase.put("QMDFYE", qmdfye.setScale(2, BigDecimal.ROUND_HALF_UP));
                dataPullBase.put("QMYEFX", -1);
            } else {
                dataPullBase.put("QMJFYE", BigDecimal.ZERO);
                dataPullBase.put("QMDFYE", BigDecimal.ZERO);
                dataPullBase.put("QMYEFX", 0);
            }
        }
        return dataPullBase;
    }
}