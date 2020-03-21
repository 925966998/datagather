
package com.czw.czw.exception;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2016年3月18日 下午5:02:53 
* @parameter  
* @return  
*/
public class NoLoginException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -669652256397299008L;

	private int code=-100;
	/**
	 * @return code
	 */
	@Override
	public int getCode() {
		return code;
	}
	
	/**
	 * 权限异常
	 * @param message
	 */
	public NoLoginException(String message) {
		super(message);
	}
}
