<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：referenceCarDistributionPlan.jsp
   *
   * 功能描述：汽运配送计划参照页面
   * 
   * 版本历史：
   * 2017-01-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page import="com.dfhc.bus.cardistributionplan.ICarDistributionPlanConstants" %>
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
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCode")%></span><input class="m-wrap" name="planCode" id="planCode" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("distributionPlanId")%></span><input type="text" class="text_field_reference text_field_readonly"  hiddenInputId="distributionPlanId" name="distributionPlanIdName" inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("distributionPlanId")%>" value="" /><input type="hidden" name="distributionPlanId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getReference(new Array(form.distributionPlanId, form.distributionPlanIdName), '<%=request.getContextPath()%>/', '<%=request.getContextPath()%>/distributionplan/reference?referenceInputType=radio');"/><span class="style_required_red">* </span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%></span><input name="makeDate" id="makeDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%></span><input class="m-wrap" name="logisticsCompany" id="logisticsCompany" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("region")%></span><input class="m-wrap" name="region" id="region" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("customerName")%></span><input class="m-wrap" name="customerName" id="customerName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("goodsName")%></span><input class="m-wrap" name="goodsName" id="goodsName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPerson")%></span><input class="m-wrap" name="receivingPerson" id="receivingPerson" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPersonPhone")%></span><input class="m-wrap" name="receivingPersonPhone" id="receivingPersonPhone" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("area")%></span><input class="m-wrap" name="area" id="area" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingAddress")%></span><input class="m-wrap" name="shippingAddress" id="shippingAddress" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("num")%></span><input class="m-wrap" name="num" id="num" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%></span><input class="m-wrap" name="plannedMileage" id="plannedMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("factMileage")%></span><input class="m-wrap" name="factMileage" id="factMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("valuationMileage")%></span><input class="m-wrap" name="valuationMileage" id="valuationMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("tonKm")%></span><input class="m-wrap" name="tonKm" id="tonKm" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("settlementFreight")%></span><input class="m-wrap" name="settlementFreight" id="settlementFreight" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("totalFreight")%></span><input class="m-wrap" name="totalFreight" id="totalFreight" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("price")%></span><input class="m-wrap" name="price" id="price" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%></span>					<textarea class="medium m-wrap" name="remark" id="remark" 					inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%>" 						 maxLength="300" 					 ></textarea>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%></span><input name="sendCarDate" id="sendCarDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_PLAN_STATUS), "", "inputName='" + ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remainderNum")%></span><input class="m-wrap" name="remainderNum" id="remainderNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remainderCarNum")%></span><input class="m-wrap" name="remainderCarNum" id="remainderCarNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode" id="shipperCode" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingVehiclesNumber")%></span><input class="m-wrap" name="shippingVehiclesNumber" id="shippingVehiclesNumber" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingNumber")%></span><input class="m-wrap" name="shippingNumber" id="shippingNumber" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("flowToId")%></span><input class="m-wrap" name="flowToId" id="flowToId" type="text"  						maxLength="19" />
							</div>

							</div>
			 </div>
     <input type="hidden" name="referenceInputType" value="${referenceInputType}"/>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi('<%=request.getContextPath()%>/cardistributionplan/reference?referenceInputType=${referenceInputType}');">
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
					
					url: getRootPath()+'/cardistributionplan/referenceAjax?${queryString}',
					datatype: "json",
				    mtype: 'POST',  
					height: '100%',
					colNames:['planCode',
'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCode")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("distributionPlanId")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("region")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("customerName")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("goodsName")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPerson")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPersonPhone")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("area")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingAddress")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("num")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("factMileage")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("valuationMileage")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("tonKm")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("settlementFreight")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("totalFreight")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("price")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("status")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remainderNum")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remainderCarNum")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingVehiclesNumber")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingNumber")%>'
,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("flowToId")%>'
],
					colModel:[
					    {name:'planCode',index:'planCode',width:90, editable:true,hidedlg:true,hidden:true},		
			{name:'planCode',index:'planCode', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'distributionPlanId',index:'distributionPlanId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'makeDate',index:'makeDate',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'logisticsCompany',index:'logisticsCompany', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'region',index:'region', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'customerName',index:'customerName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'goodsName',index:'goodsName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'receivingPerson',index:'receivingPerson', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'receivingPersonPhone',index:'receivingPersonPhone', width:90,editable: true,editoptions:{size:"20",maxLength:"20"}},
			{name:'area',index:'area', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'shippingAddress',index:'shippingAddress', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'num',index:'num', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'plannedMileage',index:'plannedMileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}},
			{name:'factMileage',index:'factMileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}},
			{name:'valuationMileage',index:'valuationMileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}},
			{name:'tonKm',index:'tonKm', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'settlementFreight',index:'settlementFreight', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'totalFreight',index:'totalFreight', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'price',index:'price', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'remark',index:'remark', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'sendCarDate',index:'sendCarDate',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'status',index:'status', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'remainderNum',index:'remainderNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'remainderCarNum',index:'remainderCarNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'shipperCode',index:'shipperCode', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'shippingVehiclesNumber',index:'shippingVehiclesNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'shippingNumber',index:'shippingNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'flowToId',index:'flowToId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}}
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
			
					editurl: getRootPath()+'/cardistributionplan/update',//nothing is saved
					caption: "汽运配送计划列表展示",
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
