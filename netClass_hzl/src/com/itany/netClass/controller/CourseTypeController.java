package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.ParameterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/course")
public class CourseTypeController {
    @RequestMapping("/findAllCourse")
    public String findFirstCourse(HttpServletRequest request, HttpServletResponse response) {
        CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeService");

        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if (ParameterUtil.isNull(pageNo)) {
            pageNo = "1";
        }
        if (ParameterUtil.isNull(pageSize)) {
            pageSize = "5";
        }
        try {
            PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
            List<CourseType> lists = courseTypeService.findFirstCourse();
            PageInfo<CourseType> courses = new PageInfo<CourseType>(lists);
            request.setAttribute("courses", courses);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "back_courseTypeSet";
    }

    @RequestMapping("/findSon")
    public String findSonCourseType(HttpServletRequest request, HttpServletResponse response) {
        CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeService");

        String id = request.getParameter("id");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if (ParameterUtil.isNull(pageNo)) {
            pageNo = "1";
        }
        if (ParameterUtil.isNull(pageSize)) {
            pageSize = "5";
        }
        try {
            PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
            List<CourseType> list = courseTypeService.findSonCourseType(id);
            PageInfo<CourseType> courses = new PageInfo<CourseType>(list);
            request.setAttribute("courses", courses);
            if (id != null && !"".equals(id)) {
                request.setAttribute("id", Integer.parseInt(id));
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "back_courseTypeSet";
    }

    @RequestMapping("/findParent")
    public String findParentCourseType(HttpServletRequest request, HttpServletResponse response) {
        CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeService");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if (ParameterUtil.isNull(pageNo)) {
            pageNo = "1";
        }
        if (ParameterUtil.isNull(pageSize)) {
            pageSize = "5";
        }
        String sid = request.getParameter("id");
        Integer id = Integer.parseInt(sid);
        try {
            PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
            List<CourseType> list = courseTypeService.findParentCourseType(id);
            PageInfo<CourseType> courses = new PageInfo<CourseType>(list);
            request.setAttribute("courses", courses);
            request.setAttribute("id", list.get(0).getParentId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "back_courseTypeSet";
    }

    @ResponseBody
    @RequestMapping("/addType")
    public AjaxResult addCourseType(HttpServletRequest request, HttpServletResponse response) {
        CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeService");
        AjaxResult ar = new AjaxResult();
        String typeName = request.getParameter("name");
        String parentId = request.getParameter("parentId");
        String id = request.getParameter("id");
        try {
            courseTypeService.addCourseType(typeName, parentId, id);
            ar.setSuccess(true);
            return ar;
        } catch (Exception e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }

    @ResponseBody
    @RequestMapping("/changeStatusType")
    public AjaxResult changeCourseTypeStatus(HttpServletRequest request, HttpServletResponse response) {
        CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeService");
        AjaxResult ar = new AjaxResult();
        String sid = request.getParameter("id");
        Integer id = Integer.parseInt(sid);
        try {
            courseTypeService.changeCourseTypeStatus(id);
            ar.setSuccess(true);
            return ar;
        } catch (Exception e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }

    @ResponseBody
    @RequestMapping("/findThis")
    public AjaxResult findThis(HttpServletRequest request, HttpServletResponse response) {
        CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeService");
        AjaxResult ar = new AjaxResult();
        String sid = request.getParameter("id");
        Integer id = Integer.parseInt(sid);
        try {
            CourseType courseType = courseTypeService.findThisType(id);
            ar.setSuccess(true);
            ar.setObj(courseType);
            return ar;
        } catch (Exception e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;

    }
}
