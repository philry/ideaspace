package com.itany.nmms.controller;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.CodeErrorException;
import com.itany.nmms.exception.StaffNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.StaffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RequestMapping("/staff")
public class StaffController {
    private StaffService staffService= (StaffService) ObjectFactory.getObject("staffService");
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String loginName=request.getParameter("loginName");
        String password=request.getParameter("password");
        String role=request.getParameter("role");
        String code=request.getParameter("code");
        String image= (String) request.getSession().getAttribute("code");

        try {
           Staff staff = staffService.login(loginName,password,role,code,image);
           request.getSession().setAttribute("staff",staff);
           return "redirect:/showMain.do";
        } catch (CodeErrorException e) {
            e.printStackTrace();
            request.getSession().setAttribute("loginMsg",e.getMessage());
            return "backend/login";
        } catch (StaffNotExistException e) {
            e.printStackTrace();
            request.getSession().setAttribute("loginMsg",e.getMessage());
            return "/showLogin.do";
        }
    }
}
