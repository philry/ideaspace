<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-10-25
  Time: 下午5:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>查找用户</title>
    <script type="text/javascript" src="${pfindageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
    <script>
        $(function(){
            $(li).mouseover(function(){
                $("#d").show();
                $.ajax({
                    url:"${pageContext.request.contextPath}/find",
                    data:{"id":$(this).attr("user-id")},
                    dataType:"Json",
                    success:function(users){
                        $("#s1").html(users.id);
                        $("#s2").html(users.name);
                        $("#s3").html(users.password);
                        $("#s4").html(users.phone);
                        $("#s5").html(users.address);
                    }
                })
            });
        });
    </script>
</head>
<body>
    <ul >
        <c:forEach items="${users}" var="user" >
            <li user-id="${user.id}">${user.username}</li>
        </c:forEach>
    </ul>
    <hr/>
    <div style="background-color: dimgray;width:20%;display: none;padding: 5px;position:absolute;" id="d"></div>
    编号：<span id="s1"></span><br/>
    姓名：<span id="s2"></span><br/>
    密码：<span id="s3"></span><br/>
    电话：<span id="s4"></span><br/>
    地址：<span id="s5"></span><br/>
</body>
</html>
