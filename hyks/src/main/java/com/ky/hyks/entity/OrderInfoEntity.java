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
    private String orderType;
    private String oddNum;
    private String orderOrg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date needTime;
    private String matterType;
    private String pk_order;

    private String code;
    private String matterSpec;
    private String matterName;
    private String marbasClassCode;
    private String marbasClassName;
    private String nastNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dbillDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String pk_group;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMatterSpec() {
        return matterSpec;
    }

    public void setMatterSpec(String matterSpec) {
        this.matterSpec = matterSpec;
    }

    public String getMatterName() {
        return matterName;
    }

    public void setMatterName(String matterName) {
        this.matterName = matterName;
    }

    public String getMarbasClassCode() {
        return marbasClassCode;
    }

    public void setMarbasClassCode(String marbasClassCode) {
        this.marbasClassCode = marbasClassCode;
    }

    public String getMarbasClassName() {
        return marbasClassName;
    }

    public void setMarbasClassName(String marbasClassName) {
        this.marbasClassName = marbasClassName;
    }

    public String getNastNum() {
        return nastNum;
    }

    public void setNastNum(String nastNum) {
        this.nastNum = nastNum;
    }

    public Date getDbillDate() {
        return dbillDate;
    }

    public void setDbillDate(Date dbillDate) {
        this.dbillDate = dbillDate;
    }

    public String getPk_group() {
        return pk_group;
    }

    public void setPk_group(String pk_group) {
        this.pk_group = pk_group;
    }

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

    public String getMatterType() {
        return matterType;
    }

    public void setMatterType(String matterType) {
        this.matterType = matterType;
    }

    public String getPk_order() {
        return pk_order;
    }

    public void setPk_order(String pk_order) {
        this.pk_order = pk_order;
    }
}
