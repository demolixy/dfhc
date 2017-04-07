<%@page import="com.dfhc.bus.order.IOrderConstants" %>
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


$(function(){
	     orderStatusShow=[]; 

		 <c:forEach items="${ORDER_STATUS}" var="listStatus"> 
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
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("orderNum")%></span><input class="m-wrap" name="orderNum"  type="text"  						maxLength="13" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("customerId")%></span><input class="m-wrap" name="customerId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("customerName")%></span><input class="m-wrap" name="customerName"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryStartDate")%></span><input name="deliveryStartDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryStartDate")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryEndDate")%></span><input name="deliveryEndDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryEndDate")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("amountMoney")%></span><input class="m-wrap" name="amountMoney"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("shipMode")%></span><input class="m-wrap" name="shipMode"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("shipModeId")%></span><input class="m-wrap" name="shipModeId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("receivingUnitId")%></span><input class="m-wrap" name="receivingUnitId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("receivingUnit")%></span><input class="m-wrap" name="receivingUnit"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("province")%></span><input class="m-wrap" name="province"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("provinceId")%></span><input class="m-wrap" name="provinceId"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("city")%></span><input class="m-wrap" name="city"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("cityId")%></span><input class="m-wrap" name="cityId"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("address")%></span><input class="m-wrap" name="address"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("orderAttachment")%></span><input class="m-wrap" name="orderAttachment"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("attorneyLetter")%></span><input class="m-wrap" name="attorneyLetter"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("placeOrderTime")%></span><input name="placeOrderTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("placeOrderTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("inputPerson")%></span><input class="m-wrap" name="inputPerson"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("inputPersonId")%></span><input class="m-wrap" name="inputPersonId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("auditPersonId")%></span><input class="m-wrap" name="auditPersonId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("auditPerson")%></span><input class="m-wrap" name="auditPerson"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%></span><input name="auditTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%>" />
							</div>

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/order/insert',$(insertForm).serialize())">
       <i class="icon-ok"></i>
               保存</a>
       <a href="javascript:void(0)" id="cData" class="cancel fm-button ui-state-default ui-corner-all fm-button-icon-left">
      <i class="icon-remove"></i> 
              取消</a>
     </div>
   
     </div></div><div class="jqResize ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div></div>
	
	<!-- end 新增 -->
	

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
							<input id="status" name="status" type="hidden"  value="<%=ISystemConstant.DICTIONARY_ORDER_STATUS_01%>"/>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("orderNum")%></span><input class="m-wrap" name="orderNum"  type="text"  						maxLength="13" />
							</div>

			<%-- 				<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("customerId")%></span><input class="m-wrap" name="customerId"  type="text"  						maxLength="19" />
							</div>



							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_ORDER_STATUS), "", "inputName='" + IOrderConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>
--%>
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("customerName")%></span><input class="m-wrap" name="customerName"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryStartDate")%></span><input name="deliveryStartDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryStartDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryEndDate")%></span><input name="deliveryEndDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryEndDate")%>" />
							</div>

<%-- 							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("amountMoney")%></span><input class="m-wrap" name="amountMoney"  type="text"  						maxLength="19" />
							</div>
--%>
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("shipMode")%></span><input type="text" class="m-wrap" readonly="readonly" name="shipMode" />			<input type="hidden" class="m-wrap"  name="shipModeId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/shipmode/reference?referenceInputType=radio','form.shipModeId,form.shipMode');">选择</span>
							</div>

<%-- 							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("shipModeId")%></span><input class="m-wrap" name="shipModeId"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("receivingUnitId")%></span><input class="m-wrap" name="receivingUnitId"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("receivingUnit")%></span><input class="m-wrap" name="receivingUnit"  type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("province")%></span><input class="m-wrap" name="province"  type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("provinceId")%></span><input class="m-wrap" name="provinceId"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("city")%></span><input class="m-wrap" name="city"  type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("cityId")%></span><input class="m-wrap" name="cityId"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("address")%></span><input class="m-wrap" name="address"  type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("orderAttachment")%></span><input class="m-wrap" name="orderAttachment"  type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("attorneyLetter")%></span><input class="m-wrap" name="attorneyLetter"  type="text"  						maxLength="300" />
							</div>
--%>
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("placeOrderTime")%></span><input name="placeOrderTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("placeOrderTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("inputPerson")%></span><input class="m-wrap" name="inputPerson"  type="text"  						maxLength="200" />
							</div>
