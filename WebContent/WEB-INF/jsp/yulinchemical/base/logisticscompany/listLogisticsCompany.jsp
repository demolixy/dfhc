<%@page import="com.dfhc.base.logisticscompany.ILogisticsCompanyConstants" %>
<%@page import="org.quickbundle.orgauth.cache.RmPartyTypeCache"%>
<%@page import="org.quickbundle.orgauth.rmuser.util.IRmUserConstants"%>
<%@page import="org.quickbundle.orgauth.IOrgauthConstants"%>
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
	
	var name= $("#add_form").find("input[name=companyName]").val();
	if(name.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (regu.test(name)) {
	    	alert("请输入正确的登陆名!");
			return false;
	    }
	    }
	
	var name= $("#add_form").find("input[name=name]").val();
	if(name.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (regu.test(name)) {
	    	alert("请输入正确的物流公司名称!");
			return false;
	    }
	    }

	var name= $("#add_form").find("input[name=indexCode]").val();
	if(name.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (regu.test(name)) {
	    	alert("请输入正确的物流公司拼音码!");
			return false;
	    }
	    }
	var name= $("#add_form").find("input[name=legalPerson]").val();
	if(name.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (regu.test(name)) {
	    	alert("请输入正确的法人!");
			return false;
	    }
	    }
	var name= $("#add_form").find("input[name=contacts]").val();
	if(name.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (regu.test(name)) {
	    	alert("请输入正确的联系人!");
			return false;
	    }
	    }

	var flag=true;//标志错误信息
	$(".notNullX").each(function(){		
		var  val=$(this).val();
		val=$.trim(val);
		if(val==''){
			alert("标注必填的字段不可为空");
			flag= false;
			return false;
		}else{			
			var attName=$(this).attr("name");
			if(attName=='email'){				
				flag=isEmail(val);				
				if(!flag)
					return flag;
			}
		}	
	});
	
	var psw=$("#insertForm").find("input[name='password']").val();
	psw=$.trim(psw);
	$("#insertForm").find("input[name='password']").val(psw);
	
	pwdMd5(psw,'insertForm');
	
	if(!flag){
		return false;
	}	
	
	submitInsertAjax('<%=request.getContextPath()%>/logisticscompany/insert',$(insertForm).serialize());

/* 	//刷新表单
	 
   	window.location.reload(true); */
   
}

