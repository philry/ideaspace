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
import com.itany.nmms.entity.Dept;
import com.itany.nmms.entity.Staff;
import com.itany.nmms.exception.DeptException;
import com.itany.nmms.exception.DeptExistException;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.DeptService;
import com.itany.nmms.util.ParameterUtil;
import com.itany.nmms.util.ResponseResult;

@RequestMapping("/dept")
public class DeptAction {
	
	private DeptService deptService = (DeptService) ObjectFactory.getObject("deptService");
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addFatherDept")
	public ResponseResult addFatherDept(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();

		String name = request.getParameter("name");
		String remark = request.getParameter("remark");
		
		Staff staff = (Staff) request.getSession().getAttribute("staff");
		
		try {
			deptService.addFatherDept(name, remark, staff);
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
		} catch (DeptExistException e) {
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

		if (ParameterUtil.isNull(pageNo)) {
			pageNo = DictConstant.PAGE_NO_DEFAULT;
		}
		if (ParameterUtil.isNull(pageSize)) {
			pageSize = DictConstant.PAGE_SIZE_DEFAULT;
		}

		//开始使用分页插件
		//1.设置分页属性,当前页与一页显示多少条
		PageHelper.startPage(Integer.parseInt(pageNo),
				Integer.parseInt(pageSize));

		//2.查询业务处理的数据
		List<Dept> depts = deptService.findAll();

		//3.对业务数据进行加工，封装成分页对象
		PageInfo<Dept> pageDepts = new PageInfo<Dept>(depts);
		request.setAttribute("pageDepts", pageDepts);
		
		return "backend/deptManager";
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addSonDept")
	public ResponseResult addSonDept(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();

		String name = request.getParameter("name");
		String remark = request.getParameter("remark");
		String fatherId = request.getParameter("fatherId");
		
		Staff staff = (Staff) request.getSession().getAttribute("staff");
		
		try {
			deptService.addSonDept(fatherId, name, remark, staff);
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
		} catch (DeptExistException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		}
		
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/findById")
	public ResponseResult findById(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();
		
		String id = request.getParameter("id");
		
		try {
			Dept dept = deptService.findById(id);
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setReturnObject(dept);
		} catch (ParameterException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMTER_ERROR);
			result.setMessage(e.getMessage());
		}

		return result;
	}
	
	@ResponseBody
	@RequestMapping("/modifyDept")
	public ResponseResult modifyDept(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String remark = request.getParameter("remark");
		
		try {
			deptService.modifyDept(id, name, remark);
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
		} catch (DeptExistException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		}
				
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/modifyStatus")
	public ResponseResult modifyStatus(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();

		String id = request.getParameter("id");
		
		try {
			deptService.modifyStatus(id);
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
		} catch (DeptException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

}
