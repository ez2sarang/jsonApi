<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.system.commoncode" /></title>
<script language="JavaScript">	
	jQuery(document).ready(function(e){
		$('.widget-tasks .panel-body').pixelTasks().sortable({
			axis: "y",
			handle: ".task-sort-icon",
			stop: function( event, ui ) {
				// IE doesn't register the blur when sorting
				// so trigger focusout handlers to remove .ui-state-focus
				ui.item.children( ".task-sort-icon" ).triggerHandler( "focusout" );
			}
		});
		
		$('#listForm').validate( {
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("listForm", "wait");
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/system/reorderCommonCode.do",
						data: $('form#listForm').serialize(),
						success: function(msg){
							if (msg == 'Success') {
								openInform('Success', msg, function() {
									closeModalWindow('01');
									goDetailView('${groupCode}');
								});
							} else {
								openAlert('Fail', msg, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("listForm", "default");
								});
							}
						},
						error: function(){
							openAlert('Fail', 'Failure');
							$("#saveButton").prop("disabled", false);
							setCursor("listForm", "default");
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
<form name="listForm" id="listForm" class="panel form-horizontal">
	<input type="hidden" name="groupCode" value="${groupCode}">
	
	<div class="panel widget-tasks" style="margin-bottom: 0;">
		<div class="panel-heading">
			<span class="panel-title"><i class="panel-title-icon fa fa-tasks"></i><spring:message code='button.ordering'/></span>
		</div> <!-- / .panel-heading -->
		<div class="panel-body">
			<c:forEach items="${commonCodeList}" var="commonCode">
				<div class="task">
					<div class="fa fa-arrows-v task-sort-icon"></div>
					<c:out value="${commonCode.codeName}" />
					<input type="hidden" name="code" value="${commonCode.code}">
				</div> <!-- / .task -->
			</c:forEach>
		</div> <!-- / .panel-body -->
		<div class="panel-footer clearfix">
			<div class="pull-right">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
				<button type="submit" class="btn btn-primary" id="saveButton"><spring:message code='button.save'/></button>
			</div>
		</div> <!-- / .panel-body -->
	</div> <!-- / .panel -->
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>