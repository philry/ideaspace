package com.itany.netClass.service.proxy;

import java.text.ParseException;
import java.util.List;

import com.itany.netClass.entity.Admin;
import com.itany.netClass.exception.AdminIsNotException;
import com.itany.netClass.exception.AdminNotExistException;
import com.itany.netClass.exception.CodeErrorException;
import com.itany.netClass.exception.EmailSameException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.transaction.TransactionManager;

public class AdminServiceProxy implements AdminService {

	private TransactionManager trans = ObjectFactory.getObject("transaction");
	private AdminService adminServiceTarget = ObjectFactory.getObject("adminServiceTarget");

	// 登录代理类
	@Override
	public Admin login(String loginname, String password, String code,
			String image) throws CodeErrorException, AdminNotExistException,
			AdminIsNotException {
		try {
			trans.beginTransaction();
			Admin admin = adminServiceTarget.login(loginname, password, code,
					image);
			trans.commit();
			return admin;
		} catch (CodeErrorException e) {
			trans.rollback();
			throw e;
		} catch (AdminNotExistException e) {
			trans.rollback();
			throw e;
		} catch (AdminIsNotException e) {
			trans.rollback();
			throw e;
		}
	}

	/**
	 * 查找所有用户的事务
	 * */
	@Override
	public List<Admin> findAll() {
		trans.beginTransaction();
		List<Admin> admins = adminServiceTarget.findAll();
		trans.commit();
		return admins;
	}

	/**
	 * 修改用户状态
	 * */
	@Override
	public void modifyStatus(Integer id) {
		trans.beginTransaction();
		adminServiceTarget.modifyStatus(id);
		trans.commit();
	}

	/**
	 * 修改用户信息
	 * 
	 * @throws EmailSameException
	 * */
	@Override
	public void modifyAdmin(String nickname, String role, String password,
			String email, Integer id) throws EmailSameException {
		try {
			trans.beginTransaction();
			adminServiceTarget.modifyAdmin(nickname, role, password, email, id);
			trans.commit();
		} catch (EmailSameException e) {
			trans.rollback();
			throw e;
		}
	}

	/**
	 * 模糊查询
	 * 
	 * @throws ParseException
	 * */
	@Override
	public List<Admin> doSerach(String loginname, String nickname,
			String email, String role, String createDate, String removeDate,
			String startDate, String endDate) throws ParseException {
		trans.beginTransaction();
		try {
			List<Admin> admins;
			admins = adminServiceTarget.doSerach(loginname, nickname, email,
					role, createDate, removeDate, startDate, endDate);
			trans.commit();
			return admins;
		} catch (ParseException e) {
			trans.rollback();
			throw e;
		}
	}

}
