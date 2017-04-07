<%@page import="com.dfhc.bus.msg.IMsgConstants" %>

<%@page import="org.quickbundle.tools.helper.RmJspHelper" %>
<%@page import="org.quickbundle.project.RmGlobalReference" %>
<%@page import="com.dfhc.ISystemConstant" %>
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

<script src="<%=request.getContextPath()%>/js/jqGrid/json2.js"></script>
<script type="text/javascript">
//保存 提交
function doSaveOrCommit(isSaveOrCommit){
	//表单验证通过
	if(doSaveOrCommitCheckForm()){
		
		if(!confirm("确认发送?")){
			return false;
		}

		$("#save").attr("disabled","disabled");
		
		$.ajax({
			    type:'post',//可选get
			    url:"${pageContext.request.contextPath}/msg/addMsg",
			    data:$(zongForm).serialize(),
			    dataType:'json',
			    async:false , //false为同步
			    success:function(data,status){
				  if(data.status=="0"){
	    	   	     //将按钮设置为可用
	       			 $("#save").removeAttr("disabled");
					  alert(data.message);
			   	   }else{
			   	     	alert(data.message);
	                    window.location.href="${pageContext.request.contextPath}/msg";
			   	   }
			   },
			   error:function(xhr,s1,s2){
			    	var message = xhr.responseText;
					eval("var json = "+message);
	    	   	     //将按钮设置为可用
	       			 $("#save").removeAttr("disabled");
					if($.trim(json.error).length==0){
						alert('数据异常,请刷新页面重试');
					}else{
						alert(json.error);
					}
			   }
		});
	}
}

//保存提交验证主表信息
function doSaveOrCommitCheckForm(){
	var customerIds=$("#zongForm").find("input[name=customerIds]").val();
	var logisticsCompanyIds=$("#zongForm").find("input[name=logisticsCompanyIds]").val();
	if($.trim(customerIds).length == 0  &&  $.trim(logisticsCompanyIds).length == 0){
		alert("选择人员");
		return  false;
	}
	var title=$("#zongForm").find("input[name=title]").val();
	if($.trim(title).length == 0){
		alert("填写标题");
		return  false;
	}

	var content=$("#zongForm").find("textarea[name=content]").val();
	if($.trim(content).length == 0){
		alert("填写内容");
		return  false;
	}

	return true;
}

</script>
</head>
<body>
	
	

	
	   <!-- begin  头部 -->
	   
	  <%@ include file="/jsp/include/web/jqGrid/header.jsp" %> 
	
		
	   <!-- end 头部 -->

		<div class="main-container" id="main-container">
			<script type="text/javascript">
                      	     try{ ace.settings.check('main-container' , 'fixed')
                             } catch(e){
                             }
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
							try{
							  ace.settings.check('breadcrumbs' , 'fixed')
							}catch(e){
                                                           }
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>
									<li>
										<a href="#">通知管理</a>
									</li>
									<li class="active">通知管理</li>
								
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">

 <!-- begin  详情 -->
		                
		                 <div class="widget-box">
		                 
		                     <div class="widget-header">
		                      
		                      <h5>消息详情</h5>
		                      <div class="widget-toolbar">
		                      
		                         <a href="javascript:history.go(-1);" title="返回">
		                         
		                           <i class="icon-share-alt"></i>
		                           
		                         </a>
		                      
		                      </div>
		                         
		                      
		                     </div>
		                     <!-- end  header -->
		          <form name="zongForm" id="zongForm"  method="post"  >
		                     
		                     <div class="widget-body">
		                     
		                       <div class="widget-main">
		                       
		                         <table  class="table table-striped table-bordered table-hover detail-tab">
		                         
			                         <tbody>
			                             <input type="hidden" class="tab-inp" name="id" value="${id}"/>
				                      <tr>
				                             <th >客户:</th>
				                             <td colspan="2">  <input type="text" class="tab-inp" name="customerNames" />  <input type="hidden" class="tab-inp" name="customerIds" /> 
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/customer/reference?referenceInputType=check','zongForm.customer_id,zongForm.customerNames,zongForm.customerIds');">选择</span>
				                             </td>
				                             <th >物流公司:</th>
				                             <td colspan="2"  > <input type="text" class="tab-inp" name="logisticsCompanyNames" /> <input type="hidden" class="tab-inp" name="logisticsCompanyIds" /> 
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/logisticscompany/reference?referenceInputType=check','zongForm.logisticsCompanyIds,zongForm.logisticsCompanyNames');">选择</span>
				                             </td>
				                      </tr>
				                      <tr>
				                             <th >标题:</th>
				                             <td colspan="5"  > <input type="text" class="tab-inp" name="title" /> 
				                             </td>
				                      </tr>

			                          <tr>
		                           
 			                             <th>内容:</th>
			                             <td colspan="5"><textarea  name="content"></textarea>
			                               </td>
			                          </tr>

			                      </tbody>
		                 
		                 </table>

		            </div>
		                     

		                         <!-- begin 操作按钮 -->
		                         <div class="opt-div">
		                         
		                          <a href="javascript:void(0);" class="save-btn-shi" id="save" onclick="javascript:doSaveOrCommit('save')">发送</a>
		                         </div>
		                         <!-- end 操作按钮 -->
		                          

		                     
		                     </div>
		                     
		                     <!-- end  -->
		              </form>   
		                 
		                 </div>
		
		                   
 						<!-- end 详情 -->
 						


					</div><!-- /.page-content -->
				</div><!-- /.main-content -->


			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

		<script type="text/javascript">

		
		
		</script>
	</body>
</html>
