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
					<i class="fa fa-indent red"></i>Activity Manage
				</h6>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<label class="col-sm-2 control-label" for="input-small">ActivityName：</label>
					<div class="col-sm-4">
						<input type="text" id="activityname" name="activityname"
							class="form-control input-sm" placeholder="ActivityName" />
					</div>
					<div class="col-sm-6" style="text-align: right">
						<button type="button" class="btn btn-success" onclick="search()">search</button>
						<button id="btn_add" type="button" class="btn btn-success"  data-toggle="modal" data-target="#activityModal">
                			<span aria-hidden="true"></span>add
            			</button>
            			<button type="button" class="btn btn-success" onclick="update()">update</button>
						<button type="button" class="btn btn-success" onclick="deleteActivity()">delete</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 活动列表 -->
<div class="row" id="dataDiv">
</div>

<!-- 新增活动 -->
<div class="modal fade bs-example-modal-lg" id="activityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCancel">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    	 Add Activity Info
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
	                            <label class="col-lg-2  control-label">ActivityName：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="addActivityName" class="form-control"  id="addActivityName" >
	                            </div>
	                            <label class="col-lg-2  control-label">ActivityDate：</label>
	                            <div class="col-lg-4 ">
	                            	<div class="input-group">
											<span class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</span>
											<input type="text"  value="${currDate }" name="addActivityDate" class="datepicker"  id="addActivityDate" />
									</div>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">IsEnd：</label>
	                            <div class="col-lg-4 ">
	                            	 <select  class="form-control" id="addEnd" name="addEnd">
	                                     <option value="0">Begin</option>
	                                     <option value="1">End</option>
	                                 </select>
	                            </div>
                             	<label class="col-lg-2  control-label">Activity Status：</label>
	                            <div class="col-lg-4 ">
	                            	 <select  class="form-control" id="addStatus" name="addStatus">
	                                     <option value="1">Enable</option>
	                                     <option value="0">Disable</option>
	                                 </select>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">ActivityContent：</label>
	                            <div class="col-lg-10 col-md-10">
									<textarea class="form-control" rows="3" id="addActivityContent" name="addActivityContent"></textarea>
								</div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">close</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveActivity()">save</button>
                    </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
</div>

<!-- 更新活动 -->
<div class="modal fade bs-example-modal-lg" id="activityUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCancel">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    	 Update Activity Info
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
                        		<input type="hidden" id="updateActivityId"/>
	                            <label class="col-lg-2  control-label">ActivityName：</label>
	                            <div class="col-lg-4 ">
	                            	<input type="text" name="updateActivityName" class="form-control"  id="updateActivityName" >
	                            </div>
	                            <label class="col-lg-2  control-label">ActivityDate：</label>
	                            <div class="col-lg-4 ">
	                            	<div class="input-group">
											<span class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</span>
											<input type="text"  name="updateActivityDate" class="datepicker"  id="updateActivityDate" />
									</div>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">IsEnd：</label>
	                            <div class="col-lg-4 ">
	                            	 <select  class="form-control" id="updateEnd" name="updateEnd">
	                                     <option value="0">Begin</option>
	                                     <option value="1">End</option>
	                                 </select>
	                            </div>
                             	<label class="col-lg-2  control-label">Activity Status：</label>
	                            <div class="col-lg-4 ">
	                            	 <select  class="form-control" id="updateStatus" name="updateStatus">
	                                     <option value="1">Enable</option>
	                                     <option value="0">Disable</option>
	                                 </select>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2  control-label">ActivityContent：</label>
	                            <div class="col-lg-10 col-md-10">
									<textarea class="form-control" rows="3" id="updateActivityContent" name="updateActivityContent"></textarea>
								</div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">close</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveUpdateActivity()">update</button>
                    </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
</div>

