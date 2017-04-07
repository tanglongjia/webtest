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
						<button type="button" class="btn btn-success" onclick="search()">查询</button>
						<button id="btn_add" type="button" class="btn btn-success"
							data-toggle="modal" data-target="#roleModal">
							<span aria-hidden="true"></span>新增
						</button>
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

<script type="text/javascript">
	getTree();
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
	 
	  // 监听菜单时间 
      $('#treeDiv').on('changed.jstree', function(e, data) {  
          r = [];  
          var i, j;  
          for (i = 0, j = data.selected.length; i < j; i++) {
              var node = data.instance.get_node(data.selected[i]); 
              getTable(node.id,1); 
          }  
      });  
   }
   
   function getTable(id,pageid){
   		if(pageid==undefined){
			pageid=1;
		}
   		$.ajax({
   			url:'<%=basePath%>/bsRes/getData',
   			data:{
   				id:id
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
						search(number);
					}
				});
   			},
   			error:function(err){
   			
   			}
   		});
   }
   
     
</script>