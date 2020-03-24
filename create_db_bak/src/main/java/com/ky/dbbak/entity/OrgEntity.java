package com.ky.dbbak.entity;


import com.ky.dbbak.mybatis.BaseEntity;

public class OrgEntity extends BaseEntity {

    private String provinceId;
    private String cityId;
    private String areaId;
    private String orgLevel;

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    private String orgCode;
    private String orgName;
    private String areaCode;
    private String pid;

    //as
    private String areaName;

    private String areaLevel;

    private String zt;
    private String ztlx;
    private String kjnd;

    public String getZtlx() {
        return ztlx;
    }

    public void setZtlx(String ztlx) {
        this.ztlx = ztlx;
    }

    private String zzjgdm;
    private String dwxz;
    private String hyfl;
    private String kfdw;
    private String bbh;
    private String bwb;
    private String sfhyysz;
    //czw jia
    private String sjdm;
    private Integer dmjc;
    private String sfmj;
    private String xzjb;
    private String ysglfs;
    private String dwlb;
    private String zgksdm;


    public String getSjdm() {
        return sjdm;
    }

    public void setSjdm(String sjdm) {
        this.sjdm = sjdm;
    }

    public Integer getDmjc() {
        return dmjc;
    }

    public void setDmjc(Integer dmjc) {
        this.dmjc = dmjc;
    }

    public String getSfmj() {
        return sfmj;
    }

    public void setSfmj(String sfmj) {
        this.sfmj = sfmj;
    }

    public String getXzjb() {
        return xzjb;
    }

    public void setXzjb(String xzjb) {
        this.xzjb = xzjb;
    }

    public String getYsglfs() {
        return ysglfs;
    }

    public void setYsglfs(String ysglfs) {
        this.ysglfs = ysglfs;
    }

    public String getDwlb() {
        return dwlb;
    }

    public void setDwlb(String dwlb) {
        this.dwlb = dwlb;
    }

    public String getZgksdm() {
        return zgksdm;
    }

    public void setZgksdm(String zgksdm) {
        this.zgksdm = zgksdm;
    }

    public String getKjnd() {
        return kjnd;
    }

    public void setKjnd(String kjnd) {
        this.kjnd = kjnd;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }


    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    public String getDwxz() {
        return dwxz;
    }

    public void setDwxz(String dwxz) {
        this.dwxz = dwxz;
    }

    public String getHyfl() {
        return hyfl;
    }

    public void setHyfl(String hyfl) {
        this.hyfl = hyfl;
    }

    public String getKfdw() {
        return kfdw;
    }

    public void setKfdw(String kfdw) {
        this.kfdw = kfdw;
    }

    public String getBbh() {
        return bbh;
    }

    public void setBbh(String bbh) {
        this.bbh = bbh;
    }

    public String getBwb() {
        return bwb;
    }

    public void setBwb(String bwb) {
        this.bwb = bwb;
    }


    public String getSfhyysz() {
        return sfhyysz;
    }

    public void setSfhyysz(String sfhyysz) {
        this.sfhyysz = sfhyysz;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
