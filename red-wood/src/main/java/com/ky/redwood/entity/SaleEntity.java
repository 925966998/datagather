package com.ky.redwood.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class SaleEntity extends BaseEntity {
    private String productId;
    private BigDecimal goodsPrice;
    private String customName;

    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date sellDate;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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




}
