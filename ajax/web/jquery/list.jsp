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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("li").mouseover(function(){
                $("#d").show();
                <%--$.get("${pageContext.request.contextPath}/findById.product?id="+$(this).attr("data-id"),--%>
                    <%--function(result){--%>
                        <%--//此时的result相当于var result = xhr.responseText;--%>
                        <%--//参数不一定叫result，可以随便写--%>
                        <%--console.log(typeof result);--%>
                        <%--$("#s1").html(result.id);--%>
                        <%--$("#s2").html(result.name);--%>
                        <%--$("#s3").html(result.price);--%>
                    <%--},"json"--%>
                <%--)--%>

                //post
                <%--$.post("${pageContext.request.contextPath}/findById.product",--%>
                    <%--{"id":$(this).attr("data-id")},--%>
                    <%--function(result){--%>
                        <%--//此时的result相当于var result = xhr.responseText;--%>
                        <%--//参数不一定叫result，可以随便写--%>
                        <%--console.log(typeof result);--%>
                        <%--$("#s1").html(result.id);--%>
                        <%--$("#s2").html(result.name);--%>
                        <%--$("#s3").html(result.price);--%>
                    <%--},"json"--%>
                <%--)--%>

                <%--$.getJSON("${pageContext.request.contextPath}/findById.product?id="+$(this).attr("data-id"),--%>
                    <%--function(result){--%>
                        <%--//此时的result相当于var result = xhr.responseText;--%>
                        <%--//参数不一定叫result，可以随便写--%>
                        <%--console.log(typeof result);--%>
                        <%--$("#s1").html(result.id);--%>
                        <%--$("#s2").html(result.name);--%>
                        <%--$("#s3").html(result.price);--%>
                    <%--},"text"--%>
                <%--)--%>

                $.ajax({
                   // type:"post",//默认为get，即没有这个选项的时候，表示请求为get请求
                   url:"${pageContext.request.contextPath}/findById.product",
                   data:{"id":$(this).attr("data-id")},
                   // dataType:"json",//默认值为text，即返回普通字符串
                   success:function(result){
                       console.log(typeof result);
                       $("#s1").html(result.id);
                       $("#s2").html(result.name);
                       $("#s3").html(result.price);
                   }
                });
            });
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
            <li data-id="${product.id}">${product.name}</li>
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
