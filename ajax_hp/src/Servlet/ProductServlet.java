package Servlet;

import entity.Product;
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

@WebServlet("*.product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html,chardet=utf-8");
        String path=req.getServletPath();
        if("/findAll.product".equals(path)){
            findAll(req,resp);
        }
        if("/findById.product".equals(path)){
            findById(req,resp);
        }
    }

    private void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product=null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf-8";
            conn = DriverManager.getConnection(url, "root", "");
            String sql = new StringBuffer()
                    .append(" select id,name,price from t_product ").toString();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
            }
            String jsonProduct=JSONObject.fromObject(product).toString();
            out.print(jsonProduct);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<Product> products=new ArrayList<Product>();
        System.out.println("2");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf-8";
            conn=DriverManager.getConnection(url,"root","");
            String sql=new StringBuffer()
                    .append(" select id,name,price from t_product ").toString();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Product product=new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }

            req.setAttribute("products",products);
            req.getRequestDispatcher("/list.jsp").forward(req,resp);
            System.out.println("3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
