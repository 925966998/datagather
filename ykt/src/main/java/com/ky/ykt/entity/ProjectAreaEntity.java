package com.ky.ykt.entity;

import com.ky.ykt.mybatis.BaseEntity;

import java.math.BigDecimal;

/**
 * @Classname:com.ky.ykt.entity
 * @Auther: ywj
 * @Date: 2020/7/11 10:49
 * @Description:
 */
public class ProjectAreaEntity extends BaseEntity {
    private String projectId;
    private String areaId;
    private BigDecimal areaAmount;
    private String userId;
    private String operDepartment;

    public String getOperDepartment() {
        return operDepartment;
    }

    public void setOperDepartment(String operDepartment) {
        this.operDepartment = operDepartment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public BigDecimal getAreaAmount() {
        return areaAmount;
    }

    public void setAreaAmount(BigDecimal areaAmount) {
        this.areaAmount = areaAmount;
    }
}
