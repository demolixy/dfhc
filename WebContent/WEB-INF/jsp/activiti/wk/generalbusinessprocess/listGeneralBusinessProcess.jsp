<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="com.dfhc.wk.generalbusinessprocess.IGeneralBusinessProcessConstants" %>
<%@page import="org.quickbundle.base.web.page.RmPageVo"%>
<%@page import="org.quickbundle.project.RmGlobalReference"%>
<%@page import="org.quickbundle.tools.helper.RmJspHelper"%>
<%@page import="com.dfhc.ISystemConstant" %>
<%@page import="org.quickbundle.tools.helper.RmVoHelper"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->

<!-- BEGIN HEAD -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE11" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/jsp/cipher/global.jsp" %>

<title><%=IGeneralBusinessProcessConstants.TABLE_NAME_DISPLAY%></title>
</head>
<body class="page-header-fixed" >
  <div class="page-content"  style="margin: 0px 0px 0px 0px;padding: 0px 0px;">
  <!-- begain 修改 -->
   <div id="portlet-config-edit" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>修改	<input type="button" class="btn blue" value="提交" style="float: right;" 
										 onclick="javascript:submitUpdateAjax('<%=request.getContextPath()%>/generalbusinessprocess/update',$(updateForm).serialize(),'<%=request.getContextPath()%>/generalbusinessprocess');"/></h3>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath()%>/generalbusinessprocess/update" id="updateForm" name="updateForm" method="post">
						<div class="control-group">
							<div class="controls">
							<input id="id" name="id" type="hidden" />

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%></span><input type="text" class="text_field_reference text_field_readonly"  hiddenInputId="procDefId" name="procDefIdName" inputName="<%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%>" value="" /><input type="hidden" name="procDefId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getReference(new Array(updateForm.procDefId, updateForm.procDefIdName), '<%=request.getContextPath()%>/', '<%=request.getContextPath()%>/reprocdef/reference?referenceInputType=radio');"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procInstId")%></span><input class="m-wrap" name="procInstId" id="procInstId" type="text"  						maxLength="64" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procName")%></span><input class="m-wrap" name="procName" id="procName" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procKey")%></span><input class="m-wrap" name="procKey" id="procKey" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiator")%></span><input class="m-wrap" name="initiator" id="initiator" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorId")%></span><input class="m-wrap" name="initiatorId" id="initiatorId" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("sendOrganizationDepartment")%></span><input class="m-wrap" name="sendOrganizationDepartment" id="sendOrganizationDepartment" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorOrganizationId")%></span><input class="m-wrap" name="initiatorOrganizationId" id="initiatorOrganizationId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorTime")%></span><input name="initiatorTime" id="initiatorTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessType")%></span><input class="m-wrap" name="businessType" id="businessType" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessGroup")%></span><input class="m-wrap" name="businessGroup" id="businessGroup" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("parentId")%></span><input class="m-wrap" name="parentId" id="parentId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute70")%></span><input class="m-wrap" name="businessAttribute70" id="businessAttribute70" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute69")%></span><input class="m-wrap" name="businessAttribute69" id="businessAttribute69" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute68")%></span><input class="m-wrap" name="businessAttribute68" id="businessAttribute68" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute67")%></span><input class="m-wrap" name="businessAttribute67" id="businessAttribute67" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute66")%></span><input class="m-wrap" name="businessAttribute66" id="businessAttribute66" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute65")%></span><input class="m-wrap" name="businessAttribute65" id="businessAttribute65" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute64")%></span><input class="m-wrap" name="businessAttribute64" id="businessAttribute64" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute63")%></span><input class="m-wrap" name="businessAttribute63" id="businessAttribute63" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute62")%></span><input class="m-wrap" name="businessAttribute62" id="businessAttribute62" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute61")%></span><input class="m-wrap" name="businessAttribute61" id="businessAttribute61" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute60")%></span><input class="m-wrap" name="businessAttribute60" id="businessAttribute60" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute59")%></span><input class="m-wrap" name="businessAttribute59" id="businessAttribute59" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute58")%></span><input class="m-wrap" name="businessAttribute58" id="businessAttribute58" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute57")%></span><input class="m-wrap" name="businessAttribute57" id="businessAttribute57" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute56")%></span><input class="m-wrap" name="businessAttribute56" id="businessAttribute56" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute55")%></span><input class="m-wrap" name="businessAttribute55" id="businessAttribute55" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute54")%></span><input class="m-wrap" name="businessAttribute54" id="businessAttribute54" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute53")%></span><input class="m-wrap" name="businessAttribute53" id="businessAttribute53" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute52")%></span><input class="m-wrap" name="businessAttribute52" id="businessAttribute52" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute51")%></span><input class="m-wrap" name="businessAttribute51" id="businessAttribute51" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute50")%></span><input class="m-wrap" name="businessAttribute50" id="businessAttribute50" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute49")%></span><input class="m-wrap" name="businessAttribute49" id="businessAttribute49" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute48")%></span><input class="m-wrap" name="businessAttribute48" id="businessAttribute48" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute47")%></span><input class="m-wrap" name="businessAttribute47" id="businessAttribute47" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute46")%></span><input class="m-wrap" name="businessAttribute46" id="businessAttribute46" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute45")%></span><input class="m-wrap" name="businessAttribute45" id="businessAttribute45" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute44")%></span><input class="m-wrap" name="businessAttribute44" id="businessAttribute44" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute43")%></span><input class="m-wrap" name="businessAttribute43" id="businessAttribute43" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute42")%></span><input class="m-wrap" name="businessAttribute42" id="businessAttribute42" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute41")%></span><input class="m-wrap" name="businessAttribute41" id="businessAttribute41" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute40")%></span><input class="m-wrap" name="businessAttribute40" id="businessAttribute40" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute39")%></span><input class="m-wrap" name="businessAttribute39" id="businessAttribute39" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute38")%></span><input class="m-wrap" name="businessAttribute38" id="businessAttribute38" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute37")%></span><input class="m-wrap" name="businessAttribute37" id="businessAttribute37" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute36")%></span><input class="m-wrap" name="businessAttribute36" id="businessAttribute36" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute35")%></span><input class="m-wrap" name="businessAttribute35" id="businessAttribute35" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute34")%></span><input class="m-wrap" name="businessAttribute34" id="businessAttribute34" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute33")%></span><input class="m-wrap" name="businessAttribute33" id="businessAttribute33" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute32")%></span><input class="m-wrap" name="businessAttribute32" id="businessAttribute32" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute31")%></span><input class="m-wrap" name="businessAttribute31" id="businessAttribute31" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute30")%></span><input class="m-wrap" name="businessAttribute30" id="businessAttribute30" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute29")%></span><input class="m-wrap" name="businessAttribute29" id="businessAttribute29" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute28")%></span><input class="m-wrap" name="businessAttribute28" id="businessAttribute28" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute27")%></span><input class="m-wrap" name="businessAttribute27" id="businessAttribute27" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute26")%></span><input class="m-wrap" name="businessAttribute26" id="businessAttribute26" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute25")%></span><input class="m-wrap" name="businessAttribute25" id="businessAttribute25" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute24")%></span><input class="m-wrap" name="businessAttribute24" id="businessAttribute24" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute23")%></span><input class="m-wrap" name="businessAttribute23" id="businessAttribute23" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute22")%></span><input class="m-wrap" name="businessAttribute22" id="businessAttribute22" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute21")%></span><input class="m-wrap" name="businessAttribute21" id="businessAttribute21" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute20")%></span><input class="m-wrap" name="businessAttribute20" id="businessAttribute20" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute19")%></span><input class="m-wrap" name="businessAttribute19" id="businessAttribute19" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute18")%></span><input class="m-wrap" name="businessAttribute18" id="businessAttribute18" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute17")%></span><input class="m-wrap" name="businessAttribute17" id="businessAttribute17" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute16")%></span><input class="m-wrap" name="businessAttribute16" id="businessAttribute16" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute15")%></span><input class="m-wrap" name="businessAttribute15" id="businessAttribute15" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute14")%></span><input class="m-wrap" name="businessAttribute14" id="businessAttribute14" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute13")%></span><input class="m-wrap" name="businessAttribute13" id="businessAttribute13" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute12")%></span><input class="m-wrap" name="businessAttribute12" id="businessAttribute12" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute11")%></span><input class="m-wrap" name="businessAttribute11" id="businessAttribute11" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute10")%></span><input class="m-wrap" name="businessAttribute10" id="businessAttribute10" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute9")%></span><input class="m-wrap" name="businessAttribute9" id="businessAttribute9" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute8")%></span><input class="m-wrap" name="businessAttribute8" id="businessAttribute8" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute7")%></span><input class="m-wrap" name="businessAttribute7" id="businessAttribute7" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute6")%></span><input class="m-wrap" name="businessAttribute6" id="businessAttribute6" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute5")%></span><input class="m-wrap" name="businessAttribute5" id="businessAttribute5" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute4")%></span><input class="m-wrap" name="businessAttribute4" id="businessAttribute4" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute3")%></span><input class="m-wrap" name="businessAttribute3" id="businessAttribute3" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute1")%></span><input class="m-wrap" name="businessAttribute1" id="businessAttribute1" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute2")%></span><input class="m-wrap" name="businessAttribute2" id="businessAttribute2" type="text"  						maxLength="255" />
							</div>

							</div>
						</div>
					</form>
				</div>
			</div>
	 <!-- end 修改 -->
	 <!-- begain 新增 -->
	 <div id="portlet-config-add" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close closeMargain" type="button"></button>
					<h3>新增	<input type="button" class="btn blue" value="提交" style="float: right;"
								onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/generalbusinessprocess/insert',$(insertForm).serialize(),'<%=request.getContextPath()%>/generalbusinessprocess');"/></h3>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath()%>/generalbusinessprocess/insert" id="insertForm" name="insertForm" method="post">
						<div class="control-group">
							<div class="controls">
							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%></span><input type="text" class="text_field_reference text_field_readonly"  hiddenInputId="procDefId" name="procDefIdName" inputName="<%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%>" value="" /><input type="hidden" name="procDefId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getNewReference('<%=request.getContextPath()%>/reprocdef/reference?referenceInputType=radio','insertForm.procDefId,insertForm.procDefIdName');"/>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procInstId")%></span><input class="m-wrap" name="procInstId"  type="text"  						maxLength="64" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procName")%></span><input class="m-wrap" name="procName"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procKey")%></span><input class="m-wrap" name="procKey"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiator")%></span><input class="m-wrap" name="initiator"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorId")%></span><input class="m-wrap" name="initiatorId"  type="text"  />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("sendOrganizationDepartment")%></span><input class="m-wrap" name="sendOrganizationDepartment"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorOrganizationId")%></span><input class="m-wrap" name="initiatorOrganizationId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorTime")%></span><input name="initiatorTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessType")%></span><input class="m-wrap" name="businessType"  type="text"  						maxLength="20" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessGroup")%></span><input class="m-wrap" name="businessGroup"  type="text"  						maxLength="20" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("parentId")%></span><input class="m-wrap" name="parentId"  type="text"  						maxLength="19" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute70")%></span><input class="m-wrap" name="businessAttribute70"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute69")%></span><input class="m-wrap" name="businessAttribute69"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute68")%></span><input class="m-wrap" name="businessAttribute68"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute67")%></span><input class="m-wrap" name="businessAttribute67"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute66")%></span><input class="m-wrap" name="businessAttribute66"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute65")%></span><input class="m-wrap" name="businessAttribute65"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute64")%></span><input class="m-wrap" name="businessAttribute64"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute63")%></span><input class="m-wrap" name="businessAttribute63"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute62")%></span><input class="m-wrap" name="businessAttribute62"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute61")%></span><input class="m-wrap" name="businessAttribute61"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute60")%></span><input class="m-wrap" name="businessAttribute60"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute59")%></span><input class="m-wrap" name="businessAttribute59"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute58")%></span><input class="m-wrap" name="businessAttribute58"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute57")%></span><input class="m-wrap" name="businessAttribute57"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute56")%></span><input class="m-wrap" name="businessAttribute56"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute55")%></span><input class="m-wrap" name="businessAttribute55"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute54")%></span><input class="m-wrap" name="businessAttribute54"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute53")%></span><input class="m-wrap" name="businessAttribute53"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute52")%></span><input class="m-wrap" name="businessAttribute52"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute51")%></span><input class="m-wrap" name="businessAttribute51"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute50")%></span><input class="m-wrap" name="businessAttribute50"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute49")%></span><input class="m-wrap" name="businessAttribute49"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute48")%></span><input class="m-wrap" name="businessAttribute48"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute47")%></span><input class="m-wrap" name="businessAttribute47"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute46")%></span><input class="m-wrap" name="businessAttribute46"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute45")%></span><input class="m-wrap" name="businessAttribute45"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute44")%></span><input class="m-wrap" name="businessAttribute44"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute43")%></span><input class="m-wrap" name="businessAttribute43"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute42")%></span><input class="m-wrap" name="businessAttribute42"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute41")%></span><input class="m-wrap" name="businessAttribute41"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute40")%></span><input class="m-wrap" name="businessAttribute40"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute39")%></span><input class="m-wrap" name="businessAttribute39"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute38")%></span><input class="m-wrap" name="businessAttribute38"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute37")%></span><input class="m-wrap" name="businessAttribute37"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute36")%></span><input class="m-wrap" name="businessAttribute36"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute35")%></span><input class="m-wrap" name="businessAttribute35"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute34")%></span><input class="m-wrap" name="businessAttribute34"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute33")%></span><input class="m-wrap" name="businessAttribute33"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute32")%></span><input class="m-wrap" name="businessAttribute32"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute31")%></span><input class="m-wrap" name="businessAttribute31"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute30")%></span><input class="m-wrap" name="businessAttribute30"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute29")%></span><input class="m-wrap" name="businessAttribute29"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute28")%></span><input class="m-wrap" name="businessAttribute28"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute27")%></span><input class="m-wrap" name="businessAttribute27"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute26")%></span><input class="m-wrap" name="businessAttribute26"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute25")%></span><input class="m-wrap" name="businessAttribute25"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute24")%></span><input class="m-wrap" name="businessAttribute24"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute23")%></span><input class="m-wrap" name="businessAttribute23"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute22")%></span><input class="m-wrap" name="businessAttribute22"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute21")%></span><input class="m-wrap" name="businessAttribute21"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute20")%></span><input class="m-wrap" name="businessAttribute20"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute19")%></span><input class="m-wrap" name="businessAttribute19"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute18")%></span><input class="m-wrap" name="businessAttribute18"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute17")%></span><input class="m-wrap" name="businessAttribute17"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute16")%></span><input class="m-wrap" name="businessAttribute16"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute15")%></span><input class="m-wrap" name="businessAttribute15"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute14")%></span><input class="m-wrap" name="businessAttribute14"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute13")%></span><input class="m-wrap" name="businessAttribute13"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute12")%></span><input class="m-wrap" name="businessAttribute12"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute11")%></span><input class="m-wrap" name="businessAttribute11"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute10")%></span><input class="m-wrap" name="businessAttribute10"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute9")%></span><input class="m-wrap" name="businessAttribute9"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute8")%></span><input class="m-wrap" name="businessAttribute8"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute7")%></span><input class="m-wrap" name="businessAttribute7"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute6")%></span><input class="m-wrap" name="businessAttribute6"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute5")%></span><input class="m-wrap" name="businessAttribute5"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute4")%></span><input class="m-wrap" name="businessAttribute4"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute3")%></span><input class="m-wrap" name="businessAttribute3"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute1")%></span><input class="m-wrap" name="businessAttribute1"  type="text"  						maxLength="255" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute2")%></span><input class="m-wrap" name="businessAttribute2"  type="text"  						maxLength="255" />
							</div>

							</div>
						</div>
					</form>
				</div>
			</div>
	 <!-- end 新增 -->
	 
	 <!-- begain查询 -->
	<form action="<%=request.getContextPath()%>/generalbusinessprocess"	method="post" name="form" >
	<div id="portlet-config-search" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>查询<input type="submit" class="btn blue" value="查询" style="float: right;" /></h3>
				</div>
				<div class="modal-body">
						<div class="control-group">
							<div class="controls">

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%></span><input type="text" class="text_field_reference text_field_readonly"  hiddenInputId="procDefId" name="procDefIdName" inputName="<%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%>" value="" /><input type="hidden" name="procDefId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getReference(new Array(form.procDefId, form.procDefIdName), '<%=request.getContextPath()%>/', '<%=request.getContextPath()%>/reprocdef/reference?referenceInputType=radio');"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procInstId")%></span><input class="m-wrap" name="procInstId" type="text"  						maxLength="64" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procName")%></span><input class="m-wrap" name="procName" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procKey")%></span><input class="m-wrap" name="procKey" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiator")%></span><input class="m-wrap" name="initiator" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorId")%></span><input class="m-wrap" name="initiatorId" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("sendOrganizationDepartment")%></span><input class="m-wrap" name="sendOrganizationDepartment" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorOrganizationId")%></span><input class="m-wrap" name="initiatorOrganizationId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorTime")%></span><input name="initiatorTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessType")%></span><input class="m-wrap" name="businessType" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessGroup")%></span><input class="m-wrap" name="businessGroup" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("parentId")%></span><input class="m-wrap" name="parentId" type="text"  						maxLength="19" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute70")%></span><input class="m-wrap" name="businessAttribute70" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute69")%></span><input class="m-wrap" name="businessAttribute69" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute68")%></span><input class="m-wrap" name="businessAttribute68" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute67")%></span><input class="m-wrap" name="businessAttribute67" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute66")%></span><input class="m-wrap" name="businessAttribute66" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute65")%></span><input class="m-wrap" name="businessAttribute65" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute64")%></span><input class="m-wrap" name="businessAttribute64" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute63")%></span><input class="m-wrap" name="businessAttribute63" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute62")%></span><input class="m-wrap" name="businessAttribute62" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute61")%></span><input class="m-wrap" name="businessAttribute61" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute60")%></span><input class="m-wrap" name="businessAttribute60" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute59")%></span><input class="m-wrap" name="businessAttribute59" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute58")%></span><input class="m-wrap" name="businessAttribute58" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute57")%></span><input class="m-wrap" name="businessAttribute57" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute56")%></span><input class="m-wrap" name="businessAttribute56" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute55")%></span><input class="m-wrap" name="businessAttribute55" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute54")%></span><input class="m-wrap" name="businessAttribute54" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute53")%></span><input class="m-wrap" name="businessAttribute53" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute52")%></span><input class="m-wrap" name="businessAttribute52" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute51")%></span><input class="m-wrap" name="businessAttribute51" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute50")%></span><input class="m-wrap" name="businessAttribute50" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute49")%></span><input class="m-wrap" name="businessAttribute49" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute48")%></span><input class="m-wrap" name="businessAttribute48" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute47")%></span><input class="m-wrap" name="businessAttribute47" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute46")%></span><input class="m-wrap" name="businessAttribute46" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute45")%></span><input class="m-wrap" name="businessAttribute45" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute44")%></span><input class="m-wrap" name="businessAttribute44" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute43")%></span><input class="m-wrap" name="businessAttribute43" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute42")%></span><input class="m-wrap" name="businessAttribute42" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute41")%></span><input class="m-wrap" name="businessAttribute41" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute40")%></span><input class="m-wrap" name="businessAttribute40" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute39")%></span><input class="m-wrap" name="businessAttribute39" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute38")%></span><input class="m-wrap" name="businessAttribute38" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute37")%></span><input class="m-wrap" name="businessAttribute37" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute36")%></span><input class="m-wrap" name="businessAttribute36" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute35")%></span><input class="m-wrap" name="businessAttribute35" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute34")%></span><input class="m-wrap" name="businessAttribute34" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute33")%></span><input class="m-wrap" name="businessAttribute33" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute32")%></span><input class="m-wrap" name="businessAttribute32" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute31")%></span><input class="m-wrap" name="businessAttribute31" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute30")%></span><input class="m-wrap" name="businessAttribute30" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute29")%></span><input class="m-wrap" name="businessAttribute29" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute28")%></span><input class="m-wrap" name="businessAttribute28" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute27")%></span><input class="m-wrap" name="businessAttribute27" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute26")%></span><input class="m-wrap" name="businessAttribute26" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute25")%></span><input class="m-wrap" name="businessAttribute25" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute24")%></span><input class="m-wrap" name="businessAttribute24" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute23")%></span><input class="m-wrap" name="businessAttribute23" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute22")%></span><input class="m-wrap" name="businessAttribute22" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute21")%></span><input class="m-wrap" name="businessAttribute21" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute20")%></span><input class="m-wrap" name="businessAttribute20" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute19")%></span><input class="m-wrap" name="businessAttribute19" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute18")%></span><input class="m-wrap" name="businessAttribute18" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute17")%></span><input class="m-wrap" name="businessAttribute17" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute16")%></span><input class="m-wrap" name="businessAttribute16" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute15")%></span><input class="m-wrap" name="businessAttribute15" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute14")%></span><input class="m-wrap" name="businessAttribute14" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute13")%></span><input class="m-wrap" name="businessAttribute13" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute12")%></span><input class="m-wrap" name="businessAttribute12" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute11")%></span><input class="m-wrap" name="businessAttribute11" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute10")%></span><input class="m-wrap" name="businessAttribute10" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute9")%></span><input class="m-wrap" name="businessAttribute9" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute8")%></span><input class="m-wrap" name="businessAttribute8" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute7")%></span><input class="m-wrap" name="businessAttribute7" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute6")%></span><input class="m-wrap" name="businessAttribute6" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute5")%></span><input class="m-wrap" name="businessAttribute5" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute4")%></span><input class="m-wrap" name="businessAttribute4" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute3")%></span><input class="m-wrap" name="businessAttribute3" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute1")%></span><input class="m-wrap" name="businessAttribute1" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute2")%></span><input class="m-wrap" name="businessAttribute2" type="text"  						maxLength="255" />
							</div>

							</div>
						</div>
				</div>
			</div>
	 <!-- end 查询 -->
	 
	 
     <div class="container-fluid">
	   <div class="row-fluid" style="margin: 0 0px;padding: 0px 0px;">
			<div class="span12">
			  <ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="index.html">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">xx模块</a> <i class="icon-angle-right"></i></li>
					<li><a href="#"><%=IGeneralBusinessProcessConstants.TABLE_NAME_DISPLAY%></a></li>
				</ul>
			</div>
	 </div>
    <div class="row-fluid" style="margin: 0 0px;padding: 0px 0px;">
				<div class="span12">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-globe"></i>列表
							</div>
							<div class="tools">
										<a href="#portlet-config-search" data-toggle="modal" class="halflings-icon query"></a>
										<a href="#portlet-config-add" data-toggle="modal" class="halflings-icon white file"></a> 
										<a href="" onclick="update();" data-toggle="modal" class="config"></a> 
										<a href="javascript:;" onclick="_delete('<%=request.getContextPath()%>/generalbusinessprocess/logicDelete');" class="halflings-icon white trash"></a>
									</div>
						</div>
						<div class="portlet-body no-more-tables" style="overflow: auto;" >
							<table class=" table-striped table-condensed cf table-hover table table-bordered">
								<thead>
									<tr>
									<th style="width: 20px">序号</th>
									<th style="width: 85px" class="checkmain selectedAll" ischecked="false" title="全选" class="checkbox">
		                            				<div class="checkbox">
										<ins id="ischeck">
										<input type="hidden" class="checkmain" ischecked="false" />
											
									   </ins>
									   选择
									</div>
				                                       </th>

									<th class="sorting" RM_ORDER_STR="PROC_NAME"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procName")%></th>
									<th class="sorting" RM_ORDER_STR="PROC_KEY"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procKey")%></th>
									<th class="sorting" RM_ORDER_STR="INITIATOR"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiator")%></th>
									<th class="sorting" RM_ORDER_STR="SEND_ORGANIZATION_DEPARTMENT"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("sendOrganizationDepartment")%></th>
									<th class="sorting" RM_ORDER_STR="INITIATOR_TIME"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorTime")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_TYPE"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessType")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_GROUP"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessGroup")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE70"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute70")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE69"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute69")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE68"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute68")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE67"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute67")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE66"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute66")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE65"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute65")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE64"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute64")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE63"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute63")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE62"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute62")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE61"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute61")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE60"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute60")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE59"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute59")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE58"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute58")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE57"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute57")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE56"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute56")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE55"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute55")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE54"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute54")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE53"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute53")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE52"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute52")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE51"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute51")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE50"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute50")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE49"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute49")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE48"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute48")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE47"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute47")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE46"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute46")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE45"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute45")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE44"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute44")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE43"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute43")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE42"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute42")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE41"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute41")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE40"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute40")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE39"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute39")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE38"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute38")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE37"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute37")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE36"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute36")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE35"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute35")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE34"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute34")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE33"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute33")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE32"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute32")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE31"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute31")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE30"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute30")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE29"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute29")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE28"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute28")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE27"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute27")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE26"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute26")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE25"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute25")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE24"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute24")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE23"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute23")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE22"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute22")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE21"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute21")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE20"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute20")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE19"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute19")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE18"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute18")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE17"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute17")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE16"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute16")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE15"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute15")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE14"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute14")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE13"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute13")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE12"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute12")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE11"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute11")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE10"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute10")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE9"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute9")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE8"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute8")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE7"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute7")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE6"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute6")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE5"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute5")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE4"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute4")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE3"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute3")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE1"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute1")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_ATTRIBUTE2"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessAttribute2")%></th>

		</tr>
								</thead>
								<tbody>
									<c:forEach items="${beans}" var="list" varStatus="rowCounter"	begin='0' step='1'>
												<tr>
											      <td><c:out value="${rowCounter.index+1}"></c:out></td>
													<td>
								<div class="checkbox">
										<ins id="ischeck">
													<input type="hidden" class="checkmain childCkeck" ischecked="false" value="<c:out value="${list.id}"></c:out>" />
															</ins>
														</div>
													</td>
										<td><c:out value="${list.procName}"></c:out></td>
										<td><c:out value="${list.procKey}"></c:out></td>
										<td><c:out value="${list.initiator}"></c:out></td>
										<td><c:out value="${list.sendOrganizationDepartment}"></c:out></td>
										<td><c:out value="${list.initiatorTime}"></c:out></td>
										<td><c:out value="${list.businessType}"></c:out></td>
										<td><c:out value="${list.businessGroup}"></c:out></td>
										<td><c:out value="${list.businessAttribute70}"></c:out></td>
										<td><c:out value="${list.businessAttribute69}"></c:out></td>
										<td><c:out value="${list.businessAttribute68}"></c:out></td>
										<td><c:out value="${list.businessAttribute67}"></c:out></td>
										<td><c:out value="${list.businessAttribute66}"></c:out></td>
										<td><c:out value="${list.businessAttribute65}"></c:out></td>
										<td><c:out value="${list.businessAttribute64}"></c:out></td>
										<td><c:out value="${list.businessAttribute63}"></c:out></td>
										<td><c:out value="${list.businessAttribute62}"></c:out></td>
										<td><c:out value="${list.businessAttribute61}"></c:out></td>
										<td><c:out value="${list.businessAttribute60}"></c:out></td>
										<td><c:out value="${list.businessAttribute59}"></c:out></td>
										<td><c:out value="${list.businessAttribute58}"></c:out></td>
										<td><c:out value="${list.businessAttribute57}"></c:out></td>
										<td><c:out value="${list.businessAttribute56}"></c:out></td>
										<td><c:out value="${list.businessAttribute55}"></c:out></td>
										<td><c:out value="${list.businessAttribute54}"></c:out></td>
										<td><c:out value="${list.businessAttribute53}"></c:out></td>
										<td><c:out value="${list.businessAttribute52}"></c:out></td>
										<td><c:out value="${list.businessAttribute51}"></c:out></td>
										<td><c:out value="${list.businessAttribute50}"></c:out></td>
										<td><c:out value="${list.businessAttribute49}"></c:out></td>
										<td><c:out value="${list.businessAttribute48}"></c:out></td>
										<td><c:out value="${list.businessAttribute47}"></c:out></td>
										<td><c:out value="${list.businessAttribute46}"></c:out></td>
										<td><c:out value="${list.businessAttribute45}"></c:out></td>
										<td><c:out value="${list.businessAttribute44}"></c:out></td>
										<td><c:out value="${list.businessAttribute43}"></c:out></td>
										<td><c:out value="${list.businessAttribute42}"></c:out></td>
										<td><c:out value="${list.businessAttribute41}"></c:out></td>
										<td><c:out value="${list.businessAttribute40}"></c:out></td>
										<td><c:out value="${list.businessAttribute39}"></c:out></td>
										<td><c:out value="${list.businessAttribute38}"></c:out></td>
										<td><c:out value="${list.businessAttribute37}"></c:out></td>
										<td><c:out value="${list.businessAttribute36}"></c:out></td>
										<td><c:out value="${list.businessAttribute35}"></c:out></td>
										<td><c:out value="${list.businessAttribute34}"></c:out></td>
										<td><c:out value="${list.businessAttribute33}"></c:out></td>
										<td><c:out value="${list.businessAttribute32}"></c:out></td>
										<td><c:out value="${list.businessAttribute31}"></c:out></td>
										<td><c:out value="${list.businessAttribute30}"></c:out></td>
										<td><c:out value="${list.businessAttribute29}"></c:out></td>
										<td><c:out value="${list.businessAttribute28}"></c:out></td>
										<td><c:out value="${list.businessAttribute27}"></c:out></td>
										<td><c:out value="${list.businessAttribute26}"></c:out></td>
										<td><c:out value="${list.businessAttribute25}"></c:out></td>
										<td><c:out value="${list.businessAttribute24}"></c:out></td>
										<td><c:out value="${list.businessAttribute23}"></c:out></td>
										<td><c:out value="${list.businessAttribute22}"></c:out></td>
										<td><c:out value="${list.businessAttribute21}"></c:out></td>
										<td><c:out value="${list.businessAttribute20}"></c:out></td>
										<td><c:out value="${list.businessAttribute19}"></c:out></td>
										<td><c:out value="${list.businessAttribute18}"></c:out></td>
										<td><c:out value="${list.businessAttribute17}"></c:out></td>
										<td><c:out value="${list.businessAttribute16}"></c:out></td>
										<td><c:out value="${list.businessAttribute15}"></c:out></td>
										<td><c:out value="${list.businessAttribute14}"></c:out></td>
										<td><c:out value="${list.businessAttribute13}"></c:out></td>
										<td><c:out value="${list.businessAttribute12}"></c:out></td>
										<td><c:out value="${list.businessAttribute11}"></c:out></td>
										<td><c:out value="${list.businessAttribute10}"></c:out></td>
										<td><c:out value="${list.businessAttribute9}"></c:out></td>
										<td><c:out value="${list.businessAttribute8}"></c:out></td>
										<td><c:out value="${list.businessAttribute7}"></c:out></td>
										<td><c:out value="${list.businessAttribute6}"></c:out></td>
										<td><c:out value="${list.businessAttribute5}"></c:out></td>
										<td><c:out value="${list.businessAttribute4}"></c:out></td>
										<td><c:out value="${list.businessAttribute3}"></c:out></td>
										<td><c:out value="${list.businessAttribute1}"></c:out></td>
										<td><c:out value="${list.businessAttribute2}"></c:out></td>
										</tr>
								 </c:forEach>
								</tbody>
							</table>
							<jsp:include page="/jsp/include/page.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<input type="hidden" name="RM_ORDER_STR" value="${RM_ORDER_STR}">
		</form>
	</div>	
 <script type="text/javascript">
	//根据字段排序
	$(function(){
         var RM_ORDER_STR='${RM_ORDER_STR}';//当前排序值字段
         orderBy(RM_ORDER_STR);
         var url="<%=request.getContextPath()%>/generalbusinessprocess";
        sorting(url);
        });
	function update() {
		var allcheck = $(".checkmain");
		var selectupdate = 0;
		var updateDate = "";
		for (var g = 0; g < allcheck.length; g++) {
			var b = $(allcheck[g]).val();
			var c = $(allcheck[g]).attr("ischecked");
			if (c == "true") {
				selectupdate++;
				updateDate = b;
			}
		}
		if (selectupdate == 0) {
			$.teninedialog({
	             title:'请选择记录',
	             content:'请至少选择一条记录'
	         });
			return false;
		} 
		if (selectupdate > 1) {
			$.teninedialog({
	             title:'选择记录数目错误',
	             content:'只能选择一条记录进行修改'
	         });
			return false;
		}
		 $("#portlet-config-edit").modal("show");
		 $.ajax({
             type: "POST",
             url: "<%=request.getContextPath()%>/generalbusinessprocess/get/" + updateDate+"/"+new Date(),
             dataType: "json",
             async:false , //false为同步
             success: function(data,status) {
     	   	   if(data.status=="0"){
     	   	       alert(data.message);
     	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=procDefId]").val(data.bean.procDefId);
                      $("#portlet-config-edit").find("input[id=procInstId]").val(data.bean.procInstId);
                      $("#portlet-config-edit").find("input[id=procName]").val(data.bean.procName);
                      $("#portlet-config-edit").find("input[id=procKey]").val(data.bean.procKey);
                      $("#portlet-config-edit").find("input[id=initiator]").val(data.bean.initiator);
                      $("#portlet-config-edit").find("input[id=initiatorId]").val(data.bean.initiatorId);
                      $("#portlet-config-edit").find("input[id=sendOrganizationDepartment]").val(data.bean.sendOrganizationDepartment);
                      $("#portlet-config-edit").find("input[id=initiatorOrganizationId]").val(data.bean.initiatorOrganizationId);
                      $("#portlet-config-edit").find("input[id=initiatorTime]").val(data.bean.initiatorTime);
                      $("#portlet-config-edit").find("input[id=businessType]").val(data.bean.businessType);
                      $("#portlet-config-edit").find("input[id=businessGroup]").val(data.bean.businessGroup);
                      $("#portlet-config-edit").find("input[id=parentId]").val(data.bean.parentId);
                      $("#portlet-config-edit").find("input[id=businessAttribute70]").val(data.bean.businessAttribute70);
                      $("#portlet-config-edit").find("input[id=businessAttribute69]").val(data.bean.businessAttribute69);
                      $("#portlet-config-edit").find("input[id=businessAttribute68]").val(data.bean.businessAttribute68);
                      $("#portlet-config-edit").find("input[id=businessAttribute67]").val(data.bean.businessAttribute67);
                      $("#portlet-config-edit").find("input[id=businessAttribute66]").val(data.bean.businessAttribute66);
                      $("#portlet-config-edit").find("input[id=businessAttribute65]").val(data.bean.businessAttribute65);
                      $("#portlet-config-edit").find("input[id=businessAttribute64]").val(data.bean.businessAttribute64);
                      $("#portlet-config-edit").find("input[id=businessAttribute63]").val(data.bean.businessAttribute63);
                      $("#portlet-config-edit").find("input[id=businessAttribute62]").val(data.bean.businessAttribute62);
                      $("#portlet-config-edit").find("input[id=businessAttribute61]").val(data.bean.businessAttribute61);
                      $("#portlet-config-edit").find("input[id=businessAttribute60]").val(data.bean.businessAttribute60);
                      $("#portlet-config-edit").find("input[id=businessAttribute59]").val(data.bean.businessAttribute59);
                      $("#portlet-config-edit").find("input[id=businessAttribute58]").val(data.bean.businessAttribute58);
                      $("#portlet-config-edit").find("input[id=businessAttribute57]").val(data.bean.businessAttribute57);
                      $("#portlet-config-edit").find("input[id=businessAttribute56]").val(data.bean.businessAttribute56);
                      $("#portlet-config-edit").find("input[id=businessAttribute55]").val(data.bean.businessAttribute55);
                      $("#portlet-config-edit").find("input[id=businessAttribute54]").val(data.bean.businessAttribute54);
                      $("#portlet-config-edit").find("input[id=businessAttribute53]").val(data.bean.businessAttribute53);
                      $("#portlet-config-edit").find("input[id=businessAttribute52]").val(data.bean.businessAttribute52);
                      $("#portlet-config-edit").find("input[id=businessAttribute51]").val(data.bean.businessAttribute51);
                      $("#portlet-config-edit").find("input[id=businessAttribute50]").val(data.bean.businessAttribute50);
                      $("#portlet-config-edit").find("input[id=businessAttribute49]").val(data.bean.businessAttribute49);
                      $("#portlet-config-edit").find("input[id=businessAttribute48]").val(data.bean.businessAttribute48);
                      $("#portlet-config-edit").find("input[id=businessAttribute47]").val(data.bean.businessAttribute47);
                      $("#portlet-config-edit").find("input[id=businessAttribute46]").val(data.bean.businessAttribute46);
                      $("#portlet-config-edit").find("input[id=businessAttribute45]").val(data.bean.businessAttribute45);
                      $("#portlet-config-edit").find("input[id=businessAttribute44]").val(data.bean.businessAttribute44);
                      $("#portlet-config-edit").find("input[id=businessAttribute43]").val(data.bean.businessAttribute43);
                      $("#portlet-config-edit").find("input[id=businessAttribute42]").val(data.bean.businessAttribute42);
                      $("#portlet-config-edit").find("input[id=businessAttribute41]").val(data.bean.businessAttribute41);
                      $("#portlet-config-edit").find("input[id=businessAttribute40]").val(data.bean.businessAttribute40);
                      $("#portlet-config-edit").find("input[id=businessAttribute39]").val(data.bean.businessAttribute39);
                      $("#portlet-config-edit").find("input[id=businessAttribute38]").val(data.bean.businessAttribute38);
                      $("#portlet-config-edit").find("input[id=businessAttribute37]").val(data.bean.businessAttribute37);
                      $("#portlet-config-edit").find("input[id=businessAttribute36]").val(data.bean.businessAttribute36);
                      $("#portlet-config-edit").find("input[id=businessAttribute35]").val(data.bean.businessAttribute35);
                      $("#portlet-config-edit").find("input[id=businessAttribute34]").val(data.bean.businessAttribute34);
                      $("#portlet-config-edit").find("input[id=businessAttribute33]").val(data.bean.businessAttribute33);
                      $("#portlet-config-edit").find("input[id=businessAttribute32]").val(data.bean.businessAttribute32);
                      $("#portlet-config-edit").find("input[id=businessAttribute31]").val(data.bean.businessAttribute31);
                      $("#portlet-config-edit").find("input[id=businessAttribute30]").val(data.bean.businessAttribute30);
                      $("#portlet-config-edit").find("input[id=businessAttribute29]").val(data.bean.businessAttribute29);
                      $("#portlet-config-edit").find("input[id=businessAttribute28]").val(data.bean.businessAttribute28);
                      $("#portlet-config-edit").find("input[id=businessAttribute27]").val(data.bean.businessAttribute27);
                      $("#portlet-config-edit").find("input[id=businessAttribute26]").val(data.bean.businessAttribute26);
                      $("#portlet-config-edit").find("input[id=businessAttribute25]").val(data.bean.businessAttribute25);
                      $("#portlet-config-edit").find("input[id=businessAttribute24]").val(data.bean.businessAttribute24);
                      $("#portlet-config-edit").find("input[id=businessAttribute23]").val(data.bean.businessAttribute23);
                      $("#portlet-config-edit").find("input[id=businessAttribute22]").val(data.bean.businessAttribute22);
                      $("#portlet-config-edit").find("input[id=businessAttribute21]").val(data.bean.businessAttribute21);
                      $("#portlet-config-edit").find("input[id=businessAttribute20]").val(data.bean.businessAttribute20);
                      $("#portlet-config-edit").find("input[id=businessAttribute19]").val(data.bean.businessAttribute19);
                      $("#portlet-config-edit").find("input[id=businessAttribute18]").val(data.bean.businessAttribute18);
                      $("#portlet-config-edit").find("input[id=businessAttribute17]").val(data.bean.businessAttribute17);
                      $("#portlet-config-edit").find("input[id=businessAttribute16]").val(data.bean.businessAttribute16);
                      $("#portlet-config-edit").find("input[id=businessAttribute15]").val(data.bean.businessAttribute15);
                      $("#portlet-config-edit").find("input[id=businessAttribute14]").val(data.bean.businessAttribute14);
                      $("#portlet-config-edit").find("input[id=businessAttribute13]").val(data.bean.businessAttribute13);
                      $("#portlet-config-edit").find("input[id=businessAttribute12]").val(data.bean.businessAttribute12);
                      $("#portlet-config-edit").find("input[id=businessAttribute11]").val(data.bean.businessAttribute11);
                      $("#portlet-config-edit").find("input[id=businessAttribute10]").val(data.bean.businessAttribute10);
                      $("#portlet-config-edit").find("input[id=businessAttribute9]").val(data.bean.businessAttribute9);
                      $("#portlet-config-edit").find("input[id=businessAttribute8]").val(data.bean.businessAttribute8);
                      $("#portlet-config-edit").find("input[id=businessAttribute7]").val(data.bean.businessAttribute7);
                      $("#portlet-config-edit").find("input[id=businessAttribute6]").val(data.bean.businessAttribute6);
                      $("#portlet-config-edit").find("input[id=businessAttribute5]").val(data.bean.businessAttribute5);
                      $("#portlet-config-edit").find("input[id=businessAttribute4]").val(data.bean.businessAttribute4);
                      $("#portlet-config-edit").find("input[id=businessAttribute3]").val(data.bean.businessAttribute3);
                      $("#portlet-config-edit").find("input[id=businessAttribute1]").val(data.bean.businessAttribute1);
                      $("#portlet-config-edit").find("input[id=businessAttribute2]").val(data.bean.businessAttribute2);
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
	</script>		
</body>
</html>
<script type="text/javascript">
<%  //表单回写
    if(request.getAttribute(IGeneralBusinessProcessConstants.REQUEST_WRITE_BACK_FORM_VALUES) != null) {  //如果request中取出的表单回写bean不为空
        out.print(RmVoHelper.writeBackMapToForm((java.util.Map)request.getAttribute(IGeneralBusinessProcessConstants.REQUEST_WRITE_BACK_FORM_VALUES)));  //输出表单回写方法的脚本
    }
 
%>
</script>
