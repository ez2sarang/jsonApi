<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<script>
	$(document).ready(function(){
		
		$("#addProviderIdForm").validate({
			focusInvalid: false,
			rules: {
				'providerId': {
					required: true
				},
				'providerIdName': {
					required: true
				},
				'adiIdPrefix': {
					required: true
				}
			},
			messages: {
				'jq-validation-policy': 'You must check it!'
			}
		});
		
		$("#addProviderIdButton").click(function() {
			if(!$('#addProviderIdForm').valid())
			{return;}

			$("#addProviderIdButton").prop("disabled", true);

			try{
				$.ajax({
					type: "POST",
					url: "${ctxRoot}/codemanage/addProviderId.do",
					data: $('#addProviderIdForm').serialize(),
					success: function(result){
						if (result.resultCode == '0') {
							openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
								$("#addProviderIdButton").prop("disabled", false);
								redrawDataTable(result.regIdx);
								getProviderIdInfo(result.regIdx);

// 								$("#providerIdDetailDiv").show();
								
// 								var tUrl = "${ctxRoot}/codemanage/getAddProviderIdForm.do";
// 								$('#providerIdDetailDiv').load(tUrl);
																
// 								var nextUrl = "${ctxRoot}/subscriber/getSubscriberId.do?subscriberIdRegIdx=" + result.subscriberIdRedIdx;
// 								$("#addSubscriberIdForm").attr("action",nextUrl);
// 								$("#addSubscriberIdForm").submit();
							});

						} else {
							openAlert('<spring:message code="open.alert.fail"/>', result.msg, function() {
								$("#addProviderIdButton").prop("disabled", false);
							});
						}
					},
					error: function(){
						openAlert('<spring:message code="open.alert.fail"/>', 'Failure');
						$("#addProviderIdButton").prop("disabled", false);
					}
				});
			}catch(e) {
				alert (e);
			}
		});
	});
</script>
			<div class="panel colourable">
				<div class="panel-heading">
					<span class="panel-title"><i class="panel-title-icon fa fa-align-justify"></i><spring:message code="provider.id.add"/></span>
				</div>
				<div class="panel-body" style="height:450px; overflow:auto;">
					<div class="form-group col-sm-12" align="right">
						<button class="btn btn-primary" id="addProviderIdButton"><spring:message code="button.save"/></button>
					</div>
					<form:form commandName="newProviderId" name="addProviderIdForm" id="addProviderIdForm">
						<div class="form-group">
							<label for="providerId" class="col-sm-3 control-label"><spring:message code="provider.id"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="providerId" />
							</div>
						</div>
						<div class="form-group">
							<label for="providerIdName" class="col-sm-3 control-label"><spring:message code="provider.id.name"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="providerIdName" />
							</div>
						</div>
						<div class="form-group">
							<label for="adiIdPrefix" class="col-sm-3 control-label"><spring:message code="provider.id.adi.id.prefix"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="adiIdPrefix" />
							</div>
						</div>
						<div class="form-group">
							<label for="providerIdDesc" class="col-sm-3 control-label"><spring:message code="provider.id.desc"/></label>
							<div class="col-sm-9">
								<form:textarea class="form-control" path="providerIdDesc" rows="3" />
							</div>
						</div>
					</form:form>
				</div>
			</div>