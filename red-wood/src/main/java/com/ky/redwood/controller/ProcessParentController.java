package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.MaterialEntity;
import com.ky.redwood.entity.MaterialOutEntity;
import com.ky.redwood.entity.ProcessParentEntity;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.mybatis.RestResult;
import com.ky.redwood.service.MaterialOutService;
import com.ky.redwood.service.MaterialService;
import com.ky.redwood.service.ProcessParentService;
import com.ky.redwood.utils.HttpUtils;
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
@RequestMapping("/ky-redwood/ProcessParent")
public class ProcessParentController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProcessParentService processParentService;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessParentController queryByParams method params are {}", params);
        return processParentService.queryAll(params);
    }

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByMaterial", method = RequestMethod.GET)
    public Object queryByMaterial(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessParentController queryByParams method params are {}", params);
        return processParentService.queryOrder(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessParentController queryById method params are {}", params);
        return processParentService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "流程管理新增,修改操作", module = "流程管理")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = "application/json")
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The ProcessParentController saveOrUpdate method params are {}", body);
        ProcessParentEntity processParentEntity = JSONObject.parseObject(body, ProcessParentEntity.class);
        if (StringUtils.isNotEmpty(processParentEntity.getId())) {
            return processParentService.update(processParentEntity);
        } else {
            processParentEntity.setId(UUID.randomUUID().toString());
            return processParentService.add(processParentEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "流程管理逻辑删除操作", module = "流程管理")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessParentController delete method params is {}", params);
        return processParentService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "流程管理物理删除操作", module = "流程管理")
    @RequestMapping(value = "/deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessParentController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                processParentService._deleteForce(split[i]);
            }
        } else {
            processParentService._deleteForce(params.get("id").toString());
        }
        return new RestResult();
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        params.put("currentPage", params.get("page"));
        params.put("pageSize", params.get("rows"));
        logger.info("The MaterialOutController queryPage method params are {}", params);
        return processParentService.queryPage(params);
    }

}
