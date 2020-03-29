<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>优学堂</title>
    <!-- js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/swiper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/front-index.js"></script>
    <script src="${pageContext.request.contextPath}/js/template-web.js"></script>
    <!-- css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href='${pageContext.request.contextPath}/iconfont/font_1/iconfont.css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/front-index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
    <style>
        html,
        body {
            height: 100%;
        }

        body > .wrap-cc {
            min-height: 100%;
        }

        .content-cc {
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
    <script>
        $(function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/user/check.do',
                dataType: 'json',
                data: {"loginName": sessionStorage.getItem("loginName")},
                type: 'post',
                success: function (data) {
                    console.log('login=', data);
                    if (data.success) {
                        //TODO: 显示当前用户的信息
                        $("#loginOn-nickname").html(data.obj.nickname);
                        sessionStorage.setItem("loginName", data.obj.loginName);
                    }
                    changeUserDiv(data.success);
                },
                error: function () {
                    alert('error');
                }
            });


            $(".signBtn").click(function () {
                $(".expe").show().addClass('animated forward fadeOutUp');

                $(".signBtn").html("已签到").unbind("click").addClass('gray').removeClass('hoverRed');
            });

            var isLogin = false;
            console.log("isLogin=", isLogin);
            changeUserDiv(isLogin);
            /*
            $.ajax({
                url:'types.json',
                dataType:'json',
                success:function(data){
                    $('#typeItemDiv').html("");
                    console.log('template=====data====', data);
                    var txt = template('typeItemList', {list: data});
                    $('#typeItemDiv').html(txt);
                },
                error:function(){
                    alert('加载分类出错!');
                }
            });*/

            $("#showUser").click(function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/record/",
                })
            });

            $("#findMyPoint").click(function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/record/findMyPoint.do",
                    data: {"loginName": sessionStorage.getItem("loginName")},
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            $("#gold").html(result.obj[1]);
                            $("#point").html(result.obj[0]);
                        }
                    }
                })
            });

            $("#goldPointsRecord").click(function () {
                location.href = "${pageContext.request.contextPath}/record/findPointRecord.do?loginName=" +
                    sessionStorage.getItem("loginName") + "&gold=" + $("#gold").html() + "&point=" + $("#point").html() + "&nickname=" + $("#loginOn-nickname").html()
            });


            //注册
            $("#doRegist").click(function () {
                $.ajax({
                    url: '${pageContext.request.contextPath}/user/front/regist.do',
                    data: $("#registForm").serialize(),
                    dataType: 'json',
                    type: 'post',
                    success: function (result) {
                        if (result.success) {
                            $("#login_modal").modal("show");
                            $("#regist_modal").modal("hide");
                        } else {
                            $("#regist_modal").modal("show");
                            $("#registMsg").html(result.msg);
                        }
                    }
                });
            });
            //校验注册的登录名是否可用
            $("#registLoginname").blur(function () {
                var loginname = $(this).val();
                if (!loginname || !(loginname.trim())) {
                    $("#registLoginnameMsg").html("登录名不能为空").css("color", "red");
                    return;
                }
                $.ajax({
                    url: '${pageContext.request.contextPath}/user/front/checkLoginname.do',
                    data: {"loginName": loginname},
                    dataType: 'json',
                    type: 'post',
                    success: function (result) {
                        if (result.success) {
                            $("#registLoginnameMsg").html(result.msg).css("color", "green");
                        } else {
                            $("#registLoginnameMsg").html(result.msg).css("color", "red");
                            $("#registLoginname").focus();
                        }
                    }
                });
            });

        });

        function openUserModal(isRegist) {
            if (isRegist) { //是注册
                $("#regist_modal").modal("show");
                return;
            }
            //是登录
            $("#login_modal").modal("show");
        }

        function changeUserDiv(isLogin) {
            if (isLogin) { //
                $("#loginOff").hide();
                $("#loginOn").show();
                $("#login_modal").modal("hide");
                // $(".signBtn").html("已签到").unbind("click").addClass('gray').removeClass('hoverRed');
            } else {
                $("#loginOn").hide();
                $("#loginOff").show();
            }
        }

        function userLogin() {
            var user = $("#userLoginForm").serializeArray();
            console.log('user=', user);
            //TODO: 数据格式验证

            $.ajax({
                url: '${pageContext.request.contextPath}/user/front/login.do',
                dataType: 'json',
                data: user,
                type: 'post',
                success: function (data) {
                    console.log('login=', data);
                    alert(data.msg);//提示信息
                    if (data.success) {
                        $("#loginOff").hide();
                        $("#loginOn").show();//显示当前用户的信息组件
                        //TODO: 显示当前用户的信息
                        $("#loginOn-nickname").html(data.obj.nickname);
                        $("#y").val(data.obj.email);
                        $("#x").val(data.obj.nickname);

                        $("#login_modal").modal("hide");
                        // $(".signBtn").html("已签到").unbind("click").addClass('gray').removeClass('hoverRed');
                        sessionStorage.setItem("loginName", data.obj.loginName);

                    }
                },
                error: function () {
                    alert('error');
                }
            });
        }

        function userLoginOut() {

            location.href = "${pageContext.request.contextPath}/user/front/loginOut.do?loginName=" + sessionStorage.getItem("loginName");
        }

        //修改
        function modifyUser() {
            var user = $("#modify").serializeArray();
            console.log(user)
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/front/modifyUser.do",
                data: {
                    "loginName": sessionStorage.getItem("loginName"),
                    "password": $("#v").val(),
                    "newPassword": $("#m").val(),
                    "rePassword": $("#o").val(),
                    "nickName": $("#x").val(),
                    "email": $("#y").val()
                },
                dataType: "json",
                success: function (data) {
                    alert(data.msg);
                    if (data.success) {
                        $("#userSet").modal("hide");
                    }
                },
                error: function (data) {
                    alert('error');
                }

            })

        }

        function sign() {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/front/sign.do",
                data: {
                    "loginName": sessionStorage.getItem("loginName"),
                    //  "pointsTotal":$("#point").val()
                },
                dataType: "json",
                success: function (data) {
                    alert(data.msg);
                    if (data.success) {
                        $(".expe").show().addClass('animated forward fadeOutUp');
                        $(".signBtn").html("已签到").unbind("click").addClass('gray').removeClass('hoverRed');
                        // $("#point").html(data.obj.pointsTotal);

                    }

                }
            })
        }

        function sign() {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/front/sign.do",
                data: {
                    "loginName": sessionStorage.getItem("loginName"),
                },
                dataType: "json",
                success: function (data) {
                    alert(data.msg);
                    if (data.success) {
                        $(".expe").show().addClass('animated forward fadeOutUp');
                        $(".signBtn").html("已签到").unbind("click").addClass('gray').removeClass('hoverRed');

                    }
                },
                error: function (data) {
                    alert('error');
                }

            })

        }


    </script>
