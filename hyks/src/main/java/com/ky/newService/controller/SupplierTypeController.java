package com.ky.newService.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.newService.entity.SupplierTypeEntity;
import com.ky.newService.mybatis.RestResult;
import com.ky.newService.service.SupplierTypeService;
import com.ky.newService.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @className: SupplierTypeController
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:20
 */
@RestController
@RequestMapping("/ky-supplier/supplierType")
public class SupplierTypeController {

    private static final Logger logger = LoggerFactory.getLogger(SupplierTypeController.class);
    @Autowired
    SupplierTypeService supplierTypeService;
    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierTypeController queryByParams method params are {}", params);
        return supplierTypeService.queryAll(params);
    }
    /**
     * 根据Id查询数据
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierTypeController queryByParams method params are {}", params);
        return supplierTypeService.get(params);
    }
    /**
     * 物理删除
     */
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierTypeController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        System.out.println(id);
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                supplierTypeService._deleteForce(split[i]);
            }
        } else {
            supplierTypeService._deleteForce(params.get("id").toString());
        }
        return new RestResult();
    }
    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierTypeController queryPage method params are {}", params);
        return supplierTypeService.queryPage(params);
    }
    /**
     * 新增OR更新数据
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The SupplierTypeController saveOrUpdate method params are {}", body);
        SupplierTypeEntity supplierTypeEntity = JSONObject.parseObject(body, SupplierTypeEntity.class);
        if (supplierTypeEntity.getId() != null && supplierTypeEntity.getId().length() > 0) {
            return supplierTypeService.update(supplierTypeEntity);
        } else {
            supplierTypeEntity.setId(UUID.randomUUID().toString());
            return supplierTypeService.add( supplierTypeEntity);
        }
    }

}
