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
		                      
		                      <h5>额度账户详情</h5>
		                      <div class="widget-toolbar">
		                      
		                         <a href="javascript:void(0)" title="审核通过">
		                           
		                           <i class="icon-check" ></i>
		                         
		                         </a>
		                         
		                         <a href="javascript:void(0)" title="审核驳回" >
		                           
		                           <i class="icon-ban-circle"></i>
		                         
		                         </a>
		                         
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
		                       
		                       </div>
		                     
		                     
		                     </div>
		                     
		                     <!-- end  -->
		                 
		                 
		                 </div>
		
		                   
 						<!-- end 详情 -->
 						
 						
 						
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

								<script type="text/javascript">
									var $path_base = "/";//this will be used in gritter alerts containing images
								</script>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

	

		<script type="text/javascript">
			//var grid_data = ${grid_data};	
		
			jQuery(function($) {
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";

				jQuery(grid_selector).jqGrid({
					//direction: "rtl",
					
					url: getRootPath()+'/quota/getChildData?pid=${id}',
					datatype: "json",
				    mtype: 'POST',  
					height:300,
					colNames:['创建时间','账户名称','信用额度', '充值额度', 'freezeQuota','availableQuota'],
					colModel:[
                        {name:'create_time',index:'create_time',sorttype :"date",formatter: formatDateRow},
						{name:'accountHolderName',index:'accountHolderName'},
						{name:'creditQuota',index:'creditQuota' ,editable: false},
						{name:'rechargeQuota',index:'rechargeQuota',  editable: true,edittype:"text"},
						{name:'freezeQuota',index:'freezeQuota', editable: true,edittype:"text"},
						{name:'availableQuota',index:'freezeQuota', sortable:false,editable: true,edittype:"text"} 
					], 
				
					viewrecords : true,
					rownumbers:true,
					rowNum:15,
					rowList:[15,30],
					pager : pager_selector,
					sortname : 'id',//初始化的时候排序的字段
					sortorder : "desc",//排序方式,可选desc,asc
					altRows: true,
					//toppager: true,
					
					multiselect: true,
					//multikey: "ctrlKey",
			        multiboxonly: true,
			
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							
							
							updateActionIcons(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
					},
			
					editurl: getRootPath()+'/quota/update',//nothing is saved
					caption: "额度账户列表展示",
					autowidth: true
			
				});
				
				//enable search/filter toolbar
				//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
			
				//enable datepicker
				function pickDate( cellvalue, options, cell ) {
					setTimeout(function(){
						$(cell) .find('input[type=text]')
								.datepicker({format:'yyyy-mm-dd' , autoclose:true}); 
					}, 0);
				}
			   
				
			
			
				//navButtons
				jQuery(grid_selector).jqGrid('navGrid',pager_selector,
					{ 	//navbar options
						edit: false,
						editicon : 'icon-pencil blue',
						add: false,
						addicon : 'icon-plus-sign purple',
						del: false,
						delicon : 'icon-trash red',
						search: false,
						searchicon : 'icon-search orange',
						refresh: false,
						refreshicon : 'icon-refresh green',
						view: false,
						viewicon : 'icon-zoom-in grey',
					
					}
				)
				
			
				
				//查询
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-search orange",
				      onClickButton:search_div , 
				      position:"last" }) ;
				
				
				
					
					//查看
					  jQuery(grid_selector) .navButtonAdd(pager_selector,
						{ caption:"",title:"查看详情" ,buttonicon:"icon-eye-open red",
					      onClickButton:to_detail , 
					      position:"last" }) ;
				
	
				
							
					//查看详情
					 
					function to_detail(){
						
						var checkedLen= $(".cbox:checked").length;
				    	if(checkedLen==0){
				    		
				    		alert("请至少选择一条数据！");
				    		return false;
				    	}
	                    if(checkedLen>1){
				    		
				    		alert("最多只能选择一条数据！");
				    		return false;
				    	}
	                    var pointer=$(".cbox:checked").parents("tr");
	                    var id=$(pointer).attr("id");
	                    
	                    window.location.href="${pageContext.request.contextPath}/quota/toDetail?id="+id+"&pcode=${pcode}";
						 
						
					}
				
			
					
					
			     //新增弹出
			     
				 function addShow(){
			    	 
			    	 $(".ui_out_tan").show();
			    	 $("#add_form").show();
					 
				 }
			     
			     //  修改弹出
			     function updateShow(){
			    	 
			    	var checkedLen= $(".cbox:checked").length;
			    	if(checkedLen==0){
			    		
			    		alert("请至少选择一条数据！");
			    		return false;
			    	}
                    if(checkedLen>1){
			    		
			    		alert("最多只能选择一条数据！");
			    		return false;
			    	}
                    var pointer=$(".cbox:checked").parents("tr");
                    var updateDate=$(pointer).attr("id");
                    $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/quota/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                                 $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                                 $("#portlet-config-edit").find("input[id=userId]").val(data.bean.userId);
                                 $("#portlet-config-edit").find("input[id=accountHolderName]").val(data.bean.accountHolderName);
                                 $("#portlet-config-edit").find("input[id=creditQuota]").val(data.bean.creditQuota);
                                 $("#portlet-config-edit").find("input[id=rechargeQuota]").val(data.bean.rechargeQuota);
                                 $("#portlet-config-edit").find("input[id=freezeQuota]").val(data.bean.freezeQuota);
                                 $("#portlet-config-edit").find("input[id=availableQuota]").val(data.bean.availableQuota);
                                 $("#portlet-config-edit").find("input[id=temporaryQuota]").val(data.bean.temporaryQuota);
                                 $("#portlet-config-edit").find("input[id=quotaAmount]").val(data.bean.quotaAmount);
                                 $("#portlet-config-edit").find("input[id=signature]").val(data.bean.signature);
                	   	   }
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
			    	 $(".ui_out_tan").show();
                     $("#portlet-config-edit").show();
			    	 
			     }
			     
			     //删除弹出
			     function deleteShow (){
			    	 
			    	 var checkedLen= $(".cbox:checked").length;
				    	if(checkedLen==0){
				    		
				    		alert("请至少选择一条数据！");
				    		return false;
				    	}
				    	$(".ui_out_tan").show();
				        $("#portlet-config-delete").show(); 
				    	
			    	 
			     }
                //查询层
			     
			     function search_div(){
			    	 $(".ui_out_tan").show();
			    	 $("#portlet-config-query").show();
			     }
			     
			     
			 
			     
			     
			     
				//unlike navButtons icons, action icons in rows seem to be hard-coded
				//you can change them like this in here if you want
				function updateActionIcons(table) {
					/**
					var replacement = 
					{
						'ui-icon-pencil' : 'icon-pencil blue',
						'ui-icon-trash' : 'icon-trash red',
						'ui-icon-disk' : 'icon-ok green',
						'ui-icon-cancel' : 'icon-remove red'
					};
					$(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
					*/
				}
				
				//replace icons with FontAwesome icons like above
				function updatePagerIcons(table) {
					var replacement = 
					{
						'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
						'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
						'ui-icon-seek-next' : 'icon-angle-right bigger-140',
						'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
					};
					$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					});
				}
			
				function enableTooltips(table) {
					$('.navtable .ui-pg-button').tooltip({container:'body'});
					$(table).find('.ui-pg-div').tooltip({container:'body'});
				}
			
				//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
			
			
			});
		</script>
	</body>
</html>
