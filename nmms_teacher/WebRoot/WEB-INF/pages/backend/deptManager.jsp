<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
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
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    $(function(){
    	var pages;
    	if("${pageDepts.pages}" == 0){
    		pages = 1;
    	}else{
    		pages = "${pageDepts.pages}";
    	}
    	
    	var options = {
    		bootstrapMajorVersion:3, //表示当前bootstrap版本号
    		currentPage:"${pageDepts.pageNum}",//当前页
    		totalPages:pages,//总页数
    		size:"normal",
    		aligment:"center",
    		pageUrl:function(type,page,current){
    			return "${pageContext.request.contextPath}/dept/findAll.do?pageNo="+page;
    		}
    	};
    	
    	$("#deptPage").bootstrapPaginator(options);
    	
    	$("#addFatherDept").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/dept/addFatherDept.do",
    			data:{"name":$("#fatherName").val(),"remark":$("#fatherRemark").val()},
    			dataType:"json",
    			success:function(result){
    				console.log(result.responseCode);
    				if(result.responseCode == 0){
    					location.href="${pageContext.request.contextPath}/dept/findAll.do?pageNo=${pageDepts.pageNum}";
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						//template表示显示的错误信息
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    			},error:function(result){
    				console.log("error:"+result);
    			}
    		});
    	});
    	
    	
    	//设置添加子部门的默认值
    	$("input[name='doAddSonDept']").click(function(){
    		$("#fatherDeptId").val($(this).attr("data-id"));
    		$("#fatherDeptName").val($(this).attr("data-name"));
    	});
    	
    	//添加子部门
    	$("#addSonDept").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/dept/addSonDept.do",
    			data:{"name":$("#deptName").val(),"remark":$("#remark").val(),"fatherId":$("#fatherDeptId").val()},
    			dataType:"json",
    			success:function(result){
    				console.log(result.responseCode);
    				if(result.responseCode == 0){
    					location.href="${pageContext.request.contextPath}/dept/findAll.do?pageNo=${pageDepts.pageNum}";
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						//template表示显示的错误信息
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    			},error:function(result){
    				console.log("error:"+result);
    			}
    		});
    	});
    	
    	//设置修改默认值
    	$("input[name='toModifyDept']").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/dept/findById.do",
    			data:{"id":$(this).attr("data-id")},
    			dataType:"json",
    			success:function(result){
    				if(result.responseCode == 0){
    					$("#modifyId").val(result.returnObject.deptId);
    					$("#modifyName").val(result.returnObject.deptName);
    					$("#modifyRemark").val(result.returnObject.remark);
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
    	
    	//修改部门信息
    	$("#modifyDept2").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/dept/modifyDept.do",
    			data:{"id":$("#modifyId").val(),"name":$("#modifyName").val(),"remark":$("#modifyRemark").val()},
    			dataType:"json",
    			success:function(result){
    				if(result.responseCode == 0){
    					location.href="${pageContext.request.contextPath}/dept/findAll.do?pageNo=${pageDepts.pageNum}";
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
    	
    	
    	//启用与禁用功能
    	$("input[name='modifyStatus']").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/dept/modifyStatus.do",
    			data:{"id":$(this).attr("data-id")},
    			dataType:"json",
    			success:function(result){
    				if(result.responseCode == 0){
    					location.href="${pageContext.request.contextPath}/dept/findAll.do?pageNo=${pageDepts.pageNum}";
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

    <!-- 部门管理 -->
    <div class="panel panel-default" id="departmentSet">
        <div class="panel-heading">
            <h3 class="panel-title">部门管理&nbsp;&nbsp;&nbsp;<span id="errorMsg"></span></h3>
        </div>
        <div class="panel-body">
            <input type="button" value="添加部门" class="btn btn-primary" id="doAddDept">
            <br>
            <br>
            <div class="modal fade" tabindex="-1" id="Dept">
                <!-- 窗口声明 -->
                <div class="modal-dialog modal-lg">
                    <!-- 内容声明 -->
                    <div class="modal-content">
                        <!-- 头部、主体、脚注 -->
                        <div class="modal-header">
                            <button class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">添加部门</h4>
                        </div>
                        <div class="modal-body text-center">
                            <div class="row text-right">
                                <label for="dept-name" class="col-sm-4 control-label">部门名称：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="fatherName">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="dept-duty" class="col-sm-4 control-label">部门职能：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="fatherRemark">
                                </div>
                            </div>
                            <br>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary addDept" id="addFatherDept">添加</button>
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 添加子部门模态框 -->
             <div class="modal fade" tabindex="-1" id="sonDept">
                <!-- 窗口声明 -->
                <div class="modal-dialog modal-lg">
                    <!-- 内容声明 -->
                    <div class="modal-content">
                        <!-- 头部、主体、脚注 -->
                        <div class="modal-header">
                            <button class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">添加子部门</h4>
                        </div>
                        <div class="modal-body text-center">
                            <div class="row text-right">
                                <label for="dept-name" class="col-sm-4 control-label">父部门名称：</label>
                                <div class="col-sm-4">
                                	<input type="hidden" id="fatherDeptId">
                                    <input type="text" class="form-control" id="fatherDeptName" value="市场部" readonly="readonly">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="dept-name" class="col-sm-4 control-label">部门名称：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="deptName">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="dept-duty" class="col-sm-4 control-label">部门职能：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="remark">
                                </div>
                            </div>
                            <br>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary addSonDept" id="addSonDept">添加</button>
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
            
            
            <!-- 修改部门模态框 -->
             <div class="modal fade" tabindex="-1" id="modifyDept">
                <!-- 窗口声明 -->
                <div class="modal-dialog modal-lg">
                    <!-- 内容声明 -->
                    <div class="modal-content">
                        <!-- 头部、主体、脚注 -->
                        <div class="modal-header">
                            <button class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">修改部门</h4>
                        </div>
                        <div class="modal-body text-center">
                            <div class="row text-right">
                                <label for="dept-name" class="col-sm-4 control-label">id：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="modifyId" value="1" readonly="readonly">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="dept-name" class="col-sm-4 control-label">部门名称：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="modifyName">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="dept-duty" class="col-sm-4 control-label">部门职能：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="modifyRemark">
                                </div>
                            </div>
                            <br>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary modifyDept" id="modifyDept2">修改</button>
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="show-list">
                <table class="table table-bordered table-hover" style='text-align: center;'>
                    <thead>
                        <tr class="text-danger">
                            <th class="text-center">序号</th>
                            <th class="text-center">部门编号</th>
                            <th class="text-center">部门名称</th>
                            <th class="text-center">部门职能</th>
                            <th class="text-center">所属部门</th>
                            <th class="text-center">部门状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                    	<c:forEach items="${pageDepts.list}" var="dept" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td>${dept.deptNo}</td>
                            <td>${dept.deptName}</td>
                            <td>${dept.remark}</td>
                            <td>
                            <c:choose>
                            	<c:when test="${dept.fatherDept == null}">--</c:when>
                            	<c:otherwise>${dept.fatherDept.deptName}</c:otherwise>
                            </c:choose>
                            </td>
                            <td>
                            	<c:if test="${dept.isValid eq 1}">有效</c:if>
                            	<c:if test="${dept.isValid eq 0}">无效</c:if>
                            </td>
                            <td class="text-center">
                                <input type="button" class="btn btn-info btn-sm doAddSonDept" name="doAddSonDept" data-id="${dept.deptId}" data-name="${dept.deptName}" value="添加子部门">
                                <input type="button" class="btn btn-warning btn-sm doModifyDept" name="toModifyDept" data-id="${dept.deptId}" value="修改">
                                <c:if test="${dept.isValid eq 1}">
	                                <input type="button" class="btn btn-danger btn-sm doDisable" name="modifyStatus" data-id="${dept.deptId}" value="禁用">
                                </c:if>
                            	<c:if test="${dept.isValid eq 0}">
	                                <input type="button" class="btn btn-success btn-sm doDisable" name="modifyStatus" data-id="${dept.deptId}" value="启用">
                            	</c:if>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <ul id="deptPage"></ul>
            </div>
        </div>
    </div>
</body>

</html>