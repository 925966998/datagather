package com.ky.hyks.service;

import com.ky.hyks.entity.OrderListEntity;
import com.ky.hyks.mapper.OrderListMapper;
import com.ky.hyks.mybatis.PagerResult;
import com.ky.hyks.mybatis.RestResult;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderListService
 * @Description: TODO
 * @Author Lcj
 * @Date 2021/5/17
 **/
@Service
public class OrderListService {

    private static final Logger logger = LoggerFactory.getLogger(OrderListService.class);

    @Autowired
    OrderListMapper orderListMapper;


    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public RestResult queryPage(Map params) {
        List<OrderListEntity> list = orderListMapper._queryPage(params);
        long count = orderListMapper._queryCount(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "page"),
                MapUtils.getLongValue(params, "rows"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult _get(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListMapper._get(id));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListMapper._add(params));
    }

    public Object add(OrderListEntity orderListEntity) {
        orderListMapper._addEntity(orderListEntity);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListEntity.getId());
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListMapper._update(params));
    }

    public Object update(OrderListEntity orderListEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListMapper._updateEntity(orderListEntity));
    }

    /**
     * 逻辑删除
     */
    @Transactional
    public Object delete(String id) {
        orderListMapper._delete(id);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListMapper._deleteForce(id));
    }

    public Object queryAll(Map params) {
        List<OrderListEntity> orderListEntities = orderListMapper._queryAll(params);
        return orderListEntities;
    }

    public Object get(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListMapper._get(params.get("id")));
    }
}

