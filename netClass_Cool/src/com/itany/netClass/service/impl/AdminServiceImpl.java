package com.itany.netClass.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.AdminMapper;
import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.entity.Admin;
import com.itany.netClass.exception.AdminIsNotException;
import com.itany.netClass.exception.AdminNotExistException;
import com.itany.netClass.exception.CodeErrorException;
import com.itany.netClass.exception.EmailSameException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.vo.AdminQuery;



public class AdminServiceImpl implements AdminService{
		
	
	private AdminMapper adminMapper = ObjectFactory.getObject("adminMapper");
	
	/**
	 * 管理员登录功能
	 * @throws CodeErrorException 
	 * @throws AdminNotExistException 
	 * @throws AdminIsNotException 
	 * */
	@Override
	public Admin login(String loginname, String password, String code,
			String image) throws CodeErrorException, AdminNotExistException, AdminIsNotException {
			//现判断验证码是否正确 
			if(!image.equals(code)){
				throw new CodeErrorException("验证码错误");
			}
			//通过帐号密码来查找用户是否能查找的到
			List<Admin> admin = adminMapper.login(loginname, password);
			//如果返回的结果为空的话，则抛出帐号或密码错误的异常
			if(admin.isEmpty()){
				throw new AdminNotExistException("帐号或密码错误");
			}
			//       如果不是管理员或者帐号的role为null 不让登录后台
			//System.out.println(admin.get(0));
			if(admin.get(0).getRole()==null||!admin.get(0).getRole().equals(Constant.USER_ROLE_ADMIN)){
				throw new AdminIsNotException("您不是管理员");
			}
		return admin.get(0);
	}

	
	/**
	 * 查找所有用户的service
	 * */
	@Override
	public List<Admin> findAll() {
		List<Admin> admins = adminMapper.findAll();
		return admins;
	}

	/**
	 * 修改用户状态
	 * */
	@Override
	public void modifyStatus(Integer id) {
		List<Admin> admin = adminMapper.selectById(id);
		if(admin.get(0).getStatus()==0){
			adminMapper.modifyStatus("1", id);
		}
		if(admin.get(0).getStatus()==1){
			adminMapper.modifyStatus("0", id);
		}
		//如果用户状态为null 修改状态 但是此条语句貌似没用
 		if(admin.get(0).getStatus()==null){
			adminMapper.modifyStatus("0", id);
		}
		
	}

	/**
	 * 修改用户信息
	 * @throws EmailSameException 
	 * */
	@Override
	public void modifyAdmin(String nickname, String role, String password,
			String email, Integer id) throws EmailSameException {
		//根据id查找出对应的用户
		List<Admin> admin = adminMapper.selectById(id);
		if(admin.get(0).getEmail()==null){
			adminMapper.modifyAdmin(nickname, role, CommonUtil.md5(password), email, id);
		}
		if(admin.get(0).getEmail().equals(email)){
			throw new EmailSameException("用户邮箱不能相同");
		}
		if(admin.get(0).getPassword()==null){
			adminMapper.modifyAdmin(nickname, role, CommonUtil.md5(password), email, id);
		}
		if(admin.get(0).getRole()==null){
			adminMapper.modifyAdmin(nickname, role, CommonUtil.md5(password), email, id);
		}
		if(admin.get(0).getNickname()==null){
			adminMapper.modifyAdmin(nickname, role, CommonUtil.md5(password), email, id);
		}
		adminMapper.modifyAdmin(nickname, role, CommonUtil.md5(password), email, id);
	}

	/**
	 * 模糊查询所有用户
	 * @throws ParseException 
	 * */
	@Override
	public List<Admin> doSerach(String loginname, String nickname,
			String email, String role, String createDate, String removeDate,
			String startDate, String endDate) throws ParseException {
			Date cd = new Date();
			Date rd = new Date();
			Date sd = new Date();
			Date ed = new Date();
			if(createDate!=null&&!"".equals(createDate)){
				cd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createDate+" 00:00:00");
			}else{
				cd = null;
			}
			if(removeDate!=null&&!"".equals(removeDate)){
				rd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(removeDate+" 23:59:59");
			}else{
				rd = null;
			}
			if(startDate!=null&&!"".equals(startDate)){
				sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate+" 00:00:00");
			}else{
				sd = null;
			}
			if(endDate!=null&&!"".equals(endDate)){
				ed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate+" 23:59:59");
			}else {
				ed = null;
			}
			AdminQuery adminQuery = new AdminQuery();
			adminQuery.setLoginname(loginname);
			adminQuery.setNickname(nickname);
			adminQuery.setEmail(email);
			adminQuery.setRole(role);
			adminQuery.setCreateDate(cd);
			adminQuery.setRemoveDate(rd);
			adminQuery.setStartDate(sd);
			adminQuery.setEndDate(ed);
			List<Admin> admins = adminMapper.doSerach(adminQuery);
 		return admins;
	}

}
