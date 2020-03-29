package com.itany.netClass.controller;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.constant.Constant;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.CommonUtil.MyImage;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 示例Controller
 * @author teacher
 * @date 2018-8-22
 */
@RequestMapping("/user")
public class UserController {
	private UserService userService = ObjectFactory.getObject("userService");

	@RequestMapping("/regist")
	@ResponseBody
	public AjaxResult regist(HttpServletRequest request,HttpServletResponse response){
		AjaxResult ar=AjaxResult.isOk("注册成功");
		//请求参数封装指定对象
		User user=new User();
		CommonUtil.getObj(request,user,null);

		//数据格式验证
		if(CommonUtil.isNull(user.getLoginName())){
			ar.setMsg("登录名不可为空");
			ar.setSuccess(false);
			return ar;
		}
//		String loginName2="[a-zA-Z0-9_]+";
//		if(!loginName.matches(loginName2)){
//			ar.setMsg("登录名格式出错");
//			ar.setSuccess(false);
//			return ar;
//		}
		if(CommonUtil.isNull(user.getPassword())||CommonUtil.isNull(user.getRePassword())){
			ar.setMsg("密码不可为空");
			ar.setSuccess(false);
			return ar;
		}
//		if(password!=repassword){
//			ar.setMsg("两次密码输入不一致");
//			ar.setSuccess(false);
//			return ar;
//		}
//		String password2="[a-zA-z0-9]+";
//		if(!password.matches(password2)&&!repassword.matches(password2)){
//			ar.setMsg("密码格式出错");
//			ar.setSuccess(false);
//			return ar;
//		}
		if(CommonUtil.isNull(user.getNickname())){
			ar.setMsg("昵称不可为空");
			ar.setSuccess(false);
			return ar;
		}
//		String nickename2="[a-zA-z0-9]{1,60}";
//		if(!nickname.matches(nickename2)){
//			ar.setMsg("昵称格式出错");
//			ar.setSuccess(false);
//			return ar;
//		}
//		String email2="^[a‐z]+@[a‐z0‐9]+(.com)(.cn)";
//		if(!email.matches(email2)){
//			ar.setMsg("邮箱格式出错");
//			ar.setSuccess(false);
//			return ar;
//		}

		try {
			userService.add(user);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMsg(e.getMessage());
			ar.setSuccess(false);
		}
		return ar;
	}

	@RequestMapping("/login")
	@ResponseBody
	public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("登录成功");

		try {
			//调用service方法
			HttpSession session = request.getSession();
			System.out.println("sessionId=" + session.getId());
			ar.setSuccess(true);
			ar.setMsg("登录成功");

			User user=new User();
			CommonUtil.getObj(request,user,null);
			//登录时密码不回jsp
			User loginUser=userService.login(user);
//			loginUser.setPassword(null);
			ar.setObj(loginUser);

		} catch (Exception e) {
			e.printStackTrace();
			ar.setSuccess(false);
			ar.setMsg(e.getMessage());
		}
		return ar;
	}

	@RequestMapping("/ajaxRegist")
	@ResponseBody
	public AjaxResult upload(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> files) throws Exception {
		AjaxResult ar = AjaxResult.isOk("成功");

		if(null != files && !files.isEmpty()){
			for (CommonsMultipartFile f : files) {
				System.out.println(f);
				System.out.println(f.isEmpty() ? "空" : f.getOriginalFilename());
			}
		}
		return ar;
	}

	@RequestMapping("/getCodeImage")
	public void getCodeImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String numStr = request.getParameter("num");
		MyImage m = null;
		if(null == numStr){
			numStr = "0";
		}
		int num = 0;
		try {
			num = Integer.parseInt(numStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (num) {
			case 0:
				String codeStr = request.getParameter("codeStr");
				if(null == codeStr){
					codeStr = CommonUtil.randomCode();
				}
				//用自己的字符串来生成图片
				m = CommonUtil.getImage(null, codeStr, true);
				break;
			case 1:
				//使用中文验证码,有干扰线
				m = CommonUtil.getImage(null, 4, true, true);
				break;
			case 2:
				//使用中文验证码,没有干扰线
				m = CommonUtil.getImage(null, 4, true, false);
				break;
			case 3:
				//使用数字和字母(4个)验证码,有干扰线
				m = CommonUtil.getImage(null, 4, false, true);
				break;
			case 4:
				//使用数字和字母(4个)验证码,没有干扰线
				m = CommonUtil.getImage(null, 4, false, false);
				break;
			default:
				//使用数字和字母验证码,有干扰线
				m = CommonUtil.getImage(null, num, false, true);
				break;
		}
		System.out.println("code=" + m.getCode());
		request.getSession().setAttribute("code", m.getCode());
		ServletOutputStream out = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(m.getImage());
	}

}
