package com.ky.ykt.service;

import com.ky.ykt.entity.ProjectStandardEntity;
import com.ky.ykt.mapper.ProjectStandardMapper;
import com.ky.ykt.mybatis.PagerResult;
import com.ky.ykt.mybatis.RestResult;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DepartmentService
 * @Description: TODO
 * @Author czw
 * @Date 2020/2/18
 **/
@Service
public class ProjectStandardService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectStandardService.class);

    @Autowired
    ProjectStandardMapper projectStandardMapper;

    public Object queryAll(Map params) {
        List<ProjectStandardEntity> departmentEntities = projectStandardMapper._queryAll(params);
        return departmentEntities;
    }

    /**
     * currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10
     *
     * @param params
     * @return
     */
    public RestResult queryPage(Map params) {
        List<ProjectStandardEntity> list = projectStandardMapper._queryPage(params);
        long count = projectStandardMapper._queryCount(params);
        PagerResult pagerResult = new PagerResult(list, count, MapUtils.getLongValue(params, "page"),
                MapUtils.getLongValue(params, "rows"));
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, pagerResult);
    }

    /**
     * 新增 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object add(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectStandardMapper._add(params));
    }

    public RestResult _get(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectStandardMapper._get(id));
    }

    public Object add(ProjectStandardEntity projectStandardEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectStandardMapper._addEntity(projectStandardEntity));
    }

    /**
     * 更新 参数 map里的key为属性名（字段首字母小写） value为要插入的key的value
     */
    public Object update(Map<String, String> params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectStandardMapper._update(params));
    }

    public Object update(ProjectStandardEntity projectStandardEntity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectStandardMapper._updateEntity(projectStandardEntity));
    }

    /**
     * 逻辑删除
     */
    public Object delete(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectStandardMapper._delete(id));
    }

    /**
     * 物理删除
     */
    public Object _deleteForce(String id) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, projectStandardMapper._deleteForce(id));
    }
}
