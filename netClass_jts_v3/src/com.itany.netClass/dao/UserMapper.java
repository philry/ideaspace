package com.itany.netClass.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itany.netClass.entity.User;
import com.itany.netClass.vo.UserQuery;

/**
 * t_user的操作
 * @author teacher
 * @date 2018-8-17
 */
public interface UserMapper {
	//注册添加
	public void insert(User user);
	//查询(1.注册使用查重,2.登录查空)
	public List<User> selectNoSame(String loginName,String email);
	public List<User> selectById(Integer id);
	//更新（修改信息）
	public void updateById(User user);

	public List<User> selectByCondition(UserQuery query);

	public void insertReturnId(User user);

	public Integer getUserCount(@Param("nickname") String nickname);

}