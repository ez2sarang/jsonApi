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
    <style>
        .ui-accordion-header:after {
            content: '<spring:message code="button.select"/>';
        }
    </style>
    <script language="JavaScript">
        // <spring:message code="code.error.codeGroupSix" var="codeGroupSixError"/>
        $(document).ready(function() {
            $('#entity').validate({
                rules: {
                    name: {required: true}
                    , validTermStart: {required: true, date: true}
                    , validTermEnd: {required: true, date: true}
                    , showCounts: {required: true, digits: true}
                    , showPages: {required: true, digits: true}
                    <c:forEach items="${requiredLocaleCodeList}" var="required">
                    , ${required}: {required: true}
                    </c:forEach>
                }
                , messages: {},
                success: function (label) {
                    label.addClass("valid");
                }
                , submitHandler: function () {
                    $("#generateScripts").html("");
                    if($("#submitBtn").hasClass("save")) {
                        setCursor("entity", "wait");
                        var selectedSize = -1;
                        try {
                            $("#menuSOGroupMapListSelect option").each(function(index){
                                if(this.selected) {
                                    if($("#checkSOGroup").is(":checked")) {
                                        this.selected = false;
                                    } else {
                                        $("#generateScripts").append(
                                                $("<input type='hidden' name='menuSOGroupMapList["+(++selectedSize)+"].menuId'>").val(${entity.id})
                                        ).append(
                                                $("<input type='hidden' name='menuSOGroupMapList["+(selectedSize)+"].code'>").val(this.value)
                                        );
                                    }
                                }
                            });
                        } catch (e) {
                            alert("error:"+e);
                        }
                        try {
                            selectedSize = -1;
                            $("#menuSTBMapListSelect option").each(function(index){
                                if(this.selected) {
                                    if($("#checkStbType").is(":checked")) {
                                        this.selected = false;
                                    } else {
                                        $("#generateScripts").append(
                                                $("<input type='hidden' name='menuSTBMapList["+(++selectedSize)+"].menuId'>").val(${entity.id})
                                        ).append(
                                                $("<input type='hidden' name='menuSTBMapList["+(selectedSize)+"].code'>").val(this.value)
                                        );
                                    }
                                }
                            });
                        } catch (e) {
                            alert("error:"+e);
                        }
                        try {
                            selectedSize = -1;
                            $("#search-tabs-banner tbody tr").each(function(){
                                if($(this).find("input").length>0) {
                                    $("#generateScripts").append(
                                            $("<input type='hidden' name='menuBannerMapList["+(++selectedSize)+"].menuId'>").val(${entity.id})
                                    ).append(
                                            $("<input type='hidden' name='menuBannerMapList["+(selectedSize)+"].bannerId'>").val($(this).find("input:hidden").val())
                                    ).append(
                                            $("<input type='hidden' name='menuBannerMapList["+(selectedSize)+"].priority'>").val($(this).find("input:checkbox").is(":checked")?"1":"0")
                                    ).append(
                                            $("<input type='hidden' name='menuBannerMapList["+(selectedSize)+"].displayOrder'>").val(selectedSize+1)
                                    )
                                    ;
                                }
                            });
                        } catch (e) {
                            alert("error:"+e);
                        }
                        try {
                            selectedSize = -1;
                            $("#search-tabs-offer tbody tr").each(function(){
                                if($(this).find("input").length>0) {
                                    $("#generateScripts").append(
                                            $("<input type='hidden' name='menuOfferMapList["+(++selectedSize)+"].menuId'>").val(${entity.id})
                                    ).append(
                                            $("<input type='hidden' name='menuOfferMapList["+(selectedSize)+"].offerId'>").val($(this).find("input:hidden").val())
                                    ).append(
                                            $("<input type='hidden' name='menuOfferMapList["+(selectedSize)+"].priority'>").val($(this).find("input:checkbox").is(":checked")?"1":"0")
                                    ).append(
                                            $("<input type='hidden' name='menuOfferMapList["+(selectedSize)+"].displayOrder'>").val(selectedSize+1)
                                    )
                                    ;
                                }
                            });
                        } catch (e) {
                            alert("error:"+e);
                        }
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

            try {
                $("#pathTitle").text(currentTree.path);
            } catch (e) {
                alert(e);
            }

            $("#name").focus();

            //test
            $('#menuSOGroupMapListSelect').select2({ placeholder: '<spring:message code="tree.menu.soGroup"/>' }).change(function(){
                $(this).valid();
            });

            $('#menuSTBMapListSelect').select2({ placeholder: '<spring:message code="tree.menu.stbType"/>' }).change(function(){
                $(this).valid();
            });

            $('#categoryId').select2({ placeholder: '<spring:message code="tree.menu.category"/>' }).on('change', function(){
                $(this).valid();
            }).trigger('change');

            $("#linkSelectBtn").click(function() {
                var tUrl = "${ctxRoot}/category/categoryListPopup.do?popupType=categorySelectBox&targetId=categoryId";
                openModalWindow('02', tUrl);
            });

            $('#sortType').select2({ placeholder: '<spring:message code="tree.menu.sortType"/>' }).change(function(){
                $(this).valid();
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

            $('.switcher-selectAll-square').switcher({ theme: 'square' , on_state_content: '<spring:message code="button.all"/>',  off_state_content: '<spring:message code="button.select"/>'});
            $('.switcher-example-square').switcher({ theme: 'square' });

            $('.ui-accordion-header').on('click', function(){
                if($("#search-tabs-banner").hasClass("active")) {
                    openModalWindow("01", "${ctxRoot}/marketing/getBannerListForm.do?popupOption=selectManage");
                }
                if($("#search-tabs-offer").hasClass("active")) {
                    openModalWindow("01", "${ctxRoot}/asset/getOfferListForm.do?popupOption=selectManage");
                }
            });
            staticItemListCountUpdate();
        });
        function staticItemListCountUpdate() {
            var iCount = 0;
            $("#search-tabs-banner tbody tr").each(function(){
                iCount += $(this).find(".btn-primary").length;
            });
            if(iCount == 0) {
                $("#search-tabs-banner tbody tr").css("display", "");
            }
            $('.search-tabs').find("a:contains('Banner') > span").text(iCount);
            iCount = 0;
            $("#search-tabs-offer tbody tr").each(function(){
                iCount += $(this).find(".btn-primary").length;
            });
            if(iCount == 0) {
                $("#search-tabs-offer tbody tr").css("display", "");
            }
            $('.search-tabs').find("a:contains('Offer') > span").text(iCount);
        }

        function selectRow(row, removeColumns) {
            if('<spring:message code="button.add"/>' == $(row).val()) {
                var clone = $(row).closest('tr').clone();
                $(row).css("display", "none");
                clone.find(".btn-primary").val('<spring:message code="button.delete"/>');
                clone.find(".btn-primary").removeAttr("id");
                for(var columnIndex=removeColumns.length-1; columnIndex>-1;columnIndex--) {
                    clone.find("td:eq("+removeColumns[columnIndex]+")").remove();
                }
                $('#selectDiv table').append(clone.append($('<td><label class="checkbox-inline"><input type="checkbox" class="px"/><span class="lbl">&nbsp;</span></label></td>')).append($("<td><a class='btn btn-xs btn-outline' onclick=\"var row=$(this).closest('tr');row.insertBefore(row.prev());\"><i class='icon-arrow-up'/></a><a class='btn btn-xs btn-outline' onclick=\"var row=$(this).closest('tr');row.insertAfter(row.next());\"><i class='icon-arrow-down'/></a></td>")));
            } else {
                var selected = $("#"+$(row).attr("refer"));
                selected.val('<spring:message code="button.add"/>');
                selected.css("display", "");
                $(row).closest("tr").remove();
            }
            if(!$('#selectDiv').length>0) {
                staticItemListCountUpdate();
            }
        }
    </script>
</head>
<body>
    <form:form modelAttribute="entity" cssClass="panel form-horizontal">
        <div class="panel-heading">
            <form:hidden path="id"/>
            <form:hidden path="parentId"/>
            <form:hidden path="visible"/>
            <div id="generateScripts"></div>
            <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i>${title}</span>
        </div>
        <div class="panel-body" style="overflow:auto;">
            <div class="form-group">
                <label for="uriId" class="col-sm-2 control-label"><spring:message code="tree.category.path"/></label>
                <div class="col-sm-10">
                    <pre id="pathTitle"></pre>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label"><spring:message code="tree.menu.name"/></label>
                <div class="col-sm-10">
                    <ul id="tabs-default" class="nav nav-tabs nav-tabs-xs">
                        <c:forEach items="${localeList}" var="locale" varStatus="status">
                            <c:choose>
                                <c:when test="${status.first}">
                                    <li class="active">
                                        <a href="#tabs-default-${locale.localeCode}" data-toggle="tab">${locale.languageForInput}</span></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="">
                                        <a href="#tabs-default-${locale.localeCode}" data-toggle="tab">${locale.languageForInput}</span></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                    <div class="tab-content tab-content-bordered">
                        <c:forEach items="${localeList}" var="locale" varStatus="status">
                            <c:choose>
                                <c:when test="${status.first}">
                                    <div class="tab-pane fade active in" id="tabs-default-${locale.localeCode}">
                                        <form:hidden path="menuLocaleList['${locale.localeCode}'].menuId" value="${entity.id}"/>
                                        <form:hidden path="menuLocaleList['${locale.localeCode}'].localeCode" value="${locale.localeCode}"/>
                                        <form:input path="menuLocaleList['${locale.localeCode}'].name"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="tab-pane fade" id="tabs-default-${locale.localeCode}">
                                        <form:hidden path="menuLocaleList['${locale.localeCode}'].menuId" value="${entity.id}"/>
                                        <form:hidden path="menuLocaleList['${locale.localeCode}'].localeCode" value="${locale.localeCode}"/>
                                        <form:input path="menuLocaleList['${locale.localeCode}'].name"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="menuSOGroupMapListSelect" class="col-sm-2 control-label"><spring:message code="tree.menu.soGroup"/></label>
                <div class="col-sm-10">
                    <input type="checkbox" path="checkSOGroup" id="checkSOGroup" data-class="switcher" class="form-control switcher-selectAll-square valign-middle" onclick="$('#menuSOGroupMapListSelect').attr('disabled', this.checked)" <c:out value="${fn:length(entity.menuSOGroupMapList)>0?'':'checked'}"/>/>
                    <div class="select2-success">
                        <select id="menuSOGroupMapListSelect" name="menuSOGroupMapListSelect" class="form-control" multiple="multiple" <c:out value="${fn:length(entity.menuSOGroupMapList)>0?'':'disabled '}"/>readonly>
                            <ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_SO_TYPE')}" selected="${entity.menuSOGroupMapList}"/>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="menuSTBMapListSelect" class="col-sm-2 control-label"><spring:message code="tree.menu.stbType"/></label>
                <div class="col-sm-10">
                    <input type="checkbox" path="checkStbType" id="checkStbType" data-class="switcher" class="form-control switcher-selectAll-square valign-middle" onclick="$('#menuSTBMapListSelect').attr('disabled', this.checked)"  <c:out value="${fn:length(entity.menuSTBMapList)>0?'':'checked'}"/>/>
                    <div class="select2-success">
                        <select id="menuSTBMapListSelect" name="menuSTBMapListSelect" class="form-control" multiple="multiple" <c:out value="${fn:length(entity.menuSTBMapList)>0?'':'disabled '}"/>readonly >
                            <ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_STB_TYPE')}" selected="${entity.menuSTBMapList}"/>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="categoryId" class="col-sm-2 control-label"><spring:message code="tree.menu.category"/></label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <div class="select2-success">
                            <select id="categoryId" name="categoryId" multiple="multiple">
                                <c:if test="${not empty entity.categoryId}"><option selected value="${entity.categoryId}" selected>${entity.categoryName}</option></c:if>
                            </select>
                        </div>
                        <span class="input-group-btn">
                            <button class="btn" type="button" id="linkSelectBtn" ><spring:message code="button.select" /></button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="sortType" class="col-sm-2 control-label"><spring:message code="tree.menu.sortType"/></label>
                <div class="col-sm-10">
                    <select id="sortType" name="sortType" class="form-control" readonly>
                        <option value=""><spring:message code="combo.select" /></option>
                        <ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_DP_TYPE')}" selected="${entity.sortType}"/>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="adult" class="col-sm-2 control-label"><spring:message code="tree.menu.adult"/></label>
                <div class="col-sm-10">
                    <form:checkbox path="adult" id="adult" data-class="switcher" cssClass="form-control switcher-example-square valign-middle" placeholder="${zm:message(wmSource,'tree.menu.adult.guide')}"/>
                </div>
            </div>
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
            <div class="form-group">
                <label for="showCounts" class="col-sm-2 control-label"><spring:message code="tree.menu.showCounts"/></label>
                <div class="col-sm-10">
                    <form:input path="showCounts" id="showCounts" cssClass="form-control" placeholder="${zm:message(wmSource,'tree.menu.showCounts')}"/>
                </div>
            </div>
            <div class="form-group">
                <label for="showPages" class="col-sm-2 control-label"><spring:message code="tree.menu.showPages"/></label>
                <div class="col-sm-10">
                    <form:input path="showPages" id="showPages" cssClass="form-control" placeholder="${zm:message(wmSource,'tree.menu.showPages')}"/>
                </div>
            </div>

            <!-- Tabs -->
            <div class="search-tabs">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#search-tabs-banner" data-toggle="tab">Banner <span class="label label-success"></span></a>
                    </li>
                    <li>
                        <a href="#search-tabs-offer" data-toggle="tab">Offer <span class="label label-danger"></span></a>
                    </li>
                </ul> <!-- / .nav -->
            </div>
            <!-- / Tabs -->

            <!-- Panel -->
            <div class="panel search-panel">
                <div class="ui-accordion-header">
                    <i class="fa fa-thumb-tack page-header-icon"></i>&nbsp;&nbsp;<spring:message code="tree.menu.static"/>
                </div>

                <!-- Search results -->
                <div class="panel-body tab-content">

                    <!-- banner search -->
                    <div class="search-messages tab-pane fade widget-messages active in" id="search-tabs-banner">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th><spring:message code="tree.menu.banner.name"/></th>
                                <th><spring:message code="tree.menu.banner.term"/></th>
                                <th><spring:message code="tree.menu.offer.displayOrder"/></th>
                                <th><spring:message code="tree.menu.offer.displayOrder"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${entity.menuBannerMapList}" var="item" varStatus="status">
                                <tr>
                                    <td>${item.bannerId}<input type="hidden" name="bannerId" value="${item.bannerId}"></td>
                                    <td>name<input refer="${item.bannerId}" type=button class='btn btn-primary btn-xs' value='<spring:message code="button.delete"/>' onclick="selectRow(this,[2,3,4])"></td>
                                    <td>2014.08.01 12:00:00 ~ 2014.12.31 23:59:59</td>
                                    <td><label class="checkbox-inline"><input type="checkbox" class="px"<c:if test="${item.priority}"> checked</c:if>/><span class="lbl">&nbsp;</span></label></td>
                                    <td><a class="btn btn-xs btn-outline" onclick="var row=$(this).closest('tr');row.insertBefore(row.prev());"><i class="icon-arrow-up"/></a><a class="btn btn-xs btn-outline" onclick="var row=$(this).closest('tr');row.insertAfter(row.next());"><i class="icon-arrow-down"/></a></td>
                                </tr>
                            </c:forEach>
                                <tr style="display:<c:if test="${fn:length(entity.menuBannerMapList)>0}">none</c:if>"><td colspan="5"><spring:message code="result.noSelected" arguments="${zm:message(wmSource,'list')}"/></td></tr>
                            </tbody>
                        </table>
                    </div>
                    <!-- / Messages search -->

                    <!-- offer search -->
                    <div class="search-users tab-pane fade" id="search-tabs-offer">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th class="text-center">ID</th>
                                <th><spring:message code="tree.menu.offer.name"/></th>
                                <th><spring:message code="tree.menu.offer.price"/></th>
                                <th><spring:message code="tree.menu.offer.term"/></th>
                                <th><spring:message code="tree.menu.offer.priority"/></th>
                                <th><spring:message code="tree.menu.offer.displayOrder"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${entity.menuOfferMapList}" var="item" varStatus="status">
                                <tr>
                                    <td>${item.offerId}<input type="hidden" name="offerId" value="${item.offerId}"></td>
                                    <td>name<input refer="${item.offerId}" type=button class='btn btn-primary btn-xs' value='<spring:message code="button.delete"/>' onclick="selectRow(this,[3,4])"></td>
                                    <td>aa</td>
                                    <td>2014.08.01 12:00:00 ~ 2014.12.31 23:59:59</td>
                                    <td><label class="checkbox-inline"><input type="checkbox" class="px"<c:if test="${item.priority}"> checked</c:if>/><span class="lbl">&nbsp;</span></label></td>
                                    <td><a class="btn btn-xs btn-outline" onclick="var row=$(this).closest('tr');row.insertBefore(row.prev());"><i class="icon-arrow-up"/></a><a class="btn btn-xs btn-outline" onclick="var row=$(this).closest('tr');row.insertAfter(row.next());"><i class="icon-arrow-down"/></a></td>
                                </tr>
                            </c:forEach>
                            <tr style="display:<c:if test="${fn:length(entity.menuOfferMapList)>0}">none</c:if>"><td colspan="6"><spring:message code="result.noSelected" arguments="${zm:message(wmSource,'list')}"/></td></tr>
                            </tbody>
                        </table>
                    </div>
                    <!-- / offer search -->
                </div>
                <!-- / Search results -->
            </div>
            <!-- / Panel -->
            <!-- / .form-group -->
        </div>
        <div class="panel-footer pull-right">
            <div class="form-group" style="margin-bottom: 0;">
                <div class="col-sm-offset-2 col-sm-10 pull-right">
                    <c:choose>
                        <c:when test="${entity.id == null || entity.id == 0}">
                            <button id="submitBtn" class="btn btn-primary save"><spring:message code="button.save"/></button>
                        </c:when>
                        <c:otherwise>
                            <button id="submitBtn" class="btn btn-primary save"><spring:message code="button.edit"/></button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </form:form>
</body>
</html>



