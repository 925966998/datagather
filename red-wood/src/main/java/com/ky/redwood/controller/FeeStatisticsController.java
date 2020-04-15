package com.ky.redwood.controller;

import com.ky.redwood.service.FeeStatisticsService;
import com.ky.redwood.utils.HttpUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/queryFeeStatistics", method = RequestMethod.GET)
    public Object queryFeeStatistics(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The FeeStatisticsController queryFeeStatistics method params are {}", params);
        return feeStatisticsService.queryFeeStatistics(dealTimeFormat(params));
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
