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
            			<button type="button" class="btn btn-success" onclick="update()">update</button>
						<button type="button" class="btn btn-success" onclick="deleteUser()">delete</button>
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
                    	 Add User Info
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
	                            <label class="col-lg-2  control-label">Email：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addEmail" class="form-control"  id="addEmail" >
	                            </div>
	                            <label class="col-lg-2  control-label">Address：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addAddress" class="form-control"  id="addAddress" >
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
	                                     <option value="0">leader</option>
	                                     <option value="1">member</option>
	                                 </select>
	                            </div>
                             	<label class="col-lg-2  control-label">Accout Status：</label>
	                            <div class="col-lg-4 ">
	                            	<select  class="form-control" id="addStatus" name="addStatus">
	                                     <option value="1">Enable</option>
	                                     <option value="0">Disable</option>
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

<!-- 更新用户 -->
<div class="modal fade bs-example-modal-lg" id="userUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCancel">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    	 Update User Info
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
                        		<input type="hidden" id="updateUserId" />
	                            <label class="col-lg-2  control-label">TrueName：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateTrueName" class="form-control"  id="updateTrueName" >
	                            </div>
	                            <label class="col-lg-2  control-label">Telephone：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateTelephone" class="form-control"  id="updateTelephone" >
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">Email：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateEmail" class="form-control"  id="updateEmail" >
	                            </div>
	                            <label class="col-lg-2  control-label">Address：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateAddress" class="form-control"  id="updateAddress" >
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">Age：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateAge" class="form-control"  id="updateAge" >
	                            </div>
                             	<label class="col-lg-2  control-label">Sex：</label>
	                            <div class="col-lg-4 ">
	                            	 <select  class="form-control" id="updateSex" name="updateSex">
	                                     <option value="1">Male</option>
	                                     <option value="2">Female</option>
	                                 </select>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">LoginName：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateLoginName" class="form-control"  id="updateLoginName" >
	                            </div>
                             	<label class="col-lg-2  control-label">PassWord：</label>
	                            <div class="col-lg-4 ">
	                            	 <input type="text" name="updatePassWord" class="form-control"  id="updatePassWord" >
	                            </div>
                            </div>
                             <div class="form-group">
	                            <label class="col-lg-2  control-label">UserType：</label>
	                            <div class="col-lg-4 ">
	                            	<select  class="form-control" id="updateUserType" name="updateUserType">
	                                     <option value="0">leader</option>
	                                     <option value="1">member</option>
	                                 </select>
	                            </div>
                             	<label class="col-lg-2  control-label">Accout Status：</label>
	                            <div class="col-lg-4 ">
	                            	<select  class="form-control" id="updateStatus" name="updateStatus">
	                                     <option value="1">Enable</option>
	                                     <option value="0">Disable</option>
	                                 </select>
	                            </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">close</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveUpdateUser()">update</button>
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
					},
					onClickRow:function(item, $element){
						$("#userId").val(item[0]);
					}
				});
			},
			error:function(err){
			
			}
		});
	}
	
	//新增用户信息
	function saveUser(){
		//判断真实姓名
		var trueName = $("#addTrueName").val();
		if(trueName==null || trueName == ''){
			toastr.info('trueName is empty!');
			return ;
		}
		//判断手机号码
		var telephone = $("#addTelephone").val();
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test(telephone)) 
		{ 
			toastr.info('telephone number format error !');
		    return ; 
		}
		//判断年龄 
		var age = $("#addAge").val();
		if(age ==null || age == '' || age < 0 || age > 120 || isNaN(age)==true){
			toastr.info('the ages between 0 to 120 and num ! ');
			return ;
		}
		//判断登录用户名
		var loginName = $("#addLoginName").val();
		if(loginName==null || loginName == ''){
			toastr.info('loginName is empty!');
			return ;
		}
		//判断登录密码
		var passWord = $("#addPassWord").val();
		if(passWord==null || passWord == ''){
			toastr.info('passWord is empty!');
			return ;
		}
		//判断邮箱
		var email = $("#addEmail").val();
		//判断地址
		var address = $("#addAddress").val();
		if(address==null || address == ''){
			toastr.info('address is empty!');
			return ;
		}
		$.ajax({
			url:'<%=basePath%>bsUser/saveUser',
			type:'post',
			data:{
				trueName:trueName,
				telephone:telephone,
				age:age,
				sex:$("#addSex").val(),
				loginName:loginName,
				passWord:passWord,
				userType:$("#addUserType").val(),
				status:$("#addStatus").val(),
				email:email,
				address:address
			},
			success:function(msg){
				$("#userModal").modal('hide');
				toastr.info('save user info success！');
				search();
			},
			error:function(err){
				console.log(err);
			}
		});
	}
	
	//根据id查询用户信息 填充到用户修改框
	function update(){
		var userId = $("#userId").val();
		if(userId == null || userId == ''){
			toastr.info('please select one record to update！');
			return ;
		}
		$.ajax({
			url:'<%=basePath%>/bsUser/getUserById',
			type:'post',
			data:{
				id:userId
			},
			success:function(msg){
				var jsonObj = JSON.parse(msg); 
				console.log(jsonObj);
				$("#updateTrueName").val(jsonObj.trueName);
				$("#updateTelephone").val(jsonObj.telephone);
				$("#updateAge").val(jsonObj.age);
				$("#updateSex").val(jsonObj.sex);
				$("#updateLoginName").val(jsonObj.loginName);
				$("#updatePassWord").val(jsonObj.passWord);
				$("#updateUserType").val(jsonObj.userType);
				$("#updateStatus").val(jsonObj.status);
				$("#updateEmail").val(jsonObj.email);
				$("#updateAddress").val(jsonObj.address);
				$("#updateUserId").val(jsonObj.id);
				$("#userUpdateModal").modal('show');
			},
			erorr:function(err){
				console.log(err);
			}
		});
	}
	
	//更新用户信息
	function saveUpdateUser(){
		//判断真实姓名
		var trueName = $("#updateTrueName").val();
		if(trueName==null || trueName == ''){
			toastr.info('trueName is empty!');
			return ;
		}
		//判断手机号码
		var telephone = $("#updateTelephone").val();
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test(telephone)) 
		{ 
			toastr.info('telephone number format error !');
		    return ; 
		}
		//判断年龄 
		var age = $("#updateAge").val();
		if(age ==null || age == '' || age < 0 || age > 120 || isNaN(age)==true){
			toastr.info('the ages between 0 to 120 and num ! ');
			return ;
		}
		//判断登录用户名
		var loginName = $("#updateLoginName").val();
		if(loginName==null || loginName == ''){
			toastr.info('loginName is empty!');
			return ;
		}
		//判断登录密码
		var passWord = $("#updatePassWord").val();
		if(passWord==null || passWord == ''){
			toastr.info('passWord is empty!');
			return ;
		}
		//判断邮箱
		var email = $("#updateEmail").val();
		//判断地址
		if(address==null || address == ''){
			toastr.info('address is empty!');
			return ;
		}
		$.ajax({
			url:'<%=basePath%>bsUser/updateUser',
			type:'post',
			data:{
				trueName:trueName,
				telephone:telephone,
				age:age,
				sex:$("#updateSex").val(),
				loginName:loginName,
				passWord:passWord,
				userType:$("#updateUserType").val(),
				status:$("#updateStatus").val(),
				id:$("#updateUserId").val(),
				address:address,
				email:email
			},
			success:function(msg){
				if(msg == '0'){
					$("#userUpdateModal").modal('hide');
					toastr.info("update user info success！");
					search();
				}else{
					toastr.info("update user info failure！");
				}
				
			},
			error:function(err){
			
			}
		});
	}
	
	//删除用户
	function deleteUser(){
		var userId = $("#userId").val();
		if(userId == null || userId == ''){
			toastr.info('please select one record to delete !');
			return ;
		}
		$.confirm({
	    	title: 'Prompt message',
	    	content: 'Are you sure to delete this record !',
	    	buttons: {
		        confirm: function () {
		        	$.ajax({
		        		url:'<%=basePath%>bsUser/deleteUser',
		        		type:'post',
		        		data:{
		        			id:userId
		        		},
		        		success:function(msg){
		        			if(msg == "0"){
		        				toastr.info("delete user success!")
		        				search();
		        			}else{
		        				toastr.info("delete user failure!")
		        			}
		        		},
		        		error:function(err){
		        		
		        		}
		        	});
		        },
		        cancel: function () {
		        	
		        }
	    	}
		});
	}
	
</script>