<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：referenceProduct.jsp
   *
   * 功能描述：产品管理参照页面
   * 
   * 版本历史：
   * 2017-01-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page import="com.dfhc.base.product.IProductConstants" %>
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
var objKeys=['displayName','productTypeId', 'price'];
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

//状态汉化

 orderStatusShow=[]; 

<c:forEach items="${status}" var="listStatus"> 
 
 orderStatusShow['${listStatus.key}']='${listStatus.value}';
    
</c:forEach> 

<c:forEach items="${statusType}" var="listStatus"> 
 
 orderStatusShow['${listStatus.key}']='${listStatus.value}';
    
</c:forEach> 	

function orderStatus(cellvalue, options, cell){

	return orderStatusShow[cellvalue];
}


orderStatusluShow=[]; 

<c:forEach items="${statusMode}" var="listStatus"> 

orderStatusluShow['${listStatus.key}']='${listStatus.value}';

</c:forEach> 

function orderStatuslu(cellvalue, options, cell){

	return orderStatusluShow[cellvalue];
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
							  <span class="add-on">产品类别</span><input type="hidden" class="m-wrap"  name="productTypeId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/producttype/reference?referenceInputType=radio','insertForm.productTypeId,insertForm.productTypeIdName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span><input class="m-wrap" name="productName" id="productName" type="text"  						maxLength="200" />
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("indexCode")%></span><input class="m-wrap" name="indexCode" id="indexCode" type="text"  						maxLength="200" />
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("spellBrevityCode")%></span><input class="m-wrap" name="productName" id="spellBrevityCode" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("storeMode")%></span><%=RmJspHelper.getSelectField("storeMode", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_STORE_MODE), "", "inputName='" + IProductConstants.TABLE_COLUMN_DISPLAY.get("storeMode") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("isCtrlBusNum")%></span><%=RmJspHelper.getSelectField("isCtrlBusNum", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IProductConstants.TABLE_COLUMN_DISPLAY.get("isCtrlBusNum") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("maxBusNumInFactory")%></span><input class="m-wrap" name="maxBusNumInFactory" id="maxBusNumInFactory" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("maxBusNumInPound")%></span><input class="m-wrap" name="maxBusNumInPound" id="maxBusNumInPound" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("isPound")%></span><%=RmJspHelper.getSelectField("isPound", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IProductConstants.TABLE_COLUMN_DISPLAY.get("isPound") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("isLoading")%></span><%=RmJspHelper.getSelectField("isLoading", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IProductConstants.TABLE_COLUMN_DISPLAY.get("isLoading") + "'  ", true) %>							</div>
								
								
							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("mainCode")%></span><%=RmJspHelper.getSelectField("isLoading", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IProductConstants.TABLE_COLUMN_DISPLAY.get("isLoading") + "'  ", true) %>							</div>
								
							<div class="input-prepend">
							  <span class="add-on"><%=IProductConstants.TABLE_COLUMN_DISPLAY.get("main_secondary_product_flag")%></span><%=RmJspHelper.getSelectField("main_secondary_product_flag", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IProductConstants.TABLE_COLUMN_DISPLAY.get("main_secondary_product_flag") + "'  ", true) %>							</div>
								
					</div>
			 </div>
     <input type="hidden" name="referenceInputType" value="${referenceInputType}"/>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi('<%=request.getContextPath()%>/product/reference?referenceInputType=${referenceInputType}');">
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
					
					url: getRootPath()+'/product/referenceAjax?' + '${paramly}',
					datatype: "json",
				    mtype: 'POST',  
					height: '100%',
					colNames:['productName','productTypeId','price',
							'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("productTypeId")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("productName")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("indexCode")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("spellBrevityCode")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("storeMode")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("isCtrlBusNum")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("maxBusNumInFactory")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("maxBusNumInPound")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("isPound")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("isLoading")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("mainCode")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("main_secondary_product_flag")%>'
							,'<%=IProductConstants.TABLE_COLUMN_DISPLAY.get("bag_weight")%>'
							],
					colModel:[
					    {name:'productName',index:'productName',width:90, editable:true,hidedlg:true,hidden:true},
					    {name:'productTypeId',index:'productTypeId',width:90, editable:true,hidedlg:true,hidden:true},
					    {name:'price',index:'price',width:90, editable:true,hidedlg:true,hidden:true},
						{name:'productTypeId',index:'productTypeId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"19"}},
						{name:'productName',index:'productName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
						{name:'indexCode',index:'indexCode', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
						{name:'spellBrevityCode',index:'spellBrevityCode', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
						{name:'storeMode',index:'storeMode', width:90,editable: true,editoptions:{size:"20",maxLength:"2"},formatter:orderStatuslu},
						{name:'isCtrlBusNum',index:'isCtrlBusNum', width:90,editable: true,editoptions:{size:"20",maxLength:"1"},formatter:orderStatus},
						{name:'maxBusNumInFactory',index:'maxBusNumInFactory', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
						{name:'maxBusNumInPound',index:'maxBusNumInPound', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
						{name:'isPound',index:'isPound', width:90,editable: true,editoptions:{size:"20",maxLength:"1"},formatter:orderStatus},
						{name:'isLoading',index:'isLoading', width:90,editable: true,editoptions:{size:"20",maxLength:"1"},formatter:orderStatus},
						{name:'mainCode',index:'mainCode', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
						{name:'main_secondary_product_flag',index:'main_secondary_product_flag', width:90,editable: true,editoptions:{size:"20",maxLength:"2"},formatter:orderStatus},
						{name:'bag_weight',index:'bag_weight', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}}
						 ], 	
// 		                        shrinkToFit:false,
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
			
					editurl: getRootPath()+'/product/update',//nothing is saved
					caption: "产品管理列表展示",
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
