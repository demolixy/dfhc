<%@page import="com.dfhc.bus.cardistributionplan.ICarDistributionPlanConstants" %>
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



$(function(){
	shipperCodeLuShow=[]; 

	 <c:forEach items="${shipperCodeLu}" var="listStatus"> 
	     var keyL ='${listStatus.code}';
	     shipperCodeLuShow[keyL]='${listStatus.name}';
	 </c:forEach> 	

	 listLogicShow="<select  name=\"logisticsCompany\" ><option  value=\"1\">--请选择--</option>";
	 <c:forEach items="${listLogic}" var="logic"> 
	 listLogicShow +="<option  value=\"${logic.id}\">${logic.name}</option>";
	 </c:forEach> 	
	 listLogicShow +="</select>";

	 
	 jiJiaStr="<input  name=\"plan\"  value=\"";
	 jiJiaEnd="\"/>";
})

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

	 <c:forEach items="${PLAN_STATUS}" var="listStatus"> 
	     var keyL ='${listStatus.key}';
	     orderStatusShow[keyL]='${listStatus.value}';
	 </c:forEach> 	
})

function  submitInsert(url,param){
	if(checkNum("#updateForm")){
		submitInsertAjax(url,param);
	}
}

function  checkNum(form){
	//数量
	var num = $(form).find("input[name=num]").val();
	if($.trim(num).length==0){
    	alert("数量不能为空!");
		return false;
	}else{
		var reg = /^[1-9]\d*$/;
		if(!reg.test(num)){
			 alert("请重新输入数量(必须为整数)!");
	         return false;
		}
	}
	//计划车数
	var planCarNum = $(form).find("input[name=planCarNum]").val();
	if($.trim(planCarNum).length==0){
    	alert("计划车数不能为空!");
		return false;
	}else{
		var reg = /^[1-9]\d*$/;
		if(!reg.test(planCarNum)){
			 alert("请重新输入计划车数(必须为整数)!");
	         return false;
		}
	}
	//发货公司
	var shipperCode = $(form).find("select[name=shipperCode]").val();
	if($.trim(shipperCode).length==0){
    	alert("发货公司不能为空!");
		return false;
	}

	//物流公司
	var logisticsCompany = $(form).find("input[name=logisticsCompany]").val();
	if($.trim(logisticsCompany).length==0){
    	alert("物流公司不能为空!");
		return false;
	}
	//计划里程
	var plannedMileage = $(form).find("input[name=plannedMileage]").val();
	if($.trim(plannedMileage).length==0){
    	alert("计划里程不能为空!");
		return false;
	}else{
		var regu = /^[0-9]\d*[\.]?[0-9]{0,2}$/;
	    if (!regu.test(plannedMileage)) {
	    	alert("请重新输入计划里程(大于0,最多两位小数)!");
			return false;
	    }
	}
	
	//货物单价
	var price = $(form).find("input[name=price]").val();
	if($.trim(price).length==0){
    	alert("货物单价不能为空!");
		return false;
	}else{
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (!regu.test(price)) {
	    	alert("请重新输入货物单价(大于0,最多四位小数)!");
			return false;
	    }
	}
	//发车日期
	var sendCarDate = $(form).find("input[name=sendCarDate]").val();
	if(sendCarDate==""){
    	alert("发车日期不能为空!");
		return false;
	}
// 	var d2=new Date();//取今天的日期
// 	var d1 = new Date(Date.parse(sendCarDate));
// 	if(d2>d1){
//     	alert("发车日期不能小于当前时间!");
// 		return false;
// 	}
	
	
	return   true;

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
							

							
		<%--					<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCode")%></span><input class="m-wrap" name="planCode"  type="text"  						maxLength="13" />
							</div> --%>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCode")%></span><input type="text" class="m-wrap"  name="planCode" readonly="readonly"  /><input type="hidden" class="m-wrap"  name="distributionPlanId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/distributionplan/reference?referenceInputType=radio','insertForm.distributionPlanId,insertForm.planCode');">选择</span><span class="style_required_red">* </span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%></span><input name="makeDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%></span><input class="m-wrap" name="logisticsCompany"  type="text"  readonly="readonly" /><input class="m-wrap" name="logisticsCompanyId"  type="hidden" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/logisticscompany/reference?referenceInputType=radio','insertForm.logisticsCompanyId,insertForm.logisticsCompany');">选择</span>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("region")%></span><input class="m-wrap" name="region"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("customerName")%></span><input class="m-wrap" name="customerName"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("goodsName")%></span><input class="m-wrap" name="goodsName"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPerson")%></span><input class="m-wrap" name="receivingPerson"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPersonPhone")%></span><input class="m-wrap" name="receivingPersonPhone"  type="text"  						maxLength="20" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("area")%></span><input class="m-wrap" name="area"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingAddress")%></span><input class="m-wrap" name="shippingAddress"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("num")%></span><input class="m-wrap" name="num"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%></span><input class="m-wrap" name="plannedMileage"  type="text"  						maxLength="12" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("factMileage")%></span><input class="m-wrap" name="factMileage"  type="text"  						maxLength="12" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("valuationMileage")%></span><input class="m-wrap" name="valuationMileage"  type="text"  						maxLength="12" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("tonKm")%></span><input class="m-wrap" name="tonKm"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("settlementFreight")%></span><input class="m-wrap" name="settlementFreight"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("totalFreight")%></span><input class="m-wrap" name="totalFreight"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("price")%></span><input class="m-wrap" name="price"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%></span>           	                       <textarea class="medium m-wrap" name="remark" id="remark" 					inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%>" 						 maxLength="300" 					 ></textarea>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%></span><input name="sendCarDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_PLAN_STATUS), "", "inputName='" + ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remainderNum")%></span><input class="m-wrap" name="remainderNum"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remainderCarNum")%></span><input class="m-wrap" name="remainderCarNum"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")%></span><input class="m-wrap" name="shipperCode"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingVehiclesNumber")%></span><input class="m-wrap" name="shippingVehiclesNumber"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingNumber")%></span><input class="m-wrap" name="shippingNumber"  type="text"  						maxLength="10" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("flowToId")%></span>	<input type="text" class="m-wrap"  name="flowToIdName" id="flowToIdName" readonly="readonly"/><input type="hidden" class="m-wrap"  name="flowToId" id="flowToId" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/flowto/reference?referenceInputType=radio','insertForm.flowToId,insertForm.flowToIdName');">选择</span><span class="style_required_red">* </span>

  							</div>

     	                 </div>
                         </div>
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/cardistributionplan/insert',$(insertForm).serialize())">
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
	   <span class="ui-jqdialog-title" style="float: left;">分配物流公司</span>
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
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("distributionPlanId")%></span>		<input type="text" class="m-wrap"  name="planCode" id="planCode" readonly="readonly"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%></span><input name="makeDate" id="makeDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("customerName")%></span><input class="m-wrap" name="customerName" id="customerName" type="text"  	readonly="readonly"						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("goodsName")%></span><input class="m-wrap" name="goodsName" id="goodsName" type="text"  	readonly="readonly"						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPerson")%></span><input class="m-wrap" name="receivingPerson" id="receivingPerson" type="text"  	readonly="readonly"						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPersonPhone")%></span><input class="m-wrap" name="receivingPersonPhone" id="receivingPersonPhone" type="text"  	readonly="readonly"						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("region")%></span><input class="m-wrap" name="region" id="region" type="text"  	readonly="readonly"					maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("area")%></span><input class="m-wrap" name="area" id="area" type="text"  		readonly="readonly"					maxLength="10" />
							</div>

		<%-- 				<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingAddress")%></span><input class="m-wrap" name="shippingAddress" id="shippingAddress" type="text"  		readonly="readonly"					maxLength="300" />
							</div>
