package com.ky.redwood.service;

import com.ky.redwood.entity.ProcessFlowEntity;
import com.ky.redwood.mapper.ProcessFlowMapper;
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
public class ProcessFlowService {

    @Autowired
    ProcessFlowMapper processFlowMapper;



    /**
     * 查询全部
     *
     * @param params
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Object queryAll(Map params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processFlowMapper._queryAll(params));
    }

    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public Object queryPage(Map params) {
        List<ProcessFlowEntity> list = processFlowMapper._queryPage(params);
        long count = processFlowMapper._queryCount(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        //return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
        return pagerResult.getItems();
    }

    /**
     * 按id查询 参数 要查询的记录的id
     */
    public Object get(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processFlowMapper._get(params.get("id")));
    }


    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processFlowMapper._add(params));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(ProcessFlowEntity entity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processFlowMapper._addEntity(entity));
    }


    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processFlowMapper._update(params));
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(ProcessFlowEntity ProcessFlowEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processFlowMapper._updateEntity(ProcessFlowEntity));
    }

    /**
     * 逻辑删除
     */
    public Object delete(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processFlowMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, processFlowMapper._deleteForce(id));
    }


    public List<ProcessFlowEntity> querySmallId(Map params) {
        return processFlowMapper.querySmallId(params.get("flowStatus").toString());
    }
}
