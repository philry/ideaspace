package com.itany.nmms.service.proxy;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.CodeErrorException;
import com.itany.nmms.exception.StaffExistException;
import com.itany.nmms.exception.StaffNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.StaffService;
import com.itany.nmms.tran.TransactionManager;

public class StaffServiceProxy implements StaffService{

	public Staff login(String loginName, String password, String role,
			String code, HttpSession session) throws CodeErrorException, StaffNotExistException{
		StaffService staffService = (StaffService) ObjectFactory.getObject("staffServiceTarget");
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		tran.beginTransaction();
		try {
			Staff staff = staffService.login(loginName, password, role, code, session);
			tran.commit();
			return staff;
		} catch (CodeErrorException e) {
			tran.rollback();
			throw e;
		} catch (StaffNotExistException e) {
			tran.rollback();
			throw e;
		}
		
	}

	public void addStaff(String staffName, String loginName, String password,
			String phone, String email, String role, String deptId,
			Staff currStaff) throws StaffExistException{
		StaffService staffService = (StaffService) ObjectFactory.getObject("staffServiceTarget");
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		tran.beginTransaction();
		try {
			staffService.addStaff(staffName, loginName, password, phone, email, role, deptId, currStaff);
			tran.commit();
		} catch (StaffExistException e) {
			tran.rollback();
			throw e;
		}
		
	}

	public List<Staff> findByParams(String staffName, String loginName,
			String phone, String email, String deptId, String role,
			String isValid) {
		StaffService staffService = (StaffService) ObjectFactory.getObject("staffServiceTarget");
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		tran.beginTransaction();
		List<Staff> staffs = staffService.findByParams(staffName, loginName, phone, email, deptId, role, isValid);
		tran.commit();
		return staffs;
	}

}
