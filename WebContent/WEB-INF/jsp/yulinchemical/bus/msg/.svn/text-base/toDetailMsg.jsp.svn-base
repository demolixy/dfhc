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
				                      <tr>
				                             <th >标题:</th>
				                             <td colspan="5"  >${bean.title}
				                             </td>
				                      </tr>

			                          <tr>
		                           
 			                             <th>内容:</th>
			                             <td colspan="5"><textarea  name="content">${bean.content}</textarea>
			                               </td>
			                          </tr>

			                      </tbody>
		                 
		                 </table>


		                     <!-- end  -->
		            		<div class="row" style="height: 300px;">
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

		            </div>
		            
		            
		                     </div>
		                     
		              </form>   
		                 
		                 </div>
		
		                   
 						<!-- end 详情 -->
 						


					</div><!-- /.page-content -->
				</div><!-- /.main-content -->


			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

		<script type="text/javascript">

		
		 var jqData=[];
		 <c:forEach items="${list}" var="bean"> 
			var dataObj1=new Object();
			dataObj1['recipient']= '${bean.recipient}';
			jqData.push(dataObj1);
	   </c:forEach> 
		jQuery(function($) {

			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			
			jQuery(grid_selector).jqGrid({
				//direction: "rtl",
				data:jqData,
				datatype: "local",
			    mtype: 'POST',  
				height: "100%",
				colNames:[
				          '接收人'
							],
				colModel:[
							{name:'recipient',index:'recipient', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}}
						], 
				// shrinkToFit:false,不随列数量改变列宽度
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
		
				editurl: getRootPath()+'/orderproductdetail/update',//nothing is saved
				caption: "接收人列表展示",
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
			
			//新增
// 		   jQuery(grid_selector) .navButtonAdd(pager_selector,
// 				{ caption:"", buttonicon:"icon-plus-sign purple",
// 			      onClickButton: addShow, position:"last" }) ;
		
			//修改
// 			  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 				{ caption:"", buttonicon:"icon-pencil blue",
// 			      onClickButton:updateShow, 
// 			      position:"last" }) ;
// 			//删除
// 			  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 				{ caption:"", buttonicon:"icon-trash red",
// 			      onClickButton:deleteShow , 
// 			      position:"last" }) ;
			
// 			//查询
// 			  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 				{ caption:"", buttonicon:"icon-search orange",
// 			      onClickButton:search_div , 
// 			      position:"last" }) ;
			
		     //新增弹出
		     
			 function addShow(){
		    	 
		    	 $(".ui_out_tan").show();
		    	 $("#add_form").show();
				 
			 }
		     
		     //  修改弹出
		     function updateShow(){
		    	 
	    		var selectedId = $("#grid-table").jqGrid("getGridParam", "selarrrow");
	    	
	    		if(selectedId.length!=1){
	    			alert('请选择一条数据进行修改');
	    			return false;
	    		}
	    		var data={};
	    		for(var i=0;i<jqData.length;i++){
	    			
	    			var productId=jqData[i].productId;
	    			if(selectedId==productId){
	    				data=jqData[i];
	    				break;
	    			}
	    		}
	    		 
          
	    		$("#portlet-config-edit").find("input[name=productTypeId]").val(data.productTypeId);
               $("#portlet-config-edit").find("input[name=productId]").val(data.productId);
               $("#portlet-config-edit").find("input[name=productName]").val(data.productName);
               $("#portlet-config-edit").find("input[name=plannedVolume]").val(data.plannedVolume);
               $("#portlet-config-edit").find("input[name=unitPrice]").val(data.unitPrice);
               $("#portlet-config-edit").find("input[name=carNum]").val(data.carNum);
               $("#portlet-config-edit").find("input[name=remarkVolume]").val(data.remarkVolume);
               $("#portlet-config-edit").find("input[name=remainderCarNum]").val(data.remainderCarNum);
               $("#portlet-config-edit").find("input[name=allPrice]").val(Number(data.plannedVolume)*Number(data.unitPrice));
               $("#portlet-config-edit").find("input[name=allPrice2]").val(Number(data.plannedVolume)*Number(data.unitPrice));
             
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
				})
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
