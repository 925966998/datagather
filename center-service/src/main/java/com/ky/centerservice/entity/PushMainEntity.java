package com.ky.centerservice.entity;

import com.ky.centerservice.mybatis.BaseEntity;

/**
 * @className: PushMainEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2021-01-22 10:12
 */
public class PushMainEntity extends BaseEntity {
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String captialType;
    private String pushAmount;
    private String pushDate;
    private String pushResult;
    private String batchNumber;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCaptialType() {
        return captialType;
    }

    public void setCaptialType(String captialType) {
        this.captialType = captialType;
    }

    public String getPushAmount() {
        return pushAmount;
    }

    public void setPushAmount(String pushAmount) {
        this.pushAmount = pushAmount;
    }

    public String getPushDate() {
        return pushDate;
    }

    public void setPushDate(String pushDate) {
        this.pushDate = pushDate;
    }

    public String getPushResult() {
        return pushResult;
    }

    public void setPushResult(String pushResult) {
        this.pushResult = pushResult;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
}
