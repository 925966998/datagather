package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.*;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.mapper.GoodsMapper;
import com.ky.redwood.mapper.ProcessMapper;
import com.ky.redwood.mybatis.RestResult;
import com.ky.redwood.service.*;
import com.ky.redwood.utils.HttpUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ProcessMapper processMapper;


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
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryAllById", method = RequestMethod.GET)
    public Object queryAllById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialOutController queryAllById method params are {}", params);
        return materialOutService.getAll(params);
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
            return materialOutService.addMaterial(materialOutEntity,request);
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
        return materialOutService.queryPage(dealTimeFormat(params));
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


    @Log(description = "材料出库补料操作", module = "材料补料")
    @RequestMapping(value = "/subMaterial", method = RequestMethod.POST, consumes = "application/json")
    public Object subMaterial(@RequestBody String body, HttpServletRequest request) {
        logger.info("The MaterialOutController saveOrUpdate method params are {}", body);
        MaterialOutEntity materialOutEntity = JSONObject.parseObject(body, MaterialOutEntity.class);
        MaterialOutEntity materialOutEntity1 = materialOutService.get(materialOutEntity.getId());
        materialOutEntity1.setAmount(materialOutEntity.getAmount());
        materialOutEntity1.setId(UUID.randomUUID().toString());
        materialOutEntity1.setParentId(materialOutEntity.getId());
        materialOutEntity1.setStatus(1);
        materialOutEntity1.setUseAmount(BigDecimal.ZERO);
        MaterialEntity materialEntity = materialService.get(materialOutEntity.getMaterialId());
        materialEntity.getAmount();
        if (materialEntity.getAmount().compareTo(materialOutEntity.getAmount()) == -1) {
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
        ProcessEntity processEntity=processMapper.querybymaterialOutId(materialOutEntity.getId());
        GoodsEntity goodsEntity = goodsMapper._get(materialOutEntity.getProductName());
        if (materialEntity1 == null) {
            BigDecimal amount = materialOutEntity.getAmount();
            BigDecimal newamount = materialOutEntity.getNewAmount();
            BigDecimal lastAmount = newamount.subtract(amount);
            MaterialEntity materialEntity = materialService.get(materialOutEntity.getMaterialId());
            if (materialEntity.getAmount().compareTo(lastAmount) == -1 ) {
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数量不足");
            }
            materialEntity.setAmount(materialEntity.getAmount().subtract(lastAmount));
            materialService.update(materialEntity);
            if (goodsEntity ==null){
                materialOutEntity.setGoodsId(materialOutEntity.getGoodsId());
                materialOutEntity.setProductName(materialOutEntity.getProductName());
                processEntity.setProductName(materialOutEntity.getProductName());
                processService.update(processEntity);
            }else {
                materialOutEntity.setGoodsId(goodsEntity.getId());
                materialOutEntity.setProductName(goodsEntity.getAllName());
                processEntity.setProductName(goodsEntity.getAllName());
                processService.update(processEntity);
            }
            materialOutEntity.setAmount(newamount);
            materialOutEntity.setUpdateTime(new Date());
            materialOutEntity.setUseAmount(BigDecimal.ZERO);
            return materialOutService.update(materialOutEntity);
        } else {
            BigDecimal amount = materialOutEntity.getAmount();
            BigDecimal newamount = materialOutEntity.getNewAmount();
            MaterialEntity materialEntity = materialService.get(materialOutEntity.getMaterialId());
            materialEntity.setAmount(materialEntity.getAmount().add(amount));
            if (materialEntity1.getAmount().compareTo(newamount) ==-1 ) {
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数量不足");
            } else {
                materialService.update(materialEntity);
                materialEntity1.setAmount(materialEntity1.getAmount().subtract(newamount));
                materialEntity1.setUpdateTime(new Date());
                materialService.update(materialEntity1);
                materialOutEntity.setAmount(newamount);
                if (goodsEntity ==null){
                    materialOutEntity.setGoodsId(materialOutEntity.getGoodsId());
                    materialOutEntity.setProductName(materialOutEntity.getProductName());
                    processEntity.setProductName(materialOutEntity.getProductName());
                    processService.update(processEntity);
                }else {
                    materialOutEntity.setGoodsId(goodsEntity.getId());
                    materialOutEntity.setProductName(goodsEntity.getAllName());
                    processEntity.setProductName(goodsEntity.getAllName());
                    processService.update(processEntity);
                }
                materialOutEntity.setMaterialId(materialOutEntity.getMaterialName());
                materialOutEntity.setMaterialName(materialEntity1.getMaterialName());
                materialOutEntity.setUpdateTime(new Date());
                materialOutEntity.setUseAmount(BigDecimal.ZERO);
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



    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByMaterialOut", method = RequestMethod.GET)
    public Object queryByConsumable(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The MaterialOutController queryByMaterialOut method params are {}", params);
        return materialService.queryMaterialOut(params);
    }
}
