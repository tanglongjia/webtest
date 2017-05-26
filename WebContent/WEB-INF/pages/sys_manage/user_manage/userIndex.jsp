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
					<i class="fa fa-indent red"></i>用户管理
				</h6>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<label class="col-sm-1 control-label" for="input-small">用户名：</label>
					<div class="col-sm-3">
						<input type="text" id="username" name="username"
							class="form-control input-sm" placeholder="用户名" />
					</div>
					<label class="col-sm-1 control-label" for="input-small">手机号：</label>
					<div class="col-sm-3">
						<input type="text" id="telephone" name="telephone"
							class="form-control input-sm" placeholder="手机号" />
					</div>
					<div class="col-sm-4" style="text-align: right">
						<button type="button" class="btn btn-success" onclick="search()">查询</button>
						<button id="btn_add" type="button" class="btn btn-success"  data-toggle="modal" data-target="#userModal">
                			<span aria-hidden="true"></span>新增
            			</button>
            			<button type="button" class="btn btn-success" onclick="update()">更新</button>
						<button type="button" class="btn btn-success" onclick="deleteUser()">删除</button>
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
                    	 增加用户
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
	                            <label class="col-lg-2  control-label">真实姓名：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addTrueName" class="form-control"  id="addTrueName" >
	                            </div>
	                            <label class="col-lg-2  control-label">手机号码：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addTelephone" class="form-control"  id="addTelephone" >
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">邮件：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addEmail" class="form-control"  id="addEmail" >
	                            </div>
	                            <label class="col-lg-2  control-label">地址：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addAddress" class="form-control"  id="addAddress" >
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">年龄：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addAge" class="form-control"  id="addAge" >
	                            </div>
                             	<label class="col-lg-2  control-label">性别：</label>
	                            <div class="col-lg-4 ">
	                            	 <select  class="form-control" id="addSex" name="addSex">
	                                     <option value="1">男</option>
	                                     <option value="2">女</option>
	                                 </select>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">登录用户名：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addLoginName" class="form-control"  id="addLoginName" >
	                            </div>
                             	<label class="col-lg-2  control-label">登录密码：</label>
	                            <div class="col-lg-4 ">
	                            	 <input type="text" name="addPassWord" class="form-control"  id="addPassWord" >
	                            </div>
                            </div>
                             <div class="form-group">
	                            <label class="col-lg-2  control-label">用户类型：</label>
	                            <div class="col-lg-4 ">
	                            	<select  class="form-control" id="addUserType" name="addUserType">
	                                     <option value="0">管理员</option>
	                                     <option value="1">成员</option>
	                                 </select>
	                            </div>
                             	<label class="col-lg-2  control-label">帐号状态：</label>
	                            <div class="col-lg-4 ">
	                            	<select  class="form-control" id="addStatus" name="addStatus">
	                                     <option value="1">有效</option>
	                                     <option value="0">无效</option>
	                                 </select>
	                            </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">关闭</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveUser()">保存</button>
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
                    	 更新用户信息
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
                        		<input type="hidden" id="updateUserId" />
	                            <label class="col-lg-2  control-label">真实姓名：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateTrueName" class="form-control"  id="updateTrueName" >
	                            </div>
	                            <label class="col-lg-2  control-label">手机号码：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateTelephone" class="form-control"  id="updateTelephone" >
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">邮箱：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateEmail" class="form-control"  id="updateEmail" >
	                            </div>
	                            <label class="col-lg-2  control-label">地址：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateAddress" class="form-control"  id="updateAddress" >
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">年龄：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateAge" class="form-control"  id="updateAge" >
	                            </div>
                             	<label class="col-lg-2  control-label">性别：</label>
	                            <div class="col-lg-4 ">
	                            	 <select  class="form-control" id="updateSex" name="updateSex">
	                                     <option value="1">男</option>
	                                     <option value="2">女</option>
	                                 </select>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">登录用户名：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateLoginName" class="form-control"  id="updateLoginName" >
	                            </div>
                             	<label class="col-lg-2  control-label">登录密码：</label>
	                            <div class="col-lg-4 ">
	                            	 <input type="text" name="updatePassWord" class="form-control"  id="updatePassWord" >
	                            </div>
                            </div>
                             <div class="form-group">
	                            <label class="col-lg-2  control-label">用户类型：</label>
	                            <div class="col-lg-4 ">
	                            	<select  class="form-control" id="updateUserType" name="updateUserType">
	                                     <option value="0">管理员</option>
	                                     <option value="1">成员</option>
	                                 </select>
	                            </div>
                             	<label class="col-lg-2  control-label">帐号状态：</label>
	                            <div class="col-lg-4 ">
	                            	<select  class="form-control" id="updateStatus" name="updateStatus">
	                                     <option value="1">有效</option>
	                                     <option value="0">无效</option>
	                                 </select>
	                            </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">关闭</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveUpdateUser()">更新</button>
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
			toastr.info('真实姓名不能为空!');
			return ;
		}
		//判断手机号码
		var telephone = $("#addTelephone").val();
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test(telephone)) 
		{ 
			toastr.info('手机号码格式不正确!');
		    return ; 
		} 
		//判断年龄 
		var age = $("#addAge").val();
		if(age ==null || age == '' || age < 0 || age > 120 || isNaN(age)==true){
			toastr.info('年龄须在0到120之间! ');
			return ;
		}
		//判断登录用户名
		var loginName = $("#addLoginName").val();
		if(loginName==null || loginName == ''){
			toastr.info('登录用户名不能为空!');
			return ;
		}
		//判断登录密码
		var passWord = $("#addPassWord").val();
		if(passWord==null || passWord == ''){
			toastr.info('登录密码不能为空!');
			return ;
		}
		//判断邮箱
		var email = $("#addEmail").val();
		//判断地址
		var address = $("#addAddress").val();
		if(address==null || address == ''){
			toastr.info('地址不能为空!');
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
				toastr.info('保存用户成功！');
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
			toastr.info('请选择一条记录进行更新！');
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
			toastr.info('真实姓名不能为空!');
			return ;
		}
		//判断手机号码
		var telephone = $("#updateTelephone").val();
	    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test(telephone)) 
		{ 
			toastr.info('手机号码格式不对 !');
		    return ; 
		}
		//判断年龄 
		var age = $("#updateAge").val();
		if(age ==null || age == '' || age < 0 || age > 120 || isNaN(age)==true){
			toastr.info('年龄必须在0到120之间 ! ');
			return ;
		}
		//判断登录用户名
		var loginName = $("#updateLoginName").val();
		if(loginName==null || loginName == ''){
			toastr.info('登录用户名不能为空!');
			return ;
		}
		//判断登录密码
		var passWord = $("#updatePassWord").val();
		if(passWord==null || passWord == ''){
			toastr.info('密码不能为空!');
			return ;
		}
		//判断邮箱
		var email = $("#updateEmail").val();
		//判断地址
		var address = $("#updateAddress").val();
		if(address==null || address == ''){
			toastr.info('地址不能为空!');
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
					toastr.info("更新用户信息成功！");
					search();
				}else{
					toastr.info("更新用户信息失败！");
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
			toastr.info('请选择一条记录进行删除!');
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
		        				toastr.info("删除用户成功!");
		        				search();
		        			}else{
		        				toastr.info("删除用户失败!");
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