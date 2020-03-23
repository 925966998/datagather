package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.dbbak.entity.DzzbxxEntity;
import com.ky.dbbak.entity.SysUserEntity;
import com.ky.dbbak.mapper.SysUserMapper;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.service.DzzbxxService;
import com.ky.dbbak.service.SysUserService;
import com.ky.dbbak.utils.HttpUtils;
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
@RequestMapping("/ky-datagather/dzzbxx")
public class DzzbxxController {

    private static final Logger logger = LoggerFactory.getLogger(DzzbxxController.class);

    @Autowired
    DzzbxxService dzzbxxService;


    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SysUserController queryByParams method params are {}", params);
        return dzzbxxService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SysUserController queryByParams method params are {}", params);
        return dzzbxxService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The SysUserController saveOrUpdate method params are {}", body);
        DzzbxxEntity dzzbxxEntity = JSONObject.parseObject(body, DzzbxxEntity.class);
        if (StringUtils.isNotEmpty(dzzbxxEntity.getId())) {
            return dzzbxxService.update(dzzbxxEntity);
        } else {
            return dzzbxxService.add(dzzbxxEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SysUserController delete method params is {}", params);
        return dzzbxxService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @RequestMapping(value = "/deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SysUserController deleteForce method params is {}", params);
        return dzzbxxService._deleteForce(params.get("id").toString());
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SysUserController queryPage method params are {}", params);
        return dzzbxxService.queryPage(params);
    }



    @RequestMapping(value = "/pullDzzbxxData", method = RequestMethod.GET)
    public Object pullDzzbxxData() {

        return dzzbxxService.pullDzzbxxData();
    }
}
