<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-layout.tld" prefix="layout" %>
<!DOCTYPE html>
<HTML>
<HEAD>
	<TITLE>组织树</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="x-ua-compatible" content="ie=8" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/ztree/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.excheck-3.5.js"></script>

	<!--
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.exedit-3.5.js"></script>
	-->
<SCRIPT type="text/javascript">
		<!--
		//debugger;
		var defaultSelectedNodes = '';
		var codeArray=new Array(0);
		codeArray=defaultSelectedNodes.split(",");//初始化将系统之前选中的值赋给codeArray
		var  nameArray=new Array(0);
	  var totalCode='';
		var setting = {
		   
			async: {
			enable: true,
			url:"<%=request.getContextPath()%>/pubController/getMenuTree?cmd=roleData&is_href=1&reference=1&enableCookie=true&lazy_init=1&authorityMenuTree=true",
			autoParam:["total_code"],
			otherParam:{},
			dataFilter: filter
		  },
		  callback: {
			  beforeClick: beforeClick,
			  beforeAsync: beforeAsync,
			  onAsyncError: onAsyncError,
			  onAsyncSuccess: onAsyncSuccess,
			  beforeCheck: beforeClick,
			  onCheck: onCheck  
			  }
		};
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			var childNodes= childNodes.lcvo;
			for (var i=0, l=childNodes.length; i<l; i++) {
				if(childNodes[i].total_code.length<7){
					childNodes[i].isParent=true;
					}else{
						childNodes[i].isParent=false;
					}
				if(childNodes[i].data_value){
					childNodes[i].url="<%=request.getContextPath()%>"+childNodes[i].data_value;
					childNodes[i].target="contentFrame";
					}
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
	
		var log, className = "dark";
		function beforeAsync(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeAsync ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
			return true;
		}
		function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			showLog("[ "+getTime()+" onAsyncError ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
		}
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			showLog("[ "+getTime()+" onAsyncSuccess ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
			
		}
		
		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 8) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}


		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
		});
		function beforeClick(treeId, treeNode) {
			
			totalCode=treeNode.total_code;
			if(totalCode.length<4){
				treeNode.isParent=true;
				}
		}
		function onCheck(e, treeId, treeNode){
		
		  
			cancelHalf(treeNode)
			treeNode.checkedEx = true;

			}
		function cancelHalf(treeNode) {
			if (treeNode.checkedEx) return;
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			treeNode.halfCheck = false;
			zTree.updateNode(treeNode);			
		}
	
		//-->
	</SCRIPT>
</HEAD>

<BODY>
<div width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<div class="zTreeDemoBackground left" style="margin:0 auto ;margin-top:30px; margin-left:15px; float: left;">
		<ul id="treeDemo" class="ztree" style="display:inline-block;*display:inline;margin:0 auto;width: 260px;" ></ul>
<!--		<input type="button" value="确认" onclick="javascript:qd();"/><input type="button" value="取消" onclick="window.close()" />-->
	</div>
</div>
</BODY>
</HTML>