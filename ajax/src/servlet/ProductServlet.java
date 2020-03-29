package servlet;

import entity.Product;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/10/24 16:10
 * Description:
 * version:1.0
 */
@WebServlet("*.product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String path = request.getServletPath();
        if("/findAll.product".equals(path)){
            findAll(request,response);
        }
        if("/findById.product".equals(path)){
            findById(request,response);
        }
    }

    protected void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf8";
        Product product = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"root","");
            String sql = new StringBuffer()
                    .append(" select id,name,price ")
                    .append(" from t_product")
                    .append(" where id = ? ")
                    .toString();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            rs = ps.executeQuery();
            while(rs.next()){
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
            }
//            out.print("{'id':"+product.getId()+",'name':'"+product.getName()+"','price':"+product.getPrice()+"}");
            //fromObject(Object)将一个java对象转换成json格式的对象
            //toString():将一个json格式的对象转换成json格式的字符串
            String jsonProduct = JSONObject.fromObject(product).toString();
            out.print(jsonProduct);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf8";
        List<Product> products = new ArrayList<Product>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"root","");
            String sql = new StringBuffer()
                    .append(" select id,name,price ")
                    .append(" from t_product")
                    .toString();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }
            request.setAttribute("products",products);
//            request.getRequestDispatcher("/list.jsp").forward(request,response);
            request.getRequestDispatcher("/jquery/list.jsp").forward(request,response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
