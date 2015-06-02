<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"
%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"
%><%@ include file="/WEB-INF/views/common/includes.jsp"%><!DOCTYPE html>
<!--

TABLE OF CONTENTS.

Use search to find needed section.

=====================================================

|  1. $BODY                 |  Body                 |
|  2. $MAIN_NAVIGATION      |  Main navigation      |
|  3. $NAVBAR_ICON_BUTTONS  |  Navbar Icon Buttons  |
|  4. $MAIN_MENU            |  Main menu            |
|  5. $CONTENT              |  Content              |

=====================================================

-->


<!--[if IE 8]>         <html class="ie8"> <![endif]-->
<!--[if IE 9]>         <html class="ie9 gt-ie8"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="gt-ie8 gt-ie9 not-ie"> <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
	<script language="JavaScript" src="${ctxRoot}/js/zimin.jquery.extend.js"></script>
    <script language="javascript">var ctxRoot = "${ctxRoot}";</script>
</head>


<!-- 1. $BODY ======================================================================================
	
	Body

	Classes:
	* 'theme-{THEME NAME}'
	* 'right-to-left'      - Sets text direction to right-to-left
	* 'main-menu-right'    - Places the main menu on the right side
	* 'no-main-menu'       - Hides the main menu
	* 'main-navbar-fixed'  - Fixes the main navigation
	* 'main-menu-fixed'    - Fixes the main menu
	* 'main-menu-animated' - Animate main menu
-->
<body class="theme-default main-menu-animated">

<script>var init = [];</script>
<!-- Demo script --> <script src="${ctxRoot}/assets/demo/demo.js"></script> <!-- / Demo script -->

<div id="main-wrapper">


<!-- 2. $MAIN_NAVIGATION ===========================================================================

	Main navigation
-->
	<div id="main-navbar" class="navbar navbar-inverse" role="navigation">
		<tiles:insertAttribute name="header" />
	</div> <!-- / #main-navbar -->
<!-- /2. $END_MAIN_NAVIGATION -->


<!-- 4. $MAIN_MENU =================================================================================

		Main menu
		
		Notes:
		* to make the menu item active, add a class 'active' to the <li>
		  example: <li class="active">...</li>
		* multilevel submenu example:
			<li class="mm-dropdown">
			  <a href="#"><span class="mm-text">Submenu item text 1</span></a>
			  <ul>
				<li>...</li>
				<li class="mm-dropdown">
				  <a href="#"><span class="mm-text">Submenu item text 2</span></a>
				  <ul>
					<li>...</li>
					...
				  </ul>
				</li>
				...
			  </ul>
			</li>
-->
	<div id="main-menu" role="navigation">
		<tiles:insertAttribute name="menu" />
	</div> <!-- / #main-menu -->
<!-- /4. $MAIN_MENU -->


	<div id="content-wrapper">
<!-- 5. $CONTENT ===================================================================================

		Content
-->

		<!-- Content here -->
		<tiles:insertAttribute name="body" />

	</div> <!-- / #content-wrapper -->
	<div id="main-menu-bg"></div>
</div> <!-- / #main-wrapper -->




<!-- -------------------------------------------------------------- -->
<!-- --------------------------Modal 구역-------------------------- -->

<%@ include file="/WEB-INF/views/common/modal_includes.jsp"%>

<!-- --------------------------Modal 구역-------------------------- -->
<!-- -------------------------------------------------------------- -->

<script type="text/javascript">
    init.push(function () {
        // Javascript code here
    });
    window.PixelAdmin.start(init);
</script>

</body>
</html>