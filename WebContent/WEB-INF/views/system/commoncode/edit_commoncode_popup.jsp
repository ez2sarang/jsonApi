<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.system.commoncode" /></title>
<script language="JavaScript">
	$(document).ready(function() {
		$('#commonCode').validate( {
			rules: {
				codeName: {required: true}
			},
            success: function(label) {
            	label.addClass("valid");
            },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("commonCode", "wait");
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/system/editCommonCode.do",
						data: $('form#commonCode').serialize(),
						success: function(msg){
							if (msg == 'Success') {
								openInform('Success', msg, function() {
									//<c:choose><c:when test="${commonCode.groupCode == const:get('COMMONCODE', 'ROOT_GROUP_CODE')}">
									closeModalWindow('01');
									goDetailView('${commonCode.code}');
									//</c:when><c:otherwise>
									closeModalWindow('01');
									goDetailView('${commonCode.groupCode}');
									//</c:otherwise></c:choose>
								});
							} else {
								openAlert('Fail', msg, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("commonCode", "default");
								});
							}
						},
						error: function(){
							openAlert('Fail', 'Failure');
							$("#saveButton").prop("disabled", false);
							setCursor("commonCode", "default");
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
	<form:form modelAttribute="commonCode" name="commonCode" method="POST"
		cssClass="panel form-horizontal">
		<form:hidden path="fullCode"/>
		<div class="panel-heading">
			<c:choose>
				<c:when test="${commonCode.groupCode == const:get('COMMONCODE', 'ROOT_GROUP_CODE')}">
					<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="code.title.codeGroupEdit"/></span>
				</c:when>
				<c:otherwise>
					<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="code.title.codeEdit"/></span>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${commonCode.groupCode == const:get('COMMONCODE', 'ROOT_GROUP_CODE')}">
					<div class="form-group">
						<label for="code" class="col-sm-3 control-label"><spring:message code="code.groupCode"/></label>
						<div class="col-sm-9">
							<c:out value="${commonCode.code}"/>
							<form:hidden path="groupCode"/>
							<form:hidden path="code"/>
						</div>
					</div>
					<div class="form-group">
						<label for="codeName" class="col-sm-3 control-label"><spring:message code="code.groupCodeName"/></label>
						<div class="col-sm-9">
							<form:input path="codeName" cssClass="form-control" />
						</div>
					</div>
					<form:hidden path="useYn"/>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<label for="groupCode" class="col-sm-3 control-label"><spring:message code="code.groupCode"/></label>
						<div class="col-sm-9">
							<c:out value="${commonCode.groupCode}"/>/<ccode:name
							groupCode="${const:get('COMMONCODE', 'ROOT_GROUP_CODE')}"
							code="${commonCode.groupCode}" />
							<form:hidden path="groupCode"/>
						</div>
					</div>
					<div class="form-group">
						<label for="code" class="col-sm-3 control-label"><spring:message code="code.code" /></label>
						<div class="col-sm-9">
							<c:out value="${commonCode.code}"/>
							<form:hidden path="code"/>
						</div>
					</div>
					<div class="form-group">
						<label for="codeName" class="col-sm-3 control-label"><spring:message code="code.codeName" /></label>
						<div class="col-sm-9">
							<form:input path="codeName" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="useYn" class="col-sm-3 control-label"><spring:message code="useYn"/></label>
						<div class="col-sm-9">
							<div class="radio">
								<label>
									<form:radiobutton path="useYn" value="Y" cssClass="px" />
									<span class="lbl"><spring:message code="useYn.y"/></span>
								</label>
							</div>
							<div class="radio">
								<label>
									<form:radiobutton path="useYn" value="N" cssClass="px" />
									<span class="lbl"><spring:message code="useYn.n"/></span>
								</label>
							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			
			<div class="form-group">
				<label for="classification" class="col-sm-3 control-label"><spring:message code="code.classification"/></label>
				<div class="col-sm-9">
					<form:input path="classification" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="extendValue1" class="col-sm-3 control-label"><spring:message code="code.ext" var="extLabel"/>${extLabel}1</label>
				<div class="col-sm-9">
					<form:input path="extendValue1" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="extendValue2" class="col-sm-3 control-label">${extLabel}2</label>
				<div class="col-sm-9">
					<form:input path="extendValue2" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-3 control-label"><spring:message code="description"/></label>
				<div class="col-sm-9">
					<form:textarea path="description" rows="3" cssClass="form-control" />
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