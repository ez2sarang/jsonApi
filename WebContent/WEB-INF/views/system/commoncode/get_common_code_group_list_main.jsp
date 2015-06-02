<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.system.commoncode"/></title>
<script language="javascript">
	function addGroupCode() {
		var tUrl = "${ctxRoot}/system/addCommonCodeForm.do";
		openModalWindow('01', tUrl);
	}
	
	function addCommonCode(groupCode) {
		var tUrl = "${ctxRoot}/system/addCommonCodeForm.do?groupCode=" + groupCode;
		openModalWindow('01', tUrl);
	}
	
	function goDetailView(groupCode) {
		var tUrl = "${ctxRoot}/system/viewCommonCode.do?groupCode=" + groupCode;
		$('#detailViewDiv').load(tUrl);
	}
	
	function editCommonCode(groupCode, code) {
		var tUrl = "${ctxRoot}/system/editCommonCodeForm.do"
    		+ "?groupCode=" + groupCode + "&code=" + code;

		openModalWindow('01', tUrl);
	}
	
	function deleteCommonCode(groupCode, code) {
		// <spring:message code="code.code" var="codeLabel"/>
		openConfirm('<spring:message code="confirm.delete" arguments="${codeLabel}"/>',function(result) {
			if(result) {
				cmdForm.groupCode.value = groupCode;
				cmdForm.code.value = code;
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/system/deleteCommonCode.do",
						data: $('form#cmdForm').serialize(),
						success: function(msg){
							if (msg == 'Success') {
								openInform('Success', msg, function() {
									goDetailView(groupCode);
								});
							} else {
								openAlert('Fail', msg, function() {
								});
							}
						},
						error: function(){
							openAlert('Fail', 'Failure');
						}
					});
				}catch(e) {
					alert (e);
				}
			}
		});
   	}
	
	function deleteCommonCodeGroup(code) {
		openConfirm('<spring:message code="code.confirm.deleteAll" />',function(result) {
			if(result) {
				cmdForm.groupCode.value = code;
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/system/deleteCommonCodeGroup.do",
						data: $('form#cmdForm').serialize(),
						success: function(msg){
							if (msg == 'Success') {
								openInform('Success', msg, function() {
									location.reload();
								});
							} else {
								openAlert('Fail', msg, function() {
								});
							}
						},
						error: function(){
							openAlert('Fail', 'Failure');
						}
					});
				}catch(e) {
					alert (e);
				}
			}
		});
   	}
	
	function reorderCommonCode(groupCode) {
		var tUrl = "${ctxRoot}/system/reorderCommonCodeForm.do"
    		+ "?groupCode=" + groupCode;

		openModalWindow('01', tUrl);
	}
	
	function applyCache(groupCode) {
		openConfirm('<spring:message code="code.confirm.applyCache" />',function(result) {
			if(result) {
				try{
					$.ajax({
						type: "GET",
						url: "${ctxRoot}/system/applyCommonCodeCache.do?groupCode=" + groupCode,
						success: function(msg){
							if (msg == 'Success') {
								openInform('Success', msg, function() {
								});
							} else {
								openAlert('Fail', msg, function() {
								});
							}
						},
						error: function(){
							openAlert('Fail', 'Failure');
						}
					});
				}catch(e) {
					alert (e);
				}
			}
		});
	}
</script>
</head>
<body>
		<ul class="breadcrumb breadcrumb-page">
			<div class="breadcrumb-label text-light-gray">You are here: </div>
			<li><spring:message code="menu.system"/></li>
			<li class="active"><spring:message code="menu.system.commoncode"/></li>
		</ul>
		<div class="page-header">
			<div class="row">
				<!-- Page header, center on small screens -->
				<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-th-list page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.system.commoncode"/></h1>
			</div>
		</div> <!-- / .page-header -->

		<div class="row">
			<div class="col-md-4">

<!-- 6. $SIMPLE_PANEL ==============================================================================

				Simple panel
-->
				<div class="panel colourable">
					<div class="panel-heading">
						<span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="code.title.codeGroupList"/></span>
					</div>
					<div class="panel-body" style="height:480px; overflow:auto;">
						<c:forEach items="${commonCodeList}" var="commonCode">
							<i class="fa fa-caret-square-o-right"></i>&nbsp;
							<a
							href="javascript:goDetailView('${commonCode.code}');"><c:out value="${commonCode.codeName}(${commonCode.code})"/></a>
							<br>
						</c:forEach>
					</div>
					<div class="panel-footer" align="right">
						<button class="btn btn-primary" onclick="addGroupCode();"><spring:message code="button.add"/></button>
					</div>
				</div>
<!-- /6. $SIMPLE_PANEL -->

			</div>
			<div class="col-md-8" id="detailViewDiv">

<!-- 8. $PANEL_TITLE_WITH_ICON =====================================================================

				Panel title with icon
-->
				<div class="panel colourable">
					<div class="panel-heading">
						<span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="code.title.codeList"/></span>
					</div>
					<div class="panel-body" style="height:480px; overflow:auto;">
						<div align="right" style="margin-bottom: 5px">
							<button class="btn btn-defaul" style="cursor:default;"><spring:message code="code.button.applyCache"/></button>
							<button class="btn btn-defaul" style="cursor:default;"><spring:message code="button.edit"/></button>
							<button class="btn btn-defaul" style="cursor:default;"><spring:message code="button.delete"/></button>
						</div>
						<table class="table table-condensed">
							<tbody>
								<tr>
									<td width="20%"><spring:message code="code.groupCodeName"/></td>
									<td width="2%">:</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><spring:message code="code.groupCode"/></td>
									<td>:</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><spring:message code="description"/></td>
									<td>:</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><spring:message code="code.classification"/></td>
									<td>:</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><spring:message code="code.ext"/>1</td>
									<td>:</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><spring:message code="code.ext"/>2</td>
									<td>:</td>
									<td>&nbsp;</td>
								</tr>
							</tbody>
						</table>
						<div class="table-primary">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>#</th>
										<th><spring:message code="code.codeName"/></th>
										<th>${codeLabel}</th>
										<th><spring:message code="useYn.y"/></th>
										<th><spring:message code="button.delete"/></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="panel-footer" align="right">
						<button class="btn btn-defaul" style="cursor:default;"><spring:message code="button.ordering"/></button>
						<button class="btn btn-defaul" style="cursor:default;"><spring:message code="button.add"/></button>
					</div>
				</div>
<!-- /8. $PANEL_TITLE_WITH_ICON -->
			</div>

<form name="cmdForm" id="cmdForm">
	<input type="hidden" name="groupCode" value="">
	<input type="hidden" name="code" value="">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>