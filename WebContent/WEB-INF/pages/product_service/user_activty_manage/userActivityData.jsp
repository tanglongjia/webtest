<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div class="col-lg-12">
	<div class="panel">
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table-container table table-hover table-striped" id="activityUserTable">
					<thead>
						<tr>
							<th>No</th>
							<th>UserName</th>
							<th>ActivityName</th>
							<th>ActivityDesc</th>
							<th>CreateTime</th>
							<th>Compete Rate</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="userActivity" items="${page.resultList }">
							<tr>
								<td>${userActivity.id }</td>
								<td title="${userActivity.username}">${userActivity.username}</td>
								<td title="${userActivity.activityname}">${userActivity.activityname}</td>
								<td title="${userActivity.activitydesc }">${userActivity.activitydesc }</td>
								<td><fmt:formatDate value="${userActivity.created }" pattern="yyyy-MM-dd"/></td>
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
