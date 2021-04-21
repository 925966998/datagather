package com.ky.newService.entity;

import com.ky.newService.mybatis.BaseEntity;

import java.math.BigDecimal;

/**
 * @Classname:com.example.entity
 * @Auther: ywj
 * @Date: 2020/8/24 16:09
 * @Description: 工程项目
 */
public class ProjectEntity extends BaseEntity {
    //公司编号
    private String companyId;
    //父Id
    private String parentId;
    //工程项目名称
    private String projectName;
    //操作人
    private String userId;
    //开工日期
    private String startTime;
    //投产日期
    private String comnTime;
    //总投资
    private BigDecimal totalAmount;
    //已投资
    private BigDecimal hasAmount;
    //预测期投资
    private BigDecimal forecastSumAmount;
    //均价总投资
    private BigDecimal averageSumAmount;
    //预测算期投入
    private BigDecimal forecastAmount;
    //均价算期投入
    private BigDecimal averageAmount;

    public BigDecimal getForecastAmount() {
        return forecastAmount;
    }

    public void setForecastAmount(BigDecimal forecastAmount) {
        this.forecastAmount = forecastAmount;
    }

    public BigDecimal getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(BigDecimal averageAmount) {
        this.averageAmount = averageAmount;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getComnTime() {
        return comnTime;
    }

    public void setComnTime(String comnTime) {
        this.comnTime = comnTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getHasAmount() {
        return hasAmount;
    }

    public void setHasAmount(BigDecimal hasAmount) {
        this.hasAmount = hasAmount;
    }

    public BigDecimal getForecastSumAmount() {
        return forecastSumAmount;
    }

    public void setForecastSumAmount(BigDecimal forecastSumAmount) {
        this.forecastSumAmount = forecastSumAmount;
    }

    public BigDecimal getAverageSumAmount() {
        return averageSumAmount;
    }

    public void setAverageSumAmount(BigDecimal averageSumAmount) {
        this.averageSumAmount = averageSumAmount;
    }
}
