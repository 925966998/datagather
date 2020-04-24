package com.ky.redwood.entity;

import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;

/**
 * @author linan
 * Create By Generator
 */
public class MaterialOutEntity extends BaseEntity {
    private String goodsId; //
    private String productName; //
    private String materialId; //材料id
    private String materialName; //材料id
    private BigDecimal amount; //数量
    private BigDecimal useAmount; //使用数量
    private Integer status; //是否补料0否1是
    private Integer processStatus; //补料流程状态
    private String userId; //用户id
    private String parentId; //用户id
    private BigDecimal newAmount; //用户id
    private Integer flowStatus; //用户id
    private Integer consumablesIs;
    private BigDecimal price; //单价
    private Integer goodsAmount;

    public String getAllName() {
        return allName;
    }

    public void setAllName(String allName) {
        this.allName = allName;
    }

    private String allName; //材料id

    public Integer getGoodsAmount() {
        return goodsAmount;
    }
    public void setGoodsAmount(Integer goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

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

    public BigDecimal getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(BigDecimal newAmount) {
        this.newAmount = newAmount;
    }

    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setGoodsId (String  goodsId){
       this.goodsId=goodsId;
    }

    public  String getGoodsId(){
       return this.goodsId;
    }
    public void setProductName (String  productName){
        this.productName=productName;
    }

    public  String getProductName(){
        return this.productName;
    }
    public void setMaterialId (String  materialId){
       this.materialId=materialId;
    }

    public  String getMaterialId(){
       return this.materialId;
    }

    public void setMaterialName (String  materialName){
        this.materialName=materialName;
    }

    public  String getMaterialName(){
        return this.materialName;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
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
