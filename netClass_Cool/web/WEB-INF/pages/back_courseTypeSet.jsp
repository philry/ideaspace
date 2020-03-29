<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>课程类别管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/template-web.js"></script>
    <script type="text/javascript" charset="utf-8">
        $(function(){


            //添加课程类别
            $("#doAddCourseType").on("click", function() {
                $("#courseType-id-input").hide();
                $("#courseTypeModal").modal("show");
            });
            $(".courseType-btn").on("click", function() {
                $("#courseTypeModal").modal("hide");
                var data = new FormData();
                data.append("typeName",$("#courseType-name").val());
                data.append("parentId",$("#findPageId").val());
                console.log(data);
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/type/addType.do",
                    data:data,
                    dataType:"json",
                    processData:false,
                    contentType:false,
                    success:function (result) {
                        if(result.success){
                            console.log(result.msg);
                        }else{
                            console.log(result.msg);

                        }
                    }
                });

            });
            //修改课程类别
            $(".courseType-modify").on("click", function() {
                $("#courseType-id-input").show();
                $("#courseTypeModal").modal("show");
            });

            //查询子类别
            $(document).on("click","#childId", function() {
                //拿到父级id做为parentId
                //查询到所有数据后分页做好分页对象
                //直接用template框架完成布置
                //判断是否是二级类型，是则不提供添加功能8


                var parentId = $(this).attr("data-id");
                console.log(parentId);
                var data = new FormData();
                data.append("parentId",parentId);
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/type/findPage.do",
                    data:data,
                    dataType:"json",
                    processData:false,
                    contentType:false,
                    success:function (result) {
                        if(result.success){
                            myPaginatorFun("myPages", result.obj.pageNum, result.obj.pages);
                            var txt = template("template1",{"arr":result.obj.list});
                            $("#tb").html(txt);
                            if(result.obj.list.length==0){
                                console.log("kkkkkk");
                                var hiddenId = $("<input type='hidden'id='findPageId'>");
                                hiddenId.val(parentId);
                                $("#tb").append(hiddenId);
                            }
                        }else{
                            alert(result.msg);
                        }
                    }
                });
                // $('#frame-id', window.parent.document).attr('src', 'back_courseTypeSet.html');
            });
            //返回父类别列表页面
            $("#back").on("click", function() {
                $('#frame-id', window.parent.document).attr('src', 'back_courseTypeSet.html');
            });


            //按钮的超链接设置函数
            //      myoptions.pageUrl = function(type, page, current) {
            //          return "${pageContext.request.contextPath }/findAll.do?pageNo="
            //                  + page;
            //      };
            //分页回调函数,点击按钮事件
            myoptions.onPageClicked = function(event, originalEvent, type, page) {
                var data = new FormData();
                data.append("pageNo",page);
                data.append("pageSize",${types.pageSize});
                data.append("parentId",$("#findPageId").val());
                console.log(page);
                //ajax回调接收数据成功时再重新初始化分页按钮
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/type/findPage.do",
                    data:data,
                    dataType:"json",
                    processData:false,
                    contentType:false,
                    success:function (result) {
                        if(result.success){
                            myPaginatorFun("myPages", result.obj.pageNum, result.obj.page);
                            var txt = template("template1",{"arr":result.obj.list});
                            $("#tb").html(txt);
                            //判断list里面没有数据，就添加隐藏的parentId


                        }else{
                            console.log(result.msg);
                        }
                    }
                });
            };

            //分页,在bootstrap-mypaginator.js中
            myPaginatorFun("myPages", ${types.pageNum}, ${types.pages});

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
        <input type="button" value="添加课程类别" class="btn btn-primary" id="doAddCourseType" />
        <!-- courseType父类别id,没有时,移除此按钮 -->
        <input type="hidden" name="parent_id" value=""  />
        <input type="button" value="返回上级列表" class="btn btn-success" id="back" style="float: right;" />
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
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right" id="courseType-id-input" >
                            <label for="courseType-id" class="col-sm-4 control-label">编号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="courseType-id" readonly="true" />
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="courseType-name" class="col-sm-4 control-label">类别名称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="courseType-name" />
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary courseType-btn">确定</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
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
                <c:forEach items="${types.list}" var="type" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${type.typeName}</td>
                    <c:if test="${type.status eq 0}"><td>启用</td></c:if>
                    <c:if test="${type.status eq 1}"><td>禁用</td></c:if>
                    <td>
                        <input type="hidden" id="findPageId" value="${type.parentId}">
                        <input type="button" class="btn btn-warning btn-sm courseType-modify" value="修改" />
                        <c:if test="${type.status eq 0}">
                        <input type="button" class="btn btn-danger btn-sm" value="禁用" />
                        </c:if>
                        <c:if test="${type.status eq 1}">
                        <input type="button" class="btn btn-success btn-sm" value="启用" />
                        </c:if>
                        <input type="button" class="btn btn-success btn-sm course-type-detail" id="childId" data-id="${type.id}" value="查询子类别" />
                    </td>
                </tr>
                </c:forEach>
                <script id="template1" type="text/html">
                    {{each arr item index }}
                    <tr>
                        <td>{{index+1}}</td>
                        <td>{{item.typeName}}</td>
                        {{if item.status == 0}}
                        <td>启用</td>
                        {{else}}
                        <td>禁用</td>
                        {{/if}}
                        <td>
                            <input type="hidden" id="findPageId" value="{{item.parentId}}">
                            <input type="button" class="btn btn-warning btn-sm courseType-modify" value="修改" />
                            {{if item.status == 0}}
                                <input type="button" class="btn btn-danger btn-sm" value="禁用" />
                            {{else}}
                                <input type="button" class="btn btn-success btn-sm" value="启用" />
                            {{/if}}
                            <input type="button" class="btn btn-success btn-sm course-type-detail" id="childId" data-id="{{item.id}}" value="查询子类别" />
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
