<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.contentAsset" /></title>
<link href="${ctxRoot}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link href="${ctxRoot}/css/fileinput.min.css" rel="stylesheet" type="text/css">
<script src="${ctxRoot}/js/fileinput.min.js"></script>
<script src="${ctxRoot}/js/moment.js"></script>
<script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
<script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>
<script language="JavaScript">
	$(document).ready(function() {
		$.validator.addMethod("dateFormat", function(value, element) {
			return moment(value, moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>'), true).isValid();
		}, "Please enter a date in the format <%=DateUtil.defaultDateTimeFormat %>.");
		
		$('#contentSubset').validate( {
			rules: {
				title: {required: true},
				contentSubsetType: {required: true},
				startDateTime: {required: true, dateFormat: true},
				endDateTime: {required: true, dateFormat: true},
				sourceFile: {required: true}
			},
            success: function(label) {
            	label.addClass("valid");
            },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("contentSubset", "wait");
				
				try{
					var formData = new FormData(document.getElementById('contentSubset'));
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/asset/addImageContentSubset.do",
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
									setCursor("contentSubset", "default");
								});
							}
						},
						error: function(jqXHR, textStatus, errorThrown){
							openAlert('Fail', 'Failure:' + textStatus);
							$("#saveButton").prop("disabled", false);
							setCursor("contentSubset", "default");
						}
					});
				}catch(e) {
					alert (e);
				}
            }
		});
		
		$("#contentSubset #unlimitButton").click(function() {
			if ($("#endDateTime").val() == "") {
				$('#datetimepicker9').data("DateTimePicker").setDate(new Date("December 31, 9999"));
			} else {
				$("#endDateTime").val("");
			}
		});
	});
</script>
</head>
<body>

	<form:form modelAttribute="contentSubset" method="POST"
		cssClass="panel form-horizontal" enctype="multipart/form-data" >
		<div class="panel-heading">
			<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="contentSubset.title.imageContent.add"/></span>
		</div>
		<div class="panel-body">
			
			<div class="form-group">
				<label for="title" class="col-sm-3 control-label"><spring:message code="contentSubset.title" /></label>
				<div class="col-sm-9">
					<form:input path="title" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="contentSubsetType" class="col-sm-3 control-label"><spring:message code="contentSubset.contentSubsetType" /></label>
				<div class="col-sm-9">
					<form:select path="contentSubsetType" cssClass="form-control">
						<option value=""><spring:message code="combo.select" /></option>
						<form:options items="${contentSubsetTypeList}" itemValue="code" itemLabel="codeName"/>
					</form:select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="startDateTime" class="col-sm-3 control-label"><spring:message code="contentSubset.availablePeriod" /></label>
				<div class="col-sm-9">
					<div align="right">
						<i class="fa fa-retweet" id="unlimitButton" style="cursor: pointer;"></i>&nbsp;&nbsp;&nbsp;<spring:message code="unlimited" />
					</div>
					<div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker8'>
		                	<spring:message code="contentSubset.availablePeriod.start" var="startPeriodLabel" />
		                    <form:input path="startDateTime" cssClass="form-control" placeholder="${startPeriodLabel}"/>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
			        </div>
			        <div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker9'>
		                	<spring:message code="contentSubset.availablePeriod.end" var="endPeriodLabel" />
		                    <form:input path="endDateTime" cssClass="form-control" placeholder="${endPeriodLabel}"/>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
			        </div>
				</div>
			</div>
		    <script type="text/javascript">
		        $(function () {
		            $('#datetimepicker8').datetimepicker({
		            	useSeconds: true,
		            	defaultDate: new Date(),
						format: moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>')
		            });
		            $('#datetimepicker9').datetimepicker({
		            	useSeconds: true,
						format: moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>')
		            });
		            $("#datetimepicker8").on("dp.change",function (e) {
		               $('#datetimepicker9').data("DateTimePicker").setMinDate(e.date);
		            });
		            $("#datetimepicker9").on("dp.change",function (e) {
		               $('#datetimepicker8').data("DateTimePicker").setMaxDate(e.date);
		            });
		        });
		    </script>
			
			<div class="form-group">
				<label for="sourceFile" class="col-sm-3 control-label"><spring:message code="contentSubset.fileSelect" /></label>
				<div class="col-sm-9">
					<input id="sourceFileName" name="sourceFile" type="file" class="file" data-show-upload="false">
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