<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.bulkJob"/></title>
<style type="text/css" class="init">
	.zimin-left2 {
		float:left;
	}
	.zimin-right2 {
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
		
		var table = $('#bulkJobOfferDatatable').DataTable({
			"columns": [
			            { "data": "rowId" },
			            { "data": "titleBrief" },
			            { "data": "offerId"},
			            { "data": "status"},
			            { "data": "threeDOfferId"},
			            { "data": "message"}
			],
			"columnDefs": [
				{
					"render": function (data, type, row) {
						if (!row.threeDOfferId) {
							return data;
						} else {
				        	return data + ' / ' + row.threeDOfferId;
						}
				    },
				    "targets": 2
				},
				{
					"render": function (data, type, row) {
						if (data == "${const:get('COMMONCODE', 'BULK_JOB_STATUS_SUCCESS_VERIFICATION')}"
								|| data == "${const:get('COMMONCODE', 'BULK_JOB_STATUS_SUCCESS_WORKING')}") {
							return statusMap[data];
						} else {
							return statusMap[data] + ' <button type="button" class="btn btn-warning btn-flat btn-xs" data-toggle="tooltip" data-original-title="' +
									row.message + '"><spring:message code="bulkJob.button.jobLog"/></button>';
						}
	                },
	                "targets": 3
				},
				{"visible": false, "targets": [-2]},
				{"visible": false, "targets": [-1]}
			],
			"searching": false,
			"sort" : false,
			"dom": '<"table-header clearfix"<"zimin-left2"<"DT-per-page"l>><"DT-lf-right"<"zimin-right2">>>t<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>',
			"processing": true,
			"serverSide": true,
	        "ajax": {
	            "url": "${ctxRoot}/asset/getBulkJobOfferList.json",
	            "type": "POST",
	            "data": function ( d ) {
	            	d.jobId = $('#bulkJobOfferForm #jobId').val();
	                d.status = $('#bulkJobOfferForm #status').val();
	            },
	            "beforeSend": function(jqXHR, settings) {
			  		jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
			  		try {
			  			$("#bulkJobOfferForm #searchButton").prop("disabled", true);
						setCursor("bulkJobOfferForm", "wait");
			  		} catch(e) {}
			    },
			    "complete": function(jqXHR, textStatus){
					try {
						$("#bulkJobOfferForm #searchButton").prop("disabled", false);
						setCursor("bulkJobOfferForm", "default");
						
						$('[data-toggle="tooltip"]').tooltip({
			            	trigger: 'togle', 'placement': 'top'
			    	    });
			  		} catch(e) {}
				}
	        }
		});
		
		// 첫페이지는 search parameter 없이 요청됨
		$('.zimin-right2').html($('#temp_search2').html());
		
		$("#bulkJobOfferForm #searchButton").click(function() {
			table.draw(true);
		});
		
		/*
		$('#bulkJobOfferDatatable tbody').on('click', 'button', function () {
        	var tr = $(this).closest('tr');
        	openAlert('Error Messages', table.cell(tr,5).data(), function(){
        	});
		});
		*/
	});
	
</script>
</head>
<body>
	<div class="row" id="listDiv">
		<form:form modelAttribute="bulkJobOffer" id="bulkJobOfferForm" >
			<div class="col-sm-12">
				<div class="table-primary">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="bulkJobOfferDatatable">
						<thead>
							<tr>
								<th>No</th>
								<th><spring:message code="bulkJob.offer.title"/></th>
								<th><spring:message code="bulkJob.offer.id"/> / 3D</th>
								<th><spring:message code="bulkJob.status"/></th>
							</tr>
						</thead>
					</table>
					<form:hidden path="jobId"/>
				</div>
				<div align="right">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
				</div>
			</div>
		</form:form>
		<div id="temp_search2" style="display:none">
			<table>
				<tr>
					<td>
						<spring:message code="button.search" var="searchLabel"/>
						<select id="status" name="status" class="form-control">
							<option value="">-- Status --</option>
							<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_BULK_JOB_STATUS')}" />
						</select>
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