package com.czw.czw.exception;

/**
 * 基本异常
 * @author 作者 E-mail:
 * @date 创建时间：2015年11月17日 下午4:35:29
 * @parameter
 * @return
 */
public abstract class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2727000724488561337L;
	public abstract int getCode();
	public BaseException(String message) {
		super(message);
	}
}
