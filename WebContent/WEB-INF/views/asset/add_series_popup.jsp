<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.series" /></title>
<script language="JavaScript">
	$(document).ready(function() {
		$('#series').validate( {
			rules: {
				seriesName: {required: true}
			},
            success: function(label) {
            	label.addClass("valid");
            },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("series", "wait");
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/asset/addSeries.do",
						data: $('form#series').serialize(),
						success: function(msg){
							if (msg == 'Success') {
								openInform('Success', msg, function() {
									location.reload();
								});
							} else {
								openAlert('Fail', msg, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("series", "default");
								});
							}
						},
						error: function(){
							openAlert('Fail', 'Failure');
							$("#saveButton").prop("disabled", false);
							setCursor("series", "default");
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
	<form:form modelAttribute="series" name="series" method="POST"
		cssClass="panel form-horizontal">
		<div class="panel-heading">
			<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="series.title.add"/></span>
		</div>
		<div class="panel-body">
			
			<div class="form-group">
				<label for="seriesName" class="col-sm-3 control-label"><spring:message code="series.name" /></label>
				<div class="col-sm-9">
					<form:input path="seriesName" cssClass="form-control" />
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