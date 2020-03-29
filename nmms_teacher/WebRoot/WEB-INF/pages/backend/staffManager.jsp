<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    $(function(){
    	var pages;
    	if("${pageStaffs.pages}" == 0){
    		pages = 1;
    	}else{
    		pages = "${pageStaffs.pages}";
    	}
    	
    	var options = {
    		bootstrapMajorVersion:3, //表示当前bootstrap版本号
    		currentPage:"${pageStaffs.pageNum}",//当前页
    		totalPages:pages,//总页数
    		size:"normal",
    		aligment:"center",
    		onPageClicked:function(event,originalEvent,type,page){
    			$("#pageNo").attr("value",page);
    			$("#searchForm").submit();
    		}
    	};
    	
    	$("#staffPage").bootstrapPaginator(options);
    	
    	
    	$("#addStaff").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/staff/addStaff.do",
    			data:{
    				"staffName":$("#staffName").val(),
    				"loginName":$("#managerNo").val(),
    				"password":$("#password").val(),
    				"phone":$("#mobliePhone").val(),
    				"email":$("#myEmail").val(),
    				"role":$("#role").val(),
    				"deptId":$("#deptId").val()
    			},
    			dataType:"json",
    			success:function(result){
    				console.log(result.responseCode);
    				if(result.responseCode == 0){
    					location.href="${pageContext.request.contextPath}/staff/findByParams.do";
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
    <!-- 管理员管理 -->
    <div class="panel panel-default" id="adminSet">
        <div class="panel-heading">
            <h3 class="panel-title">管理员管理&nbsp;&nbsp;&nbsp;<span id="errorMsg"></span></h3>
        </div>
        <div class="panel-body">
            <div class="showmargersearch">
                <form id="searchForm" class="form-inline" action="${pageContext.request.contextPath}/staff/findByParams.do" method="post">
				  <div class="form-group">
				    <label for="exampleInputName2">姓名:</label>
				    <input type="hidden" id="pageNo" name="pageNo">
				    <input type="text" class="form-control" id="userName" placeholder="请输入姓名" name="staffName" value="${staffParam.staffName}">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputName2">帐号:</label>
				    <input type="text" class="form-control" id="loginName" placeholder="请输入帐号" name="loginName" value="${staffParam.loginName}">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputName2">电话:</label>
				    <input type="text" class="form-control" id="phone" placeholder="请输入电话" name="phone" value="${staffParam.phone}">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputEmail3">邮箱:</label>
				    <input type="email" class="form-control" id="email" placeholder="请输入邮箱" name="email" value="${staffParam.email}">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputName2">部门</label>
	                    <select class="form-control" name="deptId">
	                        <option value="-1">全部</option>
	                        <c:forEach items="${depts}" var="dept">
	                        <option value="${dept.deptId}" ${staffParam.deptId eq dept.deptId ? "selected" : ""}>${dept.deptName}</option>
	                        </c:forEach>
	                    </select>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputName2">角色</label>
	                    <select class="form-control" name="role">
	                        <option value="-1">全部</option>
	                        <option value="1001" ${staffParam.role eq 1001 ? "selected" : ""}>系统管理员</option>
	                        <option value="1002" ${staffParam.role eq 1002 ? "selected" : ""}>普通管理员</option>
	                    </select>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputName2">状态</label>
	                    <select class="form-control" name="isValid">
	                        <option value="-1">全部</option>
	                        <option value="1" ${staffParam.isValid eq 1 ? "selected" : ""}>---有效---</option>
	                        <option value="0" ${staffParam.isValid eq 0 ? "selected" : ""}>---无效---</option>
	                    </select>
				  </div>
				  <input type="submit" value="查询" class="btn btn-primary" id="doSearch">
				</form>
            </div>
            <br>
            <br>
            <input type="button" value="添加管理员" class="btn btn-primary" id="doAddManger">
            <!-- 添加管理员 -->
            <div class="modal fade" tabindex="-1" id="myMangerUser">
                <!-- 窗口声明 -->
                <div class="modal-dialog modal-lg">
                    <!-- 内容声明 -->
                    <div class="modal-content">
                        <!-- 头部、主体、脚注 -->
                        <div class="modal-header">
                            <button class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">添加管理员</h4>
                        </div>
                        <div class="modal-body text-center">
                            <div class="row text-right">
                                <label for="marger-username" class="col-sm-4 control-label">员工姓名：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="staffName">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="marger-loginName" class="col-sm-4 control-label">登录帐号：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="managerNo">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="marger-password" class="col-sm-4 control-label">登录密码：</label>
                                <div class="col-sm-4">
                                    <input type="password" class="form-control" id="password">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="marger-phone" class="col-sm-4 control-label">联系电话：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="mobliePhone">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="marger-adrees" class="col-sm-4 control-label">电子邮箱：</label>
                                <div class="col-sm-4">
                                    <input type="email" class="form-control" id="myEmail">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="role" class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                                <div class=" col-sm-4">
                                    <select class="form-control" id="role">
                                        <option value="-1">请选择</option>
                                        <option value="1001">系统管理员</option>
                                        <option value="1002">普通管理员</option>
                                    </select>
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="role" class="col-sm-4 control-label">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：</label>
                                <div class=" col-sm-4">
                                    <select class="form-control" id="deptId">
                                        <option value="-1">请选择</option>
                                        <c:forEach items="${depts}" var="dept">
                                        <option value="${dept.deptId}">${dept.deptName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <br/>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary add-Manger" id="addStaff">添加</button>
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 管理员修改 -->
            <div class="modal fade" tabindex="-1" id="myModal-Manger">
                <!-- 窗口声明 -->
                <div class="modal-dialog modal-lg">
                    <!-- 内容声明 -->
                    <div class="modal-content">
                        <!-- 头部、主体、脚注 -->
                        <div class="modal-header">
                            <button class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">管理员修改</h4>
                        </div>
                        <div class="modal-body text-center">
                            <div class="row text-right">
                                <label for="MargerUsername" class="col-sm-4 control-label">id：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="MargerStaffId" readonly="readonly">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="MargerUsername" class="col-sm-4 control-label">管理员姓名：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="MargerStaffname">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="MargerLoginName" class="col-sm-4 control-label">登录帐号：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="MargerLoginName" readonly="readonly">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="MargerPhone" class="col-sm-4 control-label">联系电话：</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="MargerPhone">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="MargerAdrees" class="col-sm-4 control-label">联系地址：</label>
                                <div class="col-sm-4">
                                    <input type="email" class="form-control" id="MargerAdrees">
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="MargerRole" class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                                <div class=" col-sm-4">
                                    <select class="form-control" id="MargerRole">
                                        <option>请选择</option>
                                        <option>系统管理员</option>
                                        <option>普通管理员</option>
                                    </select>
                                </div>
                            </div>
                            <br>
                            <div class="row text-right">
                                <label for="role" class="col-sm-4 control-label">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：</label>
                                <div class=" col-sm-4">
                                    <select class="form-control">
                                        <option>请选择</option>
                                        <option>研发部</option>
                                        <option>市场部</option>
                                    </select>
                                </div>
                            </div>
                            <br/>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-warning doMargerModal">修改</button>
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="show-list" style="position: relative; top: 10px;">
                <table class="table table-bordered table-hover" style='text-align: center;'>
                    <thead>
                        <tr class="text-danger">
                            <th class="text-center">序号</th>
                            <th class="text-center">姓名</th>
                            <th class="text-center">帐号</th>
                            <th class="text-center">电话</th>
                            <th class="text-center">邮箱</th>
                            <th class="text-center">部门</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">角色</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                    	<c:forEach items="${pageStaffs.list}" var="staff" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td>${staff.staffName}</td>
                            <td>${staff.loginName}</td>
                            <td>${staff.phone}</td>
                            <td>${staff.email}</td>
                            <td>${staff.dept.deptName}</td>
                            <td>
                            	<c:if test="${staff.isValid eq 1}">有效</c:if>
                            	<c:if test="${staff.isValid eq 0}">无效</c:if>
                            </td>
                            <td>
                            	<c:if test="${staff.role eq 1001}">系统管理员</c:if>
                            	<c:if test="${staff.role eq 1002}">普通管理员</c:if>
                            </td>
                            <td class="text-center">
                                <input type="button" class="btn btn-warning btn-sm doMangerModify" value="修改">
                                <input type="button" class="btn btn-danger btn-sm doMangerDisable" value="禁用">
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <ul id="staffPage"></ul>
            </div>
        </div>
    </div>
</body>

</html>