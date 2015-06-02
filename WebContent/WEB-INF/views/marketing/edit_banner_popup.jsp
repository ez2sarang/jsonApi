<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.marketing.banner" /></title>
<link href="${ctxRoot}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<script src="${ctxRoot}/js/moment.js"></script>
<script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
<script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>
<script language="JavaScript">
	$(document).ready(function() {
		$.validator.addMethod("dateFormat", function(value, element) {
			return moment(value, moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>'), true).isValid();
		}, "Please enter a date in the format <%=DateUtil.defaultDateTimeFormat %>.");

		$('#banner').validate( {
			rules: {
				bannerName: {required: true},
				bannerType: {required: true},
				linkType: {required: true},
				linkInfo: {required: true},
				licenceStartTime: {required: true, dateFormat: true},
				licenceEndTime: {required: true, dateFormat: true}
			},
            success: function(label) {
            	label.addClass("valid");
            },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("banner", "wait");
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/marketing/editBanner.do",
						data: $('form#banner').serialize(),
						success: function(data, textStatus, jqXHR){
							if (data == 'Success') {
								openInform('Success', data, function() {
									closeModalWindow('01');
									viewDetail('${banner.bannerId}');
								});
							} else {
								openAlert('Fail', data, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("banner", "default");
								});
							}
						},
						error: function(jqXHR, textStatus, errorThrown){
							openAlert('Fail', 'Failure:' + textStatus);
							$("#saveButton").prop("disabled", false);
							setCursor("banner", "default");
						}
					});
				}catch(e) {
					alert (e);
				}
            }
		});
		
		$("#banner #linkSelectBtn").click(function() {
			if (($('#banner #linkType').val() == '01')) {
				alert("01");
			} else if (($('#banner #linkType').val() == '02')) {
				var tUrl = "${ctxRoot}/category/categoryListPopup.do?popupType=categorySelect";
				openModalWindow('02', tUrl);
			} else {
				openAlert('Alert!!', '<spring:message code="banner.error.linkType.first"/>', function() {
				});
			}
		});
		
	});
</script>
</head>
<body>
	


<!-- 10. $FORM_EXAMPLE =============================================================================

				Form example
-->
	<form:form modelAttribute="banner" cssClass="panel form-horizontal" >
		<div class="panel-heading">
			<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="banner.title.edit"/></span>
		</div>
		<div class="panel-body">
			
			<div class="form-group">
				<label for="bannerName" class="col-sm-3 control-label"><spring:message code="banner.name"/></label>
				<div class="col-sm-9">
					<form:input path="bannerName" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="bannerType" class="col-sm-3 control-label"><spring:message code="banner.bannerType" /></label>
				<div class="col-sm-9">
					<form:select path="bannerType" cssClass="form-control">
						<option value=""><spring:message code="combo.select" /></option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_BANNER_TYPE')}" selected="${banner.bannerType}" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="linkType" class="col-sm-3 control-label"><spring:message code="banner.linkType" /></label>
				<div class="col-sm-9">
					<form:select path="linkType" cssClass="form-control">
						<option value=""><spring:message code="combo.select" /></option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_BANNER_LINK_TYPE')}" selected="${banner.linkType}"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="linkInfo" class="col-sm-3 control-label"><spring:message code="banner.linkInfo" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<form:input path="linkInfo" cssClass="form-control" />
						<span class="input-group-btn">
							<button class="btn" type="button" id="linkSelectBtn" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="licenceStartTime" class="col-sm-3 control-label"><spring:message code="banner.licencePeriod" /></label>
				<div class="col-sm-9">
					<div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker8'>
		                	<spring:message code="banner.licencePeriod.start" var="startPeriodLabel" />
		                    <form:input path="licenceStartTime" cssClass="form-control" placeholder="${startPeriodLabel}"/>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
			        </div>
			        <div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker9'>
		                	<spring:message code="banner.licencePeriod.end" var="endPeriodLabel" />
		                    <form:input path="licenceEndTime" cssClass="form-control" placeholder="${endPeriodLabel}"/>
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
				<label for="description" class="col-sm-3 control-label"><spring:message code="description" /></label>
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