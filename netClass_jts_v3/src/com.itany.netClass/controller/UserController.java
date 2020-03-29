package com.itany.netClass.controller;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netClass.entity.User;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.filters.ApplicationLisenter;
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
import java.util.List;
import java.util.Map;

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

		try {
			userService.add(user);
			ar.setSuccess(true);
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
			loginUser.setPassword(null);
			ar.setObj(loginUser);

			//登录时将对应的user传给监听器，后面登出可以删除删除，检查可以调用
			Map<String,User> userMap=ApplicationLisenter.getMap();
			userMap.put(loginUser.getLoginName(),loginUser);

		} catch (Exception e) {
			e.printStackTrace();
			ar.setSuccess(false);
			ar.setMsg(e.getMessage());
		}
		return ar;
	}

	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar=AjaxResult.isOk("修改成功");
		User user=new User();
		CommonUtil.getObj(request,user,null);

		try {
//			System.out.println(user);
			userService.modifyById(user);
			ar.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setSuccess(false);
			ar.setMsg(e.getMessage());
		}
		return ar;
	}
	//登出，清除浏览器的数据(清除session)
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		//获取登录用户名，
		//通过监听器在此时时获取整个应用的方法
		//用map集合获取监听器获取的内容和用户名关联
		//清除用户名，返回index
		String loginName=request.getParameter("loginName");
		Map<String,User> userMap=ApplicationLisenter.getMap();
		userMap.remove(loginName);

//		//清空所有session区域中的数据
//		request.getSession().invalidate();
		return "redirect:/showIndex.do";
	}

	//检查，刷新后全局查是否有session，有则表示登录了
	@RequestMapping("/check")
	@ResponseBody
	public AjaxResult check(HttpServletRequest request, HttpServletResponse response){
		AjaxResult ar = AjaxResult.isOk("已登录");
//		String loginName=request.getParameter("loginName");
//		System.out.println(loginName);
//		HttpSession session=request.getSession(false);
//		System.out.println(session.getId());
//		if(loginName!="1"){
//			ar.setMsg("未登录");
//			ar.setSuccess(false);
//			return ar;
//		}
//		ar.setObj(session);

//		System.out.println(session+"111");
//		if(session==null){
//			ar.setMsg("未登录");
//			ar.setSuccess(false);
//			return ar;
//		}
//		ar.setObj(session);



//		UserMapper userMapper = ObjectFactory.getObject("userMapper");
//		userMapper.selectNoSame(String.,null);


		//以上gg
		//用监听器获取应用
		Map<String,User> userMap=ApplicationLisenter.getMap();
		String loginName=request.getParameter("loginName");
		System.out.println(loginName+"2");
		User user=userMap.get(loginName);
		System.out.println(user);
		if(user==null){
			ar.setMsg("未登录");
			ar.setSuccess(false);
			return ar;
		}
		ar.setObj(user);
		return ar;
	}

	//签到，通过接收到的loginName带到监听器中获取对应User,判断用户是否登录,对应id传给service
	@RequestMapping("/sign")
	@ResponseBody
	public AjaxResult sign(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ar = AjaxResult.isOk("签到成功");
		try {
			String loginName=request.getParameter("loginName");
			Map<String,User> userMap=ApplicationLisenter.getMap();
			User loginUser=userMap.get(loginName);
			if(loginUser==null){
				ar.setMsg("用户未登录");
				ar.setSuccess(false);
				return ar;
			}
			userService.sign(loginUser.getId());
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
