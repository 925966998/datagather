package com.ky.newService.entity;

import com.ky.newService.mybatis.BaseEntity;

/**
 * @className: ViolationEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:49
 */
public class ViolationEntity extends BaseEntity {
    private String name;
    private String supplierManageId;
    private String startTime;
    private String opinion;
    private String dealTime;
    private Integer result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
