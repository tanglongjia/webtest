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
					<i class="fa fa-indent red"></i>菜单管理
				</h6>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
					<label class="col-sm-2 control-label" for="input-small">菜单名称：</label>
					<div class="col-sm-3">
						<input type="text" id="menuName" name="menuName"
							class="form-control input-sm" placeholder="菜单名称" />
					</div>
					<div class="col-sm-3 col-sm-offset-4">
						<button type="button" class="btn btn-success" onclick="getTree()">查询</button>
						<button type="button" class="btn btn-success" onclick="add()">新增</button>
						<button type="button" class="btn btn-success" onclick="update()">修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-3" >
		<div class="panel">
			<div class="panel-heading bk-bg-primary">
				<h6>
					<i class="fa fa-indent red"></i>系统菜单
				</h6>
			</div>
			<div class="panel-body" id="treeDiv">
				
			</div>
		</div>
	</div>
	<div class="col-lg-9" id="dataDiv">
	</div>
</div>

<!-- 修改菜单信息 -->
<div class="modal fade bs-example-modal-lg" id="updateResModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCancel">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    	 菜单信息
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
	                            <label class="col-lg-2 col-md-2 col-sm-12 control-label">菜单名称：</label>
	                            <div class="col-lg-4 col-md-10">
	                            	<input type="text" name="updateMenuName" class="form-control"  id="updateMenuName" >
	                            	<input type="hidden" name="updateMenuId" class="form-control"  id="updateMenuId" >
	                            </div>
	                             <label class="col-lg-2 col-md-2 col-sm-12 control-label">菜单图标：</label>
	                            <div class="col-lg-4 col-md-10 ">
	                            	<div class="input-group">
	                            	   <input type="text" class="form-control" id="updateMenuImgPath" >
									   <span class="input-group-btn">
									        <button class="btn btn-default" id="imgPath"  role="iconpicker"></button>
									   </span>
									 </div>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2 col-md-2 col-sm-12 control-label">访问路径：</label>
								<div class="col-lg-4 col-md-10">
									<input type="text" name="updateMenuUrl" class="form-control"  id="updateMenuUrl" >
								</div>
								<label class="col-lg-2 col-md-2 col-sm-12 control-label">启用状态：</label>
								<div class="col-lg-4 col-md-10">
									 <select  class="form-control" id="updateResStatus"  name="updateResStatus">
	                                     <option value="1">启用</option>
	                                     <option value="0">停用</option>
	                                 </select>
								</div>
							</div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">关闭</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveUpdateRes()">保存</button>
                    </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
</div>

<!-- 新增菜单 -->
<div class="modal fade bs-example-modal-lg" id="addResModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCancel">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    	 菜单信息
                    </h4>
                </div>
                    <div class="modal-body">
                        <div>
                        	<div class="form-group">
	                            <label class="col-lg-2 col-md-2 col-sm-12 control-label">菜单名称：</label>
	                            <div class="col-lg-4 col-md-10">
	                            	<input type="text" name="addMenuName" class="form-control"  id="addMenuName" >
	                            </div>
	                             <label class="col-lg-2 col-md-2 col-sm-12 control-label">菜单图标：</label>
	                            <div class="col-lg-4 col-md-10 ">
	                            	<div class="input-group">
	                            	   <input type="text" class="form-control" id="addMenuImgPath">
									   <span class="input-group-btn">
									        <button class="btn btn-default" id="addImgPath"  role="iconpicker"></button>
									   </span>
									 </div>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2 col-md-2 col-sm-12 control-label">菜单编码：</label>
	                            <div class="col-lg-4 col-md-10">
	                            	<input type="text" name="addMenuCode" class="form-control"  id="addMenuCode" >
	                            </div>
	                             <label class="col-lg-2 col-md-2 col-sm-12 control-label">是否叶子节点：</label>
	                            <div class="col-lg-4 col-md-10 ">
	                            	<select  class="form-control" id="addLeafNode"  name="addLeafNode">
	                                     <option value="1">是</option>
	                                     <option value="0">否</option>
	                                 </select>
	                            </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-lg-2 col-md-2 col-sm-12 control-label">访问路径：</label>
								<div class="col-lg-4 col-md-10">
									<input type="text" name="addMenuUrl" class="form-control"  id="addMenuUrl" >
								</div>
								<label class="col-lg-2 col-md-2 col-sm-12 control-label">启用状态：</label>
								<div class="col-lg-4 col-md-10">
									 <select  class="form-control" id="addResStatus"  name="addResStatus">
	                                     <option value="1">启用</option>
	                                     <option value="0">停用</option>
	                                 </select>
								</div>
							</div>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top:none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnClose">关闭</button>
                        <button type="submit" class="btn btn-primary" id="btnSave" onclick="saveAddRes()">保存</button>
                    </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
</div>
<input type="hidden" id="parentId" />

