<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：referenceLogisticsCostSettlement.jsp
   *
   * 功能描述：物流费用结算审批单参照页面
   * 
   * 版本历史：
   * 2017-01-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page import="com.dfhc.acc.logisticscostsettlement.ILogisticsCostSettlementConstants" %>
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
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("settlementType")%></span><%=RmJspHelper.getSelectField("settlementType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_SETTLEMENT_TYPE), "", "inputName='" + ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("settlementType") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("documentNumber")%></span><input class="m-wrap" name="documentNumber" id="documentNumber" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("settlementStartDate")%></span><input name="settlementStartDate" id="settlementStartDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("settlementStartDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("settlementEndDate")%></span><input name="settlementEndDate" id="settlementEndDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("settlementEndDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%></span><input class="m-wrap" name="logisticsCompany" id="logisticsCompany" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompanyId")%></span><input class="m-wrap" name="logisticsCompanyId" id="logisticsCompanyId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("taxNo")%></span><input class="m-wrap" name="taxNo" id="taxNo" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("address")%></span><input class="m-wrap" name="address" id="address" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("tel")%></span><input class="m-wrap" name="tel" id="tel" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("bank")%></span><input class="m-wrap" name="bank" id="bank" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("accountName")%></span><input class="m-wrap" name="accountName" id="accountName" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("amountPayable")%></span><input class="m-wrap" name="amountPayable" id="amountPayable" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("amountPayableCapital")%></span><input class="m-wrap" name="amountPayableCapital" id="amountPayableCapital" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("remark")%></span>					<textarea class="medium m-wrap" name="remark" id="remark" 					inputName="<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("remark")%>" 						 maxLength="300" 					 ></textarea>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("totalQuantity")%></span><input class="m-wrap" name="totalQuantity" id="totalQuantity" type="text"  						maxLength="14" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("totalValuationMileage")%></span><input class="m-wrap" name="totalValuationMileage" id="totalValuationMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("productTotalCost")%></span><input class="m-wrap" name="productTotalCost" id="productTotalCost" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode" id="shipperCode" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_LOGISTICS_COST_SETTLEMENT_STATUS), "", "inputName='" + ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							</div>
			 </div>
     <input type="hidden" name="referenceInputType" value="${referenceInputType}"/>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi('<%=request.getContextPath()%>/logisticscostsettlement/reference?referenceInputType=${referenceInputType}');">
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
					
					url: getRootPath()+'/logisticscostsettlement/referenceAjax',
					datatype: "json",
				    mtype: 'POST',  
					height: 250,
					colNames:['id',
'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("settlementType")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("documentNumber")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("settlementStartDate")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("settlementEndDate")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompanyId")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("taxNo")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("address")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("tel")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("bank")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("accountName")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("amountPayable")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("amountPayableCapital")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("remark")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("totalQuantity")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("totalValuationMileage")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("productTotalCost")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%>'
,'<%=ILogisticsCostSettlementConstants.TABLE_COLUMN_DISPLAY.get("status")%>'
],
					colModel:[
					    {name:'id',index:'id',width:90, editable:true,hidedlg:true,hidden:true},		
			{name:'settlementType',index:'settlementType', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'documentNumber',index:'documentNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'settlementStartDate',index:'settlementStartDate',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'settlementEndDate',index:'settlementEndDate',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'logisticsCompany',index:'logisticsCompany', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'logisticsCompanyId',index:'logisticsCompanyId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'taxNo',index:'taxNo', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'address',index:'address', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'tel',index:'tel', width:90,editable: true,editoptions:{size:"20",maxLength:"20"}},
			{name:'bank',index:'bank', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'accountName',index:'accountName', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'amountPayable',index:'amountPayable', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'amountPayableCapital',index:'amountPayableCapital', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'remark',index:'remark', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'totalQuantity',index:'totalQuantity', width:90,editable: true,editoptions:{size:"20",maxLength:"14"}},
			{name:'totalValuationMileage',index:'totalValuationMileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}},
			{name:'productTotalCost',index:'productTotalCost', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'shipperCode',index:'shipperCode', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'status',index:'status', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}}
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
			
					editurl: getRootPath()+'/logisticscostsettlement/update',//nothing is saved
					caption: "物流费用结算审批单列表展示",
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