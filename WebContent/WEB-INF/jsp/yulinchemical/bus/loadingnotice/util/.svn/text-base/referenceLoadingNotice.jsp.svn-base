<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：referenceLoadingNotice.jsp
   *
   * 功能描述：装车通知单参照页面
   * 
   * 版本历史：
   * 2017-01-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page import="com.dfhc.bus.loadingnotice.ILoadingNoticeConstants" %>
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
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%></span><input type="text" class="text_field_reference text_field_readonly"  hiddenInputId="ladingBillId" name="ladingBillIdName" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%>" value="" /><input type="hidden" name="ladingBillId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getReference(new Array(form.ladingBillId, form.ladingBillIdName), '<%=request.getContextPath()%>/', '<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio');"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode" id="ladingBillCode" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("jobTime")%></span><input name="jobTime" id="jobTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("jobTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("dispatchTime")%></span><input name="dispatchTime" id="dispatchTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("dispatchTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%></span><input class="m-wrap" name="licensePlate" id="licensePlate" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId" id="productId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span><input class="m-wrap" name="productName" id="productName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%></span><input class="m-wrap" name="productModelNumber" id="productModelNumber" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productBatchNumber")%></span><input class="m-wrap" name="productBatchNumber" id="productBatchNumber" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productLevel")%></span><input class="m-wrap" name="productLevel" id="productLevel" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productLevelId")%></span><input class="m-wrap" name="productLevelId" id="productLevelId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("num")%></span><input class="m-wrap" name="num" id="num" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("gateNum")%></span><input class="m-wrap" name="gateNum" id="gateNum" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("positionNum")%></span><input class="m-wrap" name="positionNum" id="positionNum" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("toneNum")%></span><input class="m-wrap" name="toneNum" id="toneNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("storemanId")%></span><input class="m-wrap" name="storemanId" id="storemanId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("storeman")%></span><input class="m-wrap" name="storeman" id="storeman" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftMonitorId")%></span><input class="m-wrap" name="forkliftMonitorId" id="forkliftMonitorId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftMonitor")%></span><input class="m-wrap" name="forkliftMonitor" id="forkliftMonitor" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("operationSupervisorId")%></span><input class="m-wrap" name="operationSupervisorId" id="operationSupervisorId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("operationSupervisor")%></span><input class="m-wrap" name="operationSupervisor" id="operationSupervisor" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS), "", "inputName='" + ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("printDate")%></span><input name="printDate" id="printDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckIsConfirm")%></span><input class="m-wrap" name="forkliftTruckIsConfirm" id="forkliftTruckIsConfirm" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorIsConfirm")%></span><input class="m-wrap" name="supervisorIsConfirm" id="supervisorIsConfirm" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckConfirmTime")%></span><input name="forkliftTruckConfirmTime" id="forkliftTruckConfirmTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckConfirmTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorConfirmTime")%></span><input name="supervisorConfirmTime" id="supervisorConfirmTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorConfirmTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("truckInputBreakBagNum")%></span><input class="m-wrap" name="truckInputBreakBagNum" id="truckInputBreakBagNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorInputBreakBagNum")%></span><input class="m-wrap" name="supervisorInputBreakBagNum" id="supervisorInputBreakBagNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("actualDeliveryNumber")%></span><input class="m-wrap" name="actualDeliveryNumber" id="actualDeliveryNumber" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("isNeedWeigh")%></span><input class="m-wrap" name="isNeedWeigh" id="isNeedWeigh" type="text"  						maxLength="1" />
							</div>

							</div>
			 </div>
     <input type="hidden" name="referenceInputType" value="${referenceInputType}"/>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi('<%=request.getContextPath()%>/loadingnotice/reference?referenceInputType=${referenceInputType}');">
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
					
					url: getRootPath()+'/loadingnotice/referenceAjax',
					datatype: "json",
				    mtype: 'POST',  
					height: 250,
					colNames:['id',
'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("jobTime")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("dispatchTime")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productId")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productName")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productBatchNumber")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productLevel")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productLevelId")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("num")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("gateNum")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("positionNum")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("toneNum")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("storemanId")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("storeman")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftMonitorId")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftMonitor")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("operationSupervisorId")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("operationSupervisor")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("status")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckIsConfirm")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorIsConfirm")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckConfirmTime")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorConfirmTime")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("truckInputBreakBagNum")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorInputBreakBagNum")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("actualDeliveryNumber")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("isNeedWeigh")%>'
],
					colModel:[
					    {name:'id',index:'id',width:90, editable:true,hidedlg:true,hidden:true},		
			{name:'ladingBillId',index:'ladingBillId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'ladingBillCode',index:'ladingBillCode', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'jobTime',index:'jobTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'dispatchTime',index:'dispatchTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'licensePlate',index:'licensePlate', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'productId',index:'productId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'productName',index:'productName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'productModelNumber',index:'productModelNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'productBatchNumber',index:'productBatchNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'productLevel',index:'productLevel', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'productLevelId',index:'productLevelId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'num',index:'num', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'gateNum',index:'gateNum', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'positionNum',index:'positionNum', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'toneNum',index:'toneNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'storemanId',index:'storemanId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'storeman',index:'storeman', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'forkliftMonitorId',index:'forkliftMonitorId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'forkliftMonitor',index:'forkliftMonitor', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'operationSupervisorId',index:'operationSupervisorId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'operationSupervisor',index:'operationSupervisor', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'status',index:'status', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'printDate',index:'printDate',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'forkliftTruckIsConfirm',index:'forkliftTruckIsConfirm', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'supervisorIsConfirm',index:'supervisorIsConfirm', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'forkliftTruckConfirmTime',index:'forkliftTruckConfirmTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'supervisorConfirmTime',index:'supervisorConfirmTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'truckInputBreakBagNum',index:'truckInputBreakBagNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'supervisorInputBreakBagNum',index:'supervisorInputBreakBagNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'actualDeliveryNumber',index:'actualDeliveryNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'isNeedWeigh',index:'isNeedWeigh', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}}
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
			
					editurl: getRootPath()+'/loadingnotice/update',//nothing is saved
					caption: "装车通知单列表展示",
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
