<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>

<%@page import="org.apache.commons.lang.StringUtils"%>
<html>
<head>
<title><spring:message code="menu.subscriber.identification"/></title>
</head>

<script>
// 	$.fn.dataTable.ext.search.push(
// 	    function( settings, data, dataIndex ) {
		    
// 	        return true;
// 	    }
// 	);
	
	$(document).ready(function(){
		var ajaxUrl = "${ctxRoot}/subscriber/getSubscriberIdList.do";

	    var varSubscriberIdListTable = $('#subscriberIdListTable').DataTable( {
	        "processing": true,
	        "serverSide": true,
	        "ajax": ajaxUrl,
// 	        "ajax": {
// 	        	"url": ajaxUrl,
// 	        	"data": { name: "John", location: "Boston" }
// 		    },
	        "columns": [
						{ "data": "subscriberIdRegIdx", "name":'subscriberIdRegIdx' },
						{ "data": "contractId", "name":'contractId' },
						{ "data": "idType", "name":'idType' },
						{ "data": "subscriberId", "name":'subscriberId' },
						{ "data": "useYn", "name":'useYn' },
	                ],
			"searchCols": [
			               null,
			               { sSearch: '${scContractId}' },
			               { sSearch: '${scIdType}' },
			               { sSearch: '${scSubscriberId}' },
			               null
			             ]             
		} );
		$('#subscriberIdListTable_wrapper .table-caption').text('<spring:message code="search.result"/>');
		$('#subscriberIdListTable_wrapper .dataTables_filter input').attr('placeholder', 'Search...');

		$( "#subscriberIdSearchForm" ).submit(function( event ) {
		});

		$("#buttonSearch").click(function() {
// 			$("#subscriberIcdSearchForm").attr("action", "<c:url value='/subscriber/getSubscriberIdList.do'/>");
// 			$("#subscriberIdSearchForm").submit();

			// custom search parameter set
			varSubscriberIdListTable.column(1).search(document.getElementById("scContractId").value);
			varSubscriberIdListTable.column(3).search(document.getElementById("scSubscriberId").value);
			varSubscriberIdListTable.column(2).search(document.getElementById("scIdType").value);
			varSubscriberIdListTable.draw(true);
		});
		
		$("#buttonAdd").click(function() {
			$("#subscriberIdSearchForm").attr("action", "<c:url value='/subscriber/getAddSubscriberIdForm.do'/>");
			$("#subscriberIdSearchForm").submit();
		});

		$('#subscriberIdListTable tbody').on( 'click', 'tr', function () {
	        var d = varSubscriberIdListTable.row( this ).data();
			document.getElementById("subscriberIdRegIdx").value = d.subscriberIdRegIdx;
	        
			$("#subscriberIdSearchForm").attr("action", "<c:url value='/subscriber/getSubscriberId.do'/>");
			$("#subscriberIdSearchForm").submit();
	        
	    } );
	});
</script>

<script type="text/javaScript" language="javascript" defer="defer">
function fnUpdateSubscriberId(subscriberIdRegIdx)
{
	document.getElementById("subscriberIdRegIdx").value = subscriberIdRegIdx;
	document.subscriberIdSearchForm.action = "<c:url value='/subscriber/getSubscriberId.do'/>";
    document.subscriberIdSearchForm.submit();
}
// 	function getAddSubscriberIdentificationForm()
// 	{
// 		document.userIdSearchForm.pageIndex.value = 1;
//  		document.userIdSearchForm.action = "<c:url value='/subscriber/getAddSubscriberIdForm.do'/>";
// 	    document.userIdSearchForm.submit();
// 	}
</script>

<body>
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.subscriber"/></li>
		<li class="active"><spring:message code="menu.subscriber.identification"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-key page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.subscriber.identification"/></h1>
		</div>
	</div> <!-- / .page-header -->

	<div class="panel form-horizontal">
		<div class="panel-heading">
			<span class="panel-title"><i class="panel-title-icon fa fa-search"></i><spring:message code="search.condition"/></span>
		</div>
		<div class="panel-body">
			<form id="subscriberIdSearchForm" name="subscriberIdSearchForm" method="get">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="hidden" id="subscriberIdRegIdx" name="subscriberIdRegIdx" value="novalue"/>
				<div class="row">
					<div class="col-sm-4">
						<div class="form-group no-margin-hr">
							<label class="control-label"><spring:message code="subscriber.contract.id"/></label>
							<input id="scContractId" name="scContractId" class="form-control" type="text" value="${scContractId}">
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group no-margin-hr">
							<label class="control-label"><spring:message code="subscriber.subscriber.id"/></label>
							<input id="scSubscriberId" name="scSubscriberId" class="form-control" type="text" value="${scSubscriberId}">
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group no-margin-hr">
							<label class="control-label"><spring:message code="subscriber.id.type"/></label>
							<select id="scIdType" name="scIdType" class="form-control form-group-margin">
								<option value="">All</option>
								<option value="ID" <c:if test="${scIdType == 'ID'}">selected</c:if>>ID</option>
								<option value="STB" <c:if test="${scIdType == 'STB'}">selected</c:if>>STB</option>
							</select>
						</div>
					</div>
				</div>
			</form>	
		</div>
		<div class="panel-footer text-right">
			<button class="btn btn-primary" id="buttonSearch"><spring:message code="button.search"/></button>&nbsp;&nbsp;&nbsp;
			<button class="btn btn-primary" id="buttonAdd"><spring:message code="button.add"/></button>
		</div>
	</div>
	
	<div class="panel form-horizontal">
		<div class="panel-body">
			<!-- Javascript -->
			<script>
// 				init.push(function () {
// 					$('#subscriberIdListTable').dataTable();
// 					$('#subscriberIdListTable_wrapper .table-caption').text('<spring:message code="search.result"/>');
// 					$('#subscriberIdListTable_wrapper .dataTables_filter input').attr('placeholder', 'Search...');
// 				});
			</script>
			<!-- / Javascript -->
			<div class="table-primary">
				<table width="100%" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="subscriberIdListTable">
					<thead>
						<tr>
							<th><spring:message code="subscriber.id.reg.idx"/></th>
							<th><spring:message code="subscriber.contract.id"/></th>
							<th><spring:message code="subscriber.id.type"/></th>
							<th><spring:message code="subscriber.subscriber.id"/></th>
							<th><spring:message code="useYn"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>	
</body>
</html>