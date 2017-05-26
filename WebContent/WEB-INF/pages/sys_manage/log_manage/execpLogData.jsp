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
				<table class="table-container table table-hover table-striped" id="execpLogTable">
					<thead>
						<tr>
							<th>序号</th>
							<th>日志信息</th>
							<th>操作人</th>
							<th>ip地址</th>
							<th>异常代码</th>
							<th>异常详情</th>
							<th>操作时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="log" items="${page.resultList }" varStatus="status">
							<tr>
								<td>${status.index+1 }</td>
								<td>${log.description }</td>
								<td>
									${log.operatorName}
								</td>
								<td>
									${log.requestIp}
								</td>
								<td>${log.excepCode}</td>
								<td>${log.excepDetail}</td>
								<td>${log.createDate }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="pageTotal2" value="${page.totalResult}"/>

