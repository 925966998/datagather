package com.ky.redwood.entity;

import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;

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
    private BigDecimal price; //单价
    private String userId; //用户id
    public void setMaterialName (String  materialName){
       this.materialName=materialName;
    }

    public  String getMaterialName(){
       return this.materialName;
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
