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
					<i class="fa fa-indent red"></i>User Activity Manage
				</h6>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<label class="col-sm-2 control-label" for="input-small">ActivityName：</label>
					<div class="col-sm-4">
						<input type="text" id="activityName" name="activityName"
							class="form-control input-sm" placeholder="ActivityName" />
					</div>
					<div class="col-sm-6" style="text-align: right">
						<button type="button" class="btn btn-success" onclick="search()">search</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 用户活动列表 -->
<div class="row" id="dataDiv">
</div>

<script type="text/javascript">
	search();
	function search(pageid){
		if(pageid==undefined){
			pageid=1;
		}
		$.ajax({
			url:'<%=basePath%>sUserActivity/mbUserActivityData',
			data:{
				activityname:$("#activityName").val(),
				page:pageid
			},
			type:'post',
			success:function(msg){
				$("#dataDiv").html(msg);
				$("#activityUserTable").bootstrapTable({
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