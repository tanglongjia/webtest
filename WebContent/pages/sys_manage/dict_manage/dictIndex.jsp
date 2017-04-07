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
					<i class="fa fa-indent red"></i>字典管理
				</h6>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<label class="col-sm-2 control-label" for="input-small">字典名称：</label>
					<div class="col-sm-3">
						<input type="text" id="dictName" name="dictName"
							class="form-control input-sm" placeholder="字典名称" />
					</div>
					<div class="col-sm-3 col-sm-offset-4">
						<button type="button" class="btn btn-success" onclick="search()">查询</button>
						<button id="btn_add" type="button" class="btn btn-success"  data-toggle="modal" data-target="#dictModal">
                			<span aria-hidden="true"></span>新增
            			</button>
            			<button type="button" class="btn btn-success" onclick="deleteDict()">删除</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 列表数据 -->
<div class="row" id="dataDiv">
</div>

<!-- 新增模态框 -->
<div class="modal fade bs-example-modal-lg" id="dictModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCancel">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    	 字典信息
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                            <table width="100%" border="0" class="">
                                <tr>
                                    <th width="15%">字典名称：</th>
                                    <td width="35%">
                                        <input type="text" name="addDicName" class="form-control" style="border-radius:3px; width:220px;" id="addDicName" required="" aria-required="true">
                                    </td>
                                     <th width="15%">字典编码：</th>
                                    <td width="35%">
                                        <input type="text" name="addDicCode" class="form-control" style="border-radius:3px; width:220px;" id="addDicCode" required="" aria-required="true">
                                    </td>
                                </tr>
                                <tr>
                                    <th width="15%">字典编号：</th>
                                    <td width="35%">
                                        <input type="text" name="addDicNum" class="form-control" style="border-radius:3px; width:220px;" id="addDicNum" required="" aria-required="true">
                                    </td>
                                    <th>启用状态：</th>
                                    <td>
                                        <select class="selectpicker show-tick" id="addStatus" style="height: 30px; width: 220px;" name="addStatus">
                                            <option value="1">启用</option>
                                            <option value="0">停用</option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                       		<hr/>
                       		<span class="glyphicon glyphicon-plus" aria-hidden="true" onclick="addField()"></span>
                       		<table width="100%" border="0" class="table table-Condensed">
                       			<thead>
									<tr>
										<th>字段名称</th>
										<th>字段值</th>
										<th>操作</th>
									</tr>
                       			</thead>
                       			<tbody id="tbody">
                       				
                       			</tbody>
                       		</table>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">关闭</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveDict()">保存</button>
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
			url:'<%=basePath%>bsDict/dictData',
			data:{
				dictName:$("#dictName").val(),
				page:pageid
			},
			type:'post',
			success:function(msg){
				$("#dataDiv").html(msg);
			},
			error:function(err){
			
			}
		});
	}
	
	//保存数据
	function saveDict(){
		var dicKey=[];
		var dicValue=[];
        $("input[name='addDicKey']").val(function(i,v){
            dicKey.push(v);
        });
         $("input[name='addDicValue']").val(function(i,v){
            dicValue.push(v);
        });
		$.ajax({
			url:'<%=basePath%>bsDict/saveDict',
			data:{
				addDicName:$("#addDicName").val(),
				addDicCode:$("#addDicCode").val(),
				addDicNum:$("#addDicNum").val(),
				addStatus:$("#addStatus").val(),
				addDicKey:dicKey.toString(),
				addDicValue:dicValue.toString()
			},
			type:'post',
			success:function(msg){
				$('#dictModal').modal('hide');
				toastr.info("保存成功！");
				search();
			},
			error:function(err){
			
			}
		});
	}
	
	//增加行
	function addField(){
		var tabHtml = '<tr><td><input type="text" name="addDicKey"/></td><td><input type="text" name="addDicValue"/></td><td><a onclick="delFiled(this)">删除</a></td></tr>';
		$(tabHtml).appendTo("#tbody");
	}
	
	//删除当前行
	function delFiled(nowTr){
		$(nowTr).parent().parent().remove(); 
	}
	
	function deleteDict(){
		var dicCode = $("#dicCode").val();
		if(dicCode ==null || dicCode ==''){
			toastr.info("请选择记录进行删除！");
			return;
		}
		$.ajax({
			url:'<%=basePath%>bsDict/delDict',
			type:'post',
			data:{
				dicCode:dicCode
			},
			success:function(msg){
				toastr.success("删除成功！");
				search();
			},
			error:function(err){
				toastr.error("删除失败！");
			}
		});
	}
	
</script>