package com.itany.netClass.controller;

import com.itany.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/comment")
public class CommentController extends HttpServlet {
    @RequestMapping("/showAllChecked")
    public String showAllChecked(HttpServletRequest req , HttpServletResponse res){
        return "back_commentSet";
    }
}
