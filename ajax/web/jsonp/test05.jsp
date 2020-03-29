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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function test(a){
            console.log(a);
        }
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jp"></script>
</head>
<body>
    <a href="javaScript:;" onclick="f1()">测试</a>
</body>
</html>
