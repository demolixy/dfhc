<%@page import="java.net.URLDecoder"%>
<%@page import="org.quickbundle.third.fileupload.RmUploadHelper"%>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/rm-tools.js"></script>

<link href="<%=request.getContextPath()%>/third/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/third/swfupload/js/swfupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/third/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/third/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/third/swfupload/js/handlers.js"></script>
<style type="text/css">
/*弹出层的STYLE 1b559c*/
html,body {height:100%; margin:0px; font-size:12px;}
 
.mydiv {
	filter:alpha(Opacity=90);
	background:#ffffff;
	border: 1px solid #1b559c;
	text-align: center;
	line-height: 40px;
	font-size: 12px;
	font-weight: bold;
	z-index:99;
	width: 750px;
	height: 400px;
	left:30%;/*FF IE7*/
	top: 30%;/*FF IE7*/
	 
	margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 */
	margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/
	 
	margin-top:0px;
	 
	position:fixed!important;/*FF IE7*/
	position:absolute;/*IE6*/
	 
	_top:       expression(eval(document.compatMode &&
	            document.compatMode=='CSS1Compat') ?
	            documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
	            document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/
	 
}


/*The END*/
 
</style>
<script type="text/javascript">
var swfu=null;
var this_name_flg=<%=request.getParameter("name_flg")%>;
$(function(){

	var settings = {
			flash_url : "<%=request.getContextPath()%>/third/swfupload/flash/swfupload.swf",
			upload_url: "<%=request.getContextPath()%>/third/swfupload/fileUpload.jsp?orderId=<%=request.getParameter("orderId")%>",
			file_size_limit : "<%=RmUploadHelper.DEFAULT_sizeMax/(1024*1024)%> MB",
			file_types : "*.*",
			file_types_description : "All Files",
			file_upload_limit_max : 200,
			file_upload_limit : <%=request.getParameter("files")%>,//表示可以一次上传多少个附件
			file_queue_limit : 0,
			custom_settings : {
				progressTarget : "fsUploadProgress",
				cancelButtonId : "btnCancel"
			},
			debug: false,

			// Button settings
			button_image_url: "<%=request.getContextPath()%>/third/swfupload/images/TestImageNoText_65x29.png",
			button_width: "65",
			button_height: "29",
			button_placeholder_id: "spanButtonPlaceHolder",
			button_text: '<span class="theFont">选择文件</span>',
			button_text_style: ".theFont {  }",
			button_text_left_padding: 12,
			button_text_top_padding: 6,
			
			// The event handler functions are defined in handlers.js
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,
			queue_complete_handler : queueComplete	// Queue plugin event
		};
	//window.setTimeout("refreshFileUploadLimit()", 500);
	swfu = new SWFUpload(settings);//实例化一个SWFUpload，传入参数配置对象
})

function closeUploadDiv(filename,type){
	//document.getElementById('content').style.display='none';
	//document.getElementById('bg').style.display='none';
	window.parent.closeUploadDiv(filename,type);
	}
window.onload = function(){
};
function uploadSuccess(file, serverData) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setComplete();
		progress.setStatus("上传完毕!\n");
		progress.toggleCancel(false);
		//写到结果
		document.getElementById("uploadResult").innerHTML = serverData;
		window.setTimeout("refreshFileUploadLimit()", 500);
		window.setTimeout("resetParentIframe()", 800);
		//$("#content").hide();
		 
	} catch (ex) {
		alert(ex.message);
		//this.debug(ex);
	}
}
function uploadComplete(file) {
	if (this.getStats().files_queued === 0) {
			document.getElementById(this.customSettings.cancelButtonId).disabled = true;
			 //window.location="<%=request.getContextPath()%>/supregisterinfo/nextStepUrl?type=Basic";
			 if(this_name_flg=='0')//0 是不改变上传文件的名称    1改上传文件名称，所以得到后台去查
			 {
				 closeUploadDiv('<%=request.getParameter("filename")%>',file.name);
		     }
			 else
			 {
				closeUploadDiv('<%=request.getParameter("filename")%>',file.type);
			 }
		}
	}
	
