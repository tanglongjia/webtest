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
				<table class="table-container table table-hover table-striped" id="roleTable">
					<thead>
						<tr>
							<!-- <th data-checkbox="true"></th> -->
							<th>序号</th>
							<th>角色名称</th>
							<th>角色描述</th>
							<th>创建时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="role" items="${page.resultList }">
							<tr>
								<!-- <td></td> -->
								<td>${role.id }</td>
								<td>
									${role.rolename}
								</td>
								<td>
									${role.roledesc}
								</td>
								<td><fmt:formatDate value="${role.created }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
										<c:if test="${role.status==1 }">
											启用
										</c:if>
										<c:if test="${role.status==0 }">
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
<input type="hidden" id="roleId" value=""/>

