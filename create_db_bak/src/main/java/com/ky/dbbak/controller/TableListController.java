package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.dbbak.mybatis.PagerResult;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.service.TargetService;
import com.ky.dbbak.targetmapper.TragetMapper;
import com.ky.dbbak.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TableListController
 * @Description: TODO
 * @Author czw
 * @Date 2020/3/23
 **/
@RestController
@RequestMapping("/ky-datagather/tableList/")
public class TableListController {
    @Autowired
    TragetMapper tragetMapper;
    @Autowired
    TargetService targetService;

    @RequestMapping(value = "queryDescription", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> _queryDescription(String tableName) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("tableName", tableName);
        List<Map<String, Object>> maps = tragetMapper._queryDescription(pageData);
        return maps;
    }

    @RequestMapping(value = "queryPageFZNCS", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPageFZNCS(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPageFZNCS(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    @RequestMapping(value = "queryPageYSDW", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPageYSDW(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPageYSDW(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    @RequestMapping(value = "queryPagePZFZMX", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPagePZFZMX(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPagePZFZMX(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    public JSONObject toJson(PagerResult data) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("total", data.getTotalItemsCount());
        jsonObj.put("rows", data.getItems());
        return jsonObj;
    }
}
