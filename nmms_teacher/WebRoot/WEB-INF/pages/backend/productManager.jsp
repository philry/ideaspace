<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstarp/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/bootstarp/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js" type="text/javascript" charset="utf-8"></script>
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
    <script type="text/javascript">
    $(function(){
    	var pages;
    	if("${pageProducts.pages}" == 0){
    		pages = 1;
    	}else{
    		pages = "${pageProducts.pages}";
    	}
    	
    	var options = {
    		bootstrapMajorVersion:3, //表示当前bootstrap版本号
    		currentPage:"${pageProducts.pageNum}",//当前页
    		totalPages:pages,//总页数
    		size:"normal",
    		aligment:"center",
    		pageUrl:function(type,page,current){
    			return "${pageContext.request.contextPath}/product/findAll.do?pageNo="+page;
    		}
    	};
    	
    	$("#productPage").bootstrapPaginator(options);
    	
    	$("input[name='toModify']").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/product/findById.do",
    			data:{"id":$(this).attr("data-id")},
    			dataType:"json",
    			success:function(result){
    				if(result.responseCode == 0){
    					$("#modifyId").val(result.returnObject.productId);
    					$("#modifyName").val(result.returnObject.name);
    					$("#modifyPrice").val(result.returnObject.price);
    					console.log(result.returnObject.productType.id);
    					console.log($("#modifyTypeId").find("option[value='"+result.returnObject.productType.id+"']"));
    					$("#modifyTypeId").find("option[value='"+result.returnObject.productType.id+"']").attr("selected",true);
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
    <div class="panel panel-default" id="userPic">
        <div class="panel-heading">
            <h3 class="panel-title">商品管理&nbsp;&nbsp;&nbsp;<span id="errorMsg"></span></h3>
        </div>
        <div class="panel-body">
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
                        <form action="${pageContext.request.contextPath}/product/addProduct.do" method="post" enctype="multipart/form-data">
                        <div class="modal-body text-center">
                            <div class="row text-right">
                                <label for="product-name" class="col-sm-4 control-label">商品名称：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="product-name" name="name">
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
                                        <input type="file" name="image" id="">
                                    </a>
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="productTypeId">
                                        <option value="-1">请选择</option>
                                        <c:forEach items="${types}" var="type">
                                       		<option value="${type.id}">${type.name}</option>
                                       </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <br>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-primary addProduct" value="添加"></input>
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                        </form>  
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
                            <th class="text-center">编号</th>
                            <th class="text-center">商品</th>
                            <th class="text-center">价格</th>
                            <th class="text-center">产品类型</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                    	<c:forEach items="${pageProducts.list}" var="product" varStatus="i">
	                        <tr>
	                            <td>${i.index+1}</td>
	                            <td>${product.productNo}</td>
	                            <td>${product.name}</td>
	                            <td>${product.price}</td>
	                            <td>${product.productType.name}</td>
	                            <td>
	                            	<c:if test="${product.productType.status == 0}">无效商品</c:if>
	                            	<c:if test="${product.productType.status == 1}">有效商品</c:if>
	                            </td>
	                            <td class="text-center">
	                                <input type="button" class="btn btn-warning btn-sm doProModify" value="修改" name="toModify" data-id="${product.productId}">
	                                <input type="button" class="btn btn-danger btn-sm doProDisable" value="删除" productId="${product.productId}">
	                            </td>
	                        </tr>
                    	</c:forEach>
                    </tbody>
                </table>
                <ul id="productPage"></ul>
            </div>
            <div class="modal fade" tabindex="-1" id="myProduct">
                <!-- 窗口声明 -->
                <div class="modal-dialog modal-lg">
                    <!-- 内容声明 -->
                    <div class="modal-content">
                    	<form action="${pageContext.request.contextPath}/product/modifyProduct.do" method="post" enctype="multipart/form-data">
                    	<input type="hidden" name="pageNo" value="${pageProducts.pages}">
                        <!-- 头部、主体、脚注 -->
                        <div class="modal-header">
                            <button class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">商品修改</h4>
                        </div>
                        <div class="modal-body text-center">
                            <div class="row text-right">
                                <label for="product-name" class="col-sm-4 control-label">商品编号：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="modifyId" name="modifyId" readonly="readonly">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="product-name" class="col-sm-4 control-label">商品名称：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="modifyName" name="modifyName">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="product-price" class="col-sm-4 control-label">商品价格：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="modifyPrice" name="modifyPrice">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="product-image" class="col-sm-4 control-label">商品图片：</label>
                                <div class="col-sm-4">
                                    <a href="javascript:;" class="file">选择文件
                                        <input type="file" name="modifyImage" id="modifyImage">
                                    </a>
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="modifyTypeId" name="modifyTypeId">
                                        <option value="-1">请选择</option>
                                        <c:forEach items="${types}" var="type">
                                       		<option value="${type.id}">${type.name}</option>
                                       </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <br>
                        </div>
                        <div class="modal-footer">
                            <input class="btn btn-primary updatePro" type="submit" value="修改">
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