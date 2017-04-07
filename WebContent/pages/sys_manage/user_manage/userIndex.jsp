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
					<label class="col-sm-2 control-label" for="input-small">用户名：</label>
					<div class="col-sm-3">
						<input type="text" id="username" name="username"
							class="form-control input-sm" placeholder="用户名" />
					</div>
					<label class="col-sm-2 control-label" for="input-small">手机号码：</label>
					<div class="col-sm-3">
						<input type="text" id="telephone" name="telephone"
							class="form-control input-sm" placeholder="手机号" />
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-success" onclick="search()">查询</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="row" id="dataDiv">
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
</script>