package com.itany.netClass.dao;

import com.itany.netClass.entity.User;
import org.apache.ibatis.annotations.Param;


/**
 * t_user的操作
 *
 * @author teacher
 * @date 2018-8-17
 */
public interface UserMapper {


    public void insert(User user);
	


public User selectByNameAndPassword(String name, String password);

    public User selectByEmail(@Param("email")String email);

    public User selectByLoginName(@Param("loginName")String loginName);

    /**
     * 根据用户名和密码查询用户信息,并且查出金币、积分额
     * @param user
     * @return
     */
    public User selectByLoginnameAndPasswordFront(User user);

    public void  updateUser(User user);

    public User selectById(User user);

}
