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

<script language="javascript">
	var termsTargetMap = {};



    function addRow() {
        $('.search-tabs').find("a:contains('Offer') > span").text($("#selectDiv tr").length);
        if($('#selectDiv table tr').length>0) {
            $("#search-tabs-offer tbody tr").each(function(){
                if($(this).find("input").length>0) {
                    $(this).remove();
                } else {
                    $(this).css("display","none");
                }
            });
        }
        $('#search-tabs-offer tbody').append($('#selectDiv table tr'));
        $('#selectDiv').remove();
    }
	$(document).ready(function () {
        $('#search-tabs-offer tbody tr').each(function(){
            if($(this).find(".btn-primary").length>0) {
                $('#selectDiv table').append($(this).clone());
            }
        });

		// 공통코드 적재
		<c:forEach items="${termsTargetCodeList}" var="termsTarget">
			termsTargetMap['${termsTarget.code}'] = '${termsTarget.codeName}';
		</c:forEach>
		
		var table = $('#offerDatatable').DataTable({
			"columns": [
			            { "data": "offerId" },
			            { "data": "title" },
			            { "data": "termsTarget" },
			            { "data": "price" },
			            { "data": "cpId" },
			            { "data": "startDateTime"},
			            { "data": "endDateTime" }
			],
			"columnDefs": [
				{"visible": false, "targets": [-1]},
				{"width": "150px", "targets": [4]},
				{"width": "270px", "targets": [5]},
                {
                    "render": function (data, type, row) {
                        return data + "<input type=hidden name='offerId' value='"+data+"'>";
                    },
                    "targets": 0
                },
				{
					"render": function (data, type, row) {
                        if($('#selectDiv table input[value=' + row.offerId + ']').length>0) {
                            return data + "<input id='"+row.offerId+"' refer='"+row.offerId+"' type=button class='btn btn-primary btn-xs' value='<spring:message code="button.add"/>' onclick='selectRow(this,[2,4])' style='display:none'>";
                        } else {
                            return data + "<input id='"+row.offerId+"' refer='"+row.offerId+"' type=button class='btn btn-primary btn-xs' value='<spring:message code="button.add"/>' onclick='selectRow(this,[2,4])'>";
                        }
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
	                d.title = $('#offerListForm #title').val();
	                d.offerId = $('#offerListForm #offerId').val();
	                //d.termsTarget = $('#offerListForm #termsTarget').val();
	                if (($('#offerListForm #searchOptionPeriodPast').is(":checked")))
	                	d.searchOptionPeriodPast = 'true';
	                if (($('#offerListForm #searchOptionPeriodPresent').is(":checked")))
	                	d.searchOptionPeriodPresent = 'true';
	                if (($('#offerListForm #searchOptionPeriodFuture').is(":checked")))
	                	d.searchOptionPeriodFuture = 'true';
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
	});
	
</script>
</head>
<body>
	<div class="row" id="listDivPopup">
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
						<input type="text" id="title" name="title" class="form-control" placeholder="${titleLabel} ${searchLabel}">
					</td>
					<td>
						&nbsp;
						<button type="button" class="btn btn-default" id="searchButton">${searchLabel}</button>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="padding-top: 10px; text-align: right;">
						<spring:message code="offer.availablePeriod"/>: &nbsp;
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
		<input type="hidden" name="offerId" value="">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

    <div class="row" id="selectDiv">
        <table class="table table-striped table-bordered dataTable no-footer"></table>
    </div>

</body>
</html>