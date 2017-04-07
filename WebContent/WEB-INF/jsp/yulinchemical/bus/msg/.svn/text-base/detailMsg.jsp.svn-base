<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：detailMsg.jsp
   *
   * 功能描述：消息表详细信息页面
   * 
   * 版本历史：
   * 2017-03-27   1.0.0版 （龙色波）（创建文件）
   * 
--%>

<%@page import="com.dfhc.bus.msg.IMsgConstants" %>
<%@page import="org.quickbundle.tools.helper.RmVoHelper" %>
<%@page import="org.quickbundle.tools.helper.RmStringHelper" %>
<%@page import="com.dfhc.bus.msg.vo.MsgVo" %>
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

		     
		     
//查询操作

function  searchShi(){
	   jQuery("#grid-table").jqGrid("setGridParam",
	   {
	       datatype:"json",
	       postData:$(form).serialize(), //发送数据  
	       page:"1"
	   }).trigger("reloadGrid");
		 
	   
	$(".ui_inner_tan").hide();
   	$(".ui_out_tan").hide();
	 
}


</script>
	</head>
	


	<body>
	
	
	

    <!-- begin 查询 -->
    
     <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-query" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
      <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">查询</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
   <div class="modal-body">
   <form name="form" id="form" class="FormGrid ui_inner_form" method="post" >
   
           <div class="control-group">
							<div class="controls">
							<input id="id" name="id" type="hidden" />
							<div class="input-prepend">
							  <span class="add-on"><%=IMsgConstants.TABLE_COLUMN_DISPLAY.get("title")%></span><input class="m-wrap" name="title" id="title" type="text"  />
							</div>
							<div class="input-prepend">
							  <span class="add-on"><%=IMsgConstants.TABLE_COLUMN_DISPLAY.get("content")%></span><input class="m-wrap" name="content" id="content" type="text"  />
							</div>
							<div class="input-prepend">
							  <span class="add-on"><%=IMsgConstants.TABLE_COLUMN_DISPLAY.get("expirationTime")%></span><input class="m-wrap" name="expirationTime" id="expirationTime" type="text"  />
							</div>
							<div class="input-prepend">
							  <span class="add-on"><%=IMsgConstants.TABLE_COLUMN_DISPLAY.get("messageType")%></span><input class="m-wrap" name="messageType" id="messageType" type="text"  />
							</div>
							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi();">
       <i class="icon-search"></i>
           查询</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div>
     
     </div>
     
    
    
    <!-- end 查询 -->
    
 
	
	   <!-- begin  头部 -->
	   
	  <%@ include file="/jsp/include/web/jqGrid/header.jsp" %> 
	
		
	   <!-- end 头部 -->

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{
                                   ace.settings.check('main-container' , 'fixed')
                                }catch(e){
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
                                                      }  catch(e)
                                                      {
                                                      }
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">消息表</a>
							</li>
							<li class="active">详情查看</li>
						</ul><!-- .breadcrumb -->

						
					</div>

					<div class="page-content">

		                <!-- begin  详情 -->
		                
		                 <div class="widget-box">
		                 
		                     <div class="widget-header">
		                      
		                      <h5>消息表详情</h5>
		                      <div class="widget-toolbar">
		                      		                         
		                         <a href="javascript:void(0)" title="返回">
		                         
		                           <i class="icon-share-alt"></i>
		                           
		                         </a>
		                      
		                      </div>
		                         
		                      
		                     </div>
		                     <!-- end  header -->
		                     
		                     <!-- begin  -->
		                     
		                     <div class="widget-body">
		                     
		                       <div class="widget-main">
		                       
		                         <table  class="table table-striped table-bordered table-hover detail-tab">
		                         
			                         <tbody>
	                  			          <tr>
			                             <th><%=IMsgConstants.TABLE_COLUMN_DISPLAY.get("senderId")%>:</th>
			                             <td>${msgVo.senderId}</td>
			                             <th><%=IMsgConstants.TABLE_COLUMN_DISPLAY.get("title")%>:</th>
			                             <td>${msgVo.title}</td>
			                             <th><%=IMsgConstants.TABLE_COLUMN_DISPLAY.get("content")%>:</th>
			                             <td>${msgVo.content}</td>
	                  			          </tr>
	                  			          <tr>
			                             <th><%=IMsgConstants.TABLE_COLUMN_DISPLAY.get("expirationTime")%>:</th>
			                             <td>${msgVo.expirationTime}</td>
			                             <th><%=IMsgConstants.TABLE_COLUMN_DISPLAY.get("messageType")%>:</th>
			                             <td>${msgVo.messageType}</td>
	                  			          </tr>
			                         </tbody>
		                      
		                         
		                         </table>
		                       
		                       </div>
		                     
		                     
		                     </div>
		                     
		                     <!-- end  -->
		                 
		                 
		                 </div>
		
		                   
 						<!-- end 详情 -->
 						
 						
 						
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

	

		
	</body>
</html>
