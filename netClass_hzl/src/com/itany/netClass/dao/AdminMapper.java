package com.itany.netClass.dao;

import com.itany.netClass.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    /**
     * 后台登录
     *
     * @param name
     * @param password
     * @return
     */
    public User selectAdminByNameAndPassword(@Param("name") String name, @Param("password") String password);

    /**
     * 后台查询所有用户
     *
     * @return
     */
    public List<User> findAllUsers();

    /**
     * 查询用户个人信息
     *
     * @param id
     * @return
     */
    public User findUserMessage(Integer id);

    /**
     * 后台修改用户信息
     *
     * @param user
     */
    public void changeUserMessage(User user);
}
