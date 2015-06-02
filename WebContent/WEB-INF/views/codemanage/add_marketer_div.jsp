<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<script>
	$(document).ready(function(){
		
		$("#addMarketerForm").validate({
			focusInvalid: false,
			rules: {
				'marketerId': {
					required: true
				},
				'marketerRepName': {
					required: true
				}
			},
			messages: {
				'jq-validation-policy': 'You must check it!'
			}
		});
		
		$("#addMarketerButton").click(function() {
			if(!$('#addMarketerForm').valid())
			{return;}

			$("#addMarketerButton").prop("disabled", true);

			try{
				$.ajax({
					type: "POST",
					url: "${ctxRoot}/codemanage/addMarketer.do",
					data: $('#addMarketerForm').serialize(),
					success: function(result){
						if (result.resultCode == '0') {
							openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
								$("#addMarketerButton").prop("disabled", false);
								redrawDataTable(result.regIdx);
								getMarketerInfo(result.regIdx);

// 								$("#providerIdDetailDiv").show();
								
// 								var tUrl = "${ctxRoot}/codemanage/getAddProviderIdForm.do";
// 								$('#providerIdDetailDiv').load(tUrl);
																
// 								var nextUrl = "${ctxRoot}/subscriber/getSubscriberId.do?subscriberIdRegIdx=" + result.subscriberIdRedIdx;
// 								$("#addSubscriberIdForm").attr("action",nextUrl);
// 								$("#addSubscriberIdForm").submit();
							});

						} else {
							openAlert('<spring:message code="open.alert.fail"/>', result.msg, function() {
								$("#addMarketerButton").prop("disabled", false);
							});
						}
					},
					error: function(){
						openAlert('<spring:message code="open.alert.fail"/>', 'Failure');
						$("#addMarketerButton").prop("disabled", false);
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
					<span class="panel-title"><i class="panel-title-icon fa fa-align-justify"></i><spring:message code="marketer.add"/></span>
				</div>
				<div class="panel-body" style="height:450px; overflow:auto;">
					<div class="form-group col-sm-12" align="right">
						<button class="btn btn-primary" id="addMarketerButton"><spring:message code="button.save"/></button>
					</div>
					<form:form modelAttribute="newMarketer" name="addMarketerForm" id="addMarketerForm">
						<div class="form-group">
							<label for="marketerId" class="col-sm-3 control-label"><spring:message code="marketer.id"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="marketerId" />
							</div>
						</div>
						<div class="form-group">
							<label for="marketerRepName" class="col-sm-3 control-label"><spring:message code="marketer.rep.name"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="marketerRepName" />
							</div>
						</div>
						<div class="form-group">
							<label for="marketerRepTelNo" class="col-sm-3 control-label"><spring:message code="marketer.rep.tel.no"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="marketerRepTelNo" />
							</div>
						</div>
						<div class="form-group">
							<label for="marketerRepEmail" class="col-sm-3 control-label"><spring:message code="marketer.rep.email"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="marketerRepEmail" />
							</div>
						</div>
						<div class="form-group">
							<label for="marketerDesc" class="col-sm-3 control-label"><spring:message code="marketer.desc"/></label>
							<div class="col-sm-9">
								<form:textarea class="form-control" path="marketerDesc" rows="3" />
							</div>
						</div>
					</form:form>
				</div>
			</div>