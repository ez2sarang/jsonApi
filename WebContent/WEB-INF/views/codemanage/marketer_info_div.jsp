<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<script>
	$(document).ready(function(){
		
		$("#currentMarketer").validate({
			focusInvalid: false,
			rules: {
				'marketerName': {
					required: true
				}
			},
			messages: {
				'jq-validation-policy': 'You must check it!'
			}
		});
		
		$("#updateMarketerButton").click(function() {
			
			openConfirm('<spring:message code="confirm.save" arguments="${currentMarketer.marketerId}"/>',function(result) {
				if(result)
				{
					if(!$('#currentMarketer').valid())
					{return;}

					$("#updateMarketerButton").prop("disabled", true);
					$("#deleteMarketerButton").prop("disabled", true);

//		 			$("#regIdx").prop("disabled", false);
//		 			$("#providerId").prop("disabled", false);

//		 			alert("<c:out value="${currentProviderId.createId}"/>");
// 					alert($('#currentMarketer').serialize());

					try{
						$.ajax({
							type: "POST",
							url: "${ctxRoot}/codemanage/updateMarketer.do",
							data: $('#currentMarketer').serialize(),
//		 					data: $('#providerIdInfoForm').serialize(),
							success: function(result){
								if (result.resultCode == '0') {
									openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
										$("#updateMarketerButton").prop("disabled", false);
										$("#deleteMarketerButton").prop("disabled", false);
										redrawDataTable(result.regIdx);

//		 								$("#providerIdDetailDiv").show();
										
//		 								var tUrl = "${ctxRoot}/codemanage/getAddProviderIdForm.do";
//		 								$('#providerIdDetailDiv').load(tUrl);
																		
//		 								var nextUrl = "${ctxRoot}/subscriber/getSubscriberId.do?subscriberIdRegIdx=" + result.subscriberIdRedIdx;
//		 								$("#addSubscriberIdForm").attr("action",nextUrl);
//		 								$("#addSubscriberIdForm").submit();
									});

								} else {
									openAlert('<spring:message code="open.alert.fail"/>', result.msg, function() {
										$("#updateMarketerButton").prop("disabled", false);
										$("#deleteMarketerButton").prop("disabled", false);
									});
								}
							},
							error: function(){
								openAlert('<spring:message code="open.alert.fail"/>', 'Failure');
								$("#updateMarketerButton").prop("disabled", false);
								$("#deleteMarketerButton").prop("disabled", false);
							}
						});
					}catch(e) {
						alert (e);
					}
				}
			});
			
		});

		$("#deleteMarketerButton").click(function() {
			openConfirm('<spring:message code="confirm.delete" arguments="${currentMarketer.marketerId}"/>',function(result) {
				if(result)
				{
					if(!$('#currentMarketer').valid())
					{return;}

					$("#updateMarketerButton").prop("disabled", true);
					$("#deleteMarketerButton").prop("disabled", true);

//		 			$("#regIdx").prop("disabled", false);
//		 			$("#providerId").prop("disabled", false);

//		 			alert("<c:out value="${currentProviderId.createId}"/>");
// 					alert($('#currentMarketer').serialize());

					try{
						$.ajax({
							type: "POST",
							url: "${ctxRoot}/codemanage/deleteMarketer.do",
							data: $('#currentMarketer').serialize(),
//		 					data: $('#providerIdInfoForm').serialize(),
							success: function(result){
								if (result.resultCode == '0') {
									openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
										$("#updateMarketerButton").prop("disabled", false);
										$("#deleteMarketerButton").prop("disabled", false);
										redrawDataTable(result.regIdx);
										clearSubViewDiv();

//		 								$("#providerIdDetailDiv").show();
										
//		 								var tUrl = "${ctxRoot}/codemanage/getAddProviderIdForm.do";
//		 								$('#providerIdDetailDiv').load(tUrl);
																		
//		 								var nextUrl = "${ctxRoot}/subscriber/getSubscriberId.do?subscriberIdRegIdx=" + result.subscriberIdRedIdx;
//		 								$("#addSubscriberIdForm").attr("action",nextUrl);
//		 								$("#addSubscriberIdForm").submit();
									});

								} else {
									openAlert('<spring:message code="open.alert.fail"/>', result.msg, function() {
										$("#updateMarketerButton").prop("disabled", false);
										$("#deleteMarketerButton").prop("disabled", false);
									});
								}
							},
							error: function(){
								openAlert('<spring:message code="open.alert.fail"/>', 'Failure');
								$("#updateMarketerButton").prop("disabled", false);
								$("#deleteMarketerButton").prop("disabled", false);
							}
						});
					}catch(e) {
						alert (e);
					}
				}
			});
		});
	});
</script>
			<div class="panel colourable">
				<div class="panel-heading">
					<span class="panel-title"><i class="panel-title-icon fa fa-align-justify"></i><spring:message code="marketer.add"/></span>
				</div>
				<div class="panel-body" style="height:450px; overflow:auto;">
					<div class="form-group col-sm-12" align="right">
<%-- 						<button class="btn btn-primary" id="updateMarketerButton"><spring:message code="button.save"/></button> --%>
						<button class="btn btn-primary" id="updateMarketerButton"><spring:message code="button.save"/></button>&nbsp&nbsp
						<button class="btn btn-primary" id="deleteMarketerButton"><spring:message code="button.delete"/></button>
					</div>
					<form:form modelAttribute="currentMarketer" name="MarketerInfoForm">
<%-- 						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
						<div class="form-group">
							<label for="regIdx" class="col-sm-3 control-label"><spring:message code="reg.idx"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="regIdx" readonly="true" />
							</div>
						</div>
						<div class="form-group">
							<label for="marketerId" class="col-sm-3 control-label"><spring:message code="marketer.id"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="marketerId" readonly="true" />
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
<!-- 						<div class="form-group"> -->
<%-- 							<label for="useYn" class="col-sm-3 control-label"><spring:message code="provider.id.useYn"/></label> --%>
<!-- 							<div class="col-sm-9"> -->
<%-- 								<form:select class="form-control" path="useYn"> --%>
<%-- 									<option value="Y" <c:if test="${currentProviderId.useYn == 'Y'}">selected</c:if>>Y</option> --%>
<%-- 									<option value="N" <c:if test="${currentProviderId.useYn == 'N'}">selected</c:if>>N</option> --%>
<%-- 								</form:select> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="form-group"> -->
<%-- 							<label for="updateId" class="col-sm-3 control-label"><spring:message code="updater"/></label> --%>
<!-- 							<div class="col-sm-9"> -->
<%-- 								<form:input class="form-control" path="updateId" readonly="true"/> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="form-group"> -->
<%-- 							<label for="updateTime" class="col-sm-3 control-label"><spring:message code="updateTime"/></label> --%>
<!-- 							<div class="col-sm-9"> -->
<%-- 								<form:input class="form-control" path="updateTime" readonly="true" /> --%>
<!-- 							</div> -->
<!-- 						</div> -->
						<form:hidden path="createId"/>
						<form:hidden path="createTime"/>
						<form:hidden path="useYn"/>
					</form:form>
				</div>
			</div>	