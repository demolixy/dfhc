<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：referenceWeighingBill.jsp
   *
   * 功能描述：过磅单参照页面
   * 
   * 版本历史：
   * 2017-01-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page import="com.dfhc.bus.weighingbill.IWeighingBillConstants" %>
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
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%></span><input type="text" class="text_field_reference text_field_readonly"  hiddenInputId="ladingBillId" name="ladingBillIdName" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%>" value="" /><input type="hidden" name="ladingBillId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getReference(new Array(form.ladingBillId, form.ladingBillIdName), '<%=request.getContextPath()%>/', '<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio');"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode" id="ladingBillCode" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("truckNo")%></span><input class="m-wrap" name="truckNo" id="truckNo" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("phoneNumber")%></span><input class="m-wrap" name="phoneNumber" id="phoneNumber" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId" id="productId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span><input class="m-wrap" name="productName" id="productName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%></span><input class="m-wrap" name="productModelNumber" id="productModelNumber" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("tare")%></span><input class="m-wrap" name="tare" id="tare" type="text"  						maxLength="14" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("grossWeight")%></span><input class="m-wrap" name="grossWeight" id="grossWeight" type="text"  						maxLength="14" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("netWeight")%></span><input class="m-wrap" name="netWeight" id="netWeight" type="text"  						maxLength="14" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("firstWeighingTime")%></span><input name="firstWeighingTime" id="firstWeighingTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("firstWeighingTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("twoWeighingTime")%></span><input name="twoWeighingTime" id="twoWeighingTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("twoWeighingTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighmanId")%></span><input class="m-wrap" name="weighmanId" id="weighmanId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman")%></span><input class="m-wrap" name="weighman" id="weighman" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighmanId2")%></span><input class="m-wrap" name="weighmanId2" id="weighmanId2" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman2")%></span><input class="m-wrap" name="weighman2" id="weighman2" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode" id="shipperCode" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("shipper")%></span><input class="m-wrap" name="shipper" id="shipper" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingUnit")%></span><input class="m-wrap" name="receivingUnit" id="receivingUnit" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("transportUnit")%></span><input class="m-wrap" name="transportUnit" id="transportUnit" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("timeToGoOut")%></span><input name="timeToGoOut" id="timeToGoOut" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("timeToGoOut")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("printDate")%></span><input name="printDate" id="printDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("storageTransportationMan")%></span><input class="m-wrap" name="storageTransportationMan" id="storageTransportationMan" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_WEIGHING_BILL_STATUS), "", "inputName='" + IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("storageTransportationId")%></span><input class="m-wrap" name="storageTransportationId" id="storageTransportationId" type="text"  						maxLength="19" />
							</div>

							</div>
			 </div>
     <input type="hidden" name="referenceInputType" value="${referenceInputType}"/>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi('<%=request.getContextPath()%>/weighingbill/reference?referenceInputType=${referenceInputType}');">
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
					
					url: getRootPath()+'/weighingbill/referenceAjax',
					datatype: "json",
				    mtype: 'POST',  
					height: 250,
					colNames:['id',
'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("truckNo")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("phoneNumber")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productId")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("tare")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("grossWeight")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("netWeight")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("firstWeighingTime")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("twoWeighingTime")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighmanId")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighmanId2")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman2")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("shipper")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingUnit")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("transportUnit")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("timeToGoOut")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("storageTransportationMan")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("status")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("storageTransportationId")%>'
],
					colModel:[
					    {name:'id',index:'id',width:90, editable:true,hidedlg:true,hidden:true},		
			{name:'ladingBillId',index:'ladingBillId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'ladingBillCode',index:'ladingBillCode', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'truckNo',index:'truckNo', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'phoneNumber',index:'phoneNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"20"}},
			{name:'productId',index:'productId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'productName',index:'productName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'productModelNumber',index:'productModelNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'tare',index:'tare', width:90,editable: true,editoptions:{size:"20",maxLength:"14"}},
			{name:'grossWeight',index:'grossWeight', width:90,editable: true,editoptions:{size:"20",maxLength:"14"}},
			{name:'netWeight',index:'netWeight', width:90,editable: true,editoptions:{size:"20",maxLength:"14"}},
			{name:'firstWeighingTime',index:'firstWeighingTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'twoWeighingTime',index:'twoWeighingTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'weighmanId',index:'weighmanId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'weighman',index:'weighman', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'weighmanId2',index:'weighmanId2', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'weighman2',index:'weighman2', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'shipperCode',index:'shipperCode', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'shipper',index:'shipper', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'receivingUnit',index:'receivingUnit', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'transportUnit',index:'transportUnit', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'timeToGoOut',index:'timeToGoOut',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'printDate',index:'printDate',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'storageTransportationMan',index:'storageTransportationMan', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'status',index:'status', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'storageTransportationId',index:'storageTransportationId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}}
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
			
					editurl: getRootPath()+'/weighingbill/update',//nothing is saved
					caption: "过磅单列表展示",
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
