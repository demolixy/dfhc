<%@page import="org.quickbundle.project.RmProjectHelper"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="org.quickbundle.project.RmGlobalReference"%>
<%@ page import="org.quickbundle.tools.helper.RmVoHelper" %>
<%@ page import="org.quickbundle.tools.helper.RmStringHelper" %>
<%@ page import="org.quickbundle.modules.log.rmlog.vo.RmLogVo" %>
<%@ page import="org.quickbundle.modules.log.rmlog.util.IRmLogConstants" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/jsp/cipher/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="qb.web_title"/></title>
<script type="text/javascript">
	function viewLog_onClick(){  //直接点到修改页面
		var logFileName = $("#selectLogFileName").val();
		if(isStrEmpty(logFileName)){
			alert("请选择日志文件名!");
			return;
		}
		form.action="<%=request.getContextPath()%>/fileLogMonitor/viewLogFile";
		form.submit();
	}
	function downLoadLog_onClick(){  //下载日志
		var logFileName = $("#selectLogFileName").val();
		if(isStrEmpty(logFileName)){
			alert("请选择日志文件名!");
			return;
		}
		window.location.href="<%=request.getContextPath()%>/fileLogMonitor/downLoadLogFile?selectLogFileName="+logFileName;
  }
</script>
</head>
<body>
<form name="form" method="post">
<table class="mainTable" align="center">
	<tr>
		<td align="right">日志文件名列表：</td>
		<td>
		<select name="selectLogFileName" id="selectLogFileName">
		<c:forEach items="${filenames}" var="item" >
		 <option value="${item.fullName}" >${item.shortName}</option>
		 </c:forEach>
		</select>&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	</table>


<table align="center">
	<tr>
		<td>
			<input type="button" class="button_ellipse" id="button_update" value="查看日志" onClick="javascript:viewLog_onClick();">
			<input type="button" class="button_ellipse" id="button_update" value="下载日志" onClick="javascript:downLoadLog_onClick();">
		</td>
	</tr>
</table>



</form>
</body>
</html>