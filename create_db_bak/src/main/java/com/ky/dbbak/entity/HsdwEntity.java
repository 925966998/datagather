package com.ky.dbbak.entity;

import com.ky.dbbak.mybatis.BaseEntity;

/**
 * @class: create_db_bak
 * @classDesc: 功能描述（）
 * @author: yaoWieJie
 * @createTime: 2020-04-06 14:29
 * @version: v1.0
 */
public class HsdwEntity extends BaseEntity {
    private String dwdm;
    private String dwmc;

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }
}