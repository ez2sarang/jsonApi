<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>

<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>

<html>
<head>
<title><spring:message code="menu.service.deploy.history"/></title>
</head>

<script>
	$(window).load(function(){
		
	});

	$(document).ready(function(){

		var scServiceType = "${scServiceType}";
		if(scServiceType == "")
		{
			$('#scServiceId').val("");
			$('#scServiceId').prop("readonly", true );
		}

		$('#bs-datepicker-range').datepicker({
		    format: 'yyyy-mm-dd',
		    autoclose: true
		})
		
		var ajaxUrl = "${ctxRoot}/serviceflow/getServiceDeployHistoryList.do";

// 		var domString = '<"table-header clearfix"<"table-caption"><"DT-lf-right"<"DT-search"f>>r>t';		// header and table body
// 		var domString = '<"table-header clearfix"<"table-caption"><"DT-lf-right"<"DT-per-page"l>>r>t';		// header and table body
		var domString = '<"table-header clearfix"<"table-caption"><"DT-lf-right"<"DT-per-page"l>>r>t';		// header and table body
		domString = domString + '<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>';					// footer
		
	    var varServiceDeployHistoryTable = $('#serviceDeployHistoryTable').DataTable( {
	        "processing": true,
	        "serverSide": true,
// 	        "ajax": ajaxUrl,
	        "ajax": {
	        	"url": ajaxUrl,
	        	"data": function (d)
    			{
					return $.extend( {}, d, {
    					"scServiceType": $("#scServiceType").val(),
    					"scServiceId": $("#scServiceId").val(),
    					"scStartDate": $("#scStartDate").val(),
    					"scEndDate": $("#scEndDate").val()})}
// 	        	"data": { 
// 		        	scServiceType: "<c:out value="${scServiceType}"/>",
// 		        	scServiceId: "<c:out value="${scServiceId}"/>",
// 		        	scStartDate: "<c:out value="${scStartDate}"/>",
// 		        	scEndDate: "<c:out value="${scEndDate}"/>"}
		    },	        
	        "columns": [
						{ "data": "idx", "name":'idx', "orderable": false, "visible":false},
						{ "data": "serviceType", "name":'serviceType', "orderable": true, "width":"10%" },
						{ "data": "serviceId", "name":'serviceId', "orderable": true },
						{ "data": "title", "name":'title', "orderable": true, "width":"15%" },
						{ "data": "createTime", "name":'createTime', "orderable": true, "width":"10%" },
						{ "data": "requestTime", "name":'requestTime', "orderable": true, "width":"10%" },
						{ "data": "approveTime", "name":'approveTime', "orderable": true, "width":"10%" },
						{ "data": "reviewer1", "name":'reviewer1', "orderable": true, "width":"15%"},
						{ "data": "deployTime", "name":'deployTime', "orderable": true, "width":"10%" }
	                ],
	    	"dom": domString
		} );
		$('#serviceDeployHistoryTable_wrapper .table-caption').text('<spring:message code="menu.service.deploy.history"/> <spring:message code="search.result"/>');
// 		$('#serviceDeployHistoryTable_wrapper .dataTables_filter input').attr('placeholder', '<spring:message code="service.deploy.status.offer.id"/>');

		$("#buttonSearch").click(function() {
			varServiceDeployHistoryTable.draw(true);
// 			$("#serviceDeployHistoryListSearchform").submit();
		});

		$("#buttonMakeExcel").click(function() {
// 			var tUrl = "${ctxRoot}/serviceflow/getServiceDeployHistoryListExcel.do?";
// 			tUrl = tUrl + "scServiceType=" + "<c:out value="${scServiceType}"/>";
// 			tUrl = tUrl + "&scServiceId=" + "<c:out value="${scServiceId}"/>";
// 			tUrl = tUrl + "&scStartDate=" + "<c:out value="${scStartDate}"/>";
// 			tUrl = tUrl + "&scEndDate=" + "<c:out value="${scEndDate}"/>";
			
			var tUrl = "${ctxRoot}/serviceflow/getServiceDeployHistoryListExcel.do?";
			tUrl = tUrl + "scServiceType=" + $("#scServiceType").val();
			tUrl = tUrl + "&scServiceId=" + $("#scServiceId").val();
			tUrl = tUrl + "&scStartDate=" + $("#scStartDate").val();
			tUrl = tUrl + "&scEndDate=" + $("#scEndDate").val();

			location.href = tUrl;
		});

		$("#scServiceType").change(function() {
			if ($(this).val() == "") {
				$('#scServiceId').val("");
				$('#scServiceId').prop("readonly", true );
	        }else{
				$('#scServiceId').prop("readonly", false );
	        } 
		});

// 		$('#serviceDeployStatusTable tbody').on( 'click', 'tr', function () {
// 	        var d = varServiceDeployStatusTable.row( this ).data();
			
// 			$('#listView').hide();
// 			location.href="#detailView";
// 			var tUrl = "${ctxRoot}/serviceflow/getServiceRequestOfferDetail.do?offerId=" + d.offerId;
// 			$('#detailView').load(tUrl);
// 			$('#detailView').show();
// 		});
	});

