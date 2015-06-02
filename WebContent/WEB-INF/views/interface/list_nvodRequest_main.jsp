<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 2014. 10. 20.
  Time: 오후 2:09
  To change this template use File | Settings | File Templates.
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ include file="/WEB-INF/views/common/includes.jsp"
%><%@ page import="com.fast2.zimin.util.DateUtil"
%><%@ taglib prefix="zm" uri="ziminTag"
%><%@ taglib prefix="const" uri="Constants"
%><html>
<head>
    <title><spring:message code="menu.interfaces.nvod.assets" var="title"/>${title}</title>
    <style type="text/css" class="init">
        .zimin-left {
            float:left;
        }
        .zimin-right-main {
            float:right;
        }
    </style>
    <script src="${ctxRoot}/js/moment.js"></script>
    <script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
    <script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctxRoot}/js/zimin.jquery.extend.js"></script>
    <script language="javascript">
        $(document).ready(function () {
            var table = $('#userDatatable').DataTable({
                "columns": [
                    { "data": "id" }
                    , { "data": "contentAssetId" }
                    , { "data": "contentGroupId" }
                    , { "data": "cpId" }
                    , { "data": "contentGroupTitle", "orderable": true }
                    , { "data": "fileName" }
                    , { "data": "requestTime", "orderable": true }
                ]
                , "order" : [[0, "desc"]]
                , "columnDefs": [
                    { "visible": false, "targets": 0 }
                ]
                , "searching": true
                , "sort" : true
                , "dom": '<"table-header clearfix"<"zimin-left"<"DT-per-page"l>><"DT-lf-right"<"zimin-right-main">>>t<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>'
                , "processing": true
                , "serverSide": true
                , "ajax": {
                    "url": "${ctxRoot}/interface/listNvodRequest.json"
                    , "type": "GET"
                    , "data": function ( d ) {
                        d.contentGroupTitle = $('#searchForm #contentGroupTitle').val();
                        try {
                            d.startTime = $.isBlank($('#searchForm #startTime').val())?"":String.format("{0} 00:00:00", $('#searchForm #startTime').val());
                            d.endTime = $.isBlank($('#searchForm #endTime').val())?"":String.format("{0} 23:59:59", $('#searchForm #endTime').val());
                        } catch (e) {
                            alert("date:"+e);
                        }
                    }
                    , "beforeSend": function(jqXHR, settings) {
                        jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                        try {
                            $("#searchForm #searchButton").prop("disabled", true);
                            setCursor("btnReject", "wait");
                            setCursor("btnConfirm", "wait");
                        } catch(e) {}
                    }
                    , "complete": function(jqXHR, textStatus) {
                        try {
                            $("#searchForm #searchButton").prop("disabled", false);
                            setCursor("btnReject", "wait");
                            setCursor("btnConfirm", "wait");
                        } catch(e) {}
                    }
                }
            });

            // 첫페이지는 search parameter 없이 요청됨
            $('.zimin-right-main').html($('#nvod_search').html());

            $("#searchForm #searchButton").click(function() {
                try {
                    if(!$.isBlank($('#searchForm #startTime').val()) || !$.isBlank($('#searchForm #endTime').val())) {
                        try {
                            if($.isBlank($('#startTime').val())) {
                                openAlert('<spring:message code="errors.required" arguments="${zm:message(wmSource,'contentSubset.availablePeriod.start')}"/>','', function() {
                                });
                                return;
                            }
                        } catch (e) {
                            alert("search s:"+e);
                        }
                        try {
                            if($.isBlank($('#endTime').val())) {
                                openAlert('<spring:message code="errors.required" arguments="${zm:message(wmSource,'contentSubset.availablePeriod.end')}"/>','', function() {
                                });
                                return;
                            }
                        } catch (e) {
                            alert("search e:"+e);
                        }
                    }
                } catch (e) {
                    alert("search:"+e);
                }
                table.draw(true);
            });

            $("#searchForm #contentGroupTitle").keypress(function(e) {
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
            $("#btnAdd").on("click", function(){
                var tUrl = "${ctxRoot}/asset/listContentGroupForm.do?popupOption=multiSelectNavigation";
                openModalWindow('01', tUrl);
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
        });
        function viewDetail(id) {
            $('#listDiv').hide();
            location.href="#detailView";
            var tUrl = "${ctxRoot}/asset/viewOffer.do?offerId=" + id;
            $('#detailDiv').load(tUrl);
            $('#detailDiv').show();
        }
        function goList() {
            $('#detailDiv').hide();
            $('#listDiv').show();
        }
    </script>
</head>
<body>
<ul class="breadcrumb breadcrumb-page">
    <div class="breadcrumb-label text-light-gray">You are here:</div>
    <li><spring:message code="menu.interfaces"/></li>
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
                    <th><spring:message code="contentSubset.contentAssetId"/></th>
                    <th><spring:message code="serviceAsset.contentGroup.contentGroupId"/></th>
                    <th><spring:message code="offer.cpId"/></th>
                    <th><spring:message code="serviceAsset.contentGroup.titleBrief"/></th>
                    <th><spring:message code="contentSubset.fileName"/></th>
                    <th><spring:message code="tree.menu.createTime"/></th>
                </tr>
                </thead>
            </table>
        </div>
        <div id="nvod_search" style="display:none">
            <spring:message code="button.search" var="searchLabel"/>
            <div class="row">
                <div class="col-md-6">
                </div>
                <div class="col-md-3">
                    <div class="input-group">
                        <input id="startTime" name="startTime" class="input-sm form-control" type="text" placeholder="<spring:message code="epg.to.tvod.start.time"/>(From)" value="${scFromDate}">
                        <span class="input-group-addon">-</span>
                        <input id="endTime" name="endTime" class="input-sm form-control" type="text" placeholder="<spring:message code="epg.to.tvod.end.time"/>(To)" value="${scToDate}">
                    </div>
                </div>
                <div class="col-md-2">
                    <input type="text" id="contentGroupTitle" name="contentGroupTitle" class="form-control input-normal" placeholder="<spring:message code="serviceAsset.contentGroup.titleBrief"/>">
                </div>
                <div class="col-md-1">
                    <input type="button" type="button" class="btn btn-default form-control" id="searchButton" value="${searchLabel}">
                </div>
            </div>
        </div>
        </form>
        <div class="pull-right">
            <button id="btnAdd" type="button" class="btn btn-primary"><spring:message code="button.add"/></button>
        </div>
        <a name="detailView"></a>
    </div>
    <div class="row" id="detailDiv" style="display:none">
    </div>
</body>
</html>