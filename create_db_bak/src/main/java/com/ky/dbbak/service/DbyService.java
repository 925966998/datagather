package com.ky.dbbak.service;

import com.ky.dbbak.mybatis.PagerResult;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.sourcemapper.SourceMapper;
import com.ky.dbbak.targetmapper.TragetMapper;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DbyService {
    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;

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
                String kmqc = "";
                Map<String, Object> quM = new HashMap<String, Object>();
                for (int w = 0; w < lbfjStr.length; w++) {
                    Map<String, Object> dataPullBase = new HashMap<String, Object>(map);
                    num = num + Integer.valueOf(lbfjStr[w]);
                    if (num < legth) {
                        quM.put("kmdm", map.get("KJKMBM").toString().substring(0, num));
                        List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(quM);
                        dataPullBase.put("KJKMBM", map.get("KJKMBM").toString().substring(0, num));
                        dataPullBase.put("KJKMJC", w + 1);
                        dataPullBase.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                        kmqc += pageDataGL_KMXX.get(0).get("kmmc").toString().trim() + "/";
                        kmqc = kmqc.substring(0, kmqc.length());
                        kmqc = kmqc.replace("　", "");
                        dataPullBase.put("KMQC", kmqc.trim());
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
}
