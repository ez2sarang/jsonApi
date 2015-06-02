<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<script>
	$(document).ready(function(){
		// Multiselect
		$("#contractToCpMap").select2({
			placeholder: "<spring:message code="contract.cp.select"/>"
		});

		$("#contract").validate({
			focusInvalid: false,
			rules: {
				'contractName': {
					required: true
				},
				'contractToCpMap': {
					required: true
				}
			},
			messages: {
				'jq-validation-policy': 'You must check it!'
			}
		});
		
		$("#updateContractButton").click(function() {
			if(!$('#contract').valid())
			{return;}

			$("#updateContractButton").prop("disabled", true);
			$("#deleteContractButton").prop("disabled", true);

			try{
				$.ajax({
					type: "POST",
					url: "${ctxRoot}/contract/updateContract.do",
					data: $('#contract').serialize(),
					success: function(result){
						if (result.resultCode == '0') {
							openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
								$("#updateContractButton").prop("disabled", false);
								$("#deleteContractButton").prop("disabled", false);
								redrawDataTable(result.regIdx);
								getContractInfo(result.regIdx);
							});

						} else {
							openAlert('<spring:message code="open.alert.fail"/>', result.msg, function() {
								$("#updateContractButton").prop("disabled", false);
								$("#deleteContractButton").prop("disabled", false);
							});
						}
					},
					error: function(){
						openAlert('<spring:message code="open.alert.fail"/>', 'Failure');
						$("#updateContractButton").prop("disabled", false);
						$("#deleteContractButton").prop("disabled", false);
					}
				});
			}catch(e) {
				alert (e);
			}
		});

		$("#deleteContractButton").click(function() {
// 			if(!$('#contract').valid())
// 			{return;}
			openConfirm('<spring:message code="confirm.delete" arguments="${contract.contractName}"/>',function(result) {
				if(result)
				{
					$("#useYn").val("N");
					
					$("#updateContractButton").prop("disabled", true);
					$("#deleteContractButton").prop("disabled", true);

					try{
						$.ajax({
							type: "POST",
							url: "${ctxRoot}/contract/updateContract.do",
							data: $('#contract').serialize(),
							success: function(result){
								if (result.resultCode == '0') {
									openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
										$("#updateContractButton").prop("disabled", false);
										$("#deleteContractButton").prop("disabled", false);
										redrawDataTable(result.regIdx);
										clearSubViewDiv();
// 										getContractInfo(result.regIdx);
									});

								} else {
									openAlert('<spring:message code="open.alert.fail"/>', result.msg, function() {
										$("#updateContractButton").prop("disabled", false);
										$("#deleteContractButton").prop("disabled", false);
									});
								}
							},
							error: function(){
								openAlert('<spring:message code="open.alert.fail"/>', 'Failure');
								$("#updateContractButton").prop("disabled", false);
								$("#deleteContractButton").prop("disabled", false);
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
					<span class="panel-title"><i class="panel-title-icon fa fa-align-justify"></i><spring:message code="contract.update"/></span>
				</div>
				<div class="panel-body" style="height:450px; overflow:auto;">
					<div class="form-group col-sm-12" align="right">
						<button class="btn btn-primary" id="updateContractButton"><spring:message code="button.edit"/></button>
						<button class="btn btn-primary" id="deleteContractButton"><spring:message code="button.delete"/></button>
					</div>
					<form:form modelAttribute="contract" name="contract">
						<form:hidden path="regIdx" />
						<form:hidden path="contractInternalId" />
						<form:hidden path="createId" />
						<form:hidden path="createTime" />
						<form:hidden path="useYn" />
						<div class="form-group">
							<label for="contractName" class="col-sm-3 control-label"><spring:message code="contract.name"/></label>
							<div class="col-sm-9">
								<form:input class="form-control" path="contractName" />
							</div>
						</div>
						<div class="form-group">
							<label for="contractDesc" class="col-sm-3 control-label"><spring:message code="contract.desc"/></label>
							<div class="col-sm-9">
								<form:textarea class="form-control" path="contractDesc" rows="3" />
							</div>
						</div>
						<div class="form-group">
							<label for="contractToCpMapList" class="col-sm-3 control-label"><spring:message code="contract.cp.list"/></label>
							<div class="col-sm-9">
								<select id="contractToCpMap" name="contractToCpMap" tabindex="-1" class="form-control select2-offscreen" multiple="multiple">
									<c:forEach var="contentProviderSelectItem" items="${contentProviderSelectItems}" varStatus="status">
										<c:set var="selectedString" value=""/>
										<c:forEach var="contractToCpMapData" items="${contract.contractToCpMapList}" varStatus="status">
											<c:if test="${contentProviderSelectItem.cpId == contractToCpMapData.cpId}"><c:set var="selectedString" value="selected"/></c:if>
										</c:forEach>
										<option value="<c:out value="${contentProviderSelectItem.cpId}"/>" <c:out value="${selectedString}"/>><c:out value="${contentProviderSelectItem.cpName}"/></option>
									</c:forEach>
								</select>
							</div>								
						</div>
					</form:form>
				</div>
			</div>
