package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.SaleEntity;
import com.ky.redwood.entity.StockEntity;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.mybatis.RestResult;
import com.ky.redwood.service.SaleService;
import com.ky.redwood.service.StockService;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ky-redwood/sale")
public class SaleController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SaleService saleService;
    @Autowired
    StockService stockService;


    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SaleController queryByParams method params are {}", params);
        return saleService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SaleController queryByParams method params are {}", params);
        return saleService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "销售单新增,修改操作", module = "销售单管理")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = "application/json")
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The SaleController saveOrUpdate method params are {}", body);
        SaleEntity saleEntity = JSONObject.parseObject(body, SaleEntity.class);
        if (StringUtils.isNotEmpty(saleEntity.getId())) {
            return saleService.update(saleEntity);
        } else {
            saleEntity.setId(UUID.randomUUID().toString());
            StockEntity stockEntity = stockService.get(saleEntity.getStockId());
            saleEntity.setGoodsModel(stockEntity.getGoodsModel());
            saleEntity.setGoodsSpecs(stockEntity.getGoodsSpecs());
            saleEntity.setProductName(stockEntity.getProductName());
            saleEntity.setGoodsUnit(stockEntity.getGoodsUnit());
            saleEntity.setSaleOrNo(0);
            if (saleEntity.getGoodsNum()>stockEntity.getGoodsNum()){
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数量不足");
            }
            stockEntity.setGoodsNum(stockEntity.getGoodsNum()-saleEntity.getGoodsNum());
            stockService.update(stockEntity);
            return saleService.add(saleEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "销售单管理逻辑删除操作", module = "销售单管理")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SaleController delete method params is {}", params);
        return saleService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "销售单管理物理删除操作", module = "销售单管理")
    @RequestMapping(value = "/deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SaleController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                saleService._deleteForce(split[i]);
            }
        } else {
            saleService._deleteForce(params.get("id").toString());
        }
        return new RestResult();
    }

    @Log(description = "销售单管理操作", module = "生成订单管理")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.GET)
    public Object generateOrder(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SaleController generateOrder method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                SaleEntity saleEntity = saleService.get(split[i]);
                saleEntity.setSaleOrNo(1);
                saleService.update(saleEntity);
            }
        } else {
            SaleEntity saleEntity = saleService.get(id);
            saleEntity.setSaleOrNo(1);
            saleService.update(saleEntity);
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
        logger.info("The SaleController queryPage method params are {}", params);
        return saleService.queryPage(params);
    }

}
