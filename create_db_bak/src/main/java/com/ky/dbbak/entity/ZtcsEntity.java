package com.ky.dbbak.entity;

import com.ky.dbbak.mybatis.BaseEntity;

/**
 * @class: create_db_bak
 * @classDesc: 功能描述（）
 * @author: yaoWieJie
 * @createTime: 2020-04-02 9:33
 * @version: v1.0
 */
public class ZtcsEntity extends BaseEntity {
    private String ztbh;
    private String hsdwdm;

    public String getHsdwdm() {
        return hsdwdm;
    }

    public void setHsdwdm(String hsdwdm) {
        this.hsdwdm = hsdwdm;
    }

    public String getZtbh() {
        return ztbh;
    }

    public void setZtbh(String ztbh) {
        this.ztbh = ztbh;
    }
}