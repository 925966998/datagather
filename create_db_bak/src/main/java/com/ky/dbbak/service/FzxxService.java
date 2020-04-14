package com.ky.dbbak.service;


import com.ky.dbbak.entity.FZLXEntity;
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
public class FzxxService {

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


    public String FzxxBase(String KJDZZBBH,int FzxxBBH){
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<FZLXEntity> fzlxEntityList = fzlxMapper._queryAll(pageData);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> dataPullBase = new HashMap<>();
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
        List<String> lbdmList = new ArrayList<String>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (FZLXEntity fzlx : fzlxEntityList) {
            if (fzlx.getFZLXBM().equals("0")) {
                List<Map<String, Object>> pageDatapubbmXX = new ArrayList<>();
                if (FzxxBBH == 1){
                    pageDatapubbmXX = kmxzlxMapper._queryPUBBMXX();
                }else if (FzxxBBH == 2){
                    Map<String, Object> orgData = new HashMap<String, Object>();
                    orgData.put("kjnd", datadzzbxx.get("KJND"));
                    orgData.put("gsdm", datadzzbxx.get("DWDM"));
                    pageDatapubbmXX = pubbmxxMapper._queryPubbmxx(orgData);
                }
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
                                        List<Map<String, Object>> maps = new ArrayList<>();
                                        if (FzxxBBH ==1){
                                            maps = kmxzlxMapper._queryPUBBMXXq(fzdm.substring(0, num));
                                        }else if (FzxxBBH ==2){
                                            Map<String, Object> bmfzdm = new HashMap<>();
                                            bmfzdm.put("bmdm", fzdm.substring(0, num));
                                            bmfzdm.put("gsdm", datadzzbxx.get("DWDM"));
                                            bmfzdm.put("kjnd", datadzzbxx.get("KJND"));
                                            maps = pubbmxxMapper._queryyePubbmxx(bmfzdm);
                                        }
                                        if (maps != null && maps.size() > 0 && maps.get(0).get("bmmc") != null) {
                                            qc.add(maps.get(0).get("bmmc").toString());
                                        }
                                        dataPull.put("SJFZBM", fzdm.substring(0, num));
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
                List<Map<String, Object>> pageDataxmzl = new ArrayList<>();
                if (FzxxBBH==1){
                    pageDataxmzl= kmxzlxMapper._queryGL_XMZL();
                }else if (FzxxBBH==2){
                    Map<String, Object> orgData = new HashMap<String, Object>();
                    orgData.put("KJND", datadzzbxx.get("KJND"));
                    orgData.put("GSDM", datadzzbxx.get("DWDM"));
                    pageDataxmzl = xmzlMapper._queryXmzl(orgData);
                }
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
                                        List<Map<String, Object>> maps = new ArrayList<>();
                                        if (FzxxBBH==1){
                                            maps = kmxzlxMapper._queryXMZLq(fzdm.substring(0, num));
                                        }else if (FzxxBBH==2){
                                            Map<String, Object> queryPd1 = new HashMap<String, Object>();
                                            queryPd1.put("XMDM", fzdm.substring(0, num));
                                            queryPd1.put("KJND", datadzzbxx.get("KJND"));
                                            queryPd1.put("GSDM", datadzzbxx.get("DWDM"));
                                            maps = xmzlMapper._queryYeXmzl(queryPd1);
                                        }
                                        if (maps != null && maps.size() > 0) {
                                            if (maps.get(0).get("XMMC") != null) {
                                                qc.add(maps.get(0).get("XMMC").toString());
                                            }
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
            }else if (fzlx.getFZLXBM().equals("2") && FzxxBBH==2) {
                Map<String, Object> orgData = new HashMap<String, Object>();
                orgData.put("KJND", datadzzbxx.get("KJND"));
                orgData.put("GSDM", datadzzbxx.get("DWDM"));
                List<Map<String, Object>> pageDataPubkszl = pubkszlMapper._queryPubkszl(orgData);
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
                                        Map<String, Object> queryPd = new HashMap<String, Object>();
                                        queryPd.put("dwdm", fzdm.substring(0, num));
                                        queryPd.put("kjnd", datadzzbxx.get("KJND"));
                                        queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                                        List<Map<String, Object>> maps= pubkszlMapper._queryYePubkszl(queryPd);
                                        if (maps!=null && maps.size()>0 && maps.get(0).get("dwmc") != null) {
                                            qc.add(maps.get(0).get("dwmc").toString());
                                        }
                                        dataPull.put("SJFZBM", fzdm.substring(0,num));
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
                List<Map<String, Object>> pageDataPubkszl = new ArrayList<>();
                if (FzxxBBH==1){
                    pageDataPubkszl = kmxzlxMapper._queryPUBKSZL();
                }else if (FzxxBBH==2){
                    Map<String, Object> orgData = new HashMap<String, Object>();
                    orgData.put("KJND", datadzzbxx.get("KJND"));
                    orgData.put("GSDM", datadzzbxx.get("DWDM"));
                    pageDataPubkszl = pubkszlMapper._queryPubkszl(orgData);
                }
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
                                        List<Map<String, Object>> maps = new ArrayList<>();
                                        if (FzxxBBH==1){
                                            maps = kmxzlxMapper._queryPUBKSZLq(fzdm.substring(0, num));
                                        }else if (FzxxBBH==2){
                                            Map<String, Object> queryPd = new HashMap<String, Object>();
                                            queryPd.put("dwdm", fzdm.substring(0, num));
                                            queryPd.put("kjnd", datadzzbxx.get("KJND"));
                                            queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                                            maps = pubkszlMapper._queryYePubkszl(queryPd);
                                        }
                                        if (maps!=null && maps.size()>0 && maps.get(0).get("dwmc") != null) {
                                            qc.add(maps.get(0).get("dwmc").toString());
                                        }
                                        dataPull.put("SJFZBM", fzdm.substring(0,num));
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
                List<Map<String, Object>> pageDataFzxzl = new ArrayList<>();
                if (FzxxBBH==1){
                    Map<String, Object> dataFzxlb = new HashMap<String, Object>();
                    dataFzxlb.put("lbdm", fzlx.getFZLXBM());
                    pageDataFzxzl = kmxzlxMapper._queryGL_Fzxzl(dataFzxlb);
                }else if (FzxxBBH==2){
                    Map<String, Object> dataFzxlb = new HashMap<String, Object>();
                    dataFzxlb.put("lbdm", fzlx.getFZLXBM());
                    dataFzxlb.put("kjnd", datadzzbxx.get("KJND"));
                    dataFzxlb.put("gsdm", datadzzbxx.get("DWDM"));
                    pageDataFzxzl = fzxzlMapper._queryFzxzl(dataFzxlb);
                }
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
                                                List<Map<String, Object>> pageDataGL_Fzxzl = new ArrayList<>();
                                                if (FzxxBBH==1){
                                                    queryPd.put("fzdm", fzdm.substring(0, num));
                                                    queryPd.put("lbdm", fzlx.getFZLXBM());
                                                    pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
                                                }else if (FzxxBBH ==2){
                                                    queryPd.put("fzdm", fzdm.substring(0, num));
                                                    queryPd.put("lbdm", fzlx.getFZLXBM());
                                                    queryPd.put("kjnd", datadzzbxx.get("KJND"));
                                                    queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                                                    pageDataGL_Fzxzl = fzxzlMapper._queryYeFzxzl(queryPd);
                                                }
                                                if (pageDataGL_Fzxzl.size() > 0 && pageDataGL_Fzxzl != null) {
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
                                                List<Map<String, Object>> pageDataGL_Fzxzl = new ArrayList<>();
                                                if (FzxxBBH==1){
                                                    queryPd.put("fzdm", fzdm.substring(0, num));
                                                    queryPd.put("lbdm", fzlx.getFZLXBM());
                                                    pageDataGL_Fzxzl = sourceMapper._queryGL_Fzxzl(queryPd);
                                                }else if (FzxxBBH==2){
                                                    queryPd.put("fzdm", fzdm.substring(0, num));
                                                    queryPd.put("lbdm", fzlx.getFZLXBM());
                                                    queryPd.put("kjnd", datadzzbxx.get("KJND"));
                                                    queryPd.put("gsdm", datadzzbxx.get("DWDM"));
                                                    pageDataGL_Fzxzl = fzxzlMapper._queryYeFzxzl(queryPd);
                                                }
                                                if (pageDataGL_Fzxzl.size() > 0 && pageDataGL_Fzxzl != null) {
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
        }
        return "success";
    }

    public boolean fzxxB(String KJDZZBBH){
        this.FzxxBase(KJDZZBBH,1);
        return true;
    }

    public boolean fzxxG(String KJDZZBBH){
        this.FzxxBase(KJDZZBBH,2);
        return true;
    }

}
