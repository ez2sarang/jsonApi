<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.offer"/></title>
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
<script src="${ctxRoot}/js/jquery-ui.min-1.11.1.js"></script>
<script src="${ctxRoot}/js/tree.js"></script>
<script src="${ctxRoot}/js/contextMenu.min.js"></script>
<script src="${ctxRoot}/js/moment.js"></script>
<script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
<script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>
<script language="javascript">
	var termsTargetMap = {};
	var stageCodeMap = {};

	$(document).ready(function () {
		// 공통코드 적재
		<c:forEach items="${termsTargetCodeList}" var="termsTarget">
			termsTargetMap['${termsTarget.code}'] = '${termsTarget.codeName}';
		</c:forEach>
		<c:forEach items="${stageCodeList}" var="stageCode">
			stageCodeMap['${stageCode.code}'] = '${stageCode.codeName}';
		</c:forEach>
		
		var table = $('#offerDatatable').DataTable({
			"columns": [
			            { "data": "offerId" },
			            { "data": "title" },
			            { "data": "termsTarget" },
			            { "data": "price" },
			            { "data": "cpId" },
			            { "data": "startDateTime"},
			            { "data": "endDateTime" },
			            { "data": "stageCode" }
			],
			"columnDefs": [
				{"visible": false, "targets": [-2]},
				{"width": "270px", "targets": [5]},
				{
					"render": function (data, type, row) {
						return '<a href="javascript:viewDetail(\'' + row.offerId + '\')">' + data + '</a>';
	                },
	                "targets": 1
				},
				{
					"render": function (data, type, row) {
	                    return termsTargetMap[data];
	                },
	                "targets": 2
				},
				{
					"render": function (data, type, row) {
	                    return data + ' ~ '+ row.endDateTime;
	                },
	                "targets": 5
				},
				{
					"render": function (data, type, row) {
	                    return stageCodeMap[data];
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
	            "url": "${ctxRoot}/asset/getOfferList.json",
	            "type": "POST",
	            "data": function ( d ) {
                    try {
                        //<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        	d.adminSearch = "cp";
                        //</sec:authorize>
                        d.cpId = $("#offerListForm #cpId").val();
                        d.searchType = $("#offerListForm #searchType").val();
                        d.title = $('#offerListForm #title').val();
                        d.searchCategoryId = ('array' == typeOf($("#offerListForm #searchCategoryId").val())?$("#offerListForm #searchCategoryId").val().join(""):$("#offerListForm #searchCategoryId").val());
                        d.dateSearchType = $("#dateSearchType").val();
                        if($("#dateSearchType").val() == 'term') {
                            if (($('#offerListForm #searchOptionPeriodPast').is(":checked")))
                                d.searchOptionPeriodPast = 'true';
                            if (($('#offerListForm #searchOptionPeriodPresent').is(":checked")))
                                d.searchOptionPeriodPresent = 'true';
                            if (($('#offerListForm #searchOptionPeriodFuture').is(":checked")))
                                d.searchOptionPeriodFuture = 'true';
                        } else {
                            d.startTime = $.isBlank($('#startTime').val())?"":String.format("{0} 00:00:00", $('#startTime').val());
                            d.endTime = $.isBlank($('#endTime').val())?"":String.format("{0} 23:59:59", $('#endTime').val());
                            d.searchOptionPeriodPast = 'true';
                            d.searchOptionPeriodPresent = 'true';
                            d.searchOptionPeriodFuture = 'true';
                        }
                    } catch (e) {
                        alert("error:"+e);
                    }
	            },
	            "beforeSend": function(jqXHR, settings) {
			  		jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
			  		try {
			  			$("#offerListForm #searchButton").prop("disabled", true);
						setCursor("offerListForm", "wait");
			  		} catch(e) {}
			    },
			    "complete": function(jqXHR, textStatus){
					try {
						$("#offerListForm #searchButton").prop("disabled", false);
						setCursor("offerListForm", "default");
			  		} catch(e) {}
				}

	        }
		});
		
		// 첫페이지는 search parameter 없이 요청됨
		$('.zimin-right').html($('#temp_search').html());

		$("#offerListForm #searchButton").click(function() {
			if (!($('#offerListForm #searchOptionPeriodPast').is(":checked"))
					&& !($('#offerListForm #searchOptionPeriodPresent').is(":checked"))
					&& !($('#offerListForm #searchOptionPeriodFuture').is(":checked"))) {
				openAlert('Alert!!', '<spring:message code="offer.error.availablePeriod.atLeastOne"/>', function() {
				});
			} else if ($("#offerListForm #title").val().length > 0
					&& $("#offerListForm #title").val().length < 2) {
				openAlert('Alert!!', '<spring:message code="errors.search.moreCharacters" arguments="2"/>', function() {
				});
			} else {
				table.draw(true);
			}
		});
		
		$("#offerListForm #title").keypress(function(e) {
			if(e.keyCode == 13) {
				e.preventDefault();
				$("#offerListForm #searchButton").trigger("click");
		    }
		});
		
		$("#offerListForm #offerId").keypress(function(e) {
			if(e.keyCode == 13) {
				e.preventDefault();
				$("#offerListForm #searchButton").trigger("click");
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

        $("#categoryBtn").click(function() {
            var tUrl = "${ctxRoot}/category/categoryListPopup.do?popupType=categorySelectBox&targetId=searchCategoryId";
            openModalWindow('02', tUrl);
        });
        $("#searchCategoryId").select2({placeholder: '<spring:message code="offer.display" />'/*, width: 150*/});

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
            params += String.format("cpId={0}", $("#cpId").val());
            //<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
            	params += "&adminSearch=cp";
            //</sec:authorize>
            params += String.format("&searchType={0}", $("#searchType").val());
            params += String.format("&title={0}", $('#offerListForm #title').val());
            params += String.format("&searchCategoryId={0}", $("#offerListForm #searchCategoryId").val());
            params += String.format("&dateSearchType={0}", $("#dateSearchType").val());
            if($("#dateSearchType").val() == 'term') {
                if (($('#searchOptionPeriodPast').is(":checked")))
                    params += String.format("&searchOptionPeriodPast={0}", 'true');
                if (($('#searchOptionPeriodPresent').is(":checked")))
                    params += String.format("&searchOptionPeriodPresent={0}", 'true');
                if (($('#searchOptionPeriodFuture').is(":checked")))
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
                url: "${ctxRoot}/asset/getOfferList.json",
                beforeSend: function(jqXHR, settings) {
                    jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
//                    jqXHR.setRequestHeader('Accept', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
                },
                data : params.replace("null", ""),
                success: function(data, textStatus, jqXHR){
                    try {
                        columnsDef = {"offerId":"Number"
                            , "title": {
                                "render": function(data) {
                                    return String.format("<![CDATA[{0}]]>", data);
                                }
                                , "type":"String"
                            }
                            , "revision":"String"
                            , "revisionDateTime": {
                                "render": function (data) {
                                    return new Date(data);
                                }
                                , "type":"String"
                            }
                            , "revisionCreatorId":"String"
                            , "cpId":"String"
                            , "startDateTime":"String"
                            , "endDateTime":"String"
                            , "termsTarget": {
                                "render": function (data) {
                                    return termsTargetMap[data];
                                }
                                , "type":"String"
                            }
                            , "price":"String"
                            , "stageCode": {
                                "render": function (data) {
                                    return stageCodeMap[data];
                                }
                                , "type":"String"
                            }
                        };
                        var blob = new Blob([jsonToSsXml(data.data, columnsDef)], {
                            'type': 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
                        });
                        var url = URL.createObjectURL(blob);
                        var link = document.createElement("a");
                        link.setAttribute("href", url);
                        link.setAttribute("download", 'offer.xls'||"Download.xls");
                        var event = document.createEvent('MouseEvents');
                        event.initMouseEvent('click', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
                        link.dispatchEvent(event);
                    } catch (e) {
                        alert(String.format("--error message({0})--\n{1}", (new Error(e)).lineNumber, e.stack));
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

    function InitClick (event) {
        try {
            if (event.initMouseEvent) {     // all browsers except IE before version 9
                var clickEvent = document.createEvent ("MouseEvent");
                clickEvent.initMouseEvent ("click", true, true, window, 0,
                        event.screenX, event.screenY, event.clientX, event.clientY,
                        event.ctrlKey, event.altKey, event.shiftKey, event.metaKey,
                        0, null);
                event.target.dispatchEvent (clickEvent);
            } else {
                if (document.createEventObject) {   // IE before version 9
                    var clickEvent = document.createEventObject (window.event);
                    clickEvent.button = 1;  // left click
                    event.srcElement.fireEvent ("onclick", clickEvent);
                }
            }
        } catch (e) {
            alert("e---"+e.stack);
        }
    }
	
	function goList() {
		$('#detailDiv').hide();
		$('#listDiv').show();
	}
	
	function viewDetail(offerId) {
		$('#listDiv').hide();
		location.href="#detailView";
		var tUrl = "${ctxRoot}/asset/viewOffer.do?offerId=" + offerId;
		$('#detailDiv').load(tUrl);
		$('#detailDiv').show();
	}
	
	function addOffer() {
		var tUrl = "${ctxRoot}/asset/addOfferForm.do";
		openModalWindow('01', tUrl);
	}
	
	function editOffer(offerId) {
		var tUrl = "${ctxRoot}/asset/editOfferForm.do?offerId=" + offerId;
		openModalWindow('01', tUrl);
	}
	
	function deleteOffer(offerId) {
		// <spring:message code="offer.info" var="offerLabel"/>
		openConfirm('<spring:message code="confirm.delete" arguments="${offerLabel}"/>',function(result) {
			if(result) {
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/asset/deleteOffer/" + offerId + ".do",
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
		<li class="active"><spring:message code="menu.serviceAsset.offer"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa  fa-picture-o page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.serviceAsset.offer"/></h1>
		</div>
	</div> <!-- / .page-header -->
	
	<div class="row" id="listDiv">
		<form:form modelAttribute="offer" id="offerListForm">
			<div class="col-sm-12">
			
				<div class="table-primary">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="offerDatatable">
						<thead>
							<tr>
								<th>ID</th>
								<th><spring:message code="offer.title" var="titleLabel"/>${titleLabel}</th>
								<th><spring:message code="offer.termsTarget"/></th>
								<th><spring:message code="offer.price"/></th>
								<th><spring:message code="offer.cpId"/></th>
								<th><spring:message code="offer.availablePeriod"/></th>
								<th><spring:message code="offer.availablePeriod"/></th>
								<th><spring:message code="serviceAsset.contentGroup.stageCode"/></th>
							</tr>
						</thead>
						
					</table>
				</div>
				
				<div align="right">
					<button type="button" class="btn btn-primary" onclick="addOffer();"><spring:message code="offer.button.add"/></button>
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
                            <option value="price"><spring:message code="offer.price"/></option>
                        </select>
						<input type="text" id="title" name="title" class="form-control" placeholder="${searchLabel}">
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                            <div class="input-group zimin-right">
                                <div class="select2-success">
                                    <select id="searchCategoryId" name="searchCategoryId" class="orm-control" multiple="multiple" >
                                    </select>
                                </div>
                            <span class="input-group-btn">
                                <button class="btn" type="button" id="categoryBtn" ><spring:message code="button.select" /></button>
                            </span>
                            </div>
                        </sec:authorize>
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
                            <option value="term"><spring:message code="offer.availablePeriod"/></option>
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
		<input type="hidden" name="offerId" value="">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

</body>
</html>