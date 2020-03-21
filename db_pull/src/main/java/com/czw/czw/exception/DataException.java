
package com.czw.czw.exception;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2015年11月17日 下午4:38:26 
 * @parameter  
 * @return  
 */
public class DataException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6694075865645017971L;

	private int code=-103;
	/**
	 * @return code
	 */
	@Override
	public int getCode() {
		return code;
	}

	/**
	 * 数据异常
	 * @param message
	 */
	public DataException(String message) {
		super(message);
		// TODO 自动生成的构造函数存根
	}

}
