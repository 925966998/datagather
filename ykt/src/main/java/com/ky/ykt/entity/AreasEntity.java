package com.ky.ykt.entity;

import com.ky.ykt.mybatis.BaseEntity;

/**
 * @class: ykt
 * @classDesc: 功能描述（区域划分）
 * @author: yaoWieJie
 * @createTime: 2020-02-27 16:35
 * @version: v1.0
 */
public class AreasEntity extends BaseEntity {

    private String city;
    private String county;
    private String address;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AreasEntity(String city, String county, String address) {
        this.city = city;
        this.county = county;
        this.address = address;
    }

    public AreasEntity() {
    }
}