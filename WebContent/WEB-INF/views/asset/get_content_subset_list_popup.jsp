<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.contentAsset"/></title>
<style type="text/css" class="init">
	.zimin-left {
		float:left;
	}
	.zimin-right {
		float:right;
	}
</style>

<script language="javascript">
	var contentSubsetTypeMap = {};
	$(document).ready(function () {
		// 공통코드 적재
		<c:forEach items="${contentSubsetTypeCodeList}" var="contentSubsetType">
			contentSubsetTypeMap['${contentSubsetType.code}'] = '${contentSubsetType.codeName}';
		</c:forEach>
		
		var table = $('#contentAssetDatatable').DataTable({
			"columns": [
			            { "data": "contentSubsetId" },
			            { "data": "title" },
			            { "data": "startDateTime"},
			            { "data": "endDateTime" }
			],
			"columnDefs": [
				{"visible": false, "targets": [-1]},
				{"width": "150px", "targets": [1]},
				{"width": "270px", "targets": [2]},
				{
					"render": function (data, type, row) {
						var escapeData =  data.replace(/[\\']/g, '\\\\$&').replace(/\u0000/g,'\\0');
						escapeData =  escapeData.replace(/["]/g, '&quot;');
						return '<a href="javascript:selectBox(\'' + row.contentSubsetId + '\',\'' + escapeData + '\')">' + data + '</a>';
	                },
	                "targets": 1
				},
				{
					"render": function (data, type, row) {
	                    return data + ' ~ '+ row.endDateTime;
	                },
	                "targets": 2
				}
			],
			"searching": false,
			"sort" : false,
			"dom": '<"table-header clearfix"<"zimin-left"<"DT-per-page"l>><"DT-lf-right"<"zimin-right">>>t<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>',
			"processing": true,
			"serverSide": true,
	        "ajax": {
	            "url": "${ctxRoot}/asset/getContentSubsetList.json",
	            "type": "POST",
	            "data": function ( d ) {
	            	d.searchType = 'title';
	                d.title = $('#contentSubsetForm #title').val();
	                d.contentSubsetType = $('#contentSubsetForm #contentSubsetType').val();
	                if (($('#contentSubsetForm #searchOptionPeriodPast').is(":checked")))
	                	d.searchOptionPeriodPast = 'true';
	                if (($('#contentSubsetForm #searchOptionPeriodPresent').is(":checked")))
	                	d.searchOptionPeriodPresent = 'true';
	                if (($('#contentSubsetForm #searchOptionPeriodFuture').is(":checked")))
	                	d.searchOptionPeriodFuture = 'true';
	            },
	            "beforeSend": function(jqXHR, settings) {
			  		jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
			  		try {
			  			$("#contentSubsetForm #searchButton").prop("disabled", true);
						setCursor("contentSubsetForm", "wait");
			  		} catch(e) {}
			    },
			    "complete": function(jqXHR, textStatus){
					try {
						$("#contentSubsetForm #searchButton").prop("disabled", false);
						setCursor("contentSubsetForm", "default");
			  		} catch(e) {}
				}

	        }
		});
		
		// 첫페이지는 search parameter 없이 요청됨
		$('.zimin-right').html($('#temp_search').html());

		$("#contentSubsetForm #searchButton").click(function() {
			if (!($('#contentSubsetForm #searchOptionPeriodPast').is(":checked"))
					&& !($('#contentSubsetForm #searchOptionPeriodPresent').is(":checked"))
					&& !($('#contentSubsetForm #searchOptionPeriodFuture').is(":checked"))) {
				openAlert('Alert!!', '<spring:message code="contentSubset.error.availablePeriod.atLeastOne"/>', function() {
				});
			} else if ($("#contentSubsetForm #title").val().length > 0
					&& $("#contentSubsetForm #title").val().length < 2) {
				openAlert('Alert!!', '<spring:message code="errors.search.moreCharacters" arguments="2"/>', function() {
				});
			} else {
				table.draw(true);
			}
		});
		
		$("#contentSubsetForm #title").keypress(function(e) {
			if(e.keyCode == 13) {
				e.preventDefault();
				$("#contentSubsetForm #searchButton").trigger("click");
		    }
		});
	});
	
    function selectBox(id, name) {

        try {
            $("#${param.targetId} option").each(function(){
                $(this).remove();
            });
        } catch (e) {
            alert("error:"+e)
        }
        $("#${param.targetId}").append($('<option>').val(id).text(name).attr('selected', true)).trigger('change');
        closeModalWindow('02');
    }
</script>
</head>
<body>
	<div class="row" id="listDiv">
		<form:form modelAttribute="contentSubset" id="contentSubsetForm" >
			<div class="col-sm-12">
				<div class="table-primary">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="contentAssetDatatable">
						<thead>
							<tr>
								<th>ID</th>
								<th><spring:message code="contentSubset.title" var="titleLabel"/>${titleLabel}</th>
								<th><spring:message code="contentSubset.availablePeriod"/></th>
								<th><spring:message code="contentSubset.availablePeriod"/></th>
							</tr>
						</thead>
					</table>
                    <input type="hidden" id="contentSubsetType" name="contentSubsetType" value="${param.subsetType}">
                    <input type="checkbox" id="searchOptionPeriodPresent" name="searchOptionPeriodFuture" checked="checked" style="visibility: hidden;">
                    <input type="checkbox" id="searchOptionPeriodFuture" name="searchOptionPeriodFuture" checked="checked" style="visibility: hidden;">
				</div>
				<div align="right">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
				</div>
			</div>
		</form:form>
		<div id="temp_search" style="display:none">
			<table>
				<tr>
					<td>
						<spring:message code="button.search" var="searchLabel"/>
						<input type="text" id="title" name="title" class="form-control" placeholder="${titleLabel} ${searchLabel}">
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
		<input type="hidden" name="contentSubsetId" value="">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

</body>
</html>