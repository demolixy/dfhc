<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="com.dfhc.wk.tasknodeextconf.ITaskNodeExtConfConstants" %>
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

<title><%=ITaskNodeExtConfConstants.TABLE_NAME_DISPLAY%></title>
</head>
<body class="page-header-fixed" >
  <div class="page-content"  style="margin: 0px 0px 0px 0px;padding: 0px 0px;">
  <!-- begain 修改 -->
   <div id="portlet-config-edit" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>修改	<input type="button" class="btn blue" value="提交" style="float: right;" 
										 onclick="javascript:submitUpdateAjax('<%=request.getContextPath()%>/tasknodeextconf/update',$(updateForm).serialize(),'<%=request.getContextPath()%>/tasknodeextconf');"/></h3>
				</div>
				<div class="modal-body" style="max-height: 500px;">
					<form action="<%=request.getContextPath()%>/tasknodeextconf/update" id="updateForm" name="updateForm" method="post">
						<div class="control-group">
							<div class="controls">
							<input id="id" name="id" type="hidden" />
							<input type="hidden" name="attribute1" id="attribute1"/>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%></span><input type="text" class="text_field_reference text_field_readonly" hiddenInputId="procDefId" name="procDefIdName" inputName="<%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%>" value="" /><input type="hidden" id="updateProcDefId" name="procDefId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getNewReference('<%=request.getContextPath()%>/processdataitems/referenceProcessDefinition?referenceInputType=radio','updateForm.procDefId,updateForm.procDefIdName,updateForm.taskDefinitionKey');"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskId")%></span><input type="text" class="text_field_reference text_field_readonly"   hiddenInputId="taskId" name="taskIdName" inputName="<%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskId")%>" value="" /><input type="hidden" name="taskId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:updateTaskReference();"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskDefinitionKey")%></span><input class="m-wrap" name="taskDefinitionKey" id="taskDefinitionKey" type="text" readonly="readonly" maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScriptType")%></span><%=RmJspHelper.getSelectField("readTaskDataScriptType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_SCRIPT_TYPE), "", "inputName='" + ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScriptType") + "'  ", true) %>							</div>
		
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScript")%></span>
							  <textarea rows="10" cols="30" name="readTaskDataScript" style="margin: 0px 0px 10px; width: 456px; height: 200px;"></textarea>
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScriptType")%></span><%=RmJspHelper.getSelectField("writeTaskDataScriptType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_SCRIPT_TYPE), "", "inputName='" + ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScriptType") + "'  ", true) %>							</div>
		
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScript")%></span>
							  <textarea rows="10" cols="30" name="writeTaskDataScript" style="margin: 0px 0px 10px; width: 456px; height: 200px;"></textarea>
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
								onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/tasknodeextconf/insert',$(insertForm).serialize(),'<%=request.getContextPath()%>/tasknodeextconf');"/></h3>
				</div>
				<div class="modal-body" style="max-height: 500px;">
					<form action="<%=request.getContextPath()%>/tasknodeextconf/insert" id="insertForm" name="insertForm" method="post">
						<div class="control-group">
							<div class="controls">
							<input type="hidden" name="attribute1" />

							
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%></span><input type="text" class="text_field_reference text_field_readonly" hiddenInputId="procDefId" name="procDefIdName" inputName="<%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%>" value="" /><input type="hidden" id="addProcDefId" name="procDefId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getNewReference('<%=request.getContextPath()%>/processdataitems/referenceProcessDefinition?referenceInputType=radio','insertForm.procDefId,insertForm.procDefIdName,insertForm.taskDefinitionKey');"/>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskId")%></span><input type="text" class="text_field_reference text_field_readonly"   hiddenInputId="taskId" name="taskIdName" inputName="<%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskId")%>" value="" /><input type="hidden" name="taskId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:addTaskReference();"/>
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskDefinitionKey")%></span><input class="m-wrap" name="taskDefinitionKey"  type="text" readonly="readonly"	maxLength="255" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScriptType")%></span><%=RmJspHelper.getSelectField("readTaskDataScriptType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_SCRIPT_TYPE), "", "inputName='" + ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScriptType") + "'  ", true) %>							</div>
							
							
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScript")%></span>
							  <textarea rows="10" cols="30" name="readTaskDataScript" style="margin: 0px 0px 10px; width: 456px; height: 200px;"></textarea>
							  <%-- <input class="m-wrap" name="readTaskDataScript"  type="text"  />--%>
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScriptType")%></span><%=RmJspHelper.getSelectField("writeTaskDataScriptType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_SCRIPT_TYPE), "", "inputName='" + ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScriptType") + "'  ", true) %>							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScript")%></span>
							  <textarea rows="10" cols="30" name="writeTaskDataScript" style="margin: 0px 0px 10px; width: 456px; height: 200px;"></textarea>
							  <%-- <input class="m-wrap" name="writeTaskDataScript"  type="text"  />--%>
							</div>

							</div>
						</div>
					</form>
				</div>
			</div>
	 <!-- end 新增 -->
	 
	 <!-- begain查询 -->
	<form action="<%=request.getContextPath()%>/tasknodeextconf"	method="post" name="form" >
	<div id="portlet-config-search" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>查询<input type="submit" class="btn blue" value="查询" style="float: right;" /></h3>
				</div>
				<div class="modal-body">
						<div class="control-group">
							<div class="controls">

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%></span><input type="text" class="text_field_reference text_field_readonly" hiddenInputId="procDefId" name="procDefIdName" inputName="<%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("procDefId")%>" value="" /><input type="hidden" name="procDefId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getNewReference('<%=request.getContextPath()%>/processdataitems/referenceProcessDefinition?referenceInputType=radio','form.procDefId,form.procDefIdName,form.taskDefinitionKey');"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskId")%></span><input type="text" class="text_field_reference text_field_readonly"   hiddenInputId="taskId" name="taskIdName" inputName="<%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskId")%>" value="" /><input type="hidden" name="taskId"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getNewReference('<%=request.getContextPath()%>/processdataitems/referenceProcessTask?referenceInputType=radio','form.taskId,form.taskIdName');"/>
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskDefinitionKey")%></span><input class="m-wrap" name="taskDefinitionKey" type="text"  						maxLength="255" />
							</div>
							
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScriptType")%></span><%=RmJspHelper.getSelectField("readTaskDataScriptType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_SCRIPT_TYPE), "", "inputName='" + ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScriptType") + "'  ", true) %>							</div>
							<%-- 
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScript")%></span><input class="m-wrap" name="readTaskDataScript" type="text"  />
							</div>
							--%>
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScriptType")%></span><%=RmJspHelper.getSelectField("writeTaskDataScriptType", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_SCRIPT_TYPE), "", "inputName='" + ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScriptType") + "'  ", true) %>							</div>
							<%-- 
							<div class="input-prepend">
							  <span class="add-on"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScript")%></span><input class="m-wrap" name="writeTaskDataScript" type="text"  />
							</div>
							--%>
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
					<li><a href="#"><%=ITaskNodeExtConfConstants.TABLE_NAME_DISPLAY%></a></li>
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
										<a href="javascript:;" onclick="_delete('<%=request.getContextPath()%>/tasknodeextconf/logicDelete');" class="halflings-icon white trash"></a>
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

									<th class="sorting" RM_ORDER_STR="TASK_DEFINITION_KEY"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("taskDefinitionKey")%></th>
									<th class="sorting" RM_ORDER_STR="READ_TASK_DATA_SCRIPT_TYPE"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScriptType")%></th>
									<th class="sorting" RM_ORDER_STR="READ_TASK_DATA_SCRIPT"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("readTaskDataScript")%></th>
									<th class="sorting" RM_ORDER_STR="WRITE_TASK_DATA_SCRIPT_TYPE"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScriptType")%></th>
									<th class="sorting" RM_ORDER_STR="WRITE_TASK_DATA_SCRIPT"><%=ITaskNodeExtConfConstants.TABLE_COLUMN_DISPLAY.get("writeTaskDataScript")%></th>

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
										<td><c:out value="${list.taskDefinitionKey}"></c:out></td>
										<bean:define id="readTaskDataScriptType" name="list" property="readTaskDataScriptType"/>
										<td><%=RmGlobalReference.get(ISystemConstant.DICTIONARY_SCRIPT_TYPE, readTaskDataScriptType) %></td>
										<td><c:out value="${list.readTaskDataScript}"></c:out></td>
										<bean:define id="writeTaskDataScriptType" name="list" property="writeTaskDataScriptType"/>
										<td><%=RmGlobalReference.get(ISystemConstant.DICTIONARY_SCRIPT_TYPE, writeTaskDataScriptType) %></td>
										<td><c:out value="${list.writeTaskDataScript}"></c:out></td>
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
         var url="<%=request.getContextPath()%>/tasknodeextconf";
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
             url: "<%=request.getContextPath()%>/tasknodeextconf/get/" + updateDate+"/"+new Date(),
             dataType: "json",
             async:false , //false为同步
             success: function(data,status) {
     	   	   if(data.status=="0"){
     	   	       alert(data.message);
     	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=attribute1]").val(data.bean.attribute1);
                      $("#portlet-config-edit").find("input[name=procDefId]").val(data.bean.procDefId);
                      $("#portlet-config-edit").find("input[name=procDefIdName]").val(data.bean.procDefId);
                      $("#portlet-config-edit").find("input[id=taskId]").val(data.bean.taskId);
                      $("#portlet-config-edit").find("input[name=taskIdName]").val(data.bean.taskId);
                      $("#portlet-config-edit").find("input[id=taskDefinitionKey]").val(data.bean.taskDefinitionKey);
                      $("#portlet-config-edit").find("textarea[name=readTaskDataScript]").val(data.bean.readTaskDataScript);
                      $("#portlet-config-edit").find("select[name=readTaskDataScriptType]").val(data.bean.readTaskDataScriptType);
                      $("#portlet-config-edit").find("textarea[name=writeTaskDataScript]").val(data.bean.writeTaskDataScript);
                      $("#portlet-config-edit").find("select[name=writeTaskDataScriptType]").val(data.bean.writeTaskDataScriptType);
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
	function addTaskReference(){
		var addProcDefId = $("#addProcDefId").val();
		if(addProcDefId==""){
			alert("请先选择流程定义");
			return false;
		}
		getNewReference('<%=request.getContextPath()%>/processdataitems/referenceProcessTask?referenceInputType=radio&procDefId='+addProcDefId,'insertForm.taskId,insertForm.taskIdName,insertForm.attribute1');
    }
    
    function updateTaskReference(){
    	var addProcDefId = $("#updateProcDefId").val();
		if(addProcDefId==""){
			alert("请先选择流程定义");
			return false;
		}
    	getNewReference('<%=request.getContextPath()%>/processdataitems/referenceProcessTask?referenceInputType=radio&procDefId='+addProcDefId,'updateForm.taskId,updateForm.taskIdName,updateForm.attribute1');
    }
	</script>		
</body>
</html>
<script type="text/javascript">
<%  //表单回写
    if(request.getAttribute(ITaskNodeExtConfConstants.REQUEST_WRITE_BACK_FORM_VALUES) != null) {  //如果request中取出的表单回写bean不为空
        out.print(RmVoHelper.writeBackMapToForm((java.util.Map)request.getAttribute(ITaskNodeExtConfConstants.REQUEST_WRITE_BACK_FORM_VALUES)));  //输出表单回写方法的脚本
    }
 
%>
</script>
