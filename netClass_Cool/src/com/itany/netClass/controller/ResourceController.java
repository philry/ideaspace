package com.itany.netClass.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.entity.Resource;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.ResourceService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.ParameterUtil;

/**
 * 用户资源管理
 * */
@RequestMapping("/resource")
public class ResourceController {
	private ResourceService resourceService = ObjectFactory.getObject("resourceService");
	/**
	 * 查找所有用户资源的方法
	 * */
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSzie");
		// 每页3条数据
		pageSize = "3";
		if (ParameterUtil.isNull(pageNo)) {
			// 如果请求中没有默认页面参数 给个默认值
			pageNo = Constant.BACKEND_PAGE_NO_DEFAULT;
		}
		if (ParameterUtil.isNull(pageSize)) {
			// 如果请求中没有默认一页显示几条信息 给个默认值
			pageSize = Constant.BACKEND_PAGE_SIZE_DEFAULT;
		}
		PageHelper.startPage(Integer.parseInt(pageNo),
				Integer.parseInt(pageSize));
			List<Resource> resources = resourceService.findAll();
			PageInfo<Resource> pageResources = new PageInfo<Resource>(resources);
			request.setAttribute("pageResources", pageResources);
				return "backend/back_resourceSet";
	}
	/**
	 * 修改用户资源状态
	 * */
	@ResponseBody
	@RequestMapping("/modifyStatus")
	public AjaxResult modifyStatus(HttpServletRequest request,
			HttpServletResponse response) {
			AjaxResult ar = new AjaxResult();
			String id = request.getParameter("id");
			resourceService.modify(Integer.parseInt(id));
			ar.setSuccess(true);
			ar.setMsg("修改成功");
			return ar;	
	}
	


}
