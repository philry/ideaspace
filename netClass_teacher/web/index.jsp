<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>index</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
	.myBar {
		width: 100%;
		height: 30px;
		background-color: #ff7e00;
		display: inline-block;
		position: relative;
	}
	.myProgress {
		width: 0px;
		height: 30px;
		background-color: #6af4f9;
		display: inline-block;
		position: absolute;
	}
	.myTip {
		width: 100%;
		height: 30px;
		display: inline-block;
		position: absolute;
		text-align: center;
		line-height: 30px;
		font-weight: bold;
		color: #f5ff93;
	}
</style>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<%
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "name111");
		map.put("b", "name222");
		map.put("v", "name333");
		application.setAttribute("users", map);
	%>
<script type="text/javascript">
	function ajaxTest() {
        //$("#registForm").submit();
        var param = $("#registForm").serialize();
        console.log('param=', param);
		var user = $("#registForm").serializeArray();
        console.log('user=', user);
		//有 form时的数据处理方式
		var formData = new FormData($("#registForm")[0]);
		/*
		var formData = new FormData();//ajax上传文件时数据的类型
		var f = $('#myFile')[0].files[0];
		console.log(f);
		//没有form时 数据的处理方式
		formData.append('file', f);
		formData.append('username', 'admin123');
		formData.append('nickname', '管理员123');
		formData.append('password', '123123');
		formData.append('createDate', '2018-06-21 10:18:18');
		//formData.append('username', $('input[name=nickname]').val());
		*/
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/user/ajaxRegist.do",
			dataType : "json",
			data : formData,
			processData : false,//ajax上传文件必须的属性1
			contentType : false,//ajax上传文件必须的属性2
			xhr: function(){
				//xhr对象
				var myXhr = $.ajaxSettings.xhr();
				//进度条函数
				myXhr.upload.onprogress = function (e) {
					//console.log('e====', e);
					console.log('total='+e.total+',current='+e.loaded);
					var percent = parseInt((e.loaded/e.total) * 100);
					console.log(percent);
					$("#xxx").html(percent + "%");
					$(".myProgress").css('width', percent+'%');
					$(".myTip").html(percent+'%');
				};
				return myXhr;
			},
			success : function(data) {
				//$('#myImage').attr('src', fileDir + data);
				//alert(fileDir + data);
			},
			error : function() {
				alert("error111");
			}
		});

	}
    var name = '${applicationScope.users["a"]}';
    console.log('name=', name);
    function login () {
        var user = $("#registForm").serializeArray();
        var name = $("input[name='loginName']").val();
        sessionStorage.setItem("loginName", name);
        localStorage.setItem("loginName", name);
        $.ajax({
            type : "post",
            url : "${pageContext.request.contextPath}/user/login.do",
            dataType : "json",
            data : user,
            success : function(data) {
                console.log('login=', data);
            },
            error : function() {
                alert("login-error");
            }
        });
	}
</script>

</head>

<body>
	<h1>用户注册</h1>
	<hr />
	<form id="registForm" action="${pageContext.request.contextPath }/user/regist.do"
		method="post" enctype="multipart/form-data" >
		用户名：<input type="text" name="loginName" /><br />
		昵称：<input type="text" name="nickname" /><br />
		角色：<input type="text" name="role" /><br />
		密码：<input type="password" name="password" /><br />
		邮箱：<input type="text" name="email" /><br />
		图片:<input type="file" id="myFile" name="file" /><span id="xxx" style="color:red;" >0%</span><br />
		创建日期:<input type="text" name="createDate" /><br />
		资源名称:<input type="text" name="resource.name" /><br/>
		验证码:<input type="text" name="imageCode" id="codeInput" />
		<img id="imageCode" src="${pageContext.request.contextPath }/user/getCodeImage.do?codeStr=a9x7&num=1"
			alt="no image code" /><br />
<!-- 		<input type="submit" value="注册" /><br /> -->
	</form>
	<button onclick="ajaxTest();">ajax</button><br/>
	<button onclick="login();">login</button>
	<br/>
	<div class="myBar" >
		<div class="myProgress" ></div>
		<div class="myTip" >0%</div>
	</div>
	<br/>
</body>
</html>
