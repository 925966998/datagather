package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.utils.DoConnection;
import com.ky.dbbak.utils.HttpUtils;
import com.ky.dbbak.utils.PropertiesUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ky-datagather/properties")
public class TrendsProperties {

    private static final Logger logger = LoggerFactory.getLogger(TrendsProperties.class);

    @Value("${dbip}")
    private String dbip;
    @Value("${dbport}")
    private String dbport;
    @Value("${dbname}")
    private String dbname;
    @Value("${name}")
    private String name;
    @Value("${dbpass}")
    private String dbpass;

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Object update(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        if (StringUtils.isEmpty(MapUtils.getString(params, "dbip"))
                || StringUtils.isEmpty(MapUtils.getString(params, "dbport"))
                || StringUtils.isEmpty(MapUtils.getString(params, "dbname"))
                || StringUtils.isEmpty(MapUtils.getString(params, "name"))
                || StringUtils.isEmpty(MapUtils.getString(params, "dbpass"))) {
            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数据信息有误");
        }
     /*   Connection connection = DoConnection.connection("sqlserver", "单节点", null, MapUtils.getString(params, "dbip"), MapUtils.getString(params, "dbport"), MapUtils.getString(params, "dbname"), MapUtils.getString(params, "name"),
                MapUtils.getString(params, "dbpass"));
        if (connection == null) {
            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "连接失败");
        }*/
        Map<String, String> map = new HashMap<>();
        try {
            map.put("spring.source.datasource.jdbc-url", "jdbc:sqlserver://" + params.get("dbip") + ":" + params.get("dbport") + ";DatabaseName=" + params.get("dbname"));
            map.put("spring.source.datasource.username", params.get("name").toString());
            map.put("spring.source.datasource.password", params.get("dbpass").toString());

            map.put("dbip", params.get("dbip").toString());
            map.put("dbport", params.get("dbport").toString());
            map.put("dbname", params.get("dbname").toString());
            map.put("name", params.get("name").toString());
            map.put("dbpass", params.get("dbpass").toString());

            logger.info("The profileByClassLoader is {}", JSON.toJSONString(map));
            PropertiesUtils.updateProperties("application.properties", map);
        } catch (Exception e) {
            logger.error("properties update exception", e);
        }
        return new RestResult();
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public Object read() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dbip", dbip);
        jsonObject.put("dbport", dbport);
        jsonObject.put("dbname", dbname);
        jsonObject.put("name", name);
        jsonObject.put("dbpass", dbpass);
        return new RestResult(jsonObject);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object testConnection(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        if (StringUtils.isEmpty(MapUtils.getString(params, "dbip"))
                || StringUtils.isEmpty(MapUtils.getString(params, "dbport"))
                || StringUtils.isEmpty(MapUtils.getString(params, "dbname"))
                || StringUtils.isEmpty(MapUtils.getString(params, "name"))
                || StringUtils.isEmpty(MapUtils.getString(params, "dbpass"))) {
            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数据信息有误");
        }
        Connection connection = DoConnection.connection("sqlserver", "单节点", null, MapUtils.getString(params, "dbip"), MapUtils.getString(params, "dbport"), MapUtils.getString(params, "dbname"), MapUtils.getString(params, "name"),
                MapUtils.getString(params, "dbpass"));
        if (connection != null) {
            return new RestResult("连接成功");
        } else {
            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "连接失败");
        }

    }
}