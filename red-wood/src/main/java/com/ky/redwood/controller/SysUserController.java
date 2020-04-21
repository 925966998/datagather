package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.SysUserEntity;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.mapper.SysUserMapper;
import com.ky.redwood.mybatis.RestResult;
import com.ky.redwood.service.SysUserService;
import com.ky.redwood.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ky-redwood/sysUser")
public class SysUserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SysUserService sysUserService;


    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SysUserController queryByParams method params are {}", params);
        return sysUserService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SysUserController queryByParams method params are {}", params);
        return sysUserService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "用户管理新增,修改操作", module = "用户管理")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = "application/json")
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The SysUserController saveOrUpdate method params are {}", body);
        SysUserEntity sysUserEntity = JSONObject.parseObject(body, SysUserEntity.class);
        sysUserEntity.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        if (StringUtils.isNotEmpty(sysUserEntity.getId())) {
            return sysUserService.update(sysUserEntity);
        } else {
            sysUserEntity.setId(UUID.randomUUID().toString());
            sysUserEntity.setStatus(0);
            SysUserEntity sysUserEntity1 = sysUserMapper.queryByUserName(sysUserEntity.getUserName());
            if (sysUserEntity1 != null) {
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "用户名已存在");
            }
            return sysUserService.add(sysUserEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "用户管理逻辑删除操作", module = "用户管理")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SysUserController delete method params is {}", params);
        return sysUserService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "用户管理物理删除操作", module = "用户管理")
    @RequestMapping(value = "/deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SysUserController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                sysUserService._deleteForce(split[i]);
            }
        } else {
            sysUserService._deleteForce(params.get("id").toString());
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
        logger.info("The SysUserController queryPage method params are {}", params);
        return sysUserService.queryPage(params);
    }

    @RequestMapping(value = "/ggg", method = RequestMethod.GET)
    public Object ggg(HttpServletRequest request) {
        return sysUserMapper.ggg();
    }
}
