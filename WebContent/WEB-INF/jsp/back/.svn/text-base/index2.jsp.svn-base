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

	<style type="text/css">
	
	.ui-title{padding-left:20px;line-height:40px;background-color:#307ecc;
	          color:#fff;margin-top:10px;font-size: 16px;font-weight: bold;}
	.ui-content{
			border: 1px dotted #ccc;
			min-height: 80px;
			border-top-style: hidden;
			height: auto;
			padding-bottom: 10px;
	}
	.ui-content div{padding:0px 5px;line-height: 20px;color: #666;}
	
	.ui-content a{color:#f04618;}
	
	.chart {text-align: center;padding-top:10px;}
	.chart img{width: 200px;height: auto;}
	
	.js_tab{margin:auto;text-align: center;padding-top:10px !important;}
	.js_tab table{width: 80%;}
	.js_tab td{border: 1px solid #ccc;height: 30px;}
	.js-con > div{margin-top: 10px ;text-align: center;}
	.js-con select,.js-con input{width:220px;}
	</style>
	</head>

	<body>
	
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
					<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
					
					<div class="row">
					
					  <div class="col-xs-12">
					  
					    <div class="col-xs-6 " >
					       
					       <div class="ui-title">通知公告</div>
					       <div class="ui-content">
					       
					       <c:forEach  items="${list}"  var="bean">
					       		 <div><c:out value="${fn:substring(bean.title,0,20)}"></c:out><c:if test="${fn:length(bean.title)>20}">...</c:if>
								<div  style="float: right"><c:out value="${bean.sendTime}"></c:out></div></div>
					       </c:forEach>
					       
					       </div>
					       
					    </div>
					    
					     <div class="col-xs-6" >
					      <div class="ui-title">待办任务</div>
					      <div class="ui-content">
						   <c:forEach  items="${listRmCommonVo}"  var="bean">
					       		 <div><c:out value="${fn:substring(bean.name,0,20)}"></c:out><c:if test="${fn:length(bean.name)>20}">...</c:if>
								<div  style="float: right"><c:out value="${bean.countnum}"></c:out></div></div>
					       </c:forEach>
					       
					       </div>
					    </div>
					    
					    <!-- begin 2 -->
					    <div class="col-xs-12">
					  
					        <div class="ui-title">计划完成情况</div>
					        <div class="ui-content clearfix">
					        
					          <div class="col-xs-6 chart" >
					          
					            <img alt="" src="${pageContext.request.contextPath }/images/logo.png"/>
					          
					          </div>
					          
					          <div class="col-xs-6" >
					            
					            <div class="js_tab">
					               
					               <table>
					               
					                <tr>
					                  <td>计划类型</td>
					                  <td>计划车数</td>
					                  <td>已完成车数</td>
					                
					                </tr>
					               
					               
					               </table>
					            
					            </div>
					          
					          </div>
					          
					        
					        </div>
					  
					  
					    </div>
					    
					    <!-- end2 -->
					    
					    <!-- begin3 
					    
					    <div class="col-xs-12">
					    
					     <div class="ui-title">基础运费查询</div>
					     <div class="ui-content js-con clearfix">
					     
						     <div class="col-xs-12">
						       
						        <div class="col-xs-4">
								       <span>发货单位：</span>
								       <span>
									        <select >
									          <option value="0">--请选择--</option>
									          <option value="1">--榆林化工--</option>
									        
									        </select>
								       </span>
						     	</div>
						        <div class="col-xs-4">
								       <span>目的类型：</span>
								       <span>
									        <select >
									          <option value="0">--请选择--</option>
									          <option value="1">--榆林化工--</option>
									        
									        </select>
								       </span>
						     	</div>
						        <div class="col-xs-4">
								       <span>名称：</span>
								       <span>
									        <input type="text" value="" placeholder="请输入名称" />
								       </span>
						     	</div>
						     
						     </div>
						     <div class="col-xs-12">
						     
						          <div style="margin-bottom:10px;">
						          
						             <a href="javascript:void(0);" class=" fm-button-icon-left btn btn-sm btn-primary" style="margin-right:30px;">查询</a>
						             <a href="javascript:void(0);" class=" fm-button-icon-left btn btn-sm btn-primary">重置</a>
						          </div>
						         

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

								<script type="text/javascript">
									var $path_base = "/";//this will be used in gritter alerts containing images
								</script>

						      
						     </div>
						   
					    
					     
					     </div>
					     
					    
					    </div>
					    
					    <!-- end 3 -->
					    
					    
					 
					  </div>
					  
					 
					  
					
					</div>	


				</div><!-- /.main-content -->



			</div><!-- /.main-container-inner -->

		
		</div><!-- /.main-container -->

	

		<script type="text/javascript">
			var grid_data = 
			[ 
				{id:"1",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
				{id:"2",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
				{id:"3",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
				{id:"4",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"},
				{id:"5",name:"Laser Printer",note:"note2",stock:"Yes",ship:"FedEx",sdate:"2007-12-03"},
				{id:"6",name:"Play Station",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
				{id:"7",name:"Mobile Telephone",note:"note",stock:"Yes",ship:"ARAMEX",sdate:"2007-12-03"},
				{id:"8",name:"Server",note:"note2",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
				{id:"9",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
				{id:"10",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
				{id:"11",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
				{id:"12",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
				{id:"13",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"},
				{id:"14",name:"Laser Printer",note:"note2",stock:"Yes",ship:"FedEx",sdate:"2007-12-03"},
				{id:"15",name:"Play Station",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
				{id:"16",name:"Mobile Telephone",note:"note",stock:"Yes",ship:"ARAMEX",sdate:"2007-12-03"},
				{id:"17",name:"Server",note:"note2",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
				{id:"18",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
				{id:"19",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
				{id:"20",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
				{id:"21",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
				{id:"22",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
				{id:"23",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"}
			];	
			
			jQuery(function($) {
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";
			
				jQuery(grid_selector).jqGrid({
					//direction: "rtl",
					
					data: grid_data,
					datatype: "local",
					height: 250,
					colNames:[ 'ID','Last Sales','Name', 'Stock', 'Ship via','Notes'],
					colModel:[
						
						{name:'id',index:'id', width:60, sorttype:"int", editable: true},
						{name:'sdate',index:'sdate',width:90, editable:true, sorttype:"date",unformat: pickDate},
						{name:'name',index:'name', width:150,editable: true,editoptions:{size:"20",maxlength:"30"}},
						{name:'stock',index:'stock', width:70, editable: true,edittype:"checkbox",editoptions: {value:"Yes:No"},unformat: aceSwitch},
						{name:'ship',index:'ship', width:90, editable: true,edittype:"select",editoptions:{value:"FE:FedEx;IN:InTime;TN:TNT;AR:ARAMEX"}},
						{name:'note',index:'note', width:150, sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"10"}} 
					], 
			
					viewrecords : true,
					rowNum:10,
					rowList:[10,20,30],
					pager : pager_selector,
					altRows: true,
					//toppager: true,
					
					multiselect: false,
					//multikey: "ctrlKey",
			        multiboxonly: false,
			
					loadComplete : function() {
						var table = this;
						setTimeout(function(){
							styleCheckbox(table);
							
							updateActionIcons(table);
							updatePagerIcons(table);
							enableTooltips(table);
						}, 0);
					},
			
					editurl: $path_base+"/dummy.html",//nothing is saved
					caption: "",
			
			
					autowidth: true
			
				});
			
				//enable search/filter toolbar
				//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
			
				//switch element when editing inline
				function aceSwitch( cellvalue, options, cell ) {
					setTimeout(function(){
						$(cell) .find('input[type=checkbox]')
								.wrap('<label class="inline" />')
							.addClass('ace ace-switch ace-switch-5')
							.after('<span class="lbl"></span>');
					}, 0);
				}
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
			
			
				
				
				
			
			
				//it causes some flicker when reloading or navigating grid
				//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
				//or go back to default browser checkbox styles for the grid
				function styleCheckbox(table) {
				/**
					$(table).find('input:checkbox').addClass('ace')
					.wrap('<label />')
					.after('<span class="lbl align-top" />')
			
			
					$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
					.find('input.cbox[type=checkbox]').addClass('ace')
					.wrap('<label />').after('<span class="lbl align-top" />');
				*/
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
