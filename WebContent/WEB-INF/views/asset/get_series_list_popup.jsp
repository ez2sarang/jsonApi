<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.series"/></title>
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
			],
			"columnDefs": [
            <c:choose>
                <c:when test="${param.popupType=='selectBox'}">
                {
                    "render": function (data, type, row) {
                        return '<a href="javascript:selectBox(\'' + row.seriesId + '\', \'' + data + '\')">' + data + '</a>';
                    },
                    "targets": 1
                }
                </c:when>
                <c:otherwise>
                {
                    "render": function (data, type, row) {
                        return '<button type="button" class="btn btn-primary btn-xs" onClick="editSeries(' + row.seriesId + ')"><spring:message code="button.edit"/></button>';
                    },
                    "targets": 2
                }
                </c:otherwise>
            </c:choose>
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
	<div class="row">
		<form:form modelAttribute="series" id="seriesForm" >
			<div class="col-sm-12">
			
				<div class="table-primary">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="seriesDatatable">
						<thead>
							<tr>
								<th>ID</th>
								<th><spring:message code="series.name" var="seriesNameLabel"/>${seriesNameLabel}</th>
							</tr>
						</thead>
						
					</table>
				</div>
				
				<div align="right">
                    <c:choose>
                        <c:when test="${param.popupType=='selectBox'}"><button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button></c:when>
                        <c:otherwise><button type="button" class="btn btn-primary" onclick="addSeries();"><spring:message code="series.title.add"/></button></c:otherwise>
                    </c:choose>
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