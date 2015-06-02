<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<script type="text/javascript">
//window.history.forward(1);

/*
$(document).bind("keydown",function(e){
    if( (event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) || (e.keyCode==8) ){
         event.keyCode = 0;
         event.cancelBubble = true;
         event.returnValue =false;
    }
});
*/
</script>

<div class="col-sm-12">
	<div class="note note-info">${banner.bannerName}</div>

	<table id="user" class="table table-bordered table-striped" style="clear: both">
		<tbody>
			<tr>
				<td width="30%"><spring:message code="banner.id"/></td>
				<td width="70%">${banner.bannerId}</td>
			</tr>
			<tr>
				<td><spring:message code="banner.bannerType"/></td>
				<td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_BANNER_TYPE')}" code="${banner.bannerType}" /></td>
			</tr>
			<tr>
				<td><spring:message code="banner.linkType"/></td>
				<td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_BANNER_LINK_TYPE')}" code="${banner.linkType}" /></td>
			</tr>
			<tr>
				<td><spring:message code="banner.linkInfo"/></td>
				<td>${banner.linkInfo}</td>
			</tr>
			<tr>
				<td><spring:message code="banner.licencePeriod"/></td>
				<td>
					<fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>" value="${banner.licenceStartTime}" />
					~
					<fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>" value="${banner.licenceEndTime}" />
				</td>
			</tr>
			
			<c:forEach items="${localeList}" var="locale">
				<tr>
					<td><spring:message code="banner.file"/>(${locale.language})</td>
					<td>
						<c:set var="bannerImage" value="${bannerImageMap[locale.localeCode]}"/>
						<c:choose>
						<c:when test="${bannerImage eq null}">
							<button class="btn btn-primary btn-xs" onclick="addBannerImage('${banner.bannerId}', '${locale.localeCode}');"><spring:message code="button.file.add"/></button>
							<button class="btn btn-defaul btn-xs" disabled="disabled"><spring:message code="button.file.edit"/></button>
							<button class="btn btn-defaul btn-xs" disabled="disabled"><spring:message code="button.file.delete"/></button>
						</c:when>
						<c:otherwise>
							<img src="${ctxRoot}/marketing/getBannerImage/${bannerImage.idx}.do">
							<br><br>
							<button class="btn btn-defaul btn-xs" disabled="disabled"><spring:message code="button.file.add"/></button>
							<button class="btn btn-primary btn-xs" onclick="editBannerImage('${bannerImage.idx}');"><spring:message code="button.file.edit"/></button>
							<c:choose>
							<c:when test="${locale.required}">
								<button class="btn btn-defaul btn-xs" disabled="disabled"><spring:message code="button.file.delete"/></button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-primary btn-xs" onclick="deleteBannerImage('${banner.bannerId}', '${bannerImage.idx}');"><spring:message code="button.file.delete"/></button>
							</c:otherwise>
							</c:choose>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td><spring:message code="description"/></td>
				<td><textarea class="form-control" rows="3" disabled="">${banner.description}</textarea></td>
			</tr>
		</tbody>
	</table>
	<div>
		<div style="float:left;">
			<button class="btn btn-primary" onclick="goList();"><spring:message code="button.goBack"/></button>
		</div>
		<div align="right">
			<button class="btn btn-primary" onclick="editBanner('${banner.bannerId}');"><spring:message code="button.edit"/></button>
			<button class="btn btn-primary" onclick="deleteBanner('${banner.bannerId}');"><spring:message code="button.delete"/></button>
		</div>
	</div>
	<br>
	<br>
</div>