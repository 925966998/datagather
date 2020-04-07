package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSON;
import com.ky.dbbak.entity.AreaEntity;
import com.ky.dbbak.entity.ZtcsEntity;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.service.AreaService;
import com.ky.dbbak.service.DbyService;
import com.ky.dbbak.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/ky-datagather/area")
public class AreaController {
    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    AreaService areaService;

    @Autowired
    DbyService dbyService;

    @RequestMapping(value = "/queryByAreaLevel/{level}", method = RequestMethod.GET)
    public Object queryByAreaLevel(@PathVariable String level) {
        return areaService.queryByAreaLevel(level);
    }

    @RequestMapping(value = "/queryByPid/{pid}", method = RequestMethod.GET)
    public Object queryByPid(@PathVariable String pid) {
        return areaService.queryByPid(pid);
    }

    @RequestMapping(value = "/queryById/{id}", method = RequestMethod.GET)
    public Object queryById(@PathVariable String id) {
        AreaEntity areaEntity = areaService.queryById(id);
        //areaEntity.setAreaName(this.queryNameById(id));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, areaEntity);
    }

    public String queryNameById(String id) {
        StringBuilder name = new StringBuilder();
        areaService.queryNameById(id, name);
        logger.info(name.toString());
        String[] split = name.toString().split("/");
        List<String> strings = Arrays.asList(split);
        Collections.reverse(strings);
        return String.join("/", strings);
    }

    @RequestMapping(value = "/queryTree", method = RequestMethod.GET)
    public Object queryTree() {
        return areaService.queryTree();
    }

    @RequestMapping(value = "/queryZTH", method = RequestMethod.GET)
    public Object queryZTH(String hsdwdm) {
        List<ZtcsEntity> ztcsEntityList = dbyService.queryByZTH(hsdwdm);
        //return ztcsEntityList;
        return JSON.toJSONString(ztcsEntityList);
    }

    @RequestMapping(value = "/queryOrgname", method = RequestMethod.GET)
    public Object queryOrgname(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The AreasController queryByParams method params are {}", params);
        return areaService.queryOrgname(params);
    }

    @RequestMapping(value = "/queryOrgCode", method = RequestMethod.POST)
    public Object queryOrgCode(String dwmc) {
        Map params = new HashMap();
        params.put("dwmc",dwmc);
        logger.info("The AreasController queryByParams method params are {}", params);
        return areaService.queryOrgname(params);
    }
}
