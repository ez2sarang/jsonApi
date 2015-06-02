<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.offer" /></title>
<link href="${ctxRoot}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<script src="${ctxRoot}/js/moment.js"></script>
<script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
<script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>
<script language="JavaScript">
	$(document).ready(function() {
		$.validator.addMethod("dateFormat", function(value, element) {
			return moment(value, moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>'), true).isValid();
		}, "Please enter a date in the format <%=DateUtil.defaultDateTimeFormat %>.");
		/*
		$.validator.addMethod("royaltyPercent", function(value, element) {
			if ($("#royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_PERCENT')}") {
				if ($("#royaltyPercent").val() == '') {
					return false;
				}
			}
			return true;
		}, "This field is required..");
		$.validator.addMethod("royaltyMinimum", function(value, element) {
			if ($("#royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_PERCENT')}") {
				if ($("#royaltyMinimum").val() == '') {
					return false;
				}
			}
			return true;
		}, "This field is required..");
		$.validator.addMethod("royaltyFlatRate", function(value, element) {
			if ($("#royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_FLATRATE')}") {
				if ($("#royaltyFlatRate").val() == '') {
					return false;
				}
			}
			return true;
		}, "This field is required..");
		*/
		
		$('#offer').validate( {
			rules: {
				title: {required: true},
				startDateTime: {required: true, dateFormat: true},
				endDateTime: {required: true, dateFormat: true},
				licenseStartDateTime: {required: true, dateFormat: true},
				licenseEndDateTime: {required: true, dateFormat: true},
				termsTarget: {required: true},
				contractId: {required: true},
				rentalPeriodYear: {number: true, digits: true},
				rentalPeriodMonth: {number: true, digits: true},
				rentalPeriodDay: {number: true, digits: true},
				rentalPeriodHour: {number: true, digits: true},
				subscriberViewLimit: {number: true, digits: true},
				/*
				price: {required: true, number: true, digits: true},
				pointPrice: {number: true, digits: true},
				royaltyType: {required: true},
				royaltyPercent: {royaltyPercent: true, number: true, range: [0, 100]},
				royaltyMinimum: {royaltyMinimum: true, number: true, digits: true},
				royaltyFlatRate: {royaltyFlatRate: true, number: true, digits: true},
				*/
				promotionalContentGroupRef: {required: true},
				contentGroupIds: {required: true}
			},
            success: function(label) {
            	label.addClass("valid");
            },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("offer", "wait");
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/asset/addOffer.do",
						data: $('form#offer').serialize(),
						success: function(msg){
							if (msg == 'Success') {
								openInform('Success', msg, function() {
									location.reload();
								});
							} else {
								openAlert('Fail', msg, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("offer", "default");
								});
							}
						},
						error: function(){
							openAlert('Fail', 'Failure');
							$("#saveButton").prop("disabled", false);
							setCursor("offer", "default");
						}
					});
				}catch(e) {
					alert (e);
				}
            }
		});
		
		$("#offer #saveButton").click(function() {
			// PnYnMnDTnHnMn.nnnS
	  		var periodStr = "";
	  		if ($("#rentalPeriodYear").val() != "") {
	  			periodStr += $("#rentalPeriodYear").val() + "Y";
	  		}
	  		if ($("#rentalPeriodMonth").val() != "") {
	  			periodStr += $("#rentalPeriodMonth").val() + "M";
	  		}
	  		if ($("#rentalPeriodDay").val() != "") {
	  			periodStr += $("#rentalPeriodDay").val() + "D";
	  		}
	  		if ($("#rentalPeriodHour").val() != "") {
	  			periodStr += "T" + $("#rentalPeriodHour").val() + "H";
	  		}
	  		
	  		if (periodStr != "") {
	  			periodStr = "P" + periodStr;
	  		}
	  		
	  		$("#rentalPeriod").val(periodStr);
		});
		
		/*
		$('#royaltyType').change(function(){
	        if ($("#royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_PERCENT')}") {
	        	$("#royaltyPercent").prop("disabled", false);
	        	$("#royaltyMinimum").prop("disabled", false);
	        	$("#royaltyFlatRate").prop("disabled", true);
	        } else if ($("#royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_FLATRATE')}") {
	        	$("#royaltyPercent").prop("disabled", true);
	        	$("#royaltyMinimum").prop("disabled", true);
	        	$("#royaltyFlatRate").prop("disabled", false);
	        } else {
	        	$("#royaltyPercent").prop("disabled", false);
	        	$("#royaltyMinimum").prop("disabled", false);
	        	$("#royaltyFlatRate").prop("disabled", false);
	        }
	    });
		*/
		
		$("#offer #pcgSelectBtn").click(function() {
			var tUrl = "${ctxRoot}/asset/listContentGroupForm.do?popupOption=selectSingle&inputElementId=promotionalContentGroupRef";
			openModalWindow('02', tUrl);
		});
		
		$("#offer #cgSelectBtn").click(function() {
			//var tUrl = "${ctxRoot}/asset/listContentGroupForm.do?popupOption=selectMulti";
			var tUrl = "${ctxRoot}/asset/listContentGroupForm.do?popupOption=selectSingle&inputElementId=contentGroupIds";
			openModalWindow('02', tUrl);
		});
		
		$("#offer #categorySelectBtn").click(function() {
			var tUrl = "${ctxRoot}/category/categoryListPopup.do?popupType=categoryMultiSelect";
			openModalWindow('02', tUrl);
		});
		
		$("#offer #unlimitButton").click(function() {
			if ($("#endDateTime").val() == "") {
				$('#datetimepicker9').data("DateTimePicker").setDate(new Date("December 31, 9999"));
			} else {
				$("#endDateTime").val("");
			}
		});
		
		$("#offer #unlimitButton2").click(function() {
			if ($("#licenseEndDateTime").val() == "") {
				$('#datetimepicker2').data("DateTimePicker").setDate(new Date("December 31, 9999"));
			} else {
				$("#licenseEndDateTime").val("");
			}
		});
		
		$("#offer #contractSelectBtn").click(function() {
			var tUrl = "${ctxRoot}/asset/getContractList.do";
			openModalWindow('02', tUrl);
		});
		
		$("#contractId").select2();
		$("#promotionalContentGroupRef").select2();
		$("#contentGroupIds").select2();
		$("#categoryInfos").select2();
	});
	
	function setContractId(contractId, contractName) {
		$('#contractId')
				.find('option')
				.remove()
				.end()
				.append($('<option></option>')
						.attr('value', contractId)
						.attr('selected', 'selected')
						.text(contractName)
				).trigger('change');
		
		closeModalWindow('02');
	}
	
	function setSingleContentGroup(inputElementId, cgId, cgName) {
		var inputItemId = "promotionalContentGroupRef";
		//$('#promotionalContentGroupRef')
		$('#' + inputElementId)
				.find('option')
				.remove()
				.end()
				.append($('<option></option>')
						.attr('value', cgId)
						.attr('selected', 'selected')
						.text(unescape(cgName))
				).trigger('change');
		
		if (inputElementId == 'contentGroupIds') {
			if ($('#promotionalContentGroupRef').val() == null
					|| $('#promotionalContentGroupRef').val() == '') {
				setSingleContentGroup('promotionalContentGroupRef', cgId, cgName);
			}
		}
		
		closeModalWindow('02');
	}
	
	function setContentGroup(cgId, cgName) {
		var selectAlready = false;
		$('#contentGroupIds :selected').each(function(i, selected){
			if ($(selected).val() == cgId) {
				selectAlready = true;
				return false; // break
			}
		});
		
		if (!selectAlready) {
			$('#contentGroupIds')
					.append($('<option></option>')
							.attr('value', cgId)
							.attr('selected', 'selected')
							.text(cgName)
					).trigger('change');
		}
		
		closeModalWindow('02');
	}
	
	function setCategoryInfo(categoryId, categoryUri, categoryName, isNew, isLastChance, priority) {
		//$('#categoryInfos :selected').each(function(i, selected){
		$('#categoryInfos > option').each(function(){
			// categoryId 중복 체크
			if ($(this).val().indexOf(categoryId + "_") == 0) {
				$(this).remove();
			}
		});
		
		$('#categoryInfos')
				.append($('<option></option>')
						.attr('value', categoryId + '_' + isNew + '_' + isLastChance + '_' + priority)
						.attr('selected', 'selected')
						.text(categoryName)
				).trigger('change');
		
		closeModalWindow('02');
	}
