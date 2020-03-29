<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 18-10-31
  Time: 下午1:37
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nmms.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/bootstarp/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js" type="text/javascript" charset="utf-8"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-paginator.js" charset="utf-8"></script>
    <script type="text/javascript">
        $(function(){
            var pages;
            if("${productTypes.pages}" == 0){
                pages = 1;
            }else{
                pages = "${productTypes.pages}";
            }
            var options = { //使用bootstrap分页插件实现前台分页
                bootstrapMajorVersion:3,//表示当前bootstrap版本号
                currentPage:"${productTypes.pageNum}",//当前页
                totalPages:pages,//总页数
                size:"normal",
                aligment:"center",
                pageUrl:function(type,page,current){
                    return "${pageContext.request.contextPath}/type/findAll.do?pageNo="+page;
                }
            };
            $("#productTypePage").bootstrapPaginator(options);


            $("#addType").click(function(){
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/type/addType.do",
                    data:{"name":$("#productTypeName").val()},
                    dataType:"json",
                    success:function(result){
                        if(result.responseCode == '0'){
                            location.href="${pageContext.request.contextPath}/type/findAll.do?pageNo=${productTypes.pageNum}";
                        }else{
                            $("#errorMsg").tooltip({
                                title:"error",
                                placement:"center",
                                template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
                                tigger:"manual"
                            }).tooltip("show");
                        }
                    }
                })
            });

            //点showMain页面的修改按钮后,设置修改模态框默认值
            $("[name='modifyName']").click(function(){
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/type/findById.do",
                    data:{"id":$(this).attr("data-id")},
                    dataType:"json",
                    success:function(result){
                        if(result.responseCode == '0'){
                            $("#proTypeNum").val(result.returnObject.id);
                            $("#proTypeName").val(result.returnObject.name);
                        }else{
                            $("#errorMsg").tooltip({
                                title:"error",
                                placement:"center",
                                template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
                                tigger:"manual"
                            }).tooltip("show");
                        }
                    }
                })
            });

            //修改商品类型的名称
            $("#modifyType").click(function(){
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/type/modifyType.do",
                    data:{"id":$("#proTypeNum").val(),
                          "rename":$("#proTypeName").val()},
                    dataType:"json",
                    success:function(result){
                        if(result.responseCode == '0'){
                            location.href="${pageContext.request.contextPath}/type/findAll.do?pageNo=${productTypes.pageNum}"
                        }else{
                            $("#errorMsg").tooltip({
                                title:"error",
                                placement:"center",
                                template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
                                tigger:"manual"
                            }).tooltip("show");
                        }
                    }
                })
            });

            //修改状态
            $("[name='modifyStatus']").click(function(){
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/type/modifyStatus.do",
                    data:{"id":$(this).attr("data-id")},   //参数id传给controller层对应的方法
                    dataType:"json",
                    success:function(result){
                        if(result.responseCode == '0'){
                            location.href="${pageContext.request.contextPath}/type/findAll.do?pageNo=${productTypes.pageNum}"
                        }else{
                            $("#errorMsg").tooltip({
                                title:"error",
                                placement:"center",
                                template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
                                tigger:"manual"
                            }).tooltip("show");
                        }
                    }
                })
            });
        });
    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">商品类型管理&nbsp;&nbsp;&nbsp;<span id="errorMsg"></span></h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加商品类型" class="btn btn-primary" id="doAddProTpye">
        <div class="modal fade" tabindex="-1" id="ProductType">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <div class="modal-content">
                    <!-- 头部、主体、脚注 -->
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">添加商品类型信息</h4>
                    </div>
                    <div class="modal-body text-center">
                        <div class="row text-right">
                            <label for="productTypeName" class="col-sm-4 control-label">类型名称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="productTypeName">
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary addProductType" id="addType">添加</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="show-list">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">类型名称</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${productTypes.list}" var="productType">
                <tr>
                    <td>${productType.id}</td>
                    <td>${productType.name}</td>
                        <td>
                            <c:if test="${productType.status==1}">启用</c:if>
                            <c:if test="${productType.status eq 0}">禁用</c:if>
                        </td>
                    <td class="text-center">
                        <input type="button" class="btn btn-warning btn-sm doProTypeModify" data-id="${productType.id}" value="修改" name="modifyName">
                        <c:if test="${productType.status eq 1}">
                            <input type="button" class="btn btn-danger btn-sm doProTypeDisable" data-id="${productType.id}" name="modifyStatus" value="禁用">
                        </c:if>
                        <c:if test="${productType.status==0}">
                            <input type="button" class="btn btn-danger btn-sm doProTypeDisable" data-id="${productType.id}" name="modifyStatus" value="启用">
                        </c:if>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul id="productTypePage"></ul>
        </div>
        <div class="modal fade" tabindex="-1" id="myProductType">
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
                            <label for="proTypeNum" class="col-sm-4 control-label">编号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="proTypeNum" readonly="readonly">
                            </div>
                        </div>
                        <br>
                        <div class="row text-right">
                            <label for="proTypeName" class="col-sm-4 control-label">类型名称</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="proTypeName">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-warning updateProType" id="modifyType">修改</button>
                        <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>