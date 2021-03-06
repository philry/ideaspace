<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午4:50
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
    <title>课程章节管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/template-web.js"></script>
    <style type="text/css" >
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
    </style>

    <script type="text/javascript" >
        $(function(){
            //返回课程列表
            $("#back").on('click', function(){
                $('#frame-id', window.parent.document).attr('src', 'back_courseSet.html');
            });
            //相关评论
            $(".comment-detail").on('click', function(){
                var id = $(this).attr("data-id");
                $('#frame-id', window.parent.document).attr('src', '${pageContext.request.contextPath}/comment/findByChapterId.do?id='+id);
            });

            //添加章节
            $("#doAddCourseReource").on('click', function(){
                $(".modal-title").html("添加章节");
                $("#course-resource-id-input").hide();
                $("#courseReourceModal").modal("show");
            });
            $(".course-reource-btn").on('click', function(){
                $("#courseReourceModal").modal("hide");
            });

            $(".course-reource-modify-btn").on('click', function(){
                $(".modal-title").html("修改章节");
                $("#course-resource-id-input").show();
                $("#courseReourceModal").modal("show");
            });

            //显示播放页
            $(".resource-detail").on('click', function(){
                $('#frame-id', window.parent.document).attr('src', 'back_resourceDetailSet.html');
            });

            // 显示隐藏查询列表
            $('#show-course-resource-search').click(function() {
                $('#show-course-resource-search').hide();
                $('#upp-course-resource-search').show();
                $('.show-course-resource-search').slideDown(500);
            });
            $('#upp-course-resource-search').click(function() {
                $('#show-course-resource-search').show();
                $('#upp-course-resource-search').hide();
                $('.show-course-resource-search').slideUp(500);
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
            myPaginatorFun("myPages", 1, 5);

        });

        function fileUpload(item){
            $(item).click();
        }
        function fileChange(item){
            var file = item.files[0];//获取选中的第一个文件
            $(".file").html(file.name);
            //console.log("file", file.name);
        }

    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">课程章节管理</h3>
    </div>
    <div>
        <input type="button" value="添加章节" class="btn btn-primary" id="doAddCourseReource" style="margin: 5px 5px 5px 15px;" />
        <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 0px;" />
        <input type="button" class="btn btn-primary" id="show-course-resource-search" value="展开搜索" />
        <input type="button" value="收起搜索" class="btn btn-primary" id="upp-course-resource-search" style="display: none;" />
        <input type="button" value="返回课程列表" class="btn btn-success" id="back" style="margin: 5px 15px 5px 0px;float: right;" />
    </div>
    <div class="panel-body">
        <div class="show-course-resource-search" style="display: none;">
            <form class="form-horizontal">
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="course-resource-title-search" class="col-xs-3 control-label">标题：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="course-resource-title-search" placeholder="请输入标题" />
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="course-resource-info-search" class="col-xs-3 control-label">简介：</label>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" id="course-resource-info-search" placeholder="请输入简介" />
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
                <div class="form-group">
                    <div class="form-group col-xs-6">
                        <label for="course-resource-stauts-search" class="col-xs-3 control-label">状态：</label>
                        <div class="col-xs-8">
                            <select class="form-control" id="course-resource-stauts-search" name="course-resource-stauts-search" >
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
                    <th class="text-center">标题</th>
                    <th class="text-center">简介</th>
                    <th class="text-center">创建时间</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${chapters.list}" var="chapter" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${chapter.title}</td>
                    <td>${chapter.info}</td>
                    <td>${chapter.createDate}</td>
                    <c:if test="${chapter.status eq 0}"><td>启用</td></c:if>
                    <c:if test="${chapter.status eq 1}"><td>禁用</td></c:if>
                    <td class="text-center">
                        <input type="button" class="btn btn-warning btn-sm course-reource-modify-btn" value="修改">
                        <c:if test="${chapter.status eq 0}">
                            <input type="button" class="btn btn-danger btn-sm" value="禁用">
                        </c:if>
                        <c:if test="${chapter.status eq 1}">
                            <input type="button" class="btn btn-success btn-sm" value="启用">
                        </c:if>
                        <input type="button" class="btn btn-success btn-sm resource-detail" value="详情" />
                        <input type="button" class="btn btn-success btn-sm comment-detail" data-id="${chapter.id}" value="相关评论" />
                    </td>
                </tr>
                </c:forEach>
                <tr>
                    <td>2</td>
                    <td>java基础</td>
                    <td>java基础相关</td>
                    <td>2017-10-10 12:00:00</td>
                    <td>禁用</td>
                    <td class="text-center">
                        <input type="button" class="btn btn-warning btn-sm course-reource-modify-btn" value="修改">
                        <input type="button" class="btn btn-success btn-sm" value="启用">
                        <input type="button" class="btn btn-success btn-sm resource-detail" value="详情" />
                        <input type="button" class="btn btn-success btn-sm comment-detail" value="相关评论" />
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <!-- 分页 -->
        <div style="text-align: center;" >
            <ul id="myPages" ></ul>
        </div>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="courseReourceModal" >
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" >添加章节</h4>
            </div>
            <div class="modal-body text-center">
                <!-- course-id,没有时,移除 '返回课程列表' 按钮 -->
                <input type="hidden" name="course_id" value=""  />
                <div class="row text-right" id="course-resource-id-input" >
                    <label for="course-resource-id" class="col-xs-4 control-label">章节编号：</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" id="course-resource-id" readonly="true" />
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="course-resource-title" class="col-xs-4 control-label">章节标题：</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" id="course-resource-title" />
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="course-resource-context" class="col-xs-4 control-label">内容详情：</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" id="course-resource-context" />
                    </div>
                </div>
                <br>
                <p/>
                <div class="row text-right">
                    <label class="col-xs-4 control-label">资源：</label>
                    <div class="col-xs-4">
                        <a href="javascript:fileUpload('#course-resource-file');" class="file" >选择文件</a>
                        <input type="file" name="course_resource_file" style="display: none;" onchange="fileChange(this)" id="course-resource-file" />
                    </div>
                </div>
                <br/>
                <div class="row text-right">
                    <label for="course-resource-file-title" class="col-xs-4 control-label">资源标题：</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" id="course-resource-file-title" />
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="file-cost-type" class="col-xs-4 control-label">查看资源花费类型：</label>
                    <div class="col-xs-4">
                        <select class="form-control" id="file-cost-type" name="file_cost_type_id" >
                            <option value="-1" >请选择</option>
                            <option value="1" >积分</option>
                            <option value="2" >金币</option>
                        </select>
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="file-cost-type-val" class="col-xs-4 control-label">花费值：</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" id="file-cost-type-val" />
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="resource-type-id" class="col-xs-4 control-label">资源类型：</label>
                    <div class="col-xs-4">
                        <select class="form-control" id="resource-type-id" name="resource_type_id" >
                            <option value="-1" >请选择</option>
                            <option value="1" >mp4</option>
                            <option value="2" >pdf</option>
                        </select>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary course-reource-btn">确定</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
</body>

</html>
