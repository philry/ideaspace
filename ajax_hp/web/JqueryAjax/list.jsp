<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-10-25
  Time: 下午5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>JQuery-Ajax</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
    <script>
        $(function(){
            $(li).mouseover(function(){
                $("#d").show();

                $.ajax({
                    url:"${pageContext.request.contextPath}/findById.product",
                    data:{"id":$(this).attr("data-id")},
                    dataType:"json",
                    success:function(result){
                        console.log(typeof result);
                        $("#s1").html(result.id);
                        $("#s2").html(result.name);
                        $("#s3").html(result.price);
                    }
                })
            });
        });

    </script>
</head>
<body>
<ul>
    <c:forEach items="${products}" var="product">
        <li data-id="${product.id}">${product.name}</li>
    </c:forEach>
</ul>

<hr/>
<div style="background-color: #abc7c4;width: 20%;padding: 10px;display: none;position:absolute;" id="d">
    编号:<span id="s1"></span><br/>
    商品名称:<span id="s2"></span><br/>
    商品单价:<span id="s3"></span><br/>
</div>
</body>
</html>
