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
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span>						
							  <input type="text" class="m-wrap"  name="ladingBillCode" />
							  <input type="hidden" class="m-wrap"  name="ladingBillId" />
							  <span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','insertForm.ladingBillId,insertForm.ladingBillCode');">选择</span>
							</div>

							
<!-- 							<div class="input-prepend"> -->
<%-- 							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode"  type="text"  						maxLength="13" /> --%>
<!-- 							</div> -->

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("truckNo")%></span>
							  	<input class="m-wrap" name="truckNo"  type="text"  						maxLength="100" />
							  	
							<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/rownumber/referenceTruckNo?referenceInputType=radio','insertForm.rowNumbeId,insertForm.truckNo,insertForm.phoneNumber');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("phoneNumber")%></span><input class="m-wrap" name="phoneNumber"  type="text"  						maxLength="20" />
							</div>

							
<!-- 							<div class="input-prepend"> -->
<%-- 							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId"  type="text"  						maxLength="19" /> --%>
<!-- 							</div> -->

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span>
							  
							  	<input class="m-wrap" name="productName"  type="text"  						maxLength="200" />
								<input class="m-wrap" name="productId"  type="hidden"  						maxLength="19" />
							
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/product/reference?referenceInputType=radio','insertForm.productId,insertForm.productName');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%></span><input class="m-wrap" name="productModelNumber"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("tare")%></span><input class="m-wrap" name="tare"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("grossWeight")%></span><input class="m-wrap" name="grossWeight"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("netWeight")%></span><input class="m-wrap" name="netWeight"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("firstWeighingTime")%></span><input name="firstWeighingTime"  class="m-wrap m-ctrl-medium " onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("firstWeighingTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("twoWeighingTime")%></span><input name="twoWeighingTime"  class="m-wrap m-ctrl-medium " onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("twoWeighingTime")%>" />
							</div>

							
							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman")%></span>
							  <input class="m-wrap" name="weighmanId"  type="hidden"  						maxLength="19" />
							  <input class="m-wrap" name="weighman"  type="text"  						maxLength="200" />
							
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio&userType=<%=ISystemConstant.DICTIONARY_USER_TYPE_5 %>','insertForm.weighmanId,insertForm.weighman');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman2")%></span>
							  <input class="m-wrap" name="weighman2"  type="text"  						maxLength="200" />
							  <input class="m-wrap" name="weighmanId2"  type="hidden"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio&userType=<%=ISystemConstant.DICTIONARY_USER_TYPE_5 %>','insertForm.weighmanId2,insertForm.weighman2');">选择</span>
							</div>

							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("shipper")%></span>
							  	<input class="m-wrap" name="shipper"  type="text"  						maxLength="300" />
								<input class="m-wrap" name="shipperCode"  type="hidden"  						maxLength="10" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/shipper/reference?referenceInputType=radio','insertForm.shipperId,insertForm.shipperCode,insertForm.shipper');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingUnit")%></span><input class="m-wrap" name="receivingUnit"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("transportUnit")%></span><input class="m-wrap" name="transportUnit"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("timeToGoOut")%></span><input name="timeToGoOut"  class="m-wrap m-ctrl-medium "onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("timeToGoOut")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("printDate")%></span><input name="printDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>" />
							</div>

							
							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_WEIGHING_BILL_STATUS), "", "inputName='" + IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("storageTransportationMan")%></span>
							  <input class="m-wrap" name="storageTransportationId"  type="hidden"  						maxLength="19" />
							  <input class="m-wrap" name="storageTransportationName"  type="text"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio','insertForm.storageTransportationId,insertForm.storageTransportationName');">选择</span>
							</div>

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/weighingbill/insert',$(insertForm).serialize())">
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
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span>						
							  <input type="text" class="m-wrap"  name="ladingBillCode" />
							  <input type="hidden" class="m-wrap"  name="ladingBillId" />
							  <span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','updateForm.ladingBillId,updateForm.ladingBillCode');">选择</span>
							</div>

							
