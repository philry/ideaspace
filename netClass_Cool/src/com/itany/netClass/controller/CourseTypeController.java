package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.entity.CourseType;
import com.itany.netClass.exception.CourseTypeExistsException;
import com.itany.netClass.exception.CourseTypeNoExistsException;
import com.itany.netClass.exception.ParameterException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.CourseTypeService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.ParameterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RequestMapping("/type")
public class CourseTypeController {
    private CourseTypeService typeService = ObjectFactory.getObject("courseTypeService");
    @RequestMapping("/findFirstLevel")
    public String findFirstLevel(HttpServletRequest request, HttpServletResponse response){
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
            List<CourseType> list = typeService.findFirstLevel();
            System.out.println(list.get(0));
            PageInfo<CourseType> types = new PageInfo<>(list);
            request.setAttribute("types",types);
            return "back_courseTypeSet";
        } catch (CourseTypeNoExistsException e) {
            e.printStackTrace();
            request.setAttribute("typeMsg",e.getMessage());
            return "back_courseTypeSet";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("typeMsg","服务器内部错误");
            return "back_courseTypeSet";
        }
    }
    @ResponseBody
    @RequestMapping("/addType")
    public AjaxResult addType(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> file){
        String typeName = request.getParameter("typeName");
        String parentId = request.getParameter("parentId");

        System.out.println("parentId="+parentId);

        try {
            typeService.addType(typeName,parentId);
            AjaxResult ar = AjaxResult.isOk("添加成功");
            return ar;
        } catch (CourseTypeExistsException e) {
            e.printStackTrace();
            AjaxResult ar = AjaxResult.isFail(e.getMessage());
            return ar;
        } catch (ParameterException e) {
            e.printStackTrace();
            AjaxResult ar = AjaxResult.isFail(e.getMessage());
            return ar;
        } catch (Exception e) {
            e.printStackTrace();
            AjaxResult ar = AjaxResult.isFail("服务器错误");
            return ar;
        }
    }

    @ResponseBody
    @RequestMapping("/findPage")
    public AjaxResult findPage(HttpServletRequest request,HttpServletResponse response,List<CommonsMultipartFile> file){
        String parentId = request.getParameter("parentId");
        System.out.println("parentId====="+parentId);
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
            List<CourseType> list = typeService.findAll(parentId);
            PageInfo<CourseType> types = new PageInfo<>(list);
            if("0".equals(types.getPages())||types.getPages()==0){
                types.setPages(Constant.PAGE_NO);
            }
            request.setAttribute("types",types);
            AjaxResult ar = AjaxResult.isOk("success", types);
            return ar;
        } catch (ParameterException e) {
            AjaxResult ar = AjaxResult.isOk("fail");
            return ar;
        } catch (Exception e) {
            AjaxResult ar = AjaxResult.isOk("server error");
            return ar;
        }
    }


}
