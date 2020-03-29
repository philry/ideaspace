<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    <script type="text/javascript" charset="utf-8">
        $(function () {
            var pages;
            if ("${users.pages}" == 0) {
                pages = 1;
            } else {
                pages = "${users.pages}";
            }

            //使用bootstrap分页插件实现前台分页
            var options = {
                bootstrapMajorVersion:3,//表示当前bootstrap版本号
                currentPage:"${users.pageNum}",//当前页
                totalPages:pages,//总页数
                size:"normal",
                aligment:"center",
                pageUrl:function(type, page, current) {
                    return "${pageContext.request.contextPath}/admin/findAllUser.do?pageNo="+page;
                }
            };
            $("#myPages").bootstrapPaginator(options);


            $(".doModify").on("click", function () {
                $(".modal-title").html("用户修改");
                $("#myModal").modal("show");
            });
            $(".updateOne").on("click", function () {
                $("#myModal").modal("hide");
            });

            // 显示隐藏查询列表
            $('#show-user-search').click(function () {
                $('#show-user-search').hide();
                $('#upp-user-search').show();
                $('.showusersearch').slideDown(500);
            });
            $('#upp-user-search').click(function () {
                $('#show-user-search').show();
                $('#upp-user-search').hide();
                $('.showusersearch').slideUp(500);
            });


            $("[name=findThis]").click(function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/admin/findThis.do",
                    data: {"id": $(this).parent().parent().find("td").eq(0).html()},
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            $("#user_id").val(result.obj.id);
                            $("#username").val(result.obj.nickname);
                            if (result.obj.role == 'admin') {
                                $("#roleName").val("管理员");
                            } else {
                                $("#roleName").val("普通用户");
                            }
                            $("#email").val(result.obj.email);
                        }
                    }
                })
            });

            $("[name=banned]").click(function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/admin/banned.do",
                    data: {"id": $(this).parent().parent().find("td").eq(0).html()},
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            location.href = "${pageContext.request.contextPath}/admin/findAllUser.do";
                        }
                    }
                })
            });

            $("[name=modify]").click(function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/admin/modifyUser.do",
                    data: {
                        "id": $("#user_id").val(), "nickname": $("#username").val(),
                        "rolename": $("#roleName").val(), "password": $("#password").val(),
                        "email": $("#email").val()
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            location.href = "${pageContext.request.contextPath}/admin/findAllUser.do";
                        }
                    },
                })
            });
        });

        //ajax回调接收数据成功时再重新初始化分页按钮
        function ajaxLoadData(data, url, page) {
            //TODO:
        }

        //ajaxLoadData("", url, 1);

    </script>

</head>

<body>
<div class="panel panel-default" id="userInfo">
    <div class="panel-heading">
        <h3 class="panel-title">用户管理</h3>
    </div>
    <div>
        <input type="button" value="查询" class="btn btn-success" id="doSearch"
               style="margin: 5px 5px 5px 15px;"/> <input type="button"
                                                          class="btn btn-primary" id="show-user-search" value="展开搜索"/>
        <input
                type="button" value="收起搜索" class="btn btn-primary"
                id="upp-user-search" style="display: none;"/>
    </div>

    <div class="panel-body">
        <div class="showusersearch" style="display: none;">
            <form class="form-horizontal">
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="user-name" class="col-xs-3 control-label">登录名：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="user-name"
                                   placeholder="请输入登录名"/>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="user-nickname" class="col-xs-3 control-label">昵称：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="user-nickname"
                                   placeholder="请输入昵称"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="user-email" class="col-xs-3 control-label">邮箱：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="user-email"
                                   placeholder="请输入邮箱"/>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="role-name" class="col-xs-3 control-label">角色：</label>
                        <div class="col-xs-8">
                            <select class="form-control" id="role-name" name="role-name">
                                <option value="-1">全部</option>
                                <option value="normal">普通</option>
                                <option value="admin">管理员</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">开始日期：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control"
                                   placeholder="请输入创建开始时间:2017-10-10"/>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">结束日期：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control"
                                   placeholder="请输入创建结束时间:2017-10-12"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">开始日期：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control"
                                   placeholder="请输入登录开始时间:2017-10-10"/>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">结束日期：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control"
                                   placeholder="请输入登录结束时间:2017-10-12"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="show-list">
            <table class="table table-bordered table-hover"
                   style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">登录名</th>
                    <th class="text-center">角色</th>
                    <th class="text-center">昵称</th>
                    <th class="text-center">邮箱</th>
                    <th class="text-center">创建日期</th>
                    <th class="text-center">最近登录日期</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${users.list}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.loginName}</td>
                        <td>${user.role}</td>
                        <td>${user.nickname}</td>
                        <td>${user.email}</td>
                        <td>${user.createDate}</td>
                        <td>${user.loginDate}</td>
                        <c:if test="${user.status==0}">
                            <td class="text-center"><input type="button"
                                                           class="btn btn-warning btn-sm doModify" value="修改"
                                                           name="findThis"/> <input
                                    type="button" class="btn btn-danger btn-sm" value="禁用" name="banned"/></td>
                        </c:if>
                        <c:if test="${user.status!=0}">
                            <td class="text-center"><input type="button"
                                                           class="btn btn-warning btn-sm doModify" value="修改"
                                                           name="findThis"/> <input
                                    type="button" class="btn btn-success btn-sm" value="启用" name="banned"/></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- 分页 -->
        <div style="text-align: center;">
            <ul id="myPages"></ul>
        </div>

        <div class="modal fade" tabindex="-1" id="myModal">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">用户修改</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="user_id" class="col-xs-4 control-label">编号：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="user_id"
                                       readonly="true"/>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="username" class="col-xs-4 control-label">昵称：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="username"/>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="roleName" class="col-xs-4 control-label">角色：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="roleName"/>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="password" class="col-xs-4 control-label">密码：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="password"/>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="email" class="col-xs-4 control-label">邮箱：</label>
                            <div class="col-xs-4">
                                <input type="email" class="form-control" id="email"/>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-warning updateOne" name="modify">修改</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
