<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午5:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>资源详情</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <style type="text/css">
        .my-video {
            margin: 30px auto auto auto;

        }

        .bgColor {
            background: black;
        }

        .padding-0 {
            padding: 0 0 !important;
        }

    </style>

    <script type="text/javascript">
        $(function () {
            //返回资源列表
            $("#back").on('click', function () {
                $('#frame-id', window.parent.document).attr('src', 'back_resourceSet.html');
            });
        });
    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">资源详情</h3>
    </div>
    <div>
        <input type="button" value="返回资源列表" class="btn btn-success" id="back"
               style="margin: 5px 15px 5px 0px;float: right;"/>
    </div>
    <div style="margin-top: 40px;">
        <div class="container-fluid padding-0 bgColor">
            <video style="width: 100%; height:470px;" controls="controls">
                <source src="upload/banner.mp4">
            </video>
        </div>
    </div>
</div>

</body>

</html>
