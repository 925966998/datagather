package com.ky.centerservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.centerservice.entity.PersonDetailEntity;
import com.ky.centerservice.logUtil.Log;
import com.ky.centerservice.mapper.PersonDetailMapper;
import com.ky.centerservice.mybatis.PagerResult;
import com.ky.centerservice.mybatis.RestResult;
import com.ky.centerservice.service.PersonDetailService;
import com.ky.centerservice.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * @className: PersonDetailController
 * @description: TODO
 * @author: Lcj
 * @date: 2021-01-22 09:56
 */

@RestController
@RequestMapping("/ky-ykt/personDetail")
public class PersonDetailController {

    private static final Logger logger = LoggerFactory.getLogger(PersonDetailController.class);

    @Autowired
    PersonDetailService personDetailService;
    @Autowired
    PersonDetailMapper personDetailMapper;
    /**
     * 查询全部数据不分页
     */
    @RequestMapping(value = "queryByParams", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonDetailController queryByParams method params are {}", params);
        return personDetailService.queryAll(params);
    }

    @Log(description = "成本管理回显", module = "成本管理")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object queryById(String id) {
        logger.info("The PersonDetailController queryById method params are {}", id);
        PersonDetailEntity personDetailEntity = personDetailMapper._get(id);
        return personDetailEntity;
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "角色管理新增，修改操作", module = "角色管理")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object saveOrUpdate(PersonDetailEntity personDetailEntity) {
        logger.info("The PersonDetailController saveOrUpdate method params are {}", personDetailEntity);
        if (StringUtils.isNotEmpty(personDetailEntity.getId())) {
            return personDetailService.update(personDetailEntity);
        } else {
            personDetailEntity.setId(UUID.randomUUID().toString());
            return personDetailService.add(personDetailEntity);
        }
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonDetailController queryPage method params are {}", params);
        RestResult restResult = personDetailService.queryPage(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }
    public JSONObject toJson(PagerResult data) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("total", data.getTotalItemsCount());
        jsonObj.put("rows", data.getItems());
        return jsonObj;
    }

    /**
     * 删除多个
     */
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteMoney(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonDetailController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                personDetailService._deleteForce(split[i]);
            }
        } else {
            personDetailService._deleteForce(params.get("id").toString());
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }

}
