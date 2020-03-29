package com.itany.nmms.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.nmms.constant.DictConstant;
import com.itany.nmms.constant.ResponseCodeConstant;
import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.exception.ProductTypeExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductTypeService;
import com.itany.nmms.util.ParameterUtil;
import com.itany.nmms.util.ResponseResult;

@RequestMapping("/type")
public class ProductTypeAction {

	private ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeService");
	
	//@ResponseBody该注解表示当前方法返回的就是响应对象,而不是做页面的跳转
	@ResponseBody
	@RequestMapping("/addType")
	public ResponseResult addType(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();

		String name = request.getParameter("name");
		
		try {
			typeService.addType(name);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
		} catch (ProductTypeExistException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {

		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		
		if(ParameterUtil.isNull(pageNo)){
			pageNo = DictConstant.PAGE_NO_DEFAULT;
		}
		if(ParameterUtil.isNull(pageSize)){
			pageSize = DictConstant.PAGE_SIZE_DEFAULT;
		}
		
		//开始使用分页插件
		//1.设置分页属性,当前页与一页显示多少条
		PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		
		//2.查询业务处理的数据
		List<ProductType> types = typeService.findAll();
		
		//3.对业务数据进行加工，封装成分页对象
		PageInfo<ProductType> pageTypes = new PageInfo<ProductType>(types);
		
		request.setAttribute("pageTypes", pageTypes);
		
		return "backend/productTypeManager";
	}
	
	@ResponseBody
	@RequestMapping("/findById")
	public ResponseResult findById(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();

		String id = request.getParameter("id");
		
		try {
			ProductType type = typeService.findById(id);
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setReturnObject(type);
		} catch (ParameterException e) {
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMTER_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/modifyName")
	public String method(HttpServletRequest request,
			HttpServletResponse response) {
		
		String pageNo = request.getParameter("pageNo");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		try {
			typeService.modifyType(id, name);
		} catch (ProductTypeExistException e) {
			request.setAttribute("typeMsg", e.getMessage());
		}
		
		return "findAll.do";
	}
	
	@ResponseBody
	@RequestMapping("/modifyStatus")
	public ResponseResult modifyStatus(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();

		String id = request.getParameter("id");
		String status = request.getParameter("status");
		
		typeService.modifyStatus(id, status);
		result.setMessage("成功");
		result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
		return result;
	}
	
}
