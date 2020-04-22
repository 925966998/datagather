package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.MaterialEntity;
import com.ky.redwood.entity.MaterialOutEntity;
import com.ky.redwood.entity.ProcessParentEntity;
import com.ky.redwood.entity.SysUserEntity;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.mybatis.RestResult;
import com.ky.redwood.service.MaterialOutService;
import com.ky.redwood.service.MaterialService;
import com.ky.redwood.service.ProcessParentService;
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
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ky-redwood/consumable")
public class ConsumableController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MaterialOutService materialOutService;

    @Autowired
    MaterialService materialService;



    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialOutController queryById method params are {}", params);
        return materialOutService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "消耗品出库新增,修改操作", module = "消耗品出库")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = "application/json")
    public Object saveOrUpdate(@RequestBody String body, HttpServletRequest request) {
        logger.info("The MaterialOutController saveOrUpdate method params are {}", body);
        MaterialOutEntity materialOutEntity = JSONObject.parseObject(body, MaterialOutEntity.class);
        if (StringUtils.isNotEmpty(materialOutEntity.getId())) {
            materialOutEntity.setUpdateTime(new Date());
            return materialOutService.update(materialOutEntity);
        } else {
            MaterialEntity materialEntity = materialService.get(materialOutEntity.getMaterialName());
            int amount = materialOutEntity.getAmount();
            if (materialEntity.getAmount() < amount) {
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数量不足");
            }
            materialEntity.setAmount(materialEntity.getAmount() - amount);
            materialService.update(materialEntity);
            materialOutEntity.setId(UUID.randomUUID().toString());
            materialOutEntity.setMaterialId(materialEntity.getId());
            materialOutEntity.setMaterialName(materialEntity.getMaterialName());
            SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
            materialOutEntity.setUserId(user.getId());
            materialOutEntity.setConsumablesIs(materialEntity.getConsumablesIs());
            return materialOutService.add(materialOutEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "消耗品出库管理逻辑删除操作", module = "消耗品出库")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialOutController delete method params is {}", params);
        return materialOutService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "消耗品出库管理物理删除操作", module = "消耗品出库")
    @RequestMapping(value = "/deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialOutController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                materialOutService._deleteForce(split[i]);
            }
        } else {
            materialOutService._deleteForce(params.get("id").toString());
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
        return materialOutService.queryPage(params);
    }

    /*
     *查询材料库中的消耗品
     */

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByConsumable", method = RequestMethod.GET)
    public Object queryByConsumable(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialOutController queryByConsumable method params are {}", params);
        return materialService.queryConsumable(params);
    }
}
