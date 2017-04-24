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
				<table class="table-container table table-hover table-striped" id="resTable">
					<thead>
						<tr>
							<!-- <th data-checkbox="true"></th> -->
							<th>序号</th>
							<th>菜单名称</th>
							<th>菜单图标</th>
							<th>菜单代码</th>
							<th>访问路径</th>
							<th>菜单等级</th>
							<th>叶子节点</th>
							<th>创建时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="res" items="${page.resultList }">
							<tr>
								<!-- <td></td> -->
								<td>${res.id }</td>
								<td>
									${res.menuName}
								</td>
								<td>
								<i class="glyphicon  ${res.menuImgPath }">
								</td>
								<td>
									${res.menuCode}
								</td>
								<td>${res.menuUrl }</td>
								<td>${res.menuLevel }级</td>
								<td>
									<c:if test="${res.isLeafNode==1 }">
											是
										</c:if>
										<c:if test="${res.isLeafNode==0 }">
											否
										</c:if>
								</td>
								<td><fmt:formatDate value="${res.created }" pattern="yyyy-MM-dd"/></td>
								<td>
										<c:if test="${res.status==1 }">
											启用
										</c:if>
										<c:if test="${res.status==0 }">
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
<input type="hidden" id="resId" value=""/>

