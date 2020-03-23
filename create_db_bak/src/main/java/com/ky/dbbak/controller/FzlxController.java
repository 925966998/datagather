package com.ky.dbbak.controller;

import com.ky.dbbak.mapper.DbMapper;
import com.ky.dbbak.mapper.FzlxMapper;
import com.ky.dbbak.mapper.FzxxMapper;
import com.ky.dbbak.mapper.KjkmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/fzlx/")
public class FzlxController {

    @Autowired
    DbMapper dbMapper;

    @Autowired
    FzlxMapper fzlxMapper;

    @Autowired
    FzxxMapper fzxxMapper;

    @Autowired
    KjkmMapper kjkmMapper;

    @RequestMapping(value = "fzlx")
    @ResponseBody
    public String fzlx(Integer bid) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = dbMapper._queryGL_Yeb(pageData);
        List<Map<String, Object>> dzzbxxList = dbMapper._queryDzzbxx(pageData);
        for (Map<String, Object> pd : GL_YebList
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
            for (int i = 0; i < 31; i++){
                dataPull.put("FZLXBM",i);
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("lbdm", i);
                List<Map<String, Object>> pageDataFzxlb = dbMapper._queryGL_Fzxlb(queryPd);
                if (pageDataFzxlb != null && pageDataFzxlb.size() > 0) {
                    dataPull.put("FZLXMC", pageDataFzxlb.get(0).get("lbmc"));
                    dataPull.put("FZLXJG", pageDataFzxlb.get(0).get("lbfj"));
                }
                resultList.add(dataPull);
            }

        }
        for (Map<String, Object> map : resultList
        ) {
            fzlxMapper._addFzlx(map);
        }
        return "FZLX-辅助类型表生成完成";
    }



    /* 辅助信息表*/
    @RequestMapping(value = "fzxx")
    @ResponseBody
    public String fzxx(Integer bid) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> GL_YebList = dbMapper._queryGL_Yeb(pageData);
        List<Map<String, Object>> dzzbxxList = dbMapper._queryDzzbxx(pageData);
        for (Map<String, Object> pd : GL_YebList
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
            dataPull.put("FZSM", null);
            dataPull.put("SFWYSFZ", 0);
            if (pd.get("fzdm0") != null && !StringUtils.isEmpty(pd.get("fzdm0").toString().trim())) {
                dataPull.put("FZLX", "部门");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("bmdm", pd.get("fzdm0"));
                List<Map<String, Object>> pageDataPUBBMXX = dbMapper._queryPubbmxx(queryPd);
                List<Map<String, Object>> pageDataFzxlb = dbMapper._queryGL_Fzxlb(queryPd);
                if (pageDataPUBBMXX != null && pageDataPUBBMXX.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBBMXX.get(0).get("bmdm"));
                    dataPull.put("FZMC", pageDataPUBBMXX.get(0).get("bmmc"));
                    dataPull.put("FZQC",pageDataPUBBMXX.get(0).get("bmmc"));
                    dataPull.put("FZJC",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    dataPull.put("SJFZBM",null);
                }
                resultList.add(dataPull);
            }
            if (pd.get("fzdm1") != null && !StringUtils.isEmpty(pd.get("fzdm1").toString().trim())) {
                dataPull.put("FZLX", "项目");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("xmdm", pd.get("fzdm1"));
                List<Map<String, Object>> pageDataGL_Xmzl = dbMapper._queryGL_Xmzl(queryPd);
                List<Map<String, Object>> pageDataFzxlb = dbMapper._queryGL_Fzxlb(queryPd);
                if (pageDataGL_Xmzl != null && pageDataGL_Xmzl.size() > 0) {
                    dataPull.put("FZBM", pageDataGL_Xmzl.get(0).get("XMDM"));
                    dataPull.put("FZMC", pageDataGL_Xmzl.get(0).get("XMMC"));
                    dataPull.put("FZQC",pageDataGL_Xmzl.get(0).get("XMMC"));
                    dataPull.put("FZJC",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    dataPull.put("SJFZBM",null);
                }

                resultList.add(dataPull);
            }
            if (pd.get("fzdm2") != null && !StringUtils.isEmpty(pd.get("fzdm2").toString().trim())) {

            }
            if (pd.get("fzdm3") != null && !StringUtils.isEmpty(pd.get("fzdm3").toString().trim())) {
                dataPull.put("FZLX", "往来单位");
                Map<String, Object> queryPd = new HashMap<String, Object>();
                queryPd.put("wldm", pd.get("fzdm3"));
                List<Map<String, Object>> pageDataPUBKSZL = dbMapper._queryPUBKSZL(queryPd);
                List<Map<String, Object>> pageDataFzxlb = dbMapper._queryGL_Fzxlb(queryPd);
                if (pageDataPUBKSZL != null && pageDataPUBKSZL.size() > 0) {
                    dataPull.put("FZBM", pageDataPUBKSZL.get(0).get("dwdm"));
                    dataPull.put("FZMC", pageDataPUBKSZL.get(0).get("dwmc"));
                    dataPull.put("FZQC",pageDataPUBKSZL.get(0).get("dwmc"));
                    dataPull.put("FZJC",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    dataPull.put("SJFZBM",null);
                }
                resultList.add(dataPull);
            }
            for (int q = 4; q < 31; q++) {
                if (pd.get("fzdm" + q) != null && !StringUtils.isEmpty(pd.get("fzdm" + q).toString().trim())) {
                    Map<String, Object> queryPd = new HashMap<String, Object>();
                    queryPd.put("fzdm", pd.get("fzdm" + q));
                    queryPd.put("lbdm", String.valueOf(q));
                    List<Map<String, Object>> pageDataFzxlb = dbMapper._queryGL_Fzxlb(queryPd);
                    List<Map<String, Object>> pageDataGL_Fzxzl = dbMapper._queryGL_Fzxzl(queryPd);
                    if (pageDataFzxlb != null && pageDataFzxlb.size() > 0) {
                        dataPull.put("FZLX", pageDataFzxlb.get(0).get("lbmc"));
                    }
                    if (pageDataGL_Fzxzl != null && pageDataGL_Fzxzl.size() > 0) {
                        dataPull.put("FZBM", pageDataGL_Fzxzl.get(0).get("fzdm"));
                        dataPull.put("FZMC", pageDataGL_Fzxzl.get(0).get("fzmc"));
                        String a[] = pageDataFzxlb.get(0).get("lbfj").toString().split("-");
                        for ( int i=0; i<pageDataFzxlb.get(0).get("lbfj").toString().split("-").length+1;i++){

                        }
                        dataPull.put("FZJC",pageDataFzxlb.get(0).get("lbfj").toString().split("-").length);
                    }
                    resultList.add(dataPull);
                }
            }

        }
        for (Map<String, Object> map : resultList
        ) {
            fzxxMapper._addFzxx(map);
        }
        return "FZLX-辅助信息表生成完成";
    }




    /*会计科目表 */
    @RequestMapping(value = "kjkm")
    @ResponseBody
    public String index(Integer bid) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        pageData.put("id", bid);
        List<Map<String, Object>> bypznrList = dbMapper._queryPznr(pageData);
        List<Map<String, Object>> dzzbxxList = dbMapper._queryDzzbxx(pageData);
        int i = 1;
        int flag = 1;
        for (Map<String, Object> pd : bypznrList
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
            dataPull.put("FZHSBZ",  null);
            dataPull.put("FZHSX",  null);


            List<Map<String, Object>>  dataKmxx1 = kjkmMapper._queryKmxx();
            for (int j = 0; j<9;j++){
                dataPull.put("KMLBBH", dataKmxx1.get(j).get("lxdm"));
                dataPull.put("KMLBMC", dataKmxx1.get(j).get("lxmc"));
            }
            List<Map<String, Object>> pageDataGL_KMXX = dbMapper._queryGL_KMXX(pd);
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                dataPull.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("D")) {
                    dataPull.put("YEFX", -1);
                }
                if (pageDataGL_KMXX.get(0).get("yefx").toString().equals("J")) {
                    dataPull.put("YEFX", 1);
                }

                Integer legth = pageDataGL_KMXX.get(0).get("kmdm").toString().length();
                switch (legth) {
                    case 4:
                        dataPull.put("KJKMJC", 1);
                        break;
                    case 6:
                        dataPull.put("KJKMJC", 2);
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 4));
                        break;
                    case 8:
                        dataPull.put("KJKMJC", 3);
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 6));
                        break;
                    case 10:
                        dataPull.put("KJKMJC", 4);
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 8));
                        break;
                    case 12:
                        dataPull.put("KJKMJC", 5);
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 10));
                        break;
                }
                dataPull.put("KJTX", pageDataGL_KMXX.get(0).get("KJTXDM"));
                dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));

            }
            dataPull.put("KJTX", pd.get("KJTXDM"));
            dataPull.put("KJKMBM", pd.get("kmdm"));
            List<Map<String, Object>> dataKmxx = dbMapper._queryGL_KMXX(pd);
            dataPull.put("KJKMMC", dataKmxx.get(0).get("kmmc"));
            dataPull.put("JLDWDM", null);
            dataPull.put("JLDWDM", 0);
        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        for (Map<String, Object> map : resultList
        ) {
            kjkmMapper._addKjkm(map);
        }
        return "KJKM-会计科目表生成完成";
    }


}
