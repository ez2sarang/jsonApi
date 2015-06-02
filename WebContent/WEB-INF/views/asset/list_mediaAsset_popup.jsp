<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 2014. 9. 12.
  Time: 오후 3:48
  To change this template use File | Settings | File Templates.
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ page import="com.fast2.zimin.util.DateUtil"
%><%@ include file="/WEB-INF/views/common/includes.jsp"
%><%@ taglib prefix="const" uri="Constants"
%><%@ taglib prefix="ccode" uri="CommonCode"
%><%@ taglib prefix="zm" uri="ziminTag"
%><html>
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

    <!-- Get jQuery from Google CDN -->
    <!--[if !IE]> -->
    <script type="text/javascript"> window.jQuery || document.write('<script language="JavaScript" src="${ctxRoot}/js/jquery-2.0.3.min.js">'+"<"+"/script>"); </script>
    <!-- <![endif]-->
    <!--[if lte IE 9]>
    <script type="text/javascript"> window.jQuery || document.write('<script language="JavaScript" src="${ctxRoot}/js/jquery-1.8.3.min.js">'+"<"+"/script>"); </script>
    <![endif]-->
<!--     <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script> -->
    <script src="${ctxRoot}/js/jquery-ui.min-1.11.1.js"></script>

    <!-- Pixel Admin's javascripts -->
    <script src="${ctxRoot}/assets/javascripts/bootstrap.min.js"></script>
    <%--<script src="${ctxRoot}/assets/javascripts/pixel-admin.min.js"></script>--%>

    <!--[if lt IE 9]>
    <script src="${ctxRoot}/assets/javascripts/ie.min.js"></script>
    <![endif]-->

<!--     <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script> -->

    <script src="${ctxRoot}/js/common.js"></script>
    <link href="${ctxRoot}/js/video-js/video-js.min.css" rel="stylesheet">
    <script src="${ctxRoot}/js/video-js/video.js"></script>
    <script src="http://www.videojs.com/js/home.js"></script>
    <script language="javascript">
        videojs.options.flash.swf = "${ctxRoot}/js/video-js/video-js.swf";
        (function($){
            $(document).ready(function () {
                //document.createElement('video');
                //$("#mediaViewer").attr("height",800);
                //$("#example_video_1").append("<source src='http://192.168.0.7:8080/zimin/js/streamingMedia.do?fileName=/Users/ez2sarang/Downloads/test.mp4' type='video/mp4'/>");
            });
        })(jQuery);

        function playVideo(fileName, width, height) {
            try {
                if(fileName.indexOf("D:")>=0) {
                    //$("#mediaViewer").html("<source src='http://192.168.0.7:8080/zimin/js/streamingMedia.do?fileName=/Users/ez2sarang/Downloads/test.mp4' type='video/mp4'/>");
                    $("#mediaViewer_html5_api").append(String.format("<source src='${ctxRoot}/js/test.mp4'/>"));
                } else {
                    $("#detailViewDiv .panel-body").html(
                            String.format(
                                    "<video id=\"mediaViewer\" class=\"video-js vjs-default-skin\" controls preload=\"auto\" width=\"100%\" height=\"{2}\" data-setup='{\"example_option\":true}'>" +
                                            "<source src='${ctxRoot}/serviceflow/streamingMedia.do?fileName={0}' typeX='video/{1}'/>" +
                                            "<p class='vjs-no-js'>To view this video please enable JavaScript, and consider upgrading to a web browser that <a href='http://videojs.com/html5-video-support/' target='_blank'>supports HTML5 video</a></p>" +
                                            "</video>", fileName, fileName.substring(fileName.lastIndexOf(".")+1, height))
                    )
                    //$("#mediaViewer").attr("height",height);
                }
            } catch (e) {
                alert("player error(2):"+e);
            }
        }
    </script>
</head>
<body>
    <div class="panel-heading">
        <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i>${title}</span>
    </div>
    <div class="panel-body">
        <div class="col-md-5" style="overflow-x: auto;">
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
                                <th><spring:message code="contentSubset.encryptionKeyBlock"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${assetList}" var="item">
                            <tr>
                                <td>(${item.fileType})${item.contentAssetId}</td>
                                <td><a href="#" onclick="playVideo('${item.fileName}', '${item.xResolution}','${item.yResolution}');">${item.fileNameNoWithPath}</a></td>
                                <td><ccode:name	groupCode="${const:get('COMMONCODE', 'GROUP_CODE_STAGE_CODE')}" code="${item.stageCode}" /></td>
                                <td>${item.resolution}</td>
                                <td>${item.bitRate}</td>
                                <td>${item.encryptionKeyBlock}</td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-7" id="detailViewDiv">
            <div class="panel colourable">
                <div class="panel-heading">
                    <span class="panel-title"><i class="panel-title-icon fa fa-flask"></i><spring:message code="contentSubset.contentAsset.view"/></span>
                </div>
                <div class="panel-body" style="overflow:auto;">
                    <%--<video id="mediaViewer" class="video-js vjs-default-skin"
                           controls preload="auto" width="100%" height="400"

                           data-setup='{"example_option":true}'>
                        &lt;%&ndash;<source src="http://video-js.zencoder.com/oceans-clip.mp4" type='video/mp4' />&ndash;%&gt;
                        <!--<source src="http://video-js.zencoder.com/oceans-clip.webm" type='video/webm' />--//-->
                        <!--<source src="http://video-js.zencoder.com/oceans-clip.ogv" type='video/ogg' />--//-->
                        <source src="http://192.168.0.7:8080/zimin/serviceflow/streamingMedia.do?fileName=/Users/ez2sarang/Downloads/test.ogv" type='video/ogg'/>

                        <p class="vjs-no-js">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a></p>
                    </video>--%>
                    <%--<embed type="application/x-vlc-plugin" pluginspage="http://www.videolan.org" id="vlc" width="100%" height="100%" target="http://192.168.0.7:8080/zimin//serviceflow/streamingMedia.do?fileName=/Users/ez2sarang/Downloads/test2.avi"/>
                    <object classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921" codebase="http://download.videolan.org/pub/videolan/vlc/last/win32/axvlc.cab"></object>--%>

                    <%--<object type="application/x-vlc-plugin" data="http://192.168.0.7:8080/zimin/js/streamingMedia.do?fileName=/Users/ez2sarang/Downloads/test2.avi" width="400" height="300" id="video1">
                        <param name="movie" value="http://192.168.0.7:8080/zimin/js/streamingMedia.do?fileName=/Users/ez2sarang/Downloads/test2.avi"/>
                        <embed type="application/x-vlc-plugin" name="video1"
                               autoplay="no" loop="no" width="400" height="300"
                               target="http://192.168.0.7:8080/zimin/js/streamingMedia.do?fileName=/Users/ez2sarang/Downloads/test2.avi" />
                        <a href="http://192.168.0.7:8080/zimin//serviceflow/streamingMedia.do?fileName=/Users/ez2sarang/Downloads/test2.avi">Download Video1</a>
                    </object>
                    <a href="javascript:;" onclick='document.video1.play()'>Play video1</a>
                    <a href="javascript:;" onclick='document.getElementById('video1').pause()'>Pause video1</a>
                    <a href="javascript:;" onclick='document.video1.stop()'>Stop video1</a>
                    <a href="javascript:;" onclick='document.video1.fullscreen()'>Fullscreen</a>--%>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer" style="margin-bottom: 0;">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="if(opener){window.close();}else{try{reLoadTree();}catch(e){}}"><spring:message code='button.close'/></button>
        </div>
    </div>
</body>
</html>
