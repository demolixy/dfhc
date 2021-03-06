<%@page import="com.dfhc.quota.IQuotaConstants"%>
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
							  <span class="add-on"><%=IQuotaConstants.TABLE_COLUMN_DISPLAY.get("userId")%></span><input class="m-wrap" name="userId" id="userId" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IQuotaConstants.TABLE_COLUMN_DISPLAY.get("accountHolderName")%></span><input class="m-wrap" name="accountHolderName" id="accountHolderName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IQuotaConstants.TABLE_COLUMN_DISPLAY.get("creditQuota")%></span><input class="m-wrap" name="creditQuota" id="creditQuota" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IQuotaConstants.TABLE_COLUMN_DISPLAY.get("rechargeQuota")%></span><input class="m-wrap" name="rechargeQuota" id="rechargeQuota" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IQuotaConstants.TABLE_COLUMN_DISPLAY.get("freezeQuota")%></span><input class="m-wrap" name="freezeQuota" id="freezeQuota" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IQuotaConstants.TABLE_COLUMN_DISPLAY.get("availableQuota")%></span><input class="m-wrap" name="availableQuota" id="availableQuota" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IQuotaConstants.TABLE_COLUMN_DISPLAY.get("temporaryQuota")%></span><input class="m-wrap" name="temporaryQuota" id="temporaryQuota" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IQuotaConstants.TABLE_COLUMN_DISPLAY.get("quotaAmount")%></span><input class="m-wrap" name="quotaAmount" id="quotaAmount" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IQuotaConstants.TABLE_COLUMN_DISPLAY.get("signature")%></span><input class="m-wrap" name="signature" id="signature" type="text"  						maxLength="32" />
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
								<a href="#">额度账户</a>
							</li>
							<li class="active">详情查看</li>
						</ul><!-- .breadcrumb -->

						
					</div>

					<div class="page-content">


		                <!-- begin  详情 -->
		                
		                 <div class="widget-box">
		                 
		                     <div class="widget-header">
		                      
		                      <h5>额度账户详情</h5>
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
		                           
			                             <th>账户名称:</th>
			                             <td>王晓文</td>
			                             <th>额度账户余额:</th>
			                             <td>￥100.00</td>
			                             <th>信用度</th>
			                             <td>￥20.00</td>
			                          
			                           </tr>
			                           
			                           <tr>
			                            
			                            <th>冻结额度金额</th>
			                            <td>￥100.2</td>
			                            <th>账户充值金额</th>
			                            <td colspan="3">10.0</td>
			                            
			                           
			                           </tr>
			                          
			                           <tr>
			                           
			                            <th>额度账户调整</th>
			                            <td colspan="3"><input type="text" class="tab-inp"/> </td>
			                             <th>调整时间</th>
			                            <td><input type="text"  class="tab-inp date-picker" readonly  /></td>
			                           
			                            </tr>
			                            
			                            
			                            <tr>
			                            
			                             <th>审核意见</th>
			                             <td colspan="5">
			                              
			                              <div>
			                              
			                                <textarea rows="3" cols="3" class="tab-textarea"></textarea>
			                                
			                              </div>
			                              
			                             
			                             </td>
			                             
			                            
			                            </tr>
			                           
			                           
			                           
			                           
			                         </tbody>
		                      
		                         
		                         </table>
		                       
		                       
		                          <!-- begin 子表 -->
		                          
		                            <div class="widget-header"> 
					                      <h5>tab增加行</h5>
					                      
					                      <div class="widget-toolbar">
					                      
					                        <a href="javascript:void(0);" title="增加1条数据">
					                         
					                         <i  class="icon-plus-sign"> </i>
					                        
					                        </a>
					                        
					                        
					                      </div>
					                     
					                   </div>
		                         
		                              <table  class="table table-striped table-bordered table-hover detail-tab">
		                              
		                              <thead>
		                                
			                               <tr>
			                                 <th>列名1</th>
			                                 <th>列名2</th>
			                                 <th>列名3</th>
			                                 <th>列名4</th>
			                                 <th>列名5</th>
			                                 <th>列名6</th>
			                               </tr>
		                                 
		                                
		                              </thead>
		                              <tbody>
			                              <tr>
			                                 
			                                 <td> <input type="text" class="tab-inp-small"/> </td>
			                                 <td> <input type="text" class="tab-inp-small"/> </td>
			                                 <td> <input type="text" class="tab-inp-small"/> </td>
			                                 <td> <input type="text" class="tab-inp-small"/> </td>
			                                 <td> <input type="text" class="tab-inp-small"/> </td>
			                                 <td> <input type="text" class="tab-inp-small"/> </td>
			                                
			                                 
			                                 
			                              </tr>
		                              
		                              
		                              
		                              </tbody>
		                              
		                              
		                              </table>
		                         
		                          
		                          <!-- end 子表 -->
		                          
		                          
		                         
		                         <!-- begin 操作按钮 -->
		                         
		                         <div class="opt-div">
		                         
		                         
		                          <a href="javascript:void(0);" class="save-btn-shi">保存</a>
		                          <a href="javascript:void(0);" class="sub-btn-shi"> 提交</a>
		                         
		                         
		                         
		                         </div>
		                         
		                         <!-- end 操作按钮 -->
		                          
		                          
		                          
		                          
		                          
		                       
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
