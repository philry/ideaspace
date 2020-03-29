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
    <script type="text/javascript">
        function query(){
            var xhr = new XMLHttpRequest();
            var key = document.getElementById("key").value;
            var d = document.getElementById("d");
            if(key == ""){
                d.style.display="none";
                return;
            }
            d.innerHTML="";
            xhr.onreadystatechange=function(){
                if(xhr.status == 200 && xhr.readyState == 4){
                    var datas = xhr.responseText;
                    console.log(datas);
                    datas = eval("("+datas+")");
                    d.style.display="block";
                    if(datas.length == 0){
                        d.style.display="none";
                        return;
                    }
                    for(var i = 0; i < datas.length;i++){
                        d.innerHTML = d.innerHTML+"<li>"+datas[i].message+"</li>";
                    }
                }
            };
            xhr.open("get","${pageContext.request.contextPath}/query?key="+key,true);
            xhr.send(null);
        }
    </script>
</head>
<body>
    <input type="text" id="key" style="width: 312px;" onkeyup="query()">
    <div id="d">
    </div>
</body>
</html>
