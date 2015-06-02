<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 2014. 9. 12.
  Time: 오후 3:48
  To change this template use File | Settings | File Templates.
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ page import="com.fast2.zimin.util.DateUtil"
%><%@ include file="/WEB-INF/views/common/includes.jsp"
%><%@ taglib prefix="zm" uri="ziminTag"
%><html>
<head>
    <title><spring:message code="tree.menu.popup" var="title"/>${title}</title>
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
<!--     <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script> -->
    <script src="${ctxRoot}/js/jquery-ui.min-1.11.1.js"></script>

    <!-- Pixel Admin's javascripts -->
    <script src="${ctxRoot}/assets/javascripts/bootstrap.min.js"></script>
    <%--<script src="${ctxRoot}/assets/javascripts/pixel-admin.min.js"></script>--%>

    <!--[if lt IE 9]>
    <script src="${ctxRoot}/assets/javascripts/ie.min.js"></script>
    <![endif]-->
    <script language="JavaScript" src="${ctxRoot}/js/common.js"></script>
    <script language="JavaScript" src="${ctxRoot}/js/tree.js"></script>


    <link href="${ctxRoot}/css/tree.css" rel="stylesheet" type="text/css">
    <link href="${ctxRoot}/css/contextMenu.css" rel="stylesheet" type="text/css">
<!--     <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script> -->
    <script src="${ctxRoot}/js/jquery-ui.min-1.11.1.js"></script>
    <script src="${ctxRoot}/js/contextMenu.min.js"></script>

    <link href="${ctxRoot}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <script src="${ctxRoot}/js/moment.js"></script>
    <script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
    <script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>

    <script language="javascript">
        var ctxRoot = "${ctxRoot}",
            requestParam = {
                treeMove : {
                    parentId : '${param.id}'
                    , moveAlertMessage : '<spring:message code="confirm.save" arguments="${zm:message(wmSource,'tree.menu')}"/>'
                }
                /*,apped:{}*/
            };
        //var menuOptions = {};
        popupTrees = [];
        <c:forEach items = "${treeList}" var="tree" >popupTrees.push(new Tree(${tree}));
        </c:forEach >

        rootTree = {id:${param.parentId}, name:"Roots", parentId:0}

        $(document).ready(function () {
            loadTree("${param.popupType}", popupTrees, false);
            //alert(jQuery.stringify(treeClone));

            if (opener) {
                GenerateAlertDiv();
            }
        });
    </script>
</head>
<body>
    <div class="panel-heading">
        <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="tree.menu.list"/></span>
    </div>
    <div class="panel-body">
        <div id="${param.popupType}" class="tree well" style="overflow-x: auto;"></div>
    </div>
    <div class="modal-footer" style="margin-bottom: 0;">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="if(opener){window.close();}else{try{reLoadTree();}catch(e){}}"><spring:message code='button.close'/></button>
        </div>
    </div>
</body>
</html>
