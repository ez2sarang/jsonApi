<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 2014. 9. 3.
  Time: 오전 11:08
  To change this template use File | Settings | File Templates.
--%><%@ include file="/WEB-INF/views/common/includes.jsp"
%><%@ page import="com.fast2.zimin.util.DateUtil"
%><%@ taglib prefix="zm" uri="ziminTag"
%><%@ taglib prefix="const" uri="Constants"
%><%@ taglib prefix="ccode" uri="CommonCode"
%><html xmlns:c="http://java.sun.com/jsp/jstl/core">
<head>
    <title><spring:message code="tree.menu.info" var="title"/>${title}</title>
    <script language="JavaScript">
        // <spring:message code="code.error.codeGroupSix" var="codeGroupSixError"/>
        $(document).ready(function() {
            $('#entity').validate({
                rules: {
                    name: {required: true}
                }
                , messages: {},
                success: function (label) {
                    label.addClass("valid");
                }
                , submitHandler: function () {
                    if($("#submitBtn")) {
                        setCursor("entity", "wait");
                        try {
                            $.ajax({
                                type: "POST"
                                , url: "${ctxRoot}/menu/registMenuGroup.do"
                                , data: $('form#entity').serialize()
                                , success: function (msg) {
                                    if (msg == 'Success') {
                                        openInform('Success', msg, function () {
                                            location.reload();
                                        });
                                    } else {
                                        openAlert('Fail', msg, function () {
                                            $("#saveButton").prop("disabled", false);
                                            setCursor("entity", "default");
                                        });
                                    }
                                }
                                , error: function () {
                                    openAlert('Fail', 'Failure');
                                    $("#saveButton").prop("disabled", false);
                                    setCursor("entity", "default");
                                }
                            });
                        } catch (e) {
                            alert(e);
                        }
                    } else {
//                        alert(false);
                    }
                }
            });

            $("#name").focus();
        });
    </script>
</head>
<body>
    <form:form modelAttribute="entity" cssClass="panel form-horizontal">
        <div class="panel-heading">
            <form:hidden path="id"/>
            <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i>${title}</span>
        </div>
        <div class="panel-body" style="overflow:auto;">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label"><spring:message code="tree.menu.name"/></label>
                <div class="col-sm-10">
                    <form:input path="name" value="${entity.name}"/>
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 0;">
                <div class="col-sm-offset-2 col-sm-9" align="right">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="button.close"/></button>
                <c:choose>
                    <c:when test="${entity.id == null || entity.id == 0}">
                    <button id="submitBtn" type="submit" class="btn btn-primary"><spring:message code="button.save"/></button>
                    </c:when>
                    <c:otherwise>
                    <button id="submitBtn" type="submit" class="btn btn-primary"><spring:message code="button.edit"/></button>
                    </c:otherwise>
                </c:choose>
                </div>
            </div>
            <!-- / .form-group -->
        </div>
    </form:form>
</body>
</html>



