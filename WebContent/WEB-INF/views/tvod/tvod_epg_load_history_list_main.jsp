<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>

<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>

<html>
<head>
<title><spring:message code="menu.interfaces"/></title>
</head>

<script>
	$(window).load(function(){
		
	});

	$(document).ready(function(){

		$('#bs-datepicker-range').datepicker({
		    format: 'yyyy-mm-dd',
		    autoclose: true
		});
		
		var ajaxUrl = "${ctxRoot}/tvod/getTvodEpgLoadHistoryList.do";

		var domString = '<"table-header clearfix"<"table-caption"><"DT-lf-right"<"DT-per-page"l>>r>t';		// header and table body
		domString = domString + '<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>';					// footer

	    var varTvodEpgLoadHistoryTable = $('#tvodEpgLoadHistoryTable').DataTable( {
	        "processing": true,
	        "serverSide": true,
	        "ajax": {
	        	"url": ajaxUrl,
	        	"data": function (d)
	        			{
        					return $.extend( {}, d, {
            					"scResult": $("#scResult").val(),
            					"scFileName": $("#scFileName").val(),
            					"scFromDate": $("#scFromDate").val(),
            					"scToDate": $("#scToDate").val()})}
		    },	        
	        "columns": [
						{ "data": "idx", "name":'idx', "orderable": false, "width":"10%" },
						{ "data": "fileName", "name":'fileName', "orderable": true },
						{ "data": "startTime", "name":'startTime', "orderable": true, "width":"20%"  },
						{ "data": "endTime", "name":'endTime', "orderable": true, "width":"20%" },
						{ "data": "result", "name":'result', "orderable": true, "width":"10%" },
						{ "data": "resultMessage", "name":'resultMessage', "visible":false }
	                ],
			"order": [[ 0, "desc" ]],	                
	    	"dom": domString
	    	,
	    	"createdRow": function ( row, data, index ) 
	    	{
	    		$(row).attr('title', data.resultMessage);
	        }
		} );
		$('#tvodEpgLoadHistoryTable_wrapper .table-caption').text('<spring:message code="menu.interfaces.epg.to.tvod"/> <spring:message code="search.result"/>');
// 		$('#serviceDeployHistoryTable_wrapper .dataTables_filter input').attr('placeholder', '<spring:message code="service.deploy.status.offer.id"/>');

		$("#buttonSearch").click(function() {
			varTvodEpgLoadHistoryTable.draw(true);
		});

		// Array to track the ids of the details displayed rows
	    var detailRows = [];
	    
		$('#tvodEpgLoadHistoryTable tbody').on( 'click', 'tr td:first-child', function () {
	        var tr = $(this).closest('tr');
	        var row = varTvodEpgLoadHistoryTable.row( tr );
	        var idx = $.inArray( tr.attr('id'), detailRows );
	 
	        if ( row.child.isShown() ) {
	            tr.removeClass( 'details' );
	            row.child.hide();
	 
	            // Remove from the 'open' array
	            detailRows.splice( idx, 1 );
	        }
	        else {
	            tr.addClass( 'details' );
	            row.child( format( row.data() ) ).show();
	 
	            // Add to the 'open' array
	            if ( idx === -1 ) {
	                detailRows.push( tr.attr('id') );
	            }
	        }
	    } );

// 		$('#serviceDeployStatusTable tbody').on( 'click', 'tr', function () {
// 	        var d = varServiceDeployStatusTable.row( this ).data();
			
// 			$('#listView').hide();
// 			location.href="#detailView";
// 			var tUrl = "${ctxRoot}/serviceflow/getServiceRequestOfferDetail.do?offerId=" + d.offerId;
// 			$('#detailView').load(tUrl);
// 			$('#detailView').show();
// 		});
	});

</script>


<!-- / Javascript -->

<body>
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.interfaces"/></li>
		<li><spring:message code="menu.interfaces.epg.to.tvod"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-cloud-download page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.interfaces.epg.to.tvod"/></h1>
		</div>
	</div> <!-- / .page-header -->

	<div id="listView">
		<div class="panel form-horizontal">
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-1">
						<label class="control-label" ><spring:message code="search.condition"/></label>
					</div>
					<form id="tvodEpgLoadHistoryListSearchform" name="tvodEpgLoadHistoryListSearchform">
						<div class="col-xs-2">
							<select id="scResult" name="scResult" class="form-control form-group-margin">
								<option value=""><spring:message code="epg.to.tvod.result"/></option>
								<option value="O"><spring:message code="text.succeeded"/></option>
								<option value="X"><spring:message code="text.failed"/></option>
							</select>		
						</div>
						<div class="col-xs-2">
							<input id="scFileName" name="scFileName" class="input-sm form-control" type="text" placeholder="<spring:message code="epg.to.tvod.file.name"/>" value="${scFileName}">
						</div>
						<div class="col-xs-4">
							<div class="input-daterange input-group" id="bs-datepicker-range">
								<input id="scFromDate" name="scFromDate" class="input-sm form-control" type="text" placeholder="<spring:message code="epg.to.tvod.start.time"/>(From)" value="${scFromDate}">
								<span class="input-group-addon">-</span>
								<input id="scToDate" name="scToDate" class="input-sm form-control" type="text" placeholder="<spring:message code="epg.to.tvod.start.time"/>(To)" value="${scToDate}">
							</div>
						</div>
					</form>
					<div class="col-xs-3">
						<button class="btn btn-primary" id="buttonSearch"><spring:message code="button.search"/></button>
					</div>
				</div>
			</div>
			
			<div class="panel-body">
				<div class="table-primary">
					<table id="tvodEpgLoadHistoryTable" width="100%" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="epg.to.tvod.idx"/></th>
								<th><spring:message code="epg.to.tvod.file.name"/></th>
								<th><spring:message code="epg.to.tvod.start.time"/></th>
								<th><spring:message code="epg.to.tvod.end.time"/></th>
								<th><spring:message code="epg.to.tvod.result"/></th>
								<th><spring:message code="epg.to.tvod.result.message"/></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="detailView" style="display:none">
	</div>
</body>
</html>
