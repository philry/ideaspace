package com.itany.netClass.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.vo.UserQuery;
/**
 * 示例serviceImpl
 * @author teacher
 * @date 2018-8-22
 */
public class UserServiceImpl implements UserService {

	private UserMapper userMapper = ObjectFactory.getObject("userMapper");
	
	@Override
	public void add(User user) {
		//数据验证
		//loginName
		UserQuery query = new UserQuery();
		query.setLoginName(user.getLoginName());
		List<User> list = userMapper.selectByCondition(query);
		if(!list.isEmpty()){//存在,重复
			throw new RuntimeException("登录名重复");
		}

		userMapper.insert(user);
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
