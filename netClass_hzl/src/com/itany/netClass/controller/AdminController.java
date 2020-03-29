package com.itany.netClass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netClass.entity.User;
import com.itany.netClass.exception.CodeNotWriteException;
import com.itany.netClass.exception.MessageIsNullException;
import com.itany.netClass.exception.adminNotExistException;
import com.itany.netClass.factory.ObjectFactory;
import com.itany.netClass.service.AdminService;
import com.itany.netClass.util.AjaxResult;
import com.itany.netClass.util.CommonUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/admin")
public class AdminController {
    AdminService adminService = ObjectFactory.getObject("adminService");

    @RequestMapping("/findAllUser")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        String pageNum = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if (pageNum == null) {
            pageNum = "1";
        }
        if (pageSize == null) {
            pageSize = "5";
        }
        try {
            PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
            List<User> list = adminService.findAllUsers();
            PageInfo<User> users = new PageInfo<User>(list);
            request.setAttribute("users", users);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "back_userSet";
    }


    @RequestMapping("/adminLogin")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String image = request.getSession().getAttribute("code").toString();

        try {
            User admin = adminService.login(name, password, code, image);
            request.getSession().setAttribute("admin", admin);
        } catch (CodeNotWriteException e) {
            e.printStackTrace();
        } catch (adminNotExistException e) {
            e.printStackTrace();
        }

        return "/home.do";


    }

    @ResponseBody
    @RequestMapping("/modifyUser")
    public AjaxResult modifyName(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = new AjaxResult();
        String sid = request.getParameter("id");
        Integer id = Integer.parseInt(sid);
        String nickname = request.getParameter("nickname");
        String role = request.getParameter("rolename");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        try {
            adminService.modifyUserMessage(id, nickname, role, password, email);
            ar.setSuccess(true);

        } catch (MessageIsNullException e) {
            e.printStackTrace();
            ar.setSuccess(false);
            ar.setMsg("修改失败");
        }

        return ar;
    }

    @ResponseBody
    @RequestMapping("/findThis")
    public AjaxResult findThis(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = new AjaxResult();
        String sid = request.getParameter("id");
        Integer id = Integer.parseInt(sid);
        try {
            User user = adminService.findUserMessage(id);
            ar.setSuccess(true);
            ar.setObj(user);
        } catch (MessageIsNullException e) {
            e.printStackTrace();
            ar.setSuccess(false);

        }
        return ar;
    }

    @ResponseBody
    @RequestMapping("/banned")
    public AjaxResult modifyStatus(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ar = new AjaxResult();
        String sid = request.getParameter("id");
        Integer id = Integer.parseInt(sid);
        try {
            adminService.changeUserStatus(id);
            ar.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ar.setSuccess(false);
        }
        return ar;
    }

    @RequestMapping("/getCodeImage")
    public void getCodeImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String numStr = request.getParameter("num");
        CommonUtil.MyImage m = null;
        if (null == numStr) {
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
                if (null == codeStr) {
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

