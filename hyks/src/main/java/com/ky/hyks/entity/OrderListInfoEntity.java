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
    private String orderInfoName;
    private String orderListName;

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

    public String getOrderInfoName() {
        return orderInfoName;
    }

    public void setOrderInfoName(String orderInfoName) {
        this.orderInfoName = orderInfoName;
    }

    public String getOrderListName() {
        return orderListName;
    }

    public void setOrderListName(String orderListName) {
        this.orderListName = orderListName;
    }
}