<!-- 							<div class="input-prepend"> -->
<%-- 							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode"  type="text"  						maxLength="13" /> --%>
<!-- 							</div> -->

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("truckNo")%></span>
							  	<input class="m-wrap" name="truckNo"  type="text"  						maxLength="100" />
							  	
							<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/rownumber/referenceTruckNo?referenceInputType=radio','updateForm.rowNumbeId,updateForm.truckNo,updateForm.phoneNumber');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("phoneNumber")%></span><input class="m-wrap" name="phoneNumber"  type="text"  						maxLength="20" />
							</div>

							
<!-- 							<div class="input-prepend"> -->
<%-- 							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId"  type="text"  						maxLength="19" /> --%>
<!-- 							</div> -->

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span>
							  
							  	<input class="m-wrap" name="productName"  type="text"  						maxLength="200" />
								<input class="m-wrap" name="productId"  type="hidden"  						maxLength="19" />
							
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/product/reference?referenceInputType=radio','updateForm.productId,updateForm.productName');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%></span><input class="m-wrap" name="productModelNumber"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("tare")%></span><input class="m-wrap" name="tare"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("grossWeight")%></span><input class="m-wrap" name="grossWeight"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("netWeight")%></span><input class="m-wrap" name="netWeight"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("firstWeighingTime")%></span><input name="firstWeighingTime"  class="m-wrap m-ctrl-medium " onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("firstWeighingTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("twoWeighingTime")%></span><input name="twoWeighingTime"  class="m-wrap m-ctrl-medium " onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("twoWeighingTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman")%></span>
							  <input class="m-wrap" name="weighmanId"  type="hidden"  						maxLength="19" />
							  <input class="m-wrap" name="weighman"  type="text"  						maxLength="200" />
							
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio&userType=<%=ISystemConstant.DICTIONARY_USER_TYPE_5 %>','updateForm.weighmanId,updateForm.weighman');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman2")%></span>
							  <input class="m-wrap" name="weighman2"  type="text"  						maxLength="200" />
							  <input class="m-wrap" name="weighmanId2"  type="hidden"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio&userType=<%=ISystemConstant.DICTIONARY_USER_TYPE_5 %>','updateForm.weighmanId2,updateForm.weighman2');">选择</span>
							</div>

							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("shipper")%></span>
							  	<input class="m-wrap" name="shipper"  type="text"  						maxLength="300" />
								<input class="m-wrap" name="shipperCode"  type="hidden"  						maxLength="10" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/shipper/reference?referenceInputType=radio','updateForm.shipperId,updateForm.shipperCode,updateForm.shipper');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingUnit")%></span><input class="m-wrap" name="receivingUnit"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("transportUnit")%></span><input class="m-wrap" name="transportUnit"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("timeToGoOut")%></span><input name="timeToGoOut"  class="m-wrap m-ctrl-medium "onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("timeToGoOut")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("printDate")%></span><input name="printDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>" />
							</div>

							
							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_WEIGHING_BILL_STATUS), "", "inputName='" + IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("storageTransportationMan")%></span>
							  <input class="m-wrap" name="storageTransportationId"  type="hidden"  						maxLength="19" />
							  <input class="m-wrap" name="storageTransportationName"  type="text"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio','updateForm.storageTransportationId,updateForm.storageTransportationName');">选择</span>
							</div>


							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/weighingbill/update',$(updateForm).serialize())">
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
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span>						
							  <input type="text" class="m-wrap"  name="ladingBillCode" />
							  <input type="hidden" class="m-wrap"  name="ladingBillId" />
							  <span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','form.ladingBillId,form.ladingBillCode');">选择</span>
							</div>

							
<!-- 							<div class="input-prepend"> -->
<%-- 							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode"  type="text"  						maxLength="13" /> --%>
<!-- 							</div> -->

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("truckNo")%></span>
							  	<input class="m-wrap" name="truckNo"  type="text"  						maxLength="100" />
							  	
							<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/rownumber/referenceTruckNo?referenceInputType=radio','form.rowNumbeId,form.truckNo,form.phoneNumber');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("phoneNumber")%></span><input class="m-wrap" name="phoneNumber"  type="text"  						maxLength="20" />
							</div>

							
