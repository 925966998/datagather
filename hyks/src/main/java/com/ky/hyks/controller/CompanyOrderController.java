package com.ky.hyks.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.hyks.entity.CompanyEntity;
import com.ky.hyks.entity.CompanyOrderEntity;
import com.ky.hyks.entity.SysUserEntity;
import com.ky.hyks.logUtil.Log;
import com.ky.hyks.mapper.SysUserMapper;
import com.ky.hyks.mybatis.PagerResult;
import com.ky.hyks.mybatis.RestResult;
import com.ky.hyks.service.CompanyOrderService;
import com.ky.hyks.service.CompanyService;
import com.ky.hyks.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/ky-supplier/companyOrder")
public class CompanyOrderController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyOrderController.class);

    @Autowired
    CompanyOrderService companyOrderService;

    @Autowired
    SysUserMapper sysUserMapper;
    /**
     * 查询全部数据不分页
     */
    @RequestMapping(value = "queryByParams", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The CompanyOrderController queryByParams method params are {}", params);
        return companyOrderService.queryAll(params);
    }

    @RequestMapping(value = "queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonController queryByParams method params are {}", params);
        return companyOrderService.get(params);
    }
    /**
     * 新增OR更新数据
     */
    @Log(description = "角色管理新增，修改操作", module = "角色管理")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The CompanyOrderController saveOrUpdate method params are {}", body);
        CompanyOrderEntity companyOrderEntity = JSONObject.parseObject(body, CompanyOrderEntity.class);
        if (StringUtils.isNotEmpty(companyOrderEntity.getId())) {
            return companyOrderService.update(companyOrderEntity);
        } else {
            companyOrderEntity.setId(UUID.randomUUID().toString());
            return companyOrderService.add(companyOrderEntity);
        }
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The CompanyOrderController queryPage method params are {}", params);
        RestResult restResult = companyOrderService.queryPage(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }
    public JSONObject toJson(PagerResult data) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("total", data.getTotalItemsCount());
        jsonObj.put("rows", data.getItems());
        return jsonObj;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public CompanyOrderEntity select(String id) {
        logger.info("The CompanyOrderController queryPage method params are {}", id);
        RestResult restResult = companyOrderService._get(id);
        CompanyOrderEntity data = (CompanyOrderEntity) restResult.getData();
        return data;
    }

    /**
     * 删除多个
     */
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteMoney(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The CompanyOrderController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                companyOrderService._deleteForce(split[i]);
            }
        } else {
            companyOrderService._deleteForce(params.get("id").toString());
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }



}
