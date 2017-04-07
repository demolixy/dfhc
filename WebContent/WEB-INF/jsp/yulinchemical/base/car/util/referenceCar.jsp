<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：referenceCar.jsp
   *
   * 功能描述：车辆信息参照页面
   * 
   * 版本历史：
   * 2017-01-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page import="com.dfhc.base.car.ICarConstants" %>
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
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("truckNo")%></span><input class="m-wrap" name="truckNo" id="truckNo" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("grand")%></span><input class="m-wrap" name="grand" id="grand" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("model")%></span><input class="m-wrap" name="model" id="model" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("color")%></span><input class="m-wrap" name="color" id="color" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("modelSerial")%></span><input class="m-wrap" name="modelSerial" id="modelSerial" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("engineNo")%></span><input class="m-wrap" name="engineNo" id="engineNo" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("certDept")%></span><input class="m-wrap" name="certDept" id="certDept" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("emptyWeight")%></span><input class="m-wrap" name="emptyWeight" id="emptyWeight" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("owner")%></span><input class="m-wrap" name="owner" id="owner" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("driveNo")%></span><input class="m-wrap" name="driveNo" id="driveNo" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("driveType")%></span><input class="m-wrap" name="driveType" id="driveType" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("contact")%></span><input class="m-wrap" name="contact" id="contact" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("gender")%></span><input class="m-wrap" name="gender" id="gender" type="text"  						maxLength="2" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("idNo")%></span><input class="m-wrap" name="idNo" id="idNo" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("axleNum")%></span><input class="m-wrap" name="axleNum" id="axleNum" type="text"  						maxLength="5" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("picFront")%></span><input class="m-wrap" name="picFront" id="picFront" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarConstants.TABLE_COLUMN_DISPLAY.get("picBack")%></span><input class="m-wrap" name="picBack" id="picBack" type="text"  						maxLength="300" />
							</div>

							</div>
			 </div>
     <input type="hidden" name="referenceInputType" value="${referenceInputType}"/>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi('<%=request.getContextPath()%>/car/reference?referenceInputType=${referenceInputType}');">
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
					
					url: getRootPath()+'/car/referenceAjax',
					datatype: "json",
				    mtype: 'POST',  
					height: 250,
					colNames:['id',
'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("truckNo")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("grand")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("model")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("color")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("modelSerial")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("engineNo")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("certDept")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("emptyWeight")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("owner")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("driveNo")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("driveType")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("contact")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("gender")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("idNo")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("axleNum")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("picFront")%>'
,'<%=ICarConstants.TABLE_COLUMN_DISPLAY.get("picBack")%>'
],
					colModel:[
					    {name:'id',index:'id',width:90, editable:true,hidedlg:true,hidden:true},		
			{name:'truckNo',index:'truckNo', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'grand',index:'grand', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'model',index:'model', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'color',index:'color', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'modelSerial',index:'modelSerial', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'engineNo',index:'engineNo', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'certDept',index:'certDept', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'emptyWeight',index:'emptyWeight', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'owner',index:'owner', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'driveNo',index:'driveNo', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'driveType',index:'driveType', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'contact',index:'contact', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'gender',index:'gender', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'idNo',index:'idNo', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'axleNum',index:'axleNum', width:90,editable: true,editoptions:{size:"20",maxLength:"5"}},
			{name:'picFront',index:'picFront', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'picBack',index:'picBack', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}}
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
			
					editurl: getRootPath()+'/car/update',//nothing is saved
					caption: "车辆信息列表展示",
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
