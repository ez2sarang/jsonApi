<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html class="gt-ie8 gt-ie9 not-ie"> <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Error 500 - Pages - ZimiNAdmin</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

	<!-- Open Sans font from Google CDN -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300&subset=latin" rel="stylesheet" type="text/css">

	<!-- Pixel Admin's stylesheets -->
	<link href="${ctxRoot}/assets/stylesheets/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${ctxRoot}/assets/stylesheets/pixel-admin.min.css" rel="stylesheet" type="text/css">
	<link href="${ctxRoot}/assets/stylesheets/pages.min.css" rel="stylesheet" type="text/css">
	<link href="${ctxRoot}/assets/stylesheets/rtl.min.css" rel="stylesheet" type="text/css">

	<!--[if lt IE 9]>
		<script src="${ctxRoot}/assets/javascripts/ie.min.js"></script>
	<![endif]-->

</head>


<!-- 1. $BODY ======================================================================================
	
	Body

	Classes:
	* 'right-to-left' - Sets text direction to right-to-left
-->
<body class="page-500">

<script>var init = [];</script>

	<div class="header">
		<a href="index.html" class="logo">
			<div class="demo-logo"><img src="${ctxRoot}/assets/demo/logo-big.png" alt="" style="margin-top: -4px;"></div>&nbsp;
			<strong>ZimiN</strong>Admin
		</a> <!-- / .logo -->
	</div> <!-- / .header -->

	<div class="error-code">500</div>

	<div class="error-text">
		<span class="oops">OUCH!</span><br>
		<span class="hr"></span>
		<br>
		SOMETHING IS NOT QUITE RIGHT
		<br>
		<span class="solve">We hope to solve it shortly<br><br>(<%= exception.toString().replaceAll(":",":<br>") %>)</span>
	</div> <!-- / .error-text -->

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

<script type="text/javascript">
	init.push(function () {
		// Javascript code here
	})
	window.PixelAdmin.start(init);
</script>

</body>
</html>