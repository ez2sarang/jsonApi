<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
				<div class="panel colourable">
					<div class="panel-heading">
						<span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="code.title.codeList"/></span>
					</div>
					<div class="panel-body" style="height:480px; overflow:auto;">
						<div align="right" style="margin-bottom: 5px">
							<button class="btn btn-primary" onclick="applyCache('${commonCodeGroup.code}');"><spring:message code="code.button.applyCache"/></button>
							<button class="btn btn-primary" onclick="editCommonCode('${commonCodeGroup.groupCode}', '${commonCodeGroup.code}');"><spring:message code="button.edit"/></button>
							<button class="btn btn-primary" onclick="deleteCommonCodeGroup('${commonCodeGroup.code}');"><spring:message code="button.delete"/></button>
						</div>
						<table class="table table-condensed">
							<tbody>
								<tr>
									<td width="20%"><spring:message code="code.groupCodeName"/></td>
									<td width="2%">:</td>
									<td><c:out value="${commonCodeGroup.codeName}"/></td>
								</tr>
								<tr>
									<td><spring:message code="code.groupCode"/></td>
									<td>:</td>
									<td><c:out value="${commonCodeGroup.code}"/></td>
								</tr>
								<tr>
									<td><spring:message code="description"/></td>
									<td>:</td>
									<td>${commonCodeGroup.description}</td>
								</tr>
								<tr>
									<td><spring:message code="code.classification"/></td>
									<td>:</td>
									<td>${commonCodeGroup.classification}</td>
								</tr>
								<tr>
									<td><spring:message code="code.ext"/>1</td>
									<td>:</td>
									<td>${commonCodeGroup.extendValue1}</td>
								</tr>
								<tr>
									<td><spring:message code="code.ext"/>2</td>
									<td>:</td>
									<td>${commonCodeGroup.extendValue2}</td>
								</tr>
							</tbody>
						</table>
						<div class="table-primary">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>#</th>
										<th><spring:message code="code.codeName"/></th>
										<th><spring:message code="code.code"/></th>
										<th><spring:message code="useYn.y"/></th>
										<th><spring:message code="button.delete"/></th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${commonCodeList}" var="commonCode">
									<tr>
										<td><c:out value="${commonCode.displayOrder}" /></td>
										<td><a href="javascript:editCommonCode('${commonCode.groupCode}', '${commonCode.code}');"><c:out value="${commonCode.codeName}" /></a></td>
										<td><c:out value="${commonCode.code}" /></td>
										<td><c:out value="${commonCode.useYn}" /></td>
										<td>
											<button class="btn btn-primary btn-xs" onclick="deleteCommonCode('${commonCode.groupCode}', '${commonCode.code}');"><spring:message code="button.delete"/></button>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="panel-footer" align="right">
						<c:if test="${commonCodeList.size() > 1 }">
							<button class="btn btn-primary" onclick="reorderCommonCode('${commonCodeGroup.code}');"><spring:message code="button.ordering"/></button>
						</c:if>
						<button class="btn btn-primary" onclick="addCommonCode('${commonCodeGroup.code}');"><spring:message code="button.add"/></button>
					</div>
				</div>