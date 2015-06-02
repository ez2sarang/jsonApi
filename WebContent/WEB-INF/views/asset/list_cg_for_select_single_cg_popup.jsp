<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 2014. 10. 20.
  Time: 오후 2:09
  To change this template use File | Settings | File Templates.
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ include file="/WEB-INF/views/common/includes.jsp"
%><%@ taglib prefix="zm" uri="ziminTag"
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
        .zimin-pcg-right {
            float:right;
        }
    </style>
<!--     <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script> -->
    <script src="${ctxRoot}/js/jquery-ui.min-1.11.1.js"></script>
    <script src="${ctxRoot}/js/tree.js"></script>
    <script src="${ctxRoot}/js/contextMenu.min.js"></script>
    <script src="${ctxRoot}/js/moment.js"></script>
    <script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
    <script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>
    <script language="javascript">
        var bannerTypeMap = {};
        var linkTypeMap = {};
        $(document).ready(function () {
            var table = $('#userDatatable').DataTable({
                "columns": [
                    { "data": "contentGroupId" }
                    , { "data": "titleBrief" }
                    , { "data": "startDateTime" }
                    , { "data": null }
                    , { "data": "endDateTime" }
                ]
                , "columnDefs": [
                    { "visible": false, "targets": -1 }
                    , { "width": "270px", "targets": 2 }
                    , { "width": "60px", "targets": 3 }
                    , {
                        "render": function (data, type, row) {
                            return data + ' ~ '+ row.endDateTime;
                        }
                        , "targets": 2
                      }
                    , {
                    	"render": function (data, type, row) {
    						//return '<button type="button" class="btn btn-primary btn-sm" onClick="setPromotionalContentGroup(' + row.contentGroupId + ',\'' + escape(row.titleBrief) + '\')"><spring:message code="button.select"/></button>';
    						return '<button type="button" class="btn btn-primary btn-sm"><spring:message code="button.select"/></button>';
    	                },
    	                "targets": 3
                      }
                ]
                , "searching": false
                , "sort" : false
                , "dom": '<"table-header clearfix"<"zimin-left"<"DT-per-page"l>><"DT-lf-right"<"zimin-pcg-right">>>t<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>'
                , "processing": true
                , "serverSide": true
                , "ajax": {
                    "url": "${ctxRoot}/asset/listContentGroup.json"
                    , "type": "POST"
                    , "data": function ( d ) {
                        d.titleBrief = $('#searchForm #titleBrief').val();
                        d.cpId = $('#searchForm #cpId').val();
                        d.searchOptionPeriodPast = 'false';
                        d.searchOptionPeriodPresent = 'true';
                        d.searchOptionPeriodFuture = 'true';
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
            $('.zimin-pcg-right').html($('#cg_search').html());

            $("#searchForm #searchButton").click(function() {
            	if ($("#searchForm #titleBrief").val().length > 0
    					&& $("#searchForm #titleBrief").val().length < 2) {
    				openAlert('Alert!!', '<spring:message code="errors.search.moreCharacters" arguments="2"/>', function() {
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
            
            $('#userDatatable tbody').on( 'click', 'button', function () {
            	var tr = $(this).closest('tr');
            	setSingleContentGroup('${inputElementId}', tr.find('td:first').text(), tr.find('td:eq(1)').text());
			} );
        });
    </script>
</head>
<body>


    <div class="row" id="listDiv">
        <form id="searchForm">
        <div class="table-primary">
            <table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="userDatatable">
                <thead>
                <tr>
                    <th>ID</th>
                    <th><spring:message code="serviceAsset.contentGroup.titleBrief"/></th>
                    <th><spring:message code="serviceAsset.contentGroup.validTerm"/></th>
                    <th><spring:message code="button.select"/></th>
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
                    <td>
                        <spring:message code="button.search" var="searchLabel"/>
                        <input type="text" id="titleBrief" name="titleBrief" class="form-control" placeholder="<spring:message code="serviceAsset.contentGroup.titleBrief"/> ${searchLabel}">
                    </td>
                    <td>
                        &nbsp;
                        <button type="button" class="btn btn-default" id="searchButton">${searchLabel}</button>
                        &nbsp;
                    </td>
                </tr>
            </table>
        </div>
        <div class="pull-right">
            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
        </div>
    </div>
</body>
</html>