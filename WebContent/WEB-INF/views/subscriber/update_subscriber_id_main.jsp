<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>

<%@page import="org.apache.commons.lang.StringUtils"%>
<html>
<head>
<title><spring:message code="subscriber.title.update.user.identification"/></title>

<%-- <script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.js' />" ></script> --%>
</head>

<script>
	$(window).load(function(){
		// init field attributes
		$("#idType").val("${subscriberId.idType}");
		$("#idType").trigger("change");
	});
	
	$(document).ready(function(){
		$("#idType").change(function() {
			if ($(this).val() == "ID") {
	            $("#stbTypeArea").hide();
				$("#idTypeArea").show();
	            $("#passwordArea").hide();
	            $("#passwordConfirmArea").hide();
	        }else{
	            $("#stbTypeArea").show();
				$("#idTypeArea").hide();
	        } 
		});

		$( "#updateSubscriberIdForm" ).submit(function( event ) {
// 			alert($("#updateSubscriberIdForm").attr("action"));
// 			event.preventDefault();
		});

		$("#saveButton").click(function() {
			$("#saveButton").prop("disabled", true);
// 			setCursor("updateSubscriberIdForm", "wait");

			try{
				$.ajax({
					type: "POST",
					url: "${ctxRoot}/subscriber/updateSubscriberId.do",
					data: $('#updateSubscriberIdForm').serialize(),
					success: function(result){
						if (result.resultCode == '0') {
							openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
								$("#saveButton").prop("disabled", false);
// 								setCursor("updateSubscriberIdForm", "default");

								var nextUrl = "${ctxRoot}/subscriber/getSubscriberId.do?subscriberIdRegIdx=" + "${subscriberId.subscriberIdRegIdx}";
								$("#updateSubscriberIdForm").attr("action",nextUrl);
								$("#updateSubscriberIdForm").submit();
							});

							
						} else {
							openAlert('<spring:message code="open.alert.fail"/>', result.msg, function() {
								$("#saveButton").prop("disabled", false);
// 								setCursor("updateSubscriberIdForm", "default");
							});
						}
					},
					error: function(){
						openAlert('<spring:message code="open.alert.fail"/>', 'Failure');
						$("#saveButton").prop("disabled", false);
// 						setCursor("updateSubscriberIdForm", "default");
					}
				});
			}catch(e) {
				alert (e);
			}
			
		});

		$("#closeButton").click(function() {
			$("#updateSubscriberIdForm").attr("action","<c:url value='/subscriber/getContentProviderListForm.do'/>");
			$("#updateSubscriberIdForm").submit();
		});

		$('#changePassword').click(function() {
	       var ischecked = $('#changePassword').is(':checked');;
	       
	       if(ischecked){
	            $("#passwordArea").show();
	            $("#passwordConfirmArea").show();
	       }else{
	            $("#passwordArea").hide();
	            $("#passwordConfirmArea").hide();
	       }
		});	
	});

		
	init.push(function () {
		// Setup validation
		$("#updateSubscriberIdForm").validate({
// 			ignore: '.ignore, .select2-input',
			focusInvalid: false,
			rules: {
				'contractId': {
					required: true
				},
				'customerId': {
					required: true
				},
				'idType': {
					required: true
				},
				'subscriberId': {
					required: true
				},
				'newPassword': {
					required: true,
					minlength: 6,
					maxlength: 20
				},
				'passwordConfirm': {
					required: true,
					minlength: 6,
					equalTo: "#newPassword"
				},
				'smartCardId': {
					required: true
				},
				'stbModel': {
					required: true
				},
				'stbType': {
					required: true
				},
				'menuGroup': {
					required: true
				}
			},
			messages: {
				'jq-validation-policy': 'You must check it!'
			}
		});
		
	});
	
</script>

<!-- / Javascript -->

<body>
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.subscriber"/></li>
		<li><spring:message code="menu.subscriber.identification"/></li>
		<li class="active"><spring:message code="subscriber.title.update.user.identification"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-key page-header-icon"></i>&nbsp;&nbsp;<spring:message code="subscriber.title.update.user.identification"/></h1>
		</div>
	</div> <!-- / .page-header -->

	<div class="panel form-horizontal">
<%-- 	<form:form modelAttribute="subscriberId" name="updateSubscriberIdForm" id="updateSubscriberIdForm"> --%>
	<form:form commandName="subscriberId" name="updateSubscriberIdForm" id="updateSubscriberIdForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		
		<input type="hidden" id="scContractId", name="scContractId" value="${scContractId}" />
		<input type="hidden" id="scSubscriberId", name="scSubscriberId" value="${scSubscriberId}" />
		<input type="hidden" id="scIdType", name="scIdType" value="${scIdType}" />
		
		<form:hidden path="subscriberIdRegIdx" value="${subscriberId.subscriberIdRegIdx}" />
		
		<div class="panel-body">
			<div class="form-group">
				<label for="contractId" class="col-sm-3 control-label"><spring:message code="subscriber.contract.id"/></label>
				<div class="col-sm-9">
					<input class="form-control" value="${subscriberId.contractId}" disabled=""/>
					<form:hidden path="contractId" />
				</div>
			</div>
