<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ taglib prefix="lvb" uri="LabelValueBean"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<script type="text/javascript">
	$('ul.nav-tabs-sm').tabdrop();
	
	function showContentGroupDetail(cgId) {
		var tUrl = "${ctxRoot}/asset/viewContentGroup.do?windowOption=popup&contentGroupId=" + cgId;
		openModalWindow('01', tUrl);
	}
	
	function editOffer(offerId) {
		var tUrl = "${ctxRoot}/asset/editOfferForm.do?offerId=" + offerId;
		openModalWindow('01', tUrl);
	}
	
	function deleteOffer(offerId) {
		// <spring:message code="offer.info" var="offerLabel"/>
		openConfirm('<spring:message code="confirm.delete" arguments="${offerLabel}"/>',function(result) {
			if(result) {
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/asset/deleteOffer/" + offerId + ".do",
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

<div class="col-sm-12">
	<div class="note note-info">${offer.title}</div>

	<table id="offerDetailTable" class="table table-bordered table-striped" style="clear: both">
		<tbody>
			<tr>
				<td width="30%"><spring:message code="offer.id"/></td>
				<td width="70%">${offer.offerId}</td>
			</tr>
			<tr>
				<td width="30%"><ccode:name	groupCode="${const:get('COMMONCODE', 'ROOT_GROUP_CODE')}" code="${const:get('COMMONCODE', 'GROUP_CODE_STAGE_CODE')}" /></td>
				<td width="70%">
					<ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_STAGE_CODE')}" code="${offer.stageCode}" />(${offer.stageCode})
				</td>
			</tr>
			
			<c:if test="${offer.stageCode == const:get('COMMONCODE', 'STAGE_CODE_REJECTED')}">
				<tr>
					<td width="30%"><spring:message code="offer.rejectMsg"/></td>
					<td width="70%">${offer.reviewerMsg}</td>
				</tr>
			</c:if>
			
			<tr>
				<td width="30%"><ccode:name	groupCode="${const:get('COMMONCODE', 'ROOT_GROUP_CODE')}" code="${const:get('COMMONCODE', 'GROUP_CODE_SERVICE_STATUS')}" /></td>
				<td width="70%">
					<ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_SERVICE_STATUS')}" code="${offer.serviceStatus}" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="offer.availablePeriod"/></td>
				<td>
					<fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>" value="${offer.startDateTime}" />
					~
					<fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>" value="${offer.endDateTime}" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="offer.licensePeriod"/></td>
				<td>
					<fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>" value="${offer.licenseStartDateTime}" />
					~
					<fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>" value="${offer.licenseEndDateTime}" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="offer.termsTarget"/></td>
				<td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_TERMS_TARGET')}" code="${offer.termsTarget}" /></td>
			</tr>
			<tr>
				<td><spring:message code="offer.contractId"/></td>
				<td>${contract.contractName}(${contract.contractInternalId})</td>
			</tr>
			<tr>
				<td><spring:message code="offer.termsDescription"/></td>
				<td><textarea class="form-control" rows="3" disabled="">${offer.termsDescription}</textarea></td>
			</tr>
			<tr>
				<td><spring:message code="offer.pricingType"/></td>
				<td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_PRICING_TYPE')}" code="${offer.pricingType}" /></td>
			</tr>
			<tr>
				<td><spring:message code="offer.rentalPeriod"/></td>
				<td>${offer.rentalPeriod}</td>
			</tr>
			<tr>
				<td><spring:message code="offer.subscriberViewLimit"/></td>
				<td>${offer.subscriberViewLimit}</td>
			</tr>
			<tr>
				<td><spring:message code="offer.price"/></td>
				<td>${offer.price}</td>
			</tr>
			<tr>
				<td><spring:message code="offer.pointPrice"/></td>
				<td>${offer.pointPrice}</td>
			</tr>
			<tr>
				<td><spring:message code="offer.royaltyType"/></td>
				<td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_ROYALTY_TYPE')}" code="${offer.royaltyType}" /></td>
			</tr>
			<tr>
				<td><spring:message code="offer.royaltyPercent"/></td>
				<td>${offer.royaltyPercent}</td>
			</tr>
			<tr>
				<td><spring:message code="offer.royaltyMinimum"/></td>
				<td>${offer.royaltyMinimum}</td>
			</tr>
			<tr>
				<td><spring:message code="offer.royaltyFlatRate"/></td>
				<td>${offer.royaltyFlatRate}</td>
			</tr>
			<tr>
				<td><spring:message code="offer.providerContentTier"/></td>
				<td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_CONTENT_TIER')}" code="${offer.providerContentTier}" /></td>
			</tr>
			<tr>
				<td><spring:message code="offer.revisionInfo"/></td>
				<td>${offer.revision}(<fmt:formatDate pattern="<%=DateUtil.defaultDateTimeFormat %>" value="${offer.revisionDateTime}" />)</td>
			</tr>
			<tr>
				<td><spring:message code="offer.revisionCreatorId"/></td>
				<td>${offer.revisionCreatorId}</td>
			</tr>
			
			<tr>
				<td><spring:message code="offer.cpId"/></td>
				<td>${offer.cpId}</td>
			</tr>
			<tr>
				<td><spring:message code="offer.promotionalContentGroupRef"/></td>
				<td>
					<a href="javascript:showContentGroupDetail(${offer.promotionalContentGroupRef})">${promotionalContentGroup.titleBrief}(${offer.promotionalContentGroupRef})</a>
				</td>
			</tr>
			<tr>
				<td><spring:message code="offer.contentGroup"/></td>
				<td>
					<c:forEach items="${offer.contentGroupList}" var="contentGroup">
						<a href="javascript:showContentGroupDetail(${contentGroup.contentGroupId})">${contentGroup.titleBrief}(${contentGroup.contentGroupId})</a>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td><spring:message code="offer.display"/></td>
				<td>
					<ul id="tabs-default" class="nav nav-tabs nav-tabs-sm">
						<c:set var="activeStr" value="active"/>
						<c:forEach items="${offer.offerCategoryList}" var="offerCategory" varStatus="status">
							<c:if test="${!status.first}">
								<c:set var="activeStr" value=""/>
							</c:if>
							<li class="${activeStr}">
								<a href="#tabs-default-${offerCategory.idx}" data-toggle="tab">
									<lvb:label	serviceBeanName="categoryService" key1="${offerCategory.categoryId}" />
								</a>
							</li>
						</c:forEach>
					</ul>

					<div class="tab-content tab-content-bordered">
						<c:set var="activeInStr" value="active in"/>
						<c:forEach items="${offer.offerCategoryList}" var="offerCategory" varStatus="status">
							<c:if test="${!status.first}">
								<c:set var="activeInStr" value=""/>
							</c:if>
							<div class="tab-pane fade ${activeInStr}" id="tabs-default-${offerCategory.idx}">
								<table class="table">
									<tbody>
										<tr>
											<td width="24%"><spring:message code="offer.display.categoryId"/></td>
											<td width="1%">:</td>
											<td width="75%">${offerCategory.categoryId}</td>
										</tr>
										<tr>
											<td><spring:message code="offer.display.new"/></td>
											<td>:</td>
											<td>${offerCategory.displayDaysAsNew}</td>
										</tr>
										<tr>
											<td><spring:message code="offer.display.lastChance"/></td>
											<td>:</td>
											<td>${offerCategory.displayDaysAsLastChance}</td>
										</tr>
										<tr>
											<td><spring:message code="offer.display.priority"/></td>
											<td>:</td>
											<td>${offerCategory.priority}</td>
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
		<div style="float:left;">
			<button class="btn btn-primary" onclick="goList();"><spring:message code="button.goBack"/></button>
		</div>
		<div align="right">
			<c:if test="${editable}">
				<button class="btn btn-primary" onclick="editOffer(${offer.offerId})"><spring:message code="button.edit"/></button>
			</c:if>
			<c:if test="${!editable}">
				<button class="btn btn-default" disabled="disabled"><spring:message code="button.edit"/></button>
			</c:if>
			
			<c:if test="${deletable}">
				<button class="btn btn-primary" onclick="deleteOffer(${offer.offerId})"><spring:message code="button.delete"/></button>
			</c:if>
			<c:if test="${!deletable}">
				<button class="btn btn-default" disabled="disabled"><spring:message code="button.delete"/></button>
			</c:if>
		</div>
	</div>
	<br>
	<br>
</div>