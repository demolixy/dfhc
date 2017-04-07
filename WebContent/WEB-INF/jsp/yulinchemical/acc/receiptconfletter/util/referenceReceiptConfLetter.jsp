<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：referenceReceiptConfLetter.jsp
   *
   * 功能描述：收货确认函参照页面
   * 
   * 版本历史：
   * 2017-01-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page import="com.dfhc.acc.receiptconfletter.IReceiptConfLetterConstants" %>
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
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("documentNumber")%></span><input class="m-wrap" name="documentNumber" id="documentNumber" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("customerId")%></span><input class="m-wrap" name="customerId" id="customerId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("customerName")%></span><input class="m-wrap" name="customerName" id="customerName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("settlementStartDate")%></span><input name="settlementStartDate" id="settlementStartDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("settlementStartDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("settlementEndDate")%></span><input name="settlementEndDate" id="settlementEndDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("settlementEndDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("applyPerson")%></span><input class="m-wrap" name="applyPerson" id="applyPerson" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("applyPersionId")%></span><input class="m-wrap" name="applyPersionId" id="applyPersionId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("applyTime")%></span><input name="applyTime" id="applyTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("applyTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("totalAmount")%></span><input class="m-wrap" name="totalAmount" id="totalAmount" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("bookBalance")%></span><input class="m-wrap" name="bookBalance" id="bookBalance" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode" id="shipperCode" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_CONFIRMATION_LETTER_STATUS), "", "inputName='" + IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("actualSettlementAmount")%></span><input class="m-wrap" name="actualSettlementAmount" id="actualSettlementAmount" type="text"  						maxLength="19" />
							</div>

							</div>
			 </div>
     <input type="hidden" name="referenceInputType" value="${referenceInputType}"/>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi('<%=request.getContextPath()%>/receiptconfletter/reference?referenceInputType=${referenceInputType}');">
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
					
					url: getRootPath()+'/receiptconfletter/referenceAjax',
					datatype: "json",
				    mtype: 'POST',  
					height: 250,
					colNames:['id',
'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("documentNumber")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("customerId")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("customerName")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("settlementStartDate")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("settlementEndDate")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("applyPerson")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("applyPersionId")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("applyTime")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("totalAmount")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("bookBalance")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("status")%>'
,'<%=IReceiptConfLetterConstants.TABLE_COLUMN_DISPLAY.get("actualSettlementAmount")%>'
],
					colModel:[
					    {name:'id',index:'id',width:90, editable:true,hidedlg:true,hidden:true},		
			{name:'documentNumber',index:'documentNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'customerId',index:'customerId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'customerName',index:'customerName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'settlementStartDate',index:'settlementStartDate',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'settlementEndDate',index:'settlementEndDate',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'applyPerson',index:'applyPerson', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'applyPersionId',index:'applyPersionId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'applyTime',index:'applyTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'totalAmount',index:'totalAmount', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'bookBalance',index:'bookBalance', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'shipperCode',index:'shipperCode', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'status',index:'status', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'actualSettlementAmount',index:'actualSettlementAmount', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}}
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
			
					editurl: getRootPath()+'/receiptconfletter/update',//nothing is saved
					caption: "收货确认函列表展示",
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
