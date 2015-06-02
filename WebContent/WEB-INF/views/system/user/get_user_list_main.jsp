<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.system.user"/></title>
<script language="javascript">
	$(document).ready(function () {
		var table = $('#userDatatable').DataTable({
			"aoColumnDefs": [
				{"sortable": false, "aTargets": [0, -2, -1]},
				{"searchable": false, "aTargets": [0, 3, 4, 5, -2, -1]},
				{"visible": false, "aTargets": [-2]},
				{"width": "150px", "aTargets": [-1]}
			],
			"aaSorting": [[1, 'asc']]
		});
		
		$('#userDatatable_wrapper .table-caption').text('');
		//<spring:message code="user.name" var="nameLabel"/>
		//<spring:message code="user.id" var="idLabel"/>
		$('#userDatatable_wrapper .dataTables_filter input').attr('placeholder', '${nameLabel} or ${idLabel}');
		
		$('#userDatatable tbody').on( 'click', '#editButton', function () {
	        var data = table.row( $(this).parents('tr') ).data();
	        var tUrl = "${ctxRoot}/system/editUserForm.do"
	    		+ "?userId=" + data[2];
	        
			openModalWindow('01', tUrl);
	    } );
		
		$('#userDatatable tbody').on( 'click', '#passwordButton', function () {
	        var data = table.row( $(this).parents('tr') ).data();
	        var tUrl = "${ctxRoot}/system/editUserPasswordForm.do"
	    		+ "?userId=" + data[2];
	        
			openModalWindow('01', tUrl);
	    } );
		
		function format ( d ) {
		    return d[6];
		}
		
		// Add event listener for opening and closing details
	    $('#userDatatable tbody').on('click', 'td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = table.row( tr );
	        var i = $(this).find(' > i');
	 
	        if ( row.child.isShown() ) {
	            // This row is already open - close it
	            row.child.hide();
	            tr.removeClass('shown');
	            i.removeClass('fa fa-minus-square').addClass('fa fa-plus-square');
	        }
	        else {
	            // Open this row
	            row.child( format(row.data()) ).show();
	            tr.addClass('shown');
	            i.removeClass('fa fa-plus-square').addClass('fa fa-minus-square');
	        }
	    } );
	});
	
	function addUser() {
		var tUrl = "${ctxRoot}/system/addUserForm.do";
		openModalWindow('01', tUrl);
	}
</script>
</head>
<body>

	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.system"/></li>
		<li class="active"><spring:message code="menu.system.user"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa  fa-users page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.system.user"/></h1>
		</div>
	</div> <!-- / .page-header -->
	
	<div class="row">
		<div class="col-sm-12">
			
			<div class="table-primary">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="userDatatable">
					<thead>
						<tr>
							<th>&nbsp;</th>
							<th>${nameLabel}</th>
							<th>${idLabel}</th>
							<th><spring:message code="user.mobile"/></th>
							<th><spring:message code="user.userType"/></th>
							<th><spring:message code="user.state"/></th>
							<th></th>
							<th><spring:message code="button.edit"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userList}" var="user">
							<tr class="odd gradeX">
								<td class="details-control" style="cursor:pointer; text-align: center"><i class="fa fa-plus-square"></i></td>
								<td>${user.userName}</td>
								<td>${user.userId}</td>
								<td>${user.mobile}</td>
								<td>
									<ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_USER_TYPE')}" code="${user.userType}" />
								</td>
								<td>
									<ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_USER_STATE')}" code="${user.state}" />
								</td>
								<td>
									&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
									<spring:message code="user.email"/> : ${user.email}<br>
									&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
									<spring:message code="updater"/> : ${user.updateId}<br>
									&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
									<spring:message code="updateTime"/> : <fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>"
											value="${user.updateTime}" />
								</td>
								<td>
									<button class="btn btn-primary btn-xs" id="editButton" ><spring:message code="menu.userInfo.account"/></button>
									&nbsp;
									<button class="btn btn-primary btn-xs" id="passwordButton" ><spring:message code="menu.userInfo.password"/></button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div align="right">
				<button class="btn btn-primary" onclick="addUser();"><spring:message code="user.title.userReg"/></button>
			</div>
			
		</div>
	</div>

</body>
</html>