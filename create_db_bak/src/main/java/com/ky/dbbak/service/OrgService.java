package com.ky.dbbak.service;

import com.ky.dbbak.entity.DzzbxxEntity;
import com.ky.dbbak.entity.OrgEntity;
import com.ky.dbbak.entity.TreeNode;
import com.ky.dbbak.mapper.OrgMapper;
import com.ky.dbbak.mybatis.PagerResult;
import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.sourcemapper.GlztcsMapper;
import com.ky.dbbak.targetmapper.AllTableCheckDataMapper;
import com.ky.dbbak.targetmapper.DzzbxxMapper;
import com.ky.dbbak.targetmapper.TragetMapper;
import com.ky.dbbak.targetmapper.YsdwMapper;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(OrgService.class);
    @Autowired
    OrgMapper orgMapper;
    @Autowired
    AllTableCheckDataMapper allTableCheckDataMapper;
    @Autowired
    DzzbxxMapper dzzbxxMapper;

    @Autowired
    TragetMapper tragetMapper;

    @Autowired
    GlztcsMapper glztcsMapper;
    @Autowired
    YsdwMapper ysdwMapper;

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
        orgEntity.setKjdzzbbh(orgEntity.getAreaCode() + orgEntity.getOrgCode() + orgEntity.getZt() + orgEntity.getZtlx() + orgEntity.getKjnd());
        orgMapper._deleteByKjdzzbbh(orgEntity.getKjdzzbbh());
        dzzbxxMapper._deleteByCode(orgEntity.getKjdzzbbh());
        ysdwMapper.deleteYsdw(orgEntity.getOrgCode(), orgEntity.getOrgName(), orgEntity.getAreaCode());
        DzzbxxEntity dzzbxxEntity = new DzzbxxEntity();
        dzzbxxEntity.setBBH(orgEntity.getBbh());
        dzzbxxEntity.setBWB(orgEntity.getBwb());
        dzzbxxEntity.setDWDM(orgEntity.getOrgCode());
        dzzbxxEntity.setDWMC(orgEntity.getOrgName());
        dzzbxxEntity.setDWXZ(orgEntity.getDwxz());
        dzzbxxEntity.setHYFL(orgEntity.getHyflmc());
        dzzbxxEntity.setKFDW(orgEntity.getKfdw());
        dzzbxxEntity.setKJDZZBBH(orgEntity.getAreaCode() + orgEntity.getOrgCode() + orgEntity.getZt() + orgEntity.getZtlx() + orgEntity.getKjnd());
        dzzbxxEntity.setKJDZZBMC(orgEntity.getOrgName() + orgEntity.getKjnd());
        dzzbxxEntity.setKJKMJG(orgEntity.getKjdzzbbh());
        dzzbxxEntity.setKJND(orgEntity.getKjnd());
        dzzbxxEntity.setXZQHDM(orgEntity.getAreaCode());
        dzzbxxEntity.setXZQHMC(orgEntity.getAreaName());
        dzzbxxEntity.setZZJGDM(orgEntity.getZzjgdm());
        dzzbxxEntity.setSFHYYSZ(orgEntity.getSfhyysz());
        dzzbxxMapper._addEntity(dzzbxxEntity);
        this.updateYsdw(orgEntity);
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, orgMapper._addEntity(orgEntity));
    }
