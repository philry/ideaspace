package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.User;
import com.itany.netClass.exception.*;

import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.transaction.TransactionManager;

public class UserServiceProxy implements UserService {

    private TransactionManager trans = ObjectFactory.getObject("transaction");

    private UserService userServiceTarget = ObjectFactory.getObject("userServiceTarget");

    private UserService userService = ObjectFactory.getObject("userServiceTarget");

    @Override
    public void fontRegist(String loginName, String password, String nickname, String email, String rePassword) {
        try {
            trans.beginTransaction();
            userService.fontRegist(loginName, password, nickname, email, rePassword);
            trans.commit();
        } catch (RequestParameterException e) {
            e.printStackTrace();
            trans.rollback();
        }
    }

    @Override
    public void frontCheckLoginname(String loginName) {


        try {
            trans.beginTransaction();
            userService.frontCheckLoginname(loginName);
            trans.commit();
        } catch (RequestParameterException e) {
            e.printStackTrace();
            trans.rollback();
        } catch (DuplicateNameException e) {
            e.printStackTrace();
            trans.rollback();
        }

    }

    @Override
    public User frontLogin(User user) throws RequestParameterException, UserNotFoundException, StatusErrorException {

        try {
            trans.beginTransaction();
            User user1 = userService.frontLogin(user);
            trans.commit();
            return user1;
        } catch (RequestParameterException e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } catch (StatusErrorException e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        }

    }


    @Override
    public void modifyUser(String loginName, String password, String newPassword, String nickName, String email) throws RequestParameterException, UserNotFoundException {
        try {
            trans.beginTransaction();
            userService.modifyUser(loginName, password, newPassword, nickName, email);
            trans.commit();
        } catch (RequestParameterException e) {
            trans.rollback();
            throw e;
        } catch (UserNotFoundException e) {
            trans.rollback();
            throw e;
        }
    }

    @Override
    public void frontSign(String loginName) throws SignException {
        try {
            trans.beginTransaction();
            userService.frontSign(loginName);
            trans.commit();
        } catch (SignException e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        }
    }


}

