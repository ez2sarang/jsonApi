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

<link rel="stylesheet" href="${ctxRoot}/js/spinner/css/iosOverlay.css">
<link rel="stylesheet" href="${ctxRoot}/js/spinner/css/prettify.css">
<script src="${ctxRoot}/js/spinner/js/iosOverlay.js"></script>
<script src="${ctxRoot}/js/spinner/js/spin.min.js"></script>
<script src="${ctxRoot}/js/spinner/js/prettify.js"></script>
<script src="${ctxRoot}/js/spinner/js/custom.js"></script>
<script src="${ctxRoot}/js/spinner/js/modernizr-2.0.6.min.js"></script>
<script src="${ctxRoot}/js/excel.js"></script>
<script src="${ctxRoot}/js/moment.js"></script>
<script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
<script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>
<script language="javascript">
	var contentSubsetTypeMap = {};
	var stageCodeMap = {};

	$(document).ready(function () {
		// 공통코드 적재
		<c:forEach items="${contentSubsetTypeCodeList}" var="contentSubsetType">
			contentSubsetTypeMap['${contentSubsetType.code}'] = '${contentSubsetType.codeName}';
		</c:forEach>
		<c:forEach items="${stageCodeList}" var="stageCode">
			stageCodeMap['${stageCode.code}'] = '${stageCode.codeName}';
		</c:forEach>
		
		var table = $('#contentAssetDatatable').DataTable({
			"columns": [
			            { "data": "contentSubsetId" },
			            { "data": "title" },
			            { "data": "contentSubsetType" },
			            { "data": "cpId" },
			            { "data": "startDateTime"},
			            { "data": "endDateTime" },
			            { "data": "stageCode" }
			],
			"columnDefs": [
				{"visible": false, "targets": [-2]},
				{"width": "270px", "targets": [4]},
				{
					"render": function (data, type, row) {
						return '<a href="javascript:viewDetail(\'' + row.contentSubsetId + '\')">' + data + '</a>';
	                },
	                "targets": 1
				},
				{
					"render": function (data, type, row) {
	                    return contentSubsetTypeMap[data];
	                },
	                "targets": 2
				},
				{
					"render": function (data, type, row) {
	                    return data + ' ~ '+ row.endDateTime;
	                },
	                "targets": 4
				},
				{
					"render": function (data, type, row) {
						if (data == null) {
							return "";
						} else {
	                    	return stageCodeMap[data];
						}
	                },
	                "targets": 6
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
                    d.searchType = $("#searchType").val();
                    d.title = $('#contentSubsetForm #title').val();
                    //<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                    	d.adminSearch = "cp";
                    //</sec:authorize>
                    d.cpId = $('#contentSubsetForm #cpId').val();
                    d.contentSubsetType = $('#contentSubsetForm #contentSubsetType').val();
	                d.fileType = $('#fileType').prop("disabled")?null:$('#fileType').val();
                    d.dateSearchType = $("#dateSearchType").val();
                    if($("#dateSearchType").val() == 'term') {
                        if (($('#contentSubsetForm #searchOptionPeriodPast').is(":checked")))
                            d.searchOptionPeriodPast = 'true';
                        if (($('#contentSubsetForm #searchOptionPeriodPresent').is(":checked")))
                            d.searchOptionPeriodPresent = 'true';
                        if (($('#contentSubsetForm #searchOptionPeriodFuture').is(":checked")))
                            d.searchOptionPeriodFuture = 'true';
                    } else {
                        d.startTime = $.isBlank($('#startTime').val())?"":String.format("{0} 00:00:00", $('#startTime').val());
                        d.endTime = $.isBlank($('#endTime').val())?"":String.format("{0} 23:59:59", $('#endTime').val());
                        d.searchOptionPeriodPast = 'true';
                        d.searchOptionPeriodPresent = 'true';
                        d.searchOptionPeriodFuture = 'true';
                    }
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

        $("#contentSubsetType").bind('change', function(){
            if(this.value == "${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_MOVIE')}" || this.value == "${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_PREVIEW')}") {
                $("#fileType").prop("disabled", false);
                $("#fileType").show();
            } else {
                $("#fileType").prop("disabled", true);
                $("#fileType").hide();
            }
        });
        $("#fileType").prop("disabled", true);
        $("#fileType").hide();

        try {
            $('#startTime').datetimepicker({
                useSeconds: true,
                format: moment().toMomentFormatString('<%=DateUtil.defaultDateFormat %>')
            });
            $('#endTime').datetimepicker({
                useSeconds: true,
                format: moment().toMomentFormatString('<%=DateUtil.defaultDateFormat %>')
            });
            $("#startTime").on("dp.change",function (e) {
                $('#endTime').data("DateTimePicker").setMinDate(e.date);
            });
            $("#endTime").on("dp.change",function (e) {
                $('#startTime').data("DateTimePicker").setMaxDate(e.date);
            });
        } catch (e) {
            alert("calendar:"+e);
        }
        $("#dateSearchType").bind('change', function(){
            if(this.value == 'term') {
                $("#termOpt").show();
                $("#timeOpt").hide();
            } else {
                $("#timeOpt").show();
                $("#termOpt").hide();
            }
        });
        $("#termOpt").show();
        $("#timeOpt").hide();

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
		
		$(window).bind('hashchange', function () {
	        var hash = window.location.hash.slice(1);
	        if (hash == 'detailView') {
	        	$('#listDiv').hide();
	    		$('#detailDiv').show();
	        } else {
	        	goList();
	        }
	    });


        $("#startTime").bind('keyup', function(){
            if(!(event.keyCode == 8 || event.keyCode == 37 || event.keyCode == 39)) {
                document.execCommand('undo', false, null);
            }
        });
        $("#endTime").bind('keyup', function(){
            if(!(event.keyCode == 8 || event.keyCode == 37 || event.keyCode == 39)) {
                document.execCommand('undo', false, null);
            }
        });

        $("#excel").bind('click', function(){
            var params = "";
            params += String.format("searchType={0}", $("#searchType").val());
            params += String.format("&title={0}", $('#contentSubsetForm #title').val());
            //<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
            	params += "&adminSearch=cp";
            //</sec:authorize>
            params += String.format("&cpId={0}", $('#contentSubsetForm #cpId').val());
            params += String.format("&contentSubsetType={0}", $('#contentSubsetForm #contentSubsetType').val());
            params += String.format("&fileType={0}", $('#fileType').prop("disabled")?null:$('#fileType').val());
            params += String.format("&dateSearchType={0}", $("#dateSearchType").val());
            if($("#dateSearchType").val() == 'term') {
                if (($('#contentSubsetForm #searchOptionPeriodPast').is(":checked")))
                    params += String.format("&searchOptionPeriodPast={0}", 'true');
                if (($('#contentSubsetForm #searchOptionPeriodPresent').is(":checked")))
                    params += String.format("&searchOptionPeriodPresent={0}", 'true');
                if (($('#contentSubsetForm #searchOptionPeriodFuture').is(":checked")))
                    params += String.format("&searchOptionPeriodFuture={0}", 'true');
            } else {
                params += String.format("&startTime={0}", $.isBlank($('#startTime').val())?"":String.format("{0} 00:00:00", $('#startTime').val()));
                params += String.format("&endTime={0}", $.isBlank($('#endTime').val())?"":String.format("{0} 23:59:59", $('#endTime').val()));
                params += String.format("&searchOptionPeriodPast={0}", 'true');
                params += String.format("&searchOptionPeriodPresent={0}", 'true');
                params += String.format("&searchOptionPeriodFuture={0}", 'true');
            }
            params += "&order[0][column]=0&order[0][dir]=asc&start=0&length=-1"/*+Number.MAX_VALUE*/;

            var opts = {
                lines: 13, // The number of lines to draw
                length: 11, // The length of each line
                width: 5, // The line thickness
                radius: 17, // The radius of the inner circle
                corners: 1, // Corner roundness (0..1)
                rotate: 0, // The rotation offset
                color: '#FFF', // #rgb or #rrggbb
                speed: 1, // Rounds per second
                trail: 60, // Afterglow percentage
                shadow: false, // Whether to render a shadow
                hwaccel: false, // Whether to use hardware acceleration
                className: 'spinner', // The CSS class to assign to the spinner
                zIndex: 2e9, // The z-index (defaults to 2000000000)
                top: 'auto', // Top position relative to parent in px
                left: 'auto' // Left position relative to parent in px
            };
            var target = document.createElement("div");
            document.body.appendChild(target);
            var spinner = new Spinner(opts).spin(target);
            var overlay = iosOverlay({
                text: "Loading",
                spinner: spinner
            });
            $.ajax({
                type: "POST",
                url: "${ctxRoot}/asset/getContentSubsetList.json",
                beforeSend: function(jqXHR, settings) {
                    jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                },
                data : params.replace("null", ""),
                success: function(data, textStatus, jqXHR){
                    try {
                        columnsDef = {"contentSubsetId":"Number"
                            , "revision":"String"
                            , "revisionDateTime": {
                                "render": function (data) {
                                    return new Date(data);
                                }
                                , "type":"String"
                            }
                            , "revisionCreatorId":"String"
                            , "cpId":"String"
                            , "contentSubsetType": {
                                "render": function (data) {
                                    return contentSubsetTypeMap[data];
                                }
                                , "type":"String"
                            }
                            , "title": {
                                "render": function(data) {
                                    return String.format("<![CDATA[{0}]]>", data);
                                }
                                , "type":"String"
                            }
                            , "startDateTime":"String"
                            , "endDateTime":"String"
                            , "sourceFileName":"String"
                        };
                        var blob = new Blob([jsonToSsXml(data.data, columnsDef)], {
                            'type': 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
                        });
                        var url = URL.createObjectURL(blob);
                        var link = document.createElement("a");
                        link.setAttribute("href", url);
                        link.setAttribute("download", 'asset.xls'||"Download.xls");
                        var event = document.createEvent('MouseEvents');
                        event.initMouseEvent('click', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
                        link.dispatchEvent(event);
                    } catch (e) {
                        alert("error:"+e);
                    } finally {
                        overlay.hide();
                    }
                },
                error: function(jqXHR, textStatus, errorThrown){
                    overlay.hide();
                    openAlert('Fail', 'Failure:' + textStatus);
                }
            });
        });
	});

	function goList() {
		$('#detailDiv').hide();
		$('#listDiv').show();
	}
	
	function viewDetail(contentSubsetId) {
		$('#listDiv').hide();
		location.href="#detailView";
		var tUrl = "${ctxRoot}/asset/viewContentSubset.do?contentSubsetId=" + contentSubsetId;
		$('#detailDiv').load(tUrl);
		$('#detailDiv').show();
	}
	
	function addMediaContentSubset() {
		var tUrl = "${ctxRoot}/asset/addMediaContentSubsetForm.do";
		openModalWindow('01', tUrl);
	}
	
	function addImageContentSubset() {
		var tUrl = "${ctxRoot}/asset/addImageContentSubsetForm.do";
		openModalWindow('01', tUrl);
	}
	
	function editContentSubset(contentSubsetId) {
		var tUrl = "${ctxRoot}/asset/editContentSubsetForm.do?contentSubsetId=" + contentSubsetId;
		openModalWindow('01', tUrl);
	}
	
	function deleteContentSubset(contentSubsetId) {
		// <spring:message code="contentSubset.info" var="contentSubsetLabel"/>
		openConfirm('<spring:message code="confirm.delete" arguments="${contentSubsetLabel}"/>',function(result) {
			if(result) {
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/asset/deleteContentSubset/" + contentSubsetId + ".do",
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
		<li><spring:message code="menu.serviceAsset"/></li>
		<li class="active"><spring:message code="menu.serviceAsset.contentAsset"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa  fa-picture-o page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.serviceAsset.contentAsset"/></h1>
		</div>
	</div> <!-- / .page-header -->
	
	<div class="row" id="listDiv">
		<form:form modelAttribute="contentSubset" id="contentSubsetForm" >
			<div class="col-sm-12">
			
				<div class="table-primary">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="contentAssetDatatable">
						<thead>
							<tr>
								<th>ID</th>
								<th><spring:message code="contentSubset.title" var="titleLabel"/>${titleLabel}</th>
								<th><spring:message code="contentSubset.contentSubsetType"/></th>
								<th><spring:message code="contentSubset.cpId"/></th>
								<th><spring:message code="contentSubset.availablePeriod"/></th>
								<th><spring:message code="contentSubset.availablePeriod"/></th>
								<th><spring:message code="serviceAsset.contentGroup.stageCode"/></th>
							</tr>
						</thead>
					</table>
				</div>
				
				<div align="right">
					<button type="button" class="btn btn-primary" onclick="addMediaContentSubset();"><spring:message code="contentSubset.title.mediaContent.add"/></button>
					<button type="button" class="btn btn-primary" onclick="addImageContentSubset();"><spring:message code="contentSubset.title.imageContent.add"/></button>
				</div>
			</div>
		</form:form>
				
		<div id="temp_search" style="display:none">
			<table>
				<tr>
					<td>
						<spring:message code="button.search" var="searchLabel"/>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                            <select id="cpId" name="cpId" class="form-control">
                                <option value="">--<spring:message code="combo.select" /> (CP)--</option>
                                <c:forEach items="${contentProviderSelectItems}" var="cp">
                                    <option value="${cp.cpId}">${cp.cpName}</option>
                                </c:forEach>
                            </select>
                        </sec:authorize>
                        <select id="searchType" name="searchType" class="form-control">
                            <option value="title">${titleLabel}</option>
                            <option value="id">ID</option>
                        </select>
						<input type="text" id="title" name="title" class="form-control" placeholder="${searchLabel}">
						<select id="contentSubsetType" name="contentSubsetType" class="form-control">
							<option value="">-- <spring:message code="contentSubset.contentSubsetType"/> --</option>
                            <ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_CONTENT_SUBSET_TYPE')}" />
						</select>
                        <select name="fileType" id="fileType" class="form-control">
                            <option value=""><spring:message code="select.all" /></option>
                            <c:forEach items="${fileTypeList}" var="fileType">
                                <option value="${fileType.fileType}">${fileType.fileTypeName}</option>
                            </c:forEach>
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
                        <select id="dateSearchType" class="form-control">
                            <option value="term"><spring:message code="contentSubset.availablePeriod"/></option>
                            <option value="time"><spring:message code="tree.menu.createTime"/></option>
                        </select>
						: &nbsp;
						<div id="termOpt" class="input-group" style="float:right;">
                            <label class="checkbox-inline">
                                <input type="checkbox" id="searchOptionPeriodPast" name="searchOptionPeriodPast" checked="checked" class="px"> <span class="lbl"><spring:message code="search.option.searchOptionPeriodPast"/></span>
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="searchOptionPeriodPresent" name="searchOptionPeriodPresent" checked="" class="px"> <span class="lbl"><spring:message code="search.option.searchOptionPeriodPresent"/></span>
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="searchOptionPeriodFuture" name="searchOptionPeriodFuture" checked="" class="px"> <span class="lbl"><spring:message code="search.option.searchOptionPeriodFuture"/></span>
                            </label>
                        </div>
						<div style="float:right;">
	                        <div id="timeOpt" class="input-group">
	                            <input id="startTime" name="startTime" class="input-sm form-control" type="text" placeholder="<spring:message code="epg.to.tvod.start.time"/>(From)" value="${scFromDate}">
	                            <span class="input-group-addon">-</span>
	                            <input id="endTime" name="endTime" class="input-sm form-control" type="text" placeholder="<spring:message code="epg.to.tvod.end.time"/>(To)" value="${scToDate}">
	                        </div>
                        </div>
					</td>
					<td>
                        &nbsp;
                        <button type="button" class="btn btn-default" id="excel">Excel</button>
                        &nbsp;
                    </td>
				</tr>
			</table>
		</div>
		
	</div>
	
	<a name="detailView"></a>
	<div class="row" id="detailDiv" style="display:none">
	</div>
	
	<form name="cmdForm" id="cmdForm">
		<input type="hidden" name="contentSubsetId" value="">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

</body>
</html>