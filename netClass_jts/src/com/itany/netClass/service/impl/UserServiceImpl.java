package com.itany.netClass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.vo.UserQuery;

import java.util.Date;
import java.util.List;

/**
 * 示例serviceImpl
 * @author teacher
 * @date 2018-8-22
 */
public class UserServiceImpl implements UserService {

	private UserMapper userMapper = ObjectFactory.getObject("userMapper");

	//注册
	@Override
	public void add(User user) {
		//判重
		//loginName/email
		List<User> list = userMapper.selectNoSame(user.getLoginName(),user.getEmail());
		if(!list.isEmpty()){//存在,重复
			throw new RuntimeException("登录名或邮箱重复");
		}

		user.setRole(Constant.USER_ROLE_NORMAL);
		user.setCreateDate(new Date());
		user.setStatus(Constant.STATUS_ENABLE);
		user.setPassword(CommonUtil.md5(user.getPassword()));

		userMapper.insert(user);
	}

	//登录
	@Override
	public User login(User user) {
		List<User> list=userMapper.selectNoSame(user.getLoginName(),user.getEmail());
		//判断登录名是否存在
		if(list.isEmpty()){
			throw new RuntimeException("登录名不存在");
		}
		User u=list.get(0);
		//判断密码是否正确
		if(!u.getPassword().equals(CommonUtil.md5(user.getPassword()))){
			throw new RuntimeException("密码错误");
		}
		//判断该用户是否被禁用
		if(u.getStatus().equals(Constant.STATUS_DISABLE)){
			throw new RuntimeException("该用户不可用");
		}
		return u;
	}

	@Override
	public void modifyById(User user) {
		//数据验证

		userMapper.updateById(user);
	}

	public void modifyById(User user, String newPwd, String rePwd) {
		//旧密码的验证等


		userMapper.updateById(user);
	}


	@Override
	public void removeById(Integer id) {
		//id是否存,null

		User user = new User();
		user.setId(id);
		user.setStatus(Constant.STATUS_DISABLE);
		userMapper.updateById(user);
	}

	@Override
	public PageInfo<User> findByCondition(int pageNo, int pageSize,
										  UserQuery query) {
		PageHelper.startPage(pageNo, pageSize);

		List<User> list = userMapper.selectByCondition(query);

		PageInfo<User> pageInfo = new PageInfo<User>(list);

		return pageInfo;
	}

}
