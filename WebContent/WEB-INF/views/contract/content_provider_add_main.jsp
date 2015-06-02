<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>

<%@page import="org.apache.commons.lang.StringUtils"%>
<html>
<head>
<title><spring:message code="menu.content.provider.add"/></title>
</head>

<script>
	$(window).load(function(){

		$("#royaltyRangeUseFlag").val("N");
		$("#priceRangeUseFlag").val("N");

		$("#royaltyLowerRange").prop("readonly", true );
		$("#royaltyUpperRange").prop("readonly", true );

		$("#priceLowerRange").prop("readonly", true );
		$("#priceUpperRange").prop("readonly", true );
		
		// init placeholder
// 		$("#contractId").attr('placeholder', '<spring:message code="subscriber.contract.id"/>');
// 		$("#subscriberId").attr('placeholder', '<spring:message code="subscriber.subscriber.id"/>');
// 		$("#smartCardId").attr('placeholder', '<spring:message code="subscriber.smartcard.id"/>');
// 		$("#stbModel").attr('placeholder', '<spring:message code="subscriber.stb.model"/>');
// 		$("#password").attr('placeholder', '<spring:message code="password"/>');
// 		$("#passwordConfirm").attr('placeholder', '<spring:message code="password.confirm"/>');

		// init field values
// 		var varIdType = "${subscriberId.idType}";
// 		if((varIdType == "") || (varIdType == null))
// 		{
// 			varIdType = "STB";
// 		}
		
// 		$("#idType").val(varIdType);
// 		$("#idType").trigger("change");

	});
	
	$(document).ready(function(){
		$("#newContentProvider").validate({
			focusInvalid: false,
			rules: {
				'cpId': {
					required: true,
					maxlength: 30
				},
				'cpName': {
					required: true
				},
				'idDomain': {
					required: true
				},
				'royaltyCpInfo': {
					required: true
				},
				'ftpPath': {
					required: true
				},
				'royaltyLowerRange': {
					number: true,
					range: [0, 100]
				},
				'royaltyUpperRange': {
					number: true,
					range: [0, 100]
				},
				'priceLowerRange': {
					number: true
				},
				'priceUpperRange': {
					number: true
				}
			},
			messages: {
				'jq-validation-policy': 'You must check it!'
			}
		});

		$("#addButton").click(function() {
			if(!$('#newContentProvider').valid())
			{return;}
			
			$("#addButton").prop("disabled", true);

			try{
				$.ajax({
					type: "POST",
					url: "${ctxRoot}/contract/addContentProvider.do",
					data: $('#newContentProvider').serialize(),
					success: function(result){
						if (result.resultCode == '0') {
							openInform('<spring:message code="open.inform.success"/>', result.msg, function() {
								$("#addButton").prop("disabled", false);

								var nextUrl = "${ctxRoot}/contract/getContentProvider.do?regIdx=" + result.regIdx;
								$("#newContentProvider").attr("action",nextUrl);
								$("#newContentProvider").submit();
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

		$("#royaltyRangeUseFlagCheckbox").click(function(){
			if($("#royaltyRangeUseFlagCheckbox").is(":checked") == true)
			{
				$("#royaltyRangeUseFlag").val("Y");
				$("#royaltyLowerRange").prop("readonly", false );
				$("#royaltyUpperRange").prop("readonly", false );
			}
			else
			{
				$("#royaltyRangeUseFlag").val("N");
				$("#royaltyLowerRange").val(0);
				$("#royaltyLowerRange").prop("readonly", true );
				$("#royaltyUpperRange").val(0);
				$("#royaltyUpperRange").prop("readonly", true );
			}
		});

		$("#priceRangeUseFlagCheckbox").click(function(){
			if($("#priceRangeUseFlagCheckbox").is(":checked") == true)
			{
				$("#priceRangeUseFlag").val("Y");
				$("#priceLowerRange").prop("readonly", false );
				$("#priceUpperRange").prop("readonly", false );
			}
			else
			{
				$("#priceRangeUseFlag").val("N");
				$("#priceLowerRange").val(0);
				$("#priceLowerRange").prop("readonly", true );
				$("#priceUpperRange").val(0);
				$("#priceUpperRange").prop("readonly", true );
			}
		}); 

		$("#closeButton").click(function() {
			$("#closeForm").attr("action","<c:url value='/contract/getContentProviderListForm.do'/>");
			$("#closeForm").submit();
		});
		
	});

	init.push(function () {
		// Setup validation
		
	});
	
</script>

<!-- / Javascript -->

<body>
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.contract"/></li>
		<li><spring:message code="menu.content.provider"/></li>
		<li class="active"><spring:message code="menu.content.provider.add"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-plus-circle page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.content.provider.add"/></h1>
		</div>
	</div> <!-- / .page-header -->

	<div class="panel form-horizontal">
	<form:form modelAttribute="newContentProvider" >
<%-- 		<form:hidden path="regIdx" value="${newContentProvider.regIdx}" /> --%>
		<form:hidden path="useYn" value="Y" />
		<div class="panel-body">
			<div class="form-group">
				<label for="cpId" class="col-sm-3 control-label"><spring:message code="content.provider.id"/></label>
				<div class="col-sm-9">
					<form:input class="form-control" path="cpId" />
				</div>
			</div>
			<div class="form-group">
				<label for="cpName" class="col-sm-3 control-label"><spring:message code="content.provider.name"/></label>
				<div class="col-sm-9">
					<form:input class="form-control" path="cpName" />
				</div>
			</div>
			<div class="form-group">
				<label for="cpDesc" class="col-sm-3 control-label"><spring:message code="content.provider.desc"/></label>
				<div class="col-sm-9">
					<form:textarea class="form-control" path="cpDesc" rows="3" />
				</div>
			</div>
			<div class="form-group">
				<label for="idDomain" class="col-sm-3 control-label"><spring:message code="content.provider.id.domain"/></label>
				<div class="col-sm-9">
					<form:input class="form-control" path="idDomain" />
				</div>
			</div>
			<div class="form-group">
				<label for="royaltyCpInfo" class="col-sm-3 control-label"><spring:message code="content.provider.royalty.cp.info"/></label>
				<div class="col-sm-9">
					<form:input class="form-control" path="royaltyCpInfo" />
				</div>
			</div>
			<div class="form-group">
				<label for="ftpPath" class="col-sm-3 control-label"><spring:message code="content.provider.ftp.path"/></label>
				<div class="col-sm-9">
					<form:input class="form-control" path="ftpPath" />
				</div>
			</div>
<!-- 			<div class="form-group"> -->
<%-- 				<label for="royaltyRangeUseFlag" class="col-sm-3 control-label"><spring:message code="content.provider.royalty.range"/></label> --%>
<!-- 				<div class="col-sm-9"> -->
<%-- 					<form:input path="royaltyLowerRange" />% ~ <form:input path="royaltyUpperRange" />% --%>
<%-- 					&nbsp&nbsp&nbsp<input type="checkbox" id="royaltyRangeUseFlagCheckbox" /><spring:message code="content.provider.royalty.range.use"/> --%>
<%-- 					<form:hidden path="royaltyRangeUseFlag" /> --%>
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="form-group">
				<label for="royaltyRangeUseFlag" class="col-sm-3 control-label"><spring:message code="content.provider.royalty.range"/></label>
				<div class="col-sm-9">
					<label class="checkbox-inline">
						<input type="checkbox" class="px" id="royaltyRangeUseFlagCheckbox" value="Y" /><span class="lbl"><spring:message code="content.provider.royalty.range.use"/></span>
						<form:hidden path="royaltyRangeUseFlag" />
					</label>
				</div>							
			</div>
			<div class="form-group">
				<label for="royaltyLowerRange" class="col-sm-3 control-label"></label>
				<div class="col-sm-9">
					<div class="col-sm-4" style="padding-left:0;padding-right:0">
						<div class='input-group'>
							<form:input class="form-control" path="royaltyLowerRange" /><span class="input-group-addon">%</span>
<%-- 							<span class="input-group-addon"><spring:message code="currency.unit"/></span><form:input class="form-control" path="priceLowerRange" /> --%>
						</div>
	                </div>
					<div class="col-sm-1 text-center">
					~
					</div>	                
					<div class="col-sm-4" style="padding-left:0;padding-right:0">
						<div class='input-group'>
<%-- 							<span class="input-group-addon"><spring:message code="currency.unit"/></span><form:input class="form-control" path="priceUpperRange" /> --%>
							<form:input class="form-control" path="royaltyUpperRange" /><span class="input-group-addon">%</span>
						</div>
	                </div>
				</div>
			</div>
			<div class="form-group">
				<label for="priceRangeUseFlag" class="col-sm-3 control-label"><spring:message code="content.provider.price.range"/></label>
				<div class="col-sm-9">
					<label class="checkbox-inline">
						<input type="checkbox" class="px" id="priceRangeUseFlagCheckbox" value="Y" /><span class="lbl"><spring:message code="content.provider.price.range.use"/></span>
						<form:hidden path="priceRangeUseFlag" />
					</label>
				</div>							
			</div>
			<div class="form-group">
				<label for="priceLowerRange" class="col-sm-3 control-label"></label>
				<div class="col-sm-9">
					<div class="col-sm-4" style="padding-left:0;padding-right:0">
						<div class='input-group'>
<%-- 							<form:input class="form-control" path="priceLowerRange" /><span class="input-group-addon">%</span> --%>
							<span class="input-group-addon"><spring:message code="currency.unit"/></span><form:input class="form-control" path="priceLowerRange" />
						</div>
	                </div>
					<div class="col-sm-1 text-center">
					~
					</div>	                
					<div class="col-sm-4" style="padding-left:0;padding-right:0">
						<div class='input-group'>
							<span class="input-group-addon"><spring:message code="currency.unit"/></span><form:input class="form-control" path="priceUpperRange" />
<%-- 							<form:input class="form-control" path="priceUpperRange" /><span class="input-group-addon">%</span> --%>
						</div>
	                </div>
				</div>
			</div>
		</div>			
	</form:form>
	<form id="closeForm">
<%-- 		<input type="hidden" id="displayStart" name="displayStart" value="<c:out value="${displayStart}"/>" /> --%>
<%-- 		<input type="hidden" id="search" name="search" value="<c:out value="${search}"/>" /> --%>
	</form>
	<div class="panel-footer text-right">
		<button id="addButton" name="addButton" class="btn btn-primary"><spring:message code="button.add"/></button>
		<button id="closeButton" name="closeButton" class="btn btn-primary"><spring:message code="button.close"/></button>
	</div>
	</div>
</body>
</html>
