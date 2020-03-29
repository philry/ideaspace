package com.itany.netClass.service.impl;

import com.itany.netClass.constant.Constant;
import com.itany.netClass.dao.GoldPointsMapper;
import com.itany.netClass.dao.UserMapper;
import com.itany.netClass.entity.GoldPoints;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.DuplicateNameException;
import com.itany.netClass.exception.RequestParameterException;

import com.itany.netClass.exception.SignException;
import com.itany.netClass.exception.UserNotFoundException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.util.DateUtil;
import com.itany.netClass.util.MD5Util;
import com.itany.netClass.util.ParameterUtil;

import java.util.Date;

public class UserServiceImpl implements UserService {

     private UserMapper userMapper = ObjectFactory.getObject("userMapper");
     private GoldPointsMapper goldPointsMapper=ObjectFactory.getObject("goldPointsMapper");

     @Override
     public void fontRegist(String loginName, String password, String nickname, String email, String rePassword) throws RequestParameterException {
         if (ParameterUtil.isNull(nickname)) {
             throw new RequestParameterException("昵称不能为空");
         }
         nickname = nickname.trim();

         if (nickname.length() > 60) {
             throw new RequestParameterException("昵称由1-60个合法字符组成");
         }
         if (ParameterUtil.isIllegal(nickname)) {
             throw new RequestParameterException("昵称不能含有非法字符");
         }


         if (ParameterUtil.isNull(email)) {
             throw new RequestParameterException("修改失败，邮箱不能为空");
         }
         email = email.trim();
         //正则校验邮箱
         String emailReg = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
         if (!email.matches(emailReg)) {
             throw new RequestParameterException("邮箱格式不正确");
         }
         User user2 = userMapper.selectByEmail(email);
         if (null != user2) {
             throw new RequestParameterException("该邮箱已经被注册了");
         }


         User user = new User();
         user.setNickname(nickname);
         user.setCreateDate(new Date());
         user.setEmail(email);
         user.setRole(Constant.USER_ROLE_NORMAL);
         user.setStatus(Constant.STATUS_ENABLE);
         user.setPassword(password);

         if (ParameterUtil.isNull(password) || ParameterUtil.isNull(rePassword)) {
             throw new RequestParameterException("密码不能为空");
         }
         password = password.trim();
         rePassword = rePassword.trim();

         if (!password.equals(rePassword)) {
             throw new RequestParameterException("两个密码不一致");
         }
         //正则校验密码，密码必须为字母或数字，长度为6到20位
         String passwordReg = "^[a-zA-Z0-9]{6,20}$";
         if (password.matches(passwordReg)) {
             user.setPassword(MD5Util.md5(password));
         } else {
             throw new RequestParameterException("密码只能由6到20位的数字或字母组成");
         }


         try {
             frontCheckLoginname(loginName);
         } catch (Exception e) {
             e.printStackTrace();
         }


         user.setLoginName(loginName.trim());

         userMapper.insert(user);

     }

     @Override
     public void frontCheckLoginname(String loginName) throws RequestParameterException, DuplicateNameException {

         if (ParameterUtil.isNull(loginName)) {
            throw new RequestParameterException("登录名不能为空");
         }

         loginName = loginName.trim();
         //正则校验登录名，登录名由2-20个英文字母或数字组成
         String reg = "^[a-zA-Z0-9]{2,20}$";
         if (!loginName.matches(reg)) {
             throw new RequestParameterException("登录名由2-20个英文字母或数字组成");
         }

         User selectUser = userMapper.selectByLoginName(loginName);
         if (null != selectUser) {
             throw new DuplicateNameException("该登录名已被注册了");
         }
     }

     @Override
     public User frontLogin(User user) {
         user.setPassword(MD5Util.md5(user.getPassword()));

        User user1=userMapper.selectByLoginnameAndPasswordFront(user);

        if(user1==null){
            throw new RuntimeException("登录名或密码错误");
        }


      return  user1;
     }

    @Override
    public void modifyUser(String loginName,String password, String newPassword, String nickname, String email) throws RequestParameterException, UserNotFoundException {
         if(ParameterUtil.isNull(nickname)){
             throw new RequestParameterException("修改失败，昵称不能为空");
         }

         if(ParameterUtil.isNull(email)){
             throw new RequestParameterException("修改失败，邮箱不能为空" );
         }

        User user=userMapper.selectByLoginName(loginName);
         if(user==null){
             throw new UserNotFoundException("该用户不存在");
         }else{
             user.setLoginName(loginName);
             user.setPassword(MD5Util.md5(password));
             user.setNickname(nickname);
             user.setNewPassward(MD5Util.md5(newPassword));
             user.setEmail(email);
         }
         userMapper.updateUser(user);
    }

    @Override
    public void frontSign(String loginName) throws SignException {


        User selectUser=userMapper.selectByLoginName(loginName);
        if(selectUser.getLoginDate()==null|| DateUtil.isNeedSign(selectUser.getLoginDate())){
            Date d=new Date();
            selectUser.setLoginDate(d);

            System.out.println(selectUser.getPassword());
            selectUser.setPassword("");
            userMapper.updateUser(selectUser);
            GoldPoints gp=new GoldPoints();
            gp.setCreateDate(d);
            gp.setGoldCount(0);
            gp.setInfo("您已签到获取五积分");
            gp.setPointCount(5);
            gp.setUser(selectUser);
            System.out.println(selectUser.toString());
            goldPointsMapper.insert(gp);
           }else{
            throw new SignException("您今天已经签过到了！");
        }
    }
}