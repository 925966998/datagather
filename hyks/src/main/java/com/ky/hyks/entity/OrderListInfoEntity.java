package com.ky.hyks.entity;

import com.ky.hyks.mybatis.BaseEntity;

import java.math.BigDecimal;

/**
 * @className: OrderListInfoEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2021-04-22 09:30
 */
public class OrderListInfoEntity extends BaseEntity {

    private String orderInfoId;
    private String orderListId;
    private String matterName;
    private String orderListName;
    private String matterType;
    private String matterSpec;
    private BigDecimal nastNum;

    public String getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(String orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(String orderListId) {
        this.orderListId = orderListId;
    }

    public String getMatterName() {
        return matterName;
    }

    public void setMatterName(String matterName) {
        this.matterName = matterName;
    }

    public String getOrderListName() {
        return orderListName;
    }

    public void setOrderListName(String orderListName) {
        this.orderListName = orderListName;
    }

    public String getMatterType() {
        return matterType;
    }

    public void setMatterType(String matterType) {
        this.matterType = matterType;
    }

    public String getMatterSpec() {
        return matterSpec;
    }

    public void setMatterSpec(String matterSpec) {
        this.matterSpec = matterSpec;
    }

    public BigDecimal getNastNum() {
        return nastNum;
    }

    public void setNastNum(BigDecimal nastNum) {
        this.nastNum = nastNum;
    }
}
