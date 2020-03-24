package com.ky.dbbak.service;

import com.ky.dbbak.entity.AreaEntity;
import com.ky.dbbak.mapper.AreaMapper;
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

    public RestResult queryPageYSDW(Map params) {
        params.put("currentPage", MapUtils.getLongValue(params, "page"));
        params.put("pageSize", MapUtils.getLongValue(params, "rows"));
        List<Map<String, Object>> list = tragetMapper.queryPageYSDW(params);
        long count = tragetMapper._queryCountYsdw(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }
}
