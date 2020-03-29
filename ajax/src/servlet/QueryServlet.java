package servlet;

import entity.Data;
import entity.Product;
import net.sf.json.JSONArray;

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

/**a
 * Author:shixiaojun@itany.com
 * Date:2018/10/25 10:55
 * Description:
 * version:1.0
 */
@WebServlet("/query")
public class QueryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        String key = req.getParameter("key");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf8";
        List<Data> datas = new ArrayList<Data>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"root","");
            String sql = new StringBuffer()
                    .append(" select id,spell,message ")
                    .append(" from t_data ")
                    .append(" where spell like ? ")
                    .append(" or message like ? ")
                    .toString();
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+key+"%");
            ps.setString(2,"%"+key+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                Data data = new Data();
                data.setId(rs.getInt("id"));
                data.setSpell(rs.getString("spell"));
                data.setMessage(rs.getString("message"));
                datas.add(data);
            }

            String datasJson = JSONArray.fromObject(datas).toString();
            out.print(datasJson);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
