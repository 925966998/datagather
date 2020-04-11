package com.ky.dbbak.service;

import com.ky.dbbak.entity.FZLXEntity;
import com.ky.dbbak.entity.OrgEntity;
import com.ky.dbbak.mapper.OrgMapper;
import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.FzlxMapper;
import com.ky.dbbak.targetmapper.FzxxMapper;
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
public class FzlxService {
    @Autowired
    SourceMapper sourceMapper;
    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    OrgMapper orgMapper;
    @Autowired
    YebMapper yebMapper;
    @Autowired
    FzlxMapper fzlxMapper;
    @Autowired
    KmxzlxMapper kmxzlxMapper;
    @Autowired
    PubbmxxMapper pubbmxxMapper;
    @Autowired
    PubkszlMapper pubkszlMapper;
    @Autowired
    XmzlMapper xmzlMapper;
    @Autowired
    FzxzlMapper fzxzlMapper;
    @Autowired
    FzxlbMapper fzxlbMapper;
    @Autowired
    FzxxMapper fzxxMapper;

    @Transactional("targetTransactionManager")
    public List FzlxB (List<Map<String, Object>> bypznrList){
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
        return lbdmList;
    }

    @Transactional("targetTransactionManager")
    public boolean FzlxStrB (List<String> lbdmList, Map<String, Object> dataPull ){
        List<Map<String, Object>> resultList = new ArrayList<>();
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
            return true;
        }
        return false;
    }

    @Transactional
    public List Fzlx(String KJDZZBBH){
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        List<OrgEntity> Org = orgMapper.queryOrgZT(datadzzbxx.get("KJDZZBBH").toString());
        Map<String, Object> orgData = new HashMap<String, Object>();
        orgData.put("kjnd",Org.get(0).getKjnd());
        orgData.put("gsdm",Org.get(0).getOrgCode());
        orgData.put("ZTH",Org.get(0).getZt());
        List<Map<String, Object>> bypznrList = yebMapper._queryYebKjnd(orgData);
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
        return lbdmList;
    }

    @Transactional("targetTransactionManager")
    public boolean FzlxStr(List<String> lbdmList,String KJDZZBBH){
        Map<String, Object> dataPull = new HashMap<String, Object>();
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        List<Map<String, Object>> resultList = new ArrayList<>();
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
            queryPd.put("gsdm", datadzzbxx.get("DWDM"));
            queryPd.put("kjnd", datadzzbxx.get("KJND"));
            List<Map<String, Object>> pageDataFzxlb = fzxlbMapper._queryFzxlb(queryPd);
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
            return true;
        }
        return false;
    }


    @Transactional("targetTransactionManager")
    public boolean fzxxB (List<FZLXEntity> fzlxEntityList,Map<String, Object> dataPullBase){
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<String> lbdmList = new ArrayList<String>();
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
                        dataPull.put("SJFZBM", " ");
                        String fzlxjg = fzlx.getFZLXJG();
                        String fzdm = pubbmxx.get("bmdm").toString();
                        List<String> qc = new ArrayList<String>();
                        int jc = 1;
                        if (!StringUtils.isEmpty(fzlxjg)) {
                            String[] fzlxjgStr = fzlxjg.split("-");
                            int num = 0;//3  3  2  2  111 111 11
                            if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                for (int w = 0; w < fzlxjgStr.length; w++) {
                                    num = num + Integer.valueOf(fzlxjgStr[w]);
                                    if (num < fzdm.length()) {
                                        List<Map<String, Object>> maps = kmxzlxMapper._queryPUBBMXXq(fzdm.substring(0, num));
                                        qc.add(maps.get(0).get("bmmc").toString());
                                        dataPull.put("SJFZBM", maps.get(0).get("bmdm").toString());
                                        jc++;
                                    }
                                }
                            }
                        }
                        qc.add(pubbmxx.get("bmmc").toString());
                        dataPull.put("FZQC", String.join("/", qc));
                        dataPull.put("FZJC", jc);
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
                        dataPull.put("SJFZBM", " ");
                        String fzlxjg = fzlx.getFZLXJG();
                        String fzdm = xmzl.get("XMDM").toString();
                        List<String> qc = new ArrayList<String>();
                        int jc = 1;
                        if (!StringUtils.isEmpty(fzlxjg)) {
                            String[] fzlxjgStr = fzlxjg.split("-");
                            int num = 0;//3  3  2  2  111 111 11
                            if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                for (int w = 0; w < fzlxjgStr.length; w++) {
                                    num = num + Integer.valueOf(fzlxjgStr[w]);
                                    if (num < fzdm.length()) {
                                        List<Map<String, Object>> maps = kmxzlxMapper._queryXMZLq(fzdm.substring(0, num));
                                        if(maps!=null&&maps.size()>0){
                                            qc.add(maps.get(0).get("XMMC").toString());
                                        }
                                        dataPull.put("SJFZBM", fzdm.substring(0, num));
                                        jc++;
                                    }
                                }
                            }
                        }
                        qc.add(xmzl.get("XMMC").toString());
                        dataPull.put("FZQC", String.join("/", qc));
                        dataPull.put("FZJC", jc);
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
                        dataPull.put("SJFZBM", " ");
                        String fzlxjg = fzlx.getFZLXJG();
                        String fzdm = pubkszl.get("dwdm").toString();
                        List<String> qc = new ArrayList<String>();
                        int jc = 1;
                        if (!StringUtils.isEmpty(fzlxjg)) {
                            String[] fzlxjgStr = fzlxjg.split("-");
                            int num = 0;//3  3  2  2  111 111 11
                            if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                for (int w = 0; w < fzlxjgStr.length; w++) {
                                    num = num + Integer.valueOf(fzlxjgStr[w]);
                                    if (num < fzdm.length()) {
                                        List<Map<String, Object>> maps = kmxzlxMapper._queryPUBKSZLq(fzdm.substring(0, num));
                                        qc.add(maps.get(0).get("dwmc").toString());
                                        dataPull.put("SJFZBM", maps.get(0).get("dwdm").toString());
                                        jc++;
                                    }
                                }
                            }
                        }
                        qc.add(pubkszl.get("dwmc").toString());
                        dataPull.put("FZQC", String.join("/", qc));
                        dataPull.put("FZJC", jc);
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
                                                if(pageDataGL_Fzxzl.size()>0 && pageDataGL_Fzxzl != null) {
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
                                                }
                                            } else {
                                                Map<String, Object> queryPd = new HashMap<String, Object>();
                                                queryPd.put("fzdm", fzdm.substring(0, num));
                                                queryPd.put("lbdm", fzlx.getFZLXBM());
                                                List<Map<String, Object>> pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
                                                if(pageDataGL_Fzxzl.size()>0 && pageDataGL_Fzxzl != null) {
                                                    qcList.add(pageDataGL_Fzxzl.get(0).get("fzmc").toString());
                                                }
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
            return true;
        }
        return false;
    }


    public Map<String, Object> Fzxx (String KJDZZBBH){
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String,Object> dataPullBase = new HashMap<>();
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
        return dataPullBase;
    }

    public List<Map<String, Object>> pubbmxx(String KJDZZBBH){
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        Map<String, Object> orgData = new HashMap<String, Object>();
        orgData.put("kjnd",datadzzbxx.get("KJND"));
        orgData.put("gsdm",datadzzbxx.get("DWDM"));
        List<Map<String, Object>> bypznrList = pubbmxxMapper._queryPubbmxx(orgData);
        return bypznrList;
    }

    public List<Map<String, Object>> Xmzl(String KJDZZBBH){
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        Map<String, Object> orgData = new HashMap<String, Object>();
        orgData.put("KJND",datadzzbxx.get("KJND"));
        orgData.put("GSDM",datadzzbxx.get("DWDM"));
        List<Map<String, Object>> bypznrList = xmzlMapper._queryXmzl(orgData);
        return bypznrList;
    }

    public List<Map<String, Object>> Pubkszl(String KJDZZBBH){
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        Map<String, Object> orgData = new HashMap<String, Object>();
        orgData.put("KJND",datadzzbxx.get("KJND"));
        orgData.put("GSDM",datadzzbxx.get("DWDM"));
        List<Map<String, Object>> bypznrList = pubkszlMapper._queryPubkszl(orgData);
        return bypznrList;
    }

    @Transactional("targetTransactionManager")
    public boolean FzxxStr(Map<String, Object> dataPullBase,List<Map<String, Object>> pageDatapubbmXX,List<Map<String, Object>> pageDataxmzl,
                        List<Map<String, Object>> pageDataPubkszl,String KJDZZBBH){
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<String> lbdmList = new ArrayList<String>();
        Map<String,Object> pageData = new HashMap<>();
        List<FZLXEntity> fzlxEntityList = fzlxMapper._queryAll(pageData);
        Map<String, Object> pageData1 = new HashMap<String, Object>();
        pageData1.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData1);
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        for (FZLXEntity fzlx : fzlxEntityList) {
            if (fzlx.getFZLXBM().equals("0")) {
                for (Map<String, Object> pubbmxx : pageDatapubbmXX) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    if (!lbdmList.contains(fzlx.getFZLXBM() + "-" + pubbmxx.get("bmdm"))) {
                        lbdmList.add(fzlx.getFZLXBM() + "-" + pubbmxx.get("bmdm"));
                        dataPull.put("FZLX", fzlx.getFZLXMC());
                        dataPull.put("FZBM", pubbmxx.get("bmdm"));
                        dataPull.put("FZMC", pubbmxx.get("bmmc"));
                        dataPull.put("SJFZBM", " ");
                        String fzlxjg = fzlx.getFZLXJG();
                        String fzdm = pubbmxx.get("bmdm").toString();
                        List<String> qc = new ArrayList<String>();
                        int jc = 1;
                        if (!StringUtils.isEmpty(fzlxjg)) {
                            String[] fzlxjgStr = fzlxjg.split("-");
                            int num = 0;//3  3  2  2  111 111 11
                            if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                for (int w = 0; w < fzlxjgStr.length; w++) {
                                    num = num + Integer.valueOf(fzlxjgStr[w]);
                                    if (num < fzdm.length()) {
                                        Map<String,Object> bmfzdm = new HashMap<>();
                                        bmfzdm.put("bmdm", fzdm.substring(0, num));
                                        bmfzdm.put("gsdm",datadzzbxx.get("DWDM"));
                                        bmfzdm.put("kjnd",datadzzbxx.get("KJND"));
                                        List<Map<String, Object>> maps = pubbmxxMapper._queryyePubbmxx(bmfzdm);
                                        qc.add(maps.get(0).get("bmmc").toString());
                                        dataPull.put("SJFZBM", maps.get(0).get("bmdm").toString());
                                        jc++;
                                    }
                                }
                            }
                        }
                        qc.add(pubbmxx.get("bmmc").toString());
                        dataPull.put("FZQC", String.join("/", qc));
                        dataPull.put("FZJC", jc);
                        resultList.add(dataPull);
                    }
                }
            } else if (fzlx.getFZLXBM().equals("1")) {
                for (Map<String, Object> xmzl : pageDataxmzl) {
                    Map<String, Object> dataPull = new HashMap<String, Object>();
                    dataPull = new HashMap<String, Object>(dataPullBase);
                    if (!lbdmList.contains(fzlx.getFZLXBM() + "-" + xmzl.get("XMDM"))) {
                        lbdmList.add(fzlx.getFZLXBM() + "-" + xmzl.get("XMDM"));
                        dataPull.put("FZLX", fzlx.getFZLXMC());
                        dataPull.put("FZBM", xmzl.get("XMDM"));
                        dataPull.put("FZMC", xmzl.get("XMMC"));
                        dataPull.put("SJFZBM", " ");
                        String fzlxjg = fzlx.getFZLXJG();
                        String fzdm = xmzl.get("XMDM").toString();
                        List<String> qc = new ArrayList<String>();
                        int jc = 1;
                        if (!StringUtils.isEmpty(fzlxjg)) {
                            String[] fzlxjgStr = fzlxjg.split("-");
                            int num = 0;//3  3  2  2  111 111 11
                            if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                for (int w = 0; w < fzlxjgStr.length; w++) {
                                    num = num + Integer.valueOf(fzlxjgStr[w]);
                                    if (num < fzdm.length()) {
                                        Map<String, Object> queryPd1 = new HashMap<String, Object>();
                                        queryPd1.put("XMDM", fzdm.substring(0, num));
                                        queryPd1.put("KJND", datadzzbxx.get("KJND"));
                                        queryPd1.put("GSDM", datadzzbxx.get("DWDM"));
                                        List<Map<String, Object>> maps = xmzlMapper._queryYeXmzl(queryPd1);
                                        qc.add(maps.get(0).get("XMMC").toString());
                                        dataPull.put("SJFZBM", maps.get(0).get("XMDM").toString());
                                        jc++;
                                    }
                                }
                            }
                        }
                        qc.add(xmzl.get("XMMC").toString());
                        dataPull.put("FZQC", String.join("/", qc));
                        dataPull.put("FZJC", jc);
                        resultList.add(dataPull);
                    }
                }
            } else if (fzlx.getFZLXBM().equals("2")) {
                for (Map<String, Object> pubkszl : pageDataPubkszl) {
                    if (!lbdmList.contains(fzlx.getFZLXBM() + "-" + pubkszl.get("dwdm"))) {
                        Map<String, Object> dataPull = new HashMap<String, Object>();
                        dataPull = new HashMap<String, Object>(dataPullBase);
                        lbdmList.add(fzlx.getFZLXBM() + "-" + pubkszl.get("dwdm"));
                        dataPull.put("FZLX", fzlx.getFZLXMC());
                        dataPull.put("FZBM", fzlx.getFZLXBM());
                        dataPull.put("FZMC", pubkszl.get("dwmc"));
                        dataPull.put("SJFZBM", " ");
                        resultList.add(dataPull);
                        String fzlxjg = fzlx.getFZLXJG();
                        String fzdm = pubkszl.get("dwdm").toString();
                        List<String> qc = new ArrayList<String>();
                        int jc = 1;
                        if (!StringUtils.isEmpty(fzlxjg)) {
                            String[] fzlxjgStr = fzlxjg.split("-");
                            int num = 0;//3  3  2  2  111 111 11
                            if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                for (int w = 0; w < fzlxjgStr.length; w++) {
                                    num = num + Integer.valueOf(fzlxjgStr[w]);
                                    if (num < fzdm.length()) {
                                        Map<String, Object> queryPd = new HashMap<String, Object>();
                                        queryPd.put("dwdm", fzdm.substring(0, num));
                                        queryPd.put("kjnd", datadzzbxx.get("KJND"));
                                        queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                                        List<Map<String, Object>> maps = pubkszlMapper._queryYePubkszl(queryPd);
                                        qc.add(maps.get(0).get("dwmc").toString());
                                        dataPull.put("SJFZBM", maps.get(0).get("dwdm").toString());
                                        jc++;
                                    }
                                }
                            }
                        }
                        qc.add(pubkszl.get("dwmc").toString());
                        dataPull.put("FZQC", String.join("/", qc));
                        dataPull.put("FZJC", jc);
                        resultList.add(dataPull);
                    }
                }
            }else if (fzlx.getFZLXBM().equals("3")) {
                for (Map<String, Object> pubkszl : pageDataPubkszl) {
                    if (!lbdmList.contains(fzlx.getFZLXBM() + "-" + pubkszl.get("dwdm"))) {
                        Map<String, Object> dataPull = new HashMap<String, Object>();
                        dataPull = new HashMap<String, Object>(dataPullBase);
                        lbdmList.add(fzlx.getFZLXBM() + "-" + pubkszl.get("dwdm"));
                        dataPull.put("FZLX", fzlx.getFZLXMC());
                        dataPull.put("FZBM", fzlx.getFZLXBM());
                        dataPull.put("FZMC", pubkszl.get("dwmc"));
                        dataPull.put("SJFZBM", " ");
                        resultList.add(dataPull);
                        String fzlxjg = fzlx.getFZLXJG();
                        String fzdm = pubkszl.get("dwdm").toString();
                        List<String> qc = new ArrayList<String>();
                        int jc = 1;
                        if (!StringUtils.isEmpty(fzlxjg)) {
                            String[] fzlxjgStr = fzlxjg.split("-");
                            int num = 0;//3  3  2  2  111 111 11
                            if (fzlxjgStr != null && fzlxjgStr.length > 0) {
                                for (int w = 0; w < fzlxjgStr.length; w++) {
                                    num = num + Integer.valueOf(fzlxjgStr[w]);
                                    if (num < fzdm.length()) {
                                        Map<String, Object> queryPd = new HashMap<String, Object>();
                                        queryPd.put("dwdm", fzdm.substring(0, num));
                                        queryPd.put("kjnd", datadzzbxx.get("KJND"));
                                        queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                                        List<Map<String, Object>> maps = pubkszlMapper._queryYePubkszl(queryPd);
                                        qc.add(maps.get(0).get("dwmc").toString());
                                        dataPull.put("SJFZBM", maps.get(0).get("dwdm").toString());
                                        jc++;
                                    }
                                }
                            }
                        }
                        qc.add(pubkszl.get("dwmc").toString());
                        dataPull.put("FZQC", String.join("/", qc));
                        dataPull.put("FZJC", jc);
                        resultList.add(dataPull);
                    }
                }
            } else {
                Map<String, Object> dataFzxlb = new HashMap<String, Object>();
                dataFzxlb.put("lbdm", fzlx.getFZLXBM());
                dataFzxlb.put("kjnd", datadzzbxx.get("KJND"));
                dataFzxlb.put("gsdm", datadzzbxx.get("DWDM"));
                List<Map<String, Object>> pageDataFzxzl = fzxzlMapper._queryFzxzl(dataFzxlb);
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
                                                queryPd.put("kjnd", datadzzbxx.get("KJND"));
                                                queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                                                List<Map<String, Object>> pageDataGL_Fzxzl = fzxzlMapper._queryYeFzxzl(queryPd);
                                                if (pageDataGL_Fzxzl !=null && pageDataGL_Fzxzl.size()>0) {
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
                                                }
                                            } else {
                                                Map<String, Object> queryPd = new HashMap<String, Object>();
                                                queryPd.put("fzdm", fzdm.substring(0, num));
                                                queryPd.put("lbdm", fzlx.getFZLXBM());
                                                queryPd.put("kjnd", datadzzbxx.get("KJND"));
                                                queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                                                List<Map<String, Object>> pageDataGL_Fzxzl = fzxzlMapper._queryYeFzxzl  (queryPd);
                                                if (pageDataGL_Fzxzl !=null && pageDataGL_Fzxzl.size()>0) {
                                                    qcList.add(pageDataGL_Fzxzl.get(0).get("fzmc").toString());
                                                }
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
            return true;
        }
        return false;
    }

}
