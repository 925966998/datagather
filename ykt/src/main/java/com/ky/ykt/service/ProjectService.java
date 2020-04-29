package com.ky.ykt.service;

import com.ky.ykt.entity.DepartmentEntity;
import com.ky.ykt.entity.ProjectEntity;
import com.ky.ykt.entity.StatisticEntity;
import com.ky.ykt.entity.SysUserEntity;
import com.ky.ykt.mapper.DepartmentMapper;
import com.ky.ykt.mapper.ProjectDetailMapper;
import com.ky.ykt.mapper.ProjectMapper;
import com.ky.ykt.mybatis.PagerResult;
import com.ky.ykt.mybatis.RestResult;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ProjectService
 * @Description: TODO
 * @Author czw
 * @Date 2020/2/24
 **/
@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    ProjectDetailMapper projectDetailMapper;

    public Object queryAll(Map params, HttpServletRequest request) {
        Object roleCodeSession = request.getSession().getAttribute("roleCode");
        String roleCode = "";
        if (roleCodeSession != null) {
            roleCode = roleCodeSession.toString();
            if(roleCode.equals("4")){
                SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
                DepartmentEntity departmentEntity = departmentMapper._get(user.getDepartmentId());
                params.put("DJFlag","4J");
                params.put("departmentId",departmentEntity.getParentId());
            }
        }
        List<ProjectEntity> projectDetailEntities = projectMapper._queryAll(params);
        return projectDetailEntities;
    }

    public Map<String, Object> queryCount(Map params) {
        params.put("state", 1);
        long finishState = projectMapper._queryCount(params);
        params.put("state", 0);
        long ingState = projectMapper._queryCount(params);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finishState", finishState);
        map.put("ingState", ingState);
        return map;
    }

    public RestResult queryPage(Map params) {
        List<ProjectEntity> list = projectMapper._queryPage(params);
        long count = projectMapper._queryCount(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "page"),
                MapUtils.getLongValue(params, "rows"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    public Object add(ProjectEntity projectEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectMapper._addEntity(projectEntity));
    }

    /**
     * 逻辑删除
     */
    public Object delete(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectMapper._delete(id));
    }

    public List<StatisticEntity> queryType() {
        return projectMapper.queryType();
    }

    public List<StatisticEntity> statistic(Map pagerParam) {
        return projectDetailMapper.statistic(pagerParam);
    }

    public RestResult _get(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectMapper._get(id));
    }

    public Object update(ProjectEntity projectEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectMapper._updateEntity(projectEntity));
    }
}