--%>
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("num")%></span><input class="m-wrap" name="num" id="num" type="text"  						maxLength="10" />
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCarNum")%></span><input class="m-wrap" name="planCarNum" id="planCarNum" type="text"  						maxLength="10" />
							</div>
							
							<div class="input-prepend" style="margin-top:-11px;">
								<span class="add-on">发货公司</span>
								<%=RmJspHelper.getSelectField("shipperCode",-1, RmProjectHelper.getCommonServiceInstance().paseToArrays("select CODE as data_key,name as data_value from BASE_SHIPPER where nvl(BASE_SHIPPER.DELETE_FLAG,'0') = 0"),"", "    inputName=\""+ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")+"\" validate='notNull;'  ", true , "--请选择--") %>			
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%></span><input class="m-wrap" name="logisticsCompany" id="logisticsCompany" type="text"  readonly="readonly"/><input class="m-wrap" name="logisticsCompanyId" id="logisticsCompanyId" type="hidden" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/logisticscompany/reference?referenceInputType=radio','updateForm.logisticsCompanyId,updateForm.logisticsCompany');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%></span><input class="m-wrap" name="plannedMileage" id="plannedMileage" type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("price")%></span><input class="m-wrap" name="price" id="price" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%></span>           	                       <textarea class="medium m-wrap" name="remark" id="remark" 					inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%>" 						 maxLength="300" 					 ></textarea>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%></span><input name="sendCarDate" id="sendCarDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%>" />
							</div>

							</div>
			 </div>
     
     </form>
     </div>
     
     <div class="EditTable operate_div" >
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left" onclick="javascript:submitInsert('<%=request.getContextPath()%>/cardistributionplan/updateToLogis',$(updateForm).serialize())">
       <i class="icon-ok"></i>
           确定</a>
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
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCode")%></span>  <input type="text" class="m-wrap"  name="planCode" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%></span><input name="makeDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("makeDate")%>" />
							</div>
							
							<div class="input-prepend" style="margin-top:-11px;">
								<span class="add-on">发货公司</span>
								<%=RmJspHelper.getSelectField("shipperCode",-1, RmProjectHelper.getCommonServiceInstance().paseToArrays("select CODE as data_key,name as data_value from BASE_SHIPPER where nvl(BASE_SHIPPER.DELETE_FLAG,'0') = 0"),"", "    inputName=\""+ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shipperCode")+"\" validate='notNull;'  ", true , "--请选择--") %>			
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%></span><input class="m-wrap" name="logisticsCompany" readonly="readonly" type="text" maxLength="300" /><input class="m-wrap" name="logisticsCompanyId"  type="hidden"  						maxLength="300" />
<span class="choose_ref"  onclick="javascript:getNewReference('<%=request.getContextPath()%>/logisticscompany/reference?referenceInputType=radio','form.logisticsCompanyId,form.logisticsCompany');">选择</span>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("customerName")%></span><input class="m-wrap" name="customerName"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("goodsName")%></span><input class="m-wrap" name="goodsName"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPerson")%></span><input class="m-wrap" name="receivingPerson"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPersonPhone")%></span><input class="m-wrap" name="receivingPersonPhone"  type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("num")%></span><input class="m-wrap" name="num"  type="text"  						maxLength="10" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%></span><input class="m-wrap" name="plannedMileage"  type="text"  						maxLength="12" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("price")%></span><input class="m-wrap" name="price"  type="text"  						maxLength="19" />
							</div>


							<div class="input-prepend">
							  <span class="add-on"><%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%></span><input name="sendCarDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%>" />
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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/cardistributionplan/logicDelete?pcode=${pcode }');">
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

							<li class="active">汽运配送计划</li>

							<li class="active">
							<c:choose>
								<c:when test="${logisticsCompanyIdIsNull=='1' }">汽运配送物流公司分配</c:when>
								<c:when test="${logisticsCompanyIdIsNull=='0' }">汽配已分配查看</c:when>
								<c:otherwise>汽运销售计划</c:otherwise>
							</c:choose>
							</li>

						</ul><!-- .breadcrumb -->

			<%--		<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div> --%>	
						<!-- #nav-search -->
					</div>

					<div class="page-content">
						<!-- <div class="page-header">
							<h1>
								汽运配送计划
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

				   if(${logisticsCompanyIdIsNull=='1'}){

						
						jQuery(grid_selector).jqGrid({
							//direction: "rtl",
							
							url: getRootPath()+'/cardistributionplan/ajaxList?${queryString}',
							datatype: "json",
						    mtype: 'POST',  
							height: '100%',
							colNames:[
		'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCode")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%>'
		,'序号','接收订单时间'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("price")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("region")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("customerName")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("goodsName")%>'
		,'等级'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPerson")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPersonPhone")%>'
		,'省份'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("area")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingAddress")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("num")%>'
		,'元/吨.公里（合同）','单价（元/吨）','取整单价'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("totalFreight")%>'
		,'元/吨.公里（结算）'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%>'
		],
							colModel:[
					{name:'planCode',index:'planCode', width:130,editable: true,editoptions:{size:"20",maxLength:"13"}},
					{name:'logisticsCompanyId',index:'logisticsCompanyId', width:180,editable: true,editoptions:{size:"20",maxLength:"300"},formatter:showLogisticsCompany},
					{name:'plannedMileage',index:'plannedMileage', width:180,editable: true,editoptions:{size:"20",maxLength:"12"},formatter:showJiJia},
					{name:'sequenceNumber',index:'sequenceNumber', width:90,editable: true},
					{name:'acceptOrderTime',index:'acceptOrderTime', width:90,editable: true,formatter: formatDateShow},
					{name:'sendCarDate',index:'sendCarDate',width:90, editable:true, sorttype:"date",formatter: formatDateShow},
					{name:'price',index:'price', width:90,editable: true},
					{name:'region',index:'region', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
					{name:'customerName',index:'customerName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
					{name:'goodsName',index:'goodsName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
					{name:'productLevel',index:'productLevel', width:90,editable: true},
					{name:'receivingPerson',index:'receivingPerson', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
					{name:'receivingPersonPhone',index:'receivingPersonPhone', width:90,editable: true,editoptions:{size:"20",maxLength:"20"}},
					{name:'province',index:'province', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
					{name:'area',index:'area', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
					{name:'shippingAddress',index:'shippingAddress', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
					{name:'num',index:'num', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
					{name:'contractPrice',index:'contractPrice', width:90,editable: true},
					{name:'unitPrice',index:'unitPrice', width:90,editable: true},
					{name:'roundingPrice',index:'roundingPrice', width:90,editable: true},
					{name:'totalFreight',index:'totalFreight', width:90,editable: true},
					{name:'tonKm',index:'tonKm', width:90,editable: true},
					{name:'remark',index:'remark', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}}
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
					
							editurl: getRootPath()+'/cardistributionplan/update',//nothing is saved
							caption: "已分配计划列表展示",
							autowidth: true
					
						});
				
				   }

				   if(${logisticsCompanyIdIsNull=='0'}){
					
					   jQuery(grid_selector).jqGrid({
						//direction: "rtl",
						
						url: getRootPath()+'/cardistributionplan/ajaxList?${queryString}',
						datatype: "json",
					    mtype: 'POST',  
						height: '100%',
							colNames:[
		'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("planCode")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("logisticsCompany")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("plannedMileage")%>'
		,'序号','接收订单时间'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("sendCarDate")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("price")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("region")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("customerName")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("goodsName")%>'
		,'等级'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPerson")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("receivingPersonPhone")%>'
		,'省份'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("area")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("shippingAddress")%>'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("num")%>'
		,'元/吨.公里（合同）','单价（元/吨）','取整单价'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("totalFreight")%>'
		,'元/吨.公里（结算）'
		,'<%=ICarDistributionPlanConstants.TABLE_COLUMN_DISPLAY.get("remark")%>'
		],
							colModel:[
					{name:'planCode',index:'planCode', width:130,editable: true,editoptions:{size:"20",maxLength:"13"}},
					{name:'logisticsCompany',index:'logisticsCompany', width:180,editable: true,editoptions:{size:"20",maxLength:"300"},formatter:showLogisticsCompany},
					{name:'plannedMileage',index:'plannedMileage', width:180,editable: true,editoptions:{size:"20",maxLength:"12"},formatter:showJiJia},
					{name:'sequenceNumber',index:'sequenceNumber', width:90,editable: true},
					{name:'acceptOrderTime',index:'acceptOrderTime', width:90,editable: true,formatter: formatDateShow},
					{name:'sendCarDate',index:'sendCarDate',width:90, editable:true, sorttype:"date",formatter: formatDateShow},
					{name:'price',index:'price', width:90,editable: true},
					{name:'region',index:'region', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
					{name:'customerName',index:'customerName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
					{name:'goodsName',index:'goodsName', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
					{name:'productLevel',index:'productLevel', width:90,editable: true},
					{name:'receivingPerson',index:'receivingPerson', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
					{name:'receivingPersonPhone',index:'receivingPersonPhone', width:90,editable: true,editoptions:{size:"20",maxLength:"20"}},
					{name:'province',index:'province', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
					{name:'area',index:'area', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
					{name:'shippingAddress',index:'shippingAddress', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
					{name:'num',index:'num', width:90,editable: true,editoptions:{size:"20",maxLength:"10"}},
					{name:'contractPrice',index:'contractPrice', width:90,editable: true},
					{name:'unitPrice',index:'unitPrice', width:90,editable: true},
					{name:'roundingPrice',index:'roundingPrice', width:90,editable: true},
					{name:'totalFreight',index:'totalFreight', width:90,editable: true},
					{name:'tonKm',index:'tonKm', width:90,editable: true},
					{name:'remark',index:'remark', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}}
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
					
							editurl: getRootPath()+'/cardistributionplan/update',//nothing is saved
							caption: "已分配计划列表展示",
							autowidth: true
					
						});
					
				}
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
			   */
			   if(${logisticsCompanyIdIsNull=='1'}){
// 			   		//修改
// 				  jQuery(grid_selector) .navButtonAdd(pager_selector,
// 					{ caption:"", buttonicon:"icon-pencil blue",
// 				      onClickButton:updateShow, 
// 				      position:"last" }) ;
			   		
			   		
				 //多条修改
					  jQuery(grid_selector) .navButtonAdd(pager_selector,
			 			{ caption:"",title:"多条分配",buttonicon:"icon-pencil blue",
					      onClickButton:updateList, 
					      position:"last" }) ;
				 
			   }
			   if(${logisticsCompanyIdIsNull=='0'}){
			   		//带条件修改
				  jQuery(grid_selector) .navButtonAdd(pager_selector,
					{ caption:"", buttonicon:"icon-pencil blue",
				      onClickButton:updateShowCondit, 
				      position:"last" }) ;
			   }
				
				
				/*//删除
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
                        url: "<%=request.getContextPath()%>/cardistributionplan/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=planCode]").val(data.bean.planCode);
                      $("#portlet-config-edit").find("input[id=distributionPlanId]").val(data.bean.distributionPlanId);
                      $("#portlet-config-edit").find("input[id=makeDate]").val(data.bean.makeDate);
                      $("#portlet-config-edit").find("input[id=logisticsCompany]").val(data.bean.logisticsCompany);
                      $("#portlet-config-edit").find("input[id=region]").val(data.bean.region);
                      $("#portlet-config-edit").find("input[id=customerName]").val(data.bean.customerName);
                      $("#portlet-config-edit").find("input[id=goodsName]").val(data.bean.goodsName);
                      $("#portlet-config-edit").find("input[id=receivingPerson]").val(data.bean.receivingPerson);
                      $("#portlet-config-edit").find("input[id=receivingPersonPhone]").val(data.bean.receivingPersonPhone);
                      $("#portlet-config-edit").find("input[id=area]").val(data.bean.area);
                      $("#portlet-config-edit").find("input[id=shippingAddress]").val(data.bean.shippingAddress);
                      $("#portlet-config-edit").find("input[id=num]").val(data.bean.num);
                      $("#portlet-config-edit").find("input[id=plannedMileage]").val(data.bean.plannedMileage);
                      $("#portlet-config-edit").find("input[id=factMileage]").val(data.bean.factMileage);
                      $("#portlet-config-edit").find("input[id=valuationMileage]").val(data.bean.valuationMileage);
                      $("#portlet-config-edit").find("input[id=tonKm]").val(data.bean.tonKm);
                      $("#portlet-config-edit").find("input[id=settlementFreight]").val(data.bean.settlementFreight);
                      $("#portlet-config-edit").find("input[id=totalFreight]").val(data.bean.totalFreight);
                      $("#portlet-config-edit").find("input[name=price]").val(data.bean.price);
                      $("#portlet-config-edit").find("textarea[id=remark]").val(data.bean.remark);
                      $("#portlet-config-edit").find("input[id=sendCarDate]").val(data.bean.sendCarDate);
                      $("#portlet-config-edit").find("select[name=status]").val(data.bean.status);
                      $("#portlet-config-edit").find("input[id=remainderNum]").val(data.bean.remainderNum);
                      $("#portlet-config-edit").find("input[id=remainderCarNum]").val(data.bean.remainderCarNum);
                      $("#portlet-config-edit").find("select[name=shipperCode]").val(data.bean.shipperCode);
                      $("#portlet-config-edit").find("input[id=shippingVehiclesNumber]").val(data.bean.shippingVehiclesNumber);
                      $("#portlet-config-edit").find("input[id=shippingNumber]").val(data.bean.shippingNumber);
                      $("#portlet-config-edit").find("input[id=planCarNum]").val(data.bean.planCarNum);
                      $("#portlet-config-edit").find("input[id=flowToId]").val(data.bean.flowToId);
                      $("#portlet-config-edit").find("input[id=flowToIdName]").val(data.bean.flowToIdName);
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
			     
			     //  带条件修改弹出
			     function updateShowCondit(){
			    	 
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
                        url: "<%=request.getContextPath()%>/cardistributionplan/get/" + updateDate+"/"+new Date()+"?isExist=1",
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=planCode]").val(data.bean.planCode);
                      $("#portlet-config-edit").find("input[id=distributionPlanId]").val(data.bean.distributionPlanId);
                      $("#portlet-config-edit").find("input[id=makeDate]").val(data.bean.makeDate);
                      $("#portlet-config-edit").find("input[id=logisticsCompany]").val(data.bean.logisticsCompany);
                      $("#portlet-config-edit").find("input[id=region]").val(data.bean.region);
                      $("#portlet-config-edit").find("input[id=customerName]").val(data.bean.customerName);
                      $("#portlet-config-edit").find("input[id=goodsName]").val(data.bean.goodsName);
                      $("#portlet-config-edit").find("input[id=receivingPerson]").val(data.bean.receivingPerson);
                      $("#portlet-config-edit").find("input[id=receivingPersonPhone]").val(data.bean.receivingPersonPhone);
                      $("#portlet-config-edit").find("input[id=area]").val(data.bean.area);
                      $("#portlet-config-edit").find("input[id=shippingAddress]").val(data.bean.shippingAddress);
                      $("#portlet-config-edit").find("input[id=num]").val(data.bean.num);
                      $("#portlet-config-edit").find("input[id=plannedMileage]").val(data.bean.plannedMileage);
                      $("#portlet-config-edit").find("input[id=factMileage]").val(data.bean.factMileage);
                      $("#portlet-config-edit").find("input[id=valuationMileage]").val(data.bean.valuationMileage);
                      $("#portlet-config-edit").find("input[id=tonKm]").val(data.bean.tonKm);
                      $("#portlet-config-edit").find("input[id=settlementFreight]").val(data.bean.settlementFreight);
                      $("#portlet-config-edit").find("input[id=totalFreight]").val(data.bean.totalFreight);
                      $("#portlet-config-edit").find("input[id=price]").val(data.bean.price);
                      $("#portlet-config-edit").find("textarea[id=remark]").val(data.bean.remark);
                      $("#portlet-config-edit").find("input[id=sendCarDate]").val(data.bean.sendCarDate);
                      $("#portlet-config-edit").find("select[name=status]").val(data.bean.status);
                      $("#portlet-config-edit").find("input[id=remainderNum]").val(data.bean.remainderNum);
                      $("#portlet-config-edit").find("input[id=remainderCarNum]").val(data.bean.remainderCarNum);
                      $("#portlet-config-edit").find("select[name=shipperCode]").val(data.bean.shipperCode);
                      $("#portlet-config-edit").find("input[id=shippingVehiclesNumber]").val(data.bean.shippingVehiclesNumber);
                      $("#portlet-config-edit").find("input[id=shippingNumber]").val(data.bean.shippingNumber);
                      $("#portlet-config-edit").find("input[id=planCarNum]").val(data.bean.planCarNum);
                      $("#portlet-config-edit").find("input[id=flowToId]").val(data.bean.flowToId);
                      $("#portlet-config-edit").find("input[id=flowToIdName]").val(data.bean.flowToIdName);

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
				
				function  shipperCodeLu(cellvalue, options, cell){
					return   shipperCodeLuShow[cellvalue];				
				}
				
				function  showLogisticsCompany(cellvalue, options, cell){
					if(cellvalue){
						return   cellvalue;				
					}else{
						return  listLogicShow;
					}
				}
				
				function  showJiJia(cellvalue, options, cell){
					if(!cellvalue){
						cellvalue=0;
					}
					   if(${logisticsCompanyIdIsNull=='1'}){
							return  jiJiaStr+cellvalue+jiJiaEnd;
					   }else{
							return  cellvalue;
					   }
				}
				
				//修改多条 
				function updateList(){
					debugger;
					  var flag=false;
				      var  ids="";
				      var  logisticsIds="";
				      var  logisticsIdNames="";
				      var  plannedMileages="";
				      var len = $(".cbox").length-1;
// 			    	  if(len==0){
			    		
// 			    		alert("请至少选择一条数据！");
// 			    		return false;
// 			    	  }
				      var count=1;
				      $(".cbox").each(function(){
				      	
						var dataId =$(this).parents("tr").attr("id");
						if(typeof(dataId) != 'undefined'){
							
						    var logis =$("#"+dataId).find("select  option:selected").val();
	// 					    if(!logis){
	// 					    	flag=true;
	// 					    	alert("请选全物流公司");
	// 					    	return  false;
	// 					    }
						    var logiNames =$("#"+dataId).find("select  option:selected").text();
						    var plannedMileage =$("#"+dataId).find("input[name=plan]").val();
							//计划里程
							if($.trim(plannedMileage).length==0){
						    	flag=true;
						    	alert("请填全计划里程!");
								return false;
							}else{
								var regu = /^[0-9]\d*[\.]?[0-9]{0,2}$/;
							    if (!regu.test(plannedMileage)) {
							    	flag=true;
							    	alert("请重新输入计划里程(大于0,最多两位小数)!");
									return false;
							    }
							}
					      	ids+=dataId;
					      	logisticsIds +=logis;
					      	logisticsIdNames +=logiNames;
					      	plannedMileages +=plannedMileage;
					      	if(len!=count){
					      		ids+=",";
					      		logisticsIds+=",";
					      		logisticsIdNames+=",";
					      		plannedMileages +=",";
					      	}
					      	count++;
						}

				      });
				     
				      if(flag){
				    	  return  false;
				      }
				     
			    	if(confirm("确认修改吗")){
				   		 $.ajax({
							    type:'post',//可选get
							    url:"${pageContext.request.contextPath}/cardistributionplan/doUpdateLogisticsIds",
							    data:{'ids':ids,'logisticsIds':logisticsIds,'logisticsIdNames':logisticsIdNames,'plannedMileages':plannedMileages},
							    dataType:'json',
							    async:false , //false为同步
							    success:function(data,status){
								  if(data.status=="0"){
					    	   	     //将按钮设置为可用
									  alert(data.message);
							   	   }else{
							   	     	alert(data.message);
					                    window.location.href="${pageContext.request.contextPath}/cardistributionplan?logisticsCompanyIdIsNull=${logisticsCompanyIdIsNull}&pcode=${pcode}";
							   	   }
							   },
							   error:function(xhr,s1,s2){
							    	var message = xhr.responseText;
									eval("var json = "+message);
					    	   	     //将按钮设置为可用
									if($.trim(json.error).length==0){
										alert('数据异常,请刷新页面重试');
									}else{
										alert(json.error);
									}
							   }
						});
			    		
			    	}
				}
			});
		</script>
	</body>
</html>