</head>

<body>
<div class="wrap-cc">

    <div class="content-cc">
        <!-- head -->
        <nav class="navbar navbar-default">
            <div class="container-fluid" style="background: #fff;box-shadow: 5px 5px 5px #eef;padding-left: 20px;">
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
                    <form action="front_select.html" class="navbar-form navbar-left searchInput" style="padding:10px;">
                        <div class="form-group">
                            <input type="text" class="" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default "><span class="glyphicon glyphicon-search"></span>
                        </button>
                    </form>
                    <div id="loginOff" class="regist ccc">
                        <span style="margin-right: 20px;font-size: 14px;">下载APP</span>
                        <a href="javascript:openUserModal(false);" class="ccc">登录</a> &nbsp;&nbsp;/&nbsp;&nbsp;
                        <a href="javascript:openUserModal(true);" class="ccc">注册</a>
                    </div>
                    <ul id="loginOn" class="nav navbar-nav navbar-right">
                        <li class="nav navbar-nav signIn">
                            <div onclick="sign()" class="signBtn hoverRed">签到</div>
                            <div class="expe">+5经验值</div>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button"
                               id="findMyPoint">
                                <img class="img-circle" src="images/user.jpeg" id="userImg">
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu userinfo cc">
                                <li>
                                    <img src="images/user.jpeg" class="img-circle" alt="">
                                    <div class="" name="loginName">
                                        <p id="loginOn-nickname"></p>
                                        <p>金币<span id="gold"></span>&nbsp;积分 <span id="point"></span></p>
                                    </div>
                                </li>
                                <li>
                                    <a href="front_mycourse.html">
                                        <i class="glyphicon glyphicon-edit"></i>我的课程
                                    </a>
                                    <a id="goldPointsRecord">
                                        <i class="glyphicon glyphicon-record"></i>积分记录
                                    </a>
                                </li>
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#userSet">
                                        <i class="glyphicon glyphicon-cog"></i>个人设置
                                    </a>
                                    <a href="javascript:userLoginOut();"><i class="glyphicon glyphicon-off"></i>退出登录</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>
        <!-- nav -->
        <div class="main">
            <!-- 左侧 -->
            <div id="typeItemDiv" class="menu-left">
                <div class="item" data-id="a">
                    <a href="#">
                        <span class="group">前端开发1</span>
                        <span class="tip">&gt;</span>
                    </a>
                    <div class="course-detail">
                        <div class="one">
                            <div class="base">
                                <span>基础</span>
                                <div class="line"></div>
                            </div>
                            <p>
                                <a href="#"><span>HTML/CSS</span></a>
                                <!-- <a href="#"><span>JavaScript</span></a>


                                <a href="#"><span>jQuery</span></a> -->
                            </p>
                        </div>
                        <!-- <div class="one">
                            <div class="base">
                                <span>进阶</span>
                                <div class="line"></div>
                            </div>
                            <p>
                                <a href="#"><span>HTML/CSS</span></a>
                                <a href="#"><span>JavaScript</span></a>
                                <a href="#"><span>jQuery</span></a>
                            </p>
                        </div> -->

                        <div class="two">
                            <div class="item-box">
                                <a href="front_course.html">
                                    <div class="item-course">
                                        <div class="item-course-l">
                                            <img src="./images/course04.jpg" alt="">
                                        </div>
                                        <div class="item-course-r">
                                            <p>前端进阶：响应式开发与常用框架</p>
                                            <p>
                                                <span>职业路径</span>
                                                <span>5步/30课</span>
                                                <span>503人</span>
                                            </p>
                                            <p>￥599.00</p>
                                        </div>
                                    </div>
                                </a>
                                <a href="front_course.html">
                                    <div class="item-course">
                                        <div class="item-course-l">
                                            <img src="./images/course02.jpg" alt="">
                                        </div>
                                        <div class="item-course-r">
                                            <p>前端进阶：响应式开发与常用框架</p>
                                            <p>
                                                <span>职业路径</span>
                                                <span>5步/30课</span>
                                                <span>503人</span>
                                            </p>
                                            <p>￥599.00</p>
                                        </div>
                                    </div>
                                </a>
                                <a href="front_course.html">
                                    <div class="item-course">
                                        <div class="item-course-l">
                                            <img src="./images/course03.jpg" alt="">
                                        </div>
                                        <div class="item-course-r">
                                            <p>前端进阶：响应式开发与常用框架</p>
                                            <p>
                                                <span>职业路径</span>
                                                <span>5步/30课</span>
                                                <span>503人</span>
                                            </p>
                                            <p>￥599.00</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="item" data-id="a">
                    <a href="#">
                        <span class="group">前端开发</span>
                        <span class="tip">&gt;</span>
                    </a>
                    <div class="course-detail">
                        <div class="one">
                            <div class="base">
                                <span>基础</span>
                                <div class="line"></div>
                            </div>
                            <p>
                                <a href="#"><span>HTML/CSS</span></a>
                                <a href="#"><span>JavaScript</span></a>
                                <a href="#"><span>jQuery</span></a>
                            </p>
                        </div>
                        <div class="one">
                            <div class="base">
                                <span>进阶</span>
                                <div class="line"></div>
                            </div>
                            <p>
                                <a href="#"><span>HTML/CSS</span></a>
                                <a href="#"><span>JavaScript</span></a>
                                <a href="#"><span>jQuery</span></a>
                            </p>
                        </div>
                        <div class="two">
                            <div class="item-box">
                                <a href="front_course.html">
                                    <div class="item-course">
                                        <div class="item-course-l">
                                            <img src="./images/course04.jpg" alt="">
                                        </div>
                                        <div class="item-course-r">
                                            <p>前端进阶：响应式开发与常用框架</p>
                                            <p>
                                                <span>职业路径</span>
                                                <span>5步/30课</span>
                                                <span>503人</span>
                                            </p>
                                            <p>￥599.00</p>
                                        </div>
                                    </div>
                                </a>
                                <a href="front_course.html">
                                    <div class="item-course">
                                        <div class="item-course-l">
                                            <img src="./images/course02.jpg" alt="">
                                        </div>
                                        <div class="item-course-r">
                                            <p>前端进阶：响应式开发与常用框架</p>
                                            <p>
                                                <span>职业路径</span>
                                                <span>5步/30课</span>
                                                <span>503人</span>
                                            </p>
                                            <p>￥599.00</p>
                                        </div>
                                    </div>
                                </a>
                                <a href="front_course.html">
                                    <div class="item-course">
                                        <div class="item-course-l">
                                            <img src="./images/course03.jpg" alt="">
                                        </div>
                                        <div class="item-course-r">
                                            <p>前端进阶：响应式开发与常用框架</p>
                                            <p>
                                                <span>职业路径</span>
                                                <span>5步/30课</span>
                                                <span>503人</span>
                                            </p>
                                            <p>￥599.00</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <script id="typeItemList" type="text/html">
                {{each list as type1 index}}
                <div class="item" typeId="{{type1.id}}">
                    <a href="#">
                        <span class="group">{{type1.typeName}}</span>
                        <span class="tip">&gt;</span>
                    </a>
                    <div class="course-detail">
                        {{each type1.children as type2 index2}}
                        <div class="one">
                            <div class="base" typeId="{{type2.id}}">
                                <span>{{type2.typeName}}</span>
                                <div class="line"></div>
                            </div>
                            <p>
                                {{each type2.children as type3 index3}}
                                <a href="#">
                                    <span typeId="{{type3.id}}">{{type3.typeName}}</span>
                                </a>
                                {{/each}}
                            </p>
                        </div>
                        {{/each}}
                        <!-- 当前分类下,点击量前四的课程 -->
                        <div class="two">
                            <div class="item-box">
                                <a href="front_course.html">
                                    <div class="item-course">
                                        <div class="item-course-l">
                                            <img src="./images/course04.jpg" alt="">
                                        </div>
                                        <div class="item-course-r">
                                            <p>前端进阶：响应式开发与常用框架</p>
                                            <p>
                                                <span>职业路径</span>
                                                <span>5步/30课</span>
                                                <span>503人</span>
                                            </p>
                                            <p>￥599.00</p>
                                        </div>
                                    </div>
                                </a>
                                <a href="front_course.html">
                                    <div class="item-course">
                                        <div class="item-course-l">
                                            <img src="./images/course02.jpg" alt="">
                                        </div>
                                        <div class="item-course-r">
                                            <p>前端进阶：响应式开发与常用框架</p>
                                            <p>
                                                <span>职业路径</span>
                                                <span>5步/30课</span>
                                                <span>503人</span>
                                            </p>
                                            <p>￥599.00</p>
                                        </div>
                                    </div>
                                </a>
                                <a href="front_course.html">
                                    <div class="item-course">
                                        <div class="item-course-l">
                                            <img src="./images/course03.jpg" alt="">
                                        </div>
                                        <div class="item-course-r">
                                            <p>前端进阶：响应式开发与常用框架</p>
                                            <p>
                                                <span>职业路径</span>
                                                <span>5步/30课</span>
                                                <span>503人</span>
                                            </p>
                                            <p>￥599.00</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                {{/each}}
            </script>

            <!-- 右侧 -->
            <div class="menu-right">
                <!-- banner -->
                <div class="banner">
                    <div class="swiper-container">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">
                                <img src="./images/banner01.jpg" alt="">
                            </div>
                            <div class="swiper-slide">
                                <img src="./images/banner02.jpg" alt="">
                            </div>
                            <div class="swiper-slide">
                                <img src="./images/banner03.jpg" alt="">
                            </div>
                            <div class="swiper-slide">
                                <img src="./images/banner04.jpg" alt="">
                            </div>
                            <div class="swiper-slide">
                                <img src="./images/banner05.jpg" alt="">
                            </div>
                        </div>
                        <!-- Add Pagination -->
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
                <!-- 课程信息 -->
                <div class="tips">
                    <div class="path-banner">
                        <a href="#">
                            <i class="i-web"></i>
                            <p class="tit">Web前端攻城狮</p>
                            <p class="desc">互联网时代最火爆的技术</p>
                        </a>
                        <a href="#">
                            <i class="i-java"></i>
                            <p class="tit">Java攻城狮</p>
                            <p class="desc">健壮、安全、适用广泛</p>
                        </a>
                        <a href="#">
                            <i class="i-android"></i>
                            <p class="tit">Android攻城狮</p>
                            <p class="desc">移动设备市场份额第一</p>
                        </a>
                        <a href="#">
                            <i class="i-php"></i>
                            <p class="tit">PHP攻城狮</p>
                            <p class="desc">世界上最好的语言：）</p>
                        </a>
                        <a href="#">
                            <i class="i-ios"></i>
                            <p class="tit">iOS攻城狮</p>
                            <p class="desc">可能是全球最好用的系统</p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- 实战推荐 -->
        <div class="course">
            <h3 class="types-title">
                <span class="tit-icon tit-icon-l"></span>
                <em>实</em>／<em>战</em>／<em>推</em>／<em>荐</em>
            </h3>
            <div class="course-box">
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course01.jpg" alt="">
                        <div class="java">
                            <label>Java</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="front_course.html">
                            <h4>Java企业级电商项目架构演进之路 Tomcat集群与Redis分布式</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course02.jpg" alt="">
                        <div class="java">
                            <label>Python</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="front_course.html">
                            <h4>全网最热Python3入门+进阶 更快上手实际开发</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course03.jpg" alt="">
                        <div class="java">
                            <label>Java</label>
                            <label>Spring Boot</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="front_course.html">
                            <h4>Spring Boot企业微信点餐系统</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course04.jpg" alt="">
                        <div class="java">
                            <label>Android</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="front_course.html">
                            <h4>人人都是架构师 从零开始设计架构并开发Android电商项目</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course05.jpg" alt="">
                        <div class="java">
                            <label>React.JS</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="front_course.html">
                            <h4>Redux+React Router+Node.js全栈开发</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
            </div>
            <div class="course-box">
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course01.jpg" alt="">
                        <div class="java">
                            <label>Java</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="#">
                            <h4>Java企业级电商项目架构演进之路 Tomcat集群与Redis分布式</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course02.jpg" alt="">
                        <div class="java">
                            <label>Python</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="#">
                            <h4>全网最热Python3入门+进阶 更快上手实际开发</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course03.jpg" alt="">
                        <div class="java">
                            <label>Java</label>
                            <label>Spring Boot</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="#">
                            <h4>Spring Boot企业微信点餐系统</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course04.jpg" alt="">
                        <div class="java">
                            <label>Android</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="#">
                            <h4>人人都是架构师 从零开始设计架构并开发Android电商项目</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course05.jpg" alt="">
                        <div class="java">
                            <label>React.JS</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="#">
                            <h4>Redux+React Router+Node.js全栈开发</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
            </div>
            <div class="course-box">
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course01.jpg" alt="">
                        <div class="java">
                            <label>Java</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="#">
                            <h4>Java企业级电商项目架构演进之路 Tomcat集群与Redis分布式</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course02.jpg" alt="">
                        <div class="java">
                            <label>Python</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="#">
                            <h4>全网最热Python3入门+进阶 更快上手实际开发</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course03.jpg" alt="">
                        <div class="java">
                            <label>Java</label>
                            <label>Spring Boot</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="#">
                            <h4>Spring Boot企业微信点餐系统</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course04.jpg" alt="">
                        <div class="java">
                            <label>Android</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="#">
                            <h4>人人都是架构师 从零开始设计架构并开发Android电商项目</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
                <div class="course-item">
                    <div class="item-t">
                        <img src="images/course05.jpg" alt="">
                        <div class="java">
                            <label>React.JS</label>
                        </div>
                    </div>
                    <div class="item-b">
                        <a href="#">
                            <h4>Redux+React Router+Node.js全栈开发</h4>
                        </a>
                        <p class="title">
                            <span>实战</span>
                            <span>高级</span>
                            <span>￥400</span>
                            <span>4星</span>
                        </p>
                        <p class="price">￥ 399.00</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer-cc">
    <div class="foots">
        <div>
            版权所有： 南京网博
        </div>
        <div>
            Copyright © 2017 imooc.com All Rights Reserved | 京ICP备 13046642号-2
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
            <form id="modify" action="#" class="form-horizontal" method="post">
                <input id="mo" type="hidden" name="loginName">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">旧密码：</label>
                        <div class="col-sm-6">
                            <input id="v" class="form-control" type="text" name="password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-6">
                            <input id="m" class="form-control" type="password" name="newPassword"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">确认密码：</label>
                        <div class="col-sm-6">
                            <input id="o" class="form-control" type="password" name="rePassword"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">昵称：</label>
                        <div class="col-sm-6">
                            <input id="x" class="form-control" type="text" name="nickname" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">邮箱：</label>
                        <div class="col-sm-6">
                            <input id="y" class="form-control" type="text" name="email" />
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭
                    </button>
                    <button type="reset" class="btn btn-info">重&nbsp;&nbsp;置</button>
                    <button onclick="modifyUser()" type="button" class="btn btn-info">确&nbsp;&nbsp;定</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- regist modal -->
