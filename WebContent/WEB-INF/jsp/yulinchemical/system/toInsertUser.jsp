
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
							<li class="active">用户新增</li>
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
		                      
		                      <h5>
		                      
		                      <c:choose>
		                      
		                        <c:when test="${updateInsertFlag=='insert' }">
		                            
		                                                                                    用户新增
		                        
		                        </c:when>
		                        <c:otherwise>
		                        
		                 		       用户修改
		                        
		                        </c:otherwise>
		                      
		                      
		                      </c:choose>
		                      
		                      </h5>
		                      <div class="widget-toolbar">
		                      
		                      <c:choose>
		                      
		                        <c:when test="${updateInsertFlag=='insert' }">
		                        
		                          <a href="javascript:void(0)" title="新增" onclick="javascript:updateInsert('${pageContext.request.contextPath}/user/doInsertUser','insert')">
		                           
		                           <i class="icon-check" ></i>
		                         
		                         </a>
		                        
		                        
		                        </c:when>
		                        <c:otherwise>
		                        
		                          <a href="javascript:void(0)" title="更新数据" onclick="javascript:updateInsert('${pageContext.request.contextPath}/user/doUpdateUser','update')">
		                           
		                           <i class="icon-check" ></i>
		                         
		                         </a>
		                        </c:otherwise>
		                      
		                      
		                      </c:choose>
		                      
		                       
		                         
		                       
		                         <a href="${pageContext.request.contextPath }/user/listUserMana" title="返回">
		                         
		                           <i class="icon-share-alt"></i>
		                           
		                         </a>
		                      
		                      </div>
		                         
		                      
		                     </div>
		                     <!-- end  header -->
		                     
		                     <!-- begin  -->
		                     
		                     <div class="widget-body">
		                     
		                       <div class="widget-main">
		                       
		                       <form name="form" method="post" id="form">
		                       
		                       
								<input type="hidden" name="view_id" value="<%=IOrgauthConstants.PartyView.DEFAULT.id() %>" id="view_id"></input>
								<input type="hidden" name="isInherit" value="0"></input>
								<input type="hidden" name="oldName" value="${bean.name }"></input>
								<input type="hidden" name="attribute1" value="${bean.attribute1 }"></input>
								<input type="hidden" name="party_type_id"  value="<%=IOrgauthConstants.Config.isUserRelationParty.value() && RmPartyTypeCache.getPartyType(IRmUserConstants.TABLE_NAME) != null ? RmPartyTypeCache.getPartyType(IRmUserConstants.TABLE_NAME).getId() : ""%>"/>
							
								<input type="hidden"  name="user_id" value="${bean.id }" id="user_id"></input>
		                        <input type="hidden" name="id" value="${bean.id }"></input>
		                        
		                        <input type="hidden" name="password" id="password">
		                         
		                         <table  class="table table-striped table-bordered table-hover detail-tab">
		                         
			                         <tbody>
			                         
			                            <c:choose>
		                       
					                        <c:when test="${empty bean && updateInsertFlag=='update' }">
					                        
					                        <tr><td style="color:red">数据异常没找到对应的数据</td></tr>
					                        
					                        </c:when>
					                        
					                        <c:otherwise>
					                        
					                        <tr>
					                        
					                          <th><i class="notNull">*</i>&nbsp;<%=IRmUserConstants.TABLE_COLUMN_CHINESE.get("name")%>：</th>
					                          <td><input  class="m-wrap tab-inp notNullInp" name="name" value="${bean.name }"/></td>
					                          
					                          <th><i class="notNull">*</i>&nbsp;<%=IRmUserConstants.TABLE_COLUMN_CHINESE.get("login_id")%></th>
					                          <td><input  class="m-wrap tab-inp notNullInp" name="login_id" id="login_id" value="${bean.login_id }"/></td>
					                         
					                          <th><i class="notNull">*</i>&nbsp;<%=IRmUserConstants.TABLE_COLUMN_CHINESE.get("lock_status")%></th>
					                          <td>
					                              <select class="tab-select" name="lock_status" >
					                              
					                                 <option value="">--选择用户状态--</option>
						                              <c:forEach items="${lockStatusMap}" var="listStatus"> 
						                             
														 <option value="${listStatus.key}">${listStatus.value}</option>
														
													  </c:forEach> 	
						                              
					                              </select>
					                          
					                       
					                          
					                          </td>
					                        </tr>
					                         <tr>
					                        
					                          <th><i class="notNull">*</i>&nbsp;用户权限类型：</th>
					                          <td>
					                            
					                            <select class="tab-select" name="admin_type">
					                            
					                            <option value="">--请选择用户权限类型-</option>
					                           
					                             <c:forEach items="${adminTypeMap}" var="listStatus"> 
						                             
						                                 <option value="${listStatus.key}">${listStatus.value} </option>
						                              
											      </c:forEach> 
					                            
					                            </select>
					                            
					                            </td>
					                            
					                          <th><i class="notNull">*</i>&nbsp;<%=IRmUserConstants.TABLE_COLUMN_CHINESE.get("email")%>：</th>
					                          <td ><input  class="m-wrap tab-inp notNullInp" name="email" value="${bean.email }"/></td>
					                          
					                          
					                           <th><i class="notNull">*</i>&nbsp;用户类型：</th>
					                          <td>
					                            
					                            <select class="tab-select" name="user_type">
					                            
					                            <option value="">--请选择对应的用户类型--</option>
					                           
					                             <c:forEach items="${userTypeMap}" var="listStatus"> 
						                             
						                                 <option value="${listStatus.key}">${listStatus.value} </option>
						                              
											      </c:forEach> 
					                            
					                            </select>
					                            
					                            </td>
					                            
					                         
					                  
					                        </tr>
					                        
					                        <tr>
					                        
					                         <th><i class="notNull">*</i>&nbsp;设置密码：</th>
					                         <td><input  class="m-wrap tab-inp notNullInp" type="password" name="pwd1" value="" onblur="changePwd()"/></td>
					                        
					                        <th><i class="notNull">*</i>&nbsp;确认密码：</th>
					                         
					                        <td colspan="3"><input  class="m-wrap tab-inp notNullInp" type="password" name="pwd2" value=""  onchange="confirmPwd()"/></td>
					                      
					                        </tr>
					                        
					                        <tr>
					                        
					                         <th>
						                          <span id="org_title">
						                           <%=IRmRoleConstants.TABLE_COLUMN_CHINESE.get("owner_org_id")%>
						                          </span>
					                           </th>
					                           
					                           
					                          <td colspan="5">
					                          
					                          <div id="org_div">
					                            <span>
					                               <input type="text" readonly   name="organization_name" value="${bean.attribute1 }" />
					                            </span>
					                           	
			                               	   <input type="hidden" value="${bean.organization_id }" name="organization_id"></input>
					                              <span class="choose_ref" onClick="getParty()">选择</span>
					                          
					                          </div>
					                          
					                          </td>
					                        
					                        </tr>
					                        
					                        
					                        
					                        <tr>
					                        <th><%=IRmRoleConstants.TABLE_COLUMN_CHINESE.get("description")%></th>
					                        <td colspan="5">
					                        <textarea class="tab-textarea" name="description" value="${bean.description }"></textarea>
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
		
	
	
		var enableStatus='${bean.lock_status}';
		
		$("select[name='lock_status']").find("option").each(function(){
			
			var valOpt=$(this).val();
			if(valOpt==enableStatus){
				
				$(this).prop("selected","selected");
				
				return false;
			}

			
		});
	   var level='${bean.admin_type}';
	     
	     $("select[name='admin_type']").find("option").each(function(){
	    	 
	    	 var valOpt=$(this).val();
	    	
	    	 if(valOpt==level){
	    		
	    		$(this).prop("selected","selected");
	    		
	    		return false;
	    		
	    	 }
	    	 
	     });
	     
	     
      var userType='${bean.user_type}';
	     
	     $("select[name='user_type']").find("option").each(function(){
	    	 
	    	 var valOpt=$(this).val();
	    	
	    	 if(valOpt==userType){
	    		
	    		$(this).prop("selected","selected");
	    		
	    		return false;
	    		
	    	 }
	    	 
	     });
	     
	 	</c:if>
	
	     

		
	});
	
	//新增用户
	function updateInsert(url,source){
		
		var flag=validForm(source);//验证表单数据
		if(!flag){
			return flag;
		}
		
		subData(url,$("#form").serialize(),'${pageContext.request.contextPath }/user/listUserMana');
		
		
		
	}
	
	
	
	
	function subData(url,param,successUrl){
		
		 $.ajax({
			    type:'post',//可选get
			    url:url,
			    data:param,//送评表的表格id属性值
			    dataType:'json',
			    async:false , //false为同步
			    success:function(data,status){
			    	
			    	alert(data.msg);
			    	window.location.href=successUrl;
				  
			   },
			   error:function(xhr,s1,s2){
			    	var  message= xhr.responseText;
			    	var  json = jQuery.parseJSON(message);

					if($.trim(json.exception).length==0){
						alert('数据异常,请刷新页面重试');
					}else{
						alert(json.exception);
					}
					
			     }
			   });
	}
	
	//验证form 数据
	function  validForm(sourceFlag){
		
		
		var flag=true;
		$(".notNullInp").each(function(){
			
			var  inpVal=$(this).val();
			
			inpVal=$.trim(inpVal);
			
			if(inpVal.length==0){
				
				flag=false;
				alert("标志位必填的数据项必须填写数据!");
				return false;
				
			}
			var nameAttr=$(this).attr("name");
			if(nameAttr=='email'){
				
				flag=isEmail(inpVal);//验证邮箱
			}
			$(this).val(inpVal);
			
		});
		
		if(!flag){
			return false;
		}
		
		
		$("select").each(function(){
			
			var selectedVal=$(this).find("option:selected").val();
			if(selectedVal.length==0){
				
				var msg=$(this).parents("td").prev().text();
				alert(msg+"：为必选项！");
				flag=false;
				return false;
			}
			
		});
		
		
		if(!flag){
			return false;
		}
		
		flag=confirmPwd();//验证两次密码是不是一致
		
		if(!flag){
			return flag;
		}
		
		var pwd=$("#form").find("input[name='pwd1']").val();
		
		$("#form").find("input[name='password']").val($.md5(pwd));//加密数据
		
		return flag;
		
	}
	
	
	
	
	//一次输入密码
	function changePwd(){
	
		
		var pwd=$("#form").find("input[name='pwd1']").val();
	
		pwd=$.trim(pwd);
		
		if(pwd.length==0){
			
			alert("用户密码不能为空");
			return  false;
		}
		$("#form").find("input[name='pwd1']").val(pwd);
		
		
		
		
	}
	
	//确认密码框输入
	function confirmPwd(){
		
		var pwd=$("#form").find("input[name='pwd1']").val();
		
		pwd=$.trim(pwd);
		
		var againPwd=$("#form").find("input[name='pwd2']").val();
		
		againPwd=$.trim(againPwd);
		
		if(againPwd.length==0){
			alert("请填写确认密码！");
		
			return false;
		}
		
		if(pwd!=againPwd){
			alert("两次输入的密码不一致，请重新输入！");
			$("#form").find("input[name='pwd1']").val("");
			$("#form").find("input[name='pwd2']").val("");
			return false;
		}
		
		return true;
	
		
	}
	
	
	</script>

	</body>
</html>
