package com.itany.netClass.service.proxy;

import com.itany.netClass.entity.User;
import com.itany.netClass.exception.CodeNotWriteException;
import com.itany.netClass.exception.MessageIsNullException;
import com.itany.netClass.exception.adminNotExistException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.transaction.TransactionManager;

import java.util.List;

public class adminServiceProxy implements AdminService {
    @Override
    public User login(String name, String password, String code, String image) throws CodeNotWriteException, adminNotExistException {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        AdminService adminService = ObjectFactory.getObject("adminServiceTarget");
        try {
            tran.beginTransaction();
            User admin = adminService.login(name, password, code, image);
            tran.commit();
            return admin;
        } catch (CodeNotWriteException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        } catch (adminNotExistException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public List<User> findAllUsers() {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        AdminService adminService = ObjectFactory.getObject("adminServiceTarget");

        try {
            tran.beginTransaction();
            List<User> users = adminService.findAllUsers();
            tran.commit();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public User findUserMessage(Integer id) throws MessageIsNullException {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        AdminService adminService = ObjectFactory.getObject("adminServiceTarget");

        try {
            tran.beginTransaction();
            User user = adminService.findUserMessage(id);
            tran.commit();
            return user;
        } catch (MessageIsNullException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void changeUserStatus(Integer id) {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        AdminService adminService = ObjectFactory.getObject("adminServiceTarget");
        try {
            tran.beginTransaction();
            adminService.changeUserStatus(id);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }
    }

    @Override
    public void modifyUserMessage(Integer id, String nickname, String role, String password, String email) throws MessageIsNullException {
        TransactionManager tran = ObjectFactory.getObject("transaction");
        AdminService adminService = ObjectFactory.getObject("adminServiceTarget");

        try {
            tran.beginTransaction();
            adminService.modifyUserMessage(id, nickname, role, password, email);
            tran.commit();
        } catch (MessageIsNullException e) {
            e.printStackTrace();
            tran.rollback();
            throw e;
        }

    }
}
