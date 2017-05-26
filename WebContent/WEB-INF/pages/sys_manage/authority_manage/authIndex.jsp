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
					<label class="col-sm-2 control-label" for="input-small">角色名称：</label>
					<div class="col-sm-3">
						<input type="text" id="roleName" name="roleName"
							class="form-control input-sm" placeholder="角色名称" />
					</div>
					<div class="col-sm-3 col-sm-offset-4">
						<button type="button" class="btn btn-success"
							onclick="roleTable()">查询</button>
						<button type="button" class="btn btn-success" onclick="saveSet()">保存配置</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="row">
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
				</table>
			</div>
		</div>
	</div>
	<div class="col-lg-6">
		<div class="panel">
			<div class="panel-heading bk-bg-primary">
				<h6>
					<i class="fa fa-indent red"></i>菜单列表
				</h6>
				<div class="panel-actions">
					<a href="#" class="btn-setting"><i class="fa fa-rotate-right"></i>
					</a> <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i>
					</a> <a href="#" class="btn-close"><i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div class="panel-body" id="resDiv"></div>
		</div>
	</div>
</div>
<input type="hidden" id="roleId"/>
<script>
	$(function(){
		roleTable();
		getResTree();
	});
	
	function roleTable(){
		$("#roleTable").bootstrapTable('destroy').bootstrapTable({
			url:'<%=basePath%>bsAuthority/getRoleData',
			method : 'get',
			dataType : 'json',
			pagination : true,
			pageList : [ 10 ], //记录数可选列表  
			search : false, //显示搜索框
			sidePagination : "server", //表示服务端请求 
			pageSize : 10,
			columns : [ {
				title : '角色名称',
				field : 'rolename',
				align : 'center',
				valign : 'middle'
			}, {
				title : '角色描述',
				field : 'roledesc',
				align : 'center',
				valign : 'middle'
			} ],
			queryParams : function(params) {
				var roleName = $("#roleName").val();
				return {
					page : params.offset + 1,
					pageSize : params.limit,
					rolename : roleName
				};
			},
			onClickRow : function(item, $element) {
				$("#roleId").val(item.id);
				//获取选中的角色
				selectMenu(item.id);
			}
		});
	}
	
	function saveSet(){
		var roleId =$("#roleId").val();
		if(roleId == null || roleId == ''){
			toastr.info('请勾选角色!');
			return;
		} 
		//获取选中的节点
        var nodes=$("#resDiv").jstree("get_checked");
        $.ajax({
        	url:'<%=basePath%>bsAuthority/savePz',
        	traditional: true,
			data:{
				roleId:roleId,
				menuId:nodes
			},
			type:'post',
			success:function(data){
				if(data == '0'){
					toastr.info("权限配置成功！");
				}else{
					toastr.error("权限配置失败！");
				}
			},
			error:function(err){
			}
        });
	}
	
	function getResTree(){
		$.ajax({
			url:'<%=basePath%>bsAuthority/getResTree',
			data:{},
			type:'post',
			success:function(data){
				createTree(data);
			},
			error:function(err){
			}
		});
	}
	
	function createTree(data){
		$("#resDiv").jstree("destroy"); 
		$("#resDiv").jstree({  
            "core" : {
	            "themes" : {
	                "responsive": false
	            },
	            'data' : JSON.parse(data)
       		 },
	        "types" : {
	            "default" : {
	                "icon" : "fa fa-folder icon-state-warning icon-lg"
	            },
	            "file" : {
	                "icon" : "fa fa-file icon-state-warning icon-lg"
	            }
	        },
	        "checkbox" : {"keep_selected_style" : false},
	        "state" : { "key" : "demo2" }, 
	        "plugins" :  ["checkbox" ],
	 	}); 
  	 }
	
	function selectMenu(roleId){
		$.ajax({
			url:'<%=basePath%>bsAuthority/selectMenu',
			data:{
				roleId:roleId
			},
			type:'post',
			success:function(data){
				console.log(data);
				createTree(data);
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
