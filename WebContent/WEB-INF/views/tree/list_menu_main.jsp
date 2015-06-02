<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 2014. 8. 18.
  Time: 오후 3:04
  To change this template use File | Settings | File Templates.
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ include file="/WEB-INF/views/common/includes.jsp"
%><%@ taglib prefix="zm" uri="ziminTag"
%><html>
<head>
    <title><spring:message code="tree.menu.main" var="title"/>${title}</title>
    <link href="${ctxRoot}/css/tree.css" rel="stylesheet" type="text/css">
    <link href="${ctxRoot}/css/contextMenu.css" rel="stylesheet" type="text/css">
    <link href="${ctxRoot}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<!--     <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script> -->
    <script src="${ctxRoot}/js/jquery-ui.min-1.11.1.js"></script>
    <script src="${ctxRoot}/js/tree.js"></script>
    <script src="${ctxRoot}/js/contextMenu.min.js"></script>
    <script src="${ctxRoot}/js/moment.js"></script>
    <script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
    <script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>
    <script language="javascript">
        $(document).ready(function () {
            //var fnFakeRowspan = $("#tree").dataTable().fnFakeRowspan();

            $("#menuGroupAdd").on("click", function(){
                openModalWindow("01", "${ctxRoot}/menu/registMenuGroupForm.do")
            });

            $("#menuAdd").on("click", function(){
                openModalWindow("01", "${ctxRoot}/menu/registMenuForm.do")
            });

            $(".modifyButton").each(function(){
                $(this).on('click', function(){
                    var url = "${ctxRoot}/menu/registMenuGroupForm.do?id="+$(this).attr("val");
                    openModalWindow("01", url);
                });
            });

            $(".modifyTermButton").each(function(){
                $(this).on('click', function(){
                    var url = "${ctxRoot}/menu/editMenuTermForm.do?id="+$(this).attr("menuId");
                    openModalWindow("01", url);
                });
            });

            $(".moveButton").each(function(){
                $(this).on('click', function(){
                    var url = "${ctxRoot}/menu/moveMenuGroupForm.do?menuId="+$(this).attr("val");
                    openModalWindow("01", url);
                });
            });

            $(".deleteButton").on("click", function(){
                var menuGroupId = $(this).attr("val");
                openConfirm('<spring:message code="confirm.delete" arguments="${zm:message(wmSource,'tree.menu.group')}"/>',function(result) {
                    if(result) {
                        try{
                            $.ajax({
                                type: "GET"
                                , url: "${ctxRoot}/menu/deleteMenuGroup.do"
                                , data: {id:menuGroupId}
                                , success: function(data, textStatus, jqXHR){
                                    if (data == 'Success') {
                                        openInform('Success', data, function() {
                                            location.reload();
                                        });
                                    } else {
                                        openAlert('Fail', data, function() {
                                        });
                                    }
                                }
                                , error: function(jqXHR, textStatus, errorThrown){
                                    openAlert('Fail', 'Failure:' + this.data+"<br>"+textStatus);
                                }
                            });
                        }catch(e) {
                            alert (e);
                        }
                    }
                });
            });

            $(".excludeButton").each(function(){
                $(this).on('click', function(){
                    var groupId = $(this).attr("groupId");
                    var menuId = $(this).attr("menuId");
                    openConfirm('<spring:message code="confirm.exclude" arguments="${zm:message(wmSource,'tree.menu')}"/>',function(result) {
                        if(result) {
                            try{
                                $.ajax({
                                    type: "GET"
                                    , url: "${ctxRoot}/menu/excludeMenuGroup.do"
                                    , data: {groupId:groupId, menuId:menuId}
                                    , success: function(data, textStatus, jqXHR){
                                        if (data == 'Success') {
                                            openInform('Success', data, function() {
                                                location.reload();
                                            });
                                        } else {
                                            openAlert('Fail', data, function() {
                                            });
                                        }
                                    }
                                    , error: function(jqXHR, textStatus, errorThrown){
                                        openAlert('Fail', 'Failure:' + this.data+"<br>"+textStatus);
                                    }
                                });
                            }catch(e) {
                                alert (e);
                            }
                        }
                    });
                });
            });

            $('.switcher-example-square').switcher({ theme: 'square' }).on('change', function() {
                var selectedMenu = this;
                $.ajax({
                    type: "GET"
                    , url: "${ctxRoot}/menu/setVisible.do"
                    , data: {id:selectedMenu.value, visible:selectedMenu.checked}
                    , success: function(data, textStatus, jqXHR){
                        if (data == 'Success') {
                            openInform('<spring:message code="category.visible.success"/>', data, function() {
                            });
                        } else {
                            openAlert('<spring:message code="category.visible.fail"/>', data, function() {
                                location.reload();
                            });
                        }
                    }
                    , error: function(jqXHR, textStatus, errorThrown){
                        try {
                            openAlert('Fail', 'Failure:' +this.url+"<br>"+ jqXHR.responseText, function(){
                                location.reload();
                            });
                        } catch (e) {
                            alert(e);
                        }
                    }
                });
            });
        });
        function viewDetail(id) {
            $('#listDiv').hide();
            location.href="#detailView";
            var tUrl = "${ctxRoot}/menu/subMenuList.do?parentId=" + id;
            $('#detailDiv').load(tUrl);
            $('#detailDiv').show();
        }
        function goList() {
            $('#detailDiv').hide();
            $('#listDiv').show();
        }
    </script>
</head>
<body>
<ul class="breadcrumb breadcrumb-page">
    <div class="breadcrumb-label text-light-gray">You are here:</div>
    <li><spring:message code="tree.parent"/></li>
    <li class="active">${title}</li>
