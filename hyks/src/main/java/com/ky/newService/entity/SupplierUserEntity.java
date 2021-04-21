package com.ky.newService.entity;

import com.ky.newService.mybatis.BaseEntity;

/**
 * @className: supplierType
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:37
 */
public class SupplierUserEntity extends BaseEntity {
    private String supplierManageId;
    private String userId;
    private int state;
    private String supplierTypeId;
    private String supplierTypeName;
    //供应商编码
    private String code;
    //供应商名称
    private String name;
    //税号
    private String taxNum;
    //法人
    private String legalPerson;
    private String telePhone;
    //联系人
    private String contact;
    private String supplierState;

    public String getSupplierManageId() {
        return supplierManageId;
    }

    public void setSupplierManageId(String supplierManageId) {
        this.supplierManageId = supplierManageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSupplierTypeId() {
        return supplierTypeId;
    }

    public void setSupplierTypeId(String supplierTypeId) {
        this.supplierTypeId = supplierTypeId;
    }

    public String getSupplierTypeName() {
        return supplierTypeName;
    }

    public void setSupplierTypeName(String supplierTypeName) {
        this.supplierTypeName = supplierTypeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSupplierState() {
        return supplierState;
    }

    public void setSupplierState(String supplierState) {
        this.supplierState = supplierState;
    }
}
