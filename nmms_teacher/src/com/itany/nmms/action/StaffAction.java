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
import com.itany.nmms.exception.CodeErrorException;
import com.itany.nmms.exception.StaffExistException;
import com.itany.nmms.exception.StaffNotExistException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.DeptService;
import com.itany.nmms.service.StaffService;
import com.itany.nmms.util.ParameterUtil;
import com.itany.nmms.util.ResponseResult;
import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer.Param;

@RequestMapping("/staff")
public class StaffAction {

	private StaffService staffService = (StaffService) ObjectFactory.getObject("staffService");
	private DeptService deptService = (DeptService) ObjectFactory.getObject("deptService");
	
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,
			HttpServletResponse response) {

		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String code = request.getParameter("code");
		
		try {
			Staff staff = staffService.login(loginName, password, role, code, request.getSession());
			request.getSession().setAttribute("staff", staff);
			//方法返回值中以rediect:
			//表示使用重定向，重定向命令为冒号后面的内容
			return "redirect:/showMain.do";
		} catch (CodeErrorException e) {
			request.setAttribute("loginMsg", e.getMessage());
			//如果方法返回值是一个字符串,则表示直接访问对应的jsp页面
			//加上前缀与后缀去访问
			//例如:return "backend/login"
			//就表示访问/WEB-INF/pages/backend/login.jsp
			return "backend/login";
		} catch (StaffNotExistException e) {
			request.setAttribute("loginMsg", e.getMessage());
			//如果返回的字符串没有以rediect:开头
			//且后缀为.do
			//则表示以转发的方式访问该命令
			return "/showLogin.do";
		}
				
	}
	
	@RequestMapping("/findByParams")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {

		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		String staffName = request.getParameter("staffName");
		String loginName = request.getParameter("loginName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String deptId = request.getParameter("deptId");
		String role = request.getParameter("role");
		String isValid = request.getParameter("isValid");
		
		Staff staff = new Staff();
		staff.setStaffName(staffName);
		staff.setLoginName(loginName);
		staff.setPhone(phone);
		staff.setEmail(email);
		if(!ParameterUtil.isNull(deptId)){
			staff.setDeptId(Integer.parseInt(deptId));
		}
		staff.setRole(role);
		if(!ParameterUtil.isNull(isValid)){
			staff.setIsValid(Integer.parseInt(isValid));
		}
		
		
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
		List<Staff> staffs = staffService.findByParams(staffName, loginName, phone, email, deptId, role, isValid);
		//3.对业务数据进行加工，封装成分页对象
		PageInfo<Staff> pageStaffs = new PageInfo<Staff>(staffs);
		
		
		List<Dept> depts = deptService.findEnable();
		request.setAttribute("depts", depts);
		request.setAttribute("pageStaffs", pageStaffs);
		request.setAttribute("staffParam", staff);
		return "backend/staffManager";
	}
	
	
	@ResponseBody
	@RequestMapping("/addStaff")
	public ResponseResult addStaff(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();

		String staffName = request.getParameter("staffName");
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String deptId = request.getParameter("deptId");
		Staff currStaff = (Staff) request.getSession().getAttribute("staff");
		
		try {
			staffService.addStaff(staffName, loginName, password, phone, email, role, deptId, currStaff);
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setMessage("成功");
		} catch (StaffExistException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage("服务器内部异常");
		} 
		
		
		return result;
	}
	
}
