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
 * Date:2018/10/25 16:29
 * Description:
 * version:1.0
 */
@WebServlet("/jp")
public class JSONPServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String callback = req.getParameter("callback");
        out.print(callback+"('Hello JSONP')");
    }
}
