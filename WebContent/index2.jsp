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
		<title>Dashboard | Nadhif - Responsive Admin Template</title>
		<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<!-- Favicon and touch icons -->
		<link rel="shortcut icon" href="assets/ico/favicon.ico" type="image/x-icon" />
		<link rel="apple-touch-icon" href="assets/ico/apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="57x57" href="assets/ico/apple-touch-icon-57x57.png" />
		<link rel="apple-touch-icon" sizes="72x72" href="assets/ico/apple-touch-icon-72x72.png" />
		<link rel="apple-touch-icon" sizes="76x76" href="assets/ico/apple-touch-icon-76x76.png" />
		<link rel="apple-touch-icon" sizes="114x114" href="assets/ico/apple-touch-icon-114x114.png" />
		<link rel="apple-touch-icon" sizes="120x120" href="assets/ico/apple-touch-icon-120x120.png" />
		<link rel="apple-touch-icon" sizes="144x144" href="assets/ico/apple-touch-icon-144x144.png" />
		<link rel="apple-touch-icon" sizes="152x152" href="assets/ico/apple-touch-icon-152x152.png" />
	    <!-- start: CSS file-->
		<!-- Vendor CSS-->
		<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
		<link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		<!-- Plugins CSS-->		
		<link href="assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />	
		<link href="assets/plugins/scrollbar/css/mCustomScrollbar.css" rel="stylesheet" />
		<link href="assets/plugins/fullcalendar/css/fullcalendar.css" rel="stylesheet" />
		<link href="assets/plugins/jquery-ui/css/jquery-ui-1.10.4.min.css" rel="stylesheet" />
		<link href="assets/plugins/xcharts/css/xcharts.min.css" rel="stylesheet" />
		<link href="assets/plugins/morris/css/morris.css" rel="stylesheet" />
		<!-- Theme CSS -->
		<link href="assets/css/jquery.mmenu.css" rel="stylesheet" />
		<!-- Page CSS -->		
		<link href="assets/css/style.css" rel="stylesheet" />
		<link href="assets/css/add-ons.min.css" rel="stylesheet" />
		<!-- bootstrap table css -->
		<link href="assets/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
		<!-- toastr -->
		<link href="assets/plugins/toastr/toastr.css" rel="stylesheet" />
		<!-- bootstrap-editable.css -->
		<link href="assets/plugins/editable/css/bootstrap-editable.css" rel="stylesheet" />
		<!-- bootstrap extra css -->
		<link href="assets/css/bootstrap_extra.css" rel="stylesheet" />
		<!-- jsTree css -->
		<link href="assets/plugins/jstree/themes/default/style.css" rel="stylesheet" />
		<!-- end: CSS file-->	
		<!-- Head Libs -->
		<script src="assets/plugins/modernizr/js/modernizr.js"></script>
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
					<!-- Search Form 					
					<form class="search navbar-form">
						<div class="input-group input-search">
							<input type="text" class="form-control" name="q" id="q" placeholder="Search...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
							</span>
						</div>						
					</form>-->
				</div>
				<!-- Navbar Right -->
				<div class="navbar-right">
					<!-- Notifications -->
					<ul class="notifications hidden-sm hidden-xs">
						<li>
							<a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
								<i class="fa fa-tasks"></i>
								<span class="badge">1</span>
							</a>
							<ul class="dropdown-menu update-menu" role="menu">
								<li><a href="#"><i class="fa fa-database bk-fg-primary"></i> Database </a></li>
								<li><a href="#"><i class="fa fa-bar-chart-o bk-fg-primary"></i> Connection </a></li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
								<i class="fa fa-envelope"></i>
								<span class="badge">1</span>
							</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-header">
									<strong>Messages</strong>
									<div class="progress progress-xs  progress-striped active">
										<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
											60%
										</div>
									</div>
								</li>
								<li class="avatar">
									<a href="page-inbox.html">
										<img class="avatar" src="assets/img/avatar1.jpg" alt="" />
										<div><div class="point point-primary point-lg"></div>New message</div>
										<span><small>1 minute ago</small></span>							
									</a>
								</li>
								<li class="dropdown-menu-footer text-center">
									<a href="page-inbox.html">View all messages</a>
								</li>	
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
								<i class="fa fa-bell"></i>
								<span class="badge">1</span>
							</a>
							<ul class="dropdown-menu list-group">
								<li class="dropdown-menu-header">
									<strong>Notifications</strong>
									<div class="progress progress-xs  progress-striped active">
										<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
											60%
										</div>
									</div>
								</li>
								<li class="list-item">
									<a href="#">
										<div class="pull-left">
										   <i class="fa fa-cogs bk-fg-primary"></i>
										</div>
										<div class="media-body clearfix">
											<div>New Settings</div>
											<h6>There are new settings available</h6>
										</div>								
									</a>
								</li>
							</ul>
						</li>
					</ul>
					<!-- End Notifications -->
					<!-- Userbox -->
					<div class="userbox">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<div class="profile-info">
								<span class="name">唐飛</span>
								<span class="role">管理员</span>
							</div>			
							<i class="fa custom-caret"></i>
						</a>
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="dropdown-menu-header bk-bg-white bk-margin-top-15">						
									<div class="progress progress-xs  progress-striped active">
										<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
											60%
										</div>
									</div>							
								</li>	
								<li>
									<a href="#"><i class="fa fa-wrench"></i> 修改密码</a>
								</li>
								<li>
									<a href="page-login.html"><i class="fa fa-power-off"></i> 注销</a>
								</li>
							</ul>
						</div>						
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
							<span style="font-weight: bold;font-size: 24px">后台管理系统</span>
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
							<small>TonyJ <i class="fa fa-coffee"></i> 56帮你工作室  tonyj0602@qq.com</small>
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
		
		<!-- start: JavaScript-->
		<!-- Vendor JS-->				
		<script src="assets/vendor/js/jquery.min.js"></script>
		<script src="assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
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
								menuHtml = menuHtml + '<li  class="nav-parent"><a><i class="fa fa-laptop" aria-hidden="true"></i><span>'+data[index].menuName+'</span></a>';
								menuHtml = menuHtml + '<ul class="nav nav-children">';
								var child = data[index].childList;
								$.each(child, function(index2, val) {
									menuHtml = menuHtml + '<li><a onclick="LoadAjaxContent(\''+child[index2].menuUrl+'\',\''+data[index].menuName+'\',\''+child[index2].menuName+'\')"><span class="text">'+child[index2].menuName+'</span></a></li>';
								});
								menuHtml = menuHtml + '</ul></li>';
							}else{
								menuHtml = menuHtml + '<li><a onclick="LoadAjaxContent(\''+data[index].menuUrl+'\',\''+data[index].menuName+'\',null)"><i class="fa fa-laptop" aria-hidden="false"></i><span>'+data[index].menuName+'</span></a></li>';
							}
						});
						$(menuHtml).appendTo("#menuDiv")
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
	</script>
		<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="assets/vendor/skycons/js/skycons.js"></script>		
		<!-- Plugins JS-->		
		<script src="assets/plugins/jquery-ui/js/jquery-ui-1.10.4.min.js"></script>
		<script src="assets/plugins/scrollbar/js/jquery.mCustomScrollbar.concat.min.js"></script>
		<script src="assets/plugins/bootkit/js/bootkit.js"></script>
		<script src="assets/plugins/moment/js/moment.min.js"></script>	
		<script src="assets/plugins/fullcalendar/js/fullcalendar.min.js"></script>
		<script src="assets/plugins/touchpunch/js/jquery.ui.touch-punch.min.js"></script>
		<script src="assets/plugins/flot/js/jquery.flot.min.js"></script>
		<script src="assets/plugins/flot/js/jquery.flot.pie.min.js"></script>
		<script src="assets/plugins/flot/js/jquery.flot.resize.min.js"></script>
		<script src="assets/plugins/flot/js/jquery.flot.stack.min.js"></script>
		<script src="assets/plugins/flot/js/jquery.flot.time.min.js"></script>
		<script src="assets/plugins/xcharts/js/xcharts.min.js"></script>
		<script src="assets/plugins/autosize/jquery.autosize.min.js"></script>
		<script src="assets/plugins/placeholder/js/jquery.placeholder.min.js"></script>
		<script src="assets/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
		<script src="assets/plugins/datatables/js/jquery.dataTables.min.js"></script>
		<script src="assets/plugins/raphael/js/raphael.min.js"></script>
		<script src="assets/plugins/morris/js/morris.min.js"></script>
		<script src="assets/plugins/gauge/js/gauge.min.js"></script>		
		<script src="assets/plugins/d3/js/d3.min.js"></script>	
		<!-- bootstrap table -->
		<script src="assets/plugins/bootstrap-table/bootstrap-table.js"></script>	
		<script src="assets/plugins/bootstrap-table/bootstrap-table-zh-CN.js"></script>		
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		<!-- Pages JS -->
		<script src="assets/js/pages/index.js"></script>
		<!-- toastr -->
		<script src="assets/plugins/toastr/toastr.js"></script>
		<!-- bootstrap-editable -->
		<script src="assets/plugins/editable/js/bootstrap-editable.min.js"></script>
		<!-- jsTree -->
		<script src="assets/plugins/jstree/jstree.js"></script>
		<!-- end: JavaScript-->
		
	</body>
</html>