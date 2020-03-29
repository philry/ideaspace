<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>课程类别管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js" type="text/javascript"
            charset="utf-8"></script>
    <script type="text/javascript" charset="utf-8">
        $(function () {
            var pages;
            if ("${courses.pages}" == 0) {
                pages = 1;
            } else {
                pages = "${courses.pages}";
            }

            //使用bootstrap分页插件实现前台分页
            var options = {
                bootstrapMajorVersion: 3,//表示当前bootstrap版本号
                currentPage: "${courses.pageNum}",//当前页
                totalPages: pages,//总页数
                size: "normal",
                aligment: "center",
                pageUrl: function (type, page, current) {
                    return "${pageContext.request.contextPath}/course/findAllCourse.do?pageNo=" + page;
                }
            };
            $("#myPages").bootstrapPaginator(options);


            $("#doAddCourseType").on("click", function () {
                $("#courseType-id-input").hide();
                $("#courseTypeModal").modal("show");
            });
            $(".courseType-btn").on("click", function () {
                $("#courseTypeModal").modal("hide");
            });

            $(".courseType-modify").on("click", function () {
                $("#courseType-id-input").show();
                $("#courseTypeModal").modal("show");
            });

            //添加或修改课程类别
            $("[name=addType]").click(function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/course/addType.do",
                    data: {
                        "name": $("#courseType-name").val(), "parentId": $("#net-parentId").html(),
                        "id": $("#courseType-id").val()
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            location.href = "${pageContext.request.contextPath}/course/findSon.do?id=" + $("#net-parentId").html();
                        }
                    }
                })
            });

            //查询子类别
            $(".course-type-detail").on("click", function () {
                $('#frame-id', window.parent.document).attr('src', '${pageContext.request.contextPath}/course/findSon.do?id=' + $(this).parent().parent().find("td").eq(0).html());
            });
            //返回父类别列表页面
            $("#back").on("click", function () {
                $('#frame-id', window.parent.document).attr('src', '${pageContext.request.contextPath}/course/findParent.do?id=' + $("#net-parentId").html());
            });

            //禁用启用
            $("[name=banned]").click(function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/course/changeStatusType.do",
                    data: {"id": $(this).parent().parent().find("td").eq(0).html()},
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            location.href = "${pageContext.request.contextPath}/course/findSon.do?id=" + $("#net-parentId").html();
                        }
                    }
                })
            });

            //回写
            $("[name=findThis]").click(function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/course/findThis.do",
                    data: {
                        "id": $(this).parent().parent().find("td").eq(0).html()
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            $("#courseType-id").val(result.obj.id);
                            $("#courseType-name").val(result.obj.name);
                        }
                    }
                })
            });


            <%--//按钮的超链接设置函数--%>
            <%--//      myoptions.pageUrl = function(type, page, current) {--%>
            <%--//          return "${pageContext.request.contextPath }/findAll.do?pageNo="--%>
            <%--//                  + page;--%>
            <%--//      };--%>
            <%--//分页回调函数,点击按钮事件--%>
            <%--myoptions.onPageClicked = function(event, originalEvent, type, page) {--%>
            <%--//ajax回调接收数据成功时再重新初始化分页按钮--%>
            <%--console.log("pageNo=", page);--%>
            <%--};--%>

            <%--//分页,在bootstrap-mypaginator.js中--%>
            <%--myPaginatorFun("myPages", 1, 5);--%>

        });

    </script>


</head>

<body>

<!-- 课程类别管理 -->
<div class="panel panel-default" id="departmentSet">
    <div class="panel-heading">
        <h3 class="panel-title">课程类别管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加课程类别" class="btn btn-primary" id="doAddCourseType"/>
        <!-- courseType父类别id,没有时,移除此按钮 -->
        <h1 id="net-parentId" style="display: none">${id}</h1>
        <input type="hidden" name="parent_id" value=""/>
        <c:if test="${id!=null}">
            <input type="button" value="返回上级列表" class="btn btn-success" id="back" style="float: right;"/>
        </c:if>
        <br>
        <br>
        <div class="modal fade" tabindex="-1" id="courseTypeModal">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">添加类别</h4>

                        <div class="modal-body text-center">
                            <div class="row text-right" id="courseType-id-input">
                                <label for="courseType-id" class="col-sm-4 control-label">编号：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="courseType-id" readonly="true"/>
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="courseType-name" class="col-sm-4 control-label">类别名称：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="courseType-name"/>
                                </div>
                            </div>
                            <br>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary courseType-btn" name="addType">确定</button>
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">名称</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${courses.list}" var="course">
                    <tr>
                        <td>${course.id}</td>
                        <td>${course.name}</td>
                        <c:if test="${course.status==0}">
                            <td>启用</td>
                            <td>
                                <input type="button" class="btn btn-warning btn-sm courseType-modify" value="修改"
                                       name="findThis"/>
                                <input type="button" class="btn btn-danger btn-sm" value="禁用" name="banned"/>
                                <input type="button" class="btn btn-success btn-sm course-type-detail" value="查询子类别"
                                       name="findSon"/>
                            </td>
                        </c:if>
                        <c:if test="${course.status!=0}">
                            <td>禁用</td>
                            <td>
                                <input type="button" class="btn btn-warning btn-sm courseType-modify" value="修改"
                                       name="findThis"/>
                                <input type="button" class="btn btn-success btn-sm" value="启用" name="banned"/>
                                <input type="button" class="btn btn-success btn-sm course-type-detail" value="查询子类别"
                                       name="findSon"/>
                            </td>
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
    </div>
</div>

</body>

</html>
