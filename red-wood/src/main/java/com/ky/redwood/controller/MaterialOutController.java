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
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ky-redwood/materialOut")
public class MaterialOutController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MaterialOutService materialOutService;

    @Autowired
    MaterialService materialService;

    @Autowired
    ProcessParentService processParentService;

    @Autowired
    ProcessService processService;

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
    @Log(description = "材料出库新增,修改操作", module = "材料出库")
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
            materialOutEntity.setProcessStatus(0);
            SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
            materialOutEntity.setUserId(user.getId());
            String ProcessParentId = UUID.randomUUID().toString();
            materialOutEntity.setProcessParentId(ProcessParentId);
            materialOutEntity.setStatus(0);
            ProcessParentEntity processParentEntity = new ProcessParentEntity();
            processParentEntity.setProcessName(materialOutEntity.getProcessName());
            processParentEntity.setId(ProcessParentId);
            processParentEntity.setType(1);
            processParentService.add(processParentEntity);
            return materialOutService.add(materialOutEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "材料出库管理逻辑删除操作", module = "材料出库")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialOutController delete method params is {}", params);
        return materialOutService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "材料出库管理物理删除操作", module = "材料出库")
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


    @Log(description = "材料出库补料操作", module = "材料出库")
    @RequestMapping(value = "/subMaterial", method = RequestMethod.POST, consumes = "application/json")
    public Object subMaterial(@RequestBody String body, HttpServletRequest request) {
        logger.info("The MaterialOutController saveOrUpdate method params are {}", body);
        MaterialOutEntity materialOutEntity = JSONObject.parseObject(body, MaterialOutEntity.class);
        MaterialOutEntity materialOutEntity1 = materialOutService.get(materialOutEntity.getId());
        materialOutEntity1.setAmount(materialOutEntity.getAmount());
        materialOutEntity1.setId(UUID.randomUUID().toString());
        materialOutEntity1.setParentId(materialOutEntity.getId());
        materialOutEntity1.setStatus(1);
        MaterialEntity materialEntity = materialService.get(materialOutEntity.getMaterialId());
        materialEntity.getAmount();
        if (materialEntity.getAmount() < materialOutEntity.getAmount()) {
            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数量不足");
        }
        return materialOutService.subMaterial(materialEntity, materialOutEntity, materialOutEntity1);
    }


    /**
     * 新增OR更新数据
     */
    @Log(description = "材料出库修改操作", module = "材料出库修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
    public Object update(@RequestBody String body, HttpServletRequest request) {
        logger.info("The MaterialOutController update method params are {}", body);
        MaterialOutEntity materialOutEntity = JSONObject.parseObject(body, MaterialOutEntity.class);
        MaterialEntity materialEntity1 = materialService.get(materialOutEntity.getMaterialName());
        System.out.println(materialEntity1);
        if (materialEntity1 == null) {
            int amount = materialOutEntity.getAmount();
            int newamount = materialOutEntity.getNewAmount();
            int lastAmount = newamount - amount;
            MaterialEntity materialEntity = materialService.get(materialOutEntity.getMaterialId());
            if (materialEntity.getAmount() < lastAmount) {
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数量不足");
            }
            materialEntity.setAmount(materialEntity.getAmount() - lastAmount);
            materialService.update(materialEntity);
            materialOutEntity.setAmount(newamount);
            materialOutEntity.setUpdateTime(new Date());
            return materialOutService.update(materialOutEntity);
        } else {
            int amount = materialOutEntity.getAmount();
            int newamount = materialOutEntity.getNewAmount();
            MaterialEntity materialEntity = materialService.get(materialOutEntity.getMaterialId());
            materialEntity.setAmount(materialEntity.getAmount() + amount);
            if (materialEntity1.getAmount() < newamount) {
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数量不足");
            } else {
                materialService.update(materialEntity);
                materialEntity1.setAmount(materialEntity1.getAmount() - newamount);
                materialEntity1.setUpdateTime(new Date());
                materialService.update(materialEntity1);
                materialOutEntity.setAmount(newamount);
                materialOutEntity.setMaterialId(materialOutEntity.getMaterialName());
                materialOutEntity.setMaterialName(materialEntity1.getMaterialName());
                materialOutEntity.setUpdateTime(new Date());
                return materialOutService.update(materialOutEntity);
            }
        }

    }


    /**
     * 根据Id查询是否加工
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryProcessById", method = RequestMethod.GET)
    public Object queryProcessById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialOutController queryProcessById method params are {}", params);
        return processParentService.queryProcessById(params);
    }

}
