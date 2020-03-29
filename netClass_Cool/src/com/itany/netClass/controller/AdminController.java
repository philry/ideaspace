package com.itany.netClass.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.constant.ResponseCodeConstant;
import com.itany.netClass.entity.Admin;
import com.itany.netClass.exception.AdminIsNotException;
import com.itany.netClass.exception.AdminNotExistException;
import com.itany.netClass.exception.CodeErrorException;
import com.itany.netClass.exception.EmailSameException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.CommonUtil.MyImage;
import com.itany.netClass.util.ParameterUtil;
import com.itany.netClass.util.ResponseResult;
import com.itany.netClass.vo.AdminQuery;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 后台管理员登录界面
 * 
 * @author ligang
 * @date 2019-09-18
 */
@RequestMapping("/admin")
public class AdminController {

	private AdminService adminService = ObjectFactory.getObject("adminService");

	/**
	 * 根据条件模糊查询所有用户
	 * */
	@RequestMapping("/doSerach")
	public String doSerach(HttpServletRequest request,
			HttpServletResponse response) {
		String loginname = request.getParameter("loginname");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String createDate = request.getParameter("createDate");
		String removeDate = request.getParameter("removeDate");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		try {
			List<Admin> admins = adminService.doSerach(loginname, nickname,
					email, role, createDate, removeDate, startDate, endDate);
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
			// 利用分页插件分页
			PageHelper.startPage(Integer.parseInt(pageNo),
					Integer.parseInt(pageSize));

			PageInfo<Admin> pageAdmins = new PageInfo<Admin>(admins);
			request.setAttribute("pageAdmins", pageAdmins);
			return "backend/back_userSet";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "backend/back_userSet";
	}

	/**
	 * 修改用户信息
	 * */
	@ResponseBody
	@RequestMapping("/modifyAdmin")
	public ResponseResult modifyAdmin(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult responseResult = new ResponseResult();
		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
	//	System.out.println(id + "---" + nickname + "---" + email + "---" + role
			//	+ "------" + password);
		try {
			adminService.modifyAdmin(nickname, role, password, email,
					Integer.parseInt(id));
			responseResult
					.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			responseResult.setMessage("修改成功");
		} catch (NumberFormatException e) {
			responseResult
					.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			responseResult.setMessage(e.getMessage());
		} catch (EmailSameException e) {
			responseResult
					.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			responseResult.setMessage(e.getMessage());
		}
		return responseResult;
	}

	/**
	 * 修改用户状态功能
	 * */
	@ResponseBody
	@RequestMapping("/modifyStatus")
	public ResponseResult modifyStatus(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult responseResult = new ResponseResult();
		String adminId = request.getParameter("id");

		adminService.modifyStatus(Integer.parseInt(adminId));
		responseResult
				.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
		responseResult.setMessage("修改成功");
		return responseResult;
	}

	/**
	 * 查询所有用户功能
	 * */
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		String loginname = request.getParameter("loginname");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String createDate = request.getParameter("createDate");
		String removeDate = request.getParameter("removeDate");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
	//	System.out.println(loginname+"-------------------------------------------------");
		if (loginname != null &&!"".equals(loginname)&& nickname != null &&"".equals(nickname)&& email != null
				&&"".equals(email)&& !role.equals(-1)&&"".equals(role) && createDate != null&&"".equals(createDate)
				&& removeDate != null&&"".equals(removeDate)
				&& startDate != null &&"".equals(startDate)&& endDate != null&&"".equals(endDate)) {
			try {
				String pageNo = request.getParameter("pageNo");
				String pageSize = request.getParameter("pageSzie");
				// 每页3条数据
				pageSize = "3";
				if (ParameterUtil.isNull(pageNo)) {

					pageNo = Constant.BACKEND_PAGE_NO_DEFAULT;
				}
				if (ParameterUtil.isNull(pageSize)) {

					pageSize = Constant.BACKEND_PAGE_SIZE_DEFAULT;
				}

				PageHelper.startPage(Integer.parseInt(pageNo),
						Integer.parseInt(pageSize));
				List<Admin> admins = adminService.doSerach(loginname, nickname, email, role, createDate,
								removeDate, startDate, endDate);
				PageInfo<Admin> pageAdmins = new PageInfo<Admin>(admins);
				request.setAttribute("pageAdmins", pageAdmins);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "backend/back_userSet";
		} else {
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
			// 利用分页插件分页
			PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
			List<Admin> admins = adminService.findAll();
			PageInfo<Admin> pageAdmins = new PageInfo<Admin>(admins);
			request.setAttribute("pageAdmins", pageAdmins);
			return "backend/back_userSet";
		}

	}

	/**
	 * 后台管理员登录功能
	 * 
	 * @return
	 * */
	@RequestMapping("/login")
	public String doLogin(HttpServletRequest request,
			HttpServletResponse response) {
		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		String image = request.getParameter("code");


		String code = (String) request.getSession().getAttribute("code");
		try {
			Admin admin = adminService.login(loginname, password, code, image);
			request.getSession().setAttribute("admin", admin);
			return "redirect:/backhome.do";
		} catch (CodeErrorException e) {
			request.setAttribute("loginMsg", e.getMessage());
			return "/showLogin.do";
		} catch (AdminNotExistException e) {
			request.setAttribute("loginMsg", e.getMessage());
			return "/showLogin.do";
		} catch (AdminIsNotException e) {
			request.setAttribute("loginMsg", e.getMessage());
			return "/showLogin.do";
		}

	}

	/**
	 * 管理员退出登录功能
	 * */
	@RequestMapping("/loginOut")
	public void loginOut(HttpServletRequest request,
			HttpServletResponse response) {

		request.getSession().invalidate();
		try {
			response.sendRedirect("/net/showLogin.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