<!-- 			<div class="form-group"> -->
<%-- 				<label for="customerId" class="col-sm-3 control-label"><spring:message code="subscriber.cust.id"/></label> --%>
<!-- 				<div class="col-sm-9"> -->
<%-- 					<input type="text" class="form-control" id="customerId" name="customerId" placeholder="<spring:message code="subscriber.cust.id"/>"> --%>
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="form-group">
				<label for="idType" class="col-sm-3 control-label"><spring:message code="subscriber.id.type"/></label>
				<div class="col-sm-9">
					<c:choose>
						<c:when test="${subscriberId.idType == 'ID'}">
							<input class="form-control" value="ID" disabled=""/>
						</c:when>
						<c:otherwise>
							<input class="form-control" value="STB" disabled=""/>
						</c:otherwise>
					</c:choose>
					<form:hidden path="idType" />
<%-- 					<form:select class="form-control" path="idType" onChange="changeInputForm()" > --%>
<!-- 						<option value="STB">STB</option> -->
<!-- 						<option value="ID">ID</option> -->
<%-- 					</form:select> --%>
				</div>
			</div>
			<div class="form-group">
				<label for="subscriberId" class="col-sm-3 control-label"><spring:message code="subscriber.subscriber.id"/></label>
				<div class="col-sm-9">
					<c:choose>
						<c:when test="${subscriberId.idType == 'ID'}">
							<input class="form-control" value="${subscriberId.subscriberId}" disabled=""/>
							<form:hidden  path="subscriberId" />
						</c:when>
						<c:otherwise>
							<form:input type="text" class="form-control" path="subscriberId" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div id=stbTypeArea>
			<div class="panel-body">
				<div id="smartCardIdArea" class="form-group">
					<label for="smartCardId" class="col-sm-3 control-label"><spring:message code="subscriber.smartcard.id"/></label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" path="smartCardId" />
					</div>
				</div>
				<div id="stbModelArea" class="form-group">
					<label for="stbModel" class="col-sm-3 control-label"><spring:message code="subscriber.stb.model"/></label>
					<div class="col-sm-9">
						<form:input type="text" class="form-control" path="stbModel" />
					</div>
				</div>
				<div id="stbModelTypeArea" class="form-group">
					<label for="stbType" class="col-sm-3 control-label"><spring:message code="subscriber.stb.type"/></label>
					<div class="col-sm-9">
						<form:select class="form-control" path="stbType">
							<option value="HD" <c:if test="${subscriberId.stbType == 'HD'}">selected</c:if>>HD</option>
							<option value="UHD" <c:if test="${subscriberId.stbType == 'UHD'}">selected</c:if>>UHD</option>
							<option value="SMART" <c:if test="${subscriberId.stbType == 'SMART'}">selected</c:if>>SMART</option>
							<option value="SD" <c:if test="${subscriberId.stbType == 'SD'}">selected</c:if>>SD</option>
						</form:select>
					</div>
				</div>
			</div>
		</div>
		<div id=idTypeArea>
			<div class="panel-body">
<!-- 				<div class="form-group checkbox" style="margin: 0;"> -->
				<div class="form-group">
					<label for="changePassword" class="col-sm-3 control-label"></label>
					<div class="col-sm-9">
						<input type="checkbox" id="changePassword" name="changePassword" value="true"> <spring:message code="subscriber.change.password"/>
<!-- 						<label> -->
<%-- 							<input type="checkbox" id="changePassword" name="changePassword" value="true" class="px"><spring:message code="subscriber.change.password"/> --%>
<%-- 							<span class="lbl"><spring:message code="subscriber.change.password"/></span> --%>
<!-- 						</label> -->
					</div>
				</div> <!-- / .checkbox -->
				<div id="passwordArea" class="form-group">
					<label for="password" class="col-sm-3 control-label"><spring:message code="password"/></label>
					<div class="col-sm-9">
<%-- 						<form:password class="form-control" path="password" /> --%>
						<input type="password" class="form-control" id="newPassword" name="newPassword" />
						<form:hidden path="password" />
					</div>
				</div>
				<div id="passwordConfirmArea" class="form-group">
					<label for="passwordConfirm" class="col-sm-3 control-label"><spring:message code="password.confirm"/></label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" />
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label for="menuGroup" class="col-sm-3 control-label"><spring:message code="subscriber.menu.group"/></label>
				<div class="col-sm-9">
					<form:select class="form-control" path="menuGroup">
						<option value="mg01" <c:if test="${subscriberId.menuGroup == 'mg01'}">selected</c:if>>Menu Group 01</option>
						<option value="mg02" <c:if test="${subscriberId.menuGroup == 'mg02'}">selected</c:if>>Menu Group 02</option>
						<option value="mg03" <c:if test="${subscriberId.menuGroup == 'mg03'}">selected</c:if>>Menu Group 03</option>
					</form:select>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label for="useYn" class="col-sm-3 control-label"><spring:message code="useYn"/></label>
				<div class="col-sm-9">
					<form:select class="form-control" path="useYn">
						<option value="Y" <c:if test="${subscriberId.menuGroup == 'Y'}">selected</c:if>>Y</option>
						<option value="N" <c:if test="${subscriberId.menuGroup == 'N'}">selected</c:if>>N</option>
					</form:select>
				</div>
			</div>
		</div>
	</form:form>
	<div class="panel-footer text-right">
		<button id="saveButton" name="saveButton" class="btn btn-primary"><spring:message code="button.save"/></button>&nbsp;&nbsp;&nbsp;
		<button id="closeButton" name="closeButton" class="btn btn-primary"><spring:message code="button.close"/></button>
	</div>
	</div>
</body>
</html>
