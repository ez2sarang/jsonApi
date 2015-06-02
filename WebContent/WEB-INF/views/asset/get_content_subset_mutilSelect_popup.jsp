<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib prefix="zm" uri="ziminTag"
%><%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="contentSubset.contentAsset.list" var="title"/>${title}</title>
<style type="text/css" class="init">
	.zimin-left {
		float:left;
	}
	.zimin-right {
		float:right;
	}
</style>
<link rel="stylesheet" href="${ctxRoot}/js/spinner/css/custom.css">
<link rel="stylesheet" href="${ctxRoot}/js/spinner/css/iosOverlay.css">
<link rel="stylesheet" href="${ctxRoot}/js/spinner/css/prettify.css">
<script src="${ctxRoot}/js/zimin.jquery.extend.js"></script>
<script src="${ctxRoot}/js/spinner/js/iosOverlay.js"></script>
<script src="${ctxRoot}/js/spinner/js/spin.min.js"></script>
<script src="${ctxRoot}/js/spinner/js/prettify.js"></script>
<script src="${ctxRoot}/js/spinner/js/custom.js"></script>
<script src="${ctxRoot}/js/spinner/js/modernizr-2.0.6.min.js"></script>
<script language="javascript">
    var commonCode;
    callbackCommonCode('contentSubsetType', function (jsonObj){commonCode=jsonObj});
	$(document).ready(function () {
        $(this).find('.modal-body').css({
            'width':'900px'
            //, 'max-width':'100%'
        });
        $(this).find('.modal-content').css({
            'width':'900px'
        });

        //alert(JSON.stringify(commonCode));

        var table = $('#contentAssetDatatable').DataTable({
            "columns": [
                { "data": "contentGroupId" }
                , { "data": "titleBrief" }
                , { "data": "contentAssetList" }
                , { "data": "cpId" }
                , { "data": "startDateTime"}
                , { "data": "endDateTime" }
            ], "columnDefs": [
                {
                    "render": function (data, type, row) {

                        return data /*+ "<input type=hidden name='contentSubsetId' value='"+data+"'>"*/;
                    },
                    "targets": 0
                },
				{"visible": false, "targets": [-1]},
				{
					"render": function (data, type, row) {
                        return '<a href="javascript:viewDetail(\'' + row.movieSubsetId + '\')">' + data + '</a> ';
                    },
	                "targets": 1
				},
				{
					"render": function (data, type, row) {
	                    return data + ' ~ '+ row.endDateTime;
	                },
	                "targets": 4
				},
                {
                    "render": function (data, type, row) {
                        var csList;
                        try {
                            csList = $.map(row.contentAssetList, function(cs){
                                var assetFile;
                                try {
                                    assetFile = eval(String.format("commonCode.contentSubsetType${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_MOVIE')}['{0}']['fileTypeName']", cs.fileType));
                                } catch (e) {
                                    //alert("map:"+e);
                                    assetFile = cs.fileType;
                                }

                                if($('#selectDiv table input[value=' + row.contentGroupId + '_'+cs.contentAssetId+']').length>0) {
                                    return "<input id='"+row.contentGroupId+"_"+cs.contentAssetId+"' refer='"+row.contentGroupId+"_"+cs.contentAssetId+"' type=button class='btn btn-primary btn-xs' value='"+assetFile+" <spring:message code="button.add"/>' onclick='selectItem(this,[2,3,4])' style='display:none'>";
                                } else {
                                    return "<input id='"+row.contentGroupId+"_"+cs.contentAssetId+"' refer='"+row.contentGroupId+"_"+cs.contentAssetId+"' type=button class='btn btn-primary btn-xs' value='"+assetFile+" <spring:message code="button.add"/>' onclick='selectItem(this,[2,3,4])'>";
                                }
                            }).join("<p/>");
                            //alert(csList);
                        } catch (e) {
                            alert("csList:"+e);
                        }
                        return csList;
                    },
                    "targets": 2
                }
			],
			"searching": false,
			"sort" : false,
			"dom": '<"table-header clearfix"<"zimin-left"<"DT-per-page"l>><"DT-lf-right"<"zimin-right">>>t<"table-footer clearfix"<"DT-label"i><"DT-pagination"p>>',
			"processing": true,
			"serverSide": true,
	        "ajax": {
	            "url": "${ctxRoot}/asset/listContentGroup.json",
	            "type": "POST",
	            "data": function ( d ) {
	                d.movieSubsetId = 0;
	                d.titleBrief = $('#contentSubsetForm #titleBrief').val();
	                d.contentSubsetType = '${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_MOVIE')}';
                    d.searchOptionPeriodPast = 'false';
                    d.searchOptionPeriodPresent = 'true';
                    d.searchOptionPeriodFuture = 'true';
	            },
	            "beforeSend": function(jqXHR, settings) {
			  		jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
			  		try {
			  			$("#contentSubsetForm #searchButton").prop("disabled", true);
						setCursor("contentSubsetForm", "wait");
			  		} catch(e) {}
			    },
			    "complete": function(jqXHR, textStatus){
					try {
						$("#contentSubsetForm #searchButton").prop("disabled", false);
						setCursor("contentSubsetForm", "default");
			  		} catch(e) {}
				}

	        }
		});

		// 첫페이지는 search parameter 없이 요청됨
		$('.zimin-right').html($('#temp_search').html());

		$("#contentSubsetForm #searchButton").click(function() {
			if ($("#contentSubsetForm #titleBrief").val().length > 0
					&& $("#contentSubsetForm #titleBrief").val().length < 2) {
				openAlert('Alert!!', '<spring:message code="errors.search.moreCharacters" arguments="2"/>', function() {
				});
			} else {
				table.draw(true);
			}
		});

		$("#contentSubsetForm #titleBrief").keypress(function(e) {
			if(e.keyCode == 13) {
				e.preventDefault();
				$("#contentSubsetForm #searchButton").trigger("click");
		    }
		});

		$(window).bind('hashchange', function () {
	        var hash = window.location.hash.slice(1);
	        if (hash == 'detailView') {
	        	$('#mediaListDiv').hide();
	    		$('#mediaDetailDiv').show();
	        } else {
	        	goList();
	        }
	    });
	});

    /**
    * 저장
     */
    function addRow() {
        try {
            if($('#selectDiv input[name=contentId]').map(function(){return $(this).val();}).get().length == 0) {
                openAlert('<spring:message code="result.noSelected" arguments="${zm:message(wmSource, 'menu.interfaces.nvod.assets')}"/>','');
                return;
            }
        } catch (e) {
            alert('no select:'+e);
        }
        if($('#selectDiv input[name=price]').map(function(){
            if($.isBlank(this.value)) {
                return false;
            }
        }).get().length>0) {
            openAlert('<spring:message code="errors.required" arguments="${zm:message(wmSource, 'content.provider.price')}"/>','');
            return;
        }
        if($('#selectDiv input[name=price]').map(function(){
            if(!$.isNumeric(this.value)) {
                return false;
            }
        }).get().length>0) {
            openAlert('<spring:message code="errors.required.number" arguments="${zm:message(wmSource, 'content.provider.price')}"/>','');
            return;
        }
        $.ajax({
            type: "POST"
            , url: "${ctxRoot}/interface/countNvodRequest.do"
            , data: {contentId : $('#selectDiv input[name=contentId]').map(function(){return $(this).val();}).get().join(';')}
            , "beforeSend": function(xhr, settings) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            }
            , success: function(data, textStatus, jqXHR){
                try {
                    var exist = parseInt(data)>0;
                    openConfirm(exist?'<spring:message code="confirm.save" arguments="${zm:message(wmSource,'content.nvod.id.duplicated')}"/>':'<spring:message code="confirm.save" arguments="${zm:message(wmSource,'menu.interfaces.nvod.assets')}"/>',function(result) {
                        $(".btn").css("cursor","wait");
                        $(".btn").prop("disabled", true);

                        if(result) {
                            var opts = {
                                lines: 13, // The number of lines to draw
                                length: 11, // The length of each line
                                width: 5, // The line thickness
                                radius: 17, // The radius of the inner circle
                                corners: 1, // Corner roundness (0..1)
                                rotate: 0, // The rotation offset
                                color: '#FFF', // #rgb or #rrggbb
                                speed: 1, // Rounds per second
                                trail: 60, // Afterglow percentage
                                shadow: false, // Whether to render a shadow
                                hwaccel: false, // Whether to use hardware acceleration
                                className: 'spinner', // The CSS class to assign to the spinner
                                zIndex: 2e9, // The z-index (defaults to 2000000000)
                                top: 'auto', // Top position relative to parent in px
                                left: 'auto' // Left position relative to parent in px
                            };
                            var target = document.createElement("div");
                            document.body.appendChild(target);
                            var spinner = new Spinner(opts).spin(target);
                            var overlay = iosOverlay({
                                text: "Loading",
                                spinner: spinner
                            });
                            try{
                                $.ajax({
                                    type: "GET"
                                    , url: "${ctxRoot}/interface/registNvodRequest.do"
                                    , data: {contentId : $('#selectDiv input[name=contentId]').map(function(index){return String.format("{0}_{1}",$(this).val(),$('#selectDiv input[name=price]').map(function(){return $(this).val();}).get()[index]);}).get().join(';')}
                                    , success: function(data, textStatus, jqXHR){
                                        $(".btn").css("cursor","default");
                                        $(".btn").prop("disabled", false);
                                        if (data == 'Success') {
                                            overlay.hide();
                                            openInform('Success', data, function() {
                                                location.reload();
                                            });
                                        } else {
                                            overlay.hide();
                                            openAlert('Fail', data, function() {
                                            });
                                        }
                                    }
                                    , error: function(jqXHR, textStatus, errorThrown){
                                        overlay.hide();
                                        openAlert('Fail', 'Failure:' + this.data+"<br>"+textStatus);
                                        $(".btn").css("cursor","default");
                                        $(".btn").prop("disabled", false);
                                    }
                                });
                            } catch(e) {
                                overlay.hide();
                                alert (e);
                            }
                        } else {
                            $(".btn").css("cursor","default");
                            $(".btn").prop("disabled", false);
                        }
                    });
                } catch (e) {
                    openAlert('Fail', data, function() {
                    });
                }

            }
            , error: function(jqXHR, textStatus, errorThrown){
                openAlert('Fail', 'Failure:' + this.data+"<br>"+textStatus);
            }
        });
    }

    function selectItem(btn, removeColumns) {
        if($(btn).val().indexOf('<spring:message code="button.add"/>')>-1) {
            var clone = $(btn).closest('tr').clone();
            for(var columnIndex=removeColumns.length-1; columnIndex>-1;columnIndex--) {
                clone.find("td:eq("+removeColumns[columnIndex]+")").remove();
            }
            clone.append(
                    $("<td>")
                            .append($("<input type=hidden name=contentId>").val($(btn).attr("refer")))
                            .append($("<input type=text id=price name=price size=6>").attr("placeholder", "<spring:message code='content.provider.price'/>"))
                            .append($(btn).clone().removeAttr("id").val($(btn).val().replace('<spring:message code="button.add"/>','<spring:message code="button.delete"/>')))
            );
            $('#selectDiv table').append(clone);
            $(btn).css("display", "none");
        } else {
            var selected = $("#"+$(btn).attr("refer"));
            selected.css("display", "");
            $(btn).closest("tr").remove();
        }
    }
	
	function goList() {
		$('#mediaDetailDiv').hide();
		$('#mediaListDiv').show();
	}
	
	function viewDetail(contentSubsetId) {
		$('#mediaListDiv').hide();
		location.href="#mediaDetailDiv";
		var tUrl = "${ctxRoot}/asset/viewContentSubset.do?location=nvod&contentSubsetId=" + contentSubsetId;
		$('#mediaDetailDiv').load(tUrl);
		$('#mediaDetailDiv').show();
	}
