package com.itany.netClass.service;

import java.text.ParseException;
import java.util.List;

import com.itany.netClass.entity.Admin;
import com.itany.netClass.exception.AdminIsNotException;
import com.itany.netClass.exception.AdminNotExistException;
import com.itany.netClass.exception.CodeErrorException;
import com.itany.netClass.exception.EmailSameException;

	/**
	 * 管理员登录系统 
	 * */
	public interface AdminService {

	/**
	 * 管理员登录功能
	 * */
	public Admin login(String loginname, String password, String code, String image) throws CodeErrorException, AdminNotExistException, AdminIsNotException;
	
	/**
	 * 查询所有用户功能
	 * */
	public List<Admin> findAll();
	
	/**
	 * 修改用户状态
	 * */
	public void modifyStatus(Integer id);
	/**
	 * 修改用户信息
	 * @throws EmailSameException 
	 * */
	public void modifyAdmin(String nickname, String role, String password,
                            String email, Integer id) throws EmailSameException;
	/**
	 * 模糊查询用户信息
	 * @throws ParseException 
	 * */
	public List<Admin> doSerach(String loginname, String nickname, String email, String role, String createDate, String removeDate,
                                String startDate, String endDate) throws ParseException;
}
