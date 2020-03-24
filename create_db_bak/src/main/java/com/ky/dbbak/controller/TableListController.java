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

    @RequestMapping(value = "truncate", method = RequestMethod.GET)
    @ResponseBody
    public String truncate(String tableName, String XZQHDM) throws Exception {
        Map<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("tableName", tableName);
        pageData.put("XZQHDM", XZQHDM);
        int truncate = tragetMapper.truncate(pageData);
        return "success";
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

    @RequestMapping(value = "queryPageJZPZ", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPageJZPZ(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPageJZPZ(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    @RequestMapping(value = "queryPageKjqjdy", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPageKjqjdy(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPageKjqjdy(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    @RequestMapping(value = "queryPageKMNCS", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPageKMNCS(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPageKMNCS(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    @RequestMapping(value = "queryPageKMYE", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPageKMYE(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPageKMYE(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    @RequestMapping(value = "queryPageFzlx", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPageFzlx(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPageFzlx(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    @RequestMapping(value = "queryPageFzxx", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPageFzxx(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPageFzxx(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    @RequestMapping(value = "queryPageKJKM", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPageKJKM(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPageKJKM(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    @RequestMapping(value = "queryPageFZYE", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryPageFZYE(HttpServletRequest request) throws Exception {
        Map params = HttpUtils.getParams(request);
        RestResult restResult = targetService.queryPageFZYE(params);
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
