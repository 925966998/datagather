package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.ProcessEntity;
import com.ky.redwood.entity.ProductEntity;
import com.ky.redwood.entity.ProductEntity;
import com.ky.redwood.entity.SysUserEntity;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.mapper.ProcessFlowMapper;
import com.ky.redwood.mapper.ProcessMapper;
import com.ky.redwood.mybatis.RestResult;
import com.ky.redwood.service.MaterialOutService;
import com.ky.redwood.service.ProcessParentService;
import com.ky.redwood.service.ProductService;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/ky-redwood/product")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductService productService;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProductController queryByParams method params are {}", params);
        return productService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProductController queryById method params are {}", params);
        return productService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "产品新增,修改操作", module = "产品管理")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = "application/json")
    public Object saveOrUpdate(@RequestBody String body, HttpServletRequest request) {
        logger.info("The ProductController saveOrUpdate method params are {}", body);
        ProductEntity productEntity = JSONObject.parseObject(body, ProductEntity.class);
        if (StringUtils.isNotEmpty(productEntity.getId())) {
            return productService.update(productEntity);
        } else {
            return productService.add(productEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "产品出库删除操作", module = "出库管理")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProductController delete method params is {}", params);
        return productService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "产品出库删除操作", module = "出库管理")
    @RequestMapping(value = "/deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProductController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                productService._deleteForce(split[i]);
            }
        } else {
            productService._deleteForce(params.get("id").toString());
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
        logger.info("The ProductController queryPage method params are {}", params);
        return productService.queryPage(params);
    }

    /**
     * 物理出库
     */
    @Log(description = "产品出库管理", module = "产品出库")
    @RequestMapping(value = "/outForce", method = RequestMethod.GET)
    public Object outForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ProductController outForce method params is {}", params);
        String id = params.get("id").toString();
        String productParentId = UUID.randomUUID().toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                productService._updateForce(productParentId,split[i]);
            }
        } else {
            productService._updateForce(productParentId,params.get("id").toString());
        }
        return new RestResult();
    }
}
