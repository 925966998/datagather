package com.ky.newService.entity;


import com.ky.newService.mybatis.BaseEntity;

/**
 * @Classname:com.example.entity
 * @Auther: Lcj
 * @Date: 2020/8/24 08:35
 * @Description: 公司详细信息
 */
public class SupplierManageEntity extends BaseEntity {
    //供应商编码
    private String code;
    //供应商名称
    private String name;
    //供应商类型
    private String supplierTypeId;
    //税号
    private String taxNum;
    //法人
    private String legalPerson;
    //联系电话
    private String phone;
    //手机号
    private String telePhone;
    //建档日期
    private String recordDate;
    //客商标志
    private String supplierMark;
    private String contact;
    private String userId;
    //供应商类型
    private String supplierTypeName;
    private Integer state;
    private Integer qualificationNum;
    private Integer violationNum;
    public String getSupplierMark() {
        return supplierMark;
    }

    public void setSupplierMark(String supplierMark) {
        this.supplierMark = supplierMark;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getSupplierTypeId() {
        return supplierTypeId;
    }

    public void setSupplierTypeId(String supplierTypeId) {
        this.supplierTypeId = supplierTypeId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getQualificationNum() {
        return qualificationNum;
    }

    public void setQualificationNum(Integer qualificationNum) {
        this.qualificationNum = qualificationNum;
    }

    public Integer getViolationNum() {
        return violationNum;
    }

    public void setViolationNum(Integer violationNum) {
        this.violationNum = violationNum;
    }
}
