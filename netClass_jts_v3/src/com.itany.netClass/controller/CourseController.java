package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.FileUploadException;
import com.itany.netClass.exception.ParamsHavingNullException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.util.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RequestMapping("/course")
public class CourseController {

    CourseService courseService=ObjectFactory.getObject("courseService");
    CourseTypeService courseTypeService=ObjectFactory.getObject("courseTypeService");

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

    @RequestMapping("/front/showCourses")
    public String showCourseAndChapter(HttpServletRequest request, HttpServletResponse response){
        AjaxResult ar = new AjaxResult();
        List<Course> coursesTopThree = courseService.selectCoursesOfTopThree();
        request.getSession().setAttribute("coursesTopThree",coursesTopThree);
        ar.setSuccess(true);
        return "/showFrontCourses.do";
    }

    @RequestMapping("/front/showCourseSelect")
    public String showCourseSelect(HttpServletRequest request, HttpServletResponse response){
        return "/showFrontSelect.do";
    }


    /**
     * 后台课程管理界面初始显示与条件查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/back/findByParams")
    public String findAllCourses(HttpServletRequest request, HttpServletResponse response){
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if(pageNo==null || pageNo.equals("")){
            pageNo = "1";
        }
        if(pageSize==null || pageSize.equals("")){
            pageSize = "2";
        }
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        String author = request.getParameter("author");
        String courseName = request.getParameter("courseName");
        String status = request.getParameter("status");
        String courseTypeId = request.getParameter("courseTypeId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Course course = new Course();
        if(status==null || status.equals("-1")){
            course.setStatus(null);
        }else{
            course.setStatus(Integer.parseInt(status));
        }
        if(courseTypeId==null || courseTypeId.equals("-1")){
            course.setCourseType(null);
        }else{
            CourseType courseType = new CourseType();
            courseType.setId(Integer.parseInt(courseTypeId));
            course.setCourseType(courseType);
        }
        course.setAuthor(author);
        course.setCourseName(courseName);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if(startDate==null || startDate.equals("")){
                course.setStartDate(null);
            }
            else{
                course.setStartDate(new Timestamp(format.parse(startDate).getTime()));
            }
            if(endDate==null || endDate.equals("")){
                course.setEndDate(null);
            }
            else{
                course.setEndDate(new Timestamp(format.parse(endDate).getTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Course> courses = courseService.selectByParams(course);
        PageInfo<Course> pageCourses = new PageInfo<Course>(courses);

        //查出所有可用课程类型在添加课程和条件查询模态框中显示
        List<CourseType> courseTypes = courseTypeService.selectLastTypes();
        request.setAttribute("pageCourses",pageCourses);
        request.setAttribute("courseTypes",courseTypes);
        return "/showBackCourses.do";
    }

    @ResponseBody
    @RequestMapping("/back/modifyStatus")
    public AjaxResult modifyStatus(HttpServletRequest request,HttpServletResponse response){
        AjaxResult ar = new AjaxResult();
        String courseId = request.getParameter("courseId");
        String status = request.getParameter("status");
        try {
            courseService.modifyStatus(Integer.parseInt(courseId),Integer.parseInt(status));
            ar.setSuccess(true);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }

    @ResponseBody
    @RequestMapping("/back/addCourse")
    public AjaxResult addCourse(HttpServletRequest request,HttpServletResponse response,List<CommonsMultipartFile> files){
        AjaxResult ar = new AjaxResult();
        String courseName = request.getParameter("courseName");
        String courseInfo = request.getParameter("courseInfo");
        String author = request.getParameter("author");
        String courseTypeId = request.getParameter("courseTypeId");
        if(courseTypeId.equals("-1")){
            courseTypeId = null;
        }
        try {
            courseService.addCourse(courseName,courseInfo,author,courseTypeId,files.get(0),request.getSession());
            ar.setSuccess(true);

        } catch (FileUploadException e) {
            e.printStackTrace();
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        } catch (ParamsHavingNullException e) {
            e.printStackTrace();
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        }
        return ar;
    }

    @ResponseBody
    @RequestMapping("/back/modifyCourse")
    public AjaxResult modifyCourse(HttpServletRequest request,HttpServletResponse response,List<CommonsMultipartFile> files){
        AjaxResult ar = new AjaxResult();
        String courseId = request.getParameter("courseId");
        String courseName = request.getParameter("courseName");
        String courseInfo = request.getParameter("courseInfo");
        String author = request.getParameter("author");
        String courseTypeId = request.getParameter("courseTypeId");
        String coverImage = request.getParameter("coverImage");
        if(courseTypeId.equals("-1")){
            courseTypeId=null;
        }
        try {
            courseService.modifyCourse(courseId,courseName,courseInfo,author,
                    courseTypeId,coverImage,files.get(0),request.getSession());
            ar.setSuccess(true);
        } catch (ParamsHavingNullException e) {
            e.printStackTrace();
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        } catch (FileUploadException e) {
            e.printStackTrace();
            ar.setSuccess(false);
            ar.setMsg(e.getMessage());
        }

        return ar;
    }

    @ResponseBody
    @RequestMapping("/back/showBackCourse")
    public AjaxResult showBackCourse(HttpServletRequest request,HttpServletResponse response){
        AjaxResult ar = new AjaxResult();
        String courseId = request.getParameter("courseId");
        Course course = new Course();
        course.setId(Integer.parseInt(courseId));
        try {
            List<Course> courses = courseService.selectByParams(course);
            String s = courses.get(0).getCoverImageUrl();
            ar.setSuccess(true);
            ar.setObj(courses.get(0));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }
}
