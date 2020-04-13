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
@RequestMapping("/ky-redwood/materialOut")
public class MaterialOutController {

    private static final Logger logger = LoggerFactory.getLogger(MaterialOutController.class);

    @Autowired
    MaterialOutService materialOutService;

    @Autowired
    MaterialService materialService;

    @Autowired
    ProcessParentService processParentService;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialOutController queryByParams method params are {}", params);
        return materialOutService.queryAll(params);
    }

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
    @Log(description = "用户管理新增,修改操作", module = "物料管理")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = "application/json")
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The MaterialOutController saveOrUpdate method params are {}", body);
        MaterialOutEntity materialOutEntity = JSONObject.parseObject(body, MaterialOutEntity.class);
        if (StringUtils.isNotEmpty(materialOutEntity.getId())) {
            return materialOutService.update(materialOutEntity);
        } else {
            materialOutEntity.setId(UUID.randomUUID().toString());
            return materialOutService.add(materialOutEntity);
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
        logger.info("The MaterialOutController delete method params is {}", params);
        return materialOutService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "用户管理物理删除操作", module = "物料管理")
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


    @Log(description = "用户管理新增,修改操作", module = "物料管理")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public Object save(@RequestBody String body) {
        logger.info("The MaterialOutController saveOrUpdate method params are {}", body);
        MaterialOutEntity materialOutEntity = JSONObject.parseObject(body, MaterialOutEntity.class);
        String materialId = materialOutEntity.getMaterialName();
        System.out.println(materialId);
        int amount = materialOutEntity.getAmount();
        String processid = materialOutEntity.getProcessName();
        List<ProcessParentEntity> processParentEntities = processParentService.nameById(processid);
        List<MaterialEntity> materialEntities = materialService.countById(materialId);
        if (materialEntities.size()>0 && materialEntities!=null) {
            if (materialEntities.get(0).getAmount() > amount) {
                int newamount = materialEntities.get(0).getAmount() - amount;
                materialEntities.get(0).setAmount(newamount);
                materialService.update(materialEntities.get(0));
                materialOutEntity.setId(UUID.randomUUID().toString());
                materialOutEntity.setMaterialId(materialEntities.get(0).getId());
                materialOutEntity.setMaterialName(materialEntities.get(0).getMaterialName());
                materialOutEntity.setProcessParentId(processParentEntities.get(0).getId());
                materialOutEntity.setProcessName(processParentEntities.get(0).getProcessName());
                return materialOutService.add(materialOutEntity);
            } else {
                return false;
            }
        }else {
            return false;
        }
    }
}
