package com.ky.redwood.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class StockEntity extends BaseEntity {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productNum;
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date storageTime;

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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
