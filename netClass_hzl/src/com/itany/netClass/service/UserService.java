package com.itany.netClass.service;


import com.itany.netClass.entity.User;
import com.itany.netClass.exception.*;

/**
 * 示例service接口
 * @author teacher
 * @date 2018-8-22
 */
public interface UserService {
	
	public void fontRegist(String loginName, String password, String nickname, String email,String rePassword) throws RequestParameterException;

    public void frontCheckLoginname(String loginName) throws RequestParameterException, DuplicateNameException;


    public User  frontLogin(User user) throws RequestParameterException, UserNotFoundException, StatusErrorException;

    public void modifyUser(String loginName,String password, String newPassword, String nickName, String email) throws RequestParameterException, UserNotFoundException;

    public  void frontSign( String loginName) throws SignException;



















}
