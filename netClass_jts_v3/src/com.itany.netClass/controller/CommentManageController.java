package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentManageService;
import com.itany.netClass.util.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.itany.netClass.factory.ObjectFactory.getObject;

@RequestMapping("/commentManage")
public class CommentManageController {

    CommentManageService commentManageService=ObjectFactory.getObject("commentManageService");

    @ResponseBody
    @RequestMapping("/back/jiaoyan")
    public AjaxResult jiaoyan(HttpServletRequest request, HttpServletResponse response){
        AjaxResult ar = new AjaxResult();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        if(startDate==null || startDate.equals("") || startDate.matches("\\d{4}-\\d{2}-\\d{2}"
        )){
            ar.setSuccess(true);
        }else{
            ar.setSuccess(false);
            ar.setMsg("日期格式错误,正确格式例如2018-01-01");
        }
        if(endDate==null || endDate.equals("") || endDate.matches("\\d{4}-\\d{2}-\\d{2}"
        )){
            ar.setSuccess(true);
        }else{
            ar.setSuccess(false);
            ar.setMsg("日期格式错误,正确格式例如2018-01-01");
        }
        return ar;
    }

    @RequestMapping("/back/findByParams")
    public String findByParams(HttpServletRequest request, HttpServletResponse response){

        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if(pageNo==null || pageNo.equals("")){
            pageNo = "1";
        }
        if(pageSize==null || pageSize.equals("")){
            pageSize = "2";
        }
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        String nickname = request.getParameter("nickname");
        String context = request.getParameter("context");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Comment comment = new Comment();
        User user = new User();
        if(nickname==null || nickname.equals("")){
            user.setNickname(null);
        }else {
            user.setNickname("%" + nickname + "%");
        }
        comment.setUser(user);
        if(context==null || context.equals("")){
            comment.setContext(null);
        }else {
            comment.setContext("%" + context + "%");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if(startDate==null || startDate.equals("")){
                comment.setStartDate(null);
            }
            else{
                comment.setStartDate(new Timestamp(format.parse(startDate).getTime()));
            }
            if(endDate==null || endDate.equals("")){
                comment.setEndDate(null);
            }
            else{
                comment.setEndDate(new Timestamp(format.parse(endDate).getTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Comment> comments = commentManageService.findByParams(comment);
        PageInfo<Comment> pageComments = new PageInfo<Comment>(comments);
        request.setAttribute("pageComments",pageComments);
        return "/showCommentManage.do";
    }

    @ResponseBody
    @RequestMapping("/back/passComment")
    public AjaxResult passComment(HttpServletRequest request,HttpServletResponse response){
        AjaxResult ar = new AjaxResult();
        String commentId = request.getParameter("commentId");
        try {
            commentManageService.passCommentById(Integer.parseInt(commentId));
            ar.setSuccess(true);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }catch (Exception e){
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }

    @ResponseBody
    @RequestMapping("/back/refuseComment")
    public AjaxResult refuseComment(HttpServletRequest request,HttpServletResponse response){
        AjaxResult ar = new AjaxResult();
        String commentId = request.getParameter("commentId");
        try {
            commentManageService.refuseCommentById(Integer.parseInt(commentId));
            ar.setSuccess(true);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }catch (Exception e){
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }
}
