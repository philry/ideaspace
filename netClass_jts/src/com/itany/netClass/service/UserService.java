package com.itany.netClass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.User;
import com.itany.netClass.vo.UserQuery;

/**
 * 示例service接口
 * @author teacher
 * @date 2018-8-22
 */
public interface UserService {
	//注册
	public void add(User user);

	//登录
	public User login(User user);

	public void modifyById(User user);
	/**
	 * 前台首页用户自己的修改,需要验证旧密码
	 * @author teacher
	 * @date 2018-8-22
	 * @param user
	 * @param newPwd
	 * @param rePwd
	 */
	public void modifyById(User user, String newPwd, String rePwd);
	/**
	 * 做状态的修改
	 * @author teacher
	 * @date 2018-8-22
	 * @param id
	 */
	public void removeById(Integer id);

	public PageInfo<User> findByCondition(int pageNo, int pageSize, UserQuery query);
}