</script>
</head>
<body>
    <div class="page-body" id="mediaListDiv">
        <div id="selectDiv" class="col-md-5" style="height: 500px; overflow-x: auto;">
            <div class="panel-heading">
                <div id="generateScripts"></div>
                <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i>${title}</span>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered dataTable no-footer"></table>
            </div>
            <div class="panel-footer">
                <div align="right">
                    <button type="button" class="btn btn-primary" onclick="addRow()"><spring:message code="button.file.add"/></button>
                </div>
            </div>
        </div>
        <div class="col-md-7" id="listDiv">
            <form:form modelAttribute="contentSubset" id="contentSubsetForm" >
                <div class="col-sm-12">

                    <div class="table-primary">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%" class="table table-striped table-bordered" id="contentAssetDatatable">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th><spring:message code="contentSubset.title" var="titleLabel"/>${titleLabel}</th>
                                <th><spring:message code="contentSubset.fileType"/></th>
                                <th><spring:message code="contentSubset.cpId"/></th>
                                <th><spring:message code="contentSubset.availablePeriod"/></th>
                                <th><spring:message code="contentSubset.availablePeriod"/></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <div align="right">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
                        <p/>
                    </div>
                </div>
            </form:form>

            <div id="temp_search" style="display:none">
                <table>
                    <tr>
                        <td>
                            <spring:message code="button.search" var="searchLabel"/>
                            <input type="text" id="titleBrief" name="titleBrief" class="form-control" placeholder="${titleLabel} ${searchLabel}">
                        </td>
                        <td>
                            &nbsp;
                            <button type="button" class="btn btn-default" id="searchButton">${searchLabel}</button>
                            &nbsp;
                        </td>
                    </tr>
                </table>
            </div>

        </div>
    </div>

	<a name="detailView"></a>
	<div class="row" id="mediaDetailDiv" style="display:none">
	</div>
	<form name="cmdForm" id="cmdForm">
		<input type="hidden" name="contentSubsetId" value="">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</body>
</html>