<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="com.dfhc.wk.formtemplate.IFormTemplateConstants" %>
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

<title><%=IFormTemplateConstants.TABLE_NAME_DISPLAY%></title>
</head>
<body class="page-header-fixed" >
  <div class="page-content"  style="margin: 0px 0px 0px 0px;padding: 0px 0px;">
  <!-- begain 修改 -->
   <div id="portlet-config-edit" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>修改	<input type="button" class="btn blue" value="提交" style="float: right;" 
										 onclick="javascript:submitUpdateAjax('<%=request.getContextPath()%>/formtemplate/update',$(updateForm).serialize(),'<%=request.getContextPath()%>/formtemplate');"/></h3>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath()%>/formtemplate/update" id="updateForm" name="updateForm" method="post">
						<div class="control-group">
							<div class="controls">
							<input id="id" name="id" type="hidden" />

							<div class="input-prepend">
							  <span class="add-on"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateName")%></span><input class="m-wrap" name="templateName" id="templateName" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateHtml")%></span><input class="m-wrap" name="templateHtml" id="templateHtml" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateFreemark")%></span><input class="m-wrap" name="templateFreemark" id="templateFreemark" type="text"  />
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
								onclick="javascript:submitInsertAjax('<%=request.getContextPath()%>/formtemplate/insert',$(insertForm).serialize(),'<%=request.getContextPath()%>/formtemplate');"/></h3>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath()%>/formtemplate/insert" id="insertForm" name="insertForm" method="post">
						<div class="control-group">
							<div class="controls">
							

							
							<div class="input-prepend">
							  <span class="add-on"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateName")%></span><input class="m-wrap" name="templateName"  type="text"  						maxLength="300" />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateHtml")%></span><input class="m-wrap" name="templateHtml"  type="text"  />
							</div>

							
							<div class="input-prepend">
							  <span class="add-on"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateFreemark")%></span><input class="m-wrap" name="templateFreemark"  type="text"  />
							</div>

							</div>
						</div>
					</form>
				</div>
			</div>
	 <!-- end 新增 -->
	 
	 <!-- begain查询 -->
	<form action="<%=request.getContextPath()%>/formtemplate"	method="post" name="form" >
	<div id="portlet-config-search" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>查询<input type="submit" class="btn blue" value="查询" style="float: right;" /></h3>
				</div>
				<div class="modal-body">
						<div class="control-group">
							<div class="controls">

							<div class="input-prepend">
							  <span class="add-on"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateName")%></span><input class="m-wrap" name="templateName" type="text"  						maxLength="300" />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateHtml")%></span><input class="m-wrap" name="templateHtml" type="text"  />
							</div>

							<div class="input-prepend">
							  <span class="add-on"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateFreemark")%></span><input class="m-wrap" name="templateFreemark" type="text"  />
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
					<li><a href="#"><%=IFormTemplateConstants.TABLE_NAME_DISPLAY%></a></li>
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
										<a href="javascript:;" onclick="_delete('<%=request.getContextPath()%>/formtemplate/logicDelete');" class="halflings-icon white trash"></a>
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
									<th class="sorting" RM_ORDER_STR="ID"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("id")%></th>
									<th class="sorting" RM_ORDER_STR="TEMPLATE_NAME"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateName")%></th>
									<th class="sorting" RM_ORDER_STR="TEMPLATE_HTML"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateHtml")%></th>
									<th class="sorting" RM_ORDER_STR="TEMPLATE_FREEMARK"><%=IFormTemplateConstants.TABLE_COLUMN_DISPLAY.get("templateFreemark")%></th>

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
										<td><c:out value="${list.id}"></c:out></td>
										<td><c:out value="${list.templateName}"></c:out></td>
										<td>
											<a href="${pageContext.request.contextPath }/formtemplate/gotoTemplateEditor?id=${list.id}">编辑</a>
										</td>
										<td><a href="${pageContext.request.contextPath }/formtemplate/gotoTemplateEditor?id=${list.id}&type=template">编辑</a></td>
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
         var url="<%=request.getContextPath()%>/formtemplate";
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
             url: "<%=request.getContextPath()%>/formtemplate/get/" + updateDate+"/"+new Date(),
             dataType: "json",
             async:false , //false为同步
             success: function(data,status) {
     	   	   if(data.status=="0"){
     	   	       alert(data.message);
     	   	   }else{
                      $("#portlet-config-edit").find("input[id=id]").val(data.bean.id);
                      $("#portlet-config-edit").find("input[id=templateName]").val(data.bean.templateName);
                      $("#portlet-config-edit").find("input[id=templateHtml]").val(data.bean.templateHtml);
                      $("#portlet-config-edit").find("input[id=templateFreemark]").val(data.bean.templateFreemark);
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
    if(request.getAttribute(IFormTemplateConstants.REQUEST_WRITE_BACK_FORM_VALUES) != null) {  //如果request中取出的表单回写bean不为空
        out.print(RmVoHelper.writeBackMapToForm((java.util.Map)request.getAttribute(IFormTemplateConstants.REQUEST_WRITE_BACK_FORM_VALUES)));  //输出表单回写方法的脚本
    }
 
%>
</script>
