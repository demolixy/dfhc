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
	
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				
				
				onCheck:onClick
			},
			 check: {    
				 chkStyle :"radio",
				 radioType :"all",
                 enable: true  
             } 
		};

		var zNodes =[
		    <c:forEach items="${beans}" var="bean" varStatus="status">
			{ id:"${bean.child_party_id}", pId:"${bean.parent_party_id}", name:"${bean.child_party_name}", open:true}
			,
			</c:forEach>
		];
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
		function onClick(event, treeId, treeNode, clickFlag) {
	
			var party_id=treeNode.id;
			$('#party_id').val(party_id);
			var party_name=treeNode.name;
			$('#party_name').val(party_name);
			
		}
		
	
	
		//-->
	</SCRIPT>
</HEAD>

<BODY>
<div class="content_wrap" style="margin:0 auto; margin-top:20px;float: none;min-height: 400px;height: auto;padding-bottom: 20px;">
  
	<div class="zTreeDemoBackground left" style="margin:0 auto ;margin-top:10px; margin-left:15px; float: left;">
		<ul id="treeDemo" class="ztree" style="display:inline-block;*display:inline;margin:0 auto;width: 350px;" ></ul>
		
	</div>
	<input name="party_id" type="hidden"id="party_id" value=""/>
	<input name="party_name" type="hidden"id="party_name" value=""/>
	
</div>
</BODY>
</HTML>