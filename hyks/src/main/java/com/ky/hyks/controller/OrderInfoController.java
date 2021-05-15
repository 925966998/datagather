package com.ky.hyks.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ky.hyks.entity.CompanyEntity;
import com.ky.hyks.entity.OrderInfoEntity;
import com.ky.hyks.entity.SysUserEntity;
import com.ky.hyks.logUtil.Log;
import com.ky.hyks.mybatis.PagerResult;
import com.ky.hyks.mybatis.RestResult;
import com.ky.hyks.service.CompanyService;
import com.ky.hyks.service.OrderInfoService;
import com.ky.hyks.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/ky-supplier/orderInfo")
public class OrderInfoController {

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoController.class);

    @Autowired
    OrderInfoService orderInfoService;

    /**
     * 查询全部数据不分页
     */
    @RequestMapping(value = "queryByParams", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderInfoController queryByParams method params are {}", params);
        return orderInfoService.queryAll(params);
    }

    @RequestMapping(value = "queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonController queryByParams method params are {}", params);
        return orderInfoService.get(params);
    }
    /**
     * 新增OR更新数据
     */
    @Log(description = "角色管理新增，修改操作", module = "角色管理")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The OrderInfoController saveOrUpdate method params are {}", body);
        OrderInfoEntity orderInfoEntity = JSONObject.parseObject(body, OrderInfoEntity.class);
        if (StringUtils.isNotEmpty(orderInfoEntity.getId())) {
            return orderInfoService.update(orderInfoEntity);
        } else {
            orderInfoEntity.setId(UUID.randomUUID().toString());
            return orderInfoService.add(orderInfoEntity);
        }
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderInfoController queryPage method params are {}", params);
        RestResult restResult = orderInfoService.queryPage(params);
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
    public OrderInfoEntity select(String id) {
        logger.info("The OrderInfoController queryPage method params are {}", id);
        RestResult restResult = orderInfoService._get(id);
        OrderInfoEntity data = (OrderInfoEntity) restResult.getData();
        return data;
    }

    /**
     * 删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "/deleteOne", method = RequestMethod.GET)
    public Object deleteOne(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderInfoController delete method params is {}", params);
        return orderInfoService.delete(params.get("id").toString());
    }

    /**
     * 删除多个
     */
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteMoney(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderInfoController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                orderInfoService._deleteForce(split[i]);
            }
        } else {
            orderInfoService._deleteForce(params.get("id").toString());
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }


    @Log(description = "成本管理新增/删除操作", module = "成本管理")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object save( String orderInfoEntities, HttpServletRequest request) {
        logger.info("The OrderInfoController saveOrUpdate method params are {}", orderInfoEntities);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        List<OrderInfoEntity> orderInfoEntityList = JSON.parseArray(orderInfoEntities, OrderInfoEntity.class);
        for (OrderInfoEntity orderInfoEntity : orderInfoEntityList) {
            if (StringUtils.isNotEmpty(orderInfoEntity.getId())) {
                return orderInfoService.update(orderInfoEntity);
            } else {
                orderInfoEntity.setId(UUID.randomUUID().toString());
                return orderInfoService.add(orderInfoEntity);
            }
        }
        return new RestResult();
    }
}
