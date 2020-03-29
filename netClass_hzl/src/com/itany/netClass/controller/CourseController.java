package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.entity.Course;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseExistException;
import com.itany.netClass.exception.CourseNotExistException;
import com.itany.netClass.exception.CourseTypeNotExistException;
import com.itany.netClass.exception.FileUploadException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseService;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.ParameterUtil;
import oracle.sql.TIMESTAMP;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/course")
public class CourseController extends HttpServlet {
    CourseTypeService courseTypeService=ObjectFactory.getObject("courseTypeService");
    CourseService courseService=ObjectFactory.getObject("courseService");

    @ResponseBody
    @RequestMapping("/addCourse")                   //controller中文件上传的方法一定有下面这三个类型的参数
    public AjaxResult addCourse(HttpServletRequest req, HttpServletResponse resp,List<CommonsMultipartFile> images) {
        AjaxResult.isOk("成功");
        AjaxResult ar=new AjaxResult();
        String courseName=req.getParameter("course_name");
        String courseInfo=req.getParameter("course_info");
        String author=req.getParameter("author");
        Integer clickNum=Constant.CLICKNUM_DEFAULT;
        Integer status=Constant.STATUS_ENABLE;
        String recommendGrade=req.getParameter("recommendation_grade");
        String courseTypeId=req.getParameter("course_type_id");
     //   Date createDate=new Date();

        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Timestamp timestamp=new Timestamp(format.parse(time).getTime());

            courseService.insertCourse(courseName,courseInfo,author,
                    timestamp,clickNum,status,recommendGrade,
                    courseTypeId,images, req.getSession());
        } catch (FileUploadException e) {
            e.printStackTrace();
         //   req.getSession().setAttribute("courseMsg",e.getMessage());
            ar.setMsg(e.getMessage());
        } catch (CourseExistException e) {
            e.printStackTrace();
            ar.setMsg(e.getMessage());
        //    req.getSession().setAttribute("courseMsg",e.getMessage());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ar;
    }

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest req, HttpServletResponse resp){
        String pageNo=req.getParameter("pageNo");
        String pageNum=req.getParameter("pageNum");
        if(ParameterUtil.isNull(pageNo)){
            pageNo=Integer.toString(Constant.PAGE_START);//默认起始页
        }
        if(ParameterUtil.isNull(pageNum)){
            pageNum=Integer.toString(Constant.PAGE_SIZE);//默认每页页数
        }
        try {
            List<CourseType> courseTypes=courseTypeService.findEnable();
            PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageNum));
            System.out.println(courseTypes);
            req.setAttribute("courseTypes",courseTypes);
           List<Course> courses=courseService.findAll();
            PageInfo<Course> pageCourses = new PageInfo<Course>(courses);
           req.setAttribute("courses",courses);
            req.setAttribute("pageCourses",pageCourses);
        } catch (CourseTypeNotExistException e) {
            e.printStackTrace();
        }

        return "back_courseSet";
    }

    /**
     * 在点击页面修改按钮后弹出的模态框回显数据
     */
    @ResponseBody
    @RequestMapping("/findById")
    public AjaxResult findById(HttpServletRequest req, HttpServletResponse res){
       String id=req.getParameter("courseId");
        AjaxResult result =new AjaxResult();
        try {
            Course course=courseService.findById(Integer.parseInt(id));
            result.setMsg("成功");
            result.setObj(course);
        } catch (CourseNotExistException e) {
            e.printStackTrace();
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 模态框修改
     * @param req
     * @param resp
     * @param images
     * @return
     */
    @ResponseBody
    @RequestMapping("/modifyCourse")
    public AjaxResult modifyCourse(HttpServletRequest req, HttpServletResponse resp,List<CommonsMultipartFile> images) {
        AjaxResult ar=new AjaxResult();
        String id= req.getParameter("course_id");
        String courseName=req.getParameter("course_name");
        String courseInfo=req.getParameter("course_info");
        String author=req.getParameter("author");
        String recommendGrade=req.getParameter("recommendation_grade");
        String courseTypeId=req.getParameter("course_type_id");

        try {
            courseService.modifyCourse(Integer.parseInt(id),courseName,courseInfo,author,
                    Integer.parseInt(recommendGrade),Integer.parseInt(courseTypeId),images,req.getSession());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return ar;
        // return "redirect:/course/findAll.do";
    }

    @ResponseBody
    @RequestMapping("/changeStatus")
    public AjaxResult changeStatus(HttpServletRequest req,HttpServletResponse res){
        AjaxResult ar=new AjaxResult();
        String id=req.getParameter("courseId");
        courseService.changeStatus(Integer.parseInt(id));
        return ar;
    }
}