<script type="text/javascript">
	getTree();
	$('#addImgPath').iconpicker().on('change', function(e){
    		$("#addMenuImgPath").val(e.icon);
    });
	function getTree(){
		$.ajax({
			url:'<%=basePath%>/bsRes/getTree',
			data:{},
			type:'post',
			success:function(data){
				createTree(data);
				getTable(null,1);
			},
			error:function(err){
			
			}
		});
	}
	
	function createTree(data){
		$("#treeDiv").jstree("destroy"); 
		$("#treeDiv").jstree({  
            "core" : {
            "themes" : {
                "responsive": false
            },
            "check_callback" : true,
            'data' : JSON.parse(data)
        },
        "types" : {
            "default" : {
                "icon" : "fa fa-folder icon-state-warning icon-lg"
            },
            "file" : {
                "icon" : "fa fa-file icon-state-warning icon-lg"
            }
        },
        "state" : { "key" : "demo2" },
        "plugins" : ["dnd", "state", "types" ],
	 }); 
	 
	  // 监听菜单事件
      $('#treeDiv').on('activate_node.jstree', function(obj, e) {  
          var currentNode = e.node;
          getTable(currentNode.id,1);
          $("#parentId").val(currentNode.id);
          console.log(currentNode);
      });  
      
   }
   
   function getTable(id,pageid){
   		if(pageid==undefined){
			pageid=1;
		}
   		$.ajax({
   			url:'<%=basePath%>/bsRes/getData',
   			data:{
   				id:id,
   				page:pageid
   			},
   			type:'post',
   			success:function(htmlStr){
   				$("#dataDiv").html(htmlStr);
				$("#resTable").bootstrapTable({
					totalRows:$("#pageTotal").val(),
					pagination : true, //启动分页  
					pageSize : 10, //每页显示的记录数  
					pageNumber : pageid, //当前第几页  
					pageList : [10], //记录数可选列表  
					onlyInfoPagination : false,
					sidePagination : "server", //表示服务端请求  
					onPageChange : function(number,size){
						getTable(null,number);
					},
					onClickRow:function(item, $element){
						$("#resId").val(item[0]);
					}
				});
   			},
   			error:function(err){
   			
   			}
   		});
   }
   
   //跳转到更新页面
   function update(){
   		//bootstrap icon初始化
    	$('#imgPath').iconpicker().on('change', function(e){
    		$("#updateMenuImgPath").val(e.icon);
    	});
   		var resId = $("#resId").val();
   		if(resId==null || resId ==''){
   			toastr.info("请选择要修改的记录");
   			return;
   		}
   		$.ajax({
   			url:'<%=basePath%>/bsRes/getResByPk',
   			type:'post',
   			data:{
   				id:resId
   			},
   			success:function(str){
   				var json = JSON.parse(str); 
   				$("#updateMenuName").val(json.menuName);
   				$("#updateMenuId").val(json.id);
   				$("#updateMenuImgPath").val(json.menuImgPath);
   				$("#imgPath").attr("data-icon",json.menuImgPath);
   				$("#updateMenuUrl").val(json.menuUrl);
   				$("#updateResStatus").val(json.status);
   				$("#updateResModal").modal('show');
   			},
   			error:function(err){
   			
   			}
   		});
   }
   
   //更新菜单信息
   function saveUpdateRes(){
   		$.ajax({
   			url:'<%=basePath%>/bsRes/updateResByPk',
   			type:'post',
   			data:{
   				id:$("#updateMenuId").val(),
   				menuName:$("#updateMenuName").val(),
   				menuImgPath:$("#updateMenuImgPath").val(),
   				menuUrl:$("#updateMenuUrl").val(),
   				status:$("#updateResStatus").val()
   			},
   			success:function(str){
   				$("#updateResModal").modal('hide');
   				toastr.success("更新成功！");
   				getTree();
   			},
   			error:function(err){
   				toastr.error("更新失败！");
   			}
   		});
   }
   
   //弹出新增页面
   function add(){
   		var parentId = $("#parentId").val();
   		if(parentId==null || parentId ==''){
   			toastr.info("请在左侧菜单树选择新增菜单添加在哪个菜单下！");
   			return;
   		}
   		$("#addResModal").modal('show');
   }
   
   //保存新增菜单信息  
   function saveAddRes(){
		$.ajax({
		url:'<%=basePath%>/bsRes/saveAddRes',
		type:'post',
		data:{
			menuName:$("#addMenuName").val(),
			menuImgPath:$("#addMenuImgPath").val(),
			menuUrl:$("#addMenuUrl").val(),
			status:$("#addResStatus").val(),
			menuCode:$("#addMenuCode").val(),
			isLeafNode:$("#addLeafNode").val(),
			parentId:$("parentId").val()
		},
		success:function(str){
			$("#addResModal").modal('hide');
			toastr.success("保存成功！");
			getTree();
		},
		error:function(err){
			toastr.error("保存失败！");
		}
	});
   }
</script>