package com.ky.hyks.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ky.hyks.entity.CompanyOrderEntity;
import com.ky.hyks.entity.OrderListEntity;
import com.ky.hyks.entity.SysUserEntity;
import com.ky.hyks.logUtil.Log;
import com.ky.hyks.mapper.CompanyOrderMapper;
import com.ky.hyks.mybatis.PagerResult;
import com.ky.hyks.mybatis.RestResult;
import com.ky.hyks.service.OrderListService;
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
@RequestMapping("/ky-supplier/orderList")
public class OrderListController {

    private static final Logger logger = LoggerFactory.getLogger(OrderListController.class);

    @Autowired
    OrderListService orderListService;
    @Autowired
    CompanyOrderMapper companyOrderMapper;

    /**
     * 查询全部数据不分页
     */
    @RequestMapping(value = "queryByParams", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderListController queryByParams method params are {}", params);
        return orderListService.queryAll(params);
    }

    @RequestMapping(value = "queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonController queryByParams method params are {}", params);
        return orderListService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "角色管理新增，修改操作", module = "角色管理")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The OrderListController saveOrUpdate method params are {}", body);
        OrderListEntity orderListEntity = JSONObject.parseObject(body, OrderListEntity.class);
        if (StringUtils.isNotEmpty(orderListEntity.getId())) {
            return orderListService.update(orderListEntity);
        } else {
            orderListEntity.setId(UUID.randomUUID().toString());
            return orderListService.add(orderListEntity);
        }
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderListController queryPage method params are {}", params);
        RestResult restResult = orderListService.queryPage(params);
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
    public OrderListEntity select(String id) {
        logger.info("The OrderListController queryPage method params are {}", id);
        RestResult restResult = orderListService._get(id);
        OrderListEntity data = (OrderListEntity) restResult.getData();
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
        logger.info("The OrderListController delete method params is {}", params);
        return orderListService.delete(params.get("id").toString());
    }

    /**
     * 删除多个
     */
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteMoney(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderListController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                orderListService._deleteForce(split[i]);
            }
        } else {
            orderListService._deleteForce(params.get("id").toString());
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }


    @Log(description = "成本管理新增/删除操作", module = "成本管理")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object save(String orderListEntities, HttpServletRequest request) {
        logger.info("The OrderListController saveOrUpdate method params are {}", orderListEntities);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        List<OrderListEntity> orderListEntityList = JSON.parseArray(orderListEntities, OrderListEntity.class);
        for (OrderListEntity orderListEntity : orderListEntityList) {
            if (StringUtils.isNotEmpty(orderListEntity.getId())) {
                return orderListService.update(orderListEntity);
            } else {
                orderListEntity.setId(UUID.randomUUID().toString());
                return orderListService.add(orderListEntity);
            }
        }
        return new RestResult();
    }


    @Log(description = "成本管理新增/删除操作", module = "成本管理")
    @RequestMapping(value = "/saveSupplier", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object saveSupplier(@RequestBody String body, HttpServletRequest request) {
        logger.info("The OrderListController saveOrUpdate method params are {}", body);
        OrderListEntity orderListEntity = JSONObject.parseObject(body, OrderListEntity.class);
        if (orderListEntity.getSupplierId().contains(",")) {
            String b = orderListEntity.getSupplierId().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
            String[] split = b.split(",");
            for (int i = 0; i < split.length; i++) {
                Map map = new HashMap();
                map.put("companyId", split[i]);
                map.put("orderId", orderListEntity.getId());
                List<CompanyOrderEntity> companyOrderEntities = companyOrderMapper._queryRelation(map);
                if (companyOrderEntities.size() < 1) {
                    CompanyOrderEntity companyOrderEntity = new CompanyOrderEntity();
                    companyOrderEntity.setId(UUID.randomUUID().toString());
                    companyOrderEntity.setCompanyId(split[i]);
                    companyOrderEntity.setOrderId(orderListEntity.getId());
                    companyOrderEntity.setAmount(orderListEntity.getTotalAmount());
                    companyOrderMapper._addEntity(companyOrderEntity);
                } else {
                    return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "第" + i + "个客商已询价");
                }
            }
        } else {
            Map map = new HashMap();
            map.put("companyId", orderListEntity.getSupplierId());
            map.put("orderId", orderListEntity.getId());
            List<CompanyOrderEntity> companyOrderEntities = companyOrderMapper._queryRelation(map);
            if (companyOrderEntities.size() < 1) {
                CompanyOrderEntity companyOrderEntity = new CompanyOrderEntity();
                companyOrderEntity.setId(UUID.randomUUID().toString());
                companyOrderEntity.setCompanyId(orderListEntity.getSupplierId());
                companyOrderEntity.setOrderId(orderListEntity.getId());
                companyOrderEntity.setAmount(orderListEntity.getTotalAmount());
                companyOrderMapper._addEntity(companyOrderEntity);
            } else {
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "该客商已询价");
            }
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, "指派成功");
    }
}
