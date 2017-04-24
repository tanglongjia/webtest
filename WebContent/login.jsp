<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
	<title>Login One</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="assets/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="assets/css/templatemo_style.css" rel="stylesheet" type="text/css">	
	<script src="assets/vendor/js/jquery.min.js"></script>
	<script src="assets/vendor/js/jquery-2.1.1.min.js"></script>
	<!-- toastr -->
	<script src="assets/plugins/toastr/toastr.js"></script>
	<!-- toastr -->
	<link href="assets/plugins/toastr/toastr.css" rel="stylesheet" />
</head>
<body class="templatemo-bg-gray">
	<div class="container">
		<div class="col-md-12" style="padding-top: 100px;">
			<h1 class="margin-bottom-15">Login</h1>
			<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-180" 
				role="form" action="#" method="post" >				
		        <div class="form-group">
		          <div class="col-xs-12">		            
		            <div class="control-wrapper">
		            	<label for="username" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
		            	<input type="text" class="form-control" id="username" placeholder="Username" onkeydown="keyLogin()">
		            </div>		            	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
		            	<input type="password" class="form-control" id="password" placeholder="Password" onkeydown="keyLogin()">
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		          		<input type="button" value="Log in" class="btn btn-info" onclick="doLogin()">
		          	</div>
		          </div>
		        </div>
		        <hr>
		      </form>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function keyLogin(){
		if (event.keyCode == 13){
		    event.returnValue=false;
		    event.cancel = true;
		    doLogin();
		 }
	}

	function doLogin(){
		var username = $("#username").val();
		var password = $("#password").val();
		if(username == null || username ==''){
			toastr.info('username is empty!');
			return;
		}
		if(password == null || password == ''){
			toastr.info('password is empty!');
			return;
		}
		$.ajax({
			url:'<%=basePath%>bsLogin/doLogin',
			type:'post',
			data:{
				username:username,
				password:password,
			},
			success:function(msg){
				if(msg == 0){
					toastr.info('Username Or Password is Wrong!');
				}
				if(msg == 1){
					toastr.info('User Account blocked!');
				}
				if(msg == 2){
					window.location.href='<%=basePath%>bsLogin/index';
				}
			},
			error:function(err){
			
			}
		});
	}
</script>