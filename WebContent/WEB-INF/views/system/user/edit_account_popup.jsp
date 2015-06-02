<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.system.user" /></title>
<script language="JavaScript">
	$(document).ready(function() {
		$('#user').validate( {
			rules: {
				userName: {required: true},
				email: {email: true}
			},
            success: function(label) {
            	label.addClass("valid");
            },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("user", "wait");
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/mymenu/editAccount.do",
						data: $('form#user').serialize(),
						success: function(msg){
							if (msg == 'Success') {
								openInform('Success', msg, function() {
									closeModalWindow('01');
								});
							} else {
								openAlert('Fail', msg, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("user", "default");
								});
							}
						},
						error: function(){
							openAlert('Fail', 'Failure');
							$("#saveButton").prop("disabled", false);
							setCursor("user", "default");
						}
					});
				}catch(e) {
					alert (e);
				}
            }
		});
	});
</script>
</head>
<body>
	


<!-- 10. $FORM_EXAMPLE =============================================================================

				Form example
-->
	<form:form modelAttribute="user" name="user" method="POST"
		cssClass="panel form-horizontal">
		<div class="panel-heading">
			<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="user.title.userEdit"/></span>
		</div>
		<div class="panel-body">
			
			<div class="form-group">
				<label for="userId" class="col-sm-3 control-label"><spring:message code="user.id"/></label>
				<div class="col-sm-9">
					<c:out value="${user.userId}"/>
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-3 control-label"><spring:message code="user.name" /></label>
				<div class="col-sm-9">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<form:input path="userName" cssClass="form-control" />
					</sec:authorize>
					<sec:authorize access="!hasRole('ROLE_ADMIN')">
						<c:out value="${user.userName}"/>
						<form:hidden path="userName" />
					</sec:authorize>
				</div>
			</div>
			<div class="form-group">
				<label for="mobile" class="col-sm-3 control-label"><spring:message code="user.mobile" /></label>
				<div class="col-sm-9">
					<form:input path="mobile" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-3 control-label"><spring:message code="user.email" /></label>
				<div class="col-sm-9">
					<form:input path="email" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group" style="margin-bottom: 0;">
				<div class="col-sm-offset-2 col-sm-9" align="right">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
					<button type="submit" class="btn btn-primary" id="saveButton"><spring:message code='button.save'/></button>
				</div>
			</div> <!-- / .form-group -->
		</div>
	</form:form>
<!-- /10. $FORM_EXAMPLE -->
	

</body>
</html>