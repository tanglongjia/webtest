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
					<i class="fa fa-indent red"></i>User Manage
				</h6>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<label class="col-sm-1 control-label" for="input-small">UserName：</label>
					<div class="col-sm-3">
						<input type="text" id="username" name="username"
							class="form-control input-sm" placeholder="UserName" />
					</div>
					<label class="col-sm-1 control-label" for="input-small">Telephone：</label>
					<div class="col-sm-3">
						<input type="text" id="telephone" name="telephone"
							class="form-control input-sm" placeholder="Telephone" />
					</div>
					<div class="col-sm-4" style="text-align: right">
						<button type="button" class="btn btn-success" onclick="search()">search</button>
						<button id="btn_add" type="button" class="btn btn-success"  data-toggle="modal" data-target="#userModal">
                			<span aria-hidden="true"></span>add
            			</button>
            			<button id="btn_update" type="button" class="btn btn-success"  data-toggle="modal" data-target="#userUpdateModal">
                			<span aria-hidden="true"></span>update
            			</button>
						<button type="button" class="btn btn-success">delete</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 用户列表 -->
<div class="row" id="dataDiv">
</div>

<!-- 新增用户 -->
<div class="modal fade bs-example-modal-lg" id="userModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCancel">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    	 User Info
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
	                            <label class="col-lg-2  control-label">TrueName：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addTrueName" class="form-control"  id="addTrueName" >
	                            </div>
	                            <label class="col-lg-2  control-label">Telephone：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addTelephone" class="form-control"  id="addTelephone" >
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">Age：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addAge" class="form-control"  id="addAge" >
	                            </div>
                             	<label class="col-lg-2  control-label">Sex：</label>
	                            <div class="col-lg-4 ">
	                            	 <select  class="form-control" id="addSex" name="addSex">
	                                     <option value="1">Male</option>
	                                     <option value="2">Female</option>
	                                 </select>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">LoginName：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addLoginName" class="form-control"  id="addLoginName" >
	                            </div>
                             	<label class="col-lg-2  control-label">PassWord：</label>
	                            <div class="col-lg-4 ">
	                            	 <input type="text" name="addPassWord" class="form-control"  id="addPassWord" >
	                            </div>
                            </div>
                             <div class="form-group">
	                            <label class="col-lg-2  control-label">UserType：</label>
	                            <div class="col-lg-4 ">
	                            	<select  class="form-control" id="addUserType" name="addUserType">
	                                     <option value="0">admin</option>
	                                     <option value="1">user</option>
	                                 </select>
	                            </div>
                             	<label class="col-lg-2  control-label">Accout Status：</label>
	                            <div class="col-lg-4 ">
	                            	<select  class="form-control" id="addStatus" name="addStatus">
	                                     <option value="0">Enable</option>
	                                     <option value="1">Disable</option>
	                                 </select>
	                            </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">close</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveUser()">save</button>
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
			url:'<%=basePath%>bsUser/userData',
			data:{
				trueName:$("#username").val(),
				telephone:$("#telephone").val(),
				page:pageid
			},
			type:'post',
			success:function(msg){
				$("#dataDiv").html(msg);
				$("#userTable").bootstrapTable({
					totalRows:$("#pageTotal").val(),
					pagination : true, //启动分页  
					pageSize : 10, //每页显示的记录数  
					pageNumber : pageid, //当前第几页  
					pageList : [10], //记录数可选列表  
					onlyInfoPagination : false,
					sidePagination : "server", //表示服务端请求  
					onPageChange : function(number,size){
						search(number);
					}
				});
			},
			error:function(err){
			
			}
		});
	}
	
	function saveUser(){
		$.ajax({
			url:'<%=basePath%>bsUser/saveUser',
			type:'post',
			data:{
				trueName:$("#addTrueName").val(),
				telephone:$("#addTelephone").val(),
				age:$("#addAge").val(),
				sex:$("#addSex").val(),
				loginName:$("#addLoginName").val(),
				passWord:$("#addPassWord").val(),
				userType:$("#addUserType").val(),
				status:$("#addStatus").val(),
			},
			success:function(msg){
				$("#userModal").modal('hide');
				toastr.info('save success！');
				search();
			},
			error:function(err){
			
			}
		});
	}
</script>