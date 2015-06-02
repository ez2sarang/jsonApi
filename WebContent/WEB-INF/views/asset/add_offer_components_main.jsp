<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.easyRegist"/></title>
<link href="${ctxRoot}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link href="${ctxRoot}/css/fileinput.min.css" rel="stylesheet" type="text/css">
<script src="${ctxRoot}/js/fileinput.min.js"></script>
<script src="${ctxRoot}/js/moment.js"></script>
<script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
<script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>
<script language="javascript">
	$(document).ready(function() {
		$.validator.addMethod("dateFormat", function(value, element) {
			return moment(value, moment().toMomentFormatString('<%=DateUtil.defaultDateTimeFormat %>'), true).isValid();
		}, "Please enter a date in the format <%=DateUtil.defaultDateTimeFormat %>.");
		// <spring:message code="contentGroup.error.displayRunTime.atLeastOne" var="displayRunTimeMsg"/>
		$.validator.addMethod("displayRunTime", function(value, element) {
			var hourNum = parseInt($("#displayRunTimeHour").val(), 10) || 0;
			var minNum = parseInt($("#displayRunTimeMin").val(), 10) || 0;
			var secNum = parseInt($("#displayRunTimeSec").val(), 10) || 0;
			
			if (hourNum + minNum + secNum < 1) {
				return false;
			}
			return true;
		}, "${displayRunTimeMsg}");
		$.validator.addMethod("sourceFileRequired", function(value, element) {
			if (!$("#offer\\.termsTarget").val()) {
				if (!value && !$("#sourceFileName2").val()) {
					return false;
				}
			}
			return true;
		}, '[<spring:message code="contentSubset.fileSelect"/>] or [<spring:message code="contentSubset.fileSelect.sd" />] are required');
		$.validator.addMethod("hdSourceFileRequired", function(value, element) {
			if ($("#offer\\.termsTarget").val()) {
				var termsTarget = $("#offer\\.termsTarget").val() + '';
				if (termsTarget.indexOf("${const:getContentAssetConstant('FILE_TYPE_HD')}") >= 0 && !value) {
					return false;
				}
			}
			return true;
		}, 'This field is required..');
		$.validator.addMethod("sdSourceFileRequired", function(value, element) {
			if ($("#offer\\.termsTarget").val()) {
				var termsTarget = $("#offer\\.termsTarget").val() + '';
				if (termsTarget.indexOf("${const:getContentAssetConstant('FILE_TYPE_SD')}") >= 0 && !value) {
					return false;
				}
			}
			return true;
		}, 'This field is required..');
		/*
		$.validator.addMethod("royaltyPercent", function(value, element) {
			if ($("#offer\\.royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_PERCENT')}") {
				if ($("#offer\\.royaltyPercent").val() == '') {
					return false;
				}
			}
			return true;
		}, "This field is required..");
		$.validator.addMethod("royaltyMinimum", function(value, element) {
			if ($("#offer\\.royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_PERCENT')}") {
				if ($("#offer\\.royaltyMinimum").val() == '') {
					return false;
				}
			}
			return true;
		}, "This field is required..");
		$.validator.addMethod("royaltyFlatRate", function(value, element) {
			if ($("#offer\\.royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_FLATRATE')}") {
				if ($("#offer\\.royaltyFlatRate").val() == '') {
					return false;
				}
			}
			return true;
		}, "This field is required..");
		*/
		
		$('#offerComponents').validate( {
			rules: {
				"contentGroup.titleBrief": {required: true, maxlength: 128},
				"offer.startDateTime": {required: true, dateFormat: true},
				"offer.endDateTime": {required: true, dateFormat: true},
				"offer.licenseStartDateTime": {required: true, dateFormat: true},
				//"offer.licenseEndDateTime": {required: true, dateFormat: true},
				"offer.licenseEndDateTime": {required: true},
				"offer.termsTarget": {required: true},
				"movieSubset.sourceFileName": {sourceFileRequired: true, hdSourceFileRequired: true},
				"movieSubset.sourceFileName2": {sdSourceFileRequired: true},
				posterFile: {required: true},
				"contentGroup.summaryLong": {maxlength: 4096},
				"contentGroup.episodeNo": {number: true, digits: true},
				"contentGroup.rating": {required: true},
				"contentGroup.displayRunTime": {required: true},
				displayRunTimeHour: {displayRunTime: true, number: true, digits: true, max: 99},
				displayRunTimeMin: {number: true, digits: true, max: 59},
				displayRunTimeSec: {number: true, digits: true, max: 59},
				"contentGroup.year": {number: true, digits: true, minlength: 4, maxlength: 4},
				rentalPeriodYear: {number: true, digits: true},
				rentalPeriodMonth: {number: true, digits: true},
				rentalPeriodDay: {number: true, digits: true},
				rentalPeriodHour: {number: true, digits: true},
				"offer.subscriberViewLimit": {number: true, digits: true},
				/*
				"offer.price": {required: true, number: true, digits: true},
				"offer.pointPrice": {number: true, digits: true},
				"offer.royaltyType": {required: true},
				"offer.royaltyPercent": {royaltyPercent: true, number: true, range: [0, 100]},
				"offer.royaltyMinimum": {royaltyMinimum: true, number: true, digits: true},
				"offer.royaltyFlatRate": {royaltyFlatRate: true, number: true, digits: true},
				*/
				categoryInfos: {required: true}
			},
			highlight: function (element) {
				var group = $(element).closest(".form-group");
				if (group.hasClass("multi-validation-group")) {
	            	$(element).closest('div').addClass('has-error');
	            } else {
	            	$(element).closest('.form-group').addClass('has-error');
	            }
				
	        },
	        unhighlight: function (element) {
				var group = $(element).closest(".form-group");
				if (group.hasClass("multi-validation-group")) {
	            	$(element).closest('div').removeClass('has-error');
	            } else {
	            	$(element).closest('.form-group').removeClass('has-error');
	            }
	        },
	        success: function(label) {
	        	label.addClass("valid");
	        },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("offerComponents", "wait");
				
				try{
					var formData = new FormData(document.getElementById('offerComponents'));
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/asset/addOfferComponents.do",
						data: formData,
						cache: false,
					    contentType: false,
					    processData: false,
					    beforeSend: function(jqXHR, settings) {
					  		jqXHR.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
					    },
					    success: function(data, textStatus, jqXHR){
							if (data == 'Success') {
								openInform('Success', data, function() {
									location.reload();
								});
							} else {
								openAlert('Fail', data, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("offerComponents", "default");
								});
							}
						},
						error: function(jqXHR, textStatus, errorThrown){
							openAlert('Fail', 'Failure:' + textStatus);
							$("#saveButton").prop("disabled", false);
							setCursor("offerComponents", "default");
						}
					});
				}catch(e) {
					alert (e);
				}
	        }
		});
		
		$("#offerComponents #unlimitButton").click(function() {
			if ($("#offer\\.endDateTime").val() == "") {
				$('#datetimepicker9').data("DateTimePicker").setDate(new Date("December 31, 9999"));
			} else {
				$("#offer\\.endDateTime").val("");
			}
		});
		
		$("#offerComponents #unlimitButton2").click(function() {
			if ($("#offer\\.licenseEndDateTime").val() == "") {
				$('#datetimepicker2').data("DateTimePicker").setDate(new Date("December 31, 9999"));
			} else {
				$("#offer\\.licenseEndDateTime").val("");
			}
		});
		
		$('#offerComponents #seriesId').change(function(){
			try{
				$.getJSON('${ctxRoot}/asset/getLastEpisode.do', {seriesId: $("#seriesId option:selected").val()}, function(data) {
					$("#contentGroup\\.episodeNo").val(data.episodeId);
					if (data.episodeId != "1") {
						$("#contentGroup\\.actor").val(data.actor);
						$("#contentGroup\\.audience").val(data.audience.split("|")).select2();
						$("#contentGroup\\.countryOfOrigin").val(data.countryOfOrigin);
						$("#contentGroup\\.episodeName").val(data.episodeName);
						$("#contentGroup\\.episodeType").val("0");
						$("#contentGroup\\.genre").val(data.genre.split(",")).select2();
						$("#contentGroup\\.producer").val(data.producer);
						$("#contentGroup\\.rating").val(data.rating);
						$("#contentGroup\\.showType").val(data.showType.split("|")).select2();
						$("#contentGroup\\.studio").val(data.studio);
						$("#contentGroup\\.summaryLong").val(data.summaryLong);
						$("#contentGroup\\.titleBrief").val(data.titleBrief);
						$("#contentGroup\\.writer").val(data.writer);
						$("#contentGroup\\.year").val(data.year);
						$("#movieSubset\\.language").val(data.language.split(",")).select2();
						$("#movieSubset\\.subtitleLanguage").val(data.subtitleLanguage.split(",")).select2();
						$("#contentGroup\\.director").val(data.director);
			  		}
		        });
			}catch(e) {
				alert (e);
			}
	    });
		
		$("#offerComponents #fileSelectBtn").click(function() {
			var tUrl = "${ctxRoot}/asset/getMediaFileList.do?inputElementId=sourceFileName";
			openModalWindow('02', tUrl);
		});
		
		$("#offerComponents #fileSelectBtn2").click(function() {
			var tUrl = "${ctxRoot}/asset/getMediaFileList.do?inputElementId=sourceFileName2";
			openModalWindow('02', tUrl);
		});
		
		/*
		$("#offerComponents #fileSelectBtn3").click(function() {
			var tUrl = "${ctxRoot}/asset/getMediaFileList.do?inputElementId=previewSubset_sourceFileName";
			openModalWindow('02', tUrl);
		});
		*/
		
		$("#offerComponents #linkSelectBtnSeries").click(function() {
			var tUrl = "${ctxRoot}/asset/getSeriesListForm.do?popupType=selectBox&targetId=seriesId";
            openModalWindow('02', tUrl);
		});
		
		$("#offerComponents #saveButton").click(function() {
			// HH:mm:ss
	  		var runtimeStr = pad($("#displayRunTimeHour").val(), 2, '0')
	  				+ ":" + pad($("#displayRunTimeMin").val(), 2, '0')
	  				+ ":" + pad($("#displayRunTimeSec").val(), 2, '0');
	  		
	  		$("#contentGroup\\.displayRunTime").val(runtimeStr);
	  		
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
	  		
	  		$("#offer\\.rentalPeriod").val(periodStr);
		});
		
		/*
		$('#offer\\.royaltyType').change(function(){
	        if ($("#offer\\.royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_PERCENT')}") {
	        	$("#offer\\.royaltyPercent").prop("disabled", false);
	        	$("#offer\\.royaltyMinimum").prop("disabled", false);
	        	$("#offer\\.royaltyFlatRate").prop("disabled", true);
	        } else if ($("#offer\\.royaltyType option:selected").val() == "${const:get('COMMONCODE', 'ROYALTY_TYPE_FLATRATE')}") {
	        	$("#offer\\.royaltyPercent").prop("disabled", true);
	        	$("#offer\\.royaltyMinimum").prop("disabled", true);
	        	$("#offer\\.royaltyFlatRate").prop("disabled", false);
	        } else {
	        	$("#offer\\.royaltyPercent").prop("disabled", false);
	        	$("#offer\\.royaltyMinimum").prop("disabled", false);
	        	$("#offer\\.royaltyFlatRate").prop("disabled", false);
	        }
	    });
		*/
		
		$("#offerComponents #categorySelectBtn").click(function() {
			var tUrl = "${ctxRoot}/category/categoryListPopup.do?popupType=categoryMultiSelect";
			openModalWindow('02', tUrl);
		});
		
		// <spring:message code="combo.select" var="selectMsg"/>
		$("#offer\\.termsTarget").select2({placeholder: '${selectMsg}'});
		$("#sourceFileName").select2();
		$("#sourceFileName2").select2();
		//$("#previewSubset_sourceFileName").select2();
		$("#movieSubset\\.language").select2({placeholder: '${selectMsg}'});
		$("#movieSubset\\.subtitleLanguage").select2({placeholder: '${selectMsg}'});
		$("#seriesId").select2();
		$("#contentGroup\\.audience").select2({placeholder: '${selectMsg}'});
		$("#contentGroup\\.countryOfOrigin").select2();
		$("#contentGroup\\.genre").select2({
			placeholder: '<spring:message code="serviceAsset.contentGroup.genre.guide"/>'
            , maximumSelectionSize: 5
        });
		$("#categoryInfos").select2();
	});
	
	function setSourceFileName(inputElementId, fileName, filePath) {
		$('#' + inputElementId)
				.find('option')
				.remove()
				.end()
				.append($('<option></option>')
						.attr('value', filePath)
						.attr('selected', 'selected')
						.text(fileName)
				).trigger('change');
		
		closeModalWindow('02');
		
		if (inputElementId == 'sourceFileName') {
			try{
				$.getJSON('${ctxRoot}/asset/getDisplayRuntime.do', {mediaPath: filePath}, function(data) {
					$("#displayRunTimeHour").val(data.hour);
					$("#displayRunTimeMin").val(data.minute);
					$("#displayRunTimeSec").val(data.second);
		        });
			}catch(e) {
				alert (e);
			}
		}
	}
	
	function setCategoryInfo(categoryId, categoryUri, categoryName, isNew, isLastChance, priority) {
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
	<ul class="breadcrumb breadcrumb-page">
		<div class="breadcrumb-label text-light-gray">You are here: </div>
		<li><spring:message code="menu.serviceAsset"/></li>
		<li class="active"><spring:message code="menu.serviceAsset.easyRegist"/></li>
	</ul>
	<div class="page-header">
		<div class="row">
			<!-- Page header, center on small screens -->
			<h1 class="col-xs-12 col-sm-4 text-center text-left-sm"><i class="fa  fa-picture-o page-header-icon"></i>&nbsp;&nbsp;<spring:message code="menu.serviceAsset.easyRegist"/></h1>
		</div>
	</div> <!-- / .page-header -->
	
	<form:form modelAttribute="offerComponents" method="POST"
		cssClass="panel form-horizontal" enctype="multipart/form-data" >
		<div class="panel-heading">
			<span class="panel-title"></span>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label for="contentGroup.titleBrief" class="col-sm-2 control-label"><spring:message code="offer.title"/></label>
				<div class="col-sm-10">
					<form:input path="contentGroup.titleBrief" cssClass="form-control" placeholder="Max length = 128 characters."/>
				</div>
			</div>
			<div class="form-group multi-validation-group">
				<label for="offer.startDateTime" class="col-sm-2 control-label"><spring:message code="offer.availablePeriod"/></label>
				<div class="col-sm-10">
					<div align="right">
						<i class="fa fa-retweet" id="unlimitButton" style="cursor: pointer;"></i>&nbsp;&nbsp;&nbsp;<spring:message code="unlimited" />
					</div>
					<div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker8'>
		                	<spring:message code="contentSubset.availablePeriod.start" var="startPeriodLabel" />
		                    <form:input path="offer.startDateTime" cssClass="form-control" placeholder="${startPeriodLabel}"/>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
			        </div>
			        <div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker9'>
		                	<spring:message code="contentSubset.availablePeriod.end" var="endPeriodLabel" />
		                    <form:input path="offer.endDateTime" cssClass="form-control" placeholder="${endPeriodLabel}"/>
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
		            	defaultDate: new Date("December 31, 9999"),
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
		    <div class="form-group multi-validation-group">
				<label for="offer.licenseStartDateTime" class="col-sm-2 control-label"><spring:message code="offer.licensePeriod" /></label>
				<div class="col-sm-10">
					<div align="right">
						<i class="fa fa-retweet" id="unlimitButton2" style="cursor: pointer;"></i>&nbsp;&nbsp;&nbsp;<spring:message code="unlimited" />
					</div>
					<div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker1'>
		                	<spring:message code="offer.availablePeriod.start" var="startPeriodLabel" />
		                    <form:input path="offer.licenseStartDateTime" cssClass="form-control" placeholder="${startPeriodLabel}"/>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
			        </div>
			        <div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker2'>
		                	<spring:message code="offer.availablePeriod.end" var="endPeriodLabel" />
		                    <form:input path="offer.licenseEndDateTime" cssClass="form-control" placeholder="${endPeriodLabel}"/>
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
		            	defaultDate: new Date("December 31, 9999"),
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
				<label for="offer.termsTarget" class="col-sm-2 control-label"><spring:message code="offer.termsTarget" /></label>
				<div class="col-sm-10">
					<div class="select2-success">
						<form:select path="offer.termsTarget" cssClass="form-control" multiple="multiple">
							<option value=""><spring:message code="combo.select" /></option>
							<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_TERMS_TARGET')}" />
						</form:select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="movieSubset.sourceFileName" class="col-sm-2 control-label"><spring:message code="contentSubset.fileSelect" /></label>
				<div class="col-sm-10">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="movieSubset.sourceFileName" id="sourceFileName" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="fileSelectBtn" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group" id="sourceFileName2Div">
				<label for="movieSubset.sourceFileName2" class="col-sm-2 control-label"><spring:message code="contentSubset.fileSelect.sd"/></label>
				<div class="col-sm-10">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="movieSubset.sourceFileName2" id="sourceFileName2" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="fileSelectBtn2" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<!-- 
			<div class="form-group">
				<label for="previewSubset.sourceFileName" class="col-sm-2 control-label"><spring:message code="contentSubset.fileSelect.preview" /></label>
				<div class="col-sm-10">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="previewSubset.sourceFileName" id="previewSubset_sourceFileName" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="fileSelectBtn3" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			-->
			<div class="form-group">
				<label for="posterFile" class="col-sm-2 control-label"><spring:message code="contentSubset.fileSelect.poster" /></label>
				<div class="col-sm-10">
					<input name="posterFile" type="file" class="file" data-show-upload="false">
				</div>
			</div>
			<div class="form-group">
				<label for="movieSubset.audioType" class="col-sm-2 control-label"><spring:message code="contentSubset.audioType" /></label>
				<div class="col-sm-10">
					<form:select path="movieSubset.audioType" cssClass="form-control">
						<option value="">${selectMsg}</option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_AUDIO_TYPE')}" selected="${offerComponents.movieSubset.audioType}"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="movieSubset.language" class="col-sm-2 control-label"><spring:message code="contentSubset.language" /></label>
				<div class="col-sm-10">
					<div class="select2-success">
						<form:select path="movieSubset.language" items="${languageCodeList}" itemLabel="codeName" itemValue="code" cssClass="form-control" multiple="multiple">
						</form:select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="movieSubset.subtitleLanguage" class="col-sm-2 control-label"><spring:message code="contentSubset.subtitleLanguage" /></label>
				<div class="col-sm-10">
					<div class="select2-success">
						<form:select path="movieSubset.subtitleLanguage" items="${languageCodeList}" itemLabel="codeName" itemValue="code" cssClass="form-control" multiple="multiple">
						</form:select>
					</div>
				</div>
			</div>
			
			<!-- 이하 ContentGroup -->
			
			<div class="form-group">
				<label for="contentGroup.summaryLong" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.summaryLong" /></label>
				<div class="col-sm-10">
					<form:textarea path="contentGroup.summaryLong" rows="2" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.actor" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.actor" /></label>
				<div class="col-sm-10">
					<form:input path="contentGroup.actor" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.writer" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.writer" /></label>
				<div class="col-sm-10">
					<form:input path="contentGroup.writer" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.director" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.director" /></label>
				<div class="col-sm-10">
					<form:input path="contentGroup.director" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.producer" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.producer" /></label>
				<div class="col-sm-10">
					<form:input path="contentGroup.producer" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.studio" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.studio" /></label>
				<div class="col-sm-10">
					<form:input path="contentGroup.studio" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.seriesId" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.seriesId" /></label>
				<div class="col-sm-10">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="contentGroup.seriesId" id="seriesId" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="linkSelectBtnSeries" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.episodeType" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.episodeType" /></label>
				<div class="col-sm-10">
					<form:select path="contentGroup.episodeType" cssClass="form-control">
						<option value="">${selectMsg}</option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'CG_EPISODE_TYPE')}"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.episodeNo" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.episodeNo" /></label>
				<div class="col-sm-10">
					<form:input path="contentGroup.episodeNo" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.episodeName" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.episodeName" /></label>
				<div class="col-sm-10">
					<form:input path="contentGroup.episodeName" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.rating" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.rating" /></label>
				<div class="col-sm-10">
					<form:select path="contentGroup.rating" cssClass="form-control">
						<option value="">${selectMsg}</option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'CG_RATING')}"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.audience" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.audience" /></label>
				<div class="col-sm-10">
					<div class="select2-success">
						<form:select path="contentGroup.audience" cssClass="form-control" multiple="multiple">
							<ccode:options groupCode="${const:get('COMMONCODE', 'CG_AUDIENCE')}"/>
						</form:select>
					</div>
				</div>
			</div>
		    <div class="form-group multi-validation-group">
				<label for="contentGroup.displayRunTime" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.displayRunTime" /></label>
				<div class="col-sm-10">
					<div class='col-md-4' style="padding-left:0;padding-right:0">
		                <div class='input-group' style="width: 100%">
		                	<form:hidden path="contentGroup.displayRunTime"/>
		                	<input type="text" id="displayRunTimeHour" name="displayRunTimeHour" class="form-control" placeholder="<spring:message code='serviceAsset.contentGroup.displayRunTime.hour'/>"/>
		                </div>
			        </div>
					<div class='col-md-4' style="padding-left:0;padding-right:0">
		                <div class='input-group' style="width: 100%">
		                	<input type="text" id="displayRunTimeMin" name="displayRunTimeMin" class="form-control" placeholder="<spring:message code='serviceAsset.contentGroup.displayRunTime.minute'/>" />
		                </div>
			        </div>
					<div class='col-md-4' style="padding-left:0;padding-right:0">
		                <div class='input-group' style="width: 100%">
		                	<input type="text" id="displayRunTimeSec" name="displayRunTimeSec" class="form-control" placeholder="<spring:message code='serviceAsset.contentGroup.displayRunTime.sec'/>" />
		                </div>
			        </div>
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.year" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.year" /></label>
				<div class="col-sm-10">
					<form:input path="contentGroup.year" cssClass="form-control" />
				</div>
			</div>
		    <div class="form-group">
				<label for="contentGroup.countryOfOrigin" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.countryOfOrigin" /></label>
				<div class="col-sm-10">
					<form:select path="contentGroup.countryOfOrigin" cssClass="form-control">
						<ccode:options groupCode="${const:get('COMMONCODE', 'CG_COUNTRY_OF_ORIGIN')}"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="contentGroup.genre" class="col-sm-2 control-label"><spring:message code="serviceAsset.contentGroup.genre" /></label>
				<div class="col-sm-10">
					<div class="select2-success">
						<form:select path="contentGroup.genre" cssClass="form-control" multiple="multiple">
							<ccode:options groupCode="${const:get('COMMONCODE', 'CG_GENRE')}"/>
						</form:select>
					</div>
				</div>
			</div>
			
			<!-- Offer -->
			
			<div class="form-group">
				<label for="offer.termsDescription" class="col-sm-2 control-label"><spring:message code="offer.termsDescription" /></label>
				<div class="col-sm-10">
					<form:textarea path="offer.termsDescription" rows="3" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="offer.rentalPeriod" class="col-sm-2 control-label"><spring:message code="offer.rentalPeriod" /></label>
				<div class="col-sm-10">
					<div class='col-md-3' style="padding-left:0;padding-right:0">
		                <div class='input-group'>
		                	<form:hidden path="offer.rentalPeriod"/>
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
		                	<input type="text" id="rentalPeriodDay" name="rentalPeriodDay" value="2" class="form-control" placeholder="<spring:message code='offer.rentalPeriod.day'/>" />
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
				<label for="offer.subscriberViewLimit" class="col-sm-2 control-label"><spring:message code="offer.subscriberViewLimit" /></label>
				<div class="col-sm-10">
					<form:input path="offer.subscriberViewLimit" cssClass="form-control" />
					
					<form:hidden path="offer.price"/>
					<form:hidden path="offer.pointPrice"/>
					<form:hidden path="offer.royaltyType"/>
					<form:hidden path="offer.royaltyPercent"/>
					<form:hidden path="offer.royaltyMinimum"/>
				</div>
			</div>
			<!--
			<div class="form-group">
				<label for="offer.price" class="col-sm-2 control-label"><spring:message code="offer.price" /></label>
				<div class="col-sm-10">
					<form:input path="offer.price" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="offer.pointPrice" class="col-sm-2 control-label"><spring:message code="offer.pointPrice" /></label>
				<div class="col-sm-10">
					<form:input path="offer.pointPrice" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="offer.royaltyType" class="col-sm-2 control-label"><spring:message code="offer.royaltyType" /></label>
				<div class="col-sm-10">
					<form:select path="offer.royaltyType" cssClass="form-control">
						<option value=""><spring:message code="combo.select" /></option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'GROUP_CODE_ROYALTY_TYPE')}" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="offer.royaltyPercent" class="col-sm-2 control-label"><spring:message code="offer.royaltyPercent" /></label>
				<div class="col-sm-10">
					<form:input path="offer.royaltyPercent" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="offer.royaltyMinimum" class="col-sm-2 control-label"><spring:message code="offer.royaltyMinimum" /></label>
				<div class="col-sm-10">
					<form:input path="offer.royaltyMinimum" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="offer.royaltyFlatRate" class="col-sm-2 control-label"><spring:message code="offer.royaltyFlatRate" /></label>
				<div class="col-sm-10">
					<form:input path="offer.royaltyFlatRate" cssClass="form-control" />
				</div>
			</div>
			-->
			<div class="form-group">
				<label for="categoryInfos" class="col-sm-2 control-label"><spring:message code="offer.display" /></label>
				<div class="col-sm-10">
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
		</div>
		<div class="panel-footer text-right">
			<button type="submit" class="btn btn-primary" id="saveButton"><spring:message code='button.save'/></button>
		</div>
	</form:form>

</body>
</html>