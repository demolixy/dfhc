<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/common/global.jsp"%>	
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include-base-styles.jsp" %>
	<%@ include file="/common/include-jquery-ui-theme.jsp" %>
	<%@ include file="/common/include-custom-styles.jsp" %>
	<title>流程列表</title>

	<script src="${ctx }/js/common/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="${ctx }/js/common/plugins/jui/jquery-ui-${themeVersion }.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function() {
    	$('#redeploy').button({
    		icons: {
    			primary: 'ui-icon-refresh'
    		}
    	});
    	$('#deploy').button({
    		icons: {
    			primary: 'ui-icon-document'
    		}
    	}).click(function() {
    		$('#deployFieldset').toggle('normal');
    	});
    });
    </script>
</head>
<body>
	<c:if test="${not empty message}">
	<div class="ui-widget">
			<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;"> 
				<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
				<strong>提示：</strong>${message}</p>
			</div>
		</div>
	</c:if>
	<fieldset id="deployFieldset">
		<legend>部署流程资源</legend>
		<span class="alert alert-info"><b>支持文件格式：</b>zip、bar、bpmn、bpmn20.xml</span>
		<form action="${ctx }//workflow/model/filedeploy" method="post" enctype="multipart/form-data" style="margin-top:1em;">
			<input type="file" name="file" />
			<input type="submit" value="Submit" class="btn" />
		</form>
		<hr class="soften" />
	</fieldset>
	<table width="100%" class="need-border">
		<thead>
			<tr>
				<th>ProcessDefinitionId</th>
				<th>DeploymentId</th>
				<th>名称</th>
				<th>KEY</th>
				<th>版本号</th>
				<th>XML</th>
				<th>图片</th>
				<th>部署时间</th>
				<th>是否挂起</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result }" var="process">
				<tr>
					<td>${process.id }</td>
					<td>${process.deploymentId }</td>
					<td>${process.name }</td>
					<td>${process.key }</td>
					<td>${process.version }</td>
					<td><a target="_blank" href='${ctx }/workflow/model/read-resource?pdid=${process.id}&resourceName=${process.resourceName }'>${process.resourceName }</a></td>
					<td><a target="_blank" href='${ctx }/workflow/model/read-resource?pdid=${process.id}&resourceName=${process.diagramResourceName}'>${process.diagramResourceName }</a></td>
					<td></td>
					<td>${process.suspended} |
						<c:if test="${process.suspended }">
							<a href="processdefinition/update/active/${process.id}">激活</a>
						</c:if>
						<c:if test="${!process.suspended }">
							<a href="processdefinition/update/suspend/${process.id}">挂起</a>
						</c:if>
					</td>
					<td>
                        <a href='${ctx }/workflow/model/deleteDeployment/${process.deploymentId}'>删除</a>
                        <a href='${ctx }/workflow/process/convert-to-model/${process.id}'>转换为Model</a>
                        <a href='${ctx }/workflow/model/start/${process.id}'>启动实例</a>
                    </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>