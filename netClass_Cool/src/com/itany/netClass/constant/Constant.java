package com.itany.netClass.constant;

/**
 * 常量接口
 * @author teacher
 * @date 2018-8-22
 */
public interface Constant {
	/**
	 * 启用状态
	 */
	int STATUS_ENABLE = 0;
	/**
	 * 禁用状态
	 */
	int STATUS_DISABLE = 1;
	/**
	 * 积分
	 */
	int COST_TYPE_SCORE = 0;
	/**
	 * 金币
	 */
	int COST_TYPE_GOLD = 1;
	/**
	 * 课程推荐等级(0普通)
	 */
	int COURSE_RECOMMEND_LV0 = 0;
	/**
	 * 课程推荐等级(1推荐)
	 */
	int COURSE_RECOMMEND_LV1 = 1;
	/**
	 * 评论状态-待审核-2
	 */
	int COMMENT_STATUS_WAITING = 2;
	/**
	 * 用户角色admin
	 */
	String USER_ROLE_ADMIN = "admin";
	/**
	 * 用户角色normal
	 */
	String USER_ROLE_NORMAL = "normal";
	
	/**
	 * 过滤器默认字符集
	 */
	String FILTER_CHARSET_UTF8 = "UTF-8";
	
	/**
	 * 获取文件上传路径的前缀
	 */
	String UPLOAD_PATH_PREFIX = "environment";
	/**
	 * 获取文件上传路径的后缀
	 */
	String UPLOAD_PATH_SUFFIX = ".path";
	/**
	 * 文件上传路径
	 */
	String FILE_PATH = "/images/";
	/**
	 * 分页
	 */
	int PAGE_SIZE = 2;
	/**
	 * 初始页码
	 */
	int PAGE_NO = 1;
	/**
	 * 点击量初始值
	 */
	int CLICK_NUMBER_INIT = 0;
	/**
	 * select框初始值
	 */
	String SELECT_INIT_NUM = "-1";
	String BACKEND_PAGE_NO_DEFAULT = "1";

	String BACKEND_PAGE_SIZE_DEFAULT = "2";
}

