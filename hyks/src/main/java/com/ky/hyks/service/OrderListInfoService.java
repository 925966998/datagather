package com.ky.hyks.service;

import com.ky.hyks.entity.CompanyOrderEntity;
import com.ky.hyks.entity.OrderInfoEntity;
import com.ky.hyks.entity.OrderListInfoEntity;
import com.ky.hyks.mapper.CompanyOrderMapper;
import com.ky.hyks.mapper.OrderListInfoMapper;
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
 * @ClassName OrderListInfoService
 * @Description: TODO
 * @Author Lcj
 * @Date 2020/5/20
 **/
@Service
public class OrderListInfoService {

    private static final Logger logger = LoggerFactory.getLogger(OrderListInfoService.class);

    @Autowired
    OrderListInfoMapper orderListInfoMapper;


    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public RestResult queryPage(Map params) {
        List<OrderListInfoEntity> list = orderListInfoMapper._queryPage(params);
        long count = orderListInfoMapper._queryCount(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "page"),
                MapUtils.getLongValue(params, "rows"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult _get(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListInfoMapper._get(id));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListInfoMapper._add(params));
    }

    public Object add(OrderListInfoEntity orderListInfoEntity) {
        orderListInfoMapper._addEntity(orderListInfoEntity);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListInfoEntity.getId());
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListInfoMapper._update(params));
    }

    public Object update(OrderListInfoEntity orderListInfoEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListInfoMapper._updateEntity(orderListInfoEntity));
    }

    /**
     * 逻辑删除
     */
    @Transactional
    public Object delete(String id) {
        orderListInfoMapper._delete(id);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListInfoMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderListInfoMapper._deleteForce(id));
    }

    public Object queryAll(Map params) {
        List<OrderListInfoEntity> orderListInfoEntityList = orderListInfoMapper._queryAll(params);
        return orderListInfoEntityList;
    }

}

