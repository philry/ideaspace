<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午4:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>我的课程</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="iconfont/font_0/iconfont.css">
    <link rel="stylesheet" href="css/style.css">
    <style type="text/css">
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
        html,body {
            height: 100%;
        }

        body > .wrap-cc{
            min-height: 100%;
        }

        .content-cc{
            /* padding-bottom 等于 footer 的高度 */
            padding-bottom: 80px;
        }

        .footer-cc {
            width: 100%;
            height: 80px;
            /* margin-top 为 footer 高度的负值 */
            margin-top: -80px;
        }
    </style>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function() {
            $(".arrow").click(function() {

                $(this).parent().next().toggle();

            })

            $(".title-ul>li").on('click', function() {
                console.log($(this).attr("show"));
                $('.' + $(this).attr("show")).show().siblings().hide();

                $(this).addClass('current').siblings().removeClass("current");

            })

            $(".source-modify").on('click', function() {
                $("#user_source").modal("show");
            });

        })

        function openAdd() {
            $("#user_source").modal("show");
        }

        function fileUpload(item) {
            $(item).click();
        }

        function fileChange(item) {
            var file = item.files[0]; //获取选中的第一个文件
            $(".file").html(file.name);
            //console.log("file", file.name);
        }
    </script>
</head>

<body>
<div class="wrap-cc">
    <div class="content-cc">
        <nav class="navbar navbar-default">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <!--  <a class="navbar-brand" href="#">Brand</a> -->
                    <img src="images/com-logo1.png" alt="" width="120" style="margin-right: 20px;">
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="#" class="vertical-middle">免费课程</a></li>
                        <li><a href="#" class="vertical-middle">职业路径</a></li>
                    </ul>
                    <form class="navbar-form navbar-left searchInput" style="padding:10px;">
                        <div class="form-group">
                            <input type="text" class="form-control " placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default "><span class="glyphicon glyphicon-search"></span></button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                                <img class="img-circle" src="images/user.jpeg" id="userImg">
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu userinfo cc">
                                <li>
                                    <img src="images/user.jpeg" class="img-circle" alt="">
                                    <div class="">
                                        <p>我叫细倩倩</p>
                                        <p>金币<span>236</span>&nbsp;积分 <span>0</span></p>
                                    </div>
                                </li>
                                <li>
                                    <a href="front_mycourse.html">
                                        <i class="glyphicon glyphicon-edit"></i>我的课程
                                    </a>
                                    <a href="front_record.html">
                                        <i class="glyphicon glyphicon-record"></i>积分记录
                                    </a>
                                </li>
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#userSet">
                                        <i class="glyphicon glyphicon-cog"></i>个人设置
                                    </a>
                                    <a href="javascript:void(0);"><i class="glyphicon glyphicon-off"></i>退出登录</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>
        <div class="container-fluid banner">
            <div class="container banner-mycourse">
                <div class="row">
                    <p>user01</p>
                </div>
                <div class="row">
                    <img src="images/girl.png" alt="" width="18">&nbsp;&nbsp;
                    <span>学习时长</span>&nbsp;
                    <span>94小时</span>&nbsp;
                    <span>积分0</span>&nbsp;
                    <span>经验256</span>
                </div>
                <div class="row">
                    这位同学很懒，木有签名的说~~
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <ul class="title-ul">
                    <li class="current" show='cc-course'>最近学习</li>
                    <li class="source" show='cc-source'>我的资源</li>
                </ul>
                <div>
                    <!-- 最近学习 -->
                    <ul class="mycourse-content cc-course">
                        <li>
                            <div class="col-md-1 col-sm-1">
                                <strong>2018</strong>
                                <div>
                                    01月31日 13:50:06
                                </div>
                            </div>
                            <div class="col-md-11 col-sm-11">
                                <span class="circle"></span>
                                <div class="row  border-bottom">
                                    <div class="col-md-3">
                                        <img src="images/timg.jpg" alt="" height="120" class="mycourseImg">
                                    </div>
                                    <div class="col-md-8 mycourse-info">
                                        <p class="padding-top-25">
                                        <span>
                                       神经网络简介
                                    </span>
                                            <span>
                                         更新至3-1
                                     </span>
                                        </p>
                                        <p class="padding-10">
                                            <span>已学8%</span> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span>
                                            用时34分
                                        </span> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span>
                                            学习至1-1 01课程背景
                                        </span>
                                        </p>
                                        <div>
                                            <div>
                                                笔记 0
                                            </div>
                                            <div>
                                                代码0
                                            </div>
                                            <div>
                                                问答0
                                            </div>
                                            <div>
                                                继续学习
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="col-md-1 col-sm-1">
                                <strong>2018</strong>
                                <div>
                                    01月31日 16:40:36
                                </div>
                            </div>
                            <div class="col-md-11 col-sm-11">
                                <span class="circle"></span>
                                <div class="row border-bottom">
                                    <div class="col-md-3">
                                        <img src="images/timg.jpg" alt="" height="120" class="mycourseImg">
                                    </div>
                                    <div class="col-md-8 mycourse-info">
                                        <p class="padding-top-25">
                                        <span>
                                       神经网络简介
                                    </span>
                                            <span>
                                         更新至3-1
                                     </span>
                                        </p>
                                        <p class="padding-10">
                                            <span>已学8%</span> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span>
                                            用时34分
                                        </span> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span>
                                            学习至1-1 01课程背景
                                        </span>
                                        </p>
                                        <div>
                                            <div>
                                                笔记 0
                                            </div>
                                            <div>
                                                代码0
                                            </div>
                                            <div>
                                                问答0
                                            </div>
                                            <div>
                                                继续学习
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="load-more">
                                <span onclick="alert('正在加载...');">加载更多...</span>
                            </div>
                        </li>
                    </ul>
                    <!-- 我的资源 -->
                    <ul class="mycourse-content cc-source">
                        <li style="text-align: right;margin-top:20px; ">
                            <button class="btn btn-primary" onclick="openAdd()" style="width: 100px;">添加资源</button>
                        </li>
                        <li>
                            <div class="col-md-1 col-sm-1">
                                <strong>2018</strong>
                                <div>
                                    01月31日 13:50:06
                                </div>
                            </div>
                            <div class="col-md-11 col-sm-11">
                                <span class="circle"></span>
                                <div class="row  border-bottom">
                                    <div class="col-md-3">
                                        <img src="images/timg.jpg" alt="" height="120" class="mycourseImg">
                                    </div>
                                    <div class="col-md-8 mycourse-info">
                                        <p class="padding-top-25">
                                        <span>
                                            我的资源01
                                        </span>
                                            <span>
                                            313M
                                        </span>
                                        </p>
                                        <p class="padding-10">
                                            <span>mp4</span> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span>
                                            91min
                                        </span> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span>
                                            xxx
                                        </span>
                                        </p>
                                        <div>
                                            <div>
                                                积分制
                                            </div>
                                            <div>
                                                11
                                            </div>
                                            <div>
                                                zbwroom
                                            </div>
                                            <div class="nostyle">
                                                <button class="btn btn-warning source-modify">修改</button>
                                                <button class="btn btn-danger">删除</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="col-md-1 col-sm-1">
                                <strong>2018</strong>
                                <div>
                                    01月31日 13:50:06
                                </div>
                            </div>
                            <div class="col-md-11 col-sm-11">
                                <span class="circle"></span>
                                <div class="row  border-bottom">
                                    <div class="col-md-3">
                                        <img src="images/timg.jpg" alt="" height="120" class="mycourseImg">
                                    </div>
                                    <div class="col-md-8 mycourse-info">
                                        <p class="padding-top-25">
                                        <span>
                                            我的资源02
                                        </span>
                                            <span>
                                            326M
                                        </span>
                                        </p>
                                        <p class="padding-10">
                                            <span>mp4</span> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span>
                                            106min
                                        </span> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <span>
                                            xxx
                                        </span>
                                        </p>
                                        <div>
                                            <div>
                                                金币
                                            </div>
                                            <div>
                                                22
                                            </div>
                                            <div>
                                                zbwroom
                                            </div>
                                            <div class="nostyle">
                                                <button class="btn btn-warning source-modify">修改</button>
                                                <button class="btn btn-danger">删除</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>                    <li>
                        <div class="col-md-1 col-sm-1">
                            <strong>2018</strong>
                            <div>
                                01月31日 13:50:06
                            </div>
                        </div>
                        <div class="col-md-11 col-sm-11">
                            <span class="circle"></span>
                            <div class="row  border-bottom">
                                <div class="col-md-3">
                                    <img src="images/timg.jpg" alt="" height="120" class="mycourseImg">
                                </div>
                                <div class="col-md-8 mycourse-info">
                                    <p class="padding-top-25">
                                        <span>
                                            我的资源02
                                        </span>
                                        <span>
                                            326M
                                        </span>
                                    </p>
                                    <p class="padding-10">
                                        <span>mp4</span> &nbsp;&nbsp;&nbsp;&nbsp;
                                        <span>
                                            106min
                                        </span> &nbsp;&nbsp;&nbsp;&nbsp;
                                        <span>
                                            xxx
                                        </span>
                                    </p>
                                    <div>
                                        <div>
                                            金币
                                        </div>
                                        <div>
                                            22
                                        </div>
                                        <div>
                                            zbwroom
                                        </div>
                                        <div class="nostyle">
                                            <button class="btn btn-warning source-modify">修改</button>
                                            <button class="btn btn-danger">删除</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>                    <li>
                        <div class="col-md-1 col-sm-1">
                            <strong>2018</strong>
                            <div>
                                01月31日 13:50:06
                            </div>
                        </div>
                        <div class="col-md-11 col-sm-11">
                            <span class="circle"></span>
                            <div class="row  border-bottom">
                                <div class="col-md-3">
                                    <img src="images/timg.jpg" alt="" height="120" class="mycourseImg">
                                </div>
                                <div class="col-md-8 mycourse-info">
                                    <p class="padding-top-25">
                                        <span>
                                            我的资源02
                                        </span>
                                        <span>
                                            326M
                                        </span>
                                    </p>
                                    <p class="padding-10">
                                        <span>mp4</span> &nbsp;&nbsp;&nbsp;&nbsp;
                                        <span>
                                            106min
                                        </span> &nbsp;&nbsp;&nbsp;&nbsp;
                                        <span>
                                            xxx
                                        </span>
                                    </p>
                                    <div>
                                        <div>
                                            金币
                                        </div>
                                        <div>
                                            22
                                        </div>
                                        <div>
                                            zbwroom
                                        </div>
                                        <div class="nostyle">
                                            <button class="btn btn-warning source-modify">修改</button>
                                            <button class="btn btn-danger">删除</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>                    <li>
                        <div class="col-md-1 col-sm-1">
                            <strong>2018</strong>
                            <div>
                                01月31日 13:50:06
                            </div>
                        </div>
                        <div class="col-md-11 col-sm-11">
                            <span class="circle"></span>
                            <div class="row  border-bottom">
                                <div class="col-md-3">
                                    <img src="images/timg.jpg" alt="" height="120" class="mycourseImg">
                                </div>
                                <div class="col-md-8 mycourse-info">
                                    <p class="padding-top-25">
                                        <span>
                                            我的资源02
                                        </span>
                                        <span>
                                            326M
                                        </span>
                                    </p>
                                    <p class="padding-10">
                                        <span>mp4</span> &nbsp;&nbsp;&nbsp;&nbsp;
                                        <span>
                                            106min
                                        </span> &nbsp;&nbsp;&nbsp;&nbsp;
                                        <span>
                                            xxx
                                        </span>
                                    </p>
                                    <div>
                                        <div>
                                            金币
                                        </div>
                                        <div>
                                            22
                                        </div>
                                        <div>
                                            zbwroom
                                        </div>
                                        <div class="nostyle">
                                            <button class="btn btn-warning source-modify">修改</button>
                                            <button class="btn btn-danger">删除</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>                    <li>
                        <div class="col-md-1 col-sm-1">
                            <strong>2018</strong>
                            <div>
                                01月31日 13:50:06
                            </div>
                        </div>
                        <div class="col-md-11 col-sm-11">
                            <span class="circle"></span>
                            <div class="row  border-bottom">
                                <div class="col-md-3">
                                    <img src="images/timg.jpg" alt="" height="120" class="mycourseImg">
                                </div>
                                <div class="col-md-8 mycourse-info">
                                    <p class="padding-top-25">
                                        <span>
                                            我的资源02
                                        </span>
                                        <span>
                                            326M
                                        </span>
                                    </p>
                                    <p class="padding-10">
                                        <span>mp4</span> &nbsp;&nbsp;&nbsp;&nbsp;
                                        <span>
                                            106min
                                        </span> &nbsp;&nbsp;&nbsp;&nbsp;
                                        <span>
                                            xxx
                                        </span>
                                    </p>
                                    <div>
                                        <div>
                                            金币
                                        </div>
                                        <div>
                                            22
                                        </div>
                                        <div>
                                            zbwroom
                                        </div>
                                        <div class="nostyle">
                                            <button class="btn btn-warning source-modify">修改</button>
                                            <button class="btn btn-danger">删除</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                        <li>
                            <div class="load-more">
                                <span onclick="alert('正在加载...');">加载更多...</span>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="modal fade" id="userSet" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">个人信息</h4>
                    </div>
                    <form action="../../php/editPassword.php" class="form-horizontal" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">旧密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="password" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">新密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" name="newPassword" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">确认密码：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="password" name="rePassword" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">昵称：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="nickname" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">邮箱：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="email" />
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                            <button type="reset" class="btn btn-info">重&nbsp;&nbsp;置</button>
                            <button type="submit" class="btn btn-info">确&nbsp;&nbsp;定</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- 资源模态框 -->
        <div class="modal fade" id="user_source" tabindex="-1" role="dialog" aria-labelledby="mySourceModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="mySourceModalLabel">资源</h4>
                    </div>
                    <form action="#" class="form-horizontal" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">标题：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="title" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">资源：</label>
                                <div class="col-sm-6">
                                    <a href="javascript:fileUpload('#course-resource-file');" class="file">选择文件</a>
                                    <input type="file" name="course_resource_file" style="display: none;" onchange="fileChange(this)" id="course-resource-file" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">资源花费类型：</label>
                                <div class="col-sm-6">
                                    <select class="form-control" id="file-cost-type" name="file_cost_type_id">
                                        <option value="-1">请选择</option>
                                        <option value="1">积分</option>
                                        <option value="2">金币</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">花费值：</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="cost_value" />
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                            <button type="submit" class="btn btn-info">确&nbsp;&nbsp;定</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer-cc">
    <div class="footer">
        <div>
            版权所有： 南京网博
        </div>
        <div>
            Copyright © 2017 imooc.com All Rights Reserved | 京ICP备 13046642号-2
        </div>
    </div>
</div>


</body>

</html>
