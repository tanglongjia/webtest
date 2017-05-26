<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html lang="en">
	<head>
		<!-- Basic -->
    	<meta charset="UTF-8" />
		<title>基础框架系统</title>
		<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<!-- Favicon and touch icons -->
		<link rel="shortcut icon" href="<%=basePath %>assets/ico/favicon.ico" type="image/x-icon" />
		<link rel="apple-touch-icon" href="<%=basePath %>assets/ico/apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="57x57" href="<%=basePath %>assets/ico/apple-touch-icon-57x57.png" />
		<link rel="apple-touch-icon" sizes="72x72" href="<%=basePath %>assets/ico/apple-touch-icon-72x72.png" />
		<link rel="apple-touch-icon" sizes="76x76" href="<%=basePath %>assets/ico/apple-touch-icon-76x76.png" />
		<link rel="apple-touch-icon" sizes="114x114" href="<%=basePath %>assets/ico/apple-touch-icon-114x114.png" />
		<link rel="apple-touch-icon" sizes="120x120" href="<%=basePath %>assets/ico/apple-touch-icon-120x120.png" />
		<link rel="apple-touch-icon" sizes="144x144" href="<%=basePath %>assets/ico/apple-touch-icon-144x144.png" />
		<link rel="apple-touch-icon" sizes="152x152" href="<%=basePath %>assets/ico/apple-touch-icon-152x152.png" />
	    <!-- start: CSS file-->
		<!-- Vendor CSS-->
		<link href="<%=basePath %>assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="<%=basePath %>assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
		<link href="<%=basePath %>assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<!-- Plugins CSS-->		
		<link href="<%=basePath %>assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />	
		<link href="<%=basePath %>assets/plugins/scrollbar/css/mCustomScrollbar.css" rel="stylesheet" />
		<link href="<%=basePath %>assets/plugins/fullcalendar/css/fullcalendar.css" rel="stylesheet" />
		<link href="<%=basePath %>assets/plugins/jquery-ui/css/jquery-ui-1.10.4.min.css" rel="stylesheet" />
		<link href="<%=basePath %>assets/plugins/xcharts/css/xcharts.min.css" rel="stylesheet" />
		<link href="<%=basePath %>assets/plugins/morris/css/morris.css" rel="stylesheet" />
		<!-- Theme CSS -->
		<link href="<%=basePath %>assets/css/jquery.mmenu.css" rel="stylesheet" />
		<!-- Page CSS -->		
		<link href="<%=basePath %>assets/css/style.css" rel="stylesheet" />
		<link href="<%=basePath %>assets/css/add-ons.min.css" rel="stylesheet" />
		<!-- bootstrap table css -->
		<link href="<%=basePath %>assets/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
		<!-- toastr -->
		<link href="<%=basePath %>assets/plugins/toastr/toastr.css" rel="stylesheet" />
		<!-- bootstrap-editable.css -->
		<link href="<%=basePath %>assets/plugins/editable/css/bootstrap-editable.css" rel="stylesheet" />
		<!-- bootstrap extra css -->
		<link href="<%=basePath %>assets/css/bootstrap_extra.css" rel="stylesheet" />
		<!-- jsTree css -->
		<link href="<%=basePath %>assets/plugins/jstree/themes/default/style.css" rel="stylesheet" />
		<!-- Bootstrap-Iconpicker -->
		<link rel="stylesheet" href="<%=basePath %>assets/plugins/bootstrap-iconpicker/icon-fonts/elusive-icons-2.0.0/css/elusive-icons.min.css"/>
        <link rel="stylesheet" href="<%=basePath %>assets/plugins/bootstrap-iconpicker/icon-fonts/font-awesome-4.2.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="<%=basePath %>assets/plugins/bootstrap-iconpicker/icon-fonts/ionicons-1.5.2/css/ionicons.min.css"/>
        <link rel="stylesheet" href="<%=basePath %>assets/plugins/bootstrap-iconpicker/icon-fonts/map-icons-2.1.0/css/map-icons.min.css"/>
        <link rel="stylesheet" href="<%=basePath %>assets/plugins/bootstrap-iconpicker/icon-fonts/material-design-1.1.1/css/material-design-iconic-font.min.css"/>
        <link rel="stylesheet" href="<%=basePath %>assets/plugins/bootstrap-iconpicker/icon-fonts/octicons-2.1.2/css/octicons.min.css"/>
        <link rel="stylesheet" href="<%=basePath %>assets/plugins/bootstrap-iconpicker/icon-fonts/typicons-2.0.6/css/typicons.min.css"/>
        <link rel="stylesheet" href="<%=basePath %>assets/plugins/bootstrap-iconpicker/icon-fonts/weather-icons-1.2.0/css/weather-icons.min.css"/>
		<link rel="stylesheet" href="<%=basePath %>assets/plugins/bootstrap-iconpicker/css/bootstrap-iconpicker.min.css"/>
		<!-- jquery-confirm-->
		<link rel="stylesheet" href="<%=basePath %>assets/plugins/jquery-confirm/jquery-confirm.min.css"/>
		<link href="<%=basePath %>assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
		<link href="<%=basePath %>assets/plugins/bootstrap-datepicker/css/datepicker-theme.css" rel="stylesheet" />
		<!-- end: CSS file-->	
		<!-- Head Libs -->
	</head>
	
	<body>
	
		<!-- Start: Header -->
		<div class="navbar" role="navigation">
			<div class="container-fluid container-nav">				
				<!-- Navbar Action -->
				<ul class="nav navbar-nav navbar-actions navbar-left">
					<li class="visible-md visible-lg"><a href="#" id="main-menu-toggle"><i class="fa fa-th-large"></i></a></li>
					<li class="visible-xs visible-sm"><a href="#" id="sidebar-menu"><i class="fa fa-navicon"></i></a></li>			
				</ul>
				<!-- Navbar Left -->
				<div class="navbar-left">
				</div>
				<!-- Navbar Right -->
				<div class="navbar-right">
					<!-- Userbox -->
					<div class="userbox">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="fa fa-user"></i>
							<div class="profile-info">
								<span class="name">欢迎您！ ${ sessionScope.bsUser.trueName}</span>
							</div>			
						</a>
						<a onclick="logout()" style="cursor:pointer;"><i class="fa fa-power-off"></i>&nbsp;&nbsp;注销</a>
						&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="updatePwd()" style="cursor:pointer;"><i class="fa fa-wrench"></i>&nbsp;&nbsp;更改密码</a>
					</div>
					<!-- End Userbox -->
				</div>
				<!-- End Navbar Right -->
			</div>		
		</div>
		<!-- End: Header -->
		<div class="copyrights"></div>
		<!-- Start: Content -->
		<div class="container-fluid content">	
			<div class="row">
			
				<!-- Sidebar -->
				<div class="sidebar">
					<div class="sidebar-collapse">
						<!-- Sidebar Header Logo-->
						<div class="sidebar-header">
							<!--  <img src="assets/img/logo.png" class="img-responsive" alt="" />-->
							<span style="font-weight: bold;font-size: 24px">基础框架系统</span>
						</div>
						<!-- Sidebar Menu-->
						<div class="sidebar-menu">						
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-sidebar" id="menuDiv">
									<!-- <div class="panel-body text-center">								
										<div class="bk-avatar">
											<img src="assets/img/avatar.jpg" class="img-circle bk-img-60" alt="" />
										</div>
										<div class="bk-padding-top-10">
											<i class="fa fa-circle text-success"></i> <small>管理员</small>
										</div>
									</div>
									<div class="divider2"></div> -->
								</ul>
							</nav>
						</div>
						<!-- End Sidebar Menu-->
					</div>
					<!-- Sidebar Footer-->
					<div class="sidebar-footer">	
						<ul class="sidebar-terms">
							<li><a href="#">团队</a></li>
							<li><a href="#">个人</a></li>
							<li><a href="#">帮助</a></li>
							<li><a href="#">关于</a></li>
						</ul>
						<div class="copyright text-center">
						</div>					
					</div>
					<!-- End Sidebar Footer-->
				</div>
				<!-- End Sidebar -->
		
				<!-- Main Page -->
				<div class="main ">
					<!-- Page Header -->
					<div class="page-header">
						<div class="pull-left">
							<ol class="breadcrumb visible-sm visible-md visible-lg">								
								<li><a href="#"><i class="icon fa fa-home"></i><span id="level1"></span></a></li>
								<li class="active"><i class="fa fa-laptop"></i><span id="level2"></span></li>
							</ol>						
						</div>
						<div class="pull-right">
							<h2><span id="level3"></span></h2>
						</div>					
					</div>
					<!-- End Page Header -->	
					
					<div class="row" id="mainContent"/>
						
					</div>
												
				</div>
				<!-- End Main Page -->			
			</div>
		</div><!--/container-->
		<div class="clearfix"></div>		
		
	<!-- 修改密码 -->
	<div class="modal fade bs-example-modal-lg" id="updatePwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCancel">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    	 Update Password 
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
                        		 <label class="col-lg-3  control-label">旧密码：</label>
	                            <div class="col-lg-9">
	                            	<input type="password" name="oldPassWord" class="form-control"  id="oldPassWord" >
	                            </div>
	                            <label class="col-lg-3  control-label">新密码：</label>
	                            <div class="col-lg-9 ">
	                            	<input type="password" name="newPassWord" class="form-control"  id="newPassWord" >
	                            </div>
	                            <label class="col-lg-3  control-label">再次新密码：</label>
	                            <div class="col-lg-9 ">
	                            	<input type="password" name="againNewPassWord" class="form-control"  id="againNewPassWord" >
	                            </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">关闭</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="savePwd()">保存</button>
                    </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
	</div>
		
		<!-- start: JavaScript-->
		<!-- Vendor JS-->				
		<script src="<%=basePath %>assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="<%=basePath %>assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<!-- Theme JS -->		
		<script src="<%=basePath %>assets/js/jquery.mmenu.min.js"></script>
		<script src="<%=basePath %>assets/js/core.min.js"></script>
		<script type="text/javascript">
			getLeftMenu();
		    function getLeftMenu(){
		    	$.ajax({  
				     url:'<%=basePath%>/bsRes/getLeftMenu',
				     data:{},  
				     type:'get',
				     async:false,  
				     cache:false,  
				     dataType:'json',  
				     success:function(data) {
				     	$("#menuDiv").html("");
				     	var menuHtml = '';
						$.each(data, function(index, val) {
							var isLeafNode = data[index].isLeafNode;
							if(isLeafNode == 0){//有下级菜单
								menuHtml = menuHtml + '<li  class="nav-parent"><a><i class="glyphicon '+data[index].menuImgPath+'" aria-hidden="true"></i><span>'+data[index].menuName+'</span></a>';
								menuHtml = menuHtml + '<ul class="nav nav-children">';
								var child = data[index].childList;
								$.each(child, function(index2, val) {
									menuHtml = menuHtml + '<li><a onclick="LoadAjaxContent(\''+child[index2].menuUrl+'\',\''+data[index].menuName+'\',\''+child[index2].menuName+'\')"><i class="glyphicon '+child[index2].menuImgPath+'" aria-hidden="true"></i><span class="text">'+child[index2].menuName+'</span></a></li>';
								});
								menuHtml = menuHtml + '</ul></li>';
							}else{
								menuHtml = menuHtml + '<li><a onclick="LoadAjaxContent(\''+data[index].menuUrl+'\',\''+data[index].menuName+'\',null)"><i class="fa fa-laptop" aria-hidden="false"></i><span>'+data[index].menuName+'</span></a></li>';
							}
						});
						$(menuHtml).appendTo("#menuDiv");
				     },  
				     error : function() {  
				     }  
				 });
		      }
			 function LoadAjaxContent(url,level1,level2){
			 		$("#level1").html(level1);
			 		$("#level2").html(level2);
			 		$("#level3").html(level2);
					$.ajax({
						mimeType: 'text/html; charset=utf-8',
						url: '<%=basePath%>'+url,
						type: 'GET',
						success: function(data) {
							$("#mainContent").html(data);
						},
						error: function (jqXHR, textStatus, errorThrown) {
						},
						dataType: "html",
						async: false
					});
			}
			
			function updatePwd(){
				$("#updatePwdModal").modal('show');
			}
			
			function savePwd(){
				var oldPassWord=$("#oldPassWord").val();
				var newPassWord=$("#newPassWord").val();
				var againNewPassWord=$("#againNewPassWord").val();
				if(oldPassWord==null || oldPassWord==''){
					toastr.warning("旧密码为空!");
					return ;
				}
				if(newPassWord==null || newPassWord==''){
					toastr.warning("新密码为空!");
					return ;
				}
				if(againNewPassWord==null || againNewPassWord==''){
					toastr.warning("第二次密码不能为空!");
					return ;
				}
				if(newPassWord!=againNewPassWord){
					toastr.warning("新密码两次不一样!");
					return ;
				}
				$.ajax({
					url:'<%=basePath%>bsLogin/updatePwd',
					type:'post',
					data:{
						oldPassWord:oldPassWord,
						newPassWord:newPassWord,
						againNewPassWord:againNewPassWord
					},
					success:function(msg){
						if(msg == '0'){
							toastr.success('update password success!');
							$("#updatePwdModal").modal('hide');
							setTimeout(function () {
			    				window.location.href='<%=basePath%>/login.jsp';
			  				}, 1500);
										
						}else{
							toastr.error('update password error!');
						}
					},
					error:function(err){
					
					}
				});
			}
		</script>
		<!-- bootstrap table -->
		<script src="<%=basePath %>assets/plugins/bootstrap-table/bootstrap-table.js"></script>	
		<script src="<%=basePath %>assets/plugins/bootstrap-table/bootstrap-table-zh-CN.js"></script>	
		<!-- Pages JS -->
		<!-- toastr -->
		<script src="<%=basePath %>assets/plugins/toastr/toastr.js"></script>
		<!-- bootstrap-editable -->
		<script src="<%=basePath %>assets/plugins/editable/js/bootstrap-editable.min.js"></script>
		<!-- jsTree -->
		<script src="<%=basePath %>assets/plugins/jstree/jstree.js"></script>
		<!-- Bootstrap-Iconpicker Iconset for Glyphicon -->
		<script type="text/javascript" src="<%=basePath %>assets/plugins/bootstrap-iconpicker/js/iconset/iconset-glyphicon.min.js"></script>
		<!-- Bootstrap-Iconpicker -->
		<script type="text/javascript" src="<%=basePath %>assets/plugins/bootstrap-iconpicker/js/bootstrap-iconpicker.min.js"></script>
		<!-- jquery-confirm -->
		<script type="text/javascript" src="<%=basePath %>assets/plugins/jquery-confirm/jquery-confirm.min.js"></script>
		<!-- bootstrap-datepicker js -->
		<script src="<%=basePath %>assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		<!-- end: JavaScript-->
		<script type="text/javascript">
			function logout(){
				window.location.href="<%= basePath%>bsLogin/logout";
			}
		</script>
	</body>
</html>