package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentManageService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.ParameterUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/commentManage")
public class CommentManageController extends HttpServlet {
    CommentManageService commentManageService=ObjectFactory.getObject("commentManageService");

    //显示按条件查询的未审核的评论
    @RequestMapping("/unchecked")
    public String showAllunchecked(HttpServletRequest req, HttpServletResponse res){
        String pageNo=req.getParameter("pageNo");
        String pageNum=req.getParameter("pageNum");
        if(ParameterUtil.isNull(pageNo)){
            pageNo=Integer.toString(Constant.PAGE_START);
        }
        if(ParameterUtil.isNull(pageNum)){
            pageNum=Integer.toString(Constant.PAGE_SIZE);
        }
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageNum));

        String userName=req.getParameter("user-name");
        String context=req.getParameter("user-comment");
        String startDate=req.getParameter("startDate");
        String endDate=req.getParameter("endDate");

        Comment comment=new Comment();
        if(userName !=null&&!"".equals(userName.trim())){
            User user=new User();
            user.setNickname("%"+userName+"%");
            comment.setUser(user);
        }
        if(context!=null&&!"".equals(context.trim())){
            comment.setContext("%"+context+"%");
        }
        try {
            if(startDate!=null&&!"".equals(startDate.trim())){
                Timestamp dateStart=new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate).getTime());
                comment.setStartdate(dateStart);
            }
            if(endDate!=null&&!"".equals(endDate.trim())){
                Timestamp dateEnd=new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate).getTime());
                comment.setEndDate(dateEnd);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Comment> commentManages = commentManageService.findByParam(comment);
        PageInfo<Comment> pageCommentManages = new PageInfo<Comment>(commentManages);
        req.setAttribute("commentManages",commentManages);
        req.setAttribute("pageCommentManages",pageCommentManages);
        return "/back_commentManageSet";
    }
}