//
//    public void addYsdw(OrgEntity orgEntity) {
//        Map<String, Object> ysdwEntity = new HashMap<String, Object>();
//        ysdwEntity.put("XZQHDM", orgEntity.getAreaCode());
//        ysdwEntity.put("XZQHMC", orgEntity.getAreaName());
//        ysdwEntity.put("KJND", orgEntity.getKjnd());
//        ysdwEntity.put("DWMC", orgEntity.getOrgName());
//        ysdwEntity.put("DWDM", orgEntity.getOrgCode());
//        ysdwEntity.put("ZZJGDM", orgEntity.getZzjgdm());
//        ysdwEntity.put("DMJC", orgEntity.getDmjc());
//        ysdwEntity.put("SFMJ", orgEntity.getSfmj());
//        ysdwEntity.put("XZJB", orgEntity.getXzjb());
//        ysdwEntity.put("DWXZ", orgEntity.getDwxz());
//        ysdwEntity.put("YSGLFS", orgEntity.getYsglfs());
//        ysdwEntity.put("DWLB", orgEntity.getDwlb());
//        ysdwEntity.put("ZGKSDM", orgEntity.getZgksdm());
//        tragetMapper._addYsdw(ysdwEntity);
//    }

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
        OrgEntity orgEntity1 = orgMapper._get(orgEntity.getId());
        String oldKjdzzbbh = orgEntity1.getKjdzzbbh();
        dzzbxxMapper._deleteByCode(orgEntity1.getKjdzzbbh());
        ysdwMapper.deleteYsdw(orgEntity1.getOrgCode(), orgEntity1.getOrgName(), orgEntity1.getAreaCode());
        orgEntity.setKjdzzbbh(orgEntity.getAreaCode() + orgEntity.getOrgCode() + orgEntity.getZt() + orgEntity.getZtlx() + orgEntity.getKjnd());
        String newKjdzzbbh = orgEntity.getKjdzzbbh();
        DzzbxxEntity dzzbxxEntity = new DzzbxxEntity();
        dzzbxxEntity.setBBH(orgEntity.getBbh());
        dzzbxxEntity.setBWB(orgEntity.getBwb());
        dzzbxxEntity.setDWDM(orgEntity.getOrgCode());
        dzzbxxEntity.setDWMC(orgEntity.getOrgName());
        dzzbxxEntity.setDWXZ(orgEntity.getDwxz());
        dzzbxxEntity.setHYFL(orgEntity.getHyflmc());
        dzzbxxEntity.setKFDW(orgEntity.getKfdw());
        dzzbxxEntity.setKJDZZBBH(orgEntity.getKjdzzbbh());
        dzzbxxEntity.setKJDZZBMC(orgEntity.getOrgName() + orgEntity.getKjnd());
        dzzbxxEntity.setKJKMJG(glztcsMapper.queryKjkmjg(orgEntity.getKjnd()));
        dzzbxxEntity.setKJND(orgEntity.getKjnd());
        dzzbxxEntity.setXZQHDM(orgEntity.getAreaCode());
        dzzbxxEntity.setXZQHMC(orgEntity.getAreaName());
        dzzbxxEntity.setZZJGDM(orgEntity.getZzjgdm());
        dzzbxxEntity.setSFHYYSZ(orgEntity.getSfhyysz());
        dzzbxxMapper._addEntity(dzzbxxEntity);
        orgMapper._updateEntity(orgEntity);
        this.updateYsdw(orgEntity);
        if (!oldKjdzzbbh.equals(newKjdzzbbh)) {
            updateAllTableKjdzzbbh(oldKjdzzbbh, newKjdzzbbh);
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }

    public void updateYsdw(OrgEntity orgEntity) {
        Map<String, Object> ysdwEntity = new HashMap<String, Object>();
        ysdwMapper.deleteYsdw(orgEntity.getOrgCode(), orgEntity.getOrgName(), orgEntity.getAreaCode());
        ysdwEntity.put("XZQHDM", " ");
        ysdwEntity.put("XZQHMC", " ");
        ysdwEntity.put("KJND", 1);
        ysdwEntity.put("DWMC", " ");
        ysdwEntity.put("DWDM", " ");
        ysdwEntity.put("ZZJGDM", " ");
        ysdwEntity.put("DMJC", 1);
        ysdwEntity.put("SFMJ", " ");
        ysdwEntity.put("XZJB", " ");
        ysdwEntity.put("DWXZ", " ");
        ysdwEntity.put("SJDM", " ");
        ysdwEntity.put("YSGLFS", " ");
        ysdwEntity.put("DWLB", " ");
        ysdwEntity.put("ZGKSDM", " ");
        ysdwEntity.put("XZQHDM", " ");
        ysdwEntity.put("DWMC", orgEntity.getOrgName());
        ysdwEntity.put("DWDM", orgEntity.getOrgCode());
        ysdwEntity.put("XZQHDM", orgEntity.getAreaCode());
        /*List<Map<String, Object>> maps = tragetMapper.queryYSDW(ysdwEntity);
        if (maps != null && maps.size() > 0) {
            ysdwEntity.put("tableName", "YSDW");
            tragetMapper.truncateYsdw(ysdwEntity);
        }*/
        ysdwEntity.put("XZQHMC", orgEntity.getAreaName());
        ysdwEntity.put("KJND", orgEntity.getKjnd());

        ysdwEntity.put("ZZJGDM", orgEntity.getZzjgdm());
        ysdwEntity.put("DMJC", orgEntity.getDmjc());
        ysdwEntity.put("SFMJ", orgEntity.getSfmj());
        if(orgEntity.getXzjb()!=null){
            ysdwEntity.put("XZJB", orgEntity.getXzjb());
        }
        ysdwEntity.put("DWXZ", orgEntity.getDwxz());
        ysdwEntity.put("SJDM", orgEntity.getSjdm());
        if(orgEntity.getYsglfs()!=null){
            ysdwEntity.put("YSGLFS", orgEntity.getYsglfs());
        }
        ysdwEntity.put("DWLB", orgEntity.getDwlb());
        ysdwEntity.put("ZGKSDM", orgEntity.getZgksdm());
        tragetMapper._addYsdw(ysdwEntity);
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
    @Transactional
    public Object _deleteForce(String id) {
        OrgEntity orgEntity = orgMapper._get(id);
        boolean b = this.checkAllTableHasData(orgEntity.getAreaCode() + orgEntity.getOrgCode() + orgEntity.getZt() + orgEntity.getZtlx() + orgEntity.getKjnd());
        if (!b) {
            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "请清空采集数据后再删除");
        }
        List<OrgEntity> orgEntities = orgMapper.queryByPid(id);
        for (OrgEntity orgEntity1 :
                orgEntities) {
            orgMapper._deleteForce(orgEntity1.getId());
            dzzbxxMapper._deleteByCode(orgEntity1.getAreaCode() + orgEntity1.getOrgCode() + orgEntity1.getZt() + orgEntity1.getZtlx() + orgEntity1.getKjnd());
            ysdwMapper.deleteYsdw(orgEntity1.getOrgCode(), orgEntity1.getOrgName(), orgEntity1.getAreaCode());
        }
        dzzbxxMapper._deleteByCode(orgEntity.getAreaCode() + orgEntity.getOrgCode() + orgEntity.getZt() + orgEntity.getZtlx() + orgEntity.getKjnd());
        orgMapper._deleteForce(id);
        ysdwMapper.deleteYsdw(orgEntity.getOrgCode(), orgEntity.getOrgName(), orgEntity.getAreaCode());
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }

    public Object queryTree() {
        List<OrgEntity> areaEntities = orgMapper._queryAll(new HashMap());
        List<TreeNode> treeNodes = new ArrayList();
        for (OrgEntity orgEntity : areaEntities) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(orgEntity.getId());
            treeNode.setParentId(orgEntity.getPid());
            treeNode.setText(orgEntity.getOrgName() + orgEntity.getZt());
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
        OrgEntity orgEntity = orgMapper.queryById(id);
        /*if (orgEntity != null) {
            Map map = new HashMap();
            map.put("XZQHDM", orgEntity.getAreaCode());
            List<Map<String, Object>> maps = tragetMapper.queryYSDW(map);
            if (maps != null && maps.size() > 0) {
                orgEntity.setSjdm(MapUtils.getString(maps.get(0), "SJDM"));
                if (MapUtils.getString(maps.get(0), "DMJC") != null) {
                    orgEntity.setDmjc(Integer.valueOf(MapUtils.getString(maps.get(0), "DMJC")));
                }
                orgEntity.setSfmj(MapUtils.getString(maps.get(0), "SFMJ"));
                orgEntity.setXzjb(MapUtils.getString(maps.get(0), "XZJB"));
                orgEntity.setYsglfs(MapUtils.getString(maps.get(0), "YSGLFS"));
                orgEntity.setDwlb(MapUtils.getString(maps.get(0), "DWLB"));
                orgEntity.setZgksdm(MapUtils.getString(maps.get(0), "ZGKSDM"));
            }
        }*/
        return orgEntity;
    }

    private boolean checkAllTableHasData(String kjdzzbbh) {
        Map checkFZLXHasData = allTableCheckDataMapper.checkFZLXHasData(kjdzzbbh);
        if (checkFZLXHasData != null && checkFZLXHasData.size() > 0)
            return false;
        Map checkFZNCSHasData = allTableCheckDataMapper.checkFZNCSHasData(kjdzzbbh);
        if (checkFZNCSHasData != null && checkFZNCSHasData.size() > 0)
            return false;
        Map checkFZXXHasData = allTableCheckDataMapper.checkFZXXHasData(kjdzzbbh);
        if (checkFZXXHasData != null && checkFZXXHasData.size() > 0)
            return false;
        Map checkFZYEHasData = allTableCheckDataMapper.checkFZYEHasData(kjdzzbbh);
        if (checkFZYEHasData != null && checkFZYEHasData.size() > 0)
            return false;
        Map checkJZPZHasData = allTableCheckDataMapper.checkJZPZHasData(kjdzzbbh);
        if (checkJZPZHasData != null && checkJZPZHasData.size() > 0)
            return false;
        Map checkKJKMHasData = allTableCheckDataMapper.checkKJKMHasData(kjdzzbbh);
        if (checkKJKMHasData != null && checkKJKMHasData.size() > 0)
            return false;
        Map checkKJQJDYHasData = allTableCheckDataMapper.checkKJQJDYHasData(kjdzzbbh);
        if (checkKJQJDYHasData != null && checkKJQJDYHasData.size() > 0)
            return false;
        Map checkKMNCSHasData = allTableCheckDataMapper.checkKMNCSHasData(kjdzzbbh);
        if (checkKMNCSHasData != null && checkKMNCSHasData.size() > 0)
            return false;
        Map checkKMYEHasData = allTableCheckDataMapper.checkKMYEHasData(kjdzzbbh);
        if (checkKMYEHasData != null && checkKMYEHasData.size() > 0)
            return false;
        Map checkPZFZMXHasData = allTableCheckDataMapper.checkPZFZMXHasData(kjdzzbbh);
        if (checkPZFZMXHasData != null && checkPZFZMXHasData.size() > 0)
            return false;

        return true;
    }


    private void updateAllTableKjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                allTableCheckDataMapper.updateFZLXkjdzzbbh(oldkjdzzbbh, newkjdzzbbh);
                allTableCheckDataMapper.updateFZNCSkjdzzbbh(oldkjdzzbbh, newkjdzzbbh);
                allTableCheckDataMapper.updateFZXXkjdzzbbh(oldkjdzzbbh, newkjdzzbbh);
                allTableCheckDataMapper.updateFZYEkjdzzbbh(oldkjdzzbbh, newkjdzzbbh);
                allTableCheckDataMapper.updateJZPZkjdzzbbh(oldkjdzzbbh, newkjdzzbbh);
                allTableCheckDataMapper.updateKJKMkjdzzbbh(oldkjdzzbbh, newkjdzzbbh);
                allTableCheckDataMapper.updateKJQJDYkjdzzbbh(oldkjdzzbbh, newkjdzzbbh);
                allTableCheckDataMapper.updateKMNCSkjdzzbbh(oldkjdzzbbh, newkjdzzbbh);
                allTableCheckDataMapper.updateKMYEkjdzzbbh(oldkjdzzbbh, newkjdzzbbh);
                allTableCheckDataMapper.updatePZFZMXkjdzzbbh(oldkjdzzbbh, newkjdzzbbh);
            }
        }).start();
        logger.info("The updateAllTableKjdzzbbh success");
    }
}
