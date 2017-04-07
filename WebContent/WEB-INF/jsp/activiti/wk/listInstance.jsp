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

<title>流程实例列表</title>
</head>
<body class="page-header-fixed" >
	<c:if test="${not empty message}">
	<div class="ui-widget">
			<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;"> 
				<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
				<strong>提示：</strong>${message}</p>
			</div>
		</div>
	</c:if>
<form action="<%=request.getContextPath()%>/workflow/model/listinstance"	method="post" name="form" >
  <div class="page-content"  style="margin: 0px 0px 0px 0px;padding: 0px 0px;">
     <div class="container-fluid">
	   <div class="row-fluid" style="margin: 0 0px;padding: 0px 0px;">
			<div class="span12">
			  <ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="index.html">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">xx模块</a> <i class="icon-angle-right"></i></li>
					<li><a href="#">流程实例列表</a></li>
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
						</div>
						<div class="portlet-body no-more-tables" style="overflow: auto;" >
							<table class=" table-striped table-condensed cf table-hover table table-bordered">
								<thead>
									<tr>
										<th>序号</th>
										<th>执行ID</th>
										<th>流程实例ID</th>
										<th>流程定义ID</th>
										<th>当前节点</th>
										<th>是否挂起</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${beans}" var="list" varStatus="rowCounter"	begin='0' step='1'>
										<tr>
											<td><c:out value="${rowCounter.index+1}"></c:out></td>
											<td>${list.id }</td>
											<td>${list.processInstanceId }</td>
											<td>${list.processDefinitionId }</td>
											<td><a class="trace" href="${ctx }/workflow/diagram-viewer/index.html?processDefinitionId=${list.processDefinitionId}&processInstanceId=${list.processInstanceId}" >流程监控</a></td>
											<td>${list.suspended }</td>
											<td>
												<c:if test="${list.suspended }">
													<a href="update/active/${list.processInstanceId}">激活</a>
												</c:if>
												<c:if test="${!list.suspended }">
													<a href="update/suspend/${list.processInstanceId}">挂起</a>
												</c:if>
											</td>
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
	</div>
	</form>	
 <script type="text/javascript">
	//根据字段排序
	$(function(){
         var RM_ORDER_STR='${RM_ORDER_STR}';//当前排序值字段
         orderBy(RM_ORDER_STR);
         var url="<%=request.getContextPath()%>/workflow/model/listinstance";
        sorting(url);
        });
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
