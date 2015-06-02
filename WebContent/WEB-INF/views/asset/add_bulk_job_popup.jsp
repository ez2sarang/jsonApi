<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.bulkJob" /></title>
<link href="${ctxRoot}/css/fileinput.min.css" rel="stylesheet" type="text/css">
<script src="${ctxRoot}/js/fileinput.min.js"></script>
<script language="JavaScript">
	$(document).ready(function() {
		//$.validator.addMethod("checkExtention", function(value, element) {
		//	var excelFile = $("#excelFile").val();
		//	if (excelFile.match("xls$") || excelFile.match("xlsx$")) {
		//		return true;
		//	}
		//	return false;
		//}, "Please select valid Excel file(.xls, .xlsx)");
		
		$('#bulkJob').validate( {
			rules: {
				title: {required: true},
				cpId: {required: true},
				fileDirPath: {required: true}
			},
            success: function(label) {
            	label.addClass("valid");
            },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("bulkJob", "wait");
				
				try{
					var formData = new FormData(document.getElementById('bulkJob'));
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/asset/addBulkJob.do",
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
									location.reload();
								});
							} else {
								openAlert('Fail', data, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("bulkJob", "default");
								});
							}
						},
						error: function(jqXHR, textStatus, errorThrown){
							openAlert('Fail', 'Failure:' + textStatus);
							$("#saveButton").prop("disabled", false);
							setCursor("bulkJob", "default");
						}
					});
				}catch(e) {
					alert (e);
				}
            }
		});
		
		$("#bulkJob #fileSelectBtn").click(function() {
			if ($("#cpId").val() == "") {
				openAlert('Alert!!', '<spring:message code="bulkJob.error.cpId.first"/>', function() {
				});
			} else {
				var tUrl = "${ctxRoot}/asset/getExcelFileList.do?cpId=" + $("#cpId").val();
				openModalWindow('02', tUrl);
			}
		});
		
		$("#fileDirPath").select2();
	});
	
	function setExcelFile(fileName, filePath) {
		$('#fileDirPath')
				.find('option')
				.remove()
				.end()
				.append($('<option></option>')
						.attr('value', filePath)
						.attr('selected', 'selected')
						.text(fileName)
				).trigger('change');
		
		closeModalWindow('02');
	}
</script>
</head>
<body>

	<form:form modelAttribute="bulkJob" method="POST"
		cssClass="panel form-horizontal" enctype="multipart/form-data" >
		<div class="panel-heading">
			<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="bulkJob.title.add"/></span>
		</div>
		<div class="panel-body">
			
			<div class="form-group">
				<label for="title" class="col-sm-3 control-label"><spring:message code="bulkJob.title" /></label>
				<div class="col-sm-9">
					<form:input path="title" cssClass="form-control" />
					<sec:authorize access="hasRole('ROLE_USER')">
						<sec:authentication property="principal.cpId" var="cpId"/>
                        <input type="hidden" id="cpId" name="cpId" value="${cpId}"/>
					</sec:authorize>
				</div>
			</div>
			<sec:authorize access="!hasRole('ROLE_USER')">
				<div class="form-group">
					<label for="cpId" class="col-sm-3 control-label"><spring:message code="bulkJob.cpId" /></label>
					<div class="col-sm-9">
						<form:select path="cpId" cssClass="form-control">
							<option value=""><spring:message code="combo.select" /></option>
							<form:options items="${contentProviderSelectItems}" itemValue="cpId" itemLabel="cpName"/>
						</form:select>
					</div>
				</div>
			</sec:authorize>
			<div class="form-group">
				<label for="excelFile" class="col-sm-3 control-label"><spring:message code="bulkJob.excelFile" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="fileDirPath" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="fileSelectBtn" ><spring:message code="button.select" /></button>
						</span>
					</div>
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
	
</body>
</html>