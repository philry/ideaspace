package com.itany.netClass.constant;

public interface ResponseCodeConstant {/**
 * 响应状态码:成功
 */
public static final  String RESPONSE_STATE_SUCCESS="0";
    /**
     * 响应状态码:失败
     */
    public static final  String RESPONSE_STATE_FAIL="1";
    /**
     * 请求参数有误
     */
    public static final  String RESPONSE_CODE_REQUEST_PARAMETER_ERROR="2";
    /**
     * 登录失败
     */
    public static final  String RESPONSE_CODE_LOGIN_DISABLE="3";
    /**
     * 权限不足
     */
    public static final  String RESPONSE_CODE_NO_PROMISSION="4";
}
