<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="col-lg-12">
	<div class="panel">
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table-container table table-hover table-striped" id="activityTable">
					<thead>
						<tr>
							<th>No</th>
							<th>ActivityName</th>
							<th>ActivityContent</th>
							<th>ActivityDate</th>
							<th>IsEnd</th>
							<th>Activity Status</th>
							<th>Operate</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="activity" items="${page.resultList }">
							<tr>
								<td>${activity.id }</td>
								<td>${activity.activityname}</td>
								<td title="${activity.activitycontent}">${activity.activitycontent}</td>
								<td>${activity.activitydate }</td>
								<td>
									<c:if test="${activity.end==0 }">
										begin
									</c:if>
									<c:if test="${activity.end==1 }">
										end
									</c:if>
								</td>
								<td>
									<c:if test="${activity.status==0 }">
										Disable
									</c:if>
									<c:if test="${activity.status==1 }">
										Enable
									</c:if>
								</td>
								<td>
									<c:choose> 
									  <c:when test="${activity.end==1 || activity.status==0 }">   
									  	<button type="button" class="bk-margin-5 btn btn-primary btn-sm" disabled="disabled" >choose</button>
									  </c:when> 
									  <c:otherwise>   
									  	<button type="button" class="bk-margin-5 btn btn-primary btn-sm" onclick="select('${activity.id }','${activity.activityname}','${activity.activitycontent}')" >choose</button>
									  </c:otherwise> 
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="pageTotal" value="${page.totalResult}"/>
<script type="text/javascript">
	function select(activityId,activityname,activitycontent){
		//1、先判断该任务是否已经选过
		$.ajax({
			url:'<%=basePath%>sActivity/mbIsExits',
			type:'post',
			data:{
				activityId:activityId
			},
			success:function(msg){
				if(msg == '0'){
					chooseActivity(activityId,activityname,activitycontent);
					search();
				}else{
					toastr.warning("This Activity has been chosen ！");
				}
			},
			error:function(err){
			
			}
		});		
	}
	
	function chooseActivity(activityId,activityname,activitycontent){
		$.ajax({
			url:'<%=basePath%>sActivity/chooseActivity',
			type:'post',
			data:{
				activityId:activityId,
				activityname:activityname,
				activitycontent:activitycontent
			},
			success:function(msg){
				if(msg =='0'){
					toastr.info("Choose Activity Success!");
				}else{
					toastr.error("Choose Activity Failure!");
				}
			},
			error:function(err){
				
			}
		});
	}
</script>
