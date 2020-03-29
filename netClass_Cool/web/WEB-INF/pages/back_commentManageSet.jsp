<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午4:48
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
    <script src="${pageContext.request.contextPath}/js/template-web.js"></script>

    <script type="text/javascript" >
        $(function(){
            // 显示隐藏查询列表
            $('#show-user-search').click(function() {
                $('#show-user-search').hide();
                $('#upp-user-search').show();
                $('.showusersearch').slideDown(500);
                $('#upp-user-search').click(function() {
                    $('#show-user-search').show();
                    $('#upp-user-search').hide();
                    $('.showusersearch').slideUp(500);
                });
                $(document).on("click","#doSearch",function () {
                    findByData(nowPage);
                    //带着条件分页按钮
                    myoptions.onPageClicked = function(event, originalEvent, type, page) {
                        findByData(page);
                    };
                });

            });
            function findByData(pageNo){
                //根据条件查询
                //带着条件分页查询
                var data = new FormData($("#form_data")[0]);//拿到所有数据
                data.append("pageNo",pageNo);
                console.log(data);
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/comment/findByData.do",
                    data:data,
                    dataType:"json",
                    processData:false,
                    contentType:false,
                    error:function () {
                        alert("error");
                    },
                    success:function (data) {
                        if(data.success){
                            myPaginatorFun("myPages",data.obj.pageNum,data.obj.pages);
                            var txt = template("template1",{"arr":data.obj.list});
                            $("#tb").html(txt);
                            return;
                        }
                        alert(data.msg);
                    }
                });
            };
            console.log(${comments.pageNum}+"and"+${comments.pages});
            //用于记录当前的页码
            var nowPage = ${comments.pageNum};
            //评论，通过
            $(document).on("click","#set_enable",function () {
                //根据id修改，将id传入后台
                //获取id数据,传入当前页码
                console.log("pageNum="+nowPage);
                console.log("id="+$(this).attr("data-id"));
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/comment/modifyEnable.do",
                    data:{
                      "pageNo":nowPage,
                        "id":$(this).attr("data-id")
                    },
                    dataType:"json",
                    success:function (data) {
                        if(data.success){
                            myPaginatorFun("myPages",data.obj.pageNum,data.obj.pages);
                            var txt = template("template1",{"arr":data.obj.list});
                            $("#tb").html(txt);
                            return;
                        }
                        alert(data.msg);
                    }
                });
            });
            //评论审核不通过
            $(document).on("click","#set_disable",function () {
                //根据id修改，将id传入后台
                //获取id数据,传入当前页码
                console.log("pageNum="+nowPage);
                console.log("id="+$(this).attr("data-id"));
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/comment/modifyDisable.do",
                    data:{
                        "pageNo":nowPage,
                        "id":$(this).attr("data-id")
                    },
                    dataType:"json",
                    success:function (data) {
                        if(data.success){
                            myPaginatorFun("myPages",data.obj.pageNum,data.obj.pages);
                            var txt = template("template1",{"arr":data.obj.list});
                            $("#tb").html(txt);
                            return;
                        }
                        alert(data.msg);
                    }
                });
            });

            //按钮的超链接设置函数
            //      myoptions.pageUrl = function(type, page, current) {
            //          return "${pageContext.request.contextPath }/findAll.do?pageNo="
            //                  + page;
            //      };
            //分页回调函数,点击按钮事件
            myoptions.onPageClicked = function(event, originalEvent, type, page) {
                nowPage = page;
                //ajax回调接收数据成功时再重新初始化分页按钮
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/comment/findPage.do",
                    data:{
                        "pageNo":page
                    },
                    dataType:"json",
                    error:function () {
                        alert("分页错误");
                    },
                    success:function (data) {
                        if(data.success){
                            myPaginatorFun("myPages",data.obj.pageNum,data.obj.pages);
                            var txt = template("template1",{"arr":data.obj.list});
                            $("#tb").html(txt);
                            return;
                        }
                        alert(data.msg);
                    }
                });
                console.log("pageNo=", page);
//                 location.href= ".../pageNo=" + page;
            };

            //分页,在bootstrap-mypaginator.js中
            myPaginatorFun("myPages", ${comments.pageNum}, ${comments.pages});
        });
    </script>
</head>

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
            <form id="form_data" class="form-horizontal">
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
                            <input type="text" class="form-control" name="start-date" placeholder="请输入评论开始时间:2017-10-10" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">结束日期：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" name="end-date" placeholder="请输入评论结束时间:2017-10-12" />
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
                <c:forEach items="${comments.list}" var="comment" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${comment.context}</td>
                    <td>${comment.user.loginName}</td>
                    <td>${comment.createDate}</td>
                    <td>${comment.count}</td>
                    <td>待审核</td>
                    <td class="text-center">
                        <input type="button" class="btn btn-success btn-sm" id="set_enable" data-id="${comment.id}" value="通过" />
                        <input type="button" class="btn btn-danger btn-sm" id="set_disable" data-id="${comment.id}" value="禁用" />
                    </td>
                </tr>
                </c:forEach>
                <script id="template1" type="text/html">
                    {{each arr item index}}
                    <tr>
                        <td>{{index+1}}</td>
                        <td>{{item.context}}</td>
                        <td>{{item.user.loginName}}</td>
                        <td>{{item.createDate}}</td>
                        <td>{{item.count}}</td>
                        <td>待审核</td>
                        <td class="text-center">
                            <input type="button" class="btn btn-success btn-sm" id="set_enable" data-id="{{item.id}}" value="通过" />
                            <input type="button" class="btn btn-danger btn-sm" id="set_disable" data-id="{{item.id}}" value="禁用" />
                        </td>
                    </tr>
                    {{/each}}
                </script>
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
