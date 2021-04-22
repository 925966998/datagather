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
    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}