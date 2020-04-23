package com.ky.redwood.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class StockEntity extends BaseEntity {
    private String productId;
    private String productName;
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date storageTime;
    private String goodsSpecs; //规格
    private String goodsModel; //型号
    private String goodsUnit; //单位u
    private Integer goodsNum; //数量
    private BigDecimal unitPrice; //单价
    private BigDecimal sumPrice; //金额
    private String manager; //主管
    private String accountant; //会计
    private String curator; //保管员
    private String operator; //经手人
    private String supplier;
    private String remarks; //经手人

    public String getGoodsSpecs() {
        return goodsSpecs;
    }

    public void setGoodsSpecs(String goodsSpecs) {
        this.goodsSpecs = goodsSpecs;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAccountant() {
        return accountant;
    }

    public void setAccountant(String accountant) {
        this.accountant = accountant;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }




    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }



    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
}
