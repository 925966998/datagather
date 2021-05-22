package com.ky.hyks.service;

import com.ky.hyks.entity.CompanyEntity;
import com.ky.hyks.entity.CompanyOrderEntity;
import com.ky.hyks.entity.OrderListInfoEntity;
import com.ky.hyks.mapper.CompanyMapper;
import com.ky.hyks.mapper.CompanyOrderMapper;
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
public class CompanyOrderService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyOrderService.class);

    @Autowired
    CompanyOrderMapper companyOrderMapper;


    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public RestResult queryPage(Map params) {
        List<CompanyOrderEntity> list = companyOrderMapper._queryPage(params);
        long count = companyOrderMapper._queryCount(params);
        return new RestResult(count, list);
    }

    public RestResult _get(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyOrderMapper._get(id));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyOrderMapper._add(params));
    }

    public Object add(CompanyOrderEntity companyOrderEntity) {
        companyOrderMapper._addEntity(companyOrderEntity);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyOrderEntity.getId());
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyOrderMapper._update(params));
    }

    public Object update(CompanyOrderEntity companyOrderEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyOrderMapper._updateEntity(companyOrderEntity));
    }

    /**
     * 逻辑删除
     */
    @Transactional
    public Object delete(String id) {
        companyOrderMapper._delete(id);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyOrderMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyOrderMapper._deleteForce(id));
    }

    public Object queryAll(Map params) {
        List<CompanyOrderEntity> companyOrderEntities = companyOrderMapper._queryAll(params);
        return companyOrderEntities;
    }

    public Object get(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyOrderMapper.queryById(params.get("id")));
    }

    public RestResult queryCommitPrice(Map params) {
        List<CompanyOrderEntity> list = companyOrderMapper._queryCommitPrice(params);
        long count = companyOrderMapper._queryCommitCount(params);
        return new RestResult(count, list);
    }
}

