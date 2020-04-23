package com.ky.redwood.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ky.redwood.mybatis.BaseEntity;

import java.util.Date;

/**
 * @author linan
 * Create By Generator
 */
public class ProcessParentEntity extends BaseEntity {
    private String processName; //
    private Integer type; //类型 1 成品0半成品
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date billDate;

    private String remarks;


    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public void setProcessName (String  processName){
       this.processName=processName;
    }

    public  String getProcessName(){
       return this.processName;
    }
    public void setType (Integer  type){
       this.type=type;
    }

    public  Integer getType(){
       return this.type;
    }
}
