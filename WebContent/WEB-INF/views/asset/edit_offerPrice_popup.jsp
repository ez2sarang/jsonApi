<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ taglib prefix="lvb" uri="LabelValueBean"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
    <title><spring:message code="menu.serviceAsset.offer" /></title>
    <link href="${ctxRoot}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <script src="${ctxRoot}/js/moment.js"></script>
    <script language="JavaScript">
        $(document).ready(function() {
            $.validator.addMethod("royaltyPercent", function(value, element) {
                if ($("#royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_PERCENT')}") {
                    if ($("#royaltyPercent").val() == '') {
                        return false;
                    }
                }
                return true;
            }, "This field is required..");
            $.validator.addMethod("royaltyMinimum", function(value, element) {
                if ($("#royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_PERCENT')}") {
                    if ($("#royaltyMinimum").val() == '') {
                        return false;
                    }
                }
                return true;
            }, "This field is required..");
            $.validator.addMethod("royaltyFlatRate", function(value, element) {
                if ($("#royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_FLATRATE')}") {
                    if ($("#royaltyFlatRate").val() == '') {
                        return false;
                    }
                }
                return true;
            }, "This field is required..");

            $('#offer').validate( {
                rules: {
                    price: {required: true, number: true, digits: true},
                    pointPrice: {number: true, digits: true},
                    royaltyType: {required: true},
                    royaltyPercent: {royaltyPercent: true, number: true, range: [0, 100]},
                    royaltyMinimum: {royaltyMinimum: true, number: true, digits: true},
                    royaltyFlatRate: {royaltyFlatRate: true, number: true, digits: true},
                },
                success: function(label) {
                    label.addClass("valid");
                },
                submitHandler: function () {
                    $("#saveButton").prop("disabled", true);
                    setCursor("offer", "wait");

                    try{
                        $.ajax({
                            type: "POST",
                            url: "${ctxRoot}/asset/editOfferPrice.do",
                            data: $('form#offer').serialize(),
                            success: function(data, textStatus, jqXHR){
                                if (data == 'Success') {
                                    openInform('Success', data, function() {
                                        closeModalWindow('01');
                                        document.location.reload();
                                    });
                                } else {
                                    openAlert('Fail', data, function() {
                                        $("#saveButton").prop("disabled", false);
                                        setCursor("offer", "default");
                                    });
                                }
                            },
                            error: function(jqXHR, textStatus, errorThrown){
                                openAlert('Fail', 'Failure:' + textStatus);
                                $("#saveButton").prop("disabled", false);
                                setCursor("offer", "default");
                            }
                        });
                    }catch(e) {
                        alert (e);
                    }
                }
            });

            $('#royaltyType').change(function(){
                if ($("#royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_PERCENT')}") {
                    $("#royaltyPercent").prop("disabled", false);
                    $("#royaltyMinimum").prop("disabled", false);
                    $("#royaltyFlatRate").prop("disabled", true);
                } else if ($("#royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_FLATRATE')}") {
                    $("#royaltyPercent").prop("disabled", true);
                    $("#royaltyMinimum").prop("disabled", true);
                    $("#royaltyFlatRate").prop("disabled", false);
                } else {
                    $("#royaltyPercent").prop("disabled", false);
                    $("#royaltyMinimum").prop("disabled", false);
                    $("#royaltyFlatRate").prop("disabled", false);
                }
            });

            $('#royaltyType').trigger('change');
        });



    </script>
</head>
<body>

<form:form modelAttribute="offer" method="POST"
           cssClass="panel form-horizontal">
    <div class="panel-heading">
        <span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="offer.title.edit"/></span>
    </div>
    <div class="panel-body">

        <div class="form-group">
            <label for="price" class="col-sm-3 control-label"><spring:message code="offer.price" /></label>
            <div class="col-sm-9">
                <form:input path="price" cssClass="form-control" />
            </div>
        </div>
        <div class="form-group">
            <label for="pointPrice" class="col-sm-3 control-label"><spring:message code="offer.pointPrice" /></label>
            <div class="col-sm-9">
                <form:input path="pointPrice" cssClass="form-control" />
            </div>
        </div>
        <div class="form-group">
            <label for="royaltyType" class="col-sm-3 control-label"><spring:message code="offer.royaltyType" /></label>
            <div class="col-sm-9">
                <form:select path="royaltyType" cssClass="form-control">
                    <option value=""><spring:message code="combo.select" /></option>
                    <ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_ROYALTY_TYPE')}" selected="${offer.royaltyType}"/>
                </form:select>
            </div>
        </div>
        <div class="form-group">
            <label for="royaltyPercent" class="col-sm-3 control-label"><spring:message code="offer.royaltyPercent" /></label>
            <div class="col-sm-9">
                <form:input path="royaltyPercent" cssClass="form-control" />
            </div>
        </div>
        <div class="form-group">
            <label for="royaltyMinimum" class="col-sm-3 control-label"><spring:message code="offer.royaltyMinimum" /></label>
            <div class="col-sm-9">
                <form:input path="royaltyMinimum" cssClass="form-control" />
            </div>
        </div>
        <div class="form-group">
            <label for="royaltyFlatRate" class="col-sm-3 control-label"><spring:message code="offer.royaltyFlatRate" /></label>
            <div class="col-sm-9">
                <form:input path="royaltyFlatRate" cssClass="form-control" />
            </div>
        </div>

        <div class="form-group" style="margin-bottom: 0;">
            <div class="col-sm-offset-2 col-sm-9" align="right">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
                <button type="submit" class="btn btn-primary" id="saveButton"><spring:message code='button.save'/></button>
            </div>
        </div> <!-- / .form-group -->
    </div>
</form:form>

</body>
</html>