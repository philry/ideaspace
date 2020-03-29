package com.itany.netClass.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.entity.Comment;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseExistsException;
import com.itany.netClass.exception.CourseTypeNoExistsException;
import com.itany.netClass.exception.ParameterException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.ParameterUtil;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/course")
public class CourseController {
    private CourseService courseService = ObjectFactory.getObject("courseService");

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request, HttpServletResponse response){
        CourseTypeService typeService = ObjectFactory.getObject("courseTypeService");
        List<CourseType> types = typeService.findAllThird();

        request.setAttribute("types",types);
        Integer pageNo;
        Integer pageSize;
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        if(ParameterUtil.isNull(no)){
            pageNo = Constant.PAGE_NO;
        }else{
            pageNo = Integer.parseInt(no);
        }

        if(ParameterUtil.isNull(size)){
            pageSize = Constant.PAGE_SIZE;
        }else{
            pageSize = Integer.parseInt(size);
        }
        System.out.println("pageNO======="+pageNo+"\n"+"pageSize====="+pageSize);
        PageHelper.startPage(pageNo,pageSize);
        List<Course> list = courseService.findAll();

        PageInfo<Course> courses = new PageInfo<Course>(list);
        if(courses.getPages()==0){
            courses.setPages(Constant.PAGE_NO);
        }
        request.setAttribute("courses",courses);

        return "back_courseSet";
    }

    @ResponseBody
    @RequestMapping("/addCourse")
    public AjaxResult addCourse(HttpServletRequest request,HttpServletResponse response,List<CommonsMultipartFile> files){
        String courseName = request.getParameter("course_name");
        String courseInfo = request.getParameter("course_info");
        String author = request.getParameter("author");
        String recommendation = request.getParameter("recommendation_grade");
        String courseTypeId = request.getParameter("course_type_id");
        HttpSession session = request.getSession();
        Integer pageNo;
        Integer pageSize;
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        if(ParameterUtil.isNull(no)){
            pageNo = Constant.PAGE_NO;
        }else{
            pageNo = Integer.parseInt(no);
        }

        if(ParameterUtil.isNull(size)){
            pageSize = Constant.PAGE_SIZE;
        }else{
            pageSize = Integer.parseInt(size);
        }
        System.out.println("course_type_id-------------"+courseTypeId+"course_name"+courseName+"course_info"+courseInfo);
        try {
            courseService.addCourse(courseName,courseInfo,author,recommendation,courseTypeId,session,files);
            PageHelper.startPage(pageNo,pageSize);
            List<Course> list = courseService.findAll();
            PageInfo<Course> courses = new PageInfo<Course>(list);
            AjaxResult ar = AjaxResult.isOk("success",courses);
            return ar;
        } catch (CourseExistsException e) {
            e.printStackTrace();
            AjaxResult ar = AjaxResult.isFail("fail");
            return ar;
        } catch (FileUploadException e) {
            e.printStackTrace();
            AjaxResult ar = AjaxResult.isFail("fail");
            return ar;
        }
    }

    @RequestMapping("/findPage")
    @ResponseBody
    public AjaxResult findPage(HttpServletRequest request,HttpServletResponse response){
        Integer pageNo;
        Integer pageSize;
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        if(ParameterUtil.isNull(no)){
            pageNo = Constant.PAGE_NO;
        }else{
            pageNo = Integer.parseInt(no);
        }

        if(ParameterUtil.isNull(size)){
            pageSize = Constant.PAGE_SIZE;
        }else{
            pageSize = Integer.parseInt(size);
        }

        PageHelper.startPage(pageNo,pageSize);
        List<Course> list = courseService.findAll();
        PageInfo<Course> courses = new PageInfo<Course>(list);
        AjaxResult ar = AjaxResult.isOk("success", courses);
        return ar;
    }

    @ResponseBody
    @RequestMapping("/findByData")
    public AjaxResult findByData(HttpServletRequest request,HttpServletResponse response,List<CommonsMultipartFile> files){
        //获取参数
        String author = request.getParameter("author-name-search");
        String courseName = request.getParameter("course-name-search");
        String status = request.getParameter("course-status-search");
        String courseTypeId = request.getParameter("course_type_id-search");
        String startDate = request.getParameter("start-date");
        String endDate = request.getParameter("end-date");
        System.out.println("获得的参数"+author+"\n"+courseName+"\n"+status+"\n"+courseTypeId+"\n"+startDate+"\n"+endDate);
        Integer pageNo;
        Integer pageSize;
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        if(ParameterUtil.isNull(no)){
            pageNo = Constant.PAGE_NO;
        }else{
            pageNo = Integer.parseInt(no);
        }

        if(ParameterUtil.isNull(size)){
            pageSize = Constant.PAGE_SIZE;
        }else{
            pageSize = Integer.parseInt(size);
        }
        try {
            PageHelper.startPage(pageNo,pageSize);
            List<Course> list = courseService.findByData(author, courseName, status, courseTypeId, startDate, endDate);

            PageInfo<Course> courses = new PageInfo<Course>(list);
            if(courses.getPages()==0){
                courses.setPages(Constant.PAGE_NO);
            }
            AjaxResult ar = AjaxResult.isOk("success", courses);
            return ar;
        } catch (ParameterException e) {
            e.printStackTrace();
            AjaxResult ar = AjaxResult.isFail("fail");
            return ar;
        }
    }
    @ResponseBody
    @RequestMapping("/modifyStatusById")
    public AjaxResult modifyStatusById(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        Integer pageNo;
        Integer pageSize;
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        if(ParameterUtil.isNull(no)){
            pageNo = Constant.PAGE_NO;
        }else{
            pageNo = Integer.parseInt(no);
        }

        if(ParameterUtil.isNull(size)){
            pageSize = Constant.PAGE_SIZE;
        }else{
            pageSize = Integer.parseInt(size);
        }

        try {
            courseService.modifyStatusById(id,status);
            PageHelper.startPage(pageNo,pageSize);
            List<Course> list = courseService.findAll();
            PageInfo<Course> courses = new PageInfo<Course>(list);
            if(courses.getPages()==0){
                courses.setPages(Constant.PAGE_NO);
            }
            AjaxResult ar = AjaxResult.isOk("success",courses);
            return ar;
        } catch (ParameterException e) {
            e.printStackTrace();
            AjaxResult ar = AjaxResult.isFail("fail");
            return ar;
        }
    }

    @ResponseBody
    @RequestMapping("/modifyById")
    public AjaxResult modifyById(HttpServletRequest request,HttpServletResponse response,List<CommonsMultipartFile> files){
        Integer pageNo;
        Integer pageSize;
        String no = request.getParameter("pageNo");
        String size = request.getParameter("pageSize");
        if(ParameterUtil.isNull(no)){
            pageNo = Constant.PAGE_NO;
        }else{
            pageNo = Integer.parseInt(no);
        }
        if(ParameterUtil.isNull(size)){
            pageSize = Constant.PAGE_SIZE;
        }else{
            pageSize = Integer.parseInt(size);
        }
        String id = request.getParameter("course_id");
        String courseName = request.getParameter("course_name");
        String courseInfo = request.getParameter("course_info");
        String author = request.getParameter("author");
        String recommendation = request.getParameter("recommendation_grade");
        String courseTypeId = request.getParameter("course_type_id");
        HttpSession session = request.getSession();
        //新建course对象 存放数据
        Course course = new Course();
        course.setId(Integer.parseInt(id));
        course.setCourseName(courseName);
        course.setCourseInfo(courseInfo);
        course.setAuthor(author);
        course.setRecommendation(recommendation.equals(Constant.SELECT_INIT_NUM)?null:Integer.parseInt(recommendation));
        //前台一定要判断类型不能为-1
        course.setCourseTypeId(Integer.parseInt(courseTypeId));
        try {
            courseService.modifyById(course,session,files);
            //操作做完查询 分页
            PageHelper.startPage(pageNo,pageSize);
            List<Course> list = courseService.findAll();
            PageInfo<Course> courses = new PageInfo<Course>(list);
            AjaxResult ar = AjaxResult.isOk("success", courses);
            return ar;
        } catch (FileUploadException e) {
            e.printStackTrace();
            AjaxResult ar = AjaxResult.isFail("fail");
            return ar;
        } catch (CourseExistsException e) {
            e.printStackTrace();
            AjaxResult ar = AjaxResult.isFail("fail");
            return ar;
        }
    }
    @RequestMapping("/front_findAllById")
    public String front_findAllById(HttpServletRequest request,HttpServletResponse response){
//        获取参数
        String id = request.getParameter("id");
//        查询数据

        return null;
    }

    /**
     * 查询课程点击量前三的课程
     * @return
     */
    List<Comment> findTopThreeCourse(){
        return null;
    }

}
