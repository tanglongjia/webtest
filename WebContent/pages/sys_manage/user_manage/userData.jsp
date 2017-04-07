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
							<th>序号</th>
							<th>姓名</th>
							<th>手机号码</th>
							<th>年龄</th>
							<th>性别</th>
							<th>登录帐号</th>
							<th>创建时间</th>
							<th>帐号状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${page.resultList }">
							<tr>
								<td>${user.id }</td>
								<td>${user.trueName}</td>
								<td>${user.telephone}</td>
								<td>${user.age }</td>
								<td>
									<c:if test="${user.sex==1 }">
										男
									</c:if>
									<c:if test="${user.sex==2 }">
										女
									</c:if>
								</td>
								<td>${user.loginName }</td>
								<td><fmt:formatDate value="${user.created }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<c:if test="${user.status==1 }">
										启用
									</c:if>
									<c:if test="${user.status==0 }">
										停用
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