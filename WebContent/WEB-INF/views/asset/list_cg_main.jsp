<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 2014. 10. 20.
  Time: 오후 2:09
  To change this template use File | Settings | File Templates.
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ include file="/WEB-INF/views/common/includes.jsp"
%><%@ taglib prefix="zm" uri="ziminTag"
%><%@ taglib prefix="const" uri="Constants"
%><%@ page import="com.fast2.zimin.util.DateUtil"
%><html>
<head>
    <title><spring:message code="menu.serviceAsset.contentGroup" var="title"/>${title}</title>
    <link href="${ctxRoot}/css/tree.css" rel="stylesheet" type="text/css">
    <link href="${ctxRoot}/css/contextMenu.css" rel="stylesheet" type="text/css">
    <link href="${ctxRoot}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <style type="text/css" class="init">
        .zimin-left {
            float:left;
        }
        .zimin-right {
            float:right;
        }
    </style>
<!--     <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script> -->
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
        var commonCode;
        callbackCommonCode('${const:get('COMMONCODE', 'GROUP_CODE_STAGE_CODE')},${const:get('COMMONCODE', 'GROUP_CODE_SERVICE_STATUS')}', function (jsonObj){commonCode=jsonObj});
        $(document).ready(function () {
            var table = $('#userDatatable').DataTable({
                "columns": [
                    { "data": "contentGroupId" }
                    , { "data": "cpId" }
                    , { "data": "titleBrief" }
                    , { "data": "summaryShort" }
                    , { "data": "startDateTime" }
                    , { "data": "rating"}
                    , { "data": "stageCode" }
                    , { "data": "endDateTime" }
                ]
                , "columnDefs": [
                    { "visible": false, "targets": -1 }
                    , { "width": "30px", "targets": 0 }
                    , { "width": "60px", "targets": 1 }
                    , {
                        "render": function (data, type, row) {
                            return '<a href="javascript:viewDetail(\'' + row.contentGroupId + '\')">' + data + '</a>';
                        }
                        , "targets": 2
                      }
                    , {
                        "render": function (data, type, row) {
                            return data + ' ~ '+ row.endDateTime;
                        }
                        , "targets": 4
                      }
                    , {
                        "render": function (data, type, row) {
                            return getResultJson(commonCode, "${const:get('COMMONCODE', 'GROUP_CODE_STAGE_CODE')}", data, "codeName");
                        }
                        , "targets": 6
                      }
                ]
                , "searching": false
                , "sort" : false
                , "dom": '<"table-header clearfix"<"zimin-left"<"DT-per-page"l>><"DT-lf-right"<"zimin-right">>>t<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>'
                , "processing": true
                , "serverSide": true
                , "ajax": {
                    "url": "${ctxRoot}/asset/listContentGroup.json"
                    , "type": "POST"
                    , "data": function ( d ) {
                        searchType = $("#searchType").val();
                        switch (searchType) {
                            case 'title':
                                d.titleBrief = $('#titleBrief').val();
                                break;
                            case 'id':
                                d.contentGroupId = $('#titleBrief').val();
                                break;
                            case 'summary':
                                d.summaryShort = $('#titleBrief').val();
                                break;
                            case 'actor':
                                d.actor = $('#titleBrief').val();
                                break;
                            case 'writer':
                                d.writer = $('#titleBrief').val();
                                break;
                            case 'director':
                                d.director = $('#titleBrief').val();
                                break;
                            case 'producer':
                                d.producer = $('#titleBrief').val();
                                break;
                            default:
                                break;
                        }
                        d.cpId = $('#searchForm #cpId').val();
                        d.dateSearchType = $("#dateSearchType").val();
                        if($("#dateSearchType").val() == 'term') {
                            if (($('#searchForm #searchOptionPeriodPast').is(":checked")))
                                d.searchOptionPeriodPast = 'true';
                            if (($('#searchForm #searchOptionPeriodPresent').is(":checked")))
                                d.searchOptionPeriodPresent = 'true';
                            if (($('#searchForm #searchOptionPeriodFuture').is(":checked")))
                                d.searchOptionPeriodFuture = 'true';
                        } else {
                            d.startTime = $.isBlank($('#startTime').val())?"":String.format("{0} 00:00:00", $('#startTime').val());
                            d.endTime = $.isBlank($('#endTime').val())?"":String.format("{0} 23:59:59", $('#endTime').val());
                            d.searchOptionPeriodPast = 'true';
                            d.searchOptionPeriodPresent = 'true';
                            d.searchOptionPeriodFuture = 'true';
                        }
                    }
                    , "beforeSend": function(jqXHR, settings) {
                        jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                        try {
                            $("#searchForm #searchButton").prop("disabled", true);
                            setCursor("banner", "wait");
                        } catch(e) {}
                    }
                    , "complete": function(jqXHR, textStatus) {
                        try {
                            $("#searchForm #searchButton").prop("disabled", false);
                            setCursor("banner", "default");
                        } catch(e) {}
                    }
                }
            });

            // 첫페이지는 search parameter 없이 요청됨
            $('.zimin-right').html($('#cg_search').html());

            $("#searchForm #searchButton").click(function() {
                if (!($('#searchForm #searchOptionPeriodPast').is(":checked"))
                        && !($('#searchForm #searchOptionPeriodPresent').is(":checked"))
                        && !($('#searchForm #searchOptionPeriodFuture').is(":checked"))) {
                    openAlert('Alert!!', '<spring:message code="banner.error.licenceTime.atLeastOne"/>', function() {
                    });
                } else {
                    table.draw(true);
                }
            });

            $("#searchForm #titleBrief").keypress(function(e) {
                if(e.keyCode == 13) {
                    e.preventDefault();
                    $("#searchForm #searchButton").trigger("click");
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
            $("#buttonAdd").on("click", function(){
                //openModalWindow("01", "${ctxRoot}/asset/registCgForm.do")
                openModalWindow("01", "${ctxRoot}/asset/addContentGroupForm.do")
            });

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
                params += String.format("cpId={0}", $('#cpId').val());
                searchType = $("#searchType").val();
                switch (searchType) {
                    case 'title':
                        params += String.format("&titleBrief={0}", $('#titleBrief').val());
                        break;
                    case 'id':
                        params += String.format("&contentGroupId={0}", $('#titleBrief').val());
                        break;
                    case 'summary':
                        params += String.format("&summaryShort={0}", $('#titleBrief').val());
                        break;
                    case 'actor':
                        params += String.format("&actor={0}", $('#titleBrief').val());
                        break;
                    case 'writer':
                        params += String.format("&writer={0}", $('#titleBrief').val());
                        break;
                    case 'director':
                        params += String.format("&director={0}", $('#titleBrief').val());
                        break;
                    case 'producer':
                        params += String.format("&producer={0}", $('#titleBrief').val());
                        break;
                    default:
                        break;
                }
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
                    url: "${ctxRoot}/asset/listContentGroup.json",
                    beforeSend: function(jqXHR, settings) {
                        jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                    },
                    data : params.replace("null", ""),
                    success: function(data, textStatus, jqXHR){
                        try {
                            columnsDef = {"contentGroupId":"Number"
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
                                , "titleBrief": {
                                    "render": function(data) {
                                        return String.format("<![CDATA[{0}]]>", data);
                                    }
                                    , "type":"String"
                                }
                                , "summaryShort": {
                                    "render": function(data) {
                                        return String.format("<![CDATA[{0}]]>", data);
                                    }
                                    , "type":"String"
                                }
                                , "actor":"String"
                                , "writer":"String"
                                , "director":"String"
                                , "producer":"String"
                                , "rating":"String"
                                , "displayRunTime":"String"
                                , "stageCode": {
                                    "render": function (data) {
                                        return getResultJson(commonCode, "${const:get('COMMONCODE', 'GROUP_CODE_STAGE_CODE')}", data, "codeName");
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
                            link.setAttribute("download", 'contentGroup.xls'||"Download.xls");
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
        function viewDetail(id) {
            $('#listDiv').hide();
            location.href="#detailView";
            var tUrl = "${ctxRoot}/asset/viewContentGroup.do?contentGroupId=" + id;
            $('#detailDiv').load(tUrl);
            $('#detailDiv').show();
        }
        function goList() {
            $('#detailDiv').hide();
            $('#listDiv').show();
        }
        function editContentGroup(id) {
            //var tUrl = "${ctxRoot}/asset/registCgForm.do?contentGroupId=" + id;
            var tUrl = "${ctxRoot}/asset/editContentGroupForm.do?contentGroupId=" + id;
            openModalWindow('01', tUrl);
        }
        function deleteContentGroup(id) {
            openConfirm('<spring:message code="confirm.delete" arguments="${zm:message(wmSource,'serviceAsset.contentGroup.info')}"/>',function(result) {
                if(result) {
                    try{
                        $.ajax({
                            type: "GET"
                            , url: "${ctxRoot}/asset/deleteContentGroup.do"
                            , data: {contentGroupId:id}
                            , success: function(data, textStatus, jqXHR){
                                if (data == 'Success') {
                                    openInform('Success', data, function() {
                                        location.reload();
                                    });
                                } else {
                                    openAlert('Fail', data, function() {
                                    });
                                }
                            }
                            , error: function(jqXHR, textStatus, errorThrown){
                                openAlert('Fail', 'Failure:' + this.data+"<br>"+textStatus);
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
    <div class="breadcrumb-label text-light-gray">You are here:</div>
    <li><spring:message code="menu.serviceAsset"/></li>
    <li class="active">${title}</li>
</ul>
<div class="page-header">
    <div class="row">
        <!-- Page header, center on small screens -->
        <h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-picture-o page-header-icon"></i>&nbsp;&nbsp;${title}</h1>
    </div>
</div>
<!-- / .page-header -->

    <div class="row" id="listDiv">
        <form id="searchForm">
        <div class="table-primary">
            <table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="userDatatable">
                <thead>
                <tr>
                    <th>ID</th>
                    <th><spring:message code="serviceAsset.contentGroup.cpId"/></th>
                    <th><spring:message code="serviceAsset.contentGroup.titleBrief" var="titleLabel"/>${titleLabel}</th>
                    <th><spring:message code="serviceAsset.contentGroup.summaryShort"/></th>
                    <th><spring:message code="serviceAsset.contentGroup.validTerm"/></th>
                    <th><spring:message code="serviceAsset.contentGroup.rating"/></th>
                    <th><spring:message code="serviceAsset.contentGroup.stageCode"/></th>
                </tr>
                </thead>
            </table>
            <sec:authentication property="principal.cpId" var="cpId"/>
            <input type="hidden" id="cpId" name="cpId" value="${cpId}">
        </div>
        </form>
        <div id="cg_search" style="display:none">
            <table>
                <tr>
                    <td align="right">
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
                            <option value="summary"><spring:message code="serviceAsset.contentGroup.summaryShort"/></option>
                            <option value="actor"><spring:message code="serviceAsset.contentGroup.actor"/></option>
                            <option value="writer"><spring:message code="serviceAsset.contentGroup.writer"/></option>
                            <option value="director"><spring:message code="serviceAsset.contentGroup.director"/></option>
                            <option value="producer"><spring:message code="serviceAsset.contentGroup.producer"/></option>
                        </select>
                        <input type="text" id="titleBrief" name="titleBrief" class="form-control" placeholder="${searchLabel}">
                    </td>
                    <td>
                        &nbsp;
                        <button type="button" class="btn btn-default" id="searchButton">${searchLabel}</button>
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td style="padding-top: 10px; text-align: right;">
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <sec:authentication property="principal.cpId" var="cpId"/>
                            <input type="hidden" id="cpId" name="cpId" value="${cpId}"/>
                        </sec:authorize>
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
        <sec:authorize access="hasRole('ROLE_USER')">
        <div class="pull-right">
            <button id="buttonAdd" type="button" class="btn btn-primary"><spring:message code="serviceAsset.contentGroup"/> <spring:message code="button.add"/></button>
        </div>
        </sec:authorize>
        <a name="detailView"></a>
    </div>
    <div class="row" id="detailDiv" style="display:none">
    </div>
</body>
</html>