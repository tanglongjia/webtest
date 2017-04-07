<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="col-lg-8">
	<div class="panel">
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table-container table table-hover table-striped" id="dictTable">
					<thead>
						<tr>
							<th>字典名称</th>
							<th>字典编码</th>
							<th>字典编号</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dict" items="${dataList}">
							<tr onclick="showField('${dict['dicCode']}')">
								<td>
									<a href="#" name="dicName" data-type="text" data-pk="${dict['dicCode']}">${dict['dicName']}</a>
								</td>
								<td>${dict['dicCode']}</td>
								<td>${dict['dicNum'] }</td>
								<td><fmt:formatDate value="${dict['created'] }" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="pageTotal" value="${page.totalResult}"/>
<input type="hidden" id="dicCode" value=""/>

<div class="col-lg-4">
	<div class="panel">
				<table class="table-container table table-hover table-striped" id="fieldTable">
					<thead>
						<tr>
							<th>字段名称</th>
							<th>字段值</th>
						</tr>
					</thead>  
					<tbody>
					</tbody>
				</table> 
	</div>
</div>
<script type="text/javascript">
	function showField(dicCode){
		$("#dicCode").val(dicCode);
		$("#fieldTable").bootstrapTable("destroy").bootstrapTable({
			url:'<%=basePath%>/bsDict/showField?dictCode='+dicCode,
			method: 'post', 
			pagination:false,
			columns:[{
				field: 'dicKey',
                title: '字段名称',
                formatter: function (value, row, index) {
                    return "<a href=\"#\" name=\"dicKey\" data-type=\"text\" data-pk=\""+row.diccode+"\" data-title=\"字段名称\">" + value + "</a>";
                },
			},{
				field: 'dicValue',
                title: '字段值',
                formatter: function (value, row, index) {
                	return "<a href=\"#\" name=\"dicValue\" data-type=\"text\" data-pk=\""+row.diccode+"\" data-title=\"字段值\">" + value + "</a>";
				}
			}],
			onLoadSuccess:function(a,b,c){
				  $("a[name='dicKey']").editable({
			           url: '<%=basePath%>/bsDict/updateDict',
			           type: 'text',
			           name: 'dickey',
			           title: '字典编码'
			    });
			    $("a[name='dicValue']").editable({
			           url: '<%=basePath%>/bsDict/updateDict',
			           type: 'text',
			           name: 'dicvalue',
			           title: '字典编号'
			    });
			}
		});
	
	}
	
	 //editables 
    $("a[name='dicName']").editable({
           url: '<%=basePath%>/bsDict/updateDict',
           type: 'text',
           name: 'dicname',
           title: '字典名称'
    });
  
</script>