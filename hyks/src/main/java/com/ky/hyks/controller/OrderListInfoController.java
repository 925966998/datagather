package com.ky.hyks.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ky.hyks.entity.CompanyOrderEntity;
import com.ky.hyks.entity.OrderInfoEntity;
import com.ky.hyks.entity.OrderListInfoEntity;
import com.ky.hyks.entity.SysUserEntity;
import com.ky.hyks.logUtil.Log;
import com.ky.hyks.mapper.SysUserMapper;
import com.ky.hyks.mybatis.PagerResult;
import com.ky.hyks.mybatis.RestResult;
import com.ky.hyks.service.CompanyOrderService;
import com.ky.hyks.service.OrderListInfoService;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/ky-supplier/orderListInfo")
public class OrderListInfoController {

    private static final Logger logger = LoggerFactory.getLogger(OrderListInfoController.class);

    @Autowired
    OrderListInfoService orderListInfoService;
    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 查询全部数据不分页
     */
    @RequestMapping(value = "queryByParams", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderListInfoController queryByParams method params are {}", params);
        return orderListInfoService.queryAll(params);
    }


    /**
     * 新增OR更新数据
     */
    @Log(description = "角色管理新增，修改操作", module = "角色管理")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The OrderListInfoController saveOrUpdate method params are {}", body);
        OrderListInfoEntity orderListInfoEntity = JSONObject.parseObject(body, OrderListInfoEntity.class);
        if (StringUtils.isNotEmpty(orderListInfoEntity.getId())) {
            return orderListInfoService.update(orderListInfoEntity);
        } else {
            orderListInfoEntity.setId(UUID.randomUUID().toString());
            return orderListInfoService.add(orderListInfoEntity);
        }
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderListInfoController queryPage method params are {}", params);
        RestResult restResult = orderListInfoService.queryPage(params);
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
    public OrderListInfoEntity select(String id) {
        logger.info("The OrderListInfoController queryPage method params are {}", id);
        RestResult restResult = orderListInfoService._get(id);
        OrderListInfoEntity data = (OrderListInfoEntity) restResult.getData();
        return data;
    }

    /**
     * 删除多个
     */
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteMoney(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderListInfoController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                orderListInfoService._deleteForce(split[i]);
            }
        } else {
            orderListInfoService._deleteForce(params.get("id").toString());
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }

    @Log(description = "成本管理新增/删除操作", module = "成本管理")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object save(String orderListInfoEntities, HttpServletRequest request) {
        logger.info("The OrderInfoController saveOrUpdate method params are {}", orderListInfoEntities);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        List<OrderListInfoEntity> orderListInfoEntityList = JSON.parseArray(orderListInfoEntities, OrderListInfoEntity.class);
        for (OrderListInfoEntity orderListInfoEntity : orderListInfoEntityList) {
            if (StringUtils.isNotEmpty(orderListInfoEntity.getId())) {
                orderListInfoService.update(orderListInfoEntity);
            } else {
                orderListInfoEntity.setId(UUID.randomUUID().toString());
                orderListInfoService.add(orderListInfoEntity);
            }
        }
        return new RestResult();
    }
}
