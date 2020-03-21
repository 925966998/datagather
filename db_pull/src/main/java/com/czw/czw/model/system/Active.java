package com.czw.czw.model.system;

import java.util.Date;

public class Active {
	/**
	 * 
	 * 类名称：Active.java 类描述：
	 * 
	 * @author 曹泽旺 作者单位： 联系方式： 创建时间：2015年8月27日
	 * @version 1.0
	 */
	private String ACTIVITY_ID; // 活动id
	private String ACTIVITY_NAME; // 活动名称
	private Date START_TIME; // 开始时间
	private Date END_TIME;// 结束时间
	private Date CONSUME_START_TIME; // 消费开始时间
	private Date CONSUME_END_TIME; // 消费结束时间
	private int NEED_INTEGRAL;// 需要积分
	private double PRICE;// 价格
	private String ACTIVITY_DES;// 简介
	private String ACTIVITY_PIC;// 活动主图
	private String ACTIVITY_IMG;// 活动图片
	private int SALE_COUNT;// 兑换量
	private int ACTIVITY_TYPE;// 活动类型
	private int ACTIVITY_STATE;// 活动状态
	private Date CREATE_DATE;// 创建时间
	private Date MODIFY_DATE;// 修改日期
	private String MODIFY_USER;// 修改人
	private String CREATE_USER;// 创建人
	public String getACTIVITY_ID() {
		return ACTIVITY_ID;
	}
	public void setACTIVITY_ID(String aCTIVITY_ID) {
		ACTIVITY_ID = aCTIVITY_ID;
	}
	public String getACTIVITY_NAME() {
		return ACTIVITY_NAME;
	}
	public void setACTIVITY_NAME(String aCTIVITY_NAME) {
		ACTIVITY_NAME = aCTIVITY_NAME;
	}
	public Date getSTART_TIME() {
		return START_TIME;
	}
	public void setSTART_TIME(Date sTART_TIME) {
		START_TIME = sTART_TIME;
	}
	public Date getEND_TIME() {
		return END_TIME;
	}
	public void setEND_TIME(Date eND_TIME) {
		END_TIME = eND_TIME;
	}
	public Date getCONSUME_START_TIME() {
		return CONSUME_START_TIME;
	}
	public void setCONSUME_START_TIME(Date cONSUME_START_TIME) {
		CONSUME_START_TIME = cONSUME_START_TIME;
	}
	public Date getCONSUME_END_TIME() {
		return CONSUME_END_TIME;
	}
	public void setCONSUME_END_TIME(Date cONSUME_END_TIME) {
		CONSUME_END_TIME = cONSUME_END_TIME;
	}
	public int getNEED_INTEGRAL() {
		return NEED_INTEGRAL;
	}
	public void setNEED_INTEGRAL(int nEED_INTEGRAL) {
		NEED_INTEGRAL = nEED_INTEGRAL;
	}
	public double getPRICE() {
		return PRICE;
	}
	public void setPRICE(double pRICE) {
		PRICE = pRICE;
	}
	public String getACTIVITY_DES() {
		return ACTIVITY_DES;
	}
	public void setACTIVITY_DES(String aCTIVITY_DES) {
		ACTIVITY_DES = aCTIVITY_DES;
	}
	public String getACTIVITY_PIC() {
		return ACTIVITY_PIC;
	}
	public void setACTIVITY_PIC(String aCTIVITY_PIC) {
		ACTIVITY_PIC = aCTIVITY_PIC;
	}
	public String getACTIVITY_IMG() {
		return ACTIVITY_IMG;
	}
	public void setACTIVITY_IMG(String aCTIVITY_IMG) {
		ACTIVITY_IMG = aCTIVITY_IMG;
	}
	public int getSALE_COUNT() {
		return SALE_COUNT;
	}
	public void setSALE_COUNT(int sALE_COUNT) {
		SALE_COUNT = sALE_COUNT;
	}
	public int getACTIVITY_TYPE() {
		return ACTIVITY_TYPE;
	}
	public void setACTIVITY_TYPE(int aCTIVITY_TYPE) {
		ACTIVITY_TYPE = aCTIVITY_TYPE;
	}
	public int getACTIVITY_STATE() {
		return ACTIVITY_STATE;
	}
	public void setACTIVITY_STATE(int aCTIVITY_STATE) {
		ACTIVITY_STATE = aCTIVITY_STATE;
	}
	public Date getCREATE_DATE() {
		return CREATE_DATE;
	}
	public void setCREATE_DATE(Date cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}
	public Date getMODIFY_DATE() {
		return MODIFY_DATE;
	}
	public void setMODIFY_DATE(Date mODIFY_DATE) {
		MODIFY_DATE = mODIFY_DATE;
	}
	public String getMODIFY_USER() {
		return MODIFY_USER;
	}
	public void setMODIFY_USER(String mODIFY_USER) {
		MODIFY_USER = mODIFY_USER;
	}
	public String getCREATE_USER() {
		return CREATE_USER;
	}
	public void setCREATE_USER(String cREATE_USER) {
		CREATE_USER = cREATE_USER;
	}

	

}
