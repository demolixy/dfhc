<%@page import="com.dfhc.bus.vehiclesafetyinspection.IVehicleSafetyInspectionConstants" %>
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
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%></span>						<input type="text" class="m-wrap"  name="ladingBillId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','insertForm.ladingBillId,insertForm.ladingBillIdName');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode"  type="text"  						maxLength="13" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%></span><input class="m-wrap" name="licensePlate"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("shippingOilVariety")%></span><input class="m-wrap" name="shippingOilVariety"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("tankVolume")%></span><input class="m-wrap" name="tankVolume"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("maxWorkingPressureTank")%></span><input class="m-wrap" name="maxWorkingPressureTank"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("maxLoadCarryingTank")%></span><input class="m-wrap" name="maxLoadCarryingTank"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("beforeResidualPressureTank")%></span><input class="m-wrap" name="beforeResidualPressureTank"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("isConsistentOriOil")%></span><input class="m-wrap" name="isConsistentOriOil"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("brakeWell")%></span><input class="m-wrap" name="brakeWell"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("staticGroundingZone")%></span><input class="m-wrap" name="staticGroundingZone"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("pressureGaugeLevelGauge")%></span><input class="m-wrap" name="pressureGaugeLevelGauge"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("gps")%></span><input class="m-wrap" name="gps"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("safetyValveIsGood")%></span><input class="m-wrap" name="safetyValveIsGood"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("normalVehicleBrake")%></span><input class="m-wrap" name="normalVehicleBrake"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("fireExtinguisher")%></span><input class="m-wrap" name="fireExtinguisher"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("flashHider")%></span><input class="m-wrap" name="flashHider"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("driverLicensePass")%></span><input class="m-wrap" name="driverLicensePass"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ownerName")%></span><input class="m-wrap" name="ownerName"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("dangerousQualifiedEscort")%></span><input class="m-wrap" name="dangerousQualifiedEscort"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("dangerousTransCertificate")%></span><input class="m-wrap" name="dangerousTransCertificate"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("pressureVesselCalibration")%></span><input class="m-wrap" name="pressureVesselCalibration"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("withoutFireworks")%></span><input class="m-wrap" name="withoutFireworks"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkMan")%></span><input class="m-wrap" name="checkMan"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkTime")%></span><input name="checkTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_CHECK_STATUS), "", "inputName='" + IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/vehiclesafetyinspection/insert',$(insertForm).serialize())">
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
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%></span>						<input type="text" class="m-wrap"  name="ladingBillId" id="ladingBillId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','insertForm.ladingBillId,insertForm.ladingBillIdName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode" id="ladingBillCode" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%></span><input class="m-wrap" name="licensePlate" id="licensePlate" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("shippingOilVariety")%></span><input class="m-wrap" name="shippingOilVariety" id="shippingOilVariety" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("tankVolume")%></span><input class="m-wrap" name="tankVolume" id="tankVolume" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("maxWorkingPressureTank")%></span><input class="m-wrap" name="maxWorkingPressureTank" id="maxWorkingPressureTank" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("maxLoadCarryingTank")%></span><input class="m-wrap" name="maxLoadCarryingTank" id="maxLoadCarryingTank" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("beforeResidualPressureTank")%></span><input class="m-wrap" name="beforeResidualPressureTank" id="beforeResidualPressureTank" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("isConsistentOriOil")%></span><input class="m-wrap" name="isConsistentOriOil" id="isConsistentOriOil" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("brakeWell")%></span><input class="m-wrap" name="brakeWell" id="brakeWell" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("staticGroundingZone")%></span><input class="m-wrap" name="staticGroundingZone" id="staticGroundingZone" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("pressureGaugeLevelGauge")%></span><input class="m-wrap" name="pressureGaugeLevelGauge" id="pressureGaugeLevelGauge" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("gps")%></span><input class="m-wrap" name="gps" id="gps" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("safetyValveIsGood")%></span><input class="m-wrap" name="safetyValveIsGood" id="safetyValveIsGood" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("normalVehicleBrake")%></span><input class="m-wrap" name="normalVehicleBrake" id="normalVehicleBrake" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("fireExtinguisher")%></span><input class="m-wrap" name="fireExtinguisher" id="fireExtinguisher" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("flashHider")%></span><input class="m-wrap" name="flashHider" id="flashHider" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("driverLicensePass")%></span><input class="m-wrap" name="driverLicensePass" id="driverLicensePass" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ownerName")%></span><input class="m-wrap" name="ownerName" id="ownerName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("dangerousQualifiedEscort")%></span><input class="m-wrap" name="dangerousQualifiedEscort" id="dangerousQualifiedEscort" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("dangerousTransCertificate")%></span><input class="m-wrap" name="dangerousTransCertificate" id="dangerousTransCertificate" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("pressureVesselCalibration")%></span><input class="m-wrap" name="pressureVesselCalibration" id="pressureVesselCalibration" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("withoutFireworks")%></span><input class="m-wrap" name="withoutFireworks" id="withoutFireworks" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkMan")%></span><input class="m-wrap" name="checkMan" id="checkMan" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkTime")%></span><input name="checkTime" id="checkTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_CHECK_STATUS), "", "inputName='" + IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>


							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/vehiclesafetyinspection/update',$(updateForm).serialize())">
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
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%></span>						<input type="text" class="m-wrap"  name="ladingBillId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','insertForm.ladingBillId,insertForm.ladingBillIdName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode"  type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%></span><input class="m-wrap" name="licensePlate"  type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("shippingOilVariety")%></span><input class="m-wrap" name="shippingOilVariety"  type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("tankVolume")%></span><input class="m-wrap" name="tankVolume"  type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("maxWorkingPressureTank")%></span><input class="m-wrap" name="maxWorkingPressureTank"  type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("maxLoadCarryingTank")%></span><input class="m-wrap" name="maxLoadCarryingTank"  type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("beforeResidualPressureTank")%></span><input class="m-wrap" name="beforeResidualPressureTank"  type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("isConsistentOriOil")%></span><input class="m-wrap" name="isConsistentOriOil"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("brakeWell")%></span><input class="m-wrap" name="brakeWell"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("staticGroundingZone")%></span><input class="m-wrap" name="staticGroundingZone"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("pressureGaugeLevelGauge")%></span><input class="m-wrap" name="pressureGaugeLevelGauge"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("gps")%></span><input class="m-wrap" name="gps"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("safetyValveIsGood")%></span><input class="m-wrap" name="safetyValveIsGood"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("normalVehicleBrake")%></span><input class="m-wrap" name="normalVehicleBrake"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("fireExtinguisher")%></span><input class="m-wrap" name="fireExtinguisher"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("flashHider")%></span><input class="m-wrap" name="flashHider"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("driverLicensePass")%></span><input class="m-wrap" name="driverLicensePass"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ownerName")%></span><input class="m-wrap" name="ownerName"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("dangerousQualifiedEscort")%></span><input class="m-wrap" name="dangerousQualifiedEscort"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("dangerousTransCertificate")%></span><input class="m-wrap" name="dangerousTransCertificate"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("pressureVesselCalibration")%></span><input class="m-wrap" name="pressureVesselCalibration"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("withoutFireworks")%></span><input class="m-wrap" name="withoutFireworks"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkMan")%></span><input class="m-wrap" name="checkMan"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkTime")%></span><input name="checkTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_CHECK_STATUS), "", "inputName='" + IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>


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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/vehiclesafetyinspection/logicDelete?pcode=${pcode }');">
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
							<li class="active">安全检查管理</li>
						</ul><!-- .breadcrumb -->
						<%-- 
						<div class="nav-search" id="nav-search">
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
								车辆安全检查
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
					
					url: getRootPath()+'/vehiclesafetyinspection/ajaxList',
					datatype: "json",
				    mtype: 'POST',  
					height: '100%',
					colNames:[
'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("shippingOilVariety")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("tankVolume")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("maxWorkingPressureTank")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("maxLoadCarryingTank")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("beforeResidualPressureTank")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("isConsistentOriOil")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("brakeWell")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("staticGroundingZone")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("pressureGaugeLevelGauge")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("gps")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("safetyValveIsGood")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("normalVehicleBrake")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("fireExtinguisher")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("flashHider")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("driverLicensePass")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("ownerName")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("dangerousQualifiedEscort")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("dangerousTransCertificate")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("pressureVesselCalibration")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("withoutFireworks")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkMan")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("checkTime")%>'
,'<%=IVehicleSafetyInspectionConstants.TABLE_COLUMN_DISPLAY.get("status")%>'
],
					colModel:[
			{name:'ladingBillCode',index:'ladingBillCode', width:90,editable: true,editoptions:{size:"20",maxLength:"13"}},
			{name:'licensePlate',index:'licensePlate', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'shippingOilVariety',index:'shippingOilVariety', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'tankVolume',index:'tankVolume', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'maxWorkingPressureTank',index:'maxWorkingPressureTank', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'maxLoadCarryingTank',index:'maxLoadCarryingTank', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'beforeResidualPressureTank',index:'beforeResidualPressureTank', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			{name:'isConsistentOriOil',index:'isConsistentOriOil', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'brakeWell',index:'brakeWell', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'staticGroundingZone',index:'staticGroundingZone', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'pressureGaugeLevelGauge',index:'pressureGaugeLevelGauge', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'gps',index:'gps', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'safetyValveIsGood',index:'safetyValveIsGood', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'normalVehicleBrake',index:'normalVehicleBrake', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'fireExtinguisher',index:'fireExtinguisher', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'flashHider',index:'flashHider', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'driverLicensePass',index:'driverLicensePass', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'ownerName',index:'ownerName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'dangerousQualifiedEscort',index:'dangerousQualifiedEscort', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'dangerousTransCertificate',index:'dangerousTransCertificate', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'pressureVesselCalibration',index:'pressureVesselCalibration', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'withoutFireworks',index:'withoutFireworks', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}},
			{name:'checkMan',index:'checkMan', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'checkTime',index:'checkTime',width:90, editable:true, sorttype:"date",unformat: pickDate}
,
			{name:'status',index:'status', width:90,editable: true,editoptions:{size:"20",maxLength:"1"}}
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
			
					editurl: getRootPath()+'/vehiclesafetyinspection/update',//nothing is saved
					caption: "车辆安全检查列表展示",
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
				
				/*//新增
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
				*/
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
                        url: "<%=request.getContextPath()%>/vehiclesafetyinspection/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=ladingBillId]").val(data.bean.ladingBillId);
                      $("#portlet-config-edit").find("input[id=ladingBillCode]").val(data.bean.ladingBillCode);
                      $("#portlet-config-edit").find("input[id=licensePlate]").val(data.bean.licensePlate);
                      $("#portlet-config-edit").find("input[id=shippingOilVariety]").val(data.bean.shippingOilVariety);
                      $("#portlet-config-edit").find("input[id=tankVolume]").val(data.bean.tankVolume);
                      $("#portlet-config-edit").find("input[id=maxWorkingPressureTank]").val(data.bean.maxWorkingPressureTank);
                      $("#portlet-config-edit").find("input[id=maxLoadCarryingTank]").val(data.bean.maxLoadCarryingTank);
                      $("#portlet-config-edit").find("input[id=beforeResidualPressureTank]").val(data.bean.beforeResidualPressureTank);
                      $("#portlet-config-edit").find("input[id=isConsistentOriOil]").val(data.bean.isConsistentOriOil);
                      $("#portlet-config-edit").find("input[id=brakeWell]").val(data.bean.brakeWell);
                      $("#portlet-config-edit").find("input[id=staticGroundingZone]").val(data.bean.staticGroundingZone);
                      $("#portlet-config-edit").find("input[id=pressureGaugeLevelGauge]").val(data.bean.pressureGaugeLevelGauge);
                      $("#portlet-config-edit").find("input[id=gps]").val(data.bean.gps);
                      $("#portlet-config-edit").find("input[id=safetyValveIsGood]").val(data.bean.safetyValveIsGood);
                      $("#portlet-config-edit").find("input[id=normalVehicleBrake]").val(data.bean.normalVehicleBrake);
                      $("#portlet-config-edit").find("input[id=fireExtinguisher]").val(data.bean.fireExtinguisher);
                      $("#portlet-config-edit").find("input[id=flashHider]").val(data.bean.flashHider);
                      $("#portlet-config-edit").find("input[id=driverLicensePass]").val(data.bean.driverLicensePass);
                      $("#portlet-config-edit").find("input[id=ownerName]").val(data.bean.ownerName);
                      $("#portlet-config-edit").find("input[id=dangerousQualifiedEscort]").val(data.bean.dangerousQualifiedEscort);
                      $("#portlet-config-edit").find("input[id=dangerousTransCertificate]").val(data.bean.dangerousTransCertificate);
                      $("#portlet-config-edit").find("input[id=pressureVesselCalibration]").val(data.bean.pressureVesselCalibration);
                      $("#portlet-config-edit").find("input[id=withoutFireworks]").val(data.bean.withoutFireworks);
                      $("#portlet-config-edit").find("input[id=checkMan]").val(data.bean.checkMan);
                      $("#portlet-config-edit").find("input[id=checkTime]").val(data.bean.checkTime);
                      $("#portlet-config-edit").find("input[id=status]").val(data.bean.status);
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
