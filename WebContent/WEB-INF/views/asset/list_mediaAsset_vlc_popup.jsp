<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 2014. 9. 12.
  Time: 오후 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ taglib prefix="const" uri="Constants"%>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ taglib prefix="zm" uri="ziminTag"%>
<html>
	<head>
		<title><spring:message code="contentSubset.contentAsset" var="title"/>${title}</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		
		<!-- Open Sans font from Google CDN -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300&subset=latin" rel="stylesheet" type="text/css">
		
		<!-- Pixel Admin's stylesheets -->
		<link href="${ctxRoot}/assets/stylesheets/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="${ctxRoot}/assets/stylesheets/pixel-admin.min.css" rel="stylesheet" type="text/css">
		<link href="${ctxRoot}/assets/stylesheets/widgets.min.css" rel="stylesheet" type="text/css">
		<link href="${ctxRoot}/assets/stylesheets/pages.min.css" rel="stylesheet" type="text/css">
		<link href="${ctxRoot}/assets/stylesheets/rtl.min.css" rel="stylesheet" type="text/css">
		<link href="${ctxRoot}/assets/stylesheets/themes.min.css" rel="stylesheet" type="text/css">

		<script src="${ctxRoot}/js/jquery-2.0.3.min.js"></script>
<!-- 		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script> -->
		<script src="${ctxRoot}/js/jquery-ui.min-1.11.1.js"></script>
		
		<!-- Pixel Admin's javascripts -->
		<script src="${ctxRoot}/assets/javascripts/bootstrap.min.js"></script>
		<script src="${ctxRoot}/assets/javascripts/pixel-admin.min.js"></script>
		
		<script src="${ctxRoot}/js/common.js"></script>
		<link href="${ctxRoot}/js/video-js/video-js.min.css" rel="stylesheet">
		<script src="${ctxRoot}/js/video-js/video.js"></script>
