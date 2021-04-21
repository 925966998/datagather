package com.ky.newService.service;


import com.ky.newService.entity.SupplierTypeEntity;
import com.ky.newService.mapper.SupplierTypeMapper;
import com.ky.newService.mybatis.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SupplierTypeService {

    private static final Logger logger = LoggerFactory.getLogger(SupplierTypeService.class);

    @Autowired
    SupplierTypeMapper supplierTypeMapper;

    /**
     * 查询全部
     *
     * @param params
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Object queryAll(Map params) {
       List<SupplierTypeEntity> supplierTypeEntityList = supplierTypeMapper._queryAll(params);
       return supplierTypeEntityList;
    }

    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public Object queryPage(Map params) {
        List<SupplierTypeEntity> list = supplierTypeMapper._queryPage(params);
        long count = supplierTypeMapper._queryCount(params);
        return new RestResult(count, list);
//        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
//                MapUtils.getLongValue(params, "pageSize"));
//        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    /**
     * 按id查询 参数 要查询的记录的id
     */
    public Object get(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierTypeMapper._get(params.get("id")));
    }


    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierTypeMapper._add(params));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(SupplierTypeEntity supplierTypeEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierTypeMapper._addEntity(supplierTypeEntity));
    }


    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierTypeMapper._update(params));
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(SupplierTypeEntity supplierTypeEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierTypeMapper._updateEntity(supplierTypeEntity));
    }

    /**
     * 逻辑删除
     */
    public Object delete(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierTypeMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierTypeMapper._deleteForce(id));
    }

    public List<SupplierTypeEntity> queryByParentId(Map<String, Object> params) {
        //params.put("isUse", 0);
        List<SupplierTypeEntity> supplierTypeEntities = supplierTypeMapper._queryAll(params);
        return supplierTypeEntities;
    }
}
