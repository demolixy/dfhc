<%@page import="java.util.Map"%>
<%@page import="com.dfhc.ISystemConstant"%>
<%@page import="org.quickbundle.project.RmGlobalReference"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>按钮</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
    <meta name="generator" content="www.leipi.org" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <!--[if lte IE 6]>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-ie6.css">
    <![endif]-->
    <!--[if lte IE 7]>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/ie.css">
    <![endif]-->
    <link rel="stylesheet" href="leipi.style.css">
    <script type="text/javascript" src="../dialogs/internal.js"></script>
    <script type="text/javascript">
/* Thank you by  
http://www.alt-tag.com/blog/2006/02/ie-dom-bugs/ */
function createElement(type, name)
{     
    var element = null;     
    try {        
        element = document.createElement('<'+type+' name="'+name+'">');     
    } catch (e) {}   
    if(element==null) {     
        element = document.createElement(type);     
        element.name = name;     
    } 
    return element;     
}
    </script>
</head>
<body>
<div class="content">
    <table class="table table-bordered table-striped table-hover">
     <tr>
        <th><span>控件值</span><span class="label label-important">*</span></th>
        <th><span>选择跳转路径</span> </th>
    </tr>
    <tr>
        <td><input type="text" id="orgname" placeholder="必填项"></td>
        <td>
        	 <select name="forwardUrl" id="forwardUrl">
        	 	<%
        	 		Map<String, Object> urls = RmGlobalReference.get(ISystemConstant.DICTIONARY_PROCESS_FORWARD_URL);
        	 		if(urls != null && urls.size() > 0){
        	 			for(Map.Entry<String, Object> entry : urls.entrySet()){
        	 	%>			
        	 		<option value="<%=entry.getValue()%>"><%=entry.getValue()%></option>		
        	 	<%			
        	 			}
        	 		}
        	 	
        	 	%>
        	 </select>
        
        </td>
    </tr>
   	<tr>
   		<th><span>控件name</span></th>
   		<th><span>控件类型</span></th>
   	</tr>
	<tr>	
		<td><input type="text" id="orgvalue"></td>
		<td>
			<select name="buttonTypes" id="buttonTypes">
				<option value="01">button</option>
				<option value="02">submit</option>
				<option value="03">a标签</option>
			</select>
		
		</td>
	</tr>
    </table>
    
    
</div>
<script type="text/javascript">
var oNode = null,thePlugins = 'button';
window.onload = function() {
    if( UE.plugins[thePlugins].editdom ){
        oNode = UE.plugins[thePlugins].editdom;
		var gValue = '';
		if(oNode.getAttribute('value'))
			gValue = oNode.getAttribute('value').replace(/&quot;/g,"\"");
		var gTitle=oNode.getAttribute('title').replace(/&quot;/g,"\""),gHidden=oNode.getAttribute('orghide'),gFontSize=oNode.getAttribute('orgfontsize'),gAlign=oNode.getAttribute('orgalign'),gWidth=oNode.getAttribute('orgwidth'),gHeight=oNode.getAttribute('orgheight'),gType=oNode.getAttribute('orgtype');
		gValue = gValue==null ? '' : gValue;
        gTitle = gTitle==null ? '' : gTitle;
		$G('orgvalue').value = gValue;
        $G('orgname').value = gTitle;
        if (gHidden == '1')
        {
            $G('orghide').checked = true;
        }
        $G('orgfontsize').value = gFontSize;
        $G('orgwidth').value = gWidth;
        $G('orgheight').value = gHeight;
        $G('orgalign').value = gAlign;
        $G('orgtype').value = gType;
    }
}
dialog.oncancel = function () {
    if( UE.plugins[thePlugins].editdom ) {
        delete UE.plugins[thePlugins].editdom;
    }
};
dialog.onok = function (){
    if($G('orgname').value==''){
        alert('请输入控件名称');
        return false;
    }
    var gValue=$G('orgvalue').value.replace(/\"/g,"&quot;"),gTitle=$G('orgname').value.replace(/\"/g,"&quot;");
    
    if( !oNode ) {
        try {
        	
        	var buttonType = document.getElementById("buttonTypes").value;
        	var forwardUrl = document.getElementById("forwardUrl").value;
        	var html = '<input type="hidden" name="' + gValue + 'forward" value="' + forwardUrl + '"/>';
        	if(buttonType == '01'){
        		html += '<input type="button" name="' + gValue + '" value="' + gTitle + '"/>';
        	}else if(buttonType == '02'){//submit
        		html += '<input type="submit" name="' + gValue + '" value="' + gTitle + '"/>';
        	}else if(buttonType == '03'){
        		html += '<a href="javascript:void(0);">' + gValue + '</a>';
        	}
            //oNode.setAttribute('style',style );
            //oNode.style.cssText=style;//ie7
            editor.execCommand('insertHtml',html);
        } catch (e) {
            try {
                editor.execCommand('error');
            } catch ( e ) {
                alert('控件异常，请到 [雷劈网] 反馈或寻求帮助！');
            }
            return false;
        }
    } else {
        
        oNode.setAttribute('title', gTitle);
        oNode.setAttribute('value', $G('orgvalue').value);
        if( $G('orghide').checked ) {
            oNode.setAttribute('orghide', 1);
        } else {
            oNode.setAttribute('orghide', 0);
        }
        if( gFontSize != '' ) {
            oNode.style.fontSize = gFontSize+ 'px';
            oNode.setAttribute('orgfontsize',gFontSize );
        }else{
            oNode.style.fontSize = '';
            oNode.setAttribute('orgfontsize', '');
        }
        if( gAlign != '' ) {
            oNode.style.textAlign = gAlign;
            oNode.setAttribute('orgalign',gAlign );
        }else{
            oNode.setAttribute('orgalign', '');
        }
        if( gWidth != '' ) {
            oNode.style.width = gWidth+ 'px';
            oNode.setAttribute('orgwidth',gWidth );
        }else{
            oNode.style.width = '';
            oNode.setAttribute('orgwidth', '');
        }
        if( gHeight != '' ) {
            oNode.style.height = gHeight+ 'px';
            oNode.setAttribute('orgheight',gHeight );
        }else{
            oNode.style.height = '';
            oNode.setAttribute('orgheight', '');
        }
        if( gType != '' ) {
            oNode.setAttribute('orgtype',gType );
        }else{
            oNode.setAttribute('orgtype', '');
        }
        delete UE.plugins[thePlugins].editdom;
    }
};
</script>
</body>
</html>