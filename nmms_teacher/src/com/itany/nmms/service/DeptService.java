package com.itany.nmms.service;

import java.util.List;

import com.itany.nmms.entity.Dept;
import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.DeptException;
import com.itany.nmms.exception.DeptExistException;
import com.itany.nmms.exception.ParameterException;

public interface DeptService {

	/**
	 * 添加父部门
	 * @param name
	 * @param remark
	 * @throws DeptExistException 
	 */
	public void addFatherDept(String name,String remark,Staff staff) throws DeptExistException;

	/**
	 * 查询所有部门信息以及其父部门的信息
	 * @return
	 */
	public List<Dept> findAll();
	
	/**
	 * 添加子部门
	 * @param fatherId
	 * @param name
	 * @param remark
	 * @param staff
	 * @throws DeptExistException 
	 */
	public void addSonDept(String fatherId,String name,String remark,Staff staff) throws DeptExistException;
	
	/**
	 * 根据主键查询部门信息
	 * @param id
	 * @return
	 * @throws ParameterException 
	 */
	public Dept findById(String id) throws ParameterException;
	
	/**
	 * 修改部门信息
	 * @param id
	 * @param name
	 * @param remark
	 * @throws DeptExistException 
	 */
	public void modifyDept(String id,String name,String remark) throws DeptExistException;
	
	/**
	 * 启用/禁用
	 * 启用时需判断上级部门状态，只有当上级部门为有效时才可启用
	 * 禁用时需同时禁用该部门所有下级部门
	 * @param id
	 * @throws DeptException 
	 */
	public void modifyStatus(String id) throws DeptException;
	
	/**
	 * 查询有效的部门
	 * @return
	 */
	public List<Dept> findEnable();
	
}
