<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午4:49
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
    <title>评论管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/template-web.js"></script>

    <script type="text/javascript" >
        $(function(){
            //返回章节列表
            $("#back").on('click', function(){
                $('#frame-id', window.parent.document).attr('src', 'back_courseReourceSet.html');
            });
            <%--alert(${comments.list});--%>
            // 显示隐藏查询列表
            $('#show-comment-search').click(function() {
                $('#show-comment-search').hide();
                $('#upp-comment-search').show();
                $('.show-comment-search').slideDown(500);
                $('#upp-comment-search').click(function() {
                    $('#show-comment-search').show();
                    $('#upp-comment-search').hide();
                    $('.show-comment-search').slideUp(500);
                });
                $("#doSearch").on("click",function () {
                    // 打包传入后台的数据
                    var data = new FormData($("#form_data")[0]);//表单
                    var url = "${pageContext.request.contextPath}/comment/findPassedByData.do";
                    //执行查询
                    // 返回结果
                    // 进行分页
                    ajax(data,url);
                    // 绑定查询后分页按钮事件
                   myoptions.onPageClicked = function (event, originalEvent, type, page) {
                       var data = new FormData($("#form_data")[0]);//表单
                       var url = "${pageContext.request.contextPath}/comment/findPassedByData.do";
                       data.append("pageNo",page);
                       ajax(data,url);
                   }
                });
            });


            //按钮的超链接设置函数
            //      myoptions.pageUrl = function(type, page, current) {
            //          return "${pageContext.request.contextPath }/findAll.do?pageNo="
            //                  + page;
            //      };
            //用于记录当前的页码数
            var pageNo = ${comments.pageNum};
            //分页回调函数,点击按钮事件
            myoptions.onPageClicked = function(event, originalEvent, type, page) {
                //ajax回调接收数据成功时再重新初始化分页按钮
                pageNo=page;
                var data = new FormData();
                data.append("pageNo",pageNo);
                // var data = {"pageNo":pageNo};
                var url = "${pageContext.request.contextPath}/comment/findPassedPage.do"
                ajax(data,url);
                console.log("pageNo=", page);
            };
            $(document).on("click","#modifyById",function () {
                var data = new FormData();
                data.append("id",$(this).attr("data-id"));
                data.append("pageNo",pageNo);
                var url = "${pageContext.request.contextPath}/comment/modifyById.do";
                ajax(data,url);
                //传入当前的页数
            });
            //ajax模板
            function ajax(formData,url){
                $.ajax({
                    type:"post",
                    url:url,
                    data:formData,
                    dataType:"json",
                    processData:false,
                    contentType:false,
                    error:function () {
                        alert("error");
                    },
                    success:function (data) {
                        if(data.success){
                            myPaginatorFun("myPages",data.obj.pageNum,data.obj.pages);
                            console.log("pages="+data.obj.pages);
                            var txt = template("template1",{"arr":data.obj.list});
                            $("#tb").html(txt);
                            return;
                        }
                        console.log(data.msg);
                    }
                });

            }
            //分页,在bootstrap-mypaginator.js中
            myPaginatorFun("myPages", ${comments.pageNum}, ${comments.pages});
        });

    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">评论管理</h3>
    </div>
    <div>
        <!-- course-resource-id,没有时,移除此按钮 -->
        <input type="hidden" name="course_resource_id" value=""  />
        <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 15px;" />
        <input type="button" class="btn btn-primary" id="show-comment-search" value="展开搜索" />
        <input type="button" value="收起搜索" class="btn btn-primary" id="upp-comment-search" style="display: none;" />
        <input type="button" value="返回章节列表" class="btn btn-success" id="back" style="margin: 5px 15px 5px 0px;float: right;" />
    </div>

    <div class="panel-body">
        <div class="show-comment-search" style="display: none;">
            <form id="form_data" class="form-horizontal">
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="user-name-search" class="col-xs-3 control-label">用户名：</label>
                        <div class="col-xs-8">
                            <input type="text" name="loginName" class="form-control" id="user-name-search" placeholder="请输入用户名" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="user-comment-search" class="col-xs-3 control-label">内容：</label>
                        <div class="col-xs-8">
                            <input type="text" name="context" class="form-control" id="user-comment-search" placeholder="请输入评论内容" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">开始日期：</label>
                        <div class="col-xs-8">
                            <input type="text" name="startDate" class="form-control" placeholder="请输入开始时间:2017-10-10" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">结束日期：</label>
                        <div class="col-xs-8">
                            <input type="text" name="endDate" class="form-control" placeholder="请输入结束时间:2017-10-12" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="course-resource-stauts-search" class="col-xs-3 control-label">状态：</label>
                        <div class="col-xs-8">
                            <select class="form-control" name="status" id="course-resource-stauts-search" name="course-resource-stauts-search" >
                                <option value="-1" >全部</option>
                                <option value="0" >启用</option>
                                <option value="1" >禁用</option>
                            </select>
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
                    <c:if test="${comment.status eq 0}"><td>启用</td></c:if>
                    <c:if test="${comment.status eq 1}"><td>禁用</td></c:if>
                    <td class="text-center">
                        <c:if test="${comment.status eq 0}">
                            <input type="button" class="btn btn-danger btn-sm" id="modifyById" data-id="${comment.id}" value="禁用" />
                        </c:if>
                        <c:if test="${comment.status eq 1}">
                            <input type="button" class="btn btn-success btn-sm" id="modifyById" data-id="${comment.id}" value="启用" />
                        </c:if>
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
                        {{if item.status == 0}}
                        <td>启用</td>
                        {{else}}
                        <td>禁用</td>
                        {{/if}}
                        <td class="text-center">
                            {{if item.status == 0}}
                            <input type="button" class="btn btn-danger btn-sm" id="modifyById" data-id="{{item.id}}" value="禁用" />
                            {{else}}
                            <input type="button" class="btn btn-success btn-sm" id="modifyById" data-id="{{item.id}}" value="启用" />
                            {{/if}}
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
