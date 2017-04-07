<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：qb5&activiti
   *
   * 文件名称：referenceGeneralBusinessProcess.jsp
   *
   * 功能描述：通用流程业务表参照页面
   * 
   * 版本历史：
   * 2016-12-26   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>
<%@page import="org.quickbundle.tools.helper.RmVoHelper" %>
<%@page import="org.quickbundle.tools.helper.RmJspHelper"%>
<%@page import="org.quickbundle.project.RmGlobalReference"%>
<%@page import="com.dfhc.ISystemConstant" %>
<%@page import="org.quickbundle.base.web.page.RmPageVo"%>
<%@page import="com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo" %>
<%@page import="com.dfhc.wk.generalbusinessprocess.IGeneralBusinessProcessConstants" %>
<%
    String referenceInputType = String.valueOf(request.getAttribute(IGeneralBusinessProcessConstants.REQUEST_REFERENCE_INPUT_TYPE));
    if(referenceInputType == null || referenceInputType.length() == 0 || (!"checkbox".equals(referenceInputType.toLowerCase()) && !"radio".equals(referenceInputType.toLowerCase()))) {
        referenceInputType = "radio";       
    }
    //取出List
    List<GeneralBusinessProcessVo> lResult = null;  //定义结果列表的List变量
    if(request.getAttribute(IGeneralBusinessProcessConstants.REQUEST_BEANS) != null) {  //如果request中的beans不为空
        lResult = (List)request.getAttribute(IGeneralBusinessProcessConstants.REQUEST_BEANS);  //赋值给resultList
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/jsp/cipher/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<title><bean:message key="qb.web_title"/></title>
<script type="text/javascript">
   //根据字段排序
   $(function(){
         var RM_ORDER_STR='${RM_ORDER_STR}';//当前排序值字段
         orderBy(RM_ORDER_STR);
         var url="<%=request.getContextPath()%>/generalbusinessprocess/reference";
        sorting(url);
   });

    function simpleQuery_onClick(){  //简单的模糊查询
        form.action="<%=request.getContextPath()%>/generalbusinessprocess/reference";
        form.submit();
    }
    function refresh_onClick(){  //刷新本页
        form.submit();
    }
    function detail_onClick(thisId) {  //实现转到详细页面
        //参照页面默认不进去细览
    }
</script>
</head>
<body class="page-header-fixed">
  <div class="page-content"  style="margin: 0px 0px 0px 0px;padding: 0px 0px;">
  <!-- begain查询 -->
<form action="<%=request.getContextPath()%>/generalbusinessprocess/reference" name="form"	method="post">
	<div id="portlet-config-search" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>查询<input type="submit" class="btn blue" style="float: right;" value="查询" /></h3>
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
					<li><i class="icon-home"></i> <a href="#">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">xx模块</a> <i class="icon-angle-right"></i></li>
					<li><a href="#"><%=IGeneralBusinessProcessConstants.TABLE_NAME_DISPLAY%>参照</a></li>
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
							</div>
						</div>

						<div class="portlet-body no-more-tables dataTables_wrapper form-inline" style="overflow: auto;" >
							<table class="table table-striped table-bordered table-hover table-full-width dataTable" aria-describedby="sample_2_info">
								<thead>
									<tr>
									<th style="width: 40px" tabindex="0"  role="columnheader" aria-controls="sample_2" style="width: 190px;" aria-label="Rendering engine: activate to sort column ascending" rowspan="1" colspan="1">选择</th>
									<th  tabindex="0" class="sorting" RM_ORDER_STR="ID" role="columnheader" aria-controls="sample_2" style="width: 273px;" aria-label="Browser: activate to sort column ascending" aria-sort="descending" rowspan="1" colspan="1"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("id")%></th>
									<th class="sorting" RM_ORDER_STR="PROC_DEF_ID"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%></th>
									<th class="sorting" RM_ORDER_STR="PROC_INST_ID"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procInstId")%></th>
									<th class="sorting" RM_ORDER_STR="PROC_NAME"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procName")%></th>
									<th class="sorting" RM_ORDER_STR="PROC_KEY"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("procKey")%></th>
									<th class="sorting" RM_ORDER_STR="INITIATOR"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiator")%></th>
									<th class="sorting" RM_ORDER_STR="INITIATOR_ID"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorId")%></th>
									<th class="sorting" RM_ORDER_STR="INITIATOR_ORGANIZATION_DEPARTMENT"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("sendOrganizationDepartment")%></th>
									<th class="sorting" RM_ORDER_STR="INITIATOR_ORGANIZATION_ID"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorOrganizationId")%></th>
									<th class="sorting" RM_ORDER_STR="INITIATOR_TIME"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("initiatorTime")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_TYPE"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessType")%></th>
									<th class="sorting" RM_ORDER_STR="BUSINESS_GROUP"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("businessGroup")%></th>
									<th class="sorting" RM_ORDER_STR="PARENT_ID"><%=IGeneralBusinessProcessConstants.TABLE_COLUMN_DISPLAY.get("parentId")%></th>
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
									<c:forEach items="${beans}" var="list" varStatus="rowCounter"
												begin='0' step='1'>
								<tr>
								<td>
									<div class="checkbox" >
										<ins id="ischeck">
											<input type="hidden" name="checkbox_template"  displayName="${list.id}" class="checkmain" ischecked="false"  displayName="${list.id}"  checkedId="<c:out value="${list.id}"></c:out>" />
										</ins>
									</div>
								</td>
						          <td><c:out value="${list.id}"></c:out></td>
						          <td><c:out value="${list.procDefId}"></c:out></td>
						          <td><c:out value="${list.procInstId}"></c:out></td>
						          <td><c:out value="${list.procName}"></c:out></td>
						          <td><c:out value="${list.procKey}"></c:out></td>
						          <td><c:out value="${list.initiator}"></c:out></td>
						          <td><c:out value="${list.initiatorId}"></c:out></td>
						          <td><c:out value="${list.sendOrganizationDepartment}"></c:out></td>
						          <td><c:out value="${list.initiatorOrganizationId}"></c:out></td>
						          <td><c:out value="${list.initiatorTime}"></c:out></td>
						          <td><c:out value="${list.businessType}"></c:out></td>
						          <td><c:out value="${list.businessGroup}"></c:out></td>
						          <td><c:out value="${list.parentId}"></c:out></td>
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
							<%-- 下边这句是翻页, 如果去掉就不带翻页了,同时注意Action中也要调整方法 --%>
							<jsp:include page="/jsp/include/page.jsp"></jsp:include>							
						</div>
					</div>
				</div>
			</div>
		</div>	
        



<input type="hidden" name="id" value=""/>
<input type="hidden" name="queryCondition" value=""/>
<input type="hidden" name="referenceInputType" value="<%=referenceInputType%>"/>

<input type="hidden" id="rmCheckReturnValue" name="rmCheckReturnValue" value=""/>
<input type="hidden" id="rmCheckReturnName" name="rmCheckReturnName" value=""/>
<input type="hidden" name="RM_ORDER_STR" value="${RM_ORDER_STR}">
</form>
</div>
</body>
</html>
<script type="text/javascript">
<%  //表单回写
    if(request.getAttribute(IGeneralBusinessProcessConstants.REQUEST_WRITE_BACK_FORM_VALUES) != null) {  //如果request中取出的表单回写bean不为空
        out.print(RmVoHelper.writeBackMapToForm((java.util.Map)request.getAttribute(IGeneralBusinessProcessConstants.REQUEST_WRITE_BACK_FORM_VALUES)));  //输出表单回写方法的脚本
    }
%>
</script>
