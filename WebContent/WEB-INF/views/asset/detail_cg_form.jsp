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
%><html>
<head>
    <title><spring:message code="serviceAsset.contentGroup.info" var="title"/>${title}</title>
    <script language="JavaScript">
        $(document).ready(function() {
        });
        
        function showContentSubsetDetail(contentSubsetId) {
    		var tUrl = "${ctxRoot}/asset/viewContentSubset.do?windowOption=popup&contentSubsetId=" + contentSubsetId;
    		openModalWindow('02', tUrl);
    	}
    </script>
</head>
<body>
    <div class="col-sm-12">
        <div class="panel-heading">
            <div class="note note-info">${entity.titleBrief}</div>
        </div>
        <div class="panel-body" style="overflow:auto;">
            <table id="user" class="table table-bordered table-striped" style="clear: both">
            <tr>
                <td width="30%"><spring:message code="serviceAsset.contentGroup.cpId"/></td>
                <td width="70%">
                    ${entity.cpId}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.contentType"/></td>
                <td>
                    <ccode:name	groupCode="${const:get('COMMONCODE', 'CG_CONTENT_TYPE')}" code="${entity.contentType}" />
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.validTerm"/></td>
                <td>
                    ${entity.startDateTime} ~ ${entity.endDateTime}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.titleSortName"/></td>
                <td>
                    ${entity.titleSortName}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.titleBrief"/></td>
                <td>
                    ${entity.titleBrief}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.titleMedium"/></td>
                <td>
                    ${entity.titleMedium}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.titleLong"/></td>
                <td>
                    ${entity.titleLong}
                </td>
            </tr>

            <tr>
                <td><spring:message code="serviceAsset.contentGroup.summaryShort"/></td>
                <td>
                    ${entity.summaryShort}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.summaryMedium"/></td>
                <td>
                    ${entity.summaryMedium}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.summaryLong"/></td>
                <td>
                    ${entity.summaryLong}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.actor"/></td>
                <td>
                    ${entity.actor}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.writer"/></td>
                <td>
                    ${entity.writer}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.director"/></td>
                <td>
                    ${entity.director}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.producer"/></td>
                <td>
                    ${entity.producer}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.studio"/></td>
                <td>
                    ${entity.studio}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.seriesId"/></td>
                <td>
                    ${entity.seriesName}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.episodeType"/></td>
                <td>
                    <ccode:name	groupCode="${const:get('COMMONCODE', 'CG_EPISODE_TYPE')}" code="${entity.episodeType}" />
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.episodeNo"/></td>
                <td>
                    ${entity.episodeNo}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.episodeName"/></td>
                <td>
                    ${entity.episodeName}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.rating"/></td>
                <td>
                    ${entity.rating}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.audience"/></td>
                <td>
                    ${entity.audience}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.displayRunTime"/></td>
                <td>
                    ${entity.displayRunTime}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.year"/></td>
                <td>
                    ${entity.year}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.countryOfOrigin"/></td>
                <td>
                    <ccode:name	groupCode="${const:get('COMMONCODE', 'CG_COUNTRY_OF_ORIGIN')}" code="${entity.countryOfOrigin}" />
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.genre"/></td>
                <td>
                    ${entity.genre}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.showType"/></td>
                <td>
                    ${entity.showType}
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.movieSubsetId"/></td>
                <td>
                	<a href="javascript:showContentSubsetDetail(${entity.movieSubsetId});">${entity.movieSubsetName}</a>
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.previewSubsetId"/></td>
                <td>
                    <a href="javascript:showContentSubsetDetail(${entity.previewSubsetId});">${entity.previewSubsetName}</a>
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.posterSubsetId"/></td>
                <td>
                    <a href="javascript:showContentSubsetDetail(${entity.posterSubsetId});">${entity.posterSubsetName}</a>
                </td>
            </tr>
            <tr>
                <td><spring:message code="serviceAsset.contentGroup.thumbnailSubsetId"/></td>
                <td>
                    <a href="javascript:showContentSubsetDetail(${entity.thumbnailSubsetId});">${entity.thumbnailSubsetName}</a>
                </td>
            </tr>
            </table>
            <!-- / .form-group -->
        </div>
        <div class="panel-footer">
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
		                <button class="btn btn-primary" onclick="editContentGroup('${entity.contentGroupId}');"><spring:message code="button.edit"/></button>
		                <!-- 
		                <button class="btn btn-primary" onclick="deleteContentGroup('${entity.contentGroupId}');"><spring:message code="button.delete"/></button>
		                -->
		            </div>
	            </c:otherwise>
	        </c:choose>
        </div>
    </div>
</body>
</html>



