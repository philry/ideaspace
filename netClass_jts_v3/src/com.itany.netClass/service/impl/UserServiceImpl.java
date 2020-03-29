package com.itany.netClass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.GoldPointMapper;
import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.entity.GoldPoint;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.RequestParameterException;
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
	private GoldPointMapper goldPointMapper=ObjectFactory.getObject("goldPointMapper");


	//注册
	@Override
	public void add(User user) {
		//判重
		// 数据格式验证
		//loginName/email
		if(CommonUtil.isNull(user.getLoginName())){
			throw new RequestParameterException("登录不可为空");
		}
		List<User> list = userMapper.selectNoSame(user.getLoginName(),user.getEmail());
		if(!list.isEmpty()){//存在,重复
			throw new RuntimeException("登录名或邮箱重复");
		}
		String loginName2="[a-zA-Z0-9_]{1,20}";
		if(!user.getLoginName().matches(loginName2)){
			throw new RequestParameterException("登录名格式出错");
		}
		if(CommonUtil.isNull(user.getPassword())||CommonUtil.isNull(user.getRePassword())){
			throw new RequestParameterException("密码不可为空");
		}
		String password2="[a-zA-z0-9]{1,20}";
		if(!user.getPassword().matches(password2)&&!user.getRePassword().matches(password2)){
			throw new RequestParameterException("密码格式出错");
		}
		if(!user.getPassword().equals(user.getRePassword())){
			throw new RequestParameterException("两次密码输入不一致");
		}
		if(CommonUtil.isNull(user.getNickname())){
			throw new RequestParameterException("昵称不可为空");
		}
		String nickename2="[\\S]{1,30}";
		if(!user.getNickname().matches(nickename2)){
			throw new RequestParameterException("昵称格式出错");
		}
		if(CommonUtil.isNull(user.getEmail())){
			throw new RequestParameterException("邮箱不可为空");
		}
		String email2="^[a-zA-Z0-9]{1,10}@[\\w]{2,5}((.com)|(.cn)|(.com.cn))$";
		if(!user.getEmail().matches(email2)){
			throw new RequestParameterException("邮箱格式出错");
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
		//判断该用户是否被禁用
		if(u.getStatus().equals(Constant.STATUS_DISABLE)){
			throw new RuntimeException("该用户不可用");
		}

		//判断密码是否正确
		if(!u.getPassword().equals(CommonUtil.md5(user.getPassword()))){
			throw new RuntimeException("密码错误");
		}
		return u;
	}

	//修改
	@Override
	public void modifyById(User user) {
		List<User> list1=userMapper.selectNoSame(user.getLoginName(),user.getEmail());
		List<User> list=userMapper.selectById(user.getId());
		User u=list.get(0);
		if(CommonUtil.isNull(user.getPassword())&&CommonUtil.isNull(user.getNewPassword())&&CommonUtil.isNull(user.getRePassword())){
//			throw new RequestParameterException("密码不可为空");
			user.setPassword(u.getPassword());
		}
		else {
			if(!u.getPassword().equals(CommonUtil.md5(user.getPassword()))){
				throw new RuntimeException("原密码错误");
			}
			if(!user.getNewPassword().equals(user.getRePassword())){
				throw new RequestParameterException("两次密码输入不一致");
			}
			user.setPassword(CommonUtil.md5(user.getNewPassword()));
		}

		if(!user.getEmail().equals(u.getEmail())){
			if(!list1.isEmpty()){
				throw new RuntimeException("该邮箱已存在");
			}
		}

		//数据验证
//		System.out.println(user+"gsdd");
		userMapper.updateById(user);
	}

	//签到
	//比较时间，添加签到记录修改登录时间
	@Override
	public void sign(Integer userId) {
		//防止前台用户被后台删除出现异常
		List<User> users = userMapper.selectById(userId);
		if(users.isEmpty()){
			throw new RuntimeException("当前用户不存在");
		}
		User user=users.get(0);

		//获取传过来的user对应的登录时间
		Date loginDate=user.getLoginDate();
		//获取当前时间
		Date nowDate=new Date();
		//loginDate有记录说明签到过，确认当天是否已登录
		if(loginDate!=null){
			//防止数据库内容时间大于当前时间，造成逻辑错误
			if(nowDate.getTime()<loginDate.getTime()) {
				throw new RuntimeException("签到时间异常");
			}
			//将两个时间转成字符串格式再进行比较
			String loginDareString=CommonUtil.dateToString(loginDate,"yyyy-MM-dd");
			String nowDateString=CommonUtil.dateToString(nowDate,"yyyy-MM-dd");
			//不比较时分秒，年月日相同即表示已签到
			if(loginDareString.equals(nowDateString)){
				throw new RuntimeException("已签到");
			}
		}
		//签到设置
		GoldPoint goldPoint=new GoldPoint();
		goldPoint.setUser(user);
		goldPoint.setPointCount(Constant.SIGN_POINT);
		goldPoint.setGoldCount(Constant.COST_TYPE_GOLD);
		goldPoint.setInfo("签到获取积分");
		goldPoint.setCreateDate(nowDate);
		System.out.println(goldPoint.getCreateDate());
		goldPointMapper.insert(goldPoint);

		user.setLoginDate(nowDate);
		System.out.println(user.getLoginDate());
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
