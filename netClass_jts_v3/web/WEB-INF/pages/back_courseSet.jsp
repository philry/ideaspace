<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-6
  Time: 下午1:49
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
    <title>课程管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    <style>
        .file {
            position: relative;
            display: inline-block;
            background: #D0EEFF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
            width: 100%;
            text-align: center;
        }
        /*.file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }*/
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }
        .file:focus {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }

        .img {
            /* max-width: 100px; */
            width: 100px;
            /* max-height: 120px; */
        }
        tr td {
            line-height: 120px !important;
        }

    </style>
    <script type="text/javascript">
        $(function(){
            var flag;
            var pages;
            if("${pageCourses.pages}" == 0){
                pages = 1;
            }else{
                pages = "${pageCourses.pages}";
            }
            var options = {
                bootstrapMajorVersion:3,//表示当前bootstrap版本号
                currentPage:"${pageCourses.pageNum}",//当前页
                totalPages:pages,//总页数
                size:"normal",
                aligment:"center",
                pageUrl:function(type,page,current){
                    return "${pageContext.request.contextPath}/course/back/findByParams.do?pageNo="+page;
                }
            };
            $("#myPages").bootstrapPaginator(options);
            // 查询表单提交
            $("#doSearch").click(function(){
                var startDate = $("input[name=startDate]").val();
                var endDate = $("input[name=endDate]").val();
                $.ajax({
                    url:"${pageContext.request.contextPath}/course/back/jiaoyan.do",
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

            $('input[name=changeStatus]').click(function(){
                var courseId = $(this).attr("courseId");
                var status = $(this).attr("status");
                $.ajax({
                    type:"post",
                    data:{'courseId':courseId,'status':status},
                    url:'${pageContext.request.contextPath}/course/back/modifyStatus.do',
                    dataType:'json',
                    success:function(result){
                        if(result.success){
                            location.href = "${pageContext.request.contextPath}/course/back/findByParams.do?pageNo=${pageCourses.pageNum}";
                        }else{
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

            $("button[name=confirmCourseButton]").click(function(){
                if(flag==0){
                    console.log("添加");
                    var data = new FormData($("#addForm")[0]);
                    $.ajax({
                        url:"${pageContext.request.contextPath}/course/back/addCourse.do",
                        type:"post",
                        dataType:"json",
                        data:data,
                        processData : false,
                        contentType : false,
                        success:function(result){
                            if(result.success){
                                location.href = "${pageContext.request.contextPath}/course/back/findByParams.do?pageNo=${pageCourses.pageNum}";
                            }else{
                                $("#errorMsg").tooltip({
                                    title: "error",
                                    placement: "center",
                                    template: "<div class='tooltip errorMsg'>" + result.msg + "</div>",
                                    tigger: "manual"
                                }).tooltip("show");
                            }
                        }
                    });
                }else if(flag==1){
                    console.log("修改");
                    var data = new FormData($("#addForm")[0]);
                    $.ajax({
                        url:"${pageContext.request.contextPath}/course/back/modifyCourse.do",
                        type:"post",
                        dataType:"json",
                        data:data,
                        processData : false,
                        contentType : false,
                        success:function(result){
                            if(result.success){
                                location.href = "${pageContext.request.contextPath}/course/back/findByParams.do?pageNo=${pageCourses.pageNum}";
                            }else{
                                $("#errorMsg").tooltip({
                                    title: "error",
                                    placement: "center",
                                    template: "<div class='tooltip errorMsg'>" + result.msg + "</div>",
                                    tigger: "manual"
                                }).tooltip("show");
                            }
                        }
                    });
                }


            });


            // 课程管理 修改课程
            $(".doCourseModify").on("click", function() {
                flag = 1;
                $("#course-id-input").show();
                $(".file").html("选择文件");
                $("#cover-image").val(undefined);
                $(".modal-title").html("修改课程");
                $("#Course").modal("show");
                var courseId = $(this).attr("courseId");
                $.ajax({
                    type:"post",
                    data:{"courseId":courseId},
                    dataType:"json",
                    url:"${pageContext.request.contextPath}/course/back/showBackCourse.do",
                    success:function(result){
                        if(result.success){
                            $("#course-id").val(result.obj.id);
                            $("#course-name").val(result.obj.courseName);
                            $("#course-info").val(result.obj.courseInfo);
                            $("#course-author").val(result.obj.author);
                            $("#cover-image").val(result.obj.coverImageUrl);
                            $("#course-type-id").val(result.obj.courseType.id);
                        }else{
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



            $(".curse-btn").on("click", function() {
                $("#Course").modal("hide");
            });

            // 课程管理 添加课程
            $("#doCourse").on("click", function() {
                flag = 0;
                $("#course-id-input").hide();
                $(".file").html("选择文件");
                $("#cover-image").val(undefined);
                $(".modal-title").html("添加课程");
                $("#Course").modal("show");
                $("#addForm")[0].reset();
            });

            //课程章节显示
            $(".course-detail").on("click", function() {
                $('#frame-id', window.parent.document).attr('src', '${pageContext.request.contextPath}/courseResource/back/findByParams.do');
            });

            // 显示隐藏查询列表
            $('#show-course-search').click(function() {
                $('#show-course-search').hide();
                $('#upp-course-search').show();
                $('.show-course-search').slideDown(500);
            });
            $('#upp-course-search').click(function() {
                $('#show-course-search').show();
                $('#upp-course-search').hide();
                $('.show-course-search').slideUp(500);
            });

            //按钮的超链接设置函数
            //      myoptions.pageUrl = function(type, page, current) {
            //          return "${pageContext.request.contextPath }/findAll.do?pageNo="
            //                  + page;
            //      };
            //分页回调函数,点击按钮事件
            // myoptions.onPageClicked = function(event, originalEvent, type, page) {
            //     //ajax回调接收数据成功时再重新初始化分页按钮
            //     console.log("pageNo=", page);
            // };
            //
            // //分页,在bootstrap-mypaginator.js中
            // myPaginatorFun("myPages", 1, 5);
        });

        function imageUpload(item){
            $(item).click();
        }
        function imageChange(item){
            var file = item.files[0];//获取选中的第一个文件
            $(".file").html(file.name);
            //console.log("file", file.name);
        }
    </script>

</head>

<body>
<div class="panel panel-default" id="userPic">
    <div class="panel-heading">
        <h3 class="panel-title">课程管理<span id="errorMsg"></span></h3>
    </div>
    <div>
        <input type="button" value="添加课程" class="btn btn-primary" id="doCourse" style="margin: 5px 5px 5px 15px;" />
        <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 0px;" />
        <input type="button" class="btn btn-primary" id="show-course-search" value="展开搜索" />
        <input type="button" value="收起搜索" class="btn btn-primary" id="upp-course-search" style="display: none;" />
    </div>
    <!-- 展开搜索条件 -->
    <div class="panel-body">
        <div class="show-course-search" style="display: none;">
            <form id="searchForm" class="form-horizontal" action="${pageContext.request.contextPath}/course/back/findByParams.do">
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="author-name-search" class="col-xs-3 control-label">作者：</label>
                        <div class="col-xs-8">
                            <input type="text" name="author" class="form-control" id="author-name-search" placeholder="请输入作者" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="course-name-search" class="col-xs-3 control-label">课程名称：</label>
                        <div class="col-xs-8">
                            <input type="text" name="courseName" class="form-control" id="course-name-search" placeholder="请输入课程名称" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="course-status-search" class="col-xs-3 control-label">状态：</label>
                        <div class="col-xs-8">
                            <select class="form-control" id="course-status-search" name="status" >
                                <option value="-1" >全部</option>
                                <option value="0" >启用</option>
                                <option value="1" >禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="course-type-id-search" class="col-xs-3 control-label">课程类别：</label>
                        <div class="col-xs-8">
                            <select class="form-control" id="course-type-id-search" name="courseTypeId" >
                                <option value="-1" >全部</option>
                                <c:forEach items="${courseTypes}" var="type">
                                    <option value="${type.id}" >${type.typeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">开始日期：</label>
                        <div class="col-xs-8">
                            <input type="text" name="startDate" class="form-control" placeholder="请输入开始时间:yyyy-MM-dd" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">结束日期：</label>
                        <div class="col-xs-8">
                            <input type="text" name="endDate" class="form-control" placeholder="请输入结束时间:yyyy-MM-dd" />
                        </div>
                    </div>
                </div>

            </form>
        </div>

        <div class="modal fade" tabindex="-1" id="Course">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <form id="addForm" enctype="multipart/form-data" method="post">
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">添加课程</h4>
                    </div>

                    <div class="modal-body text-center">
                        <div class="row text-right" id="course-id-input" style="display: none;" >
                            <label for="course-id" class="col-xs-4 control-label">课程编号：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="course-id" name="courseId" readonly="true" />
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="course-name" class="col-xs-4 control-label">课程名称：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="course-name" name="courseName" />
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="course-info" class="col-xs-4 control-label">课程简介：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="course-info" name="courseInfo" />
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="course-author" class="col-xs-4 control-label">作者：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="course-author" name="author" />
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label class="col-xs-4 control-label">封面图片：</label>
                            <div class="col-xs-4">
                                <a href="javascript:imageUpload('#cover-image');" class="file" >选择文件</a>
                                <input type="file" name="coverImage" style="display: none;" onchange="imageChange(this)" id="cover-image" />
                            </div>
                        </div>
                        <div class="row text-right">
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="course-type-id" class="col-xs-4 control-label">课程类别：</label>
                            <div class="col-xs-4">
                                <select class="form-control" id="course-type-id" name="courseTypeId" >
                                    <option value="-1" >请选择</option>
                                    <c:forEach items="${courseTypes}" var="type">
                                        <option value="${type.id}" >${type.typeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button name="confirmCourseButton" type="button" class="btn btn-primary curse-btn">确定</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="show-list">
            <table class="table table-bordered table-hover" style="text-align: center;">
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">课程名称</th>
                    <th class="text-center">作者</th>
                    <th class="text-center">封面图片</th>
                    <th class="text-center">点击量</th>
                    <th class="text-center">课程类别</th>
                    <th class="text-center">创建时间</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageCourses.list}" var="course">
                    <tr>
                    <td>${course.id}</td>
                    <td>${course.courseName}</td>
                    <td>${course.author}</td>
                    <td><image class="img" src="${pageContext.request.contextPath}/images/java-base.png" alter="no image" /></td>
                    <td>${course.clickNumber}</td>
                    <td>${course.courseType.typeName}</td>
                    <td>${course.createDate}</td>
                    <c:if test="${course.status eq 1}">
                        <td>禁用</td>
                        <td class="text-center">
                            <input courseId="${course.id}" type="button" class="btn btn-warning btn-sm doCourseModify" value="修改" />
                            <input courseId="${course.id}" status="${course.status}" name="changeStatus" type="button" class="btn btn-success btn-sm" value="启用" />
                            <input type="button" class="btn btn-success btn-sm course-detail" value="章节详情" />
                        </td>
                    </c:if>
                    <c:if test="${course.status eq 0}">
                        <td>启用</td>
                        <td class="text-center">
                            <input courseId="${course.id}" type="button" class="btn btn-warning btn-sm doCourseModify" value="修改" />
                            <input courseId="${course.id}" status="${course.status}" name="changeStatus" type="button" class="btn btn-danger btn-sm" value="禁用" />
                            <input type="button" class="btn btn-success btn-sm course-detail" value="章节详情" />
                        </td>
                    </c:if>
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
