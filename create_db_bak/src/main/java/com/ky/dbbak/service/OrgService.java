package com.ky.dbbak.service;

import com.ky.dbbak.entity.DzzbxxEntity;
import com.ky.dbbak.entity.OrgEntity;
import com.ky.dbbak.entity.TreeNode;
import com.ky.dbbak.mapper.OrgMapper;
import com.ky.dbbak.mybatis.PagerResult;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.sourcemapper.GlztcsMapper;
import com.ky.dbbak.targetmapper.DzzbxxMapper;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrgService {

    @Autowired
    OrgMapper orgMapper;

    @Autowired
    DzzbxxMapper dzzbxxMapper;

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
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orgMapper._queryAll(params));
    }

    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public Object queryPage(Map params) {
        List<OrgEntity> list = orgMapper._queryPage(params);
        long count = orgMapper._queryCount(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "currentPage"),
                MapUtils.getLongValue(params, "pageSize"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    /**
     * 按id查询 参数 要查询的记录的id
     */
    public Object get(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orgMapper._get(params.get("id")));
    }


    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orgMapper._add(params));
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    @Transactional
    public Object add(OrgEntity orgEntity) {
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
        dzzbxxEntity.setZZJGDM(orgEntity.getZzjgdm());
        dzzbxxEntity.setSFHYYSZ(orgEntity.getSfhyysz());
        dzzbxxMapper._addEntity(dzzbxxEntity);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orgMapper._addEntity(orgEntity));
    }


    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orgMapper._update(params));
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    @Transactional
    public Object update(OrgEntity orgEntity) {
        DzzbxxEntity dzzbxxEntity = dzzbxxMapper._queryByCode(orgEntity.getOrgCode());
        if (dzzbxxEntity != null) {
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
            dzzbxxEntity.setZZJGDM(orgEntity.getZzjgdm());
            dzzbxxEntity.setSFHYYSZ(orgEntity.getSfhyysz());
            dzzbxxMapper._updateEntity_pk(dzzbxxEntity, "XZQHDM", dzzbxxEntity.getXZQHDM());
        } else {
            dzzbxxEntity = new DzzbxxEntity();
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
            dzzbxxEntity.setZZJGDM(orgEntity.getZzjgdm());
            dzzbxxEntity.setSFHYYSZ(orgEntity.getSfhyysz());
            dzzbxxMapper._addEntity(dzzbxxEntity);
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orgMapper._updateEntity(orgEntity));
    }

    /**
     * 逻辑删除
     */
    public Object delete(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orgMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orgMapper._deleteForce(id));
    }

    public Object queryTree() {
        List<OrgEntity> areaEntities = orgMapper._queryAll(new HashMap());
        List<TreeNode> treeNodes = new ArrayList();
        for (OrgEntity orgEntity : areaEntities) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(orgEntity.getId());
            treeNode.setParentId(orgEntity.getPid());
            treeNode.setText(orgEntity.getOrgName());
            treeNodes.add(treeNode);
        }

        Map<String, List<TreeNode>> sub = treeNodes.stream().filter(node -> (!node.getParentId().equals("0")) && node.getParentId() != null).collect(Collectors.groupingBy(node -> node.getParentId()));
        treeNodes.forEach(node -> node.setChildren(sub.get(node.getId())));
        List<TreeNode> collect = treeNodes.stream().filter(node -> (node.getParentId().equals("0") || node.getParentId() == null)).collect(Collectors.toList());
        TreeNode treeNode = new TreeNode();
        treeNode.setId("0");
        treeNode.setText("组织机构");
        treeNode.setChildren(collect);
        treeNodes.clear();
        treeNodes.add(treeNode);
        return treeNodes;

    }

    public Object queryById(String id) {
        return orgMapper.queryById(id);
    }
}
