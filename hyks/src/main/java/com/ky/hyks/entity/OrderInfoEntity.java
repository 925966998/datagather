package com.ky.hyks.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ky.hyks.mybatis.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @className: OrderInfoEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2021-04-22 09:30
 */
public class OrderInfoEntity extends BaseEntity {

    private String orderNum;
    private String name;
    private Integer state;
    private BigDecimal totalAmount;
    private BigDecimal askAmount;
    private BigDecimal haveAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private String unit;
    private String specs;
    private String supplierId;
    private String orderType;
    private String oddNum;
    private String orderOrg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date needTime;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAskAmount() {
        return askAmount;
    }

    public void setAskAmount(BigDecimal askAmount) {
        this.askAmount = askAmount;
    }

    public BigDecimal getHaveAmount() {
        return haveAmount;
    }

    public void setHaveAmount(BigDecimal haveAmount) {
        this.haveAmount = haveAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOddNum() {
        return oddNum;
    }

    public void setOddNum(String oddNum) {
        this.oddNum = oddNum;
    }

    public String getOrderOrg() {
        return orderOrg;
    }

    public void setOrderOrg(String orderOrg) {
        this.orderOrg = orderOrg;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Date needTime) {
        this.needTime = needTime;
    }
}
