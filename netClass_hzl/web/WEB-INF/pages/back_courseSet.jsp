<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午5:11
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
            // 课程管理 修改课程
            $(".doCourseModify").on("click", function() {
                $("#course-id-input").show();
                $(".file").html("选择文件");
                $("#cover-image").val(undefined);
                $(".modal-title").html("修改课程");
                $("#Course").modal("show");
                flag=1;
            });
            $(".curse-btn").on("click", function() {
                $("#Course").modal("hide");
            });

            // 课程管理 添加课程
            $("#doCourse").on("click", function() {
                $("#course-id-input").hide();
                $(".file").html("选择文件");
                $("#cover-image").val(undefined);
                $(".modal-title").html("添加课程");
                $("#Course").modal("show");
                flag=0;
            });

            //课程章节显示
            $(".course-detail").on("click", function() {
                $('#frame-id', window.parent.document).attr('src', '${pageContext.request.contextPath}/courseResource/findAll.do');
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
            myoptions.onPageClicked = function(event, originalEvent, type, page) {
                //ajax回调接收数据成功时再重新初始化分页按钮
                console.log("pageNo=", page);
            };

            //分页,在bootstrap-mypaginator.js中
          //  myPaginatorFun("myPages", 1, 5);


            var pages;
            if("${pageCourses.pages}" == 0){
                pages = 1;
            }else{
                pages = "${pageCourses.pages}";
            }

            //使用bootstrap分页插件实现前台分页
            var options = {
                bootstrapMajorVersion:3,//表示当前bootstrap版本号
                currentPage:"${pageCourses.pageNum}",//当前页,pageCourses是controller传过来的的分页对象
                totalPages:pages,//总页数
                size:"normal",
                aligment:"center",
                pageUrl:function(type,page,current){
                    return "${pageContext.request.contextPath}/course/findAll.do?pageNo="+page;
                }
            };
            $("#myPages").bootstrapPaginator(options);

            $("#addCourse").click(function(){
                //添加课程
                if(flag==0){
                    //有 form时的数据处理方式，将form包装成一个对象，包含form里的所有数据
                    var formData = new FormData($("[name='doCourse']")[0]);
                    console.log('form=', $("[name='doCourse']")[0]);
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/course/addCourse.do",
                        data:formData,
                        /*没有form时 数据的处理方式
                        data:{
                            "course_name":$("#course-name").val(),
                            "course_info":$("#course_info").val(),
                            "author":$("#course-author").val(),
                            "recommendation_grade":$("#recommendation-grade").val(),
                            "course_type_id":$("#course_type_id").val()
                        },*/
                        processData : false,//ajax上传文件必须的属性1
                        contentType : false,//ajax上传文件必须的属性2
                        dataType:"json",
                        success : function() {
                            location.href="${pageContext.request.contextPath}/course/findAll.do?pageNo=${pageCourses.pageNum}";
                        },
                        error : function(ar) {
                            alert(ar.msg);
                        }
                    });
                    //修改课程
                }else if(flag==1){
                    //有 form时的数据处理方式，将form包装成一个对象，包含form里的所有数据
                    var formData = new FormData($("[name='doCourse']")[0]);
                    console.log('form=', $("[name='doCourse']")[0]);
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/course/modifyCourse.do",
                        data:formData,
                        /*没有form时 数据的处理方式
                        data:{
                            "course_name":$("#course-name").val(),
                            "course_info":$("#course_info").val(),
                            "author":$("#course-author").val(),
                            "recommendation_grade":$("#recommendation-grade").val(),
                            "course_type_id":$("#course_type_id").val()
                        },*/
                        processData : false,//ajax上传文件必须的属性1
                        contentType : false,//ajax上传文件必须的属性2
                        dataType:"json",
                        success : function() {
                            location.href="${pageContext.request.contextPath}/course/findAll.do?pageNo=${pageCourses.pageNum}";
                        },
                        error : function(ar) {
                            alert(ar.msg);
                        }
                    });
                }
            })

            //修改模态框页面回显
            $("input[name='findById']").click(function(){
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/course/findById.do",
                    data:{"courseId":$(this).attr("courseId")},
                    dataType:"json",
                    success : function(result) {
                        $("#course-id").val(result.obj.id),
                            $("#course-name").val(result.obj.name),
                            $("#course-info").val(result.obj.courseInfo),
                            $("#course-author").val(result.obj.author),
                            $("#recommendation-grade").val(result.obj.recommmendGreade),
                            $("#course-type-id").val(result.obj.courseType.id)
                    },
                    error : function(result) {
                        alert(result.msg);
                    }
                });
            })

            //修改状态
            $("input[name='modifyStatus']").click(function(){
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/course/changeStatus.do",
                    data:{"courseId":$(this).attr("courseId")},
                    success:function() {
                        location.href="${pageContext.request.contextPath}/course/findAll.do?pageNo=${pageCourses.pageNum}";
                    },
                    error : function(ar) {
                        alert("修改失败");
                    }
                });
            });


        });

        function imageUpload(item){
            $(item).click();
        }
        function imageChange(item){
            var file = item.files[0];//获取选中的第一个文件
            $(".file").html(file.name);
            console.log("file", file.name);
        }
    </script>
    <%--<script type="text/javascript">
        $(function(){

        })
    </script>--%>
