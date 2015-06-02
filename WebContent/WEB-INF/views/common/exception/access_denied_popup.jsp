<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html class="gt-ie8 gt-ie9 not-ie"> <!--<![endif]-->
<head>
	<title>Access Denied - ZimiNAdmin</title>
</head>
<body>
<div class="panel panel-danger">
	<div class="panel-heading">
		<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="error.access.denied"/></span>
	</div>
	<div class="panel-body">
		
		<spring:message code="errors.accessDenied" />
		
		<div class="form-group" style="margin-bottom: 0;">
			<div class="col-sm-offset-2 col-sm-9" align="right">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
			</div>
		</div>
	</div>
</div>
</body>
</html>