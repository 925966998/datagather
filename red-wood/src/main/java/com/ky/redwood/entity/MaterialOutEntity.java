package com.ky.redwood.entity;

import com.ky.redwood.mybatis.BaseEntity;

/**
 * @author linan
 * Create By Generator
 */
public class MaterialOutEntity extends BaseEntity {
    private String materialId; //材料id
    private Integer amount; //数量
    private String userId; //用户id
    public void setMaterialId (String  materialId){
       this.materialId=materialId;
    }

    public  String getMaterialId(){
       return this.materialId;
    }
    public void setAmount (Integer  amount){
       this.amount=amount;
    }

    public  Integer getAmount(){
       return this.amount;
    }
    public void setUserId (String  userId){
       this.userId=userId;
    }

    public  String getUserId(){
       return this.userId;
    }
}
