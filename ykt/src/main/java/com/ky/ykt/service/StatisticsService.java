package com.ky.ykt.service;

import com.ky.ykt.entity.PersonUploadEntity;
import com.ky.ykt.entity.StatisticEntity;
import com.ky.ykt.mapper.PersonMapper;
import com.ky.ykt.mapper.PersonUploadMapper;
import com.ky.ykt.mapper.ProjectDetailMapper;
import com.ky.ykt.mybatis.PagerResult;
import com.ky.ykt.mybatis.RestResult;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    PersonMapper personMapper;
    @Autowired
    PersonUploadMapper personUploadMapper;
    @Autowired
    ProjectDetailMapper projectDetailMapper;

    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public RestResult queryPage(Map params) {
        List<StatisticEntity> personUploadEntities = new ArrayList<>();
        personUploadEntities = personMapper.statistics(params);
        long count = personMapper.statisticsCount(params);
        PagerResult pagerResult = new PagerResult(personUploadEntities, count, MapUtils.getLongValue(params, "page"),
                MapUtils.getLongValue(params, "rows"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);

    }

    public BigDecimal statisticCount(Map params) {
        BigDecimal bigDecimal = projectDetailMapper.statisticCount(params);
        return bigDecimal;

    }

    public RestResult statisticPage(Map params) {
        List<StatisticEntity> personUploadEntities = new ArrayList<>();
        personUploadEntities = projectDetailMapper.statisticPage(params);
        long count = projectDetailMapper.statisticPageCount(params);
        PagerResult pagerResult = new PagerResult(personUploadEntities, count, MapUtils.getLongValue(params, "page"),
                MapUtils.getLongValue(params, "rows"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);

    }

}