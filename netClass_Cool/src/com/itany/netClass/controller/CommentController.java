package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CommentService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.ParameterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService = ObjectFactory.getObject("commentService");

    /**
     * 转发到待审核评论页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findAllWait")
    public String findAllWait(HttpServletRequest request, HttpServletResponse response){
        Integer pageNo = Constant.PAGE_NO;
        Integer pageSize = Constant.PAGE_SIZE;
        //开启分页
        PageHelper.startPage(pageNo,pageSize);
        //查询数据
        List<Comment> list = commentService.findAllWait();
        PageInfo<Comment> comments = new PageInfo<Comment>(list);
        if(comments.getPages()==0){
            comments.setPages(Constant.PAGE_NO);
        }
        for(Comment comment:comments.getList()){
            comment.initCount();
        }
        request.setAttribute("comments",comments);
        return "back_commentManageSet";
    }

    /**
     * 待审核页面的分页查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public AjaxResult findPage(HttpServletRequest request,HttpServletResponse response){
        //获取页码
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        Integer pageNo = ParameterUtil.getPageNo(no);
        Integer pageSize = ParameterUtil.getPageSize(size);
        PageHelper.startPage(pageNo,pageSize);
        List<Comment> list = commentService.findAllWait();
        PageInfo<Comment> comments = new PageInfo<Comment>(list);
        if(comments.getPages()==0){
            comments.setPages(Constant.PAGE_NO);
        }
        for(Comment comment:comments.getList()){
            comment.initCount();
        }
        AjaxResult ar = AjaxResult.isOk("success", comments);
        return ar;
    }

    /**
     * 待审核页面，审核通过方法
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/modifyEnable")
    public AjaxResult modifyEnable(HttpServletRequest request,HttpServletResponse response){
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        String id = request.getParameter("id");
        Integer pageNo = ParameterUtil.getPageNo(no);
        Integer pageSize = ParameterUtil.getPageSize(size);
        commentService.modifyEnable(Integer.parseInt(id));
        PageHelper.startPage(pageNo,pageSize);
        List<Comment> list = commentService.findAllWait();
        PageInfo<Comment> comments = new PageInfo<Comment>(list);
        if(comments.getPages()==0){
            comments.setPages(Constant.PAGE_NO);
        }
        for(Comment comment:comments.getList()){
            comment.initCount();
        }
        AjaxResult ar = AjaxResult.isOk("success", comments);
        return ar;
    }

    /**
     * 待审核页面，评论禁用方法
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/modifyDisable")
    public AjaxResult modifyDisable(HttpServletRequest request,HttpServletResponse response){
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        String id = request.getParameter("id");
        Integer pageNo = ParameterUtil.getPageNo(no);
        Integer pageSize = ParameterUtil.getPageSize(size);
        commentService.modifyDisable(Integer.parseInt(id));
        PageHelper.startPage(pageNo,pageSize);
        List<Comment> list = commentService.findAllWait();
        PageInfo<Comment> comments = new PageInfo<Comment>(list);
        if(comments.getPages()==0){
            comments.setPages(Constant.PAGE_NO);
        }
        for(Comment comment:comments.getList()){
            comment.initCount();
        }
        AjaxResult ar = AjaxResult.isOk("success", comments);
        return ar;
    }

    /**
     * 待审核页面，条件查询方法
     * @param request
     * @param response
     * @param files
     * @return
     */
    @RequestMapping("/findByData")
    @ResponseBody
    public AjaxResult findByData(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> files){
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        String userName = request.getParameter("user-name");
        String context = request.getParameter("user-comment");
        String startDate = request.getParameter("start-date");
        String endDate = request.getParameter("end-date");

        Integer pageNo = ParameterUtil.getPageNo(no);
        Integer pageSize = ParameterUtil.getPageSize(size);
        Comment comment = new Comment();
        comment.setContext(context);
        comment.setUser(new User());
        comment.getUser().setLoginName(userName);
        comment.setStartDate(startDate);
        comment.setEndDate(endDate);
        PageHelper.startPage(pageNo,pageSize);
        List<Comment> list = commentService.findByData(comment);
        PageInfo<Comment> comments = new PageInfo<Comment>(list);
        if(comments.getPages()==0){
            comments.setPages(Constant.PAGE_NO);
        }
        for(Comment comm:comments.getList()){
            comm.initCount();
        }
        AjaxResult ar = AjaxResult.isOk("success", comments);
        return ar;
    }

    /**
     * 章节页面跳转到相关评论页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findByChapterId")
    public String findByChapterId(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        System.out.println("执行到这里了，id="+id);
        Integer pageNo = ParameterUtil.getPageNo(null);
        Integer pageSize = ParameterUtil.getPageSize(null);

        PageHelper.startPage(pageNo,pageSize);
        List<Comment> list = commentService.findByChapterId(Integer.parseInt(id));
        PageInfo<Comment> comments = new PageInfo<Comment>(list);
//        System.out.println(comments.getList().get(0));
        //初始化点赞数
        for(Comment comm:comments.getList()){
            comm.initCount();
        }
        request.setAttribute("comments",comments);
        return "back_commentSet";
    }

    /**
     * 评论管理页面，分页方法
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/findPassedPage")
    public AjaxResult findPassedPage(HttpServletRequest request,HttpServletResponse response,List<CommonsMultipartFile> files){
        String no = request.getParameter("pageNo");//查询的页码数
//        System.out.println("no="+no);
        Integer pageNo = ParameterUtil.getPageNo(no);
        Integer pageSize = ParameterUtil.getPageSize(null);
//        System.out.println(pageNo+"and"+pageSize);
        PageHelper.startPage(pageNo,pageSize);
        List<Comment> list = commentService.findAllPassed();
        PageInfo<Comment> comments = new PageInfo<Comment>(list);
//        System.out.println("size="+comments.getList().size());
//        System.out.println("pages="+comments.getPages());
//        System.out.println("num="+comments.getPageNum());
        if(comments.getPages()<comments.getPageNum()){//保证最大页面数不为0
            comments.setPages(Constant.PAGE_NO);
        }
        for(Comment comment:comments.getList()){//初始化点赞数
            comment.initCount();
        }
        AjaxResult ar = AjaxResult.isOk("success", comments);
        return ar;
    }

    /**
     * 修改审核过了的评论的状态
     * @param request
     * @param response
     * @param files
     * @return
     */
    @ResponseBody
    @RequestMapping("/modifyById")
    public AjaxResult modifyById(HttpServletRequest request,HttpServletResponse response,List<CommonsMultipartFile> files){
        String id = request.getParameter("id");
        String no = request.getParameter("pageNo");
        Integer pageNo = ParameterUtil.getPageNo(no);
        Integer pageSize = ParameterUtil.getPageSize(null);
        //先修改状态
        commentService.modifyById(Integer.parseInt(id));
        //再查询，分页
        PageHelper.startPage(pageNo,pageSize);
        List<Comment> list = commentService.findAllPassed();
        PageInfo<Comment> comments = new PageInfo<Comment>(list);
        if(comments.getPages()<comments.getPageNum()){//保证最大页面数不为0
            comments.setPages(Constant.PAGE_NO);
        }
        for(Comment comment:comments.getList()){//初始化点赞数
            comment.initCount();
        }
        AjaxResult ar = AjaxResult.isOk("success", comments);
        return ar;
    }

    /**
     * 根据条件查询所有已审核的评论
     * @param request
     * @param response
     * @param files
     * @return
     */
    @ResponseBody
    @RequestMapping("/findPassedByData")
    public AjaxResult findPassedByData(HttpServletRequest request,HttpServletResponse response,List<CommonsMultipartFile> files){
//        获取参数
        String no = request.getParameter("pageNo");
        Integer pageNo = ParameterUtil.getPageNo(no);
        Integer pageSize = ParameterUtil.getPageSize(null);

        String loginName = request.getParameter("loginName");
        String context = request.getParameter("context");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String status = request.getParameter("status");
//        打包参数
        Comment comment = new Comment();
        comment.setUserName(loginName);
        comment.setContext(context);
        comment.setStartDate(startDate);
        comment.setEndDate(endDate);
        comment.setStatus(Integer.parseInt(status));
        PageHelper.startPage(pageNo,pageSize);
        List<Comment> list = commentService.findPassedByData(comment);
        PageInfo<Comment> comments = new PageInfo<Comment>(list);
        if(comments.getPages()<comments.getPageNum()){//保证最大页面数不为0
            comments.setPages(Constant.PAGE_NO);
        }
        for(Comment comm:comments.getList()){//初始化点赞数
            comm.initCount();
        }
        AjaxResult ar = AjaxResult.isOk("success", comments);
        return ar;
    }
}
