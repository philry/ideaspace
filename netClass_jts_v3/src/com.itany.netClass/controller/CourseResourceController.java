package com.itany.netClass.controller;

import com.itany.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/courseResource")
public class CourseResourceController {

    @RequestMapping("/back/findByParams")
    public String findByParams(HttpServletRequest request, HttpServletResponse response){

        return "/showBackCourseResource.do";
    }
}
