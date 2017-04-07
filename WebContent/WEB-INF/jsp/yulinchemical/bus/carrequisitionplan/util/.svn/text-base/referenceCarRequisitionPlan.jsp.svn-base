<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：referenceCarRequisitionPlan.jsp
   *
   * 功能描述：汽运调拨计划参照页面
   * 
   * 版本历史：
   * 2017-01-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page import="com.dfhc.bus.carrequisitionplan.ICarRequisitionPlanConstants" %>
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


<script type="text/javascript">
//查询操作
function  searchShi(urldata){
	   jQuery("#grid-table").jqGrid("setGridParam",
	   {
	       datatype:"json",
	       postData:$(form).serialize(), //发送数据  
	       page:"1",
	       rows:"18"
	   }).trigger("reloadGrid");
		 
	   
 	   $(".ui_inner_tan").hide();
	   $(".ui_out_tan").hide();	  
}
</script>
</head>
	


<body>
	

    
    <!-- begin 查询 -->
     <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-query" style="width: 70%;left:15%;top:5%;"  dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
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
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCode")%></span><input class="m-wrap" name="planCode" id="planCode" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%></span><input name="makeDate" id="makeDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%></span><input class="m-wrap" name="logisticsCompany" id="logisticsCompany" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompanyId")%></span><input class="m-wrap" name="logisticsCompanyId" id="logisticsCompanyId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("goodsName")%></span><input class="m-wrap" name="goodsName" id="goodsName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId" id="productId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("requisitionWarehouse")%></span><input class="m-wrap" name="requisitionWarehouse" id="requisitionWarehouse" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("requisitionWarehouseId")%></span><input class="m-wrap" name="requisitionWarehouseId" id="requisitionWarehouseId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingAddress")%></span><input class="m-wrap" name="shippingAddress" id="shippingAddress" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("num")%></span><input class="m-wrap" name="num" id="num" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCarNum")%></span><input class="m-wrap" name="planCarNum" id="planCarNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%></span><input class="m-wrap" name="plannedMileage" id="plannedMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("factMileage")%></span><input class="m-wrap" name="factMileage" id="factMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("valuationMileage")%></span><input class="m-wrap" name="valuationMileage" id="valuationMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("yuanTon")%></span><input class="m-wrap" name="yuanTon" id="yuanTon" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("freightYuanTon")%></span><input class="m-wrap" name="freightYuanTon" id="freightYuanTon" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("unitPrice")%></span><input class="m-wrap" name="unitPrice" id="unitPrice" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%></span>					<textarea class="medium m-wrap" name="remark" id="remark" 					inputName="<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%>" 						 maxLength="300" 					 ></textarea>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarPlanImage")%></span><input class="m-wrap" name="sendCarPlanImage" id="sendCarPlanImage" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("remainderNum")%></span><input class="m-wrap" name="remainderNum" id="remainderNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("remainderCarNum")%></span><input class="m-wrap" name="remainderCarNum" id="remainderCarNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode" id="shipperCode" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("flowToId")%></span><input class="m-wrap" name="flowToId" id="flowToId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_CAR_REQUISITION_STATUS), "", "inputName='" + ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("invalidCarNumber")%></span><input class="m-wrap" name="invalidCarNumber" id="invalidCarNumber" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("invalidNumber")%></span><input class="m-wrap" name="invalidNumber" id="invalidNumber" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingVehiclesNumber")%></span><input class="m-wrap" name="shippingVehiclesNumber" id="shippingVehiclesNumber" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingNumber")%></span><input class="m-wrap" name="shippingNumber" id="shippingNumber" type="text"  						maxLength="10" />
							</div>

							</div>
			 </div>
     <input type="hidden" name="referenceInputType" value="${referenceInputType}"/>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi('<%=request.getContextPath()%>/carrequisitionplan/reference?referenceInputType=${referenceInputType}');">
       <i class="icon-search"></i>
           查询</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div>
     
     </div>
     
    
    
    <!-- end 查询 -->
    
 

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
				
				
            
                 
				<div class="main-content ref-main-content">
				

					<div class="page-content">
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
					
					url: getRootPath()+'/carrequisitionplan/referenceAjax',
					datatype: "json",
				    mtype: 'POST',  
					height: '100%',
					colNames:['planCode',
'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCode")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompanyId")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("goodsName")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("productId")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("requisitionWarehouse")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("requisitionWarehouseId")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingAddress")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("num")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCarNum")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("factMileage")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("valuationMileage")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("yuanTon")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("freightYuanTon")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("unitPrice")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarPlanImage")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("remainderNum")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("remainderCarNum")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("flowToId")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("status")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("invalidCarNumber")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("invalidNumber")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingVehiclesNumber")%>'
,'<%=ICarRequisitionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingNumber")%>'
],
					colModel:[
					    {name:'planCode',index:'planCode',width:90, editable:true,hidedlg:true,hidden:true},		
			{name:'planCode',index:'planCode', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'makeDate',index:'makeDate',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'logisticsCompany',index:'logisticsCompany', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'logisticsCompanyId',index:'logisticsCompanyId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'goodsName',index:'goodsName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'productId',index:'productId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'requisitionWarehouse',index:'requisitionWarehouse', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'requisitionWarehouseId',index:'requisitionWarehouseId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'shippingAddress',index:'shippingAddress', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'num',index:'num', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'planCarNum',index:'planCarNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'plannedMileage',index:'plannedMileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}},
			{name:'factMileage',index:'factMileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}},
			{name:'valuationMileage',index:'valuationMileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}},
			{name:'yuanTon',index:'yuanTon', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'freightYuanTon',index:'freightYuanTon', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'unitPrice',index:'unitPrice', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'remark',index:'remark', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'sendCarPlanImage',index:'sendCarPlanImage', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'remainderNum',index:'remainderNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'remainderCarNum',index:'remainderCarNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'shipperCode',index:'shipperCode', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'flowToId',index:'flowToId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'status',index:'status', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'invalidCarNumber',index:'invalidCarNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'invalidNumber',index:'invalidNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'shippingVehiclesNumber',index:'shippingVehiclesNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'shippingNumber',index:'shippingNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}}
					], 	
		                        shrinkToFit:false,
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
			
					editurl: getRootPath()+'/carrequisitionplan/update',//nothing is saved
					caption: "汽运调拨计划列表展示",
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
