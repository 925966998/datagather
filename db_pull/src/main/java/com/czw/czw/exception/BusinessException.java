
package com.czw.czw.exception;

/** 
 * 业务异常
 * @author  作者 E-mail: 
 * @date 创建时间：2015年11月17日 下午4:39:15 
 * @parameter  
 * @return  
 */
public class BusinessException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3006390801463411690L;
	private int code=-102;
	/**
	 * @return code
	 */
	@Override
	public  int getCode() {
		return code;
	}
	public BusinessException(String message) {
		super(message);
	}

}
