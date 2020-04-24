package com.ky.redwood.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class SaleEntity extends BaseEntity {
    private String stockId;
    private BigDecimal goodsPrice;
    private String customName;
    private String productName;
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date sellDate;
    private String invoiceId;  //单据标号
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
    private String remarks; //经手人
    private String processParentId; //出库单据id
    private Integer saleOrNo; //是否卖出
    private String processParentName;//单据名称

    public String getProcessParentName() {
        return processParentName;
    }

    public void setProcessParentName(String processParentName) {
            this.processParentName = processParentName;
    }

    public String getProcessParentId() {
        return processParentId;
    }

    public void setProcessParentId(String processParentId) {
        this.processParentId = processParentId;
    }

    public Integer getSaleOrNo() {
        return saleOrNo;
    }

    public void setSaleOrNo(Integer saleOrNo) {
        this.saleOrNo = saleOrNo;
    }



    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

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

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String productId) {
        this.stockId = productId;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


}
