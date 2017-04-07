<%@page import="com.dfhc.acc.customerbalancechange.ICustomerBalanceChangeConstants" %>
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
//删除数据
function _delete(urlData){
      //var pointer=$(".cbox:checked").parents("tr");
      
      var  ids="";
      var len = $(".cbox:checked").length;
      var count=1;
      $(".cbox:checked").each(function(){
      	
      	var dataId =$(this).parents("tr").attr("id");
      	ids+=dataId
      	if(len!=count){
      		ids+=","
      	}
      	count++;
      	
      });
  
      deleteForm.action=urlData+"&ids="+ids;
      deleteForm.submit();		
     
	 
}
		     
		     
//查询操作
function  searchShi(){
	jQuery("#grid-table").jqGrid("setGridParam",
	{
	       datatype:"json",
	       postData:$(form).serialize(), //发送数据  
	       page:"1"
	}).trigger("reloadGrid");
		 
	   
	$(".ui_inner_tan").hide();
   	$(".ui_out_tan").hide();	 
}

</script>
</head>
<body>
	
	
 <!--begin  新增 -->
	
  <div class="ui-widget-overlay ui_out_tan" ></div>

  <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="add_form" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
  <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">新增</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
   <div class="modal-body">
   <form name="insertForm" id="insertForm" class="FormGrid ui_inner_form" >   
   <div class="control-group">
			 <div class="controls">
							

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("businessType")%></span><%=RmJspHelper.getSelectField("businessType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE), "", "inputName='" + ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("businessType") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("financialBookValueId")%></span>						<input type="text" class="m-wrap"  name="financialBookValueId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/financialbookvalue/reference?referenceInputType=radio','insertForm.financialBookValueId,insertForm.financialBookValueIdName');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("customerId")%></span><input class="m-wrap" name="customerId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("customerName")%></span><input class="m-wrap" name="customerName"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("oldBalance")%></span><input class="m-wrap" name="oldBalance"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("accrual")%></span><input class="m-wrap" name="accrual"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("nowBalance")%></span><input class="m-wrap" name="nowBalance"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("digest")%></span><input class="m-wrap" name="digest"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("relevanceBillsId")%></span><input class="m-wrap" name="relevanceBillsId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("amountDirection")%></span><%=RmJspHelper.getSelectField("amountDirection", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION), "", "inputName='" + ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("amountDirection") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("signature")%></span><input class="m-wrap" name="signature"  type="text"  						maxLength="32" />
							</div>

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/customerbalancechange/insert',$(insertForm).serialize())">
       <i class="icon-ok"></i>
               保存</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div><div class="jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div></div>
	
	<!-- end 新增 -->
	
	<!-- begin 修改 -->
	
	 <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-edit" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
      <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">修改</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
   <div class="modal-body">
   <form name="updateForm" id="updateForm" class="FormGrid ui_inner_form" >
   
           <div class="control-group">
							<div class="controls">
							<input id="id" name="id" type="hidden" />

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("businessType")%></span><%=RmJspHelper.getSelectField("businessType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE), "", "inputName='" + ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("businessType") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("financialBookValueId")%></span>						<input type="text" class="m-wrap"  name="financialBookValueId" id="financialBookValueId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/financialbookvalue/reference?referenceInputType=radio','insertForm.financialBookValueId,insertForm.financialBookValueIdName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("customerId")%></span><input class="m-wrap" name="customerId" id="customerId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("customerName")%></span><input class="m-wrap" name="customerName" id="customerName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("oldBalance")%></span><input class="m-wrap" name="oldBalance" id="oldBalance" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("accrual")%></span><input class="m-wrap" name="accrual" id="accrual" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("nowBalance")%></span><input class="m-wrap" name="nowBalance" id="nowBalance" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("digest")%></span><input class="m-wrap" name="digest" id="digest" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("relevanceBillsId")%></span><input class="m-wrap" name="relevanceBillsId" id="relevanceBillsId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("amountDirection")%></span><%=RmJspHelper.getSelectField("amountDirection", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION), "", "inputName='" + ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("amountDirection") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("signature")%></span><input class="m-wrap" name="signature" id="signature" type="text"  						maxLength="32" />
							</div>


							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/customerbalancechange/update',$(updateForm).serialize())">
       <i class="icon-ok"></i>
            更新</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div><div class="jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div></div>
	

    <!-- end  修改 -->	
    <!-- begin 查询 -->
    
     <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-query" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
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
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("businessType")%></span><%=RmJspHelper.getSelectField("businessType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_ACC_CHANGE_BUSTYPE), "", "inputName='" + ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("businessType") + "'  ", true) %>							</div>

<!-- 							<div class="input-prepend"> -->
<%-- 							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("financialBookValueId")%></span>						<input type="text" class="m-wrap"  name="financialBookValueId" /> --%>
<%-- 								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/financialbookvalue/reference?referenceInputType=radio','form.financialBookValueId,form.financialBookValueIdName');">选择</span> --%>
<!-- 							</div> -->


							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("customerName")%></span>
							  <input class="m-wrap" name="customerName"  type="text"  						maxLength="200" />
								<input class="m-wrap" name="customerId"  type="hidden"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio&userType=<%=ISystemConstant.DICTIONARY_USER_TYPE_0 %>','form.customerId,form.customerName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("oldBalance")%></span><input class="m-wrap" name="oldBalance"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("accrual")%></span><input class="m-wrap" name="accrual"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("nowBalance")%></span><input class="m-wrap" name="nowBalance"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("digest")%></span><input class="m-wrap" name="digest"  type="text"  						maxLength="300" />
							</div>

<!-- 							<div class="input-prepend"> -->
<%-- 							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("relevanceBillsId")%></span><input class="m-wrap" name="relevanceBillsId"  type="text"  						maxLength="19" /> --%>
<!-- 							</div> -->

							<div class="input-prepend">
							  <span class="add-on"><%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("amountDirection")%></span><%=RmJspHelper.getSelectField("amountDirection", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_AMOUNT_DIRECTION), "", "inputName='" + ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("amountDirection") + "'  ", true) %>							</div>



							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi();">
       <i class="icon-search"></i>
           查询</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div>
     
     </div>
     
    
    
    <!-- end 查询 -->

    <!-- begin 删除 -->
      <div class="ui-widget ui-widget-content ui-corner-all ui-jqdialog jqmID1 ui_inner_tan" id="portlet-config-delete" dir="ltr" tabindex="-1" role="dialog" aria-labelledby="edithdgrid-table" aria-hidden="false" >
      <div class="ui-jqdialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"  style="cursor: move;">
	   <span class="ui-jqdialog-title" style="float: left;">删除选中的数据</span>
	   <a href="javascript:void(0)" class="cancel ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;">
	    <span class="ui-icon ui-icon-closethick"></span>
	   </a>
   </div>
   <div class="ui-jqdialog-content ui-widget-content" id="editcntgrid-table"><div>
   <div class="modal-body">
   <form name="deleteForm" id="deleteForm" class="FormGrid ui_inner_form"  method="post"  >
   
           <div class="control-group">
				<span>确认删除选中的数据？</span>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/customerbalancechange/logicDelete?pcode=${pcode }');">
       <i class="icon-trash"></i>
           确认删除</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div><div class="jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div></div>
     
    <!-- end 删除 -->
	
	   <!-- begin  头部 -->
	   
	  <%@ include file="/jsp/include/web/jqGrid/header.jsp" %> 
	
		
	   <!-- end 头部 -->

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
				
				
                <!-- begin 左侧菜单 -->
			   
			   <%@ include file="/jsp/include/web/jqGrid/leftSide.jsp"%>
			   
			   

                 <!-- end 左侧菜单 -->
                 
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{
							  ace.settings.check('breadcrumbs' , 'fixed')
							}catch(e){
                                                           }
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>

							<li>
								<a href="#">副产品结算业务</a>
							</li>
							<li class="active">客户财务余额变动</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
<!-- 								<span class="input-icon"> -->
<!-- 									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" /> -->
<!-- 									<i class="icon-search nav-search-icon"></i> -->
<!-- 								</span> -->
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<!-- <div class="page-header">
							<h1>
								客户余额变动
								<small>
									<i class="icon-double-angle-right"></i>
									数据列表展示
								</small>
							</h1>
						</div> page-header -->

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
					
					url: getRootPath()+'/customerbalancechange/ajaxList',
					datatype: "json",
				    mtype: 'POST',  
					height: '100%',
					colNames:[
'<%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("businessType")%>'
,'<%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("customerName")%>'
,'<%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("oldBalance")%>'
,'<%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("accrual")%>'
,'<%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("nowBalance")%>'
,'<%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("digest")%>'
,'<%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("relevanceBillsId")%>'
,'<%=ICustomerBalanceChangeConstants.TABLE_COLUMN_DISPLAY.get("amountDirection")%>'
],
					colModel:[
			{name:'businessType',index:'businessType', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'customerName',index:'customerName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'oldBalance',index:'oldBalance', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'accrual',index:'accrual', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'nowBalance',index:'nowBalance', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'digest',index:'digest', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'relevanceBillsId',index:'relevanceBillsId', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'amountDirection',index:'amountDirection', width:90,editable: true,editoptions:{size:"20",maxLength:"2"},formatter:amountDirection},
					], 
			                shrinkToFit:true,//随列数量改变列宽度
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
			
					editurl: getRootPath()+'/customerbalancechange/update',//nothing is saved
					caption: "客户余额变动列表展示",
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
				
				//新增
// 			   jQuery(grid_selector) .navButtonAdd(pager_selector,
// 					{ caption:"", buttonicon:"icon-plus-sign purple",
// 				      onClickButton: addShow, position:"last" }) ;
			
// 				//修改
// 				  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 					{ caption:"", buttonicon:"icon-pencil blue",
// 				      onClickButton:updateShow, 
// 				      position:"last" }) ;
// 				//删除
// 				  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 					{ caption:"", buttonicon:"icon-trash red",
// 				      onClickButton:deleteShow , 
// 				      position:"last" }) ;
				
				//查询
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-search orange",
				      onClickButton:search_div , 
				      position:"last" }) ;
				
			     //新增弹出
			     
				 function addShow(){
			    	 
			    	 $(".ui_out_tan").show();
			    	 $("#add_form").show();
					 
				 }
			     
			     //  修改弹出
			     function updateShow(){
			    	 
			    	var checkedLen= $(".cbox:checked").length;
			    	if(checkedLen==0){
			    		
			    		alert("请至少选择一条数据！");
			    		return false;
			    	}
                    if(checkedLen>1){
			    		
			    		alert("最多只能选择一条数据！");
			    		return false;
			    	}
                    var pointer=$(".cbox:checked").parents("tr");
                    var updateDate=$(pointer).attr("id");
                    $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/customerbalancechange/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=businessType]").val(data.bean.businessType);
                      $("#portlet-config-edit").find("input[id=financialBookValueId]").val(data.bean.financialBookValueId);
                      $("#portlet-config-edit").find("input[id=customerId]").val(data.bean.customerId);
                      $("#portlet-config-edit").find("input[id=customerName]").val(data.bean.customerName);
                      $("#portlet-config-edit").find("input[id=oldBalance]").val(data.bean.oldBalance);
                      $("#portlet-config-edit").find("input[id=accrual]").val(data.bean.accrual);
                      $("#portlet-config-edit").find("input[id=nowBalance]").val(data.bean.nowBalance);
                      $("#portlet-config-edit").find("input[id=digest]").val(data.bean.digest);
                      $("#portlet-config-edit").find("input[id=relevanceBillsId]").val(data.bean.relevanceBillsId);
                      $("#portlet-config-edit").find("input[id=amountDirection]").val(data.bean.amountDirection);
                      $("#portlet-config-edit").find("input[id=signature]").val(data.bean.signature);
                	   	   }
                       },
                       error:function(xhr,s1,s2){
                       	var message = xhr.responseText;
               			eval("var json = "+message);
               			if($.trim(json.error).length==0){
               				alert('数据异常,请刷新页面重试');
               			}else{
               				alert(json.error);
               			}
                  	   }
                    });
			    	 $(".ui_out_tan").show();
                     $("#portlet-config-edit").show();
			    	 
			     }
			     
			     //删除弹出
			     function deleteShow (){
			    	 
			    	 var checkedLen= $(".cbox:checked").length;
				    	if(checkedLen==0){
				    		
				    		alert("请至少选择一条数据！");
				    		return false;
				    	}
				    	$(".ui_out_tan").show();
				        $("#portlet-config-delete").show(); 
				    	
			    	 
			     }
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