<%-- 
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("inputPersonId")%></span><input class="m-wrap" name="inputPersonId"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("auditPersonId")%></span><input class="m-wrap" name="auditPersonId"  type="text"  						maxLength="19" />
							</div>
--%>
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("auditPerson")%></span><input class="m-wrap" name="auditPerson"  type="text"  						maxLength="200" />
							</div>
<%-- 
							<div class="input-prepend">
							  <span class="add-on"><%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%></span><input name="auditTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%>" />
							</div>
--%>

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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/order/logicDelete?pcode=${pcode }');">
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
								<a href="#">副产品销售管理</a>
							</li>
							<li class="active">销售订单管理</li>
						</ul><!-- .breadcrumb -->

				<%--		<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
				 --%>
					</div>

					<div class="page-content">
						<!-- <div class="page-header">
							<h1>
								销售订单
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
					
					url: getRootPath()+'/order/ajaxList?status=<%=ISystemConstant.DICTIONARY_ORDER_STATUS_01%>&typeLu=saleOrderManager',
					datatype: "json",
				    mtype: 'POST',  
					height: "100%",
					colNames:[
'<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("orderNum")%>'
,'<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("status")%>'
,'产品名称','车数'
,'<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("amountMoney")%>'
,'<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("customerName")%>'
,'<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryStartDate")%>'
,'<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("deliveryEndDate")%>'
,'<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("shipMode")%>'
,'<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("placeOrderTime")%>'
,'<%=IOrderConstants.TABLE_COLUMN_DISPLAY.get("inputPerson")%>'
],
					colModel:[
			{name:'orderNum',index:'orderNum', width:150,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'status',index:'status', width:110,editable: true,editoptions:{size:"20",maxLength:"200"},formatter: orderStatus},
			{name:'listProductName',index:'listProductName', width:130,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'carSum',index:'carSum', width:50,editable: true,editoptions:{size:"20",maxLength:"8"}},
			{name:'amountMoney',index:'amountMoney', width:90,editable: true},
			{name:'customerName',index:'customerName', width:130,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'deliveryStartDate',index:'deliveryStartDate',sorttype :"date",formatter: formatDateShow},
			{name:'deliveryEndDate',index:'deliveryEndDate',sorttype :"date",formatter: formatDateShow},
			{name:'shipMode',index:'shipMode', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'placeOrderTime',index:'placeOrderTime',sorttype :"date",formatter: formatDateShow},
			{name:'inputPerson',index:'inputPerson', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}}

					], 
					
					// shrinkToFit:false,不随列数量改变列宽度
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
			
					editurl: getRootPath()+'/order/update',//nothing is saved
					caption: "销售订单列表展示",
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
// 					{ caption:"",title:"新增", buttonicon:"icon-plus-sign purple",
// 				      onClickButton: addShow, position:"last" }) ;
				//删除
// 				  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 					{ caption:"",title:"删除",buttonicon:"icon-trash red",
// 				      onClickButton:deleteShow , 
// 				      position:"last" }) ;
				
				//查询
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"",title:"查询",buttonicon:"icon-search orange",
				      onClickButton:search_div , 
				      position:"last" }) ;
				
					//审核
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"",title:"审核", buttonicon:"icon-group red",
					  onClickButton:to_audit , 
				      position:"last" }) ;
				
				//查看
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"",title:"查看详情" ,buttonicon:"icon-eye-open red",
				      onClickButton:to_detail , 
				      position:"last" }) ;

				
				//新增弹出
				 function addShow(){
			    	 
			    	// $(".ui_out_tan").show();
			    	// $("#add_form").show();
				
				   window.location.href="${pageContext.request.contextPath}/order/toAddOrder?pcode=${pcode}";
			     
			     }
			
			     
					//查看详情
					function to_detail(){
						
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
	                    var id=$(pointer).attr("id");
	                    
	                    window.location.href="${pageContext.request.contextPath}/order/toDetail?id="+id+"&pcode=${pcode}";
						 
						
					}

					//审核
					function to_audit(){
						
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
	                    var id=$(pointer).attr("id");
	                    
	                    window.location.href="${pageContext.request.contextPath}/order/toAudit?id="+id+"&pcode=${pcode}";
						 
						
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
			

				function orderStatus(cellvalue, options, cell){

					return orderStatusShow[cellvalue];
				} 
				
				function  formatDateShow(cellvalue, options, cell){
					if(cellvalue != null ){
						return  formatDateRow(cellvalue, options, cell);
					}else{
						return "";
					}
				}
			});
		</script>
	</body>
</html>
