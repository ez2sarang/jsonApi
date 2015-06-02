<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fast2.zimin.util.DateUtil"%>
<%@ taglib prefix="const" uri="Constants" %>
<%@ taglib prefix="ccode" uri="CommonCode"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<html>
<head>
<title><spring:message code="menu.serviceAsset.contentGroup" /></title>
<link href="${ctxRoot}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<script src="${ctxRoot}/js/moment.js"></script>
<script src="${ctxRoot}/js/moment-jdateformatparser.js"></script>
<script src="${ctxRoot}/js/bootstrap-datetimepicker.min.js"></script>
<script language="JavaScript">
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
		
		$('#contentGroup').validate( {
			rules: {
				contentType: {required: true},
				startDateTime: {required: true, dateFormat: true},
				endDateTime: {required: true, dateFormat: true},
				titleSortName: {maxlength: 22},
				titleBrief: {required: true, maxlength: 19},
				titleMedium: {required: true, maxlength: 35},
				titleLong: {required: true, maxlength: 128},
				summaryShort: {required: true,maxlength: 256},
				summaryMedium: {maxlength: 1024},
				summaryLong: {maxlength: 4096},
                //episodeType: {required: true},
				episodeNo: {number: true, digits: true},
				rating: {required: true},
				displayRunTime: {required: true},
				displayRunTimeHour: {displayRunTime: true, number: true, digits: true, max: 99},
				displayRunTimeMin: {number: true, digits: true, max: 59},
				displayRunTimeSec: {number: true, digits: true, max: 59},
				year: {number: true, digits: true, minlength: 4, maxlength: 4},
				genre: {required: true},
				showType: {required: true},
				movieSubsetId: {required: true},
				//previewSubsetId: {required: true},
				posterSubsetId: {required: true},
				thumbnailSubsetId: {required: true}
			},
            success: function(label) {
            	label.addClass("valid");
            },
			submitHandler: function () {
				$("#saveButton").prop("disabled", true);
				setCursor("contentGroup", "wait");
				
				try{
					$.ajax({
						type: "POST",
						url: "${ctxRoot}/asset/addContentGroup.do",
						data: $('form#contentGroup').serialize(),
						success: function(data, textStatus, jqXHR){
							if (data == 'Success') {
								openInform('Success', data, function() {
									location.reload();
								});
							} else {
								openAlert('Fail', data, function() {
									$("#saveButton").prop("disabled", false);
									setCursor("contentGroup", "default");
								});
							}
						},
						error: function(jqXHR, textStatus, errorThrown){
							openAlert('Fail', 'Failure:' + textStatus);
							$("#saveButton").prop("disabled", false);
							setCursor("contentGroup", "default");
						}
					});
				}catch(e) {
					alert (e);
				}
            }
		});
		
		$("#contentGroup #saveButton").click(function() {
			// HH:mm:ss
	  		var runtimeStr = pad($("#displayRunTimeHour").val(), 2, '0')
	  				+ ":" + pad($("#displayRunTimeMin").val(), 2, '0')
	  				+ ":" + pad($("#displayRunTimeSec").val(), 2, '0');
	  		
	  		$("#displayRunTime").val(runtimeStr);
		});
		
		$("#contentGroup #linkSelectBtnSeries").click(function() {
			var tUrl = "${ctxRoot}/asset/getSeriesListForm.do?popupType=selectBox&targetId=seriesId";
            openModalWindow('02', tUrl);
		});
		
		$("#linkSelectBtnMovie").click(function() {
            var tUrl = "${ctxRoot}/asset/getContentSubsetListForm.do?popupType=selectBox&targetId=movieSubsetId&subsetType=${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_MOVIE')}";
            openModalWindow('02', tUrl);
        });
		
        $("#linkSelectBtnPreview").click(function() {
            var tUrl = "${ctxRoot}/asset/getContentSubsetListForm.do?popupType=selectBox&targetId=previewSubsetId&subsetType=${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_PREVIEW')}";
            openModalWindow('02', tUrl);
        });
        
        $("#linkSelectBtnPoster").click(function() {
            var tUrl = "${ctxRoot}/asset/getContentSubsetListForm.do?popupType=selectBox&targetId=posterSubsetId&subsetType=${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_POSTER')}";
            openModalWindow('02', tUrl);
        });
        
        $("#linkSelectBtnThumbnail").click(function() {
            var tUrl = "${ctxRoot}/asset/getContentSubsetListForm.do?popupType=selectBox&targetId=thumbnailSubsetId&subsetType=${const:get('COMMONCODE', 'CONTENT_SUBSET_TYPE_THUMBNAIL')}";
            openModalWindow('02', tUrl);
        });
		
		$("#contentGroup #unlimitButton").click(function() {
			if ($("#endDateTime").val() == "") {
				$('#datetimepicker9').data("DateTimePicker").setDate(new Date("December 31, 9999"));
			} else {
				$("#endDateTime").val("");
			}
		});
		
		// <spring:message code="combo.select" var="selectMsg"/>
		$("#seriesId").select2();
		$("#audience").select2({placeholder: '${selectMsg}'});
		$("#countryOfOrigin").select2();
		$("#genre").select2({
			placeholder: '<spring:message code="serviceAsset.contentGroup.genre.guide"/>'
            , maximumSelectionSize: 5
        });
		$("#showType").select2({placeholder: '${selectMsg}'});
		$("#movieSubsetId").select2();
		$("#previewSubsetId").select2();
		$("#posterSubsetId").select2();
		$("#thumbnailSubsetId").select2();
	});
