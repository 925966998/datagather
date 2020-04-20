package com.ky.redwood.entity;

import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linan
 * Create By Generator
 */
public class ProductEntity extends BaseEntity {
    private String processId; //出库单据id
    private Integer productStatus; //物料id
    private String processParentId; //出库单据id
    private String materialId; //物料id
    private String productName; //产品名称
    private Integer flowStatus; //流程状态
    private Integer type; //类型  1成品 0半成品
    private Integer amount; //数量
    private BigDecimal add_fee; //补价
    private BigDecimal fee; //费用
    private String userId; //用户id
    private Date endTime;
    private String processFlowName;
    private String processingPersonnel;
    private String processName;
    private String productParentId;

    public String getProductParentId() { return productParentId; }

    public void setProductParentId(String productParentId) { this.productParentId = productParentId; }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessParentId() {
        return processParentId;
    }

    public void setProcessParentId(String processParentId) {
        this.processParentId = processParentId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getAdd_fee() {
        return add_fee;
    }

    public void setAdd_fee(BigDecimal add_fee) {
        this.add_fee = add_fee;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProcessFlowName() {
        return processFlowName;
    }

    public void setProcessFlowName(String processFlowName) {
        this.processFlowName = processFlowName;
    }

    public String getProcessingPersonnel() {
        return processingPersonnel;
    }

    public void setProcessingPersonnel(String processingPersonnel) {
        this.processingPersonnel = processingPersonnel;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }
}
