package com.itany.netClass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.DataAccessException;
import com.itany.netClass.exception.ServiceException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.transaction.TransactionManager;
import com.itany.netClass.vo.UserQuery;

public class UserServiceProxy implements UserService {

	private TransactionManager trans = ObjectFactory.getObject("transaction");
	private UserService userServiceTarget = ObjectFactory.getObject("userServiceTarget");

	@Override
	public void add(User user) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			userServiceTarget.add(user);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public User login(User user) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			User login = userServiceTarget.login(user);
			trans.commit();
			return login;
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public void modifyById(User user) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			userServiceTarget.modifyById(user);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public void sign(Integer userId) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			userServiceTarget.sign(userId);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public void modifyById(User user, String newPwd, String rePwd) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			userServiceTarget.modifyById(user, newPwd, rePwd);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public void removeById(Integer id) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法
			userServiceTarget.removeById(id);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
	}

	@Override
	public PageInfo<User> findByCondition(int pageNo, int pageSize,
										  UserQuery query) {
		try {
			trans.beginTransaction();
			//调用serviceImpl的方法,接收返回值,并返回
			userServiceTarget.findByCondition(pageNo, pageSize, query);
			trans.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			trans.rollback();
			throw new ServiceException("服务器繁忙!");
		}
		return null;
	}

}
