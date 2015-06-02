<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
		<!-- Main menu toggle -->
		<button type="button" id="main-menu-toggle"><i class="navbar-icon fa fa-bars icon"></i><span class="hide-menu-text">HIDE MENU</span></button>
		
		<div class="navbar-inner">
			<!-- Main navbar header -->
			<div class="navbar-header">

				<!-- Logo -->
				<a href="${ctxRoot}/index.jsp" class="navbar-brand">
					<div><img alt="Viettel TV" src="${ctxRoot}/assets/images/pixel-admin/main-navbar-logo.png"></div>
					CMS Admin
				</a> <font size="1" id="cmsVersion"><i>Ver. ${cmsVersion}</i></font>

				<!-- Main navbar toggle -->
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-navbar-collapse"><i class="navbar-icon fa fa-bars"></i></button>

			</div> <!-- / .navbar-header -->

			<div id="main-navbar-collapse" class="collapse navbar-collapse main-navbar-collapse">
				<div>
					<ul class="nav navbar-nav">
						
					</ul> <!-- / .navbar-nav -->

					<div class="right clearfix">
						<ul class="nav navbar-nav pull-right right-navbar-nav">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="language" /></a>
								<ul class="dropdown-menu">
									<li><a href="javascript:setLocale('en');">English</a></li>
									<li class="divider"></li>
									<li><a href="javascript:setLocale('ko');">Korean</a></li>
									<li class="divider"></li>
									<li><a href="javascript:setLocale('vi');">Vietnamese</a></li>
								</ul>
							</li>
							<script type="text/javascript">
							    function setLocale(locale) {
							    	try{
										$.ajax({
											type: "GET",
											url: "${ctxRoot}/setLocale.do?locale=" + locale,
											success: function(data, textStatus, jqXHR){
												if (data == 'Success') {
													location.reload();
												} else {
													openAlert('Fail', data, function() {
													});
												}
											},
											error: function(jqXHR, textStatus, errorThrown){
												openAlert('Fail', 'Failure:' + textStatus);
											}
										});
									}catch(e) {
										alert (e);
									}
							    }
							</script>
							
							<li class="dropdown">
								<a href="#" class="dropdown-toggle user-menu" data-toggle="dropdown">
									<img src="${ctxRoot}/assets/images/pixel-admin/avatar.png" alt="">
									<span><sec:authentication property="principal.userDisplayName"/></span>
								</a>
								<c:url var="accountFormUrl" value="/mymenu/editAccountForm.do" />
								<c:url var="passwordFormUrl" value="/mymenu/editMyPasswordForm.do" />
								<ul class="dropdown-menu">
									<li><a href="javascript:openModalWindow('01', '${accountFormUrl}');"><i class="dropdown-icon fa fa-edit"></i>&nbsp;&nbsp;<spring:message code="menu.userInfo.account" /></a></li>
									<li><a href="javascript:openModalWindow('01', '${passwordFormUrl}');"><i class="dropdown-icon fa fa-edit"></i>&nbsp;&nbsp;<spring:message code="menu.userInfo.password" /></a></li>
									<li class="divider"></li>
									<li><a href="javascript:logoutForm.submit();"><i class="dropdown-icon fa fa-power-off"></i>&nbsp;&nbsp;<spring:message code="menu.userInfo.logout" /></a></li>
								</ul>
								<form name="logoutForm" action="${ctxRoot}/logout" method="post">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								</form>
							</li>
						</ul> <!-- / .navbar-nav -->
					</div> <!-- / .right -->
				</div>
			</div> <!-- / #main-navbar-collapse -->
		</div> <!-- / .navbar-inner -->
