package com.ky.newService.entity;

import com.ky.newService.mybatis.BaseEntity;

/**
 * @className: QualificationEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:49
 */
public class VisitEntity extends BaseEntity {
    private String name;
    private String visitDate;
    private String visitName;
    private String supplierManageId;
    private String content;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitName() {
        return visitName;
    }

    public void setVisitName(String visitName) {
        this.visitName = visitName;
    }

    public String getSupplierManageId() {
        return supplierManageId;
    }

    public void setSupplierManageId(String supplierManageId) {
        this.supplierManageId = supplierManageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
