package com.itany.netClass.controller;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.UserService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.itany.netClass.util.CommonUtil.MyImage;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 示例Controller
 * @author teacher
 * @date 2018-8-22
 */
@RequestMapping("/user")
public class UserController {

//	private UserService userService = ObjectFactory.getObject("userService");
	
	@RequestMapping("/login")
	@ResponseBody
	public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = new AjaxResult();
		
		try {
			//调用service方法
			HttpSession session = request.getSession();
			System.out.println("sessionId=" + session.getId());
			ar.setSuccess(true);
			ar.setMsg("登录成功");
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
		User user = new User();
		//把请求中的参数封装成指定的对象
		//如果参数没有对应的属性则会抛出异常
		CommonUtil.getObj(request,user,null);
		System.out.println("user=" + user.toStr());
		System.out.println("resource=" + user.getResource().getName());
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
