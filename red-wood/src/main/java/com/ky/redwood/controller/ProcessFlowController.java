package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.ProcessEntity;
import com.ky.redwood.entity.ProcessFlowEntity;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.mybatis.RestResult;
import com.ky.redwood.service.ProcessFlowService;
import com.ky.redwood.service.ProcessService;
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

@RestController
@RequestMapping("/ky-redwood/processFlow")
public class ProcessFlowController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    ProcessFlowService processFlowService;
    @Autowired
    ProcessService processService;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessController queryByParams method params are {}", params);
        return processFlowService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessController queryById method params are {}", params);
        return processFlowService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "用户管理新增,修改操作", module = "物料管理")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = "application/json")
    public Object saveOrUpdate(@RequestBody String body,HttpServletRequest request) {
        logger.info("The ProcessController saveOrUpdate method params are {}", body);
        ProcessFlowEntity processFlowEntity = JSONObject.parseObject(body, ProcessFlowEntity.class);
        if (StringUtils.isNotEmpty(processFlowEntity.getId())) {
            return processFlowService.update(processFlowEntity);
        } else {
            return processFlowService.add(processFlowEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "用户管理逻辑删除操作", module = "物料管理")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessController delete method params is {}", params);
        return processFlowService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "用户管理物理删除操作", module = "物料管理")
    @RequestMapping(value = "/deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                processFlowService._deleteForce(split[i]);
            }
        } else {
            processFlowService._deleteForce(params.get("id").toString());
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
        logger.info("The ProcessController queryPage method params are {}", params);
        return processFlowService.queryPage(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/querySmallId", method = RequestMethod.GET)
    public Object querySmallId(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessController queryById method params are {}", params);
        ProcessEntity processEntity =  processService.queryProcess(params);
        params.put("flowStatus",processEntity.getFlowStatus());
        List<ProcessFlowEntity> processFlowEntityList =  processFlowService.querySmallId(params);
        return processFlowEntityList;
    }
}
