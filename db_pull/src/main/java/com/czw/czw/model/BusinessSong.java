package com.czw.czw.model;

import java.io.Serializable;

public class BusinessSong implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer isOpen;
    private Integer isSpell;
    private Integer songSpellNum;
    private String songMuch;
    private String startTime;
    private String endTime;
    private String limitTime;
    private Integer limitNum;
    private Integer limitUserNum;
    private Integer isImage;
    private Integer imageTime;
    private Integer songRepeat;
    private String songTime;
    private String divideInto;
    private Integer isGoodSong;
    private Integer isGoodSonger;
    private String sysDivideInto;
    private Integer businessId;

    public Integer getImageTime() {
        return imageTime;
    }

    public void setImageTime(Integer imageTime) {
        this.imageTime = imageTime;
    }

    public Integer getSongSpellNum() {
        return songSpellNum;
    }

    public void setSongSpellNum(Integer songSpellNum) {
        this.songSpellNum = songSpellNum;
    }

    public String getSongMuch() {
        return songMuch;
    }

    public void setSongMuch(String songMuch) {
        this.songMuch = songMuch;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getIsSpell() {
        return isSpell;
    }

    public void setIsSpell(Integer isSpell) {
        this.isSpell = isSpell;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Integer getLimitUserNum() {
        return limitUserNum;
    }

    public void setLimitUserNum(Integer limitUserNum) {
        this.limitUserNum = limitUserNum;
    }

    public Integer getIsImage() {
        return isImage;
    }

    public void setIsImage(Integer isImage) {
        this.isImage = isImage;
    }

    public Integer getSongRepeat() {
        return songRepeat;
    }

    public void setSongRepeat(Integer songRepeat) {
        this.songRepeat = songRepeat;
    }

    public String getSongTime() {
        return songTime;
    }

    public void setSongTime(String songTime) {
        this.songTime = songTime;
    }

    public String getDivideInto() {
        return divideInto;
    }

    public void setDivideInto(String divideInto) {
        this.divideInto = divideInto;
    }

    public Integer getIsGoodSong() {
        return isGoodSong;
    }

    public void setIsGoodSong(Integer isGoodSong) {
        this.isGoodSong = isGoodSong;
    }

    public Integer getIsGoodSonger() {
        return isGoodSonger;
    }

    public void setIsGoodSonger(Integer isGoodSonger) {
        this.isGoodSonger = isGoodSonger;
    }

    public String getSysDivideInto() {
        return sysDivideInto;
    }

    public void setSysDivideInto(String sysDivideInto) {
        this.sysDivideInto = sysDivideInto;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

}