// 	function goList() {
// 		$('#detailView').hide();
// 		$('#listView').show();
// 	}
	
</script>


<!-- / Javascript -->

<body>
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.serviceAsset"/></li>
		<li><spring:message code="menu.service.deploy.history"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-cloud-download page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.service.deploy.history"/></h1>
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
						<div class="col-xs-2">
							<select id="scServiceType" name="scServiceType" class="form-control form-group-margin">
								<option value=""><spring:message code="service.deploy.history.type"/></option>
								<c:forEach var="serviceTypeItem" items="${serviceTypeItemList}" varStatus="status">
									<option value="<c:out value="${serviceTypeItem.code}" />" <c:if test="${serviceTypeItem.code == scServiceType}">selected</c:if>><c:out value="${serviceTypeItem.codeName}"/></option>
								</c:forEach>
							</select>		
						</div>
						<div class="col-xs-2">
							<input id="scServiceId" name="scServiceId" class="input-sm form-control" type="text" placeholder="<spring:message code="service.deploy.history.id"/>" value="${scServiceId}">
						</div>
						<div class="col-xs-4">
							<div class="input-daterange input-group" id="bs-datepicker-range">
								<input id="scStartDate" name="scStartDate" class="input-sm form-control" type="text" placeholder="<spring:message code="service.deploy.deploy.time"/>(From)" value="${scStartDate}">
								<span class="input-group-addon">-</span>
								<input id="scEndDate" name="scEndDate" class="input-sm form-control" type="text" placeholder="<spring:message code="service.deploy.deploy.time"/>(To)" value="${scEndDate}">
							</div>
						</div>
					</form>
					<div class="col-xs-3">
						<button class="btn btn-primary" id="buttonSearch"><spring:message code="button.search"/></button>
						<button class="btn btn-primary" id="buttonMakeExcel"><spring:message code="button.make.excel"/></button>
					</div>
<!-- 					<div class="col-xs-1"> -->
<%-- 						<button class="btn btn-primary" id="buttonMakeExcel"><spring:message code="button.make.excel"/></button> --%>
<!-- 					</div> -->
				</div>
			</div>
			
			<div class="panel-body">
				<div class="table-primary">
					<table id="serviceDeployHistoryTable" width="100%" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="service.deploy.history.idx"/></th>
								<th><spring:message code="service.deploy.history.type"/></th>
								<th><spring:message code="service.deploy.history.id"/></th>
								<th><spring:message code="service.deploy.history.title"/></th>
								<th><spring:message code="service.deploy.create.time"/></th>
								<th><spring:message code="service.deploy.request.time"/></th>
								<th><spring:message code="service.deploy.approval.time"/></th>
								<th><spring:message code="service.deploy.reviewer1"/></th>
								<th><spring:message code="service.deploy.deploy.time"/></th>
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
