package com.ky.redwood.entity;

import com.ky.redwood.mybatis.BaseEntity;

/**
 * @author linan
 * Create By Generator
 */
public class ProcessParentEntity extends BaseEntity {
    private String processName; //
    private Integer type; //类型 1 成品0半成品
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
