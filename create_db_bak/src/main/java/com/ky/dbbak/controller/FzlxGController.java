package com.ky.dbbak.controller;


import com.ky.dbbak.service.FzlxService;
import com.ky.dbbak.service.FzyeService;
import com.ky.dbbak.service.KjkmService;
import com.ky.dbbak.targetmapper.FzlxMapper;
import com.ky.dbbak.targetmapper.FzxxMapper;
import com.ky.dbbak.targetmapper.FzyeMapper;
import com.ky.dbbak.targetmapper.KjkmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ky-datagather/fzlx/")
public class FzlxGController {

    @Autowired
    FzlxService fzlxService;
    @Autowired
    FzlxMapper fzlxMapper;
    @Autowired
    KjkmService kjkmService;
    @Autowired
    KjkmMapper kjkmMapper;
    @Autowired
    FzxxMapper fzxxMapper;
    @Autowired
    FzyeService fzyeService;
    @Autowired
    FzyeMapper fzyeMapper;

    @RequestMapping(value = "fzlxGB")
    @ResponseBody
    public String fzlx_G(String KJDZZBBH)throws Exception {
        List<String> lbdmList = fzlxService.Fzlx(KJDZZBBH);
        List<Map<String, Object>> resultList = fzlxService.FzlxStr(lbdmList);
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                fzlxMapper._addFzlx(map1);
            }
        }
        return "success";
    }


    /*会计科目表 */
    @RequestMapping(value = "kjkmGB")
    @ResponseBody
    public String kjkm_G(String KJDZZBBH) throws Exception {
        List<Map<String, Object>> kjkmList = kjkmService.Kjkm(KJDZZBBH) ;
        Map<String, Object> stringObjectMap = kjkmService._queryGL_Fzxlb1(KJDZZBBH);
        List<Map<String, Object>> resultList = kjkmService.Kjkmxx(KJDZZBBH ,kjkmList,stringObjectMap);
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                kjkmMapper._add(map1);
            }
        }
        return "success";
    }

    @RequestMapping(value = "fzxxtwoGB")
    @ResponseBody
    public String Fzxxtwo_G(String KJDZZBBH) throws Exception {
        Map<String, Object> dataPullBase = fzlxService.Fzxx(KJDZZBBH);
        List<Map<String, Object>> pageDatapubbmXX = fzlxService.pubbmxx(KJDZZBBH);
        List<Map<String, Object>> pageDataxmzl = fzlxService.Xmzl(KJDZZBBH);
        List<Map<String, Object>> pageDataPubkszl = fzlxService.Pubkszl(KJDZZBBH);
        List<Map<String, Object>> resultList = fzlxService.FzxxStr(dataPullBase,pageDatapubbmXX,pageDataxmzl,pageDataPubkszl,KJDZZBBH);
        if (resultList != null && resultList.size() > 0) {
            for (Map map1 : resultList
            ) {
                fzxxMapper._add(map1);
            }
        }
        return "success";
    }

    @RequestMapping(value = "fzyeGB")
    @ResponseBody
    public String fzye_G(String KJDZZBBH) {
        List<Map<String, Object>> GL_YebList = fzyeService.fzye(KJDZZBBH);
        Map<String, Object> stringObjectMap = kjkmService._queryGL_Fzxlb1(KJDZZBBH);
        List<Map<String, Object>> pageDataGL_Ztcs = fzyeService.ZtcsStr(KJDZZBBH);
        List<Map<String, Object>> resultList = fzyeService.fhyexx(KJDZZBBH,GL_YebList,stringObjectMap,pageDataGL_Ztcs);
        List<Map<String, Object>> resultListNew = fzyeService.kjkmResult(resultList, pageDataGL_Ztcs.get(0),KJDZZBBH);
        List<String> resultMapListStr = new ArrayList<String>();
        List<String> resultMapHaveListStr = new ArrayList<String>();
        List<Map<String, Object>> resultListNew2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultListNew2Have = new ArrayList<Map<String, Object>>();
        if (resultListNew != null && resultListNew.size() > 0) {
            for (Map<String, Object> map : resultListNew
            ) {
                if (!resultMapListStr.contains(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM")+ "-" + map.get("FZLX")+ "-" + map.get("FZBM"))) {
                    resultMapListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM")+ "-" + map.get("FZLX")+ "-" + map.get("FZBM"));
                    resultListNew2.add(map);
                } else {
                    resultMapHaveListStr.add(map.get("KJDZZBBH") + "-" + map.get("KJYF") + "-" + map.get("KJKMBM")+ "-" + map.get("FZLX")+ "-" + map.get("FZBM"));
                    resultListNew2Have.add(map);
                }

            }
        }
        for (Map map3 : resultListNew2
        ) {
            for (Map map4 : resultListNew2Have
            ) {
                if ((map3.get("KJDZZBBH") + "-" + map3.get("KJYF") + "-" + map3.get("KJKMBM")+ "-" + map3.get("FZLX")+ "-" +map3.get("FZBM")).equals(map4.get("KJDZZBBH") + "-" + map4.get("KJYF") + "-" + map4.get("KJKMBM")+ "-" + map4.get("FZLX")+ "-" + map4.get("FZBM"))) {
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
                try {
                    fzyeMapper._add(map1);
                }catch (Exception e){
                    System.out.println(map1);
                }
            }
        }
        return "success";
    }
}
