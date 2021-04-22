package com.ky.hyks.entity;

import com.ky.hyks.mybatis.BaseEntity;

/**
 * @className: CompanyEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2021-04-22 09:30
 */
public class CompanyEntity extends BaseEntity {

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
