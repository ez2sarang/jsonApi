<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><tiles:getAsString name="title" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

	<!-- Open Sans font from Google CDN -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300&subset=latin" rel="stylesheet" type="text/css">

	<!-- Pixel Admin's stylesheets -->
	<link href="${ctxRoot}/assets/stylesheets/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${ctxRoot}/assets/stylesheets/pixel-admin.min.css" rel="stylesheet" type="text/css">
	<link href="${ctxRoot}/assets/stylesheets/widgets.min.css" rel="stylesheet" type="text/css">
	<link href="${ctxRoot}/assets/stylesheets/pages.min.css" rel="stylesheet" type="text/css">
	<link href="${ctxRoot}/assets/stylesheets/rtl.min.css" rel="stylesheet" type="text/css">
	<link href="${ctxRoot}/assets/stylesheets/themes.min.css" rel="stylesheet" type="text/css">

    <!-- Get jQuery from Google CDN -->
    <!--[if !IE]> -->
    <script type="text/javascript"> window.jQuery || document.write('<script language="JavaScript" src="${ctxRoot}/js/jquery-2.0.3.min.js">'+"<"+"/script>"); </script>
    <!-- <![endif]-->
    <!--[if lte IE 9]>
    <script type="text/javascript"> window.jQuery || document.write('<script language="JavaScript" src="${ctxRoot}/js/jquery-1.8.3.min.js">'+"<"+"/script>"); </script>
    <![endif]-->

    <!-- Pixel Admin's javascripts -->
    <script src="${ctxRoot}/assets/javascripts/bootstrap.min.js"></script>
    <script src="${ctxRoot}/assets/javascripts/pixel-admin.min.js"></script>

	<!--[if lt IE 9]>
		<script src="${ctxRoot}/assets/javascripts/ie.min.js"></script>
	<![endif]-->

	<script language="JavaScript" src="${ctxRoot}/js/common.js"></script>
</head>

<script>
	$(document).ready(function() {
		var table = $('#example').dataTable( {
// 			"processing": true,
// 			"serverSide": true,
// // 			"ajax": "${ctxRoot}/subscriber/tableTestData.do",
// // 			"ajax": "${ctxRoot}/subscriber/getSubscriberIdList.do",
// 			"ajax": {
// 				"url": "${ctxRoot}/subscriber/tableTestData.do",
// 				"type": "POST"
// 			},
			serverSide: true,
			ajax: '${ctxRoot}/subscriber/tableTestData.do',
// 		    ajax: {
// 		        url: '${ctxRoot}/subscriber/tableTestData.do',
// 		        type: 'POST'
// 		    },
			"columns": [
				{ "data": "name" },
				{ "data": "mark" }
			]
		} );
	    
	} );
</script>

<body>
	<table id="example" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
				   <th>Name</th>
					<th>Mark</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
	 	</table>
</body>
</html>