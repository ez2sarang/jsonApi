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
    <title><spring:message code="serviceAsset.contentGroup.info" var="title"/>${title}</title>
    <style type="text/css" class="init">
        li.my-custom-css {
            background-color: green;
        }
        .select2-search-choice {
            background-color: green;
        }
    </style>
    <script language="JavaScript">
        // <spring:message code="code.error.codeGroupSix" var="codeGroupSixError"/>
        $(document).ready(function() {
            $('#entity').validate({
                rules: {
                    cpId: {required: true}
                    , startDateTime: {required: true, date: true}
                    , endDateTime: {required: true, date: true}
                    , titleBrief: {required: true}
                    , genre: {required: true
                        }
                }
                , messages: {},
                success: function (label) {
                    label.addClass("valid");
                }
                , submitHandler: function () {
                    if($("#submitBtn")) {
                        setCursor("entity", "wait");
                        var selectedSize = -1;
                        try {
                            $("#menuSOGroupMapListSelect option").each(function(index){
                                if(this.selected) {
                                }
                            });
                        } catch (e) {
                            alert("error:"+e);
                        }
                        try {
                            $.ajax({
                                type: "POST"
                                , url: "${ctxRoot}/asset/registContentGroup.do"
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

            $('#contentType').select2({ placeholder: '<spring:message code="serviceAsset.contentGroup.contentType.guide"/>' }).change(function(){
                $(this).valid();
            });
            $('#rating').select2({ placeholder: '<spring:message code="serviceAsset.contentGroup.rating.guide"/>' }).change(function(){
                $(this).valid();
            });
            $('#countryOfOrigin').select2({ placeholder: '<spring:message code="serviceAsset.contentGroup.countryOfOrigin.guide"/>' }).change(function(){
                $(this).valid();
            });
            $('#genre').val('${entity.genre}'.split(','))
                .select2({ placeholder: '<spring:message code="serviceAsset.contentGroup.genre.guide"/>'
                    , maximumSelectionSize: 5
                    , formatSelectionCssClass: function (data, container) { return "my-custom-css"; }
                }).on('change', function(){
                $(this).valid();
                $(this).parents().find(".my-custom-css").attr("style","background-color: #5EBD5E;");
            }).trigger('change');
            /*
            for input box
            $("#genre").select2("container").find("ul.select2-choices").sortable(
                {
                    containment: 'parent'
                    , start: function() { $("#genre").select2("onSortStart"); }
                    , update: function() { $("#genre").select2("onSortEnd"); }
                }
            );
            */
            $('#showType').select2({ placeholder: '<spring:message code="serviceAsset.contentGroup.showType.guide"/>' }).change(function(){
                $(this).valid();
            });

            $('#seriesId').select2({ placeholder: '<spring:message code="serviceAsset.contentGroup.seriesId.guide"/>', formatSelectionCssClass: function (data, container) { return "my-custom-css"; }}).on('change', function(){
                $(this).valid();
                $(this).parents().find(".my-custom-css").attr("style","background-color: #5EBD5E;");
            }).trigger('change');
            $('#movieSubsetId').select2({ placeholder: '<spring:message code="serviceAsset.contentGroup.movieSubsetId.guide"/>', formatSelectionCssClass: function (data, container) { return "my-custom-css"; } }).on('change', function(){
                $(this).valid();
                $(this).parents().find(".my-custom-css").attr("style","background-color: #5EBD5E;");
            }).trigger('change');
            $('#previewSubsetId').select2({ placeholder: '<spring:message code="serviceAsset.contentGroup.previewSubsetId.guide"/>', formatSelectionCssClass: function (data, container) { return "my-custom-css"; } }).on('change', function(){
                $(this).valid();
                $(this).parents().find(".my-custom-css").attr("style","background-color: #5EBD5E;");
            }).trigger('change');
            $('#posterSubsetId').select2({ placeholder: '<spring:message code="serviceAsset.contentGroup.posterSubsetId.guide"/>', formatSelectionCssClass: function (data, container) { return "my-custom-css"; } }).on('change', function(){
                $(this).valid();
                $(this).parents().find(".my-custom-css").attr("style","background-color: #5EBD5E;");
            }).trigger('change');
            $('#thumbnailSubsetId').select2({ placeholder: '<spring:message code="serviceAsset.contentGroup.thumbnailSubsetId.guide"/>', formatSelectionCssClass: function (data, container) { return "my-custom-css"; } }).on('change', function(){
                $(this).valid();
                $(this).parents().find(".my-custom-css").attr("style","background-color: #5EBD5E;");
            }).trigger('change');

            $("#linkSelectBtnSeries").click(function() {
                var tUrl = "${ctxRoot}/asset/getSeriesListForm.do?popupType=selectBox&targetId=seriesId";
                openModalWindow('02', tUrl);
            });
            $("#linkSelectBtnMovie").click(function() {
                var tUrl = "${ctxRoot}/asset/getContentSubsetListForm.do?popupType=selectBox&targetId=movieSubsetId&subsetType=${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_MOVIE')}";
                openModalWindow('02', tUrl);
            });
            $("#linkSelectBtnPreview").click(function() {
                var tUrl = "${ctxRoot}/asset/getContentSubsetListForm.do?popupType=selectBox&targetId=previewSubsetId&subsetType=${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_PREVIEW')}";
                openModalWindow('02', tUrl);
            });
            $("#linkSelectBtnPoster").click(function() {
                var tUrl = "${ctxRoot}/asset/getContentSubsetListForm.do?popupType=selectBox&targetId=posterSubsetId&subsetType=${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_POSTER')}";
                openModalWindow('02', tUrl);
            });
            $("#linkSelectBtnThumbnail").click(function() {
                var tUrl = "${ctxRoot}/asset/getContentSubsetListForm.do?popupType=selectBox&targetId=thumbnailSubsetId&subsetType=${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_THUMBNAIL')}";
                openModalWindow('02', tUrl);
            });

            try {
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
            } catch (e) {
            }
            $(".select2-input").attr("style","width:100%;");
        });
    </script>
</head>
<body>
    <form:form modelAttribute="entity" cssClass="panel form-horizontal">
        <div class="panel-heading">
            <form:hidden path="contentGroupId"/>
            <div id="generateScripts"></div>
            <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i>${title}</span>
        </div>
        <div class="panel-body" style="overflow:auto;">
            <div class="form-group">
                <label for="cpId" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.cpId"/></label>
                <div class="col-sm-10">
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="principal.cpId" var="cpId"/>
                    </sec:authorize>
                    <form:input path="cpId" id="cpId" value="${cpId}" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label for="contentType" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.contentType"/></label>
                <div class="col-sm-10">
                    <select id="contentType" name="contentType" class="form-control">
                        <ccode:options groupCode="${const:get('COMMONCODE', 'CG_CONTENT_TYPE')}" selected="${entity.contentType}"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="validTermStartCalendar" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.validTerm"/></label>
                <div class="col-sm-10">
                    <div class='col-md-6' style="padding-left:0;padding-right:0">
                        <div class='input-group date' id='validTermStartCalendar'>
                            <form:input path="startDateTime" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.startDateTime')}"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                </span>
                        </div>
                    </div>
                    <div class='col-md-6' style="padding-left:0;padding-right:0">
                        <div class='input-group date' id='validTermEndCalendar'>
                            <form:input path="endDateTime" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.endDateTime')}"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="titleSortName" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.titleSortName"/></label>
                <div class="col-sm-10">
                    <form:input path="titleSortName" id="titleSortName" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.titleSortName.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="titleBrief" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.titleBrief"/></label>
                <div class="col-sm-10">
                    <form:input path="titleBrief" id="titleBrief" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.titleBrief.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="titleMedium" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.titleMedium"/></label>
                <div class="col-sm-10">
                    <form:input path="titleMedium" id="titleMedium" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.titleMedium.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="titleLong" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.titleLong"/></label>
                <div class="col-sm-10">
                    <form:input path="titleLong" id="titleLong" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.titleLong.guide')}"/>
                </div>
            </div>

            <div class="form-group">
                <label for="summaryShort" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.summaryShort"/></label>
                <div class="col-sm-10">
                    <form:input path="summaryShort" id="summaryShort" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.summaryShort.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="summaryMedium" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.summaryMedium"/></label>
                <div class="col-sm-10">
                    <form:input path="summaryMedium" id="summaryMedium" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.summaryMedium.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="summaryLong" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.summaryLong"/></label>
                <div class="col-sm-10">
                    <form:input path="summaryLong" id="summaryLong" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.summaryLong.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="actor" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.actor"/></label>
                <div class="col-sm-10">
                    <form:input path="actor" id="actor" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.actor.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="writer" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.writer"/></label>
                <div class="col-sm-10">
                    <form:input path="writer" id="writer" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.writer.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="director" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.director"/></label>
                <div class="col-sm-10">
                    <form:input path="director" id="director" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.director.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="producer" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.producer"/></label>
                <div class="col-sm-10">
                    <form:input path="producer" id="producer" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.producer.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="studio" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.studio"/></label>
                <div class="col-sm-10">
                    <form:input path="studio" id="studio" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.studio.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="seriesId" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.seriesId"/></label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <select id="seriesId" name="seriesId" multiple="multiple">
                            <c:if test="${not empty entity.seriesId}"><option selected value="${entity.seriesId}" selected>${entity.seriesName}</option></c:if>
                        </select>
                        <span class="input-group-btn">
                            <button class="btn" type="button" id="linkSelectBtnSeries" ><spring:message code="button.select" /></button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="episodeType" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.episodeType"/></label>
                <div class="col-sm-10">
                    <select id="episodeType" name="episodeType" class="form-control">
                        <ccode:options groupCode="${const:get('COMMONCODE', 'CG_EPISODE_TYPE')}" selected="${entity.episodeType}"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="episodeNo" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.episodeNo"/></label>
                <div class="col-sm-10">
                    <form:input path="episodeNo" id="episodeNo" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.episodeNo.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="episodeName" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.episodeName"/></label>
                <div class="col-sm-10">
                    <form:input path="episodeName" id="episodeName" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.episodeName.guide')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="rating" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.rating"/></label>
                <div class="col-sm-10">
                    <select id="rating" name="rating" class="form-control">
                        <ccode:options groupCode="${const:get('COMMONCODE', 'CG_RATING')}" selected="${entity.rating}"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="displayRunTime" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.displayRunTime"/></label>
                <div class="col-sm-10">
                    <form:input path="displayRunTime" id="displayRunTime" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.displayRunTime')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="year" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.year"/></label>
                <div class="col-sm-10">
                    <form:input path="year" id="year" cssClass="form-control" placeholder="${zm:message(wmSource,'serviceAsset.contentGroup.year')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="countryOfOrigin" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.countryOfOrigin"/></label>
                <div class="col-sm-10">
                    <select id="countryOfOrigin" name="countryOfOrigin" class="form-control">
                        <ccode:options groupCode="${const:get('COMMONCODE', 'CG_COUNTRY_OF_ORIGIN')}" selected="${entity.countryOfOrigin}"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="genre" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.genre"/></label>
                <div class="col-sm-10">
                    <select id="genre" name="genre" class="form-control" multiple="multiple">
                        <ccode:options groupCode="${const:get('COMMONCODE', 'CG_GENRE')}" selected="${entity.genre}"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="showType" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.showType"/></label>
                <div class="col-sm-10">
                    <select id="showType" name="showType" class="form-control">
                        <ccode:options groupCode="${const:get('COMMONCODE', 'CG_SHOW_TYPE')}" selected="${entity.showType}"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="movieSubsetId" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.movieSubsetId"/></label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <select id="movieSubsetId" name="movieSubsetId" multiple="multiple">
                            <c:if test="${not empty entity.movieSubsetId}"><option selected value="${entity.movieSubsetId}" selected>${entity.movieSubsetId}movieSubsetName</option></c:if>
                        </select>
                        <span class="input-group-btn">
                            <button class="btn" type="button" id="linkSelectBtnMovie" ><spring:message code="button.select" /></button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="previewSubsetId" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.previewSubsetId"/></label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <select id="previewSubsetId" name="previewSubsetId" multiple="multiple">
                            <c:if test="${not empty entity.previewSubsetId}"><option selected value="${entity.previewSubsetId}" selected>${entity.previewSubsetId}previewSubsetName</option></c:if>
                        </select>
                        <span class="input-group-btn">
                            <button class="btn" type="button" id="linkSelectBtnPreview" ><spring:message code="button.select" /></button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="posterSubsetId" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.posterSubsetId"/></label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <select id="posterSubsetId" name="posterSubsetId" multiple="multiple">
                            <c:if test="${not empty entity.posterSubsetId}"><option selected value="${entity.posterSubsetId}" selected>${entity.posterSubsetId}posterSubsetName</option></c:if>
                        </select>
                        <span class="input-group-btn">
                            <button class="btn" type="button" id="linkSelectBtnPoster" ><spring:message code="button.select" /></button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="thumbnailSubsetId" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.thumbnailSubsetId"/></label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <select id="thumbnailSubsetId" name="thumbnailSubsetId" multiple="multiple">
                            <c:if test="${not empty entity.thumbnailSubsetId}"><option selected value="${entity.thumbnailSubsetId}" selected>${entity.thumbnailSubsetId}thumbnailSubsetName</option></c:if>
                        </select>
                        <span class="input-group-btn">
                            <button class="btn" type="button" id="linkSelectBtnThumbnail" value="thumbnail" ><spring:message code="button.select" /></button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 0;">
                <div class="col-sm-offset-2 col-sm-10" align="right">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="button.close"/></button>
                    <c:choose>
                        <c:when test="${entity.contentGroupId == null || entity.contentGroupId == 0}">
                            <button id="submitBtn" class="btn btn-primary"><spring:message code="button.save"/></button>
                        </c:when>
                        <c:otherwise>
                            <button id="submitBtn" class="btn btn-primary"><spring:message code="button.edit"/></button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <!-- / .form-group -->
        </div>
    </form:form>
</body>
</html>