<!-- 		<script src="http://www.videojs.com/js/home.js"></script> -->
    
		<script>
		$(document).ready(
		);
		
		function getBrowserType()
		{
			userAgent = navigator.userAgent;
			
			if (userAgent.indexOf("Trident") != -1)
			{
				return "ie";
			}
			else
			{
				return "other";	
			};		
		}
		
		function vlcPlay(filePath)
    	{
			var target = "${ctxRoot}/serviceflow/streamingMedia.do?fileName=" + filePath;
			
	   		var vlc = document.getElementById("vlc");
    		vlc.playlist.clear();
    		vlc.playlist.playItem( vlc.playlist.add(target) );
    	}
		
		function editUseDrm (contentAssetId, useDrm) {
			openConfirm('<spring:message code="contentSubset.confirm.editUseDrm"/>',function(result) {
				if(result) {
					cmdForm.contentAssetId.value = contentAssetId;
					cmdForm.useDrm.value = useDrm;
					
					try{
						$.ajax({
							type: "POST",
							url: "${ctxRoot}/asset/editUseDrmOfContentAsset.do",
							data: $('form#cmdForm').serialize(),
							success: function(data, textStatus, jqXHR){
								if (data == 'Success') {
									openInform('Success', data, function() {
										location.reload();
									});
								} else {
									openAlert('Fail', data, function() {
									});
								}
							},
							error: function(jqXHR, textStatus, errorThrown){
								openAlert('Fail', 'Failure:' + textStatus);
							}
						});
					}catch(e) {
						alert (e);
					}
				}
			});
		}
		</script>
	</head>
	<body>
		<div class="panel-heading">
			<span class="panel-title"><i class="panel-title-icon fa fa-flask"></i>${title}</span>
		</div>
		<div class="panel-body">
			<div class="col-md-7" style="overflow-x: auto;">
				<div class="panel colourable">
					<div class="panel-heading">
						<span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="contentSubset.contentAsset.list"/></span>
					</div>
					<div class="panel-body" style="overflow-x: auto;">
						<div class="table-primary">
							<table id="tree" class="table table-striped table-bordered" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th><spring:message code="contentSubset.contentAssetId"/></th>
										<th><spring:message code="contentSubset.fileName"/></th>
										<th><spring:message code="contentSubset.fileStage"/></th>
										<th><spring:message code="contentSubset.resolution"/></th>
										<th><spring:message code="contentSubset.bitRate"/></th>
										<th><spring:message code="contentSubset.useDrm"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${assetList}" var="item">
										<tr>
											<td>(${item.fileType})${item.contentAssetId}</td>
											<td><a href="#" onclick="vlcPlay('${item.fileName}');">${item.fileNameNoWithPath}</a></td>
											<td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_STAGE_CODE')}" code="${item.stageCode}" /></td>
											<td>${item.resolution}</td>
											<td>${item.bitRate}</td>
											<td>
												<c:choose>
													<c:when test="${item.useDrm}">
				                                		Y &nbsp;
				                                		<c:if test="${(item.fileType == const:getContentAssetConstant('FILE_TYPE_SD')
				                                				|| item.fileType == const:getContentAssetConstant('FILE_TYPE_HD')
				                                				|| item.fileType == const:getContentAssetConstant('FILE_TYPE_3D'))
				                                				&& item.stageCode == const:get('COMMONCODE', 'STAGE_CODE_COMPLETED_ENCODING')}">
				                                			<button class="btn btn-primary btn-sm" onclick="editUseDrm(${item.contentAssetId}, 'false');"><spring:message code="contentSubset.button.editUseDrm.n"/></button>
				                                		</c:if>
				                                	</c:when>
										            <c:otherwise>
										            	N &nbsp;
										            	<c:if test="${(item.fileType == const:getContentAssetConstant('FILE_TYPE_SD')
				                                				|| item.fileType == const:getContentAssetConstant('FILE_TYPE_HD')
				                                				|| item.fileType == const:getContentAssetConstant('FILE_TYPE_3D'))
				                                				&& item.stageCode == const:get('COMMONCODE', 'STAGE_CODE_COMPLETED_ENCODING')}">
				                                			<button class="btn btn-primary btn-sm" onclick="editUseDrm(${item.contentAssetId}, 'true');"><spring:message code="contentSubset.button.editUseDrm.y"/></button>
				                                		</c:if>
										            </c:otherwise>
									            </c:choose>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-5" id="detailViewDiv">
				<div class="panel colourable">
					<div class="panel-heading">
						<span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="contentSubset.contentAsset.view"/></span>
					</div>
					<div class="panel-body" style="overflow:auto;">
						<c:choose>
							<c:when test="${browserType == 'ie' }">
								<object
									classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921"
									codebase="http://download.videolan.org/pub/videolan/vlc/last/win32/axvlc.cab"
									id="vlc"
									name="vlc"
									class="vlcPlayer"
									events="True">
										<param name="ShowDisplay" value="True" />
										<param name="AutoLoop" value="True" />
										<param name="AutoPlay" value="True" />
								</object>
							</c:when>
							<c:when test="${browserType == 'other' }">
								<embed type="application/x-vlc-plugin" name="vlc" id="vlc" autoplay="no" loop="yes" width="400" height="300" />
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer" style="margin-bottom: 0;">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="if(opener){window.close();}else{try{reLoadTree();}catch(e){}}"><spring:message code='button.close'/></button>
			</div>
		</div>
		
		<form name="cmdForm" id="cmdForm">
			<input type="hidden" name="contentAssetId" value="">
			<input type="hidden" name="useDrm" value="">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
		
		<%@ include file="/WEB-INF/views/common/modal_includes.jsp"%>
		
		<script type="text/javascript">
		    init.push(function () {
		        // Javascript code here
		    });
		    window.PixelAdmin.start(init);
		</script>
	</body>
</html>