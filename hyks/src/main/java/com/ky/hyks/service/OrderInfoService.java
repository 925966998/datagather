package com.ky.hyks.service;

import com.ky.hyks.entity.CompanyEntity;
import com.ky.hyks.entity.OrderInfoEntity;
import com.ky.hyks.mapper.CompanyMapper;
import com.ky.hyks.mapper.OrderInfoMapper;
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
 * @ClassName RoleService
 * @Description: TODO
 * @Author czw
 * @Date 2020/2/19
 **/
@Service
public class OrderInfoService {

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoService.class);

    @Autowired
    OrderInfoMapper orderInfoMapper;


    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public RestResult queryPage(Map params) {
        List<OrderInfoEntity> list = orderInfoMapper._queryPage(params);
        long count = orderInfoMapper._queryCount(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "page"),
                MapUtils.getLongValue(params, "rows"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public RestResult _get(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderInfoMapper._get(id));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderInfoMapper._add(params));
    }

    public Object add(OrderInfoEntity orderInfoEntity) {
        orderInfoMapper._addEntity(orderInfoEntity);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderInfoEntity.getId());
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderInfoMapper._update(params));
    }

    public Object update(OrderInfoEntity orderInfoEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderInfoMapper._updateEntity(orderInfoEntity));
    }

    /**
     * 逻辑删除
     */
    @Transactional
    public Object delete(String id) {
        orderInfoMapper._delete(id);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderInfoMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderInfoMapper._deleteForce(id));
    }

    public Object queryAll(Map params) {
        List<OrderInfoEntity> orderInfoEntities = orderInfoMapper._queryAll(params);
        return orderInfoEntities;
    }

    public Object get(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orderInfoMapper._get(params.get("id")));
    }
}

