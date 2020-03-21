package com.ky.ykt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ky.ykt.entity.*;
import com.ky.ykt.logUtil.Log;
import com.ky.ykt.mapper.PersonUploadMapper;
import com.ky.ykt.mapper.ProjectDetailMapper;
import com.ky.ykt.mybatis.RestResult;
import com.ky.ykt.service.PersonService;
import com.ky.ykt.service.PersonUploadService;
import com.ky.ykt.utils.HttpUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @class: monitor
 * @classDesc: 功能描述（）
 * @author: yaoWieJie
 * @createTime: 2020-02-29 11:34
 * @version: v1.0
 */
@RestController
@RequestMapping("/ky-ykt/personUpload")
public class PersonUploadController {
    private static final Logger logger = LoggerFactory.getLogger(PersonUploadController.class);

    @Autowired
    PersonUploadMapper personUploadMapper;
    @Autowired
    PersonUploadService personUploadService;
    @Autowired
    ProjectDetailMapper projectDetailMapper;
    @Autowired
    PersonService personService;
    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "queryParams", method = RequestMethod.GET)
    public Object queryParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        params = setDepartmentIdForMap(request,params);
        logger.info("The PersonUploadController queryByParams method params are {}", params);
        return personUploadService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonUploadController queryByParams method params are {}", params);
        return personUploadService.queryByAll(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "人员档案新增，修改操作", module = "人员档案")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object saveOrUpdate(@RequestBody String body, HttpServletRequest request) {
        logger.info("The PersonUploadController saveOrUpdate method params are {}", body);
        PersonUploadEntity personUploadEntity = JSONObject.parseObject(body, PersonUploadEntity.class);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        if (personUploadEntity.getId() != null && personUploadEntity.getId().length() > 0) {
            personUploadEntity.setDepartmentId(user.getDepartmentId());
            return personUploadService.update(personUploadEntity);
        } else {
            personUploadEntity.setDepartmentId(user.getDepartmentId());
            return personUploadService.add(personUploadEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "人员管理逻辑删除操作", module = "人员管理")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonUploadController delete method params is {}", params);
        return personUploadService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "人员管理物理删除操作", module = "人员管理")
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonUploadController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                personUploadService._deleteForce(split[i]);
            }
        } else {
            personUploadService._deleteForce(params.get("id").toString());
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
        logger.info("The PersonUploadController queryPage method params are {}", params);
        //params = setDepartmentIdForMap(request,params);
        //List<ProjectDetailEntity> projectDetailEntities = projectDetailMapper._queryPage(params);
        List<ProjectDetailEntity> projectDetailEntities = projectDetailMapper._queryProjectId(params);
        if (projectDetailEntities.size()>0){
            params.put("projectId",projectDetailEntities.get(0).getId());
        }
        return personUploadService.queryPage(params);
        //return personService.queryPage(params);
    }

    @Log(description = "人员管理提交审核操作", module = "人员管理")
    @RequestMapping(value = "/doSubmitAudit", method = RequestMethod.POST)
    public Object doSubmitAudit(@RequestBody String id) {
        logger.info("The PersonUploadController doSubmitAudit method params are {}", id);
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                personUploadService.doSubmitAudit(split[i]);
            }
        } else {
            personUploadService.doSubmitAudit(id);
        }
        return new RestResult();
    }

    @Log(description = "人员管理审核操作", module = "人员管理")
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    public Object audit(@RequestBody String body) {
        logger.info("The PersonUploadController audit method params are {}", body);
        Map params = (Map) JSON.parse(body);
        return personUploadService.audit(params);
    }

    @Log(description = "人员管理推送银行进行发放资金操作", module = "人员管理")
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    public Object push(@RequestBody String id) {
        logger.info("The push push method params are {}", id);
        if (id.contains(",")) {
            if (id.contains(",")) {
                String[] split = id.split(",");
                for (int i = 0; i < split.length; i++) {
                    personUploadService.push(split[i]);
                }
            } else {
                personUploadService.push(id);
            }
        }
        return null;
    }

    public static Map setDepartmentIdForMap(HttpServletRequest request, Map params) {
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        params.put("departmentId", user.getDepartmentId());
        return params;

    }

}