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
	  var totalCode='';
		var setting = {
			async: {
			enable: true,
			url:"<%=request.getContextPath()%>/pubController/getMenuTree?cmd=roleData&is_href=1&reference=1&enableCookie=true&lazy_init=1",
			autoParam:["total_code"],
			otherParam:{},
			dataFilter: filter
		  },
		  callback: {
			  beforeClick: beforeClick,
			  beforeAsync: beforeAsync,
			  onAsyncError: onAsyncError,
			  onAsyncSuccess: onAsyncSuccess
			  }
		};
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
		
			var childNodes= childNodes.lcvo;
			if(childNodes.length==0){
				childNodes.name='无数据';
				}
			for (var i=0, l=childNodes.length; i<l; i++) {
				if(childNodes[i].total_code.length<7){
					childNodes[i].isParent=true;
					}
				childNodes[i].url='<%=request.getContextPath()%>/RmFunctionNodeAction.do?cmd=detail&id='+childNodes[i].id;
				childNodes[i].target='operation_frame';
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		function beforeClick(treeId, treeNode) {
			totalCode=treeNode.total_code;
			//alert(totalCode)
			if(totalCode.length<7){
				treeNode.isParent=true;
				}
			
			if (!treeNode.isParent) {
				alert("该节点为最后一级");
				return false;
			} else {
				return true;
			}
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

		function refreshNode(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			type = e.data.type,
			silent = e.data.silent,
			nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				alert("请先选择一个父节点");
			}
			for (var i=0, l=nodes.length; i<l; i++) {
				zTree.reAsyncChildNodes(nodes[i], type, silent);
				if (!silent) zTree.selectNode(nodes[i]);
			}
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
			$("#refreshNode").bind("click", {type:"refresh", silent:false}, refreshNode);
			$("#refreshNodeSilent").bind("click", {type:"refresh", silent:true}, refreshNode);
			$("#addNode").bind("click", {type:"add", silent:false}, refreshNode);
			$("#addNodeSilent").bind("click", {type:"add", silent:true}, refreshNode);
		});
		function qd(){//确认
		
	     	window.close();
		}
		//-->
	</SCRIPT>
</HEAD>

<BODY>
<div class="content_wrap" style="margin:0 auto; margin-top:30px;float: none;">
	<div class="zTreeDemoBackground left" style="margin:0 auto ;margin-top:30px; margin-left:15px; float: left;">
		<ul id="treeDemo" class="ztree" style="display:inline-block;*display:inline;margin:0 auto;width: 260px;" ></ul>
		<input type="button" value="确认" onclick="javascript:qd();"/><input type="button" value="取消" onclick="window.close()" />
	</div>
	<input name="party_id" id="party_id" type="hidden" value=""/>
	<input name="party_name" id="party_name" type="hidden" value=""/>
</div>
</BODY>
</HTML>