<script type="text/javascript">
	//初始化日期选择器
	$("#addActivityDate").datepicker({
            autoclose: true,//选中之后自动隐藏日期选择框
            clearBtn: true,//清除按钮
            todayBtn: true,//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
    });
    $("#updateActivityDate").datepicker({
            autoclose: true,//选中之后自动隐藏日期选择框
            clearBtn: true,//清除按钮
            todayBtn: true,//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
    });
	search();
	function search(pageid){
		if(pageid==undefined){
			pageid=1;
		}
		$.ajax({
			url:'<%=basePath%>sActivity/activityData',
			data:{
				activityname:$("#activityname").val(),
				page:pageid
			},
			type:'post',
			success:function(msg){
				$("#dataDiv").html(msg);
				$("#activityTable").bootstrapTable({
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
						$("#activityId").val(item[0]);
					}
				});
			},
			error:function(err){
			
			}
		});
	}
	
	//新增用户信息
	function saveActivity(){
		//判断活动名称
		var activityName = $("#addActivityName").val();
		if(activityName==null || activityName == ''){
			toastr.info('ActivityName is empty!');
			return ;
		}
		//判断活动日期
		var activityDate = $("#addActivityDate").val();
		if(activityDate==null || activityDate == ''){
			toastr.info('activityDate is empty!');
			return ;
		}
		$.ajax({
			url:'<%=basePath%>sActivity/saveActivity',
			type:'post',
			data:{
				activityname:activityName,
				activitydate:activityDate,
				activitycontent:$("#addActivityContent").val(),
				end:$("#addEnd").val(),
				status:$("#addStatus").val(),
			},
			success:function(msg){
				if(msg =='0'){
					$("#activityModal").modal('hide');
					toastr.info('save activity info success！');
					search();
				}else{
					toastr.error('save activity info failure！');
				}
			},
			error:function(err){
				console.log(err);
			}
		});
	}
	
	//根据id查询活动信息 填充到活动修改框
	function update(){
		var activityId = $("#activityId").val();
		if(activityId == null || activityId == ''){
			toastr.info('please select one record to update！');
			return ;
		}
		$.ajax({
			url:'<%=basePath%>/sActivity/getActivityById',
			type:'post',
			data:{
				id:activityId
			},
			success:function(msg){
				var jsonObj = JSON.parse(msg); 
				console.log(jsonObj);
				$("#updateActivityName").val(jsonObj.activityname);
				$("#updateActivityDate").val(jsonObj.activitydate);
				$("#updateEnd").val(jsonObj.end);
				$("#updateActivityContent").val(jsonObj.activitycontent);
				$("#updateStatus").val(jsonObj.status);
				$("#updateActivityId").val(jsonObj.id);
				$("#activityUpdateModal").modal('show');
			},
			erorr:function(err){
				console.log(err);
			}
		});
	}
	
	//更新活动信息
	function saveUpdateActivity(){
		//判断活动名称
		var activityName = $("#updateActivityName").val();
		if(activityName==null || activityName == ''){
			toastr.info('ActivityName is empty!');
			return ;
		}
		//判断活动日期
		var activityDate = $("#updateActivityDate").val();
		if(activityDate==null || activityDate == ''){
			toastr.info('activityDate is empty!');
			return ;
		}
		$.ajax({
			url:'<%=basePath%>sActivity/updateActivity',
			type:'post',
			data:{
				activityname:activityName,
				activitydate:activityDate,
				end:$("#updateEnd").val(),
				activitycontent:$("#updateActivityContent").val(),
				status:$("#updateStatus").val(),
				id:$("#updateActivityId").val()
			},
			success:function(msg){
				if(msg == '0'){
					$("#activityUpdateModal").modal('hide');
					toastr.info("update Activity info success！");
					search();
				}else{
					toastr.error("update Activity info failure！");
				}
				
			},
			error:function(err){
			
			}
		});
	}
	
	//删除用户
	function deleteActivity(){
		var activityId = $("#activityId").val();
		if(activityId == null || activityId == ''){
			toastr.info('please select one record to delete！');
			return ;
		}
		$.confirm({
	    	title: 'Prompt message',
	    	content: 'Are you sure to delete this record !',
	    	buttons: {
		        confirm: function () {
		        	$.ajax({
		        		url:'<%=basePath%>sActivity/deleteActivity',
		        		type:'post',
		        		data:{
		        			id:activityId
		        		},
		        		success:function(msg){
		        			if(msg == "0"){
		        				toastr.info("delete Activity success!")
		        				search();
		        			}else{
		        				toastr.error("delete Activity failure!")
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