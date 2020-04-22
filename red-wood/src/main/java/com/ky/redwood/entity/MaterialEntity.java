package com.ky.redwood.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linan
 * Create By Generator
 */
public class MaterialEntity extends BaseEntity {
    private String materialName; //材料名称
    private String materialSpec; //材料规格
    private String measdoc; //计量单位
    private String materialType; //材料类型
    private Integer amount; //数量
    private Integer useAmount; //数量
    private BigDecimal price; //单价
    private String userId; //用户id
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date buyTime;
    private Integer consumablesIs;

    public Integer getConsumablesIs() { return consumablesIs; }
    public void setConsumablesIs(Integer consumablesIs) { this.consumablesIs = consumablesIs; }

    public Date getBuyTime() {
        return buyTime;
    }
    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public void setMaterialName (String  materialName){
       this.materialName=materialName;
    }
    public  String getMaterialName(){
        return this.materialName;
    }

    public Integer getUseAmount() {
        return useAmount;
    }
    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }


    public void setMaterialSpec (String  materialSpec){
       this.materialSpec=materialSpec;
    }
    public  String getMaterialSpec(){
       return this.materialSpec;
    }

    public void setMeasdoc (String  measdoc){
       this.measdoc=measdoc;
    }
    public  String getMeasdoc(){
       return this.measdoc;
    }

    public void setMaterialType (String  materialType){
       this.materialType=materialType;
    }
    public  String getMaterialType(){
       return this.materialType;
    }

    public void setAmount (Integer  amount){
       this.amount=amount;
    }
    public  Integer getAmount(){
       return this.amount;
    }

    public void setPrice (BigDecimal  price){
       this.price=price;
    }
    public  BigDecimal getPrice(){
       return this.price;
    }

    public void setUserId (String  userId){
       this.userId=userId;
    }
    public  String getUserId(){
       return this.userId;
    }
}
