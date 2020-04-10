package com.ky.redwood.entity;

import com.ky.redwood.mybatis.BaseEntity;

/**
 * @author linan
 * Create By Generator
 */
public class MaterialOutEntity extends BaseEntity {
    private String processParentId; //
    private String materialId; //材料id
    private Integer amount; //数量
    private Integer status; //是否补料0否1是
    private Integer processStatus; //补料流程状态
    private String userId; //用户id
    public void setProcessParentId (String  processParentId){
       this.processParentId=processParentId;
    }

    public  String getProcessParentId(){
       return this.processParentId;
    }
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
    public void setStatus (Integer  status){
       this.status=status;
    }

    public  Integer getStatus(){
       return this.status;
    }
    public void setProcessStatus (Integer  processStatus){
       this.processStatus=processStatus;
    }

    public  Integer getProcessStatus(){
       return this.processStatus;
    }
    public void setUserId (String  userId){
       this.userId=userId;
    }

    public  String getUserId(){
       return this.userId;
    }
}
