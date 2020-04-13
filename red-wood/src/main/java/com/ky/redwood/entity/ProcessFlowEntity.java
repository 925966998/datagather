package com.ky.redwood.entity;

import com.ky.redwood.mybatis.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linan
 * Create By Generator
 */
public class ProcessFlowEntity extends BaseEntity {
    private String id; //
    private String processFlowName; //

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getProcessFlowName() {
        return processFlowName;
    }

    public void setProcessFlowName(String processFlowName) {
        this.processFlowName = processFlowName;
    }
}
