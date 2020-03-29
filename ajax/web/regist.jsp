<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018/10/24
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <script type="text/javascript">
        function checkUsername(){
            var xhr = new XMLHttpRequest();
            var username = document.getElementById("username").value;
            xhr.onreadystatechange=function(){
                if(xhr.status == 200 && xhr.readyState == 4){
                    var result = xhr.responseText;
                    document.getElementById("s1").innerHTML=result;
                }
            };
            <%--xhr.open("get","${pageContext.request.contextPath}/checkUsername?username="+username,true);--%>
            <%--xhr.send(null);--%>

            //post请求
            xhr.open("post","${pageContext.request.contextPath}/checkUsername",true);
            //设置请求头信息
            xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xhr.send("username="+username);

        }
    </script>

</head>
<body>
    <h1>用户注册</h1>
    <hr/>

    <form action="" method="post">
        用户名:<input type="text" id="username" onblur="checkUsername()"/><span id="s1"></span><br/>
        密&nbsp;&nbsp;&nbsp;码:<input type="password"/><br/>
        电&nbsp;&nbsp;&nbsp;话:<input type="text" id="phone"/><br/>
        地&nbsp;&nbsp;&nbsp;址:<input type="text" id="address"/><br/>
        <input type="submit" value="注册">
    </form>

</body>
</html>
