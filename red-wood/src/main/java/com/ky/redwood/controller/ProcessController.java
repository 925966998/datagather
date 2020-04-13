package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.*;
import com.ky.redwood.entity.ProcessEntity;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.mapper.ProcessFlowMapper;
import com.ky.redwood.mybatis.RestResult;
import com.ky.redwood.service.MaterialOutService;
import com.ky.redwood.service.ProcessService;
import com.ky.redwood.utils.HttpUtils;
import com.sun.javafx.collections.MappingChange;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ky-redwood/process")
public class ProcessController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    ProcessService processService;
    @Autowired
    ProcessFlowMapper processFlowMapper;

    @Autowired
    MaterialOutService materialOutService;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessController queryByParams method params are {}", params);
        return processService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProcessController queryById method params are {}", params);
        return processService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "用户管理新增,修改操作", module = "物料管理")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = "application/json")
    public Object saveOrUpdate(@RequestBody String body,HttpServletRequest request) {
        logger.info("The ProcessController saveOrUpdate method params are {}", body);
        ProcessEntity processEntity = JSONObject.parseObject(body, ProcessEntity.class);
        if (StringUtils.isNotEmpty(processEntity.getId())) {
            return processService.update(processEntity);
        } else {
            processEntity.setId(UUID.randomUUID().toString());
            // 获取当前登录用户
            SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
            processEntity.setUserId(user.getId());
            processEntity.setEndTime(new Date());
            processEntity.getAmount();
            int materialOutAmount = materialOutService.getByProcessId(processEntity.getProcessParentId());
            if (materialOutAmount < processEntity.getAmount()){
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数量不足");
            }
            return processService.add(processEntity);
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
        return processService.delete(params.get("id").toString());
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
                processService._deleteForce(split[i]);
            }
        } else {
            processService._deleteForce(params.get("id").toString());
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
        return processService.queryPage(params);
    }

    /**
     * 继续加工
     */
    @Log(description = "继续加工", module = "材料加工管理")
    @RequestMapping(value = "/doSubmitAudit", method = RequestMethod.POST, consumes = "application/json")
    public Object doSubmitAudit(@RequestBody String body,HttpServletRequest request) {
        logger.info("The ProcessController saveOrUpdate method params are {}", body);
        ProcessEntity processEntity = JSONObject.parseObject(body, ProcessEntity.class);
        if (StringUtils.isNotEmpty(processEntity.getId())) {
            return processService.update(processEntity);
        } else {
            processEntity.setId(UUID.randomUUID().toString());
            // 获取当前登录用户
            SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
            processEntity.setUserId(user.getId());
            processEntity.setEndTime(new Date());
            return processService.add(processEntity);
        }
    }
}
