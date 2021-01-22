package com.ky.centerservice.entity;

import com.ky.centerservice.mybatis.BaseEntity;

/**
 * @className: PersonDetailEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2021-01-22 09:50
 */
public class PersonDetailEntity extends BaseEntity {
    private String name;
    private String idCardNo;
    private String socialInsuranceNo;
    private String bankService;
    private String bankOpen;
    private String bankNo;
    private String belongArea;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getSocialInsuranceNo() {
        return socialInsuranceNo;
    }

    public void setSocialInsuranceNo(String socialInsuranceNo) {
        this.socialInsuranceNo = socialInsuranceNo;
    }

    public String getBankService() {
        return bankService;
    }

    public void setBankService(String bankService) {
        this.bankService = bankService;
    }

    public String getBankOpen() {
        return bankOpen;
    }

    public void setBankOpen(String bankOpen) {
        this.bankOpen = bankOpen;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }
}
