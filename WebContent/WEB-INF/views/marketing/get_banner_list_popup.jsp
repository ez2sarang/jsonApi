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
    <%--<script type="text/javascript" language="javascript" src="https://jquery-datatables-column-filter.googlecode.com/svn/trunk/media/js/jquery.dataTables.columnFilter.js"></script>--%>
    <%--<script src="//cdn.datatables.net/1.9.4/js/jquery.dataTables.js"></script>--%>
<script language="javascript">
	var bannerTypeMap = {};
	var linkTypeMap = {};

    $.fn.dataTableExt.afnFiltering.push(
            function( oSettings, aData, iDataIndex ) {
                alert(aData[1]);
                if (false) {
                    var iMin = 0;//document.getElementById('min').value * 1;
                    var iMax = 9;//document.getElementById('max').value * 1;
                    var iVersion = aData[0] == "-" ? 0 : aData[0]*1;
                    if ( iMin == "" && iMax == "" )
                    {
                        return true;
                    }
                    else if ( iMin == "" && iVersion < iMax )
                    {
                        return true;
                    }
                    else if ( iMin < iVersion && "" == iMax )
                    {
                        return true;
                    }
                    else if ( iMin < iVersion && iVersion < iMax )
                    {
                        return true;
                    }
                    return false;
                }
                return aData[1].indexOf("04")>=0
            }
    );



    function addRow() {
        $('.search-tabs').find("a:contains('Banner') > span").text($("#selectDiv tr").length);
        if($('#selectDiv table tr').length>0) {
            $("#search-tabs-banner tbody tr").each(function(){
                if($(this).find("input").length>0) {
                    $(this).remove();
                } else {
                    $(this).css("display","none");
                }
            });
        }
        $('#search-tabs-banner tbody').append($('#selectDiv table tr'));
        $('#selectDiv').remove();
    }

	$(document).ready(function () {
        $('#search-tabs-banner tbody tr').each(function(){
            if($(this).find(".btn-primary").length>0) {
                $('#selectDiv table').append($(this).clone());
            }
        });

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
            /*"rowCallback": function( row, data, displayIndex, displayIndexFull ) {
                return ( data[1].indexOf("04") >= 0 );
                    //$('td:eq(4)', row).html( '<b>A</b>' );
                    //row.remove();
                //}
            },*/
			"columnDefs": [
				{"visible": false, "targets": [-1]},
				//{"width": "150px", "targets": [4]},
				//{"width": "270px", "targets": [5]},
                {
                    "render": function (data, type, row) {
                        return data + "<input type=hidden name='bannerId' value='"+data+"'>";
                    },
                    "targets": 0
                },
				{
					"render": function (data, type, row) {
						//return '<a href="#" onclick="$(\'#selectDiv table\').append($(this).closest(\'tr\').clone());//this.disabled=true;//(\'' + row.bannerId + '\')">' + data + '</a>';
                        if($('#selectDiv table input[value=' + row.bannerId + ']').length>0) {
                            return data + "<input id='"+row.bannerId+"' refer='"+row.bannerId+"' type=button class='btn btn-primary btn-xs' value='<spring:message code="button.add"/>' onclick='selectRow(this,[2,3,4])' style='display:none'>";
                        } else {
                            return data + "<input id='"+row.bannerId+"' refer='"+row.bannerId+"' type=button class='btn btn-primary btn-xs' value='<spring:message code="button.add"/>' onclick='selectRow(this,[2,3,4])'>";
                        }
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
/*            "aoColumnDefs":[
                {"searchable": false, "aTargets": 0}
            ],*/
            //"oSearch": {"bSmart": false},
			"searching": true,
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

        try {
            //table.fnFilter('04',1,false);
            table.fnFilter('11',0);
            //$.fn.dataTable.ext.search.push(
            table.ext.search.push(
            //table.dataTableExt.afnFiltering.push(
                    function( oSettings, aData, iDataIndex ) {
                        return aData[1].indexOf("04")>=0;
                    }
            );
        } catch (e) {
            //alert(e);
        }

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
	});
</script>
</head>
<body>
	<div class="row" id="listDivPopup">
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
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$('#selectDiv').remove();"><spring:message code="button.close"/></button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="addRow()"><spring:message code="button.select"/></button>
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
	
	<form name="cmdForm" id="cmdForm">
		<input type="hidden" name="bannerId" value="">
		<input type="hidden" name="idx" value="">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

    <div class="row" id="selectDiv">
        <table class="table table-striped table-bordered dataTable no-footer"></table>
    </div>
</body>
</html>