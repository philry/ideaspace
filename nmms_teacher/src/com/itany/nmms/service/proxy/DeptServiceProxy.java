package com.itany.nmms.service.proxy;

import java.util.List;

import com.itany.nmms.entity.Dept;
import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.DeptException;
import com.itany.nmms.exception.DeptExistException;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.DeptService;
import com.itany.nmms.tran.TransactionManager;

public class DeptServiceProxy implements DeptService{

	public void addFatherDept(String name, String remark, Staff staff) throws DeptExistException {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		DeptService deptService = (DeptService) ObjectFactory.getObject("deptServiceTarget");
		
		tran.beginTransaction();
		try {
			deptService.addFatherDept(name, remark, staff);
			tran.commit();
		} catch (DeptExistException e) {
			tran.rollback();
			throw e;
		}
		
		
	}

	public List<Dept> findAll() {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		DeptService deptService = (DeptService) ObjectFactory.getObject("deptServiceTarget");
		tran.beginTransaction();
		List<Dept> depts = deptService.findAll();
		tran.commit();
		return depts;
	}

	public void addSonDept(String fatherId, String name, String remark,
			Staff staff) throws DeptExistException {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		DeptService deptService = (DeptService) ObjectFactory.getObject("deptServiceTarget");
		
		tran.beginTransaction();
		try {
			deptService.addSonDept(fatherId, name, remark, staff);
			tran.commit();
		} catch (DeptExistException e) {
			tran.rollback();
			throw e;
		}
	}

	public Dept findById(String id) throws ParameterException {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		DeptService deptService = (DeptService) ObjectFactory.getObject("deptServiceTarget");
		tran.beginTransaction();
		try {
			Dept dept = deptService.findById(id);
			tran.commit();
			return dept;
		} catch (ParameterException e) {
			tran.rollback();
			throw e;
		}
	}

	public void modifyDept(String id, String name, String remark) throws DeptExistException{
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		DeptService deptService = (DeptService) ObjectFactory.getObject("deptServiceTarget");
		tran.beginTransaction();
		try {
			deptService.modifyDept(id, name, remark);
			tran.commit();
		} catch (DeptExistException e) {
			tran.rollback();
			throw e;
		}
		
	}

	public void modifyStatus(String id) throws DeptException{
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		DeptService deptService = (DeptService) ObjectFactory.getObject("deptServiceTarget");
		tran.beginTransaction();
		try {
			deptService.modifyStatus(id);
			tran.commit();
		} catch (DeptException e) {
			tran.rollback();
			throw e;
		}
	}

	public List<Dept> findEnable() {
		TransactionManager tran = (TransactionManager) ObjectFactory.getObject("tran");
		DeptService deptService = (DeptService) ObjectFactory.getObject("deptServiceTarget");
		tran.beginTransaction();
		List<Dept> depts = deptService.findEnable();
		tran.commit();
		return depts;
	}

}