</ul>
<div class="page-header">
    <div class="row">
        <!-- Page header, center on small screens -->
        <h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa fa-th-list page-header-icon"></i>&nbsp;&nbsp;${title}</h1>
    </div>
</div>
<!-- / .page-header -->

<div class="row">
    <div class="panel" id="listDiv">
        <div class="panel-heading">
            <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="tree.menu.list"/></span>
        </div>
        <div class="panel-body">
            <div class="table-primary">
                <table id="tree" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th><spring:message code="tree.menu.group"/></th>
                        <th><spring:message code="tree.menu.id"/></th>
                        <th><spring:message code="tree.menu.name"/></th>
                        <th><spring:message code="tree.menu.validTerm"/></th>
                        <th><spring:message code="tree.menu.visible"/></th>
                        <th><spring:message code="tree.menu.function"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="itemCnt" value="0"/>
                    <c:forEach items="${menuGroupList}" var="menuGroup">
                        <c:forEach items="${treeList}" var="tree">
                            <c:if test="${tree.menuGroupId == menuGroup.id}">
                                <c:set var="itemCnt" value="${itemCnt+1}"/>
                            </c:if>
                        </c:forEach>
                        <tr>
                            <td rowspan="${itemCnt}">${menuGroup.name}
                                <div class="pull-right">
                                    <input type="button" class="btn btn-primary btn-xs modifyButton" value="<spring:message code="button.edit"/>" val="${menuGroup.id}"/>&nbsp;
                                    <input type="button" class="btn btn-primary btn-xs deleteButton" value="<spring:message code="button.delete"/>" val="${menuGroup.id}"/>
                                </div>
                            </td>
                        <c:set var="itemCnt" value="0"/>
                        <c:forEach items="${treeList}" var="tree">
                            <c:if test="${tree.menuGroupId == menuGroup.id}"><c:if test="${itemCnt>0}"><tr></c:if>
                                <c:set var="itemCnt" value="${itemCnt+1}"/>
                            <td>${tree.id}</td>
                            <td><a href="#detailView" onclick="viewDetail(${tree.id})">${tree.name}</a></td>
                            <td><fmt:formatDate value="${tree.validTermStart}" pattern="yyyy-MM-dd HH:mm"/> ~ <fmt:formatDate value="${tree.validTermEnd}" pattern="yyyy-MM-dd HH:mm"/> <input type="button" class="btn btn-primary btn-xs pull-right modifyTermButton" value="<spring:message code="button.edit"/>" menuId="${tree.id}"/></td>
                            <td><input type="checkbox" class="switcher-example-square" value="${tree.id}" <c:out value="${tree.visible?'checked':''}"/>></td>
                            <td>
                                <button class="btn btn-primary btn-xs excludeButton" menuId="${tree.id}" groupId="${menuGroup.id}"><spring:message code="button.exclude"/></button> <button class="btn btn-primary btn-xs cloneButton"><spring:message code="button.clone"/></button>
                                <%--<a class="btn btn-xs btn-outline"><i class="icon-arrow-down"/></a>&nbsp;<a class="btn btn-xs btn-outline"><i class="icon-arrow-up"/></a>--%>
                            </td>
                        </tr>
                            </c:if>
                        </c:forEach>
                        <c:if test="${itemCnt==0}">
                            <td colspan="5">empty</td>
                        </tr>
                        </c:if>
                        <c:set var="itemCnt" value="0"/>
                    </c:forEach>

                    <%--No Group--%>
                    <c:forEach items="${treeList}" var="tree">
                        <c:if test="${tree.menuGroupId == null}">
                            <c:set var="itemCnt" value="${itemCnt+1}"/>
                        </c:if>
                    </c:forEach>
                        <tr>
                            <td rowspan="${itemCnt}">no Group</td>
                    <c:set var="itemCnt" value="0"/>
                    <c:forEach items="${treeList}" var="tree">
                        <c:if test="${tree.menuGroupId == null}"><c:if test="${itemCnt>0}"><tr></c:if>
                            <c:set var="itemCnt" value="${itemCnt+1}"/>
                            <td>${tree.id}</td>
                            <td><a href="#detailView" onclick="viewDetail(${tree.id})">${tree.name}</a></td>
                            <td><fmt:formatDate value="${tree.validTermStart}" pattern="yyyy-MM-dd HH:mm"/> ~ <fmt:formatDate value="${tree.validTermEnd}" pattern="yyyy-MM-dd HH:mm"/> <input type="button" class="btn btn-primary btn-xs pull-right modifyTermButton" value="<spring:message code="button.edit"/>" menuId="${tree.id}"/></td></td>
                            <td><input type="checkbox" class="switcher-example-square" value="${tree.id}" <c:out value="${tree.visible?'checked':''}"/>></td>
                            <td>
                                <button class="btn btn-primary btn-xs moveButton" val="${tree.id}"><spring:message code="button.move"/></button>
                                <button class="btn btn-primary btn-xs cloneButton" val="${tree.id}"><spring:message code="button.clone"/></button>
                            </td>
                        </tr>
                        </c:if>
                    </c:forEach>
                    <c:if test="${itemCnt==0}">
                            <td colspan="5">empty</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="panel-footer <%--pull-right--%>">
            <button id="menuGroupAdd" type="button" class="btn btn-primary"><spring:message code="tree.menu.group"/> <spring:message code="button.add"/></button>
            <button id="menuAdd" type="button" class="btn btn-primary"><spring:message code="tree.menu"/> <spring:message code="button.add"/></button>
        </div>
        <a name="detailView"></a>
    </div>
    <div class="row" id="detailDiv" style="display:none"></div>
</div>
</body>
</html>