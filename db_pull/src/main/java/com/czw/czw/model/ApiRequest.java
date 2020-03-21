
package com.czw.czw.model;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2016年2月26日 下午3:21:12
 * @parameter
 * @return
 */
public class ApiRequest {

	private String method;
	private String token;
	private Object params;

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the params
	 */
	public Object getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(Object params) {
		this.params = params;
	}
}
