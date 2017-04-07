<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="com.dfhc.rm.role.IRoleConstants" %>
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

<title><%=IRoleConstants.TABLE_NAME_DISPLAY%></title>
</head>
<body class="page-header-fixed" >
  <div class="page-content"  style="margin: 0px 0px 0px 0px;padding: 0px 0px;">
  <!-- begain 修改 -->
   <div id="portlet-config-edit" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>修改	<input type="button" class="btn blue" value="提交" style="float: right;" 
										 onclick="javascript:submitUpdateAjax('<%=request.getContextPath()%>/role/update',$(updateForm).serialize(),'<%=request.getContextPath()%>/role');"/></h3>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath()%>/role/update" id="updateForm" name="updateForm" method="post">
						<div class="control-group">
							<div class="controls">
							<input id="id" name="id" type="hidden" />

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("roleCode")%></span><input class="m-wrap" name="roleCode" id="roleCode" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("name")%></span><input class="m-wrap" name="name" id="name" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("enableStatus")%></span><%=RmJspHelper.getSelectField("enableStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_ENABLE_STATUS), "", "inputName='" + IRoleConstants.TABLE_COLUMN_DISPLAY.get("enableStatus") + "' validate='notNull;' ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("isSystemLevel")%></span><%=RmJspHelper.getSelectField("isSystemLevel", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IRoleConstants.TABLE_COLUMN_DISPLAY.get("isSystemLevel") + "' validate='notNull;' ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("ownerOrgId")%></span><input class="m-wrap" name="ownerOrgId" id="ownerOrgId" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("isRecursive")%></span><%=RmJspHelper.getSelectField("isRecursive", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IRoleConstants.TABLE_COLUMN_DISPLAY.get("isRecursive") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("matrixCode")%></span><input class="m-wrap" name="matrixCode" id="matrixCode" type="text"  						maxLength="1000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("description")%></span><input class="m-wrap" name="description" id="description" type="text"  						maxLength="1000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("functionPermission")%></span><input class="m-wrap" name="functionPermission" id="functionPermission" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("dataPermission")%></span><input class="m-wrap" name="dataPermission" id="dataPermission" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("usableStatus")%></span><input class="m-wrap" name="usableStatus" id="usableStatus" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%></span><input name="modifyDate" id="modifyDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyIp")%></span><input class="m-wrap" name="modifyIp" id="modifyIp" type="text"  						maxLength="45" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyUserId")%></span><input class="m-wrap" name="modifyUserId" id="modifyUserId" type="text"  						maxLength="20" />
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
								onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/role/insert',$(insertForm).serialize(),'<%=request.getContextPath()%>/role');"/></h3>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath()%>/role/insert" id="insertForm" name="insertForm" method="post">
						<div class="control-group">
							<div class="controls">
							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("roleCode")%></span><input class="m-wrap" name="roleCode"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("name")%></span><input class="m-wrap" name="name"  type="text"  						maxLength="200" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("enableStatus")%></span><%=RmJspHelper.getSelectField("enableStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_ENABLE_STATUS), "", "inputName='" + IRoleConstants.TABLE_COLUMN_DISPLAY.get("enableStatus") + "' validate='notNull;' ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("isSystemLevel")%></span><%=RmJspHelper.getSelectField("isSystemLevel", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IRoleConstants.TABLE_COLUMN_DISPLAY.get("isSystemLevel") + "' validate='notNull;' ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("ownerOrgId")%></span><input class="m-wrap" name="ownerOrgId"  type="text"  						maxLength="50" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("isRecursive")%></span><%=RmJspHelper.getSelectField("isRecursive", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IRoleConstants.TABLE_COLUMN_DISPLAY.get("isRecursive") + "'  ", true) %>							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("matrixCode")%></span><input class="m-wrap" name="matrixCode"  type="text"  						maxLength="1000" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("description")%></span><input class="m-wrap" name="description"  type="text"  						maxLength="1000" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("functionPermission")%></span><input class="m-wrap" name="functionPermission"  type="text"  						maxLength="4000" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("dataPermission")%></span><input class="m-wrap" name="dataPermission"  type="text"  						maxLength="4000" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("usableStatus")%></span><input class="m-wrap" name="usableStatus"  type="text"  						maxLength="1" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%></span><input name="modifyDate"  class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%>" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyIp")%></span><input class="m-wrap" name="modifyIp"  type="text"  						maxLength="45" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyUserId")%></span><input class="m-wrap" name="modifyUserId"  type="text"  						maxLength="20" />
							</div>

							</div>
						</div>
					</form>
				</div>
			</div>
	 <!-- end 新增 -->
	 
	 <!-- begain查询 -->
	<form action="<%=request.getContextPath()%>/role"	method="post" name="form" >
	<div id="portlet-config-search" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>查询<input type="submit" class="btn blue" value="查询" style="float: right;" /></h3>
				</div>
				<div class="modal-body">
						<div class="control-group">
							<div class="controls">

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("roleCode")%></span><input class="m-wrap" name="roleCode" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("name")%></span><input class="m-wrap" name="name" type="text"  						maxLength="200" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("enableStatus")%></span><%=RmJspHelper.getSelectField("enableStatus", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_ENABLE_STATUS), "", "inputName='" + IRoleConstants.TABLE_COLUMN_DISPLAY.get("enableStatus") + "' validate='notNull;' ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("isSystemLevel")%></span><%=RmJspHelper.getSelectField("isSystemLevel", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IRoleConstants.TABLE_COLUMN_DISPLAY.get("isSystemLevel") + "' validate='notNull;' ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("ownerOrgId")%></span><input class="m-wrap" name="ownerOrgId" type="text"  						maxLength="50" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("isRecursive")%></span><%=RmJspHelper.getSelectField("isRecursive", -1, RmGlobalReference.get(ISystemConstant.DICTIONARY_RM_YES_NOT), "", "inputName='" + IRoleConstants.TABLE_COLUMN_DISPLAY.get("isRecursive") + "'  ", true) %>							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("matrixCode")%></span><input class="m-wrap" name="matrixCode" type="text"  						maxLength="1000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("description")%></span><input class="m-wrap" name="description" type="text"  						maxLength="1000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("functionPermission")%></span><input class="m-wrap" name="functionPermission" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("dataPermission")%></span><input class="m-wrap" name="dataPermission" type="text"  						maxLength="4000" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("usableStatus")%></span><input class="m-wrap" name="usableStatus" type="text"  						maxLength="1" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%></span><input name="modifyDate" class="m-wrap m-ctrl-medium date-picker" readonly size="16" type="text" value="" inputName="<%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%>" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyIp")%></span><input class="m-wrap" name="modifyIp" type="text"  						maxLength="45" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyUserId")%></span><input class="m-wrap" name="modifyUserId" type="text"  						maxLength="20" />
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
					<li><a href="#"><%=IRoleConstants.TABLE_NAME_DISPLAY%></a></li>
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
										<a href="javascript:;" onclick="_delete('<%=request.getContextPath()%>/role/logicDelete');" class="halflings-icon white trash"></a>
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

									<th class="sorting" RM_ORDER_STR="ROLE_CODE"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("roleCode")%></th>
									<th class="sorting" RM_ORDER_STR="NAME"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("name")%></th>
									<th class="sorting" RM_ORDER_STR="ENABLE_STATUS"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("enableStatus")%></th>
									<th class="sorting" RM_ORDER_STR="IS_SYSTEM_LEVEL"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("isSystemLevel")%></th>
									<th class="sorting" RM_ORDER_STR="IS_RECURSIVE"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("isRecursive")%></th>
									<th class="sorting" RM_ORDER_STR="MATRIX_CODE"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("matrixCode")%></th>
									<th class="sorting" RM_ORDER_STR="DESCRIPTION"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("description")%></th>
									<th class="sorting" RM_ORDER_STR="FUNCTION_PERMISSION"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("functionPermission")%></th>
									<th class="sorting" RM_ORDER_STR="DATA_PERMISSION"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("dataPermission")%></th>
									<th class="sorting" RM_ORDER_STR="USABLE_STATUS"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("usableStatus")%></th>
									<th class="sorting" RM_ORDER_STR="MODIFY_DATE"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyDate")%></th>
									<th class="sorting" RM_ORDER_STR="MODIFY_IP"><%=IRoleConstants.TABLE_COLUMN_DISPLAY.get("modifyIp")%></th>

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
										<td><c:out value="${list.roleCode}"></c:out></td>
										<td><c:out value="${list.name}"></c:out></td>
										<td><c:out value="${list.enableStatus}"></c:out></td>
										<td><c:out value="${list.isSystemLevel}"></c:out></td>
										<td><c:out value="${list.isRecursive}"></c:out></td>
										<td><c:out value="${list.matrixCode}"></c:out></td>
										<td><c:out value="${list.description}"></c:out></td>
										<td><c:out value="${list.functionPermission}"></c:out></td>
										<td><c:out value="${list.dataPermission}"></c:out></td>
										<td><c:out value="${list.usableStatus}"></c:out></td>
										<td><c:out value="${list.modifyDate}"></c:out></td>
										<td><c:out value="${list.modifyIp}"></c:out></td>
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
         var url="<%=request.getContextPath()%>/role";
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
             url: "<%=request.getContextPath()%>/role/get/" + updateDate+"/"+new Date(),
             dataType: "json",
             async:false , //false为同步
             success: function(data,status) {
     	   	   if(data.status=="0"){
     	   	       alert(data.message);
     	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=roleCode]").val(data.bean.roleCode);
                      $("#portlet-config-edit").find("input[id=name]").val(data.bean.name);
                      $("#portlet-config-edit").find("input[id=enableStatus]").val(data.bean.enableStatus);
                      $("#portlet-config-edit").find("input[id=isSystemLevel]").val(data.bean.isSystemLevel);
                      $("#portlet-config-edit").find("input[id=ownerOrgId]").val(data.bean.ownerOrgId);
                      $("#portlet-config-edit").find("input[id=isRecursive]").val(data.bean.isRecursive);
                      $("#portlet-config-edit").find("input[id=matrixCode]").val(data.bean.matrixCode);
                      $("#portlet-config-edit").find("input[id=description]").val(data.bean.description);
                      $("#portlet-config-edit").find("input[id=functionPermission]").val(data.bean.functionPermission);
                      $("#portlet-config-edit").find("input[id=dataPermission]").val(data.bean.dataPermission);
                      $("#portlet-config-edit").find("input[id=usableStatus]").val(data.bean.usableStatus);
                      $("#portlet-config-edit").find("input[id=modifyDate]").val(data.bean.modifyDate);
                      $("#portlet-config-edit").find("input[id=modifyIp]").val(data.bean.modifyIp);
                      $("#portlet-config-edit").find("input[id=modifyUserId]").val(data.bean.modifyUserId);
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
    if(request.getAttribute(IRoleConstants.REQUEST_WRITE_BACK_FORM_VALUES) != null) {  //如果request中取出的表单回写bean不为空
        out.print(RmVoHelper.writeBackMapToForm((java.util.Map)request.getAttribute(IRoleConstants.REQUEST_WRITE_BACK_FORM_VALUES)));  //输出表单回写方法的脚本
    }
 
%>
</script>
