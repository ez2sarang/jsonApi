<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.contract"/></title>
</head>

<script>
	$(document).ready(function(){
		var ajaxUrl = "${ctxRoot}/contract/getContractList.do";

// 		$("#addProviderIdDiv").hide();
// 		$("#providerIdDetailDiv").hide();

		var domString = '<"table-header clearfix"<"table-caption"><"DT-lf-right"<"DT-search"f>>r>t';		// header and table body
		domString = domString + '<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>';				// footer
		
		var varContractListTable = $('#contractListTable').DataTable( {
		    "processing": true,
		    "serverSide": true,
		    "lengthChange": false,
		    "ordering": false,
		    "ajax": ajaxUrl,
		    "pageLength": 5,
		    "columns": [
						{ "data": "regIdx", "name":'regIdx', "width":"25%" },
						{ "data": "contractName", "name":'contractName' }
		            ],
// 			"columnDefs": [
// 			       		{	"targets": [ 0 ], "searchable": false}
// 					],
			"dom": domString
		} );
		$('#contractListTable_wrapper .table-caption').text('<spring:message code="search.result"/>');
		$('#contractListTable_wrapper .dataTables_filter input').attr('placeholder', 'Search...');
		
		$("#getAddContractForm").click(function() {
			var tUrl = "${ctxRoot}/contract/getAddContractForm.do";
			$('#subViewDiv').load(tUrl);
		});

		$('#contractListTable tbody').on('click', 'tr', function () {
	        var d = varContractListTable.row( this ).data();
// 			document.getElementById("regIdx").value = d.regIdx;
			
			var tUrl = "${ctxRoot}/contract/getContractInfo.do?regIdx=" + d.regIdx;
			$('#subViewDiv').load(tUrl);
		} );

		function FunctionRedrawDataTable(regIdx)
		{
			varContractListTable.draw(true);
		}
		redrawDataTable = FunctionRedrawDataTable;

		function FunctionGetContractInfo(regIdx)
		{
			var tUrl = "${ctxRoot}/contract/getContractInfo.do?regIdx=" + regIdx;
			$('#subViewDiv').load(tUrl);
		}
		getContractInfo = FunctionGetContractInfo;

		function FunctionClearSubViewDiv()
		{
			$('#subViewDiv').html("");
		}
		clearSubViewDiv = FunctionClearSubViewDiv;
	});
</script>

<body>
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.contract.manage"/></li>
		<li class="active"><spring:message code="menu.contract"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-file-o page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.contract"/></h1>
		</div>
	</div> <!-- / .page-header -->

	<div class="row">
		<div class="col-md-6">
			<div class="panel colourable">
				<div class="panel-heading">
					<span class="panel-title"><i class="panel-title-icon fa fa-align-justify"></i><spring:message code="contract.list"/></span>
				</div>
				<div class="panel-body" style="height:450px; overflow:auto;">
					<div class="table-primary">
						<div align="right">
							<button class="btn btn-primary" id="getAddContractForm"><spring:message code="button.add"/></button>
						</div><br>
						<table width="100%" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" id="contractListTable">
							<thead>
								<tr>
									<th><spring:message code="reg.idx"/></th>
									<th><spring:message code="contract.name"/></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
<!-- 				<div class="panel-footer" align="right"> -->
<%-- 					<button class="btn btn-primary" onclick="addGroupCode();"><spring:message code="button.add"/></button> --%>
<!-- 				</div> -->
			</div>
		</div>

		<div class="col-md-6" id="detailViewDiv">
			<div id="subViewDiv">
			</div>
		</div>
</body>
</html>