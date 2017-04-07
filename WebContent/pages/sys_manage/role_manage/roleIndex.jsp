<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel">
			<div class="panel-heading bk-bg-primary">
				<h6>
					<i class="fa fa-indent red"></i>角色管理
				</h6>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<label class="col-sm-2 control-label" for="input-small">角色名称：</label>
					<div class="col-sm-3">
						<input type="text" id="rolename" name="rolename"
							class="form-control input-sm" placeholder="角色名称" />
					</div>
					<div class="col-sm-3 col-sm-offset-4">
						<button type="button" class="btn btn-success" onclick="search()">查询</button>
						<button id="btn_add" type="button" class="btn btn-success"  data-toggle="modal" data-target="#roleModal">
                			<span aria-hidden="true"></span>新增
            			</button>
            			<button type="button" class="btn btn-success" onclick="update()">修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="row" id="dataDiv">
</div>

<!-- 新增角色 -->
<div class="modal fade bs-example-modal-lg" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCancel">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    	 角色信息
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
	                            <label class="col-lg-2  control-label">角色名称：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addRoleName" class="form-control"  id="addRoleName" >
	                            </div>
	                            <label class="col-lg-2  control-label">启用状态：</label>
	                            <div class="col-lg-4 ">
	                            	 <select  class="form-control" id="addStatus" name="addStatus">
	                                     <option value="1">启用</option>
	                                     <option value="0">停用</option>
	                                 </select>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2 col-md-2 col-sm-12 control-label">角色描述：</label>
								<div class="col-lg-10 col-md-10">
									<textarea class="form-control" rows="3" id="addRoleDesc"></textarea>
								</div>
							</div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">关闭</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveRole()">保存</button>
                    </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
</div>

<!-- 修改角色 -->
<div class="modal fade bs-example-modal-lg" id="updateRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCancel">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    	 角色信息
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
	                            <label class="col-lg-2 col-md-2 col-sm-12 control-label">角色名称：</label>
	                            <div class="col-lg-4 col-md-10">
	                            	<input type="text" name="updateRoleName" class="form-control"  id="updateRoleName" >
	                            	<input type="hidden" name="updateRoleId" class="form-control"  id="updateRoleId" >
	                            </div>
	                             <label class="col-lg-2 col-md-2 col-sm-12 control-label">启用状态：</label>
	                            <div class="col-lg-4 col-md-10">
	                            	 <select  class="form-control" id="updateRoleStatus"  name="updateRoleStatus">
	                                     <option value="1">启用</option>
	                                     <option value="0">停用</option>
	                                 </select>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2 col-md-2 col-sm-12 control-label">角色描述：</label>
								<div class="col-lg-10 col-md-10">
									<textarea class="form-control" rows="3" id="updateRoleDesc"></textarea>
								</div>
							</div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">关闭</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveUpdateRole()">保存</button>
                    </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
</div>

<script type="text/javascript">
	search();
	function search(pageid){
		if(pageid==undefined){
			pageid=1;
		}
		$.ajax({
			url:'<%=basePath%>bsRole/roleData',
			data:{
				rolename:$("#rolename").val(),
				page:pageid
			},
			type:'post',
			success:function(msg){
				$("#dataDiv").html(msg);
				$("#roleTable").bootstrapTable({
					totalRows:$("#pageTotal").val(),
					pagination : true, //启动分页  
					pageSize : 10, //每页显示的记录数  
					pageNumber : pageid, //当前第几页  
					pageList : [10], //记录数可选列表  
					onlyInfoPagination : false,
					sidePagination : "server", //表示服务端请求  
					onPageChange : function(number,size){
						search(number);
					},
					onClickRow:function(item, $element){
						$("#roleId").val(item[0]);
					}
				});
			},
			error:function(err){
			
			}
		});
	}
	
	function saveRole(){
		$.ajax({
			url:'<%=basePath%>bsRole/saveRole',
			type:'post',
			data:{
				rolename:$("#addRoleName").val(),
				roledesc:$("#addRoleDesc").val(),
				status:$("#addStatus").val()
			},
			success:function(msg){
				$("#roleModal").modal('hide');
				toastr.info('保存成功！');
				search();
			},
			error:function(err){
			
			}
		});
	}
	
	function update(){
		var roleId = $("#roleId").val();
		if(roleId == null || roleId == ''){
			toastr.info("请选择要修改的记录");
			return ;
		}
		$.ajax({
			url:'<%=basePath%>bsRole/getRoleByPk',
			type:'post',
			data:{
				id:roleId
			},
			success:function(str){
				var jsonObj = JSON.parse(str); 
				console.log(jsonObj);
				$("#updateRoleId").val(jsonObj.id);
				$("#updateRoleName").val(jsonObj.rolename);
				$("#updateRoleDesc").val(jsonObj.roledesc);
				$("#updateRoleStatus").val(jsonObj.status);
				$("#updateRoleModal").modal('show');
			},
			error:function(err){
			
			}
		});
	}
	
	function saveUpdateRole(){
		$.ajax({
			url:'<%=basePath%>bsRole/updateRole',
			type:'post',
			data:{
				rolename:$("#updateRoleName").val(),
				roledesc:$("#updateRoleDesc").val(),
				status:$("#updateRoleStatus").val(),
				id:$("#updateRoleId").val()
			},
			success:function(msg){
				$("#updateRoleModal").modal('hide');
				toastr.info('保存成功！');
				search();
			},
			error:function(err){
			
			}
		});
	}
</script>