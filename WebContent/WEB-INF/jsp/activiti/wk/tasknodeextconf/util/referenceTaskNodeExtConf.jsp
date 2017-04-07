<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：qb5&activiti
   *
   * 文件名称：referenceTaskNodeExtConf.jsp
   *
   * 功能描述：任务节点扩展配置参照页面
   * 
   * 版本历史：
   * 2016-11-25   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>
<%@page import="org.quickbundle.tools.helper.RmVoHelper" %>
<%@page import="org.quickbundle.tools.helper.RmJspHelper"%>
<%@page import="org.quickbundle.project.RmGlobalReference"%>
<%@page import="com.dfhc.ISystemConstant" %>
<%@page import="org.quickbundle.base.web.page.RmPageVo"%>
<%@page import="com.dfhc.wk.tasknodeextconf.vo.TaskNodeExtConfVo" %>
<%@page import="com.dfhc.wk.tasknodeextconf.ITaskNodeExtConfConstants" %>
<%
    String referenceInputType = String.valueOf(request.getAttribute(ITaskNodeExtConfConstants.REQUEST_REFERENCE_INPUT_TYPE));
    if(referenceInputType == null || referenceInputType.length() == 0 || (!"checkbox".equals(referenceInputType.toLowerCase()) && !"radio".equals(referenceInputType.toLowerCase()))) {
        referenceInputType = "radio";       
    }
    //取出List
    List<TaskNodeExtConfVo> lResult = null;  //定义结果列表的List变量
    if(request.getAttribute(ITaskNodeExtConfConstants.REQUEST_BEANS) != null) {  //如果request中的beans不为空
        lResult = (List)request.getAttribute(ITaskNodeExtConfConstants.REQUEST_BEANS);  //赋值给resultList
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
         var url="<%=request.getContextPath()%>/tasknodeextconf/reference";
        sorting(url);
   });

    function simpleQuery_onClick(){  //简单的模糊查询
        form.action="<%=request.getContextPath()%>/tasknodeextconf/reference";
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
<form action="<%=request.getContextPath()%>/tasknodeextconf/reference" name="form"	method="post">
	<div id="portlet-config-search" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>查询<input type="submit" class="btn blue" style="float: right;" value="查询" /></h3>
				</div>
				<div class="modal-body">
					
						<div class="control-group">
							<div class="controls">

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%></span><input class="m-wrap" name="procDefId" type="text"  						maxLength="64" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskId")%></span><input class="m-wrap" name="taskId" type="text"  						maxLength="64" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskDefinitionKey")%></span><input class="m-wrap" name="taskDefinitionKey" type="text"  						maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScript")%></span><input class="m-wrap" name="readTaskDataScript" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScriptType")%></span><%=RmJspHelper.getSelectField("readTaskDataScriptType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_SCRIPT_TYPE), "", "inputName='" + ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScriptType") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScript")%></span><input class="m-wrap" name="writeTaskDataScript" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScriptType")%></span><input class="m-wrap" name="writeTaskDataScriptType" type="text"  						maxLength="1" />
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
					<li><a href="#"><%=ITaskNodeExtConfConstants.TABLE_NAME_DISPLAY%>参照</a></li>
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
									<th  tabindex="0" class="sorting" RM_ORDER_STR="ID" role="columnheader" aria-controls="sample_2" style="width: 273px;" aria-label="Browser: activate to sort column ascending" aria-sort="descending" rowspan="1" colspan="1"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("id")%></th>
									<th class="sorting" RM_ORDER_STR="PROC_DEF_ID"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%></th>
									<th class="sorting" RM_ORDER_STR="TASK_ID"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskId")%></th>
									<th class="sorting" RM_ORDER_STR="TASK_DEFINITION_KEY"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskDefinitionKey")%></th>
									<th class="sorting" RM_ORDER_STR="READ_TASK_DATA_SCRIPT"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScript")%></th>
									<th class="sorting" RM_ORDER_STR="READ_TASK_DATA_SCRIPT_TYPE"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScriptType")%></th>
									<th class="sorting" RM_ORDER_STR="WRITE_TASK_DATA_SCRIPT"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScript")%></th>
									<th class="sorting" RM_ORDER_STR="WRITE_TASK_DATA_SCRIPT_TYPE"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScriptType")%></th>

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
						          <td><c:out value="${list.taskId}"></c:out></td>
						          <td><c:out value="${list.taskDefinitionKey}"></c:out></td>
						          <td><c:out value="${list.readTaskDataScript}"></c:out></td>
						          <td><c:out value="${list.readTaskDataScriptType}"></c:out></td>
						          <td><c:out value="${list.writeTaskDataScript}"></c:out></td>
						          <td><c:out value="${list.writeTaskDataScriptType}"></c:out></td>
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
    if(request.getAttribute(ITaskNodeExtConfConstants.REQUEST_WRITE_BACK_FORM_VALUES) != null) {  //如果request中取出的表单回写bean不为空
        out.print(RmVoHelper.writeBackMapToForm((java.util.Map)request.getAttribute(ITaskNodeExtConfConstants.REQUEST_WRITE_BACK_FORM_VALUES)));  //输出表单回写方法的脚本
    }
%>
</script>
