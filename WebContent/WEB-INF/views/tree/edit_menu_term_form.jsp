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
        $(document).ready(function() {
            $('#entity').validate({
                rules: {
                    validTermStart: {required: true, date: true}
                    , validTermEnd: {required: true, date: true}
                }
                , messages: {}
                , success: function (label) {
                    label.addClass("valid");
                }
                , submitHandler: function () {
                    if($("#submitBtn")) {
                        setCursor("entity", "wait");
                        try {
                            $.ajax({
                                type: "POST"
                                , url: "${ctxRoot}/menu/registMenu.do"
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


        $('#validTermStartCalendar').datetimepicker({
            useSeconds: true,
            format: moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>')
        });
        $('#validTermEndCalendar').datetimepicker({
            useSeconds: true,
            format: moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>')
        });
        $("#validTermStartCalendar").on("dp.change",function (e) {
            $('#validTermEndCalendar').data("DateTimePicker").setMinDate(e.date);
        });
        $("#validTermEndCalendar").on("dp.change",function (e) {
            $('#validTermStartCalendar').data("DateTimePicker").setMaxDate(e.date);
        });

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
        <label for="validTermStartCalendar" class="col-sm-2 control-label"><spring:message code="tree.menu.validTerm"/></label>
        <div class="col-sm-10">
            <div class='col-md-6' style="padding-left:0;padding-right:0">
                <div class='input-group date' id='validTermStartCalendar'>
                    <form:input path="validTermStart" cssClass="form-control" placeholder="${zm:message(wmSource,'tree.licencePeriod.start')}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                </div>
            </div>
            <div class='col-md-6' style="padding-left:0;padding-right:0">
                <div class='input-group date' id='validTermEndCalendar'>
                    <form:input path="validTermEnd" cssClass="form-control" placeholder="${zm:message(wmSource,'tree.licencePeriod.end')}"/>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                </div>
            </div>
        </div>
    </div>
<!-- / .form-group -->
</div>
<div class="panel-footer pull-right" style="margin-bottom: 0;">
    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="button.close"/></button>
    <button id="submitBtn" class="btn btn-primary"><spring:message code="button.edit"/></button>
</div>
</form:form>
</body>
</html>



