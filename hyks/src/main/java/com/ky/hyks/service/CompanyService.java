package com.ky.hyks.service;

import com.ky.hyks.entity.CompanyEntity;
import com.ky.hyks.entity.RoleEntity;
import com.ky.hyks.mapper.CompanyMapper;
import com.ky.hyks.mapper.RoleMapper;
import com.ky.hyks.mapper.RoleMenuMapper;
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
public class CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    CompanyMapper companyMapper;


    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public Object queryPage(Map params) {
        List<CompanyEntity> list = companyMapper._queryPage(params);
        long count = companyMapper._queryCount(params);
        return new RestResult(count,list);
    }

    public RestResult _get(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyMapper._get(id));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyMapper._add(params));
    }

    public Object add(CompanyEntity companyEntity) {
        companyMapper._addEntity(companyEntity);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyEntity.getId());
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyMapper._update(params));
    }

    public Object update(CompanyEntity companyEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyMapper._updateEntity(companyEntity));
    }

    /**
     * 逻辑删除
     */
    @Transactional
    public Object delete(String id) {
        companyMapper._delete(id);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyMapper._deleteForce(id));
    }

    public Object queryAll(Map params) {
        List<CompanyEntity> companyEntities = companyMapper._queryAll(params);
        return companyEntities;
    }

    public Object get(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, companyMapper._get(params.get("id")));
    }
}

