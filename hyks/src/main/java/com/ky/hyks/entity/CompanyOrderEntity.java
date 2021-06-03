package com.ky.hyks.entity;

import com.ky.hyks.mybatis.BaseEntity;

import java.math.BigDecimal;

/**
 * @className: CompanyOrderEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2021-04-22 09:30
 */
public class CompanyOrderEntity extends BaseEntity {

    private String companyId;
    private String orderId;
    private BigDecimal amount;
    private String companyName;
    private String orderName;
    private Integer state;
    private Integer priceNum;
    private Integer talkNum;
    private BigDecimal price;
    private String listName;
    private String userName;
    private String userCell;
    private BigDecimal nastNum;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPriceNum() {
        return priceNum;
    }

    public void setPriceNum(Integer priceNum) {
        this.priceNum = priceNum;
    }

    public Integer getTalkNum() {
        return talkNum;
    }

    public void setTalkNum(Integer talkNum) {
        this.talkNum = talkNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCell() {
        return userCell;
    }

    public void setUserCell(String userCell) {
        this.userCell = userCell;
    }

    public BigDecimal getNastNum() {
        return nastNum;
    }

    public void setNastNum(BigDecimal nastNum) {
        this.nastNum = nastNum;
    }
}
