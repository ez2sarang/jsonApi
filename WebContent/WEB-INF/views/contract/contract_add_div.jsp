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

		$("#newContract").validate({
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
		
		$("#addContractButton").click(function() {
			if(!$('#newContract').valid())
			{return;}

			$("#addContractButton").prop("disabled", true);

			try{
				$.ajax({
					type: "POST",
					url: "${ctxRoot}/contract/addContract.do",
					data: $('#newContract').serialize(),
					success: function(result){
						if (result.resultCode == '0') {
							openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
								$("#addContractButton").prop("disabled", false);
								redrawDataTable(result.regIdx);
								getContractInfo(result.regIdx);
							});

						} else {
							openAlert('<spring:message code="open.alert.fail"/>', result.msg, function() {
								$("#addContractButton").prop("disabled", false);
							});
						}
					},
					error: function(){
						openAlert('<spring:message code="open.alert.fail"/>', 'Failure');
						$("#addContractButton").prop("disabled", false);
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
					<span class="panel-title"><i class="panel-title-icon fa fa-align-justify"></i><spring:message code="contract.add"/></span>
				</div>
				<div class="panel-body" style="height:450px; overflow:auto;">
					<div class="form-group col-sm-12" align="right">
						<button class="btn btn-primary" id="addContractButton"><spring:message code="button.add"/></button>
					</div>
					<form:form modelAttribute="newContract" name="newContract">
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
<!-- 						<div class="form-group  has-error"> -->
						<div class="form-group">
							<label for="contractToCpMapList" class="col-sm-3 control-label"><spring:message code="contract.cp.list"/></label>
							<div class="col-sm-9">
								<select id="contractToCpMap" name="contractToCpMap" tabindex="-1" class="form-control select2-offscreen" multiple="multiple">
									<c:forEach var="contentProviderSelectItem" items="${contentProviderSelectItems}" varStatus="status">
										<option value="<c:out value="${contentProviderSelectItem.cpId}"/>" ><c:out value="${contentProviderSelectItem.cpName}"/></option>
									</c:forEach>
<!-- 									<optgroup label="Climbing"> -->
<!-- 										<option value="pitons">Pitons</option> -->
<!-- 										<option value="cams">Cams</option> -->
<!-- 										<option value="nuts">Nuts</option> -->
<!-- 										<option value="bolts">Bolts</option> -->
<!-- 										<option value="stoppers">Stoppers</option> -->
<!-- 										<option value="sling">Sling</option> -->
<!-- 									</optgroup> -->
<!-- 									<optgroup label="Skiing"> -->
<!-- 										<option value="skis">Skis</option> -->
<!-- 										<option value="skins">Skins</option> -->
<!-- 										<option value="poles">Poles</option> -->
<!-- 									</optgroup> -->
								</select>
<!-- 								<div class="jquery-validate-error help-block" style="display: block;" for="jq-validation-select2-multi">This field is required. -->
<!-- 								</div> -->
							</div>								
						</div>
					</form:form>
				</div>
			</div>
