<%@page import="com.dfhc.base.receiptaddress.IReceiptAddressConstants" %>
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
	 
    var  ids=jQuery("#grid-table").jqGrid("getGridParam","selarrrow");
    if(typeof ids=='undefined' || ids.length==0){
  	  
  	  alert("至少选择一条数据");
  	  return false;
  	  
    }
    ids=ids.join(",");

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

//新增保存
function _saveInsert(){	

	var flag=true;//标志错误信息
	
	$(".notNullA").each(function(){		
		var  val=$(this).val();
		val=$.trim(val);
		if(val==''){
			alert("标注必填的字段不可为空");
			flag= false;
			return false;
		}	
	});	
	if(!flag){
		return flag;
	}else{
		submitInsertAjax('<%=request.getContextPath()%>/receiptaddress/insert',$(insertForm).serialize());
		window.location.reload(true);
	}	
}
//修改
function _saveUpdate(){
	
	var flag=true;//标志错误信息
	
	$(".notNullY").each(function(){		
		var  val=$(this).val();
		val=$.trim(val);
		if(val==''){
			alert("标注必填的字段不可为空");
			flag= false;
			return false;
		}		
	});	
	if(!flag){
		return flag;
	}

	submitInsertAjax('<%=request.getContextPath()%>/receiptaddress/update',$(updateForm).serialize());
}

