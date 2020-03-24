package com.ky.dbbak.controller;

import com.ky.dbbak.mapper.FzyeMapper;
import com.ky.dbbak.sourcemapper.SourceMapper;
import com.ky.dbbak.sourcemapper.YebMapper;
import com.ky.dbbak.targetmapper.TragetMapper;
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
@RequestMapping(value = "/fzye/")
public class FzyeController {

    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    SourceMapper sourceMapper;

    @Autowired
    YebMapper yebMapper;

    @Autowired
    FzyeMapper fzyeMapper;


    /*第五张——辅助余额表*/
    @RequestMapping(value = "Fzye")
    @ResponseBody
    public String Fzye(String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        //行政区域代码
        pageData.put("XZQHDM", XZQHDM);
        List<Map<String, Object>> dzzbxxList = tragetMapper._queryDzzbxx(pageData);
        List<Map<String, Object>> GL_YebList = sourceMapper._queryGL_Yeb(pageData);
        for (Map<String, Object> pd : GL_YebList
        ) {
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
            //8、会计月份



            //9、会计体系
            dataPull.put("KJTX", pd.get("KJTXDM"));
            //10、会计科目编码
            dataPull.put("KJKMBM", pd.get("kmdm"));
            //11、会计科目名称
            List<Map<String, Object>> dataKmxx = sourceMapper._queryGL_KMXX(pd);
            dataPull.put("KJKMMC", dataKmxx.get(0).get("kmmc"));
            //12、会计科目全称



            //13、年初借方余额
            dataPull.put("NCJFYE",GL_YebList.get(0).get("ncj"));
            //14、年初贷方余额
            dataPull.put("NCJFYE",GL_YebList.get(0).get("ncd"));
            //15、年初余额方向
            int a = Integer.valueOf(GL_YebList.get(0).get("ncj").toString());
            int b = Integer.valueOf(GL_YebList.get(0).get("ncd").toString());
            if ( a > b){
                dataPull.put("NCYEFX",1);
            }else if (a==b){
                dataPull.put("NCYEFX",0);
            }else {
                dataPull.put("NCYEFX",-1);
            }
            int qcjfye= (int)GL_YebList.get(0).get("ncj") ;
            int qcdfye= (int)GL_YebList.get(0).get("ncd") ;
            for (int i=1 ; i <13;i++){
                //16、期初借方余额
                dataPull.put("QCJFYE",qcjfye);
                qcjfye+=(int)pd.get("yj"+i);
                //17、期初贷方余额
                dataPull.put("QCDFYE",qcdfye);
                qcdfye+=(int)pd.get("yd"+i);
                if (qcjfye>qcjfye){
                    //18、期初余额方向
                    dataPull.put("QCYEFX",1);
                }else if (qcjfye==qcjfye){
                    //18、期初余额方向
                    dataPull.put("QCYEFX",0);
                }else {
                    //18、期初余额方向
                    dataPull.put("QCYEFX",-1);
                }
            }


            //26、QCWBJFYE期初外币借方余额
            dataPull.put("QCWBJFYE",0);
            //27期初外币贷方余额
            dataPull.put("QCWBDFYE",0);
            //28.借方外币发生额
            dataPull.put("JFWBFSE",0);
            //29.贷方外币发生额
            dataPull.put("DFWBFSE",0);
            //30.期末外币借方余额
            dataPull.put("QMWBJFYE",0);
            //31.期末外币贷方余额
            dataPull.put("QMWBDFYE",0);
            //32.会计科目级别
            List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(pd);
            if (pageDataGL_KMXX != null && pageDataGL_KMXX.size() > 0) {
                Integer legth = pageDataGL_KMXX.get(0).get("kmdm").toString().length();
                switch (legth) {
                    case 4:
                        dataPull.put("KJKMJB", 1);
                        break;
                    case 6:
                        dataPull.put("KJKMJB", 2);
                        //35.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 4));
                        break;
                    case 8:
                        dataPull.put("KJKMJB", 3);;
                        //35.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 6));
                        break;
                    case 10:
                        dataPull.put("KJKMJB", 4);
                        //35.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 8));
                        break;
                    case 12:
                        dataPull.put("KJKMJB", 5);
                        //35.上级科目编码
                        dataPull.put("SJKMBM", pageDataGL_KMXX.get(0).get("kmdm").toString().substring(0, 10));
                        break;
                }
                //33.是否最底级科目
                dataPull.put("SFZDJKM", pageDataGL_KMXX.get(0).get("kmmx"));
            }
            //34.币种名称
            dataPull.put("BZMC", dzzbxxList.get(0).get("BWB"));

            //36.分录数
            //37.辅助类型
            //38.辅助编码
            //39.辅助名称
            //40.辅助级别
            //41.上级辅助编码


            //42.币种代码
            dataPull.put("BZDM", null);

        }
        Integer listNum = resultList.size();
        Integer listnum2 = listNum % 50;
        Integer listnum3 = listNum / 50;
        Map map = new HashMap();
        for (int p = 0; p < listnum3; p++) {
            map.put("list", resultList.subList(p * 50, (p * 50 + 50)));
            fzyeMapper._addFzye(map);
        }
        map.put("list", resultList.subList(resultList.size() - listnum2, resultList.size()));
        fzyeMapper._addFzye(map);
        return "FZYE-辅助余额表生成完成";
    }



}
