<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>

<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>

<html>
<head>
<title><spring:message code="menu.service.deploy.status"/></title>
</head>

<script>
	$(window).load(function(){
		
	});

	$(document).ready(function(){

		$('#bs-datepicker-range').datepicker({
		    format: 'yyyy-mm-dd',
		    autoclose: true
		})
		
		var ajaxUrl = "${ctxRoot}/serviceflow/getServiceDeployStatusList.do";
		
	    var varServiceDeployStatusTable = $('#serviceDeployStatusTable').DataTable( {
	        "processing": true,
	        "serverSide": true,
// 	        "ajax": ajaxUrl,
	        "ajax": {
	        	"url": ajaxUrl,
	        	"data": function (d)
    			{
					return $.extend( {}, d, {
    					"scStage": $("#scStage").val(),
    					"scStartDate": $("#scStartDate").val(),
    					"scEndDate": $("#scEndDate").val()})}
// 	        	"data": { 
// 		        	scStage: "<c:out value="${scStage}"/>",
// 		        	scStartDate: "<c:out value="${scStartDate}"/>",
// 		        	scEndDate: "<c:out value="${scEndDate}"/>"}
		    },	        
	        "columns": [
						{ "data": "offerId", "name":'offerId', "orderable": false, "width":"10%" },
						{ "data": "offerTitle", "name":'offerTitle', "orderable": true },
						{ "data": "stage", "name":'stage', "orderable": true, "width":"15%" },
						{ "data": "createTime", "name":'createTime', "orderable": true, "width":"10%" },
						{ "data": "requestTime", "name":'requestTime', "orderable": true, "width":"10%" },
						{ "data": "approveTime", "name":'approveTime', "orderable": true, "width":"10%" },
						{ "data": "reviewer1Name", "name":'reviewer1Name', "orderable": false, "width":"15%"}
	                ]
		} );
		$('#serviceDeployStatusTable_wrapper .table-caption').text('<spring:message code="menu.service.deploy.status"/> <spring:message code="search.result"/>');
		$('#serviceDeployStatusTable_wrapper .dataTables_filter input').attr('placeholder', '<spring:message code="service.deploy.status.offer.id"/>');

		$("#buttonSearch").click(function() {
			varServiceDeployStatusTable.draw(true);
// 			$("#serviceDeployStatusListSearchform").submit();
		});

		$('#serviceDeployStatusTable tbody').on( 'click', 'tr', function () {
	        var d = varServiceDeployStatusTable.row( this ).data();
			
			$('#listView').hide();
			location.href="#detailView";
			var tUrl = "${ctxRoot}/serviceflow/getServiceRequestOfferDetail.do?offerId=" + d.offerId;
			$('#detailView').load(tUrl);
			$('#detailView').show();
		});
	});

	function goList() {
		$('#detailView').hide();
		$('#listView').show();
	}
	
</script>


<!-- / Javascript -->

<body>
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.serviceAsset"/></li>
		<li><spring:message code="menu.service.deploy.status"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-cloud-download page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.service.deploy.status"/></h1>
		</div>
	</div> <!-- / .page-header -->
	
	<div id="listView">
		<div class="panel form-horizontal">
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-1">
						<label class="control-label" ><spring:message code="search.condition"/></label>
					</div>
					<form id="serviceDeployStatusListSearchform" name="serviceDeployStatusListSearchform">
						<div class="col-xs-2">
							<select id="scStage" name="scStage" class="form-control form-group-margin">
								<option value="">ALL</option>
								<c:forEach var="stageItem" items="${stageItemList}" varStatus="status">
									<option value="<c:out value="${stageItem.code}" />" <c:if test="${stageItem.code == scStage}">selected</c:if>><c:out value="${stageItem.codeName}"/></option>
								</c:forEach>
							</select>		
						</div>
						<div class="col-xs-4">
							<div class="input-daterange input-group" id="bs-datepicker-range">
								<input id="scStartDate" name="scStartDate" class="input-sm form-control" type="text" placeholder="<spring:message code="service.deploy.approval.time"/>(From)" value="${scStartDate}">
								<span class="input-group-addon">-</span>
								<input id="scEndDate" name="scEndDate" class="input-sm form-control" type="text" placeholder="<spring:message code="service.deploy.approval.time"/>(To)" value="${scEndDate}">
							</div>
						</div>
					</form>
					<div class="col-xs-1">
						<button class="btn btn-primary" id="buttonSearch"><spring:message code="button.search"/></button>
					</div>
				</div>
			</div>
			
			<div class="panel-body">
				<div class="table-primary">
					<table id="serviceDeployStatusTable" width="100%" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="service.deploy.status.offer.id"/></th>
								<th><spring:message code="service.deploy.status.offer.title"/></th>
								<th><spring:message code="service.deploy.status.stage"/></th>
								<th><spring:message code="service.deploy.create.time"/></th>
								<th><spring:message code="service.deploy.request.time"/></th>
								<th><spring:message code="service.deploy.approval.time"/></th>
								<th><spring:message code="service.deploy.reviewer1"/></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="detailView" style="display:none">
	</div>
</body>
</html>
