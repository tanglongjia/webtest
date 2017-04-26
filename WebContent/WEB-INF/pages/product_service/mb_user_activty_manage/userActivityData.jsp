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
				<table class="table-container table table-hover table-striped" id="activityUserTable">
					<thead>
						<tr>
							<th>No</th>
							<th>Id</th>
							<th>UserName</th>
							<th>ActivityName</th>
							<th>ActivityDesc</th>
							<th>CreateTime</th>
							<th>Compete</th>
							<th>Compete Rate</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="userActivity" items="${activityList }" varStatus="status">
							<tr>
								<td>${status.index+1 }</td>
								<td>${userActivity.id }</td>
								<td title="${userActivity.username}">${userActivity.username}</td>
								<td title="${userActivity.activityname}">${userActivity.activityname}</td>
								<td title="${userActivity.activitydesc }">${userActivity.activitydesc }</td>
								<td><fmt:formatDate value="${userActivity.created }" pattern="yyyy-MM-dd"/></td>
								<td>
									<a href="#" name="compete"  data-type="text" data-pk="${userActivity.id }">${userActivity.compete }</a>
								</td>
								<td>
									<div class="bar">
										<div class="progress progress-md  progress-striped active">
											<div 
												<c:if test="${userActivity.compete <=50 && userActivity.compete > 25 }">
													class="progress-bar progress-bar-warning"
												</c:if>
												<c:if test="${userActivity.compete >= 50 && userActivity.compete< 75}">
													class="progress-bar progress-bar-primary"
												</c:if>
												<c:if test="${userActivity.compete >= 75}">
													class="progress-bar progress-bar-success"
												</c:if>
												<c:if test="${userActivity.compete < 25 }">
													class="progress-bar progress-bar-danger" 
												</c:if>
												role="progressbar"  aria-valuenow="${userActivity.compete }" 
												aria-valuemin="0" 
												aria-valuemax="100" 
												style="width: ${userActivity.compete }%">
											</div>
										</div>
									</div>			
									<div class="desc">${userActivity.compete } of 100</div>
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
<script>
	 //editables 
    $("a[name='compete']").editable({
           url: function(params){
                console.log(params);
           		$.ajax({
           			url:'<%=basePath%>/sUserActivity/updateComete',
           			type:'post',
           			data:params,
           			success:function(msg){
           				if(msg == '0'){
           					toastr.success("update compete success!");
           					search();
           				}else{
           					toastr.error("update compete error!");
           				}
           			},
           			error:function(err){
           			}
           		});
           },
           type: 'text',
           name: 'compete',
           title: 'Compete Rate',
           validate: function (value) { //字段验证
                if (!$.trim(value)) {
                    return 'Not Empty!';
                }
                if(value < 0 || value > 100){
                	return 'compete between 0 and 100!';
                }
            }
    });
</script>