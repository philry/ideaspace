package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.util.AjaxResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RequestMapping("/comment")
public class CommentController {

    @ResponseBody
    @RequestMapping("/back/findByParams")
    public AjaxResult findByParams(HttpServletRequest request, HttpServletResponse response) {
        CommentService commentService = ObjectFactory.getObject("commentService");
        AjaxResult ar = new AjaxResult();
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if (pageNo == null || pageNo.equals("")) {
            pageNo = "1";
        }
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "2";
        }
        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        String resourceId = request.getParameter("resourceId");
        String nickname = request.getParameter("nickname");
        String context = request.getParameter("context");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String status = request.getParameter("status");
        Comment commentParams = new Comment();
        Resource resource = new Resource();
        resource.setId(Integer.parseInt(resourceId));
        commentParams.setResource(resource);
        User user = new User();
        user.setNickname(nickname);
        commentParams.setUser(user);
        commentParams.setContext(context);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (startDate != null && startDate != "") {
                commentParams.setStartDate(new Timestamp(format.parse(startDate).getTime()));
            }
            if(endDate != null && endDate != ""){
                commentParams.setEndDate(new Timestamp(format.parse(endDate).getTime()));
            }
            commentParams.setStatus(Integer.parseInt(status));

            List<Comment> comments = commentService.findByParams(commentParams);
            PageInfo<Comment> pageComments = new PageInfo<Comment>(comments);
            request.getSession().setAttribute("pageComments2",pageComments);
            ar.setSuccess(true);
        } catch (ParseException e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }catch (Exception e){
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }
}
