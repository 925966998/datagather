package com.ky.newService.entity;

import com.ky.newService.mybatis.BaseEntity;

/**
 * @className: supplierType
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:37
 */
public class SupplierTypeEntity extends BaseEntity {
    private String supplierType;

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }
}
