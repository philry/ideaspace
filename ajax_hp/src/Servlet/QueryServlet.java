package Servlet;

import entity.Data;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("*.data")
public class QueryServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String path=req.getServletPath();
        if("/show.data".equals(path)){
            show(req,resp);
        }
    }

    protected void show(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String key=req.getParameter("key");
        PrintWriter out=resp.getWriter();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String url="jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf-8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url,"root","");
            String sql= new StringBuffer()
            .append(" select id,spell,message from t_data where spell like ? or message like ?")
            .toString();
            ps=conn.prepareStatement(sql);
            ps.setString(1,"%"+key+"%");
            ps.setString(2,"%"+key+"%");
            rs=ps.executeQuery();
            ArrayList datas=new ArrayList();
            while (rs.next()) {
                Data data=new Data();
                data.setId(rs.getInt("id"));
                data.setSpell(rs.getString("spell"));
                data.setMessage(rs.getString("message"));
                datas.add(data);
            }
            String datasJson=JSONArray.fromObject(datas).toString();
            out.print(datasJson);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
