<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.contract.manage" /></title>
<script language="JavaScript">
	$(document).ready(function() {
		$('#contractPoupupDatatable tbody').on( 'click', 'button', function () {
        	var tr = $(this).closest('tr');
        	setContractId(tr.find('td:first').text(), tr.find('td:eq(1)').text());
		} );
	});
</script>
</head>
<body>
	
	<div class="panel-heading">
		<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="contract.list"/></span>
	</div>
	<div class="panel-body">
		
		
		<div class="row">
			<div class="col-sm-12">
				
				<div class="table-primary">
					<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="contractPoupupDatatable">
						<thead>
							<tr>
								<th><spring:message code="subscriber.contract.id"/></th>
								<th><spring:message code="contract.name"/></th>
								<th><spring:message code="button.select"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${contractList}" var="contract">
								<tr class="odd gradeX">
									<td>${contract.contractInternalId}</td>
									<td>${contract.contractName}</td>
									<td>
										<button class="btn btn-primary btn-sm"><spring:message code="button.select"/></button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
			</div>
		</div>
		
		<div class="form-group" style="margin-bottom: 0;">
			<div class="col-sm-offset-2 col-sm-10" align="right">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
			</div>
		</div>
	</div>

</body>
</html>