</head>

<body>
<div class="panel panel-default" id="userPic">
    <div class="panel-heading">
        <h3 class="panel-title">课程管理</h3>
    </div>
    <div>
        <input type="button" value="添加课程" class="btn btn-primary" id="doCourse" style="margin: 5px 5px 5px 15px;" />
        <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 0px;" />
        <input type="button" class="btn btn-primary" id="show-course-search" value="展开搜索" />
        <input type="button" value="收起搜索" class="btn btn-primary" id="upp-course-search" style="display: none;" />
    </div>
    <div class="panel-body">
        <div class="show-course-search" style="display: none;">
            <form class="form-horizontal">
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="author-name-search" class="col-xs-3 control-label">作者：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="author-name-search" placeholder="请输入作者" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="course-name-search" class="col-xs-3 control-label">课程名称：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="course-name-search" placeholder="请输入课程名称" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="course-status-search" class="col-xs-3 control-label">状态：</label>
                        <div class="col-xs-8">
                            <select class="form-control" id="course-status-search" name="course-status-search" >
                                <option value="-1" >全部</option>
                                <option value="0" >启用</option>
                                <option value="1" >禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="course-type-id-search" class="col-xs-3 control-label">课程类别：</label>
                        <div class="col-xs-8">
                            <select class="form-control" id="course-type-id-search" name="course_type_id-search" >
                                <option value="-1" >全部</option>
                                <option value="1" >java</option>
                                <option value="2" >javascript</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">开始日期：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" placeholder="请输入开始时间:2017-10-10" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-3 control-label">结束日期：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" placeholder="请输入结束时间:2017-10-12" />
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
                    <form method="post" name="doCourse" enctype="multipart/form-data" action="${pageContext.request.contextPath}/course/addCourse.do">
                    <div class="modal-header">                                 <%--上面这里的action地址只有在用表单提交时才有用(即button='submit',点击按钮触发)--%>
                        <button class="close" data-dismiss="modal">&times;</button>     <%--这里用了ajax方法，action不起作用--%>
                        <h4 class="modal-title">添加课程</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right" id="course-id-input" style="display: none;" >
                            <label for="course-id" class="col-xs-4 control-label">课程编号：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="course-id" name="course_id" readonly="true" />
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="course-name" class="col-xs-4 control-label">课程名称：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="course-name" name="course_name" />
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="course-info" class="col-xs-4 control-label">课程简介：</label>
                            <div class="col-xs-4">
                                <input type="text" class="form-control" id="course-info" name="course_info" />
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
                                <input type="file" name="cover_image" style="display: none;" onchange="imageChange(this)" id="cover-image" />
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="recommendation-grade" class="col-xs-4 control-label">推荐等级：</label>
                            <div class="col-xs-4">
                                <select class="form-control" id="recommendation-grade" name="recommendation_grade " >
                                    <option value="-1" >请选择</option>
                                    <option value="0" >普通</option>
                                    <option value="1" >推荐</option>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="course-type-id" class="col-xs-4 control-label">课程类别：</label>
                            <div class="col-xs-4">
                                <select class="form-control" id="course-type-id" name="course_type_id" >
                                    <option value="-1" >请选择</option>
                                    <c:forEach items="${courseTypes}" var="courseType">
                                    <option value="${courseType.id}">${courseType.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <br>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary curse-btn" id="addCourse" type="button">确定</button>
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
                    <th class="text-center">推荐等级</th>
                    <th class="text-center">课程类别</th>
                    <th class="text-center">创建时间</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.id}</td>
                    <td>${course.name}</td>
                    <td>${course.author}</td>
                    <td><image class="img" src="${pageContext.request.contextPath}/${course.imageUrl}" alter="no image" /></td>
                    <td>${course.clickNum}</td>
                    <td>
                        <c:if test="${course.recommmendGreade eq 0}">普通</c:if>
                        <c:if test="${course.recommmendGreade eq 1}">推荐</c:if>
                    </td>
                    <td>${course.courseType.name}</td>
                    <td>${course.createDate}</td>
                    <td>
                    <c:if test="${course.status eq 0}">启用</c:if>
                    <c:if test="${course.status eq 1}">禁用</c:if>
                    </td>
                    <td class="text-center">
                        <input type="button" class="btn btn-warning btn-sm doCourseModify" value="修改" name="findById" courseId="${course.id}"/>
                        <c:if test="${course.status eq 0}">
                            <input type="button" class="btn btn-danger btn-sm"  name="modifyStatus" value="禁用" courseId="${course.id}"/>
                        </c:if>
                        <c:if test="${course.status eq 1}">
                            <input type="button" class="btn btn-danger btn-sm" name="modifyStatus" value="启用" courseId="${course.id}"/>
                        </c:if>
                        <input type="button" class="btn btn-success btn-sm course-detail" value="章节详情" />
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