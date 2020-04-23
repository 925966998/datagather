package com.ky.redwood.entity;

import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;

public class CostSharingEntity extends BaseEntity {

    private String materialOutId;
    private BigDecimal jgFee;
    private BigDecimal jwlFee;
    private BigDecimal sdFee;
    private BigDecimal fzFee;
    private BigDecimal xlFee;
    private BigDecimal qtFee;
    private String productName;
    private String productTime;
    private String startTime;
    private String endTime;
    private BigDecimal ftFee;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMaterialOutId() {
        return materialOutId;
    }

    public void setMaterialOutId(String materialOutId) {
        this.materialOutId = materialOutId;
    }

    public BigDecimal getJgFee() {
        return jgFee;
    }

    public void setJgFee(BigDecimal jgFee) {
        this.jgFee = jgFee;
    }

    public BigDecimal getJwlFee() {
        return jwlFee;
    }

    public void setJwlFee(BigDecimal jwlFee) {
        this.jwlFee = jwlFee;
    }

    public BigDecimal getSdFee() {
        return sdFee;
    }

    public void setSdFee(BigDecimal sdFee) {
        this.sdFee = sdFee;
    }

    public BigDecimal getFzFee() {
        return fzFee;
    }

    public void setFzFee(BigDecimal fzFee) {
        this.fzFee = fzFee;
    }

    public BigDecimal getXlFee() {
        return xlFee;
    }

    public void setXlFee(BigDecimal xlFee) {
        this.xlFee = xlFee;
    }

    public BigDecimal getQtFee() {
        return qtFee;
    }

    public void setQtFee(BigDecimal qtFee) {
        this.qtFee = qtFee;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
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

    public BigDecimal getFtFee() {
        return ftFee;
    }

    public void setFtFee(BigDecimal ftFee) {
        this.ftFee = ftFee;
    }
}
