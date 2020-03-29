<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018/10/24
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ajax返回json对象</title>
    <script type="text/javascript">
        function show(id,e){
            document.getElementById("d").style.display="block";

            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange=function(){
                if(xhr.status == 200 && xhr.readyState == 4){
                    var result = xhr.responseText;
                    // console.log(result);
                    var product = eval("("+result+")");
                    document.getElementById("d").style.left=e.clientX;
                    document.getElementById("d").style.top=e.clientY;
                    document.getElementById("s1").innerHTML=product.id;
                    document.getElementById("s2").innerHTML=product.name;
                    document.getElementById("s3").innerHTML=product.price;
                }
            };
            xhr.open("get","${pageContext.request.contextPath}/findById.product?id="+id,true);
            xhr.send(null);
        }
        function hide(){
            document.getElementById("d").style.display="none";
        }
    </script>
</head>
<body>
    <!-- 练习要求
        列表中的所有数据全部取自于数据库中
        当鼠标光标悬停在某一个商品上的时候
        在div中显示该商品的详细信息
        并将div在鼠标光标所在位置显示
        当鼠标光标不在任何商品上的时候
        div不显示
    -->
    <ul>
        <c:forEach items="${products}" var="product">
            <li onmouseover="show(${product.id},event)" onmouseout="hide()">${product.name}</li>
        </c:forEach>
    </ul>

    <hr/>
    <div id="d" style="background-color: #abc7c4;width: 20%;padding: 10px;display: none;position: absolute;">
        编号:<span id="s1"></span><br/>
        商品名称:<span id="s2"></span><br/>
        商品单价:<span id="s3"></span><br/>
    </div>

</body>
</html>
