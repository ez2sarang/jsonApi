<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>

<%@page import="org.apache.commons.lang.StringUtils"%>
<html>
<head>
<title><spring:message code="subscriber.title.add.user.identification"/></title>

<%-- <script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.js' />" ></script> --%>
</head>

<script>
	$(window).load(function(){
		// init placeholder
		$("#contractId").attr('placeholder', '<spring:message code="subscriber.contract.id"/>');
		$("#subscriberId").attr('placeholder', '<spring:message code="subscriber.subscriber.id"/>');
		$("#smartCardId").attr('placeholder', '<spring:message code="subscriber.smartcard.id"/>');
		$("#stbModel").attr('placeholder', '<spring:message code="subscriber.stb.model"/>');
		$("#password").attr('placeholder', '<spring:message code="password"/>');
		$("#passwordConfirm").attr('placeholder', '<spring:message code="password.confirm"/>');

		// init field values
		var varIdType = "${subscriberId.idType}";
		if((varIdType == "") || (varIdType == null))
		{
			varIdType = "STB";
		}
		
		$("#idType").val(varIdType);
		$("#idType").trigger("change");

	});
	
	$(document).ready(function(){
// 		alert($( "#getSubscriberIdListForm" ).hasClass( "active" ));
// 		$("#getSubscriberIdListForm").addClass("active");
// 		alert($( "#getSubscriberIdListForm" ).hasClass( "active" ));
// 		alert(this.location.pathname);
// 		alert($("#getSubscriberIdListForm"));


		$("#idType").change(function() {
			if ($(this).val() == "ID") {
	            $("#stbTypeArea").hide();
				$("#idTypeArea").show();
	        }else{
	            $("#stbTypeArea").show();
				$("#idTypeArea").hide();
	        } 
		});

		$("#addButton").click(function() {
			if(!$('#addSubscriberIdForm').valid())
			{return;}
			
			$("#addButton").prop("disabled", true);

			try{
				$.ajax({
					type: "POST",
					url: "${ctxRoot}/subscriber/addSubscriberId.do",
					data: $('#addSubscriberIdForm').serialize(),
					success: function(result){
						if (result.resultCode == '0') {
							openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
								$("#addButton").prop("disabled", false);

								var nextUrl = "${ctxRoot}/subscriber/getSubscriberId.do?subscriberIdRegIdx=" + result.subscriberIdRedIdx;
								$("#addSubscriberIdForm").attr("action",nextUrl);
								$("#addSubscriberIdForm").submit();
							});

						} else {
							openAlert('<spring:message code="open.alert.fail"/>', result.msg, function() {
								$("#addButton").prop("disabled", false);
							});
						}
					},
					error: function(){
						openAlert('<spring:message code="open.alert.fail"/>', 'Failure');
						$("#addButton").prop("disabled", false);
					}
				});
			}catch(e) {
				alert (e);
			}
		});
	});

		
	init.push(function () {
		// Setup validation
		$("#addSubscriberIdForm").validate({
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
				'password': {
					required: true,
					minlength: 6,
					maxlength: 20
				},
				'passwordConfirm': {
					required: true,
					minlength: 6,
					equalTo: "#password"
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
		<li class="active"><spring:message code="subscriber.title.add.user.identification"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-plus-circle page-header-icon"></i>&nbsp;&nbsp;<spring:message code="subscriber.title.add.user.identification"/></h1>
		</div>
	</div> <!-- / .page-header -->

	<div class="panel form-horizontal">
	<form:form commandName="subscriberId" name="addSubscriberIdForm" id="addSubscriberIdForm">
		<form:hidden path="subscriberIdRegIdx" value="${subscriberId.subscriberIdRegIdx}" />
		<form:hidden path="useYn" value="Y" />
		<div class="panel-body">
			<div class="form-group">
				<label for="contractId" class="col-sm-3 control-label"><spring:message code="subscriber.contract.id"/></label>
				<div class="col-sm-9">
					<form:input class="form-control" path="contractId" />
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
					<form:select class="form-control" path="idType" onChange="changeInputForm()">
						<option value="STB">STB</option>
						<option value="ID">ID</option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="subscriberId" class="col-sm-3 control-label"><spring:message code="subscriber.subscriber.id"/></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" path="subscriberId" />
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
				<div id="passwordArea" class="form-group">
					<label for="password" class="col-sm-3 control-label"><spring:message code="password"/></label>
					<div class="col-sm-9">
<%-- 						<form:password class="form-control" path="password" /> --%>
						<input type="password" class="form-control" id="password" name="password" />
					</div>
				</div>
				<div id="userPasswordConfirmArea" class="form-group">
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
					<form:select class="form-control" path="menuGroup" name="menuGroup">
						<option value="mg01" <c:if test="${subscriberId.menuGroup == 'mg01'}">selected</c:if>>Menu Group 01</option>
						<option value="mg02" <c:if test="${subscriberId.menuGroup == 'mg02'}">selected</c:if>>Menu Group 02</option>
						<option value="mg03" <c:if test="${subscriberId.menuGroup == 'mg03'}">selected</c:if>>Menu Group 03</option>
					</form:select>
				</div>
			</div>
		</div>
	</form:form>
	<div class="panel-footer text-right">
<%-- 			<button id="addButton" class="btn btn-primary" onclick="addUserIdentification();"><spring:message code="button.add"/></button> --%>
		<button id="addButton" name="addButton" class="btn btn-primary"><spring:message code="button.add"/></button>
	</div>
	</div>
</body>
</html>
