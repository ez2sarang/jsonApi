<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<c:set var="systemStr" value="/system/"/>

		<div id="main-menu-inner">
			<div class="menu-content top" id="menu-content-demo">
				<!-- Menu custom content demo
					 CSS:        styles/pixel-admin-less/demo.less or styles/pixel-admin-scss/_demo.scss
					 Javascript: html/assets/demo/demo.js
				 -->
				<div>
					<div class="text-bg"><span class="text-slim">Welcome,</span> <span class="text-semibold">ZimiN</span></div>

					<img src="${ctxRoot}/assets/demo/avatars/1.jpg" alt="" class="">
					<div class="text-bg"><span class="text-slim">Say it your way</span></div>
					<a href="#" class="close">&times;</a>
				</div>
			</div>
			
			
			<!-- ----------------------------------------------------------------- -->
			<!-- SYSADMIN, ADMIN                                                   -->
			<!-- ----------------------------------------------------------------- -->
			<sec:authorize access="hasAnyRole('ROLE_SYSADMIN', 'ROLE_ADMIN')">
				<ul class="navigation">
					<li class="mm-dropdown">
						<a href="#"><i class="menu-icon fa fa-dashboard"></i><span class="mm-text"><spring:message code="menu.system" /></span></a>
						<ul>
							<sec:authorize access="hasRole('ROLE_SYSADMIN')"> 
								<li >
									<a tabindex="-1" href="${ctxRoot}/system/getCommonCodeGroupList.do"><span class="mm-text"><spring:message code="menu.system.commoncode" /></span></a>
								</li>
							</sec:authorize>
							<li class="active">
								<a tabindex="-1" href="${ctxRoot}/system/getUserList.do"><span class="mm-text"><spring:message code="menu.system.user" /></span></a>
							</li>
						</ul>
					</li>
					<li class="mm-dropdown">
						<a href="#">
							<i class="menu-icon fa fa-bars"></i><span class="mm-text"><spring:message code="menu.contract.manage" /></span>
						</a>
						<ul>
							<li id="getContentProviderListForm" name="getContentProviderListForm" class="active">
								<a tabindex="-1" href="${ctxRoot}/contract/getContentProviderListForm.do"><span class="mm-text"><spring:message code="menu.content.provider" /></span></a>
							</li>
							<li id="getContractListForm" name="getContractListForm" class="active">
								<a tabindex="-1" href="${ctxRoot}/contract/getContractListForm.do"><span class="mm-text"><spring:message code="menu.contract" /></span></a>
							</li>
						</ul>
					</li>
					<!-- 
					<li class="mm-dropdown">
						<a href="#">
							<i class="menu-icon fa fa-bars"></i><span class="mm-text"><spring:message code="menu.code.manage" /></span>
						</a>
						<ul>
							<li id="getProviderIdListForm" name="getProviderIdListForm" class="active">
								<a tabindex="-1" href="${ctxRoot}/codemanage/getProviderIdListForm.do"><i class="menu-icon fa fa-file-o"></i><span class="mm-text"><spring:message code="menu.code.manage.provider.id" /></span></a>
							</li>
							<li id="getMarketerForm" name="getMarketerForm" class="active">
								<a tabindex="-1" href="${ctxRoot}/codemanage/getMarketerListForm.do"><i class="menu-icon fa fa-file-o"></i><span class="mm-text"><spring:message code="menu.code.manage.marketer" /></span></a>
							</li>
						</ul>
					</li>
					<li class="mm-dropdown">
						<a href="#">
							<i class="menu-icon fa fa-users"></i><span class="mm-text"><spring:message code="menu.subscriber" /></span>
						</a>
						<ul>
							<li id="getSubscriberIdListForm" name="getSubscriberIdListForm" class="active">
								<a tabindex="-1" href="${ctxRoot}/subscriber/getSubscriberIdListForm.do"><i class="menu-icon fa fa-key"></i><span class="mm-text"><spring:message code="menu.subscriber.identification" /></span></a>
							</li>
						</ul>
					</li>
					-->
					<li class="mm-dropdown">
						<a href="#"><i class="menu-icon fa fa-tasks"></i><span class="mm-text"><spring:message code="menu.category" /></span></a>
	                    <ul>
	                        <li>
	                            <a tabindex="-1" href="${ctxRoot}/category/categoryList.do"><span class="mm-text"><spring:message code="menu.category" /></span></a>
	                        </li>
	                		<!--         
	                        <li>
	                            <a tabindex="-1" href="${ctxRoot}/menu/menuList.do"><span class="mm-text"><spring:message code="menu.menu" /></span></a>
	                        </li>
	 						-->                        
	                    </ul>
					</li>
					<li class="mm-dropdown">
						<a href="#"><i class="menu-icon fa fa-desktop"></i><span class="mm-text"><spring:message code="menu.serviceAsset" /></span></a>
						<ul>
							<li>
								<a tabindex="-1" href="${ctxRoot}/asset/getContentSubsetListForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.contentAsset" /></span></a>
							</li>
							<li>
								<a tabindex="-1" href="${ctxRoot}/asset/listContentGroupForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.contentGroup" /></span></a>
							</li>
							<li class="active">
								<a tabindex="-1" href="${ctxRoot}/asset/getOfferListForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.offer" /></span></a>
							</li>
							<li tabindex="-1">
								<a tabindex="-1" href="${ctxRoot}/serviceflow/requestQcListForm.do"><span class="mm-text"><spring:message code="menu.qc.offer" /></span></a>
							</li>
	                        <li tabindex="-1">
								<a tabindex="-1" href="${ctxRoot}/serviceflow/listOfferConfirmForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.offerConfirm" /></span></a>
							</li>
	                        <li tabindex="-1">
								<a tabindex="-1" href="${ctxRoot}/asset/getRegistrationHistoryListForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.registrationHistory" /></span></a>
							</li>
							<li>
								<a tabindex="-1" href="${ctxRoot}/serviceflow/getServiceDeployStatusListForm.do"><span class="mm-text"><spring:message code="menu.service.deploy.status" /></span></a>
							</li>
							<li>
								<a tabindex="-1" href="${ctxRoot}/serviceflow/getServiceDeployHistoryListForm.do"><span class="mm-text"><spring:message code="menu.service.deploy.history" /></span></a>
							</li>
							<!-- 
							<li>
								<a tabindex="-1" href="${ctxRoot}/asset/getBulkJobListForm.do"><span class="mm-text"><spring:message code="menu.bulkloader" /></span></a>
							</li>
							-->
							<li>
								<a tabindex="-1" href="${ctxRoot}/asset/getSeriesListForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.series" /></span></a>
							</li>
						</ul>
					</li>
					<li class="mm-dropdown">
						<a href="#"><i class="menu-icon fa fa-gears"></i><span class="mm-text"><spring:message code="menu.interfaces" /></span></a>
						<ul>
							<li class="active">
								<a tabindex="-1" href="${ctxRoot}/tvod/getTvodEpgLoadHistoryListForm.do"><span class="mm-text"><spring:message code="menu.interfaces.epg.to.tvod" /></span></a>
							</li>
							<li class="active">
								<a tabindex="-1" href="${ctxRoot}/interface/listNvodRequestForm.do"><span class="mm-text"><spring:message code="menu.interfaces.nvod.assets" /></span></a>
							</li>
						</ul>
					</li>
				</ul> <!-- / .navigation -->
			</sec:authorize>
			
			
			<!-- ----------------------------------------------------------------- -->
			<!-- USER                                                              -->
			<!-- ----------------------------------------------------------------- -->
			<sec:authorize access="hasRole('ROLE_USER')">
				<ul class="navigation">
					<li class="mm-dropdown">
						<a href="#"><i class="menu-icon fa fa-desktop"></i><span class="mm-text"><spring:message code="menu.serviceAsset" /></span></a>
						<ul>
							<li>
								<a tabindex="-1" href="${ctxRoot}/asset/getContentSubsetListForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.contentAsset" /></span></a>
							</li>
							<li>
								<a tabindex="-1" href="${ctxRoot}/asset/listContentGroupForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.contentGroup" /></span></a>
							</li>
							<li class="active">
								<a tabindex="-1" href="${ctxRoot}/asset/getOfferListForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.offer" /></span></a>
							</li>
							<li class="active">
								<a tabindex="-1" href="${ctxRoot}/asset/addOfferComponentsForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.easyRegist" /></span></a>
							</li>
							<li>
								<a tabindex="-1" href="${ctxRoot}/serviceflow/getServiceDeployStatusListForm.do"><span class="mm-text"><spring:message code="menu.service.deploy.status" /></span></a>
							</li>
							<li>
								<a tabindex="-1" href="${ctxRoot}/asset/getSeriesListForm.do"><span class="mm-text"><spring:message code="menu.serviceAsset.series" /></span></a>
							</li>
						</ul>
					</li>
				</ul> <!-- / .navigation -->
			</sec:authorize>
			
			
			<!-- ----------------------------------------------------------------- -->
			<!-- QC                                                                -->
			<!-- ----------------------------------------------------------------- -->
			<sec:authorize access="hasRole('ROLE_QC')">
				<ul class="navigation">
					<li class="mm-dropdown">
						<a href="#"><i class="menu-icon fa fa-desktop"></i><span class="mm-text"><spring:message code="menu.serviceAsset" /></span></a>
						<ul>
							<li tabindex="-1">
								<a tabindex="-1" href="${ctxRoot}/serviceflow/requestQcListForm.do"><span class="mm-text"><spring:message code="menu.qc.offer" /></span></a>
							</li>
						</ul>
					</li>
				</ul> <!-- / .navigation -->
			</sec:authorize>
			
			
			
		</div> <!-- / #main-menu-inner -->
