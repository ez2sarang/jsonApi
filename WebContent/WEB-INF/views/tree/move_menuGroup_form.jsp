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
    <title><spring:message code="tree.menu.group" var="title"/>${title}</title>
    <script>
        function moveMenuGroup(id){
            var menuGroupId = $(this).attr("val");
            openConfirm('<spring:message code="confirm.move" arguments="${zm:message(wmSource,'tree.menu')}"/>',function(result) {
                if(result) {
                    try{
                        $.ajax({
                            type: "GET"
                            , url: "${ctxRoot}/menu/includeMenuGroup.do"
                            , data: {groupId:id, menuId:${param.menuId}}
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
        };
    </script>
</head>
<body>
<div class="row">
    <div class="panel" id="listDiv">
        <div class="panel-heading">
            <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="button.move"/></span>
        </div>
        <div class="panel-body">
            <div class="table-primary">
                <table id="tree" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th><spring:message code="tree.menu.group"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="itemCnt" value="0"/>
                    <c:forEach items="${menuGroupList}" var="menuGroup">
                        <tr>
                            <td>
                            <a href="javascript:moveMenuGroup(${menuGroup.id});">${menuGroup.name}</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(menuGroupList)==0}">
                        <tr>
                            <td>empty</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="panel-footer pull-right">
            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="button.close"/></button>
        </div>
    </div>
</div>
</body>
</html>