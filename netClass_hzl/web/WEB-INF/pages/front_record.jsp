<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-11-5
  Time: 下午5:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>积分金币</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/font_0/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js" type="text/javascript"
            charset="utf-8"></script>
    <script>
        $(function () {
            var pages;
            if ("${goldPointsRecords.pages}" == 0) {
                pages = 1;
            } else {
                pages = "${goldPointsRecords.pages}";
            }

            //使用bootstrap分页插件实现前台分页
            var options = {
                bootstrapMajorVersion: 3,//表示当前bootstrap版本号
                currentPage: "${goldPointsRecords.pageNum}",//当前页
                totalPages: pages,//总页数
                size: "normal",
                aligment: "center",
                pageUrl: function (type, page, current) {
                    return "${pageContext.request.contextPath}/record/findPointRecord.do?pageNo=" + page;
                }
            };
            $("#myPages").bootstrapPaginator(options);




            $(".arrow").click(function () {

                $(this).parent().next().toggle();

            })

            $(".title-ul>li").on('click', function () {
                console.log($(this));
                $(this).addClass('current').siblings().removeClass("current");
            })
        })
            //按钮的超链接设置函数
//      myoptions.pageUrl = function(type, page, current) {
//          return "${pageContext.request.contextPath }/findAll.do?pageNo="
//                  + page;
//      };
            //分页回调函数,点击按钮事件
        //     myoptions.onPageClicked = function (event, originalEvent, type, page) {
        //         //ajax回调接收数据成功时再重新初始化分页按钮
        //         console.log("pageNo=", page);
        //
        //     };
        //
        //     //分页,在bootstrap-mypaginator.js中
        //     myPaginatorFun("myPages", 5, 20);
        // })
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
                    <img src="${pageContext.request.contextPath}/images/com-logo1.png" alt="" width="120"
                         style="margin-right: 20px;">
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
                        <button type="submit" class="btn btn-default "><span class="glyphicon glyphicon-search"></span>
                        </button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                                <img class="img-circle" src="${pageContext.request.contextPath}/images/user.jpeg"
                                     id="userImg">
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu userinfo cc">
                                <li>
                                    <img src="${pageContext.request.contextPath}/images/user.jpeg" class="img-circle"
                                         alt="">
                                    <div class="">
                                        <p>${nickname}</p>
                                        <p>金币<span>${gold}</span>&nbsp;积分 <span>${point}</span></p>
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
        <div class="container padding-20">
            <div class="row ">
                <div class="col-md-3">
                    <p class="big-title">积分记录</p>
                </div>
                <div class="col-md-3 col-md-offset-6 convert">
                    <p>当前积分：<span>${point}</span></p>
                    <p>当前金币：<span>${gold}</span>
                        <button class="btn btn-warning" data-toggle="modal" data-target="#record">兑换金币</button>
                    </p>
                </div>
            </div>
            <table class="table table-hover table-striped  table-responsive padding-20 margin-top-20 ">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>数值</th>
                    <th>类型</th>
                    <th>详情</th>
                    <th>时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${goldPointsRecords.list}" var="record">
                    <tr>
                        <td>${record.id}</td>
                        <c:if test="${record.pointCount!=0}">
                            <td>${record.pointCount}</td>
                            <td>积分</td>
                        </c:if>
                        <c:if test="${record.goldCount!=0}">
                            <td>${record.goldCount}</td>
                            <td>金币</td>
                        </c:if>
                        <td>${record.info}</td>
                        <td>${record.createDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- 分页 -->
        <div style="text-align: center;">
            <ul id="myPages"></ul>
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
<div class="modal fade" id="userSet" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">个人信息</h4>
            </div>
            <form action="#" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">旧密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" name="newPassword"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">确认密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" name="rePassword"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">昵称：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="nickname"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">邮箱：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="email"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭
                    </button>
                    <button type="reset" class="btn btn-info">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-info">修&nbsp;&nbsp;改</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="record" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">兑换金币(10积分=1金币)</h4>
            </div>
            <form action="#" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-2 text-right">
                            <input class="form-control" type="text"/>
                        </div>
                        <label class="col-sm-4 control-label" style="text-align: left;">积分</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭
                    </button>
                    <button type="reset" class="btn btn-info">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-info">确&nbsp;&nbsp;定</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

</html>
