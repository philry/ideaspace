package com.itany.netClass.controller;


import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;

import com.itany.netClass.entity.User;
import com.itany.netClass.exception.*;
import com.itany.netClass.factory.ObjectFactory;

import com.itany.netClass.filters.ApplicationLisenter;
import com.itany.netClass.service.UserService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


@RequestMapping("/user")
public class UserController {

    private UserService userService = ObjectFactory.getObject("userService");

    /**
     * 前台注册
     *
     * @param request
     * @param response
     * @return
     */

    @RequestMapping("/front/regist")
    @ResponseBody
    public AjaxResult fontRegist(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = new AjaxResult();
        String loginname = request.getParameter("loginname");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");


        try {
            userService.fontRegist(loginname, password, nickname, email, rePassword);
            ar.setSuccess(true);
            ar.setMsg("注册成功");
        } catch (Exception e) {
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        }

        return ar;
    }

    /**
     * 前台注册检查登录名是否可用
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/front/checkLoginname")
    @ResponseBody
    public AjaxResult frontCheckLoginname(HttpServletRequest request, HttpServletResponse response) {
        userService = (UserService) ObjectFactory.getObject("userService");
        AjaxResult ar = new AjaxResult();

        String loginName = request.getParameter("loginName");



        try {
            userService.frontCheckLoginname(loginName);
            ar.setSuccess(true);
            ar.setMsg("该登录名可用");
        } catch (RequestParameterException e) {
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        } catch (DuplicateNameException e) {
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        } catch (ServiceException e) {
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        }

        return ar;
    }

    @RequestMapping("/front/login")
    @ResponseBody
    public AjaxResult frontLogin(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("登录成功");

        try {


            HttpSession session = request.getSession();
            System.out.println("sessionId=" + session.getId());
            ar.setSuccess(true);
            ar.setMsg("登录成功");
            String nickName = request.getParameter("nickname");



            String loginName = request.getParameter("loginName");
            Cookie c = new Cookie("loginName", loginName);
            c.setPath(request.getContextPath());
            response.addCookie(c);


           ar.setSuccess(true);
           ar.setMsg("登录成功");
//            String nickName=request.getParameter("nickname");
//
//            String loginName = request.getParameter("loginName");
//            Cookie c = new Cookie("loginName",loginName);
//            c.setPath(request.getContextPath());
//            response.addCookie(c);

            User user = new User();
            CommonUtil.getObj(request, user, null);
//            System.out.println(user.toString());
            User loginUser = userService.frontLogin(user);
            System.out.println(loginUser.toString());
            loginUser.setPassword(null);//清除密码
            ar.setObj(loginUser);

            Map<String, User> usersMap = ApplicationLisenter.getUsers();
            usersMap.put(loginUser.getLoginName(), loginUser);

        } catch (RequestParameterException e) {
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        } catch (UserNotFoundException e) {
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        } catch (StatusErrorException e) {
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        }

        return ar;
    }


    @RequestMapping("/check")
    @ResponseBody
    public AjaxResult check(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = AjaxResult.isOk("登录验证通过");
        Map<String, User> usersMap = ApplicationLisenter.getUsers();
        String loginName = request.getParameter("loginName");
        User user = usersMap.get(loginName);
        if (null == user) {
            ar.setSuccess(false);
            ar.setMsg("当前未登录");
            return ar;
        }
        ar.setObj(user);
        return ar;

    }

    @RequestMapping("/front/loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {

        String loginName = request.getParameter("loginName");
        Map<String, User> usersMap = ApplicationLisenter.getUsers();
        usersMap.remove(loginName);
        return "redirect:/showIndex.do";
    }


    @RequestMapping("/front/modifyUser")
    @ResponseBody
    public AjaxResult modifyUser(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("111111111111111111111111");
        AjaxResult ar = new AjaxResult();

        String loginName=request.getParameter("loginName");
        String password=request.getParameter("password");
        String newPassword=request.getParameter("newPassword");
        String nickName=request.getParameter("nickName");
        String email=request.getParameter("email");
        User user=new User();

        try {
            userService.modifyUser(loginName,password,newPassword,nickName,email);
            CommonUtil.getObj(request,user,null);
            System.out.println("22222222222222222222");
            ar.setSuccess(true);
            ar.setMsg("修改成功");
        } catch (Exception e) {
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return ar;
    }

    @RequestMapping("/front/sign")
    @ResponseBody
    public AjaxResult frontSign(HttpServletRequest request, HttpServletResponse response) {
    AjaxResult ar=new AjaxResult();
    String loginName=request.getParameter("loginName");
        User user=new User();
        try {
            userService.frontSign(loginName);
            Map<String, User> usersMap = ApplicationLisenter.getUsers();
            User loginUser=usersMap.get(loginName);
            //loginUser.setPointsTotal(loginUser.getPointsTotal()+5);

            usersMap.put(loginUser.getLoginName(), loginUser);
            CommonUtil.getObj(request, user, null);
            ar.setSuccess(true);
            ar.setMsg("签到成功");
            ar.setObj(loginUser);
        } catch (Exception e) {
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        }
        return  ar;
    }

}
