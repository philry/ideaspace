<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018/10/25
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>关键字搜索</title>
    <style type="text/css">
        li{
            list-style: none;
        }
        div{
            border:1px solid gray;
            width: 300px;
            padding: 5px;
            border-top: 0px;
            max-height: 100px;
            overflow: auto;
            display: none;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
           $("#key").keyup(function(){
             $("#d").empty();
             if($(this).val() == ""){
                 $("#d").hide();
                 return;
             }
             $.ajax({
                type:"post",
                <%--url:"${pageContext.request.contextPath}/query",--%>
                url:"http://127.0.0.1:8080/ajax/query",
                data:{"key":$(this).val()},
                dataType:"json",
                success:function(datas){
                    if(datas.length == 0){
                        $("#d").hide();
                        return;
                    }
                    $("#d").show();
                    $(datas).each(function(){
                       $("#d").append("<li>"+this.message+"</li>");
                    });
                }
             });
           });
        });
    </script>
</head>
<body>
    <input type="text" id="key" style="width: 312px;" >
    <div id="d">
    </div>
</body>
</html>
