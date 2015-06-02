<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>

<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>

<html>
<head>
	<title><spring:message code="menu.serviceAsset.registrationHistory"/></title>
	<link href="${ctxRoot}/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
</head>

<script>
	$(window).load(function(){
		
	});

	$(document).ready(function(){

// 		var scServiceType = "${scServiceType}";
// 		if(scServiceType == "")
// 		{
// 			$('#scServiceId').val("");
// 			$('#scServiceId').prop("readonly", true );
// 		}

		$('#bs-datepicker-range').datepicker({
		    format: 'yyyy-mm-dd',
		    autoclose: true
		})
		
		var ajaxUrl = "${ctxRoot}/asset/getRegistrationHistoryList.do";

// 		var domString = '<"table-header clearfix"<"table-caption"><"DT-lf-right"<"DT-per-page"l>>r>t';		// header and table body
		var domString = '<"table-header clearfix"<"table-caption"><"DT-lf-right">r>t';		// header and table body
// 		domString = domString + '<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>';					// footer
		domString = domString + '<"table-footer clearfix"<"DT-label"i>>';					// footer

		var varRegistrationHistoryTable = $('#registrationHistoryTable').DataTable( {
			"paging": false,
			"processing": true,
			"serverSide": true,
			"ajax": {
				"url": ajaxUrl,
				"data": function (d)
				{
					return $.extend( {}, d, {
						"scStartDate": $("#scStartDate").val(),
						"scEndDate": $("#scEndDate").val()})}
			},
			"columns": [
						{ "data": "cp", "name":'cp', "orderable": true, "visible":true},
						{ "data": "offerCount", "name":'offerCount', "orderable": true, "width":"12%", "className":"dt-body-right" },
						{ "data": "cgCount", "name":'cgCount', "orderable": true, "width":"12%", "className":"dt-body-right" },
						{ "data": "movieCount", "name":'movieCount', "orderable": true, "width":"12%", "className":"dt-body-right" },
						{ "data": "previewCount", "name":'previewCount', "orderable": true, "width":"12%", "className":"dt-body-right" },
						{ "data": "posterCount", "name":'posterCount', "orderable": true, "width":"12%", "className":"dt-body-right" },
						{ "data": "thumbnailCount", "name":'thumbnailCount', "orderable": true, "width":"12%", "className":"dt-body-right" }
	                ],
	    	"dom": domString
		} );
		$('#registrationHistoryTable_wrapper .table-caption').text('<spring:message code="menu.serviceAsset.registrationHistory"/> <spring:message code="search.result"/>');

		$("#buttonSearch").click(function() {
			varRegistrationHistoryTable.draw(true);
		});

		$("#buttonMakeExcel").click(function() {
// 			var tUrl = "${ctxRoot}/asset/getRegistrationHistoryListExcel.do?";
// 			tUrl = tUrl + "&scStartDate=" + "<c:out value="${scStartDate}"/>";
// 			tUrl = tUrl + "&scEndDate=" + "<c:out value="${scEndDate}"/>";
			
			var tUrl = "${ctxRoot}/asset/getRegistrationHistoryListExcel.do?";
			tUrl = tUrl + "&scStartDate=" + $("#scStartDate").val();
			tUrl = tUrl + "&scEndDate=" + $("#scEndDate").val();

			location.href = tUrl;
		});
	});

</script>


<!-- / Javascript -->

<body>
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.serviceAsset"/></li>
		<li><spring:message code="menu.serviceAsset.registrationHistory"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-cloud-download page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.serviceAsset.registrationHistory"/></h1>
		</div>
	</div> <!-- / .page-header -->
	
	<div id="listView">
		<div class="panel form-horizontal">
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-1">
						<label class="control-label" ><spring:message code="search.condition"/></label>
					</div>
					<form id="serviceDeployHistoryListSearchform" name="serviceDeployHistoryListSearchform">
<!-- 						<div class="col-xs-2"> -->
<!-- 							<select id="scServiceType" name="scServiceType" class="form-control form-group-margin"> -->
<%-- 								<option value=""><spring:message code="service.deploy.history.type"/></option> --%>
<%-- 								<c:forEach var="serviceTypeItem" items="${serviceTypeItemList}" varStatus="status"> --%>
<%-- 									<option value="<c:out value="${serviceTypeItem.code}" />" <c:if test="${serviceTypeItem.code == scServiceType}">selected</c:if>><c:out value="${serviceTypeItem.codeName}"/></option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select>		 -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-2"> -->
<%-- 							<input id="scServiceId" name="scServiceId" class="input-sm form-control" type="text" placeholder="<spring:message code="service.deploy.history.id"/>" value="${scServiceId}"> --%>
<!-- 						</div> -->
						<div class="col-xs-4">
							<div class="input-daterange input-group" id="bs-datepicker-range">
								<input id="scStartDate" name="scStartDate" class="input-sm form-control" type="text" placeholder="<spring:message code="registration.history.revision.date"/>(From)" value="${scStartDate}">
								<span class="input-group-addon">-</span>
								<input id="scEndDate" name="scEndDate" class="input-sm form-control" type="text" placeholder="<spring:message code="registration.history.revision.date"/>(To)" value="${scEndDate}">
							</div>
						</div>
					</form>
					<div class="col-xs-3">
						<button class="btn btn-primary" id="buttonSearch"><spring:message code="button.search"/></button>
						<button class="btn btn-primary" id="buttonMakeExcel"><spring:message code="button.make.excel"/></button>
					</div>
				</div>
			</div>
			
			<div class="panel-body">
				<div class="table-primary">
					<table id="registrationHistoryTable" width="100%" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="registration.history.cp.name"/>(<spring:message code="registration.history.cp.id"/>)</th>
								<th><spring:message code="registration.history.offer"/></th>
								<th><spring:message code="registration.history.content.group"/></th>
								<th><spring:message code="registration.history.movie.asset"/></th>
								<th><spring:message code="registration.history.preview.asset"/></th>
								<th><spring:message code="registration.history.poster.asset"/></th>
								<th><spring:message code="registration.history.thumbnail.asset"/></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="detailView" style="display:none">
	</div>
	
<!-- 	<div id="excelView" style="display:none"> -->
<!-- 	</div> -->
	
</body>
</html>
