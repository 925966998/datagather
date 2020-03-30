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
public class kmyeService {
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
                List kmdms = new ArrayList();
                for (int w = 0; w < lbfjStr.length; w++) {
                    Map<String, Object> dataPullBase = new HashMap<String, Object>(map);
                    num = num + Integer.valueOf(lbfjStr[w]);
                    if (num < legth) {
                        quM.put("kmdm", map.get("KJKMBM").toString().substring(0, num));
                        List<Map<String, Object>> pageDataGL_KMXX = sourceMapper._queryGL_KMXX(quM);
                        dataPullBase.put("KJKMBM", map.get("KJKMBM").toString().substring(0, num));
                        dataPullBase.put("KJKMJB", w + 1);
                        dataPullBase.put("KJKMMC", pageDataGL_KMXX.get(0).get("kmmc"));

                        kmdms.add(map.get("KJKMBM").toString().substring(0, num));
                        Map<String, Object> queryPd = new HashMap<String, Object>();
                        queryPd.put("kmdms", kmdms);
                        List<String> pageDataGL_KMXX1 = sourceMapper._queryGL_KMXX1(queryPd);
                        kmqc = String.join("/", pageDataGL_KMXX1);
                        dataPullBase.put("KJKMQC", kmqc.trim());
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

    public RestResult queryPageFZNCS(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageFZNCS(params);
        long count = tragetMapper._queryCountFzncs(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPageDZZBXX(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageDZZBXX(params);
        long count = tragetMapper._queryCountDZZBXX(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPagePZFZMX(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPagePZFZMX(params);
        long count = tragetMapper._queryCountPzfzmx(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPageJZPZ(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageJZPZ(params);
        long count = tragetMapper._queryCountJZPZ(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPageKjqjdy(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageKjqjdy(params);
        long count = tragetMapper._queryCountKjqjdy(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPageKMNCS(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageKMNCS(params);
        long count = tragetMapper._queryCountKMNCS(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPageKMYE(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageKMYE(params);
        long count = tragetMapper._queryCountKMYE(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPageYSDW(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageYSDW(params);
        long count = tragetMapper._queryCountYsdw(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPageFzlx(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageFzlx(params);
        long count = tragetMapper._queryCountFzlx(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPageFzxx(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageFzxx(params);
        long count = tragetMapper._queryCountFzxx(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPageKJKM(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageKJKM(params);
        long count = tragetMapper._queryCountKJKM(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult queryPageFZYE(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageFZYE(params);
        long count = tragetMapper._queryCountFZYE(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }
}
