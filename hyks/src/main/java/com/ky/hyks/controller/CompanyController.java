package com.ky.hyks.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.hyks.entity.CompanyEntity;
import com.ky.hyks.entity.CompanyOrderEntity;
import com.ky.hyks.entity.SysUserEntity;
import com.ky.hyks.logUtil.Log;
import com.ky.hyks.mapper.CompanyOrderMapper;
import com.ky.hyks.mapper.SysUserMapper;
import com.ky.hyks.mybatis.PagerResult;
import com.ky.hyks.mybatis.RestResult;
import com.ky.hyks.service.CompanyService;
import com.ky.hyks.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ky-supplier/company")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    CompanyService companyService;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    CompanyOrderMapper companyOrderMapper;
    /**
     * 查询全部数据不分页
     */
    @RequestMapping(value = "queryByParams", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The CompanyController queryByParams method params are {}", params);
        return companyService.queryAll(params);
    }

    @RequestMapping(value = "queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonController queryByParams method params are {}", params);
        return companyService.get(params);
    }
    /**
     * 新增OR更新数据
     */
    @Log(description = "角色管理新增，修改操作", module = "角色管理")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST, produces = "application/json")
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The CompanyController saveOrUpdate method params are {}", body);
        CompanyEntity companyEntity = JSONObject.parseObject(body, CompanyEntity.class);
        if (StringUtils.isNotEmpty(companyEntity.getId())) {
            return companyService.update(companyEntity);
        } else {
            companyEntity.setId(UUID.randomUUID().toString());
            return companyService.add(companyEntity);
        }
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The CompanyController queryPage method params are {}", params);
        params.put("currentPage", params.get("page"));
        params.put("pageSize", params.get("rows"));
        return companyService.queryPage(params);
    }
    public JSONObject toJson(PagerResult data) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("total", data.getTotalItemsCount());
        jsonObj.put("rows", data.getItems());
        return jsonObj;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public CompanyEntity select(String id) {
        logger.info("The CompanyController queryPage method params are {}", id);
        RestResult restResult = companyService._get(id);
        CompanyEntity data = (CompanyEntity) restResult.getData();
        return data;
    }

    /**
     * 删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "/deleteOne", method = RequestMethod.GET)
    public Object deleteOne(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The CompanyController delete method params is {}", params);
        return companyService.delete(params.get("id").toString());
    }

    /**
     * 删除多个
     */
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteMoney(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The CompanyController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                companyService._deleteForce(split[i]);
            }
        } else {
            companyService._deleteForce(params.get("id").toString());
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }

    @RequestMapping(value = "/assign", method = RequestMethod.GET)
    public Object assign(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The CompanyOrderController assign method params is {}", params);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        String companyId = params.get("id").toString();
        String[] split = companyId.split(",");
        params.get("orderId").toString();
        if (companyId.contains(",")) {
            for (int i = 0; i < split.length; i++) {
                Map map = new HashMap();
                map.put("companyId", split[i]);
                map.put("orderId",params.get("orderId").toString());
                List<CompanyOrderEntity> companyOrderEntities = companyOrderMapper._queryRelation(map);
                if (companyOrderEntities.size() < 1) {
                    CompanyOrderEntity companyOrderEntity = new CompanyOrderEntity();
                    companyOrderEntity.setId(UUID.randomUUID().toString());
                    companyOrderEntity.setCompanyId(split[i]);
                    companyOrderEntity.setOrderId(params.get("orderId").toString());
                    companyOrderMapper._addEntity(companyOrderEntity);
                } else {
                    return new RestResult(RestResult.ERROR_CODE,RestResult.ERROR_MSG, "该用户已有第" + i + "个客商权限");
                }
            }
        } else {
            Map map = new HashMap();
            map.put("companyId", params.get("id").toString());
            map.put("orderId",params.get("orderId").toString());
            List<CompanyOrderEntity> companyOrderEntities = companyOrderMapper._queryRelation(map);
            if (companyOrderEntities.size() < 1) {
                CompanyOrderEntity companyOrderEntity = new CompanyOrderEntity();
                companyOrderEntity.setId(UUID.randomUUID().toString());
                companyOrderEntity.setCompanyId(params.get("id").toString());
                companyOrderEntity.setOrderId(params.get("orderId").toString());
                companyOrderMapper._addEntity(companyOrderEntity);
            } else {
                return new RestResult(RestResult.ERROR_CODE,RestResult.ERROR_MSG, "该用户已有该客商权限");
            }
        }
        return new RestResult(RestResult.SUCCESS_CODE,RestResult.SUCCESS_MSG,"指派成功");
    }


}