<!-- 							<div class="input-prepend"> -->
<%-- 							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId"  type="text"  						maxLength="19" /> --%>
<!-- 							</div> -->

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span>
							  
							  	<input class="m-wrap" name="productName"  type="text"  						maxLength="200" />
								<input class="m-wrap" name="productId"  type="hidden"  						maxLength="19" />
							
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/product/reference?referenceInputType=radio','form.productId,form.productName');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%></span><input class="m-wrap" name="productModelNumber"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("tare")%></span><input class="m-wrap" name="tare"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("grossWeight")%></span><input class="m-wrap" name="grossWeight"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("netWeight")%></span><input class="m-wrap" name="netWeight"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("firstWeighingTime")%></span><input name="firstWeighingTime"  class="m-wrap m-ctrl-medium " onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("firstWeighingTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("twoWeighingTime")%></span><input name="twoWeighingTime"  class="m-wrap m-ctrl-medium " onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("twoWeighingTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman")%></span>
							  <input class="m-wrap" name="weighmanId"  type="hidden"  						maxLength="19" />
							  <input class="m-wrap" name="weighman"  type="text"  						maxLength="200" />
							
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio&userType=<%=ISystemConstant.DICTIONARY_USER_TYPE_5 %>','form.weighmanId,form.weighman');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman2")%></span>
							  <input class="m-wrap" name="weighman2"  type="text"  						maxLength="200" />
							  <input class="m-wrap" name="weighmanId2"  type="hidden"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio&userType=<%=ISystemConstant.DICTIONARY_USER_TYPE_5 %>','form.weighmanId2,form.weighman2');">选择</span>
							</div>

							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("shipper")%></span>
							  	<input class="m-wrap" name="shipper"  type="text"  						maxLength="300" />
								<input class="m-wrap" name="shipperCode"  type="hidden"  						maxLength="10" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/shipper/reference?referenceInputType=radio','form.shipperId,form.shipperCode,form.shipper');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingUnit")%></span><input class="m-wrap" name="receivingUnit"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("transportUnit")%></span><input class="m-wrap" name="transportUnit"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("timeToGoOut")%></span><input name="timeToGoOut"  class="m-wrap m-ctrl-medium "onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("timeToGoOut")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("printDate")%></span><input name="printDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>" />
							</div>

							
							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_WEIGHING_BILL_STATUS), "", "inputName='" + IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("storageTransportationMan")%></span>
							  <input class="m-wrap" name="storageTransportationId"  type="hidden"  						maxLength="19" />
							  <input class="m-wrap" name="storageTransportationName"  type="text"  						maxLength="19" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/user/referenceNew?referenceInputType=radio','form.storageTransportationId,form.storageTransportationName');">选择</span>
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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/weighingbill/logicDelete?pcode=${pcode }');">
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
								<a href="#">过磅业务</a>
							</li>
							<li class="active">过磅单管理</li>
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
								过磅单
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
		var statusConvert=[]; 

		 <c:forEach items="${WEIGHING_BILL_STATUS}" var="listStatus"> 
		     var keyL ='${listStatus.key}';
		     statusConvert[keyL]='${listStatus.value}';
		 </c:forEach> 
			jQuery(function($) {
				var grid_selector = "#grid-table";
				var pager_selector = "#grid-pager";

				jQuery(grid_selector).jqGrid({
					//direction: "rtl",
					
					url: getRootPath()+'/weighingbill/ajaxList',
					datatype: "json",
				    mtype: 'POST',  
					height: "100%",
					colNames:[
'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("truckNo")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("phoneNumber")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("tare")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("grossWeight")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("netWeight")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("firstWeighingTime")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("twoWeighingTime")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("weighman2")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("shipper")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingUnit")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("transportUnit")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("timeToGoOut")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("storageTransportationMan")%>'
,'<%=IWeighingBillConstants.TABLE_COLUMN_DISPLAY.get("status")%>'
],
					colModel:[
			{name:'ladingBillCode',index:'ladingBillCode',editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'truckNo',index:'truckNo', editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'phoneNumber',index:'phoneNumber',editable: true,editoptions:{size:"20",maxLength:"20"}},
			{name:'productName',index:'productName',editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'productModelNumber',index:'productModelNumber',editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'tare',index:'tare',editable: true,editoptions:{size:"20",maxLength:"14"}},
			{name:'grossWeight',index:'grossWeight',editable: true,editoptions:{size:"20",maxLength:"14"}},
			{name:'netWeight',index:'netWeight',editable: true,editoptions:{size:"20",maxLength:"14"}},
			{name:'firstWeighingTime',index:'firstWeighingTime',width:150, editable:true,sorttype :"date",formatter: formatDateRow},
			{name:'twoWeighingTime',index:'twoWeighingTime',width:150, editable:true,sorttype :"date",formatter: formatDateRow},
			{name:'weighman',index:'weighman',editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'weighman2',index:'weighman2',editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'shipper',index:'shipper',editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'receivingUnit',index:'receivingUnit',editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'transportUnit',index:'transportUnit',editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'timeToGoOut',index:'timeToGoOut', editable:true, sorttype:"date",unformat: pickDate},
			{name:'printDate',index:'printDate',editable:true, sorttype:"date",unformat: pickDate},
			{name:'storageTransportationMan',index:'storageTransportationMan',editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'status',index:'status',editable: true,editoptions:{size:"20",maxLength:"2"},formatter: convert},
					], 
			        shrinkToFit:false,//不随列数量改变列宽度
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
                        url: "<%=request.getContextPath()%>/weighingbill/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=ladingBillId]").val(data.bean.ladingBillId);
                      $("#portlet-config-edit").find("input[id=ladingBillCode]").val(data.bean.ladingBillCode);
                      $("#portlet-config-edit").find("input[id=truckNo]").val(data.bean.truckNo);
                      $("#portlet-config-edit").find("input[id=phoneNumber]").val(data.bean.phoneNumber);
                      $("#portlet-config-edit").find("input[id=productId]").val(data.bean.productId);
                      $("#portlet-config-edit").find("input[id=productName]").val(data.bean.productName);
                      $("#portlet-config-edit").find("input[id=productModelNumber]").val(data.bean.productModelNumber);
                      $("#portlet-config-edit").find("input[id=tare]").val(data.bean.tare);
                      $("#portlet-config-edit").find("input[id=grossWeight]").val(data.bean.grossWeight);
                      $("#portlet-config-edit").find("input[id=netWeight]").val(data.bean.netWeight);
                      $("#portlet-config-edit").find("input[id=firstWeighingTime]").val(data.bean.firstWeighingTime);
                      $("#portlet-config-edit").find("input[id=twoWeighingTime]").val(data.bean.twoWeighingTime);
                      $("#portlet-config-edit").find("input[id=weighmanId]").val(data.bean.weighmanId);
                      $("#portlet-config-edit").find("input[id=weighman]").val(data.bean.weighman);
                      $("#portlet-config-edit").find("input[id=weighmanId2]").val(data.bean.weighmanId2);
                      $("#portlet-config-edit").find("input[id=weighman2]").val(data.bean.weighman2);
                      $("#portlet-config-edit").find("input[id=shipperCode]").val(data.bean.shipperCode);
                      $("#portlet-config-edit").find("input[id=shipper]").val(data.bean.shipper);
                      $("#portlet-config-edit").find("input[id=receivingUnit]").val(data.bean.receivingUnit);
                      $("#portlet-config-edit").find("input[id=transportUnit]").val(data.bean.transportUnit);
                      $("#portlet-config-edit").find("input[id=timeToGoOut]").val(data.bean.timeToGoOut);
                      $("#portlet-config-edit").find("input[id=printDate]").val(data.bean.printDate);
                      $("#portlet-config-edit").find("input[id=storageTransportationMan]").val(data.bean.storageTransportationMan);
                      $("#portlet-config-edit").find("input[id=status]").val(data.bean.status);
                      $("#portlet-config-edit").find("input[id=storageTransportationId]").val(data.bean.storageTransportationId);
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
