<%--
  Created by IntelliJ IDEA.
  User: ez2sarang
  Date: 15. 5. 19.
  Time: 오후 1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
        %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>Document</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.standalone.css" rel="stylesheet" type="text/css">
    <style>
        .progress { position:relative; width:400px; border: 1px solid #ddd; padding: 1px; border-radius: 3px; }
        .bar { background-color: #B4F5B4; width:0%; height:20px; border-radius: 3px; }
        .percent { position:absolute; display:inline-block; top:3px; left:48%; }
    </style>
    <script src="//code.jquery.com/jquery-2.1.4.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.kr.min.js"></script>
    <script src="http://malsup.github.io/jquery.form.js"></script>
    <script>
    $(document).ready(function() {
        var bar = $('.bar');
        var percent = $('.percent');
        var status = $('#status');

        /*$('form#apiForm').ajaxForm({
            beforeSend: function() {
                status.empty();
                var percentVal = '0%';
                bar.width(percentVal)
                percent.html(percentVal);
            },
            uploadProgress: function(event, position, total, percentComplete) {
                var percentVal = percentComplete + '%';
                bar.width(percentVal)
                percent.html(percentVal);
                //console.log(percentVal, position, total);
            },
            success: function() {
                var percentVal = '100%';
                bar.width(percentVal)
                percent.html(percentVal);
            },
            complete: function(xhr) {
                status.html(xhr.responseText);
            }
        });*/

        var options = {
//            target:        '#output2',   // target element(s) to be updated with server response
//            beforeSubmit:  showRequest,  // pre-submit callback
//            success:       showResponse  // post-submit callback

            // other available options:
            //url:       url         // override for form's 'action' attribute
            //type:      type        // 'get' or 'post', override for form's 'method' attribute
            //dataType:  null        // 'xml', 'script', or 'json' (expected server response type)
            //clearForm: true        // clear all form fields after successful submit
            //resetForm: true        // reset the form after successful submit

            // $.ajax options can be used here too, for example:
            //timeout:   3000
        };

        // bind to the form's submit event
        /*$('form').submit(function() {
            // inside event callbacks 'this' is the DOM element so we first
            // wrap it in a jQuery object and then invoke ajaxSubmit
            $(this).ajaxSubmit(options);

            // !!! Important !!!
            // always return false to prevent standard browser submit and page navigation
            return false;
        });*/
    });

    var sessionToken = '';
    var transactionToken = '';
    var apiUrl = '';
    //getOfferList : 'menuId','listCount','pageNo','sortItem','sortDirection'
    //purchaseOffer : 'offerId','offerPrice','sourceId','purchasePW'
    //notifyContentPlay : 'cgId','assetType','controlType','offset','userId'
    var params = {
        "userId" : {
            name:"userId"
            , type:"input"
            , default:"navigator"
        },"clientId" : {
            name:"clientId"
            , type:"input"
            , default:"navigator"
        }, "userPw" : {
            name:"userPw"
            , type:"input"
            , default:"1111"
        }, "purchasePW" : {
            name:"purchasePW"
            , type:"input"
            , default:"1111"
        }, "menuPid" : {
            name:"menuPid"
            , type:"input"
        }, "menuId" : {
            name:"menuId"
            , type:"input"
        }, "userLocale" : {
            name:"userLocale"
            , type:"select"
            , options:["KO:KO"]
            , default:0
        }, "listCount" : {
            name:"listCount"
            , type:"select"
            , options:["5:5","10:10","15:15"]
            , default:0
        }, "pageNo" : {
            name:"pageNo"
            , type:"select"
            , options:["1:1","2:2","3:3"]
            , default:0
        }, "sortItem" : {
            name:"sortItem"
            , type:"select"
            , options:[":"]
            , default:0
        }, "sortDirection" : {
            name:"sortDirection"
            , type:"radio"
            , options:["ASC:ASC","DESC:DESC"]
            , default:1
        }, "controlType" : {
            name:"controlType"
            , type:"select"
            , options:["PLAY:PLAY","STOP:STOP"]
            , default:0
        }, "offerId" : {
            name:"offerId"
            , type:"input"
        }, "cgId" : {
            name:"cgId"
            , type:"input"
        }, "offerPrice" : {
            name:"offerPrice"
            , type:"input"
            , default:1000
        }, "sourceId" : {
            name:"sourceId"
            , type:"input"
        }, "assetType" : {
            name:"assetType"
            , type:"input"
        }, "offset" : {
            name:"offset"
            , type:"input"
        }, "fromDate" : {
            name:"fromDate"
            , type:"date"
            , format:"yyyymmdd"
        }, "toDate" : {
            name:"toDate"
            , type:"date"
            , format:"yyyymmdd"
        }, "fileName" : {
            name:"fileName"
            , type:"input"
        },"name" : {
            name:"name"
            , type:"input"
        },"fileData" : {
            name:"fileData"
            , type:"file"
            , options:"multiple"
        }
    };
    function setForm(link, url, dataIds) {
        $(link).closest('ul').find('li').removeClass('list-group-item-info');
        $(link).closest('li').addClass('list-group-item-info');
        try {
            $('form#apiForm').attr("enctype","");
            apiUrl = url;
            $("#apiForm").html("");
            $("#apiForm").append($("<input type='hidden' id='sessionToken' name='sessionToken'>").val(sessionToken));
            $("#apiForm").append($("<input type='hidden' id='transactionToken' name='transactionToken'>").val(transactionToken));
            var form = $("<ul>");
            for(var i in dataIds) {
                form.append($("<li>").text(dataIds[i]+" : ").append(getForm(params[dataIds[i]])));
            }
            $("#apiForm").append(form);
        } catch (e) {
            alert("error at setForm:"+e);
        }
        $('.datepicker').datepicker();
    }
    function getForm(tag) {
        if(tag.type == 'input') {
            input = $("<input type='text' name='"+tag.name+"'>");
            if(tag.default != undefined && tag.default != "") {
                input.val(tag.default);
            }
            return input;
        }
        if(tag.type == 'select') {
            select = $("<select name='"+tag.name+"'>");
            for(var pos in tag.options) {
                select.append($("<option value='"+tag.options[pos].split(":")[0]+"'"+(tag.default==pos?" selected":"")+">").text(tag.options[pos].split(":")[1]));
            }
            return select;
        }
        if(tag.type == 'radio') {
            radio = $("<span>");
            for(var pos in tag.options) {
                radio.append(tag.options[pos].split(":")[1]+" : ");
                radio.append($("<input type='radio' name='"+tag.name+"' value='"+tag.options[pos].split(":")[0]+"'"+(tag.default==pos?" checked":"")+">"));
                radio.append("  ");
            }
            return radio;
        }
        if(tag.type == 'check') {
            check = $("<span>");
            for(var pos in tag.options) {
                check.append(tag.options[pos].split(":")[1]+" : ");
                check.append($("<input type='checkbox' name='"+tag.name+"' value='"+tag.options[pos].split(":")[0]+"'"+(tag.default==pos?" checked":"")+">"));
                check.append("  ");
            }
            return check;
        }
        if(tag.type == 'date') {
            date = $("<input name='"+tag.name+"' class='datepicker'"+(tag.format?" data-date-format='"+tag.format+"'":"")+">");
            if(tag.default != undefined && tag.default != "") {
                input.val(tag.default);
            }
            return date;
        }
        if(tag.type == 'file') {
            file = $("<input type='file' name='"+tag.name+"'"+(tag.options?" "+tag.options:"")+">");
            return file;
        }
    }
    function callApi(url) {
//        alert($('input[name="fileData"]')[0].files[0]);

        try{
        /*$('form#apiForm').attr("action","${pageContext.request.contextPath}"+url+".do");
        $('form#apiForm').ajaxForm(function(result) {
            alert('the form was successfully processed');
        });*/
//        $('form#apiForm').submit(function() {
            // inside event callbacks 'this' is the DOM element so we first
            // wrap it in a jQuery object and then invoke ajaxSubmit
            var bar = $('.bar');
            var percent = $('.percent');
            var status = $('#status');
            $('form#apiForm').ajaxSubmit({
//            target:        '#output2',   // target element(s) to be updated with server response
//            beforeSubmit:  showRequest,  // pre-submit callback
//            success:       showResponse  // post-submit callback
                // other available options:
                //url:       url         // override for form's 'action' attribute
                //type:      type        // 'get' or 'post', override for form's 'method' attribute
                //dataType:  null        // 'xml', 'script', or 'json' (expected server response type)
                //clearForm: true        // clear all form fields after successful submit
                //resetForm: true        // reset the form after successful submit

                // $.ajax options can be used here too, for example:
                //timeout:   3000
                url: "${pageContext.request.contextPath}"+url+".do"
                , type: "post"
                , beforeSend: function() {
                    status.empty();
                    var percentVal = '0%';
                    bar.width(percentVal)
                    percent.html(percentVal);
                },
                uploadProgress: function(event, position, total, percentComplete) {
                    var percentVal = percentComplete + '%';
                    bar.width(percentVal)
                    percent.html(percentVal);
                    //console.log(percentVal, position, total);
                },
                success: function() {
                    var percentVal = '100%';
                    bar.width(percentVal)
                    percent.html(percentVal);
                },
                complete: function(xhr) {
                    status.html(xhr.responseText);
                    var msg = JSON.parse(xhr.responseText);
                    try {
                        if(msg.transactionToken != undefined && msg.transactionToken != "") {
                            transactionToken = msg.transactionToken;
                            $("#transactionToken").val(transactionToken);
                        }
                        if(msg.sessionToken != undefined && msg.sessionToken != "") {
                            sessionToken = msg.sessionToken;
                            $("#sessionToken").val(sessionToken);
                        }
                    } catch (e) {
                        alert("error callApi:"+e);
                    }
//                    alert(xhr.responseText);
                }
            });
            /*$('form#apiForm').ajaxForm({
                beforeSend: function() {
                    status.empty();
                    var percentVal = '0%';
                    bar.width(percentVal)
                    percent.html(percentVal);
                },
                uploadProgress: function(event, position, total, percentComplete) {
                    var percentVal = percentComplete + '%';
                    bar.width(percentVal)
                    percent.html(percentVal);
                    //console.log(percentVal, position, total);
                },
                success: function() {
                    var percentVal = '100%';
                    bar.width(percentVal)
                    percent.html(percentVal);
                },
                complete: function(xhr) {
                    status.html(xhr.responseText);
                },
                url:  "${pageContext.request.contextPath}"+url+".do"
                , type: "post"
            });*/
            // !!! Important !!!
            // always return false to prevent standard browser submit and page navigation
//            return false;
//        });
//        $('form#apiForm').submit();

        if (true) {
            return;
        }
        $.ajax({
            type: "POST"
            , url: "${pageContext.request.contextPath}"+url+".do"
//            , data: new FormData().append('file',$('input[name="fileData"]')[0].files[0])//$('form#apiForm').serialize()
//            , data: new FormData().append('file',$('input[name="fileData"]')[0].files[0])//$('form#apiForm').serialize()
            , data: new FormData(document.all)//$('form#apiForm').serialize()
            , xhr: function() {  // Custom XMLHttpRequest
                var myXhr = $.ajaxSettings.xhr();
                if(myXhr.upload){ // Check if upload property exists
                    myXhr.upload.addEventListener('progress',progressHandlingFunction, false); // For handling the progress of the upload
                }
                return myXhr;
            }
            , contentType: false
            , success: function (msg) {
                try {
                    if(msg.transactionToken != undefined && msg.transactionToken != "") {
                        transactionToken = msg.transactionToken;
                        $("#transactionToken").val(transactionToken);
                    }
                    if(msg.sessionToken != undefined && msg.sessionToken != "") {
                        sessionToken = msg.sessionToken;
                        $("#sessionToken").val(sessionToken);
                    }
                } catch (e) {
                    alert("error callApi:"+e);
                }
                try {
                    $("#result").text(stringify(msg));
                } catch (e) {
                    alert("error callApi:"+e);
                }
            }
            , error:  function (xhr, ajaxOptions, thrownError) {
                openAlert('Fail', 'Failure');
            }
        });
        }catch(e){alert("error:"+ new Error(e).lineNumber +"\n"+ e.stack);}
    }
    function progressHandlingFunction(e){
        if(e.lengthComputable){
            $('progress').attr({value:e.loaded,max:e.total});
        }
    }
    function stringify(obj) {
        if ("JSON" in window) {
            return JSON.stringify(obj);
        }

        var t = typeof (obj);
        if (t != "object" || obj === null) {
            // simple data type
            if (t == "string") obj = '"' + obj + '"';

            return String(obj);
        } else {
            // recurse array or object
            var n, v, json = [], arr = (obj && obj.constructor == Array);

            for (n in obj) {
                v = obj[n];
                t = typeof(v);
                if (obj.hasOwnProperty(n)) {
                    if (t == "string") {
                        v = '"' + v + '"';
                    } else if (t == "object" && v !== null) {
                        v = jQuery.stringify(v);
                    }

                    json.push((arr ? "" : '"' + n + '":') + String(v));
                }
            }

            return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
        }
    }
    $(document).ready(function() {
        $('.datepicker').datepicker({});
    });
    </script>
</head>
<body>
<ul class="breadcrumb breadcrumb-page">
    <li class="active">Client for Navigation Server (ver.1.0.0)</li>
</ul>
<div class="row">
    <div class="col-md-2" style="overflow-x: auto; height: 400px; border: 1px">
        <ul class="list-group">
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/login', ['userId','userPw','userLocale']);">login</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getUserInfo', []);">getUserInfo</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/logout', []);">logout</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getMenuList', ['menuPid']);">getMenuList</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getMenuInfo', ['menuId']);">getMenuInfo</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getOfferList', ['menuId','listCount','pageNo','sortItem','sortDirection']);">getOfferList</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getPcgInfo', ['offerId']);">getPcgInfo</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getRelatedOfferList', ['offerId']);">getRelatedOfferList</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getCGInfo', ['offerId']);">getCGInfo</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/purchaseOffer', ['offerId','offerPrice','sourceId','purchasePW']);">purchaseOffer</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getAssetType', ['offerId','cgId']);">getAssetType</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getSSContentInfo', ['offerId','cgId','assetType']);">getSSContentInfo</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/notifyContentPlay', ['cgId','assetType','controlType','offset','userId']);">notifyContentPlay</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getPurchasedList', ['fromDate','toDate','listCount','pageNo','sortItem','sortDirection']);">getPurchasedList</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getViewedList', ['fromDate','toDate','listCount','pageNo','sortItem','sortDirection']);">getViewedList</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/getAvailiableContentList', ['listCount','pageNo','sortItem','sortDirection']);">getAvailiableContentList</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/checkViewAuthority', ['cgId','assetType','clientId','fileName']);">checkViewAuthority</a></li>
            <li class="list-group-item"><a href="#" onclick="setForm(this, '/upload', ['name','fileData']);">upload</a></li>
        </ul>
    </div>
    <div class="col-md-10" style="overflow-x: auto; border: 1px">
        <div class="panel colourable">
            <div class="panel-heading">
                <%--<textarea id="result" rows="6" class="form-control"></textarea>--%>
                <div class="progress">
                    <div class="bar"></div>
                    <div class="percent">0%</div>
                </div>
                <div id="status"></div>
            </div>
            <div id="formArea" class="panel-body">
                <form id="apiForm">
                </form>
            </div>
            <div class="panel-footer">
                <button class="btn btn-default btn-sm active" onclick="callApi(apiUrl)">전송</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>