<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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
							<th>CreateTime</th>
							<th>Activity Status</th>
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
								<td><fmt:formatDate value="${activity.created }" pattern="yyyy-MM-dd"/></td>
								<td>
									<c:if test="${activity.status==0 }">
										Disable
									</c:if>
									<c:if test="${activity.status==1 }">
										Enable
									</c:if>
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
<input type="hidden" id="activityId" />