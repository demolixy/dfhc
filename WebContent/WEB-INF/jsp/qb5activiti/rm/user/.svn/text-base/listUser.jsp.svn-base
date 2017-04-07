<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="com.dfhc.rm.user.IUserConstants" %>
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

<title><%=IUserConstants.TABLE_NAME_DISPLAY%></title>
</head>
<body class="page-header-fixed" >
  <div class="page-content"  style="margin: 0px 0px 0px 0px;padding: 0px 0px;">
  <!-- begain 修改 -->
   <div id="portlet-config-edit" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>修改	<input type="button" class="btn blue" value="提交" style="float: right;" 
										 onclick="javascript:submitUpdateAjax('<%=request.getContextPath()%>/user/update',$(updateForm).serialize(),'<%=request.getContextPath()%>/user');"/></h3>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath()%>/user/update" id="updateForm" name="updateForm" method="post">
						<div class="control-group">
							<div class="controls">
							<input id="id" name="id" type="hidden" />

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("name")%></span><input class="m-wrap" name="name" id="name" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lockStatus")%></span><%=RmJspHelper.getSelectField("lockStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_LOCK_STATUS), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("lockStatus") + "' validate='notNull;' ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginId")%></span><input class="m-wrap" name="loginId" id="loginId" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("password")%></span><input class="m-wrap" name="password" id="password" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("authenType")%></span><input class="m-wrap" name="authenType" id="authenType" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("organizationId")%></span><input class="m-wrap" name="organizationId" id="organizationId" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("employeeId")%></span><input class="m-wrap" name="employeeId" id="employeeId" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("email")%></span><input class="m-wrap" name="email" id="email" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("adminType")%></span><%=RmJspHelper.getSelectField("adminType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_ADMIN_TYPE), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("adminType") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("description")%></span><input class="m-wrap" name="description" id="description" type="text"  						maxLength="1000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("agentStatus")%></span><%=RmJspHelper.getSelectField("agentStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_AGENT_STATUS), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("agentStatus") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginStatus")%></span><input class="m-wrap" name="loginStatus" id="loginStatus" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginDate")%></span><input name="lastLoginDate" id="lastLoginDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginIp")%></span><input class="m-wrap" name="lastLoginIp" id="lastLoginIp" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginSum")%></span><input class="m-wrap" name="loginSum" id="loginSum" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastCustomCss")%></span><input class="m-wrap" name="lastCustomCss" id="lastCustomCss" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("isAffix")%></span><input class="m-wrap" name="isAffix" id="isAffix" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("functionPermission")%></span><input class="m-wrap" name="functionPermission" id="functionPermission" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("dataPermission")%></span><input class="m-wrap" name="dataPermission" id="dataPermission" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom1")%></span><input class="m-wrap" name="custom1" id="custom1" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom2")%></span><input class="m-wrap" name="custom2" id="custom2" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom3")%></span><input class="m-wrap" name="custom3" id="custom3" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom4")%></span><input class="m-wrap" name="custom4" id="custom4" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom5")%></span><input class="m-wrap" name="custom5" id="custom5" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("customXml")%></span><input class="m-wrap" name="customXml" id="customXml" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("usableStatus")%></span><input class="m-wrap" name="usableStatus" id="usableStatus" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%></span><input name="modifyDate" id="modifyDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyIp")%></span><input class="m-wrap" name="modifyIp" id="modifyIp" type="text"  						maxLength="45" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyUserId")%></span><input class="m-wrap" name="modifyUserId" id="modifyUserId" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createTime")%></span><input name="createTime" id="createTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createUserName")%></span><input class="m-wrap" name="createUserName" id="createUserName" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute1")%></span><input class="m-wrap" name="attribute1" id="attribute1" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute2")%></span><input class="m-wrap" name="attribute2" id="attribute2" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute3")%></span><input class="m-wrap" name="attribute3" id="attribute3" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute4")%></span><input class="m-wrap" name="attribute4" id="attribute4" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute5")%></span><input class="m-wrap" name="attribute5" id="attribute5" type="text"  						maxLength="100" />
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
								onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/user/insert',$(insertForm).serialize(),'<%=request.getContextPath()%>/user');"/></h3>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath()%>/user/insert" id="insertForm" name="insertForm" method="post">
						<div class="control-group">
							<div class="controls">
							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("name")%></span><input class="m-wrap" name="name"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lockStatus")%></span><%=RmJspHelper.getSelectField("lockStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_LOCK_STATUS), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("lockStatus") + "' validate='notNull;' ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginId")%></span><input class="m-wrap" name="loginId"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("password")%></span><input class="m-wrap" name="password"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("authenType")%></span><input class="m-wrap" name="authenType"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("organizationId")%></span><input class="m-wrap" name="organizationId"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("employeeId")%></span><input class="m-wrap" name="employeeId"  type="text"  						maxLength="50" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("email")%></span><input class="m-wrap" name="email"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("adminType")%></span><%=RmJspHelper.getSelectField("adminType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_ADMIN_TYPE), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("adminType") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("description")%></span><input class="m-wrap" name="description"  type="text"  						maxLength="1000" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("agentStatus")%></span><%=RmJspHelper.getSelectField("agentStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_AGENT_STATUS), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("agentStatus") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginStatus")%></span><input class="m-wrap" name="loginStatus"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginDate")%></span><input name="lastLoginDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginDate")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginIp")%></span><input class="m-wrap" name="lastLoginIp"  type="text"  						maxLength="50" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginSum")%></span><input class="m-wrap" name="loginSum"  type="text"  						maxLength="20" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastCustomCss")%></span><input class="m-wrap" name="lastCustomCss"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("isAffix")%></span><input class="m-wrap" name="isAffix"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("functionPermission")%></span><input class="m-wrap" name="functionPermission"  type="text"  						maxLength="4000" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("dataPermission")%></span><input class="m-wrap" name="dataPermission"  type="text"  						maxLength="4000" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom1")%></span><input class="m-wrap" name="custom1"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom2")%></span><input class="m-wrap" name="custom2"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom3")%></span><input class="m-wrap" name="custom3"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom4")%></span><input class="m-wrap" name="custom4"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom5")%></span><input class="m-wrap" name="custom5"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("customXml")%></span><input class="m-wrap" name="customXml"  type="text"  						maxLength="4000" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("usableStatus")%></span><input class="m-wrap" name="usableStatus"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%></span><input name="modifyDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyIp")%></span><input class="m-wrap" name="modifyIp"  type="text"  						maxLength="45" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyUserId")%></span><input class="m-wrap" name="modifyUserId"  type="text"  						maxLength="20" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createTime")%></span><input name="createTime"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createTime")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createUserName")%></span><input class="m-wrap" name="createUserName"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute1")%></span><input class="m-wrap" name="attribute1"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute2")%></span><input class="m-wrap" name="attribute2"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute3")%></span><input class="m-wrap" name="attribute3"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute4")%></span><input class="m-wrap" name="attribute4"  type="text"  						maxLength="100" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute5")%></span><input class="m-wrap" name="attribute5"  type="text"  						maxLength="100" />
							</div>

							</div>
						</div>
					</form>
				</div>
			</div>
	 <!-- end 新增 -->
	 
	 <!-- begain查询 -->
	<form action="<%=request.getContextPath()%>/user"	method="post" name="form" >
	<div id="portlet-config-search" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>查询<input type="submit" class="btn blue" value="查询" style="float: right;" /></h3>
				</div>
				<div class="modal-body">
						<div class="control-group">
							<div class="controls">

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("name")%></span><input class="m-wrap" name="name" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lockStatus")%></span><%=RmJspHelper.getSelectField("lockStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_LOCK_STATUS), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("lockStatus") + "' validate='notNull;' ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginId")%></span><input class="m-wrap" name="loginId" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("password")%></span><input class="m-wrap" name="password" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("authenType")%></span><input class="m-wrap" name="authenType" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("organizationId")%></span><input class="m-wrap" name="organizationId" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("employeeId")%></span><input class="m-wrap" name="employeeId" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("email")%></span><input class="m-wrap" name="email" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("adminType")%></span><%=RmJspHelper.getSelectField("adminType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_ADMIN_TYPE), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("adminType") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("description")%></span><input class="m-wrap" name="description" type="text"  						maxLength="1000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("agentStatus")%></span><%=RmJspHelper.getSelectField("agentStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_AGENT_STATUS), "", "inputName='" + IUserConstants.TABLE_COLUMN_DISPLAY.get("agentStatus") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginStatus")%></span><input class="m-wrap" name="loginStatus" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginDate")%></span><input name="lastLoginDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginIp")%></span><input class="m-wrap" name="lastLoginIp" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginSum")%></span><input class="m-wrap" name="loginSum" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastCustomCss")%></span><input class="m-wrap" name="lastCustomCss" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("isAffix")%></span><input class="m-wrap" name="isAffix" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("functionPermission")%></span><input class="m-wrap" name="functionPermission" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("dataPermission")%></span><input class="m-wrap" name="dataPermission" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom1")%></span><input class="m-wrap" name="custom1" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom2")%></span><input class="m-wrap" name="custom2" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom3")%></span><input class="m-wrap" name="custom3" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom4")%></span><input class="m-wrap" name="custom4" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom5")%></span><input class="m-wrap" name="custom5" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("customXml")%></span><input class="m-wrap" name="customXml" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("usableStatus")%></span><input class="m-wrap" name="usableStatus" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%></span><input name="modifyDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyIp")%></span><input class="m-wrap" name="modifyIp" type="text"  						maxLength="45" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyUserId")%></span><input class="m-wrap" name="modifyUserId" type="text"  						maxLength="20" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createTime")%></span><input name="createTime" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createTime")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createUserName")%></span><input class="m-wrap" name="createUserName" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute1")%></span><input class="m-wrap" name="attribute1" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute2")%></span><input class="m-wrap" name="attribute2" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute3")%></span><input class="m-wrap" name="attribute3" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute4")%></span><input class="m-wrap" name="attribute4" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute5")%></span><input class="m-wrap" name="attribute5" type="text"  						maxLength="100" />
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
					<%-- <li><a href="#"><%=IUserConstants.TABLE_NAME_DISPLAY%></a></li> --%>
					<li><a href="#">用户表</a></li>
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
										<a href="javascript:;" onclick="_delete('<%=request.getContextPath()%>/user/logicDelete');" class="halflings-icon white trash"></a>
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

									<th class="sorting" RM_ORDER_STR="NAME"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("name")%></th>
									<th class="sorting" RM_ORDER_STR="LOCK_STATUS"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lockStatus")%></th>
									<th class="sorting" RM_ORDER_STR="PASSWORD"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("password")%></th>
									<th class="sorting" RM_ORDER_STR="AUTHEN_TYPE"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("authenType")%></th>
									<th class="sorting" RM_ORDER_STR="EMAIL"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("email")%></th>
									<th class="sorting" RM_ORDER_STR="ADMIN_TYPE"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("adminType")%></th>
									<th class="sorting" RM_ORDER_STR="DESCRIPTION"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("description")%></th>
									<th class="sorting" RM_ORDER_STR="AGENT_STATUS"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("agentStatus")%></th>
									<th class="sorting" RM_ORDER_STR="LOGIN_STATUS"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginStatus")%></th>
									<th class="sorting" RM_ORDER_STR="LAST_LOGIN_DATE"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginDate")%></th>
									<th class="sorting" RM_ORDER_STR="LAST_LOGIN_IP"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastLoginIp")%></th>
									<th class="sorting" RM_ORDER_STR="LOGIN_SUM"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("loginSum")%></th>
									<th class="sorting" RM_ORDER_STR="LAST_CUSTOM_CSS"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("lastCustomCss")%></th>
									<th class="sorting" RM_ORDER_STR="IS_AFFIX"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("isAffix")%></th>
									<th class="sorting" RM_ORDER_STR="FUNCTION_PERMISSION"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("functionPermission")%></th>
									<th class="sorting" RM_ORDER_STR="DATA_PERMISSION"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("dataPermission")%></th>
									<th class="sorting" RM_ORDER_STR="CUSTOM1"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom1")%></th>
									<th class="sorting" RM_ORDER_STR="CUSTOM2"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom2")%></th>
									<th class="sorting" RM_ORDER_STR="CUSTOM3"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom3")%></th>
									<th class="sorting" RM_ORDER_STR="CUSTOM4"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom4")%></th>
									<th class="sorting" RM_ORDER_STR="CUSTOM5"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("custom5")%></th>
									<th class="sorting" RM_ORDER_STR="CUSTOM_XML"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("customXml")%></th>
									<th class="sorting" RM_ORDER_STR="USABLE_STATUS"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("usableStatus")%></th>
									<th class="sorting" RM_ORDER_STR="MODIFY_DATE"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%></th>
									<th class="sorting" RM_ORDER_STR="MODIFY_IP"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("modifyIp")%></th>
									<th class="sorting" RM_ORDER_STR="CREATE_TIME"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createTime")%></th>
									<th class="sorting" RM_ORDER_STR="CREATE_USER_NAME"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("createUserName")%></th>
									<th class="sorting" RM_ORDER_STR="ATTRIBUTE1"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute1")%></th>
									<th class="sorting" RM_ORDER_STR="ATTRIBUTE2"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute2")%></th>
									<th class="sorting" RM_ORDER_STR="ATTRIBUTE3"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute3")%></th>
									<th class="sorting" RM_ORDER_STR="ATTRIBUTE4"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute4")%></th>
									<th class="sorting" RM_ORDER_STR="ATTRIBUTE5"><%=IUserConstants.TABLE_COLUMN_DISPLAY.get("attribute5")%></th>

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
										<td><c:out value="${list.name}"></c:out></td>
										<td><c:out value="${list.lockStatus}"></c:out></td>
										<td><c:out value="${list.password}"></c:out></td>
										<td><c:out value="${list.authenType}"></c:out></td>
										<td><c:out value="${list.email}"></c:out></td>
										<td><c:out value="${list.adminType}"></c:out></td>
										<td><c:out value="${list.description}"></c:out></td>
										<td><c:out value="${list.agentStatus}"></c:out></td>
										<td><c:out value="${list.loginStatus}"></c:out></td>
										<td><c:out value="${list.lastLoginDate}"></c:out></td>
										<td><c:out value="${list.lastLoginIp}"></c:out></td>
										<td><c:out value="${list.loginSum}"></c:out></td>
										<td><c:out value="${list.lastCustomCss}"></c:out></td>
										<td><c:out value="${list.isAffix}"></c:out></td>
										<td><c:out value="${list.functionPermission}"></c:out></td>
										<td><c:out value="${list.dataPermission}"></c:out></td>
										<td><c:out value="${list.custom1}"></c:out></td>
										<td><c:out value="${list.custom2}"></c:out></td>
										<td><c:out value="${list.custom3}"></c:out></td>
										<td><c:out value="${list.custom4}"></c:out></td>
										<td><c:out value="${list.custom5}"></c:out></td>
										<td><c:out value="${list.customXml}"></c:out></td>
										<td><c:out value="${list.usableStatus}"></c:out></td>
										<td><c:out value="${list.modifyDate}"></c:out></td>
										<td><c:out value="${list.modifyIp}"></c:out></td>
										<td><c:out value="${list.createTime}"></c:out></td>
										<td><c:out value="${list.createUserName}"></c:out></td>
										<td><c:out value="${list.attribute1}"></c:out></td>
										<td><c:out value="${list.attribute2}"></c:out></td>
										<td><c:out value="${list.attribute3}"></c:out></td>
										<td><c:out value="${list.attribute4}"></c:out></td>
										<td><c:out value="${list.attribute5}"></c:out></td>
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
         var url="<%=request.getContextPath()%>/user";
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
             url: "<%=request.getContextPath()%>/user/get/" + updateDate+"/"+new Date(),
             dataType: "json",
             async:false , //false为同步
             success: function(data,status) {
     	   	   if(data.status=="0"){
     	   	       alert(data.message);
     	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=name]").val(data.bean.name);
                      $("#portlet-config-edit").find("input[id=lockStatus]").val(data.bean.lockStatus);
                      $("#portlet-config-edit").find("input[id=loginId]").val(data.bean.loginId);
                      $("#portlet-config-edit").find("input[id=password]").val(data.bean.password);
                      $("#portlet-config-edit").find("input[id=authenType]").val(data.bean.authenType);
                      $("#portlet-config-edit").find("input[id=organizationId]").val(data.bean.organizationId);
                      $("#portlet-config-edit").find("input[id=employeeId]").val(data.bean.employeeId);
                      $("#portlet-config-edit").find("input[id=email]").val(data.bean.email);
                      $("#portlet-config-edit").find("input[id=adminType]").val(data.bean.adminType);
                      $("#portlet-config-edit").find("input[id=description]").val(data.bean.description);
                      $("#portlet-config-edit").find("input[id=agentStatus]").val(data.bean.agentStatus);
                      $("#portlet-config-edit").find("input[id=loginStatus]").val(data.bean.loginStatus);
                      $("#portlet-config-edit").find("input[id=lastLoginDate]").val(data.bean.lastLoginDate);
                      $("#portlet-config-edit").find("input[id=lastLoginIp]").val(data.bean.lastLoginIp);
                      $("#portlet-config-edit").find("input[id=loginSum]").val(data.bean.loginSum);
                      $("#portlet-config-edit").find("input[id=lastCustomCss]").val(data.bean.lastCustomCss);
                      $("#portlet-config-edit").find("input[id=isAffix]").val(data.bean.isAffix);
                      $("#portlet-config-edit").find("input[id=functionPermission]").val(data.bean.functionPermission);
                      $("#portlet-config-edit").find("input[id=dataPermission]").val(data.bean.dataPermission);
                      $("#portlet-config-edit").find("input[id=custom1]").val(data.bean.custom1);
                      $("#portlet-config-edit").find("input[id=custom2]").val(data.bean.custom2);
                      $("#portlet-config-edit").find("input[id=custom3]").val(data.bean.custom3);
                      $("#portlet-config-edit").find("input[id=custom4]").val(data.bean.custom4);
                      $("#portlet-config-edit").find("input[id=custom5]").val(data.bean.custom5);
                      $("#portlet-config-edit").find("input[id=customXml]").val(data.bean.customXml);
                      $("#portlet-config-edit").find("input[id=usableStatus]").val(data.bean.usableStatus);
                      $("#portlet-config-edit").find("input[id=modifyDate]").val(data.bean.modifyDate);
                      $("#portlet-config-edit").find("input[id=modifyIp]").val(data.bean.modifyIp);
                      $("#portlet-config-edit").find("input[id=modifyUserId]").val(data.bean.modifyUserId);
                      $("#portlet-config-edit").find("input[id=createTime]").val(data.bean.createTime);
                      $("#portlet-config-edit").find("input[id=createUserName]").val(data.bean.createUserName);
                      $("#portlet-config-edit").find("input[id=attribute1]").val(data.bean.attribute1);
                      $("#portlet-config-edit").find("input[id=attribute2]").val(data.bean.attribute2);
                      $("#portlet-config-edit").find("input[id=attribute3]").val(data.bean.attribute3);
                      $("#portlet-config-edit").find("input[id=attribute4]").val(data.bean.attribute4);
                      $("#portlet-config-edit").find("input[id=attribute5]").val(data.bean.attribute5);
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
    if(request.getAttribute(IUserConstants.REQUEST_WRITE_BACK_FORM_VALUES) != null) {  //如果request中取出的表单回写bean不为空
        out.print(RmVoHelper.writeBackMapToForm((java.util.Map)request.getAttribute(IUserConstants.REQUEST_WRITE_BACK_FORM_VALUES)));  //输出表单回写方法的脚本
    }
 
%>
</script>
