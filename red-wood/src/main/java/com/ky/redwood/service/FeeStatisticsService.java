package com.ky.redwood.service;

import com.ky.redwood.mapper.FeeStatisticsMapper;
import com.ky.redwood.mybatis.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeeStatisticsService {
    @Autowired
    FeeStatisticsMapper feeStatisticsMapper;

    public Object queryFeeStatistics(Map params) {
        List<Map> list = feeStatisticsMapper.queryFeeStatistics(params);
        long count = feeStatisticsMapper._queryCount(params);
      /*  PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));*/
        return new RestResult(count, list);
    }
}
