package com.ky.dbbak.controller;

import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.service.AreaService;
import com.ky.dbbak.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/ky-datagather/area")
public class AreaController {

    @Autowired
    AreaService areaService;

    @RequestMapping(value = "/queryProvinces", method = RequestMethod.GET)
    public Object queryProvinces(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        return areaService.queryProvinces(params);
    }

    @RequestMapping(value = "/queryCities", method = RequestMethod.GET)
    public Object queryProvinces(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        return areaService.queryCities(params);
    }

    @RequestMapping(value = "/queryAreas", method = RequestMethod.GET)
    public Object queryProvinces(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        return areaService.queryAreas(params);
    }
}
