<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 2014. 10. 20.
  Time: 오후 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ taglib prefix="zm" uri="ziminTag"%>
<%@ taglib prefix="const" uri="Constants"%>
<html>
<head>
	<title><spring:message code="menu.qc.offer" var="title"/>${title}</title>
	<style type="text/css" class="init">
		.zimin-left {
			float:left;
		}
		.zimin-right {
			float:right;
		}
	</style>
	
	<script>
		$(document).ready(function() {
			var table = $('#userDatatable').DataTable({
				"searching": true,
				"sort" : false,
				"dom": '<"table-header clearfix"<"zimin-left"<"DT-per-page"l>><"DT-lf-right"<"zimin-right">>>t<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>',
				"processing": true,
				"serverSide": true,
				"ajax": {
					"url": "${ctxRoot}/serviceflow/requestQcList.json",
					"type": "POST",
					"data": function(d){
						d.stageCode = "${const:get('COMMONCODE', 'STAGE_CODE_READY_APPROVAL')}";
					},
					"beforeSend": function(jqXHR, settings){
						jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
						
						try {
							$("#searchForm #searchButton").prop("disabled", true);
						}
						catch(e) {}
					},
					"complete": function(jqXHR, textStatus) {
						try {
							$("#searchForm #searchButton").prop("disabled", false);
						} catch(e) {}
					}
				},
				"columns": [
					{ "data": "offerId" },
					{ "data": "offerId" },
					{ "data": "title" },
					{ "data": "promotionalContentGroup" },
					{ "data": "category" },
					{ "data": "cpName" },
					{ "data": "requestTime" },
					{ "data": "stage" },
					{ "data": "function" }
				],
				"columnDefs": [
					{
						"render": function (data, type, row) {
							return String.format("<label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"offerId\" value='{0}' class=\"px ckb\"/><span class=\"lbl\">&nbsp;</span></label>", row.offerId);
						}, "targets": 0
					},
					{
						"render": function (data, type, row) {
// 							return '<a href="javascript:viewDetail(\'' + row.pcgRef + '\')">' + data + '</a>';
							return '<a href="javascript:showContentGroupDetail(' + row.pcgRef + ')">' + data + '</a>';
						}, "targets": 3
                    },
					{
						"render": function (data, type, row) {
							return String.format("<input type='button' class='btn btn-primary btn-xs moveButton' onclick='openWindow(\"${ctxRoot}/serviceflow/listMediaAsset.do?offerId={0}\",\"video\",\"700\",\"700\",\"scrollbars=1,resizable=1\");' value='file'/>", row.offerId);
						}, "targets": 8
                    }
				]
			});
			
			// 첫페이지는 search parameter 없이 요청됨
//	 		$('.zimin-right').html($('#cg_search').html());
			
			$(window).bind('hashchange', function () {
				var hash = window.location.hash.slice(1);
				if (hash == 'detailView') {
					$('#listDiv').hide();
					$('#detailDiv').show();
				} else {
					goList();
				}
			});
			
			$("#btnReject").on("click", function(){
				if($('input:checked[name=offerId]').map(function(){return this.value}).get().length == 0) {
					openAlert('','');
					
					openAlert('<spring:message code="result.noSelected" arguments="${zm:message(wmSource, 'service.deploy.status.offer.id')}"/>','');
					return;
				}
				
				messageValue = "";
				openConfirm('<spring:message code="confirm.reject" arguments="${zm:message(wmSource,'service.deploy.status.stage')}"/><br><textarea rows=3 id=reviewerMsg name=reviewerMsg onkeyup=\"messageValue=encodeURIComponent($(this).val());\" style=\"width:100%\" />',function(result) {
					if(result) {
						$("#btnConfirm").prop("disabled", true);
						$("#btnReject").prop("disabled", true);
						setBodyCursor("wait");
						
						try{
							$.ajax({
								type: "GET",
								url: "${ctxRoot}/serviceflow/rejectOffer.do",
								data: {
									offerId : $('input:checked[name=offerId]').map(function(){return this.value}).get().join(","),
									reviewerMsg: messageValue
									},
								//, contentType: "application/json; charset=UTF-8"
								success: function(data, textStatus, jqXHR){
									if (data == 'Success') {
										openInform('Success', data, function() {
											location.reload();
                                        });
									} else {
										openAlert('Fail', data, function() {
											
                                        });
									}
			                    	$("#btnConfirm").prop("disabled", false);
			                    	$("#btnReject").prop("disabled", false);
			                    	setBodyCursor("default");
								},
								error: function(jqXHR, textStatus, errorThrown) {
									openAlert('Fail', 'Failure:' + this.data+"<br>"+textStatus);
			                    	$("#btnConfirm").prop("disabled", false);
			                    	$("#btnReject").prop("disabled", false);
			                    	setBodyCursor("default");
								}
							});
						} catch(e) {
							alert (e);
	                    	$("#btnConfirm").prop("disabled", false);
	                    	$("#btnReject").prop("disabled", false);
	                    	setBodyCursor("default");
                        }
                    }
                });
            });
		
			$("#btnConfirm").on("click", function(){
				if($('input:checked[name=offerId]').map(function(){return this.value}).get().length == 0) {
					openAlert('<spring:message code="result.noSelected" arguments="${zm:message(wmSource, 'service.deploy.status.offer.id')}"/>','');
					return;
				}
				
				openConfirm('<spring:message code="confirm.confirm" arguments="${zm:message(wmSource,'service.deploy.status.stage')}"/>',function(result) {
					if(result) {
						
                    	$("#btnConfirm").prop("disabled", true);
                    	$("#btnReject").prop("disabled", true);
                    	setBodyCursor("wait");
                    	
                    	try{
                    		$.ajax({
                    			type: "GET",
                    			url: "${ctxRoot}/serviceflow/requestConfirmOffer.do",
                    			data: $('input[name=offerId]').serialize(),
                    			success: function(data, textStatus, jqXHR) {
                    				if (data == 'Success') {
                    					openInform('Success', data, function() {
                    						location.reload();
                    					});
                    				} else {
                    					openAlert('Fail', data, function() {
                    						
                                        });
                    				}
        	                    	$("#btnConfirm").prop("disabled", false);
        	                    	$("#btnReject").prop("disabled", false);
        	                    	setBodyCursor("default");
        	                    },
								error: function(jqXHR, textStatus, errorThrown) {
									openAlert('Fail', 'Failure:' + this.data+"<br>"+textStatus);
			                    	$("#btnConfirm").prop("disabled", false);
			                    	$("#btnReject").prop("disabled", false);
			                    	setBodyCursor("default");
			                    }
                            });
                    	} catch(e) {
                    		alert (e);
	                    	$("#btnConfirm").prop("disabled", false);
	                    	$("#btnReject").prop("disabled", false);
	                    	setBodyCursor("default");
                        }
                    }
				});
            });			
		});
		
		function goList() {
			$('#detailDiv').hide();
			$('#listDiv').show();
		}

		function checkAll(obj) {
			$(".ckb").each(function () {
				var v = this.value;
				$(this).closest(".checkbox-inline").html("<input type=\"checkbox\" name=\"offerId\" value=" + v + " class=\"px ckb\"" + (eval(obj.value)?" checked":"") + "/><span class=\"lbl\">&nbsp;</span>")
			});
			
			obj.value = !eval(obj.value);
        }
		
		function showContentGroupDetail(cgId) {
			var tUrl = "${ctxRoot}/asset/viewContentGroup.do?windowOption=popup&contentGroupId=" + cgId;
			openModalWindow('01', tUrl);
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
							<th>All <label class="checkbox-inline"><input type="checkbox" onclick='checkAll(this)' value="true" class="px"/><span class="lbl">&nbsp;</span></label></th>
							<th><spring:message code="offer.id"/></th>
							<th><spring:message code="offer.title"/></th>
							<th><spring:message code="offer.promotionalContentGroupRef"/></th>
							<th><spring:message code="offer.display"/></th>
							<th><spring:message code="offer.cpId"/></th>
							<th><spring:message code="service.deploy.request.time"/></th>
							<th><spring:message code="service.deploy.status.stage"/></th>
							<th><spring:message code="tree.menu.function"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</form>
<!-- 		<div id="cg_search" style="display:none"> -->
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<td> -->
<%-- 						<spring:message code="button.search" var="searchLabel"/> --%>
<!-- 					</td> -->
<!-- 					<td> -->
<%-- 						&nbsp;<input type="button" type="button" class="btn btn-default" id="searchButton" value="${searchLabel}">&nbsp; --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</div> -->
		<div class="pull-right">
			<button id="btnReject" type="button" class="btn btn-danger"><spring:message code="button.reject"/></button>
			<button id="btnConfirm" type="button" class="btn btn-primary"><spring:message code="button.request.confirm"/></button>
		</div>
		<a name="detailView"></a>
	</div>
	<div class="row" id="detailDiv" style="display:none">
	</div>
</body>
</html>