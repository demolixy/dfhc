<%@page import="com.dfhc.bus.loadingnotice.ILoadingNoticeConstants" %>
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
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%></span>						<input type="text" class="m-wrap"  name="ladingBillId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','insertForm.ladingBillId,insertForm.ladingBillIdName');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode"  type="text"  						maxLength="13" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("jobTime")%></span><input name="jobTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("jobTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("dispatchTime")%></span><input name="dispatchTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("dispatchTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%></span><input class="m-wrap" name="licensePlate"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span><input class="m-wrap" name="productName"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%></span><input class="m-wrap" name="productModelNumber"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productBatchNumber")%></span><input class="m-wrap" name="productBatchNumber"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productLevel")%></span><input class="m-wrap" name="productLevel"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productLevelId")%></span><input class="m-wrap" name="productLevelId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("num")%></span><input class="m-wrap" name="num"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("gateNum")%></span><input class="m-wrap" name="gateNum"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("positionNum")%></span><input class="m-wrap" name="positionNum"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("toneNum")%></span><input class="m-wrap" name="toneNum"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("storemanId")%></span><input class="m-wrap" name="storemanId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("storeman")%></span><input class="m-wrap" name="storeman"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftMonitorId")%></span><input class="m-wrap" name="forkliftMonitorId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftMonitor")%></span><input class="m-wrap" name="forkliftMonitor"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("operationSupervisorId")%></span><input class="m-wrap" name="operationSupervisorId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("operationSupervisor")%></span><input class="m-wrap" name="operationSupervisor"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS), "", "inputName='" + ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("printDate")%></span><input name="printDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckIsConfirm")%></span><input class="m-wrap" name="forkliftTruckIsConfirm"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorIsConfirm")%></span><input class="m-wrap" name="supervisorIsConfirm"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckConfirmTime")%></span><input name="forkliftTruckConfirmTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckConfirmTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorConfirmTime")%></span><input name="supervisorConfirmTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorConfirmTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("truckInputBreakBagNum")%></span><input class="m-wrap" name="truckInputBreakBagNum"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorInputBreakBagNum")%></span><input class="m-wrap" name="supervisorInputBreakBagNum"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("actualDeliveryNumber")%></span><input class="m-wrap" name="actualDeliveryNumber"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("isNeedWeigh")%></span><input class="m-wrap" name="isNeedWeigh"  type="text"  						maxLength="1" />
							</div>

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/loadingnotice/insert',$(insertForm).serialize())">
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
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%></span>						<input type="text" class="m-wrap"  name="ladingBillId" id="ladingBillId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','insertForm.ladingBillId,insertForm.ladingBillIdName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode" id="ladingBillCode" type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("jobTime")%></span><input name="jobTime" id="jobTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("jobTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("dispatchTime")%></span><input name="dispatchTime" id="dispatchTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("dispatchTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%></span><input class="m-wrap" name="licensePlate" id="licensePlate" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId" id="productId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span><input class="m-wrap" name="productName" id="productName" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%></span><input class="m-wrap" name="productModelNumber" id="productModelNumber" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productBatchNumber")%></span><input class="m-wrap" name="productBatchNumber" id="productBatchNumber" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productLevel")%></span><input class="m-wrap" name="productLevel" id="productLevel" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productLevelId")%></span><input class="m-wrap" name="productLevelId" id="productLevelId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("num")%></span><input class="m-wrap" name="num" id="num" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("gateNum")%></span><input class="m-wrap" name="gateNum" id="gateNum" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("positionNum")%></span><input class="m-wrap" name="positionNum" id="positionNum" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("toneNum")%></span><input class="m-wrap" name="toneNum" id="toneNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("storemanId")%></span><input class="m-wrap" name="storemanId" id="storemanId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("storeman")%></span><input class="m-wrap" name="storeman" id="storeman" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftMonitorId")%></span><input class="m-wrap" name="forkliftMonitorId" id="forkliftMonitorId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftMonitor")%></span><input class="m-wrap" name="forkliftMonitor" id="forkliftMonitor" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("operationSupervisorId")%></span><input class="m-wrap" name="operationSupervisorId" id="operationSupervisorId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("operationSupervisor")%></span><input class="m-wrap" name="operationSupervisor" id="operationSupervisor" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS), "", "inputName='" + ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("printDate")%></span><input name="printDate" id="printDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckIsConfirm")%></span><input class="m-wrap" name="forkliftTruckIsConfirm" id="forkliftTruckIsConfirm" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorIsConfirm")%></span><input class="m-wrap" name="supervisorIsConfirm" id="supervisorIsConfirm" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckConfirmTime")%></span><input name="forkliftTruckConfirmTime" id="forkliftTruckConfirmTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckConfirmTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorConfirmTime")%></span><input name="supervisorConfirmTime" id="supervisorConfirmTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorConfirmTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("truckInputBreakBagNum")%></span><input class="m-wrap" name="truckInputBreakBagNum" id="truckInputBreakBagNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorInputBreakBagNum")%></span><input class="m-wrap" name="supervisorInputBreakBagNum" id="supervisorInputBreakBagNum" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("actualDeliveryNumber")%></span><input class="m-wrap" name="actualDeliveryNumber" id="actualDeliveryNumber" type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("isNeedWeigh")%></span><input class="m-wrap" name="isNeedWeigh" id="isNeedWeigh" type="text"  						maxLength="1" />
							</div>


							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/loadingnotice/update',$(updateForm).serialize())">
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
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillId")%></span>						<input type="text" class="m-wrap"  name="ladingBillId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/ladingbill/reference?referenceInputType=radio','insertForm.ladingBillId,insertForm.ladingBillIdName');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("ladingBillCode")%></span><input class="m-wrap" name="ladingBillCode"  type="text"  						maxLength="13" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("jobTime")%></span><input name="jobTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("jobTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("dispatchTime")%></span><input name="dispatchTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("dispatchTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%></span><input class="m-wrap" name="licensePlate"  type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productId")%></span><input class="m-wrap" name="productId"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productName")%></span><input class="m-wrap" name="productName"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productModelNumber")%></span><input class="m-wrap" name="productModelNumber"  type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productBatchNumber")%></span><input class="m-wrap" name="productBatchNumber"  type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productLevel")%></span><input class="m-wrap" name="productLevel"  type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productLevelId")%></span><input class="m-wrap" name="productLevelId"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("num")%></span><input class="m-wrap" name="num"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("gateNum")%></span><input class="m-wrap" name="gateNum"  type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("positionNum")%></span><input class="m-wrap" name="positionNum"  type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("toneNum")%></span><input class="m-wrap" name="toneNum"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("storemanId")%></span><input class="m-wrap" name="storemanId"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("storeman")%></span><input class="m-wrap" name="storeman"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftMonitorId")%></span><input class="m-wrap" name="forkliftMonitorId"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftMonitor")%></span><input class="m-wrap" name="forkliftMonitor"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("operationSupervisorId")%></span><input class="m-wrap" name="operationSupervisorId"  type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("operationSupervisor")%></span><input class="m-wrap" name="operationSupervisor"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_LOADING_NOTICE_STATUS), "", "inputName='" + ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("printDate")%></span><input name="printDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("printDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckIsConfirm")%></span><input class="m-wrap" name="forkliftTruckIsConfirm"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorIsConfirm")%></span><input class="m-wrap" name="supervisorIsConfirm"  type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckConfirmTime")%></span><input name="forkliftTruckConfirmTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("forkliftTruckConfirmTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorConfirmTime")%></span><input name="supervisorConfirmTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorConfirmTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("truckInputBreakBagNum")%></span><input class="m-wrap" name="truckInputBreakBagNum"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("supervisorInputBreakBagNum")%></span><input class="m-wrap" name="supervisorInputBreakBagNum"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("actualDeliveryNumber")%></span><input class="m-wrap" name="actualDeliveryNumber"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("isNeedWeigh")%></span><input class="m-wrap" name="isNeedWeigh"  type="text"  						maxLength="1" />
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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/loadingnotice/logicDelete?pcode=${pcode }');">
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
								<a href="#">装车业务</a>
							</li>
							<li class="active">
								<a href="#">装车抢单业务</a>
							</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<!-- <div class="page-header">
							<h1>
								装车通知单
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
					
					url: getRootPath()+'/loadingnotice/ajaxList?loadingLading=loadingLading',
					datatype: "json",
				    mtype: 'POST',  
					height: '100%',
					colNames:[
'装车时间'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("licensePlate")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productName")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("productBatchNumber")%>',
'产品规格'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("gateNum")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("positionNum")%>'
,'<%=ILoadingNoticeConstants.TABLE_COLUMN_DISPLAY.get("toneNum")%>',
'司机姓名','联系方式','装车状态','装车班长'
],
					colModel:[
			{name:'jobTime',index:'jobTime',width:90, editable:true, sorttype:"date",formatter: formatDateRow},
			{name:'licensePlate',index:'licensePlate', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'productName',index:'productName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'productBatchNumber',index:'productBatchNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'productLevel',index:'productLevel', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'gateNum',index:'gateNum', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'positionNum',index:'positionNum', width:90,editable: true,editoptions:{size:"20",maxLength:"100"}},
			{name:'toneNum',index:'toneNum', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'driverName',index:'driverName', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'phoneNumber',index:'phoneNumber', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
			{name:'status',index:'status', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}, formatter:loadingStatusConvert},
			{name:'forkliftMonitor',index:'forkliftMonitor', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
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
			
					editurl: getRootPath()+'/loadingnotice/update',//nothing is saved
					caption: "装车通知单列表展示",
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
                        url: "<%=request.getContextPath()%>/loadingnotice/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=ladingBillId]").val(data.bean.ladingBillId);
                      $("#portlet-config-edit").find("input[id=ladingBillCode]").val(data.bean.ladingBillCode);
                      $("#portlet-config-edit").find("input[id=jobTime]").val(data.bean.jobTime);
                      $("#portlet-config-edit").find("input[id=dispatchTime]").val(data.bean.dispatchTime);
                      $("#portlet-config-edit").find("input[id=licensePlate]").val(data.bean.licensePlate);
                      $("#portlet-config-edit").find("input[id=productId]").val(data.bean.productId);
                      $("#portlet-config-edit").find("input[id=productName]").val(data.bean.productName);
                      $("#portlet-config-edit").find("input[id=productModelNumber]").val(data.bean.productModelNumber);
                      $("#portlet-config-edit").find("input[id=productBatchNumber]").val(data.bean.productBatchNumber);
                      $("#portlet-config-edit").find("input[id=productLevel]").val(data.bean.productLevel);
                      $("#portlet-config-edit").find("input[id=productLevelId]").val(data.bean.productLevelId);
                      $("#portlet-config-edit").find("input[id=num]").val(data.bean.num);
                      $("#portlet-config-edit").find("input[id=gateNum]").val(data.bean.gateNum);
                      $("#portlet-config-edit").find("input[id=positionNum]").val(data.bean.positionNum);
                      $("#portlet-config-edit").find("input[id=toneNum]").val(data.bean.toneNum);
                      $("#portlet-config-edit").find("input[id=storemanId]").val(data.bean.storemanId);
                      $("#portlet-config-edit").find("input[id=storeman]").val(data.bean.storeman);
                      $("#portlet-config-edit").find("input[id=forkliftMonitorId]").val(data.bean.forkliftMonitorId);
                      $("#portlet-config-edit").find("input[id=forkliftMonitor]").val(data.bean.forkliftMonitor);
                      $("#portlet-config-edit").find("input[id=operationSupervisorId]").val(data.bean.operationSupervisorId);
                      $("#portlet-config-edit").find("input[id=operationSupervisor]").val(data.bean.operationSupervisor);
                      $("#portlet-config-edit").find("input[id=status]").val(data.bean.status);
                      $("#portlet-config-edit").find("input[id=printDate]").val(data.bean.printDate);
                      $("#portlet-config-edit").find("input[id=forkliftTruckIsConfirm]").val(data.bean.forkliftTruckIsConfirm);
                      $("#portlet-config-edit").find("input[id=supervisorIsConfirm]").val(data.bean.supervisorIsConfirm);
                      $("#portlet-config-edit").find("input[id=forkliftTruckConfirmTime]").val(data.bean.forkliftTruckConfirmTime);
                      $("#portlet-config-edit").find("input[id=supervisorConfirmTime]").val(data.bean.supervisorConfirmTime);
                      $("#portlet-config-edit").find("input[id=truckInputBreakBagNum]").val(data.bean.truckInputBreakBagNum);
                      $("#portlet-config-edit").find("input[id=supervisorInputBreakBagNum]").val(data.bean.supervisorInputBreakBagNum);
                      $("#portlet-config-edit").find("input[id=actualDeliveryNumber]").val(data.bean.actualDeliveryNumber);
                      $("#portlet-config-edit").find("input[id=isNeedWeigh]").val(data.bean.isNeedWeigh);
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
