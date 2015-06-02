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
    <title><spring:message code="tree.category.main" var="title"/>${title}</title>
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
        var requestParam = {
            treeMove : {
                moveAlertMessage : '<spring:message code="confirm.save" arguments="${zm:message(wmSource,'tree.category')}"/>'
            }
        };
        var trees = [];
        <c:forEach items="${treeList}" var="tree">trees.push(new Tree(${tree}));
        </c:forEach>

// 		function expandFirstLevelNode()
// 		{
// 			var numberOfIconMinus = $('.icon-minus-sign').length;
// 			for(var i = 0 ; i < numberOfIconMinus ; i++)
// 			{
//          		var nodeChildren = $('.icon-minus-sign').eq(0).parent('span').parent('li.parent_li').find(' > .ulTree > .liTree');
//          		nodeChildren.toggle('fast');
//          		$('.icon-minus-sign').eq(0).parent('span').attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
//          	}
// 		}

		function reLoadTree() {
			loadTree("tree", treeClone['tree']);

// 			expandFirstLevelNode();
// 			var numberOfIconMinus = $('.icon-minus-sign').length;
// 			for(var i = 0 ; i < numberOfIconMinus ; i++)
// 			{
//          		var nodeChildren = $('.icon-minus-sign').eq(0).parent('span').parent('li.parent_li').find(' > .ulTree > .liTree');
//          		nodeChildren.toggle('fast');
//          		$('.icon-minus-sign').eq(0).parent('span').attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
//          	}
        }


		var treeMenu = [
            {
                id:'add'
                , name: '<i class="fa fa-plus"/> <spring:message code="menu.tree.add"/>'
                //, img: '${ctxRoot}/images/icons/details_open.png'
                , title: 'create button'
                , fun: function (event) {
                    try {
                        var tUrl = "${ctxRoot}/category/registCategoryForm.do?parentId=" + currentTree.id;
                        $('#detailViewDiv').load(tUrl);
                    } catch (e) {
                        alert(e);
                    }
                }
            }
            , {
                name: '<i class="fa fa-minus"/> <spring:message code="menu.tree.delete"/>'
                , title: 'delete button'
                , fun: function () {
                    openConfirm('<spring:message code="confirm.save" arguments="${zm:message(wmSource,'tree.category')}"/>', function(result) {
                        if (result) {
                            try {
                                $.ajax({
                                    type: "GET"
                                    , url: "${ctxRoot}/category/deleteCategory.do"
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
            , {
                name: '<i class="fa fa-share"/> <spring:message code="menu.tree.move"/>'
                , title: 'move button'
                , fun: function () {
                    try {
                        openModalWindow("01", "${ctxRoot}/category/categoryListPopup.do?popupType=categoryMove&id="+currentTree.id);
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
                                    , url: ctxRoot + "/category/setVisible.do"
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
                    openConfirm('<spring:message code="confirm.move" arguments="${zm:message(wmSource,'button.up')} ${zm:message(wmSource,'tree.category')} <input type=text size=1 value=1 onkeyup=\"orderValue=this.value;\">${zm:message(wmSource,'button.step')}"/>', function(result) {
                        if (result) {
                            try {
                                $.ajax({
                                    type: "GET"
                                    , url: ctxRoot + "/category/editDisplayOrder.do"
                                    , data: String.format("parentId={0}&treeIds={1}", currentTree.parentId, getOrder("tree", orderValue*-1))
                                    , success: function (msg) {
                                        if (msg == 'Success') {
                                            location.reload();
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
                    openConfirm('<spring:message code="confirm.move" arguments="${zm:message(wmSource,'button.down')} ${zm:message(wmSource,'tree.category')} <input type=text size=1 value=1 onkeyup=\"orderValue=this.value;\">${zm:message(wmSource,'button.step')}"/>', function(result) {
                        if (result) {
                            try {
                                $.ajax({
                                    type: "GET"
                                    , url: ctxRoot + "/category/editDisplayOrder.do"
                                    , data: String.format("parentId={0}&treeIds={1}", currentTree.parentId, getOrder("tree", orderValue))
                                    , success: function (msg) {
                                        if (msg == 'Success') {
                                            location.reload();
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
    (function($){
        $(document).ready(function () {
            loadTree("tree", trees);
            
//             expandFirstLevelNode()
            
//             var numberOfIconMinus = $('.icon-minus-sign').length;
//             for(var i = 0 ; i < numberOfIconMinus ; i++)
//             	{
//             		var nodeChildren = $('.icon-minus-sign').eq(0).parent('span').parent('li.parent_li').find(' > .ulTree > .liTree');
//             		nodeChildren.toggle('fast');
//             		$('.icon-minus-sign').eq(0).parent('span').attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
//             	}
            

            var modalSetting = {
                top: '50%',
                left: '50%',
                afterOpen: function (data, e) {
                    var x = e.pageX - this.offsetLeft;
                    var y = e.pageY - this.offsetTop;

                    var menu = data.menu,
                            menuWidth = menu.innerWidth(),
                            menuHeight = menu.innerHeight();

                    /*menu.css({
                     'margin-left':-(menuWidth/2)+'px',
                     'margin-top':-(menuHeight/2)+'px'
                     });*/
                    menu.css({
                        top: y + "px",
                        left: x + "px"
                    });
                    $('#overlayDiv').show();
                },
                onClose: function (data, e) {
                    var menu = data.menu;
                    menu.css({
                        'margin-left': '',
                        'margin-top': ''
                    });

                    $('#overlayDiv').hide();
                }
            };

            //$('#openModal').contextMenu('#modalBox', modalSetting);
        });
    })(jQuery);
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
    <div class="col-md-5" style="overflow-x: auto;">
        <!-- modal menu -->
        <%--<input type="button" id="openModal" value="Open model box">--%>
        <div id="overlayDiv"></div>
        <ul id="modalBox" class="dropdown-menu">
            <li><a href="#uidemo-tabs-default-demo-dropdown1" data-toggle="tab" tag="1">노출여부</a></li>
            <li><a href="#uidemo-tabs-default-demo-dropdown2" data-toggle="tab" tag="2">추가</a></li>
            <li><a href="#uidemo-tabs-default-demo-dropdown3" data-toggle="tab" tag="3">삭제</a></li>
            <li><a href="#uidemo-tabs-default-demo-dropdown4" data-toggle="tab" tag="4">이동</a></li>
        </ul>
        <div class="panel colourable">
            <div class="panel-heading">
                <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="tree.category.list"/></span>
                <%--<br/>
                <div class="btn-group">
                    <button type="button" class="btn"><i class="fa fa-plus-square-o"></i></button>
                    <button type="button" class="btn"><i class="fa fa-minus-square-o"></i></button>
                </div>--%>
            </div>
            <div class="panel-body">
                <div id="tree" class="tree well" style="overflow-x: auto;"></div>
            </div>
        </div>
    </div>
    <div class="col-md-7" id="detailViewDiv">
        <div class="panel colourable">
            <div class="panel-heading">
                <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="tree.category.info"/></span>
            </div>
            <div class="panel-body" style="height:450px; overflow:auto;">
            </div>
            <%--<div class="panel-footer">
                <button type="submit" class="btn btn-primary">Sign in</button>
            </div>--%>
        </div>
    </div>
</div>
<div id="console" class="col-md-8"></div>
<div id="console_sub" class="col-md-8"></div>
</body>
</html>