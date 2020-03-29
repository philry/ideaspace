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

	void insert(User user);
	
	void updateById(User user);
	/**
	 * 没有数据delete操作
	 * @author teacher
	 * @date 2018-8-17
	 * @param id
	 */
//	void deleteById(Integer id);
	
	List<User> selectByCondition(UserQuery query);
	
	void insertReturnId(User user);
	
	Integer getUserCount(@Param("nickname") String nickname);
	
}
