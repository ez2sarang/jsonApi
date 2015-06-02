<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.system.user" /></title>
<script language="JavaScript">
	//<spring:message code="mymenu.error.newPassword.notEqual" var="newPasswordNotEq"/>
	$(document).ready(function() {
		$('#passwordForm').validate( {
			rules: {
				password: {required: true},
				newPassword: {required: true, minlength: 4},
				newPassword2: {required: true, minlength: 4, equalTo: "#newPassword"}
			},
			messages: {
				"newPassword2": {
					equalTo: "${newPasswordNotEq}",
                }
            },
            success: function(label) {
            	label.addClass("valid");
            },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("passwordForm", "wait");
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/mymenu/editMyPassword.do",
						data: $('form#passwordForm').serialize(),
						success: function(msg){
							if (msg == 'Success') {
								openInform('Success', msg, function() {
									closeModalWindow('01');
								});
							} else {
								openAlert('Fail', msg, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("passwordForm", "default");
								});
							}
						},
						error: function(){
							openAlert('Fail', 'Failure');
							$("#saveButton").prop("disabled", false);
							setCursor("passwordForm", "default");
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
	<form name="passwordForm" id="passwordForm" method="POST" class="panel form-horizontal">
		<div class="panel-heading">
			<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="user.title.passwordEdit"/></span>
		</div>
		<div class="panel-body">
			
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label"><spring:message code="user.password.old" /></label>
				<div class="col-sm-9">
					<input type="password" name="password" id="password" class="form-control" >
				</div>
			</div>
			<div class="form-group">
				<label for="newPassword" class="col-sm-3 control-label"><spring:message code="user.password.new" /></label>
				<div class="col-sm-9">
					<input type="password" name="newPassword" id="newPassword" class="form-control" placeholder='4<spring:message code="user.password.minlength" />' >
				</div>
			</div>
			<div class="form-group">
				<label for="newPassword2" class="col-sm-3 control-label"><spring:message code="user.password.re" /></label>
				<div class="col-sm-9">
					<input type="password" name="newPassword2" id="newPassword2" class="form-control" >
				</div>
			</div>
			<div class="form-group" style="margin-bottom: 0;">
				<div class="col-sm-offset-2 col-sm-9" align="right">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
					<button type="submit" class="btn btn-primary" id="saveButton"><spring:message code='button.save'/></button>
				</div>
			</div> <!-- / .form-group -->
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
<!-- /10. $FORM_EXAMPLE -->
	

</body>
</html>