package com.ky.newService.entity;

import com.ky.newService.mybatis.BaseEntity;

/**
 * @className: QualificationEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:49
 */
public class QualificationEntity extends BaseEntity {
    private String name;
    private Integer endDay;
    private String supplierManageId;
    private String startTime;
    private String endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public void setEndDay(Integer endDay) {
        this.endDay = endDay;
    }

    public String getSupplierManageId() {
        return supplierManageId;
    }

    public void setSupplierManageId(String supplierManageId) {
        this.supplierManageId = supplierManageId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
