<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午5:09
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

</head>
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

        //按钮的超链接设置函数
        //      myoptions.pageUrl = function(type, page, current) {
        //          return "${pageContext.request.contextPath }/findAll.do?pageNo="
        //                  + page;
        //      };
        //分页回调函数,点击按钮事件
        myoptions.onPageClicked = function(event, originalEvent, type, page) {
            //ajax回调接收数据成功时再重新初始化分页按钮
            console.log("pageNo=", page);
//                 location.href= ".../pageNo=" + page;
        };

        //分页,在bootstrap-mypaginator.js中
        //  myPaginatorFun("myPages", 1, 9);


        var pages;
        if("${pageCommentManages.pages}" == 0){
            pages = 1;
        }else{
            pages = "${pageCommentManages.pages}";
        }

        //使用bootstrap分页插件实现前台分页
        var options = {
            bootstrapMajorVersion:3,//表示当前bootstrap版本号
            currentPage:"${pageCommentManages.pageNum}",//当前页,pageCommentManages是
            totalPages:pages,//总页数                 //pageCommentManageController传过来的的分页对象
            size:"normal",
            aligment:"center",
            pageUrl:function(type,page,current){
                return "${pageContext.request.contextPath}/commentManage/unchecked.do?pageNo="+page;
            }
        };
        $("#myPages").bootstrapPaginator(options);


        $("#doSearch").click(function(){
            $("#findByParam").submit();
        });


        <%--$("#doSearch").click(function(result){--%>
            <%--var formDate = new formData(".form-horizontal");--%>
            <%--$.ajax({--%>
                    <%--type:"post",--%>
                    <%--url:"${pageContext.request.contextPath}/commentManage/findByParam.do",--%>
                    <%--data:formData,--%>
                    <%--dataTypes:"json",--%>
                    <%--success : function() {--%>
                       <%--location.href="${pageContext.request.contextPath}/back_commentManage?pageNo=${pageCommentManages.pageNum}";--%>
                    <%--},--%>
                    <%--error : function(result) {--%>
                        <%--alert("查询失败");--%>
                    <%--}--%>
                <%--});--%>
            <%--});--%>
    });
</script>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">评论审核</h3>
    </div>
    <div>
        <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 15px;" />
        <input type="button" class="btn btn-primary" id="show-user-search" value="展开搜索" />
        <input type="button" value="收起搜索" class="btn btn-primary" id="upp-user-search" style="display: none;" />
    </div>

    <div class="panel-body">
        <div class="showusersearch" style="display: none;">
            <form class="form-horizontal" id="findByParam" action="${pageContext.request.contextPath}/commentManage/unchecked.do">
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="user-name" class="col-xs-3 control-label">用户名：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="user-name" name="user-name" placeholder="请输入用户名" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="user-comment" class="col-xs-3 control-label">评论内容：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="user-comment" name="user-comment" placeholder="请输入评论内容" />
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
                            <input type="text" class="form-control" name="startDate" placeholder="请输入评论开始时间:2017-10-10" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">结束日期：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" name="endDate" placeholder="请输入评论结束时间:2017-10-12" />
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
                <c:forEach items="${commentManages}" var="commentManage">
                <tr>
                    <td>${commentManage.id}</td>
                    <td>${commentManage.context}</td>
                    <td>${commentManage.user.id}</td>
                    <td>${commentManage.createDate}</td>
                    <td>${commentManage.praiseCount}</td>
                    <td>待审核</td>
                    <td class="text-center">
                        <input type="button" class="btn btn-success btn-sm" value="通过" />
                        <input type="button" class="btn btn-danger btn-sm" value="禁用" />
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
