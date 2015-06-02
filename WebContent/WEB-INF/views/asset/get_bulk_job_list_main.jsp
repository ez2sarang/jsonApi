<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.bulkJob"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<style type="text/css" class="init">
	.zimin-left {
		float:left;
	}
	.zimin-right {
		float:right;
	}
</style>

<script language="javascript">
	var statusMap = {};
	
	$(document).ready(function () {
		// 공통코드 적재
		<c:forEach items="${statusCodeList}" var="status">
			statusMap['${status.code}'] = '${status.codeName}';
		</c:forEach>
		
		var table = $('#bulkJobDatatable').DataTable({
			"columns": [
				{ "data": "jobId" },
				{ "data": "title" },
				{ "data": "excelFileName" },
				{ "data": "cpId" },
				{ "data": "status" },
				{ "data": "jobStartTime" },
				{ "data": "jobEndTime" },
				{ "data": null }
			],
			"columnDefs": [
				{
					"render": function (data, type, row) {
						var linkStr = '<a href="javascript:" data-toggle="tooltip" data-original-title="' +
								row.jobLog + '">' + statusMap[data] +'</a>';
						return linkStr;
	                },
	                "targets": 4
				},
				{"width": "135px", "targets": [7]},
				{
					"render": function (data, type, row) {
						var btnStr = '<button type="button" class="btn btn-primary btn-xs" onClick="getBulkJobOfferList(' + row.jobId + ')"><spring:message code="bulkJob.button.detailList"/></button>';
						
						if (row.status == "${const:get('COMMONCODE', 'BULK_JOB_STATUS_SUCCESS_WORKING')}"
								|| row.status == "${const:get('COMMONCODE', 'BULK_JOB_STATUS_FAILED_WORKING')}") {
							btnStr += ' <button type="button" class="btn btn-primary btn-xs" onClick="getMediaFileMap(' + row.jobId + ')"><spring:message code="bulkJob.button.fileMap"/></button>'
						}
						
						if (row.status == "${const:get('COMMONCODE', 'BULK_JOB_STATUS_FAILED_VERIFICATION')}") {
							btnStr += ' <button type="button" class="btn btn-primary btn-xs" onClick="deleteJob(' + row.jobId + ')"><spring:message code="button.delete"/></button>'
						}
						
						return btnStr;
	                },
	                "targets": 7
				}
			],
			"searching": false,
			"sort" : false,
			"dom": '<"table-header clearfix"<"zimin-left"<"DT-per-page"l>><"DT-lf-right"<"zimin-right">>>t<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>',
			"processing": true,
			"serverSide": true,
	        "ajax": {
	            "url": "${ctxRoot}/asset/getBulkJobList.json",
	            "type": "POST",
	            "data": function ( d ) {
	                d.title = $('#bulkJobForm #title').val();
	            },
	            "beforeSend": function(jqXHR, settings) {
			  		jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
			  		try {
			  			$("#bulkJobForm #searchButton").prop("disabled", true);
						setCursor("bulkJobForm", "wait");
			  		} catch(e) {}
			    },
			    "complete": function(jqXHR, textStatus){
					try {
						$("#bulkJobForm #searchButton").prop("disabled", false);
						setCursor("bulkJobForm", "default");
						
						$('[data-toggle="tooltip"]').tooltip({
			            	trigger: 'togle', 'placement': 'top'
			    	    });
			  		} catch(e) {}
				}
	        }
		});
		
		// 첫페이지는 search parameter 없이 요청됨
		$('.zimin-right').html($('#temp_search').html());
		
		$("#bulkJobForm #searchButton").click(function() {
			if ($("#bulkJobForm #title").val().length > 0
					&& $("#bulkJobForm #title").val().length < 2) {
				openAlert('Alert!!', '<spring:message code="errors.search.moreCharacters" arguments="2"/>', function() {
				});
			} else {
				table.draw(true);
			}
		});
		
		$("#bulkJobForm #title").keypress(function(e) {
			if(e.keyCode == 13) {
				e.preventDefault();
				$("#bulkJobForm #searchButton").trigger("click");
		    }
		});
	});
	
	function getBulkJobOfferList(jobId) {
		var tUrl = "${ctxRoot}/asset/getBulkJobOfferListForm.do"
    		+ "?jobId=" + jobId;
        
		openModalWindow('01', tUrl);
	}
	
	function addBulkJob() {
		var tUrl = "${ctxRoot}/asset/addBulkJobForm.do";
		openModalWindow('01', tUrl);
	}
	
	function getMediaFileMap(jobId) {
		openConfirm('<spring:message code="bulkJob.confirm.createMediaFileMap"/>', function(result) {
			if(result) {
				try{
					var tUrl = "${ctxRoot}/asset/getMediaFileMappingList.do?jobId=" + jobId;
					location.href = tUrl;
				}catch(e) {
					alert (e);
				}
			}
		});
	}
	
	function deleteJob(jobId) {
		// <spring:message code="bulkJob.info" var="jobLabel"/>
		openConfirm('<spring:message code="confirm.delete" arguments="${jobLabel}"/>',function(result) {
			if(result) {
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/asset/deleteBulkJob/" + jobId + ".do",
						data: $('form#cmdForm').serialize(),
						success: function(data, textStatus, jqXHR){
							if (data == 'Success') {
								openInform('Success', data, function() {
									location.reload();
								});
							} else {
								openAlert('Fail', data, function() {
								});
							}
						},
						error: function(jqXHR, textStatus, errorThrown){
							openAlert('Fail', 'Failure:' + textStatus, function() {
							});
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
		<li><spring:message code="menu.serviceAsset"/></li>
		<li class="active"><spring:message code="menu.serviceAsset.bulkJob"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa  fa-picture-o page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.serviceAsset.bulkJob"/></h1>
		</div>
	</div> <!-- / .page-header -->
	
	<div class="row">
		<form:form modelAttribute="bulkJob" id="bulkJobForm" >
			<div class="col-sm-12">
			
				<div class="table-primary">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="bulkJobDatatable">
						<thead>
							<tr>
								<th>ID</th>
								<th><spring:message code="bulkJob.title" var="bulkJobNameLabel"/>${bulkJobNameLabel}</th>
								<th>Excel</th>
								<th><spring:message code="offer.cpId"/></th>
								<th><spring:message code="bulkJob.status"/></th>
								<th><spring:message code="bulkJob.jobStartTime"/></th>
								<th><spring:message code="bulkJob.jobEndTime"/></th>
								<th><spring:message code="bulkJob.button.detailList"/></th>
							</tr>
						</thead>
						
					</table>
				</div>
				
				<div align="right">
					<button type="button" class="btn btn-primary" onclick="addBulkJob();"><spring:message code="bulkJob.title.add"/></button>
				</div>
			</div>
		</form:form>
		
		<div id="temp_search" style="display:none">
			<table>
				<tr>
					<td>
						<spring:message code="button.search" var="searchLabel"/>
						<input type="text" id="title" name="title" class="form-control" placeholder="${bulkJobNameLabel} ${searchLabel}">
					</td>
					<td>
						&nbsp;
						<button type="button" class="btn btn-default" id="searchButton">${searchLabel}</button>
						&nbsp;
					</td>
				</tr>
			</table>
		</div>
		
	</div>
	
	<form name="cmdForm" id="cmdForm">
		<input type="hidden" name="jobId" value="">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

</body>
</html>