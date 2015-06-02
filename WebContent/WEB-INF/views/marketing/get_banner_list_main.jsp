<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.marketing.banner"/></title>
<style type="text/css" class="init">
	.zimin-left {
		float:left;
	}
	.zimin-right {
		float:right;
	}
</style>

<script language="javascript">
	var bannerTypeMap = {};
	var linkTypeMap = {};
	
	$(document).ready(function () {
		// 공통코드 적재
		<c:forEach items="${bannerTypeCodeList}" var="bannerType">
			bannerTypeMap['${bannerType.code}'] = '${bannerType.codeName}';
		</c:forEach>
		<c:forEach items="${linkTypeCodeList}" var="linkType">
			linkTypeMap['${linkType.code}'] = '${linkType.codeName}';
		</c:forEach>
		
		var table = $('#userDatatable').DataTable({
			"columns": [
			            { "data": "bannerId" },
			            { "data": "bannerName" },
			            { "data": "bannerType" },
			            { "data": "linkType" },
			            { "data": "linkInfo" },
			            { "data": "licenceStartTime"},
			            { "data": "licenceEndTime" }
			],
			"columnDefs": [
				{"visible": false, "targets": [-1]},
				{"width": "150px", "targets": [4]},
				{"width": "270px", "targets": [5]},
				{
					"render": function (data, type, row) {
						return '<a href="javascript:viewDetail(\'' + row.bannerId + '\')">' + data + '</a>';
	                },
	                "targets": 1
				},
				{
					"render": function (data, type, row) {
	                    return bannerTypeMap[data];
	                },
	                "targets": 2
				},
				{
					"render": function (data, type, row) {
	                    return linkTypeMap[data];
	                },
	                "targets": 3
				},
				{
					"render": function (data, type, row) {
	                    return data + ' ~ '+ row.licenceEndTime;
	                },
	                "targets": 5
				}
			],
			"searching": false,
			"sort" : false,
			"dom": '<"table-header clearfix"<"zimin-left"<"DT-per-page"l>><"DT-lf-right"<"zimin-right">>>t<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>',
			"processing": true,
			"serverSide": true,
	        "ajax": {
	            "url": "${ctxRoot}/marketing/getBannerList.json",
	            "type": "POST",
	            "data": function ( d ) {
	                d.bannerName = $('#bannerForm #bannerName').val();
	                d.bannerType = $('#bannerForm #bannerType').val();
	                d.linkType = $('#bannerForm #linkType').val();
	                if (($('#bannerForm #searchOptionPeriodPast').is(":checked")))
	                	d.searchOptionPeriodPast = 'true';
	                if (($('#bannerForm #searchOptionPeriodPresent').is(":checked")))
	                	d.searchOptionPeriodPresent = 'true';
	                if (($('#bannerForm #searchOptionPeriodFuture').is(":checked")))
	                	d.searchOptionPeriodFuture = 'true';
	            },
	            "beforeSend": function(jqXHR, settings) {
			  		jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
			  		try {
			  			$("#bannerForm #searchButton").prop("disabled", true);
						setCursor("bannerForm", "wait");
			  		} catch(e) {}
			    },
			    "complete": function(jqXHR, textStatus){
					try {
						$("#bannerForm #searchButton").prop("disabled", false);
						setCursor("bannerForm", "default");
			  		} catch(e) {}
				}

	        }
		});
		
		// 첫페이지는 search parameter 없이 요청됨
		$('.zimin-right').html($('#temp_search').html());

		$("#bannerForm #searchButton").click(function() {
			if (!($('#bannerForm #searchOptionPeriodPast').is(":checked"))
					&& !($('#bannerForm #searchOptionPeriodPresent').is(":checked"))
					&& !($('#bannerForm #searchOptionPeriodFuture').is(":checked"))) {
				openAlert('Alert!!', '<spring:message code="banner.error.licenceTime.atLeastOne"/>', function() {
				});
			} else if ($("#bannerForm #bannerName").val().length > 0
					&& $("#bannerForm #bannerName").val().length < 2) {
				openAlert('Alert!!', '<spring:message code="errors.search.moreCharacters" arguments="2"/>', function() {
				});
			} else {
				table.draw(true);
			}
		});
		
		$("#bannerForm #bannerName").keypress(function(e) {
			if(e.keyCode == 13) {
				e.preventDefault();
				$("#bannerForm #searchButton").trigger("click");
		    }
		});
		
		$(window).bind('hashchange', function () {
	        var hash = window.location.hash.slice(1);
	        if (hash == 'detailView') {
	        	$('#listDiv').hide();
	    		$('#detailDiv').show();
	        } else {
	        	goList();
	        }
	    });
	});
	
	function goList() {
		$('#detailDiv').hide();
		$('#listDiv').show();
	}
	
	function viewDetail(bannerId) {
		$('#listDiv').hide();
		location.href="#detailView";
		var tUrl = "${ctxRoot}/marketing/viewBanner.do?bannerId=" + bannerId;
		$('#detailDiv').load(tUrl);
		$('#detailDiv').show();
	}
	
	function addBanner() {
		var tUrl = "${ctxRoot}/marketing/addBannerForm.do";
		openModalWindow('01', tUrl);
	}
	
	function editBanner(bannerId) {
		var tUrl = "${ctxRoot}/marketing/editBannerForm.do?bannerId=" + bannerId;
		openModalWindow('01', tUrl);
	}
	
	function addBannerImage(bannerId, localeCode) {
		var tUrl = "${ctxRoot}/marketing/addBannerImageForm.do?bannerId="
				+ bannerId + "&localeCode=" + localeCode;
		openModalWindow('01', tUrl);
	}
	
	function editBannerImage(idx) {
		var tUrl = "${ctxRoot}/marketing/editBannerImageForm.do?idx=" + idx;
		openModalWindow('01', tUrl);
	}
	
	function deleteBannerImage(bannerId, idx) {
		// <spring:message code="banner.file" var="fileLabel"/>
		openConfirm('<spring:message code="confirm.delete" arguments="${fileLabel}"/>',function(result) {
			if(result) {
				cmdForm.idx.value = idx;
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/marketing/deleteBannerImage.do",
						data: $('form#cmdForm').serialize(),
						success: function(data, textStatus, jqXHR){
							if (data == 'Success') {
								openInform('Success', data, function() {
									viewDetail(bannerId);
								});
							} else {
								openAlert('Fail', data, function() {
								});
							}
						},
						error: function(jqXHR, textStatus, errorThrown){
							openAlert('Fail', 'Failure:' + textStatus);
						}
					});
				}catch(e) {
					alert (e);
				}
			}
		});
	}
	
	function deleteBanner(bannerId) {
		// <spring:message code="banner.info" var="bannerLabel"/>
		openConfirm('<spring:message code="confirm.delete" arguments="${bannerLabel}"/>',function(result) {
			if(result) {
				//cmdForm.bannerId.value = bannerId;
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/marketing/deleteBanner/" + bannerId + ".do",
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
							openAlert('Fail', 'Failure:' + textStatus);
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
		<li><spring:message code="menu.marketing"/></li>
		<li class="active"><spring:message code="menu.marketing.banner"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa  fa-picture-o page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.marketing.banner"/></h1>
		</div>
	</div> <!-- / .page-header -->
	
	<div class="row" id="listDiv">
		<form:form modelAttribute="banner" id="bannerForm" >
			<div class="col-sm-12">
			
				<div class="table-primary">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="userDatatable">
						<thead>
							<tr>
								<th>ID</th>
								<th><spring:message code="banner.name" var="bannerNameLabel"/>${bannerNameLabel}</th>
								<th><spring:message code="banner.bannerType"/></th>
								<th><spring:message code="banner.linkType"/></th>
								<th><spring:message code="banner.linkInfo"/></th>
								<th><spring:message code="banner.licencePeriod"/></th>
								<th><spring:message code="banner.licencePeriod"/></th>
							</tr>
						</thead>
						
					</table>
				</div>
				
				<div align="right">
					<button type="button" class="btn btn-primary" onclick="addBanner();"><spring:message code="banner.title.add"/></button>
				</div>
			</div>
		</form:form>
				
		<div id="temp_search" style="display:none">
			<table>
				<tr>
					<td>
						<spring:message code="button.search" var="searchLabel"/>
						<input type="text" id="bannerName" name="bannerName" class="form-control" placeholder="${bannerNameLabel} ${searchLabel}">
						<select id="bannerType" name="bannerType" class="form-control">
							<option value="">-- <spring:message code="banner.bannerType"/> --</option>
							<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_BANNER_TYPE')}" />
						</select>
						<select id="linkType" name="linkType" class="form-control">
							<option value="">-- <spring:message code="banner.linkType"/> --</option>
							<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_BANNER_LINK_TYPE')}" />
						</select>
					</td>
					<td>
						&nbsp;
						<button type="button" class="btn btn-default" id="searchButton">${searchLabel}</button>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="padding-top: 10px; text-align: right;">
						<spring:message code="banner.licencePeriod"/>: &nbsp;
						<label class="checkbox-inline">
							<input type="checkbox" id="searchOptionPeriodPast" name="searchOptionPeriodPast" checked="checked" class="px"> <span class="lbl"><spring:message code="search.option.searchOptionPeriodPast"/></span>
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" id="searchOptionPeriodPresent" name="searchOptionPeriodPresent" checked="" class="px"> <span class="lbl"><spring:message code="search.option.searchOptionPeriodPresent"/></span>
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" id="searchOptionPeriodFuture" name="searchOptionPeriodFuture" checked="" class="px"> <span class="lbl"><spring:message code="search.option.searchOptionPeriodFuture"/></span>
						</label>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</div>
		
	</div>
	
	<a name="detailView"></a>
	<div class="row" id="detailDiv" style="display:none">
	</div>
	
	<form name="cmdForm" id="cmdForm">
		<input type="hidden" name="bannerId" value="">
		<input type="hidden" name="idx" value="">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

</body>
</html>