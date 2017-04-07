<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：referenceUser.jsp
   *
   * 功能描述：用户表参照页面
   * 
   * 版本历史：
   * 2017-01-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page import="com.dfhc.rm.user.IUserConstants" %>
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
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("name")%></span><input class="m-wrap" name="name" id="name" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lockStatus")%></span><%=RmJspHelper.getSelectField("lockStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_LOCK_STATUS), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("lockStatus") + "' validate='notNull;' ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginId")%></span><input class="m-wrap" name="loginId" id="loginId" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("password")%></span><input class="m-wrap" name="password" id="password" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("authenType")%></span><input class="m-wrap" name="authenType" id="authenType" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("organizationId")%></span><input class="m-wrap" name="organizationId" id="organizationId" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("employeeId")%></span><input class="m-wrap" name="employeeId" id="employeeId" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("email")%></span><input class="m-wrap" name="email" id="email" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("adminType")%></span><%=RmJspHelper.getSelectField("adminType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_ADMIN_TYPE), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("adminType") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("description")%></span><input class="m-wrap" name="description" id="description" type="text"  						maxLength="1000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("agentStatus")%></span><%=RmJspHelper.getSelectField("agentStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_AGENT_STATUS), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("agentStatus") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginStatus")%></span><%=RmJspHelper.getSelectField("loginStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("loginStatus") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginDate")%></span><input name="lastLoginDate" id="lastLoginDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginIp")%></span><input class="m-wrap" name="lastLoginIp" id="lastLoginIp" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginSum")%></span><input class="m-wrap" name="loginSum" id="loginSum" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastCustomCss")%></span><input class="m-wrap" name="lastCustomCss" id="lastCustomCss" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("isAffix")%></span><%=RmJspHelper.getSelectField("isAffix", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("isAffix") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("functionPermission")%></span><input class="m-wrap" name="functionPermission" id="functionPermission" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("dataPermission")%></span><input class="m-wrap" name="dataPermission" id="dataPermission" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom1")%></span><input class="m-wrap" name="custom1" id="custom1" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom2")%></span><input class="m-wrap" name="custom2" id="custom2" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom3")%></span><input class="m-wrap" name="custom3" id="custom3" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom4")%></span><input class="m-wrap" name="custom4" id="custom4" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom5")%></span><input class="m-wrap" name="custom5" id="custom5" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("customXml")%></span><input class="m-wrap" name="customXml" id="customXml" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("userType")%></span><%=RmJspHelper.getSelectField("userType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_USER_TYPE), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("userType") + "'  ", true) %>							</div>

							</div>
			 </div>
     <input type="hidden" name="referenceInputType" value="${referenceInputType}"/>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi('<%=request.getContextPath()%>/user/reference?referenceInputType=${referenceInputType}');">
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
					
					url: getRootPath()+'/user/referenceAjax?' + '${queryString}',
					datatype: "json",
				    mtype: 'POST',  
					height: 250,
					colNames:[ 'displayName',
								'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("name")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lockStatus")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginId")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("password")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("authenType")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("organizationId")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("employeeId")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("email")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("adminType")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("description")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("agentStatus")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginStatus")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginDate")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginIp")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginSum")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastCustomCss")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("isAffix")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("functionPermission")%>'
								,'<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("dataPermission")%>'
								
								],
					colModel:[
					    {name:'name',index:'name',width:90, editable:true,hidedlg:true,hidden:true},		
			{name:'name',index:'name', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'lockStatus',index:'lockStatus', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"1"}},
			{name:'loginId',index:'loginId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"200"}},
			{name:'password',index:'password', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"200"}},
			{name:'authenType',index:'authenType', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"200"}},
			{name:'organizationId',index:'organizationId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"200"}},
			{name:'employeeId',index:'employeeId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"50"}},
			{name:'email',index:'email', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'adminType',index:'adminType', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"2"}},
			{name:'description',index:'description', width:90,editable: true,editoptions:{size:"20",maxLength:"1000"}},
			{name:'agentStatus',index:'agentStatus', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"1"}},
			{name:'loginStatus',index:'loginStatus', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"1"}},
			{name:'lastLoginDate',index:'lastLoginDate',width:90, editable:true, hidedlg:true,hidden:true,sorttype:"date",unformat: pickDate}
,
			{name:'lastLoginIp',index:'lastLoginIp', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"50"}},
			{name:'loginSum',index:'loginSum', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20"}},
			{name:'lastCustomCss',index:'lastCustomCss', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"200"}},
			{name:'isAffix',index:'isAffix', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"1"}},
			{name:'functionPermission',index:'functionPermission', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"4000"}},
			{name:'dataPermission',index:'dataPermission', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"4000"}},
			
					], 	
		                        /* shrinkToFit:false, */
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
			
					editurl: getRootPath()+'/user/update',//nothing is saved
					caption: "用户表列表展示",
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
