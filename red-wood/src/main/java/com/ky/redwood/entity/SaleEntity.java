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
