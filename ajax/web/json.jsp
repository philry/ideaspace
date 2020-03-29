<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018/10/24
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json用法</title>
    <script type="text/javascript">
        function f1(){
            var user = {"username":"admin","password":"123456","phone":"13812345678","address":"江苏-南京","2test":"testaaa"};
            console.log(user.username);
            console.log(user["password"]);
            console.log(user['phone']);
            console.log(user['address']);
            //通过.获取属性值的时候，属性名不能以数字开头
            //假如当前的属性偏偏只能是数字
            //则不要使用.的方式来实现
            // console.log(user.2test);
            //使用[]来获取
            console.log(user['2test']);
        }
        function f2(){
            var name = {"firstName":"zhang","lastName":"san"};
            // var user = {"id":1,"name":name};
            var user = {"id":2,"name":{"firstName":"li","lastName":"si"}};
            console.log(user.name.firstName);
            console.log(user.name['firstName']);
            console.log(user["name"].lastName);
            console.log(user["name"]["lastName"]);
        }
        function f3(){
            var users = [{
                "id":1,
                "username":"admin"
            },{
                "id":2,
                "username":"zhangsan"
            },{
                "id":3,
                "username":"lisi"
            }];
            console.log(users[1].username);
        }
        function f4(){
            var user = "{'id':1,'username':'admin'}";
            console.log(typeof user);
            // var name = {"firstName":"zhang","lastName":"san"};
            user = eval("("+user+")");
            console.log(typeof user);
            // console.log(typeof name);
            console.log(user.username);
        }
    </script>
</head>
<body>
    <a href="javaScript:;" onclick="f1()">基本语法</a><br/>
    <a href="javaScript:;" onclick="f2()">属性中包含对象</a><br/>
    <a href="javaScript:;" onclick="f3()">对象集合</a><br/>
    <a href="javaScript:;" onclick="f4()">json格式字符串转换对象</a><br/>
</body>
</html>
