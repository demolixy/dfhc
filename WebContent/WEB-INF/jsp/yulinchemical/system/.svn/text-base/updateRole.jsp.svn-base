
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
			
	    	 var url='${pageContext.request.contextPath}/pubController/getParty?cmd=department&is_allow_hanging_role=1&lazy_init=false&objKey=organization_id,organization_name&formName=form&source=orgFront';
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
		$("#form").find("input[name='owner_org_id']").val(party_id);
		$("#form").find("input[name='owner_org_id_name']").val(party_name);
		
		 $(".ui_out_tan").hide();
    	 $("#portlet-config-query").hide();
		
		
		
	}
	
	//ajax验证
	$.ajaxSetup({async:false,type:'POST'});
	function validateRoleCode(){
		
		var sValue = true;
		
		$(".notNullInp").each(function(){
			
			var inp=$(this).val();
			inp=$.trim(inp);
			if(inp.length==0){
				alert("请填入正确的数据");
				sValue=false;
				return false;
			}
			
		})
		if(!sValue){
			return sValue;
		}

		$.getJSON("<%=request.getContextPath()%>/RmRoleAction.do?cmd=validateRoleCode", 
			{
			    role_code:$("#role_code").val(),
				id:$("#role_id").val()
			}, 
			function(data){
				if(data == '1') {
					alert("角色编码已被使用，请重新输入！");
					sValue = false;
				}else{
					sValue = true;
				}
			});
		return sValue;
	}
	

	//保存更新角色
	function update(url){
		
		debugger;
		if(!confirm("是否确认提交填写的数据")){
			
			return false;
		}
		
		if(!validateRoleCode()){
			return false;
		}
		
	 $.ajax({
		 
		 url:url,
		 type:"post",
		 dataType:"json",
		 data:$(form).serialize(),
		 async:false,
		 success:function(data,status){
			 
			 alert(data.msg);
			 window.location.href="${pageContext.request.contextPath}/user/listRoles";
			 
			 
		 },
		 error:function(xhr,s1,s2){
			 var message = xhr.responseText;
				eval("var json = "+message);
				if($.trim(json.error).length==0){
					alert('数据异常,请刷新页面重试');
				}else{
					alert(json.error);
				}
		 }
		 
		 
	 });
	 
		
		
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
							<li class="active">角色修改</li>
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
		                      
		                      <h5>角色修改</h5>
		                      <div class="widget-toolbar">
		                      
		                      <c:choose>
		                      
		                        <c:when test="${updateInsertFlag=='insert' }">
		                        
		                          <a href="javascript:void(0)" title="新增" onclick="javascript:update('${pageContext.request.contextPath}/user/insertRole')">
		                           
		                           <i class="icon-check" ></i>
		                         
		                         </a>
		                        
		                        
		                        </c:when>
		                        <c:otherwise>
		                        
		                          <a href="javascript:void(0)" title="更新数据" onclick="javascript:update('${pageContext.request.contextPath}/user/updateRole')">
		                           
		                           <i class="icon-check" ></i>
		                         
		                         </a>
		                        </c:otherwise>
		                      
		                      
		                      </c:choose>
		                      
		                       
		                         
		                       
		                         <a href="${pageContext.request.contextPath }/user/listRoles" title="返回">
		                         
		                           <i class="icon-share-alt"></i>
		                           
		                         </a>
		                      
		                      </div>
		                         
		                      
		                     </div>
		                     <!-- end  header -->
		                     
		                     <!-- begin  -->
		                     
		                     <div class="widget-body">
		                     
		                       <div class="widget-main">
		                       
		                       <form name="form" method="post" id="form">
		                         <input type="hidden" name="id" value="${bean.id }"></input>
		                         <input type="hidden" id="role_id" name="role_id" value="${bean.id }"/>
		                         <table  class="table table-striped table-bordered table-hover detail-tab">
		                         
			                         <tbody>
			                         
			                            <c:choose>
		                       
					                        <c:when test="${empty bean && updateInsertFlag=='update' }">
					                        
					                        <tr><td style="color:red">数据异常没找到对应的数据</td></tr>
					                        
					                        </c:when>
					                        
					                        <c:otherwise>
					                        
					                        <tr>
					                        
					                          <th><i class="notNull">*</i>&nbsp;<%=IRmRoleConstants.TABLE_COLUMN_CHINESE.get("name")%>：</th>
					                          <td><input  class="m-wrap tab-inp notNullInp" name="name" value="${bean.name }"/></td>
					                          <th><i class="notNull">*</i>&nbsp;<%=IRmRoleConstants.TABLE_COLUMN_CHINESE.get("role_code")%></th>
					                          <td><input  class="m-wrap tab-inp notNullInp" name="role_code" id="role_code" value="${bean.role_code }"/></td>
					                          <th><i class="notNull">*</i>&nbsp;<%=IRmRoleConstants.TABLE_COLUMN_CHINESE.get("enable_status")%></th>
					                          <td>
					                              <select class="tab-select" name="enable_status" >
						                              <c:forEach items="${statusMap}" var="listStatus"> 
						                             
														 <option value="${listStatus.key}">${listStatus.value}</option>
														
													  </c:forEach> 	
						                              
					                              </select>
					                          
					                       
					                          
					                          </td>
					                        </tr>
					                         <tr>
					                        
					                          <th><i class="notNull">*</i>&nbsp;<%=IRmRoleConstants.TABLE_COLUMN_CHINESE.get("is_system_level")%>：</th>
					                          <td>
					                            
					                            <select class="tab-select" name="is_system_level">
					                           
					                             <c:forEach items="${levelMap}" var="listStatus"> 
						                             
						                                 <option value="${listStatus.key}">${listStatus.value} </option>
						                              
											      </c:forEach> 
					                            
					                            </select>
	                          
					                          <th>
					                          <span id="org_title">
					                           <%=IRmRoleConstants.TABLE_COLUMN_CHINESE.get("owner_org_id")%>
					                          </span>
					                          
					                           
					                           </th>
					                          <td colspan="3">
					                          
					                          <div id="org_div">
					                            <span>
					                               <input type="text" readonly  hiddenInputId="owner_org_id" name="owner_org_id_name" value="${bean.owner_org_id_name }" />
					                            </span>
					                           	
			                               	   <input type="hidden" value="${bean.owner_org_id }" name="owner_org_id"></input>
					                              <span class="choose_ref" onClick="getParty()">选择</span>
					                          
					                          </div>
					                          
					                          </td>
					                       
					                        </tr>
					                        <tr>
					                        <th><%=IRmRoleConstants.TABLE_COLUMN_CHINESE.get("description")%></th>
					                        <td colspan="5">
					                        <textarea class="tab-textarea"></textarea>
					                        </td>
					                        
					                        </tr>
					                        
					                        
					                        
					                        </c:otherwise>
				                       
				                      	 </c:choose>
			                           
			                           
			                         </tbody>
		                         
		                         </table>
		                       </form>
		                       
		                       </div>
		                     
		                     
		                     </div>
		                     
		                     <!-- end  -->
		                 
		                 
		                 </div>
		
		                   
 						<!-- end 详情 -->
 						
 						
 						
						
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

	
	<script type="text/javascript">
	
	$(function(){
		
		<c:if test="${updateInsertFlag=='update'}">
		
	
	
		var enableStatus='${bean.enable_status}';
		
		$("select[name='enable_status']").find("option").each(function(){
			
			var valOpt=$(this).val();
			if(valOpt==enableStatus){
				
				$(this).prop("selected","selected");
				
				return false;
			}

			
		});
	   var level='${bean.is_system_level}';
	     
	     $("select[name='is_system_level']").find("option").each(function(){
	    	 
	    	 var valOpt=$(this).val();
	    	 
	    	 if(level=='1'){
	    		 $("#org_title").hide();
	    		 $("#org_div").hide();
	    	 }
	    	 
	    	 if(valOpt==level){
	    		
	    		$(this).prop("selected","selected");
	    		
	    		return false;
	    		
	    	 }
	    	 
	     });
	     
	 	</c:if>
	 	
	     $("select[name='is_system_level']").on("click",function(){
	    	 
	    	var chkVal= $(this).val();
	    	if(chkVal=='1'){
	    		 $("#org_title").hide();
	    		 $("#org_div").hide();
	    	 }else{
	    		 $("#org_title").show();
	    		 $("#org_div").show();
	    	 }
	    	 
	    	 
	     })
	     

		
	});
	
	
	
	</script>

	</body>
</html>
