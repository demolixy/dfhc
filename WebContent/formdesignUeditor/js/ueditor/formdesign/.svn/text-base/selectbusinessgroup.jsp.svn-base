<%@page import="java.util.Map"%>
<%@page import="com.dfhc.ISystemConstant"%>
<%@page import="org.quickbundle.project.RmGlobalReference"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>文本框</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/dialogs/internal.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/jquery-1.7.2.min.js?2023"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/ueditor.config.js?2023"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/ueditor.all.js?2023"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/lang/zh-cn/zh-cn.js?2023"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/formdesignUeditor/js/ueditor/formdesign/leipi.formdesign.v4.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/data-template.js"></script>
<style type="text/css">
.popup {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 100;
}
.popup-back {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 101;
  background: #000;
  opacity: 0.3;
}
.popup-panel {
  position: relative;
  z-index: 1001;
  width: 800px;
  height: 500px;
  margin: 80px auto;
  background: #fff;
}
.tableHidBorder{
	
}
.tableHidBorder tr td{
	border: none;
}
</style>
</head>
<body>
<form action="" enctype="application/x-www-form-urlencoded"></form>
<div class="content">
    <table id="table1" class="table table-bordered table-striped table-hover">
     <tr>
        <th><span>选择业务组</span><span class="label label-important">*</span></th>
        <th><span>选择业务类型</span><span class="label label-important">*</span></th>
    </tr>
    <tr>
        <td>
        <select name="businessGroup">
        	<%
        		Map<String, Object> businessGroups = RmGlobalReference.get(ISystemConstant.DICTIONARY_BUSINESS_GROUP);
        		if(businessGroups != null && businessGroups.size() > 0){
        			for(Map.Entry<String, Object> entry : businessGroups.entrySet()){
        	%>	
        		<option value="<%=entry.getKey()%>"><%=entry.getValue() %></option>
        			
        	<% 
        			}
        		}
        	%>
        </select>
        
        </td>
        <td>
        <select name="businesType">
        	<%
        		Map<String, Object> businessTypes = RmGlobalReference.get(ISystemConstant.DICTIONARY_BUSINESS_TYPE);
        		if(businessTypes != null && businessTypes.size() > 0){
        			for(Map.Entry<String, Object> entry : businessTypes.entrySet()){
        	%>	
        		<option value="<%=entry.getKey()%>"><%=entry.getValue() %></option>
        			
        	<% 
        			}
        		}
        	%>
        </select>
        
        </td>
    </tr>

    </table> 
   
    
</div>
<script type="text/javascript">

dialog.onok = function (){
    var html = getTemplateHtml();
	//情况内容
    //editor.execCommand('cleardoc');
	//插入内容
	if(html){
	    editor.execCommand('insertHtml',html);
	}
};
function getTemplateHtml(){
	var businessGroup = $("select[name=businessGroup]").val();
	var businesType = $("select[name=businesType]").val();
	
	if(!businesType && !businessGroup){
		alert("请选择业务组或者业务类型！");
		return false;
	}
	
	var html = "";
	if(businesType){
		html += '<input type="hidden" name="businessType" leipiplugins="hidden" value="' + businesType +  '"/>';
	}
	if(businessGroup){
		html += '<input type="hidden" name="businessGroup" leipiplugins="hidden" value="' + businessGroup + '"/>';
	}
	return html;
}
</script>

</body>
</html>