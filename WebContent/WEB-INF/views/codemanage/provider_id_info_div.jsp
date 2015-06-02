<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<script>
	$(document).ready(function(){
		
		$("#currentProviderId").validate({
			focusInvalid: false,
			rules: {
				'providerIdName': {
					required: true
				},
				'adiIdPrefix':{
					required: true
				}
			},
			messages: {
				'jq-validation-policy': 'You must check it!'
			}
		});
		
		$("#updateProviderIdButton").click(function() {

			openConfirm('<spring:message code="confirm.save" arguments="${currentProviderId.providerId}"/>',function(result) {
				if(result)
				{
					if(!$('#currentProviderId').valid())
					{return;}

					$("#updateProviderIdButton").prop("disabled", true);
					$("#deleteProviderIdButton").prop("disabled", true);

					$("#regIdx").prop("disabled", false);
					$("#providerId").prop("disabled", false);

//		 			alert("<c:out value="${currentProviderId.createId}"/>");
//		 			alert($('#currentProviderId').serialize());

					try{
						$.ajax({
							type: "POST",
							url: "${ctxRoot}/codemanage/updateProviderId.do",
							data: $('#currentProviderId').serialize(),
//		 					data: $('#providerIdInfoForm').serialize(),
							success: function(result){
								if (result.resultCode == '0') {
									openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
										$("#updateProviderIdButton").prop("disabled", false);
										$("#deleteProviderIdButton").prop("disabled", false);
										redrawDataTable(result.regIdx);
										getProviderIdInfo(result.regIdx);

//		 								$("#providerIdDetailDiv").show();
										
//		 								var tUrl = "${ctxRoot}/codemanage/getAddProviderIdForm.do";
//		 								$('#providerIdDetailDiv').load(tUrl);
																		
//		 								var nextUrl = "${ctxRoot}/subscriber/getSubscriberId.do?subscriberIdRegIdx=" + result.subscriberIdRedIdx;
//		 								$("#addSubscriberIdForm").attr("action",nextUrl);
//		 								$("#addSubscriberIdForm").submit();
									});

								} else {
									openAlert('<spring:message code="open.alert.fail"/>', result.msg, function() {
										$("#updateProviderIdButton").prop("disabled", false);
										$("#deleteProviderIdButton").prop("disabled", false);
									});
								}
							},
							error: function(){
								openAlert('<spring:message code="open.alert.fail"/>', 'Failure');
								$("#updateProviderIdButton").prop("disabled", false);
								$("#deleteProviderIdButton").prop("disabled", false);
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
					<span class="panel-title"><i class="panel-title-icon fa fa-align-justify"></i><spring:message code="provider.id.add"/></span>
				</div>
				<div class="panel-body" style="height:450px; overflow:auto;">
					<div class="form-group col-sm-12" align="right">
						<button class="btn btn-primary" id="updateProviderIdButton"><spring:message code="button.save"/></button>
<%-- 						<button class="btn btn-primary" id="updateProviderIdButton"><spring:message code="button.save"/></button>&nbsp&nbsp --%>
<%-- 						<button class="btn btn-primary" id="deleteProviderIdButton"><spring:message code="button.delete"/></button> --%>
					</div>
					<form:form modelAttribute="currentProviderId" name="providerIdInfoForm">
<%-- 					<form:form modelAttribute="currentProviderId" name="providerIdInfoForm" id="providerIdInfoForm"> --%>
<%-- 					<form:form modelAttribute="currentProviderId"> --%>
<%-- 						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
						<div class="form-group">
							<label for="regIdx" class="col-sm-3 control-label"><spring:message code="reg.idx"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="regIdx" readonly="true" />
							</div>
						</div>
						<div class="form-group">
							<label for="providerId" class="col-sm-3 control-label"><spring:message code="provider.id"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="providerId" readonly="true" />
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
						<div class="form-group">
							<label for="useYn" class="col-sm-3 control-label"><spring:message code="provider.id.useYn"/></label>
							<div class="col-sm-9">
								<form:select class="form-control" path="useYn">
									<option value="Y" <c:if test="${currentProviderId.useYn == 'Y'}">selected</c:if>>Y</option>
									<option value="N" <c:if test="${currentProviderId.useYn == 'N'}">selected</c:if>>N</option>
								</form:select>
							</div>
						</div>
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
						<input type="hidden" id="prevUseYn" name="prevUseYn" value="${currentProviderId.useYn}" />
					</form:form>
				</div>
			</div>	