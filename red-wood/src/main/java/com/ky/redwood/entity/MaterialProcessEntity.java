package com.ky.redwood.entity;

import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;

/**
 * @author linan
 * Create By Generator
 */
public class MaterialProcessEntity extends BaseEntity {
    private String materialOutId; //出库单据id
    private Integer processFlow; //1开料2木工定制3机雕4手调5木工组装6刮磨7上蜡
    private String productName; //产品名称
    private Integer productUseAmount; //产品用料数量
    private BigDecimal processFee; //加工费用
    private Integer status; //0未成品1已成品(processFlow=7为已成品)
    private String userId; //用户id
    public void setMaterialOutId (String  materialOutId){
       this.materialOutId=materialOutId;
    }

    public  String getMaterialOutId(){
       return this.materialOutId;
    }
    public void setProcessFlow (Integer  processFlow){
       this.processFlow=processFlow;
    }

    public  Integer getProcessFlow(){
       return this.processFlow;
    }
    public void setProductName (String  productName){
       this.productName=productName;
    }

    public  String getProductName(){
       return this.productName;
    }
    public void setProductUseAmount (Integer  productUseAmount){
       this.productUseAmount=productUseAmount;
    }

    public  Integer getProductUseAmount(){
       return this.productUseAmount;
    }
    public void setProcessFee (BigDecimal  processFee){
       this.processFee=processFee;
    }

    public  BigDecimal getProcessFee(){
       return this.processFee;
    }
    public void setStatus (Integer  status){
       this.status=status;
    }

    public  Integer getStatus(){
       return this.status;
    }
    public void setUserId (String  userId){
       this.userId=userId;
    }

    public  String getUserId(){
       return this.userId;
    }
}
