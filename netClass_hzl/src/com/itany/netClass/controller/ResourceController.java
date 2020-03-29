package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.ParameterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/resource")
public class ResourceController {
    @RequestMapping("/findAll")
    public String findAllResource(HttpServletRequest request, HttpServletResponse response) {
        ResourceService resourceService = ObjectFactory.getObject("resourceService");
//        String title = request.getParameter("title");
//        String userName = request.getParameter("userName");
//        String status = request.getParameter("status");
//        String type = request.getParameter("type");

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
            List<Resource> list = resourceService.selectAllResource();
            PageInfo<Resource> resources = new PageInfo<>(list);
            request.setAttribute("resources", resources);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "back_resourceSet";
    }

    @ResponseBody
    @RequestMapping("/changeStatus")
    public AjaxResult changeResourceStatus(HttpServletRequest request, HttpServletResponse response) {
        ResourceService resourceService = ObjectFactory.getObject("resourceService");
        AjaxResult ar = new AjaxResult();
        String sid = request.getParameter("id");
        Integer id = Integer.parseInt(sid);
        try {
            resourceService.changeResourceStatus(id);
            ar.setSuccess(true);
            return ar;
        } catch (Exception e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }

    @RequestMapping("/showDetail")
    public String showDetail(HttpServletRequest request, HttpServletResponse response) {
        ResourceService resourceService = ObjectFactory.getObject("resourceService");
        String sid = request.getParameter("id");
        Integer id = Integer.parseInt(sid);
        try {
            Resource resource = resourceService.findById(id);
            request.setAttribute("resource", resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "back_resourceDetailSet";
    }
}
