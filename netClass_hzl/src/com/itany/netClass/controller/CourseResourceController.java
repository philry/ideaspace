package com.itany.netClass.controller;

import com.itany.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/courseResource")
public class CourseResourceController extends HttpServlet {
    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest req, HttpServletResponse res){



        return "back_courseResourceSet";
    }
}
