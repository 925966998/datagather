package com.ky.ykt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ky.ykt.entity.ProjectTypeEntity;
import com.ky.ykt.entity.StatisticEntity;
import com.ky.ykt.mybatis.PagerResult;
import com.ky.ykt.mybatis.RestResult;
import com.ky.ykt.service.ProjectService;
import com.ky.ykt.service.ProjectTypeService;
import com.ky.ykt.service.StatisticsService;
import com.ky.ykt.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Statisticscontroller
 * @Description: TODO
 * @Author czw
 * @Date 2020/3/4
 **/
@RestController
@RequestMapping("/ky-ykt/statistics")
public class StatisticsController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);
    @Autowired
    StatisticsService statisticsService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectTypeService projectTypeService;


    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The Statisticscontroller queryPage method params are {}", params);
        RestResult restResult = statisticsService.queryPage(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    @RequestMapping(value = "/statisticCount", method = RequestMethod.GET)
    public Object statisticCount(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The Statisticscontroller queryPage method params are {}", params);
        BigDecimal bigDecimal = statisticsService.statisticCount(params);
        return bigDecimal;
    }

    @RequestMapping(value = "/projectPage", method = RequestMethod.GET)
    public Object projectPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The Statisticscontroller queryPage method params are {}", params);
        RestResult restResult = statisticsService.statisticPage(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    public JSONObject toJson(PagerResult data) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("total", data.getTotalItemsCount());
        jsonObj.put("rows", data.getItems());
        return jsonObj;
    }

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public Map<String, Object> statistic(String id) {
        logger.info("The ProjectController select method params are {}", id);
        List<StatisticEntity> statisticEntities = projectService.queryType();
        Map<String, Object> mapResult = new HashMap<String, Object>();
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        for (StatisticEntity statisticEntity : statisticEntities
        ) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("value", statisticEntity.getNum());
            map.put("name", statisticEntity.getProjectType());
            datas.add(map);
        }
        mapResult.put("datas", statisticEntities);
        return mapResult;
    }

    @RequestMapping(value = "/dateStatistic", method = RequestMethod.GET)
    public List<StatisticEntity> dateStatistic() {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        List<StatisticEntity> statistic = projectService.statistic(mapResult);
        return statistic;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Map<String, Object> query() {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        List<StatisticEntity> statistic = projectService.statistic(mapResult);
        return mapResult;
    }
}
