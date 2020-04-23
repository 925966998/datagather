package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.CostSharingEntity;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.service.FeeStatisticsService;
import com.ky.redwood.utils.HttpUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/ky-redwood/feeStatistics")
public class FeeStatisticsController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FeeStatisticsService feeStatisticsService;

    @RequestMapping(value = "/queryNoSharing", method = RequestMethod.GET)
    public Object queryNoSharing(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        params.put("currentPage", params.get("page"));
        params.put("pageSize", params.get("rows"));
        logger.info("The FeeStatisticsController queryNoSharing method params are {}", params);
        return feeStatisticsService.queryNoSharing(dealTimeFormat(params));
    }

    @RequestMapping(value = "/querySharing", method = RequestMethod.GET)
    public Object querySharing(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        params.put("currentPage", params.get("page"));
        params.put("pageSize", params.get("rows"));
        logger.info("The FeeStatisticsController querySharing method params are {}", params);
        return feeStatisticsService.querySharing(dealTimeFormat(params));
    }

    @RequestMapping(value = "/queryJgFeeCount", method = RequestMethod.GET)
    public Object queryJgFeeCount(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The FeeStatisticsController queryJgFeeCount method params are {}", params);
        return feeStatisticsService.queryJgFeeCount(dealTimeFormat(params));
    }

    @RequestMapping(value = "/queryAllFeeCount", method = RequestMethod.GET)
    public Object queryAllFeeCount(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The FeeStatisticsController queryAllFeeCount method params are {}", params);
        return feeStatisticsService.queryAllFeeCount(dealTimeFormat(params));
    }


    /**
     * 新增OR更新数据
     */
    @Log(description = "费用分摊操作", module = "费用单据")
    @RequestMapping(value = "/costSharing", method = RequestMethod.POST, consumes = "application/json")
    public Object costSharing(@RequestBody String body) {
        logger.info("The MaterialController costSharing method params are {}", body);
        CostSharingEntity costSharingEntity = JSONObject.parseObject(body, CostSharingEntity.class);
        return feeStatisticsService.costSharing(costSharingEntity);
    }

    private Map dealTimeFormat(Map params) {
        if (StringUtils.isNotEmpty(MapUtils.getString(params, "startTime"))) {
            params.put("startTime", params.get("startTime") + " 00:00:00");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(params, "endTime"))) {
            params.put("endTime", params.get("endTime") + " 23:59:59");
        }
        return params;
    }
}
