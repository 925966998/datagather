package com.ky.newService.service;


import com.ky.newService.entity.QualificationEntity;
import com.ky.newService.entity.SupplierChangeEntity;
import com.ky.newService.entity.ViolationEntity;
import com.ky.newService.mapper.QualificationMapper;
import com.ky.newService.mapper.SupplierChangeMapper;
import com.ky.newService.mapper.SupplierManageMapper;
import com.ky.newService.mapper.ViolationMapper;
import com.ky.newService.mybatis.RestResult;
import com.ky.newService.entity.SupplierManageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplierManageService {

    private static final Logger logger = LoggerFactory.getLogger(SupplierManageService.class);

    @Autowired
    SupplierManageMapper supplierManageMapper;
    @Autowired
    SupplierChangeMapper supplierChangeMapper;
    @Autowired
    QualificationMapper qualificationMapper;
    @Autowired
    ViolationMapper violationMapper;
    /**
     * 查询全部
     *
     * @param params
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Object queryAll(Map params) {
       List<SupplierManageEntity> supplierManageEntityList = supplierManageMapper._queryAll(params);
       return supplierManageEntityList;
    }

    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public Object queryPage(Map params) {
        List<SupplierManageEntity> list = supplierManageMapper._queryPage(params);
        long count = supplierManageMapper._queryCount(params);
        return new RestResult(count, list);
//        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
//                MapUtils.getLongValue(params, "pageSize"));
//        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    /**
     * 按id查询 参数 要查询的记录的id
     */
    public Object get(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierManageMapper._get(params.get("id")));
    }


    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierManageMapper._add(params));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(SupplierManageEntity supplierManageEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierManageMapper._addEntity(supplierManageEntity));
    }


    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierManageMapper._update(params));
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(SupplierManageEntity supplierManageEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierManageMapper._updateEntity(supplierManageEntity));
    }

    /**
     * 逻辑删除
     */
    public Object delete(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierManageMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, supplierManageMapper._deleteForce(id));
    }

    public Object queryContent(Map params) {
        List<SupplierManageEntity> list = supplierManageMapper.queryContent(params);
        List result=new ArrayList();
        for (SupplierManageEntity supplierManageEntity : list){
            if (supplierManageEntity!=null){
                Map map = new HashMap();
                map.put("supplierManageId",supplierManageEntity.getId());
                List<QualificationEntity> qualificationEntities=qualificationMapper._queryBySupplierId(map);
                supplierManageEntity.setQualificationNum(qualificationEntities.size());
                List<ViolationEntity> violationEntities=violationMapper._queryBySupplierId(map);
                supplierManageEntity.setViolationNum(violationEntities.size());
                result.add(supplierManageEntity);
            }
        }
        long count = supplierManageMapper._queryContentCount(params);
        return new RestResult(count, result);

    }
    public Object queryPreContent(Map params) {
        List<SupplierManageEntity> list = supplierManageMapper.queryPreContent(params);
        List result=new ArrayList();
        for (SupplierManageEntity supplierManageEntity : list){
            if (supplierManageEntity!=null){
                Map map = new HashMap();
                map.put("supplierManageId",supplierManageEntity.getId());
                List<QualificationEntity> qualificationEntities=qualificationMapper._queryBySupplierId(map);
                supplierManageEntity.setQualificationNum(qualificationEntities.size());
                List<ViolationEntity> violationEntities=violationMapper._queryBySupplierId(map);
                supplierManageEntity.setViolationNum(violationEntities.size());
                result.add(supplierManageEntity);
            }
        }
        long count = supplierManageMapper._queryPreContentCount(params);
        return new RestResult(count, result);
//        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
//                MapUtils.getLongValue(params, "pageSize"));
//        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public Object queryChange(Map params) {
        List<SupplierChangeEntity> list = supplierChangeMapper._queryPage(params);
        long count = supplierChangeMapper._queryCount(params);
        return new RestResult(count, list);
    }

}
