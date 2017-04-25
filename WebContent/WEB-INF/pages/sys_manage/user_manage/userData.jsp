<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div class="col-lg-12">
	<div class="panel">
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table-container table table-hover table-striped" id="userTable">
					<thead>
						<tr>
							<th>No</th>
							<th>TrueName</th>
							<th>Telephone</th>
							<th>Email</th>
							<th>Address</th>
							<th>Age</th>
							<th>Sex</th>
							<th>LoginName</th>
							<th>PassWord</th>
							<th>CreateTime</th>
							<th>UserType</th>
							<th>Accout Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${page.resultList }">
							<tr>
								<td>${user.id }</td>
								<td>${user.trueName}</td>
								<td>${user.telephone}</td>
								<td>${user.email}</td>
								<td>${user.address}</td>
								<td>${user.age }</td>
								<td>
									<c:if test="${user.sex==1 }">
										Male
									</c:if>
									<c:if test="${user.sex==2 }">
										Female
									</c:if>
								</td>
								<td>${user.loginName }</td>
								<td>${user.passWord }</td>
								<td><fmt:formatDate value="${user.created }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<c:if test="${user.userType==0 }">
										leader
									</c:if>
									<c:if test="${user.userType==1 }">
										member
									</c:if>
								</td>
								<td>
									<c:if test="${user.status==1 }">
										Enable 
									</c:if>
									<c:if test="${user.status==0 }">
										Disable 
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
<input type="hidden" id="userId" />