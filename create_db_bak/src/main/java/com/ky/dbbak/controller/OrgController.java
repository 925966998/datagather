package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.dbbak.entity.OrgEntity;
import com.ky.dbbak.entity.SysUserEntity;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.service.AreaService;
import com.ky.dbbak.service.OrgService;
import com.ky.dbbak.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ky-datagather/org")
public class OrgController {
    private static final Logger logger = LoggerFactory.getLogger(OrgController.class);

    @Autowired
    OrgService orgService;

    @RequestMapping(value = "/queryTree", method = RequestMethod.GET)
    public Object queryTree() {
        return orgService.queryTree();
    }


    /**
     * 新增OR更新数据
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The OrgController saveOrUpdate method params are {}", body);
        OrgEntity orgEntity = JSONObject.parseObject(body, OrgEntity.class);
        if (StringUtils.isNotEmpty(orgEntity.getId())) {
            return orgService.update(orgEntity);
        } else {
            orgEntity.setId(UUID.randomUUID().toString());
            return orgService.add(orgEntity);
        }
    }

    @RequestMapping(value = "/queryById/{id}", method = RequestMethod.GET)
    public Object queryById(@PathVariable String id) {
        logger.info("The OrgController queryById method id are {}", id);
        return orgService.queryById(id);
    }

    /**
     * 物理删除
     */
    @RequestMapping(value = "/deleteForce/{id}", method = RequestMethod.GET)
    public Object deleteForce(@PathVariable String id) {
        logger.info("The OrgController deleteForce method id is {}", id);
        return orgService._deleteForce(id);
    }
}
