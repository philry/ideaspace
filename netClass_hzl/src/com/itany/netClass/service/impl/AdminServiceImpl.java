package com.itany.netClass.service.impl;

import com.itany.netClass.dao.AdminMapper;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.CodeNotWriteException;
import com.itany.netClass.exception.MessageIsNullException;
import com.itany.netClass.exception.adminNotExistException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.util.MD5Util;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public User login(String name, String password, String code, String image) throws CodeNotWriteException, adminNotExistException {
        AdminMapper adminMapper = ObjectFactory.getObject("adminMapper");

        if (code == null || "".equals(code)) {
            throw new CodeNotWriteException("请输入验证码");
        }
        if (!code.equals(image)) {
            throw new CodeNotWriteException("验证码输入错误");
        }
        String mdPassword = MD5Util.md5(password);
        User admin = adminMapper.selectAdminByNameAndPassword(name, mdPassword);
        if (admin == null) {
            throw new adminNotExistException("不存在该管理员身份");
        }
        String role = admin.getRole();
        if (!"admin".equals(role)) {
            throw new adminNotExistException("该用户不是管理员");
        }
        return admin;
    }

    @Override
    public List<User> findAllUsers() {
        AdminMapper adminMapper = ObjectFactory.getObject("adminMapper");
        List<User> users = adminMapper.findAllUsers();
        return users;
    }

    @Override
    public User findUserMessage(Integer id) throws MessageIsNullException {
        AdminMapper adminMapper = ObjectFactory.getObject("adminMapper");
        User user = adminMapper.findUserMessage(id);
        if (user.getNickname() == null || user.getEmail() == null || user.getRole() == null) {
            throw new MessageIsNullException("个人信息不能为空");
        }
        return user;
    }

    @Override
    public void changeUserStatus(Integer id) {
        AdminMapper adminMapper = ObjectFactory.getObject("adminMapper");
        User user = adminMapper.findUserMessage(id);
        if (user.getStatus() == 0) {
            user.setStatus(1);
        } else {
            user.setStatus(0);
        }
        adminMapper.changeUserMessage(user);
    }

    @Override
    public void modifyUserMessage(Integer id, String nickname, String role, String password, String email) throws MessageIsNullException {
        AdminMapper adminMapper = ObjectFactory.getObject("adminMapper");
        User user = adminMapper.findUserMessage(id);
        if (role == null || "".equals(role)) {
            throw new MessageIsNullException("用户身份不可为空");
        }
        if (nickname == null || "".equals(nickname)) {
            throw new MessageIsNullException("用户昵称不可为空");
        }

        if ("管理员".equals(role)) {
            user.setRole("admin");
        } else if ("普通用户".equals(role)) {
            user.setRole("normal");
        } else {
            throw new MessageIsNullException("用户角色填写错误");
        }
        user.setNickname(nickname);
        if (password != null && !"".equals(password)) {
            user.setPassword(MD5Util.md5(password));
        }
        user.setPassword(password);
        user.setEmail(email);

        adminMapper.changeUserMessage(user);

    }
}