$(function(){
	     orderStatusShow=[]; 

		 <c:forEach items="${status}" var="listStatus"> 
		     var keyL ='${listStatus.key}';
		     orderStatusShow[keyL]='${listStatus.value}';
		 </c:forEach> 	
})
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
							  <span class="add-on">客户名称</span>	
							  <input type="text" class="m-wrap"  name="userName" readOnly="true" />					
							  <input type="hidden" class="m-wrap"  name="cId" />
							   <input type="hidden" class="m-wrap"  name="userId" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/customer/reference?referenceInputType=radio','insertForm.cId,insertForm.userName,insertForm.userId');">选择</span>
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("recipient")%></span>
							  <input class="m-wrap notNullA" name="recipient"  type="text"  						maxLength="200" style="    width: 200px"/>
							<i class="notNull">*</i>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("recipientPhone")%></span>
							  <input class="m-wrap notNullA" name="recipientPhone"  type="text"  						maxLength="20"  style="    width: 195px"/>
							<i class="notNull">*</i>
							</div>
							
							<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">省</span>
								<select name="provinceId" style="min-width: 200px;" >
									<option></option>
								</select>
									<input class="m-wrap notNullA" name="province" value="" type="hidden"/>
									<i class="notNull">*</i>	
							</div>
							
							<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">市</span>
								<select name="cityId"  style="min-width: 200px;" >
									<option></option>
								</select>	
									<input class="m-wrap notNullA" name="city" value="" type="hidden"/>
									<i class="notNull">*</i>	
							</div>
							
							<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">区县</span>
								<select name="districtCountyId" style="min-width: 200px;" >
									<option></option>
								</select>
								<input class="m-wrap " name="districtCounty" value=""  type="hidden"/>
							
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("address")%></span>
							  <input class="m-wrap" name="address"  type="text"  						maxLength="300"  style="    width: 200px"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("zipcode")%></span>
							  <input class="m-wrap" name="zipcode"  type="text"  						maxLength="6" />
							</div>

							<div class="input-prepend" style="height:45px">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("isDefault")%></span><%=RmJspHelper.getSelectField("isDefault", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("isDefault") + "'  ", true) %>							</div>

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:_saveInsert()">
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
							  <span class="add-on">客户名称</span>	
							  <input type="text" class="m-wrap"  name="userName" readOnly="true" />					
							  <input type="hidden" class="m-wrap"  name="userId" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/customer/reference?referenceInputType=radio','updateForm.userId,updateForm.userName');">选择</span>
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("recipient")%></span>
							  <input class="m-wrap notNullY" name="recipient" id="recipient" type="text"  						maxLength="200" style="    width: 200px"/>
							<i class="notNull">*</i>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("recipientPhone")%></span><input class="m-wrap notNullY" name="recipientPhone" id="recipientPhone" type="text"  						maxLength="20" style="    width: 195px"/>
							<i class="notNull">*</i>
							</div>
							
							<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">省</span>
								<select name="provinceId" style="min-width: 200px;" >
									<option></option>
								</select>
									<input class="m-wrap notNullY" name="province" value="" type="hidden"/>
									<i class="notNull">*</i>	
							</div>
							
							<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">市</span>
								<select name="cityId"  style="min-width: 200px;" >
									<option></option>
								</select>	
									<input class="m-wrap notNullY" name="city" value="" type="hidden"/>
									<i class="notNull">*</i>	
							</div>
							
							<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">区县</span>
								<select name="districtCountyId" style="min-width: 200px;" >
									<option></option>
								</select>
								<input class="m-wrap " name="districtCounty" value=""  type="hidden"/>
								
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("address")%></span><input class="m-wrap" name="address" id="address" type="text"  						maxLength="300" style="    width: 200px"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("zipcode")%></span><input class="m-wrap" name="zipcode" id="zipcode" type="text"  						maxLength="6" />
							</div>

							<div class="input-prepend" style="    height: 45px">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("isDefault")%></span><%=RmJspHelper.getSelectField("isDefault", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("isDefault") + "'  ", true) %>							</div>


							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:_saveUpdate()">
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
							  <span class="add-on">客户名称</span>	
							  <input type="text" class="m-wrap"  name="userName"  readOnly="true"/>					
							  <input type="hidden" class="m-wrap"  name="userId" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio','form.userId,form.userName');">选择</span>
							</div>
						
							<div class="input-prepend">
							  <span class="add-on"><%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("recipient")%></span><input class="m-wrap" name="recipient"  type="text"  						maxLength="200" />
							</div>


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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/receiptaddress/logicDelete?pcode=${pcode }');">
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
								<a href="#">基础信息</a>
							</li>
							<li class="active">收货方维护</li>
						</ul><!-- .breadcrumb -->

					<%-- 	<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->  --%>
					</div>

					<div class="page-content">
						<!-- <div class="page-header">
							<h1>
								收货地址维护
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
					
					url: getRootPath()+'/receiptaddress/ajaxList',
					datatype: "json",
				    mtype: 'POST',  
					height: "100%",
					colNames:[
'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("userId")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("province")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("provinceId")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("city")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("cityId")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("districtCounty")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("districtCountyId")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("address")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("recipient")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("recipientPhone")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("zipcode")%>'
,'<%=IReceiptAddressConstants.TABLE_COLUMN_DISPLAY.get("isDefault")%>'
],
					colModel:[
			{name:'userId',index:'userId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"19"}},
			{name:'province',index:'province', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'provinceId',index:'provinceId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"10"}},
			{name:'city',index:'city', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'cityId',index:'cityId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"10"}},
			{name:'districtCounty',index:'districtCounty', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'districtCountyId',index:'districtCountyId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"10"}},
			{name:'address',index:'address', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'recipient',index:'recipient', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'recipientPhone',index:'recipientPhone', width:90,editable: true,editoptions:{size:"20",maxLength:"20"}},
			{name:'zipcode',index:'zipcode', width:90,editable: true,editoptions:{size:"20",maxLength:"6"}},
			{name:'isDefault',index:'isDefault', width:90,editable: true,editoptions:{size:"20",maxLength:"1"},formatter:orderStatus}
					], 
			                shrinkToFit:true,//不随列数量改变列宽度
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
			
					editurl: getRootPath()+'/receiptaddress/update',//nothing is saved
					caption: "收货地址维护列表展示",
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
			   jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-plus-sign purple",
				      onClickButton: addShow, position:"last" }) ;
			
				//修改
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-pencil blue",
				      onClickButton:updateShow, 
				      position:"last" }) ;
				//删除
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-trash red",
				      onClickButton:deleteShow , 
				      position:"last" }) ;
				
				//查询
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-search orange",
				      onClickButton:search_div , 
				      position:"last" }) ;
				
				//运费维护页面
 				  jQuery(grid_selector).navButtonAdd(pager_selector,
 					{ caption:"",title:"运费维护", buttonicon:"icon-group red",
 					  onClickButton:toAddFreight , 
 				      position:"last" }) ;
				
			     //去运费维护页面
			     function toAddFreight(){
			    	
			    	 var ids=jQuery(grid_selector).jqGrid("getGridParam","selarrrow");
			    	
			    	 if(ids.length==0){
			    		 alert("至少选择一条数据进行维护");
			    		 return false;
			    		 
			    	 }
			    	 if(ids.length>1){
			    		 
			    		 alert("只能选中一条进行维护");
			    		 return false;
			    		 
			    	 }
			    	 
			    	 
			    	 var id=ids[0];
			    	 debugger
			    	 debugger
			    	 window.location.href="${pageContext.request.contextPath}/receiptaddress/toAddFreight/"+id.toString();
			    	 
			    	 
			     }
			     
				//新增弹出
				 function addShow(){
					 loadProvinceList('add_form');
					 
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
                    loadProvinceList('portlet-config-edit');
                  
                    var pointer=$(".cbox:checked").parents("tr");
                    var updateDate=$(pointer).attr("id");
                    $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/receiptaddress/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[name=userName]").val(data.bean.userName);
                      $("#portlet-config-edit").find("input[id=userId]").val(data.bean.userId);
                      $("#portlet-config-edit").find("input[name=province]").val(data.bean.province);
                      $("#portlet-config-edit").find("input[name=provinceId]").val(data.bean.provinceId);
                      $("#portlet-config-edit").find("input[name=city]").val(data.bean.city);
                      $("#portlet-config-edit").find("select[name=cityId]").val(data.bean.cityId);
                      $("#portlet-config-edit").find("input[name=districtCounty]").val(data.bean.districtCounty);
                      $("#portlet-config-edit").find("select[name=districtCountyId]").val(data.bean.districtCountyId);
                      $("#portlet-config-edit").find("input[id=address]").val(data.bean.address);
                      $("#portlet-config-edit").find("input[id=recipient]").val(data.bean.recipient);
                      $("#portlet-config-edit").find("input[id=recipientPhone]").val(data.bean.recipientPhone);
                      $("#portlet-config-edit").find("input[id=zipcode]").val(data.bean.zipcode);
                      $("#portlet-config-edit").find("select[name=isDefault]").val(data.bean.isDefault);
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
                	
			    	 loadProvinceList('portlet-config-query');
	                	
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
			
				var editOrAdd='';
				// 加载省
				function loadProvinceList(obj){
					editOrAdd = obj;
					$.ajax({
			            type: "POST",
			            url: "<%=request.getContextPath()%>/address/getAddressCompany/-1",
			            dataType: "json",
			            async:true , //false为同步
			            success: function(data,status) {
							var addressVos = data.addressVos;
							if(addressVos != null){
								$('#' + obj + ' select[name=provinceId]').empty();
								var html = "<option value=''>请选择省</option>";
								for(var a in addressVos){
									var address = addressVos[a];
									html += "<option value=\"" + address.addressId + "\">" + address.addressName + "</option>";
								}
								$('#' +obj).find('select[name=provinceId]').append(html);
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
				}
				$("select[name=provinceId]").change(function(){
					
					$(this).parents("form").find("input[name='province']").val($(this).find("option:selected").text());
					$(this).parents("form").find("input[name='city']").val('');//清空县级
				
					var provinceId = $(this).val();
					provinceId = $.trim(provinceId);
					$.ajax({
			            type: "POST",
			            url: "<%=request.getContextPath()%>/address/getAddressCompany/" + provinceId,
			            dataType: "json",
			            async:true , //false为同步
			            success: function(data,status) {
							var addressVos = data.addressVos;
							if(addressVos != null){
								$('select[name=cityId]').empty();
								var html = "<option value=''>请选择市</option>";
								for(var a in addressVos){
									var address = addressVos[a];
									html += "<option value=\"" + address.addressId + "\">" + address.addressName + "</option>";
								}
								$('#' + editOrAdd).find("select[name=cityId]").append(html);
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
				})
				$("select[name=cityId]").change(function(){
						
						$(this).parents("form").find("input[name='city']").val($(this).find("option:selected").text());
						$(this).parents("form").find("input[name='districtCounty']").val('');//清空县级
					
						var provinceId = $(this).val();
						provinceId = $.trim(provinceId);
						$.ajax({
				            type: "POST",
				            url: "<%=request.getContextPath()%>/address/getAddressCompany/" + provinceId,
				            dataType: "json",
				            async:true , //false为同步
				            success: function(data,status) {
								var addressVos = data.addressVos;
								if(addressVos != null){
									$('select[name=districtCountyId]').empty();
									var html = "<option value=''>请选择区县</option>";
									for(var a in addressVos){
										var address = addressVos[a];
										html += "<option value=\"" + address.addressId + "\">" + address.addressName + "</option>";
									}
									$('#' + editOrAdd).find("select[name=districtCountyId]").append(html);
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
					})
					$("select[name=districtCountyId]").change(function(){
						
						$(this).parents("form").find("input[name='districtCounty']").val($(this).find("option:selected").text());//清空县级
					})	
					
					function orderStatus(cellvalue, options, cell){

					return orderStatusShow[cellvalue];
				} 
				

			});
		</script>
	</body>
</html>
