<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-10-25
  Time: 上午9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>关键字搜索</title>
    <style type="text/css">
        li{
            list-style:none;
        }
        div{
            border:1px solid black;
            width: 200px;
            padding: 5px;
            border-top: 0px;
            max-height: 100px;
            display:none;
            overflow: auto;  /*设置滚动条*/
        }
    </style>
    <script type="text/javascript">
        function query(){
            var xhr=new XMLHttpRequest();//获取AJAX 引擎
            var key=document.getElementById("key").value;
            var d=document.getElementById("d");
            if(key==""){
                d.style.display="none";
                return;
            }
            d.innerHTML="";
            xhr.onreadystatechange=function(){
                console.log(1);
                if(xhr.status==200&&xhr.readyState==4){
                    var result=xhr.responseText;  //result是字符串类型
                    console.log(result);
                    var datas=eval("("+result+")");//把字符串result转换成json对象
                  if(datas.length==0){
                      d.style.display="none";
                      return;
                  }
                  d.style.display="block";
                  for(var i=0;i<datas.length;i++){
                      d.innerHTML=d.innerHTML+"<li>"+datas[i].message+"<li>";
                  }
                }
            };
            xhr.open("get","${pageContext.request.contextPath}/show.data?key="+key,true);
            xhr.send(null);
        }
    </script>
</head>
<body>
    <input type="text" style="width:212px;" onkeyup="query()" id="key">
    <div id="d"></div>
</body>
</html>