</script>
</head>
<body>

	<form:form modelAttribute="offer" method="POST"
		cssClass="panel form-horizontal">
		<div class="panel-heading">
			<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="offer.title.add"/></span>
		</div>
		<div class="panel-body">
			
			<div class="form-group">
				<label for="title" class="col-sm-3 control-label"><spring:message code="offer.title" /></label>
				<div class="col-sm-9">
					<form:input path="title" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="startDateTime" class="col-sm-3 control-label"><spring:message code="offer.availablePeriod" /></label>
				<div class="col-sm-9">
					<div align="right">
						<i class="fa fa-retweet" id="unlimitButton" style="cursor: pointer;"></i>&nbsp;&nbsp;&nbsp;<spring:message code="unlimited" />
					</div>
					<div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker8'>
		                	<spring:message code="offer.availablePeriod.start" var="startPeriodLabel" />
		                    <form:input path="startDateTime" cssClass="form-control" placeholder="${startPeriodLabel}"/>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
			        </div>
			        <div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker9'>
		                	<spring:message code="offer.availablePeriod.end" var="endPeriodLabel" />
		                    <form:input path="endDateTime" cssClass="form-control" placeholder="${endPeriodLabel}"/>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
			        </div>
				</div>
			</div>
		    <script type="text/javascript">
		        $(function () {
		            $('#datetimepicker8').datetimepicker({
		            	useSeconds: true,
		            	defaultDate: new Date(),
						format: moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>')
		            });
		            $('#datetimepicker9').datetimepicker({
		            	useSeconds: true,
						format: moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>')
		            });
		            $("#datetimepicker8").on("dp.change",function (e) {
		               $('#datetimepicker9').data("DateTimePicker").setMinDate(e.date);
		            });
		            $("#datetimepicker9").on("dp.change",function (e) {
		               $('#datetimepicker8').data("DateTimePicker").setMaxDate(e.date);
		            });
		        });
		    </script>
		    <div class="form-group">
				<label for="licenseStartDateTime" class="col-sm-3 control-label"><spring:message code="offer.licensePeriod" /></label>
				<div class="col-sm-9">
					<div align="right">
						<i class="fa fa-retweet" id="unlimitButton2" style="cursor: pointer;"></i>&nbsp;&nbsp;&nbsp;<spring:message code="unlimited" />
					</div>
					<div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker1'>
		                	<spring:message code="offer.availablePeriod.start" var="startPeriodLabel" />
		                    <form:input path="licenseStartDateTime" cssClass="form-control" placeholder="${startPeriodLabel}"/>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
			        </div>
			        <div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker2'>
		                	<spring:message code="offer.availablePeriod.end" var="endPeriodLabel" />
		                    <form:input path="licenseEndDateTime" cssClass="form-control" placeholder="${endPeriodLabel}"/>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
			        </div>
				</div>
			</div>
		    <script type="text/javascript">
		        $(function () {
		            $('#datetimepicker1').datetimepicker({
		            	useSeconds: true,
		            	defaultDate: new Date(),
						format: moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>')
		            });
		            $('#datetimepicker2').datetimepicker({
		            	useSeconds: true,
						format: moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>')
		            });
		            $("#datetimepicker1").on("dp.change",function (e) {
		               $('#datetimepicker2').data("DateTimePicker").setMinDate(e.date);
		            });
		            $("#datetimepicker2").on("dp.change",function (e) {
		               $('#datetimepicker1').data("DateTimePicker").setMaxDate(e.date);
		            });
		        });
		    </script>
		    
			<div class="form-group">
				<label for="termsTarget" class="col-sm-3 control-label"><spring:message code="offer.termsTarget" /></label>
				<div class="col-sm-9">
					<form:select path="termsTarget" cssClass="form-control">
						<option value=""><spring:message code="combo.select" /></option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_TERMS_TARGET')}" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="contractId" class="col-sm-3 control-label"><spring:message code="offer.contractId" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="contractId" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="contractSelectBtn" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="termsDescription" class="col-sm-3 control-label"><spring:message code="offer.termsDescription" /></label>
				<div class="col-sm-9">
					<form:textarea path="termsDescription" rows="3" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="rentalPeriod" class="col-sm-3 control-label"><spring:message code="offer.rentalPeriod" /></label>
				<div class="col-sm-9">
					<div class='col-md-3' style="padding-left:0;padding-right:0">
		                <div class='input-group'>
		                	<form:hidden path="rentalPeriod"/>
		                	<input type="text" id="rentalPeriodYear" name="rentalPeriodYear" class="form-control" placeholder="<spring:message code='offer.rentalPeriod.year'/>"/>
		                </div>
			        </div>
					<div class='col-md-3' style="padding-left:0;padding-right:0">
		                <div class='input-group'>
		                	<input type="text" id="rentalPeriodMonth" name="rentalPeriodMonth" class="form-control" placeholder="<spring:message code='offer.rentalPeriod.month'/>" />
		                </div>
			        </div>
					<div class='col-md-3' style="padding-left:0;padding-right:0">
		                <div class='input-group'>
		                	<input type="text" id="rentalPeriodDay" name="rentalPeriodDay" class="form-control" placeholder="<spring:message code='offer.rentalPeriod.day'/>" />
		                </div>
			        </div>
					<div class='col-md-3' style="padding-left:0;padding-right:0">
		                <div class='input-group'>
		                	<input type="text" id="rentalPeriodHour" name="rentalPeriodHour" class="form-control" placeholder="<spring:message code='offer.rentalPeriod.hour'/>" />
		                </div>
			        </div>
				</div>
			</div>
			<div class="form-group">
				<label for="subscriberViewLimit" class="col-sm-3 control-label"><spring:message code="offer.subscriberViewLimit" /></label>
				<div class="col-sm-9">
					<form:input path="subscriberViewLimit" cssClass="form-control" />
					
					<form:hidden path="price"/>
					<form:hidden path="pointPrice"/>
					<form:hidden path="royaltyType"/>
					<form:hidden path="royaltyPercent"/>
					<form:hidden path="royaltyMinimum"/>
				</div>
			</div>
			<!-- 
			<div class="form-group">
				<label for="price" class="col-sm-3 control-label"><spring:message code="offer.price" /></label>
				<div class="col-sm-9">
					<form:input path="price" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="pointPrice" class="col-sm-3 control-label"><spring:message code="offer.pointPrice" /></label>
				<div class="col-sm-9">
					<form:input path="pointPrice" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="royaltyType" class="col-sm-3 control-label"><spring:message code="offer.royaltyType" /></label>
				<div class="col-sm-9">
					<form:select path="royaltyType" cssClass="form-control">
						<option value=""><spring:message code="combo.select" /></option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_ROYALTY_TYPE')}" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="royaltyPercent" class="col-sm-3 control-label"><spring:message code="offer.royaltyPercent" /></label>
				<div class="col-sm-9">
					<form:input path="royaltyPercent" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="royaltyMinimum" class="col-sm-3 control-label"><spring:message code="offer.royaltyMinimum" /></label>
				<div class="col-sm-9">
					<form:input path="royaltyMinimum" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="royaltyFlatRate" class="col-sm-3 control-label"><spring:message code="offer.royaltyFlatRate" /></label>
				<div class="col-sm-9">
					<form:input path="royaltyFlatRate" cssClass="form-control" />
				</div>
			</div>
			-->
			
			<div class="form-group">
				<label for="contentGroupIds" class="col-sm-3 control-label"><spring:message code="offer.contentGroup" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<div class="select2-success">
							<select id="contentGroupIds" name="contentGroupIds" class="orm-control" multiple="multiple">
							</select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="cgSelectBtn" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="promotionalContentGroupRef" class="col-sm-3 control-label"><spring:message code="offer.promotionalContentGroupRef" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="promotionalContentGroupRef" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="pcgSelectBtn" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="categoryInfos" class="col-sm-3 control-label"><spring:message code="offer.display" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<div class="select2-success">
							<select id="categoryInfos" name="categoryInfos" class="orm-control" multiple="multiple">
							</select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="categorySelectBtn" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			
			<div class="form-group" style="margin-bottom: 0;">
				<div class="col-sm-offset-2 col-sm-9" align="right">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close'/></button>
					<button type="submit" class="btn btn-primary" id="saveButton"><spring:message code='button.save'/></button>
				</div>
			</div> <!-- / .form-group -->
		</div>
	</form:form>

</body>
</html>