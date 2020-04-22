package com.ky.redwood.entity;

import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;

/**
 * @author linan
 * Create By Generator
 */
public class MaterialOutEntity extends BaseEntity {
    private String processParentId; //
    private String processName; //
    private String materialId; //材料id
    private String materialName; //材料id
    private Integer amount; //数量
    private Integer useAmount; //使用数量
    private Integer status; //是否补料0否1是
    private Integer processStatus; //补料流程状态
    private String userId; //用户id
    private String parentId; //用户id

    private Integer newAmount; //用户id
    private Integer flowStatus; //用户id
    private Integer consumablesIs;
    private BigDecimal price; //单价

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getConsumablesIs() { return consumablesIs; }

    public void setConsumablesIs(Integer consumablesIs) { this.consumablesIs = consumablesIs; }


    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }

    public Integer getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(Integer newAmount) {
        this.newAmount = newAmount;
    }

    public Integer getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }

    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setProcessParentId (String  processParentId){
       this.processParentId=processParentId;
    }

    public  String getProcessParentId(){
       return this.processParentId;
    }
    public void setProcessName (String  processName){
        this.processName=processName;
    }

    public  String getProcessName(){
        return this.processName;
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
    public void setMaterialName (String  materialName){
        this.materialName=materialName;
    }

    public  String getMaterialName(){
        return this.materialName;
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
