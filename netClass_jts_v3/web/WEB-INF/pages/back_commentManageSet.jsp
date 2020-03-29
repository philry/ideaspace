<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-8
  Time: 下午4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>评论审核</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>

    <script type="text/javascript" >
        $(function(){
            // 显示隐藏查询列表
            $('#show-user-search').click(function() {
                $('#show-user-search').hide();
                $('#upp-user-search').show();
                $('.showusersearch').slideDown(500);
            });
            $('#upp-user-search').click(function() {
                $('#show-user-search').show();
                $('#upp-user-search').hide();
                $('.showusersearch').slideUp(500);
            });

            var pages;
            if("${pageComments.pages}" == 0){
                pages = 1;
            }else{
                pages = "${pageComments.pages}";
            }
            var options = {
                bootstrapMajorVersion:3,//表示当前bootstrap版本号
                currentPage:"${pageComments.pageNum}",//当前页
                totalPages:pages,//总页数
                size:"normal",
                aligment:"center",
                pageUrl:function(type,page,current){
                    return "${pageContext.request.contextPath}/commentManage/back/findByParams.do?pageNo="+page;
                }
            };
            $("#myPages").bootstrapPaginator(options);

            $("#doSearch").click(function(){
                var startDate = $("input[name=startDate]").val();
                var endDate = $("input[name=endDate]").val();
                $.ajax({
                    url:'${pageContext.request.contextPath}/commentManage/back/jiaoyan.do',
                    data:{'startDate':startDate,'endDate':endDate},
                    dataType:'json',
                    success:function(result){
                        if(result.success){
                            $("#searchForm").submit();
                        }
                        else{
                            $("#errorMsg").tooltip({
                                title: "error",
                                placement: "center",
                                template: "<div class='tooltip errorMsg'>" + result.msg + "</div>",
                                tigger: "manual"
                            }).tooltip("show");
                        }
                    }
                });

            });

            $("input[name=passButton]").click(function(){
                var commentId = $(this).attr("commentId");
                $.ajax({
                    type:'post',
                    url:'${pageContext.request.contextPath}/commentManage/back/passComment.do',
                    data:{'commentId':commentId},
                    dataType:'json',
                    success:function(result){
                        if(result.success){
                            location.href="${pageContext.request.contextPath}/commentManage/back/findByParams.do?pageNo=${pageComments.pageNum}";
                        }
                    }
                });
            });

            $("input[name=refuseButton]").click(function(){
                var commentId = $(this).attr("commentId");
                $.ajax({
                    type:'post',
                    url:'${pageContext.request.contextPath}/commentManage/back/refuseComment.do',
                    data:{'commentId':commentId},
                    dataType:'json',
                    success:function(result){
                        if(result.success){
                            location.href="${pageContext.request.contextPath}/commentManage/back/findByParams.do?pageNo=${pageComments.pageNum}";
                        }
                    }
                });
            });

            //按钮的超链接设置函数
            //      myoptions.pageUrl = function(type, page, current) {
            //          return "${pageContext.request.contextPath }/findAll.do?pageNo="
            //                  + page;
            //      };
            //分页回调函数,点击按钮事件
            //myoptions.onPageClicked = function(event, originalEvent, type, page) {
                //ajax回调接收数据成功时再重新初始化分页按钮
                //console.log("pageNo=", page);
//                 location.href= ".../pageNo=" + page;
            //};

            //分页,在bootstrap-mypaginator.js中
            //myPaginatorFun("myPages", 1, 9);
        });
    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">评论审核<span id="errorMsg"></span></h3>
    </div>
    <div>
        <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 15px;" />
        <input type="button" class="btn btn-primary" id="show-user-search" value="展开搜索" />
        <input type="button" value="收起搜索" class="btn btn-primary" id="upp-user-search" style="display: none;" />
    </div>

    <div class="panel-body">
        <div class="showusersearch" style="display: none;">
            <form id="searchForm" class="form-horizontal" action="${pageContext.request.contextPath}/commentManage/back/findByParams.do">
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="user-name" class="col-xs-3 control-label">用户名：</label>
                        <div class="col-xs-8">
                            <input type="text" name="nickname" class="form-control" id="user-name" placeholder="请输入用户名" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="user-comment" class="col-xs-3 control-label">评论内容：</label>
                        <div class="col-xs-8">
                            <input type="text" name="context" class="form-control" id="user-comment" placeholder="请输入评论内容" />
                        </div>
                    </div>
                </div>
                <!-- <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="role-name" class="col-xs-3 control-label">状态：</label>
                        <div class="col-xs-8">
                            <select class="form-control" id="role-name" name="role-name" >
                                <option value="-1" >全部</option>
                                <option value="普通" >待审核</option>
                                <option value="管理员" >禁用</option>
                                <option value="管理员" >启用</option>
                            </select>
                        </div>
                    </div>
                </div> -->
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">开始日期：</label>
                        <div class="col-xs-8">
                            <input type="text" name="startDate" class="form-control" placeholder="请输入评论开始时间:2017-10-10" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">结束日期：</label>
                        <div class="col-xs-8">
                            <input type="text" name="endDate" class="form-control" placeholder="请输入评论结束时间:2017-10-12" />
                        </div>
                    </div>
                </div>

            </form>
        </div>

        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">评论内容</th>
                    <th class="text-center">用户名</th>
                    <th class="text-center">评论时间</th>
                    <th class="text-center">赞</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageComments.list}" var="comment">
                    <tr>
                        <td>${comment.id}</td>
                        <td>${comment.context}</td>
                        <td>${comment.user.nickname}</td>
                        <td>${comment.createDate}</td>
                        <td>${comment.praiseCount}</td>
                        <td>待审核</td>
                        <td class="text-center">
                            <input name="passButton" commentId="${comment.id}" type="button" class="btn btn-success btn-sm" value="通过" />
                            <input name="refuseButton" commentId="${comment.id}" type="button" class="btn btn-danger btn-sm" value="禁用" />
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

        <!-- 分页 -->
        <div style="text-align: center;" >
            <ul id="myPages" ></ul>
        </div>

    </div>
</div>
</body>

</html>
