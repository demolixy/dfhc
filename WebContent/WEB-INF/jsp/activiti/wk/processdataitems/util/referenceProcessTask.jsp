<%--
   * 软件著作权：东方汇创
   *
   * 系统名称：qb5&activiti
   *
   * 文件名称：referenceProcessDataItems.jsp
   *
   * 功能描述：流程数据项参照页面
   * 
   * 版本历史：
   * 2016-12-05   1.0.0版 （龙色波）（创建文件）
   * 
--%>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>
<%@page import="org.quickbundle.tools.helper.RmVoHelper" %>
<%@page import="org.quickbundle.tools.helper.RmJspHelper"%>
<%@page import="org.quickbundle.project.RmGlobalReference"%>
<%@page import="com.dfhc.ISystemConstant" %>
<%@page import="org.quickbundle.base.web.page.RmPageVo"%>
<%@page import="com.dfhc.wk.processdataitems.vo.ProcessDataItemsVo" %>
<%@page import="com.dfhc.wk.processdataitems.IProcessDataItemsConstants" %>
<%
    String referenceInputType = String.valueOf(request.getAttribute(IProcessDataItemsConstants.REQUEST_REFERENCE_INPUT_TYPE));
    if(referenceInputType == null || referenceInputType.length() == 0 || (!"checkbox".equals(referenceInputType.toLowerCase()) && !"radio".equals(referenceInputType.toLowerCase()))) {
        referenceInputType = "radio";       
    }
    //取出List
    List<ProcessDataItemsVo> lResult = null;  //定义结果列表的List变量
    if(request.getAttribute(IProcessDataItemsConstants.REQUEST_BEANS) != null) {  //如果request中的beans不为空
        lResult = (List)request.getAttribute(IProcessDataItemsConstants.REQUEST_BEANS);  //赋值给resultList
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
         var url="<%=request.getContextPath()%>/processdataitems/referenceProcessTask";
        sorting(url);
   });

    function simpleQuery_onClick(){  //简单的模糊查询
        form.action="<%=request.getContextPath()%>/processdataitems/referenceProcessTask";
        form.submit();
    }
    function refresh_onClick(){  //刷新本页
        form.submit();
    }
    function detail_onClick(thisId) {  //实现转到详细页面
        //参照页面默认不进去细览
    }
    var objKeys = ['displayName','isStart'];
</script>
</head>
<body class="page-header-fixed">
  <div class="page-content"  style="margin: 0px 0px 0px 0px;padding: 0px 0px;">
  <!-- begain查询 -->
<form action="<%=request.getContextPath()%>/processdataitems/referenceProcessTask" name="form"	method="post">
	<div id="portlet-config-search" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close  closeMargain" type="button"></button>
					<h3>查询<input type="submit" class="btn blue" style="float: right;" value="查询" /></h3>
				</div>
				<div class="modal-body">
					
						<div class="control-group">
							<div class="controls">

							<div class="input-prepend">
							  <span class="add-on">名称</span><input class="m-wrap" name="name" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on">KEY</span><input class="m-wrap" name="key" type="text"  						maxLength="100" />
							</div>

							<div class="input-prepend">
							  <span class="add-on">版本</span><input class="m-wrap" name="version" type="text"  						maxLength="100" />
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
					<li><a href="#">流程任务参照</a></li>
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
										<th>任务名称</th>
										<th>任务ID</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${beans}" var="list" varStatus="rowCounter"
												begin='0' step='1'>
								<tr>
								<td>
									<div class="checkbox" >
										<ins id="ischeck">
											<input type="hidden" name="checkbox_template" class="checkmain" ischecked="false"  displayName="${list.taskName}" isStart="${list.isStart}"  checkedId="<c:out value="${list.taskId}"></c:out>" />
										</ins>
									</div>
								</td>
						          <td><c:out value="${list.taskName}"></c:out></td>
						          <td><c:out value="${list.taskId}"></c:out></td>
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
    if(request.getAttribute(IProcessDataItemsConstants.REQUEST_WRITE_BACK_FORM_VALUES) != null) {  //如果request中取出的表单回写bean不为空
        out.print(RmVoHelper.writeBackMapToForm((java.util.Map)request.getAttribute(IProcessDataItemsConstants.REQUEST_WRITE_BACK_FORM_VALUES)));  //输出表单回写方法的脚本
    }
%>
</script>
