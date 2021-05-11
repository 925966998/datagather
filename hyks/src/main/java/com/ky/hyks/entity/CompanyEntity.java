package com.ky.hyks.entity;

import com.ky.hyks.mybatis.BaseEntity;

/**
 * @className: CompanyEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2021-04-22 09:30
 */
public class CompanyEntity extends BaseEntity {
    /*主键*/
    private String pk_supplier;
    /*编码*/
    private String code;
    /*法人*/
    private String legalbody;
    /*名称*/
    private String name;
    /*简称*/
    private String shortname;
    /*供应商类型  0=外部单位，1=内部单位，*/
    private Integer supprop;
    /*备注*/
    private String memo;
    /*营业执照号码*/
    private String buslicensenum;
    /*纳税人登记号*/
    private String taxpayerid;
    /*地址*/
    private String corpaddress;
    /*启用状态 1=未启用，2=已启用，3=已停用，*/
    private Integer enablestate;
    /*供应商状态 0=潜在，1=核准， ，*/
    private String supstate;
    /*助记码*/
    private String mnecode;
    /*供应商基本分类 */
    private String pk_supplierclass;
    /*供应商税类*/
    private String pk_suptaxes;
    private String cellNum;
    private String tellPhone;
    private String cellName;

    public String getPk_supplier() {
        return pk_supplier;
    }

    public void setPk_supplier(String pk_supplier) {
        this.pk_supplier = pk_supplier;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLegalbody() {
        return legalbody;
    }

    public void setLegalbody(String legalbody) {
        this.legalbody = legalbody;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public Integer getSupprop() {
        return supprop;
    }

    public void setSupprop(Integer supprop) {
        this.supprop = supprop;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBuslicensenum() {
        return buslicensenum;
    }

    public void setBuslicensenum(String buslicensenum) {
        this.buslicensenum = buslicensenum;
    }

    public String getTaxpayerid() {
        return taxpayerid;
    }

    public void setTaxpayerid(String taxpayerid) {
        this.taxpayerid = taxpayerid;
    }

    public String getCorpaddress() {
        return corpaddress;
    }

    public void setCorpaddress(String corpaddress) {
        this.corpaddress = corpaddress;
    }

    public Integer getEnablestate() {
        return enablestate;
    }

    public void setEnablestate(Integer enablestate) {
        this.enablestate = enablestate;
    }

    public String getSupstate() {
        return supstate;
    }

    public void setSupstate(String supstate) {
        this.supstate = supstate;
    }

    public String getMnecode() {
        return mnecode;
    }

    public void setMnecode(String mnecode) {
        this.mnecode = mnecode;
    }

    public String getPk_supplierclass() {
        return pk_supplierclass;
    }

    public void setPk_supplierclass(String pk_supplierclass) {
        this.pk_supplierclass = pk_supplierclass;
    }

    public String getPk_suptaxes() {
        return pk_suptaxes;
    }

    public void setPk_suptaxes(String pk_suptaxes) {
        this.pk_suptaxes = pk_suptaxes;
    }

    public String getCellNum() {
        return cellNum;
    }

    public void setCellNum(String cellNum) {
        this.cellNum = cellNum;
    }

    public String getTellPhone() {
        return tellPhone;
    }

    public void setTellPhone(String tellPhone) {
        this.tellPhone = tellPhone;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }
}
