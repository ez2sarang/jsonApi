<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.series"/></title>
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
	$(document).ready(function () {
		
		var table = $('#seriesDatatable').DataTable({
			"columns": [
				{ "data": "seriesId" },
				{ "data": "seriesName" },
				{ "data": "cpId" },
				{ "data": null }
			],
			"columnDefs": [
				{
					"render": function (data, type, row) {
						return '<button type="button" class="btn btn-primary btn-xs" onClick="editSeries(' + row.seriesId + ')"><spring:message code="button.edit"/></button>';
	                },
	                "targets": 3
				}
			],
			"searching": false,
			"sort" : false,
			"dom": '<"table-header clearfix"<"zimin-left"<"DT-per-page"l>><"DT-lf-right"<"zimin-right">>>t<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>',
			"processing": true,
			"serverSide": true,
	        "ajax": {
	            "url": "${ctxRoot}/asset/getSeriesList.json",
	            "type": "POST",
	            "data": function ( d ) {
	                d.seriesName = $('#seriesForm #seriesName').val();
	            },
	            "beforeSend": function(jqXHR, settings) {
			  		jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
			  		try {
			  			$("#seriesForm #searchButton").prop("disabled", true);
						setCursor("seriesForm", "wait");
			  		} catch(e) {}
			    },
			    "complete": function(jqXHR, textStatus){
					try {
						$("#seriesForm #searchButton").prop("disabled", false);
						setCursor("seriesForm", "default");
			  		} catch(e) {}
				}

	        }
		});
		
		// 첫페이지는 search parameter 없이 요청됨
		$('.zimin-right').html($('#temp_search').html());

		$("#seriesForm #searchButton").click(function() {
			if ($("#seriesForm #seriesName").val().length > 0
					&& $("#seriesForm #seriesName").val().length < 2) {
				openAlert('Alert!!', '<spring:message code="errors.search.moreCharacters" arguments="2"/>', function() {
				});
			} else {
				table.draw(true);
			}
		});
		
		$("#seriesForm #seriesName").keypress(function(e) {
			if(e.keyCode == 13) {
				e.preventDefault();
				$("#seriesForm #searchButton").trigger("click");
		    }
		});
	});
	
	function editSeries(seriesId) {
		var tUrl = "${ctxRoot}/asset/editSeriesForm.do"
    		+ "?seriesId=" + seriesId;
        
		openModalWindow('01', tUrl);
	}
	
	function addSeries() {
		var tUrl = "${ctxRoot}/asset/addSeriesForm.do";
		openModalWindow('01', tUrl);
	}
	
</script>
</head>
<body>
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.serviceAsset"/></li>
		<li class="active"><spring:message code="menu.serviceAsset.series"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa  fa-picture-o page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.serviceAsset.series"/></h1>
		</div>
	</div> <!-- / .page-header -->
	
	<div class="row">
		<form:form modelAttribute="series" id="seriesForm" >
			<div class="col-sm-12">
			
				<div class="table-primary">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="seriesDatatable">
						<thead>
							<tr>
								<th>ID</th>
								<th><spring:message code="series.name" var="seriesNameLabel"/>${seriesNameLabel}</th>
								<th><spring:message code="offer.cpId"/></th>
								<th><spring:message code="button.edit"/></th>
							</tr>
						</thead>
						
					</table>
				</div>
				
				<div align="right">
					<button type="button" class="btn btn-primary" onclick="addSeries();"><spring:message code="series.title.add"/></button>
				</div>
			</div>
		</form:form>

		<div id="temp_search" style="display:none">
			<table>
				<tr>
					<td>
						<spring:message code="button.search" var="searchLabel"/>
						<input type="text" id="seriesName" name="seriesName" class="form-control" placeholder="${seriesNameLabel} ${searchLabel}">
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
		<input type="hidden" name="seriesId" value="">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

</body>
</html>