<div class="modal fade" id="regist_modal" tabindex="-1" role="dialog" aria-labelledby="myRegistLabel">
    <div class="modal-dialog modal-md" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myRegistLabel">注册&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span id="registMsg" style="color:red;font-size:40px;"></span></h4>
            </div>
            <form action="#" class="form-horizontal" method="post" id="registForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录名：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="loginname" id="registLoginname"
                                   placeholder="登录名由2-20个英文字母或数字组成"/>
                            <span id="registLoginnameMsg"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" name="password" placeholder="密码由6-20位由字母或数字组成"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">确认密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" name="rePassword"
                                   placeholder="密码由6-20位由字母或数字组成"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">昵称：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="nickname" placeholder="昵称由1-60个合法字符组成"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">邮箱：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="email" placeholder="邮箱必须有效且未注册过"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close"
                            id="cancleRegist">关&nbsp;&nbsp;闭
                    </button>
                    <button type="button" class="btn btn-info" id="doRegist">注&nbsp;&nbsp;册</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- login modal -->
<div class="modal fade" id="login_modal" tabindex="-1" role="dialog" aria-labelledby="myLoginLabel">
    <div class="modal-dialog modal-md" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myLoginLabel">登录</h4>
            </div>
            <form id="userLoginForm" action="#" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录名：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="loginName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" name="password"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="button" class="btn btn-info" onclick="userLogin()">登&nbsp;&nbsp;录</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

</html>
