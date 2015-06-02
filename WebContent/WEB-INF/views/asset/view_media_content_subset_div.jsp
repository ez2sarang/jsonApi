<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<script type="text/javascript">
	$('ul.nav-tabs-sm').tabdrop();
	
	function editContentSubset(contentSubsetId) {
		var tUrl = "${ctxRoot}/asset/editMediaContentSubsetForm.do?contentSubsetId=" + contentSubsetId;
		openModalWindow('01', tUrl);
	}
</script>

<div class="col-sm-12">
	<div class="note note-info">${contentSubset.title}</div>

	<table id="user" class="table table-bordered table-striped" style="clear: both">
		<tbody>
			<tr>
				<td width="30%"><spring:message code="contentSubset.id"/></td>
				<td width="70%">${contentSubset.contentSubsetId}</td>
			</tr>
			<tr>
				<td><spring:message code="contentSubset.contentSubsetType"/></td>
				<td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_CONTENT_SUBSET_TYPE')}" code="${contentSubset.contentSubsetType}" /></td>
			</tr>
			<tr>
				<td><spring:message code="contentSubset.fileName.orgin"/></td>
				<td>${contentSubset.sourceFileName}</td>
			</tr>
			<tr>
				<td><spring:message code="contentSubset.fileName.orgin"/>(3D)</td>
				<td>${contentSubset.sourceFileName2}</td>
			</tr>
			<tr>
				<td><spring:message code="contentSubset.audioType"/></td>
				<td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_AUDIO_TYPE')}" code="${contentSubset.audioType}" /></td>
			</tr>
			<tr>
				<td><spring:message code="contentSubset.language"/></td>
				<td>
					<ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_LANGUAGE_CODE')}" code="${contentSubset.language}" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="contentSubset.subtitleLanguage"/></td>
				<td>
					<ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_LANGUAGE_CODE')}" code="${contentSubset.subtitleLanguage}" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="contentSubset.availablePeriod"/></td>
				<td>
					<fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>" value="${contentSubset.startDateTime}" />
					~
					<fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>" value="${contentSubset.endDateTime}" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="contentSubset.revisionInfo"/></td>
				<td>${contentSubset.revision}(<fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>" value="${contentSubset.revisionDateTime}" />)</td>
			</tr>
			<tr>
				<td><spring:message code="contentSubset.revisionCreatorId"/></td>
				<td>${contentSubset.revisionCreatorId}</td>
			</tr>
			
			<tr>
				<td><spring:message code="contentSubset.cpId"/></td>
				<td>${contentSubset.cpId}</td>
			</tr>
			
			<tr>
				<td><spring:message code="contentSubset.contentAsset"/></td>
				<td>
					<ul id="tabs-default" class="nav nav-tabs nav-tabs-sm">
						<c:set var="activeStr" value="active"/>
						<c:forEach items="${contentSubset.contentAssetList}" var="contentAsset" varStatus="status">
							<c:if test="${!status.first}">
								<c:set var="activeStr" value=""/>
							</c:if>
							<li class="${activeStr}">
								<a href="#tabs-default-${contentAsset.fileType}" data-toggle="tab">${const:getFileTypeName(contentSubset.contentSubsetType, contentAsset.fileType)}</a>
							</li>
						</c:forEach>
					</ul>

					<div class="tab-content tab-content-bordered">
						<c:set var="activeInStr" value="active in"/>
						<c:forEach items="${contentSubset.contentAssetList}" var="contentAsset" varStatus="status">
							<c:if test="${!status.first}">
								<c:set var="activeInStr" value=""/>
							</c:if>
							<div class="tab-pane fade ${activeInStr}" id="tabs-default-${contentAsset.fileType}">
								<table class="table">
									<tbody>
										<tr>
											<td width="24%"><spring:message code="contentSubset.contentAssetId"/></td>
											<td width="1%">:</td>
											<td width="75%">${contentAsset.contentAssetId}</td>
										</tr>
										<tr>
											<td><spring:message code="contentSubset.fileName"/></td>
											<td>:</td>
											<td>${contentAsset.fileNameNoWithPath}</td>
										</tr>
										<tr>
											<td><spring:message code="contentSubset.fileStage"/></td>
											<td>:</td>
											<td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_STAGE_CODE')}" code="${contentAsset.stageCode}" /></td>
										</tr>
										<tr>
											<td><spring:message code="contentSubset.useDrm"/></td>
											<td>:</td>
											<td>
												<c:if test="${contentAsset.useDrm}">
													<spring:message code="contentSubset.useDrm.y"/>
												</c:if>
											</td>
										</tr>
										<tr>
											<td><spring:message code="contentSubset.resolution"/></td>
											<td>:</td>
											<td>${contentAsset.resolution}</td>
										</tr>
										<tr>
											<td><spring:message code="contentSubset.bitRate"/></td>
											<td>:</td>
											<td>${contentAsset.bitRate}</td>
										</tr>
										<tr>
											<td><spring:message code="contentSubset.encryptionKeyBlock"/></td>
											<td>:</td>
											<td>${contentAsset.encryptionKeyBlock}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</c:forEach>
					</div>
				</td>
			</tr>
			
		</tbody>
	</table>
	<div>
		<c:choose>
            <c:when test="${param.windowOption=='popup'}">
            	<div align="right">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
				</div>
            </c:when>
            <c:otherwise>
	            <div style="float:left;">
					<button class="btn btn-primary" onclick="goList();"><spring:message code="button.goBack"/></button>
				</div>
				<div align="right">
					<c:if test="${param.location != 'nvod'}"><button class="btn btn-primary" onclick="editContentSubset(${contentSubset.contentSubsetId})"><spring:message code="button.edit"/></button></c:if>
					<!-- 
					<button class="btn btn-default" disabled="disabled"><spring:message code="button.delete"/></button>
					-->
				</div>
            </c:otherwise>
        </c:choose>
	</div>
	<br>
	<br>
</div>