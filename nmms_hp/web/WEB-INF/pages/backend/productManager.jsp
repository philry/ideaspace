<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-10-31
  Time: 上午10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstarp/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/bootstarp/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js" type="text/javascript" charset="utf-8"></script>
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

        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }

        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }
    </style>
</head>

<body>
<div class="panel panel-default" id="userPic">
    <div class="panel-heading">
        <h3 class="panel-title">商品管理</h3>
    </div>
    <form actin="${pageContext.request.contextPath}/type/addProduct.do" method="post" enctype="multipart/form-data">
        <input type="button" value="添加商品" class="btn btn-primary" id="doAddPro">
        <div class="modal fade" tabindex="-1" id="Product">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">添加商品</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="product-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="product-name" name="productName">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="product-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="product-price" name="price">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="product-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-4">
                                <a href="javascript:;" class="file">选择文件
                                    <input type="file" name="file" id="product-image">
                                </a>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="product-type1" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="product-type1" name="typeId">
                                    <option value="-1">请选择</option>
                                    <c:forEach items="${types}" var="type" >
                                    <option value="${type.id}">${type.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary addProduct" type="submit">添加</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
        </form>
        <br>
        <br>
        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">商品</th>
                    <th class="text-center">价格</th>
                    <th class="text-center">产品类型</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="products" var="product" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>有效商品</td>
                    <td class="text-center">
                        <input type="button" class="btn btn-warning btn-sm doProModify" value="修改">
                        <input type="button" class="btn btn-danger btn-sm doProDisable" value="删除">
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="modal fade" tabindex="-1" id="myProduct">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">商品修改</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="pro-num" class="col-sm-4 control-label">序号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="pro-num">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="pro-name" class="col-sm-4 control-label">商品名称</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="pro-name">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="pro-price" class="col-sm-4 control-label">商品价格：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="pro-price">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="product-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-4">
                                <a href="javascript:;" class="file">选择文件
                                    <input type="file" name="" id="">
                                </a>
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="product-type2" class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="product-type2">
                                    <option>请选择</option>
                                    <option>电子产品</option>
                                    <option>化妆品</option>
                                </select>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary updatePro">修改</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>
