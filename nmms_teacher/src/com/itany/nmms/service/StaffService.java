package com.itany.nmms.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.CodeErrorException;
import com.itany.nmms.exception.StaffExistException;
import com.itany.nmms.exception.StaffNotExistException;

public interface StaffService {

	/**
	 * 员工登录
	 * @param loginName
	 * @param password
	 * @param role
	 * @param code
	 * @param session
	 * @return
	 * @throws CodeErrorException
	 * @throws StaffNotExistException
	 */
	public Staff login(String loginName,String password,String role,String code,HttpSession session) throws CodeErrorException, StaffNotExistException;
	
	/**
	 * 添加员工信息
	 * 登录账号不能重复
	 * @param staffName
	 * @param loginName
	 * @param password
	 * @param phone
	 * @param email
	 * @param role
	 * @param deptId
	 * @throws StaffExistException 
	 */
	public void addStaff(String staffName,String loginName,String password,String phone,String email,String role,String deptId,Staff currStaff) throws StaffExistException; 
	
	/**
	 * 根据条件查询符合条件的员工信息
	 * @param staffName
	 * @param loginName
	 * @param phone
	 * @param email
	 * @param deptId
	 * @param role
	 * @param isValid
	 * @return
	 */
	public List<Staff> findByParams(String staffName,String loginName,String phone,String email,String deptId,String role,String isValid);
}
