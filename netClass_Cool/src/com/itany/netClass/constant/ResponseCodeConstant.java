package com.itany.netClass.constant;

public interface ResponseCodeConstant {

	/**
	 * 响应状态为:成功
	 */
	public static final String RESPONSE_CODE_SUCCESS = "10001";
	
	/**
	 * 响应状态为:失败
	 */
	public static final String RESPONSE_CODE_FAIL = "10002";
	
	/**
	 * 响应状态为:请求参数有误
	 */
	public static final String RESPONSE_CODE_REQUEST_PARAMETER_ERROR = "10003";
	
	/**
	 * 响应状态为:登录失效
	 */
	public static final String RESPONSE_CODE_LOGIN_DISABLE = "10004";
	
	/**
	 * 响应状态为:权限不足
	 */
	public static final String RESPONSE_CODE_NO_PROMISSION = "10005";
	
}
