<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstarp/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/nmms.css" />
    <script src="${pageContext.request.contextPath }/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath }/bootstarp/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath }/js/userSetting.js" type="text/javascript" charset="utf-8"></script>
    <!--  bootstrap分页插件必备js:bootstrap-paginator.js -->
    <script src="${pageContext.request.contextPath }/js/bootstrap-paginator.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    $(function(){
    	var pages;
    	if("${pageTypes.pages}" == 0){
    		pages = 1;
    	}else{
    		pages = "${pageTypes.pages}";
    	}
    	
    	var options = {
    		bootstrapMajorVersion:3, //表示当前bootstrap版本号
    		currentPage:"${pageTypes.pageNum}",//当前页
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
    				if(result.responseCode == 0){
    					location.href="${pageContext.request.contextPath}/type/findAll.do?pageNo=${pageTypes.pageNum}";
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						//template表示显示的错误信息
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    			}
    		
    		});
    	});
    	
    	$("input[name='toModifyType']").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/type/findById.do",
    			data:{"id":$(this).attr("data-id")},
    			dataType:"json",
    			success:function(result){
    				if(result.responseCode == 0){
    					$("#proTypeNum").val(result.returnObject.id);
    					$("#proTypeName").val(result.returnObject.name);
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						//template表示显示的错误信息
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    			}
    		});
    	});
    	
    	$("input[name='modifyStatus']").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/type/modifyStatus.do",
    			data:{"id":$(this).attr("data-id"),"status":$(this).attr("data-status")},
    			dataType:"json",
    			success:function(result){
    				if(result.responseCode == 0){
    					location.href="${pageContext.request.contextPath}/type/findAll.do?pageNo=${pageTypes.pageNum}";
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						//template表示显示的错误信息
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    			}
    		});
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
                            <th class="text-center">序号</th>
                            <th class="text-center">类型名称</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                    	<c:forEach items="${pageTypes.list }" var="type" varStatus="i">
                        <tr>
                            <td>${i.index+1 }</td>
                            <td>${type.name }</td>
                            <td>
                            	<c:if test="${type.status eq 1 }">启用</c:if>
                            	<c:if test="${type.status eq 0 }">禁用</c:if>
                            </td>
                            <td class="text-center">
                                <input type="button" class="btn btn-warning btn-sm doProTypeModify" name="toModifyType" data-id="${type.id }" value="修改">
                                <c:if test="${type.status eq 1 }">
                                	<input type="button" class="btn btn-danger btn-sm doProTypeDisable" value="禁用" data-id="${type.id}" data-status="${type.status}" name="modifyStatus">
                                </c:if>
                            	<c:if test="${type.status eq 0 }">
                                	<input type="button" class="btn btn-success btn-sm doProTypeDisable" value="启用" data-id="${type.id}" data-status="${type.status}" name="modifyStatus">
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
                    	<form action="${pageContext.request.contextPath}/type/modifyName.do" method="post">
                    	<input type="hidden" name="pageNo" value="${pageTypes.pages}">
                        <!-- 头部、主体、脚注 -->
                        <div class="modal-header">
                            <button class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">商品修改</h4>
                        </div>
                        <div class="modal-body text-center">
                            <div class="row text-right">
                                <label for="proTypeNum" class="col-sm-4 control-label">编号：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="proTypeNum" readonly="readonly" name="id">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="proTypeName" class="col-sm-4 control-label">类型名称</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="proTypeName" name="name">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input class="btn btn-warning updateProType" type="submit" value="修改">
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>