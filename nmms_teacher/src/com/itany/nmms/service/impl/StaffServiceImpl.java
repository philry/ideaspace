package com.itany.nmms.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.itany.nmms.constant.StatusConstant;
import com.itany.nmms.dao.StaffDao;
import com.itany.nmms.entity.Dept;
import com.itany.nmms.entity.Staff;
import com.itany.nmms.entity.StaffExample;
import com.itany.nmms.exception.CodeErrorException;
import com.itany.nmms.exception.StaffExistException;
import com.itany.nmms.exception.StaffNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.StaffService;
import com.itany.nmms.util.MD5Util;
import com.itany.nmms.util.ParameterUtil;
import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer.Param;

public class StaffServiceImpl implements StaffService {
	
	public Staff login(String loginName, String password, String role,
			String code, HttpSession session) throws CodeErrorException, StaffNotExistException {
		StaffDao staffDao = (StaffDao) ObjectFactory.getObject("staffDao");
		
		//判断验证码是否正确
		String image = (String) session.getAttribute("code");
		if(!image.equals(code)){
			throw new CodeErrorException("验证码不正确");
			
		}
		
		Staff staff = staffDao.selectByLoginNameAndPassword(loginName, MD5Util.md5(password), role, StatusConstant.IS_VALID_TRUE);
		if(staff == null){
			throw new StaffNotExistException("账号或密码错误");
		}
		return staff;
	}

	public void addStaff(String staffName, String loginName, String password,
			String phone, String email, String role, String deptId,Staff currStaff) throws StaffExistException {
		StaffDao staffDao = (StaffDao) ObjectFactory.getObject("staffDao");
		StaffExample example = new StaffExample();
		example.or()
			   .andLoginNameEqualTo(loginName);
		
		List<Staff> selectStaffs = staffDao.selectByExample(example);
		if(!selectStaffs.isEmpty()){
			throw new StaffExistException("该登录账号已经存在");
		}
		
		Staff staff = new Staff();
		staff.setLoginName(loginName);
		staff.setStaffName(staffName);
		staff.setPassword(MD5Util.md5(password));
		staff.setPhone(phone);
		staff.setEmail(email);
		staff.setDeptId(Integer.parseInt(deptId));
		staff.setRole(role);
		staff.setIsValid(StatusConstant.IS_VALID_TRUE);
		staff.setCreateDate(new Date());
		staff.setCreateStaffId(currStaff.getStaffId());
		
		staffDao.insertSelective(staff);	
		
	}

	public List<Staff> findByParams(String staffName,String loginName,String phone,String email,String deptId,String role,String isValid) {
		StaffDao staffDao = (StaffDao) ObjectFactory.getObject("staffDao");
	    Staff staffParam = new Staff();
	    staffParam.setStaffName(ParameterUtil.escape(staffName));
	    staffParam.setLoginName(ParameterUtil.escape(loginName));
	    staffParam.setPhone(ParameterUtil.escape(phone));
	    staffParam.setEmail(ParameterUtil.escape(email));
	    if(!ParameterUtil.isNull(deptId) && !"-1".equals(deptId)){
	    	Dept dept = new Dept();
	    	dept.setDeptId(Integer.parseInt(deptId));
	    	staffParam.setDept(dept);
	    }
	    
	    staffParam.setRole(role);
	    if(!ParameterUtil.isNull(isValid)){
	    	staffParam.setIsValid(Integer.parseInt(isValid));
	    }
	    	
	    List<Staff> staffs = staffDao.selectByParams(staffParam);
		
		return staffs;
	}

}
