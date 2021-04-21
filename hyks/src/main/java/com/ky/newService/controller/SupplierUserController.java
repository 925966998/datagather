package com.ky.newService.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.newService.entity.SupplierUserEntity;
import com.ky.newService.entity.SysUserEntity;
import com.ky.newService.mapper.SupplierUserMapper;
import com.ky.newService.mapper.SysUserMapper;
import com.ky.newService.mybatis.RestResult;
import com.ky.newService.service.SupplierUserService;
import com.ky.newService.utils.HttpUtils;
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
@RequestMapping("/ky-supplier/supplierUser")
public class SupplierUserController {


    private static final Logger logger = LoggerFactory.getLogger(SupplierUserController.class);

    @Autowired
    SupplierUserService supplierUserService;
    @Autowired
    SupplierUserMapper supplierUserMapper;
    @Autowired
    SysUserMapper sysUserMapper;


    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        params.put("state", 0);
        logger.info("The SupplierUserController queryByParams method params are {}", params);
        return supplierUserService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierUserController queryByParams method params are {}", params);
        return supplierUserService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public Object saveOrUpdate(@RequestBody String body, HttpServletRequest request) {
        logger.info("The SupplierUserController saveOrUpdate method params are {}", body);
        SupplierUserEntity supplierUserEntity = JSONObject.parseObject(body, SupplierUserEntity.class);
        if (supplierUserEntity.getId() != null && supplierUserEntity.getId().length() > 0) {
            return supplierUserService.update(supplierUserEntity);
        } else {
            SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
            String supplierManageId = UUID.randomUUID().toString();
            supplierUserEntity.setId(supplierManageId);
            supplierUserEntity.setUserId(user.getId());
            return supplierUserService.add(supplierUserEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierUserController delete method params is {}", params);
        return supplierUserService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierUserController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        System.out.println(id);
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                supplierUserService._deleteForce(split[i]);
            }
        } else {
            supplierUserService._deleteForce(params.get("id").toString());
        }
        return new RestResult();
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        logger.info("The SupplierUserController queryPage method params are {}", params);
        params.put("state", 0);
        if (user.getRoleId().trim().equals("a599f1da-f57c-4afc-a600-b58e15836aa0") || user.getRoleId().trim().equals("07e74af9-a3dd-4093-b84f-9b3a7d249a19")) {
            return supplierUserService.queryPage(params);
        } else {
            params.put("userId", user.getId());
            return supplierUserService.queryPage(params);
        }
    }


    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/assign", method = RequestMethod.GET)
    public Object assign(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierUserController assign method params is {}", params);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        String supplierManageId = params.get("id").toString();
        String supplierTypeId= params.get("typeId").toString();
        String[] split1 = supplierTypeId.split(",");
        params.get("userId").toString();
        SysUserEntity sysUserEntity = sysUserMapper._get(params.get("userId").toString());
        if (supplierManageId.contains(",")) {
            String[] split = supplierManageId.split(",");
            for (int i = 0; i < split.length; i++) {
                Map map = new HashMap();
//                map.put("userId", params.get("userId").toString());
                map.put("supplierManageId", split[i]);
                map.put("supplierTypeId",sysUserEntity.getCompanyId());
                List<SupplierUserEntity> supplierUserEntities = supplierUserMapper._querySupplier(map);
                if (supplierUserEntities.size() < 1) {
                    SupplierUserEntity supplierUserEntity = new SupplierUserEntity();
                    supplierUserEntity.setId(UUID.randomUUID().toString());
                    supplierUserEntity.setSupplierManageId(split[i]);
                    supplierUserEntity.setUserId(params.get("userId").toString());
                    supplierUserEntity.setSupplierTypeId(sysUserEntity.getCompanyId());
                    supplierUserEntity.setState(1);
                    supplierUserService.add(supplierUserEntity);
                } else {
                    return new RestResult(RestResult.ERROR_CODE,RestResult.ERROR_MSG, "该用户已有第" + i + "个客商权限");
                }
            }
        } else {
            Map map=new HashMap();
            map.put("supplierManageId",params.get("id").toString());
            map.put("supplierTypeId",sysUserEntity.getCompanyId());
            List<SupplierUserEntity> supplierUserEntities = supplierUserMapper._querySupplier(map);
            if (supplierUserEntities.size() < 1) {
                SupplierUserEntity supplierUserEntity = new SupplierUserEntity();
                supplierUserEntity.setId(UUID.randomUUID().toString());
                supplierUserEntity.setSupplierManageId(supplierManageId);
                supplierUserEntity.setUserId(params.get("userId").toString());
                supplierUserEntity.setSupplierTypeId(sysUserEntity.getCompanyId());
                supplierUserEntity.setState(1);
                supplierUserService.add(supplierUserEntity);
            } else {
                return new RestResult(RestResult.ERROR_CODE,RestResult.ERROR_MSG, "该用户已有该客商权限");
            }
        }
        return new RestResult(RestResult.SUCCESS_CODE,RestResult.SUCCESS_MSG,"指派成功");
    }
}
