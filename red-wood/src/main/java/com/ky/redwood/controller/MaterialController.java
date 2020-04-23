package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.MaterialEntity;
import com.ky.redwood.entity.SysUserEntity;
import com.ky.redwood.excle.ExcelHead;
import com.ky.redwood.excle.ExcelUtils;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.mapper.MaterialMapper;
import com.ky.redwood.mybatis.RestResult;
import com.ky.redwood.service.MaterialService;
import com.ky.redwood.utils.HttpUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ky-redwood/material")
public class MaterialController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MaterialService materialService;

    @Autowired
    MaterialMapper materialMapper;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialController queryByParams method params are {}", params);
        return materialService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialController queryById method params are {}", params);
        return materialService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "用户管理新增,修改操作", module = "物料管理")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = "application/json")
    public Object saveOrUpdate(@RequestBody String body,HttpServletRequest request) {
        logger.info("The MaterialController saveOrUpdate method params are {}", body);
        MaterialEntity materialEntity = JSONObject.parseObject(body, MaterialEntity.class);
        if (StringUtils.isNotEmpty(materialEntity.getId())) {
            materialEntity.setUpdateTime(new Date());
            return materialService.update(materialEntity);
        } else {
            materialEntity.setId(UUID.randomUUID().toString());
            // 获取当前登录用户
            SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
            materialEntity.setUserId(user.getId());
            return materialService.add(materialEntity);
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
        logger.info("The MaterialController delete method params is {}", params);
        return materialService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "用户管理物理删除操作", module = "物料管理")
    @RequestMapping(value = "/deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                materialService._deleteForce(split[i]);
            }
        } else {
            materialService._deleteForce(params.get("id").toString());
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
        logger.info("The MaterialController queryPage method params are {}", params);
        return materialService.queryPage(dealTimeFormat(params));
    }

    private Map dealTimeFormat(Map params) {
        if (StringUtils.isNotEmpty(MapUtils.getString(params, "startTime"))) {
            params.put("startTime", params.get("startTime") + " 00:00:00");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(params, "endTime"))) {
            params.put("endTime", params.get("endTime") + " 23:59:59");
        }
        return params;
    }

}
