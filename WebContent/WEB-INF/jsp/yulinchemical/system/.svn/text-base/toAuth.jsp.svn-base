
<%@page import="org.quickbundle.orgauth.rmrole.vo.RmRoleVo"%>
<%@page import="org.quickbundle.orgauth.rmrole.util.IRmRoleConstants"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
	   <!-- 全局js 和css包 -->
	   <%@ include file="/jsp/include/web/jqGrid/global.jsp" %>
		<meta charset="utf-8" />
		<title><bean:message key="qb.web_title"/></title>

		<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />




	
	<script type="text/javascript">
	
	
	</script>

	</head>
	


	<body>

	<form name="form1" method="post">   
	   <!-- begin  头部 -->
	   
	  <%@ include file="/jsp/include/web/jqGrid/header.jsp" %> 
	  
	  <input type="hidden" name="old_code_id" value="${old_code_id }">
		<input type="hidden" value="" name="function_node_ids" id="function_node_ids">
	    <input type="hidden" value="" name="function_node_id_names" id="function_node_id_names">
	    <input type="hidden" value="${rmRoleVo.id }" name="role_id"> 
		
	   <!-- end 头部 -->

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				
				
                <!-- begin 左侧菜单 -->
			   
			   <%@ include file="/jsp/include/web/jqGrid/leftSide.jsp"%>
			   
			   

                 <!-- end 左侧菜单 -->
                 
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">系统管理</a>
							</li>
							
						</ul><!-- .breadcrumb -->

						
					</div>

					<div class="page-content">
<!-- 						<div class="page-header"> -->
<!-- 							<h1> -->
<!-- 								账户查询模块 -->
<!-- 								<small> -->
<!-- 									<i class="icon-double-angle-right"></i> -->
<!-- 									数据列表展示 -->
<!-- 								</small> -->
<!-- 							</h1> -->
<!-- 						</div>/.page-header -->

		                <!-- begin  详情 -->
		                
		                 <div class="widget-box">
		                 
		                     <div class="widget-header">
		                      
		                      <h5>角色授权</h5>
		                      <div class="widget-toolbar">
		          
		                         
		                       
		                         <a href="${pageContext.request.contextPath }/user/listRoles" title="返回">
		                         
		                           <i class="icon-share-alt"></i>
		                           
		                         </a>
		                      
		                      </div>
		                         
		                      
		                     </div>
		                     <!-- end  header -->
		                     
		                     <!-- begin  -->
		                     
		                     <div class="widget-body">
		                     
		                     
		                       <div class="widget-main">
		                       
		                        <div style="margin-left:80px;">
		                     
			                     <span>角色名称：</span><span style="color:red;margin-right:10px;">${rmRoleVo.name}</span>
			                     <span>团体类别：</span><span style="color:red">${partyTypeName }</span>
			                     
			                    </div>
		                       
		                        <div class="modal-body" style="padding:0;">
      
							        <iframe name="iframe_role_function_node_tree" id="iframe_role_function_node_tree" style="border:none;min-height:470px;width:100%;height: auto;max-height: 500px;" src="<%=request.getContextPath()%>/orgauth/zTree/toAuthZtree.jsp?submitType=submitAll&nodeRelationType=hasRelation&lazy_init=0&inputType=checkbox&enableCookie=false">
							        
							        </iframe>
							   
							     </div>
		                          
		                          <div class="EditTable operate_div" >
									       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:updateRoleFunctionNode();">
									       <i class="icon-ok"></i> 确认选择</a>
									       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:resetRoleFunctionNode();">
									         <i class="icon-remove"></i> 重置</a>
							      </div>
		                       </div>
		                     
		                     
		                     </div>
		                     
		                     <!-- end  -->
		                 
		                 
		                 </div>
		
		                   
 						<!-- end 详情 -->
 						
 						
 						
						
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

</form>
<script type="text/javascript">

	function updateRoleFunctionNode() {
		var submitObjectArray = window.frames["iframe_role_function_node_tree"].returnValueName();
		var alertStr = "";
		for(var i=0; i<submitObjectArray.length; i++) {
			if(alertStr != "") {
				alertStr += ",";
			}
			alertStr += submitObjectArray[i]["returnValue"];
		}
		$("#function_node_ids").val(alertStr);
	
		alertStr = "";
		for(var i=0; i<submitObjectArray.length; i++) {
			if(alertStr != "") {
				alertStr += ",";
			}
			alertStr += submitObjectArray[i]["childName"];
		}
		$("#function_node_id_names").val(alertStr);
		
		//alert(alertStr);
		form1.action="<%=request.getContextPath()%>/user/doAuthRole";
		form1.submit();
	}
	function resetRoleFunctionNode()
	{
		window.iframe_role_function_node_tree.location.reload();
	}

</script>
	</body>
</html>
