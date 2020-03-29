<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018/10/25
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsonp</title>
    <script type="text/javascript" src="http://127.0.0.1:8080/ajax/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
           $.ajax({
              url:"http://127.0.0.1:8080/ajax/jp",
              jsonp:"asdsasd",
              dataType:"jsonp",
               success:function(data){
                  console.log(data);
               }
           });
        });
    </script>
</head>
<body>
    <a href="javaScript:;" >测试</a>
</body>
</html>
