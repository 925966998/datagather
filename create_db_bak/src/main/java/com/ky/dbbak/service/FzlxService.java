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
    FzxlbMapper fzxlbMapper;




    @Transactional("targetTransactionManager")
    public boolean FzlxB(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> bypznrList = sourceMapper._queryGL_Yeb(pageData);
        this.FzlxBase(KJDZZBBH,bypznrList,1);
        return true;
    }

    @Transactional("targetTransactionManager")
    public boolean Fzlx(String KJDZZBBH) {
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> datadzzbxx = dzzbxxList.get(0);
        List<OrgEntity> Org = orgMapper.queryOrgZT(datadzzbxx.get("KJDZZBBH").toString());
        Map<String, Object> orgData = new HashMap<String, Object>();
        orgData.put("kjnd", Org.get(0).getKjnd());
        orgData.put("gsdm", Org.get(0).getGsdm());
        orgData.put("ZTH", Org.get(0).getZtbh());
        List<Map<String, Object>> bypznrList = yebMapper._queryYebKjnd(orgData);
        this.FzlxBase(KJDZZBBH,bypznrList,2);
        return true;
    }


    public String FzlxBase(String KJDZZBBH,List<Map<String, Object>> bypznrList,int FzlxBBH){
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        Map<String, Object> dataPull = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
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
        for (String str : lbdmList
        ) {
            dataPull = new HashMap<>(dataPull);
            List<Map<String, Object>> pageDataFzxlb =new ArrayList<>();
            if (FzlxBBH ==1){
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", str);
                pageDataFzxlb = sourceMapper._queryGL_Fzxlb(queryPd);
            }else if (FzlxBBH ==2){
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", str);
                queryPd.put("gsdm", Org.get(0).getGsdm());
                queryPd.put("kjnd", Org.get(0).getKjnd());
                pageDataFzxlb = fzxlbMapper._queryFzxlb(queryPd);
            }
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
        return "success";
    }

}
