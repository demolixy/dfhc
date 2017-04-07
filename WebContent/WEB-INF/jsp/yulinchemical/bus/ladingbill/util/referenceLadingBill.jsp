<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：中煤陕西榆林能源化工产品销售智能管理系统
   *
   * 文件名称：referenceLadingBill.jsp
   *
   * 功能描述：司机提货单参照页面
   * 
   * 版本历史：
   * 2017-01-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
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
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("orderPlanId")%></span><input type="text" class="text_field_reference text_field_readonly"  hiddenInputId="orderPlanId" name="orderPlanIdName" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("orderPlanId")%>" value="" /><input type="hidden" name="orderPlanId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getReference(new Array(form.orderPlanId, form.orderPlanIdName), '<%=request.getContextPath()%>/', '<%=request.getContextPath()%>/order/reference?referenceInputType=radio');"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("orderPlanCode")%></span><input class="m-wrap" name="orderPlanCode" id="orderPlanCode" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode" id="ladingBillCode" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("billType")%></span><%=RmJspHelper.getSelectField("billType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_BILL_TYPE), "", "inputName='" + ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("billType") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("shipMode")%></span><input class="m-wrap" name="shipMode" id="shipMode" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("shipModeId")%></span><input class="m-wrap" name="shipModeId" id="shipModeId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productType")%></span><input class="m-wrap" name="productType" id="productType" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productTypeId")%></span><input class="m-wrap" name="productTypeId" id="productTypeId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId" id="productId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span><input class="m-wrap" name="productName" id="productName" type="text"  						maxLength="200" />
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
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%></span><input name="auditTime" id="auditTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditPerson")%></span><input class="m-wrap" name="auditPerson" id="auditPerson" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditPersonId")%></span><input class="m-wrap" name="auditPersonId" id="auditPersonId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("estimatedLoadingTime")%></span><input name="estimatedLoadingTime" id="estimatedLoadingTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("estimatedLoadingTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("takeNoTime")%></span><input name="takeNoTime" id="takeNoTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("takeNoTime")%>" />
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
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inTime")%></span><input name="inTime" id="inTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyWeight")%></span><input class="m-wrap" name="emptyWeight" id="emptyWeight" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyPoundTime")%></span><input name="emptyPoundTime" id="emptyPoundTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyPoundTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullWeight")%></span><input class="m-wrap" name="fullWeight" id="fullWeight" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullPoundTime")%></span><input name="fullPoundTime" id="fullPoundTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullPoundTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outGate")%></span><input class="m-wrap" name="outGate" id="outGate" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outTime")%></span><input name="outTime" id="outTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputPersonId")%></span><input class="m-wrap" name="inputPersonId" id="inputPersonId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputPerson")%></span><input class="m-wrap" name="inputPerson" id="inputPerson" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputTime")%></span><input name="inputTime" id="inputTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidPersonId")%></span><input class="m-wrap" name="invalidPersonId" id="invalidPersonId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidPerson")%></span><input class="m-wrap" name="invalidPerson" id="invalidPerson" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidTime")%></span><input name="invalidTime" id="invalidTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidReason")%></span><input class="m-wrap" name="invalidReason" id="invalidReason" type="text"  						maxLength="300" />
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
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("freightSettlementId")%></span><input type="text" class="text_field_reference text_field_readonly"  hiddenInputId="freightSettlementId" name="freightSettlementIdName" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("freightSettlementId")%>" value="" /><input type="hidden" name="freightSettlementId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getReference(new Array(form.freightSettlementId, form.freightSettlementIdName), '<%=request.getContextPath()%>/', '<%=request.getContextPath()%>/logisticscostsettlement/reference?referenceInputType=radio');"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invoiceSettlementId")%></span><input type="text" class="text_field_reference text_field_readonly"  hiddenInputId="invoiceSettlementId" name="invoiceSettlementIdName" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invoiceSettlementId")%>" value="" /><input type="hidden" name="invoiceSettlementId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getReference(new Array(form.invoiceSettlementId, form.invoiceSettlementIdName), '<%=request.getContextPath()%>/', '<%=request.getContextPath()%>/receiptconfletter/reference?referenceInputType=radio');"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode" id="shipperCode" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("printTime")%></span><input name="printTime" id="printTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("printTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("printTimes")%></span><input class="m-wrap" name="printTimes" id="printTimes" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("remark")%></span>					<textarea class="medium m-wrap" name="remark" id="remark" 					inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("remark")%>" 						 maxLength="200" 					 ></textarea>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsSpecialistId")%></span><input class="m-wrap" name="logisticsSpecialistId" id="logisticsSpecialistId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsSpecialistName")%></span><input class="m-wrap" name="logisticsSpecialistName" id="logisticsSpecialistName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("importTime")%></span><input name="importTime" id="importTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("importTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("distributionTime")%></span><input name="distributionTime" id="distributionTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("distributionTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompanyClerkId")%></span><input class="m-wrap" name="logisticsCompanyClerkId" id="logisticsCompanyClerkId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompanyClerk")%></span><input class="m-wrap" name="logisticsCompanyClerk" id="logisticsCompanyClerk" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("allocationVehicleTime")%></span><input name="allocationVehicleTime" id="allocationVehicleTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("allocationVehicleTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isPound")%></span><%=RmJspHelper.getSelectField("isPound", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isPound") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isLoading")%></span><%=RmJspHelper.getSelectField("isLoading", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isLoading") + "'  ", true) %>							</div>

							</div>
			 </div>
     <input type="hidden" name="referenceInputType" value="${referenceInputType}"/>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:searchShi('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=${referenceInputType}');">
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
					
					url: getRootPath()+'/ladingbill/referenceAjax?${queryString}',
					datatype: "json",
				    mtype: 'POST',  
					height: '100%',
					colNames:['ladingBillCode',
'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("orderPlanCode")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("billType")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("shipMode")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productType")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productName")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("phoneNumber")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("carType")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("driverName")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("driverIdCardNumber")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("ladingBillStatus")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("auditPerson")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("estimatedLoadingTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("takeNoTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("actualShippingWeight")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("nuclearLoad")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inGate")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyWeight")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("emptyPoundTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullWeight")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("fullPoundTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outGate")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("outTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputPerson")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("inputTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidPerson")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("invalidReason")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isBringBelt")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isLipolysis")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isSince")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isUrgent")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("factMileage")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("valuationMileage")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("settlementFreight")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("yuanKmTon")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("freight")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingPerson")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingAddress")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("receivingPersonPhone")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("printTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("printTimes")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("remark")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsSpecialistName")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("importTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("distributionTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompanyClerk")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("allocationVehicleTime")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isPound")%>'
,'<%=ILadingBillConstants.TABLE_COLUMN_DISPLAY.get("isLoading")%>'
],
					colModel:[
					    {name:'ladingBillCode',index:'ladingBillCode',width:90, editable:true,hidedlg:true,hidden:true},		
			{name:'orderPlanCode',index:'orderPlanCode', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'ladingBillCode',index:'ladingBillCode', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'billType',index:'billType', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'shipMode',index:'shipMode', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'productType',index:'productType', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'productName',index:'productName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'productModelNumber',index:'productModelNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'phoneNumber',index:'phoneNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"20"}},
			{name:'licensePlate',index:'licensePlate', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'carType',index:'carType', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'driverName',index:'driverName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'driverIdCardNumber',index:'driverIdCardNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"18"}},
			{name:'ladingBillStatus',index:'ladingBillStatus', width:90,editable: true,editoptions:{size:"20",maxLength:"2"}},
			{name:'auditTime',index:'auditTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'auditPerson',index:'auditPerson', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'estimatedLoadingTime',index:'estimatedLoadingTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'takeNoTime',index:'takeNoTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'actualShippingWeight',index:'actualShippingWeight', width:90,editable: true,editoptions:{size:"20",maxLength:"14"}},
			{name:'nuclearLoad',index:'nuclearLoad', width:90,editable: true,editoptions:{size:"20",maxLength:"14"}},
			{name:'inGate',index:'inGate', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'inTime',index:'inTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'emptyWeight',index:'emptyWeight', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'emptyPoundTime',index:'emptyPoundTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'fullWeight',index:'fullWeight', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'fullPoundTime',index:'fullPoundTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'outGate',index:'outGate', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'outTime',index:'outTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'inputPerson',index:'inputPerson', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'inputTime',index:'inputTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'invalidPerson',index:'invalidPerson', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'invalidTime',index:'invalidTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'invalidReason',index:'invalidReason', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'isBringBelt',index:'isBringBelt', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'isLipolysis',index:'isLipolysis', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'isSince',index:'isSince', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'isUrgent',index:'isUrgent', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'plannedMileage',index:'plannedMileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}},
			{name:'factMileage',index:'factMileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}},
			{name:'valuationMileage',index:'valuationMileage', width:90,editable: true,editoptions:{size:"20",maxLength:"12"}},
			{name:'settlementFreight',index:'settlementFreight', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'yuanKmTon',index:'yuanKmTon', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'freight',index:'freight', width:90,editable: true,editoptions:{size:"20",maxLength:"19"}},
			{name:'receivingPerson',index:'receivingPerson', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'receivingAddress',index:'receivingAddress', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'receivingPersonPhone',index:'receivingPersonPhone', width:90,editable: true,editoptions:{size:"20",maxLength:"20"}},
			{name:'shipperCode',index:'shipperCode', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'printTime',index:'printTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'printTimes',index:'printTimes', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'remark',index:'remark', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'logisticsSpecialistName',index:'logisticsSpecialistName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'importTime',index:'importTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'distributionTime',index:'distributionTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'logisticsCompanyClerk',index:'logisticsCompanyClerk', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'allocationVehicleTime',index:'allocationVehicleTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'isPound',index:'isPound', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'isLoading',index:'isLoading', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}}
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
