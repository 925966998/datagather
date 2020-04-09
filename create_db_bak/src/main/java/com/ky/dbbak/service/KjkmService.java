package com.ky.dbbak.service;

import com.ky.dbbak.entity.FZLXEntity;
import com.ky.dbbak.entity.OrgEntity;
import com.ky.dbbak.mapper.OrgMapper;
import com.ky.dbbak.sourcemapper.*;
import com.ky.dbbak.targetmapper.FzlxMapper;
import com.ky.dbbak.targetmapper.KjkmMapper;
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
public class KjkmService {
    @Autowired
    SourceMapper sourceMapper;

    @Autowired
    TragetMapper tragetMapper;

    @Autowired
    OrgMapper orgMapper;

    @Autowired
    KmxxMapper kmxxMapper;

    @Autowired
    FzxlbService fzxlbService;

    @Autowired
    KmxzlxMapper kmxzlxMapper;

    @Autowired
    ZtcsMapper ztcsMapper;

    @Autowired
    FzxlbMapper fzxlbMapper;

    @Autowired
    KjkmMapper kjkmMapper;

    @Transactional("targetTransactionManager")
    public boolean kjkmB(List<Map<String, Object>> dzzbxxList,List<Map<String, Object>> kjkmList,Map<String, Object> stringObjectMap){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
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
            String lxdm1 = kj.get("kmxz").toString();
            List<Map<String, Object>> _queryKMXZLX = kmxzlxMapper._queryKMXZLX(lxdm1);
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
            if (a > 1) {
                dataPull.put("FZHSBZ", 1);
            } else {
                dataPull.put("FZHSBZ", 0);
            }
            String[] fzhsbz = kj.get("fzhs").toString().split(",");
            System.out.println(fzhsbz.length);
            if (fzhsbz.length > 1) {
                String fzhsx = "";
                for (int x = 1; x < fzhsbz.length; x++) {
                    if (!StringUtils.isEmpty(fzhsbz[x])) {
                        String lbdm = fzhsbz[x];
                        System.out.println(lbdm);
                        if (stringObjectMap.get(lbdm) != null) {
                            Map<String, Object> o = (Map<String, Object>) stringObjectMap.get(lbdm);
                            fzhsx += o.get("lbmc").toString().trim() + ",";
                        }
                    }
                }
                if (!StringUtils.isEmpty(fzhsx)) {
                    fzhsx = fzhsx.substring(0, fzhsx.length() - 1);
                    fzhsx = fzhsx.replace("　", "");
                    dataPull.put("FZHSX", fzhsx.trim());
                } else {
                    dataPull.put("FZHSX", " ");
                }
            } else {
                dataPull.put("FZHSX", " ");
            }
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace(" ",""));
                //18.余额方向
                String yefx = pageDataGL_KMXX.get(0).get("yefx").toString();
                if (!yefx.equals("") || !StringUtils.isEmpty(yefx)) {
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
                    kmqc = kmqc.substring(kmqc.lastIndexOf(kmqc), kmqc.length() - 1);
                    kmqc = kmqc.replace("　", "");
                    dataPull.put("KMQC", kmqc.trim());
                } else {
                    dataPull.put("KJKMJC", 1);
                    dataPull.put("SJKMBM", " ");
                    dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace(" ",""));
                }
            }
            resultList.add(dataPull);
        }
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                kjkmMapper._add(map1);
            }
            return true;
        }
        String ds = resultList.get(0).get("ds").toString();
        return  false;
    }

    public List Kjkm(String KJDZZBBH){
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        Map<String, Object> orgData = new HashMap<String, Object>();
        orgData.put("kjnd",Org.get(0).getKjnd());
        orgData.put("gsdm",Org.get(0).getOrgCode());
        orgData.put("ZTH",Org.get(0).getZt());
        List<Map<String, Object>> kjkmList = kmxxMapper._queryKmxxList(orgData);
        return kjkmList;
    }

    public Map<String, Object> _queryGL_Fzxlb1(String KJDZZBBH) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        Map<String, Object> orgData = new HashMap<String, Object>();
        orgData.put("kjnd",Org.get(0).getKjnd());
        orgData.put("gsdm",Org.get(0).getOrgCode());
        List<Map<String, Object>> maps = fzxlbMapper._querykmFzxlb(orgData);
        if (maps != null && maps.size() > 0) {
            for (Map<String, Object> resultMap : maps
            ) {
                map.put(resultMap.get("lbdm").toString(), resultMap);
            }
        }
        return map;
    }

    public boolean Kjkmxx (String KJDZZBBH, List<Map<String, Object>> kjkmList,Map<String, Object> stringObjectMap){
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("KJDZZBBH", KJDZZBBH);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<OrgEntity> Org = orgMapper.queryOrgZT(KJDZZBBH);
        Map<String, Object> ztcsStr = new HashMap<String, Object>();
        ztcsStr.put("kjnd",Org.get(0).getKjnd());
        ztcsStr.put("ztbh",Org.get(0).getZt());
        List<Map<String, Object>> pageDataGL_Ztcs = ztcsMapper._queryZtcszh(ztcsStr);
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
            Map<String,Object>  kmxzlStr= new HashMap<>();
            kmxzlStr.put("lxdm",kj.get("kmxz"));
            kmxzlStr.put("gsdm",Org.get(0).getOrgCode());
            kmxzlStr.put("zth",Org.get(0).getZt());
            List<Map<String, Object>> _queryKMXZLX = kmxzlxMapper._queryGL_KMXZLX(kmxzlStr);
            dataPull.put("KMLBMC", _queryKMXZLX.get(0).get("lxmc"));
            //17.计量单位代码
            dataPull.put("JLDWDM", " ");
            //21.是否现金或现金等价物
            dataPull.put("SFXJHXJDJW", 0);
            Map<String, Object> pageData1 = new HashMap<String, Object>();
            pageData1.put("kmdm",kj.get("kmdm"));
            pageData1.put("gsdm",Org.get(0).getOrgCode());
            pageData1.put("ZTH",Org.get(0).getZt());
            pageData1.put("kjnd",Org.get(0).getKjnd());
            List<Map<String, Object>> pageDataGL_KMXX = kmxxMapper._queryKmxxmx(pageData1);
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
            if (a > 1) {
                dataPull.put("FZHSBZ", 1);
            } else {
                dataPull.put("FZHSBZ", 0);
            }
            String[] fzhsbz = kj.get("fzhs").toString().split(",");
            System.out.println(fzhsbz.length);
            if (fzhsbz.length > 1) {
                String fzhsx = "";
                for (int x = 1; x < fzhsbz.length; x++) {
                    if (!StringUtils.isEmpty(fzhsbz[x])) {
                        String lbdm = fzhsbz[x];
                        System.out.println(lbdm);
                        if (stringObjectMap.get(lbdm) != null) {
                            Map<String, Object> o = (Map<String, Object>) stringObjectMap.get(lbdm);
                            fzhsx += o.get("lbmc").toString().trim() + ",";
                        }
                    }
                }
                if (!StringUtils.isEmpty(fzhsx)) {
                    fzhsx = fzhsx.substring(0, fzhsx.length() - 1);
                    fzhsx = fzhsx.replace("　", "");
                    dataPull.put("FZHSX", fzhsx.trim());
                }else{
                    dataPull.put("FZHSX", " ");
                }
            } else {
                dataPull.put("FZHSX", " ");
            }
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace(" ",""));
                //18.余额方向
                String yefx = pageDataGL_KMXX.get(0).get("yefx").toString();
                if (!yefx.equals("") || !StringUtils.isEmpty(yefx)) {
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
                            queryPd.put("kjnd", Org.get(0).getKjnd());
                            queryPd.put("gsdm", Org.get(0).getOrgCode());
                            queryPd.put("ZTH", Org.get(0).getZt());
                            List<Map<String, Object>> pageDataGL_KMXXQc = kmxxMapper._queryKmxxmx(queryPd);
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
                    dataPull.put("KMQC", pageDataGL_KMXX.get(0).get("kmmc").toString().trim().replace(" ",""));
                }
            }
            resultList.add(dataPull);
        }
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                kjkmMapper._add(map1);
            }
            return true;
        }
        return false;
    }
}
