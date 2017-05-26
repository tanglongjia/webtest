<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=basePath%>assets/js/core.min.js"></script>
<div class="row">
	<div class="col-lg-12">
		<div class="panel">
			<div class="panel-heading bk-bg-primary">
				<h6>
					<i class="fa fa-indent red"></i>权限管理
				</h6>
				<div class="panel-actions">
					<a href="#" class="btn-setting"><i class="fa fa-rotate-right"></i>
					</a> <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i>
					</a> <a href="#" class="btn-close"><i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<label class="col-sm-2 control-label" for="input-small">真实姓名：</label>
					<div class="col-sm-3">
						<input type="text" id="trueName" name="trueName"
							class="form-control input-sm" placeholder="真实姓名" />
					</div>
					<div class="col-sm-3 col-sm-offset-4">
						<button type="button" class="btn btn-success"
							onclick="userTable()">查询</button>
						<button type="button" class="btn btn-success" onclick="saveSet()">保存配置</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel">
			<div class="panel-heading bk-bg-primary">
				<h6>
					<i class="fa fa-indent red"></i>用户列表
				</h6>
				<div class="panel-actions">
					<a href="#" class="btn-setting"><i class="fa fa-rotate-right"></i>
					</a> <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i>
					</a> <a href="#" class="btn-close"><i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div class="panel-body" id="userDiv">
				<table class="table table-condensed" id="userTable">
				</table>
			</div>
		</div>
	</div>
	<div class="col-lg-6">
		<div class="panel bk-bg-white">
			<div class="panel-heading bk-bg-primary">
				<h6>
					<i class="fa fa-indent red"></i>角色列表
				</h6>
				<div class="panel-actions">
					<a href="#" class="btn-setting"><i class="fa fa-rotate-right"></i>
					</a> <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i>
					</a> <a href="#" class="btn-close"><i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div class="panel-body" id="roleDiv">
				<table class="table table-condensed" id="roleTable">
					<thead>
						<tr>
							<th><input name="checkAll" type="checkbox"
								onclick="checkAll(this)" />
							</th>
							<th>角色名称</th>
							<th>角色描述</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${roleList }" var="role">
							<tr onclick="selectRoleTr(${role.id})" id="roleTr${role.id }">
								<td><input name="checkbox" type="checkbox"
									value="${role.id }" onclick="checkOne(this)" />
								</td>
								<td>${role.rolename }</td>
								<td>${role.roledesc }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="userId"/>
<script>
	$(function(){
		//1、加载用户 分页加载
		userTable();
	});
	
	function saveSet(){
		var userId = $("#userId").val();
		if(userId == null|| userId == ''){
			toastr.info('请选择用户!');
			return;
		}
		var chk_value =[]; 
		$('input[name="checkbox"]:checked').each(function(){ 
			chk_value.push($(this).val()); 
		}); 
        $.ajax({
        	url:'<%=basePath%>bsAuthority/saveUserRole',
        	traditional: true,
			data:{
				userId:userId,
				roleId:chk_value,
			},
			type:'post',
			success:function(data){
				if(data == '0'){
					toastr.info("角色菜单设置成功！");
				}else{
					toastr.error("角色菜单设置失败！");
				}
			},
			error:function(err){
			}
        });
	}
	
	
	function userTable(){
		$("#userTable").bootstrapTable('destroy').bootstrapTable({
			url:'<%=basePath%>bsAuthority/getUserData',
			method : 'get',
			dataType : 'json',
			pagination : true,
			pageList : [ 10 ], //记录数可选列表  
			search : false, //显示搜索框
			sidePagination : "server", //表示服务端请求 
			pageSize : 10,
			columns : [ {
				title : '真实姓名',
				field : 'trueName',
				align : 'center',
				valign : 'middle'
			}, {
				title : '登录用户名',
				field : 'loginName',
				align : 'center',
				valign : 'middle'
			} ],
			queryParams : function(params) {
				var trueName = $("#trueName").val();
				return {
					page : params.offset + 1,
					pageSize : params.limit,
					trueName : trueName
				};
			},
			onClickRow : function(item, $element) {
				$("#userId").val(item.id);
				//获取选中的角色
				selectRole(item.id);
			}
		});
	}
	
	function selectRole(userId){
		$.ajax({
			url:'<%=basePath%>bsAuthority/selectRole',
			data:{userId:userId},
			type:'post',
			success:function(data){
				var json = JSON.parse(data);
				console.log(json);
				if(json.length > 0){
					$('input[name="checkbox"]').each(function(){ 
						var chk_value = $(this).val();
							for(var i=0;i<json.length;i++ ){
								if(chk_value == json[i]){
									$(this).prop("checked",true);
									break;
								}else{
									$(this).prop("checked",false);
								}
							}
					}); 
				}else{
					$(":checkbox").prop("checked", false);
				}
			},
			error:function(err){
			}
		});
	}
	
	function checkAll(c) {
		if (c.checked) {  
        	$(":checkbox").prop("checked", true);  
	    } else {  
	        $(":checkbox").prop("checked", false);  
	    }  
	}
</script>
