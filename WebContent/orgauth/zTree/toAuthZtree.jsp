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
		var defaultSelectedNodes = parent.document.form1.old_code_id.value;
		var codeArray=new Array(0);
		codeArray=defaultSelectedNodes.split(",");//初始化将系统之前选中的值赋给codeArray
		var  nameArray=new Array(0);
	    var totalCode='';
	    var demoMsg = {
				async:"正在进行异步加载，请等一会儿再点击...",
				expandAllOver: "全部展开完毕",
				asyncAllOver: "后台异步加载完毕",
				asyncAll: "已经异步加载完毕，不再重新加载",
				expandAll: "已经异步加载完毕，使用 expandAll 方法"
			};//全部加载
		var setting = {
		   check: {
		    enable: true
	        },
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
				debugger;
				if(defaultSelectedNodes.indexOf(childNodes[i].total_code)>-1){
					childNodes[i].checked=true;
				//	console.info(childNodes[i]);
					codeArray.push(childNodes[i].total_code);
					nameArray[childNodes[i].total_code]=childNodes[i].name;
					}
				
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
				
				
			}
			
			return childNodes;
		}
	
		var log, className = "dark";
// 		function beforeAsync(treeId, treeNode) {
// 			className = (className === "dark" ? "":"dark");
// 			showLog("[ "+getTime()+" beforeAsync ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
// 			return true;
// 		}
// 		function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
// 			showLog("[ "+getTime()+" onAsyncError ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
// 		}
// 		function onAsyncSuccess(event, treeId, treeNode, msg) {
// 			debugger
// 			showLog("[ "+getTime()+" onAsyncSuccess ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
			
// 		}
		
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


		
		function beforeClick(treeId, treeNode) {
			
			totalCode=treeNode.total_code;
			if(totalCode.length<7){
				treeNode.isParent=true;
				}
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				//展开当前节点 触发异步请求子节点的动作
				treeObj.expandNode(treeNode,true,true,true);
		}
		function onCheck(e, treeId, treeNode){
		
		    var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
		    nodes=treeObj.getCheckedNodes(true);
		    if(nodes.length==0){
			    codeArray=[];
			    nameArray=[];
		    }else{
		    	 codeArray=[];
		    	 nameArray=[];
                 for(var nlen=0;nlen<nodes.length;nlen++){
                	 codeArray.push(nodes[nlen].total_code);
                	 nameArray[nodes[nlen].total_code]=nodes[nlen].name;
                     }
			    }
			cancelHalf(treeNode);
			treeNode.checkedEx = true;

			}
		function cancelHalf(treeNode) {
			if (treeNode.checkedEx) return;
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			treeNode.halfCheck = false;
			zTree.updateNode(treeNode);			
		}
		function returnValueName() {  //获得所有选择的checkbox
			var submitObjectArray = new Array(0);
			var submitStringArray = new Array(0);
			debugger;
			submitStringArray= codeArray;
			for(var strLen=0;strLen<submitStringArray.length;strLen++){
				var tempObj = new Object();
				tempObj["childName"] = nameArray[submitStringArray[strLen]];
				tempObj["returnValue"] = submitStringArray[strLen];
				submitObjectArray[submitObjectArray.length] = tempObj
				}
		
       		return submitObjectArray;
		}
		
		
		
		//异步加载全部
	
		function beforeAsync() {
			curAsyncCount++;
		}
		
		function onAsyncSuccess(event, treeId, treeNode, msg) {
		
			if(!treeNode){
				treeNode=$.fn.zTree.getZTreeObj("treeDemo");
				  asyncNodes(treeNode.getNodes());
			}else{
				  asyncNodes(treeNode.children);
			}
		  
		
		
		}

		function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			curAsyncCount--;

			if (curAsyncCount <= 0) {
				curStatus = "";
				if (treeNode!=null) asyncForAll = true;
			}
		}

		var curStatus = "init", curAsyncCount = 0, asyncForAll = false,
		goAsync = false;
		
		
		function asyncNodes(nodes) {
			
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			
			for (var i=0, l=nodes.length; i<l; i++) {
				if (nodes[i].isParent && nodes[i].zAsync) {
					asyncNodes(nodes[i].children);
				} else {
					goAsync = true;
					zTree.reAsyncChildNodes(nodes[i], "refresh", true);
				}
			}
			
		    
		}

	

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
		
		
		});
		//-->
	</SCRIPT>
</HEAD>

<BODY>
<div class="content_wrap" style="margin:0 auto; float: none;">
	<div class="zTreeDemoBackground left" style="margin:0 auto ;margin-top:30px; margin-left:15px; float: left;">
		<ul id="treeDemo" class="ztree" style="display:inline-block;*display:inline;margin:0 auto;width: 260px;" ></ul>
<!--		<input type="button" value="确认" onclick="javascript:qd();"/><input type="button" value="取消" onclick="window.close()" />-->
	</div>
	<p class="highlight_red" id="demoMsg"></p>
</div>
</BODY>
</HTML>