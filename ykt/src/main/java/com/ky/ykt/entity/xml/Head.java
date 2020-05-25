package com.ky.ykt.entity.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName Head
 * @Description: TODO
 * @Author czw
 * @Date 2020/5/25
 **/
@XmlRootElement
public class Head {
    private String ID;
    private String UUID;
    private String CallDate;
    private String CallTime;
    private String CallUser;
    private String Token;
    private String District;
    private String BankNo;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getCallDate() {
        return CallDate;
    }

    public void setCallDate(String callDate) {
        CallDate = callDate;
    }

    public String getCallTime() {
        return CallTime;
    }

    public void setCallTime(String callTime) {
        CallTime = callTime;
    }

    public String getCallUser() {
        return CallUser;
    }

    public void setCallUser(String callUser) {
        CallUser = callUser;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getBankNo() {
        return BankNo;
    }

    public void setBankNo(String bankNo) {
        BankNo = bankNo;
    }
}
