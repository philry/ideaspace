<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018/10/24
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HelloWorld</title>
    <script type="text/javascript">
        function f1(){
            //通用
            var xhr = new XMLHttpRequest();
            //IE8之前版本获取方式
            // if(window.ActiveXObject){
            //     var xhr = new ActiveXObject("Microsoft.XMLHTTP");
            // }
            alert(xhr);
        }
        function sayHello(){
            //获取Ajax引擎
            var xhr = new XMLHttpRequest();
            //设置回调函数
            xhr.onreadystatechange=function(){
                if(xhr.status == 200 && xhr.readyState == 4){
                    var result = xhr.responseText;
                    document.getElementById("s1").innerHTML = result;
                }
            };
            xhr.open("get","${pageContext.request.contextPath}/sayHello",true);
            xhr.send(null);
        }
    </script>
</head>
<body>
    <a href="javaScript:;" onclick="f1()">获取Ajax引擎</a><br/>
    <a href="javaScript:;" onclick="sayHello()">sayHello</a><br/>
    <span id="s1" style="color:#c7030c;"></span>
</body>
</html>
