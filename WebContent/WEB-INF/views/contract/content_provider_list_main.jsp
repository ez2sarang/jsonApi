<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>

<html>
<head>
	<title><spring:message code="menu.content.provider"/></title>
</head>

<script>
	$(document).ready(function(){
		var ajaxUrl = "${ctxRoot}/contract/getContentProviderList.do";
	
// 		var displayStart = <c:out value="${displayStart}"/>;
// 		var search = "<c:out value="${search}"/>";
		
	    var varContentProviderListTable = $('#contentProviderListTable').DataTable( {
	        "processing": true,
	        "serverSide": true,
	        "ajax": ajaxUrl,
	        "columns": [
						{ "data": "regIdx", "name":'regIdx', "orderable": true, "width":"10%" },
						{ "data": "cpId", "name":'cpId', "orderable": true, "width":"10%" },
						{ "data": "cpName", "name":'cpName', "orderable": true, "width":"20%" },
						{ "data": "idDomain", "name":'idDomain', "orderable": false, "width":"30%" },
						{ "data": "ftpPath", "name":'ftpPath', "orderable": false },
	                ],
			"searchCols": [
			               null,
			               { sSearch: '${cpId}' },
			               { sSearch: '${cpName}' },
			               null,
			               null
			             ],
// 			"displayStart": displayStart,
// 			"search": {
// 			    "search": search
// 			  },
			"drawCallback": function( settings ) {
// 				var api = this.api();
// 				var pageInfo = api.page.info();
// 				$("#displayStart").val(pageInfo.start);
// 				$("#search").val(api.search());
			}
		} );
		$('#contentProviderListTable_wrapper .table-caption').text('<spring:message code="search.result"/>');
		$('#contentProviderListTable_wrapper .dataTables_filter input').attr('placeholder', 'Search...');
	
		$("#buttonAdd").click(function() {
	// 		$("#actionForm").attr("action", "<c:url value='/contract/getAddContentProviderForm.do'/>");
	// 		$("#actionForm").submit();
	
			$('#listView').hide();
			location.href="#addView";
			var tUrl = "${ctxRoot}/contract/getAddContentProviderForm.do";
			$('#detailView').load(tUrl);
			$('#detailView').show();
	
		});
	
		$('#contentProviderListTable tbody').on( 'click', 'tr', function () {
	        var d = varContentProviderListTable.row( this ).data();

			$('#listView').hide();
			location.href="#detailView";
			var tUrl = "${ctxRoot}/contract/getContentProvider.do?regIdx=" + d.regIdx;
			$('#detailView').load(tUrl);
			$('#detailView').show();
	        
// 			document.getElementById("regIdx").value = d.regIdx;
// 			$("#actionForm").attr("action", "<c:url value='/contract/getContentProvider.do'/>");
// 			$("#actionForm").submit();
	        
	    } );

		function FunctionRedrawDataTable()
		{
			varContentProviderListTable.draw(true);
		}
		redrawDataTable = FunctionRedrawDataTable;
	    
	});
	
	function goList() {
		$('#detailView').hide();
		$('#listView').show();
	}

</script>

<body>
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.contract.manage"/></li>
		<li class="active"><spring:message code="menu.content.provider"/></li>
	</ul>
	
	<div id="listView">
		<div class="page-header">
			<div class="row">
				<!-- Page header, center on small screens -->
				<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-key page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.content.provider"/></h1>
			</div>
		</div> <!-- / .page-header -->

<!-- 		<div class="panel form-horizontal"> -->
<!-- 			<div class="panel-heading text-right"> -->
<%-- 				<button class="btn btn-primary" id="buttonAdd"><spring:message code="button.add"/></button> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		
		<div class="panel form-horizontal">
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-12 text-right">
						<button class="btn btn-primary" id="buttonAdd"><spring:message code="button.add"/></button>
					</div>
				</div>
			</div>		
			<div class="panel-body">
				<div class="table-primary">
					<table id="contentProviderListTable" width="100%" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="content.provider.reg.idx"/></th>
								<th><spring:message code="content.provider.id"/></th>
								<th><spring:message code="content.provider.name"/></th>
								<th><spring:message code="content.provider.id.domain"/></th>
								<th><spring:message code="content.provider.ftp.path"/></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div id="detailView" style="display:none">
	</div>
	
	<form id="actionForm" name="actionForm" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" id="regIdx" name="regIdx"/>
<%-- 		<input type="hidden" id="displayStart" name="displayStart" value="<c:out value="${displayStart}"/>" /> --%>
<%-- 		<input type="hidden" id="search" name="search" value="<c:out value="${search}"/>" /> --%>
	</form>	
</body>
</html>