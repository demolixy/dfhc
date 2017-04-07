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
		 window.onfocus = function focus(){//重写父窗口window对象中的onfocus，使其在获得焦点时执行以下代码   
			  if( typeof(window.childWindow)!="undefined"){//如果子窗口存在，将焦点转到子窗口   
			   window.childWindow.focus();   
			  }   
		 }  
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick,
				onDblClick: zTreeOnDblClick				
			}
		};

		var zNodes =[
		    <c:forEach items="${beans}" var="bean" varStatus="status">
			{ id:"${bean.child_party_id}", pId:"${bean.parent_party_id}", name:"${bean.child_party_name}",
				child_party_code:"${bean.child_party_code}",url:"<%=request.getContextPath()%>/orgauth/tree/manageParty.jsp?view_id=${view_id}&amp;child_party_code=${bean.child_party_code}&amp;party_id=", target:"right_org_name", open:false}
			,
			</c:forEach>
		];
		
		$(document).ready(function(){

			window.focus();
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
		var array = new Array();
		var myObjKeyValue = new Array(5);
		function onClick(event, treeId, treeNode, clickFlag) {
			var party_id=treeNode.id;
			$('#party_id').val(party_id);
			var party_name=treeNode.name;
			$('#party_name').val(party_name);
			var child_party_code=treeNode.child_party_code;
			$('#child_party_code').val(child_party_code);
			myObjKeyValue[0]=party_id;
			myObjKeyValue[1]=party_name;
		}
		
		function zTreeOnDblClick(event, treeId, treeNode) {
			var party_id=treeNode.id;
			$('#party_id').val(party_id);
			var party_name=treeNode.name;
			$('#party_name').val(party_name);
			var child_party_code=treeNode.child_party_code;
			$('#child_party_code').val(child_party_code);
			myObjKeyValue[0]=party_id;
			myObjKeyValue[1]=party_name;
		};
		function qd(){//确认
			var objKey='${objKey}';//获取需要回写的字段
			var  fromName='${fromName}';//要回写的form的name属性所对应的值
			var arr="";
			if(objKey){
				objKey.split(",");//将以，分割的字符转换为数组
				}
				
	     	//$("#"+fromName+" input[name='"+arr[0]+"']").val($('#party_id').val());
	     	//$("#"+fromName+" input[name='"+arr[1]+"']").val($('#party_name').val());
	     
	    	var pa=window.opener.document;
	    //	alert(pa)
	     	for(var i=0;i<arr.length;i++){
		     	//alert(arr[i]);
	     		$(pa).find("form[name='"+fromName+"']").find("input[name='"+arr[i]+"']").val(myObjKeyValue[i]);
		     	}
	     	window.close();
		}
		//-->
	</SCRIPT>
</HEAD>

<BODY>
<div class="content_wrap" margin-top:30px;float: none;">
	<div class="zTreeDemoBackground left" style="margin:0 auto ;margin-top:30px; float: left;">
		<ul id="treeDemo" class="ztree" style="display:inline-block;*display:inline;margin:0 auto;width: 260px;margin-left: 15px;" ></ul>
		<input type="button" value="确认" onclick="javascript:qd();"/><input type="button" value="取消" onclick="window.close()" />
	</div>
	<input name="party_id" id="party_id" type="hidden" value=""/>
	<input name="party_name" id="party_name" type="hidden" value=""/>
	<input name="child_party_code" id="child_party_code" type="hidden" value=""/>
</div>
</BODY>
</HTML>