</script>
</head>
<body>

	<form:form modelAttribute="contentGroup" method="POST"
		cssClass="panel form-horizontal">
		<div class="panel-heading">
			<span class="panel-title"><i class="fa fa-ellipsis-v"></i> &nbsp; <spring:message code="serviceAsset.contentGroup.add"/></span>
		</div>
		<div class="panel-body">
		
			<div class="form-group">
				<label for="contentType" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.contentType" /></label>
				<div class="col-sm-9">
					<form:select path="contentType" cssClass="form-control">
						<option value="">${selectMsg}</option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'CG_CONTENT_TYPE')}" selected="${contentGroup.contentType}"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="startDateTime" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.validTerm" /></label>
				<div class="col-sm-9">
					<div align="right">
						<i class="fa fa-retweet" id="unlimitButton" style="cursor: pointer;"></i>&nbsp;&nbsp;&nbsp;<spring:message code="unlimited" />
					</div>
					<div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker8'>
		                	<spring:message code="serviceAsset.contentGroup.startDateTime" var="startPeriodLabel" />
		                    <form:input path="startDateTime" cssClass="form-control" placeholder="${startPeriodLabel}"/>
		                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
		                    </span>
		                </div>
			        </div>
			        <div class='col-md-6' style="padding-left:0;padding-right:0">
		                <div class='input-group date' id='datetimepicker9'>
		                	<spring:message code="serviceAsset.contentGroup.endDateTime" var="endPeriodLabel" />
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
				<label for="titleSortName" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.titleSortName" /></label>
				<div class="col-sm-9">
					<form:input path="titleSortName" cssClass="form-control" />
				</div>
			</div>
		    <div class="form-group">
				<label for="titleBrief" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.titleBrief" /></label>
				<div class="col-sm-9">
					<form:input path="titleBrief" cssClass="form-control" />
				</div>
			</div>
		    <div class="form-group">
				<label for="titleMedium" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.titleMedium" /></label>
				<div class="col-sm-9">
					<form:input path="titleMedium" cssClass="form-control" />
				</div>
			</div>
		    <div class="form-group">
				<label for="titleLong" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.titleLong" /></label>
				<div class="col-sm-9">
					<form:input path="titleLong" cssClass="form-control" />
				</div>
			</div>
		    <div class="form-group">
				<label for="summaryShort" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.summaryShort" /></label>
				<div class="col-sm-9">
					<form:textarea path="summaryShort" rows="2" cssClass="form-control" />
				</div>
			</div>
		    <div class="form-group">
				<label for="summaryMedium" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.summaryMedium" /></label>
				<div class="col-sm-9">
					<form:textarea path="summaryMedium" rows="2" cssClass="form-control" />
				</div>
			</div>
		    <div class="form-group">
				<label for="summaryLong" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.summaryLong" /></label>
				<div class="col-sm-9">
					<form:textarea path="summaryLong" rows="2" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="actor" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.actor" /></label>
				<div class="col-sm-9">
					<form:input path="actor" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="writer" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.writer" /></label>
				<div class="col-sm-9">
					<form:input path="writer" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="director" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.director" /></label>
				<div class="col-sm-9">
					<form:input path="director" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="producer" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.producer" /></label>
				<div class="col-sm-9">
					<form:input path="producer" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="studio" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.studio" /></label>
				<div class="col-sm-9">
					<form:input path="studio" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="seriesId" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.seriesId" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="seriesId" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="linkSelectBtnSeries" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="episodeType" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.episodeType" /></label>
				<div class="col-sm-9">
					<form:select path="episodeType" cssClass="form-control">
						<option value="">${selectMsg}</option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'CG_EPISODE_TYPE')}"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="episodeNo" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.episodeNo" /></label>
				<div class="col-sm-9">
					<form:input path="episodeNo" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="episodeName" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.episodeName" /></label>
				<div class="col-sm-9">
					<form:input path="episodeName" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="rating" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.rating" /></label>
				<div class="col-sm-9">
					<form:select path="rating" cssClass="form-control">
						<option value="">${selectMsg}</option>
						<ccode:options groupCode="${const:get('COMMONCODE', 'CG_RATING')}"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="audience" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.audience" /></label>
				<div class="col-sm-9">
					<div class="select2-success">
						<form:select path="audience" cssClass="form-control" multiple="multiple">
							<ccode:options groupCode="${const:get('COMMONCODE', 'CG_AUDIENCE')}"/>
						</form:select>
					</div>
				</div>
			</div>
		    <div class="form-group">
				<label for="displayRunTime" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.displayRunTime" /></label>
				<div class="col-sm-9">
					<div class='col-md-4' style="padding-left:0;padding-right:0">
		                <div class='input-group'>
		                	<form:hidden path="displayRunTime"/>
		                	<input type="text" id="displayRunTimeHour" name="displayRunTimeHour" class="form-control" placeholder="<spring:message code='serviceAsset.contentGroup.displayRunTime.hour'/>"/>
		                </div>
			        </div>
					<div class='col-md-4' style="padding-left:0;padding-right:0">
		                <div class='input-group'>
		                	<input type="text" id="displayRunTimeMin" name="displayRunTimeMin" class="form-control" placeholder="<spring:message code='serviceAsset.contentGroup.displayRunTime.minute'/>" />
		                </div>
			        </div>
					<div class='col-md-4' style="padding-left:0;padding-right:0">
		                <div class='input-group'>
		                	<input type="text" id="displayRunTimeSec" name="displayRunTimeSec" class="form-control" placeholder="<spring:message code='serviceAsset.contentGroup.displayRunTime.sec'/>" />
		                </div>
			        </div>
				</div>
			</div>
			<div class="form-group">
				<label for="year" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.year" /></label>
				<div class="col-sm-9">
					<form:input path="year" cssClass="form-control" />
				</div>
			</div>
		    <div class="form-group">
				<label for="countryOfOrigin" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.countryOfOrigin" /></label>
				<div class="col-sm-9">
					<form:select path="countryOfOrigin" cssClass="form-control">
						<ccode:options groupCode="${const:get('COMMONCODE', 'CG_COUNTRY_OF_ORIGIN')}"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="genre" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.genre" /></label>
				<div class="col-sm-9">
					<div class="select2-success">
						<form:select path="genre" cssClass="form-control" multiple="multiple">
							<ccode:options groupCode="${const:get('COMMONCODE', 'CG_GENRE')}"/>
						</form:select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="showType" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.showType" /></label>
				<div class="col-sm-9">
					<div class="select2-success">
						<form:select path="showType" cssClass="form-control" multiple="multiple">
							<ccode:options groupCode="${const:get('COMMONCODE', 'CG_SHOW_TYPE')}"/>
						</form:select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="movieSubsetId" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.movieSubsetId" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="movieSubsetId" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="linkSelectBtnMovie" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="previewSubsetId" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.previewSubsetId" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="previewSubsetId" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="linkSelectBtnPreview" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="posterSubsetId" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.posterSubsetId" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="posterSubsetId" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="linkSelectBtnPoster" ><spring:message code="button.select" /></button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="thumbnailSubsetId" class="col-sm-3 control-label"><spring:message code="serviceAsset.contentGroup.thumbnailSubsetId" /></label>
				<div class="col-sm-9">
					<div class="input-group">
						<div class="select2-success">
							<form:select path="thumbnailSubsetId" cssClass="form-control" multiple="multiple">
							</form:select>
						</div>
						<span class="input-group-btn">
							<button class="btn" type="button" id="linkSelectBtnThumbnail" ><spring:message code="button.select" /></button>
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