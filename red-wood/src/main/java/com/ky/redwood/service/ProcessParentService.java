package com.ky.redwood.service;

import com.ky.redwood.entity.MaterialEntity;
import com.ky.redwood.entity.ProcessParentEntity;
import com.ky.redwood.mapper.ProcessParentMapper;
import com.ky.redwood.mybatis.PagerResult;
import com.ky.redwood.mybatis.RestResult;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
@Service
public class ProcessParentService {

    @Autowired
    ProcessParentMapper processParentMapper;



    /**
     * 查询全部
     *
     * @param params
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Object queryAll(Map params) {
        List<ProcessParentEntity> processParentEntities = processParentMapper._queryAll(params);
        return new RestResult(processParentEntities.size(), processParentEntities).getRows();
    }


    /**
     * 查询类型为1
     *
     * @param params
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Object queryOrder(Map params) {
        List<ProcessParentEntity> processParentEntities = processParentMapper._queryOrder(params);
        return new RestResult(processParentEntities.size(), processParentEntities).getRows();
    }
    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public Object queryPage(Map params) {
        List<ProcessParentEntity> list = processParentMapper._queryPage(params);
        long count = processParentMapper._queryCount(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return pagerResult.getItems();
    }

    /**
     * 按id查询 参数 要查询的记录的id
     */
    public Object get(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processParentMapper._get(params.get("id")));
    }


    public ProcessParentEntity get(String id) {
        return  processParentMapper._get(id);
    }
    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processParentMapper._add(params));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(ProcessParentEntity entity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processParentMapper._addEntity(entity));
    }


    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processParentMapper._update(params));
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(ProcessParentEntity ProcessParentEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processParentMapper._updateEntity(ProcessParentEntity));
    }

    /**
     * 逻辑删除
     */
    public Object delete(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processParentMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processParentMapper._deleteForce(id));
    }


    public List nameById(String id) {
        List<ProcessParentEntity> list = processParentMapper._nameById(id);
        return list;
    }

    public List queryProcessById(Map params) {
        List<ProcessParentEntity> list = processParentMapper._queryProcessById(params);
        return list;
    }
}
