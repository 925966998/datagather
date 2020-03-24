package com.ky.dbbak.service;

import com.ky.dbbak.mybatis.PagerResult;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.targetmapper.TragetMapper;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TargetService
 * @Description: TODO
 * @Author czw
 * @Date 2020/3/24
 **/
@Service
public class TargetService {
    @Autowired
    TragetMapper tragetMapper;


    public RestResult queryPageFZNCS(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageFZNCS(params);
        long count = tragetMapper._queryCountFzncs(params);
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
