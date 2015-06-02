<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 2014. 9. 3.
  Time: 오전 11:08
  To change this template use File | Settings | File Templates.
--%><%@ include file="/WEB-INF/views/common/includes.jsp"
%><%@ page import="com.fast2.zimin.util.DateUtil"
%><%@ taglib prefix="zm" uri="ziminTag"
%><html>
<head>
    <title><spring:message code="tree.category.info" var="title"/>${title}</title>
    <script language="JavaScript">
    $.validator.addMethod("dateFormat", function(value, element) {
		return moment(value, moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>'), true).isValid();
	}, "Please enter a date in the format <%=DateUtil.defaultDateTimeFormat %>.");
    
        // <spring:message code="code.error.codeGroupSix" var="codeGroupSixError"/>
        $(document).ready(function() {
            $('#entity').validate({
                rules: {
                    name: {required: true}
                    , uriId: {required: true}
                    , categoryPath: {required: true}
                    , validTermStart: {required: true, dateFormat: true}
//                     , validTermStart: {date: true}
                    , validTermEnd: {required: true, dateFormat: true}
//                     , validTermEnd: {date: true}
                }
                , messages: {},
                success: function (label) {
                    label.addClass("valid");
                }
                , submitHandler: function () {
                    openConfirm('<spring:message code="confirm.save" arguments="${zm:message(wmSource,'tree.category')}"/>', function(result) {
                        if(result && $("#submitBtn").hasClass("save")) {
                            setCursor("entity", "wait");
                            try {
                                $("#depth").val(currentTree.path==""?1:currentTree.path.split("/ ").length-1);
                            } catch (e) {
                            }
                            try {
                                $.ajax({
                                    type: "POST"
                                    , url: "${ctxRoot}/category/registCategory.do"
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
                    });
                }
            });

            try {
                if(currentTree.path.trim() == '' && !${entity.temp}) {
                    $("#submitBtn").attr("class","btn btn-default");
                } else {
                    $("#name").on('change', function(){
                        $("#uriId").value(currentTree.path + " / " + this.value);
                        $("#categoryPath").value(currentTree.path + " / " + this.value);
                    });

                }
            } catch (e) {
                $("#submitBtn").attr("class","btn btn-default");
            }

            $("#pathTitle").text(currentTree.path);

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

            $("#name").focus();
        });
    </script>
</head>
<body>
    <form:form modelAttribute="entity" cssClass="panel form-horizontal">
        <div class="panel-heading">
            <form:hidden path="id"/>
            <form:hidden path="parentId"/>
            <form:hidden path="temp"/>
            <form:hidden path="depth"/>
            <form:hidden path="leaf"/>
            <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i>${title}</span>
        </div>
        <div class="panel-body" style="height:550px; overflow:auto;">
            <div class="form-group">
                <label class="col-sm-2 control-label"><spring:message code="tree.category.path"/></label>
                <div class="col-sm-10">
                    <pre id="pathTitle"></pre>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label"><spring:message code="tree.category.name"/></label>
                <div class="col-sm-10">
                    <form:input path="name" id="name" cssClass="form-control" placeholder="${zm:message(wmSource,'tree.category.name')}" disabled="${zm:message(wmSource,zm:concat('boolean.',!entity.temp))}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="uriId" class="col-sm-2 control-label"><spring:message code="tree.category.uriId"/></label>
                <div class="col-sm-10">
                    <form:input path="uriId" id="uriId" cssClass="form-control" placeholder="${zm:message(wmSource,'tree.category.uriId')}" disabled="${zm:message(wmSource,zm:concat('boolean.',!entity.temp))}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="categoryPath" class="col-sm-2 control-label"><spring:message code="tree.category.categoryPath"/></label>
                <div class="col-sm-10">
                    <form:input path="categoryPath" id="categoryPath" cssClass="form-control" placeholder="${zm:message(wmSource,'tree.category.categoryPath')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="providerVersionNumber" class="col-sm-2 control-label"><spring:message code="tree.category.providerVersionNumber"/></label>
                <div class="col-sm-10">
                    <form:input path="providerVersionNumber" id="providerVersionNumber" cssClass="form-control"  placeholder="${zm:message(wmSource,'tree.category.providerVersionNumber')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="internalVersionNumber" class="col-sm-2 control-label"><spring:message code="tree.category.internalVersionNumber"/></label>
                <div class="col-sm-10">
                    <form:input path="internalVersionNumber" id="internalVersionNumber" cssClass="form-control" placeholder="${zm:message(wmSource,'tree.category.internalVersionNumber')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="validTermStartCalendar" class="col-sm-2 control-label"><spring:message code="tree.category.validTerm"/></label>
                <%--<div class="col-sm-10">
                    <input type="text" class="form-control" id="validTerm" placeholder="<spring:message code="tree.category.validTerm"/>">
                </div>--%>
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
            <div class="form-group">
                <label for="providerContact" class="col-sm-2 control-label"><spring:message code="tree.category.providerContact"/></label>
                <div class="col-sm-10">
                    <form:input path="providerContact" id="providerContact" cssClass="form-control" placeholder="${zm:message(wmSource,'tree.category.providerContact')}"/>
                </div>
            </div>
            <!-- / .form-group -->
            <div class="form-group" style="margin-bottom: 0;">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${entity.id == null || entity.id == 0}">
                            <button id="submitBtn" type="submit" class="btn btn-primary save"><spring:message code="button.save"/></button>
                        </c:when>
                        <c:otherwise>
                            <button id="submitBtn" type="submit" class="btn btn-primary save"><spring:message code="button.edit"/></button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <!-- / .form-group -->
        </div>
    </form:form>
</body>
</html>



