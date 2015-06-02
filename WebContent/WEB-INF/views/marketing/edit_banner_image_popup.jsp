<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.marketing.banner" /></title>
<link href="${ctxRoot}/css/fileinput.min.css" rel="stylesheet" type="text/css">
<script src="${ctxRoot}/js/fileinput.min.js"></script>
<script language="JavaScript">
	$(document).ready(function() {
		$('#bannerImage').validate( {
			rules: {
				newFile: {required: true}
			},
            success: function(label) {
            	label.addClass("valid");
            },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("bannerImage", "wait");
				
				try{
					var formData = new FormData(document.getElementById('bannerImage'));
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/marketing/editBannerImage.do",
						data: formData,
						cache: false,
					    contentType: false,
					    processData: false,
					    beforeSend: function(jqXHR, settings) {
					  		jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
					    },
						success: function(data, textStatus, jqXHR){
							if (data == 'Success') {
								openInform('Success', data, function() {
									closeModalWindow('01');
									viewDetail('${bannerImage.bannerId}');
								});
							} else {
								openAlert('Fail', data, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("bannerImage", "default");
								});
							}
						},
						error: function(jqXHR, textStatus, errorThrown){
							openAlert('Fail', 'Failure:' + textStatus);
							$("#saveButton").prop("disabled", false);
							setCursor("bannerImage", "default");
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
	<form:form modelAttribute="bannerImage" cssClass="panel form-horizontal" method="POST" enctype="multipart/form-data">
		<div class="panel-heading">
			<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="banner.title.edit"/></span>
		</div>
		<form:hidden path="idx" />
		<form:hidden path="bannerId" />
		<form:hidden path="localeCode" />
		<form:hidden path="originalFileName" />
		<form:hidden path="filePath" />
		<div class="panel-body">
			<div class="form-group">
				<label for="newFile" class="col-sm-2 control-label">${language}</label>
				<div class="col-sm-10">
					<input id="newFile" name="newFile" type="file" class="file" data-show-upload="false">
				</div>
			</div>
			
			<div class="form-group" style="margin-bottom: 0;">
				<div class="col-sm-offset-2 col-sm-10" align="right">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
					<button type="submit" class="btn btn-primary" id="saveButton"><spring:message code='button.save'/></button>
				</div>
			</div> <!-- / .form-group -->
			
		</div>
	</form:form>
<!-- /10. $FORM_EXAMPLE -->
	

</body>
</html>