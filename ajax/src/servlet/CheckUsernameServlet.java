package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/24 11:31
 * Description:
 * version:1.0
 */
@WebServlet("/checkUsername")
public class CheckUsernameServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");

        if("admin".equals(username)){
            out.print("<font color='red'>该用户已经存在</font>");
        }else{
            out.print("<font color='green'>用户名可用</font>");
        }


    }
}
