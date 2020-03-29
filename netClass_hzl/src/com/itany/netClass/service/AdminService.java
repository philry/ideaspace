package com.itany.netClass.service;

import com.itany.netClass.entity.User;
import com.itany.netClass.exception.CodeNotWriteException;
import com.itany.netClass.exception.MessageIsNullException;
import com.itany.netClass.exception.adminNotExistException;

import java.util.List;

public interface AdminService {
    /**
     * 管理员后台登录
     *
     * @param name
     * @param password
     * @param code
     * @param image
     */
    public User login(String name, String password, String code, String image) throws CodeNotWriteException, adminNotExistException;

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
    public User findUserMessage(Integer id) throws MessageIsNullException;

    /**
     * 后台更改用户状态
     *
     * @param id
     */
    public void changeUserStatus(Integer id);

    /**
     * 后台更改用户信息
     *
     * @param id
     */
    public void modifyUserMessage(Integer id, String nickname, String role, String password, String email) throws MessageIsNullException;

}
