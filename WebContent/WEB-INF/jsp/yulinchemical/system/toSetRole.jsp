
<%@page import="org.quickbundle.orgauth.cache.RmPartyTypeCache"%>
<%@page import="org.quickbundle.orgauth.IOrgauthConstants"%>
<%@page import="org.quickbundle.orgauth.rmuser.util.IRmUserConstants"%>
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
	
	//弹出组织机构
		function getParty(){
			
	    	 var url='${pageContext.request.contextPath}/pubController/getParty?cmd=department&is_allow_hanging_user=1&lazy_init=false&objKey=organization_id,organization_name&formName=form&source=orgFront';
	    	 $("#partyIframe").attr("src",url);
	    	 $(".ui_out_tan").show();
	    	 $("#portlet-config-query").show();
			
		}
	
	//选择组织机构并确认
	function selectOrgOk(){
		
		var party_id=$("#partyIframe").contents().find("#party_id").val();
		party_id=$.trim(party_id);
		if(party_id.length==0){
			alert("请选择对应的组织机构");
			return false;
		}
	
		var party_name=$("#partyIframe").contents().find("#party_name").val();
		$("#form").find("input[name='organization_id']").val(party_id);
		$("#form").find("input[name='organization_name']").val(party_name);
		$("#form").find("input[name='attribute1']").val(party_name);
		
		 $(".ui_out_tan").hide();
    	 $("#portlet-config-query").hide();
		
		
		
	}
	
	//用户角色设置
	function doSetUserRole(url){
		
		if(!confirm("确认提交该设置？")){
			
			return false;
		}
		form.action=url;
		form.submit();
		
	}
	


	</script>

	</head>
	


	<body>
	
  <!-- begin 组织机构 -->

     <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-query" style="top:5%;" >
      <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">组织机构选择</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
      <div class="modal-body" style="padding:0;">
      
        <iframe id="partyIframe" style="border:none;min-height:470px;width:100%;height: auto;max-height: 500px;" src="">
        
        </iframe>
   
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:selectOrgOk();">
       <i class="icon-ok"></i>
                         确认选择</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div>
     
     </div>

    
    <!-- end 组织机构 -->
	
	   <!-- begin  头部 -->
	   
	  <%@ include file="/jsp/include/web/jqGrid/header.jsp" %> 
	
		
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
							<li class="active">用户角色设置</li>
						</ul><!-- .breadcrumb -->

						
					</div>

					<div class="page-content">
					
					<form method="post" name="form" id="form">
					
					<input name="id" value="${party_id }" type="hidden"/>

		                 <div class="widget-box">
		                 
		                     <div class="widget-header">
		                      
		                      <h5>
		                                                                   用户管理	&gt;&gt; 分配角色 	&gt;&gt; 当前员工名称：<c:out value="${party_name }"></c:out>
		                      
		                      </h5>
		                      <div class="widget-toolbar">
		                      
		                  
		                          <a href="javascript:void(0)" title="用户角色设置" onclick="javascript:doSetUserRole('${pageContext.request.contextPath}/user/doSetUserRole')">
		                           
		                           <i class="icon-check" ></i>
		                         
		                         </a>
		                     
		                       
		                         <a href="${pageContext.request.contextPath }/user/listUserMana" title="返回">
		                         
		                           <i class="icon-share-alt"></i>
		                           
		                         </a>
		                      
		                      </div>
		                         
		                      
		                     </div>
		                     <!-- end  header -->
		                     
		                     <!-- begin  -->
		                     
		                     <div class="widget-body">
		                     
		                       <div class="widget-main widget-main clearfix">
		                       
		                       
		                          <!--begin 全局角色 -->
		                          
		                         
		                          
		                          <div class="role">
		                          
		                            <div class="role_title"> <span>全局角色 </span>  </div>
		                          
		                            <c:forEach var="systemLevelVo" items="${isSystemLevelList }">
		                            
		                              <div>
		                              
		                               <span>
		                               <input type="checkbox" name="role_name" value="${systemLevelVo.id }" />
		                               </span>
		                               <span>
		                               
		                               <c:out value="${systemLevelVo.name }"></c:out>
		                               (<c:out value="${systemLevelVo.role_code }"></c:out>)
		                               </span>
		                               
		                              
		                              </div>
		                            
		                            
		                            </c:forEach>
		                          
		                          </div>
		                          
		                          
		                          <!-- end 全局角色 -->
		                          
		                          <!-- begin 公司角色 -->
		                          
		                           <div class="role">
		                            
		                             <div class="role_title"> <span>公司角色 </span>  </div>
		                           
		                             <c:forEach items="${notSystemLevelList }" var="notSystemLevelVo">
		                             
		                             <div>
		                             
		                               <span>
		                                  <input name="role_name" type="checkbox" value="${notSystemLevelVo.id }" />
		                               </span>
		                               
		                               <span>
		                                <c:out value="${notSystemLevelVo.name }"></c:out>
		                                 (<c:out value="${notSystemLevelVo.role_code }"></c:out>)
		                               </span>
		                              </div>
		                             
		                             
		                             </c:forEach>
		                           
		                           
		                           
		                           
		                           </div>
		                          
		                          <!-- end 公司角色 -->
		                       
		                          
		                       
		                       </div>
		                     
		                     
		                     </div>
		                     
		                     <!-- end  -->
		                 
		                 
		                 </div>
		
		                   
 						<!-- end 详情 -->
 						
 						
 			    	</form>
						
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->
			
			
		

		
		</div><!-- /.main-container -->

     
     <script type="text/javascript">
     
     $(function(){
    
    	 
    	 var roleArr=new Array();
    	 
	     <c:forEach items="${roleListVo }" var="roleVo">
		      
	     roleArr['${roleVo.id}']='${roleVo.id}';
	     
		 </c:forEach>
		 
		 $("input[name='role_name']").each(function(){
			 
			 var thisChk=$(this).val();//获取checkBox的值
		     
			 var arrVal=roleArr[thisChk];
			
			 
			 if(arrVal){
				 
				 $(this).prop("checked",true);
				 return true;
				 
			 }
			 
			 
		 });
    	 
    	 
     });
     
     
     
     </script>
	


	</body>
</html>
