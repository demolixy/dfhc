
<%@page import="com.dfhc.bus.ladingbill.ILadingBillConstants" %>
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
function selectSelected($_select, value){
	$_select.find("option").each(function(){
  	  if($(this).attr("value") == value){
  		  $(this).attr("selected", "selected");
  		  return;
  	  }
    });
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
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("orderPlanId")%></span>						<input type="text" class="m-wrap"  name="orderPlanId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/order/reference?referenceInputType=radio','insertForm.orderPlanId,insertForm.orderPlanIdName');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("orderPlanCode")%></span><input class="m-wrap" name="orderPlanCode"  type="text"  						maxLength="13" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode"  type="text"  						maxLength="13" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("billType")%></span><%=RmJspHelper.getSelectField("billType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_BILL_TYPE), "", "inputName='" + ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("billType") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("shipMode")%></span><input class="m-wrap" name="shipMode"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("shipModeId")%></span><input class="m-wrap" name="shipModeId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productType")%></span><input class="m-wrap" name="productType"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productTypeId")%></span><input class="m-wrap" name="productTypeId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span><input class="m-wrap" name="productName"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%></span><input class="m-wrap" name="productModelNumber"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("phoneNumber")%></span><input class="m-wrap" name="phoneNumber"  type="text"  						maxLength="20" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%></span><input class="m-wrap" name="licensePlate"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("carType")%></span><input class="m-wrap" name="carType"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("driverName")%></span><input class="m-wrap" name="driverName"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("driverIdCardNumber")%></span><input class="m-wrap" name="driverIdCardNumber"  type="text"  						maxLength="18" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillStatus")%></span><%=RmJspHelper.getSelectField("ladingBillStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_LADING_BILL_STATUS), "", "inputName='" + ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillStatus") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%></span><input name="auditTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditPerson")%></span><input class="m-wrap" name="auditPerson"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditPersonId")%></span><input class="m-wrap" name="auditPersonId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("estimatedLoadingTime")%></span><input name="estimatedLoadingTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("estimatedLoadingTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("takeNoTime")%></span><input name="takeNoTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("takeNoTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("actualShippingWeight")%></span><input class="m-wrap" name="actualShippingWeight"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("nuclearLoad")%></span><input class="m-wrap" name="nuclearLoad"  type="text"  						maxLength="14" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inGate")%></span><input class="m-wrap" name="inGate"  type="text"  						maxLength="50" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inTime")%></span><input name="inTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyWeight")%></span><input class="m-wrap" name="emptyWeight"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyPoundTime")%></span><input name="emptyPoundTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyPoundTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullWeight")%></span><input class="m-wrap" name="fullWeight"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullPoundTime")%></span><input name="fullPoundTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullPoundTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outGate")%></span><input class="m-wrap" name="outGate"  type="text"  						maxLength="50" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outTime")%></span><input name="outTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputPersonId")%></span><input class="m-wrap" name="inputPersonId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputPerson")%></span><input class="m-wrap" name="inputPerson"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputTime")%></span><input name="inputTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidPersonId")%></span><input class="m-wrap" name="invalidPersonId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidPerson")%></span><input class="m-wrap" name="invalidPerson"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidTime")%></span><input name="invalidTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidReason")%></span><input class="m-wrap" name="invalidReason"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isBringBelt")%></span><input class="m-wrap" name="isBringBelt"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isLipolysis")%></span><input class="m-wrap" name="isLipolysis"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isSince")%></span><input class="m-wrap" name="isSince"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isUrgent")%></span><input class="m-wrap" name="isUrgent"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%></span><input class="m-wrap" name="plannedMileage"  type="text"  						maxLength="12" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("factMileage")%></span><input class="m-wrap" name="factMileage"  type="text"  						maxLength="12" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("valuationMileage")%></span><input class="m-wrap" name="valuationMileage"  type="text"  						maxLength="12" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("settlementFreight")%></span><input class="m-wrap" name="settlementFreight"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("yuanKmTon")%></span><input class="m-wrap" name="yuanKmTon"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("freight")%></span><input class="m-wrap" name="freight"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingPerson")%></span><input class="m-wrap" name="receivingPerson"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingAddress")%></span><input class="m-wrap" name="receivingAddress"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingPersonPhone")%></span><input class="m-wrap" name="receivingPersonPhone"  type="text"  						maxLength="20" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("freightSettlementId")%></span>						<input type="text" class="m-wrap"  name="freightSettlementId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/logisticscostsettlement/reference?referenceInputType=radio','insertForm.freightSettlementId,insertForm.freightSettlementIdName');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invoiceSettlementId")%></span>						<input type="text" class="m-wrap"  name="invoiceSettlementId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/receiptconfletter/reference?referenceInputType=radio','insertForm.invoiceSettlementId,insertForm.invoiceSettlementIdName');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("printTime")%></span><input name="printTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("printTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("printTimes")%></span><input class="m-wrap" name="printTimes"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("remark")%></span>           	                       <textarea class="medium m-wrap" name="remark" id="remark" 					inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("remark")%>" 						 maxLength="200" 					 ></textarea>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsSpecialistId")%></span><input class="m-wrap" name="logisticsSpecialistId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsSpecialistName")%></span><input class="m-wrap" name="logisticsSpecialistName"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("importTime")%></span><input name="importTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("importTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("distributionTime")%></span><input name="distributionTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("distributionTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompanyClerkId")%></span><input class="m-wrap" name="logisticsCompanyClerkId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompanyClerk")%></span><input class="m-wrap" name="logisticsCompanyClerk"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("allocationVehicleTime")%></span><input name="allocationVehicleTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("allocationVehicleTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isPound")%></span><%=RmJspHelper.getSelectField("isPound", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isPound") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isLoading")%></span><%=RmJspHelper.getSelectField("isLoading", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isLoading") + "'  ", true) %>							</div>

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/ladingbill/insert',$(insertForm).serialize())">
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
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("orderPlanCode")%></span>
							  <input class="m-wrap" name="orderPlanCode" id="orderPlanCode" type="text"  						maxLength="13" />
								<input type="hidden" class="m-wrap"  name="orderPlanId" id="orderPlanId" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/order/reference?referenceInputType=radio','updateForm.orderPlanId,updateForm.orderPlanCode');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode" id="ladingBillCode" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("billType")%></span><%=RmJspHelper.getSelectField("billType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_BILL_TYPE), "", "inputName='" + ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("billType") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("shipMode")%></span>
							  <input class="m-wrap" name="shipModeId" id="shipModeId" type="hidden"  						maxLength="19" />
							  <input class="m-wrap" name="shipMode" id="shipMode" type="text"  						maxLength="100" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/shipmode/reference?referenceInputType=radio','updateForm.shipModeId,updateForm.shipMode');">选择</span>
							</div>


							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productType")%></span>
							  <input class="m-wrap" name="productTypeId" id="productTypeId" type="hidden"  						maxLength="19" />
							  <input class="m-wrap" name="productType" id="productType" type="text"  						maxLength="200" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/producttype/reference?referenceInputType=radio','updateForm.productTypeId,updateForm.productType');">选择</span>
							</div>



							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%>
							  </span>
							  <input class="m-wrap" name="productName" id="productName" type="text"  						maxLength="200" />
								<input class="m-wrap" name="productId" id="productId" type="hidden"  						maxLength="19" />
							
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/product/reference?referenceInputType=radio','updateForm.productId,updateForm.productName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%></span><input class="m-wrap" name="productModelNumber" id="productModelNumber" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("phoneNumber")%></span><input class="m-wrap" name="phoneNumber" id="phoneNumber" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%></span><input class="m-wrap" name="licensePlate" id="licensePlate" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("carType")%></span><input class="m-wrap" name="carType" id="carType" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("driverName")%></span><input class="m-wrap" name="driverName" id="driverName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("driverIdCardNumber")%></span><input class="m-wrap" name="driverIdCardNumber" id="driverIdCardNumber" type="text"  						maxLength="18" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillStatus")%></span><%=RmJspHelper.getSelectField("ladingBillStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_LADING_BILL_STATUS), "", "inputName='" + ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillStatus") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%></span><input name="auditTime" id="auditTime"  class="m-wrap m-ctrl-medium " readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditPerson")%></span><input class="m-wrap" name="auditPerson" id="auditPerson" type="text"  						maxLength="200" />
							</div>


							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("estimatedLoadingTime")%></span><input name="estimatedLoadingTime" id="estimatedLoadingTime"  class="m-wrap m-ctrl-medium" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("estimatedLoadingTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("takeNoTime")%></span><input name="takeNoTime" id="takeNoTime"  class="m-wrap m-ctrl-medium" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("takeNoTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("actualShippingWeight")%></span><input class="m-wrap" name="actualShippingWeight" id="actualShippingWeight" type="text"  						maxLength="14" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("nuclearLoad")%></span><input class="m-wrap" name="nuclearLoad" id="nuclearLoad" type="text"  						maxLength="14" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inGate")%></span><input class="m-wrap" name="inGate" id="inGate" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inTime")%></span><input name="inTime" id="inTime"  class="m-wrap m-ctrl-medium" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyWeight")%></span><input class="m-wrap" name="emptyWeight" id="emptyWeight" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyPoundTime")%></span><input name="emptyPoundTime" id="emptyPoundTime"  class="m-wrap m-ctrl-medium" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyPoundTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullWeight")%></span><input class="m-wrap" name="fullWeight" id="fullWeight" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullPoundTime")%></span><input name="fullPoundTime" id="fullPoundTime"  class="m-wrap m-ctrl-medium" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullPoundTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outGate")%></span><input class="m-wrap" name="outGate" id="outGate" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outTime")%></span><input name="outTime" id="outTime"  class="m-wrap m-ctrl-medium" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outTime")%>" />
							</div>


							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputPerson")%></span><input class="m-wrap" name="inputPerson" id="inputPerson" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputTime")%></span><input name="inputTime" id="inputTime"  class="m-wrap m-ctrl-medium" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputTime")%>" />
							</div>




							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isBringBelt")%></span><input class="m-wrap" name="isBringBelt" id="isBringBelt" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isLipolysis")%></span><input class="m-wrap" name="isLipolysis" id="isLipolysis" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isSince")%></span><input class="m-wrap" name="isSince" id="isSince" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isUrgent")%></span><input class="m-wrap" name="isUrgent" id="isUrgent" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%></span><input class="m-wrap" name="plannedMileage" id="plannedMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("factMileage")%></span><input class="m-wrap" name="factMileage" id="factMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("valuationMileage")%></span><input class="m-wrap" name="valuationMileage" id="valuationMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("settlementFreight")%></span><input class="m-wrap" name="settlementFreight" id="settlementFreight" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("yuanKmTon")%></span><input class="m-wrap" name="yuanKmTon" id="yuanKmTon" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("freight")%></span><input class="m-wrap" name="freight" id="freight" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingPerson")%></span><input class="m-wrap" name="receivingPerson" id="receivingPerson" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingAddress")%></span><input class="m-wrap" name="receivingAddress" id="receivingAddress" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingPersonPhone")%></span><input class="m-wrap" name="receivingPersonPhone" id="receivingPersonPhone" type="text"  						maxLength="20" />
							</div>



							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode" id="shipperCode" type="text"  						maxLength="10" />
							</div>


							

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsSpecialistId")%></span><input class="m-wrap" name="logisticsSpecialistId" id="logisticsSpecialistId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsSpecialistName")%></span><input class="m-wrap" name="logisticsSpecialistName" id="logisticsSpecialistName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("importTime")%></span><input name="importTime" id="importTime"  class="m-wrap m-ctrl-medium " readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("importTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("distributionTime")%></span><input name="distributionTime" id="distributionTime"  class="m-wrap m-ctrl-medium " readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("distributionTime")%>" />
							</div>


							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompanyClerk")%></span><input class="m-wrap" name="logisticsCompanyClerk" id="logisticsCompanyClerk" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("allocationVehicleTime")%></span><input name="allocationVehicleTime" id="allocationVehicleTime"  class="m-wrap m-ctrl-medium " readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("allocationVehicleTime")%>" />
							</div>



							</div>
							<div class="input-prepend  text-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("remark")%></span>           	                       <textarea class="medium m-wrap" name="remark" id="remark" 					inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("remark")%>" 						 maxLength="200" 					 ></textarea>
							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/ladingbill/update',$(updateForm).serialize())">
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
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("orderPlanCode")%></span>
							  <input class="m-wrap" name="orderPlanCode" id="orderPlanCode" type="text"  						maxLength="13" />
								<input type="hidden" class="m-wrap"  name="orderPlanId" id="orderPlanId" />
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/order/reference?referenceInputType=radio','updateForm.orderPlanId,updateForm.orderPlanCode');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode" id="ladingBillCode" type="text"  						maxLength="13" />
							</div>
							
							<div class="input-prepend">
							  <span class="add-on">物流公司</span> <input type="text" class="tab-inp" name="logisticsCompany"/> <input type="hidden" class="tab-inp" name="logisticsCompanyId"/>
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/logisticscompany/reference?referenceInputType=radio','form.logisticsCompanyId,form.logisticsCompany');">选择</span>
			               </div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%>
							  </span>
							  <input class="m-wrap" name="productName" id="productName" type="text"  						maxLength="200" />
								<input class="m-wrap" name="productId" id="productId" type="hidden"  						maxLength="19" />
							
								<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/product/reference?referenceInputType=radio','form.productId,form.productName');">选择</span>
							</div>
						<div class="input-prepend">
							  <span class="add-on">序号</span><input class="m-wrap" name="sequenceNumber" id="sequenceNumber" type="text"  						maxLength="20" />
						</div>

						<div class="input-prepend">
							  <span class="add-on">发货日期从</span><input name="outTimeStr" id="outTimeStr"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outTime")%>" />
						</div> 
						<div class="input-prepend">
							  <span class="add-on">到</span><input name="outTimeEnd" id="outTimeEnd"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outTime")%>" />
						</div> 
						<div class="input-prepend">
							  <span class="add-on">省份</span><input class="m-wrap" name="province" id="province" type="text"  						maxLength="20" />
						</div>
						<div class="input-prepend">
							  <span class="add-on">区域</span><input class="m-wrap" name="region" id="region" type="text"  						maxLength="20" />
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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/ladingbill/logicDelete?pcode=${pcode }');">
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
										<a href="#">主产品计划管理</a>
									</li>
									<li class="active">汽运配送台账</li>
									
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
								司机提货单
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
					
					url: getRootPath()+'/ladingbill/ajaxList?${queryString}',
					datatype: "json",
				    mtype: 'POST',  
					height: '100%',
					colNames:[
'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("orderPlanCode")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%>'
,'序号','接受订单日期','安排订单日期','价格','发货仓库','物流公司','区域','客户名称','产品名称','等级','接货人','接货人电话','省份','区域','发货详细地址','实际发货数量','里程','元/吨.公里','单价(元/吨)','取整单价','总运费','元/吨.公里（结算）','备注'
],
					colModel:[
			{name:'orderPlanCode',index:'orderPlanCode', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'ladingBillCode',index:'ladingBillCode', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'sequenceNumber',index:'sequenceNumber', width:90,editable: true},
			{name:'receiveTime',index:'receiveTime',width:90, editable:true, sorttype:"date", formatter:formatDateRow},
			{name:'outTime',index:'takeNoTime',width:90, editable:true, sorttype:"date", formatter:formatDateRow},
			{name:'price',index:'price', width:90,editable: true},
			{name:'shipperCode',index:'shipperCode', width:90,editable: true},
			{name:'logisticsCompany',index:'logisticsCompany', width:90,editable: true},
			{name:'regionName',index:'regionName', width:90,editable: true},
			{name:'customerName',index:'customerName', width:90,editable: true},
			{name:'productName',index:'productName', width:90,editable: true},
			{name:'grade',index:'grade', width:90,editable: true},
			{name:'receivingPerson',index:'receivingPerson', width:90,editable: true},
			{name:'receivingPersonPhone',index:'receivingPersonPhone', width:90,editable: true},
			{name:'province',index:'province', width:90,editable: true},
			{name:'region',index:'region', width:90,editable: true},
			{name:'receivingAddress',index:'receivingAddress', width:90,editable: true},
			{name:'actualShippingWeight',index:'actualShippingWeight', width:90,editable: true},
			{name:'factMileage',index:'factMileage', width:90,editable: true},
			{name:'yuanKmTon',index:'yuanKmTon', width:90,editable: true},
			{name:'productUnitPrice',index:'productUnitPrice', width:90,editable: true},
			{name:'roundingUnit',index:'roundingUnit', width:90,editable: true},
			{name:'settlementFreight',index:'settlementFreight', width:90,editable: true},
			{name:'settlementPrice',index:'settlementPrice', width:90,editable: true},
			{name:'remark',index:'actualShippingAmount', width:90,editable: true}
			
			
			
					], 
// 			                shrinkToFit:false,//不随列数量改变列宽度
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
			
					editurl: getRootPath()+'/ladingbill/update',//nothing is saved
					caption: "司机提货单列表展示",
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
					//修改
// 					  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 						{ caption:"",title:"修改", buttonicon:"icon-pencil blue",
// 					      onClickButton:updateShow, 
// 					      position:"last" }) ;
// 					//删除
// 					  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 						{ caption:"",title:"删除", buttonicon:"icon-trash red",
// 					      onClickButton:deleteShow , 
// 					      position:"last" }) ;
					
					//查询
					  jQuery(grid_selector) .navButtonAdd(pager_selector,
						{ caption:"",title:"查询", buttonicon:"icon-search orange",
					      onClickButton:search_div , 
					      position:"last" }) ;
					

		     //作废
		     function invalidShow(){
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
			    	var rowData = $("#grid-table").jqGrid('getRowData',updateDate);
					
			    	if(rowData.ladingBillStatus != ladingBillStatusConvert('<%=ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02%>','','')
                   		&& rowData.ladingBillStatus != ladingBillStatusConvert('<%=ISystemConstant.DICTIONARY_LADING_BILL_STATUS_03%>','','')){
                   	alert("该提货单不能作废！");
                   	return false;
                   }
   				
            		$.ajax({
                       type: "POST",
                       url: "<%=request.getContextPath()%>/ladingbill/doInvalidLadingBills?date="+new Date(),
                       data:{"ladingBillId":updateDate},		 
                       dataType: "json",
                       async:false , //false为同步
                       success: function(data,status) {
               	   	   if(data.status=="0"){
               	   	       alert(data.message);
               	   	   }else{
                   	   		alert(data.message);
              				   window.location.href="${pageContext.request.contextPath}/ladingbill?operation=ladingBillManage&pcode=${pcode}";
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
                        url: "<%=request.getContextPath()%>/ladingbill/get2/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=orderPlanId]").val(data.bean.orderPlanId);
                      $("#portlet-config-edit").find("input[id=orderPlanCode]").val(data.bean.orderPlanCode);
                      $("#portlet-config-edit").find("input[id=ladingBillCode]").val(data.bean.ladingBillCode);
                      selectSelected($("#portlet-config-edit").find("select[name=billType]"), data.bean.billType);
                    
                      $("#portlet-config-edit").find("input[id=shipMode]").val(data.bean.shipMode);
                      $("#portlet-config-edit").find("input[id=shipModeId]").val(data.bean.shipModeId);
                      $("#portlet-config-edit").find("input[id=productType]").val(data.bean.productType);
                      $("#portlet-config-edit").find("input[id=productTypeId]").val(data.bean.productTypeId);
                      $("#portlet-config-edit").find("input[id=productId]").val(data.bean.productId);
                      $("#portlet-config-edit").find("input[id=productName]").val(data.bean.productName);
                      $("#portlet-config-edit").find("input[id=productModelNumber]").val(data.bean.productModelNumber);
                      $("#portlet-config-edit").find("input[id=phoneNumber]").val(data.bean.phoneNumber);
                      $("#portlet-config-edit").find("input[id=licensePlate]").val(data.bean.licensePlate);
                      $("#portlet-config-edit").find("input[id=carType]").val(data.bean.carType);
                      $("#portlet-config-edit").find("input[id=driverName]").val(data.bean.driverName);
                      $("#portlet-config-edit").find("input[id=driverIdCardNumber]").val(data.bean.driverIdCardNumber);
                      
                      selectSelected($("#portlet-config-edit").find("select[name=ladingBillStatus]"), data.bean.ladingBillStatus);
                      $("#portlet-config-edit").find("input[id=auditTime]").val(data.bean.auditTime);
                      $("#portlet-config-edit").find("input[id=auditPerson]").val(data.bean.auditPerson);
                      $("#portlet-config-edit").find("input[id=auditPersonId]").val(data.bean.auditPersonId);
                      $("#portlet-config-edit").find("input[id=estimatedLoadingTime]").val(data.bean.estimatedLoadingTime);
                      $("#portlet-config-edit").find("input[id=takeNoTime]").val(data.bean.takeNoTime);
                      $("#portlet-config-edit").find("input[id=actualShippingWeight]").val(data.bean.actualShippingWeight);
                      $("#portlet-config-edit").find("input[id=nuclearLoad]").val(data.bean.nuclearLoad);
                      $("#portlet-config-edit").find("input[id=inGate]").val(data.bean.inGate);
                      $("#portlet-config-edit").find("input[id=inTime]").val(data.bean.inTime);
                      $("#portlet-config-edit").find("input[id=emptyWeight]").val(data.bean.emptyWeight);
                      $("#portlet-config-edit").find("input[id=emptyPoundTime]").val(data.bean.emptyPoundTime);
                      $("#portlet-config-edit").find("input[id=fullWeight]").val(data.bean.fullWeight);
                      $("#portlet-config-edit").find("input[id=fullPoundTime]").val(data.bean.fullPoundTime);
                      $("#portlet-config-edit").find("input[id=outGate]").val(data.bean.outGate);
                      $("#portlet-config-edit").find("input[id=outTime]").val(data.bean.outTime);
                      $("#portlet-config-edit").find("input[id=inputPersonId]").val(data.bean.inputPersonId);
                      $("#portlet-config-edit").find("input[id=inputPerson]").val(data.bean.inputPerson);
                      $("#portlet-config-edit").find("input[id=inputTime]").val(data.bean.inputTime);
                      $("#portlet-config-edit").find("input[id=invalidPersonId]").val(data.bean.invalidPersonId);
                      $("#portlet-config-edit").find("input[id=invalidPerson]").val(data.bean.invalidPerson);
                      $("#portlet-config-edit").find("input[id=invalidTime]").val(data.bean.invalidTime);
                      $("#portlet-config-edit").find("input[id=invalidReason]").val(data.bean.invalidReason);
                      $("#portlet-config-edit").find("input[id=isBringBelt]").val(data.bean.isBringBelt);
                      $("#portlet-config-edit").find("input[id=isLipolysis]").val(data.bean.isLipolysis);
                      $("#portlet-config-edit").find("input[id=isSince]").val(data.bean.isSince);
                      $("#portlet-config-edit").find("input[id=isUrgent]").val(data.bean.isUrgent);
                      $("#portlet-config-edit").find("input[id=plannedMileage]").val(data.bean.plannedMileage);
                      $("#portlet-config-edit").find("input[id=factMileage]").val(data.bean.factMileage);
                      $("#portlet-config-edit").find("input[id=valuationMileage]").val(data.bean.valuationMileage);
                      $("#portlet-config-edit").find("input[id=settlementFreight]").val(data.bean.settlementFreight);
                      $("#portlet-config-edit").find("input[id=yuanKmTon]").val(data.bean.yuanKmTon);
                      $("#portlet-config-edit").find("input[id=freight]").val(data.bean.freight);
                      $("#portlet-config-edit").find("input[id=receivingPerson]").val(data.bean.receivingPerson);
                      $("#portlet-config-edit").find("input[id=receivingAddress]").val(data.bean.receivingAddress);
                      $("#portlet-config-edit").find("input[id=receivingPersonPhone]").val(data.bean.receivingPersonPhone);
                      $("#portlet-config-edit").find("input[id=freightSettlementId]").val(data.bean.freightSettlementId);
                      $("#portlet-config-edit").find("input[id=invoiceSettlementId]").val(data.bean.invoiceSettlementId);
                      $("#portlet-config-edit").find("input[id=shipperCode]").val(data.bean.shipperCode);
                      $("#portlet-config-edit").find("input[id=printTime]").val(data.bean.printTime);
                      $("#portlet-config-edit").find("input[id=printTimes]").val(data.bean.printTimes);
                      $("#portlet-config-edit").find("textarea[id=remark]").val(data.bean.remark);
                      $("#portlet-config-edit").find("input[id=logisticsSpecialistId]").val(data.bean.logisticsSpecialistId);
                      $("#portlet-config-edit").find("input[id=logisticsSpecialistName]").val(data.bean.logisticsSpecialistName);
                      $("#portlet-config-edit").find("input[id=importTime]").val(data.bean.importTime);
                      $("#portlet-config-edit").find("input[id=distributionTime]").val(data.bean.distributionTime);
                      $("#portlet-config-edit").find("input[id=logisticsCompanyClerkId]").val(data.bean.logisticsCompanyClerkId);
                      $("#portlet-config-edit").find("input[id=logisticsCompanyClerk]").val(data.bean.logisticsCompanyClerk);
                      $("#portlet-config-edit").find("input[id=allocationVehicleTime]").val(data.bean.allocationVehicleTime);
                      $("#portlet-config-edit").find("input[id=isPound]").val(data.bean.isPound);
                      $("#portlet-config-edit").find("input[id=isLoading]").val(data.bean.isLoading);
                      $(".ui_out_tan").show();
                      $("#portlet-config-edit").show();
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
