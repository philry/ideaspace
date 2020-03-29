<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstarp/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index.css" />
    <script src="${pageContext.request.contextPath }/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath }/bootstarp/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath }/js/userSetting.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    $(function(){
    	// 点击切换页面
	    $("#user-set").click(function() {
	        $("#frame-id").attr("src", "userManager.html");
	    });
	    $("#product-set").click(function() {
	        $("#frame-id").attr("src", "${pageContext.request.contextPath}/product/findAll.do");
	    });
	    $("#product-type-set").click(function() {
	        $("#frame-id").attr("src", "${pageContext.request.contextPath}/type/findAll.do");
	    });
	    $("#manager-set").click(function() {
	        $("#frame-id").attr("src", "${pageContext.request.contextPath}/staff/findByParams.do");
	    });
	    $("#dept-set").click(function() {
	        $("#frame-id").attr("src", "${pageContext.request.contextPath}/dept/findAll.do");
	    });
    });
    </script>
</head>

<body>
    <div class="wrapper-cc clearfix">
        <div class="content-cc">
            <!-- header start -->
            <div class="clear-bottom head">
                <div class="container head-cc">
                    <p>在线商城<span>后台管理系统</span></p>
                    <div class="welcome">
                        <div class="left">欢迎您：</div>
                        <div class="right">xxx</div>
                        <div class="exit">退出</div>
                    </div>
                </div>
            </div>
            <!-- header end -->
            <!-- content start -->
            <div class="container-flude flude-cc" id="a">
                <div class="row user-setting">
                    <div class="col-xs-3 user-wrap">
                        <ul class="list-group">
                            <li class="list-group-item active" name="userSet" id="product-type-set">
                                <i class="glyphicon glyphicon-lock"></i> &nbsp;商品类型管理
                            </li>
                            <li class="list-group-item" name="userPic" id="product-set">
                                <i class="glyphicon glyphicon-facetime-video"></i> &nbsp;商品管理
                            </li>
                            <c:if test="${staff.role eq 1001}">
                            <li class="list-group-item" name="departmentSet" id="dept-set">
                                <i class="glyphicon glyphicon-modal-window"></i> &nbsp;部门管理
                            </li>
                            <li class="list-group-item" name="adminSet" id="manager-set">
                                <i class="glyphicon glyphicon-globe"></i> &nbsp;管理员管理
                            </li>
                            </c:if>
                            <li class="list-group-item" name="userInfo" id="user-set">
                                <i class="glyphicon glyphicon-user"></i> &nbsp;用户管理
                            </li>
                        </ul>
                    </div>
                    <div class="col-xs-9" id="userPanel">
                        <iframe id="frame-id" src="${pageContext.request.contextPath }/type/findAll.do" width="100%" height="100%" frameborder="0" scrolling="no">
                    </div>

                </div>
            </div>
            <!-- content end-->
        </div>
    </div>
    <div class="footer">
        <!-- footers start -->
        版权所有：南京网博 &nbsp; &nbsp; BY:shixiaojun@itany.com
        <!-- footers end -->
    </div>
</body>

</html>