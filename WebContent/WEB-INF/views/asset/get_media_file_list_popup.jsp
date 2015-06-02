<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.contentAsset" /></title>
<script language="javascript">
	$(document).ready(function () {
		var table = $('#mediaFileDatatable').DataTable({
			"columnDefs": [
				{"sortable": false, "targets": -1}
			],
			"order": [[0, 'asc']]
		});
		
		$('#mediaFileDatatable_wrapper .table-caption').text('');
		//<spring:message code="contentSubset.fileName" var="fileNameLabel"/>
		$('#mediaFileDatatable_wrapper .dataTables_filter input').attr('placeholder', '${fileNameLabel}');
		
		$('#mediaFileDatatable tbody').on( 'click', 'button', function () {
			var tr = $(this).closest('tr');
			setSourceFileName("${param.inputElementId}", tr.find('td:first').text(), tr.find('td:first input').val());
	    });
	});
</script>
</head>
<body>
	
	<div class="row">
		<div class="col-sm-12">
			
			<div class="table-primary">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="mediaFileDatatable">
					<thead>
						<tr>
							<th>${fileNameLabel}</th>
							<th><spring:message code="button.edit"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${labelValueBeanList}" var="bean">
							<tr class="odd gradeX">
								<td>
									<input type="hidden" value="${bean.value}"/>
									${bean.label}
								</td>
								<td>
									<button class="btn btn-primary btn-sm"><spring:message code="button.select"/></button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div align="right">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
			</div>
			
		</div>
	</div>

</body>
</html>