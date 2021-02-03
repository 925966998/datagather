package com.ky.dbbak.service;

import com.ky.dbbak.entity.DzzbxxEntity;
import com.ky.dbbak.entity.OrgEntity;
import com.ky.dbbak.mapper.OrgMapper;
import com.ky.dbbak.mybatis.PagerResult;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.sourcemapper.GlztcsMapper;
import com.ky.dbbak.targetmapper.DzzbxxMapper;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DzzbxxService {

    @Autowired
    DzzbxxMapper dzzbxxMapper;

    @Autowired
    OrgMapper orgMapper;

    @Autowired
    GlztcsMapper glztcsMapper;


    /**
     * 查询全部
     *
     * @param params
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Object queryAll(Map params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, dzzbxxMapper._queryAll(params));
    }

    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public Object queryPage(Map params) {
        List<DzzbxxEntity> list = dzzbxxMapper._queryPage(params);
        long count = dzzbxxMapper._queryCount(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    /**
     * 按id查询 参数 要查询的记录的id
     */
    public Object get(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, dzzbxxMapper._get(params.get("id")));
    }


    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, dzzbxxMapper._add(params));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(DzzbxxEntity entity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, dzzbxxMapper._addEntity(entity));
    }


    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, dzzbxxMapper._update(params));
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(DzzbxxEntity DzzbxxEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, dzzbxxMapper._updateEntity(DzzbxxEntity));
    }

    /**
     * 逻辑删除
     */
    public Object delete(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, dzzbxxMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, dzzbxxMapper._deleteForce(id));
    }

    public Object pullDzzbxxData() {
        List<OrgEntity> orgEntities = orgMapper._queryAll(new HashMap());
        for (OrgEntity orgEntity : orgEntities) {
            DzzbxxEntity dzzbxxEntity = new DzzbxxEntity();
            dzzbxxEntity.setBBH(orgEntity.getBbh());
            dzzbxxEntity.setBWB(orgEntity.getBwb());
            dzzbxxEntity.setDWDM(orgEntity.getOrgCode());
            dzzbxxEntity.setDWMC(orgEntity.getOrgName());
            dzzbxxEntity.setDWXZ(orgEntity.getDwxz());
            dzzbxxEntity.setHYFL(orgEntity.getHyfl());
            dzzbxxEntity.setKFDW(orgEntity.getKfdw());
            dzzbxxEntity.setKJDZZBBH(orgEntity.getAreaCode() + orgEntity.getOrgCode() + orgEntity.getZt() + orgEntity.getZtlx() + orgEntity.getKjnd());
            dzzbxxEntity.setKJDZZBMC(orgEntity.getOrgName() + orgEntity.getKjnd());
            dzzbxxEntity.setKJKMJG(glztcsMapper.queryKjkmjg(orgEntity.getKjnd()));
            dzzbxxEntity.setKJND(orgEntity.getKjnd());
            dzzbxxEntity.setXZQHDM(orgEntity.getAreaCode());
            dzzbxxEntity.setXZQHMC(orgEntity.getAreaName());
            dzzbxxEntity.setTYSHXYDM(orgEntity.getZzjgdm());
            dzzbxxEntity.setSFHYYSZ(orgEntity.getSfhyysz());
            dzzbxxMapper._addEntity(dzzbxxEntity);
        }
        return null;
    }
}
