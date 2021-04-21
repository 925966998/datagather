package com.ky.newService.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.newService.entity.VisitEntity;
import com.ky.newService.mapper.SupplierUserMapper;
import com.ky.newService.mapper.VisitMapper;
import com.ky.newService.mybatis.RestResult;
import com.ky.newService.service.VisitService;
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
 * @className: VisitController
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:19
 */

@RestController
@RequestMapping("/ky-supplier/visit")
public class VisitController {
    private static final Logger logger = LoggerFactory.getLogger(VisitController.class);
    @Autowired
    VisitService visitService;
    @Autowired
    VisitMapper visitMapper;
    @Autowired
    SupplierUserMapper supplierUserMapper;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The VisitController queryByParams method params are {}", params);
        return visitService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The VisitController queryByParams method params are {}", params);
        return visitService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The VisitController saveOrUpdate method params are {}", body);
        VisitEntity visitEntity = JSONObject.parseObject(body, VisitEntity.class);
        if (visitEntity.getId() != null && visitEntity.getId().length() > 0) {
            return visitService.update(visitEntity);
        } else {
            visitEntity.setId(UUID.randomUUID().toString());
            return visitService.add(visitEntity);
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Object save(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        System.out.println(params);
        if (params.get("id").toString() == "" || params.get("id").toString() == null) {
            VisitEntity visitEntity = new VisitEntity();
            visitEntity.setId(UUID.randomUUID().toString());
            visitEntity.setName(params.get("name").toString());
            visitEntity.setVisitName(params.get("visitName").toString());
            visitEntity.setVisitDate(params.get("visitDate").toString());
            visitEntity.setSupplierManageId(params.get("supplierManageId").toString());
            visitEntity.setContent(params.get("content").toString());
            return visitService.add(visitEntity);
        } else {
            VisitEntity visitEntity = visitMapper._get(params.get("id").toString());
            visitEntity.setName(params.get("name").toString());
            visitEntity.setVisitName(params.get("visitName").toString());
            visitEntity.setVisitDate(params.get("visitDate").toString());
            visitEntity.setContent(params.get("content").toString());
            return visitService.update(visitEntity);
        }
    }


    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The VisitController delete method params is {}", params);
        return visitService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The VisitController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        System.out.println(id);
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                visitService._deleteForce(split[i]);
            }
        } else {
            visitService._deleteForce(params.get("id").toString());
        }
        return new RestResult();
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The VisitController queryPage method params are {}", params);
        return visitService.queryPage(params);
    }


}