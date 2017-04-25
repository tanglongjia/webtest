<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div class="panel">
	<div class="panel-body">
		<div
			class="text-left bk-bg-white bk-padding-top-40 bk-padding-bottom-10">
			<div class="row">
				<div
					class="col-lg-12 col-md-12 col-sm-12 col-xs-12 bk-vcenter text-center">
					<div class="bk-avatar">
						<img src="<%=basePath %>assets/img/user.png" alt=""
							class="img-circle bk-img-120 bk-border-light-gray bk-border-3x" />
					</div>
					<h4 class="bk-margin-top-10 bk-docs-font-weight-300">${bsUser.trueName }</h4>
				</div>
				<hr class="bk-margin-off" />
			</div>
		</div>
		<hr class="bk-margin-off" />
		<div class="bk-ltr bk-bg-white">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="bk-widget bk-border-off bk-webkit-fix">
						<div
							class="bk-bg-white text-center bk-padding-top-20 bk-padding-bottom-10">
							<div class="row">
								<div class="text-left bk-padding-left-10">
									<h4 class="bk-margin-off">General Information</h4>
								</div>
							</div>
						</div>
						<div class="bs-example">
							<div id="carousel-example-generic3" class="carousel bk-carousel-fade slide" data-ride="carousel">
								LoginName : ${bsUser.loginName}<br/>
								PassWord  : ${bsUser.passWord}<br/>
								Age:${bsUser.age }<br/>
								Sex:<c:if test="${bsUser.sex==1 }">
										Male
									</c:if>
									<c:if test="${bsUser.sex==2 }">
										Female
									</c:if><br/>
								CreateTime:<fmt:formatDate value="${bsUser.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr class="bk-margin-off" />
		<div class="bk-ltr bk-bg-white">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div
						class="bk-bg-white text-center bk-padding-top-20 bk-padding-bottom-10">
						<div class="row">
							<div class="text-left bk-padding-left-10">
								<h4 class="bk-margin-off">Address</h4>
							</div>
						</div>
					</div>
					<div
						class="bk-bg-white bk-bg-lighten bk-padding-top-10 bk-padding-left-20">
						<div class="pull-left bk-margin-right-10">
							<div class="bk-round bk-bg-darken bk-border-off">
								<i class="fa fa-map-marker fa-2x bk-fg-danger"></i>
							</div>
						</div>
						<p class="text-left">
							<small>${bsUser.address }</small>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="bk-ltr bk-padding-bottom-20 bk-padding-left-20">
			<div class="row">
				<div
					class="col-12-4 col-md-12 col-sm-12 col-xs-12 bk-bg-white bk-padding-top-10">
					<i class="fa fa-phone"></i> ${bsUser.telephone }
				</div>
				<div
					class="col-12-4 col-md-12 col-sm-12 col-xs-12 bk-bg-white bk-padding-top-10">
					<i class="fa fa-tablet"></i> ${bsUser.email }
				</div>
			</div>
		</div>
	</div>
</div>
