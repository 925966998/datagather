package com.ky.dbbak.entity;


import com.ky.dbbak.mybatis.BaseEntity;

public class IndustryEntity extends BaseEntity {

    private String industryCode;
    private String industryName;
    private String industryState;
    private String parentID;

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getIndustryState() {
        return industryState;
    }

    public void setIndustryState(String industryState) {
        this.industryState = industryState;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }
}
