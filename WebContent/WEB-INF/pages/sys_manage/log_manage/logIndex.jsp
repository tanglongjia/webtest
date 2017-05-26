<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
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
					<i class="fa fa-indent red"></i>日志管理
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
					<label class="col-sm-2  control-label">开始时间：</label>
					<div class="col-sm-4 ">
                         <div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
							<input type="text"  name="beginTime" class="datepicker"  id="beginTime" value="${defaultDate }"/>
						</div>
                    </div>
                    <label class="col-sm-2 control-label" >结束时间：</label>
					<div class="col-sm-4 ">
                         <div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span>
							<input type="text"  name="endTime" class="datepicker"  id="endTime" value="${defaultDate }"/>
						</div>
                    </div>
                    <label class="col-sm-2  control-label" >操作人名称：</label>
					<div class="col-sm-3" style="margin-top: 10px;">
						<input type="text" id="operatorName" name="operatorName"
							class="form-control input-sm" placeholder="操作人名称" />
					</div>
					<label class="col-sm-2  col-sm-offset-1 control-label" >日志信息：</label>
					<div class="col-sm-3" style="margin-top: 10px;">
						<input type="text" id="description" name="description"
							class="form-control input-sm" placeholder="日志信息" />
					</div>
					<div class="col-sm-1 ">
						<button type="button" class="btn btn-success"
							onclick="search()">查询</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel">
			<div class="panel-heading">
			</div>
			<div class="panel-body">
				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a href="#operLogDiv" id="operLog">用户操作日志</a>
					</li>
					<li><a href="#execpLogDiv" id="execpLog">异常操作日志</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="operLogDiv"></div>
					<div class="tab-pane" id="execpLogDiv"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" value="operLog" id="logType"/>

<script>
	$(function() {
		//初始化日期选择器
		$("#beginTime").datepicker({
	            autoclose: true,//选中之后自动隐藏日期选择框
	            clearBtn: true,//清除按钮
	            todayBtn: true,//今日按钮
	            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
	    });
	    $("#endTime").datepicker({
	            autoclose: true,//选中之后自动隐藏日期选择框
	            clearBtn: true,//清除按钮
	            todayBtn: true,//今日按钮
	            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
	    });
		$('#myTab a:first').tab('show');
		
		//分页查询日志信息
		searchOperLogData();
	})
	
	function search(){
		var logType = $("#logType").val();
		if(logType == 'operLog'){
			searchOperLogData();
		}else{
			searchExcepLogData();
		}
	}
	
	function searchExcepLogData(pageid){
		if(pageid==undefined){
			pageid=1;
		}
		$.ajax({
			url:'<%=basePath%>bsLog/logData',
			type:'post',
			data:{
				operatorName:$("#operatorName").val(),
				beginTime:$("#beginTime").val(),
				endTime:$("#endTime").val(),
				description:$("#description").val(),
				logType:1,
				page:pageid
			},
			success:function(htmlStr){
				$("#execpLogDiv").html(htmlStr);
				$("#execpLogTable").bootstrapTable({
					totalRows:$("#pageTotal2").val(),
					pagination : true, //启动分页  
					pageSize : 10, //每页显示的记录数  
					pageNumber : pageid, //当前第几页  
					pageList : [10], //记录数可选列表  
					onlyInfoPagination : false,
					sidePagination : "server", //表示服务端请求  
					onPageChange : function(number,size){
						searchExcepLogData(number);
					}
				});
			},
			error:function(err){
				console.log(err);
			}
		});
	}
	
	function searchOperLogData(pageid){
		if(pageid==undefined){
			pageid=1;
		}
		$.ajax({
			url:'<%=basePath%>bsLog/logData',
			type:'post',
			data:{
				operatorName:$("#operatorName").val(),
				beginTime:$("#beginTime").val(),
				endTime:$("#endTime").val(),
				description:$("#description").val(),
				logType:0,
				page:pageid
			},
			success:function(htmlStr){
				$("#operLogDiv").html(htmlStr);
				$("#operLogTable").bootstrapTable({
					totalRows:$("#pageTotal").val(),
					pagination : true, //启动分页  
					pageSize : 10, //每页显示的记录数  
					pageNumber : pageid, //当前第几页  
					pageList : [10], //记录数可选列表  
					onlyInfoPagination : false,
					sidePagination : "server", //表示服务端请求  
					onPageChange : function(number,size){
						searchOperLogData(number);
					}
				});
			},
			error:function(err){
				console.log(err);
			}
		});
	}
	
	$('#myTab a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
		$("#logType").val($(this).context.id);
		if($(this).context.id == 'operLog'){
			searchOperLogData();
		}else{
			searchExcepLogData();
		}
	})
</script>

