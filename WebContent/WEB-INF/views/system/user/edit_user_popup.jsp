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
				email: {email: true},
				state: {required: true}
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
						url: "${ctxRoot}/system/editUser.do",
						data: $('form#user').serialize(),
						success: function(msg){
							if (msg == 'Success') {
								openInform('Success', msg, function() {
									//closeModalWindow('01');
									location.reload();
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
					<form:input path="userName" cssClass="form-control" />
				</div>
			</div>
			<c:if test="${user.userType == '2'}">
				<div class="form-group">
					<label for="cpId" class="col-sm-3 control-label"><spring:message code="user.cp.name" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" path="cpId">
							<c:forEach var="contentProviderSelectItem" items="${contentProviderSelectItems}" varStatus="status">
								<option value="<c:out value="${contentProviderSelectItem.cpId}"/>" <c:if test="${contentProviderSelectItem.cpId == user.cpId}">selected</c:if>><c:out value="${contentProviderSelectItem.cpName}"/></option>
							</c:forEach>
						</form:select>					
					</div>
				</div>
			</c:if>
<!-- 			<div class="form-group"> -->
<%-- 				<label for="cpId" class="col-sm-3 control-label"><spring:message code="user.cp.id" /></label> --%>
<!-- 				<div class="col-sm-9"> -->
<%-- 					<form:input path="cpId" cssClass="form-control" /> --%>
<!-- 				</div> -->
<!-- 			</div> -->
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
			<div class="form-group">
				<label for="state" class="col-sm-3 control-label"><spring:message code="user.state" /></label>
				<div class="col-sm-9">
					<form:select path="state" cssClass="form-control">
						<option value=""><spring:message code="combo.select" /></option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_USER_STATE')}" selected="${user.state}"/>
					</form:select>
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