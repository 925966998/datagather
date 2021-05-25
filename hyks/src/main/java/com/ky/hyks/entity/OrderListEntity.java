package com.ky.hyks.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ky.hyks.mybatis.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @className: OrderListEntity
 * @description: TODO
 * @author: Lcj
 * @date: 2021-05-18 09:30
 */
public class OrderListEntity extends BaseEntity {

    private String listName;
    private String userName;
    private String userCell;
    private Integer talkNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private Integer state;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCell() {
        return userCell;
    }

    public void setUserCell(String userCell) {
        this.userCell = userCell;
    }

    public Integer getTalkNum() {
        return talkNum;
    }

    public void setTalkNum(Integer talkNum) {
        this.talkNum = talkNum;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
