package com.itany.nmms.controller;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.CodeErrorException;
import com.itany.nmms.exception.StaffNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.StaffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/30 16:07
 * Description:
 * version:1.0
 */
@RequestMapping("/staff")
public class StaffController {

    private StaffService staffService = (StaffService) ObjectFactory.getObject("staffService");


    //@RequestMapping在类体上表示访问当前类需要的命令
    //在方法上表示访问当前方法需要的命令
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String code = request.getParameter("code");
        String image = (String) request.getSession().getAttribute("code");

        try {
            Staff staff = staffService.login(loginName,password,role,code,image);
            request.getSession().setAttribute("staff",staff);
            //返回值为一个字符串
            //其值有三种情况
            //第一种:redirect:路径,表示通过重定向的方式访问对应的命令
            return "redirect:/showMain.do";
        } catch (CodeErrorException e) {
            request.setAttribute("loginMsg",e.getMessage());
            //第二种:直接返回视图名，表示转发到对应的视图
            //此处表示直接跳转到页面:/WEB-INF/pages/backend/login.jsp
            return "backend/login";
        } catch (StaffNotExistException e) {
            request.setAttribute("loginMsg",e.getMessage());
            //第三种:直接返回某个命令，表示转发到某个命令
            //表示直接访问命令/showLogin
            return "/showLogin.do";
        }


    }

}
