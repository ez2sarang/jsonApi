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
        trees = [];
        <c:forEach items="${treeList}" var="tree">trees.push(new Tree(${tree}));
        </c:forEach>
        var treeMenu = [
            {
                id:'add',
                name: '<i class="fa fa-plus"/> <spring:message code="menu.tree.add"/>',
                title: 'create button',
                fun: function (event) {
                    try {
                        var tUrl = "${ctxRoot}/menu/registMenuForm.do?parentId=" + currentTree.id;
                        $('#detailViewDiv').load(tUrl);
                    } catch (e) {
                        alert(e);
                    }
                }
            }
            ,
            {
                name: '<i class="fa fa-minus"/> <spring:message code="menu.tree.delete"/>',
                title: 'delete button',
                fun: function () {
                    openConfirm('<spring:message code="confirm.delete" arguments="${zm:message(wmSource,'tree.menu')}"/>', function(result) {
                        if (result) {
                            try {
                                $.ajax({
                                    type: "GET"
                                    , url: "${ctxRoot}/menu/deleteMenu.do"
                                    , data: {id:currentTree.id}
                                    , success: function (msg) {
                                        if (msg == 'Success') {
                                            for (var idx = 0; idx < treeClone["tree"].length; idx++) {
                                                if (currentTree.id == treeClone["tree"][idx].id) {
                                                    treeClone["tree"].splice(idx, 1);
                                                    break;
                                                }
                                            }
                                            loadTree("tree", treeClone["tree"]);
                                            $('.switcher-example-square').switcher({ theme: 'square' });
                                        } else {
                                            openAlert('Fail', msg, function () {
                                            });
                                        }
                                    }
                                    , error:  function (xhr, ajaxOptions, thrownError) {
                                        openAlert('Fail', 'Failure');
                                    }
                                });
                            } catch (e) {
                                alert(e);
                            }
                        }
                    });
                }
            }
            ,
            {
                name: '<i class="fa fa-share"/> <spring:message code="menu.tree.move"/>',
                title: 'move button',
                fun: function () {
                    try {
                        openModalWindow("01", "${ctxRoot}/menu/menuListPopup.do?popupType=menuMove&parentId=${param.parentId}&id="+currentTree.id);
                    } catch (e) {
                        alert("error:"+e);
                    }
                }
            }
            , {
                name: '<i class="fa fa-eye"/> <spring:message code="menu.tree.visible"/>'
                , title: 'change visible'
                , fun: function () {
                    openConfirm('<spring:message code="confirm.save" arguments="${zm:message(wmSource,'menu.tree.visible')}"/>', function(result) {
                        if (result) {
                            try {
                                $.ajax({
                                    type: "GET"
                                    , url: ctxRoot + "/menu/setVisible.do"
                                    , data: {id:currentTree.id, visible:currentTree.visible?false:true}
                                    , success: function (msg) {
                                        if (msg == 'Success') {
                                            for (var idx = 0; idx < treeClone["tree"].length; idx++) {
                                                if (currentTree.id == treeClone["tree"][idx].id) {
                                                    treeClone["tree"][idx].visible = currentTree.visible?false:true;
                                                    currentTree.visible = treeClone["tree"][idx].visible;
                                                    $("#"+currentTree.id).find("span:first").css("background-color", treeClone["tree"][idx].visible?"#fff":"gray");
                                                    break;
                                                }
                                            }
                                        } else {
                                            openAlert('Fail', msg, function () {
                                            });
                                        }
                                    }
                                    , error:  function (xhr, ajaxOptions, thrownError) {
                                        openAlert('Fail', 'Failure');
                                    }
                                });
                            } catch (e) {
                                alert(e);
                            }
                        }
                    });
                }
            }
            , {
                name: '<i class="fa fa-arrow-up"/> <spring:message code="button.up"/>'
                , title: 'move up'
                , fun: function() {
                    orderValue = 1;
                    openConfirm('<spring:message code="confirm.move" arguments="${zm:message(wmSource,'button.up')} ${zm:message(wmSource,'tree.menu')} <input type=text size=1 value=1 onkeyup=\"orderValue=this.value;\">${zm:message(wmSource,'button.step')}"/>', function(result) {
                        if (result) {
                            try {
                                $.ajax({
                                    type: "GET"
                                    , url: ctxRoot + "/menu/editDisplayOrder.do"
                                    , data: String.format("parentId={0}&treeIds={1}", currentTree.parentId, getOrder("menuTree", orderValue*-1))
                                    , success: function (msg) {
                                        if (msg == 'Success') {
                                            $('#detailDiv').load("${ctxRoot}/menu/subMenuList.do?parentId=${param.parentId}");
                                        } else {
                                            openAlert('Fail', msg, function () {
                                            });
                                        }
                                    }
                                    , error:  function (xhr, ajaxOptions, thrownError) {
                                        openAlert('Fail', 'Failure');
                                    }
                                });
                            } catch (e) {
                                alert(e);
                            }
                        }
                    });
                }
            }
            , {
                name: '<i class="fa fa-arrow-down"/> <spring:message code="button.down"/>'
                , title: 'move down'
                , fun: function() {
                    orderValue = 1;
                    openConfirm('<spring:message code="confirm.move" arguments="${zm:message(wmSource,'button.down')} ${zm:message(wmSource,'tree.menu')} <input type=text size=1 value=1 onkeyup=\"orderValue=this.value;\">${zm:message(wmSource,'button.step')}"/>', function(result) {
                        if (result) {
                            try {
                                $.ajax({
                                    type: "GET"
                                    , url: ctxRoot + "/menu/editDisplayOrder.do"
                                    , data: String.format("parentId={0}&treeIds={1}", currentTree.parentId, getOrder("menuTree", orderValue))
                                    , success: function (msg) {
                                        if (msg == 'Success') {
                                            $('#detailDiv').load("${ctxRoot}/menu/subMenuList.do?parentId=${param.parentId}");
                                        } else {
                                            openAlert('Fail', msg, function () {
                                            });
                                        }
                                    }
                                    , error:  function (xhr, ajaxOptions, thrownError) {
                                        openAlert('Fail', 'Failure');
                                    }
                                });
                            } catch (e) {
                                alert(e);
                            }
                        }
                    });
                }
            }
        ];
        rootTree = {id:${param.parentId}, name:"Roots", parentId:0}

        $(document).ready(function () {
            loadTree("menuTree", trees);
        });
    </script>
</head>
<body>
<div class="row">
    <div class="panel-body" style="overflow:auto;">
        <div class="col-md-5" style="overflow-x: auto;">
            <div class="panel colourable">
                <div class="panel-heading">
                    <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="tree.menu.list"/></span>
                </div>
                <div class="panel-body">
                    <div id="menuTree" class="tree well" style="overflow-x: auto;"></div>
                </div>
            </div>
        </div>
        <div class="col-md-7" id="detailViewDiv">
            <div class="panel colourable">
                <div class="panel-heading">
                    <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="tree.menu.info"/></span>
                </div>
                <div class="panel-body" style="height:550px; overflow:auto;">
                </div>
            </div>
        </div>
    </div>
    <div class="panel-footer">
        <div style="float:left;">
            <button class="btn btn-primary" onclick="goList();"><spring:message code="button.goBack"/></button>
        </div>
    </div>
</div>
<div id="console" class="col-md-8"></div>
<div id="console_sub" class="col-md-8"></div>
</body>
</html>