function pwdMd5(obj,formId){

	if($.trim(obj).length>0){
		
		var psw=$.md5(obj);

		$("#"+formId).find("input[name=hidPas]").val(psw);
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
	}	
	);		
	if(!flag){
		return flag;
	}	
	var name= $("#updateForm").find("input[name=name]").val();
	if(name.length>0){
		var regu =/^[0-9]\d*[\.]?[0-9]{0,4}$/ ;
	    if (regu.test(name)) {
	    	alert("请输入正确的用户简称!");
			return false;
	    }
	    }
	var name= $("#updateForm").find("input[name=indexCode]").val();
	if(name.length>0){
		var regu = /^[A-Za-z]*$/;
	    if (!regu.test(name)) {
	    	alert("请输入正确的用户拼音码!");
			return false;
	    }
	    }
	var name= $("#updateForm").find("input[name=legalPerson]").val();
	if(name.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (regu.test(name)) {
	    	alert("请输入正确的法人!");
			return false;
	    }
	    }
	var name= $("#updateForm").find("input[name=contacts]").val();
	if(name.length>0){
		var regu = /^[0-9]\d*[\.]?[0-9]{0,4}$/;
	    if (regu.test(name)) {
	    	alert("请输入正确的联系人!");
			return false;
	    }
	    }
	submitInsertAjax('<%=request.getContextPath()%>/logisticscompany/update',$(updateForm).serialize());
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
        <input type="hidden" name="view_id" value="<%=IOrgauthConstants.PartyView.DEFAULT.id() %>" id="view_id"></input>
     <input type="hidden" name="isInherit" value="0"></input>
     <input type="hidden" name="party_type_id"  value="<%=IOrgauthConstants.Config.isUserRelationParty.value() && RmPartyTypeCache.getPartyType(IRmUserConstants.TABLE_NAME) != null ? RmPartyTypeCache.getPartyType(IRmUserConstants.TABLE_NAME).getId() : ""%>"/> 
   <div class="control-group">
			 <div class="controls">
			 
							<div class="input-prepend" >
							  <span class="add-on">登陆名</span>	
							    <input type="text" class="m-wrap "  name="companyName"   />	
							    <i class="notNull">*</i>	
                        	</div>
                        	
                           <div class="input-prepend">
							  <span class="add-on">邮箱</span>
							  <input class="m-wrap " name="email"  type="text"  maxLength="50" />
							  <i class="notNull">*</i>
							</div>
							
	                         <div class="input-prepend">
							  <span class="add-on">账户密码</span>
							  <input class="m-wrap " name="password"  type="password"  maxLength="50" />
							  <i class="notNull">*</i>
							</div>
							
							<input name="hidPas" type="hidden"/>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("name")%></span>
							  <input class="m-wrap notNullX" name="name"  type="text"  						maxLength="300" style="    width: 190px;"/>
							<i class="notNull">*</i>	
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("indexCode")%></span>
							  <input class="m-wrap notNullX" name="indexCode"  type="text"  						maxLength="200" />
							<i class="notNull">*</i>	
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("regAddr")%></span>
							  <input class="m-wrap" name="regAddr"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("officeAddr")%></span>
							  <input class="m-wrap" name="officeAddr"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("postAddr")%></span>
							  <input class="m-wrap" name="postAddr"  type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("postCode")%></span>
							  <input class="m-wrap" name="postCode"  type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("fax")%></span>
							  <input class="m-wrap" name="fax"  type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("tel")%></span>
							  <input class="m-wrap " name="tel"  type="text"  						maxLength="50" />
							
							</div>

							<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">省</span>
								<select name="provinceId" style="min-width: 200px;" >
									<option></option>
								</select>
									<input class="m-wrap " name="provinceIdName" value="" type="hidden"/>
							</div>
							
							<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">市</span>
								<select name="cityId"  style="min-width: 200px;" >
									<option></option>
								</select>	
									<input class="m-wrap " name="cityName" value="" type="hidden"/>
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("bank")%></span>
							  <input class="m-wrap " name="bank"  type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on" ><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("accountName")%></span>
							  <input class="m-wrap " name="accountName"  type="text"  						maxLength="50" />
							</div>
	
							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("taxNo")%></span>
							  <input class="m-wrap " name="taxNo"  type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("legalPerson")%></span>
							  <input class="m-wrap " name="legalPerson"  type="text"  						maxLength="50" />
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("contacts")%></span>
							  <input class="m-wrap" name="contacts"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("contactPhone")%></span><input class="m-wrap" name="contactPhone"  type="text"  						maxLength="20" />
							</div>
								
							<div class="input-prepend" style="height:45px">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("remark")%></span>           	                       
							  <textarea cols="3" rows="5" class="medium m-wrap" name="remark" id="remark" inputName="<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("remark")%>" 	 maxLength="1000"  ></textarea>
							</div>
							
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
							<div class="input-prepend" >
							  <span class="add-on">登陆名</span>	
							    <input type="text" class="m-wrap "  readOnly name="companyName"   />	
							    <i class="notNull">*</i>	
                        	</div>
                        	
							  <input class="m-wrap " name="email"  type="hidden"  maxLength="50" />
							
	                  	 	 <div class="input-prepend">
							  <span class="add-on">账户密码</span>
							  <input class="m-wrap " name="password"  type="password"  maxLength="50" />
							  <i class="notNull">*</i>
							</div>
							     
							<input name="hidPas" type="hidden"/>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("name")%></span>
							  <input class="m-wrap notNullY" name="name" id="name" type="text"  	style="    width: 190px;"					maxLength="300" />
							  <i class="notNull">*</i>
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("indexCode")%></span>
							  <input class="m-wrap notNullY" name="indexCode" id="indexCode" type="text"  						maxLength="200" />
							  <i class="notNull">*</i>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("regAddr")%></span>
							  <input class="m-wrap" name="regAddr" id="regAddr" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("officeAddr")%></span>
							  <input class="m-wrap" name="officeAddr" id="officeAddr" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("postAddr")%></span>
							  <input class="m-wrap" name="postAddr" id="postAddr" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("postCode")%></span>
							  <input class="m-wrap" name="postCode" id="postCode" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("fax")%></span><input class="m-wrap" name="fax" id="fax" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("tel")%></span>
							  <input class="m-wrap notNullY" name="tel" id="tel" type="text"  						maxLength="50" />
							<i class="notNull">*</i>
							</div>

							<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">省</span>
								<select name="provinceId" style="min-width: 205px;" >
									<option></option>
								</select>
									<input class="m-wrap " name="provinceIdName" value="" type="hidden"/>
									
							</div>
							
							<div class="input-prepend" style="margin-top:-11px;">
							<span class="add-on">市</span>
								<select name="cityId"  style="min-width: 210px;" >
									<option></option>
								</select>	
									<input class="m-wrap " name="cityName" value="" type="hidden"/>
										
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("bank")%></span>
							  <input class="m-wrap notNullY" name="bank" id="bank" type="text"  						maxLength="50" />
							<i class="notNull">*</i>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("accountName")%></span>
							  <input class="m-wrap notNullY" name="accountName" id="accountName" type="text"  						maxLength="50" />
							<i class="notNull">*</i>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("taxNo")%></span>
							  <input class="m-wrap notNullY" name="taxNo" id="taxNo" type="text"  						maxLength="50" />
							<i class="notNull">*</i>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("legalPerson")%></span>
							  <input class="m-wrap notNullY" name="legalPerson" id="legalPerson" type="text"  						maxLength="50" />
							<i class="notNull">*</i>
							</div>

						<%--<div class="input-prepend" style="    height: 45px">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("status")%></span><%=RmJspHelper.getSelectField("status", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_CUST_STATUS), "", "inputName='" + ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("status") + "'  ", true) %>							</div>
 --%>	
							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("contacts")%></span>
							  <input class="m-wrap" name="contacts" id="contacts" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("contactPhone")%></span>
							  <input class="m-wrap" name="contactPhone" id="contactPhone" type="text"  						maxLength="20" />
							</div>
								
							<div class="input-prepend"style="height:45px">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("remark")%></span>           	                       
							  <textarea cols="3" rows="5" class="medium m-wrap" name="remark" id="remark" inputName="<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("remark")%>" 	 maxLength="1000"  ></textarea>
							</div>

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
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("name")%></span>
							  <input class="m-wrap" name="name"  type="text"  						maxLength="300" style="width:190px" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("indexCode")%></span><input class="m-wrap" name="indexCode"  type="text"  						maxLength="200" />
							</div>

					<%--	<div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("tel")%></span><input class="m-wrap" name="tel"  type="text"  						maxLength="50" />
							</div>    --%>	

					<%-- <div class="input-prepend">
							  <span class="add-on"><%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("contacts")%></span><input class="m-wrap" name="contacts"  type="text"  						maxLength="200" />
							</div>  --%>		

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
       <a href="javascript:void(0)" id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn-danger" onclick="javascript:_delete('<%=request.getContextPath()%>/logisticscompany/logicDelete?pcode=${pcode }');">
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
							<li class="active">物流公司维护</li>
						</ul><!-- .breadcrumb -->

				<%--   	<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search --> --%>
					</div>

					<div class="page-content">
						<!-- <div class="page-header">
							<h1>
								物流公司
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
					
					url: getRootPath()+'/logisticscompany/ajaxList',
					datatype: "json",
				    mtype: 'POST',  
					height: "100%",
					colNames:[
'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("name")%>'

,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("indexCode")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("regAddr")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("officeAddr")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("postAddr")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("postCode")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("fax")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("tel")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("provinceId")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("provinceIdName")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("cityId")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("cityName")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("bank")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("accountName")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("taxNo")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("legalPerson")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("status")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("contacts")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("contactPhone")%>'
,'<%=ILogisticsCompanyConstants.TABLE_COLUMN_DISPLAY.get("remark")%>'
],
					colModel:[
			{name:'name',index:'name', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}},
			
			{name:'indexCode',index:'indexCode', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'regAddr',index:'regAddr', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'officeAddr',index:'officeAddr', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'postAddr',index:'postAddr', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'postCode',index:'postCode', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'fax',index:'fax', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'tel',index:'tel', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'provinceId',index:'provinceId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"11"}},
			{name:'provinceIdName',index:'provinceIdName', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'cityId',index:'cityId', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"11"}},
			{name:'cityName',index:'cityName', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'bank',index:'bank', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'accountName',index:'accountName', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'taxNo',index:'taxNo', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'legalPerson',index:'legalPerson', width:90,editable: true,editoptions:{size:"20",maxLength:"50"}},
			{name:'status',index:'status', width:90,editable: true,hidedlg:true,hidden:true,editoptions:{size:"20",maxLength:"1"}},
			{name:'contacts',index:'contacts', width:90,editable: true,editoptions:{size:"20",maxLength:"200"}},
			{name:'contactPhone',index:'contactPhone', width:90,editable: true,editoptions:{size:"20",maxLength:"20"}},
			{name:'remark',index:'remark', width:90,editable: true,editoptions:{size:"20",maxLength:"300"}}
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
			
					editurl: getRootPath()+'/logisticscompany/update',//nothing is saved
					caption: "物流公司列表展示",
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
			    	 
					 $("#add_form").find("input[name=view_id]").val("<%=IOrgauthConstants.PartyView.DEFAULT.id() %>");
					 $("#add_form").find("input[name=isInherit]").val("0");
					 $("#add_form").find("input[name=party_type_id]").val("<%=IOrgauthConstants.Config.isUserRelationParty.value() && RmPartyTypeCache.getPartyType(IRmUserConstants.TABLE_NAME) != null ? RmPartyTypeCache.getPartyType(IRmUserConstants.TABLE_NAME).getId() : ""%>");
					 
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
                        url: "<%=request.getContextPath()%>/logisticscompany/get/" + updateDate+"/"+new Date(),
                        dataType: "json",
                        async:false , //false为同步
                        success: function(data,status) {
                	   	   if(data.status=="0"){
                	   	       alert(data.message);
                	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[name=companyName]").val(data.bean.companyName);
                      $("#portlet-config-edit").find("input[name=email]").val(data.bean.email);
                      $("#portlet-config-edit").find("input[name=password]").val(data.bean.password);
                      $("#portlet-config-edit").find("input[id=name]").val(data.bean.name);
                      $("#portlet-config-edit").find("textarea[id=remark]").val(data.bean.remark);
                      $("#portlet-config-edit").find("input[id=indexCode]").val(data.bean.indexCode);
                      $("#portlet-config-edit").find("input[id=regAddr]").val(data.bean.regAddr);
                      $("#portlet-config-edit").find("input[id=officeAddr]").val(data.bean.officeAddr);
                      $("#portlet-config-edit").find("input[id=postAddr]").val(data.bean.postAddr);
                      $("#portlet-config-edit").find("input[id=postCode]").val(data.bean.postCode);
                      $("#portlet-config-edit").find("input[id=fax]").val(data.bean.fax);
                      $("#portlet-config-edit").find("input[id=tel]").val(data.bean.tel);
                      $("#portlet-config-edit").find("input[id=provinceId]").val(data.bean.provinceId);
                      $("#portlet-config-edit").find("input[id=provinceIdName]").val(data.bean.provinceIdName);
                      $("#portlet-config-edit").find("input[id=cityId]").val(data.bean.cityId);
                      $("#portlet-config-edit").find("input[id=cityName]").val(data.bean.cityName);
                      $("#portlet-config-edit").find("input[id=bank]").val(data.bean.bank);
                      $("#portlet-config-edit").find("input[id=accountName]").val(data.bean.accountName);
                      $("#portlet-config-edit").find("input[id=taxNo]").val(data.bean.taxNo);
                      $("#portlet-config-edit").find("input[id=legalPerson]").val(data.bean.legalPerson);
                      $("#portlet-config-edit").find("input[id=status]").val(data.bean.status);
                      $("#portlet-config-edit").find("input[id=contacts]").val(data.bean.contacts);
                      $("#portlet-config-edit").find("input[id=contactPhone]").val(data.bean.contactPhone);
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
					
					$(this).parents("form").find("input[name='provinceIdName']").val($(this).find("option:selected").text());
					$(this).parents("form").find("input[name='cityName']").val('');//清空县级
				
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
								var html = "<option>请选择市</option>";
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
				
					$(this).parents("form").find("input[name='cityName']").val($(this).find("option:selected").text());//清空县级
				})	
			
			});
		</script>
	</body>
</html>