function resetParentIframe() {
	//重设高度
	var iframes = parent.document.frames;
	for(var i=0; i<iframes.length; i++) {
		if(iframes[i].name = "contentFrame") {
			reinitIframe(iframes[i]);
		}
	}
}

function reinitIframe(iframe){
	//alert(iframe + " " + iframe.document + " " + iframe.document.body.outerHTML);
	var bHeight = iframe.document.body.scrollHeight;
	var dHeight = iframe.document.documentElement.scrollHeight;
	//alert(bHeight + " " + dHeight);
	var height = Math.max(bHeight, dHeight);
	iframe.height =  height;
}

function refreshFileUploadLimit() {
	var currentFileCount = document.getElementById("uploadResult").getElementsByTagName("SPAN").length;
	var remainUploadCount = swfu.settings.file_upload_limit_max - currentFileCount;
	if(remainUploadCount < 1) {
		swfu.setButtonDisabled(true);
		setFeedback("您最多能上传" + swfu.settings.file_upload_limit_max + "个文件，已经上传了" + document.getElementById("uploadResult").getElementsByTagName("SPAN").length + "个，不能继续上传了");
	} else {
		swfu.setFileUploadLimit(remainUploadCount);
	}
	//setFeedback2("max=" + swfu.settings.file_upload_limit_max + "<br>length=" + document.getElementById("uploadResult").getElementsByTagName("SPAN").length + "<br> swfu.settings.file_upload_limit=" +  swfu.settings.file_upload_limit);
	try {
		var iframes = parent.document.getElementsByTagName("IFRAME");
		for(var i=0; i<iframes.length; i++) {
			if(window == iframes[i].contentWindow) {
				var inputObj = iframes[i].parentNode.children[0];
				inputObj.value = currentFileCount > 0 ? 1 : 0;
			}
		}
	} catch(e){alert(e.message);}
}

function setFeedback(msg) {
	document.getElementById("divStatusFeedback").innerHTML = msg;
}

function setFeedback2(msg) {
	document.getElementById("divStatusFeedback2").innerHTML = msg;
}

///////以上是上传文件js ///////
</script>


<div id="content" >

	<form id="form2" action="index.jsp" method="post" enctype="multipart/form-data">
		<div >
		<table width="100%" style="background:#1b559c;">
			<tr>
				<td style='text-align:left;font-size:15px;background:#1b559c;color:#ffffff;font-weight:bold;border-right:0px;'>文件上传</td>
				<td style='border-right:0px;font-size:15px;background:#1b559c;color:#ffffff;font-weight:bold;'>
				<span id="closeDetail" onclick="closeUploadDiv('','')" style="cursor: pointer;float:right">关闭&nbsp;</span></td>
			</tr>
		</table>
		</div>
		<table>
			<tr>
				<td >
					<div style='border:1px solid #1b559c;height:230px; margin-left: 50%' class="fieldset flash" id="fsUploadProgress">
						<span class="legend">上传队列</span>
					</div>
					<div id="divStatus" style="margin-left: 50%;color: red;width: 100%;">
					<%
					  if(request.getParameter("ts")!=null){
				    %>
				      <%=URLDecoder.decode(request.getParameter("ts").toString(),"utf-8")%>
				    <% 
					  }
					%>
					</div>
					<div id="divStatusFeedback"></div>
					<div id="divStatusFeedback2"></div>
				</td>
				<td>
					<div style='border:1px solid #1b559c;height:230px;display: none;'  class="fieldset flash" id="uploadResult_outer">
						<span class="legend">附件列表</span>
						<div id="uploadResult">
                                <%@include file="/third/swfupload/i_uploadResult.jsp" %>
						</div>
					</div>
				</td>
			</tr>
		</table>	
		<div style="margin-left: 300px;">
			<span id="spanButtonPlaceHolder"></span>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnCancel" type="hidden" value="" onclick="swfu.cancelQueue();" disabled="disabled" style="margin-left: 2px; height: 29px;" />
	     </div>
	</form>	
</div>