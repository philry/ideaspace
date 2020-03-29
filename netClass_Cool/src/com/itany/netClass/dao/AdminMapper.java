package com.itany.netClass.dao;

import java.util.List;

import com.itany.netClass.entity.Admin;
import com.itany.netClass.vo.AdminQuery;

public interface AdminMapper {

	/**
	 * 管理员登录
	 *
	 * */
	public List<Admin> login(String username, String password);

	/**
	 * 查询所有用户
	 *
	 */
	public List<Admin> findAll();

	/**
	 * 根据用户id查找用户
	 *
	 * */
	public List<Admin> selectById(Integer id);

	/**
	 * 修改用户状态
	 *
	 * */
	public void modifyStatus(String status, Integer id);

	/**
	 * 修改用户信息
	 *
	 * */
	public void modifyAdmin(String nickname, String role, String password,
                            String email, Integer id);
	
	/**
	 * 模糊查询用户信息
	 *
	 * */
	public List<Admin> doSerach(AdminQuery adminQuery);
}
